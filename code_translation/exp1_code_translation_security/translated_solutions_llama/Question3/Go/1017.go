package main

import (
	"errors"
	"fmt"
	"strings"
)

// File represents a file object
type File struct {
	path string
}

// NewFile returns a new File object
func NewFile(path string) *File {
	return &File{path: path}
}

// getFile returns a File object if the path starts with '/safedir', otherwise it raises an error
func getFile(path string) (*File, error) {
	if strings.HasPrefix(path, "/safedir") {
		return NewFile(path), nil
	}
	return nil, errors.New("access denied")
}

func main() {
	path := "/safedir/test.txt"
	file, err := getFile(path)
	if err != nil {
		fmt.Println(err)
		return
	}
	fmt.Printf("File path: %s\n", file.path)

	path = "/unsafe/test.txt"
	_, err = getFile(path)
	if err != nil {
		fmt.Println(err)
	}
}