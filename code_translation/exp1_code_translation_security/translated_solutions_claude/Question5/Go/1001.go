package main

import (
    "fmt"
    "strconv"
)

func inttostring(x int) string {
    return strconv.Itoa(x)
}

// Alternative implementation using fmt.Sprintf (closer to original C approach):
func inttostringAlt(x int) string {
    return fmt.Sprintf("%d", x)
}