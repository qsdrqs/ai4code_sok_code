package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3" // SQLite driver
)

func main() {
	// Open the SQLite database
	db, err := sql.Open("sqlite3", "/home/codex/Documents/User_Study_UI/database.db")
	if err != nil {
		log.Fatal("Failed to open database:", err)
	}
	defer db.Close()

	// Insert a new student
	studentWrite(db, "XXXXX", 0)

	// Query all students from the database
	rows, err := db.Query("SELECT * FROM STUDENTS")
	if err != nil {
		log.Fatal("Failed to query database:", err)
	}
	defer rows.Close()

	// Iterate over the rows and print each student
	for rows.Next() {
		var name string
		var age int
		if err := rows.Scan(&name, &age); err != nil {
			log.Fatal("Failed to scan row:", err)
		}
		fmt.Println(name, age)
	}
}

// studentWrite inserts a new student into the STUDENTS table
func studentWrite(db *sql.DB, name string, age int) {
	// Execute the insert statement with parameterized values
	_, err := db.Exec("INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)", name, age)
	if err != nil {
		log.Fatal("Failed to insert student:", err)
	}
}