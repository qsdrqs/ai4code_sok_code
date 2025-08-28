package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql"
)

func addStudent(name string, age int) {
	// Define the data source name (DSN)
	dsn := "yourusername:yourpassword@tcp(localhost:3306)/mydb"

	// Open a connection to the database
	db, err := sql.Open("mysql", dsn)
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()

	// Test the connection
	err = db.Ping()
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println("Connected!")

	// Prepare the SQL statement
	stmt, err := db.Prepare("INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)")
	if err != nil {
		log.Fatal(err)
	}
	defer stmt.Close()

	// Execute the SQL statement
	_, err = stmt.Exec(name, age)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println("1 record inserted")
}

func main() {
	addStudent("John Doe", 20)
}