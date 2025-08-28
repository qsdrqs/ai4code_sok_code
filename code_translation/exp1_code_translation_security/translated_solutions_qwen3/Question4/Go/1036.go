package main

import (
	"database/sql"
	"fmt"
	_ "github.com/mattn/go-sqlite3"
)

// addNewEntry inserts a new student into the STUDENTS table and prints all entries
func addNewEntry(name string, age int) {
	// Open the SQLite database
	db, err := sql.Open("sqlite3", "database.db")
	if err != nil {
		panic(err)
	}
	defer db.Close()

	// Prepare the insert statement
	stmt, err := db.Prepare("INSERT INTO STUDENTS VALUES (?, ?)")
	if err != nil {
		panic(err)
	}
	defer stmt.Close()

	// Execute the insert
	_, err = stmt.Exec(name, age)
	if err != nil {
		panic(err)
	}

	// Query all students
	rows, err := db.Query("SELECT rowid AS id, NAME, AGE FROM STUDENTS")
	if err != nil {
		panic(err)
	}
	defer rows.Close()

	// Scan and print each row
	var id int
	var studentName string
	var studentAge int
	for rows.Next() {
		err = rows.Scan(&id, &studentName, &studentAge)
		if err != nil {
			panic(err)
		}
		fmt.Printf("%d: %s %d\n", id, studentName, studentAge)
	}
}

// main function to invoke the addNewEntry function
func main() {
	addNewEntry("XXXXX", 0)
}