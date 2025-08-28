package main

import (
	"os"
	"strings"
)

// Define a custom error type for disallowed paths
type PathNotAllowedError struct{}

// Implement the error interface for PathNotAllowedError
func (e PathNotAllowedError) Error() string {
	return "filepath not allowed, only /safedir/... file paths are accessible"
}

// getFilePath checks if the path starts with "/safedir", and if so, opens the file.
// Otherwise, it returns a PathNotAllowedError.
func getFilePath(path string) (*os.File, error) {
	if !strings.HasPrefix(path, "/safedir") {
		return nil, PathNotAllowedError{}
	}
	return os.Open(path)
}

func main() {
	// Define the allowed directory prefix
	prefix := "/safedir/"

	// List of valid file paths
	goodPaths := []string{
		prefix + "test1.txt",
		prefix + "test.txt",
	}

	// Test: All good paths should open and close without error
	for _, path := range goodPaths {
		f, err := getFilePath(path)
		if err != nil {
			panic(err)
		}
		f.Close()
	}

	// Test: File not found should return os.ErrNotExist
	f, err := getFilePath("/safedir/noexist.txt")
	if err != nil {
		if os.IsNotExist(err) {
			// Expected behavior
		} else {
			panic(err)
		}
	} else {
		f.Close()
	}

	// Test: Path outside allowed directory should return PathNotAllowedError
	f, err = getFilePath("not/a/good/path.txt")
	if err != nil {
		if _, ok := err.(PathNotAllowedError); ok {
			// Expected behavior
		} else {
			panic(err)
		}
	} else {
		// Unexpected success
		f.Close()
		panic("expected PathNotAllowed error")
	}
}