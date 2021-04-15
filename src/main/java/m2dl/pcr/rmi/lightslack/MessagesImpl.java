package m2dl.pcr.rmi.lightslack;

import java.util.ArrayList;
import java.util.List;

public class MessagesImpl implements Messages {

    private List<String> messages;

    public MessagesImpl() {
        messages = new ArrayList<String>();
    }

        public void addMessage(String message) {
            messages.add(message);
        }

        public List<String> getMessages() {
            return messages;
        }

        public String popMessage() {
            if(messages.isEmpty()) {
                return "Il n'y a pas de message.";
            } else {
                return messages.remove(0);
            }
        }
}
