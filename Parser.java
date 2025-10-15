// Parser.java: Implementa o analisador sintático com tradução para código pós-fixado

public class Parser {

    private Scanner scan; 
    private Token currentToken; 

    public Parser(byte[] input) {
        this.scan = new Scanner(input); 
    }

    // Símbolo Sentencial: statements -> statement*
    public void parse() {
        nextToken(); 
        statements(); 
        
        // Verifica se chegamos ao fim do arquivo
        if (currentToken.type != TokenType.EOF) {
             throw new Error("Sintaxe inválida: caracteres extras no final da expressão.");
        }
    }
    
    // Regra: statements -> statement*
    void statements () {
        while (currentToken.type != TokenType.EOF) {
            statement();
        }
    }

    // Regra: statement -> printStatement | letStatement
    void statement () {
        if (currentToken.type == TokenType.PRINT) {
            printStatement();
        } else if (currentToken.type == TokenType.LET) {
            letStatement();
        } else {
            throw new Error("syntax error: Expected LET or PRINT but found " + currentToken.type);
        }
    }
    
    // Regra: printStatement -> 'print' expression ';'
    void printStatement () {
        match(TokenType.PRINT);
        expr();
        System.out.println("print"); // Geração de código
        match(TokenType.SEMICOLON);
    }
    
    // Regra: letStatement -> 'let' identifier '=' expression ';'
    void letStatement () {
        match(TokenType.LET);
        
        var id = currentToken.lexeme;
        
        match(TokenType.IDENT);
        match(TokenType.EQ);
        
        expr(); 
        
        System.out.println("pop "+id); // Geração de código: Atribuição
        
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
    
    // --- Funções Auxiliares ---
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
}