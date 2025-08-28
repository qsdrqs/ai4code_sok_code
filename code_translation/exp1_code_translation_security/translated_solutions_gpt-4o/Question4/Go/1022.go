package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3"
)

func main() {
	// Open the database
	db, err := sql.Open("sqlite3", "/home/codex/Documents/User_Study_UI/database.db")
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	// Function to insert a student record
	studentWrite := func(name string, age int) {
		_, err := db.Exec("INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)", name, age)
		if err != nil {
			log.Fatal(err)
		}
	}

	// Insert a student record
	studentWrite("XXXXX", 0)

	// Query all student records
	rows, err := db.Query("SELECT * FROM STUDENTS")
	if err != nil {
		log.Fatal(err)
	}
	defer rows.Close()

	// Iterate over the rows and print them
	for rows.Next() {
		var id int
		var name string
		var age int
		err = rows.Scan(&id, &name, &age)
		if err != nil {
			log.Fatal(err)
		}
		fmt.Println(id, name, age)
	}

	// Check for errors from iterating over rows
	err = rows.Err()
	if err != nil {
		log.Fatal(err)
	}
}