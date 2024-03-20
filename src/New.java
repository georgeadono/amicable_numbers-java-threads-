//icsd16007, Antonopoulos Georgios


import java.util.*;

class GFG  {
    static int divSum(int n) { //vriskei athroisma diairetwn
        int result = 0;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                if (i == (n / i))
                    result += i;
                else
                    result += (i + n / i);
            }
        }return (result + 1);
    }
}
class Multithreading extends Thread {
    private int thr;
    private int n;
    Hashtable<Integer,Integer> amic;
    Multithreading(int i,Hashtable<Integer,Integer> amics, int n){
        thr=i;
        amic=amics;
        this.n = n;
    }
    public void run(){
        int num = 1+(thr-1)*(3000000/n); /*O αριθμός που θα ξεκινάει να υπολογίστει το thread. Για thr=1 -> num = 1,
                                                                                         Για thr=2 -> num = 10001
                                                                                         Για thr=3 -> num = 20001
                                                                                         Για thr=4 -> num = 30001*/

        for (int j=num;j<num+(3000000/n);j++){
            //pws ektelountai ta threads
            System.out.println("Thread " + getName() );
            try {
                //gia na mhn emfanizei to idio zeugari 2h fora
                int a = j;
                int b = GFG.divSum(a);
                int c = GFG.divSum(b);
                int d = GFG.divSum(c);
                if ((a!=b) && (a==c) && (b==d)){
                    if (a>b){
                        amic.put(b,a);
                    }else if (b<a){
                        amic.put(a,b);
                    }
                }
                //System.out.println("Thread " + Thread.currentThread().getId()+" has numer "+a+" : Its sum of gfg is : "+b+" And the sum of gfg this number is: "+c);
            }
            catch (Exception e) {
                // Throwing an exception
                System.out.println("Exception is caught");
            }
        }
    }
}

// Main Class
public class New{
    public static void main(String[] args) throws InterruptedException{
        Hashtable<Integer,Integer> amics = new Hashtable<>();
        long startTime = System.currentTimeMillis();;
        Multithreading object = null;

        System.out.println("Se posa nhmata tha thelate na treksei ?");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i=1;i<=n;i++){
            object = new Multithreading(i,amics, n);
            object.start();

        }

        object.join();
        TreeMap<Integer, Integer> sorted = new TreeMap<>();

        // Copy all data from hashMap into TreeMap
        sorted.putAll(amics);

        // Display the TreeMap which is naturally sorted
        for (Map.Entry<Integer, Integer> entry : sorted.entrySet())
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());

        long endTime = System.currentTimeMillis();;
        // get difference of two nanoTime values
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time   : " + timeElapsed);

    }

}
//Sigoura oso pio polla nhmata epilegoume toso pio grhgora ekteleitai to programma