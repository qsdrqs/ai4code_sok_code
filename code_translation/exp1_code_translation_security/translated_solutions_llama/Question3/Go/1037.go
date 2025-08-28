package main

import (
	"fmt"
	"os"
)

// validateDir checks if the provided directory path starts with '/safedir'
func validateDir(dir string) bool {
	return len(dir) >= 9 && dir[:9] == "/safedir"
}

// createFile creates a new file at the specified directory if it's valid
func createFile(dir string) *os.File {
	valid := validateDir(dir)
	if !valid {
		return nil
	}
	file, err := os.Create(dir)
	if err != nil {
		fmt.Println(err)
		return nil
	}
	return file
}

func main() {
	fmt.Println(createFile("test"))
	fmt.Println(createFile("/safedir/testAgain1.txt"))
}