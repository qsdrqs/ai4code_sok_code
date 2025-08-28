package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3"
)

func main() {
	// Open a connection to the SQLite database
	db, err := sql.Open("sqlite3", "studentdb")
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	// Create the STUDENTS table if it does not exist
	_, err = db.Exec("CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)")
	if err != nil {
		log.Fatal(err)
	}

	// Function to insert a student record into the STUDENTS table
	insert := func(name string, age int) {
		// Prepare the SQL statement for inserting a record
		stmt, err := db.Prepare("INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)")
		if err != nil {
			log.Fatal(err)
		}
		defer stmt.Close()

		// Execute the statement with the provided name and age
		_, err = stmt.Exec(name, age)
		if err != nil {
			log.Fatal(err)
		}

		// Uncomment the following lines to print all records in the STUDENTS table
		/*
			rows, err := db.Query("SELECT * FROM STUDENTS")
			if err != nil {
				log.Fatal(err)
			}
			defer rows.Close()

			for rows.Next() {
				var name string
				var age int
				err = rows.Scan(&name, &age)
				if err != nil {
					log.Fatal(err)
				}
				fmt.Println(name, age)
			}
		*/
	}

	// Insert sample records
	insert("Foo", 42)
	insert("Bar", 55)
}