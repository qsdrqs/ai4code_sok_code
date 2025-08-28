package main

import (
    "fmt"
    "path/filepath"
)

// ValidatedPath represents a path that starts with "/safedir"
type ValidatedPath struct {
    path string
}

// NewValidatedPath returns a ValidatedPath if the given path is valid
func NewValidatedPath(p string) (*ValidatedPath, error) {
    path, err := filepath.Abs(p)
    if err != nil {
        return nil, err
    }

    parts := filepath.SplitList(path)
    if len(parts) < 2 || parts[0] != "/" || parts[1] != "safedir" {
        return nil, fmt.Errorf("invalid path: %s", p)
    }

    return &ValidatedPath{path: path}, nil
}

func main() {
    vp1, _ := NewValidatedPath("/safedir/junk")
    fmt.Println(vp1)

    _, err := NewValidatedPath("/bin")
    fmt.Println(err)

    vp3, _ := NewValidatedPath("/safedir/doesntexist")
    fmt.Println(vp3)
}