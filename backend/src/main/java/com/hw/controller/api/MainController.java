package com.hw.controller.api;

import com.hw.exception.UserAlreadyExistsException;
import com.hw.model.User;
import com.hw.service.user.UserService;
import com.hw.util.security.TokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@PropertySource("classpath:constants.properties")
@RestController
@RequestMapping("api")
public class MainController {

    @Value("${token_name}")
    private String tokenName;
    @Value("${wrong_password}")
    private String wrongPassword;

    private final TokenHandler tokenHandler;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MainController(TokenHandler tokenHandler, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.tokenHandler = tokenHandler;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity login(@RequestParam String email,
                                @RequestParam String password,
                                HttpServletResponse response) {
        String message = null;
        User user = null;
        try {
            user = (User) userService.loadUserByUsername(email);
            if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
                response.setHeader(tokenName, tokenHandler.generateAccessToken(user.getId(), LocalDateTime.now().plusDays(14)));
                Map<String, Object> userInfo = new HashMap();
                userInfo.put("firstName", user.getFirstName());
                userInfo.put("lastName", user.getLastName());
                userInfo.put("roles",
                        user.getRoles()
                                .stream()
                                .map(role -> role.getName())
                                .collect(Collectors.toList())
                );
                return ResponseEntity.ok(tokenHandler.encode(userInfo));
            } else {
                message = wrongPassword;
            }
        } catch (UsernameNotFoundException exception) {
            message = exception.getMessage();
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(message);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody User user) {
        try {
            userService.registerUser(user);
            return new ResponseEntity(HttpStatus.OK);
        } catch (UserAlreadyExistsException exception) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("message", exception.getMessage()));
        }
    }

}
