// Scanner.java: Implementa o analisador léxico que retorna objetos Token
public class Scanner {

    // Ajustado para String, o que torna a leitura e a função number() mais seguras.
    private String input; 
    private int current;

    // Construtor: Recebe String
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
    // Scanner.java
// ...
private void skipWhitespace() {
    char ch = peek();
    // Pula espaço, retorno de carro, tabulação e nova linha
    while (ch == ' ' || ch == '\r' || ch == '\t' || ch == '\n') { 
        advance();
        ch = peek();
    }
}
// ...

    // Lógica para agrupar múltiplos dígitos no Token.NUMBER
    private Token number() {
        int start = current;
        while (Character.isDigit(peek())) {
            advance();
        }
        
        // Usa substring(), que é a forma mais robusta em Java para strings
        String n = input.substring(start, current); 
        return new Token(TokenType.NUMBER, n);
    }

    public Token nextToken () {

        skipWhitespace();
        
        char ch = peek();

        // 1. Números
        if (ch == '0') {
            advance();
            // Retorna o token para o zero
            return new Token(TokenType.NUMBER, Character.toString(ch));
        } else if (Character.isDigit(ch)) {
            // Chama a função number() para agrupar outros dígitos
            return number();
        }

        // 2. Operadores e EOF
        switch (ch) {
            case '+':
                advance();
                return new Token(TokenType.PLUS, "+");
            case '-':
                advance();
                return new Token(TokenType.MINUS, "-");
            case '\0':
                return new Token(TokenType.EOF, "EOF");
            default:
                throw new Error("lexical error at " + ch);
        }
    }
    
    // Main de teste do Scanner exigido pelo tutorial
    public static void main(String[] args) {
        // A ENTRADA É STRING e não byte[]
        String input = "289-85+0+69";
        Scanner scan = new Scanner(input); 
        System.out.println(scan.nextToken());
        System.out.println(scan.nextToken());
        System.out.println(scan.nextToken());
        System.out.println(scan.nextToken());
        System.out.println(scan.nextToken());
        System.out.println(scan.nextToken());
        System.out.println(scan.nextToken());
    }
}