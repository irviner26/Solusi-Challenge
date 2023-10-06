package com.chapterempat.handson.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "name", length = 100)
    private String name;
    @Column(name = "image_url", length = 100)
    private String image;
    @Column(name = "schedule", length = 100)
    private Date schedule;
    @Column(length = 100)
    private String info;
    @Column(length = 100)
    private String seat;

}
