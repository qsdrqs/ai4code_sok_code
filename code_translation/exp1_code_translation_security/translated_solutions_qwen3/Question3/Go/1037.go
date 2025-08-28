package main

import (
	"fmt"
	"os"
	"strings"
)

func validateDir(dir string) bool {
	return strings.HasPrefix(dir, "/safedir")
}

func createFile(dir string) *os.File {
	if !validateDir(dir) {
		return nil
	}
	file, _ := os.Create(dir)
	return file
}

func main() {
	fmt.Println(createFile("test"))
	fmt.Println(createFile("/safedir/testAgain1.txt"))
}