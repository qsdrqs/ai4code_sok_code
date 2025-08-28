package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

// getFile replicates the Python get_file function.
// It returns *os.File if the given path starts with "/safedir" and
// the file exists; otherwise it prints an error message and returns nil.
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
	// Ensure /safedir exists
	if err := os.MkdirAll("/safedir", 0o755); err != nil {
		panic(err)
	}

	// Create /safedir/test.txt and write "Hello"
	f, err := os.Create("/safedir/test.txt")
	if err != nil {
		panic(err)
	}
	_, _ = f.WriteString("Hello")
	f.Close()

	// Read first line using getFile
	testF := getFile("/safedir/test.txt")
	if testF != nil {
		reader := bufio.NewReader(testF)
		line, _ := reader.ReadString('\n') // Read until newline (or EOF)
		fmt.Print(line)                    // Prints "Hello"
		testF.Close()
	}

	// Attempt to open an invalid path
	testF = getFile("/test.txt") // Should fail
	fmt.Println(testF)           // Prints "<nil>"
}