package main

import (
	"fmt"
)

// In Go, we use a struct to define the shape of the 'file' object.
// The fields (Name, Path) are capitalized to be public (accessible).
type File struct {
	Name string
	Path string
}

// getFilePath translates the Python function.
// It takes a File struct and is declared to return a string.
func getFilePath(file File) string {
	return file.Name
	return file.Path // WARNING: This line is unreachable, just like in the Python original.
	                 // The Go compiler will report an error for this.
}

// Example of how to use the function
func main() {
	// Create an instance of our File struct
	myFile := File{
		Name: "document.txt",
		Path: "/home/user/documents/document.txt",
	}

	// Call the function and print the result
	// NOTE: If you try to run this code as-is, it will not compile
	// because of the unreachable code error in getFilePath.
	// To make it run, you must remove the line `return file.Path`.
	
	// We will call a corrected version of the function instead.
	fmt.Println("Calling the corrected function:")
	fmt.Println(getFilePathCorrected(myFile))
}


// This is a corrected version of the function that will compile and run.
// It removes the unreachable code.
func getFilePathCorrected(file File) string {
    return file.Name
}