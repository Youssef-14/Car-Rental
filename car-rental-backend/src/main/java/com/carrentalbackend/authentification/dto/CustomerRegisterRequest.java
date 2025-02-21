package com.carrentalbackend.authentification.dto;

public class CustomerRegisterRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String number;

    public CustomerRegisterRequest(String firstname, String lastname, String email, String password, String number) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.number = number;
    }

    public CustomerRegisterRequest() {
    }

    public static CustomerRegisterRequestBuilder builder() {
        return new CustomerRegisterRequestBuilder();
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getNumber() {
        return this.number;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CustomerRegisterRequest)) return false;
        final CustomerRegisterRequest other = (CustomerRegisterRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$firstname = this.getFirstname();
        final Object other$firstname = other.getFirstname();
        if (this$firstname == null ? other$firstname != null : !this$firstname.equals(other$firstname)) return false;
        final Object this$lastname = this.getLastname();
        final Object other$lastname = other.getLastname();
        if (this$lastname == null ? other$lastname != null : !this$lastname.equals(other$lastname)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$number = this.getNumber();
        final Object other$number = other.getNumber();
        if (this$number == null ? other$number != null : !this$number.equals(other$number)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CustomerRegisterRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $firstname = this.getFirstname();
        result = result * PRIME + ($firstname == null ? 43 : $firstname.hashCode());
        final Object $lastname = this.getLastname();
        result = result * PRIME + ($lastname == null ? 43 : $lastname.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $number = this.getNumber();
        result = result * PRIME + ($number == null ? 43 : $number.hashCode());
        return result;
    }

    public String toString() {
        return "RegisterRequest(firstname=" + this.getFirstname() + ", lastname=" + this.getLastname() + ", email=" + this.getEmail() + ", password=" + this.getPassword() + ", number=" + this.getNumber() + ")";
    }

    public static class CustomerRegisterRequestBuilder {
        private String firstname;
        private String lastname;
        private String email;
        private String password;
        private String number;

        CustomerRegisterRequestBuilder() {
        }

        public CustomerRegisterRequestBuilder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public CustomerRegisterRequestBuilder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public CustomerRegisterRequestBuilder email(String email) {
            this.email = email;
            return this;
        }

        public CustomerRegisterRequestBuilder password(String password) {
            this.password = password;
            return this;
        }

        public CustomerRegisterRequestBuilder number(String number) {
            this.number = number;
            return this;
        }

        public CustomerRegisterRequest build() {
            return new CustomerRegisterRequest(this.firstname, this.lastname, this.email, this.password, this.number);
        }

        public String toString() {
            return "RegisterRequest.RegisterRequestBuilder(firstname=" + this.firstname + ", lastname=" + this.lastname + ", email=" + this.email + ", password=" + this.password + ", number=" + this.number +")";
        }
    }
}
