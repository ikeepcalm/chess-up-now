package dev.ua.ikeepcalm.chessupnow.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import dev.ua.ikeepcalm.chessupnow.database.dao.ConnectionService;
import dev.ua.ikeepcalm.chessupnow.database.dao.SessionService;
import dev.ua.ikeepcalm.chessupnow.database.entities.Connection;
import dev.ua.ikeepcalm.chessupnow.database.entities.Session;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "")
@PageTitle(value = "Chess Up Now")
public class MainView extends VerticalLayout {

    private final SessionService sessionService;
    private final ConnectionService connectionService;
    TextField inputCode;

    @Autowired
    public MainView(SessionService sessionService, ConnectionService connectionService) {
        this.sessionService = sessionService;
        this.connectionService = connectionService;
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        H1 title = new H1("Chess Up Now");

        TextField yourCode = new TextField();
        yourCode.setLabel("Your code");
        yourCode.setReadOnly(true);
        Session session = sessionService.findBySessionId(VaadinSession.getCurrent().getPushId());
        yourCode.setValue(String.valueOf(session.getUniqueId()));

        inputCode = new TextField();
        inputCode.setLabel("Enter Code");

        Button startButton = new Button("Start Game");
        startButton.addClickListener(e -> startListener(inputCode.getValue(), session));

        add(title);
        add(yourCode);
        add(inputCode);
        add(startButton);
    }

    private void startListener(String uniqueId, Session selfSession) {
        try {
            Connection connection = connectionService.findByUniqueId(Long.valueOf(uniqueId));
            connection.setSecondSessionValidated(true);
            connectionService.save(connection);
            inputCode.setReadOnly(true);
            Notification.show("Both codes validated!");
        } catch (RuntimeException ex){
            Connection connection = new Connection();
            connection.setFirstSession(selfSession);
            connection.setFirstSessionValidated(true);
            try{
                Session foundSession = sessionService.findByUniqueId(Long.valueOf(uniqueId));
                connection.setSecondSession(foundSession);
                connectionService.save(connection);
                inputCode.setReadOnly(true);
            } catch (RuntimeException e){
                Notification.show("Code is not valid!");
            }
        }
    }
}
