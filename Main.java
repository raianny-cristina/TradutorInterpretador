public class Main {
    public static void main(String[] args) {
        String input = "8+5-7+9"; // Expressão de teste do tutorial
        
        try {
            // Cria o Parser, passando a entrada como array de bytes
            Parser p = new Parser (input.getBytes()); 
            System.out.print("Entrada Infixa: " + input + " -> Saída Pós-Fixada: ");
            p.parse();
            System.out.println();
        } catch (Exception e) {
            System.out.println("\nAnálise falhou: " + e.getMessage());
        }

    }
}