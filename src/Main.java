import java.util.ArrayList;

public class Main {




    public static void main(String[] args) {
        Compiler cp = new Compiler();
        Tokens tokens;


        do {
            tokens = cp.getTokens();

            if (tokens != null) {
                System.out.println(tokens.toString());
            }
        } while (tokens != null);


    }
}
