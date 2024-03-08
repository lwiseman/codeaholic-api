package com.codeaholic;

import java.util.Set;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.smallrye.mutiny.Uni;

@Entity
@Cacheable
public class Entry extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "entry_seq_gen", sequenceName = "entry_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "entry_seq_gen")
    @JsonIgnore
    public Long id;

    public String title;

    public String slug;

    @Column(columnDefinition = "TEXT")
    public String content; // Angular1 content to compile

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Entry> children;

    @ManyToOne()
    @JsonIgnore
    public Entry parent;

    public static Uni<Entry> findBySlug(String slug) {
        return find("slug", slug).firstResult();
    }
}
