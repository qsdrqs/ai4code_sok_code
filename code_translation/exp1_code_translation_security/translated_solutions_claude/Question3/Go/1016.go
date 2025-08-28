package main

import (
	"fmt"
	"os"
	"strings"
)

// pathToFile converts the path to a file object
func pathToFile(path string) *os.File {
	if strings.Contains(path, "/safedir") {
		file, err := os.Open(path)
		if err != nil {
			// In Python, open() would raise an exception if file doesn't exist
			// Here we return nil to match the Python behavior when file can't be opened
			return nil
		}
		return file
	}
	return nil
}

func main() {
	file1 := pathToFile("/safedir/random.txt")
	fmt.Println(file1)
	
	file2 := pathToFile("/notsafedir/random.txt")
	fmt.Println(file2)
}