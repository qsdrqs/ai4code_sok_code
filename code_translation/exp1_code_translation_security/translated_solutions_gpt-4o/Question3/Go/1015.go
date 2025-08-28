package main

import (
	"fmt"
	"os"
)

func getFileObject(inputPath string) (*os.File, error) {
	firstPart := inputPath[0:9]
	if firstPart == "/safedir/" {
		file, err := os.Open(inputPath)
		if err != nil {
			return nil, err
		}
		return file, nil
	}
	return nil, fmt.Errorf("invalid path")
}

func main() {
	file, err := getFileObject("/safedir/test.txt")
	if err != nil {
		fmt.Println("Error:", err)
		return
	}
	defer file.Close()

	// You can add additional code here to work with the file if needed
}