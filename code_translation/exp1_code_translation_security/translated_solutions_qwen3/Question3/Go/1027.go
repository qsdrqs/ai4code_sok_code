package main

import (
	"fmt"
	"os"
	"strings"
)

// openFile attempts to open a file at the given path.
// It ensures the path starts with "/safedir", otherwise returns an error.
// If the file cannot be opened, it prints "File not found" and returns nil.
func openFile(filePath string) (*os.File, error) {
	if !strings.HasPrefix(filePath, "/safedir") {
		return nil, fmt.Errorf("File is outside of /safedir")
	}

	file, err := os.Open(filePath)
	if err != nil {
		fmt.Println("File not found")
		return nil, nil
	}

	return file, nil
}

func main() {
	file, err := openFile("/safedir/Jim")
	if err != nil {
		fmt.Println(err)
		os.Exit(1)
	}
	fmt.Println(file)
}