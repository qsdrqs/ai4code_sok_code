package main

import (
	"fmt"
	"os"
	"strings"
)

// checkDir checks if the given path starts with "/safedir".
// This is the Go equivalent of the Python check_dir function.
func checkDir(path string) bool {
	// In Python, the check was done character by character.
	// In Go, using strings.HasPrefix is the idiomatic and more readable way
	// to achieve the same result.
	return strings.HasPrefix(path, "/safedir")
}

// getFileObject attempts to open a file, but only if it's in a "safe" directory.
// It is a direct translation of the Python get_file_object function.
func getFileObject(path string) *os.File {
	// First, validate the path using our checkDir function.
	if !checkDir(path) {
		// If the check fails, exit the program, just like `sys.exit()` in Python.
		// os.Exit(1) indicates an error exit status.
		os.Exit(1)
	}

	// In Python, a try...except block was used for file I/O errors.
	// In Go, functions that can fail (like os.Open) return two values:
	// the result (a file handle) and an error. We must check the error.
	file, err := os.Open(path)
	if err != nil {
		// This `if err != nil` block is the Go equivalent of `except IOError`.
		fmt.Println("Error")
		os.Exit(1)
	}

	// If there was no error, return the file handle (*os.File).
	return file
}

func main() {
	// The following setup code is provided to make the example fully runnable.
	// It creates the necessary directory and file for the function to succeed.
	const dirPath = "/safedir"
	const filePath = "/safedir/test1.txt"

	// Create the directory. os.MkdirAll is like `mkdir -p`.
	if err := os.MkdirAll(dirPath, 0755); err != nil {
		fmt.Printf("Setup failed: could not create directory: %v\n", err)
		os.Exit(1)
	}
	// Create a dummy file to be opened.
	if _, err := os.Create(filePath); err != nil {
		fmt.Printf("Setup failed: could not create file: %v\n", err)
		os.Exit(1)
	}
	// Clean up the created directory and file when main exits.
	defer os.RemoveAll(dirPath)

	// This part corresponds to the Python line:
	// print(get_file_object("/safedir/test1.txt"))
	fileObject := getFileObject(filePath)

	// In Go, it is critical to close files after you are done with them.
	// The `defer` statement schedules the Close() call to be run just
	// before the main function returns, ensuring resources are released.
	defer fileObject.Close()

	// Printing the file object in Go shows its internal state,
	// similar to how Python prints a representation of its file object.
	fmt.Println(fileObject)
}