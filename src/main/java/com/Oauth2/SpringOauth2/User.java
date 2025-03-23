package com.Oauth2.SpringOauth2;


public class User {
    private int userId; // Auto-generated
    private String userName;
    private String userEmail;
    private long userPhoneNo;
    private String userPassword; // Google does not provide password, set default
    private String userGender;
    private String userAddress;

    // Constructor
    public User(int userId, String userName, String userEmail, long userPhoneNo, String userPassword, String userGender, String userAddress) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhoneNo = userPhoneNo;
        this.userPassword = userPassword;
        this.userGender = userGender;
        this.userAddress = userAddress;
    }

    // Getters & Setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public long getUserPhoneNo() { return userPhoneNo; }
    public void setUserPhoneNo(long userPhoneNo) { this.userPhoneNo = userPhoneNo; }

    public String getUserPassword() { return userPassword; }
    public void setUserPassword(String userPassword) { this.userPassword = userPassword; }

    public String getUserGender() { return userGender; }
    public void setUserGender(String userGender) { this.userGender = userGender; }

    public String getUserAddress() { return userAddress; }
    public void setUserAddress(String userAddress) { this.userAddress = userAddress; }
}
