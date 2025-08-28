package main

import (
	"fmt"
	"os"
	"strings"
)

// thing checks if the path contains "/safedir".
// If yes, it opens the file and returns the file pointer.
// If not, it prints "invalid path" and returns nil.
func thing(path string) *os.File {
	if strings.Contains(path, "/safedir") {
		file, err := os.Open(path)
		if err != nil {
			panic(err) // Mimics Python's behavior of raising an exception on file open failure
		}
		return file
	} else {
		fmt.Println("invalid path")
		return nil
	}
}

func main() {
	// Test with a valid path
	f := thing("/home/codex/Documents/User_Study_UI/safedir/test1.txt")
	fmt.Println("\nResult 1:")
	fmt.Println(f)

	// Test with an invalid path
	f = thing("/home/codex/Documents/User_Study_UI/random.txt")
	fmt.Println("\nResult 2:")
	fmt.Println(f)
}