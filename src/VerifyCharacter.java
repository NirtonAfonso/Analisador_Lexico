public class VerifyCharacter {

    public boolean isNumber (char x){
        return x >= '0' && x<= '9' || x=='.';
    }

    public boolean isMathOperator(char x){
        return x == '*' || x == '-' || x == '+' || x == '/' || x == '%';
    }
    public boolean isSymbol(char x){
        return x == '(' || x == '{' || x == '[' || x == ')' || x == '}' || x == ']' || x == '.' || x == ',' || x == ':';
    }
    public boolean isInvalidCharacter(char x){
        return (x >= 'a' && x <= 'z') || (x >= 'A' && x <= 'Z') || x == '$' || x == '@';
    }

    public boolean isSpace(char x){
        return x == ' ' || x == '\n' || x == '\t' || x == '\r';
    }
}
