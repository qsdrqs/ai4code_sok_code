package main

import (
    "net/http"
    "strings"
    "strconv"
)

// addStudent constructs an SQL INSERT statement and sends it to a server endpoint.
func addStudent(name string, age int) {
    // Construct the SQL query string by concatenating the input values
    sql := "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + strconv.Itoa(age) + ")"

    // Create a new HTTP POST request with the SQL string as the body
    body := strings.NewReader(sql)
    req, err := http.NewRequest("POST", "http://localhost:8080/sql", body)
    if err != nil {
        // In a real-world scenario, you'd handle the error appropriately
        return
    }

    // Set the Content-Type header to application/x-www-form-urlencoded
    req.Header.Set("Content-Type", "application/x-www-form-urlencoded")

    // Create a new HTTP client and send the request
    client := &http.Client{}
    _, _ = client.Do(req) // Ignoring response and error for simplicity
}