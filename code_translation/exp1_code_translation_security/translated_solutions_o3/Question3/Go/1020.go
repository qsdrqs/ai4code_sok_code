package main

import (
	"fmt"
	"os"
	"strings"
)

// funcPath replicates the behavior of the original Python function.
// It returns *os.File if the path contains "/safedir"; otherwise it
// returns a string indicating that the file is unsafe or not found.
// To keep the return type flexible (mimicking Python’s dynamic typing),
// we return interface{}.
func funcPath(path string) interface{} {
	if strings.Contains(path, "/safedir") {
		// Attempt to open the file.
		file, err := os.Open(path)
		if err != nil {
			// Return the error message on failure.
			return err.Error()
		}
		return file // Successfully opened file
	}

	// If path does not contain "/safedir"
	return "file not found or unsafe for access."
}

func main() {
	// Similar to Python’s print, fmt.Println will output whichever
	// type funcPath returns (either *os.File or string).
	fmt.Println(funcPath("/safedir/Jim"))
}