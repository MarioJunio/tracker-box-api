package br.com.trackerapi.filter.security;

import br.com.trackerapi.entity.UserEntity;
import br.com.trackerapi.service.UserService;
import br.com.trackerapi.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        // check if authorization header is empty or not starts with "Bearer"
        if (!StringUtils.hasText(header) || !header.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        final String token = header.split(" ")[1];
        final String usernameFromToken = jwtTokenUtil.getUsernameFromToken(token);

        // check if the token has username and security context is empty
        if (StringUtils.hasText(usernameFromToken) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
            final UserEntity user = userService.getByUsername(usernameFromToken);

            // validate the token
            if (!jwtTokenUtil.validateToken(token, user)) {
                chain.doFilter(request, response);
                return;
            }

            // token valid, then create the authentication token
            final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // set authentication token in spring security context
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            chain.doFilter(request, response);
        }
    }
}
