public class Token {

    final TokenType type;
    final String lexeme;

    public Token (TokenType type, String lexeme) {
        this.type = type;
        this.lexeme = lexeme;
    }

    @Override
    public String toString() {
        // Formato de saída exigido pelo teste do Scanner
        return "<"+ type +">" + lexeme + "</"+ type + ">";
    }
}