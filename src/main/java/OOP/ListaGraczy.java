package OOP;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ListaGraczy {

    private Map <String, Gracz> gracze = new HashMap<>();


    public synchronized void dodaj(String nazwaGracza, Gracz gracz) {

        if (nazwaGracza.equals("")) {
            System.out.println("Nie podałęś niku gracza");
        } else if (gracze.containsKey(nazwaGracza)) {
            System.out.println("Podałeś "+nazwaGracza+" ale taki gracz już istnieje, podaj inny nik");
        }   else {
            gracze.put(nazwaGracza, gracz);
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
