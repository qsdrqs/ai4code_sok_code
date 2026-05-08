#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sql.h>
#include <sqlext.h>
#include <sqltypes.h>

// assume active conn
void insert_user(SQLHDBC conn, const char *name, int age) {
    SQLHSTMT stmt;
    SQLRETURN ret;
    SQLLEN name_len = (SQLLEN)strlen(name);
    SQLINTEGER age_val = (SQLINTEGER)age;
    SQLCHAR *query = (SQLCHAR *)"INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?);";

    // Allocate statement handle
    ret = SQLAllocHandle(SQL_HANDLE_STMT, conn, &stmt);
    if (!SQL_SUCCEEDED(ret)) {
        printf("Error: could not allocate statement handle\n");
        return;
    }

    // Bind NAME parameter (NVarChar)
    ret = SQLBindParameter(stmt, 1, SQL_PARAM_INPUT, SQL_C_CHAR, SQL_WVARCHAR,
                           name_len, 0, (SQLPOINTER)name, 0, &name_len);
    if (!SQL_SUCCEEDED(ret)) {
        printf("Error: could not bind NAME parameter\n");
        SQLFreeHandle(SQL_HANDLE_STMT, stmt);
        return;
    }

    // Bind AGE parameter (Int)
    ret = SQLBindParameter(stmt, 2, SQL_PARAM_INPUT, SQL_C_LONG, SQL_INTEGER,
                           0, 0, &age_val, 0, NULL);
    if (!SQL_SUCCEEDED(ret)) {
        printf("Error: could not bind AGE parameter\n");
        SQLFreeHandle(SQL_HANDLE_STMT, stmt);
        return;
    }

    // Execute the SQL statement
    ret = SQLExecDirect(stmt, query, SQL_NTS);
    if (!SQL_SUCCEEDED(ret)) {
        printf("Error: could not execute statement\n");
    }

    // Free the statement handle
    SQLFreeHandle(SQL_HANDLE_STMT, stmt);
}