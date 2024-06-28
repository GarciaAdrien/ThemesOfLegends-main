package com.hcorp.themesoflegends.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_liaison_avatar_utilisateur")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAvatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_liaison_avatar_utilisateur")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_avatar")
    private Avatar avatar;

    @Column(name = "lau_selectionnable")
    private boolean isSelectable;

    @Column(name = "lau_selectione")
    private boolean isSelected;
}
