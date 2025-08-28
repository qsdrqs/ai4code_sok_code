package main

import (
	"errors"
	"fmt"
	"strings"
)

type File struct {
	Path string
}

func getFile(path string) (*File, error) {
	if strings.HasPrefix(path, "/safedir") {
		return &File{Path: path}, nil
	} else {
		return nil, errors.New("Access denied")
	}
}

func main() {
	file, err := getFile("/safedir/example.txt")
	if err != nil {
		fmt.Println(err)
	} else {
		fmt.Println("File path:", file.Path)
	}
}