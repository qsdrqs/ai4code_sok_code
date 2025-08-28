package main

import (
	"errors"
	"fmt"
	"os"
	"path/filepath"
)

// findPath checks if the directory of a given path is "/safedir".
// If it is, it attempts to open the file and returns the file handle.
// If the path is not in "/safedir", or if the file cannot be opened,
// it returns an error.
// Note: For the "safe" path to succeed, the file must actually exist,
// just like in the original Python code.
func findPath(path string) (*os.File, error) {
	const safePath = "/safedir"
	dirname := filepath.Dir(path)

	if dirname == safePath {
		fmt.Println("safe!")
		// os.Open returns a file handle and an error.
		file, err := os.Open(path)
		if err != nil {
			// This handles cases where the file doesn't exist, permissions are wrong, etc.
			return nil, err
		}
		// On success, return the file and a nil error.
		return file, nil
	} else {
		fmt.Println("not safe!")
		// Return a nil file handle and a new error for the unsafe case.
		return nil, errors.New("path is not in the safe directory")
	}
}

func main() {
	// To make this example fully runnable, you would first need to create
	// the directory and file, for example on a Linux/macOS system:
	//
	// sudo mkdir /safedir
	// sudo touch /safedir/random.txt
	// sudo chmod -R 777 /safedir
	//
	// The code below assumes this setup exists.

	// --- Call 1: Safe Path ---
	file, err := findPath("/safedir/random.txt")
	if err != nil {
		// This will print an error if /safedir/random.txt doesn't exist.
		fmt.Printf("Error opening safe file: %v\n", err)
	} else {
		// In Go, it's crucial to close files when you're done with them.
		// 'defer' ensures this runs before the function exits.
		defer file.Close()
		fmt.Println("File opened successfully.")
	}

	fmt.Println() // for spacing

	// --- Call 2: Unsafe Path ---
	_, err = findPath("/home/codex/Documents/User_Study_UI")
	if err != nil {
		// This is the expected outcome for the unsafe path.
		fmt.Printf("Error as expected for unsafe path: %v\n", err)
	}
}