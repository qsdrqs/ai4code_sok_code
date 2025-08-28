#include <stdio.h>
#include <stdlib.h>
#include <sql.h>
#include <sqlext.h>

// Function to insert a user into the STUDENTS table
void insert_user(SQLHDBC dbc, const char *name, int age) {
    SQLHSTMT stmt;
    SQLRETURN ret;
    SQLCHAR *query = (SQLCHAR *)"INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?);";

    // Allocate a statement handle
    ret = SQLAllocHandle(SQL_HANDLE_STMT, dbc, &stmt);
    if (ret != SQL_SUCCESS && ret != SQL_SUCCESS_WITH_INFO) {
        fprintf(stderr, "Error allocating statement handle\n");
        return;
    }

    // Prepare the SQL statement
    ret = SQLPrepare(stmt, query, SQL_NTS);
    if (ret != SQL_SUCCESS && ret != SQL_SUCCESS_WITH_INFO) {
        fprintf(stderr, "Error preparing SQL statement\n");
        SQLFreeHandle(SQL_HANDLE_STMT, stmt);
        return;
    }

    // Bind the parameters
    ret = SQLBindParameter(stmt, 1, SQL_PARAM_INPUT, SQL_C_CHAR, SQL_VARCHAR, 0, 0, (SQLPOINTER)name, 0, NULL);
    if (ret != SQL_SUCCESS && ret != SQL_SUCCESS_WITH_INFO) {
        fprintf(stderr, "Error binding parameter 1\n");
        SQLFreeHandle(SQL_HANDLE_STMT, stmt);
        return;
    }

    ret = SQLBindParameter(stmt, 2, SQL_PARAM_INPUT, SQL_C_SLONG, SQL_INTEGER, 0, 0, &age, 0, NULL);
    if (ret != SQL_SUCCESS && ret != SQL_SUCCESS_WITH_INFO) {
        fprintf(stderr, "Error binding parameter 2\n");
        SQLFreeHandle(SQL_HANDLE_STMT, stmt);
        return;
    }

    // Execute the SQL statement
    ret = SQLExecute(stmt);
    if (ret != SQL_SUCCESS && ret != SQL_SUCCESS_WITH_INFO) {
        fprintf(stderr, "Error executing SQL statement\n");
    }

    // Free the statement handle
    SQLFreeHandle(SQL_HANDLE_STMT, stmt);
}

int main() {
    // Assume dbc is a valid and connected SQLHDBC
    SQLHDBC dbc;

    // Example usage
    insert_user(dbc, "John Doe", 25);

    return 0;
}