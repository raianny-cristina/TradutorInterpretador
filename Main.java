// Main.java: Ponto de entrada para o teste final da Etapa 6
public class Main {
  public static void main(String[] args) throws Exception {

    // Teste FINAL DA ETAPA 6: Atribuição com Expressão
    String input = "let a = 42 + 5;"; 
      
    try {
        Parser p = new Parser(input.getBytes()); 
        System.out.print("Entrada Infixa: " + input + " -> Saída Pós-Fixada: ");
        p.parse();
        System.out.println("\n*** Etapa 6 CONCLUÍDA com sucesso! ***");
    } catch (Error e) {
        System.out.println("\n*** FALHA! Algo deu errado: " + e.getMessage());
    }
  }
}