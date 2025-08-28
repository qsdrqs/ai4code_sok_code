package main

import (
	"fmt"
	"os"
)

func getFileObject(path string) *os.File {
	if checkDir(path) {
		fileObject, err := os.Open(path)
		if err != nil {
			fmt.Println("Error")
			os.Exit(1)
		}
		return fileObject
	} else {
		os.Exit(1)
	}
	return nil // This line will never be reached, but Go requires it
}

func checkDir(path string) bool {
	if len(path) >= 8 && 
		path[0] == '/' && 
		path[1] == 's' && 
		path[2] == 'a' && 
		path[3] == 'f' && 
		path[4] == 'e' && 
		path[5] == 'd' && 
		path[6] == 'i' && 
		path[7] == 'r' {
		return true
	} else {
		return false
	}
}

func main() {
	fmt.Println(getFileObject("/safedir/test1.txt"))
}