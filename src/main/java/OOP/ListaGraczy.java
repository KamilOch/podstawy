package OOP;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ListaGraczy {

    private Map <String, Gracz> gracze = new HashMap<>();
    String wiadomosc;

    public synchronized String dodaj(String nazwaGracza, Gracz gracz) {



        if (nazwaGracza.equals("")) {
            System.out.println("Nie podałęś niku gracza");
            wiadomosc = "Nie podałęś niku gracza";
            return wiadomosc;
        } else if (gracze.containsKey(nazwaGracza)) {
            System.out.println("Podałeś "+nazwaGracza+" ale taki gracz już istnieje, podaj inny nik");
            wiadomosc = "Podałeś "+nazwaGracza+" ale taki gracz już istnieje, podaj inny nik";
            return wiadomosc;
        }   else {
            gracze.put(nazwaGracza, gracz);
            wiadomosc =  "Dodano nowego gracza";
            return wiadomosc;
       }
    }

    public synchronized Gracz podaj(String kto) {

        return gracze.get(kto);
    }

    public synchronized Set<String> podajNazwyGraczy() {
        HashMap<String, Gracz> kopiaGraczy = new HashMap<>(gracze);
        return kopiaGraczy.keySet();
    }




}
