package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

/*
Function getFile

Input: file path
Returns file as object (*os.File)

If file is outside of directory "/safedir", return nil.
*/
func getFile(filePath string) *os.File {
	// In Go, we use strings.HasPrefix for the startswith check.
	if !strings.HasPrefix(filePath, "/safedir") {
		fmt.Println("File must be inside /safedir")
		// Returning nil is the Go equivalent of returning None in Python.
		return nil
	}

	// Go's idiomatic error handling replaces Python's try/except block.
	// os.Open returns the file and an error value.
	file, err := os.Open(filePath)
	if err != nil {
		// If err is not nil, an error occurred (e.g., file not found).
		fmt.Println("File not found")
		return nil
	}

	// If no error, return the file object.
	return file
}

func main() {
	// Setup: This part is the Go equivalent of the Python code:
	// with open("/safedir/test.txt", "w") as f:
	//     f.write("Hello")
	//
	// NOTE: Creating a directory at the root "/" may require administrator
	// privileges. This code assumes it's run in an environment where this
	// is permitted (like a Docker container or with `sudo`).
	err := os.MkdirAll("/safedir", 0755)
	if err != nil {
		panic(fmt.Sprintf("Failed to create directory /safedir: %v", err))
	}

	// os.WriteFile is a convenient way to write data to a file.
	// It handles opening, writing, and closing the file automatically.
	err = os.WriteFile("/safedir/test.txt", []byte("Hello"), 0644)
	if err != nil {
		panic(fmt.Sprintf("Failed to write to /safedir/test.txt: %v", err))
	}

	// --- Test Case 1: Successful file retrieval ---
	// Python: test_f = get_file("/safedir/test.txt")
	testF := getFile("/safedir/test.txt")

	// In Go, we must explicitly check for nil.
	if testF != nil {
		// `defer` schedules a function call (in this case, Close()) to be run
		// just before the function (main) returns. This is the idiomatic Go
		// way to ensure resources are cleaned up, similar to Python's `with` statement.
		defer testF.Close()

		// Python: print(test_f.readline())
		// Reading a line in Go is often done with a bufio.Scanner.
		scanner := bufio.NewScanner(testF)
		if scanner.Scan() { // .Scan() advances to the next line.
			fmt.Println(scanner.Text()) // .Text() returns the line as a string.
		}
	}

	// --- Test Case 2: Failed file retrieval ---
	// Python: test_f = get_file("/test.txt")  # Should fail
	testF2 := getFile("/test.txt")

	// Python: print(test_f)
	// Printing a nil pointer in Go results in "<nil>", which is the
	// equivalent of Python's "None".
	fmt.Println(testF2)
}