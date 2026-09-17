package frontend;

import backend.controller.QLNSController;
import entity.Account;
import entity.Department;
import entity.Gender;
import entity.Position;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Function {
    private Scanner scanner;
    private QLNSController controller;


    public Function(){
        scanner = new Scanner(System.in);
        controller = new QLNSController();
    }

    public String checkLength(int min, int max) {
        while (true) {
            String text = scanner.nextLine();
            if (text.trim().length() < min || text.trim().length() > max) {
                System.err.println(String.format("Vui lòng nhập từ %d đến %d kí tự!\n", min, max));
                continue;
            }
            return text;
        }
    }

    public void hienthi(){
        System.out.println("===== Hien thi toan bo account =====");
        List<Account> accounts = controller.getAccounts();
        this.show(accounts);
    }
    public void themAccount(){
        String email;
        while (true){
            System.out.println("Nhap email: ");
            email = this.checkLength(6,100);
            boolean checkEmailExist = controller.checkEmailExist(email);
            if (checkEmailExist) {// true -> báo lỗi
                System.err.println("Mã tài liệu này đã tồn tại. Nhập lại!");
                continue;
            }
            String EMAIL_REGEX = "^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$";
            if (!email.trim().matches(EMAIL_REGEX)) {
                System.err.println("Email khong dung dinh dang! (vd: a@gmail.com)");
                continue;
            }
            break;
        }

        String userName;
        while (true){
            System.out.println("Nhap username: ");
            userName = this.checkLength(6,100);
            boolean checkUserNameExist = controller.checkUserNameExist(userName);
            if (checkUserNameExist) {// true -> báo lỗi
                System.err.println("Mã tài liệu này đã tồn tại. Nhập lại!");
                continue;
            }
            break;
        }

        System.out.println("Nhap ho ten: ");
        String fullName = this.checkLength(6,100);

        System.out.println("Chon gender: 1. Male    2. Female    other.Other");
        Gender gender;
        int choose = Integer.parseInt(scanner.nextLine().trim());
        switch (choose) {
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            default:
                gender = Gender.OTHER;
                break;
        }


        Department department;
        while (true) {
            List<Department> departments = controller.getDepartments();
            System.out.println("===== Danh sach phong ban =====");
            for (Department department1 : departments) {
                System.out.printf("Ma phong ban: %d - Ten phong ban: %s\n", department1.getDepartmentId(), department1.getDepartmentName());
            }
            System.out.println("Chon ma phong ban: ");
            if (scanner.hasNextInt()) {
                int choiceDep = scanner.nextInt();
                scanner.nextLine();
                department = departments.stream()
                        .filter(department1 -> department1.getDepartmentId() == choiceDep)
                        .findFirst().orElse(null);
                if (Objects.isNull(department)) {
                    System.out.println("Chon sai. Chon lai!");
                } else {
                    break;
                }
            } else {
                System.out.println("Chon sai. Chon lai!");
                scanner.nextLine();
            }
        }

        Position position;
        while (true) {
            List<Position> positions = controller.getPositions();
            System.out.println("===== Danh sach chuc vu =====");
            for (Position position1 : positions) {
                System.out.printf("Ma chuc vu: %d - Ten chuc vu: %s\n", position1.getPositionId(), position1.getPositionName());
            }
            System.out.println("Chon ma chuc vu: ");
            if (scanner.hasNextInt()) {
                int choicePos = scanner.nextInt();
                scanner.nextLine();
                position = positions.stream()
                        .filter(position1 -> position1.getPositionId() == choicePos)
                        .findFirst().orElse(null);
                if (Objects.isNull(position)) {
                    System.out.println("Chon sai. Chon lai!");
                } else {
                    break;
                }
            } else {
                System.out.println("Chon sai. Chon lai!");
                scanner.nextLine();
            }
        }

        Account account = new Account();
        account.setEmail(email);
        account.setUserName(userName);
        account.setFullName(fullName);
        account.setGender(gender);
        account.setDepartment(department);
        account.setPosition(position);
        try {
            boolean check = controller.themAccount(account);
            if (check) {
                System.out.println("Them tai khoan thanh cong!");
            } else {
                System.out.println("Them tai khoan khong thanh cong!");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void sua(){
        int accountId;
        while (true){
            System.out.println("Nhap ma dinh danh can sua: ");
            if (scanner.hasNextInt()){
                accountId = Integer.parseInt(scanner.nextLine().trim());
                if (!controller.checkAccountIdExist(accountId)) {
                    System.err.println("Ma dinh danh khong ton tai. Nhap lai!");
                    continue;
                }
                break;
            }else
                System.out.println("Ma dinh danh phai la so. Nhap lai!");
        }

        String userName;
        while (true) {
            System.out.println("Nhap username moi: ");
            userName = this.checkLength(6, 100);
            boolean checkUserNameExist = controller.checkUserNameExist(userName);
            if (checkUserNameExist) {// true -> báo lỗi
                System.err.println("Username da ton tai. Nhap lai!");
                continue;
            }
            break;
        }

        Department department;
        while (true) {
            List<Department> departments = controller.getDepartments();
            System.out.println("===== Danh sach phong ban =====");
            for (Department department1 : departments) {
                System.out.printf("Ma phong ban: %d - Ten phong ban: %s\n", department1.getDepartmentId(), department1.getDepartmentName());
            }
            System.out.println("Chon ma phong ban: ");
            if (scanner.hasNextInt()) {
                int choiceDep = scanner.nextInt();
                scanner.nextLine();
                department = departments.stream()
                        .filter(department1 -> department1.getDepartmentId() == choiceDep)
                        .findFirst().orElse(null);
                if (Objects.isNull(department)) {
                    System.out.println("Chon sai. Chon lai!");
                } else {
                    break;
                }
            } else {
                System.out.println("Chon sai. Chon lai!");
                scanner.nextLine();
            }
        }

        Position position;
        while (true) {
            List<Position> positions = controller.getPositions();
            System.out.println("===== Danh sach chuc vu =====");
            for (Position position1 : positions) {
                System.out.printf("Ma chuc vu: %d - Ten chuc vu: %s\n", position1.getPositionId(), position1.getPositionName());
            }
            System.out.println("Chon ma chuc vu: ");
            if (scanner.hasNextInt()) {
                int choicePos = scanner.nextInt();
                scanner.nextLine();
                position = positions.stream()
                        .filter(position1 -> position1.getPositionId() == choicePos)
                        .findFirst().orElse(null);
                if (Objects.isNull(position)) {
                    System.out.println("Chon sai. Chon lai!");
                } else {
                    break;
                }
            } else {
                System.out.println("Chon sai. Chon lai!");
                scanner.nextLine();
            }
        }
        boolean check = controller.suaTheoAccId(accountId, userName, department.getDepartmentId(), position.getPositionId());
        if(check){
            System.out.println("Sua thanh cong!");
        }else
            System.out.println("Sua that bai!");
    }

    public void xoa(){
        int accountId;
        while (true){
            System.out.println("Nhap ma dinh danh can xoa: ");
            accountId = Integer.parseInt(scanner.nextLine().trim());
            boolean checkAccountIdExist = controller.checkAccountIdExist(accountId);
            if (checkAccountIdExist)
                break;
            else
                System.out.println(" Ma dinh danh nay khong ton tai!");

            boolean check = controller.xoaTheoAccId(accountId);
            if(check){
                System.out.println("Xoa thanh cong!");
            }else
                System.out.println("Xoa that bai!");
        }
    }

    public void show(List<Account> accounts) {
        System.out.println("+---------------+-------------------------+---------------+-------------------------+---------------+---------------+-------------------------+---------------+-------------------------+---------------+");
        System.out.printf("|%15s|%25s|%15s|%25s|%15s|%15s|%25s|%15s|%25s|%15s|\n", "Ma dinh danh", "Email", "Ten", "Ten day du", "Gioi tinh", "Ma phong ban", "Ten phong ban", "Ma chuc vu", "Ten chuc vu", "Ngay tao");
        System.out.println("+---------------+-------------------------+---------------+-------------------------+---------------+---------------+-------------------------+---------------+-------------------------+---------------+");
        if (accounts.size() > 0) {
            for (Account account : accounts) {
                System.out.printf("|%15d|%25s|%15s|%25s|%15s|%15d|%25s|%15d|%25s|%15s|\n",
                        account.getAccountId(), account.getEmail(), account.getUserName(), account.getFullName(),
                        account.getGender(), account.getDepartment().getDepartmentId(), account.getDepartment().getDepartmentName(),
                        account.getPosition().getPositionId(), account.getPosition().getPositionName(), account.getCreateDate());
            }
        } else {
            System.out.printf("|%77s|\n", "Khong co thong tin");
        }
        System.out.println("+---------------+-------------------------+---------------+-------------------------+---------------+---------------+-------------------------+---------------+-------------------------+---------------+");
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
                    break;
                case "2":
                    this.themAccount();
                    break;
                case "3":
                    this.xoa();
                    break;
                case "4":
                    this.sua();
                    break;
                case "5":
                    System.exit(0);
                    break;
                default:System.out.println("Nhap sai! Nhap lai");
            }
        }
    }
}
