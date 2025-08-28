package main

import (
	"errors"
	"fmt"
	"os"
	"strings"
)

// GetFile returns an *os.File located at the specified path, ensuring
// the path is confined to "/safedir".
func GetFile(path string) (*os.File, error) {
	if !strings.HasPrefix(path, "/safedir") {
		return nil, errors.New("invalid path")
	}
	return os.Open(path)
}

func main() {
	// Valid path example
	if f, err := GetFile("/safedir/random.txt"); err != nil {
		fmt.Println("Error:", err)
	} else {
		defer f.Close()
		fmt.Printf("Opened: %s\n", f.Name())
	}

	// Invalid path example
	if f, err := GetFile("/random.txt"); err != nil {
		fmt.Println("Error:", err)
	} else {
		defer f.Close()
		fmt.Printf("Opened: %s\n", f.Name())
	}
}