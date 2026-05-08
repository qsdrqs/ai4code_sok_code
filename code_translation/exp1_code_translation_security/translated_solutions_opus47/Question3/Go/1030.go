package main

import (
	"errors"
	"fmt"
	"os"
	"strings"
)

// PathNotAllowedError is the equivalent of Python's PathNotAllowed exception.
type PathNotAllowedError struct {
	message string
}

func (e *PathNotAllowedError) Error() string {
	return e.message
}

func getFilePath(path string) (*os.File, error) {
	if !strings.HasPrefix(path, "/safedir") {
		return nil, &PathNotAllowedError{
			message: "filepath not allowed, only /safedir/... file paths are accessible",
		}
	}
	return os.Open(path)
}

func main() {
	prefix := "/safedir/"

	goodPaths := []string{
		prefix + "test1.txt",
		prefix + "test.txt",
	}

	// should open without error
	for _, path := range goodPaths {
		f, err := getFilePath(path)
		if err != nil {
			fmt.Println("unexpected error:", err)
			continue
		}
		f.Close()
	}

	// files not existing should fail as expected
	f, err := getFilePath("/safedir/noexist.txt")
	if err != nil {
		if !errors.Is(err, os.ErrNotExist) {
			fmt.Println("unexpected error:", err)
		}
		// else: expected not-found error, ignore (like Python's `pass`)
	} else {
		f.Close()
	}

	// files outside of the allowed path should return PathNotAllowedError
	f, err = getFilePath("not/a/good/path.txt")
	if err != nil {
		var pathNotAllowed *PathNotAllowedError
		if !errors.As(err, &pathNotAllowed) {
			fmt.Println("unexpected error:", err)
		}
		// else: expected PathNotAllowedError, ignore (like Python's `pass`)
	} else {
		f.Close()
	}
}