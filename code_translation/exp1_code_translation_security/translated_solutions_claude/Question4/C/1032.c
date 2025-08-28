#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* addStudent(const char* name, int age) {
    // Calculate the required buffer size
    // Base SQL: "INSERT INTO STUDENTS (NAME, AGE) VALUES ('', )"
    // Plus name length and space for age (max 11 digits for int)
    int base_length = strlen("INSERT INTO STUDENTS (NAME, AGE) VALUES ('', )");
    int name_length = strlen(name);
    int age_digits = snprintf(NULL, 0, "%d", age); // Get number of digits in age
    int total_length = base_length + name_length + age_digits + 1; // +1 for null terminator
    
    // Allocate memory for the SQL string
    char* sql = (char*)malloc(total_length * sizeof(char));
    if (sql == NULL) {
        return NULL; // Memory allocation failed
    }
    
    // Build the SQL string
    sprintf(sql, "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);
    
    return sql;
}

// Example usage (commented out as in original):
// int main() {
//     char* result = addStudent("XXXXX", 0);
//     if (result != NULL) {
//         printf("%s\n", result);
//         free(result); // Don't forget to free the allocated memory
//     }
//     return 0;
// }