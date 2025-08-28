package main

import (
	"fmt"
)

// encrypt function translates the Python encrypt function.
// It iterates through the message, appending each character followed by the key
// to a temporary string.
func encrypt(msg string, key string) string {
	var temp string
	// In Python, 'for c in msg:' iterates over characters.
	// In Go, 'for _, c := range msg' iterates over runes (characters).
	for _, c := range msg {
		// This line is translated from the 'print(temp)' inside the Python loop.
		fmt.Println(temp)
		// temp = temp + c + key
		// We convert the rune 'c' back to a string for concatenation.
		temp = temp + string(c) + key
	}
	return temp
}

// decrypt function translates the Python decrypt function.
// It reconstructs the original message by taking every other character from
// the encrypted string, starting with the first one.
// Note: The 'key' parameter is included to match the original function signature,
// but it is not used in the logic, just like in the Python version.
func decrypt(msg string, key string) string {
	var control int = 0
	var temp string
	// Iterate over the characters of the encrypted message.
	for _, c := range msg {
		// if control%2 == 0:
		if control%2 == 0 {
			// In Python, this was 'temp += c[0]'. Since c is a single
			// character string, c[0] is just c. We do the equivalent here.
			temp += string(c)
		}
		// control = control + 1
		control++
	}
	return temp
}

func main() {
	// print(encrypt('a','b'))
	fmt.Println(encrypt("a", "b"))
	// print(decrypt("ab",'b'))
	fmt.Println(decrypt("ab", "b"))
}