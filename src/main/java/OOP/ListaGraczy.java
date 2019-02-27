package OOP;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ListaGraczy {

    private Map <String, Gracz> gracze = new HashMap<>();
    String wiadomosc;

    public synchronized String dodaj(String nazwaGracza, Gracz gracz) {



        if (nazwaGracza.equals("")) {
            wiadomosc = "Nie podałęś niku gracza";
            System.out.println(wiadomosc);
            return wiadomosc;
        } else if (gracze.containsKey(nazwaGracza)) {
            wiadomosc = "Podałeś "+nazwaGracza+" ale taki gracz już istnieje, podaj inny nik";
            System.out.println(wiadomosc);
            return wiadomosc;
        }   else {
            gracze.put(nazwaGracza, gracz);
            wiadomosc =  "Dodano nowego gracza";
            System.out.println(wiadomosc);
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
