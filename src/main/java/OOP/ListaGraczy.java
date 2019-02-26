package OOP;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;

public class ListaGraczy {

    private Map <String, Gracz> gracze = new HashMap<>();


    public void dodaj(String nazwaGracza, Gracz gracz) {
        gracze.put(nazwaGracza,gracz);
    }

    public Gracz podaj(String kto) {

        return gracze.get(kto);
    }

    public ArrayList<String> podajNazwyGraczy() {
        ArrayList<String> kopiaListyGraczy = new ArrayList<>();
        kopiaListyGraczy.addAll((ArrayList<String>)gracze.keySet());
        return kopiaListyGraczy;
    }




}
