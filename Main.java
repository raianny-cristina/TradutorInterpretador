public class Main {
    public static void main(String[] args) throws Exception {
        // !!! ENTRADA DE TESTE DA ETAPA 4/5 !!!
        String input = "45+89-876"; 
        
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