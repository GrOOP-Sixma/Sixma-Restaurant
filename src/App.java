import java.io.File;
import java.util.Scanner;

import Factory.RRPSS;

public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to the RRPSS!");
        new File("./tmp").mkdir(); // create tmp directory if it doesnt exist to store config files
        System.out.println("Please enter the name of the restaurant:");
        String name = new Scanner(System.in).nextLine();
        RRPSS rrpss = new RRPSS(name);
        System.out.println("RRPSS is runnning");
        System.out.println("------------------------------------------------------------");
        rrpss.run();
    }
}
