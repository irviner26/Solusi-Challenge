package org.binaracademy.challenge4.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "userdb")
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(unique = true)
    @NotBlank
    private String uname;

    @Column(unique = true)
    @NotBlank
    @Email
    private String gmail;

    @NotBlank
    private String password;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable( name = "user_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private List<Merchant> merchants;

    public User(String uname, String gmail, String encode) {
        this.uname = uname;
        this.gmail = gmail;
        this.password = encode;
    }
}
