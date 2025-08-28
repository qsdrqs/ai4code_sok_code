package main

import (
	"errors"
	"fmt"
	"os"
	"strings"
)

// openFile checks if the path starts with "/safedir".
// If it does, it opens the file and returns the file pointer and nil error.
// Otherwise, it returns nil and an error.
func openFile(path string) (*os.File, error) {
	if strings.HasPrefix(path, "/safedir") {
		file, err := os.Open(path)
		if err != nil {
			return nil, err
		}
		return file, nil
	}
	return nil, errors.New("Path must start with /safedir")
}

func main() {
	// First call: valid path
	res1, err := openFile("/safedir/random.txt")
	if err != nil {
		fmt.Println(err)
		return
	}
	fmt.Println(res1)

	// Second call: invalid path
	res2, err := openFile("blah")
	if err != nil {
		fmt.Println(err)
	}
}