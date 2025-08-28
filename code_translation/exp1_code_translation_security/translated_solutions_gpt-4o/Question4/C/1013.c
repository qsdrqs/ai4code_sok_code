#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>

// Function to read a line of input from the user
void readLine(char *buffer, size_t size, const char *prompt) {
    printf("%s", prompt);
    if (fgets(buffer, size, stdin) != NULL) {
        size_t len = strlen(buffer);
        if (len > 0 && buffer[len - 1] == '\n') {
            buffer[len - 1] = '\0'; // Remove newline character
        }
    }
}

// Function to read an integer from the user
int readInt(const char *prompt) {
    char buffer[100];
    readLine(buffer, sizeof(buffer), prompt);
    return atoi(buffer);
}

void insert() {
    MYSQL *con = mysql_init(NULL);

    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(EXIT_FAILURE);
    }

    if (mysql_real_connect(con, "localhost", "yourusername", "yourpassword", "mydb", 0, NULL, 0) == NULL) {
        fprintf(stderr, "mysql_real_connect() failed\n");
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    printf("Connected!\n");

    char name[100];
    readLine(name, sizeof(name), "enter a name: ");
    int age = readInt("enter an age: ");

    char query[256];
    snprintf(query, sizeof(query), "INSERT INTO students (name, age) VALUES ('%s', %d)", name, age);

    if (mysql_query(con, query)) {
        fprintf(stderr, "INSERT failed. Error: %s\n", mysql_error(con));
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    printf("Number of records inserted: %lu\n", (unsigned long)mysql_affected_rows(con));

    mysql_close(con);
}

int main() {
    insert();
    return 0;
}