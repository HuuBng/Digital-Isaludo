package LootTheLoop;

import java.util.*;

public class Menu {

    public int int_getChoice(ArrayList<String> Opts) {

        int N = Opts.size();

        for (int i = 0; i < N; i++) {
            System.out.println((i + 1) + ". " + Opts.get(i));
        }
        System.out.print("Please choose an option: 1..." + N + ": ");

        int response = Integer.parseInt(new Scanner(System.in).nextLine().trim());
        return response;
    }
}
