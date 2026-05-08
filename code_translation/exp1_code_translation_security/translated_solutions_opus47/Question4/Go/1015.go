package main

import (
	"database/sql"
	"fmt"

	_ "github.com/go-sql-driver/mysql"
)

func addStudent(name string, age int) {
	// Create database connection (equivalent to mysql.createConnection)
	db, err := sql.Open("mysql", "yourusername:yourpassword@tcp(localhost:3306)/mydb")
	if err != nil {
		panic(err)
	}
	defer db.Close()

	// Verify the connection (equivalent to con.connect)
	err = db.Ping()
	if err != nil {
		panic(err)
	}
	fmt.Println("Connected!")

	// Execute the INSERT query (equivalent to con.query)
	sqlQuery := "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)"
	_, err = db.Exec(sqlQuery, name, age)
	if err != nil {
		panic(err)
	}
	fmt.Println("1 record inserted")
}