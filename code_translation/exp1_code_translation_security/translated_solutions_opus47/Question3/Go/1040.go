package main

import (
	"fmt"
	"os"
	"strings"
)

// createFileObject creates file object for path
func createFileObject(path string) *os.File {
	if strings.HasPrefix(path, "/safedir") {
		file, err := os.Open(path)
		if err != nil {
			return nil
		}
		return file
	}
	return nil
}

func main() {
	fmt.Println(createFileObject("/etc/passwd"))
}