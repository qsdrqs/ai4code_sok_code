package main

import (
	"fmt"
	"log"
	"os"
	"strings"
)

// openFile replicates the Python open_file function.
func openFile(path string) (*os.File, error) {
	if strings.HasPrefix(path, "/safedir") {
		return os.Open(path)
	}
	return nil, fmt.Errorf("path must start with /safedir")
}

func main() {
	// Attempt #1: valid path
	res1, err := openFile("/safedir/random.txt")
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println(res1)

	// Attempt #2: invalid path
	res2, err := openFile("blah")
	if err != nil {
		fmt.Println("error:", err)
	} else {
		fmt.Println(res2)
	}
}