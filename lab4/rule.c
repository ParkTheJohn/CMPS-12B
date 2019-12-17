
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "list.h"
#include "rule.h"
#include "helpers.h"
#include <assert.h>

// Constructor for Rule
Rule* make_rule(char* key){
  /*
    TODO 3
  */
	Rule* rule = malloc(sizeof(rule));
	rule->key = copy_string(key);
	rule->expansions = make_list();
	return rule; // replace this
  /*
    TODO 3
  */
}

// Destructor for Rule
void free_rule(Rule* rule){
  /*
    TODO 3
  */
	if (rule != NULL){
		if (rule->key != NULL){
			free(rule->key);
		}
		free_list(rule->expansions);
		free(rule);
	}
	
  /*
    TODO 3
  */
	
}

// Fills in grammar, add Rule key to the end of grammar List, read expansion, add it to the end of the
// most recent Rule, and return the filled grammar
List* read_grammar(char* filename){
   
  /*
   * TODO 4A
   */ 
  //Construct a new List* called grammar that we will fill up in the following code
	List* grammar = make_list();
	Rule* lastRule = NULL;
  /* 
   * TODO 4A
   */
  FILE* input_file = fopen(filename,"r");
  char buffer[1000];
  
  int number_of_expansions = 0;
  int buffer_index = 0;

  int number_of_rules = 0;
  for (char current = fgetc(input_file); current != EOF; current = fgetc(input_file)){
    if (current == ':'){
      
	  
      char* key = calloc(buffer_index+1,sizeof(char));
      memcpy(key,buffer,buffer_index);
      /*
       * TODO 4B
       */ 
	   
      //Construct a new Rule* and add it to grammar
      lastRule = make_rule(key);
      add(grammar, grammar->size, lastRule);
      /*
       * TODO 4B
       */ 
      buffer_index = 0;
    }
    else if (current == ',' || current == '\n'){
      
      char* expansion = calloc(buffer_index+1,sizeof(char));      
      memcpy(expansion,buffer,buffer_index);
		
      /*
       * TODO 4C
       */ 
      //Get the last Rule* inserted into grammar and add expansion to it 
      if (lastRule != NULL){
    	  add(lastRule->expansions, lastRule->expansions->size, expansion);
      } else{
    	  assert("Rule is NULL");
      }
      /*
       * TODO 4C
       */ 
      buffer_index = 0;
		 
    }
    else {
      buffer[buffer_index] = current;
      buffer_index++;
    }
  }
  fclose(input_file);

  
  /*
   * TODO 4D
   */ 
  return grammar; // replace this to return the grammar we just filled up
  /*
   * TODO 4D
   */ 
}


// Splits grammar at delimiter and adds the words into a List, separated whether they have an even or odd index,
// and the function is called again recursively if the index is odd, repeating the expansion. Returns the final
// expanded string.
char* expand(char* text, List* grammar){

  /*
   * BONUS TODO
   */
	List* words = split(text, "#");
	List* texts = make_list();
	for (int i = 0; i < words->size; i++){
		if (i%2 == 0){
			add(texts, texts->size, copy_string(get(words, i)));
		} else{
			for (int j = 0; j < grammar->size; j++){
				Rule* rule = get(grammar, j);
				if (strcmp(rule->key, get(words, i)) == 0){
					char* randomSelect = get_random(rule->expansions);
					add(texts, texts->size, expand(randomSelect, grammar));
				}
			}

		}
	}
	char* finalString = join(texts);
	free_list(words);
	free_list(texts);
	return finalString;
	
  /*
   * BONUS TODO
   */
}

//Iterates through a grammar list and prints out all of the rules
void print_grammar(List* grammar){
  
  for (int ii = 0; ii < grammar->size; ii++){
    Rule* rule = get(grammar,ii);
    for (int jj = 0; jj < rule->expansions->size; jj++){
      printf("A potential expansion of rule '%s' is '%s'\n",rule->key, (char*) get(rule->expansions,jj));
    }
  }
  
}
