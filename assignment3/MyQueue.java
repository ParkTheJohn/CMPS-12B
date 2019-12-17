public class MyQueue implements QueueInterface {
	/* 
	* TODO 2: Implement "MyQueue"
	*/
    
    MyLinkedList queue;
    
    public MyQueue() {
        queue = new MyLinkedList();
    }
    
    public boolean isEmpty() {
        if (queue.size == 0)
            return true;
        else
            return false;
        
    }

    public void enqueue(Object item) {
        queue.add(0, item);
    }

    public Object dequeue() {
        int size = queue.size();
        if (size == 0) {
            return null;
        }
        Object item = queue.get(size - 1);
        queue.remove(size - 1);
        return item;
    }

    public void dequeueAll() {
        queue.removeAll();
    }

    public Object peek() {
        if (queue.size() == 0) {
            return null;
        }
        return queue.get(0);
        
    }
    
    public String toString() {
        String s = queue.toString();
        return s;
    }
	/* 
	* TODO 2: Implement "MyQueue"
	*/

} 