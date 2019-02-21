package CwiczenieWatki.Trzecie;

public class MultiThreadDemo {
    public static void main(String[] args) {
        new NewThread("Jeden");
        new NewThread("Dwa");
        new NewThread("Trzy");


        try {
            Thread.sleep(10000);
        }catch (InterruptedException e){
            System.out.println("Przerwano wątek główny");
        }
        System.out.println("Koniec wątku głównego");
    }
}
