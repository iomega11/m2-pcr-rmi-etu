package m2dl.pcr.rmi.lightslack;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class LightSlackClient {
    private String host;
    private String clientName;
    private Messages stub;

    public LightSlackClient(String host) {
        this.host = host;
        this.clientName = generateName();
    }

    private String generateName() {
        // TODO Rendre le nom d'utilisateur mieux fichu
        Random random = new Random();
        return "user" + random.nextInt();
    }

    private void connect() throws Exception {
        Registry registry = LocateRegistry.getRegistry(host);
        stub = (Messages) registry.lookup(LightSlackServer.BIND);
    }

    private void send(String message) throws RemoteException {
        stub.addMessage(message);
    }

    private List<String> receive() throws RemoteException {
        return stub.getMessages();
    }

    private void printMessages() throws RemoteException {
        receive().forEach(System.out::println);
    }

    private String waitForUserInput() {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("[" + getClientName() + "] ");
        return "[" + getClientName() + "] " + keyboard.nextLine();
    }

    public String getClientName() {
        return clientName;
    }

    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];

        LightSlackClient client = new LightSlackClient(host);
        System.out.println("[" + client.getClientName() + "] Client initialized.");

        try {
            client.connect();
            while(true) {
                client.send(client.waitForUserInput());
                client.printMessages();
            }

        } catch (Exception e) {
            System.err.println("[" + client.getClientName() + "] Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
