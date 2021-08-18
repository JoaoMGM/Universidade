public class InfixToPost {

    public String InfixToPost(String[] infix){

        Stack <Character> stack = new ArrayStack<>();
        String postfix = "";
        char c;
        String current = "";

        for (int i = 0; i < infix.length; i++){
            current = infix[i];
            c = current.charAt(0);

            if (c >= '0' && c <= '9'){
                postfix += current;
                postfix += " ";
            }

            else if (stack.isEmpty()){
                stack.push(c);
                //System.out.println("stack.push" + "(" + c + ")");
            }

            else if ((c == '*' || c == '/') && (!stack.isEmpty() || stack.top() == '+' || stack.top() == '-')){
                stack.push(c);
                //System.out.println("stack.push" + "(" + c + ")");
            }

            else if (c == stack.top()){
                postfix += stack.pop() + " ";
                stack.push(c);
                //System.out.println("stack.push" + "(" + c + ")");
            }

            else if ((c == '+' || c == '-') && (!stack.isEmpty() || stack.top() == '*' || stack.top() == '/')){
                while (!stack.isEmpty() && (stack.top() != '+' || stack.top() != '-')){
                    postfix += stack.pop() + " ";
                }
                stack.push(c);
                //System.out.println("stack.push" + "(" + c + ")");
            }
        }

        while (!stack.isEmpty()){
            postfix += stack.pop() + " ";
        }

        System.out.println(postfix);
        return postfix;
    }
}