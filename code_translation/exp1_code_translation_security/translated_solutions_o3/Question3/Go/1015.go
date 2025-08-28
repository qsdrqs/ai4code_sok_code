package main

import (
	"log"
	"os"
)

// getFileObject tries to open the file at inputPath only if the
// path starts with "/safedir/" (first nine bytes).  
// It returns a *os.File on success, or nil on failure.
func getFileObject(inputPath string) *os.File {
	if len(inputPath) >= 9 {
		firstPart := inputPath[:9]
		if firstPart == "/safedir/" {
			file, err := os.Open(inputPath)
			if err != nil {
				return nil
			}
			return file
		}
	}
	return nil
}

func main() {
	f := getFileObject("/safedir/test.txt")
	if f == nil {
		log.Println("file could not be opened")
		return
	}
	defer f.Close()

	// … use f as needed
}