package dev.ua.ikeepcalm.chessupnow.database.dao;

import dev.ua.ikeepcalm.chessupnow.database.entities.Connection;
import dev.ua.ikeepcalm.chessupnow.database.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long> {

    Optional<Connection> findByFirstSession(Session session);
    Optional<Connection> findBySecondSession(Session session);
    Optional<Connection> findByFirstSessionAndSecondSession(Session firstSession, Session secondSession);
    Optional<Connection> findByFirstSessionOrSecondSession(Session firstSession, Session secondSession);

}
