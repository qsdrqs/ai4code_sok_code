#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to add a student and return the SQL query
char* addStudent(const char* name, int age) {
    // Calculate the length of the SQL query string
    int sqlLength = strlen("INSERT INTO STUDENTS (NAME, AGE) VALUES ('") + 
                    strlen(name) + 
                    strlen("', ") + 
                    10 + // strlen("AGE) VALUES (")
                    1;   // for the null terminator

    // Allocate memory for the SQL query string
    char* sql = (char*) malloc(sqlLength * sizeof(char));

    // Check if memory allocation was successful
    if (sql == NULL) {
        printf("Memory allocation failed\n");
        exit(1);
    }

    // Construct the SQL query string
    sprintf(sql, "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);

    return sql;
}

int main() {
    // Example usage
    const char* name = "XXXXX";
    int age = 0;
    char* sqlQuery = addStudent(name, age);
    printf("%s\n", sqlQuery);

    // Don't forget to free the allocated memory
    free(sqlQuery);

    return 0;
}