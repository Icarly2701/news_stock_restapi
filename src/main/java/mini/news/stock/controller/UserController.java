package mini.news.stock.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mini.news.stock.dto.SignupDto;
import mini.news.stock.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@Valid SignupDto signupDto){
        userService.signupProcess(signupDto);
        return "ok";
    }

}
