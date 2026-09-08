package Frontend;

import Backend.IQLTV;
import Backend.QLTV;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Main main = new Main();
        main.menu();
    }

    public void menu(){
        IQLTV iqltv = new QLTV();
        while (true){
            System.out.println("=====Quan ly thu vien=====");
            System.out.println("1. Them moi tai lieu.");
            System.out.println("2. Xoa tai lieu theo ma tai lieu.");
            System.out.println("3. Hien thi thong tin ve tai lieu.");
            System.out.println("4. Tim kiem tai lieu theo loai: sach, tap chi, bao.");
            System.out.println("5. Thoat chuong trinh.");
            System.out.println("==========================");
            System.out.print("Moi ban chon chuc nang: ");
            String choose = scanner.nextLine();
            switch (choose){
                case "1" :
                    iqltv.themMoiTaiLieu();
                    break;
                case "2" :
                    iqltv.xoaTheoMa();
                    break;
                case "3" :
                    iqltv.hienThiTaiLieu();
                    break;
                case "4" :
                    iqltv.timKiemTheoLoai();
                    break;
                case "5" :
                    System.exit(0);
                default:
                    System.out.println("Nhap sai! Moi ban nhap lai");
            }
        }
    }
}