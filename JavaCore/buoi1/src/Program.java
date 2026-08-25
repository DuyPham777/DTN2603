import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Program {
    public static void main(String[] args) {
        Department department1 = new Department(1, "Marketing");
        Department department2 = new Department(2, "Sale");
        Department department3 = new Department(3, "Bảo vệ");
        ArrayList<Department> departments = new ArrayList<>();
        departments.add(department1);
        departments.add(department2);
        departments.add(department3);

        Position position1 = new Position(1, PositionName.DEV);
        Position position2 = new Position(2, PositionName.TEST);
        Position position3 = new Position(3, PositionName.SCRUM_MASTER);

        Account account1 = new Account(1, "user1@gmail.com", "user01", "Nguyen Thi Ngoc A", department1,position1, LocalDate.of(2004,5, 7));
        Account account2 = new Account(2, "user2@gmail.com", "user02", "Le Thi S", department2,position2, LocalDate.of(1975,1, 26));
        Account account3 = new Account(3, "user3@gmail.com", "user03", "Pham Thanh T", department3,position3, LocalDate.of(1975,11, 21));
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);
        accounts.add(account3);

        Group group1 = new Group(1, "Java Backend Team", account1, LocalDate.of(2018, 5,1));
        Group group2 = new Group(2, "Java Backend Team", account2, LocalDate.of(2018, 6,15));
        Group group3 = new Group(3, "Java Backend Team", account3, LocalDate.of(2019, 1,10));

        GroupAccount groupAccount1 = new GroupAccount(group1, account1, LocalDate.of(2018, 5,1));
        GroupAccount groupAccount2 = new GroupAccount(group2, account2, LocalDate.of(2018, 6,15));
        GroupAccount groupAccount3 = new GroupAccount(group3, account3, LocalDate.of(2019, 1,10));
        ArrayList<GroupAccount> groupAccounts = new ArrayList<>();
        groupAccounts.add(groupAccount1);
        groupAccounts.add(groupAccount2);
        groupAccounts.add(groupAccount3);

        TypeQuestion typeQuestion1 = new TypeQuestion(1, TypeName.ESSAY);
        TypeQuestion typeQuestion2 = new TypeQuestion(2, TypeName.MULTIPLE_CHOICE);
        TypeQuestion typeQuestion3 = new TypeQuestion(3, TypeName.MULTIPLE_CHOICE);

        CategoryQuestion categoryQuestion1 = new CategoryQuestion(1, "Java");
        CategoryQuestion categoryQuestion2 = new CategoryQuestion(2, ".Net");
        CategoryQuestion categoryQuestion3 = new CategoryQuestion(3, "Sql");

        Question question1 = new Question(1, "Question 1?", typeQuestion1, categoryQuestion1, account1,LocalDate.of(2026, 5, 19));
        Question question2 = new Question(2, "Question 2?", typeQuestion2, categoryQuestion2, account2,LocalDate.of(2026, 5, 19));
        Question question3 = new Question(3, "Question 3?", typeQuestion3, categoryQuestion3, account3,LocalDate.of(2026, 5, 19));

        Answer answer1 = new Answer(1, "Answer 1", question1, true);
        Answer answer2 = new Answer(2, "Answer 2", question1, true);
        Answer answer3 = new Answer(3, "Answer 3", question1, false);

        Exam exam1 = new Exam(1, "101", "Đề thi Java cơ bản", categoryQuestion1, Duration.ofMinutes(60), account1, LocalDate.of(2025,1,10));
        Exam exam2 = new Exam(2, "102", "Đề thi SQL nâng cao", categoryQuestion2, Duration.ofMinutes(90), account1, LocalDate.of(2025,1,15));
        Exam exam3 = new Exam(3, "103", "Đề thi .NET cơ bản", categoryQuestion3, Duration.ofMinutes(60), account1, LocalDate.of(2025,1,20));

        ExamQuestion examQuestion1 = new ExamQuestion(exam1, question1);
        ExamQuestion examQuestion2 = new ExamQuestion(exam2, question2);
        ExamQuestion examQuestion3 = new ExamQuestion(exam3, question3);

        /*System.out.println("===== Departments =====");
        System.out.println(department1);
        System.out.println(department2);
        System.out.println(department3);

        System.out.println("\n===== Positions =====");
        System.out.println(position1);
        System.out.println(position2);
        System.out.println(position3);

        System.out.println("\n===== Accounts =====");
        System.out.println(account1);
        System.out.println(account2);
        System.out.println(account3);

        System.out.println("\n===== Groups =====");
        System.out.println(group1);
        System.out.println(group2);
        System.out.println(group3);

        System.out.println("\n===== GroupAccounts =====");
        System.out.println(groupAccount1);
        System.out.println(groupAccount2);
        System.out.println(group3);

        System.out.println("\n===== TypeQuestions =====");
        System.out.println(typeQuestion1);
        System.out.println(typeQuestion2);
        System.out.println(typeQuestion3);

        System.out.println("\n===== CategoryQuestions =====");
        System.out.println(categoryQuestion1);
        System.out.println(categoryQuestion2);
        System.out.println(categoryQuestion3);

        System.out.println("\n===== Questions =====");
        System.out.println(question1);
        System.out.println(question2);
        System.out.println(question3);

        System.out.println("\n===== Answers =====");
        System.out.println(answer1);
        System.out.println(answer2);
        System.out.println(answer3);

        System.out.println("\n===== Exams =====");
        System.out.println(exam1);
        System.out.println(exam2);
        System.out.println(exam3);

        System.out.println("\n===== ExamQuestions =====");
        System.out.println(examQuestion1);
        System.out.println(examQuestion2);
        System.out.println(examQuestion3);*/

        //Exercise 1: Flow Control
        //Question 1
//        if (account2.getDepartment() == null){
//            System.out.println("Nhân viên này chưa có phòng ban");
//        }else {
//            System.out.println("Phòng ban của nhân viên này là " + account2.getDepartment().getDepartmentName());
//        }
//
        //Question 2

//
//        //Question 3
//        String a = (account2.getDepartment() == null) ? "Nhân viên này chưa có phòng ban" : "Phòng ban của nhân viên này là " + account2.getDepartment().getDepartmentName();
//        System.out.println(a);
//
//        //Question 4
//        String b = (account1.getPosition().getPositionName() == PositionName.DEV)  ? "Đây là Developer" : "Người này không phải là Developer";
//        System.out.println(b);
//
//        //Question 5
//        int demacc1 = 0;
//        for(GroupAccount ga : groupAccounts){
//            if(ga.getGroup().getGroupId() == group1.getGroupId())
//                demacc1 ++;
//        }
//        switch (demacc1) {
//            case 1:
//                System.out.println("Nhóm có một thành viên");
//                break;
//            case 2:
//                System.out.println("Nhóm có hai thành viên");
//                break;
//            case 3:
//                System.out.println("Nhóm có ba thành viên");
//                break;
//            default:
//                System.out.println("Nhóm có nhiều thành viên");
//                break;
//        }
//
//        //Question 6
//
//
//        //Question 7
//        switch (account1.getPosition().getPositionName()) {
//            case DEV:
//                System.out.println("Đây là Developer");
//                break;
//            default:
//                System.out.println("Người này không phải là Developer");
//                break;
//        }

        //Question 8
//        System.out.println("+-----+--------------------+--------------------+--------------------+");
//        System.out.println("|   ID|               Email|            FullName|          Department|");
//        System.out.println("+-----+--------------------+--------------------+--------------------+");
//        for(Account acc : accounts){
//            System.out.printf("|%5d|%20s|%20s|%20s|\n", acc.getAccountID(), acc.getEmail(), acc.getUserName(), (acc.getDepartment().getDepartmentName()));
//        }
//        System.out.println("+-----+--------------------+--------------------+--------------------+");
//
//        //Question 9
//        System.out.println("+-----+--------------------+");
//        System.out.println("|   ID|      DepartmentName|");
//        for (Department dep : departments){
//            System.out.printf("|%5d|%20s|\n", dep.getDepartmentId(), dep.getDepartmentName());
//        }
//        System.out.println("+-----+--------------------+");
//
//        //Question 10
//        for (int i = 0; i < accounts.size(); i++) {
//            Account acc = accounts.get(i);
//            System.out.println("Thông tin account thứ " + (i + 1) + " là:");
//            System.out.println("Email: " + acc.getEmail());
//            System.out.println("Full name: " + acc.getFullName());
//            System.out.println("Phòng ban: " + acc.getDepartment().getDepartmentName());
//            System.out.println();
//        }
//
//        //Question 11
//        for (int i = 0; i < departments.size(); i++) {
//            Department dep = departments.get(i);
//            System.out.println("Thông tin department thứ " + (i + 1) + " là:");
//            System.out.println("ID: " + dep.getDepartmentId());
//            System.out.println("Name: " + dep.getDepartmentName());
//        }

        //Question 12
//        System.out.println("+-----+--------------------+");
//        System.out.println("|   ID|                Name|");
//        System.out.println("+-----+--------------------+");
//        for (int i = 0; i < 2; i++) {
//            Department dep = departments.get(i);
//            System.out.printf("|%5d|%20s|\n", dep.getDepartmentId(), dep.getDepartmentName());
//        }
//        System.out.println("+-----+--------------------+");
//
//        //Question 13
//        System.out.println("+-----+--------------------+--------------------+--------------------+");
//        System.out.println("|   ID|               Email|            FullName|          Department|");
//        System.out.println("+-----+--------------------+--------------------+--------------------+");
//        for (int i = 0; i < accounts.size(); i++) {
//            Account acc = accounts.get(i);
//            if (i ==1)
//                continue;
//            System.out.printf("|%5d|%20s|%20s|%20s|\n", acc.getAccountID(), acc.getEmail(), acc.getUserName(), (acc.getDepartment().getDepartmentName()));
//        }
//        System.out.println("+-----+--------------------+--------------------+--------------------+");
//
//        //Question 14
//        System.out.println("+-----+--------------------+--------------------+--------------------+");
//        System.out.println("|   ID|               Email|            FullName|          Department|");
//        System.out.println("+-----+--------------------+--------------------+--------------------+");
//        for (int i = 0; i < accounts.size(); i++) {
//            Account acc = accounts.get(i);
//            if (acc.getAccountID() < 4)
//                System.out.printf("|%5d|%20s|%20s|%20s|\n", acc.getAccountID(), acc.getEmail(), acc.getUserName(), (acc.getDepartment().getDepartmentName()));
//        }
//        System.out.println("+-----+--------------------+--------------------+--------------------+");
//
//        //Question 15
//        for (int i = 0; i <= 20; i+= 2){
//            System.out.println("Cac so chan nho hon hoac bang 20 la: " + i);
//        }
//        //Question 16(Question 10)
//        System.out.println("=== Câu 10 (while) ===");
//        int i1 = 0;
//        while (i1 < accounts.size()) {
//            Account acc = accounts.get(i1);
//            System.out.println("Thông tin account thứ " + (i1 + 1) + " là:");
//            System.out.println("Email: " + acc.getEmail());
//            System.out.println("Full name: " + acc.getFullName());
//            String dept = (acc.getDepartment() == null) ? "Chưa có" : acc.getDepartment().getDepartmentName();
//            System.out.println("Phòng ban: " + dept);
//            System.out.println();
//            i1++;
//        }
//
//        //Question 17(Question 10)
//        int i2 = 0;
//        do {
//            Account acc = accounts.get(i2);
//            System.out.println("Thông tin account thứ " + (i2 + 1) + " là:");
//            System.out.println("Email: " + acc.getEmail());
//            System.out.println("Full name: " + acc.getFullName());
//            String dept = (acc.getDepartment() == null) ? "Chưa có" : acc.getDepartment().getDepartmentName();
//            System.out.println("Phòng ban: " + dept);
//            System.out.println();
//            i2++;
//        } while (i2 < accounts.size());

        //Exercise 2: System out printf
        //Question 1
        int a = 5;
        System.out.printf("So nguyen: %d", a);
        System.out.println("\n");

        //Question 2
        int b = 100000000;
        System.out.printf("So nguyen: %,d", b);
        System.out.println("\n");

        //Question 3
        double c = 5.567098;
        System.out.printf("So thuc: %.4f", c);
        System.out.println("\n");

        //Question 4
        String fullName = "Nguyễn Văn A";
        System.out.println("Tên tôi là " + fullName + " và tôi đang độc thân.");

        //Question 5
//        String pattern = "dd/MM/yyyy HH:mm:ss";
//        LocalDateTime now = LocalDateTime.now();
//        System.out.printf("Thoi gian hien tai: %s", );

        //Question 6 (Question 8 Excercise 1 co lam roi)

        //Exercise 3: Date Format
        //Question 1
        LocalDate creatDate = exam1.getCreateDate();
        System.out.println("\nQuestion 1:");
        System.out.println("Exam ID: " + exam1.getExamId());
        System.out.println("Code: " + exam1.getCode());
        System.out.println("Title: " + exam1.getTitle());
        System.out.println("Create date: "+ creatDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        //Question 2
        LocalDateTime dateTime = creatDate.atStartOfDay();
        System.out.println("Exam được tạo ngày: " + dateTime.format(DateTimeFormatter.ofPattern("yyyy – MM – dd – HH : mm : ss")));

        //Question 3
        System.out.println("Exam được tạo năm: " + dateTime.format(DateTimeFormatter.ofPattern("yyyy")));

        //Question 4
        System.out.println("Exam được tạo từ: " + dateTime.format(DateTimeFormatter.ofPattern("yyyy – MM")));

        //Question 5
        System.out.println("Exam được tạo từ: " + dateTime.format(DateTimeFormatter.ofPattern("MM – dd")));

        //Exercise 4: Random Number
        Random random = new Random();
        //Question 1
        int snr = random.nextInt();
        System.out.println("Số nguyên ngẫu nhiên: " + snr);

        //Question 2
        float str = random.nextFloat();
        System.out.println("Số thực ngẫu nhiên: " + str);

        //Question 3
        String[] name = {"Huy", "Thinh", "Nang", "Bao", "Manh", "Duy", "Trung", "Hoang", "Nam", "Sang", "Viet"};
        int randomIndex = random.nextInt(name.length);
        System.out.println("Tên ngẫu nhiên: " + name[randomIndex]);

        //Question 4
        //Question 5
        //Question 6

        //Question 7
        int x = random.nextInt(900) + 100;
        System.out.println("Số ngẫu nhiên có 3 chữ số: " + x);

        //Exercise 5: Input from console
        Scanner scanner = new Scanner(System.in);
        //Question 1
        System.out.println("Nhập số nguyên thứ 1: ");
        int i1 = scanner.nextInt();
        System.out.println("Nhập số nguyên thứ 2: ");
        int i2 = scanner.nextInt();
        System.out.println("Nhập số nguyên thứ 3: ");
        int i3 = scanner.nextInt();
        System.out.println("Ba số nguyên đã nhập: " + i1 + ", " + i2 + ", " + i3);

        //Question 2
        System.out.println("Nhập số thực thứ 1: ");
        double d1 = scanner.nextDouble();
        System.out.println("Nhập số thực thứ 2: ");
        double d2 = scanner.nextDouble();
        System.out.println("Hai số thực: " + d1 + ", " + d2);

        //Question 3
        scanner.nextLine();
        System.out.println("Nhập họ và tên: ");
        String allName = scanner.nextLine();
        System.out.println("Họ và tên: " + allName);

        //Question 4
        System.out.println("Nhập ngày sinh (dd/MM/yyyy): ");
        String birthday = scanner.next();
        LocalDate birthDate = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("Ngày sinh: " + birthDate);
        //Question 5

        //Question 4
        //Question 4
        //Question 4
        //Question 4
        //Question 4
//        soChanDuong();
//        soNguyenDuong();
//        inAccount(accounts);
    }

    public static Account createAccount(Scanner scanner, ArrayList<Department> departments) {
        System.out.print("Nhập ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // tiêu hao newline
        System.out.print("Nhập Email: ");
        String email = scanner.nextLine();
        System.out.print("Nhập Username: ");
        String username = scanner.nextLine();
        System.out.print("Nhập FullName: ");
        String fullName = scanner.nextLine();
        System.out.print("Nhập tên phòng ban: ");
        String deptName = scanner.nextLine();
        int maxId = 0;
        for (Department d : departments) {
            if (d.getDepartmentId() > maxId) maxId = d.getDepartmentId();
        }
        Department department = new Department(maxId + 1, deptName);
        departments.add(department);

        System.out.println("Chọn vị trí (Position):");
        System.out.println("1. Dev");
        System.out.println("2. Test");
        System.out.println("3. ScrumMaster");
        System.out.println("4. PM");
        System.out.print("Nhập số (1-4): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        Position position;
        switch (choice) {
            case 1:
                position = new Position(1, PositionName.DEV);
                break;
            case 2:
                position = new Position(2, PositionName.TEST);
                break;
            case 3:
                position = new Position(3, PositionName.SCRUM_MASTER);
                break;
            case 4:
                position = new Position(4, PositionName.PM);
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ! Mặc định chọn Dev.");
                position = new Position(1, PositionName.DEV);
                break;
        }
        Account newAccount = new Account(id, email, username, fullName,
                department, position, LocalDate.now());

        System.out.println("✅ Tạo account thành công!");
        return newAccount;
    }

    //Question 6
    public static Department createDepartment(Scanner scanner) {
        System.out.print("Nhập Department ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nhập Department Name: ");
        String name = scanner.nextLine();
        return new Department(id, name);
    }

    //Question 7
    public static void nhapSoChan(Scanner scanner) {
        int y;
        while (true) {
            System.out.print("Nhập một số chẵn: ");
            y = scanner.nextInt();
            if (y % 2 == 0) {
                System.out.println("Bạn đã nhập số chẵn: " + y);
                break;
            } else {
                System.out.println("Số bạn nhập là số lẻ, vui lòng nhập lại!");
            }
        }
    }

    //Question 8
    public static void q8(Scanner scanner, ArrayList<Department> departments){
        while (true) {
            System.out.println("Mời bạn nhập chức năng muốn sử dụng:");
            System.out.println("1. Tạo account");
            System.out.println("2. Tạo department");
            System.out.print("Nhập lựa chọn: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createAccount(scanner, departments);
                    break;
                case 2:
                    createDepartment(scanner);
                    break;
                default:
                    System.out.println("Mời bạn nhập lại");
                    continue;
            }
            break;
        }
    }

    //Question 10
    //Question 11
    //Question 12

//    //  Exercise 6: Method
//    //  Question 1:
//    public static void soChanDuong() {
//        System.out.println("Các số chẵn nguyên dương nhỏ hơn 10 là:");
//        for (int i = 2; i < 10; i += 2) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
//    }
//
//    //  Question 2:
//    public static void inAccount(ArrayList<Account> accounts) {
//        System.out.println("+-----+--------------------+--------------------+--------------------+");
//        System.out.println("|   ID|               Email|            FullName|          Department|");
//        System.out.println("+-----+--------------------+--------------------+--------------------+");
//        for(Account acc : accounts){
//            System.out.printf("|%5d|%20s|%20s|%20s|\n", acc.getAccountID(), acc.getEmail(), acc.getUserName(), (acc.getDepartment().getDepartmentName()));
//        }
//        System.out.println("+-----+--------------------+--------------------+--------------------+");
//        }
//    //  Question 3:
//    //  Tạo method để in ra các số nguyên dương nhỏ hơn 10
//    public static void soNguyenDuong() {
//        System.out.println("Các số nguyên dương nhỏ hơn 10 là:");
//        for (int i = 1; i < 10; i++) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
//    }
}