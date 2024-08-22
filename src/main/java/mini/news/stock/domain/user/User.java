package mini.news.stock.domain.user;

import jakarta.persistence.*;
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

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
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
