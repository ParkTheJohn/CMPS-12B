class MyHashtable implements DictionaryInterface {

	// Returns the size of the biggest bucket (most collisions) in the hashtable. 
	public int biggestBucket() {
		int biggestBucket = 0; 
		for(int i = 0; i < table.length; i++) {
			// Loop through the table looking for non-null locations. 
			if (table[i] != null) {
				// If you find a non-null location, compare the bucket size against the largest
				// bucket size found so far. If the current bucket size is bigger, set biggestBucket
				// to this new size. 
				MyLinkedList bucket = table[i];
				if (biggestBucket < bucket.size())
					biggestBucket = bucket.size();
			}
		}
		return biggestBucket; // Return the size of the biggest bucket found. 
	}

	// Returns the average bucket length. Gives a sense of how many collisions are happening overall.
	public float averageBucket() {
		float bucketCount = 0; // Number of buckets (non-null table locations)
		float bucketSizeSum = 0; // Sum of the size of all buckets
		for(int i = 0; i < table.length; i++) {
			// Loop through the table 
			if (table[i] != null) {
				// For a non-null location, increment the bucketCount and add to the bucketSizeSum
				MyLinkedList bucket = table[i];
				bucketSizeSum += bucket.size();
				bucketCount++;
			}
		}

		// Divide bucketSizeSum by the number of buckets to get an average bucket length. 
		return bucketSizeSum/bucketCount; 
	}

	public String toString() {
		String s = "";
		for(int tableIndex = 0; tableIndex < tableSize; tableIndex++) {
			if (table[tableIndex] != null) {
				MyLinkedList bucket = table[tableIndex];
				for(int listIndex = 0; listIndex < bucket.size(); listIndex++) {
					Entry e = (Entry)bucket.get(listIndex);
					s = s + "key: " + e.key + ", value: " + e.value + "\n";
				}
			}
		}
		return s; 
	}
	
	protected class Entry{
	    public String key;
	    public Object value;
	    
	    Entry() {
	        key = "";
	        value = null;
	    }
	}
	
	protected int tableSize;
	protected int size;
	protected MyLinkedList[] table;
	
	// returns true if the hashtable is empty, false otherwise
	public boolean isEmpty() {
	    if (this.size == 0) {
	        return true;
	    } else {
	        return false;
	    }
	    
	}
	
	// returns the size of the hashtable
	public int size() {
	    return this.size;
	}
	
	// adds a new key/value pair to the hashtable If the key has been previously
	//added, it replaces the value stored with this key with the new value, and returns
	//the old value. Otherwise it returns null.
	public Object put(String key, Object value) {
	    int hashCode = key.hashCode();
	    int arrayIndex = Math.abs(hashCode) % tableSize;
	    if (table[arrayIndex] == null) {
	        MyLinkedList bucket = new MyLinkedList();
	        Entry entry = new Entry();
	        entry.key = key;
	        entry.value = value;
	        bucket.add(bucket.size(), entry);
	        table[arrayIndex] = bucket;
	        size++;
	        return null;
	    } else {
	        MyLinkedList bucket = table[arrayIndex];
	        Entry entryFound = null;
	        for (int i = 0; i < bucket.size(); i++) {
                Entry entry = (Entry)bucket.get(i);
                if (entry.key.equals(key)) {
                    entryFound = entry;
                    break;
                }
            }
	        if (entryFound == null) {
	            entryFound = new Entry();
	            entryFound.key = key;
	            entryFound.value = value;
	            bucket.add(bucket.size(), entryFound);
	            size++;
	            return null;
	        } else {
	            Object old = entryFound.value;
	            entryFound.value = value;
	            return old;
	        }
	    }
	    


	}
	
	// Returns the value stored with the key. If the key has not previously been stored in
	//the hashtable, returns null.
	public Object get(String key) {
	    int hashCode = key.hashCode();
	    int arrayIndex = Math.abs(hashCode) % tableSize;
	    MyLinkedList bucket = table[arrayIndex];
	    if (bucket == null) {
	        return null;
	    } else {
	        for (int i = 0; i < bucket.size(); i++) {
	            Entry entry = (Entry)bucket.get(i);
	            if (entry.key.equals(key)) {
	                return entry.value;
	            }
	        }
	        return null;
	    }
	}
	
	// Removes the key/value pair associated with the key from the hashtable
	public void remove(String key) {
        int hashCode = key.hashCode();
        int arrayIndex = Math.abs(hashCode) % tableSize;
        MyLinkedList bucket = table[arrayIndex];
        if (bucket == null) {
            return;
        } else {
            for (int i = 0; i < bucket.size(); i++) {
                Entry entry = (Entry)bucket.get(i);
                if (entry.key.equals(key)) {
                    bucket.remove(i);
                    size--;
                }
            }
        }
	    
	}
	
	// Empties the hashtable
	public void clear() {
        this.table = new MyLinkedList[0];
        this.size = 0;
	}
	
	// Returns an array of all the keys stored in the table. 
	public String[] getKeys() {
	    String[] keys = new String[size];
	    int index = 0;
	    for (int i = 0; i < table.length; i++) {
	        MyLinkedList bucket = table[i];
	        if (bucket != null) {
                for (int j = 0; j < bucket.size(); j++) {
                    Entry entry = (Entry)bucket.get(j);
                    keys[index] = entry.key;
                    index++;
                }
	        }
	    }
	    return keys;
	    
	}
	
	// constructor for MyHashtable
	public MyHashtable(int tableSize) {
	    this.tableSize = tableSize;
	    this.size = 0;
	    this.table = new MyLinkedList[tableSize];
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}