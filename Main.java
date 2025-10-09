public class Main {
    public static void main(String[] args) throws Exception {
        String input = "8+5-7+9"; // Expressão de teste do tutorial
        Parser p = new Parser (input.getBytes());
        p.parse();
    }
}

