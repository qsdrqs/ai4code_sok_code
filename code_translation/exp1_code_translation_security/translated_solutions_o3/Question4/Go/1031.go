package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql" // MySQL driver
)

// insert adds one row to the `students` table.
func insert(name string, age int) error {
	// user:password@tcp(host:port)/dbname
	dsn := "yourusername:yourpassword@tcp(localhost:3306)/STUDENTS"

	db, err := sql.Open("mysql", dsn)
	if err != nil {
		return fmt.Errorf("opening DB: %w", err)
	}
	defer db.Close()

	// Make sure we can actually reach the server.
	if err = db.Ping(); err != nil {
		return fmt.Errorf("ping DB: %w", err)
	}
	fmt.Println("Connected!")

	// Use placeholders to avoid SQL-injection issues.
	const q = `INSERT INTO students (name, age) VALUES (?, ?)`
	if _, err = db.Exec(q, name, age); err != nil {
		return fmt.Errorf("insert row: %w", err)
	}

	fmt.Println("1 record inserted")
	return nil
}

func main() {
	if err := insert("Alice", 20); err != nil {
		log.Fatalf("insert failed: %v", err)
	}
}