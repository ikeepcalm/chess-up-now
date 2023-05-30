package dev.ua.ikeepcalm.chessupnow.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identityId;

    @Column(unique = true, nullable = false)
    private String sessionId;

    @Column(unique = true, nullable = false)
    private Long uniqueId;

}
