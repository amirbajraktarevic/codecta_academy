package com.codecta.academy.repository.entity;

public class WelcomeMessage {
    private String welcome;
    private String description;
    private String workingHours;

    public WelcomeMessage(String welcome, String description, String workingHours) {
        this.welcome = welcome;
        this.description = description;
        this.workingHours = workingHours;
    }

    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }
}
