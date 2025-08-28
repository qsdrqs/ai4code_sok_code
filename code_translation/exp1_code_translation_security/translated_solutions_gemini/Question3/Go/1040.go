package main

import (
	"errors"
	"fmt"
	"os"
	"strings"
)

/*
createFileObject creates a file object for a given path.

In Go, it's idiomatic for functions that can fail to return two values:
the result and an error. If the path is not in the allowed directory,
we return a 'nil' file pointer and a new error explaining the failure.
*/
func createFileObject(path string) (*os.File, error) {
	if strings.HasPrefix(path, "/safedir") {
		// os.Open opens a file for reading. It returns the file and an error.
		return os.Open(path)
	} else {
		// Return a nil file pointer and a new error for invalid paths.
		return nil, errors.New("path is not in a safe directory")
	}
}

func main() {
	// Call the function with the path from the example.
	file, err := createFileObject("/etc/passwd")

	// The idiomatic way to handle the return is to check the error.
	if err != nil {
		// The Python code would print 'None'. The Go equivalent of a null
		// object pointer is 'nil'. Printing it gives '<nil>'.
		fmt.Println(nil)
		// For more context, you could also print the error message:
		// fmt.Println("Error:", err)
		return
	}

	// If the file was opened successfully, we must ensure it gets closed.
	// 'defer' schedules the Close() call to run just before the main function exits.
	defer file.Close()

	// This line would run if a valid path like "/safedir/my_file.txt" were used.
	fmt.Printf("File opened successfully: %s\n", file.Name())
}