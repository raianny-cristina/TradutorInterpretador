// Main.java

public class Main {
  public static void main(String[] args) throws Exception {

    // Teste FINAL DA ETAPA 7: Múltiplos Comandos e PRINT
    String input = """
let a = 42 + 5 - 8;
let b = 56 + 8;
print a + b + 6;
"""; // Correção da sintaxe do Text Block
      
    try {
        Parser p = new Parser(input.getBytes()); 
        System.out.print("Entrada Infixa: \n" + input + "\n-> Saída Pós-Fixada: \n");
        p.parse();
        System.out.println("\n*** Etapa 7 CONCLUÍDA com sucesso! ***");
    } catch (Error e) {
        System.out.println("\n*** FALHA! Algo deu errado: " + e.getMessage());
    }
  }
}