package main

import (
	"bufio"
	"database/sql"
	"fmt"
	"log"
	"os"
	"strconv"
	"strings"

	_ "github.com/go-sql-driver/mysql" // MySQL driver
)

func insert() {
	// ----------------------------------------------------------------
	// 1. Open a connection to the database.
	//    Format: <user>:<password>@tcp(<host>)/<database>
	// ----------------------------------------------------------------
	dsn := fmt.Sprintf("%s:%s@tcp(%s)/%s",
		"yourusername",
		"yourpassword",
		"localhost",
		"mydb",
	)

	db, err := sql.Open("mysql", dsn)
	if err != nil {
		log.Fatalf("opening DB: %v", err)
	}
	defer db.Close()

	// Ensure we can actually connect.
	if err := db.Ping(); err != nil {
		log.Fatalf("connecting to DB: %v", err)
	}
	fmt.Println("Connected!")

	// ----------------------------------------------------------------
	// 2. Read input from the user.
	// ----------------------------------------------------------------
	reader := bufio.NewReader(os.Stdin)

	fmt.Print("enter a name: ")
	name, _ := reader.ReadString('\n')
	name = strings.TrimSpace(name)

	fmt.Print("enter an age: ")
	ageStr, _ := reader.ReadString('\n')
	ageStr = strings.TrimSpace(ageStr)

	age, err := strconv.Atoi(ageStr)
	if err != nil {
		log.Fatalf("invalid age: %v", err)
	}

	// ----------------------------------------------------------------
	// 3. Execute the INSERT statement.
	// ----------------------------------------------------------------
	const sqlStmt = "INSERT INTO students (name, age) VALUES (?, ?)"

	res, err := db.Exec(sqlStmt, name, age)
	if err != nil {
		log.Fatalf("executing INSERT: %v", err)
	}

	affected, err := res.RowsAffected()
	if err != nil {
		log.Fatalf("getting affected rows: %v", err)
	}

	fmt.Printf("Number of records inserted: %d\n", affected)
}

func main() {
	insert()
}