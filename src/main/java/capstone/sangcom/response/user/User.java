package capstone.sangcom.response.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User{
    private String id;
    private String nickname;
    private int schoolgrade;
    private int schoolclass;
    private int schoolnumber;
    private String role;
    private int year;

    public User(){}
}