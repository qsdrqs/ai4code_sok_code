package main

import (
	"fmt"
	"os"
)

// checkDir checks if the provided path starts with "/safedir"
func checkDir(path string) bool {
	return path[:8] == "/safedir"
}

// getFileObject returns a file object for the provided path
func getFileObject(path string) (*os.File, error) {
	if checkDir(path) {
		file, err := os.Open(path)
		if err != nil {
			fmt.Println("Error")
			os.Exit(1)
		}
		return file, nil
	} else {
		os.Exit(1)
		return nil, fmt.Errorf("invalid directory")
	}
}

func main() {
	file, err := getFileObject("/safedir/test1.txt")
	if err != nil {
		return
	}
	defer file.Close() // Close the file when done
}