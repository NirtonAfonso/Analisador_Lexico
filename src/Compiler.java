/**
 * Classe principal, onde se concentra toda a lógica do analizador lexico
 * @autor Nirton Afonso
 */



import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import Enum.*;

public class Compiler {

    public Compiler(String file) {
        try {
            String textInput = new String(Files.readAllBytes(Paths.get(file)), StandardCharsets.UTF_8);
            text = textInput.toCharArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private char[] text;
    private int next = 0;
    VerifyCharacter verify = new VerifyCharacter();
    Tokens tokens = new Tokens();
    private String aux;
    private int row = 1;
    private int column = 0;


    public Tokens getTokens() {


        int state = 0;
        char character;
        boolean invalidCharacter = false;

        aux = "";

        while (true) {
            if (next == text.length) return null;

            character = text[next];
            next++;
            column++;

            if (character == '\n') {
                row++;
                column = 0;
            }

            switch (state) {
                case 0: {
                    if (verify.isSpace(character)) {
                        state = 0;
                    } else if (verify.isCharacter(character)) {
                        state = 1;
                        if (!invalidCharacter) {
                            tokens.setColumn(column);
                        }
                        joinWord(character);
                    } else if (verify.isNumber(character)) {
                        state = 4;
                        if (!invalidCharacter) {
                            tokens.setColumn(column);
                        }
                        joinWord(character);
                    } else if (verify.isMathOperator(character)) {
                        state = 12;
                        if (!invalidCharacter) {
                            tokens.setColumn(column);
                        }
                        joinWord(character);
                    } else if (verify.isSymbol(character)) {
                        state = 26;
                        if (!invalidCharacter) {
                            tokens.setColumn(column);
                        }
                        joinWord(character);
                    } else if (verify.isOperatorMost(character)) {
                        state = 11;
                        if (!invalidCharacter) {
                            tokens.setColumn(column);
                        }
                        joinWord(character);
                    } else if (verify.isOperatorLess(character)) {
                        state = 13;
                        if (!invalidCharacter) {
                            tokens.setColumn(column);
                        }
                        joinWord(character);
                    } else if (verify.isOperatorEquals(character)) {
                        state = 7;
                        if (!invalidCharacter) {
                            tokens.setColumn(column);
                        }
                        joinWord(character);
                    } else if (verify.isEndOfLine(character)) {
                        state = 25;
                        if (!invalidCharacter) {
                            tokens.setColumn(column);
                        }
                        joinWord(character);
                    } else if (verify.isDenial(character)) {
                        state = 23;
                        if (!invalidCharacter) {
                            tokens.setColumn(column);
                        }
                        joinWord(character);
                    } else if (verify.isComment(character)) {
                        state = 28;
                        if (!invalidCharacter) {
                            tokens.setColumn(column);
                        }
                        joinWord(character);
                    } else if (verify.isChar(character)) {
                        state = 29;
                        if (!invalidCharacter) {
                            tokens.setColumn(column);
                        }
                        joinWord(character);
                    } else if (verify.isText(character)) {
                        state = 30;
                        if (!invalidCharacter) {
                            tokens.setColumn(column);
                        }
                        joinWord(character);
                    } else if (verify.isLogicalOperator(character)) {
                        state = 10;
                        if (!invalidCharacter) {
                            tokens.setColumn(column);
                        }
                        joinWord(character);
                    } else if (verify.isLogicalOperatorML(character)) {
                        state = 17;
                        if (!invalidCharacter) {
                            tokens.setColumn(column);
                        }
                        joinWord(character);
                    } else {
                        state = 0;
                        tokens.setColumn(column);
                        joinWord(character);
                        invalidCharacter = true;
                    }
                    break;
                }
                case 1: {
                    if (verify.isCharacter(character) || verify.isNumber(character)) {
                        state = 1;
                        joinWord(character);
                    } else if (verify.isInvalidCharacter(character)) {
                        state = 1;
                        joinWord(character);
                        invalidCharacter = true;
                    } else {
                        if (invalidCharacter) {
                            back();
                            setTokens(TokenName.INVALID_CHARACTER, aux, row);
                            return tokens;
                        } else if (verify.isReserverWord(aux)) {
                            back();
                            setTokens(TokenName.RESERVED_WORD, aux, row);
                            return tokens;
                        } else {
                            back();
                            setTokens(TokenName.VARIABLE, aux, row);
                            return tokens;
                        }
                    }
                    break;
                }
                case 4: {
                    if (verify.isNumber(character)) {
                        state = 4;
                        joinWord(character);
                    } else if (verify.isInvalidCharacter(character)) {
                        state = 4;
                        joinWord(character);
                        invalidCharacter = true;
                    } else {
                        if (invalidCharacter) {
                            back();
                            setTokens(TokenName.INVALID_CHARACTER, aux, row);
                            return tokens;
                        } else {
                            back();
                            setTokens(TokenName.NUMBER, aux, row);
                            return tokens;
                        }
                    }
                    break;
                }
                case 7: {
                    if (verify.isOperatorEquals(character)) {
                        joinWord(character);
                        if (invalidCharacter) {
                            back();
                            setTokens(TokenName.MALFORMED_TOKEN, aux, row);
                            return tokens;
                        }
                        setTokens(TokenName.COMPARISON_OPERATOR, aux, row);
                    } else if (verify.isInvalidCharacter(character)) {
                        state = 7;
                        joinWord(character);
                        invalidCharacter = true;
                    } else {
                        if (invalidCharacter) {
                            back();
                            setTokens(TokenName.MALFORMED_TOKEN, aux, row);
                            return tokens;
                        } else {
                            back();
                            setTokens(TokenName.ASSIGNMENT_OPERATOR, aux, row);
                            return tokens;
                        }
                    }
                    break;
                }
                case 10: {
                    if (invalidCharacter) {
                        back();
                        setTokens(TokenName.MALFORMED_TOKEN, aux, row);
                        return tokens;
                    } else {
                        joinWord(character);
                        setTokens(TokenName.COMPARISON_OPERATOR, aux, row);
                        return tokens;
                    }
                }
                case 11: {
                    if (verify.isOperatorMost(character) || verify.isOperatorEquals(character)) {
                        joinWord(character);
                        setTokens(TokenName.ASSIGNMENT_OPERATOR, aux, row);
                        return tokens;
                    } else if (verify.isInvalidCharacter(character)) {
                        state = 11;
                        joinWord(character);
                        invalidCharacter = true;
                    } else {
                        if (invalidCharacter) {
                            back();
                            setTokens(TokenName.INVALID_CHARACTER, aux, row);
                            return tokens;
                        } else {
                            back();
                            setTokens(TokenName.MATH_OPERATOR, aux, row);
                            return tokens;
                        }
                    }
                    break;
                }
                case 12: {
                    if (verify.isOperatorEquals(character)) {
                        joinWord(character);
                        setTokens(TokenName.ASSIGNMENT_OPERATOR, aux, row);
                        return tokens;
                    } else if (verify.isInvalidCharacter(character)) {
                        state = 12;
                        joinWord(character);
                        invalidCharacter = true;
                    } else {
                        if (invalidCharacter) {
                            back();
                            setTokens(TokenName.INVALID_CHARACTER, aux, row);
                            return tokens;
                        } else {
                            back();
                            setTokens(TokenName.MATH_OPERATOR, aux, row);
                            return tokens;
                        }
                    }
                    break;
                }
                case 17: {
                    if ((verify.isLogicalOperatorML(character) || verify.isOperatorEquals(character))) {
                        joinWord(character);
                        setTokens(TokenName.COMPARISON_OPERATOR, aux, row);
                        return tokens;
                    } else if (verify.isInvalidCharacter(character)) {
                        state = 17;
                        joinWord(character);
                        invalidCharacter = true;
                    } else {
                        if (invalidCharacter) {
                            back();
                            setTokens(TokenName.MALFORMED_TOKEN, aux, row);
                            return tokens;
                        } else {
                            back();
                            setTokens(TokenName.COMPARISON_OPERATOR, aux, row);
                            return tokens;
                        }
                    }
                    break;
                }
                case 23: {
                    if (verify.isDenial(character) || verify.isOperatorEquals(character)) {
                        joinWord(character);
                        setTokens(TokenName.COMPARISON_OPERATOR, aux, row);
                        return tokens;
                    } else if (verify.isInvalidCharacter(character)) {
                        state = 23;
                        joinWord(character);
                        invalidCharacter = true;
                    } else {
                        if (invalidCharacter) {
                            back();
                            setTokens(TokenName.INVALID_CHARACTER, aux, row);
                            return tokens;
                        } else {
                            back();
                            setTokens(TokenName.DENIAL, aux, row);
                            return tokens;
                        }
                    }
                    break;
                }
                case 25: {
                    if (invalidCharacter) {
                        back();
                        setTokens(TokenName.MALFORMED_TOKEN, aux, row);
                        return tokens;
                    } else {
                        back();
                        setTokens(TokenName.END_OF_LINE, aux, row);
                        return tokens;
                    }
                }
                case 26: {
                    if (verify.isSymbol(character)) {
                        state = 26;
                        joinWord(character);
                    } else if (verify.isSymbol(character)) {
                        state = 26;
                        joinWord(character);
                        invalidCharacter = true;
                    } else {
                        if (invalidCharacter) {
                            back();
                            setTokens(TokenName.INVALID_CHARACTER, aux, row);
                            return tokens;
                        } else {
                            back();
                            setTokens(TokenName.SYMBOL, aux, row);
                            return tokens;
                        }
                    }
                    break;
                }
                case 28: {
                    if (character == '\r' || character == '\n') {
                        back();
                        setTokens(TokenName.COMMENT, aux, row);
                        return tokens;
                    } else if (invalidCharacter) {
                        back();
                        setTokens(TokenName.MALFORMED_TOKEN, aux, row);
                        return tokens;
                    } else {
                        state = 28;
                        joinWord(character);
                        break;
                    }
                }
                case 29: {
                    state = 31;
                    joinWord(character);
                    break;
                }
                case 30: {
                    if (verify.isText(character)) {
                        joinWord(character);
                        setTokens(TokenName.TEXT, aux, row);
                        return tokens;
                    } else {
                        state = 30;
                        joinWord(character);
                        break;
                    }
                }
                case 31: {
                    if (verify.isChar(character)) {
                        joinWord(character);
                        setTokens(TokenName.CHARACTER, aux, row);
                        return tokens;
                    } else {
                        joinWord(character);
                        setTokens(TokenName.MALFORMED_TOKEN, aux, row);
                        return tokens;
                    }
                }
            }
        }
    }

    private void joinWord(char character) {
        aux += String.valueOf(character);
    }

    private void setTokens(TokenName type, String aux, int row) {
        tokens.setType(type);
        tokens.setText(String.valueOf(aux));
        tokens.setRow(row);
    }

    private void back() {
        next--;
        column--;
    }
}

