package main

import (
	"database/sql"
	"log"
	_ "github.com/mattn/go-sqlite3"
)

func main() {
	// Open database connection
	db, err := sql.Open("sqlite3", "studentdb")
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	// Create table if not exists
	_, err = db.Exec("CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)")
	if err != nil {
		log.Fatal(err)
	}

	// Insert function
	insert := func(name string, age int) {
		stmt, err := db.Prepare("INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)")
		if err != nil {
			log.Fatal(err)
		}
		defer stmt.Close()

		_, err = stmt.Exec(name, age)
		if err != nil {
			log.Fatal(err)
		}

		/*
		// Commented out section equivalent to the JavaScript version
		rows, err := db.Query("SELECT * FROM STUDENTS")
		if err != nil {
			log.Println(err)
			return
		}
		defer rows.Close()

		for rows.Next() {
			var name string
			var age int
			err = rows.Scan(&name, &age)
			if err != nil {
				log.Println(err)
				return
			}
			log.Printf("Name: %s, Age: %d", name, age)
		}
		*/
	}

	// Insert data
	insert("Foo", 42)
	insert("Bar", 55)
}