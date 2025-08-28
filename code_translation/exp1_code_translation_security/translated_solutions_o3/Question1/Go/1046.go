package main

import (
	"fmt"
)

// encrypt appends the key after every character of msg.
// It also prints the intermediate value of temp before each addition,
// matching the behaviour of the original Python function.
func encrypt(msg, key string) string {
	temp := ""

	for _, c := range msg { // iterate over each character (rune) in msg
		fmt.Println(temp)               // print current temp (before modification)
		temp += string(c) + key         // add current character and key
	}

	return temp
}

// decrypt rebuilds the original message by taking every second character
// (starting with the 1st) from the encrypted string.  The key parameter
// is kept for signature compatibility even though it isn't needed.
func decrypt(msg, key string) string {
	temp := ""

	for i, c := range msg {
		if i%2 == 0 {                   // keep every other character, starting with index 0
			temp += string(c)
		}
	}

	return temp
}

func main() {
	fmt.Println(encrypt("a", "b"))
	fmt.Println(decrypt("ab", "b"))
}