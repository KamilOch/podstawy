package CwiczenieWatki.Trzecie;

import java.util.Random;

public class Dupa
{

    public static String rwa = "rwa";

    public static void main(String[] args) {

        rwa = "aaa";


        System.out.println("kurwa " == "kurwa ");

        System.out.println("kurwa".hashCode());
        String kurwa = "chuj";
                kurwa = "ku";
        boolean b = new Random().nextBoolean();

        rwa = "rwa";
        if (b) {
            kurwa += rwa;

        }

        System.out.println(kurwa);

        System.out.println((new String(kurwa)).hashCode());

    }
}
