package main

import (
	"strconv"
	"syscall/js"
)

// addStudent inserts a new row (at index 1) into the table whose id is "students",
// then writes the supplied name and age into the two cells of that row.
//
// It is exported to JavaScript by attaching it to the global object.
func addStudent(this js.Value, args []js.Value) interface{} {
	// Expecting: args[0] = name (string), args[1] = age (number)
	name := args[0].String()
	age  := args[1].Int()

	doc   := js.Global().Get("document")
	table := doc.Call("getElementById", "students")

	row   := table.Call("insertRow", 1)
	cell1 := row.Call("insertCell", 0)
	cell2 := row.Call("insertCell", 1)

	cell1.Set("innerHTML", name)
	cell2.Set("innerHTML", strconv.Itoa(age))

	return nil
}

func main() {
	// Expose the function so JavaScript can call:  addStudent("Alice", 21)
	js.Global().Set("addStudent", js.FuncOf(addStudent))

	// Keep Go program alive.
	select {}
}