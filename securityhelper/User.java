package com.ths.mims.securityhelper;

import lombok.Data;

//@Entity
//@Table(name = "USER")
@Data
public class User {

    //    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String userUsername;
    private String userPassword;
    private String userEmail;
    private String roleName;
    private boolean enabled;
    //more properties as your project requirements

//    public User() {
//    }

    public User(Long userId, String userUsername, String userPassword, String userEmail, String roleName, boolean enabled) {
        this.userId = userId;
        this.userUsername = userUsername;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.roleName = roleName;
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", username='" + userUsername + '\'' +
                ", password='" + userPassword + '\'' +
                ", email='" + userEmail + '\'' +
                ", rol='" + roleName + '\'' +
                ", rol='" + roleName + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
