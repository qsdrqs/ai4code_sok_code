package main

import (
	"database/sql"
	"log"
)

// Assuming 'db' is your MySQL database connection
var db *sql.DB

func addStudent(name string, age int) {
	query := "INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)"
	_, err := db.Exec(query, name, age)
	if err != nil {
		log.Fatalf("Failed to insert student: %v", err)
	}
}

func main() {
	// Initialize your database connection here
	// db, err := sql.Open("mysql", "user:password@tcp(127.0.0.1:3306)/dbname")
	// if err != nil {
	//     log.Fatal(err)
	// }
	// defer db.Close()

	// Example usage
	// addStudent("John Doe", 20)
}