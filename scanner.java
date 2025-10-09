public class Scanner {

    private String input;
    private int current;

    // Construtor: recebe a expressão de entrada
    public Scanner(String input) {
        this.input = input;
        this.current = 0;
    }

    // Retorna o próximo token, sob demanda do Parser
    public Token nextToken() {
        // Ignora espaços em branco e tabulações (microssintaxe)
        while (current < input.length() && Character.isWhitespace(input.charAt(current))) {
            current++;
        }

        // Fim de Arquivo (EOF)
        if (current >= input.length()) {
            return new Token(Token.EOF, "");
        }

        char c = input.charAt(current);

        // --- 1. Operadores (+, -) ---
        if (c == '+') {
            current++;
            return new Token(Token.PLUS, "+");
        }
        if (c == '-') {
            current++;
            return new Token(Token.MINUS, "-");
        }

        // --- 2. Números de Múltiplos Dígitos (number -> [0-9]+) ---
        if (Character.isDigit(c)) {
            StringBuilder value = new StringBuilder();
            
            // Loop para ler todos os dígitos consecutivos
            while (current < input.length() && Character.isDigit(input.charAt(current))) {
                value.append(input.charAt(current));
                current++;
            }
            // Retorna um token do tipo NUMBER com seu valor (ex: "45")
            return new Token(Token.NUMBER, value.toString());
        }

        // Se encontrou um caractere desconhecido (erro léxico)
        throw new Error("Erro Léxico: Caractere não reconhecido: " + c);
    }
}
