package com.ths.mims.securityhelper;

public class JwtRequest {

    String username;
    String password;
    String loginAs;

    public JwtRequest() {
    }

    public JwtRequest(String username, String password, String loginAs) {
        this.username = username;
        this.password = password;
        this.loginAs = loginAs;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginAs() {
        return loginAs;
    }

    public void setLoginAs(String loginAs) {
        this.loginAs = loginAs;
    }

    @Override
    public String toString() {
        return "JwtRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
