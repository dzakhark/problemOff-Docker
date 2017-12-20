package com.hw.service.util;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface TokenAuthService {
    Optional<Authentication> getAuthentication(HttpServletRequest request);
}
