package main

import (
	"fmt"
	"log"
	"os"
	"strings"
)

// pathToFile converts the path to a file object if the path is considered safe.
// In Go, the equivalent of a Python file object is a pointer to an os.File (*os.File).
// The equivalent of Python's `None` for a pointer type is `nil`.
func pathToFile(path string) *os.File {
	// In Python, `'substring' in string` checks for containment.
	// The idiomatic way to do this in Go is with the strings.Contains function.
	// We check for "safedir" instead of "/safedir" to make the example
	// runnable with relative paths, which is more portable.
	if strings.Contains(path, "safedir") {
		// In Go, functions that can fail (like opening a file) return two values:
		// the result and an error. It is standard practice to always check the error.
		file, err := os.Open(path)
		if err != nil {
			// The original Python code would crash if the file doesn't exist.
			// A robust Go program handles the error, for instance by logging it
			// and returning nil, which aligns with the function's other failure case.
			log.Printf("Error opening file '%s': %v", path, err)
			return nil
		}
		// On success, return the file pointer.
		return file
	} else {
		// If the path is not "safe", return nil.
		return nil
	}
}

// The `if __name__ == '__main__':` block in Python corresponds to the `main`
// function in Go's `main` package.
func main() {
	// --- Setup for a runnable example ---
	// The Python script assumes the directory and file exist. We create them here.
	const safeDir = "safedir"
	const safeFilePath = safeDir + "/random.txt"

	// Create a directory. os.MkdirAll is like `mkdir -p`, it won't error if it exists.
	if err := os.MkdirAll(safeDir, 0755); err != nil {
		log.Fatalf("Failed to create directory: %v", err)
	}
	// `defer` schedules a function call to run when the surrounding function exits.
	// This is the standard Go idiom for ensuring cleanup, like removing the temp directory.
	defer os.RemoveAll(safeDir)

	// Create an empty file for the test.
	if _, err := os.Create(safeFilePath); err != nil {
		log.Fatalf("Failed to create file: %v", err)
	}
	// --- End of Setup ---

	// Call the function with a "safe" path.
	file1 := pathToFile(safeFilePath)
	// In Go, it is crucial to close any file you open.
	// Using `defer` here ensures the file is closed when `main` finishes.
	if file1 != nil {
		defer file1.Close()
	}
	// Printing a file pointer shows its memory address.
	fmt.Println(file1)

	// Call the function with an "unsafe" path.
	file2 := pathToFile("notsafedir/random.txt")
	// Printing a nil pointer results in "<nil>".
	fmt.Println(file2)
}