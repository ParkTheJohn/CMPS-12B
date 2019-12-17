
public class MyStack implements StackInterface {
	/* 
	* TODO 1: Implement "MyStack"
	*/
    MyLinkedList stack;
    
    public MyStack() {
        stack = new MyLinkedList();
    }

	public boolean isEmpty() {
	    if (stack.size == 0)
            return true;
        else
            return false;
	}
	
	public void push(Object o) {
	    stack.add(0, o);
	}
	
	public Object pop() {
	    if (stack.size() == 0) {
	        return null;
	    }
	    Object o = stack.get(0);
	    stack.remove(0);
	    return o;
	}

	public Object peek() {
        if (stack.size() == 0) {
            return null;
        }
	    return stack.get(0);
	}

	public void popAll() {
	    stack.removeAll();
	}
	
	public String toString() {
	    String s = stack.toString();
	    return s;
	}
	/* 
	* TODO 1: Implement "MyStack"
	*/
}