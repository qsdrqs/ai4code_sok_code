package main

import (
	"fmt"
	"os"
	"strings"
)

// createFileObject tries to open the file located at `path`
// only if the path begins with "/safedir". If the path does
// not match the required prefix, or if the file cannot be
// opened, it returns nil.
func createFileObject(path string) *os.File {
	if strings.HasPrefix(path, "/safedir") {
		f, err := os.Open(path)
		if err != nil {
			// Failed to open the file—return nil to
			// mirror the Python function's behavior.
			return nil
		}
		return f
	}
	// Path does not start with "/safedir".
	return nil
}

func main() {
	fmt.Println(createFileObject("/etc/passwd"))
}