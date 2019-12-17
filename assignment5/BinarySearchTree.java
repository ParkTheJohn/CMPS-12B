public class BinarySearchTree implements BSTInterface {
    BSTNode root;
    
    protected class BSTNode {
        String item;
        BSTNode left;
        BSTNode right;
        
        BSTNode() {
            item = "";
            left= null;
            right = null;
        }
    }
    
    BinarySearchTree() {
        root = null;
    }
    
    // returns true if BST is empty, false otherwise
    public boolean isEmpty() {
        if (root == null) {
            return true;
        } else {
            return false;
        }
    }
    
    // clears BST so that it is empty
    public void makeEmpty() {
        root = null;
    }
    
    // if the string is already in BST, then does nothing. If the string is not in BST, put adds the string to the BST
    public void put(String s) {
        root = recursiveInsert(root, s);
    }
    
    // returns true if the BST contains the string, otherwise returns false
    public boolean contains(String s) {
        return recursiveSearch(root, s);
    }
    
    // removes the specificed string from the BST, and if the string  cannot be found, then deletes nothing
    public void delete(String s) {
        root = recursiveRemove(root, s);
    }
    
    // helper function for contains(), recursively searches BST
	// TODO: Fill this in and call it from contains()
	protected boolean recursiveSearch(BSTNode node, String s) {
	    if (node == null) {
	        return false;
	    } else {
	        int compareResult = s.compareTo(node.item);
            if (compareResult < 0) {
                return recursiveSearch(node.left, s);
            } else if (compareResult > 0) {
                return recursiveSearch(node.right, s);
            } else {
                return true;
            }
	    }
	}
    
	// helper function for put(), recursively inserts into BST
	// TODO: Fill this in and call it from put()
	protected BSTNode recursiveInsert(BSTNode node, String s){
	    if (node == null) {
	        BSTNode newNode = new BSTNode();
	        newNode.item = s;
	        return newNode;
	    } else {
	        int compareResult = s.compareTo(node.item);
            if (compareResult < 0) {
                node.left = recursiveInsert(node.left, s);
            } else if (compareResult > 0) {
                node.right = recursiveInsert(node.right, s);
            } else {
                return node;
            }
	        return node;
	    }
	}

	// helper function for delete(), recursively removes from BST
	// TODO: Fill this in and call it from delete()
	protected BSTNode recursiveRemove(BSTNode node, String s) {
	    if (node != null) {
	        int compareResult = s.compareTo(node.item);
	        if (compareResult < 0) {
                node.left = recursiveRemove(node.left, s);
            } else if (compareResult > 0) {
                node.right = recursiveRemove(node.right, s);
            } else {
                node = deleteNode(node);
            }
	    }
	    return node;
	}
	
	// helper function for recursiveRemove(), assigns the node to the root depending on the four cases
	// TODO: Fill this in and call it from recursiveRemove()
	protected BSTNode deleteNode(BSTNode node) {
	    //Case 1: No Children
	    if (node.left == null && node.right == null) {
	        node = null;
	    } else if (node.left != null && node.right == null) {      //Case 2: Just a left child
	        node = node.left;
	    } else if (node.left == null && node.right != null) {      //Case 3: Just a right child
	        node = node.right;
	    } else if (node.left != null && node.right != null) {      //Case 4: Two children
	        String smallest = getSmallest(node.right);
	        node.item = smallest;
	        node.right = recursiveRemove(node.right, smallest);
	    }
	    return node;
	}

	// helper function for deleteNode(), returns the smallest item in the BST
	// TODO: Fill this in and call it from deleteNode()
	protected String getSmallest(BSTNode node) {
	    String smallest = node.item;
	    while (node.left != null) {
	        smallest = node.left.item;
	        node = node.left;
	    }
	    return smallest;
	}
	
	// returns a queue with the elements in-order
	public MyQueue inOrder() {
	    MyQueue inOrderQueue = new MyQueue();
	    inOrderRecursive(root, inOrderQueue);
	    return inOrderQueue;
	}
	
	// returns a queue with the elements in pre-order
    public MyQueue preOrder() {
        MyQueue preOrderQueue = new MyQueue();
        preOrderRecursive(root, preOrderQueue);
        return preOrderQueue;
    }
    
    // returns a queue with the elements in post-order
    public MyQueue postOrder() {
        MyQueue postOrderQueue = new MyQueue();
        postOrderRecursive(root, postOrderQueue);
        return postOrderQueue;
    }

    // helper function for inOrder(), in-order traversal 
	// TODO: Fill this in and call it from inOrder()
	protected void inOrderRecursive(BSTNode node, MyQueue queue) {
	    if (node != null) {
	        inOrderRecursive(node.left, queue);
	        queue.enqueue(node.item);
	        inOrderRecursive(node.right, queue);
	    }
	}

    // helper function for preOrder(), pre-order traversal 
	// TODO: Fill this in and call it from preOrder()
	protected void preOrderRecursive(BSTNode node, MyQueue queue) {
        if (node != null) {
            queue.enqueue(node.item);
            preOrderRecursive(node.left, queue);
            preOrderRecursive(node.right, queue);
        }
	}

    // helper function for postOrder(), post-order traversal 
	// TODO: Fill this in and call it from postOrder()
	protected void postOrderRecursive(BSTNode node, MyQueue queue) {
        if (node != null) {
            postOrderRecursive(node.left, queue);
            postOrderRecursive(node.right, queue);
            queue.enqueue(node.item);
        }
	}

	// Prints out the tree structure, using indenting to show the different levels of the tree
	public void printTreeStructure() { 
		printTree(0, root);
	}

	// Recursive helper for printTreeStructure()
	protected void printTree(int depth, BSTNode node) {
		indent(depth);
		if (node != null) {
	    	System.out.println(node.item);
	    	printTree(depth + 1, node.left);
	    	printTree(depth + 1, node.right);
	 	} 
	 	else {
	  		System.out.println("null");
	  	}
	}

	// Indents with with spaces 
	protected void indent(int depth) {
		for(int i = 0; i < depth; i++)
			System.out.print("  "); // Indents two spaces for each unit of depth
	}


	// Extra Credit 

	// balances an unbalanced BST
	public void balanceBST() {
        MyQueue sorted = inOrder();
        MyQueue temp = new MyQueue();
        int counter = 0;
        while (!sorted.isEmpty()) {
            temp.enqueue(sorted.dequeue());
            counter++;
        }
        String[] array = new String[counter];
        for (int i = 0; i < array.length; i++) {
            array[i] = (String)temp.dequeue();
        }
        makeEmpty();
        root = balanceRecursive(array, 0, array.length - 1);
	}
	
	// helper function for balanceBST(), uses binary search algorithm to recursively split the BST in halves into a new tree
	// TODO: If doing the extra credit, fill this in and call it from balanceBST()
	protected BSTNode balanceRecursive(String[] array, int first, int last) {
	    BSTNode node = new BSTNode();
	    int mid = (first + last) / 2;
	    node.item = array[mid];
	    if (mid == first) {
	        node.left = null;
	    } else {
	        node.left = balanceRecursive(array, first, mid - 1);
	    }
	    if (mid == last) {   
	        node.right = null;
	    } else {
	        node.right = balanceRecursive(array, mid + 1, last);
	    }
	    return node;
	} 
}