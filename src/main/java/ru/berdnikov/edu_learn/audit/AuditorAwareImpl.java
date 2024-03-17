package ru.berdnikov.edu_learn.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.berdnikov.edu_learn.security.PersonDetails;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()){
            return Optional.of(((PersonDetails) authentication.getPrincipal()).getPersonId().toString());
        }
        return Optional.empty();
    }
}
