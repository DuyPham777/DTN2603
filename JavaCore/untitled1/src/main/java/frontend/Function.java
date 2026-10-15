package frontend;

import entity.Candidate;
import entity.GraduationRank;
import utils.ScannerUtils;
import backend.controller.CandidateController;

import java.util.Scanner;

public class Function {
    private Scanner scanner;
    private CandidateController candidateController;


    public Function(){
        scanner = new Scanner(System.in);
        candidateController = new CandidateController();
    }

    public void login(){
        while (true) {
            System.out.print("moi ban nhap Email cua account: ");
            String email = ScannerUtils.inputEmail();

            System.out.print("moi ban nhap password: ");
            String password = ScannerUtils.inputPassword();

            Candidate candidate = candidateController.login(email, password);

            if (candidate != null) {
                System.out.printf("Chao mung %s (%s) \n \n \n",
                        candidate.getFirstName() + " " + candidate.getLastName(), candidate.getRole());
                return;
            } else {
                System.err.println("Ban xem lai Email/Password ko dung! moi dang nhap lai!");
            }
        }
    }

    public void addExperienceCandidate() {
        System.out.print("moi ban nhap firstname cua account: ");
        String firstname = scanner.nextLine();
        System.out.print("moi ban nhap lastname cua account: ");
        String lastname = scanner.nextLine();
        System.out.print("moi ban nhap phone cua account: ");
        String phone = ScannerUtils.inputPhoneNumber();
        System.out.print("moi ban nhap password cua account: ");
        String password = ScannerUtils.inputPassword();
        System.out.print("moi ban nhap expInYear cua account: ");
        int expInYear = ScannerUtils.inputFunction(0, 10);
        System.out.print("moi ban nhap proSkill cua account: ");
        String proSkill = scanner.nextLine();

        System.out.print("moi ban nhap vao Email cua account: ");
        // a@gmail.com	a@gmail.com  // a@b
        //  123456A	    A654321
        String email = "";
        while (true) {
            email = ScannerUtils.inputEmail();// kiem tra dung dịnh dang email chua
            boolean resultExistsEmail = candidateController.checkEmailExist(email); // kiem tra xem email có bi trung hayko
            // tao 2 tk giong ý chang nhau( giong email: a@gmail.com và gióng password: 123456)   tk1 và tk2
            if (resultExistsEmail == true) {
                System.err.printf("\nEmail %s da ton tai! moi nhap email mail khac: ", email);
            } else {
                break;
            }
        }

        boolean check = candidateController.registerExperience(firstname, lastname, phone, email, expInYear, proSkill, password);
        if (check)
            System.out.println("Tạo mới ExperienceCandidate thành công");
        else
            System.out.println("Tạo ExperienceCandidate khong thành công");
    }

    public void addFresherCandidate() {
        System.out.print("moi ban nhap firstname cua account: ");
        String firstname = scanner.nextLine();
        System.out.print("moi ban nhap lastname cua account: ");
        String lastname = scanner.nextLine();
        System.out.print("moi ban nhap phone cua account: ");
        String phone = ScannerUtils.inputPhoneNumber();
        System.out.print("moi ban nhap password cua account: ");
        String password = ScannerUtils.inputPassword();

        GraduationRank graduationRank;
        System.out.print("moi ban nhap GraduationRank cua account: 1.Excellence, 2.Good, 3.Fair, Khác.Poor;");
        int key = scanner.nextInt();
        scanner.nextLine();
        switch (key) {
            case 1:
                graduationRank = GraduationRank.EXCELLENCE;
                break;
            case 2:
                graduationRank = GraduationRank.GOOD;
                break;
            case 3:
                graduationRank = GraduationRank.FAIR;
                break;
            default:
                graduationRank = GraduationRank.POOR;
                break;
        }

        String email;
        System.out.print("moi ban nhap vao Email cua account: ");
        while (true) {
            email = ScannerUtils.inputEmail();// abc @ xyz...
            boolean resultExistsEmail = candidateController.checkEmailExist(email);// kiem tra da ton tai hay chua
            if (resultExistsEmail) {
                System.err.printf("\nEmail %s da ton tai! moi nhap email mail khac: ", email);
            } else {
                break;
            }
        }

        boolean check = candidateController.registerFresher(firstname, lastname, phone, email, graduationRank, password);
        if (check)
            System.out.println("Tạo mới FresherCandidate thành công");
        else
            System.out.println("Tạo FresherCandidate khong thành công");
    }

    public void menu() {
        while (true) {
            System.out.println("===== Candidate =====");
            System.out.println("1. Register - Experience Candidate");
            System.out.println("2. Register - Fresher Candidate");
            System.out.println("3. Login");
            System.out.println("4. Thoat");
            System.out.println("===========================");
            System.out.print("Hay chon chuc nang: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addExperienceCandidate();
                    break;
                case "2":
                    addFresherCandidate();
                    break;
                case "3":
                    login();
                    break;
                case "4":
                    System.exit(0);
                default:
                    System.out.println("Nhap sai! Nhap lai");
            }
        }
    }
}
