import java.util.Scanner;

public class Rabin_Karp {
    Scanner scanner;
    String pattern;
    String[] arrText;
    int n;

    Rabin_Karp() {
        scanner = new Scanner(System.in);
    }

    public void init() {
        System.out.println("Input pattern String: ");
        pattern = scanner.nextLine();
        System.out.println("Input number of Text in array: ");
        n = scanner.nextInt();
        arrText = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Input " + i + 1 + " Text");
            arrText[i] = scanner.nextLine();
        }
    }
}
