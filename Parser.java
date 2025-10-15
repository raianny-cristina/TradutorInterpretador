// Parser.java (Estado da Etapa 5, pronto para a Etapa 6)
// Parser.java: Implementa o analisador sintático com tradução para código pós-fixado

public class Parser {

    private Scanner scan; 
    private Token currentToken; 

    public Parser(byte[] input) {
        this.scan = new Scanner(input); 
    }

    // Método principal: Agora começa com a regra de comando (letStatement)
    public void parse() {
        nextToken(); 
        letStatement(); 
        
        if (currentToken.type != TokenType.EOF) {
            throw new Error("Sintaxe inválida: caracteres extras no final da expressão.");
        }
    }
    
    private void nextToken() {
        currentToken = scan.nextToken();
    }

    private void match(TokenType t) {
        if (currentToken.type == t) {
            nextToken();
        }else {
            throw new Error("syntax error: Expected " + t + " but found " + currentToken.type);
        }
    }
    
    // Regra: letStatement -> 'let' identifier '=' expression ';'
    void letStatement () {
        match(TokenType.LET);
        
        var id = currentToken.lexeme;
        
        match(TokenType.IDENT);
        match(TokenType.EQ);
        
        expr(); 
        
        System.out.println("pop "+id); 
        
        match(TokenType.SEMICOLON);
    }
    
    // Regra: expr -> term oper 
    void expr() {
        term ();
        oper();
    }

    // Regra: term -> number | identifier 
    void term () {
        if (currentToken.type == TokenType.NUMBER)
            number();
        else if (currentToken.type == TokenType.IDENT) {
            System.out.println("push "+currentToken.lexeme);
            match(TokenType.IDENT);
        }
        else
            throw new Error("syntax error: Expected NUMBER or IDENT but found " + currentToken.type);
    }
    
    // Regra: number -> NUMBER 
    void number () {
        if (currentToken.type == TokenType.NUMBER) {
             System.out.println("push " + currentToken.lexeme);
             match(TokenType.NUMBER);
        } else {
             throw new Error("syntax error: Expected NUMBER");
        }
    }

    // Regra: oper -> + term oper | - term oper | ϵ
    void oper () {
        if (currentToken.type == TokenType.PLUS) {
            match(TokenType.PLUS);
            term(); 
            System.out.println("add");
            oper();
        } else if (currentToken.type == TokenType.MINUS) {
            match(TokenType.MINUS);
            term(); 
            System.out.println("sub");
            oper();
        } 
    }
}