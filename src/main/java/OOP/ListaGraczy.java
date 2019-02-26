package OOP;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ListaGraczy {

    private Map <String, Gracz> gracze = new HashMap<>();


    public synchronized void dodaj(String nazwaGracza, Gracz gracz) {
        gracze.put(nazwaGracza,gracz);
    }

    public synchronized Gracz podaj(String kto) {

        return gracze.get(kto);
    }

    public synchronized Set<String> podajNazwyGraczy() {
        HashMap<String, Gracz> kopiaGraczy = new HashMap<>(gracze);
        return kopiaGraczy.keySet();
    }




}
