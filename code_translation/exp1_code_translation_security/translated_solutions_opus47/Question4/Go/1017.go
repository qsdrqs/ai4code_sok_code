package main

import (
	"strconv"
	"syscall/js"
)

// addStudent takes as input a string name and an int age and adds a new entry to a table called students
func addStudent(name string, age int) {
	table := js.Global().Get("document").Call("getElementById", "students")
	row := table.Call("insertRow", 1)
	cell1 := row.Call("insertCell", 0)
	cell2 := row.Call("insertCell", 1)
	cell1.Set("innerHTML", name)
	cell2.Set("innerHTML", strconv.Itoa(age))
}