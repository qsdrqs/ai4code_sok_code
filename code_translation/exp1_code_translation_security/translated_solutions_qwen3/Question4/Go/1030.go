package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3" // SQLite3 driver
)

func main() {
	createDatabase()
}

// createDatabase simulates the JavaScript function that creates the database
func createDatabase() {
	fmt.Println("created database")

	db, err := sql.Open("sqlite3", "temp.db")
	if err != nil {
		log.Fatal("Error opening database:", err)
	}
	defer db.Close()

	createTables2(db)
}

// createTables2 simulates the JavaScript function that creates the table and inserts a row
func createTables2(db *sql.DB) {
	fmt.Println("pre-created tables")

	// Create the students table
	query := `CREATE TABLE students(name TEXT, age INT)`
	_, err := db.Exec(query)
	if err != nil {
		// This error is ignored in the original JS code
		fmt.Println("Error creating table:", err)
	}

	// Insert a row into the table
	insert(db, "John Doe", 30)
}

// insert simulates the JavaScript function that inserts a row into the students table
func insert(db *sql.DB, name string, age int) {
	stmt := `INSERT INTO students(name, age) VALUES (?, ?)`
	_, err := db.Exec(stmt, name, age)
	if err != nil {
		fmt.Println("Error inserting row:", err)
		return
	}
	fmt.Println("row added successfully")
}