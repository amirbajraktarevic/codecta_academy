package com.codecta.academy.services.model;

import com.codecta.academy.repository.entity.ThemePark;

public class CharacterDto {
    private Integer id;
    private String name;
    private String greeting;
    private Integer themeParkId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public Integer getThemeParkId() {
        return themeParkId;
    }

    public void setThemeParkId(Integer themeParkId) {
        this.themeParkId = themeParkId;
    }
}
