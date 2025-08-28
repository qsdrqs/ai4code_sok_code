#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// ODBC requires windows.h on Windows
#ifdef _WIN32
#include <windows.h>
#endif

// Core ODBC headers
#include <sql.h>
#include <sqlext.h>

/**
 * @brief A helper function to extract and print detailed ODBC error information.
 * 
 * @param handleType The type of handle (SQL_HANDLE_ENV, SQL_HANDLE_DBC, or SQL_HANDLE_STMT).
 * @param handle The handle on which the error occurred.
 */
void extract_error(const char* function_name, SQLHANDLE handle, SQLSMALLINT handleType) {
    SQLINTEGER i = 0;
    SQLINTEGER native;
    SQLCHAR state[7];
    SQLCHAR text[256];
    SQLSMALLINT len;
    SQLRETURN ret;

    fprintf(stderr, "\nAn error occurred in function: %s\n", function_name);

    // SQLGetDiagRec retrieves diagnostic records (errors/warnings)
    while ((ret = SQLGetDiagRec(handleType, handle, ++i, state, &native, text, sizeof(text), &len)) != SQL_NO_DATA) {
        fprintf(stderr, "  State: %s, Native Error: %ld, Message: %s\n", state, native, text);
    }
}

/**
 * @brief Inserts a new student record into the database.
 *        This function connects to a database via an ODBC Data Source Name (DSN),
 *        prepares a secure SQL INSERT statement, and executes it.
 * 
 * @param name The name of the student.
 * @param age The age of the student.
 */
void addStudent(const char* name, int age) {
    // 1. Declare ODBC handles
    SQLHENV henv; // Environment Handle
    SQLHDBC hdbc; // Connection Handle
    SQLHSTMT hstmt; // Statement Handle
    SQLRETURN retcode; // Return code from ODBC functions

    // --- STEP 1: Allocate Environment Handle ---
    retcode = SQLAllocHandle(SQL_HANDLE_ENV, SQL_NULL_HANDLE, &henv);
    if (retcode != SQL_SUCCESS && retcode != SQL_SUCCESS_WITH_INFO) {
        fprintf(stderr, "Error allocating environment handle\n");
        return;
    }

    // --- STEP 2: Set the ODBC version ---
    retcode = SQLSetEnvAttr(henv, SQL_ATTR_ODBC_VERSION, (SQLPOINTER*)SQL_OV_ODBC3, 0);
    if (retcode != SQL_SUCCESS && retcode != SQL_SUCCESS_WITH_INFO) {
        extract_error("SQLSetEnvAttr", henv, SQL_HANDLE_ENV);
        SQLFreeHandle(SQL_HANDLE_ENV, henv);
        return;
    }

    // --- STEP 3: Allocate Connection Handle ---
    retcode = SQLAllocHandle(SQL_HANDLE_DBC, henv, &hdbc);
    if (retcode != SQL_SUCCESS && retcode != SQL_SUCCESS_WITH_INFO) {
        extract_error("SQLAllocHandle (Connection)", henv, SQL_HANDLE_ENV);
        SQLFreeHandle(SQL_HANDLE_ENV, henv);
        return;
    }

    // --- STEP 4: Connect to the Data Source ---
    // The JavaScript `connection.Open('')` is ambiguous. In ODBC, you typically connect
    // to a pre-configured Data Source Name (DSN).
    // You must create a DSN named "MyStudentDSN" using the "ODBC Data Sources"
    // tool in Windows Control Panel for this to work.
    printf("Connecting to database...\n");
    retcode = SQLConnect(hdbc, (SQLCHAR*)"MyStudentDSN", SQL_NTS, (SQLCHAR*)NULL, 0, (SQLCHAR*)NULL, 0);
    if (retcode != SQL_SUCCESS && retcode != SQL_SUCCESS_WITH_INFO) {
        extract_error("SQLConnect", hdbc, SQL_HANDLE_DBC);
        goto cleanup_connection; // Use goto for centralized cleanup
    }
    printf("Successfully connected to database.\n");

    // --- STEP 5: Allocate Statement Handle ---
    retcode = SQLAllocHandle(SQL_HANDLE_STMT, hdbc, &hstmt);
    if (retcode != SQL_SUCCESS && retcode != SQL_SUCCESS_WITH_INFO) {
        extract_error("SQLAllocHandle (Statement)", hdbc, SQL_HANDLE_DBC);
        goto cleanup_connection;
    }

    // --- STEP 6: Prepare the SQL Statement with Parameter Markers (?) ---
    // This is the secure way to execute queries, preventing SQL injection.
    const char* sql_command = "INSERT INTO STUDENTS (Name, Age) VALUES (?, ?)";
    retcode = SQLPrepare(hstmt, (SQLCHAR*)sql_command, SQL_NTS);
    if (retcode != SQL_SUCCESS && retcode != SQL_SUCCESS_WITH_INFO) {
        extract_error("SQLPrepare", hstmt, SQL_HANDLE_STMT);
        goto cleanup_statement;
    }

    // --- STEP 7: Bind Parameters to the Statement ---
    // Bind the 'name' variable to the first parameter marker (?)
    retcode = SQLBindParameter(hstmt, 1, SQL_PARAM_INPUT, SQL_C_CHAR, SQL_VARCHAR, strlen(name), 0, (SQLPOINTER)name, 0, NULL);
    if (retcode != SQL_SUCCESS && retcode != SQL_SUCCESS_WITH_INFO) {
        extract_error("SQLBindParameter (Name)", hstmt, SQL_HANDLE_STMT);
        goto cleanup_statement;
    }

    // Bind the 'age' variable to the second parameter marker (?)
    retcode = SQLBindParameter(hstmt, 2, SQL_PARAM_INPUT, SQL_C_SLONG, SQL_INTEGER, 0, 0, &age, 0, NULL);
    if (retcode != SQL_SUCCESS && retcode != SQL_SUCCESS_WITH_INFO) {
        extract_error("SQLBindParameter (Age)", hstmt, SQL_HANDLE_STMT);
        goto cleanup_statement;
    }

    // --- STEP 8: Execute the Statement ---
    printf("Executing INSERT statement for %s, age %d...\n", name, age);
    retcode = SQLExecute(hstmt);
    if (retcode == SQL_SUCCESS || retcode == SQL_SUCCESS_WITH_INFO) {
        printf("Student added successfully.\n");
    } else {
        extract_error("SQLExecute", hstmt, SQL_HANDLE_STMT);
    }

// --- STEP 9: Cleanup ---
// Free handles in the reverse order of allocation.
cleanup_statement:
    SQLFreeHandle(SQL_HANDLE_STMT, hstmt);

cleanup_connection:
    SQLDisconnect(hdbc);
    SQLFreeHandle(SQL_HANDLE_DBC, hdbc);
    SQLFreeHandle(SQL_HANDLE_ENV, henv);
}

// Example main function to demonstrate usage
int main() {
    // To run this, you need:
    // 1. An ODBC driver for your database (e.g., for SQL Server, MySQL, etc.).
    // 2. A database with a table like: CREATE TABLE STUDENTS (Name VARCHAR(100), Age INT);
    // 3. An ODBC Data Source Name (DSN) configured in Windows named "MyStudentDSN".
    
    addStudent("Alice", 22);
    addStudent("Bob", 25);

    return 0;
}