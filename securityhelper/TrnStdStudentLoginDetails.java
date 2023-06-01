package com.ths.mims.securityhelper;

import com.ths.mims.trnstdstudent.TrnStdStudent;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class TrnStdStudentLoginDetails implements UserDetails {


    private TrnStdStudent trnStdStudent;

    public TrnStdStudentLoginDetails(TrnStdStudent trnStdStudent) {
        this.trnStdStudent = trnStdStudent;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("Student");
        return Arrays.asList(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return trnStdStudent.getStudentPassword();
    }

    @Override
    public String getUsername() {
        return trnStdStudent.getStudentUsername();
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
