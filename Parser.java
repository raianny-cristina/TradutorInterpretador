public class Parser {
    
    // Novos atributos: o Parser agora TEM um Scanner
    private Scanner scan;
    private char currentToken; // Token atual (por hora, um caractere)

    // Construtor: Recebe a entrada e inicializa o Scanner (com byte[])
    public Parser(byte[] input) {
        scan = new Scanner(input);
        // Pega o primeiro token para começar a análise
        currentToken = scan.nextToken(); 
    }

    // Método auxiliar para atualizar o token corrente
    private void nextToken () {
        currentToken = scan.nextToken();
    }

    // --- Métodos de Análise Sintática (Tradução Dirigida por Sintaxe) ---
    
    // Método principal
    public void parse () {
        expr();
        // Nota: O tutorial da Etapa 3 não verifica EOF, mas o código funcionará com a entrada de teste
    }

    // Regra: expr -> digit oper
    void expr() {
        digit();
        oper();
    }
    
    // Regra: digit -> [0-9]
    void digit () {
        if (Character.isDigit(currentToken)) {
						System.out.println("push " + currentToken); // Ação Semântica
            match(currentToken);
        } else {
           throw new Error("syntax error. Esperado um dígito.");
        }
    }
    
    // Regra: oper -> + digit oper | - digit oper | ϵ
    void oper () {
        if (currentToken == '+') {
            match('+');
            digit();
            System.out.println("add"); // Ação Semântica
            oper();
        } else if (currentToken == '-') {
            match('-');
            digit();
            System.out.println("sub"); // Ação Semântica
            oper();
        }
        // O caso ϵ (vazio) é implícito
    }

    // Método 'casamento': verifica o token corrente e avança.
    private void match(char t) {
        if (currentToken == t) {
            nextToken(); // Se o token casar, avança para o próximo
        }else {
            throw new Error("syntax error. Token: " + currentToken + ", Esperado: " + t);
        }
    }
}