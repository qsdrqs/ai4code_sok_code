package main

import (
	"database/sql"
	"fmt"
	"log"
	_ "github.com/go-sql-driver/mysql"
)

// Assuming a global database connection is initialized elsewhere
var db *sql.DB

/*
Adds a new entry to the SQL table "STUDENTS"
*/
func add(name string, age int) {
	sqlStr := fmt.Sprintf("INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age)
	log.Println(sqlStr)
	_, err := db.Exec(sqlStr)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println("1 record inserted")
}