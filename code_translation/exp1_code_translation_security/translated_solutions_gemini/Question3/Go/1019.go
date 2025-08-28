package main

import (
	"fmt"
	"io"
	"os"
	"path/filepath"
	"strings"
)

// getFile securely opens a file, ensuring it's within a "safedir" directory.
// It is a direct translation of the Python get_file function.
func getFile(path string) (*os.File, error) {
	// Python: normpath = os.path.normpath(path)
	// Go: filepath.Clean is the equivalent. It returns the shortest, canonical path.
	normpath := filepath.Clean(path)
	fmt.Println(normpath)

	// Python: if (not normpath.startswith("/safedir")):
	// Go: We use strings.HasPrefix. The path is checked against "safedir"
	// instead of "/safedir" to make this example portable and runnable anywhere.
	if !strings.HasPrefix(normpath, "safedir") {
		fmt.Println("out of bounds")
		// Python: return open("/dev/null", "r")
		// Go: os.Open opens a file for reading. os.DevNull is the cross-platform
		// name for the null device ("/dev/null" on Unix, "NUL" on Windows).
		return os.Open(os.DevNull)
	} else {
		fmt.Println("opening file", normpath)
		// Python: return open(normpath, "r+")
		// Go: "r+" (read/write) is achieved with os.OpenFile and the os.O_RDWR flag.
		// The third argument (0666) specifies file permissions if creating a new file.
		return os.OpenFile(normpath, os.O_RDWR, 0666)
	}
}

func main() {
	// --- Setup for a runnable example ---
	// The Python script assumes files and directories exist. To make this Go
	// program self-contained, we create a temporary directory and file.
	const safeDir = "safedir"
	const testFile = "safedir/test1.txt"

	// Create the directory; os.MkdirAll is like `mkdir -p`.
	if err := os.MkdirAll(safeDir, 0755); err != nil {
		fmt.Printf("Setup failed: could not create directory: %v\n", err)
		return
	}
	// Clean up the created directory and its contents when main finishes.
	defer os.RemoveAll(safeDir)

	// Create the test file with some content.
	content := []byte("Hello from the safe file!\n")
	if err := os.WriteFile(testFile, content, 0644); err != nil {
		fmt.Printf("Setup failed: could not write file: %v\n", err)
		return
	}
	// --- End of Setup ---

	// Python: f = get_file("/safedir/test1.txt")
	fmt.Println("--- Calling with a safe path ---")
	f, err := getFile("safedir/test1.txt")
	// Go functions can return multiple values, typically a result and an error.
	// It is standard practice to check for an error immediately.
	if err != nil {
		fmt.Printf("Error getting file: %v\n", err)
	} else {
		// 'defer' schedules a function call (in this case, f.Close()) to be
		// run just before the function (main) returns.
		defer f.Close()

		// Python: print(f.read())
		// Go: io.ReadAll reads the entire content from an io.Reader (like *os.File).
		fileContent, err := io.ReadAll(f)
		if err != nil {
			fmt.Printf("Error reading file: %v\n", err)
		} else {
			// The result is a byte slice ([]byte), which we convert to a string to print.
			fmt.Print(string(fileContent))
		}
	}

	// Python: g = get_file("/dev/rand0")
	fmt.Println("\n--- Calling with an unsafe path ---")
	g, err := getFile("/dev/rand0") // This path is "out of bounds".
	if err != nil {
		fmt.Printf("Error getting file: %v\n", err)
	} else {
		defer g.Close()

		// Python: print(g.read())
		// This will read from os.DevNull, which returns no data (EOF immediately).
		nullContent, err := io.ReadAll(g)
		if err != nil {
			fmt.Printf("Error reading from null device: %v\n", err)
		} else {
			// This will print an empty string.
			fmt.Print(string(nullContent))
		}
	}
}