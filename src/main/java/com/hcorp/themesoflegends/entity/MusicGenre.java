package com.hcorp.themesoflegends.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "t_genre_musique")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MusicGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genre_musique")
    private Long id;

    @Column(name = "gm_nom_genre")
    private String value;
}
