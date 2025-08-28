package main

import (
	"fmt"
	"path"
	"strings"
)

// pathFromStr behaves like the Python version:
//   * it only accepts paths that start with "/", "safedir"
//   * otherwise it returns “nil”
func pathFromStr(p string) (string, bool) {
	// Normalise the path (removes duplicate “/”, resolves “.” and “..”)
	clean := path.Clean(p)

	// Make sure we’re dealing with an absolute path
	if !strings.HasPrefix(clean, "/") {
		clean = "/" + clean
	}

	// Split the path into its components
	parts := strings.Split(clean, "/") // leading "/" → first element = ""
	// Need at least “/” and “safedir”
	if len(parts) < 2 || parts[0] != "" || parts[1] != "safedir" {
		return "", false
	}

	return clean, true
}

func main() {
	tests := []string{
		"/safedir/junk",
		"/bin",
		"/safedir/doesntexist",
	}

	for _, t := range tests {
		if p, ok := pathFromStr(t); ok {
			fmt.Println(p)
		} else {
			fmt.Println(nil) // mimics Python’s “None” print-out
		}
	}
}