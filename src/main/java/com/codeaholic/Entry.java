package com.codeaholic;

import java.util.Set;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Entry extends PanacheEntity {
    public String title;
    @Column(length = 40, unique = true)
    public String slug;
    public String content; // Angular1 content to compile

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    private Entry parent;

    @JsonIgnore
    public Set<Entry> getChildren() {
        return null;
    }
    */
}
