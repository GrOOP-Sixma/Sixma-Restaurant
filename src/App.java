import java.util.Scanner;

import Factory.RRPSS;

public class App {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Enter restaurant name");
        Scanner scanner = new Scanner(System.in);
        // String name = scanner.nextLine();
        RRPSS rrpss = new RRPSS();
        Thread t1 = new Thread(rrpss);
        t1.start();
        t1.join();
        scanner.close();
    }
}
