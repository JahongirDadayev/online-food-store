package com.example.onlinefoodstore.commons.filter;

import com.example.onlinefoodstore.commons.constans.CommonOpenEndpoints;
import com.example.onlinefoodstore.model.dto.Header;
import com.example.onlinefoodstore.model.entities.User;
import com.example.onlinefoodstore.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter implements CommonOpenEndpoints {
    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws IOException {
        try {
            String token = Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                    .filter(auth -> auth.startsWith("Bearer"))
                    .map(auth -> auth.replace("Bearer ", ""))
                    .orElseThrow(() -> new BadCredentialsException("Invalid token!"));
            String username = jwtService.extractUsername(token);
            if (Objects.nonNull(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                User user = (User) userDetailsService.loadUserByUsername(username);
                if (jwtService.isTokenValid(token, user)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("Exception occurred while request being filtered {0}", e);
            writerErrorResp(e, response);
        }
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        return OPEN_ENDPOINTS.values().stream().flatMap(Collection::stream).anyMatch(endpoint -> new AntPathMatcher().match(endpoint, request.getServletPath()));
    }

    protected void writerErrorResp(Exception exception, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        Header<String> customResponse = Header.error("E000", exception.getMessage());
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        final var content = objectMapper.writeValueAsString(customResponse);
        writer.println(content);
    }
}
