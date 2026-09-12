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
        boolean running = true;
        while (running) {
            System.out.println("============================================");
            System.out.println("1.Quan ly Department");
            System.out.println("2.Quan ly Position");
            System.out.println("3.Quan ly Account");
            System.out.println("4.Thoat.");
            System.out.println("============================================");
            System.out.print("Hay chon chuc nang: ");
            String choose = scanner.nextLine();

            switch (choose) {
                case "1":
                    menuDepartment();
                    break;
                case "2":
                    menuPosition();
                    break;
                case "3":
                    menuAccount();
                    break;
                case "4":
                    System.exit(0);
                default:
                    System.out.println("Nhap sai! Nhap lai.");
            }
        }
    }

    private void menuDepartment() {
        boolean back = false;
        while (!back) {
            System.out.println("===== QUAN LY DEPARTMENT =====");
            System.out.println("1. Hien thi danh sach Department");
            System.out.println("2. Them Department");
            System.out.println("3. Xoa Department theo id");
            System.out.println("4. Sua department_name theo id");
            System.out.println("5. Quay lai menu chinh");
            System.out.println("===========================");
            System.out.print("Hay chon chuc nang: ");

            String choose = scanner.nextLine();
            switch (choose) {
                case "1":
                    qlDepartment.hienThiDepartment();
                    break;
                case "2":
                    qlDepartment.themDepartment();
                    break;
                case "3":
                    qlDepartment.xoaDepartmentTheoId();
                    break;
                case "4":
                    qlDepartment.suaDepartmentTheoId();
                    break;
                case "5":
                    back = true;
                    break;
                default:
                    System.out.println("Nhap sai! Nhap lai.");
            }
        }
    }

    private void menuPosition() {
        boolean back = false;
        while (!back) {
            System.out.println("===== QUAN LY POSITION =====");
            System.out.println("1. Hien thi danh sach Position");
            System.out.println("2. Them Position");
            System.out.println("3. Xoa Position theo id");
            System.out.println("4. Sua position_name theo id");
            System.out.println("5. Quay lai menu chinh");
            System.out.println("===========================");
            System.out.print("Hay chon chuc nang: ");

            String choose = scanner.nextLine();
            switch (choose) {
                case "1":
                    qlPosition.hienThiPosition();
                    break;
                case "2":
                    qlPosition.themPosition();
                    break;
                case "3":
                    qlPosition.xoaPositionTheoId();
                    break;
                case "4":
                    qlPosition.suaPositionTheoId();
                    break;
                case "5":
                    back = true;
                    break;
                default:
                    System.out.println("Nhap sai! Nhap lai.");
            }
        }
    }

    private void menuAccount() {
        boolean back = false;
        while (!back) {
            System.out.println("===== QUAN LY ACCOUNT =====");
            System.out.println("1. Hien thi danh sach Account");
            System.out.println("2. Them Account");
            System.out.println("3. Xoa Account theo id");
            System.out.println("4. Sua username theo id");
            System.out.println("5. Quay lai menu chinh");
            System.out.println("===========================");
            System.out.print("Hay chon chuc nang: ");

            String choose = scanner.nextLine();
            switch (choose) {
                case "1":
                    qlAccount.hienThiAccount();
                    break;
                case "2":
                    qlAccount.themAccount();
                    break;
                case "3":
                    qlAccount.xoaAccountTheoId();
                    break;
                case "4":
                    qlAccount.suaAccountTheoId();
                    break;
                case "5":
                    back = true;
                    break;
                default:
                    System.out.println("Nhap sai! Nhap lai.");
            }
        }
    }
}