package main

import (
	"fmt"
	"os"
)

// checkDir replicates the character-by-character prefix check
func checkDir(path string) bool {
	return len(path) >= 8 &&
		path[0] == '/' &&
		path[1] == 's' &&
		path[2] == 'a' &&
		path[3] == 'f' &&
		path[4] == 'e' &&
		path[5] == 'd' &&
		path[6] == 'i' &&
		path[7] == 'r'
}

// getFileObject behaves like the original Python version
func getFileObject(path string) *os.File {
	if checkDir(path) {
		fileObject, err := os.Open(path)
		if err != nil {
			fmt.Println("Error")
			os.Exit(1)
		}
		return fileObject
	}
	os.Exit(1) // path not allowed
	return nil // unreachable, but required for compilation
}

func main() {
	fmt.Println(getFileObject("/safedir/test1.txt"))
}