#include <windows.h>
#include <sql.h>
#include <sqlext.h>
#include <stdio.h>
#include <string.h>

void addStudent(const char *name, int age) {
    SQLHENV  henv  = SQL_NULL_HENV;
    SQLHDBC  hdbc  = SQL_NULL_HDBC;
    SQLHSTMT hstmt = SQL_NULL_HSTMT;
    SQLRETURN ret;
    char command[512];

    /* Build SQL command (same string concatenation as original) */
    snprintf(command, sizeof(command),
             "INSERT INTO STUDENTS VALUES ('%s','%d');", name, age);

    /* Initialize ODBC environment */
    SQLAllocHandle(SQL_HANDLE_ENV, SQL_NULL_HANDLE, &henv);
    SQLSetEnvAttr(henv, SQL_ATTR_ODBC_VERSION, (SQLPOINTER)SQL_OV_ODBC3, 0);

    /* new ActiveXObject("ADODB.Connection")  */
    SQLAllocHandle(SQL_HANDLE_DBC, henv, &hdbc);

    /* connection.Open('')                    */
    ret = SQLDriverConnect(hdbc, NULL, (SQLCHAR *)"", SQL_NTS,
                           NULL, 0, NULL, SQL_DRIVER_NOPROMPT);

    if (SQL_SUCCEEDED(ret)) {
        /* new ActiveXObject("ADODB.Recordset") */
        SQLAllocHandle(SQL_HANDLE_STMT, hdbc, &hstmt);

        /* rs.Open(command, connection)         */
        SQLExecDirect(hstmt, (SQLCHAR *)command, SQL_NTS);

        SQLFreeHandle(SQL_HANDLE_STMT, hstmt);

        /* connection.close()                   */
        SQLDisconnect(hdbc);
    }

    SQLFreeHandle(SQL_HANDLE_DBC, hdbc);
    SQLFreeHandle(SQL_HANDLE_ENV, henv);
}