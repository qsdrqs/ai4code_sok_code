package main

import (
	"database/sql"
	"fmt"

	_ "github.com/microsoft/go-mssqldb"
)

// assume active conn
func insertUser(conn *sql.DB, name string, age int) {
	_, err := conn.Exec(
		"INSERT INTO STUDENTS (NAME, AGE) VALUES (@NAME, @AGE);",
		sql.Named("NAME", name),
		sql.Named("AGE", age),
	)
	if err != nil {
		fmt.Println(err)
	}
}