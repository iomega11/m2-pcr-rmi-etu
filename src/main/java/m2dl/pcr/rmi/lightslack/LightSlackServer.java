package m2dl.pcr.rmi.lightslack;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class LightSlackServer {

    public static String BIND = "LightSlack";

    public LightSlackServer() {
    }

    private Messages convertObject() throws RemoteException {
        Messages obj = new MessagesImpl();
        return (Messages) UnicastRemoteObject.exportObject(obj, 0);
    }

    private void bindObject(Messages stub) throws AlreadyBoundException, RemoteException {
        // Bind the remote object's stub in the registry
        Registry registry = LocateRegistry.getRegistry();
        registry.bind(BIND, stub);
    }

    public static void main(String... args) {

        LightSlackServer server = new LightSlackServer();

        try {
            Messages stub = server.convertObject();
            server.bindObject(stub);
            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
