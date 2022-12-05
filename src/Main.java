/**
 * @autor Nirton Afonso
 */

import java.util.ArrayList;
import Enum.*;

public class Main {




    public static void main(String[] args) {
        Compiler cp = new Compiler("src/Text_Input.txt");
        Tokens tokens = null;


        do {
            tokens = cp.getTokens();

            if (tokens != null) {
                System.out.println(tokens.toString());
            }
        } while (tokens != null);


    }
}
