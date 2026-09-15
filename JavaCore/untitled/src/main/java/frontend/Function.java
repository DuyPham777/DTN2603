package frontend;

import Utils.JDBCUtils;
import backend.controller.QLNSController;
import entity.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Function {
    private Scanner scanner;
    private QLNSController controller;


    public Function(){
        scanner = new Scanner(System.in);
        controller = new QLNSController();
    }

    public void hienthi(){
        System.out.println("===== Hien thi toan bo account =====");
        List<Account> accounts = controller.getAccounts();
        this.show(accounts);
    }
    public void themAccount(){
        System.out.println("Nhap email: ");
        String email = scanner.nextLine();
        System.out.println("Nhap username: ");
        String userName = scanner.nextLine();
        System.out.println("Nhap ho ten: ");
        String fullName = scanner.nextLine();
        System.out.println("Nhap ma phong ban: ");
        int departmentId = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Nhap ma chuc vu: ");
        int positionId = Integer.parseInt(scanner.nextLine().trim());
        boolean check = controller.themAccount(email, userName, fullName, departmentId, positionId);
        if (check) {
            System.out.println("Them tai khoan thanh cong!");
        } else {
            System.out.println("Them tai khoan khong thanh cong!");
        }
    }
    public void sua(){
        System.out.println("Nhap ma tai lieu can sua: ");
        int accountId = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Nhap username moi: ");
        String userName = scanner.nextLine();
        System.out.println("Nhap ma phong ban moi: ");
        int departmentId = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Nhap ma chuc vu moi: ");
        int positionId = Integer.parseInt(scanner.nextLine().trim());
        boolean check = controller.suaTheoAccId(accountId, userName, departmentId, positionId);
        if(check){
            System.out.println("Sua thanh cong!");
        }else
            System.out.println("Sua that bai!");
    }

    public void xoa(){
        System.out.println("Nhap ma dinh danh can xoa: ");
        int accountId = Integer.parseInt(scanner.nextLine());
        boolean check = controller.xoaTheoAccId(accountId);
        if(check){
            System.out.println("Xoa thanh cong!");
        }else
            System.out.println("Xoa that bai!");
    }

    public void show(List<Account> accounts){
        System.out.println("+---------------+-------------------------+---------------+-------------------------+---------------+---------------+---------------+");
        System.out.printf("|%15s|%25s|%15s|%25s|%15s|%15s|%15s|\n", "Ma dinh danh", "Email", "Ten", "Ten day du", "Ma phong ban", "Ma chuc vu", "Ngay tao");
        System.out.println("+---------------+-------------------------+---------------+-------------------------+---------------+---------------+---------------+");
        if (accounts.size() > 0) {
            for (Account account : accounts) {
                System.out.printf("|%15d|%25s|%15s|%25s|%15d|%15d|%15s|\n",
                        account.getAccountId(), account.getEmail(), account.getUserName(),
                        account.getFullName(), account.getDepartment().getDepartmentId(), account.getPosition().getPositionId(),
                        account.getCreateDate());
            }
        } else {
            System.out.printf("|%77s|\n", "Khong co thong tin");
        }
        System.out.println("+---------------+-------------------------+---------------+-------------------------+---------------+---------------+---------------+");
    }

    public void menu() {
        while (true) {
            System.out.println("===== QUAN LY ACCOUNT =====");
            System.out.println("1. Hien thi danh sach Account");
            System.out.println("2. Them Account");
            System.out.println("3. Xoa Account theo id");
            System.out.println("4. Sua username theo id");
            System.out.println("5. Quay lai menu chinh");
            System.out.println("===========================");
            System.out.print("Hay chon chuc nang: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    this.hienthi();
                    continue;
                case "2":
                    this.themAccount();
                    continue;
                case "3":
                    this.xoa();
                    continue;
                case "4":
                    this.sua();
                    continue;
                case "5":
                    System.exit(0);
                default:System.out.println("Nhap sai! Nhap lai"); continue;
            }
        }
    }
}
