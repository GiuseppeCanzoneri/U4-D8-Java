package Esercizio2;

import java.util.Arrays;
import java.util.Random;



public class Main {
    public static void main(String[] args) throws InterruptedException {
        int[] values = generateRandomValues(3000);
        int[] partialSums = new int[3];
        Thread[] threads = new Thread[3];

        for (int i = 0; i < 3; i++) {
            int[] subarray = getSubarray(values, i);
            threads[i] = new Thread(new SumTask(subarray, partialSums, i));
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int totalSum = 0;
        for (int sum : partialSums) {
            totalSum += sum;
        }
        System.out.println("Total sum: " + totalSum);
    }

    private static int[] generateRandomValues(int size) {
        int[] values = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            values[i] = random.nextInt(100);
        }
        return values;
    }

    private static int[] getSubarray(int[] values, int index) {
        int start = index * 1000;
        int end = start + 1000;
        return Arrays.copyOfRange(values, start, end);
    }
}

class SumTask implements Runnable {
    private int[] values;
    private int[] partialSums;
    private int index;

    public SumTask(int[] values, int[] partialSums, int index) {
        this.values = values;
        this.partialSums = partialSums;
        this.index = index;
    }

    @Override
    public void run() {
        int sum = 0;
        for (int value : values) {
            sum += value;
        }
        partialSums[index] = sum;
    }
}


////Il programma genera un array
//di 3000 valori numerici casuali (compresi tra 0 e 99)
//con il metodo generateRandomValues.
//Successivamente, il programma crea 3 thread figli e
//assegna ad ogni thread una partizione dell'array
//(1000 valori) con il metodo getSubarray.
//Ogni thread esegue la classe SumTask 
//che implementa l'interfaccia Runnable.
//Il metodo run di questa classe calcola la somma dei propri valori e 
//memorizza il risultato in una posizione dell'array
//partialSums. Infine, il programma aspetta 
//che tutti i thread figli terminino di eseguire 
//(thread.join()) e somma i risultati parziali
//contenuti nell'array partialSums per ottenere 
//la somma totale degli elementi dell'array originale
//. Il risultato viene stampato su console.
