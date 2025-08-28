#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Mock dependencies - these would typically come from a SQL Server client library
typedef enum {
    TYPES_NVarChar,
    TYPES_Int
} TYPES;

typedef struct {
    char* sql;
    void (*callback)(char* err);
} Request;

typedef struct {
    char* name;
    TYPES type;
    void* value;
} Parameter;

typedef struct {
    Parameter* parameters;
    int param_count;
    int param_capacity;
} ParameterList;

typedef struct {
    ParameterList params;
} Connection;

// Mock error callback function
void error_callback(char* err) {
    if (err != NULL) {
        printf("%s\n", err);
    }
}

// Mock Request constructor
Request* new_Request(const char* sql, void (*callback)(char*)) {
    Request* req = (Request*)malloc(sizeof(Request));
    req->sql = (char*)malloc(strlen(sql) + 1);
    strcpy(req->sql, sql);
    req->callback = callback;
    return req;
}

// Mock addParameter function
void addParameter(Request* req, Connection* conn, const char* name, TYPES type, void* value) {
    if (conn->params.parameters == NULL) {
        conn->params.param_capacity = 10;
        conn->params.parameters = (Parameter*)malloc(sizeof(Parameter) * conn->params.param_capacity);
        conn->params.param_count = 0;
    }
    
    if (conn->params.param_count >= conn->params.param_capacity) {
        conn->params.param_capacity *= 2;
        conn->params.parameters = (Parameter*)realloc(conn->params.parameters, 
                                                     sizeof(Parameter) * conn->params.param_capacity);
    }
    
    Parameter* param = &conn->params.parameters[conn->params.param_count];
    param->name = (char*)malloc(strlen(name) + 1);
    strcpy(param->name, name);
    param->type = type;
    
    if (type == TYPES_NVarChar) {
        param->value = malloc(strlen((char*)value) + 1);
        strcpy((char*)param->value, (char*)value);
    } else if (type == TYPES_Int) {
        param->value = malloc(sizeof(int));
        *((int*)param->value) = *((int*)value);
    }
    
    conn->params.param_count++;
}

// Mock execSql function
void execSql(Connection* conn, Request* req) {
    // This would execute the SQL with parameters in a real implementation
    printf("Executing SQL: %s\n", req->sql);
    printf("Parameters:\n");
    for (int i = 0; i < conn->params.param_count; i++) {
        printf("  %s: ", conn->params.parameters[i].name);
        if (conn->params.parameters[i].type == TYPES_NVarChar) {
            printf("%s\n", (char*)conn->params.parameters[i].value);
        } else if (conn->params.parameters[i].type == TYPES_Int) {
            printf("%d\n", *((int*)conn->params.parameters[i].value));
        }
    }
    
    // Call callback with no error (NULL)
    req->callback(NULL);
}

// Translated function
void insert_user(Connection* conn, const char* name, int age) {
    Request* request = new_Request("INSERT INTO STUDENTS (NAME, AGE) VALUES (@NAME, @AGE);", error_callback);
    
    addParameter(request, conn, "NAME", TYPES_NVarChar, (void*)name);
    addParameter(request, conn, "AGE", TYPES_Int, &age);
    
    execSql(conn, request);
    
    // Clean up
    free(request->sql);
    free(request);
}

// Helper function to initialize connection
Connection* init_connection() {
    Connection* conn = (Connection*)malloc(sizeof(Connection));
    conn->params.parameters = NULL;
    conn->params.param_count = 0;
    conn->params.param_capacity = 0;
    return conn;
}

// Helper function to clean up connection
void cleanup_connection(Connection* conn) {
    if (conn->params.parameters != NULL) {
        for (int i = 0; i < conn->params.param_count; i++) {
            free(conn->params.parameters[i].name);
            free(conn->params.parameters[i].value);
        }
        free(conn->params.parameters);
    }
    free(conn);
}

// Example usage
int main() {
    Connection* conn = init_connection();
    insert_user(conn, "John Doe", 25);
    cleanup_connection(conn);
    return 0;
}