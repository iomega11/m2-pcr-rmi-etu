package m2dl.pcr.rmi.lightslack;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Messages extends Remote {
    void addMessage(String message) throws RemoteException;

    List<String> getMessages() throws RemoteException;

    String popMessage() throws RemoteException;
}
