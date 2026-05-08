package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql"
)

//import "database/sql"
//import _ "github.com/go-sql-driver/mysql"
//create config and connection to your respective database then use the function
//connection, err := sql.Open("mysql", "user:password@tcp(127.0.0.1:3306)/dbname")

var connection *sql.DB

func insertInto(name string, age int) {
	sqlStr := fmt.Sprintf(`INSERT INTO STUDENTS(NAME,AGE)
            VALUES (%s, %d)`, name, age)
	_, err := connection.Exec(sqlStr)
	if err != nil {
		log.Fatal(err)
	}
	connection.Close()
}