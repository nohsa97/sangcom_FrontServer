package capstone.sangcom.dto.user;

import lombok.Data;

@Data
public class UserInfoEditDTO {
    private final String phone;
    private final String date;
    private final int year;
    private final int schoolgrade;
    private final int schoolclass;
    private final int schoolnumber;
    private final String email;
}
