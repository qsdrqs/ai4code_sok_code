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
	valid := validateDir(dir)
	if valid == false {
		return nil
	}
	file, err := os.Create(dir)
	if err != nil {
		return nil
	}
	return file
}

func main() {
	fmt.Println(createFile("test"))
	fmt.Println(createFile("/safedir/testAgain1.txt"))
}