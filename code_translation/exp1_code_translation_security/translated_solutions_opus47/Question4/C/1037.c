#include <stdio.h>
#include <stdlib.h>
#include <mysql/mysql.h>

/* Config values - replace with your actual database credentials */
#define DB_HOST     "localhost"
#define DB_USER     "your_user"
#define DB_PASSWORD "your_password"
#define DB_NAME     "your_database"
#define DB_PORT     0

void insertStudent(const char *name, int age) {
    /* Initialize a connection handle (equivalent to mysql.createConnection) */
    MYSQL *connection = mysql_init(NULL);
    
    if (connection == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        return;
    }
    
    /* Establish the actual connection */
    if (mysql_real_connect(connection, DB_HOST, DB_USER, DB_PASSWORD, 
                           DB_NAME, DB_PORT, NULL, 0) == NULL) {
        fprintf(stderr, "mysql_real_connect() failed: %s\n", 
                mysql_error(connection));
        mysql_close(connection);
        return;
    }
    
    /* insert statement */
    const char *sql = "INSERT INTO STUDENTS(title, completed) "
                      "VALUES('Learn how to insert a new row', true)";
    
    /* execute the insert statement (equivalent to connection.query) */
    if (mysql_query(connection, sql)) {
        fprintf(stderr, "mysql_query() failed: %s\n", 
                mysql_error(connection));
        mysql_close(connection);
        return;
    }
    
    /* Close the connection (equivalent to connection.end) */
    mysql_close(connection);
}

int main(void) {
    insertStudent("XXXXX", 0);
    return 0;
}