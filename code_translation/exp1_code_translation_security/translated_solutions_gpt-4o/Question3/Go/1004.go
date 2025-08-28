package main

import (
	"fmt"
	"os"
	"strings"
)

// Function getFile
// Input: file path
// Returns file as *os.File object
// If file is outside of directory "/safedir", return nil.
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
	// Create a file in /safedir and write "Hello" to it
	err := os.MkdirAll("/safedir", os.ModePerm)
	if err != nil {
		fmt.Println("Error creating directory:", err)
		return
	}

	f, err := os.Create("/safedir/test.txt")
	if err != nil {
		fmt.Println("Error creating file:", err)
		return
	}
	defer f.Close()

	_, err = f.WriteString("Hello")
	if err != nil {
		fmt.Println("Error writing to file:", err)
		return
	}

	// Test getFile function
	testF := getFile("/safedir/test.txt")
	if testF != nil {
		defer testF.Close()
		buf := make([]byte, 5)
		_, err := testF.Read(buf)
		if err != nil {
			fmt.Println("Error reading file:", err)
		} else {
			fmt.Println(string(buf))
		}
	}

	// Test getFile with a file outside /safedir
	testF = getFile("/test.txt") // Should fail
	fmt.Println(testF)
}