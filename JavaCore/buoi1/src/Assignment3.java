import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Assignment3 {
    public  static void E1q1(){
        float luongAcc1 = 5240.5f;
        float luongAcc2 =  10970.055f;
        int lamTronAcc1 = (int) luongAcc1;
        int lamTronAcc2 = (int) luongAcc2;
        System.out.println("Luong cua Account 1 sau khi lam tron la" + lamTronAcc1 + "$");
        System.out.println("Luong cua Account 2 sau khi lam tron la" + lamTronAcc2 + "$");
    }

    public static void E1q2(){
        Random random = new Random();
        int number = random.nextInt(100000);
        System.out.printf("%05d", number);
    }

    public static void E1q3(){
        Random random = new Random();
        int number = random.nextInt(100000);
        System.out.printf("%05d", number);
        int last2num = number % 100;
        System.out.printf("2 so cuoi la: " + "%02d", last2num);
    }

    public static void E1q4(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap so a :");
        int a = scanner.nextInt();
        System.out.println("Nhap so b :");
        int b = scanner.nextInt();
        System.out.println("Thuong cua a va b la :" + (float)a/b);
    }

    public static void E2q1(){
        Account[] accounts = new Account[5];
        for (int i = 0; i < accounts.length; i++){
            String email = "Email: " + (i + 1);
            String userName = "User name: " + (i + 1);
            String fullName = "Full name: " + (i + 1);
            LocalDate createDate = LocalDate.now();
            accounts[i] = new Account(email, userName, fullName, createDate);
        }
        for (Account acc : accounts) {
            System.out.println("Email: " + acc.getEmail() +
                    ", Username: " + acc.getUserName() +
                    ", FullName: " + acc.getFullName() +
                    ", CreateDate: " + acc.getCreateDate());
        }
    }

    public static void E3q1(){
        Integer luong = 5000;
        float luongf = luong;
        System.out.printf("%.2f", luongf);
    }

    public static void E3q2(){
        String number = "1234567";
        int numberi = Integer.parseInt(number);
        System.out.println(numberi);
    }

    public static void E3q3(){
        Integer number = Integer.valueOf("123456");
        int numberi = number;
        System.out.println(numberi);
    }

    public static void E4q1(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap xau ky tu: ");
        String kyTu = scanner.nextLine();
        int dem = 0;
        String[] arr = kyTu.trim().split(" +");
        System.out.println("So tu la: " + arr.length);
    }

    public static void E4q2(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap xau ky tu 1: ");
        String kyTu1 = scanner.nextLine();
        System.out.println("Nhap xau ky tu 2: ");
        String kyTu2 = scanner.nextLine();
        System.out.println(kyTu1 + kyTu2);
    }

    public static void E4q3(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập Tên: ");
        String ten = scanner.nextLine();
        String first = ten.substring(0, 1).toUpperCase();
        String rest = ten.substring(1);
        ten = first + rest;
        System.out.println(ten);
    }

    public static void E4q4() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap ten: ");
        String ten = scanner.nextLine();
        for (int i = 0; i < ten.length(); i++){
            String kyTu = ten.substring(i, i + 1);
            System.out.println("Ký tự thứ " + (i + 1) + " là: " + kyTu);
        }
    }

    public static void E4q5(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap ho: ");
        String ho = scanner.nextLine();
        System.out.println("Nhap ten: ");
        String ten = scanner.nextLine();
        System.out.println("Ho va ten cua ban la: " + ho + ten);
    }

    public static void E4q6(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap ho ten :");
        String hoTen = scanner.nextLine().trim();
        String[] parts = hoTen.split("\\s+");
        if (parts.length < 2) {
            System.out.println("Vui long nhap day du ho va ten!");
        } else {
            String ho = parts[0];
            String ten = parts[parts.length - 1];

            String tenDem = "";
            for (int i = 1; i < parts.length - 1; i++) {
                tenDem += parts[i] + " ";
            }
            System.out.println("Ho la: " + ho);
            System.out.println("Ten dem la: " + (tenDem.length() > 0 ? tenDem.toString() : "(khong co)"));
            System.out.println("Ten la: " + ten);
        }
    }

    public static void E4q8(Group[] groups){
        System.out.println("Các group chứa 'Java':");
        for (Group group : groups) {
            if (group.getGroupName().contains("Java")) {
                System.out.println(group.getGroupName());
            }
        }
    }

    public static void E4q9(Group[] groups){
        System.out.println("Các group có tên 'Java':");
        for (Group group : groups) {
            if (group.getGroupName().equals("Java")) {
                System.out.println(group.getGroupName());
            }
        }
    }

    public static void E4q10(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập chuỗi 1: ");
        String s1 = sc.nextLine();
        System.out.print("Nhập chuỗi 2: ");
        String s2 = sc.nextLine();
        if (s1.length() != s2.length()) {
            System.out.println("Khong phai 2 chuoi dao nguoc ");
            return;
        }
        boolean isReverse = true;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(s1.length() - 1 - i)) {
                isReverse = false;
                break;
            }
        }
        if (isReverse) {
            System.out.println(s1 + "la dao nguoc cua " + s2 );
        } else {
            System.out.println("Khong phai 2 chuoi dao nguoc");
        }
    }

    public static void E4q11() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap vao chuoi :");
        String chuoi = sc.nextLine();
        int dem = 0;
        for (int i = 0; i < chuoi.length(); i++) {
            if (chuoi.charAt(i) == 'a') {
                dem++;
            }
        }
        System.out.println("So lan xuat hien ki tu \"a\" la : " + dem);
    }

    public static void E4q12(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập vào chuỗi: ");
        String str = sc.nextLine();
        String reversed = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed += str.charAt(i);
        }
        System.out.println("Chuỗi đảo ngược: " + reversed);
    }

    public static void E4q13() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập vào chuỗi: ");
        String chuoi = sc.nextLine();

        boolean noDigit = true;
        for (int i = 0; i < chuoi.length(); i++) {
            if (Character.isDigit(chuoi.charAt(i))) {
                noDigit = false;
                break;
            }
        }
        System.out.println("Kết quả: " + noDigit);
    }

    public static void E4q14(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập chuỗi: ");
        String chuoi = sc.nextLine();
        System.out.print("Nhập ký tự cần thay: ");
        char oldChar = sc.nextLine().charAt(0);
        System.out.print("Nhập ký tự thay thế: ");
        char newChar = sc.nextLine().charAt(0);

        // Dùng replace (thay thế tất cả)
        String result = chuoi.replace(oldChar, newChar);
        System.out.println("Kết quả: " + result);
    }

    public static void E4q15(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập chuoi: ");
        String chuoi = sc.nextLine().trim();
        String[] tu = chuoi.split("\\s+");
        String reversed = "";
        for (int i = tu.length - 1; i >= 0; i--) {
            reversed += tu[i] + " ";
        }
        System.out.println(reversed.trim());
    }

    public static void E4q16(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập chuỗi: ");
        String chuoi = sc.nextLine();
        System.out.print("Nhập n: ");
        int n = sc.nextInt();
        if (n <= 0) {
            System.out.println("KO");
            return;
        }
        if (chuoi.length() % n != 0) {
            System.out.println("KO");
        } else {
            for (int i = 0; i < chuoi.length(); i += n) {
                System.out.println(chuoi.substring(i, i + n));
            }
        }
    }

    public static void E5q1(Department department) {
        System.out.println(department);
    }

    public static void E5q2(Department[] departments) {
        for (Department department : departments) {
            System.out.println(department);
        }
    }

    public static void E5q3(Department department) {
        System.out.println(department.getDepartmentName());
    }

    public static void E5q4(Department department) {
        if (department.getDepartmentName().equals("Phong A")) {
            System.out.println("Dung");
        } else {
            System.out.println("Sai");
        }
    }

    public static void E5q5(Department dep1, Department dep2) {
        if (dep1.getDepartmentName().equals(dep2.getDepartmentName())) {
            System.out.println("Bang nhau");
        } else {
            System.out.println("Khong bang nhau");
        }
    }

    public static void E5q6(Department[] departments) {
        for (int i = 0; i < departments.length; i++) {
            for (int j = 0; j < departments.length - 1; j++) {
                if (departments[i].getDepartmentName().compareToIgnoreCase(departments[j].getDepartmentName()) < 0) {
                    Department temp = departments[i];
                    departments[i] = departments[j];
                    departments[j] = temp;
                }
            }
        }
    }
    public static void
}
