package main

import (
	"errors"
	"fmt"
	"strings"
)

/*
Encrypt performs a classical Caesar–style shift.

It returns the encrypted string if every pre-condition is met,
otherwise an error is returned (mirrors the Python version that
returned None on failure).
*/
func Encrypt(key int, plainText string) (string, error) {
	// ------------------------------------------------------------------
	// Input validation ­– equivalent to the Python “return None” checks.
	// ------------------------------------------------------------------
	if key < 0 {
		return "", errors.New("key must be non-negative")
	}
	if len(plainText) == 0 {
		return "", errors.New("plainText may not be empty")
	}

	// The original Python version folded large keys back into [0, 25]
	if key > 26 {
		key %= 26
	}

	var builder strings.Builder
	for _, r := range plainText {
		switch {
		// a-z
		case r >= 'a' && r <= 'z':
			shifted := ((int(r-'a') + key) % 26) + 'a'
			builder.WriteRune(rune(shifted))

		// A-Z
		case r >= 'A' && r <= 'Z':
			shifted := ((int(r-'A') + key) % 26) + 'A'
			builder.WriteRune(rune(shifted))

		// Non-alphabetic characters are copied unchanged
		default:
			builder.WriteRune(r)
		}
	}

	return builder.String(), nil
}

/*
Decrypt reproduces the second Python function: a Vigenère-style
(one-time-pad–like) decryption that expects

    ciphertext  – the text to decrypt
    key         – the symmetric key used during encryption

The routine enforces the same constraints as the Python code and
returns an error when a rule is violated.
*/
func Decrypt(cipherText, key string) (string, error) {

	// -------------  Validation (mirrors the Python version) -------------
	if len(cipherText) == 0 || len(key) == 0 {
		return "", errors.New("cipherText and key must be non-empty")
	}
	if len(key) == 1 {
		// Python immediately returned the cipherText as-is.
		return cipherText, nil
	}
	if len(key) > len(cipherText) {
		return "", errors.New("key may not be longer than ciphertext")
	}

	// -------------  Core decryption routine -------------
	keyBytes := []byte(key)
	keyLen := len(keyBytes)
	cipherBytes := []byte(cipherText)

	var builder strings.Builder
	for i, c := range cipherBytes {
		value := (int(c) - int(keyBytes[i%keyLen])) % 26
		if value < 0 {
			value += 26 // Guarantee positivity – Go’s % keeps sign of dividend
		}
		builder.WriteByte(byte(value + 65)) // 65 == 'A'
	}

	return builder.String(), nil
}

/* -------------------------------------------------------------------- */

func main() {
	// quick demo
	enc, _ := Encrypt(3, "Hello, World!")
	fmt.Println(enc) // Khoor, Zruog!

	dec, _ := Decrypt("KHOOR", "KEY")
	fmt.Println(dec) // ?? depending on key/cipher
}