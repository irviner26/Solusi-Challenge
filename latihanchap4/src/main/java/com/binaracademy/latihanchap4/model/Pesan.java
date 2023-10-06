package com.binaracademy.latihanchap4.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "pesan"
        , uniqueConstraints = {
        @UniqueConstraint(columnNames = {"order_alamat","ojek_ojek_id"})
        })
public class Pesan {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private String pesanId;
    @Column (name = "order_alamat")
    private String alamat;
    @Column (name = "order_price")
    private Integer price;
    @ManyToOne
    private Ojek ojek;
}
