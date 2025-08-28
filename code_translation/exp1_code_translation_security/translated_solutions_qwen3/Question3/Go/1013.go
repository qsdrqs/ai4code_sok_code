package main

import (
	"os"
	"path/filepath"
	"runtime"
	"strings"
)

// normCase mimics Python's os.path.normcase behavior:
// - On Windows: converts path to lowercase and uses backslashes.
// - On other OSes: returns the path unchanged.
func normCase(path string) string {
	if runtime.GOOS == "windows" {
		return strings.ToLower(filepath.FromSlash(path))
	}
	return path
}

// commonPrefix returns the longest common prefix of two paths.
// This is equivalent to Python's os.path.commonpath for two paths.
func commonPrefix(a, b string) string {
	aComps := strings.Split(a, string(filepath.Separator))
	bComps := strings.Split(b, string(filepath.Separator))

	min := len(aComps)
	if len(bComps) < min {
		min = len(bComps)
	}

	var common []string
	for i := 0; i < min; i++ {
		if aComps[i] == bComps[i] {
			common = append(common, aComps[i])
		} else {
			break
		}
	}

	return filepath.Join(common...)
}

// inSafe checks if the file at the given path is within the safe directory.
func inSafe(fname string) bool {
	safeDir := "/safedir"

	// Resolve symlinks and get the real path
	realPath, err := filepath.EvalSymlinks(fname)
	if err != nil {
		return false
	}

	// Get the directory of the real path
	dir := filepath.Dir(realPath)

	// Normalize case for comparison
	safeDirNorm := normCase(safeDir)
	dirNorm := normCase(dir)

	// Compute the common prefix
	common := commonPrefix(safeDirNorm, dirNorm)

	// Return true if the common prefix is exactly the safe directory
	return common == safeDirNorm
}

// openFile attempts to open the file at the given path if it's in the safe directory.
func openFile(path string) *os.File {
	if inSafe(path) {
		file, err := os.Open(path)
		if err != nil {
			return nil
		}
		return file
	}
	return nil
}

// main demonstrates the usage of openFile.
func main() {
	file := openFile("/env.sh")
	if file != nil {
		defer file.Close()
		println("File opened successfully:", file.Name())
	} else {
		println("File is not in the safe directory or could not be opened.")
	}
}