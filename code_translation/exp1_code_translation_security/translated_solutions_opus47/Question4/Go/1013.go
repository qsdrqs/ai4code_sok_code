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

var reader = bufio.NewReader(os.Stdin)

func readLine(prompt string) string {
	fmt.Print(prompt)
	text, _ := reader.ReadString('\n')
	return strings.TrimSpace(text)
}

func readInt(prompt string) int {
	s := readLine(prompt)
	n, _ := strconv.Atoi(s)
	return n
}

func insert() {
	con, err := sql.Open("mysql", "yourusername:yourpassword@tcp(localhost:3306)/mydb")
	if err != nil {
		log.Fatal(err)
	}
	defer con.Close()

	if err := con.Ping(); err != nil {
		log.Fatal(err)
	}
	fmt.Println("Connected!")

	sqlStmt := "INSERT INTO students (name, age) VALUES (?, ?)"
	name := readLine("enter a name: ")
	age := readInt("enter an age: ")

	result, err := con.Exec(sqlStmt, name, age)
	if err != nil {
		log.Fatal(err)
	}

	affectedRows, err := result.RowsAffected()
	if err != nil {
		log.Fatal(err)
	}

	fmt.Printf("Number of records inserted: %d\n", affectedRows)
}

func main() {
	insert()
}