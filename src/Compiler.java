import java.util.ArrayList;

public class Compiler {

    private  String textInput = "1.2 \n +new \n String(22 + 523 - 652 / 241@) * 266\n" +
            " 25 562 + 555 -655 /55 / / \t";

    private char[] text;
    private int next = 0;
    VerifyCharacter verify = new VerifyCharacter();
    Tokens tokens = new Tokens();
    private String aux;
    private int row=1;
    private int column =0;


    public Tokens getTokens(){

        text = textInput.toCharArray();

        int state = 0;
        char character;
        boolean invalidCharacter = false;

        aux = "";

        while (true){
            if (next==text.length) return null;

            character = text[next];
            next++;
            column++;

            if (character == '\n'){
                row++;
                column = 0;
            }

            switch (state){
                case 0:{
                    if(verify.isSpace(character))state = 0;

                     else if (verify.isNumber(character)) {
                        state = 1;
                        if (!invalidCharacter){
                            tokens.setColumn(column);
                        }
                           joinWord(character);
                    } else if (verify.isMathOperator(character)) {
                        state = 2;
                        if (!invalidCharacter){
                            tokens.setColumn(column);
                        }
                            joinWord(character);
                    } else if (verify.isSymbol(character)) {
                        state = 3;
                        if (!invalidCharacter){
                            tokens.setColumn(column);
                        }
                            joinWord(character);}

                        else {
                            state = 0;
                            tokens.setColumn(column);
                            joinWord(character);
                            invalidCharacter = true;
                        }
                        break;

                }
                case 1:{
                    if (verify.isNumber(character)){
                        state = 1;
                        joinWord(character);
                    } else if (verify.isInvalidCharacter(character)) {
                        state = 1;
                        joinWord(character);
                        invalidCharacter = true;
                    }else {
                        if (invalidCharacter) {
                            back();
                            setTokens(TokenName.INVALID_CHARACTER, aux, row);
                            return tokens;
                        }else {
                            back();
                            setTokens(TokenName.NUMBER, aux, row);
                            return tokens;
                        }
                    }
                    break;
                }
                case 2:{
                    if (verify.isMathOperator(character)){
                        joinWord(character);
                    } else if (verify.isInvalidCharacter(character)) {
                        state = 2;
                        joinWord(character);
                        invalidCharacter = true;
                    }else {
                        if (invalidCharacter){
                            back();
                            setTokens(TokenName.INVALID_CHARACTER, aux, row);
                            return tokens;
                        }else {
                            back();
                            setTokens(TokenName.MATH_OPERATOR, aux, row);
                            return tokens;
                        }
                    }
                    break;
                }
                case 3:{
                    if (verify.isSymbol(character)){
                        state = 3;
                        joinWord(character);
                    } else if (verify.isSymbol(character)) {
                        state = 3;
                        joinWord(character);
                        invalidCharacter = true;
                    }else {
                        if (invalidCharacter){
                            back();
                            setTokens(TokenName.INVALID_CHARACTER, aux, row);
                            return tokens;
                        }else {
                            back();
                            setTokens(TokenName.SYMBOL, aux, row);
                            return tokens;
                        }
                    }
                    break;
                }
            }

        }


    }

    private void joinWord(char character) {
        aux += String.valueOf(character);
    }

    private void setTokens(TokenName invalidCharacter, String aux, int row) {
        tokens.setType(invalidCharacter);
        tokens.setText(String.valueOf(aux));
        tokens.setRow(row);
    }

    private void back(){
        next--;
        column--;
    }




}

