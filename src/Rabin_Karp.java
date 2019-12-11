import java.util.Scanner;

public class Rabin_Karp {
    Scanner scanner;
    String pattern;
    String[] arrText;
    long hashPattern;
    long[] hashText;
    int n;
    int[] result;
    long q; // hash constant
    int number_of_calculated_result;

    Rabin_Karp() {
        scanner = new Scanner(System.in);
        q = (long) (Math.pow(2, 64) - 1);
        number_of_calculated_result = 0;
    }

    public void init() {
        System.out.println("Input pattern String: ");
        pattern = scanner.nextLine();
        System.out.println("Input number of Text in array: ");
        n = scanner.nextInt();
        scanner.nextLine();
        arrText = new String[n];
        hashText = new long[n];
        result = new int[n];

        for (int i = 0; i < n; i++) {
            result[i] = -1;
            System.out.println("Input text " + (i + 1));
            arrText[i] = scanner.nextLine();
        }
    }

    public void compare(int windows_number, String text, int text_number_in_array) {
        int i;
        for (i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) != text.charAt(windows_number + i)) break;
        }
        if (i == pattern.length() && result[text_number_in_array] == -1) {
            result[text_number_in_array] = windows_number;
            number_of_calculated_result++;
        }
    }

    public void search() {

        // calculate hash number for pattern and first windows of input text
        int step = 0;
        int i;
        int j;
        for (i = pattern.length() - 1; i >= 0; i--) {
            hashPattern += ((int) pattern.charAt(i) * Math.pow(256, step)) % q;
            for (j = 0; j < n; j++) {
                hashText[j] += ((int) arrText[j].charAt(i) * Math.pow(256, step)) % q;
            }
            step += 1;
        }
        // mod final time because of modulo distribution nature
        hashPattern %= q;
        for (i = 0; i < n; i++) {
            hashText[i] %= q;
        }

        // compare the first hash number for all text to has number of pattern
        for (i = 0; i < n; i++) {
            if (hashPattern == hashText[i]) {
                compare(0, arrText[i], i);
            }
        }

        // calculate hash for consequence windows
        int windows_number = 1; // windows 0 already calculate in previous step
        while (number_of_calculated_result < n) { // loop till every text in array have result
            for (i = 0; i < n; i++) {
                if ((windows_number + pattern.length()) > arrText[i].length()) {
                    i++;
                    continue;
                }

                long minus = (long) ((int) arrText[i].charAt(windows_number - 1) * Math.pow(256, pattern.length() - 1)) % q; // minus hash of char with highest weight
                long plus = ((int) arrText[i].charAt(windows_number + pattern.length() - 1)) % q; // plus has of next char (obviously will become next lowest weight char)

                hashText[i] = ((hashText[i] - minus) * 256 + plus) % q;
                if (hashText[i] < 0) hashText[i] += q;

                if (hashPattern == hashText[i]) {
                    compare(windows_number, arrText[i], i);
                }
            }
            windows_number++; // each while loop will shift windows forward by 1
        }
    }

    void showResult() {
        for (int i = 0; i < n; i++) {
            System.out.println(result[i]);
        }
    }
}
