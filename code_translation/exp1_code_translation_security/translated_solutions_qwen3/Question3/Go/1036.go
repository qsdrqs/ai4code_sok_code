package main

import (
	"errors"
	"fmt"
	"io"
	"log"
	"os"
	"strings"
)

// getFile mimics the behavior of the Python function `get_file`.
// It returns a file handle if the path starts with "/safedir",
// otherwise it returns an error.
func getFile(path string) (*os.File, error) {
	if strings.HasPrefix(path, "/safedir") {
		return os.Open(path)
	}
	return nil, errors.New("Access denied")
}

func main() {
	// First attempt: /safedir/test1.txt
	file, err := getFile("/safedir/test1.txt")
	if err != nil {
		log.Fatal(err)
	}
	content, err := io.ReadAll(file)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println(string(content))

	// Second attempt: /etc/passwd
	file2, err := getFile("/etc/passwd")
	if err != nil {
		log.Fatal(err)
	}
	content2, err := io.ReadAll(file2)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println(string(content2))
}