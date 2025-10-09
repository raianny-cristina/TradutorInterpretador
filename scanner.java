// Scanner.java: Refatorado para lidar com caracteres individuais (char)
public class Scanner {

    private byte[] input; // Array de caracteres de entrada
    private int current;  // Posição atual do caractere sendo lido

    // Construtor: Inicializa o Scanner com a entrada em bytes
	public Scanner (byte[] input) {
        this.input = input;
        this.current = 0; // Inicializamos a posição de leitura
    }

    // Método auxiliar para olhar o próximo caractere sem avançar
    private char peek () {
        if (current < input.length)
           return (char)input[current];
       // Retorna caractere nulo para indicar Fim de Arquivo (EOF)
       return '\0';
    }

    // Método auxiliar que avança o ponteiro de leitura
    private void advance()  {
        char ch = peek();
        if (ch != '\0') {
            current++;
        }
    }

    // Retorna o próximo token (por hora, um caractere)
    public char nextToken () {
        char ch = peek();

        // Verifica se é um dígito
        if (Character.isDigit(ch)) {
						advance();
            return ch;
        }

        // Verifica se é um operador
        switch (ch) {
            case '+':
            case '-':
                advance();
                return ch;
            default:
                break;
        }

        // Retorna caractere nulo se não for dígito, operador ou EOF
        return '\0';
    }
}