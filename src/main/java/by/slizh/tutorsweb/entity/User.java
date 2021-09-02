package by.slizh.tutorsweb.entity;

import java.io.InputStream;

public class User extends Entity {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String city;
    private InputStream photo;
    private Role role;
    private Status status;

    public User(){
    }

    public User(int userId, String firstName, String lastName, String email, String city, InputStream photo, Role role, Status status){
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.city = city;
        this.photo = photo;
        this.role = role;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public InputStream getPhoto() {
        return photo;
    }

    public void setPhoto(InputStream photo) {
        this.photo = photo;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Role {
        ADMIN("admin", 1),
        USER("user", 2),
        TUTOR("tutor", 3);

        private String value;
        private int id;

        Role(String value, int id) {
            this.value = value;
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public int getId() {
            return id;
        }
    }

    public enum Status {
        ACTIVE("active", 1),
        INACTIVE("inactive", 2),
        REJECTED("rejected", 3),
        BLOCKED("blocked", 4);

        private String value;
        private int id;

        Status(String value, int id) {
            this.value = value;
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public int getId() {
            return id;
        }

    }

    public static class UserBuilder{
        User user;

        public UserBuilder(){
            user = new User();
        }

        public UserBuilder setUserId(int userId) {
            user.setUserId(userId);
            return this;
        }

        public UserBuilder setFirstName(String firstName) {
            user.setFirstName(firstName);
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            user.setLastName(lastName);
            return this;
        }

        public UserBuilder setEmail(String email) {
            user.setEmail(email);
            return this;
        }

        public UserBuilder setCity(String city) {
            user.setCity(city);
            return this;
        }

        public UserBuilder setPhoto(InputStream photo) {
            user.setPhoto(photo);
            return this;
        }

        public UserBuilder setRole(Role role) {
            user.setRole(role);
            return this;
        }

        public UserBuilder setStatus(Status status) {
            user.setStatus(status);
            return this;
        }

        public User createUser() {
            return user;
        }
    }
}
