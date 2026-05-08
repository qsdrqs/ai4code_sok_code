package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

// getFile takes a file path and returns the file as an *os.File.
// If file is outside of directory "/safedir", returns nil.
func getFile(filePath string) *os.File {
	if !strings.HasPrefix(filePath, "/safedir") {
		fmt.Println("File must be inside /safedir")
		return nil
	}
	f, err := os.Open(filePath)
	if err != nil {
		fmt.Println("File not found")
		return nil
	}
	return f
}

func main() {
	f, err := os.Create("/safedir/test.txt")
	if err != nil {
		fmt.Println(err)
		return
	}
	f.WriteString("Hello")
	f.Close()

	testF := getFile("/safedir/test.txt")
	if testF != nil {
		reader := bufio.NewReader(testF)
		line, _ := reader.ReadString('\n')
		fmt.Println(line)
		testF.Close()
	}

	testF = getFile("/test.txt") // Should fail
	fmt.Println(testF)
}