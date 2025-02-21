package com.carrentalbackend.users.dto;

public class ChangePasswordRequest {

    private String currentPassword;
    private String newPassword;
    private String confirmationPassword;

    ChangePasswordRequest(String currentPassword, String newPassword, String confirmationPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.confirmationPassword = confirmationPassword;
    }

    public static ChangePasswordRequestBuilder builder() {
        return new ChangePasswordRequestBuilder();
    }

    public String getCurrentPassword() {
        return this.currentPassword;
    }

    public String getNewPassword() {
        return this.newPassword;
    }

    public String getConfirmationPassword() {
        return this.confirmationPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setConfirmationPassword(String confirmationPassword) {
        this.confirmationPassword = confirmationPassword;
    }

    public static class ChangePasswordRequestBuilder {
        private String currentPassword;
        private String newPassword;
        private String confirmationPassword;

        ChangePasswordRequestBuilder() {
        }

        public ChangePasswordRequestBuilder currentPassword(String currentPassword) {
            this.currentPassword = currentPassword;
            return this;
        }

        public ChangePasswordRequestBuilder newPassword(String newPassword) {
            this.newPassword = newPassword;
            return this;
        }

        public ChangePasswordRequestBuilder confirmationPassword(String confirmationPassword) {
            this.confirmationPassword = confirmationPassword;
            return this;
        }

        public ChangePasswordRequest build() {
            return new ChangePasswordRequest(this.currentPassword, this.newPassword, this.confirmationPassword);
        }

        public String toString() {
            return "ChangePasswordRequest.ChangePasswordRequestBuilder(currentPassword=" + this.currentPassword + ", newPassword=" + this.newPassword + ", confirmationPassword=" + this.confirmationPassword + ")";
        }
    }
}
