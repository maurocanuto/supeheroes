package com.superheroes.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.superheroes.utils.JsonDateSerializer;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by mcanuto on 5/19/16.
 */


@Entity
@NamedQueries({
        @NamedQuery(name="findAllSuperheroes",
                query="SELECT e FROM Hero e")
})

@JsonAutoDetect
@Table(name = "Hero")


public class Hero {
/*
    @Id
    @GeneratedValue
    @Column(name="HERO_ID")
    private long id;*/

    @Id
    @Column(name = "NAME", length = 255)
    private String name;

    @Column(name = "PSEUDONYM", length = 255)
    private String pseudonym;

    @Column(name ="PUBLISHER", length = 255)
    private String publisher;


    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch (FetchMode.SELECT)
    @CollectionTable(name = "Hero_Skills")
    private List<String> skills;

    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @CollectionTable(name = "Hero_Allies")
    private List<String> allies;

    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional=true)
    private Date appearance;

    public Hero() {
    }

    public Hero(String name, String pseudonym, String publisher, List<String> skills, List<String> allies, Date appearance) {
        this.name = name;
        this.pseudonym = pseudonym;
        this.publisher = publisher;
        this.skills = skills;
        this.allies = allies;
        this.appearance = appearance;
    }

    /*public long getId() {
        return id;
    }*/

    public String getName() {
        return name;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public String getPublisher() {
        return publisher;
    }

    public List<String> getSkills() {
        return skills;
    }

    public List<String> getAllies() {
        return allies;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date getAppearance() {
        return appearance;
    }

    @JsonSerialize(using=JsonDateSerializer.class)
    public void setAppearance(Date appearance) {
        this.appearance = appearance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public void setAllies(List<String> allies) {
        this.allies = allies;
    }
}
