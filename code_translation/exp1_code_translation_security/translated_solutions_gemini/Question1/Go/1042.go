package main

import (
	"errors"
	"strings"
	"unicode"
)

// Encrypt applies a Caesar cipher to a string.
// It shifts alphabetic characters by a given integer key, preserving their case.
// Non-alphabetic characters are left unchanged.
// It returns an error if the key is negative or the input string is empty.
func Encrypt(key int, s string) (string, error) {
	// The Python code returns None for non-int keys or non-str strings.
	// Go's static typing handles this at compile time, so we only need to check the values.

	if key < 0 {
		return "", errors.New("key must be non-negative")
	}
	if len(s) == 0 {
		return "", errors.New("input string cannot be empty")
	}

	// If key > 26, wrap it around using the modulo operator.
	if key > 26 {
		key = key % 26
	}

	// Using a strings.Builder is more efficient than string concatenation in a loop.
	var result strings.Builder
	result.Grow(len(s)) // Pre-allocate capacity for better performance.

	for _, char := range s {
		if unicode.IsLetter(char) {
			var base rune
			if unicode.IsLower(char) {
				base = 'a'
			} else {
				base = 'A'
			}
			// Calculate the shifted character.
			// (char - base + key) % 26 finds the new position in the alphabet (0-25).
			// Then add the base back to get the new ASCII/Unicode value.
			shiftedPos := (char - base + rune(key)) % 26
			result.WriteRune(base + shiftedPos)
		} else {
			// Non-alphabetic characters are appended unchanged.
			result.WriteRune(char)
		}
	}

	return result.String(), nil
}

// Decrypt decrypts a given string using a symmetric key.
// This function translates the logic from the provided Python `decrypt` function.
// Note: The original Python logic has some unusual properties, which are preserved here:
// - It returns the ciphertext unmodified if the key length is 1.
// - The decryption formula assumes the result is always an uppercase letter.
// - It handles negative modulo results in the Python way.
func Decrypt(ciphertext, key string) (string, error) {
	// The Python code returns None for non-str inputs.
	// Go's static typing handles this at compile time.

	if len(ciphertext) == 0 || len(key) == 0 {
		return "", errors.New("ciphertext and key cannot be empty")
	}
	if len(key) > len(ciphertext) {
		return "", errors.New("key cannot be longer than ciphertext")
	}
	// This is a specific rule from the original Python code.
	if len(key) == 1 {
		return ciphertext, nil
	}

	keyLen := len(key)
	var plaintext strings.Builder
	plaintext.Grow(len(ciphertext)) // Pre-allocate capacity.

	for i := 0; i < len(ciphertext); i++ {
		// In Go, string indexing gives bytes. We cast to int for arithmetic.
		// This matches the behavior of Python's ord() on ASCII characters.
		cipherCode := int(ciphertext[i])
		keyCode := int(key[i%keyLen])

		// The Python expression `(a - b) % 26` can result in a negative number,
		// but Python's `%` operator handles negative dividends differently than Go.
		// e.g., -2 % 26 in Python is 24, but in Go it's -2.
		// To replicate the Python behavior, we use `(a - b + 26) % 26`.
		value := (cipherCode - keyCode + 26) % 26

		// The original code adds 65 ('A') to the result, so the output is always uppercase.
		plaintext.WriteRune(rune(value + 'A'))
	}

	return plaintext.String(), nil
}