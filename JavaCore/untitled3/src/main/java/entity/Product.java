package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    private int productId;                  // Mã sản phẩm
    private String productName;             // Tên sản phẩm (duy nhất)
    private String productPrice;            // Giá (lưu dạng chuỗi, VD: 10.000.000 ₫)
    private String productInfo;             // Thông tin tóm tắt
    private String productDetail;           // Chi tiết (có thể null)
    private Integer ratingStar;      // Số sao đánh giá (có thể null)
    private String productImageName;        // Tên file ảnh
    private int manufacturerId;      // Khóa ngoại nhà sản xuất
    private Manufacturer manufacturer; // Tên nhà sản xuất (lấy từ JOIN, dùng khi hiển thị)
    private Category category;

}
