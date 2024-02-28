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
import jakarta.persistence.CascadeType;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import lombok.Getter;
import lombok.EqualsAndHashCode;

@Entity
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernate_lazy_initializer", "handler"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Entry extends PanacheEntityBase {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    public Long id;

    @Getter
    public String title;

    @Column(length = 40, unique = true)
    @Getter
    public String slug;

    @Getter
    public String content; // Angular1 content to compile

    @ManyToOne(fetch = FetchType.LAZY)
    private Entry parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Set<Entry> children;

    @JsonIgnore
    public Set<Entry> getChildren() {
        return children;
    }
}
