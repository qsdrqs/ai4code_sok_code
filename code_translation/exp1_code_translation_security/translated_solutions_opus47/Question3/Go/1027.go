package main

import (
	"errors"
	"fmt"
	"os"
	"strings"
)

func openFile(filePath string) (*os.File, error) {
	if !strings.HasPrefix(filePath, "/safedir") {
		return nil, errors.New("File is outside of /safedir")
	}

	fileObj, err := os.Open(filePath)
	if err != nil {
		fmt.Println("File not found")
		return nil, nil
	}
	return fileObj, nil
}

func main() {
	path, err := openFile("/safedir/Jim")
	if err != nil {
		panic(err)
	}
	fmt.Println(path)
}