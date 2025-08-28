package main

import (
	"os"
	"path/filepath"
)

// Path2Obj translates the given Python function. It checks if any parent
// directory of the given filePath is named "safedir". If such a parent
// is found, it opens the file for reading and returns the file handle (*os.File).
// If no "safedir" is found in the path, it returns nil, mimicking the Python
// function's return of `None`.
//
// Note on the translation:
// The original Python code opens the file *before* checking the path, creating a
// resource leak if the path validation fails. This Go version corrects this flaw
// by first validating the path and only then opening the file. This is a safer
// and more idiomatic approach in Go.
func Path2Obj(filePath string) (*os.File, error) {
	// Start with the directory containing the file.
	// `filepath.Dir` is the Go equivalent of Python's `os.path.dirname`.
	directory := filepath.Dir(filePath)

	isSafe := false

	// Loop upwards through the directory hierarchy until the root is reached.
	// This loop correctly handles both absolute and relative paths on any OS.
	for {
		// `filepath.Base` is the Go equivalent of `os.path.basename`.
		// The original Python code also used `normpath`, but `filepath.Dir`
		// in Go already returns a clean path.
		if filepath.Base(directory) == "safedir" {
			isSafe = true
			break
		}

		// Get the parent directory for the next iteration.
		parent := filepath.Dir(directory)

		// If the parent directory is the same as the current one, we've reached the root.
		if parent == directory {
			break
		}
		directory = parent
	}

	// If the path was determined to be safe, proceed to open the file.
	if isSafe {
		// `os.Open` opens a file for reading, equivalent to Python's `open(path, "r")`.
		// It returns the file object and an error if one occurred.
		f, err := os.Open(filePath)
		if err != nil {
			return nil, err
		}
		// Success: return the file handle. The caller is responsible for closing it.
		return f, nil
	}

	// If "safedir" was not found, return (nil, nil), which is the idiomatic Go
	// way to indicate "not found" without it being a system error.
	return nil, nil
}