package frontend;

import backend.controller.CategoryController;
import backend.controller.ManufacturerController;
import backend.controller.ProductController;
import common.StringCommon;
import entity.Category;
import entity.Manufacturer;
import entity.Product;
import utils.ScannerUtils;

import java.util.List;

public class Function {
    private final ManufacturerController manufacturerController;
    private final CategoryController categoryController;
    private final ProductController productController;

    public Function() {
        productController = new ProductController();
        categoryController = new CategoryController();
        manufacturerController = new ManufacturerController();
    }

    /** Menu chính của chương trình. */
    public void menu() {
        while (true) {
            System.out.println("\n===== Mời bạn chọn chức năng =====");
            System.out.println(" 1. Tìm kiếm nhà sản xuất theo ID");
            System.out.println(" 2. Xem danh sách thông tin sản phẩm trên hệ thống");
            System.out.println(" 3. Xóa sản phẩm theo ID");
            System.out.println(" 4. Cập nhật tên sản phẩm đang có");
            System.out.println(" 5. Thêm mới sản phẩm");
            System.out.println(" 6. Chức năng kiểm tra email");
            System.out.println(" 7. Exit");
            System.out.println("==================================");
            System.out.print("Hãy chọn chức năng: ");
            int choice = ScannerUtils.inputFunction(1, 7);
            switch (choice) {
                case 1:
                    findManufacturerById();
                    break;
                case 2:
                    hienThiSanPham();
                    break;
                case 3:
                    xoaSanPham();
                    break;
                case 4:
                    capNhatTenSanPham();
                    break;
                case 5:
                    themSanPham();
                    break;
                case 6:
                    kiemTraEmail();
                    break;
                case 7:
                    System.out.println("Tạm biệt!");
                    return;
            }
        }
    }

    /** Tìm nhà sản xuất theo id người dùng nhập. */
    public void findManufacturerById() {
        System.out.print("Nhập ID nhà sản xuất: ");
        int id = ScannerUtils.inputInt();
        Manufacturer manufacturer = manufacturerController.findManufacturerById(id);
        if (manufacturer == null) {
            System.out.println("Không tìm thấy nhà sản xuất có id = " + id);
            return;
        }
        System.out.println("Id   : " + manufacturer.getManufacturerId());
        System.out.println("Tên  : " + manufacturer.getManufacturerName());
    }

    /** Hiển thị danh sách sản phẩm: Id, Tên, Giá, Chi tiết, Số sao, Nhà sản xuất. */
    public void hienThiSanPham() {
        List<Product> products = productController.getProducts();
        System.out.println("+------+--------------------------------+-----------------+--------------------+------+--------------+");
        System.out.printf("| %-4s | %-30s | %-15s | %-18s | %-4s | %-12s |%n",
                "Id", "Tên", "Giá", "Chi tiết", "Sao", "Nhà SX");
        System.out.println("+------+--------------------------------+-----------------+--------------------+------+--------------+");
        if (products.isEmpty()) {
            System.out.println(">> Chưa có sản phẩm nào.");
        }
        for (Product p : products) {
            System.out.printf("| %-4d | %-30s | %-15s | %-18s | %-4d | %-12s |%n",
                    p.getProductId(), p.getProductName(), p.getProductPrice(),
                    p.getProductDetail(), p.getRatingStar(),
                    p.getManufacturer().getManufacturerName());
        }
        System.out.println("+------+--------------------------------+-----------------+--------------------+------+--------------+");
    }

    /** Xóa sản phẩm theo id. */
    public void xoaSanPham() {
        System.out.print("Nhập id sản phẩm cần xóa: ");
        int id = ScannerUtils.inputInt();
        if (!productController.isProductIdExist(id)) {
            System.out.println("Sản phẩm có id = " + id + " không tồn tại!");
            return;
        }
        if (productController.xoaProductTheoId(id)) {
            System.out.println("Xóa sản phẩm thành công!");
        } else {
            System.out.println("Xóa sản phẩm thất bại!");
        }
    }

    /** Cập nhật tên sản phẩm theo id. */
    public void capNhatTenSanPham() {
        int id;
        while (true) {
            System.out.print("Nhập id sản phẩm cần cập nhật: ");
            id = ScannerUtils.inputInt();
            if (productController.isProductIdExist(id)) {
                break;
            }
            System.err.println("Id sản phẩm không tồn tại. Nhập lại!");
        }

        String newName;
        while (true) {
            newName = nhapChuoi("Nhập tên mới: ", 50);
            if (productController.isProductNameExist(newName)) {
                System.err.println("Tên sản phẩm đã tồn tại. Nhập lại!");
                continue;
            }
            break;
        }

        if (productController.suaProductNameTheoId(id, newName)) {
            System.out.println("Cập nhật thành công!");
        } else {
            System.out.println("Cập nhật thất bại!");
        }
    }

    /** Thêm mới sản phẩm (kiểm tra trùng tên trước khi thêm). */
    public void themSanPham() {
        String name;
        while (true) {
            name = nhapChuoi("Nhập tên sản phẩm: ", 50);
            if (productController.isProductNameExist(name)) {
                System.err.println("Tên sản phẩm \"" + name + "\" đã tồn tại trên hệ thống. Nhập lại!");
                continue;
            }
            break;
        }

        String price = nhapChuoi("Nhập giá: ", 50);
        String info = nhapChuoi("Nhập thông tin sản phẩm: ", 200);
        String detail = nhapChuoi("Nhập chi tiết sản phẩm: ", 500);
        System.out.print("Nhập số sao đánh giá (1-5): ");
        int ratingStar = ScannerUtils.inputFunction(1, 5);
        String imageName = nhapChuoi("Nhập tên file ảnh: ", 50);

        Manufacturer manufacturer = chonNhaSanXuat();
        Category category = chonLoaiSanPham();

        Product product = new Product();
        product.setProductName(name);
        product.setProductPrice(price);
        product.setProductInfo(info);
        product.setProductDetail(detail);
        product.setRatingStar(ratingStar);
        product.setProductImageName(imageName);
        product.setManufacturer(manufacturer);
        product.setCategory(category);

        if (productController.themProduct(product)) {
            System.out.println("Thêm sản phẩm thành công!");
        } else {
            System.out.println("Thêm sản phẩm thất bại!");
        }
    }

    /** Kiểm tra email hợp lệ theo EMAIL_REGEX (xem StringCommon). */
    public void kiemTraEmail() {
        System.out.print("Nhập email: ");
        String email = ScannerUtils.inputString().trim();
        if (email.matches(StringCommon.EMAIL_REGEX)) {
            System.out.println("Email hợp lệ.");
        } else {
            System.out.println("Email không hợp lệ.");
        }
    }

    /** Nhập chuỗi không rỗng, tối đa maxLength ký tự (khớp độ dài cột trong DB). */
    private String nhapChuoi(String message, int maxLength) {
        while (true) {
            System.out.print(message);
            String text = ScannerUtils.inputString().trim();
            if (text.length() <= maxLength) {
                return text;
            }
            System.err.println("Tối đa " + maxLength + " ký tự. Nhập lại!");
        }
    }

    /** Hiển thị danh sách nhà sản xuất và cho chọn một nhà sản xuất. */
    private Manufacturer chonNhaSanXuat() {
        List<Manufacturer> manufacturers = manufacturerController.getManufacturers();
        while (true) {
            System.out.println("===== Danh sách nhà sản xuất =====");
            for (Manufacturer m : manufacturers) {
                System.out.printf("Mã: %d - Tên: %s%n", m.getManufacturerId(), m.getManufacturerName());
            }
            System.out.print("Chọn mã nhà sản xuất: ");
            int choice = ScannerUtils.inputInt();
            for (Manufacturer m : manufacturers) {
                if (m.getManufacturerId() == choice) {
                    return m;
                }
            }
            System.err.println("Chọn sai. Chọn lại!");
        }
    }

    /** Hiển thị danh sách loại sản phẩm và cho chọn một loại. */
    private Category chonLoaiSanPham() {
        List<Category> categories = categoryController.getCategories();
        while (true) {
            System.out.println("===== Danh sách loại sản phẩm =====");
            for (Category c : categories) {
                System.out.printf("Mã: %d - Tên: %s%n", c.getCategoryId(), c.getCategoryName());
            }
            System.out.print("Chọn mã loại sản phẩm: ");
            int choice = ScannerUtils.inputInt();
            for (Category c : categories) {
                if (c.getCategoryId() == choice) {
                    return c;
                }
            }
            System.err.println("Chọn sai. Chọn lại!");
        }
    }
}
