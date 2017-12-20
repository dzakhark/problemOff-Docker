package com.hw.service.util;

import com.hw.service.user.UserService;
import com.hw.util.security.TokenHandler;
import com.hw.util.security.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class TokenAuthServiceImpl implements TokenAuthService {
    private static final String AUTH_HEADER_NAME = "X-Auth-Token";

    private final TokenHandler tokenHandler;
    private final UserService userService;

    @Autowired
    public TokenAuthServiceImpl(TokenHandler tokenHandler, UserService userService) {
        this.tokenHandler = tokenHandler;
        this.userService = userService;
    }

    public Optional<Authentication> getAuthentication(HttpServletRequest request) {

        return Optional
                .ofNullable(request.getHeader(AUTH_HEADER_NAME))
                .flatMap(tokenHandler::extractUserId)
                .flatMap(userService::findById)
                .map(UserAuthentication::new);
    }
}
