package Backend;

import Entity.Bao;
import Entity.Sach;
import Entity.TaiLieu;
import Entity.TapChi;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLTV implements IQLTV{
    private List<TaiLieu> taiLieus = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public QLTV(){
        TaiLieu sach = new Sach("S-001", "Kim Dong", 100, "Vo Quang", 1000);
        TaiLieu tapChi = new TapChi("TL-001", "Kim Dong", 100, "ISSN 0317-8417", YearMonth.of(2004,9));
        TaiLieu bao = new Bao("B-001", "Kim Dong", 100, LocalDate.of(2008, 10, 12));
        taiLieus.add(sach);
        taiLieus.add(tapChi);
        taiLieus.add(bao);
    }

    @Override
    public void themMoiTaiLieu() {
        System.out.print("Nhap ma tai lieu: ");
        String maTaiLieu = scanner.nextLine();
        System.out.print("Nhap ten nha xuat ban: ");
        String tenNhaXB = scanner.nextLine();
        System.out.print("Nhap so ban phat hanh: ");
        int soBanPhatHanh = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Chon loai tai lieu: 1. Sach     2. Tap chi     3. Bao");
        String chooseTL = scanner.nextLine();
        switch (chooseTL){
            case "1":
                System.out.print("Nhap ten tac gia: ");
                String tenTG = scanner.nextLine();
                System.out.print("Nhap so trang: ");
                int soTrang = scanner.nextInt();
                scanner.nextLine();
                TaiLieu sach = new Sach(maTaiLieu, tenNhaXB, soBanPhatHanh, tenTG, soTrang);
                taiLieus.add(sach);
                System.out.println("Tao sach thanh cong!");
                break;
            case "2":
                System.out.println("Nhap so phat hanh: ");
                String soPhatHanh = scanner.nextLine();
                System.out.println("Nhap thang phat hanh (yyyy/MM): ");
                String thangPhatHanhStr = scanner.nextLine().trim();
                YearMonth thangPhatHanh = YearMonth.parse(thangPhatHanhStr);
                scanner.nextLine();
                TaiLieu tapChi = new TapChi(maTaiLieu, tenNhaXB, soBanPhatHanh, soPhatHanh, thangPhatHanh);
                taiLieus.add(tapChi);
                System.out.println("Tao tap chi thanh cong!");
                break;
            default:
                System.out.println("Nhap ngay phat hanh (yyyy/MM/dd): ");
                String ngayPhatHanhStr = scanner.nextLine().trim();
                LocalDate ngayPhatHanh = LocalDate.parse(ngayPhatHanhStr, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                scanner.nextLine();
                TaiLieu bao = new Bao(maTaiLieu, tenNhaXB, soBanPhatHanh, ngayPhatHanh);
                taiLieus.add(bao);
                System.out.println("Tao bao thanh cong!");
                break;
        }
    }

    @Override
    public void xoaTheoMa() {
        System.out.println("Nhap ma tai lieu muon xoa: ");
        String maTaiLieu = scanner.nextLine();
        boolean daXoa = taiLieus.removeIf(taiLieu -> taiLieu.getMaTaiLieu().equalsIgnoreCase(maTaiLieu));
        if (daXoa) {
            System.out.println("Da xoa tai lieu co ma la: " + maTaiLieu);
        } else {
            System.out.println("Khong tim thay tai lieu co ma la: " + maTaiLieu);
        }
    }

    @Override
    public void hienThiTaiLieu() {
        System.out.println("+---------------+--------------------+--------------------+");
        System.out.printf("|%15s|%20s|%20s|\n", "Ma tai lieu", "Ten nha xuat ban", "So ban phat hanh");
        System.out.println("+---------------+--------------------+--------------------+");
        for (TaiLieu taiLieu : taiLieus){
            System.out.printf("|%15s|%20s|%20s|\n", taiLieu.getMaTaiLieu(), taiLieu.getTenNhaXB(), taiLieu.getSoBanPhatHanh());
        }
        System.out.println("+---------------+--------------------+--------------------+");
    }

    @Override
    public void timKiemTheoLoai() {
        System.out.println("Nhap loai tai lieu can tim: ");
        System.out.println("1. Sach.");
        System.out.println("2. Tap chi.");
        System.out.println("3. Bao.");
        String chooseLoai = scanner.nextLine();
        switch (chooseLoai){
            case "1":
                System.out.println("+---------------+--------------------+--------------------+--------------------+---------------+");
                System.out.printf("|%15s|%20s|%20s|%20s|%15s|\n", "Ma tai lieu", "Ten nha xuat ban", "So ban phat hanh", "Ten tac gia", "So trang");
                System.out.println("+---------------+--------------------+--------------------+--------------------+---------------+");
                for (TaiLieu taiLieu : taiLieus){
                    if (taiLieu instanceof Sach){
                        Sach sach = (Sach) taiLieu;
                        System.out.printf("|%15s|%20s|%20s|%20s|%15s|\n", sach.getMaTaiLieu(), sach.getTenNhaXB(), sach.getSoBanPhatHanh(), sach.getTenTacGia(), sach.getSoTrang());
                    }
                }
                System.out.println("+---------------+--------------------+--------------------+--------------------+---------------+");
                break;
            case "2":
                System.out.println("+---------------+--------------------+--------------------+--------------------+--------------------+");
                System.out.printf("|%15s|%20s|%20s|%20s|%20s|\n", "Ma tai lieu", "Ten nha xuat ban", "So ban phat hanh", "So phat hanh", "Thang phat hanh");
                System.out.println("+---------------+--------------------+--------------------+--------------------+--------------------+");
                for (TaiLieu taiLieu : taiLieus){
                    if (taiLieu instanceof TapChi){
                        TapChi tapChi = (TapChi) taiLieu;
                        System.out.printf("|%15s|%20s|%20s|%20s|%20s|\n", tapChi.getMaTaiLieu(), tapChi.getTenNhaXB(), tapChi.getSoBanPhatHanh(), tapChi.getSoPhatHanh(), tapChi.getThangPhatHanh());
                    }
                }
                System.out.println("+---------------+--------------------+--------------------+--------------------+--------------------+");
                break;
            default:
                System.out.println("+---------------+--------------------+--------------------+--------------------+");
                System.out.printf("|%15s|%20s|%20s|%20s|\n", "Ma tai lieu", "Ten nha xuat ban", "So ban phat hanh", "Ngay phat hanh");
                System.out.println("+---------------+--------------------+--------------------+--------------------+");
                for (TaiLieu taiLieu : taiLieus){
                    if (taiLieu instanceof Bao){
                        Bao bao = (Bao) taiLieu;
                        System.out.printf("|%15s|%20s|%20s|%20s|\n", bao.getMaTaiLieu(), bao.getTenNhaXB(), bao.getSoBanPhatHanh(), bao.getNgayPhatHanh());
                    }
                }
                System.out.println("+---------------+--------------------+--------------------+--------------------+");
                break;
        }

    }
}
