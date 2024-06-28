package com.hcorp.themesoflegends.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "t_utilisateur")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilisateur")
    private Long id;

    @Column(name = "u_nom_utilisateur")
    private String name;

    @Column(name = "u_uid_utilisateur")
    private String uid;

    @Column(name = "u_email")
    private String email;

    @Column(name = "u_mdp_utilisateur")
    private String password;

    @Column(name = "u_nb_partie_jouee")
    private Long gamePlayed;

    @OneToMany
    @JoinColumn(name = "id_record_point")
    private List<HighScore> highScore;

    @Column(name = "u_total_point")
    private Long totalScore;
}
