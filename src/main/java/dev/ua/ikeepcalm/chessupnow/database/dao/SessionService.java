package dev.ua.ikeepcalm.chessupnow.database.dao;

import dev.ua.ikeepcalm.chessupnow.database.entities.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public void save(Session session){
        sessionRepository.save(session);
    }

    public Session findBySessionId(String sessionId){
        Optional<Session> session = sessionRepository.findBySessionId(sessionId);
        if (session.isPresent()){
            return session.get();
        } else {
            throw new RuntimeException("Session not found for sessionId: " + sessionId);
        }
    }

    public Session findByUniqueId(Long uniqueId){
        Optional<Session> session = sessionRepository.findByUniqueId(uniqueId);
        if (session.isPresent()){
            return session.get();
        } else {
            throw new RuntimeException("Session not found for uniqueId: " + uniqueId);
        }
    }

    public void deleteBySessionId(String sessionId){
        sessionRepository.deleteBySessionId(sessionId);
    }
}
