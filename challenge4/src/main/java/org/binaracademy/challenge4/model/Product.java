package org.binaracademy.challenge4.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productdb", uniqueConstraints = {
                        @UniqueConstraint(
                                columnNames = {"name","merchant_code"}
                        ) })
public class Product implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private String code;
    @ManyToOne
    private Merchant merchant;
    private String name;
    private double price;
}
