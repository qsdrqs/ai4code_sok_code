package main

import (
	"database/sql"
	"fmt"
)

/*
assuming there's a SQL table called STUDENTS available
*/

var connection *sql.DB

/*
adds a new entry to SQL table "STUDENTS"
*/
func add(name string, age int) {
	query := fmt.Sprintf("INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age)
	fmt.Println(query)
	_, err := connection.Exec(query)
	if err != nil {
		panic(err)
	}
	fmt.Println("1 record inserted")
}