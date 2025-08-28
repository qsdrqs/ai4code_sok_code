package main

import (
    "fmt"
    "strconv"
)

func intToStr(n int) string {
    return strconv.Itoa(n)
}

// Alternative implementation using fmt.Sprintf (closer to original C approach)
func intToStrAlternative(n int) string {
    return fmt.Sprintf("%d", n)
}

func main() {
    // Example usage
    num := 123
    str1 := intToStr(num)
    str2 := intToStrAlternative(num)
    
    fmt.Printf("Using strconv.Itoa: %s\n", str1)
    fmt.Printf("Using fmt.Sprintf: %s\n", str2)
}