package Utils;

import common.StringCommon;

import java.util.Scanner;

public class ScannerUtils {
    private static Scanner scanner = new Scanner(System.in);

    public static String checkLength(int min, int max) {
        while (true) {
            String text = scanner.nextLine();
            if (text.trim().length() < min || text.trim().length() > max) {
                System.err.println(String.format("Vui long nhap tu %d den %d ki tu!\n", min, max));
                continue;
            }
            return text;
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
}
