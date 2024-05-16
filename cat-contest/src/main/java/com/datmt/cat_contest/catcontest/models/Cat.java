package com.datmt.cat_contest.catcontest.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity
@Data
@Table(name = "cat", indexes = @Index(columnList = "name", unique = true))
@EqualsAndHashCode(exclude = "contests")
public class Cat {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String image;

    @ManyToMany(mappedBy = "cats")
    private Set<Contest> contests;
}
