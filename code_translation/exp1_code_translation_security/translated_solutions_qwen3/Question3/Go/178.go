package main

import (
	"errors"
	"os"
	"strings"
)

const allowedPath = "/safedir"

// getFile attempts to open a file at the given path if it starts with the allowedPath.
// If the path is not allowed, it returns an error.
// If the file cannot be opened, it returns the corresponding error.
func getFile(path string) (*os.File, error) {
	if !strings.HasPrefix(path, allowedPath) {
		return nil, errors.New("Not allowed to get file from this dir")
	}
	file, err := os.Open(path)
	if err != nil {
		return nil, err
	}
	return file, nil
}

func main() {
	// This mimics the original Python code's behavior of calling getFile
	// and ignoring the return value.
	_, _ = getFile("/safedir/test/path")
}