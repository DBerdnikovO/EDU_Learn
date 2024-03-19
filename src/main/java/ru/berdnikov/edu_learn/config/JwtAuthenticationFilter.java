package ru.berdnikov.edu_learn.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.berdnikov.edu_learn.security.PersonDetails;
import ru.berdnikov.edu_learn.service.PersonService;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Value("${spring.jjwt.header.string}")
    public String HEADER_STRING;
    @Value("${spring.jjwt.token.prefix}")
    public String TOKEN_PREFIX;

    private final Logger logger;
    private final JwtProvider jwtProvider;
    private final PersonService personService;

    @Autowired
    public JwtAuthenticationFilter(Logger logger, JwtProvider jwtProvider, PersonService personService) {
        this.logger = logger;
        this.jwtProvider = jwtProvider;
        this.personService = personService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HEADER_STRING);
        logger.info(" Header : {}", header);
        String authToken = null;
        String email = null;

        if(header != null && header.startsWith(TOKEN_PREFIX)) {
            authToken = header.substring(7);
            try {
                email = jwtProvider.getEmailFromToken(authToken);
            } catch (IllegalArgumentException e) {
                logger.info("Illegal Argument while fetching the username !!");
                e.printStackTrace();
            } catch (ExpiredJwtException e) {
                logger.info("Given jwt token is expired !!");
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT Token Expired");
            } catch (MalformedJwtException e) {
                logger.info("Some changed has done in token !! Invalid Token");
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT Token");
                return;
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return;
            }
        } else {
            logger.warn("Invalid Header Value !! ");
        }

        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails personDetails = personService.loadUserByUsername(email);
            Boolean validateToken = jwtProvider.validateToken(authToken,email);
            if(validateToken) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        personDetails,
                        null,
                        personDetails.getAuthorities()
                );
                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                logger.warn("Validation fails!");
            }
        }
        filterChain.doFilter(request, response);
    }
}