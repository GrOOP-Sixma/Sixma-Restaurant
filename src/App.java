import java.io.File;
import java.util.Scanner;

import Factory.RRPSS;

public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to the RRPSS");
        System.out.println("Please enter the name of the restaurant");
        new File("./tmp").mkdir();
        String name = new Scanner(System.in).nextLine();
        RRPSS rrpss = new RRPSS(name);
        rrpss.run();
    }
}
