package main

import (
	"errors"
	"fmt"
	"os"
	"strings"
)

// openFile translates the Python open_file function.
// In Go, functions that can fail typically return two values: the result and an error.
// This function returns a file handle (*os.File) and an error.
func openFile(filePath string) (*os.File, error) {
	// The check `startswith('/safedir')` is translated to `strings.HasPrefix`.
	// Note: For this example to run without root permissions, we check for a relative
	// path "safedir/" instead of the absolute "/safedir".
	if !strings.HasPrefix(filePath, "safedir/") {
		// `raise Exception` in Python is equivalent to returning a new error in Go.
		return nil, errors.New("File is outside of safedir")
	}

	// The `try...except IOError` block is handled by checking the error
	// returned from `os.Open`.
	fileObj, err := os.Open(filePath)
	if err != nil {
		// This block is equivalent to the `except IOError` block.
		// We print the message and return `nil` for both the file and the error
		// to mimic the Python code's behavior of printing and returning `None`.
		fmt.Println("File not found")
		return nil, nil
	}

	// If successful, return the file object and a nil error.
	return fileObj, nil
}

func main() {
	// --- Setup for a runnable example ---
	// This part creates a dummy directory and file so the success case can be demonstrated.
	// It is not part of the original Python snippet but is needed for a complete Go program.
	if err := os.MkdirAll("safedir", 0755); err != nil {
		panic(err)
	}
	// Create the file so `os.Open` can find it.
	f, err := os.Create("safedir/Jim")
	if err != nil {
		panic(err)
	}
	f.Close()
	// `defer` schedules this command to run at the end of the `main` function,
	// ensuring our temporary directory gets cleaned up.
	defer os.RemoveAll("safedir")
	// --- End of setup ---

	// This is the direct translation of the Python script's execution.
	// `path = open_file("/safedir/Jim")`
	file, err := openFile("safedir/Jim")

	// In Go, we must explicitly check the returned error.
	// This handles the case where the Python code would have raised an exception.
	if err != nil {
		fmt.Fprintf(os.Stderr, "An unrecoverable error occurred: %v\n", err)
		os.Exit(1)
	}

	// If the file was opened, it's good practice to ensure it gets closed.
	if file != nil {
		defer file.Close()
	}

	// `print(path)`
	// This will print the file handle (e.g., &{0xc00007a180}) if successful,
	// or `<nil>` if the file was not found, matching the Python behavior.
	fmt.Println(file)
}