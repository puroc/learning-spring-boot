package com.example.springboot.dao;

import com.example.springboot.Global;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class TenantIdResolver implements CurrentTenantIdentifierResolver {
    //获取当前 tenantId
    @Override
    public String resolveCurrentTenantIdentifier() {
//        return Login.getTenantId();
        return Global.getInstance().getTenants().get();
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }

}