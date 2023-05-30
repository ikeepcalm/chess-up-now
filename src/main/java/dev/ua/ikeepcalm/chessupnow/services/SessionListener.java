package dev.ua.ikeepcalm.chessupnow.services;

import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import dev.ua.ikeepcalm.chessupnow.database.dao.SessionService;
import dev.ua.ikeepcalm.chessupnow.database.entities.Session;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionListener implements VaadinServiceInitListener {

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private SessionService sessionService;


    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addSessionInitListener(initEvent -> {
            Session session = new Session();
            session.setUniqueId(idGenerator.generateUniqueCode());
            LoggerFactory.getLogger(getClass()).info("New session: " + initEvent.getSession().getPushId());
            session.setSessionId(initEvent.getSession().getPushId());
            sessionService.save(session);
        });

        event.getSource().addSessionDestroyListener(initEvent -> {
            LoggerFactory.getLogger(getClass()).info("Delete session: " + initEvent.getSession().getPushId());
            sessionService.deleteBySessionId(initEvent.getSession().getPushId());
        });
    }
}
