#ifndef CMPS12B_LIST
#define CMPS12B_LIST

// Members for the Node
typedef struct node_type{
	/*
	* TODO 1
	*/
	void* data;
	struct node_type* next;
	/*
	* TODO 1
	*/
	
} Node;

// Members for the List
typedef struct {
	
	/*
	* TODO 1
	*/
	int size;
	Node* head;
	/*
	* TODO 1
	*/
} List;

Node* make_node(void* data, Node* next);
List* make_list();

void free_node(Node* node);
void free_list(List* list);

void add(List* list, int index, void* data);
void* get(List* list, int index);
void set(List* list, int index, void* data);


#endif

