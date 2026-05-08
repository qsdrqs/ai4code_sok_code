package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql"
)

func inserter(name string, age int) {
	// Create the database connection
	// DSN format: username:password@tcp(host:port)/dbname
	db, err := sql.Open("mysql", "yourusername:yourpassword@tcp(localhost:3306)/mydb")
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	// Verify the connection is alive
	err = db.Ping()
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println("Connected!")

	// Execute the INSERT statement using parameterized query
	sqlStatement := "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
	_, err = db.Exec(sqlStatement, name, age)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println("1 record inserted")
}

func main() {
	inserter("John", 20)
}