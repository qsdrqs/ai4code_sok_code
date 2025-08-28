package main

import (
	"fmt"
	"os"
	"strings"
)

// create file object for path
func createFileObject(path string) *os.File {
	if strings.HasPrefix(path, "/safedir") {
		file, err := os.Open(path)
		if err != nil {
			return nil
		}
		return file
	} else {
		return nil
	}
}

func main() {
	file := createFileObject("/etc/passwd")
	fmt.Println(file)
}