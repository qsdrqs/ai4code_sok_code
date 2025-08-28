package main

import (
	"errors"
	"strings"
)

// getFile is a direct translation of the Python get_file.
// It returns a *File if the path begins with "/safedir"; otherwise
// it returns an error stating that access is denied.
func getFile(path string) (*File, error) {
	if strings.HasPrefix(path, "/safedir") {
		// File is assumed to be provided elsewhere in your codebase.
		return File(path), nil
	}

	return nil, errors.New("access denied")
}