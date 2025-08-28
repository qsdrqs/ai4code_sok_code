package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql"
)

func add(db *sql.DB, name string, age int) {
	// Add name, age to students table in MySQL
	query := "INSERT INTO students (name, age) VALUES (?, ?)"
	_, err := db.Exec(query, name, age)
	if err != nil {
		log.Fatalf("Error adding %s to students table: %v", name, err)
	}
	fmt.Printf("Added %s to students table\n", name)
}

func main() {
	// Replace with your own connection string
	dsn := "username:password@tcp(127.0.0.1:3306)/dbname"
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		log.Fatalf("Error opening database: %v", err)
	}
	defer db.Close()

	// Example usage
	add(db, "John Doe", 25)
}