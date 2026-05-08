package main

import (
	"database/sql"

	_ "github.com/go-sql-driver/mysql"
)

// connection is expected to be a *sql.DB corresponding to a mysql db connection
var connection *sql.DB

/*
 * @requires variable name {connection} corresponding to a mysql db connection
 */
func addStudent(name string, age int) {
	q := `INSERT INTO STUDENTS(NAME, AGE)
	      VALUES(?,?)`
	v := []interface{}{name, age}
	_, _ = connection.Exec(q, v...)
}