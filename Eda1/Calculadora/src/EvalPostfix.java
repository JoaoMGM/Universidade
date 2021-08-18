import java.util.Scanner;
public class EvalPostfix {

    public static float calculator(String [] postfix){
        String current = "";
        char c;
        float  a, b, result = 00;
        Stack <Float> stack = new ArrayStack<>();

        for (int i = 0; i < postfix.length; i++){
            current = postfix[i];
            c = current.charAt(0);

            if (c >= '0' && c <= '9'){
                stack.push(Float.parseFloat(current));
                //System.out.println("stack.push" + "" + "(" + (Float.parseFloat(current)) + ")");
            }

            else{
                b = stack.pop();
                a = stack.pop();
                //System.out.println("stack.pop "+ "(" + b + ")");
                //System.out.println("stack.pop "+ "(" + a + ")");

                switch (c){
                    case '+':
                        result = a + b;
                        break;
                    case '-':
                        result = a - b;
                        break;
                    case '*':
                        result = a * b;
                        break;
                    case '/':
                        result = a / b;
                        break;
                    default:
                        break;
                }
                stack.push(result);
                //System.out.println("stack.push"+ "(" + result + ")");
            }
        }
        result = stack.pop();
        return result;
    }

    public static void main (String [] args) {
        InfixToPost infix = new InfixToPost();
        String string = "";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            string = scanner.nextLine();
            String[] infixS = string.split(" ");
            System.out.println(calculator(infix.InfixToPost(infixS).split(" ")));
        }
    }
}
