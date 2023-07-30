package ifTG.travelPlan.controller.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Controller
public class PostWebSocket extends TextWebSocketHandler {
    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        byte[] data = message.getPayload().array();

        BinaryMessage responseMessage = new BinaryMessage(data);

        try {
            session.sendMessage(responseMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
