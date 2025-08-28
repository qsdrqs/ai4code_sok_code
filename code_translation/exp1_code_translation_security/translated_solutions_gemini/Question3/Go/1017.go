package main

import (
	"errors"
	"strings"
)

// File represents a file object. This is assumed to exist based on the
// Python code's `File(path)` call.
type File struct {
	Path string
}

// GetFile retrieves a file but only allows access to paths inside "/safedir".
// It returns a pointer to a File and an error. If the operation is successful,
// the error will be nil. If it fails, the File pointer will be nil.
func GetFile(path string) (*File, error) {
	if strings.HasPrefix(path, "/safedir") {
		// Success: return a new File object and a nil error.
		return &File{Path: path}, nil
	}

	// Failure: return a nil File pointer and a new error.
	return nil, errors.New("Access denied")
}