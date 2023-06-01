package com.ths.mims.securityhelper;

import com.ths.mims.mstinvsupplier.MstInvSupplier;
import com.ths.mims.trnstdstudent.TrnStdStudent;
import com.ths.mims.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class UserLoginDetails implements UserDetails {

    private String loginAs = new String("User");

    private User user;
    private MstInvSupplier mstInvSupplier;
    private TrnStdStudent trnStdStudent;

    public UserLoginDetails(User user) {
        this.user = user;
        this.loginAs = "User";
    }
    public UserLoginDetails(MstInvSupplier mstInvSupplier) {
        this.mstInvSupplier = mstInvSupplier;
        this.loginAs = "Supplier";
    }
    public UserLoginDetails(TrnStdStudent trnStdStudent) {
        this.trnStdStudent = trnStdStudent;
        this.loginAs = "Student";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = null;
        if(loginAs.equals("User")){
            simpleGrantedAuthority = new SimpleGrantedAuthority(user.getUserRoleId().getRoleName());
        }else if(loginAs.equals("Supplier")){
            simpleGrantedAuthority = new SimpleGrantedAuthority("Supplier");
        }else if(loginAs.equals("Student")){
            simpleGrantedAuthority = new SimpleGrantedAuthority("Student");
        }else{ }
        return Arrays.asList(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        if(loginAs.equals("User")){
            return user.getUserPassword();
        }else if(loginAs.equals("Supplier")){
            return mstInvSupplier.getSupplierPassword();
        }else if(loginAs.equals("Student")){
            return trnStdStudent.getStudentPassword();
        }else{
            return null;
        }
    }

    @Override
    public String getUsername() {
        if(loginAs.equals("User")){
            return user.getUserUsername();
        }else if(loginAs.equals("Supplier")){
            return mstInvSupplier.getSupplierUsername();
        }else if(loginAs.equals("Student")){
            return trnStdStudent.getStudentUsername();
        }else{
            return null;
        }
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
