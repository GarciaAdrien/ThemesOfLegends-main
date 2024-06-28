package com.hcorp.themesoflegends.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_record")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HighScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_record")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private User user;

    @Column(name="r_nombre_manche")
    private Long roundNumber;

    @Column(name="u_score")
    private Long highScoreValue;

    @Column(name="r_maitrise")
    private String mastery;
}
