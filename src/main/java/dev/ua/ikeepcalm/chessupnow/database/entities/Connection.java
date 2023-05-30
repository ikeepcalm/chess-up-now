package dev.ua.ikeepcalm.chessupnow.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identityId;

    @ManyToOne
    @JoinColumn(name = "firstSessionId")
    private Session firstSession;

    @ManyToOne
    @JoinColumn(name = "secondSessionId")
    private Session secondSession;

    @Column
    private boolean firstSessionValidated;

    @Column
    boolean secondSessionValidated;


}
