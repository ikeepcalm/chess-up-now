package dev.ua.ikeepcalm.chessupnow.database.dao;

import dev.ua.ikeepcalm.chessupnow.database.entities.Connection;
import dev.ua.ikeepcalm.chessupnow.database.entities.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConnectionService {

    @Autowired
    private ConnectionRepository connectionRepository;
    @Autowired
    private SessionService sessionService;

    public void save(Connection connection){
        connectionRepository.save(connection);
    }

    public Connection findByUniqueId(Long uniqueId){
        Session session = sessionService.findByUniqueId(uniqueId);
        Optional<Connection> connection = connectionRepository.findByFirstSessionOrSecondSession(session, session);
        if (connection.isPresent()){
            return connection.get();
        } else {
            throw new RuntimeException("Connection not found for session: " + session.getSessionId());
        }
    }

    public void delete(Connection connection){
        connectionRepository.delete(connection);
    }
}
