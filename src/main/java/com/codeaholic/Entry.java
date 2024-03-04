package com.codeaholic;

import java.util.Set;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;
import jakarta.enterprise.context.RequestScoped;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import io.smallrye.mutiny.Uni;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

@Entity
@Cacheable
public class Entry extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "entry_seq_gen", sequenceName = "entry_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "entry_seq_gen")
    public Long id;

    public String title;

    @Column(length = 40, unique = true)
    public String slug;

    public String content; // Angular1 content to compile

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    public Set<Entry> children;

    @ManyToOne(targetEntity = Entry.class)
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    public Entry parent;

    public static Uni<Entry> findBySlug(String slug) {
        return find("slug", slug).firstResult();
    }
}
