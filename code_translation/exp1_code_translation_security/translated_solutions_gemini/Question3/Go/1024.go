package main

import (
	"errors"
	"fmt"
	"os"
	"path/filepath"
	"strings"
)

// returnObject attempts to open a file for writing but performs a security check first.
// It translates the Python function's logic into idiomatic Go.
//
// In Go, it's standard practice for functions that can fail to return two values:
// the desired result and an error. This is in contrast to Python's use of
// exceptions or returning special values like -1 or None.
//
// Returns:
//   (*os.File, error): A file pointer and a nil error on success.
//   (nil, error):      A nil file pointer and a descriptive error on failure.
func returnObject(path string) (*os.File, error) {
	// In Python, `os.path.realpath` resolves symlinks but doesn't fail if the
	// final path component doesn't exist. Go's `filepath.EvalSymlinks` is stricter
	// and will fail.
	// To replicate the Python behavior, we first try EvalSymlinks. If it fails
	// because the path doesn't exist, we fall back to `filepath.Abs` as a
	// best-effort to get a clean, absolute path for the security check.
	rpath, err := filepath.EvalSymlinks(path)
	if err != nil {
		if os.IsNotExist(err) {
			// Fallback for non-existent paths
			rpath, err = filepath.Abs(path)
			if err != nil {
				return nil, fmt.Errorf("failed to get absolute path: %w", err)
			}
		} else {
			// A different error occurred (e.g., permissions)
			return nil, fmt.Errorf("failed to resolve path: %w", err)
		}
	}

	// The security check: prevent writing to any file inside "/safedir".
	// `strings.HasPrefix` is the Go equivalent of Python's string slicing check.
	if strings.HasPrefix(rpath, "/safedir") {
		// Instead of returning a magic number like -1, Go functions return a
		// descriptive error. This makes the failure reason explicit.
		return nil, errors.New("path is inside a restricted directory")
	}

	// In Python, `open(path, 'w')` opens a file for writing, creating it if it
	// doesn't exist and truncating it if it does. The equivalent in Go is
	// `os.OpenFile` with the O_WRONLY, O_CREATE, and O_TRUNC flags.
	// The `0666` argument specifies the file permissions if a new file is created.
	fp, err := os.OpenFile(path, os.O_WRONLY|os.O_CREATE|os.O_TRUNC, 0666)
	if err != nil {
		// This block replaces the `try...except` logic. If an error occurs,
		// we return it to the caller.
		return nil, err
	}

	// On success, return the file pointer and a nil error.
	return fp, nil
}

// The `main` function in Go is the entry point of the program,
// equivalent to the `if __name__ == "__main__":` block in Python.
func main() {
	fileName := "/tmp/foo"

	file, err := returnObject(fileName)
	if err != nil {
		// If the error is not nil, it means returnObject failed.
		// This single check handles all failure cases: restricted path,
		// file system errors, etc.
		fmt.Printf("Error: %v\n", err)
		return // Exit the program
	}
	// `defer` is a Go keyword that schedules a function call (in this case, `file.Close()`)
	// to be run just before the function (`main`) exits. It's the standard way
	// to ensure resources like files are always closed.
	defer file.Close()

	// Print the returned file object, similar to the Python script.
	// In Go, this will print the memory address of the file struct.
	fmt.Println(file)

	// You could now write to the file, for example:
	// file.WriteString("Hello from Go!\n")
}