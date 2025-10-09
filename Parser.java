public class Parser {
    
    // Atributos
    private byte[] input;
    private int current; 

    // Construtor
	public Parser (byte[] input) {
        this.input = input;
        this.current = 0; // Inicializa o ponteiro de leitura
    }

    // Método principal que inicia a análise
    public void parse () {
        expr(); // O símbolo inicial da gramática
    }

    // --- Métodos Auxiliares ---

    // Retorna o caractere corrente, ou '\0' (final de arquivo)
    private char peek () {
        if (current < input.length)
           return (char)input[current];
       return '\0';
    }
    
    // Verifica se o caractere corrente casa com o esperado e avança, ou lança erro sintático.
    private void match (char c) {
        if (c == peek()) {
            current++;
        } else {
            throw new Error("syntax error. Esperado: " + c);
        }
    }

    // --- Implementação das Regras Gramaticais ---

    // Regra: expr → digit oper
    void expr() {
        digit();
        oper();
    }

    // Regra: digit → 0 | .. | 9
    void digit () {
        if (Character.isDigit(peek())) {
            // Ação Semântica: Imprime a operação PUSH
            System.out.println("push " + peek());
            match(peek());
        } else {
           throw new Error("syntax error. Esperado um dígito.");
        }
    }

    // Regra: oper → + digit oper | - digit oper | ε
    void oper () {
        if (peek() == '+') {
            match('+');
            digit();
            System.out.println("add"); // Ação Semântica
            oper();
        } else if (peek() == '-') {
            match('-');
            digit();
            System.out.println("sub"); // Ação Semântica
            oper();
        } 
        // O caso ε (vazio) é implícito: se não for '+' nem '-', o método simplesmente retorna.
    }
}
