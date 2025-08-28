package main

import (
	"os"
)

// getFileObject checks if the inputPath starts with "/safedir/" and returns the opened file.
// If the path does not start with "/safedir/", it returns nil.
// If the file cannot be opened, it panics (mirroring Python's exception behavior).
func getFileObject(inputPath string) *os.File {
	if len(inputPath) >= 9 && inputPath[:9] == "/safedir/" {
		file, err := os.Open(inputPath)
		if err != nil {
			panic(err)
		}
		return file
	}
	return nil
}

func main() {
	// Call the function with the test path
	getFileObject("/safedir/test.txt")
}