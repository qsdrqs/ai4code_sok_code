package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3"
)

// addStudent takes a name and age, and inserts a new entry into the STUDENTS table.
// It returns the number of rows affected.
func addStudent(db *sql.DB, name string, age int) (int64, error) {
	sqlStmt := "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
	result, err := db.Exec(sqlStmt, name, age)
	if err != nil {
		return 0, err
	}

	rowsAffected, err := result.RowsAffected()
	if err != nil {
		return 0, err
	}

	return rowsAffected, nil
}

func main() {
	// Open the database
	db, err := sql.Open("sqlite3", "./database.db")
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	// Create table (only do this once!)
	// _, err = db.Exec("CREATE TABLE STUDENTS (NAME TEXT, AGE INT)")
	// if err != nil {
	// 	log.Fatalf("%q: %s\n", err, "Failed to create table")
	// }

	// Add a student
	rowsAffected, err := addStudent(db, "XXXXX", 0)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Printf("Rows affected: %d\n", rowsAffected)

	// Display contents of the database
	rows, err := db.Query("SELECT NAME, AGE FROM STUDENTS")
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

	err = rows.Err()
	if err != nil {
		log.Fatal(err)
	}
}