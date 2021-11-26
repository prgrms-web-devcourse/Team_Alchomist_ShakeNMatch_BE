package com.shake_match.alchomist.image;

import com.shake_match.alchomist.global.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "images")
public class Image extends BaseEntity {
    @Id
    Long id;

    @Column
    String imageUrl;
}
