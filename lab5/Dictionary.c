#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "Dictionary.h"
#include "list.h"

typedef struct DictionaryObj {
	int tableSize;
	int size;
	List** table;

} DictionaryObj;
typedef struct DictionaryObj* Dictionary;


typedef struct EntryObj {
	char* key;
	char* value;

} EntryObj;

// allows EntryObj* to be called Entry in this file
typedef struct EntryObj* Entry;

/*
 *
 * YOUR FUNCTION IMPLEMENTATIONS GO BELOW HERE
 *
*/

// used to allocate memory for members of entry in newEntry
char* copy_string(char* string){
  char* new_string = calloc(strlen(string)+1,sizeof(char));
  strcpy(new_string,string);
  return new_string;
}

// allocates space for a new entry, initializes the new entry's variables and returns it
Entry newEntry(char* key, char* value) {
	Entry entry = malloc(sizeof(EntryObj));
	entry->key = copy_string(key);
	entry->value = copy_string(value);
	return entry;
}

// frees all data associated with the entry pointed at by pE
void freeEntry(Entry* pE){
	if (*pE != NULL) {
		if ((*pE)->key != NULL) {
			free((*pE)->key);
		}
		if ((*pE)->value != NULL) {
			free((*pE)->value);
		}
		free(*pE);
		*pE = NULL;
	}
}

// allocates space for a new dictionary, initializes the new Dictionary's variables and returns it
Dictionary newDictionary(int tableSize) {
	Dictionary dict = malloc(sizeof(DictionaryObj));
	dict->tableSize = tableSize;
	dict->size = 0;
	dict->table = calloc(tableSize, sizeof(List*));
	return dict;
}

// frees all data associated with the dictionary pointed at by pD
void freeDictionary(Dictionary* pD) {
	if (*pD != NULL) {
		makeEmpty(*pD);
		free((*pD)->table);
		free(*pD);
		*pD = NULL;
	}
}

// checks if the dictionary is empty, returns 1 for true and returns 0 for false
int isEmpty(Dictionary D) {
	if (D == NULL) {
		return 1;
	} else if (D->size == 0) {
		return 1;
	} else {
		return 0;
	}
}

// returns the number of key/value paurs in D
int size(Dictionary D) {
	if (D == NULL) {
		return 0;
	} else if (D->size == 0) {
		return 0;
	} else {
		return D->size;
	}
}

// adds a new key/value pair into the dictionary using a linked list to deal with collisions
void insert(Dictionary D, char* key, char* value) {
	int hashCode = hash(D, key);
	if (D->table[hashCode] == NULL) {
		List* bucket = make_list();
		Entry entry = newEntry(key, value);
		add(bucket, 0, entry);
		D->table[hashCode] = bucket;
		D->size++;
		return;
	} else {
		List* bucket = D->table[hashCode];
		Entry entryFound = NULL;
		for (int i = 0; i < bucket->size; i++) {
			Entry entry = (Entry)get(bucket, i);
			if (strcmp(entry->key, key) == 0) {
				entryFound = entry;
				break;
			}
		}
		if (entryFound == NULL) {
			entryFound = newEntry(key, value);
			add(bucket, 0, entryFound);
			D->table[hashCode] = bucket;
			D->size++;
			return;
		} else {
			if (entryFound->value != NULL){
				free(entryFound->value);
			}
			entryFound->value = value;
			return;
		}
	}
}

// returns the value in dictionary D associated with key
char* lookup(Dictionary D, char* key) {
	int hashCode = hash(D, key);
	if (D->table[hashCode] == NULL) {
		return NULL;
	} else {
		List* bucket = D->table[hashCode];
		Entry entryFound = NULL;
		for (int i = 0; i < bucket->size; i++) {
			Entry entry = (Entry)get(bucket, i);
			if (strcmp(entry->key, key) == 0) {
				entryFound = entry;
				break;
			}
		}
		if (entryFound == NULL) {
			return NULL;
		} else {
			return entryFound->value;
		}
	}
}

// removes the entry associated with key in D and frees any value deleted
void delete(Dictionary D, char* key) {
	int hashCode = hash(D, key);
	if (D->table[hashCode] == NULL) {
		return;
	} else {
		List* bucket = D->table[hashCode];
		for (int i = 0; i < bucket->size; i++) {
			Entry entry = (Entry)get(bucket, i);
			if (strcmp(entry->key, key) == 0) {
				Entry entryBackup = entry;
				freeEntry(&entry);
				remove_node(bucket, i);
				D->size--;
				break;
			}
		}
	}
}

// removes all entries from D and frees any value deleted
void makeEmpty(Dictionary D) {
	for (int i = 0; i < D->tableSize; i++) {
		if (D->table[i] != NULL) {
			List* bucket = D->table[i];
			if (bucket != NULL) {
				for (int j = 0; j < bucket->size; j++) {
					Entry entry = get(bucket, j);
					if (entry != NULL) {
						if (entry->key != NULL) {
							free(entry->key);
						}
						if (entry->value != NULL) {
							free(entry->value);
						}
					}
				}
				free_list(bucket);
				D->table[i] = NULL;
			}
		}
	}
	D->size = 0;
}

// prints the contents of dictionary D to a file
void printDictionary(FILE* out, Dictionary D) {
	for (int i = 0; i < D->tableSize; i++) {
		List* bucket = D->table[i];
		if (bucket != NULL) {
			for (int j = 0; j < bucket->size; j++) {
				Entry entry = get(bucket, j);
				fprintf(out, "%s %s\n", entry->key, entry->value);
			}
		}
	}
}

/*
 *
 * YOUR FUNCTION IMPLEMENTATIONS GO ABOVE HERE
 *
*/

/*
 * YOUR CODE GOES ABOVE THIS COMMENT
 * DO NOT ALTER THESE FUNCTIONS
 * THESE ARE THE THREE FUNCTIONS THAT WILL ALLOW YOU TO CONVERT 
 * A STRING INTO A VALID ARRAY INDEX
 * YOU WILL ONLY NEED TO CALL hash(Dictionary D, char* key)
*/

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
   int sizeInBits = 8*sizeof(unsigned int);
   shift = shift & (sizeInBits - 1);
   if ( shift == 0 ) {
      return value;
   }
   return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) { 
   unsigned int result = 0xBAE86554;
   while (*input) { 
      result ^= *input++;
      result = rotate_left(result, 5);
   }
   return result;
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(Dictionary D, char* key){
   return pre_hash(key) % D->tableSize;
}


