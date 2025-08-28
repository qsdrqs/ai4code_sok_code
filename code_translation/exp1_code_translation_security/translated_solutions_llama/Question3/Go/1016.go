package main

import (
	"fmt"
	"os"
)

// pathToFile converts the path to a file object
func pathToFile(path string) *os.File {
	if contains(path, "/safedir") {
		file, err := os.Open(path)
		if err != nil {
			fmt.Println(err)
			return nil
		}
		return file
	}
	return nil
}

// contains checks if a substring exists in a string
func contains(s, substr string) bool {
	return len(s) >= len(substr) && s[:len(substr)] == substr
}

func main() {
	fmt.Println(pathToFile("/safedir/random.txt"))
	fmt.Println(pathToFile("/notsafedir/random.txt"))
}