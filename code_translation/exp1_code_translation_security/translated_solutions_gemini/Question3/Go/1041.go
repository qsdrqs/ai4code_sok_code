package main

import (
	"errors"
	"fmt"
	"log"
	"os"
	"strings"
)

// openFile opens a file only if its path starts with a safe prefix.
// In Go, it's idiomatic to return an error as the second return value
// instead of raising an exception.
func openFile(path string) (*os.File, error) {
	// In Go, we use the strings.HasPrefix function for this check.
	// We use "safedir/" to make it a relative path for safety.
	if strings.HasPrefix(path, "safedir/") {
		// os.Open returns (*os.File, error), which matches our return signature.
		return os.Open(path)
	} else {
		// Instead of raising a ValueError, we return a nil file and a new error.
		return nil, errors.New("Path must start with safedir/")
	}
}

func main() {
	// --- Setup for a runnable example ---
	// Create the "safedir" directory and a file inside it so os.Open can succeed.
	if err := os.Mkdir("safedir", 0755); err != nil && !os.IsExist(err) {
		log.Fatalf("Failed to create directory: %v", err)
	}
	if err := os.WriteFile("safedir/random.txt", []byte("content"), 0644); err != nil {
		log.Fatalf("Failed to create dummy file: %v", err)
	}
	// Defer cleanup to remove the created directory and file after main finishes.
	defer os.RemoveAll("safedir")

	// --- First call (successful case) ---
	// This corresponds to: res1 = open_file("/safedir/random.txt")
	res1, err1 := openFile("safedir/random.txt")
	if err1 != nil {
		// This block won't be executed in this case.
		log.Fatalf("Failed to open file when it should have succeeded: %v", err1)
	}
	// It's crucial in Go to close files when you're done.
	// 'defer' ensures the Close() call runs before the function exits.
	defer res1.Close()

	// This corresponds to: print(res1)
	fmt.Printf("Successfully opened file: %s\n", res1.Name())
	fmt.Println(res1) // Prints details about the file object pointer
	fmt.Println("--------------------")

	// --- Second call (error case) ---
	// This corresponds to: res2 = open_file("blah")
	// The original Python code would crash here with an unhandled ValueError.
	// The Go code handles the returned error gracefully.
	res2, err2 := openFile("blah")
	if err2 != nil {
		// This block is expected to run.
		fmt.Printf("Successfully caught expected error: %v\n", err2)
	} else {
		// This block should not be reached.
		defer res2.Close()
		log.Fatalf("Expected an error, but the file was opened: %v", res2.Name())
	}
}