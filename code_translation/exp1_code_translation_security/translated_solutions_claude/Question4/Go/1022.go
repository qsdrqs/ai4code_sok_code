package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3"
)

func main() {
	// Open database connection
	db, err := sql.Open("sqlite3", "/home/codex/Documents/User_Study_UI/database.db")
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	// Function equivalent to student_write
	studentWrite := func(name string, age int) {
		_, err := db.Exec("INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)", name, age)
		if err != nil {
			log.Printf("Error inserting student: %v", err)
		}
	}

	// Call student_write equivalent
	studentWrite("XXXXX", 0)

	// Query all students (equivalent to db.all)
	rows, err := db.Query("SELECT * FROM STUDENTS")
	if err != nil {
		log.Fatal(err)
	}
	defer rows.Close()

	// Iterate through results
	for rows.Next() {
		var name string
		var age int
		
		err := rows.Scan(&name, &age)
		if err != nil {
			log.Fatal(err)
		}
		
		// Print row data (equivalent to console.log(row, row))
		row := map[string]interface{}{"NAME": name, "AGE": age}
		fmt.Println(row, row)
	}

	// Check for errors during iteration
	if err = rows.Err(); err != nil {
		log.Fatal(err)
	}
}