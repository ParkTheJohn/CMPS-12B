#include <stdlib.h>
#include "list.h"
#include <stdio.h>
#include <assert.h>

// Function that takes in a void* data and Node* next, allocates space for Node,
// sets members of the new Node* and returns a pointer to new Node
Node* make_node(void* data, Node* next){
	/* 
	* TODO 2
	*/ 
	Node* node = malloc(sizeof(Node));
	if (node == NULL) assert("Node allocation failed");

	node->data = data;
	node->next = next;
	
	return node; // replace this
	/* 
	* TODO 2
	*/ 
}

// Function that allocates space for List, sets members of new List and returns the pointer to new List
List* make_list(){
	/* 
	* TODO 2
	*/ 
	List* list = malloc(sizeof(List));
	if (list == NULL) assert("List allocation failed");
	
	list->size = 0;
	list->head = NULL;

	return list; // replace this
	/* 
	* TODO 2
	*/ 
}

// Destructor method for Node, frees the data contained by the Node and itself
void free_node(Node* node){
	
	/* 
	* TODO 2
	*/ 
	if (node != NULL){
		if (node->data != NULL){
			free(node->data);
		}
		free(node);
	}
	/* 
	* TODO 2
	*/ 
}

// Destructor method for List, iterates through Node* in the list and frees it, and frees itself
void free_list(List* list) {
	
	/* 
	* TODO 2
	*/ 
	if (list != NULL){
		Node* node = list->head;
		while (node != NULL){
			Node* next = node->next;
			free_node(node);
			node = next;
		}
		free(list);
	}
	/* 
	* TODO 2
	*/ 
}

// Adds data to the List on the index
void add(List* list, int index, void* data) {
	
	/* 
	* TODO 2
	*/
	Node* prev;
	Node* node;

	if (list == NULL) assert ("List is NULL");
	if (list->size < index) assert("Index was out of bounds" );

	node = make_node(index, NULL);
	node->data = data;
	
	if (index == 0){
		node->next = list->head;
		list->head = node;
	} else{
		Node* temp;
		prev = list->head;
		for (int i = 0; i < index - 1; i++){
			prev = prev->next;
		}
		temp = prev->next;
		prev->next = node;
		node->next = temp;

	}
	list->size++;
	/* 
	* TODO 2
	*/ 
}

// Returns the data of the Node at the index
void* get(List* list, int index){
	/* 
	* TODO 2
	*/ 
	Node* current = list->head;

	if (list == NULL) assert ("List is NULL");
	if (list->size <= index) assert("Index was out of bounds" );

	for (int i = 0; i < index; i++){
		current = current->next;
	}
	
	return current->data; // replace this
	/* 
	* TODO 2
	*/ 
}

// Sets the data of the Node and the index
void set(List* list, int index, void* data) {
	
	/* 
	* TODO 2
	*/ 
	Node* current = list->head;
	
	if (list == NULL) assert ("List is NULL");
	if (list->size <= index) assert("Index was out of bounds" );

	for (int i = 0; i < index; i++){
		current = current->next;
	}
	current->data = data;
	/* 
	* TODO 2
	*/ 
}
