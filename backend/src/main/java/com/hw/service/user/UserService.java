package com.hw.service.user;

import com.hw.exception.UserAlreadyExistsException;
import com.hw.exception.UserNotFoundException;
import com.hw.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    void registerUser(User user) throws UserAlreadyExistsException;

    Optional<User> findById(Long id);

    User getCurrentUser(HttpServletRequest request) throws UserNotFoundException;

    void update(User user);

    void changePassword(HttpServletRequest request, String currentPassword, String newPassword) throws UserNotFoundException;

    String getTokenUserInfo();

    void delete(Long id);

    void delete(User user);

    List<User> getAll();
}
