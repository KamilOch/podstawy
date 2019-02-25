package CwiczenieWatki.Trzecie;

public class NewThread2 implements Runnable {
    String name;
    Thread t;


    public static long dupa;
    String napis = "ala";

    NewThread2(String threadname){
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
//   synchronized ("alafdhadkkjfhsakj") {
       long dupa = NewThread2.dupa;
       dupa = dupa + 1;
       System.out.println(name+" dupa " + dupa );
       Thread.sleep(1);
       NewThread2.dupa = dupa;
//   }
    }



}
