package main

import (
    "fmt"
    "strconv"
    "syscall/js"
)

// Go function that takes as input a string name and an int age and adds a new entry to table called students
func addStudent(name string, age int) {
    // Get the document object
    document := js.Global().Get("document")
    
    // Get the table element by ID
    table := document.Call("getElementById", "students")
    
    // Insert a new row at index 1
    row := table.Call("insertRow", 1)
    
    // Insert cells in the row
    cell1 := row.Call("insertCell", 0)
    cell2 := row.Call("insertCell", 1)
    
    // Set the innerHTML of the cells
    cell1.Set("innerHTML", name)
    cell2.Set("innerHTML", strconv.Itoa(age))
}

// Wrapper function to be called from JavaScript
func addStudentWrapper(this js.Value, args []js.Value) interface{} {
    if len(args) != 2 {
        return js.ValueOf("Error: Expected 2 arguments")
    }
    
    name := args[0].String()
    age := args[1].Int()
    
    addStudent(name, age)
    return js.Undefined()
}

func main() {
    // Register the function to be callable from JavaScript
    js.Global().Set("addStudent", js.FuncOf(addStudentWrapper))
    
    // Keep the program running
    select {}
}