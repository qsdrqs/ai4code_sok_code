package main

import (
	"database/sql"
	"log"

	_ "github.com/go-sql-driver/mysql" // MySQL driver
)

// Example DSN:  "<user>:<password>@tcp(<host>:<port>)/<database>?parseTime=true"
const dsn = "user:password@tcp(localhost:3306)/school?parseTime=true"

func insertStudent(name string, age int) {
	// Open a connection pool.
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		log.Fatalf("sql.Open: %v", err)
	}
	defer db.Close()

	// Equivalent to the original hard-coded INSERT.
	sqlStr := `
		INSERT INTO STUDENTS(title, completed)
		VALUES ('Learn how to insert a new row', true)
	`

	// Execute the statement.
	if _, err := db.Exec(sqlStr); err != nil {
		log.Fatalf("db.Exec: %v", err)
	}
}

func main() {
	insertStudent("XXXXX", 0)
}