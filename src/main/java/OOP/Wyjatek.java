package OOP;

//Wyjatek typu un checked!!
public class Wyjatek extends RuntimeException {
//Wyjatek typu checked!!
//public class Wyjatek extends Exception {
    private String informacja;

    public String getMessage() {
        return "Nie ma takiego numeru gracza "+informacja;
    }
    public Wyjatek (String s){
        informacja = s;
    }
    public String toString (){
        return "Nie ma takiego numeru gracza "+informacja;
    }
}
