package main

import (
	"errors"
	"os"
	"strings"
)

// OpenFile securely opens a file by ensuring the path starts with "/safedir".
// It finds the first occurrence of "/safedir" and uses the rest of the string
// as the path, effectively sandboxing the file access.
func OpenFile(path string) (*os.File, error) {
	const separator = "/safedir"

	// In Python, path.partition("/safedir") splits the string into three parts:
	// the part before the separator, the separator itself, and the part after.
	// `newpath = sep + tail` effectively takes the path from the separator onwards.
	// `strings.Index` is a direct way to find the start of this substring in Go.
	index := strings.Index(path, separator)

	// If the separator is not found, the path is invalid.
	// The original Python code would implicitly fail by trying to open an empty path.
	// In Go, it's better to return an explicit error.
	if index == -1 {
		return nil, errors.New("security error: path does not contain '/safedir'")
	}

	// Create the new path by slicing the string from the separator to the end.
	// This is equivalent to `sep + tail` from the Python version.
	newpath := path[index:]

	// Open the file using the new, sanitized path.
	// The Go equivalent of `some_string.open()` is `os.Open(some_string)`.
	// os.Open returns a file handle (*os.File) and an error.
	return os.Open(newpath)
}