package mini.news.stock.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import mini.news.stock.domain.custom.Gender;
import mini.news.stock.dto.SignupDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    private String nickname;
    private Gender gender;
    private int age;
    private String role;

    public static User makeUserEntity(SignupDto signupDto, BCryptPasswordEncoder bCryptPasswordEncoder){
        User user = new User();
        user.username = signupDto.getUsername();
        user.nickname = signupDto.getNickname();
        user.gender = signupDto.getGender();
        user.role = "ADMIN_USER";
        user.age = signupDto.getAge();
        user.password = bCryptPasswordEncoder
                .encode(signupDto.getPassword());

        return user;
    }

    public static User makeTempUserEntity(String username, String role){
        User user = new User();
        user.username = username;
        user.password = "tempPassword";
        user.role = role;
        return user;
    }
}
