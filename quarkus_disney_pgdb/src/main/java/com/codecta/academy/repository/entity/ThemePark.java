package com.codecta.academy.repository.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema="codecta", name = "THEMEPARK")
public class ThemePark extends ModelObject{
    @SequenceGenerator(
            name = "themeParkSeq",
            sequenceName = "THEME_PARK_SEQ",
            schema = "codecta",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "themeParkSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    private String location;
    private String attractions;

    public List<DisneyCharacter> getDisneyCharacters() {
        return disneyCharacters;
    }

    public void setDisneyCharacters(List<DisneyCharacter> disneyCharacters) {
        this.disneyCharacters = disneyCharacters;
    }

    @OneToMany(mappedBy = "themePark", fetch = FetchType.LAZY)
    private List<DisneyCharacter> disneyCharacters = new ArrayList<>();

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAttractions() {
        return attractions;
    }

    public void setAttractions(String attractions) {
        this.attractions = attractions;
    }

    @Override
    public Integer getId() {
        return this.id;
    }
}
