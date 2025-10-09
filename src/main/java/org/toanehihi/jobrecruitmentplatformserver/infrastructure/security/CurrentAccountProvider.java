package org.toanehihi.jobrecruitmentplatformserver.infrastructure.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.toanehihi.jobrecruitmentplatformserver.domain.exception.AppException;
import org.toanehihi.jobrecruitmentplatformserver.domain.exception.ErrorCode;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.Account;

@Component
public class CurrentAccountProvider {
    public Account getCurrentAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AppException(ErrorCode.AUTH_UNAUTHENTICATED);
        }

        return (Account) authentication.getPrincipal();
    }

    public Long getCurrentAccountId() {
        return getCurrentAccount().getId();
    }
}
