package mini.news.stock.service;

import lombok.AllArgsConstructor;
import mini.news.stock.domain.user.User;
import mini.news.stock.dto.SignupDto;
import mini.news.stock.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void signupProcess(SignupDto signupDto){

        Boolean isExist = userRepository.existsByUsername(signupDto.getUsername());
        if(isExist) return;
        User user = User.makeUserEntity(signupDto, bCryptPasswordEncoder);
        userRepository.save(user);
    }
}
