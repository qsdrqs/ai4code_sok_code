package main

import (
	"os"
	"strings"
)

// createFileObject mimics the behavior of the Python function create_file_object.
func createFileObject(path string) *os.File {
	if strings.HasPrefix(path, "/safedir") {
		// Open the file and ignore any error (as in the original Python code)
		file, _ := os.Open(path)
		return file
	}
	return nil
}

func main() {
	// This will print <nil> since "/etc/passwd" does not start with "/safedir"
	println(createFileObject("/etc/passwd"))
}