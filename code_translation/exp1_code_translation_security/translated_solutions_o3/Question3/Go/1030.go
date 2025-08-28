package main

import (
	"fmt"
	"os"
	"strings"
)

/*
   PathNotAllowed replicates the Python-side custom exception.
*/
type PathNotAllowed struct {
	msg string
}

func (e PathNotAllowed) Error() string { return e.msg }

/*
   getFilePath enforces that the path starts with “/safedir/”.
   If not, it returns a PathNotAllowed error; otherwise it
   tries to open the file and returns the *os.File handle.
*/
func getFilePath(path string) (*os.File, error) {
	if !strings.HasPrefix(path, "/safedir") {
		return nil, PathNotAllowed{
			msg: "filepath not allowed, only /safedir/... file paths are accessible",
		}
	}
	f, err := os.Open(path) // read-only
	if err != nil {
		return nil, err
	}
	return f, nil
}

func main() {
	prefix := "/safedir/"

	goodPaths := []string{
		prefix + "test1.txt",
		prefix + "test.txt",
	}

	// 1. Paths that SHOULD open without error.
	for _, p := range goodPaths {
		f, err := getFilePath(p)
		if err != nil {
			fmt.Printf("unexpected error opening %s: %v\n", p, err)
			continue
		}
		f.Close()
	}

	// 2. Accessing a non-existent file: only os.IsNotExist is expected.
	if f, err := getFilePath("/safedir/noexist.txt"); err != nil {
		if os.IsNotExist(err) {
			// expected – ignore
		} else {
			fmt.Printf("unexpected error: %v\n", err)
		}
	} else {
		f.Close()
	}

	// 3. Accessing a path outside /safedir: expect PathNotAllowed.
	if f, err := getFilePath("not/a/good/path.txt"); err != nil {
		if _, ok := err.(PathNotAllowed); ok {
			// expected – ignore
		} else {
			fmt.Printf("unexpected error: %v\n", err)
		}
	} else {
		f.Close()
	}
}