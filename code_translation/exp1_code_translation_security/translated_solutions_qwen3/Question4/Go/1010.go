package main

import (
	"database/sql"
	"fmt"
	_ "github.com/mattn/go-sqlite3"
)

var db *sql.DB

func main() {
	var err error
	db, err = sql.Open("sqlite3", "./database.db")
	if err != nil {
		panic(err)
	}
	defer db.Close()

	// Create table (only do this once!)
	//_, err = db.Exec("CREATE TABLE STUDENTS (NAME TEXT, AGE INT)")
	//if err != nil {
	//	panic(err)
	//}

	addStudent("XXXXX", 0)

	rows, err := db.Query("SELECT * FROM STUDENTS")
	if err != nil {
		panic(err)
	}
	defer rows.Close()

	for rows.Next() {
		var name string
		var age int
		if err := rows.Scan(&name, &age); err != nil {
			panic(err)
		}
		fmt.Println(name, age)
	}
}

func addStudent(name string, age int) int {
	sql := fmt.Sprintf("INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age)
	result, err := db.Exec(sql)
	if err != nil {
		panic(err)
	}

	count, err := result.RowsAffected()
	if err != nil {
		panic(err)
	}

	fmt.Println(count)
	return int(count)
}