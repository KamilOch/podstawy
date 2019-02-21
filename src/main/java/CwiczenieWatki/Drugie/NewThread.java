package CwiczenieWatki.Drugie;

public class NewThread implements Runnable {
    Thread t;


    NewThread(){
        t=new Thread(this, "Przykładowy wątek");
        System.out.println("Wątek potomny: "+ t);
        t.start();
    }

    @Override
    public void run() {
        try{
            for(int i=5; i>0; i--){
                System.out.println("Wątek potomny:"+i);
                Thread.sleep(500);
            }
        }catch (InterruptedException e){
            System.out.println("Przerwano działane potomka");
        }
        System.out.println("Wyjście z wątku głównego");
    }
}
