package mini.news.stock.service;

import lombok.AllArgsConstructor;
import mini.news.stock.domain.user.Keyword;
import mini.news.stock.domain.user.User;
import mini.news.stock.dto.SignupDto;
import mini.news.stock.repository.KeywordRepository;
import mini.news.stock.repository.UserRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final KeywordRepository keywordRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void signupProcess(SignupDto signupDto){

        Boolean isExist = userRepository.existsByUsername(signupDto.getUsername());
        if(isExist) return;
        User user = User.makeUserEntity(signupDto, bCryptPasswordEncoder);
        userRepository.save(user);
    }

    public void addPreferenceTitle(String preferenceTitle, Authentication authentication){
        if(!(authentication instanceof AnonymousAuthenticationToken)) {
            User user = userRepository.findByUsername(authentication.getName());
            keywordRepository.save(Keyword.addKeyword(user, preferenceTitle));
        }
    }

    public void deletePreferenceTitle(Long keywordId){
        Keyword keyword = keywordRepository.findById(keywordId).orElse(null);
        if(keyword == null) return;
        keywordRepository.delete(keyword);
    }
}
