package crypto

// Encrypt XOR-encrypts a string using a symmetric key.
// (If the key is empty, the input is returned unchanged.)
func Encrypt(plain, key string) string {
	if len(key) == 0 {
		return plain
	}

	out := make([]byte, len(plain))
	for i := 0; i < len(plain); i++ {
		out[i] = plain[i] ^ key[i%len(key)]
	}
	return string(out)
}

// Decrypt XOR-decrypts a string using the same symmetric key.
//
// Because XOR is its own inverse, decryption is identical to encryption.
func Decrypt(cipher, key string) string {
	return Encrypt(cipher, key)
}