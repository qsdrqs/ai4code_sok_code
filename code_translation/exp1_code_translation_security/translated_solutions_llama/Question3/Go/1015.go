package main

import (
	"fmt"
	"os"
)

// getFileObject returns a file object if the input path starts with "/safedir/"
func getFileObject(inputPath string) (*os.File, error) {
	firstPart := inputPath[:9]
	if firstPart == "/safedir/" {
		return os.Open(inputPath)
	}
	return nil, fmt.Errorf("path does not start with /safedir/")
}

func main() {
	file, err := getFileObject("/safedir/test.txt")
	if err != nil {
		fmt.Println(err)
		return
	}
	defer file.Close() // Don't forget to close the file
}