public class Main {
    public static void main(String[] args) {
        String input = "89+508-7+99";
        Parser p = new Parser (input.getBytes());
        p.parse();
    }
        
        try {
            Parser p = new Parser(input.getBytes()); 
            System.out.print("Entrada Infixa: " + input + " -> Saída Pós-Fixada: ");
            p.parse(); 
            System.out.println();
        } catch (Exception e) {
            System.out.println("\nAnálise falhou: " + e.getMessage());
        }
    }
}