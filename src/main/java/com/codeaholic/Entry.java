package com.codeaholic;

import java.util.Set;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Entry extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;

    public String title;

    @Column(length = 40, unique = true)
    public String slug;

    public String content; // Angular1 content to compile

    @ManyToOne(fetch = FetchType.LAZY)
    private Entry parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Entry> children;

    @JsonIgnore
    public Set<Entry> getChildren() {
        return null;
    }
}
