

public class MySortedLinkedList extends MyLinkedList {

    /* TODO 
       define the method
       public void add(Comparable c)
       that, given a Comparable (an interface type for all Object subclasses that define a compareTo() method), adds it to the 
       list in sorted order.
    */
    public void add(Comparable c) {
        ListNode newNode = new ListNode(c);
        ListNode node = start;
        ListNode prev = null;
        while(node != null) {
            if (c.compareTo(node.data) < 0) {
                break;
            }
            prev = node;
            node = node.next;
        }
        if (prev != null) {
            ListNode temp = prev.next;
            prev.next = newNode;            
            newNode.next = temp;
        } else {
            newNode.next = start;
            start = newNode;
        }
        _size++;
    }
        
 
    
    /* TODO
       override the method
       void add(int index, Object o)
       so that it throws an UnsupportedOperationException with the message "Do not call add(int, Object) on MySortedLinkedList".
       Directly adding objects at an index would mess up the sorted order.
    */
    public void add(int index, Object o){
        throw new UnsupportedOperationException("Error");
    }
}