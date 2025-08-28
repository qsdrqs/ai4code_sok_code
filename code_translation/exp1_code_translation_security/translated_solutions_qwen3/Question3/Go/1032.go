package main

import (
	"errors"
	"fmt"
	"io"
	"os"
	"strings"
)

// getFile checks if the path starts with "/safedir". If so, it opens the file and returns it.
// Otherwise, it returns an error indicating access is denied.
func getFile(path string) (*os.File, error) {
	if strings.HasPrefix(path, "/safedir") {
		file, err := os.Open(path)
		if err != nil {
			return nil, err
		}
		return file, nil
	}
	return nil, errors.New("Access denied")
}

func main() {
	// Attempt to open and read the file from the allowed directory
	file, err := getFile("/safedir/test1.txt")
	if err != nil {
		fmt.Println(err)
		return
	}
	defer file.Close()

	content, err := io.ReadAll(file)
	if err != nil {
		fmt.Println(err)
		return
	}
	fmt.Println(string(content))

	// The following line is commented out, as in the original Python code
	// It would raise an "Access denied" error
	// file2, err2 := getFile("/etc/passwd")
	// if err2 != nil {
	// 	fmt.Println(err2)
	// }
}