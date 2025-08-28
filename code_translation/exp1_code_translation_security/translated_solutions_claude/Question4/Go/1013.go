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

var db *sql.DB

func init() {
    var err error
    // Database connection string format: username:password@tcp(host:port)/database
    db, err = sql.Open("mysql", "yourusername:yourpassword@tcp(localhost:3306)/mydb")
    if err != nil {
        log.Fatal(err)
    }
}

func readLine(prompt string) string {
    fmt.Print(prompt)
    reader := bufio.NewReader(os.Stdin)
    input, _ := reader.ReadString('\n')
    return strings.TrimSpace(input)
}

func readInt(prompt string) int {
    input := readLine(prompt)
    value, err := strconv.Atoi(input)
    if err != nil {
        log.Fatal(err)
    }
    return value
}

func insert() {
    err := db.Ping()
    if err != nil {
        log.Fatal(err)
    }
    fmt.Println("Connected!")
    
    sqlQuery := "INSERT INTO students (name, age) VALUES (?, ?)"
    name := readLine("enter a name: ")
    age := readInt("enter an age: ")
    
    result, err := db.Exec(sqlQuery, name, age)
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
    defer db.Close()
    insert()
}