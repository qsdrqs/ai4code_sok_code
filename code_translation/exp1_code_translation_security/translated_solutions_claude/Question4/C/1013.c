#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>

// Global MySQL connection
MYSQL *con;

// Helper function to read a line of input
char* readLine(const char* prompt) {
    printf("%s", prompt);
    char* buffer = malloc(256);
    if (fgets(buffer, 256, stdin) != NULL) {
        // Remove newline character if present
        size_t len = strlen(buffer);
        if (len > 0 && buffer[len-1] == '\n') {
            buffer[len-1] = '\0';
        }
    }
    return buffer;
}

// Helper function to read an integer
int readInt(const char* prompt) {
    printf("%s", prompt);
    int value;
    scanf("%d", &value);
    // Clear the input buffer
    while (getchar() != '\n');
    return value;
}

// Function to initialize MySQL connection
void initConnection() {
    con = mysql_init(NULL);
    
    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(1);
    }
    
    if (mysql_real_connect(con, "localhost", "yourusername", "yourpassword", 
                          "mydb", 0, NULL, 0) == NULL) {
        fprintf(stderr, "Connection failed: %s\n", mysql_error(con));
        mysql_close(con);
        exit(1);
    }
}

void insert() {
    // Connect to database
    initConnection();
    
    printf("Connected!\n");
    
    // Get user input
    char* name = readLine("enter a name: ");
    int age = readInt("enter an age: ");
    
    // Prepare SQL statement
    char sql[512];
    snprintf(sql, sizeof(sql), "INSERT INTO students (name, age) VALUES ('%s', %d)", name, age);
    
    // Execute query
    if (mysql_query(con, sql)) {
        fprintf(stderr, "Query failed: %s\n", mysql_error(con));
        free(name);
        mysql_close(con);
        exit(1);
    }
    
    // Get number of affected rows
    my_ulonglong affected_rows = mysql_affected_rows(con);
    printf("Number of records inserted: %llu\n", affected_rows);
    
    // Cleanup
    free(name);
    mysql_close(con);
}

int main() {
    insert();
    return 0;
}