import java.io.IOException;
import java.util.Scanner;

public class MainServer {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        Server server = new Server();
        boolean connect = true;
        while(true){
            if(connect){
                server.connect();
            }
            connect = server.select();

            System.out.println("Do you want to close the server? (y/N)");
            if(input.nextLine().equalsIgnoreCase("y")) break;
        }
        server.close();

    }
}
