package ru.berdnikov.edu_learn.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.berdnikov.edu_learn.security.PersonDetails;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()){
            PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
            return Optional.of(personDetails.getPersonId());
        }
        return Optional.empty();
    }
}
