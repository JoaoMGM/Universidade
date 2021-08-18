public class ArrayStack<E> implements Stack<E> {
    private int top, Max = 420;
    E[] array;

    public ArrayStack() {
        array = (E[]) new Object[Max];
        top = -1;
    }

    public void push(E o)  {
       top++;
       array[top] = o;
       return;
    }

    public E top() {
        if(!this.isEmpty()){
        E o = array[top];
        return o;
        }
        else return null;
    }

    public E pop() {
        E o = array[top];
        top--;
        return o;
    }

    public int size() {
        return top;
    }


    public boolean isEmpty() {
        if(top == -1){
            return true;
        }
        else return false;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for(int i = 0; i < top ;i++) {
            sb.append(array[i].toString());
            if(i < top-1){
                sb.append(",");
            }
        }
        sb.append(']');
        return sb.toString();
    }
}