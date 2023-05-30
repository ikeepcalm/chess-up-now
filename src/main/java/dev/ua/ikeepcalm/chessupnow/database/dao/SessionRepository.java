package dev.ua.ikeepcalm.chessupnow.database.dao;

import dev.ua.ikeepcalm.chessupnow.database.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    Optional<Session> findBySessionId(String sessionId);

    Optional<Session> findByUniqueId(Long uniqueId);

    void deleteBySessionId(String sessionId);

}
