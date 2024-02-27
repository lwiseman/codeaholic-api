package com.codeaholic;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Entry extends PanacheEntity {
    public String title;
}
