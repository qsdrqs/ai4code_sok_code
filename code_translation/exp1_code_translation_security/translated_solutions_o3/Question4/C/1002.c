#include <stdio.h>
#include <sql.h>
#include <sqlext.h>

/*  A minimal error helper.
    In the real project you can replace it by whatever error-handling
    facility you already have. */
static void
dump_odbc_error(SQLSMALLINT handle_type, SQLHANDLE handle,
                const char *where)
{
    SQLCHAR  state[6];
    SQLCHAR  message[SQL_MAX_MESSAGE_LENGTH];
    SQLINTEGER native_code;
    SQLSMALLINT len;
    SQLRETURN  rc;

    fprintf(stderr, "[ODBC] %s failed\n", where);

    rc = SQLGetDiagRec(handle_type, handle, 1,
                       state, &native_code,
                       message, sizeof(message), &len);

    if (SQL_SUCCEEDED(rc))
        fprintf(stderr, "        %s (%ld) ‑ %s\n",
                state, (long)native_code, message);
}

/*  Equivalent of the JavaScript insert_user.           */
/*  conn is an **already opened** ODBC connection (HDBC)*/
void insert_user(SQLHDBC conn, const char *name, int age)
{
    SQLHSTMT   stmt  = SQL_NULL_HSTMT;
    SQLRETURN  rc;

    /* Allocate a statement handle */
    rc = SQLAllocHandle(SQL_HANDLE_STMT, conn, &stmt);
    if (!SQL_SUCCEEDED(rc))
    {
        dump_odbc_error(SQL_HANDLE_DBC, conn, "SQLAllocHandle");
        return;
    }

    /* Prepare the INSERT statement (parameter markers “?”) */
    static const char *sql =
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?);";

    rc = SQLPrepare(stmt, (SQLCHAR *)sql, SQL_NTS);
    if (!SQL_SUCCEEDED(rc))
    {
        dump_odbc_error(SQL_HANDLE_STMT, stmt, "SQLPrepare");
        goto cleanup;
    }

    /* -------- Bind parameter @NAME (index 1) -------- */
    rc = SQLBindParameter(stmt,               /* statement   */
                          1,                  /* parameter # */
                          SQL_PARAM_INPUT,    /* direction   */
                          SQL_C_CHAR,         /* C   type    */
                          SQL_VARCHAR,        /* SQL type    */
                          0, 0,               /* size/scale  */
                          (SQLPOINTER)name,   /* buffer      */
                          0,                  /* buffer len  */
                          NULL);              /* strlen ptr  */

    if (!SQL_SUCCEEDED(rc))
    {
        dump_odbc_error(SQL_HANDLE_STMT, stmt, "SQLBindParameter(1)");
        goto cleanup;
    }

    /* -------- Bind parameter @AGE (index 2) -------- */
    rc = SQLBindParameter(stmt,
                          2,
                          SQL_PARAM_INPUT,
                          SQL_C_SLONG,
                          SQL_INTEGER,
                          0, 0,
                          (SQLPOINTER)&age,
                          0,
                          NULL);

    if (!SQL_SUCCEEDED(rc))
    {
        dump_odbc_error(SQL_HANDLE_STMT, stmt, "SQLBindParameter(2)");
        goto cleanup;
    }

    /* Execute the prepared statement */
    rc = SQLExecute(stmt);
    if (!SQL_SUCCEEDED(rc))
        dump_odbc_error(SQL_HANDLE_STMT, stmt, "SQLExecute");

cleanup:
    if (stmt != SQL_NULL_HSTMT)
        SQLFreeHandle(SQL_HANDLE_STMT, stmt);
}