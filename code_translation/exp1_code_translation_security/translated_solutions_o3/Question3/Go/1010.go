package main

import (
	"fmt"
	"os"
	"strings"
)

// openPath tries to open the file for writing after a few basic
// path-safety checks.  A nil *os.File and a non-nil error mean the check
// failed or the file could not be opened.
func openPath(filename string) (*os.File, error) {
	// Ensure the path starts with "/safedir/"
	if !strings.HasPrefix(filename, "/safedir/") {
		return nil, fmt.Errorf("openPath: path must start with /safedir/")
	}

	// Reject any appearance of "/../" inside the string.
	if strings.Contains(filename, "/../") {
		return nil, fmt.Errorf("openPath: directory traversal detected")
	}

	// Open the file for write-only, create if missing, truncate if exists
	f, err := os.OpenFile(
		filename,
		os.O_WRONLY|os.O_CREATE|os.O_TRUNC,
		0644,
	)
	if err != nil {
		return nil, err
	}
	return f, nil
}

func main() {
	// Example usage
	file, err := openPath("/safedir/Jim")
	if err != nil {
		fmt.Println("Error:", err)
		return
	}
	defer file.Close()

	fmt.Println("File opened successfully:", file.Name())
}