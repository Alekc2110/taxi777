package my.fin.project.model.entity;

import my.fin.project.model.entity.enums.Role;

public class User extends Person {


    private String email;

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public static class Builder {
        private String username;
        private String password;
        private String phoneNumber;
        private Role role;
        private String email;

        public Builder setUserName(String username) {
            this.username = username;

            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;

            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;

            return this;
        }

        public Builder setRole(Role role) {
            this.role = role;

            return this;
        }
        public Builder setEmail(String email) {
            this.email = email;

            return this;
        }


        public User build() {
            User user = new User();
            user.username = this.username;
            user.password = this.password;
            user.phoneNumber = this.phoneNumber;
            user.role = this.role;
            user.email = this.email;
            return user;
        }

    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                '}';
    }
}
