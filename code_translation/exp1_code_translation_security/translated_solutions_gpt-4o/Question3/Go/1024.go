package main

import (
	"fmt"
	"os"
	"path/filepath"
	"strings"
)

func returnObject(path string) interface{} {
	rpath, err := filepath.Abs(path)
	if err != nil {
		fmt.Printf("Error: %v\n", err)
		return nil
	}

	if strings.HasPrefix(rpath, "/safedir") {
		return -1
	}

	fp, err := os.Create(path)
	if err != nil {
		fmt.Printf("Error: %v\n", err)
		return nil
	}

	return fp
}

func main() {
	fileName := "/tmp/foo"
	fmt.Println(returnObject(fileName))
}