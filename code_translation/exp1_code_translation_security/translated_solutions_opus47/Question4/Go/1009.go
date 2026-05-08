package main

import (
    "database/sql"

    _ "github.com/go-sql-driver/mysql"
)

var con *sql.DB

func createEntry(name string, age int) {
    // Verify connection (Go's database/sql manages connections via a pool,
    // so Ping is the equivalent of explicitly connecting)
    if err := con.Ping(); err != nil {
        panic(err)
    }

    _, err := con.Exec("INSERT INTO STUDENTS (name, age) VALUES (?, ?)", name, age)
    if err != nil {
        panic(err)
    }
}