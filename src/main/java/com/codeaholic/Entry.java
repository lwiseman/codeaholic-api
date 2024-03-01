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
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties({"hibernate_lazy_initializer", "handler"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Entry extends PanacheEntityBase {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    private Long id;

    @Getter
    @Setter
    private String title;

    @Column(length = 40, unique = true)
    @Getter
    @Setter
    private String slug;

    @Getter
    @Setter
    private String content; // Angular1 content to compile

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Entry parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Setter
    private Set<Entry> children;

    @JsonIgnore
    public Set<Entry> getChildren() {
        return children;
    }

    public static Uni<Entry> findBySlug(String slug) {
        return find("slug", slug).firstResult();
    }
}
