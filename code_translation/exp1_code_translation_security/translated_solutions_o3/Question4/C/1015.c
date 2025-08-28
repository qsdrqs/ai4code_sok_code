/* add_student.c
 *
 * gcc add_student.c -o add_student -lmysqlclient
 * (plus whatever flags/paths are required on your system)
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>

/* ------------------------------------------------------------------ */
/*  addStudent : Insert one row into the STUDENTS table               */
/* ------------------------------------------------------------------ */
void addStudent(const char *name, int age)
{
    /* Connection handle */
    MYSQL *con = mysql_init(NULL);
    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(EXIT_FAILURE);
    }

    /* Connect to the database (host, user, pass, db, port, unix_socket, flags) */
    if (mysql_real_connect(con,
                           "localhost",
                           "yourusername",
                           "yourpassword",
                           "mydb",
                           0,            /* default port */
                           NULL,         /* default socket */
                           0) == NULL)   /* no special flags */
    {
        fprintf(stderr, "Connection error: %s\n", mysql_error(con));
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    printf("Connected!\n");

    /* ------------------------------------------------------------------
       Build the INSERT statement.  We escape the name so it cannot break
       the SQL syntax or be used for injection attacks.
       ------------------------------------------------------------------ */
    char esc_name[2 * 256 + 1];                 /* <- holds escaped text   */
    mysql_real_escape_string(con,               /* connection              */
                             esc_name,          /* destination             */
                             name,              /* source                  */
                             strlen(name));     /* source length           */

    char query[512];
    snprintf(query, sizeof(query),
             "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', '%d')",
             esc_name, age);

    /* Execute the INSERT */
    if (mysql_query(con, query)) {
        fprintf(stderr, "INSERT failed: %s\n", mysql_error(con));
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    printf("1 record inserted\n");

    /* Clean up */
    mysql_close(con);
}

/* ------------------------------------------------------------------ */
/*  Simple test driver                                                */
/* ------------------------------------------------------------------ */
int main(void)
{
    addStudent("Jane Doe", 20);
    return 0;
}