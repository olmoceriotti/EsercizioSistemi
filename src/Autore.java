import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Autore {
    private final String name;
    private final int id;
    private final ArrayList<String> libri;
    private final String[] AUTORI = {"Jane Austen", "Ernest Hemingway", "Toni Morrison", "Gabriel Garcia Marquez", "J.K. Rowling"};
    private final ArrayList<String>[] LIBRI = new ArrayList[]{
            new ArrayList<>(Arrays.asList("Pride and Prejudice", "Sense and Sensibility")),
            new ArrayList<>(Arrays.asList("The Old Man and the Sea", "A Farewell to Arms", "For Whom the Bell Tolls")),
            new ArrayList<>(Arrays.asList("Beloved", "Song of Solomon")),
            new ArrayList<>(Arrays.asList("One Hundred Years of Solitude", "Love in the Time of Cholera", "Chronicle of a Death Foretold")),
            new ArrayList<>(Arrays.asList("Harry Potter and the Philosopher's Stone", "Harry Potter and the Chamber of Secrets"))
    };


    public Autore(String name){
        this.name = name;
        this.id = idGenerator(name);
        this.libri = new ArrayList<>();
    }
    public Autore(int i){
        this.name = AUTORI[i];
        this.id = idGenerator(AUTORI[i]);
        this.libri = LIBRI[i];
    }
    public static int idGenerator(String name) {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public boolean addBook(String book){
        return libri.add(book);
    }

    public int getLibriSize(){
        return libri.size();
    }

    public String getLibro(int indice){
        return libri.get(indice);
    }

    @Override
    public String toString() {
        return "Autore{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", libri=" + libri.toString() +
                '}';
    }
}
