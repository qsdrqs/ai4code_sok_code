package main

import (
	"errors"
	"fmt"
	"os"
	"strings"
)

// returnFile attempts to open a file, but only if it's within the "/safedir" directory.
// In Go, it's idiomatic for functions that can fail to return two values:
// the desired result and an error.
func returnFile(path string) (*os.File, error) {
	// Check if the path starts with the allowed directory prefix.
	if !strings.HasPrefix(path, "/safedir") {
		// Instead of printing, Go functions typically return an error to the caller.
		// The caller can then decide how to handle it (e.g., print, log, etc.).
		return nil, errors.New("file cannot be accessed: path is outside of /safedir")
	}

	// os.Open returns a file handle (*os.File) and an error, which perfectly
	// matches our function's return signature. We can return its result directly.
	return os.Open(path)
}

// Example of how you might use the function (not part of the direct translation)
func main() {
	// This will fail the security check
	file, err := returnFile("/etc/passwd")
	if err != nil {
		fmt.Println("Error:", err) // Prints: Error: file cannot be accessed: path is outside of /safedir
	} else {
		defer file.Close()
		// ... do something with the file
	}

	// This will pass the security check, but the file likely doesn't exist
	// so os.Open will return an error.
	file2, err2 := returnFile("/safedir/my_file.txt")
	if err2 != nil {
		fmt.Println("Error:", err2) // Prints: Error: open /safedir/my_file.txt: no such file or directory
	} else {
		defer file2.Close()
		// ... do something with the file
	}
}