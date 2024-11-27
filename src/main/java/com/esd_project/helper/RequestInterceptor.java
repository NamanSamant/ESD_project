package com.esd_project.helper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class RequestInterceptor implements HandlerInterceptor {
    private final JWTHelper jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String authorizationHeader = request.getHeader("Authorization"); // the interceptor extracts authorization header from the request

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // when the header is missing or doesn't start with "Bearer " then 401 unauthorized will be sent
            return false;
        }

        String token = authorizationHeader.substring(7); // Extracting the jwt token by removing the "Bearer " prefix which is of length 7
        String email = jwtUtil.extractEmail(token); // Extracting the username using extractUsername function

        if (email == null || !jwtUtil.validateToken(token, email)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // when username is null or the validation fails -- it will give 401 unauthorized
            return false;
        }

        return true; // when token is valid it returns true allowing the request to proceed to the designated controller
    }
}
