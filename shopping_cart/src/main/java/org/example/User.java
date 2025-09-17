package org.example;

public class User {
    private String userId;
    private String username;
    private String email;
    private String password;
    private String address;


    public User(String userId, String username, String password, String email, String address) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
    }


    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return String.format("User{id='%s', username='%s', email='%s'}",
                userId, username, email);
    }
}
