// Parser.java: Implementa o analisador sintático com GERAÇÃO de código para o Interpretador

public class Parser {

    private Scanner scan; 
    private Token currentToken; 
    
    // NOVO: StringBuilder para armazenar o código de saída
    private StringBuilder codeOutput = new StringBuilder(); 

    public Parser(byte[] input) {
        this.scan = new Scanner(input); 
    }
    
    // NOVO: Método para imprimir o código em vez de System.out.println
    private void emit(String command) {
        codeOutput.append(command).append(System.getProperty("line.separator"));
    }

    // NOVO: Método para retornar o código gerado
    public String output() {
        return codeOutput.toString();
    }

    // Símbolo Sentencial: statements -> statement*
    public void parse() {
        nextToken(); 
        statements(); 
        
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
        emit("print"); // Geração de código
        match(TokenType.SEMICOLON);
    }
    
    // Regra: letStatement -> 'let' identifier '=' expression ';'
    void letStatement () {
        match(TokenType.LET);
        
        var id = currentToken.lexeme;
        
        match(TokenType.IDENT);
        match(TokenType.EQ);
        
        expr(); 
        
        emit("pop "+id); // Geração de código: Atribuição
        
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
            emit("push "+currentToken.lexeme);
            match(TokenType.IDENT);
        }
        else
            throw new Error("syntax error: Expected NUMBER or IDENT but found " + currentToken.type);
    }
    
    // Regra: number -> NUMBER 
    void number () {
        if (currentToken.type == TokenType.NUMBER) {
             emit("push " + currentToken.lexeme);
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
            emit("add"); 
            oper();
        } else if (currentToken.type == TokenType.MINUS) {
            match(TokenType.MINUS);
            term(); 
            emit("sub"); 
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