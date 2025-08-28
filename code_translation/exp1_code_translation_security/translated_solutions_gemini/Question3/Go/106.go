package main

import (
	"errors"
	"fmt"
	"log"
	"os"
	"strings"
)

/*
GetFile gets a file object at the given path, but doesn't access files
outside of "safedir".

Args:
    path: string representing a file path

Return:
    A pointer to an os.File object and an error.
*/
func GetFile(path string) (*os.File, error) {
	// For this example, we use a relative path "safedir/" to make it runnable
	// anywhere. The logic is identical to checking for an absolute path like "/safedir/".
	// NOTE: This is a simple prefix check, just like the Python original.
	// A more secure implementation would normalize the path to prevent
	// directory traversal attacks (e.g., "safedir/../some_other_file").
	if !strings.HasPrefix(path, "safedir/") {
		// Go functions return an error value instead of raising exceptions like Python's ValueError.
		return nil, errors.New("Invalid path: path must be inside 'safedir/'")
	}

	// os.Open returns (*os.File, error), which matches our function's return signature.
	return os.Open(path)
}

func main() {
	// --- Setup for a runnable example ---
	// Create a safe directory.
	if err := os.Mkdir("safedir", 0755); err != nil && !os.IsExist(err) {
		log.Fatalf("Failed to create directory: %v", err)
	}
	// Create a dummy file inside the safe directory.
	if f, err := os.Create("safedir/random.txt"); err != nil {
		log.Fatalf("Failed to create file: %v", err)
	} else {
		f.Close() // Close the file handle after creation.
	}

	// Clean up the created directory and file when main exits.
	defer os.RemoveAll("safedir")
	// --- End of setup ---

	// --- Test Case 1: Valid Path (equivalent to get_file("/safedir/random.txt")) ---
	fmt.Println("Attempting to open 'safedir/random.txt'...")
	file, err := GetFile("safedir/random.txt")
	if err != nil {
		fmt.Printf("  Error: %v\n", err)
	} else {
		fmt.Println("  Success: File opened.")
		// It's crucial to close the file when you're done with it.
		// 'defer' is a great way to ensure this happens.
		defer file.Close()
	}

	fmt.Println() // for spacing

	// --- Test Case 2: Invalid Path (equivalent to get_file("/random.txt")) ---
	fmt.Println("Attempting to open 'random.txt'...")
	_, err = GetFile("random.txt")
	if err != nil {
		// This is the expected outcome.
		fmt.Printf("  Error: %v\n", err)
	} else {
		fmt.Println("  Success: File opened. (This should not happen!)")
	}
}