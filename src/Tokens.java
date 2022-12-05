/**
 * Classe responsavel por ferar os tokens
 * @autor Nirton Afonso
 */


import Enum.*;

public class Tokens {

    private TokenName type;
    private String text;
    private int row;
    private int column;



    public Tokens(TokenName type, String text) {
        this.type = type;
        this.text = text;
    }

    public Tokens(TokenName type, String text, int row, int column) {
        this.type = type;
        this.text = text;
        this.row = row;
        this.column = column;
    }

    public Tokens() {

    }

    public TokenName getType() {
        return type;
    }

    public void setType(TokenName type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "{Text='" + getText() + "'" +
                ", Token=" + getType() + '\'' +
                ", row=" + getRow() +
                ", column=" + getColumn() +
                '}';
    }

}

