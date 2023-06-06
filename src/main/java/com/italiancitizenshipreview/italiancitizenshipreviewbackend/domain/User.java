package com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain;

import jakarta.persistence.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

import static org.junit.Assert.assertEquals;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String password;
    private String roles;
    private String name;
    private String email;

    public User() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Test
    public void testToString() {
        User user = new User();
        user.setUserId(1L);
        user.setUsername("XXXXXXXX");
        user.setPassword("XXXXXXXX");
        user.setRoles("USER");
        user.setName("name");
        user.setEmail("email");
        assertEquals("User{userId=1, username='XXXXXXXX', password='XXXXXXXX', roles=USER, name='name', email='email'}", user.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(roles, user.roles) && Objects.equals(name, user.name) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, roles, name, email);
    }
}