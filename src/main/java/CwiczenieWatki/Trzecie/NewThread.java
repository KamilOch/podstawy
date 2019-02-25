package CwiczenieWatki.Trzecie;

public class NewThread implements Runnable {
    String name;
    Thread t;


    private static long dupa;
    String napis = "ala";

    NewThread (String threadname){
        name=threadname;
        t=new Thread(this, name);
        System.out.println("Nowy wątek: "+t);
        t.start();
        System.out.println("2 Nowy wątek: "+t);

    }




    public void run() {
        try{
            for (int i=500;i>0; i--){
                jakkolwiek();
                System.out.println(name + ":"+i);

               Thread.sleep(1);
            }
        } catch (InterruptedException e){
            System.out.println("Przerwano " + name);
        }
        System.out.println("Zakończenie " + name);
    }

   private void jakkolwiek() throws InterruptedException {
   synchronized ("ala") {
       long dupa = NewThread.dupa;
       dupa = dupa + 1;
       System.out.println(name+" dupa " + dupa );
       Thread.sleep(1);
       NewThread.dupa = dupa;
   }
    }



}
