package mini.news.stock.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mini.news.stock.dto.SignupDto;
import mini.news.stock.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@Valid SignupDto signupDto){
        userService.signupProcess(signupDto);
        return "ok";
    }

    @GetMapping("/mypage/add")
    public ResponseEntity<String> addPreferenceTitle(@RequestParam String preferenceTitle,
                                             Authentication authentication){
        userService.addPreferenceTitle(preferenceTitle, authentication);
        return ResponseEntity.status(HttpStatus.OK).body("관심어 등록 완료");
    }

    @DeleteMapping("/mypage/delete/{preferenceTitleId}")
    public ResponseEntity<String> deletePreferenceTitle(@PathVariable("preferenceTitleId") Long preferenceTitleId){
        userService.deletePreferenceTitle(preferenceTitleId);
        return ResponseEntity.status(HttpStatus.OK).body("관심어 삭제 완료");
    }

}
