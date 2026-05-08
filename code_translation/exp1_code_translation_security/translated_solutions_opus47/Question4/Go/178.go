package main

import "fmt"

type Student struct {
    Name string
    Age  int
}

func registerStudent(name string, age int) (*Result, error) {
    student := Student{
        Name: name,
        Age:  age,
    }

    connection := NewConnection() // create connection here
    query := fmt.Sprintf("INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', '%d')", student.Name, student.Age)
    return connection.Query(query)
}