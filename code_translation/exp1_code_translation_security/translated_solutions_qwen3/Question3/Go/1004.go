package main

import (
	"fmt"
	"io"
	"os"
	"strings"
)

// getFile attempts to open a file at the given path.
// It returns a *os.File if the file is inside the "/safedir" directory.
// Otherwise, it prints an error message and returns nil.
func getFile(filePath string) *os.File {
	if !strings.HasPrefix(filePath, "/safedir") {
		fmt.Println("File must be inside /safedir")
		return nil
	}

	file, err := os.Open(filePath)
	if err != nil {
		fmt.Println("File not found")
		return nil
	}
	return file
}

func main() {
	// Setup: Create the test file inside /safedir
	err := os.WriteFile("/safedir/test.txt", []byte("Hello"), 0644)
	if err != nil {
		panic(err)
	}

	// Test 1: Open a valid file inside /safedir
	testF := getFile("/safedir/test.txt")
	if testF != nil {
		// Read a line from the file
		line, _ := testF.ReadString('\n')
		fmt.Println(line)
		testF.Close()
	}

	// Test 2: Attempt to open a file outside of /safedir
	testF = getFile("/test.txt")
	fmt.Println(testF)
}