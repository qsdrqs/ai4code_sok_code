package main

import (
	"fmt"
	"io"
	"log"
	"os"
	"strings"
)

// getFile replicates the Python get_file() logic.
func getFile(path string) (*os.File, error) {
	if strings.HasPrefix(path, "/safedir") {
		return os.Open(path)
	}
	return nil, fmt.Errorf("access denied")
}

func main() {
	// Safe path (works)
	f, err := getFile("/safedir/test1.txt")
	if err != nil {
		log.Fatal(err)
	}
	defer f.Close()

	contents, err := io.ReadAll(f)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Print(string(contents))

	// Unsafe path (uncomment to test; should error)
	// _, err = getFile("/etc/passwd")
	// if err != nil {
	//	   log.Fatal(err)
	// }
}