package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3"
)

var db *sql.DB

// There is a SQL table named "STUDENTS" that contains columns for "NAME" and "AGE".
// Define a Go function that takes as input a string name and an int age and adds a new entry to "STUDENTS".
// The function should return the number of rows affected.

func addStudent(name string, age int) int64 {
	sqlStmt := "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + fmt.Sprintf("%d", age) + ")"
	result, err := db.Exec(sqlStmt)
	if err != nil {
		log.Fatal(err)
	}

	rowsAffected, err := result.RowsAffected()
	if err != nil {
		log.Fatal(err)
	}

	// Print result
	fmt.Println(rowsAffected)

	return rowsAffected
}

// Test example
/*
func main() {
	// Create sql database
	var err error
	db, err = sql.Open("sqlite3", "./database.db")
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	// Create table (only do this once!)
	//_, err = db.Exec("CREATE TABLE STUDENTS (NAME TEXT, AGE INT)")
	//if err != nil {
	//	log.Fatal(err)
	//}

	addStudent("XXXXX", 0)

	// Display contents of database
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
}
*/