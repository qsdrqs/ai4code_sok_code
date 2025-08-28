package main

import (
    "bytes"
    "encoding/json"
    "fmt"
    "net/http"
)

// Student represents a student with a name and age
type Student struct {
    Name string `json:"name"`
    Age  int    `json:"age"`
}

// addStudent adds a new student to the STUDENTS table in the SQL database
func addStudent(name string, age int) error {
    // Create a new student
    student := Student{
        Name: name,
        Age:  age,
    }

    // Marshal the student to JSON
    jsonData, err := json.Marshal(student)
    if err != nil {
        return err
    }

    // Create a new HTTP request
    req, err := http.NewRequest("POST", "http://localhost:8080/sql", bytes.NewBuffer(jsonData))
    if err != nil {
        return err
    }

    // Set the request headers
    req.Header.Set("Content-Type", "application/json")

    // Send the request
    client := &http.Client{}
    resp, err := client.Do(req)
    if err != nil {
        return err
    }
    defer resp.Body.Close()

    // Check the response status code
    if resp.StatusCode != http.StatusOK {
        return fmt.Errorf("failed to add student: %d", resp.StatusCode)
    }

    return nil
}

func main() {
    // Example usage
    name := "John Doe"
    age := 20
    err := addStudent(name, age)
    if err != nil {
        fmt.Println(err)
    } else {
        fmt.Printf("Student '%s' added successfully\n", name)
    }
}