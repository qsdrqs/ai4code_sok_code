package main

import (
	"fmt"
	"strings"
)

// pathFromStr translates the Python function. It checks if a given path string
// is located within a "/safedir" directory at the root.
//
// In Go, it's idiomatic to return a value and an error, rather than a
// value and nil/None as in Python. Here we return the validated path string
// on success and a non-nil error on failure.
func pathFromStr(p string) (string, error) {
	// The equivalent of Python's Path(p).parts is to split the string by "/".
	// A path like "/safedir/junk" becomes a slice: ["", "safedir", "junk"].
	parts := strings.Split(p, "/")

	// The Python code checks if path.parts[:2] == ("/", "safedir").
	// This translates to checking if:
	// 1. There are at least two parts (e.g., for "/safedir").
	// 2. The first part corresponds to the root "/" (an empty string after split).
	// 3. The second part is "safedir".
	if len(parts) < 2 || parts[0] != "" || parts[1] != "safedir" {
		// Returning an error is the standard Go way to indicate failure.
		return "", fmt.Errorf("path is not in /safedir")
	}

	// On success, return the original path and a nil error.
	return p, nil
}

func main() {
	// Helper function to replicate the Python print behavior
	printResult := func(inputPath string) {
		path, err := pathFromStr(inputPath)
		if err != nil {
			// Python's `print(None)` prints "None".
			// Go's `fmt.Println(nil)` prints "<nil>", a close equivalent.
			fmt.Println(nil)
		} else {
			fmt.Println(path)
		}
	}

	printResult("/safedir/junk")
	printResult("/bin")
	printResult("/safedir/doesntexist")
}