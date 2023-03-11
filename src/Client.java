import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;
    public Client(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    public boolean menu() throws IOException {
        Scanner input = new Scanner(System.in);
        boolean exit = false;
        System.out.println("Choose the next operation: ");
        System.out.println("1)getAllAuthors");
        System.out.println("2)getAuthorId");
        System.out.println("3)addAuthor");
        System.out.println("4)addBook");
        System.out.println("5)getAllBooks");
        System.out.println("6)getBooksOf");
        System.out.println("7)exit");
        int sel = Integer.parseInt(input.nextLine());
        System.out.println(sel);
        switch (sel) {
            case 1 -> Utility.printArray(getAllAuthors());
            case 2 -> {
                System.out.println("Write the author you want the ID of");
                String name = input.nextLine();
                System.out.println("The author id is :" + getAuthorId(name));
            }
            case 3 -> {
                System.out.println("Enter the name of the author to add:");
                String name = input.nextLine();
                System.out.println("The id of the added author is: " +  addAuthor(name));
            }
            case 7 ->{
                System.out.println("Closing the connection...");
                exit = true;
            }
        }
        return exit;
    }

    private ArrayList<String> getAllAuthors() throws IOException {
        out.println("getAllAuthors\nend.");
        String readLine;
        ArrayList<String> authors  = new ArrayList<>();
        while((readLine = in.readLine()) != null){
            if(readLine.equalsIgnoreCase("end.")) break;
            authors.add(readLine);
        }
        return authors;
    }

    private int getAuthorId(String nome) throws IOException {
        out.println("getAuthorId\nend.");
        String readLine;
        int authId = -1;
        out.println(nome+"\nend.");
        while((readLine = in.readLine()) != null){
            if(readLine.equalsIgnoreCase("end.")) break;
            authId = Integer.parseInt(readLine);
        }
        return authId;
    }

    //Possibile eliminazione (?)
    private int addAuthor(String nome) throws IOException {
        out.println("addAuthor\nend.");
        String readLine;
        int authId = -1;
        out.println(nome+"\nend.");
        while((readLine = in.readLine()) != null){
            if(readLine.equalsIgnoreCase("end.")) break;
            authId = Integer.parseInt(readLine);
        }
        return authId;
    }

    public void close() throws IOException {
        socket.close();
        in.close();
        out.close();
    }
}
