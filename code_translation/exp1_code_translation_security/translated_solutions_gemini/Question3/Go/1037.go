package main

import (
	"errors"
	"fmt"
	"os"
	"strings"
)

// validateDir checks if the given directory path starts with '/safedir'.
// It's the Go equivalent of the Python `validateDir` function.
func validateDir(dir string) bool {
	// strings.HasPrefix is the Go equivalent of Python's str.startswith()
	return strings.HasPrefix(dir, "/safedir")
}

// createFile creates a file at the given path if it's within a valid directory.
// In Go, functions that can fail idiomatically return a value and an error.
// Here, it returns a pointer to an os.File object and an error.
func createFile(dir string) (*os.File, error) {
	valid := validateDir(dir)
	if !valid {
		// Instead of returning 'None' or 'nil' implicitly like in Python,
		// Go functions return an explicit error to indicate failure.
		return nil, errors.New("invalid directory: path must start with /safedir")
	}

	// os.Create creates a file for writing. If the file already exists,
	// its contents are discarded (truncated). This is equivalent to Python's open(dir, 'w').
	// It returns the file handle and an error if one occurred.
	file, err := os.Create(dir)
	if err != nil {
		return nil, err
	}

	return file, nil
}

func main() {
	// First call with an invalid path.
	file1, err1 := createFile("test")
	// In Go, you must check the returned error.
	if err1 != nil {
		// The Python code would print 'None'. The Go equivalent of a null pointer is 'nil'.
		// Since err1 is not nil, file1 is guaranteed to be nil. Printing it shows '<nil>'.
		fmt.Println(file1)
	}

	// To make the second call succeed, the '/safedir' directory must exist.
	// We create it here. The Python code would also fail if this directory didn't exist.
	// The 0755 is a standard permission mode for directories.
	err := os.MkdirAll("/safedir", 0755)
	if err != nil {
		fmt.Println("Setup failed: could not create /safedir:", err)
		return
	}

	// Second call with a valid path.
	file2, err2 := createFile("/safedir/testAgain1.txt")
	if err2 != nil {
		fmt.Println("Error creating file:", err2)
	} else {
		// It is crucial to close files when you are done with them.
		// The 'defer' statement schedules the Close() call to run just before
		// the main function exits, ensuring the file is closed.
		defer file2.Close()

		// The Python code prints the file object representation.
		// In Go, printing the file variable will show its memory address,
		// indicating that an object was successfully created.
		fmt.Println(file2)
	}
}