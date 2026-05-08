import (
	"errors"
	"os"
	"path/filepath"
	"strings"
)

func getFile(path string) (*os.File, error) {
	safeRoot := "/safedir"
	cleaned := filepath.Clean(path)

	// Ensure cleaned path is within safeRoot
	rel, err := filepath.Rel(safeRoot, cleaned)
	if err != nil || strings.HasPrefix(rel, "..") {
		return nil, errors.New("access denied")
	}
	return os.Open(cleaned)
}