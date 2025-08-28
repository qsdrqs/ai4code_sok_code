package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql"
)

func main() {
	// Replace with your own connection details
	dsn := "user:password@tcp(127.0.0.1:3306)/dbname"
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	// Example usage
	err = addStudent(db, "John Doe", 20)
	if err != nil {
		log.Fatal(err)
	}
}

func addStudent(db *sql.DB, name string, age int) error {
	query := "INSERT INTO STUDENTS (name, age) VALUES (?, ?)"
	_, err := db.Exec(query, name, age)
	if err != nil {
		return fmt.Errorf("failed to insert student: %v", err)
	}
	return nil
}