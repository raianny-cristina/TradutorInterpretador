// Main.java

public class Main {
    public static void main(String[] args) {
        
        // Entrada de teste da Etapa 8
        String input = """
let a = 42 + 2;
let b = 15 + 3;
print a + b;
""";
        
        System.out.println("--- 1. ANÁLISE E GERAÇÃO DE CÓDIGO (Parser) ---");
        Parser p = new Parser (input.getBytes());
        p.parse();
        
        String generatedCode = p.output();
        System.out.println("\nCódigo Pós-Fixado Gerado:\n" + generatedCode);

        System.out.println("--- 2. EXECUÇÃO DO INTERPRETADOR ---");
        Interpretador i = new Interpretador (generatedCode);
        i.run();
        
        System.out.println("\n*** Etapa 8 CONCLUÍDA com sucesso! ***");
    }
}