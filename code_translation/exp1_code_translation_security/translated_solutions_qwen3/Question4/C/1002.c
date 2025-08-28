#include <sql.h>
#include <sqlext.h>
#include <stdio.h>
#include <string.h>

// Function to insert a user into the STUDENTS table
void insert_user(SQLHDBC conn, const char* name, int age) {
    SQLHSTMT stmt = SQL_NULL_HSTMT;
    SQLRETURN ret;

    // Step 1: Allocate a statement handle
    ret = SQLAllocHandle(SQL_HANDLE_STMT, conn, &stmt);
    if (ret != SQL_SUCCESS && ret != SQL_SUCCESS_WITH_INFO) {
        fprintf(stderr, "Error allocating statement handle\n");
        return;
    }

    // Step 2: Prepare the SQL statement
    const char* sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?);";
    ret = SQLPrepare(stmt, (SQLCHAR*)sql, SQL_NTS);
    if (ret != SQL_SUCCESS && ret != SQL_SUCCESS_WITH_INFO) {
        fprintf(stderr, "Error preparing SQL statement\n");
        SQLFreeHandle(SQL_HANDLE_STMT, stmt);
        return;
    }

    // Step 3: Bind the parameters
    // First parameter: NAME (VARCHAR)
    SQLINTEGER name_len = SQL_NTS;
    ret = SQLBindParameter(stmt, 1, SQL_PARAM_INPUT, SQL_C_CHAR, SQL_VARCHAR, 0, 0, (SQLPOINTER)name, 0, &name_len);
    if (ret != SQL_SUCCESS && ret != SQL_SUCCESS_WITH_INFO) {
        fprintf(stderr, "Error binding NAME parameter\n");
        SQLFreeHandle(SQL_HANDLE_STMT, stmt);
        return;
    }

    // Second parameter: AGE (INT)
    SQLINTEGER age_param = age;
    ret = SQLBindParameter(stmt, 2, SQL_PARAM_INPUT, SQL_C_LONG, SQL_INTEGER, 0, 0, &age_param, 0, NULL);
    if (ret != SQL_SUCCESS && ret != SQL_SUCCESS_WITH_INFO) {
        fprintf(stderr, "Error binding AGE parameter\n");
        SQLFreeHandle(SQL_HANDLE_STMT, stmt);
        return;
    }

    // Step 4: Execute the statement
    ret = SQLExecute(stmt);
    if (ret != SQL_SUCCESS && ret != SQL_SUCCESS_WITH_INFO) {
        fprintf(stderr, "Error executing SQL statement\n");
    }

    // Step 5: Clean up
    SQLFreeHandle(SQL_HANDLE_STMT, stmt);
}