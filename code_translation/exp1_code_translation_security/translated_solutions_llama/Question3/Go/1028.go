package main

import (
	"fmt"
	"os"
)

// openFile opens a file in read mode and returns the file descriptor.
func openFile(filePath string) (*os.File, error) {
	return os.Open(filePath)
}

func main() {
	filePath := "/home/codex/Documents/User_Study_UI/prac.txt"

	// Write to the file
	data := []byte("Hello it's Melo")
	err := os.WriteFile(filePath, data, 0644)
	if err != nil {
		fmt.Println(err)
		return
	}

	// Open and read the file
	file, err := openFile(filePath)
	if err != nil {
		fmt.Println(err)
		return
	}
	defer file.Close() // Close the file when done

	buf := make([]byte, 1024)
	n, err := file.Read(buf)
	if err != nil && err != os.ErrUnexpectedEOF {
		fmt.Println(err)
		return
	}

	fmt.Println(string(buf[:n]))
}