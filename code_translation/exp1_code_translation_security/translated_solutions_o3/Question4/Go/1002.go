package main

import (
	"context"
	"database/sql"
	"log"
)

// assume an already–opened connection pool:
//
//     db, err := sql.Open("sqlserver", "<connection string>")
//     ...
//
// insertUser mirrors the behaviour of `insert_user` in the original JS code.
func insertUser(db *sql.DB, name string, age int) {
	const stmt = `INSERT INTO STUDENTS (NAME, AGE) VALUES (@NAME, @AGE);`

	_, err := db.ExecContext(
		context.Background(),
		stmt,
		sql.Named("NAME", name),
		sql.Named("AGE", age),
	)
	if err != nil {
		log.Println(err)
	}
}