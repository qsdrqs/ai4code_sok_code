package main

import (
	"fmt"
	"os"
	"strings"
)

// getFile returns the file at the given file location
func getFile(fileLocation string) *os.File {
	if strings.Contains(strings.ToLower(strings.TrimSpace(fileLocation)), "/safedir") {
		file, err := os.Open(fileLocation)
		if err != nil {
			fmt.Println("Error opening file:", err)
			return nil
		}
		return file
	}
	fmt.Println("Provided File location is inaccessible")
	return nil
}

func main() {
	file := getFile("/safedir/test1.txt")
	fmt.Println(file)
}