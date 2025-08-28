package main

import (
	"fmt"
	"os"
	"strings"
)

// ALLOWED_PATH is the allowed directory path
const ALLOWED_PATH = "/safedir"

// getFile returns a file descriptor if the path is allowed, otherwise an error message
func getFile(path string) (*os.File, error) {
	if !strings.HasPrefix(path, ALLOWED_PATH) {
		return nil, fmt.Errorf("not allowed to get file from this dir")
	}

	return os.Open(path)
}

func main() {
	path := "/safedir/test/path"
	file, err := getFile(path)
	if err != nil {
		fmt.Println(err)
		return
	}
	defer file.Close() // Close the file when done
	fmt.Println("File opened successfully")
}