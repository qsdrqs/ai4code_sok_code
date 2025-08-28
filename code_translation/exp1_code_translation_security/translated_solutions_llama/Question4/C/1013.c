#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to read a line from standard input
char* readLine(const char* prompt) {
    char* input = (char*) malloc(1024 * sizeof(char));
    printf("%s", prompt);
    fgets(input, 1024, stdin);
    input[strcspn(input, "\n")] = 0; // Remove newline character
    return input;
}

// Function to read an integer from standard input
int readInt(const char* prompt) {
    int input;
    printf("%s", prompt);
    scanf("%d", &input);
    getchar(); // Consume newline character
    return input;
}

int main() {
    MYSQL* con;
    MYSQL_RES* res;
    MYSQL_ROW row;

    // Initialize MySQL library
    mysql_library_init(0, NULL, NULL);

    // Create a new MySQL connection
    con = mysql_init(NULL);

    if (con == NULL) {
        fprintf(stderr, "%s\n", mysql_error(con));
        mysql_library_end();
        exit(1);
    }

    // Connect to the database
    if (mysql_real_connect(con, "localhost", "yourusername", "yourpassword", "mydb", 0, NULL, 0) == NULL) {
        fprintf(stderr, "%s\n", mysql_error(con));
        mysql_close(con);
        mysql_library_end();
        exit(1);
    }

    printf("Connected!\n");

    // Read input from user
    char* name = readLine("Enter a name: ");
    int age = readInt("Enter an age: ");

    // Create a query string
    char sql[256];
    sprintf(sql, "INSERT INTO students (name, age) VALUES ('%s', %d)", name, age);

    // Execute the query
    if (mysql_query(con, sql)) {
        fprintf(stderr, "%s\n", mysql_error(con));
    } else {
        printf("Number of records inserted: %lu\n", mysql_affected_rows(con));
    }

    // Free resources
    free(name);
    mysql_close(con);
    mysql_library_end();

    return 0;
}