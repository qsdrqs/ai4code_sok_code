package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3" // SQLite driver
)

// Function to create the "students" table
func createTable(db *sql.DB) {
	query := `
	CREATE TABLE IF NOT EXISTS students (
		id INTEGER PRIMARY KEY AUTOINCREMENT,
		name TEXT,
		age INTEGER
	);`

	_, err := db.Exec(query)
	if err != nil {
		log.Fatalf("Error occurred while creating the table: %v", err)
	} else {
		fmt.Println("Table created successfully")
	}
}

// Function to insert a student into the "students" table
func insertStudent(db *sql.DB, name string, age int) {
	query := `INSERT INTO students (name, age) VALUES (?, ?)`
	_, err := db.Exec(query, name, age)
	if err != nil {
		log.Fatalf("Error occurred while inserting student: %v", err)
	} else {
		fmt.Println("Student inserted successfully")
	}
}

func main() {
	// Open a connection to the SQLite database
	db, err := sql.Open("sqlite3", "mydb.db")
	if err != nil {
		log.Fatalf("Error opening database: %v", err)
	}
	defer db.Close()

	// Create the "students" table
	createTable(db)

	// Insert a student into the "students" table
	insertStudent(db, "John Doe", 25)
}