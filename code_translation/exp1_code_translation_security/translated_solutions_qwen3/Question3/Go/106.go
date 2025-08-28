package main

import (
	"fmt"
	"os"
	"strings"
	"log"
)

// getFile attempts to open a file at the given path, but only if the path starts with "/safedir".
// If the path is invalid, it returns an error.
func getFile(path string) (*os.File, error) {
	if !strings.HasPrefix(path, "/safedir") {
		return nil, fmt.Errorf("Invalid path")
	}
	return os.Open(path)
}

func main() {
	// First call: valid path
	_, err := getFile("/safedir/random.txt")
	if err != nil {
		log.Fatal(err)
	}

	// Second call: invalid path
	_, err = getFile("/random.txt")
	if err != nil {
		log.Fatal(err)
	}
}