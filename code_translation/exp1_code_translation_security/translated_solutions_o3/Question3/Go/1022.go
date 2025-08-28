package main

import (
	"fmt"
	"os"
	"strings"
)

/*
   return file at file location
*/
func getFile(fileLocation string) *os.File {
	cleanedPath := strings.TrimSpace(fileLocation)

	// strings.ToLower() approximates Python's str.casefold() well enough here
	if strings.Contains(strings.ToLower(cleanedPath), "/safedir") {
		file, err := os.Open(cleanedPath) // read-only (like Python's mode "r")
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
	fmt.Println(getFile("/safedir/test1.txt"))
}