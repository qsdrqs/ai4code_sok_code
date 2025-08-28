package main

import (
	"errors"
	"fmt"
	"io"
	"log"
	"os"
	"strings"
)

// open_file in Python is translated to openFile in Go.
// In Go, it's idiomatic to return a value and an error for functions that can fail.
// The Python 'Exception' is represented by Go's 'error' type.
func openFile(path string) (*os.File, error) {
	if strings.HasPrefix(path, "/safedir") {
		// os.Open returns a file handle (*os.File) and an error.
		return os.Open(path)
	} else {
		// Instead of raising an exception, we return nil for the file
		// and a new error object.
		return nil, errors.New("Access denied")
	}
}

// The main function in Go serves as the entry point for the application.
// This is equivalent to the code under `if __name__ == "__main__"` in Python.
func main() {
	// Note: The original Python main() function calls the built-in open(),
	// not the custom open_file() helper. This translation does the same.
	// In Go, file opening returns two values: the file and a potential error.
	f, err := os.Open("/safedir/test1.txt")
	if err != nil {
		// If an error occurred (e.g., file not found), log it and exit.
		log.Fatal(err)
	}
	// 'defer' schedules a function call (f.Close()) to be run just
	// before the main function exits. This ensures the file is always closed.
	defer f.Close()

	// Read all content from the file. This also returns two values:
	// the content as a byte slice and a potential error.
	content, err := io.ReadAll(f)
	if err != nil {
		log.Fatal(err)
	}

	// Print the content. We must convert the byte slice to a string first.
	fmt.Println(string(content))
}