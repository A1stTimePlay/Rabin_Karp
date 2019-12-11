import java.util.Scanner;

public class Rabin_Karp {
    Scanner scanner;
    String pattern;
    String[] arrText;
    int hashPattern;
    int[] hashText;
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
        hashText = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Input " + i + 1 + " Text");
            arrText[i] = scanner.nextLine();
        }
    }

    public void search() {
        int step = 0;
        for (int i = pattern.length() - 1; i >= 0; i--) {
            hashPattern += ((int) pattern.charAt(i) * Math.pow(256, step)) % (Math.pow(2, 64) - 1);
            for (int j = 0; j < n; j++) {
                hashText[j]= (int) 
            }
            step += 1;
        }
    }
}
