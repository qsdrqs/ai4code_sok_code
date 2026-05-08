package main

import (
	"database/sql"
	"log"

	_ "github.com/mattn/go-sqlite3"
)

var db *sql.DB

func insert(name string, age int) {
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
		rows, err := db.Query("SELECT * FROM STUDENTS")
		if err != nil {
			log.Println(err)
			return
		}
		defer rows.Close()

		for rows.Next() {
			var name string
			var age int
			if err := rows.Scan(&name, &age); err != nil {
				log.Println(err)
				return
			}
			fmt.Println(name, age)
		}
	*/
}

func main() {
	var err error
	db, err = sql.Open("sqlite3", "studentdb")
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	_, err = db.Exec("CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)")
	if err != nil {
		log.Fatal(err)
	}

	insert("Foo", 42)
	insert("Bar", 55)
}