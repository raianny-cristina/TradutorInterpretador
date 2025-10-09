// O Token representa uma palavra (lexema) e sua categoria sintática.
public class Token {
    
    // Constantes para os tipos de token que o nosso tradutor suportará
    public static final int NUMBER = 0;
    public static final int PLUS   = 1;
    public static final int MINUS  = 2;
    public static final int EOF    = 3; // End Of File (Fim de Arquivo)
    
    // Atributos
    private int type;
    private String value;
    
    // Construtor
    public Token(int type, String value) {
        this.type = type;
        this.value = value;
    }

    // Métodos Getters
    public int getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    // Representação em String para depuração
    @Override
    public String toString() {
        String typeName;
        switch (type) {
            case NUMBER: typeName = "NUMBER"; break;
            case PLUS:   typeName = "PLUS";   break;
            case MINUS:  typeName = "MINUS";  break;
            case EOF:    typeName = "EOF";    break;
            default:     typeName = "UNKNOWN"; break;
        }
        return "<" + typeName + ", " + value + ">";
    }
}
