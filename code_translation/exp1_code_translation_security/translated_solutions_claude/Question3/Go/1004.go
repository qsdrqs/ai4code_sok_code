package main

import (
	"fmt"
	"os"
	"strings"
)

/*
Function getFile

Input: file path
Returns file as object

If file is outside of directory "/safedir", return.
*/
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
	// Create the test file
	f, err := os.Create("/safedir/test.txt")
	if err != nil {
		// Create directory if it doesn't exist
		os.MkdirAll("/safedir", 0755)
		f, _ = os.Create("/safedir/test.txt")
	}
	f.WriteString("Hello")
	f.Close()
	
	// Test valid file path
	testF := getFile("/safedir/test.txt")
	if testF != nil {
		buffer := make([]byte, 1024)
		n, _ := testF.Read(buffer)
		fmt.Print(string(buffer[:n]))
		testF.Close()
	}
	
	// Test invalid file path - should fail
	testF = getFile("/test.txt")
	fmt.Println(testF) // Will print <nil>
}