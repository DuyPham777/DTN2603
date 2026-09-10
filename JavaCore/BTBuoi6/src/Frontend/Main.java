package Frontend;

import Backend.QLAccount;
import Backend.QLDepartment;
import Backend.QLPosition;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    QLDepartment qlDepartment = new QLDepartment();
    QLPosition qlPosition = new QLPosition();
    QLAccount qlAccount = new QLAccount();
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Main main = new Main();
        main.menu();
    }

    public void menu() {
        while (true) {
            System.out.println("============================================");
            System.out.println("1.Hien thi danh sach Department");
            System.out.println("2.Hien thi danh sach Position");
            System.out.println("3.Hien thi danh sach Account");
            System.out.println("4.thoát.");
            System.out.println("============================================");
            System.out.print("Mời bạn chọn chức năng: ");
            String choose = scanner.nextLine();
            switch (choose) {
                case "1":
                    qlDepartment.hienThiDepartment();
                    break;
                case "2":
                    qlPosition.hienThiPosition();
                    break;
                case "3":
                    qlAccount.hienThiAccount();
                    break;
                case "4":
                    System.exit(0);
                default:
                    System.out.println("Nhap sai! Nhap lai.");
            }
        }
    }
}