package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private int accountID;                   // Mã tài khoản (AccountID)
    private String email;             // Email (duy nhất)
    private String userName;          // Tên đăng nhập (duy nhất)
    private String fullName;          // Họ và tên
    private String avatarImageName;   // Tên file ảnh đại diện (có thể null)
    private String mobile;            // Số điện thoại (duy nhất)
    private String address;           // Địa chỉ
    private LocalDateTime createDate; // Ngày tạo tài khoản
    private String password;          // Mật khẩu đã mã hóa (BCrypt)
    private int status;
}
