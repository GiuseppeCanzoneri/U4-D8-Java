package Esercizio1;

public class Main {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new PrintSymbolTask("•"));
        Thread thread2 = new Thread(new PrintSymbolTask("#"));
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class PrintSymbolTask implements Runnable {
    private String symbol;

    public PrintSymbolTask(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(symbol);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


//Il programma crea due thread figli, 
//uno per ciascun simbolo. Ogni thread esegue la
//funzione print_symbol che stampa il simbolo associato 
//per 10 volte, attendendo 1 secondo tra una stampa e l'altra.
//La funzione time.sleep(1) mette in pausa 
//l'esecuzione del thread per 1 secondo. Infine, il programma
//aspetta che entrambi i thread finiscano di eseguire 
//(thread1.join() e thread2.join()) prima di terminare.