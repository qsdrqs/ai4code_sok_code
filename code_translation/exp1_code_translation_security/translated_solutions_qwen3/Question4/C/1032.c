#include <stdio.h>
#include <stdlib.h>

char* addStudent(char* name, int age) {
    // Calculate the required length for the SQL string
    int len = snprintf(NULL, 0, "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);
    
    // Allocate memory for the SQL string (+1 for null terminator)
    char* sql = (char*)malloc(len + 1);
    if (!sql) {
        return NULL; // Handle memory allocation failure
    }

    // Format the SQL string
    snprintf(sql, len + 1, "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);
    
    return sql;
}

//addStudent("XXXXX", 0);  // This line is commented out as in the original JavaScript