package main

import (
	"fmt"
	"os"
	"strings"
)

// return file at file location
func getFile(fileLocation string) *os.File {
	if strings.Contains(strings.ToLower(strings.TrimSpace(fileLocation)), "/safedir") {
		file, err := os.Open(fileLocation)
		if err != nil {
			fmt.Printf("Error opening file: %v\n", err)
			return nil
		}
		return file
	}
	fmt.Println("Provided File location is inaccessible")
	return nil
}

func main() {
	file := getFile("/safedir/test1.txt")
	if file != nil {
		defer file.Close() // Important: close the file when done
		fmt.Println(file)
	} else {
		fmt.Println("<nil>")
	}
}