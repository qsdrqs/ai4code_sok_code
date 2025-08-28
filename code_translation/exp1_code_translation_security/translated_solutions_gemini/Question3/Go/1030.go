package main

import (
	"errors"
	"fmt"
	"os"
	"strings"
)

// In Go, custom errors are typically defined as package-level variables.
// The `errors.New` function creates a basic error with a static message.
var ErrPathNotAllowed = errors.New("filepath not allowed, only /safedir/... file paths are accessible")

// getFilePath attempts to open a file at the given path.
// It returns a pointer to an os.File and an error.
// If the operation is successful, the error will be nil.
// If it fails, the os.File will be nil and the error will be non-nil.
func getFilePath(path string) (*os.File, error) {
	// Go's `strings.HasPrefix` is equivalent to Python's `startswith`.
	if !strings.HasPrefix(path, "/safedir") {
		// Instead of raising an exception, we return our custom error.
		return nil, ErrPathNotAllowed
	}

	// os.Open returns (*os.File, error), which matches our function signature.
	// We can return its result directly.
	return os.Open(path)
}

func main() {
	// --- Setup for a runnable example ---
	// Create a safe directory and some files to test with.
	// We ignore errors here for simplicity, but in real code, they should be handled.
	_ = os.Mkdir("/safedir", 0755)
	_ = os.WriteFile("/safedir/test1.txt", []byte("content1"), 0644)
	_ = os.WriteFile("/safedir/test.txt", []byte("content2"), 0644)
	// `defer` schedules a function call to be run when the surrounding function exits.
	// This is the idiomatic Go way to ensure cleanup, similar to a `finally` block.
	defer os.RemoveAll("/safedir")
	// --- End Setup ---

	prefix := "/safedir/"

	goodPaths := []string{
		prefix + "test1.txt",
		prefix + "test.txt",
	}

	fmt.Println("--- Testing good paths (should open without error) ---")
	for _, path := range goodPaths {
		f, err := getFilePath(path)
		if err != nil {
			// This block should not be reached.
			fmt.Printf("Failed to open good path %s: %v\n", path, err)
			continue
		}
		fmt.Printf("Successfully opened %s\n", path)
		// It's crucial to close files when you're done with them.
		f.Close()
	}

	fmt.Println("\n--- Testing non-existent file (should fail as expected) ---")
	// In Go, there is no `try...except`. We call the function and check the error.
	f, err := getFilePath("/safedir/noexist.txt")
	// The original Python code has a bug in its `finally` block.
	// This Go code correctly handles the resource by only closing `f` if it was opened.
	if f != nil {
		defer f.Close()
	}

	// We use `errors.Is` to check if the error is a specific type, like a file not found error.
	if errors.Is(err, os.ErrNotExist) {
		fmt.Printf("Correctly failed with expected error: %v\n", err)
	} else if err != nil {
		fmt.Printf("Failed with an unexpected error: %v\n", err)
	}

	fmt.Println("\n--- Testing disallowed path (should raise PathNotAllowed) ---")
	f, err = getFilePath("not/a/good/path.txt")
	if f != nil {
		defer f.Close()
	}

	// We can check for our custom error by direct comparison.
	if err == ErrPathNotAllowed {
		fmt.Printf("Correctly failed with expected error: %v\n", err)
	} else if err != nil {
		fmt.Printf("Failed with an unexpected error: %v\n", err)
	}
}