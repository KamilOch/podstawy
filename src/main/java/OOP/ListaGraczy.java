package OOP;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ListaGraczy {

    private Map <String, Gracz> gracze = new HashMap<>();


    public void dodaj(String nazwaGracza, Gracz gracz) {
        gracze.put(nazwaGracza,gracz);
    }

    public Gracz podaj(String kto) {

        return gracze.get(kto);
    }

    public Set<String> podajNazwyGraczy() {
        //Set<String> kopiaListyGraczy = null;
        //kopiaListyGraczy.addAll(gracze.keySet());
        //return kopiaListyGraczy;
        return gracze.keySet();
    }




}
