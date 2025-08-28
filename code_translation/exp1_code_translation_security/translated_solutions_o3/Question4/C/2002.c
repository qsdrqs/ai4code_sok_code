/*
 * Requires: a valid, already-connected MySQL handle named  `connection`
 *           (i.e.  MYSQL *connection;  provided elsewhere).
 */

#include <mysql.h>
#include <string.h>

/* -------------------------------------------------------------------------- */

void addStudent(const char *name, unsigned int age)
{
    /* SQL text with placeholders that match the bind order */
    const char *qry =
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

    /* ------------------------------------------------------------------ */
    /* 1.  Create/prepare the statement                                   */
    /* ------------------------------------------------------------------ */
    MYSQL_STMT *stmt = mysql_stmt_init(connection);
    if (stmt == NULL)                       /* allocation failure          */
        return;                             /* -- choose your own error-h. */

    if (mysql_stmt_prepare(stmt, qry, (unsigned long)strlen(qry)) != 0) {
        mysql_stmt_close(stmt);             /* prepare failed              */
        return;
    }

    /* ------------------------------------------------------------------ */
    /* 2.  Bind the parameters                                            */
    /* ------------------------------------------------------------------ */
    MYSQL_BIND bind[2];
    memset(bind, 0, sizeof(bind));

    /* NAME (parameter #0) ------------------------------------------------ */
    bind[0].buffer_type   = MYSQL_TYPE_STRING;
    bind[0].buffer        = (char *)name;           /* cast away const */
    bind[0].buffer_length = (unsigned long)strlen(name);

    /* AGE  (parameter #1) ------------------------------------------------ */
    bind[1].buffer_type   = MYSQL_TYPE_LONG;
    bind[1].buffer        = (char *)&age;
    bind[1].is_unsigned   = 0;                       /* AGE is signed? */

    if (mysql_stmt_bind_param(stmt, bind) != 0) {
        mysql_stmt_close(stmt);             /* bind failed                */
        return;
    }

    /* ------------------------------------------------------------------ */
    /* 3.  Execute, ignore result-set rows (INSERT has none)              */
    /* ------------------------------------------------------------------ */
    mysql_stmt_execute(stmt);

    /* ------------------------------------------------------------------ */
    /* 4.  Clean up                                                       */
    /* ------------------------------------------------------------------ */
    mysql_stmt_close(stmt);
}