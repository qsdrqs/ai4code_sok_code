package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3" // Import the SQLite driver
)

// addStudent inserts a new student into the STUDENTS table.
// db: *sql.DB object, assumed to contain a STUDENTS (NAME, AGE) table
// name: student name string
// age: student age integer
//
// This function doesn't do any error checking on the types or sizes of `name`
// and `age`, and neither does SQLite, so if you care, don't pass things that
// you don't want in your database.
func addStudent(db *sql.DB, name string, age int) {
	stmt, err := db.Prepare("INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)")
	if err != nil {
		log.Fatal(err)
	}
	defer stmt.Close()

	_, err = stmt.Exec(name, age)
	if err != nil {
		log.Println("inserted", err)
	} else {
		fmt.Println("inserted successfully")
	}
}

func main() {
	// Example usage
	db, err := sql.Open("sqlite3", "./students.db")
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	addStudent(db, "John Doe", 20)
}