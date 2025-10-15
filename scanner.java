// Scanner.java: Implementa o analisador léxico que retorna objetos Token
import java.util.HashMap;
import java.util.Map;
import java.nio.charset.StandardCharsets;

public class Scanner {

    // Mapa de palavras-chave estático para a Etapa 6
    private static final Map<String, TokenType> keywords; 
    
    static {
        keywords = new HashMap<>();
        keywords.put("let",    TokenType.LET);
    }

    private String input; 
    private int current;

    // Construtor 1: Recebe byte[] (para ser chamado pelo Parser)
    public Scanner(byte[] input) {
        this.input = new String(input, StandardCharsets.UTF_8); 
        this.current = 0;
    }

    // Construtor 2: Recebe String (para teste isolado)
    public Scanner(String input) {
        this.input = input;
        this.current = 0;
    }

    private char peek() {
        if (current < input.length())
            return input.charAt(current);
        return '\0';
    }

    private void advance() {
        if (current < input.length()) {
            current++;
        }
    }

    // --- Funções Auxiliares para ID ---
    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') ||
               (c >= 'A' && c <= 'Z') ||
                c == '_';
    }
    
    private boolean isAlphaNumeric(char c) {
        return isAlpha(c) || Character.isDigit((c));
    }

    // --- Tratamento de Espaços (Etapa 5) ---
    private void skipWhitespace() {
        char ch = peek();
        while (ch == ' ' || ch == '\r' || ch == '\t' || ch == '\n') { 
            advance();
            ch = peek();
        }
    }

    private Token number() {
        int start = current;
        while (Character.isDigit(peek())) {
            advance();
        }
        String n = input.substring(start, current); 
        return new Token(TokenType.NUMBER, n);
    }
    
    // Identificador - Verifica se é palavra-chave ('let') ou ID comum
    private Token identifier() {
        int start = current;
        while (isAlphaNumeric(peek())) advance();
        
        String id = input.substring(start, current);

        // Verifica o mapa de keywords
        TokenType type = keywords.get(id);
        if (type == null) {
            type = TokenType.IDENT;
        }

        return new Token(type, id);
    }

    // --- nextToken() - Lógica Principal ---
    public Token nextToken () {
        
        skipWhitespace();
        
        char ch = peek();
        
        if (isAlpha(ch)) { 
           return identifier();
        }
        
        else if (ch == '0') {
            advance();
            return new Token(TokenType.NUMBER, Character.toString(ch));
        } else if (Character.isDigit(ch)) {
            return number();
        }
        
        // Operadores, Atribuição e EOF
        switch (ch) {
            case '+':
                advance();
                return new Token(TokenType.PLUS, "+");
            case '-':
                advance();
                return new Token(TokenType.MINUS, "-");
            case '=':
                advance();
                return new Token (TokenType.EQ,"=");
            case ';':
                advance();
                return new Token (TokenType.SEMICOLON,";");
            case '\0':
                return new Token(TokenType.EOF, "EOF");
            default:
                throw new Error("lexical error at " + ch);
        }
    }
    
    // Remova o Main de teste para evitar confusão se ele estiver aqui!
    // Se quiser testar o Scanner isoladamente, use 'java Scanner'.
}
    /*
    public static void main(String[] args) {
        String input = "let preco = 45 + 5 - 2;";
        Scanner scanner = new Scanner(input);
        Token token;
        do {
            token = scanner.nextToken();
            System.out.println(token);
        } while (token.type != TokenType.EOF);
    }
    */