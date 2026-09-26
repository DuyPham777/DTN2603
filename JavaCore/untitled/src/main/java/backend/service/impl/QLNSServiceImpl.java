package backend.service.impl;

import backend.repository.IQLDepartmentRepository;
import backend.repository.IQLNSRepository;
import backend.repository.IQLPositionRepository;
import backend.repository.impl.QLDepartmentRepositoryImpl;
import backend.repository.impl.QLNSRepositoryImpl;
import backend.repository.impl.QLPositionRepositoryImpl;
import backend.service.IQLNSService;
import common.NumberCommon;
import common.StringCommon;
import entity.Account;
import entity.Department;
import entity.Gender;
import entity.Position;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QLNSServiceImpl implements IQLNSService {
    private IQLNSRepository repository;
    private IQLDepartmentRepository departmentRepository;
    private IQLPositionRepository positionRepository;

    public QLNSServiceImpl(){
        repository = new QLNSRepositoryImpl();
        departmentRepository = new QLDepartmentRepositoryImpl();
        positionRepository = new QLPositionRepositoryImpl();
    }

    @Override
    public List<Account> getAccounts() {
        List<Account> accounts = repository.getAccounts();
        return accounts;
    }

    @Override
    public List<Department> getDepartments() {
        List<Department> departments = repository.getDepartments();
        return departments;
    }

    @Override
    public List<Position> getPositions() {
        List<Position> positions = repository.getPositions();
        return positions;
    }

    @Override
    public boolean xoaTheoAccId(int accountId) {
        return repository.xoaTheoAccId(accountId);
    }

    @Override
    public boolean suaTheoAccId(int accountId, String userName, int departmentId, int positionId) {
        return repository.suaTheoAccId(accountId, userName, departmentId, positionId);
    }

    @Override
    public boolean themAccount(Account account) {
        return repository.themAccount(account);
    }


    @Override
    public boolean checkEmailExist(String email) {
        return repository.checkEmailExist(email);
    }

    @Override
    public boolean checkUserNameExist(String userName, Integer id) {
        return repository.checkUserNameExist(userName, id);
    }

    @Override
    public boolean checkAccountIdExist(int accountId) {
        return repository.checkAccountIdExist(accountId);
    }


    @Override
    public String importCSVAcc(String url) {
        if (!url.endsWith(".csv")) {
            return "File không đúng định dạng";
        }
        File file = new File(url);
        if (!file.exists()) {
            return "File không tồn tại";
        }
        List<Account> accounts = new ArrayList<>();
        List<String> listErrors = new ArrayList<>();
        String header = "";
        try (BufferedReader br = new BufferedReader(new FileReader(url))) {
            header = br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    String error = validationAccount(line, accounts);
                    if (error != null) {
                        System.out.println(error);
                        listErrors.add(error);
                    }
                } catch (Exception e) {
                    listErrors.add(line + "," + "Loi khong xac dinh: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Account account : accounts) {
            repository.themAccount(account);
        }

        if (!listErrors.isEmpty()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("F:\\DTN2603\\JavaCore\\csv\\account_errors.csv"))) {
                bw.write(header + ",error_message");
                bw.newLine();
                for (String error : listErrors) {
                    bw.write(error);
                    bw.newLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listErrors.isEmpty() ? "Import thành công" : "Đã xuất file account_errors.csv";
    }

    private String validationAccount(String line, List<Account> accounts) {
        List<String> genders= Arrays.asList("nam","nữ","chưa xác định");
        String[] values=line.split(",");
        List<String> errors=new ArrayList<>();

        String email=values[0];
        if(email.length()<6 || email.length()>100) {
            errors.add("Email có độ dài từ 6 đến 100 kí tự");
        }else if (!email.matches(StringCommon.EMAIL_REGEX)) {
            errors.add("Email sai định dạng");
        }
        else {
            if(this.checkEmailExist(email)) {
                errors.add("Email đã tồn tại");
            }
        }

        String userName=values[1];
        if(userName.length()<6 || userName.length() > 100) {
            errors.add("Username có độ dài từ 6 đến 100 kí tự");
        }
        else {
            if(this.checkUserNameExist(userName, null)) {
                errors.add("Username đã tồn tại");
            }
        }

        String fullName=values[2];
        if(fullName.length()<6 || fullName.length() > 100) {
            errors.add("FullName có độ dài từ 6 đến 100 kí tự");
        }

        int departmentId=0;
        if(!values[3].matches(NumberCommon.NUMBER_REGEX)) {
            errors.add("departmentId phải là số");
        }
        else {
            departmentId=Integer.parseInt(values[3]);
            if (departmentId <= 0) {
                errors.add("departmentId phải là số >0");
            } else if (!this.departmentRepository.isDepartmentExist(departmentId)) {
                errors.add("departmentId không tồn tại");
            }
        }

        int positionId=0;
        if(!values[4].matches(NumberCommon.NUMBER_REGEX)) {
            errors.add("postiontId phải là số");
        }
        else {
            positionId =Integer.parseInt(values[4]);
            if (positionId <= 0 ) {
                errors.add("postiontId phải là số >0");
            } else if (!this.positionRepository.isPositionExist(positionId)) {
                errors.add("positionId không tồn tại");
            }
        }

        String genderStr = values[5].trim();
        Gender gender = null;
        if (!genders.contains(genderStr)) {
            errors.add("Gender chỉ có 3 giá trị : nam , nữ, chưa xác định");
        } else {
            switch (genderStr) {
                case "nam":
                    gender = Gender.MALE;
                    break;
                case "nữ":
                    gender = Gender.FEMALE;
                    break;
                default:
                    gender = Gender.OTHER;
                    break;
            }
        }

        if (errors.isEmpty()) {
            Department department = new Department(departmentId, null);
            Position position = new Position(positionId, null);
            Account account = new Account();
            account.setEmail(email);
            account.setUserName(userName);
            account.setFullName(fullName);
            account.setGender(gender);
            account.setDepartment(department);
            account.setPosition(position);
            account.setCreateDate(LocalDate.parse(LocalDate.now().toString()));

            accounts.add(account);

            return null;
        }
        return line + "," + String.join(" | ", errors);
    }
}
