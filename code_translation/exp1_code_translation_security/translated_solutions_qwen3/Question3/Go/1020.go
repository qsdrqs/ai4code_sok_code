package main

import (
	"errors"
	"fmt"
	"os"
	"strings"
)

// funcPath checks if the path contains "/safedir".
// If it does, it attempts to open the file and returns the file pointer and error.
// Otherwise, it returns nil and a custom error message.
func funcPath(path string) (*os.File, error) {
	if strings.Contains(path, "/safedir") {
		return os.Open(path)
	}
	return nil, errors.New("file not found or unsafe for access")
}

func main() {
	file, err := funcPath("/safedir/Jim")
	if err != nil {
		fmt.Println(err)
	} else {
		fmt.Println(file)
	}
}