/**
 * Classe responsavel por fazer as verificações de cada caracter individual
 * @autor Nirton Afonso
 */

import Enum.*;

public class VerifyCharacter {

    /**
     * Verifica se é numero
     * @param x
     * @return 0-9
     */
    public boolean isNumber(char x) {
        return x >= '0' && x <= '9';
    }

    /**
     *Verifica se é um Caracter
     * @param x
     * @return a-z || A-Z
     */
    public boolean isCharacter(char x) {
        return (x >= 'a' && x <= 'z') || (x >= 'A' && x <= 'Z');
    }

    /**
     * Verifica se é o operador +
     * @param x
     * @return +
     */
    public boolean isOperatorMost(char x) {
        return x == '+';
    }

    /**
     * Verifica se é o operdor -
     * @param x
     * @return -
     */
    public boolean isOperatorLess(char x) {
        return x == '-';
    }

    /**
     * Verifica se é o Operador =
     * @param x
     * @return
     */
    public boolean isOperatorEquals(char x) {
        return x == '=';
    }

    /**
     * Verifica se é final de linha
     * @param x
     * @return ;
     */
    public boolean isEndOfLine(char x) {
        return x == ';';
    }

    /**
     * Verifica se é um operador de negação !
     * @param x
     * @return !
     */
    public boolean isDenial(char x) {
        return x == '!';
    }

    /**
     * Verifica se é um comentário
     * @param x
     * @return null
     */
    public boolean isComment(char x) {
        return x == '#';
    }

    /**
     * Verifica se é um Char (um unico caracter)
     * @param x
     * @return '
     */
    public boolean isChar(char x) {
        return x == '\'';
    }

    /**
     * Verifica se é um texto
     * @param x
     * @return "
     */
    public boolean isText(char x) {
        return x == '"';
    }

    /**
     * Verifica se é um operador lógico AND (&&) ou OR (||)
     * @param x
     * @return & || |
     */
    public boolean isLogicalOperator(char x) {
        return x == '&' || x == '|';
    }

    /**
     * Verifica se é um operador de comparação MAIOR(>) e MENOR(<)
     * @param x
     * @return < || >
     */
    public boolean isLogicalOperatorML(char x) {
        return x == '>' || x == '<';
    }

    /**
     * Verifica se é um operador Matemático
     * @param x
     * @return '/', '*', '%', '^'
     */
    public boolean isMathOperator(char x) {
        return x == '*' || x == '/' || x == '%' || x == '^';
    }

    /**
     * Verifica se é um simbolo
     * @param x
     * @return '(', '{', '[', ')', '}', ']', '.', ':', ','
     */
    public boolean isSymbol(char x) {
        return x == '(' || x == '{' || x == '[' || x == ')' || x == '}' || x == ']' || x == '.' || x == ',' || x == ':';
    }

    /**
     * Verifica se é algum caracter invalido
     * @param x
     * @return '$', '@', '?', '~'
     */
    public boolean isInvalidCharacter(char x) {
        return x == '$' || x == '@' || x == '?' || x == '~';
    }

    /**
     * Verifica se é um Espaço/quebra de linha
     * @param x
     * @return
     */
    public boolean isSpace(char x) {
        return x == ' ' || x == '\n' || x == '\t' || x == '\r';
    }

    /**
     * Verifica se é uma palavra reservada
     * @param x
     * @return
     */
    public boolean isReserverWord(String x) {
        try {
            ReservedWord.valueOf(x.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
