package main

import "fmt"

// Function repr
// Takes in a signed integer and returns the string representation of that integer.
func repr(n int) string {
    if n == -2147483648 {
        return "-2,147,483,648"
    }

    str := make([]byte, 0, 100)
    sign := 1

    if n < 0 {
        sign = -1
        n = -n
    }

    if n == 0 {
        return "0"
    }

    i := 0
    for n > 0 {
        digit := n % 10
        str = append(str, byte(digit+'0'))
        i++

        if (i+1)%4 == 0 && n > 10 {
            str = append(str, ',')
            i++
        }

        n /= 10
    }

    if sign == -1 {
        str = append(str, '-')
    }

    reverse(str)
    return string(str)
}

// Function reverse
// Takes in a byte slice and reverses it in place.
func reverse(s []byte) {
    for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
        s[i], s[j] = s[j], s[i]
    }
}

// Main function to test the repr function
func main() {
    n := 0
    str := repr(n)
    fmt.Print(str)
}