import java.util.Arrays;
import java.util.Objects;

public class Autore {
    private final String name;
    private final int id;
    private final String[] libri;
    private final String[] AUTORI = {"Jane Austen", "Ernest Hemingway", "Toni Morrison", "Gabriel Garcia Marquez", "J.K. Rowling"};
    private final String[][] LIBRI ={
        {"Pride and Prejudice", "Sense and Sensibility"},
        {"The Old Man and the Sea", "A Farewell to Arms", "For Whom the Bell Tolls"},
        {"Beloved", "Song of Solomon"},
        {"One Hundred Years of Solitude", "Love in the Time of Cholera", "Chronicle of a Death Foretold"},
        {"Harry Potter and the Philosopher's Stone", "Harry Potter and the Chamber of Secrets"}
    };

    public Autore(String name){
        this.name = name;
        this.id = idGenerator(name);
        this.libri = new String[5];
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

    @Override
    public String toString() {
        return "Autore{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", libri=" + Arrays.toString(libri) +
                '}';
    }
}
