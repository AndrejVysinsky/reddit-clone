package com.example.RedditClone.users;

import com.example.RedditClone.helpers.ModelConstraints;

public class User
{
    private Integer userId;
    private String userName;
    private String password;
    private String passwordConfirmation;
    private String profileImage;

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public boolean isValid()
    {
        if (userName.length() < ModelConstraints.UserConstraints.minUsernameLength
        || userName.length() > ModelConstraints.UserConstraints.maxUsernameLength)
        {
            return false;
        }

        if (password.length() < ModelConstraints.UserConstraints.minPasswordLength
        || password.length() > ModelConstraints.UserConstraints.maxPasswordLength)
        {
            return false;
        }

        return true;
    }
}
