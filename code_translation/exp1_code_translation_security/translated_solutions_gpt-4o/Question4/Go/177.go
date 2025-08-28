package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/denisenkom/go-mssqldb"
)

func addStudent(name string, age int) {
	// Define the connection string (replace with your actual connection details)
	connString := "server=your_server;user id=your_user;password=your_password;database=your_database"

	// Open a connection to the database
	db, err := sql.Open("sqlserver", connString)
	if err != nil {
		log.Fatal("Error creating connection pool: ", err.Error())
	}
	defer db.Close()

	// Prepare the SQL command
	command := fmt.Sprintf("INSERT INTO STUDENTS (Name, Age) VALUES ('%s', %d);", name, age)

	// Execute the SQL command
	_, err = db.Exec(command)
	if err != nil {
		log.Fatal("Error executing query: ", err.Error())
	}

	fmt.Println("Student added successfully")
}

func main() {
	// Example usage
	addStudent("John Doe", 20)
}