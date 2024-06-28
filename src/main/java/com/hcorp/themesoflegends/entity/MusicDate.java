package com.hcorp.themesoflegends.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "t_date_musique")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MusicDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_date_musique")
    private Long id;

    @Column(name = "dm_annee_date")
    private String value;
}
