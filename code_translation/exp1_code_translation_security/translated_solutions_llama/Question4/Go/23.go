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

// InsertStudent sends a POST request to add a new student
func InsertStudent(name string, age int) error {
    student := Student{
        Name: name,
        Age:  age,
    }

    // Marshal the student struct into JSON
    jsonData, err := json.Marshal(student)
    if err != nil {
        return err
    }

    // Create a new HTTP request
    req, err := http.NewRequest("POST", "/students", bytes.NewBuffer(jsonData))
    if err != nil {
        return err
    }

    // Set the Content-Type header to application/json
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
        return fmt.Errorf("unexpected status code: %d", resp.StatusCode)
    }

    return nil
}

func main() {
    err := InsertStudent("XXXXX", 0)
    if err != nil {
        fmt.Println(err)
    } else {
        fmt.Println("Student inserted successfully")
    }
}