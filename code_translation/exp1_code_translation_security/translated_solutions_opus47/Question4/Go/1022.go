package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/mattn/go-sqlite3"
)

var db *sql.DB

func student_write(name string, age int) {
	_, err := db.Exec("INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)", name, age)
	if err != nil {
		log.Println(err)
	}
}

func main() {
	var err error
	db, err = sql.Open("sqlite3", "/home/codex/Documents/User_Study_UI/database.db")
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	student_write("XXXXX", 0)

	rows, err := db.Query("SELECT * FROM STUDENTS")
	if err != nil {
		log.Fatal(err)
	}
	defer rows.Close()

	columns, err := rows.Columns()
	if err != nil {
		log.Fatal(err)
	}

	for rows.Next() {
		values := make([]interface{}, len(columns))
		valuePtrs := make([]interface{}, len(columns))
		for i := range values {
			valuePtrs[i] = &values[i]
		}
		if err := rows.Scan(valuePtrs...); err != nil {
			log.Fatal(err)
		}
		row := make(map[string]interface{})
		for i, col := range columns {
			row[col] = values[i]
		}
		fmt.Println(row, row)
	}
}