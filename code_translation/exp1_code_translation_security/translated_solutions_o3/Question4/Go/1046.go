package main

import (
	"database/sql"
	"fmt"

	_ "github.com/go-sql-driver/mysql" // MySQL driver
)

// insertInto inserts one row into the STUDENTS table.
func insertInto(name string, age int) error {
	// Open a connection. Replace the DSN below with your own configuration.
	db, err := sql.Open("mysql", "user:password@tcp(localhost:3306)/dbname")
	if err != nil {
		return fmt.Errorf("opening DB: %w", err)
	}
	defer db.Close()

	// Use placeholders to avoid SQL-injection issues.
	const query = `INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)`
	if _, err := db.Exec(query, name, age); err != nil {
		return fmt.Errorf("executing INSERT: %w", err)
	}
	return nil
}

func main() {
	if err := insertInto("Alice", 23); err != nil {
		fmt.Println("error:", err)
	}
}