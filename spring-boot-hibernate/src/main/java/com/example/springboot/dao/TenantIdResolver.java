package com.example.springboot.dao;

import com.example.springboot.domain.Login;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class TenantIdResolver implements CurrentTenantIdentifierResolver {
    //获取当前 tenantId
    @Override
    public String resolveCurrentTenantIdentifier() {
        return Login.getTenantId();
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }

}