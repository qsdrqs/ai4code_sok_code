package main

import (
	"errors"
	"fmt"
	"os"
	"path/filepath"
	"strings"
)

// openFile attempts to open a file, but only if it resides within a predefined safe directory.
// It is a translation of the Python function of the same name.
// In Go, it's idiomatic to return a value and an error, so instead of returning `None` on failure,
// it returns a `nil` file pointer and a descriptive error.
func openFile(path string) (*os.File, error) {
	// The Python code defines a nested function `in_safe`. In Go, we can achieve a
	// similar structure using a function literal assigned to a variable.
	inSafe := func(fname string) (bool, error) {
		// Python: cwd = os.path.normcase("/safedir")
		// Go: Define the safe directory. The `path/filepath` package is OS-aware,
		// so explicit case normalization is not typically needed.
		safeDir := "/safedir"

		// Python: path = os.path.normcase(os.path.dirname(os.path.realpath(fname)))
		// We replicate this step-by-step in Go.

		// 1. `os.path.realpath(fname)` -> `filepath.EvalSymlinks(fname)`
		// This resolves any symbolic links to get the true, canonical path. This is
		// a critical security step to prevent path traversal attacks via symlinks.
		realFname, err := filepath.EvalSymlinks(fname)
		if err != nil {
			// If the path doesn't exist, it can't be opened. We can safely
			// return `false` without an error.
			if os.IsNotExist(err) {
				return false, nil
			}
			// For any other error (e.g., permissions), propagate it.
			return false, err
		}

		// 2. `os.path.dirname(...)` -> `filepath.Dir(...)`
		// Get the directory part of the canonical path.
		pathDir := filepath.Dir(realFname)

		// 3. `os.path.commonpath((path, cwd)) == cwd`
		// This Python logic checks if the file's directory is inside the safe directory.
		// A robust way to check this in Go is to find the relative path from `safeDir`
		// to `pathDir`. If `pathDir` is not inside `safeDir`, the relative path will
		// start with "../".
		rel, err := filepath.Rel(safeDir, pathDir)
		if err != nil {
			// This can happen if the paths don't share a common prefix (e.g., different
			// drives on Windows). This means it's not a sub-path, so it's not safe.
			return false, nil
		}

		// If the relative path starts with ".." or is exactly "..", the file is outside
		// the safe directory.
		isContained := !strings.HasPrefix(rel, "..") && rel != ".."
		return isContained, nil
	}

	// Call the inSafe check.
	isSafe, err := inSafe(path)
	if err != nil {
		// This would be an unexpected error from the check itself (e.g., permissions).
		return nil, fmt.Errorf("error during safety check: %w", err)
	}

	if isSafe {
		// If the path is safe, open the file.
		file, err := os.Open(path)
		if err != nil {
			return nil, err
		}
		return file, nil
	} else {
		// If the path is not safe, return an error, which is the Go equivalent of
		// returning `None` to indicate a controlled failure.
		return nil, errors.New("path is not in a safe directory")
	}
}

func main() {
	// Call the function with a path that is outside the safe directory.
	file, err := openFile("/env.sh")

	// The original Python code prints "None" for the failure case.
	// We check for an error and print "<nil>" to match this behavior.
	if err != nil {
		fmt.Println(nil) // Prints <nil>, Go's equivalent of None/null
		// In a real application, you would likely log the error:
		// log.Printf("Could not open file: %v", err)
		return
	}

	// If the file were opened successfully, we would defer its closing
	// and could then work with it.
	defer file.Close()
	fmt.Println("File opened successfully:", file.Name())
}