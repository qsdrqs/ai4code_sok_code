package main

import (
	"database/sql"
	"log"

	_ "github.com/go-sql-driver/mysql"
)

func insertion(name string, age int) {
	// Create connection - DSN format: username:password@tcp(host:port)/dbname
	con, err := sql.Open("mysql", "user:password@tcp(127.0.0.1:3306)/dbname")
	if err != nil {
		log.Fatal(err)
	}
	defer con.Close()

	// Verify the connection is alive (equivalent to con.connect())
	if err = con.Ping(); err != nil {
		log.Fatal(err)
	}

	// Prepare and execute the insert statement using parameterized query
	query := "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
	_, err = con.Exec(query, name, age)
	if err != nil {
		log.Fatal(err)
	}
}

func main() {
	insertion("John", 20)
}