package com.hw.service.user;

import com.hw.exception.UserAlreadyExistsException;
import com.hw.exception.UserNotFoundException;
import com.hw.model.Role;
import com.hw.model.User;
import com.hw.repository.RoleRepository;
import com.hw.repository.UserRepository;
import com.hw.util.security.TokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@PropertySource("classpath:exceptions.properties")
@Service
public class UserServiceImpl implements UserService {

    @Value("${token_name}")
    private String tokenName;
    @Value("${user_already_exists}")
    private String userAlreadyExists;
    @Value("${user_not_found}")
    private String userNotFound;
    @Value("${not_user_in_token}")
    private String notUserInToken;
    @Value("${user_role}")
    private String userRole;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenHandler tokenHandler;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder, TokenHandler tokenHandler) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenHandler = tokenHandler;
    }

    @Override
    public void registerUser(User user) throws UserAlreadyExistsException {
        try {
            setUserRoles(user, Collections.singleton(userRole));
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistsException(userAlreadyExists);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException(userNotFound +"(" + username + ")"));
    }

    private void setUserRoles(User user, Set<String> roles) {
        user.setRoles(new HashSet<>());
        roles.forEach(name -> {
            Role role = roleRepository.findByName(name);
            if (role != null) {
                user.getRoles().add(role);
            } else {
                System.out.println();
            }
        });
    }

    @Override
    public User getCurrentUser(HttpServletRequest request) throws UserNotFoundException {
        return findById(tokenHandler.extractUserId(request.getHeader(tokenName))
                .orElseThrow(() -> new UserNotFoundException(notUserInToken))
        ).orElseThrow(() -> new UserNotFoundException(userNotFound));
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void changePassword(HttpServletRequest request,
                               String currentPassword,
                               String newPassword) throws UserNotFoundException {
//        User currentUser = getCurrentUser(request);
    }

    @Override
    public String getTokenUserInfo() {
        return null;
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach((element) -> users.add(element));
        return users;
    }

}
