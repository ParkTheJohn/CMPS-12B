

public class MyLinkedList implements ListInterface {

    /* TODO: Write a LinkedList implementation for all the methods specified in ListInterface */ 
    public class ListNode{
        protected Object data;
        protected ListNode next;
        
        public ListNode(Object data) {
            this.data = data;
            next = null;
        }
        
        public Object getData() {
            return data;
        }
        
        public void setData(Object data) {
            this.data = data;
        }
        
        public ListNode getNext() {
            return next;
        }
        
        public void setNext(ListNode next) {
            this.next = next;
        }
    }
    
    protected ListNode start;
    protected int _size;
    
    public MyLinkedList() {
        start = null;
        _size = 0;
    }
    
    public boolean isEmpty() {
        return (_size == 0);
    }

    // Returns the size of the list (number of items in the list)
    public int size() {
        return _size;
    }

    // Adds an Object to the list at the specified index. 
    public void add(int index, Object value) {
        if (index > _size) {
            throw new ListIndexOutOfBoundsException("Error");
        }
        ListNode node = new ListNode(value);
        if (index == 0) {
            node.next = start;
            start = node;
        } else {
            ListNode prev = start;
            for (int i = 0; i<index - 1; i++ ) {
                prev = prev.next;
            }
            ListNode temp = prev.next;
            prev.next = node;
            node.next = temp;
        } 
        _size++;
    }

    // Removes an item from the list at the specified index. 
    public void remove(int index) {
        if (index >= _size) {
            throw new ListIndexOutOfBoundsException("Error");
        }
        if (index==0) {
            start = start.next;
        } else {
            ListNode prev = start;
            for (int i = 0; i<index - 1; i++ ) {
                prev = prev.next;
            }
            prev.next = prev.next.next;
        }
        _size--;
    }

    // Removes all the items from the list. 
    public void removeAll() {
        start = null;
        _size = 0;
    }

    // Returns the Object stored in the list at the specified index. 
    public Object get(int index) {
        if (index >= _size) {
            throw new ListIndexOutOfBoundsException("Error");
        }
        ListNode current = start;
        for (int i = 0; i<index; i++ ) {
            current = current.next;
        }
        return current.data;
    }

    // Returns the index at which an Object is stored in the list, -1 if it's not in the list.
    public int find(Object o) {
        int currentIndex = 0;
        int index = -1;
        ListNode current = start;
        while(current != null) {
            if (o.equals(current.data)) {
                index = currentIndex;
                break;
            }
            currentIndex++;
            current = current.next;
        }
        return index;
    }
    
    public String toString() {
        String str = "(";
        ListNode current = start;
        for (int i = 0; i<_size; i++) {
            if (i>0) {
                str += ", ";
            }
            str += current.data.toString();
            current = current.next;
        }
        str += ")";
        return str;
    }
}
