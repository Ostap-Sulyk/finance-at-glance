package com.financeatglance.financeatglance.security.filter;

import com.financeatglance.financeatglance.exceptions.ErrorMessageWrapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.filter.OncePerRequestFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import java.io.IOException;

public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (BadCredentialsException e) {
            handleException(response, e, HttpServletResponse.SC_UNAUTHORIZED);
        } catch (RuntimeException | IOException | ServletException e) {
            handleException(response, e, HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    private static void handleException(HttpServletResponse response, Exception e, Integer statusCode) throws IOException {
        response.setStatus(statusCode);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ObjectMapper objectMapper = new ObjectMapper();
        ErrorMessageWrapper errorMessageWrapper = new ErrorMessageWrapper(e.getMessage());
        String errorMessage = objectMapper.writeValueAsString(errorMessageWrapper);
        response.getWriter().write(errorMessage);
    }

}
