package utils;

import com.mysql.cj.util.StringUtils;
import common.StringCommon;

import java.util.Scanner;

public class ScannerUtils {
    private static Scanner scanner = new Scanner(System.in);

    public static int inputInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Nhập lại: ");
            }
        }
    }

    public static String inputString() {
        while (true) {
            String string = scanner.nextLine();
            if (!StringUtils.isNullOrEmpty(string)) {// isEmty ==> rỗng ; !isEmpty() ==> không rỗng
//				System.out.p    rintln(string);
                return string;
            } else {
                System.err.println("Nhập lại: ");
            }
        }
    }

    public static int inputFunction(int a, int b) {// bat
        while (true) {
            int number = ScannerUtils.inputInt();
            if (number >= a && number <= b) {
                return number;
            } else {
                System.err.println("chỉ được nhập từ " + a + " đến " + b);
            }
        }
    }

    public static String inputEmail() {
        while (true) {
            String email = scanner.nextLine();

            if (!email.trim().matches(StringCommon.EMAIL_REGEX)) {
                System.err.println("Email ko hợp lệ");
                continue;
            }
            return email;
        }
    }

    public static String inputPassword() {
        while (true) {
            String password = ScannerUtils.inputString();
            if (password.length() < 6 || password.length() > 12) {
                System.out.print("Nhập lại: ");
                continue;
            }
            boolean hasAtLeast1Character = false;
            for (int i = 0; i < password.length(); i++) {
                if (Character.isUpperCase(password.charAt(i))) {
                    hasAtLeast1Character = true;
                    break;
                }
            }

            if (hasAtLeast1Character) {// password dung
                return password;
            } else {
                System.out.print("Mời bạn nhập lại password: ");
            }
        }
    }

    public static String inputPhoneNumber() {

        while (true) {
            boolean isNumber = true;
            String number = ScannerUtils.inputString();


            if (number.charAt(0) != '0') {
                isNumber = false;
            }
            if (number.length() > 12 || number.length() < 9) {
                isNumber = false;
            }

            // kiem tra co ki tu nao ko  phải la number ko
            for (int i = 0; i < number.length(); i++) {
                if (!Character.isDigit(number.charAt(i))) {//isDigit kiem tra 1 kí tu co phai so hay ko
                    isNumber = false;
                    break;
                }
            }
            if (isNumber == true) {// sdt hop le
                return number;
            } else {
                System.out.print("Nhập lại: ");
            }
        }
    }
}
