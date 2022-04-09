package capstone.sangcom.dto.login;

import lombok.Data;

@Data
public class RegisterDTO {

    private final String id;
    private final String password;
    private final String name;
    private final String birth;
    private final int schoolgrade;
    private final int schoolclass;
    private final int schoolnumber;
    private final String phone;
    private final String role;
    private final int year;
    private final String email;

}
