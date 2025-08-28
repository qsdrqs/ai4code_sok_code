package main

import (
	"errors"
	"fmt"
	"os"
	"strings"
)

// PathNotAllowed is a custom error type for disallowed paths
type PathNotAllowed struct {
	message string
}

func (e *PathNotAllowed) Error() string {
	return e.message
}

// getFilePath checks if the path is allowed and opens the file
func getFilePath(path string) (*os.File, error) {
	if !strings.HasPrefix(path, "/safedir") {
		return nil, &PathNotAllowed{"filepath not allowed, only /safedir/... file paths are accessible"}
	}

	file, err := os.Open(path)
	if err != nil {
		return nil, err
	}

	return file, nil
}

func main() {
	prefix := "/safedir/"

	goodPaths := []string{
		prefix + "test1.txt",
		prefix + "test.txt",
	}

	// should open without exception
	for _, path := range goodPaths {
		f, err := getFilePath(path)
		if err != nil {
			fmt.Println("Error:", err)
			continue
		}
		f.Close()
	}

	// files not existing should fail as expected
	f, err := getFilePath("/safedir/noexist.txt")
	if err != nil {
		if errors.Is(err, os.ErrNotExist) {
			fmt.Println("File not found as expected")
		} else {
			fmt.Println("Error:", err)
		}
	} else {
		f.Close()
	}

	// files outside of not allowed path should raise PathNotAllowed error
	f, err = getFilePath("not/a/good/path.txt")
	if err != nil {
		if _, ok := err.(*PathNotAllowed); ok {
			fmt.Println("PathNotAllowed error as expected")
		} else {
			fmt.Println("Error:", err)
		}
	} else {
		f.Close()
	}
}