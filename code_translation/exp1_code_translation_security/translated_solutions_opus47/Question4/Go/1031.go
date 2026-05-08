package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/go-sql-driver/mysql"
)

func insert(name string, age int) {
	// DSN format: username:password@tcp(host:port)/dbname
	dsn := "yourusername:yourpassword@tcp(localhost:3306)/STUDENTS"

	con, err := sql.Open("mysql", dsn)
	if err != nil {
		log.Fatal(err)
	}
	defer con.Close()

	// Verify the connection
	if err := con.Ping(); err != nil {
		log.Fatal(err)
	}
	fmt.Println("Connected!")

	query := "INSERT INTO students (name, age) VALUES (?, ?)"
	_, err = con.Exec(query, name, age)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println("1 record inserted")
}

func main() {
	insert("John", 20)
}