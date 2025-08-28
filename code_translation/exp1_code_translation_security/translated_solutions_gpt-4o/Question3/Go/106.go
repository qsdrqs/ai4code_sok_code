package main

import (
	"errors"
	"fmt"
	"os"
	"strings"
)

// GetFile opens a file at the given path, ensuring it is within "/safedir".
func GetFile(path string) (*os.File, error) {
	if !strings.HasPrefix(path, "/safedir") {
		return nil, errors.New("invalid path")
	}
	file, err := os.Open(path)
	if err != nil {
		return nil, err
	}
	return file, nil
}

func main() {
	// Example usage of GetFile
	file, err := GetFile("/safedir/random.txt")
	if err != nil {
		fmt.Println("Error:", err)
	} else {
		fmt.Println("File opened successfully:", file.Name())
		file.Close()
	}

	file, err = GetFile("/random.txt")
	if err != nil {
		fmt.Println("Error:", err)
	} else {
		fmt.Println("File opened successfully:", file.Name())
		file.Close()
	}
}