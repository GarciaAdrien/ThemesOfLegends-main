package com.hcorp.themesoflegends.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_avatar")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_avatar")
    private Long id;

    @Column(name = "a_token")
    private String token;

    @Column(name = "a_prix")
    private Long price;
}
