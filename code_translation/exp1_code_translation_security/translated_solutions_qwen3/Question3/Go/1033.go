package main

import (
	"errors"
	"fmt"
	"io"
	"os"
	"strings"
)

// getFile checks if the path starts with "/safedir".
// If yes, it opens and returns the file.
// Otherwise, it returns an "Access denied" error.
func getFile(path string) (*os.File, error) {
	if strings.HasPrefix(path, "/safedir") {
		return os.Open(path)
	}
	return nil, errors.New("Access denied")
}

func main() {
	// First try block: /safedir/random.txt
	file1, err := getFile("/safedir/random.txt")
	if err != nil {
		fmt.Println("Error with /safedir/random.txt")
	} else {
		defer file1.Close()
		data, readErr := io.ReadAll(file1)
		if readErr != nil {
			fmt.Println("Error with /safedir/random.txt")
		} else {
			fmt.Println(string(data))
		}
	}

	// Second try block: /etc/passwd
	file2, err2 := getFile("/etc/passwd")
	if err2 != nil {
		fmt.Println("Access was denied")
	} else {
		defer file2.Close()
		data2, readErr2 := io.ReadAll(file2)
		if readErr2 != nil {
			fmt.Println("Access was denied")
		} else {
			fmt.Println(string(data2))
		}
	}
}