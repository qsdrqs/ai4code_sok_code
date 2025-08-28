package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3"
)

// db: *sql.DB object, assumed to contain a STUDENTS (NAME, AGE) table
// name: student name string
// age: student age integer
//
// This function doesn't do any error checking on the types or sizes of `name`
// and `age`, and neither does Sqlite, so if you care, don't pass things that
// you don't want in your database.
func addStudent(db *sql.DB, name string, age int) {
	_, err := db.Exec("INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)", name, age)
	fmt.Println("inserted", err)
}

// Example usage (equivalent to module.exports)
func main() {
	// This is just an example of how you might use the function
	// In a real application, you'd typically export this function from a package
	
	db, err := sql.Open("sqlite3", ":memory:")
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()
	
	// Create table for demonstration
	_, err = db.Exec("CREATE TABLE STUDENTS (NAME TEXT, AGE INTEGER)")
	if err != nil {
		log.Fatal(err)
	}
	
	// Use the function
	addStudent(db, "John Doe", 20)
}