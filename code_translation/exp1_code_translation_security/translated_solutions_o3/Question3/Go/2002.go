package safefile

import (
	"os"
	"path/filepath"
)

// Path relative to /safedir, e.g. "hello/world" refers to /safedir/hello/world
func getFile(path string) (*os.File, error) {
	filename := filepath.Join("/safedir", path)
	return os.Open(filename)
}