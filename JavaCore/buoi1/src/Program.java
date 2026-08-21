import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Program {
    public static void main(String[] args) {
        Department department1 = new Department(1, "Marketing");
        Department department2 = new Department(2, "Sale");
        Department department3 = new Department(3, "Bảo vệ");

        Position position1 = new Position(1, PositionName.DEV);
        Position position2 = new Position(2, PositionName.TEST);
        Position position3 = new Position(3, PositionName.SCRUM_MASTER);

        Account account1 = new Account(1, "user1@gmail.com", "user01", "Nguyen Thi Ngoc A", department1,position1, LocalDate.of(2004,5, 7));
        Account account2 = new Account(2, "user2@gmail.com", "user02", "Le Thi S", department2,position2, LocalDate.of(1975,1, 26));
        Account account3 = new Account(3, "user3@gmail.com", "user03", "Pham Thanh T", department3,position3, LocalDate.of(1975,11, 21));

        Group group1 = new Group(1, "Java Backend Team", account1, LocalDate.of(2018, 5,1));
        Group group2 = new Group(2, "Java Backend Team", account2, LocalDate.of(2018, 6,15));
        Group group3 = new Group(3, "Java Backend Team", account3, LocalDate.of(2019, 1,10));

        GroupAccount groupAccount1 = new GroupAccount(group1, account1, LocalDate.of(2018, 5,1));
        GroupAccount groupAccount2 = new GroupAccount(group2, account2, LocalDate.of(2018, 6,15));
        GroupAccount groupAccount3 = new GroupAccount(group3, account3, LocalDate.of(2019, 1,10));

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

        System.out.println("===== Departments =====");
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
        System.out.println(examQuestion3);
    }
}