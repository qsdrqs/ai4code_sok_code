package main

import (
	"bufio"
	"database/sql"
	"fmt"
	"log"
	"os"
	"strconv"
	"strings"

	_ "github.com/go-sql-driver/mysql"
)

func main() {
	insert()
}

func insert() {
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
	sqlStatement := "INSERT INTO students (name, age) VALUES (?, ?)"

	// Read user input
	reader := bufio.NewReader(os.Stdin)

	fmt.Print("Enter a name: ")
	name, _ := reader.ReadString('\n')
	name = strings.TrimSpace(name)

	fmt.Print("Enter an age: ")
	ageStr, _ := reader.ReadString('\n')
	ageStr = strings.TrimSpace(ageStr)
	age, err := strconv.Atoi(ageStr)
	if err != nil {
		log.Fatal(err)
	}

	// Execute the SQL statement
	result, err := db.Exec(sqlStatement, name, age)
	if err != nil {
		log.Fatal(err)
	}

	// Get the number of rows affected
	rowsAffected, err := result.RowsAffected()
	if err != nil {
		log.Fatal(err)
	}
	fmt.Printf("Number of records inserted: %d\n", rowsAffected)
}