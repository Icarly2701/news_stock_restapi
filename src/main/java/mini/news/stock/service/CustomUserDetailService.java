package mini.news.stock.service;

import lombok.AllArgsConstructor;
import mini.news.stock.domain.user.User;
import mini.news.stock.domain.custom.CustomUserDetails;
import mini.news.stock.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user != null){
            return new CustomUserDetails(user);
        }
        return null;
    }
}
