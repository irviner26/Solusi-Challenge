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
@Table(name = "detaildb")
public class Detail implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "order_id")
    private Order order;
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "product_code")
    private Product product;
    private int quantity;
    private double total;
}
