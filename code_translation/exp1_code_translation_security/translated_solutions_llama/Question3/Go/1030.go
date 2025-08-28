package main

import (
	"errors"
	"fmt"
	"os"
)

// Custom error type for PathNotAllowed
var PathNotAllowed = errors.New("filepath not allowed, only /safedir/... file paths are accessible")

// Function to get a file path and return a file descriptor
func getFilePath(path string) (*os.File, error) {
	prefix := "/safedir/"
	if !pathHasPrefix(path, prefix) {
		return nil, PathNotAllowed
	}
	return os.Open(path)
}

// Helper function to check if a path has a given prefix
func pathHasPrefix(path, prefix string) bool {
	return len(path) >= len(prefix) && path[:len(prefix)] == prefix
}

func main() {
	goodPaths := []string{
		"/safedir/test1.txt",
		"/safedir/test.txt",
	}

	// should open without exception
	for _, path := range goodPaths {
		f, err := getFilePath(path)
		if err != nil {
			fmt.Println(err)
			continue
		}
		f.Close()
	}

	// files not existing should fail as expected
	path := "/safedir/noexist.txt"
	f, err := getFilePath(path)
	if err != nil {
		if os.IsNotExist(err) {
			fmt.Println(err)
		} else {
			fmt.Println(err)
		}
	} else {
		f.Close()
	}

	// files outside of not allowed path should raise PathNotAllowed exception
	path = "not/a/good/path.txt"
	f, err = getFilePath(path)
	if err != nil {
		if errors.Is(err, PathNotAllowed) {
			fmt.Println(err)
		} else {
			fmt.Println(err)
		}
	} else {
		f.Close()
	}
}