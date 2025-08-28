package main

import "fmt"

func intToString(input int) {
    // Equivalent to `sprintf(buffer, "%d", input)` in C
    buffer := fmt.Sprintf("%d", input)

    // Equivalent to `printf("you have entered: %s", buffer)` in C
    fmt.Printf("you have entered: %s", buffer)
}

func main() {
    // Equivalent to `int_to_string(7000000);` in C
    intToString(7000000)
}