import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

class FibonacciThread extends Thread {
    private int n;
    private List<Integer> fibonacciList;
    private CountDownLatch latch;

    public FibonacciThread(int n, List<Integer> fibonacciList, CountDownLatch latch) {
        this.n = n;
        this.fibonacciList = fibonacciList;
        this.latch = latch;
    }

    
    public void run() {
        int a = 0, b = 1;
        synchronized (fibonacciList) {
            fibonacciList.add(a);
            if (n > 1) fibonacciList.add(b);
            for (int i = 2; i < n; i++) {
                int next = a + b;
                fibonacciList.add(next);
                a = b;
                b = next;
            }
        }
        latch.countDown(); // Signal that Fibonacci calculation is complete
    }
}

class SumThread extends Thread {
    private List<Integer> fibonacciList;
    private List<Integer> sumList;
    private CountDownLatch latch;

    public SumThread(List<Integer> fibonacciList, List<Integer> sumList, CountDownLatch latch) {
        this.fibonacciList = fibonacciList;
        this.sumList = sumList;
        this.latch = latch;
    }

    
    public void run() {
        try {
            latch.await(); // Wait until FibonacciThread completes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int sum = 0;
        synchronized (fibonacciList) {
            for (int num : fibonacciList) {
                sum += num;
                sumList.add(sum);
            }
        }
    }
}

class PrimeThread extends Thread {
    private List<Integer> fibonacciList;
    private List<String> primeList;
    private CountDownLatch latch;

    public PrimeThread(List<Integer> fibonacciList, List<String> primeList, CountDownLatch latch) {
        this.fibonacciList = fibonacciList;
        this.primeList = primeList;
        this.latch = latch;
    }

    private boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public void run() {
        try {
            latch.await(); // Wait until FibonacciThread completes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (fibonacciList) {
            for (int num : fibonacciList) {
                if (isPrime(num)) {
                    primeList.add(num + " is Prime");
                } else {
                    primeList.add(num + " is Not Prime");
                }
            }
        }
    }
}

public class ThreadExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of Fibonacci numbers to generate: ");
        int n = scanner.nextInt();
        scanner.close();

        List<Integer> fibonacciList = new ArrayList<>();
        List<Integer> sumList = new ArrayList<>();
        List<String> primeList = new ArrayList<>();

        // Latch to make SumThread and PrimeThread wait until FibonacciThread is done
        CountDownLatch latch = new CountDownLatch(1);

        FibonacciThread fibonacciThread = new FibonacciThread(n, fibonacciList, latch);
        SumThread sumThread = new SumThread(fibonacciList, sumList, latch);
        PrimeThread primeThread = new PrimeThread(fibonacciList, primeList, latch);

        fibonacciThread.start();
        sumThread.start();
        primeThread.start();

        try {
            fibonacciThread.join();
            sumThread.join();
            primeThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Fibonacci Series: " + fibonacciList);
        System.out.println("Current Sum of Fibonacci Series: " + sumList);
        System.out.println("Prime Check for Fibonacci Numbers: " + primeList);
    }
}
