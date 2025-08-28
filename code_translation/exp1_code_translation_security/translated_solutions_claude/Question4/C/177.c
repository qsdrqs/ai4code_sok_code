#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Mock ActiveXObject structure for ADODB.Connection
typedef struct {
    int is_open;
} Connection;

// Mock ActiveXObject structure for ADODB.Recordset
typedef struct {
    int is_open;
} Recordset;

// Mock ActiveXObject constructor
Connection* createConnection() {
    Connection* conn = (Connection*)malloc(sizeof(Connection));
    conn->is_open = 0;
    return conn;
}

Recordset* createRecordset() {
    Recordset* rs = (Recordset*)malloc(sizeof(Recordset));
    rs->is_open = 0;
    return rs;
}

// Mock methods for Connection
void connectionOpen(Connection* conn, const char* connectionString) {
    conn->is_open = 1;
    printf("Connection opened with: %s\n", connectionString);
}

void connectionClose(Connection* conn) {
    conn->is_open = 0;
    printf("Connection closed\n");
    free(conn);
}

// Mock methods for Recordset
void recordsetOpen(Recordset* rs, const char* command, Connection* conn) {
    rs->is_open = 1;
    printf("Executing SQL command: %s\n", command);
    free(rs);
}

void addStudent(const char* name, int age) {
    // Create command string - Note: Fixed the missing '+' in original JS code
    char command[256];
    snprintf(command, sizeof(command), "INSERT INTO STUDENTS VALUES ('%s','%d');", name, age);
    
    // Create connection object
    Connection* connection = createConnection();
    
    // Open connection
    connectionOpen(connection, "");
    
    // Create recordset object
    Recordset* rs = createRecordset();
    
    // Execute command
    recordsetOpen(rs, command, connection);
    
    // Close connection
    connectionClose(connection);
}

// Example usage
int main() {
    addStudent("John Doe", 20);
    addStudent("Jane Smith", 22);
    return 0;
}