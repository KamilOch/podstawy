package OOP;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class ControlerStatki {

   private ListaGraczy gracze = new ListaGraczy();

   private List <StatekSklep> statkiDoKulepinia = new ArrayList <>();
   private List <Statek> statkiZlomowisko = new ArrayList<>();

   private Object monitorTworzeniaGracz = new Object();
   private Object monitorPobieraniaZMapy = new Object();

    private Gracz gracz1;
    private Gracz gracz2;
    private Gracz gracz3;

    public ControlerStatki (){

            List <Statek> listaPoczatkowychStatkow = new ArrayList<>();
            listaPoczatkowychStatkow.add(new Krazownik(new Nacja("Gallente"), "Krążownik","Vexor", 300,53565));
            listaPoczatkowychStatkow.add(new Krazownik(new Nacja("Amarr"),"Krążownik","Maller", 294,35545));
            listaPoczatkowychStatkow.add(new Fregata(new Nacja("Gallente"), "Fregata", "Tristan", 100, 5600));
            listaPoczatkowychStatkow.add(new Fregata(new Nacja("Gallente"), "Fregata", "Atron", 4000,4000));

            gracz1= new Gracz(listaPoczatkowychStatkow,1000000);
            gracz2= new Gracz(listaPoczatkowychStatkow,1000000);
            gracz3= new Gracz(listaPoczatkowychStatkow,1000000);

        statkiDoKulepinia.add( new StatekSklep(new Nacja("Gallente"), "Krążownik","Vexor", 300,53565, 30000));
        statkiDoKulepinia.add(new StatekSklep(new Nacja("Amarr"),"Krążownik","Maller", 294,35545, 33000));
        statkiDoKulepinia.add(new StatekSklep(new Nacja("Gallente"), "Fregata", "Tristan", 100, 5600, 10000));
        statkiDoKulepinia.add(new StatekSklep(new Nacja("Gallente"), "Fregata", "Atron", 4000,4000, 8000));

        gracze.dodaj("Gracz1", gracz1);
        gracze.dodaj("Gracz2", gracz2);
        gracze.dodaj("Gracz3", gracz3);
    }


    void tworzenieGraczaWMapie (String nazwa){
        String zmienna = nazwa;
        gracze.dodaj(zmienna, new Gracz(new ArrayList<>(), 1000000));
    }

    private Gracz ktoryGracz (String kto) throws Wyjatek {
        Gracz gracz = null;
        try {
            gracz = gracze.podaj(kto);
        } catch (Wyjatek w){
            w.printStackTrace();
        }
        return gracz;
    }

    @RequestMapping("/dodajGracza")
    public String dodajGracza (
            @RequestParam(value = "NazwaGracza", required = false) String NazwaGracza
    ){
        tworzenieGraczaWMapie(NazwaGracza);
        //dupa debuging!
        System.out.println(gracze.podajNazwyGraczy());

        return "redirect:/dodawanie";
    }

    // tu bedzie metoda sprzedaz
    @RequestMapping("/sprzedazZUrl")
    public String sprzedaz (
            @RequestParam(value = "sprzedanyStatek", required = false) int sprzedanyStatek,
            @RequestParam(value = "ktoSprzedaje", required = false) String ktoSprzedaje
    ) throws Wyjatek {
        ktoryGracz(ktoSprzedaje).sprzedajStatek(sprzedanyStatek, statkiZlomowisko, statkiDoKulepinia);
        return "redirect:/sprzedaz?ktoSprzedaje="+ktoSprzedaje;
    }

    //Wersja na potrzeby gracza 1 oraz 2 i 3 z uzyciem kodu URL ze strona startowa
    @RequestMapping("/kupnoZUrl")
    public String kupnoStatkuUrl (
            @RequestParam(value = "kupionyStatek", required = false) String kupionyStatek,
            @RequestParam(value = "ktoKupuje", required = false) String ktoKupuje
    ) throws Wyjatek {
        System.out.println(ktoKupuje + " kupił statek " + kupionyStatek);
        ktoryGracz(ktoKupuje).kupStatek(kupionyStatek, statkiDoKulepinia);
        return "redirect:/zakupyURL?ktoKupuje=" + ktoKupuje;
    }
    //Wersja na potrzeby gracza 1 oraz 2 i 3 z uzyciem kodu URL
    @RequestMapping("/ktoKupuje")
    public String ktoKupuje (
            Model model
    ) {
        model.addAttribute("nazwaGracza",this.gracze.podajNazwyGraczy());
        return "ktoKupujeWybor";
    }

    // tu bedzie sprzedaz
    @RequestMapping ("/sprzedaz")
    public String sprzedaz (
            @RequestParam(value = "ktoSprzedaje", required = false) String ktoSprzedaje,
            Model model
    ) throws Wyjatek {
        List <Statek> statkiNaSprzedaz;
        statkiNaSprzedaz = ktoryGracz(ktoSprzedaje).getStatki();
        int kasaGracza = ktoryGracz(ktoSprzedaje).getKasa();

        model.addAttribute("ktoSprzedaje", ktoSprzedaje);
        model.addAttribute("statkiNaSprzedaz", statkiNaSprzedaz);
        model.addAttribute("kasaGracza", kasaGracza);
        model.addAttribute("statkiZlomowisko", statkiZlomowisko);

        return "zlomowisko";
    }

    @RequestMapping ("/dodawanie")
    public String dodawanie (
            Model model
    ) {
        model.addAttribute("nazwaGracza",gracze.podajNazwyGraczy());
        return "gracze";
    }

    //Wersja na potrzeby gracza 1 oraz 2 i 3 z uzyciem kodu URL
    @RequestMapping("/zakupyURL")
    public String zakupyURL (
            @RequestParam(value = "ktoKupuje", required = false) String ktoKupuje,
            Model model
    ) throws Wyjatek {
        int kasaDanegoGracza = ktoryGracz(ktoKupuje).getKasa();

        model.addAttribute("ktoKupuje", ktoKupuje);
        model.addAttribute("statkiDoKulepinia", statkiDoKulepinia);
        model.addAttribute("kasaDanegoGracza", kasaDanegoGracza);

        return "sklepZUrl";
    }

    @RequestMapping("/obliczenia")
    public String metodaObliczenia (
            @RequestParam(value = "statekGracza1", required = false) String statekGracza1,
            @RequestParam(value = "statekGracza2", required = false) String statekGracza2,
            @RequestParam(value = "pierwszy", required = false) String pierwszy,
            @RequestParam(value = "drugi", required = false) String drugi,
            Model model
    ){

        System.out.println(statekGracza1);
        System.out.println(statekGracza2);

       Statek pierwszyStatek = null;
       Statek drugiStatek = null;

        // foreach po liscie statki

        for (int i = 0; i < ktoryGracz(pierwszy).getStatki().size(); i++) {
            Statek statek = ktoryGracz(pierwszy).getStatki().get(i);

            if (statekGracza1.equals(statek.getNazwa())) {
                pierwszyStatek = ktoryGracz(pierwszy).getStatki().get(i);
            }
        }
        // foreach po liscie statki
        for (int i = 0; i < ktoryGracz(drugi).getStatki().size(); i++) {
            Statek statek = ktoryGracz(drugi).getStatki().get(i);

            if (statekGracza2.equals(statek.getNazwa())) {
                drugiStatek = ktoryGracz(drugi).getStatki().get(i);
            }
        }

       String wygranyStatek = TestStatki.walka(pierwszyStatek,drugiStatek);
       System.out.println(wygranyStatek);

      model.addAttribute("pierwszy" , "Nazwa pierwszego gracza: "+pierwszy+" a jego statek to " + pierwszyStatek.getNazwa());
      model.addAttribute("drugi" , "Nazwa drugiego gracza "+drugi+" a jego statkek to " + drugiStatek.getNazwa());
      model.addAttribute("wygrany" ,wygranyStatek);

        return "wynik";
    }

    @RequestMapping("/")
    public String listatGraczy (
            Model model
    ){
        model.addAttribute("nazwaGracza",this.gracze.podajNazwyGraczy());
        return "ktoWalczyWybor";
    }

    @RequestMapping("/ktoWalczy")
    public String listatStatkow (
            @RequestParam(value = "pierwszy", required = false) String pierwszy,
            @RequestParam(value = "drugi", required = false) String drugi,
            Model model
    ){
        model.addAttribute("statkiGracza1",ktoryGracz(pierwszy).getStatki());
        model.addAttribute("statkiGracza2",ktoryGracz(drugi).getStatki());
        model.addAttribute("pierwszy",pierwszy);
        model.addAttribute("drugi",drugi);

        return "walka";
    }
}



