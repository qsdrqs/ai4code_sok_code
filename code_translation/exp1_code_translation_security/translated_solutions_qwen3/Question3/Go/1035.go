package main

import (
	"fmt"
	"path"
	"strings"
)

// pathFromString checks if the input path starts with "/safedir".
// If it does, it returns the cleaned path string.
// Otherwise, it returns an empty string (equivalent to None in Python).
func pathFromString(p string) string {
	cleaned := path.Clean(p)
	components := strings.Split(cleaned, "/")

	// Check if the path starts with "/safedir"
	if len(components) >= 2 && components[0] == "" && components[1] == "safedir" {
		return cleaned
	}
	return ""
}

func main() {
	// Test cases
	fmt.Println(pathFromString("/safedir/junk"))         // Output: /safedir/junk
	fmt.Println(pathFromString("/bin"))                  // Output: (empty string)
	fmt.Println(pathFromString("/safedir/doesntexist"))  // Output: /safedir/doesntexist
}