package mini.news.stock.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import mini.news.stock.domain.custom.Gender;

@Data
public class SignupDto {

    @NotBlank(message = "아이디는 공백 문자로 이루어지거나 혹은 비어있을 수 없습니다.")
    private String username;

    @NotBlank(message = "비밀번호는 공백 문자로 이루어지거나 혹은 비어있을 수 없습니다.")
    @Size(min=8, max=16, message = "비밀번호는 8글자 이상, 16글자 이하로 구성되어야 합니다.")
    private String password;

    @NotBlank(message = "닉네임은 공백 문자로 이루어지거나 혹은 비어있을 수 없습니다.")
    private String nickname;

    @NotNull
    private Gender gender;

    @NotNull
    @Positive(message = "나이는 0보다 높아야 합니다.")
    private int age;
}
