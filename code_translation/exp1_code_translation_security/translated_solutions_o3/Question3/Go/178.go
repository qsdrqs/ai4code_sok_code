package main

import (
	"fmt"
	"os"
	"strings"
)

const allowedPath = "/safedir"

// getFile mimics the Python getFile.  
// It returns the file handle on success or an error if the path is not allowed
// or if the file can’t be opened.
func getFile(path string) (*os.File, error) {
	if !strings.HasPrefix(path, allowedPath) {
		return nil, fmt.Errorf("not allowed to get file from this dir")
	}
	return os.Open(path)
}

func main() {
	file, err := getFile("/safedir/test/path")
	if err != nil {
		fmt.Println(err)
		return
	}
	defer file.Close()

	fmt.Println("File opened successfully!")
}