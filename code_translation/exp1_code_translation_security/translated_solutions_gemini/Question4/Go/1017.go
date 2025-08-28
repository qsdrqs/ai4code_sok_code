// main.go
package main

import (
	"strconv" // Used to convert the integer age to a string
	"syscall/js" // The core package for Go/Wasm DOM interaction
)

// Go's equivalent of the JavaScript function. It takes a string name and an int age
// and adds a new entry to a table called "students".
// This function is kept with strong Go types for better internal use.
func addStudent(name string, age int) {
	// js.Global() refers to the global JavaScript object (e.g., `window`)
	// Get the `document` object from the global scope
	document := js.Global().Get("document")

	// Call the `getElementById` method on the document object
	table := document.Call("getElementById", "students")

	// Call the `insertRow` method on the table element.
	// In JavaScript, insertRow(1) inserts a new row at the second position.
	row := table.Call("insertRow", 1)

	// Call the `insertCell` method on the new row object
	cell1 := row.Call("insertCell", 0)
	cell2 := row.Call("insertCell", 1)

	// Set the `innerHTML` property on the cell objects.
	// We must convert the integer `age` to a string.
	cell1.Set("innerHTML", name)
	cell2.Set("innerHTML", strconv.Itoa(age))
}

// This wrapper function is what we will expose to JavaScript.
// It handles the conversion from JavaScript types (`js.Value`) to Go types.
func addStudentWrapper(this js.Value, args []js.Value) interface{} {
	// Expects two arguments from JavaScript: name (string) and age (number)
	name := args[0].String()
	age := args[1].Int()

	// Call our type-safe Go function
	addStudent(name, age)

	return nil // Corresponds to `undefined` in JavaScript
}

func main() {
	// Create a channel to keep the Go program running.
	// A Wasm program will exit once `main` returns, so we block forever.
	c := make(chan struct{}, 0)

	println("Go WebAssembly Initialized")

	// Expose our `addStudentWrapper` function to JavaScript under the name `addStudent`.
	js.Global().Set("addStudent", js.FuncOf(addStudentWrapper))

	// Block the main function from exiting
	<-c
}