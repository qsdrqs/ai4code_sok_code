package main

import (
	"fmt"
	"os"
	"strings"
)

func pathToFile(path string) *os.File {
	if strings.Contains(path, "/safedir") {
		file, _ := os.Open(path)
		return file
	}
	return nil
}

func main() {
	fmt.Println(pathToFile("/safedir/random.txt"))
	fmt.Println(pathToFile("/notsafedir/random.txt"))
}