package com.shake_match.alchomist.group.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "permissions")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
}