#include <stdio.h>
#include <stdlib.h>

char* make_string_from(char* from, int count) 
{
    /* TODO 2 */
    char* string = calloc(count + 1, sizeof(char));
    for (int i = 0; i < count; i++)
    {
        string[i] = from[i];
    }
    string[count] = '\0';
    return string;
    /* TODO 2 */
}

int main(int argc, char** argv) 
{
    /* TODO 1 */
    for (int i = 1; i < argc; i++)
    {
        printf("%s\n", argv[i]);
    }
    /* TODO 1 */


    /* TODO 3 */
    int buffer_index = 0;
    char c;
    char* rule = NULL;
    char* char_buffer = calloc(1000, sizeof(char));
    while (1)
    {
        c = getchar();
        if (c == EOF)
        {
            break;
        }
        else if (c == ':')
        {
            if (rule != NULL)
            {
                free(rule);
            }
            rule = make_string_from(char_buffer, buffer_index);
            buffer_index = 0;
        }
        else if ((c == ',') || (c == '\n'))
        {
            if (buffer_index != 0)
            {
                char* expansion = make_string_from(char_buffer, buffer_index);
                buffer_index = 0;
                printf("A potential expansion of rule '%s' is '%s'\n", rule, expansion);
                free(expansion);
            }
        }
        else 
        {
            char_buffer[buffer_index] = c;
            buffer_index++;
        }
    }
    if (rule != NULL)
    {
        free(rule);
    }
    free(char_buffer);
    /* TODO 3 */
}


