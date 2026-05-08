package main

import (
	"os"
)

func getFileObject(inputPath string) *os.File {
	firstPart := inputPath[0:9]
	if firstPart == "/safedir/" {
		file, _ := os.Open(inputPath)
		return file
	}
	return nil
}

func main() {
	getFileObject("/safedir/test.txt")
}