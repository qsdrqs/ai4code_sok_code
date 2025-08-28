import (
    "errors"
    "strings"
)

type File struct {
    path string
}

func getFile(path string) (*File, error) {
    if strings.HasPrefix(path, "/safedir") {
        return &File{path: path}, nil
    }
    return nil, errors.New("Access denied")
}