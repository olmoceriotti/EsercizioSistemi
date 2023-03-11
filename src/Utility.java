import java.util.ArrayList;

public class Utility {

    /*public static void printArray(String[] array){
        for(int i  = 0; i < array.length; i++){
            System.out.println(array[i]);
        }
    }*/

    public static void printArray(ArrayList<String> array){
        for(int i  = 0; i < array.size(); i++){
            System.out.println(array.get(i));
        }
    }
}
