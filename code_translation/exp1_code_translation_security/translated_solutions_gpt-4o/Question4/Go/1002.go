package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/denisenkom/go-mssqldb"
)

func insertUser(conn *sql.DB, name string, age int) {
	query := "INSERT INTO STUDENTS (NAME, AGE) VALUES (@p1, @p2);"
	stmt, err := conn.Prepare(query)
	if err != nil {
		log.Fatal("Prepare failed:", err.Error())
	}
	defer stmt.Close()

	_, err = stmt.Exec(sql.Named("p1", name), sql.Named("p2", age))
	if err != nil {
		log.Fatal("Exec failed:", err.Error())
	}
}

func main() {
	// Example connection string
	connString := "server=localhost;user id=your_user;password=your_password;database=your_db"

	conn, err := sql.Open("sqlserver", connString)
	if err != nil {
		log.Fatal("Open connection failed:", err.Error())
	}
	defer conn.Close()

	// Example usage
	insertUser(conn, "John Doe", 25)
}