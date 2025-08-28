package main

import (
	"fmt"
	"os"
	"strings"
)

func openFile(filePath string) *os.File {
	// Ensure the file resides within /safedir.
	if !strings.HasPrefix(filePath, "/safedir") {
		panic("File is outside of /safedir")
	}

	fileObj, err := os.Open(filePath)
	if err != nil {
		fmt.Println("File not found")
		return nil
	}
	return fileObj
}

func main() {
	path := openFile("/safedir/Jim")
	fmt.Println(path)
}