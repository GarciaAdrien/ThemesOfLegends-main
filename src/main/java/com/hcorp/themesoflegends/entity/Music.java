package com.hcorp.themesoflegends.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "t_musique")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_musique")
    private Long id;

    @Column(name = "m_token")
    private String token;

    @Column(name = "m_nom_musique")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_genre_musique")
    private MusicGenre genre;

    @ManyToOne
    @JoinColumn(name = "id_date_musique")
    private MusicDate date;

    @Column(name = "m_url")
    private String url;

    @ElementCollection
    @CollectionTable(name = "t_alias", joinColumns = @JoinColumn(name = "id_musique"))
    @Column(name = "a_alias")
    private List<String> aliases;

}
