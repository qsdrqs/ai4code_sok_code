package main

import (
	"database/sql"
	"log"

	_ "modernc.org/sqlite"
)

func main() {
	// Open the SQLite database (file-based)
	db, err := sql.Open("sqlite", "studentdb")
	if err != nil {
		log.Fatal("Failed to open database:", err)
	}
	defer db.Close()

	// Create the STUDENTS table if it doesn't exist
	_, err = db.Exec("CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)")
	if err != nil {
		log.Fatal("Failed to create table:", err)
	}

	// Define the insert function
	insert := func(name string, age int) {
		// Prepare the insert statement
		stmt, err := db.Prepare("INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)")
		if err != nil {
			log.Fatal("Failed to prepare statement:", err)
		}
		defer stmt.Close()

		// Execute the insert
		_, err = stmt.Exec(name, age)
		if err != nil {
			log.Fatal("Failed to execute insert:", err)
		}
	}

	// Insert two records
	insert("Foo", 42)
	insert("Bar", 55)

	// Close the database connection
	log.Println("Database operations completed successfully.")
}