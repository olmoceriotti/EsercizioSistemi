import java.io.IOException;

public class MainClient {
    public static void main(String[] args) throws IOException {
        Client client = new Client("localhost", 8080);
        boolean exit = false;
        while(!exit){
            exit = client.menu();
        }
        client.close();
    }
}
