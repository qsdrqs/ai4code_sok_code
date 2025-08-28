package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql" // MySQL driver
)

// AddStudent inserts one record into the STUDENTS table.
func AddStudent(name string, age int) error {
	// user:password@tcp(host:port)/dbname
	dsn := "yourusername:yourpassword@tcp(localhost:3306)/mydb"

	db, err := sql.Open("mysql", dsn)
	if err != nil {
		return fmt.Errorf("open DB: %w", err)
	}
	defer db.Close()

	// Make sure the connection is actually alive.
	if err := db.Ping(); err != nil {
		return fmt.Errorf("ping DB: %w", err)
	}

	stmt, err := db.Prepare(`INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)`)
	if err != nil {
		return fmt.Errorf("prepare stmt: %w", err)
	}
	defer stmt.Close()

	if _, err := stmt.Exec(name, age); err != nil {
		return fmt.Errorf("exec stmt: %w", err)
	}

	fmt.Println("1 record inserted")
	return nil
}

func main() {
	if err := AddStudent("Alice", 21); err != nil {
		log.Fatalf("add student failed: %v", err)
	}
}