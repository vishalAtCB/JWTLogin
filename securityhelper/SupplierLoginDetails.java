package com.ths.mims.securityhelper;

import com.ths.mims.mstinvsupplier.MstInvSupplier;
import com.ths.mims.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class SupplierLoginDetails implements UserDetails {

    private MstInvSupplier mstInvSupplier;

    public SupplierLoginDetails(MstInvSupplier mstInvSupplier) {
        this.mstInvSupplier = mstInvSupplier;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("Supplier");
        return Arrays.asList(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return mstInvSupplier.getSupplierPassword();
    }

    @Override
    public String getUsername() {
        return mstInvSupplier.getSupplierUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
