// Interpretador.java
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

class Command {

    public enum Type {
        ADD, 
        SUB,
        PUSH,
        POP,
        PRINT
        
        ;
    }

    public Command.Type type;
    public String arg = "" ;

    public Command (String[] command) {

        type = Command.Type.valueOf(command[0].toUpperCase());
        if (command.length > 1) {
            arg = command[1];
        }
   
    }

    public String toString () {
        return type.name() + " " + arg;
    }
    
}

public class Interpretador {

    List<String[]> commands;
    Stack<Integer> stack = new Stack<>();
    Map<String,Integer> variables = new HashMap<>();

    public Interpretador (String input) {
        final String eol = System.getProperty("line.separator");
        var output = input.split(eol);
        commands = Arrays.stream(output)
        .map(String::strip)
        .filter(  (s) ->  s.indexOf("//") != 0 && s != "" && !s.isEmpty() ) // Adicionado !s.isEmpty() para segurança
        .map ( (s) ->s.split(" ")  )
        .collect(Collectors.toList());
    }

    public boolean hasMoreCommands () {
        return !commands.isEmpty();
    }

    public Command nextCommand () {
        if (commands.isEmpty()) {
            throw new IllegalStateException("No more commands to process.");
        }
        return new Command(commands.remove(0));
    }

    public void run () {
        while (hasMoreCommands()) {
            var command = nextCommand();
            
            // Console de depuração para ver o que o interpretador está fazendo
            System.out.println("-> Executando: " + command.toString()); 
            
            switch (command.type) {
                case ADD:
                    var arg2 = stack.pop();
                    var arg1 = stack.pop();
                    stack.push (arg1+arg2);
                    break;
                case SUB:
                    arg2 = stack.pop();
                    arg1 = stack.pop();
                    stack.push (arg1-arg2);
                    break;
                case PUSH:
                    var value = variables.get(command.arg);
                    if (value != null) {
                        stack.push(value);
                    } else {
                        // Trata Inteiros
                        stack.push (Integer.parseInt(command.arg));     
                    }
                    break;
                case POP:
                    value = stack.pop();
                    variables.put(command.arg, value);     
                    break;
                case PRINT:
                    var arg = stack.pop();
                    System.out.println("Resultado da Expressão: " + arg);
                    break;
                
                default:
                    throw new Error("Comando desconhecido: " + command.type);
            }
        }
    }
}