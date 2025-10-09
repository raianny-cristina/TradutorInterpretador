public class Parser {

    private Scanner scan;            
    private Token currentToken;      // Agora usa o objeto Token

    public Parser(byte[] input) {
        this.scan = new Scanner(input);
    }

    public void parse() {
        nextToken(); 
        expr();
        
        // Verifica se chegamos ao fim da expressão (EOF)
        if (currentToken.type != TokenType.EOF) {
            throw new Error("Sintaxe inválida: caracteres extras no final da expressão.");
        }
    }
    
    private void nextToken() {
        currentToken = scan.nextToken();
    }

    // Agora 'casa' com o TokenType, não com 'char'
    private void match(TokenType expectedType) {
        if (currentToken.type == expectedType) {
            nextToken();
        } else {
            throw new Error("Erro de Sintaxe. Esperado token de tipo: " + expectedType + 
                            ", mas encontrou " + currentToken.type + 
                            " (" + currentToken.lexeme + ")");
        }
    }

    // --- Regras Gramaticais ---

    // Regra: expr → number oper
    void expr() {
        number(); 
        oper();
    }

    // Regra: number → NUMBER 
    void number() {
        if (currentToken.type == TokenType.NUMBER) {
            // Imprime o lexema (o valor completo do número)
            System.out.println("push " + currentToken.lexeme);
            match(TokenType.NUMBER); 
        } else {
           throw new Error("Erro de Sintaxe. Esperado um número.");
        }
    }

    // Regra: oper → + number oper | - number oper | ϵ
    void oper() {
        if (currentToken.type == TokenType.PLUS) {
            match(TokenType.PLUS);
            number();
            System.out.println("add");
            oper();
        } else if (currentToken.type == TokenType.MINUS) {
            match(TokenType.MINUS);
            number();
            System.out.println("sub");
            oper();
        } 
    }
}