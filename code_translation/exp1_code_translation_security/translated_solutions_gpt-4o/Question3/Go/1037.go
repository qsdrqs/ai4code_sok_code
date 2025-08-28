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
	file, err := os.Create(dir)
	if err != nil {
		fmt.Println("Error creating file:", err)
		return nil
	}
	return file
}

func main() {
	fmt.Println(createFile("test"))
	fmt.Println(createFile("/safedir/testAgain1.txt"))
}