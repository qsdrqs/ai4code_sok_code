package main

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"crypto/hmac"
	"crypto/rand"
	"crypto/sha256"
	"crypto/subtle"
	"encoding/base64"
	"encoding/binary"
	"errors"
	"fmt"
	"io"
	"time"
)

// fernetVersion is the required first byte of a Fernet token.
const fernetVersion = 0x80

// encrypt implements the Fernet specification for encrypting a message.
// It uses AES-128-CBC for encryption and HMAC-SHA256 for authentication.
func encrypt(message []byte, key []byte) ([]byte, error) {
	// 1. Validate the key
	if len(key) != 32 {
		return nil, errors.New("encryption key must be 32 bytes")
	}
	signingKey := key[:16]
	encryptionKey := key[16:]

	// 2. Get current timestamp (big-endian uint64)
	timestamp := uint64(time.Now().Unix())
	tsBytes := make([]byte, 8)
	binary.BigEndian.PutUint64(tsBytes, timestamp)

	// 3. Generate a random 16-byte IV
	iv := make([]byte, aes.BlockSize)
	if _, err := io.ReadFull(rand.Reader, iv); err != nil {
		return nil, fmt.Errorf("failed to generate IV: %w", err)
	}

	// 4. Pad the message using PKCS7
	paddedMessage, err := pkcs7Pad(message, aes.BlockSize)
	if err != nil {
		return nil, err
	}

	// 5. Encrypt the padded message with AES-128-CBC
	block, err := aes.NewCipher(encryptionKey)
	if err != nil {
		return nil, fmt.Errorf("failed to create AES cipher: %w", err)
	}
	ciphertext := make([]byte, len(paddedMessage))
	mode := cipher.NewCBCEncrypter(block, iv)
	mode.CryptBlocks(ciphertext, paddedMessage)

	// 6. Create the payload to be signed
	var toSign bytes.Buffer
	toSign.WriteByte(fernetVersion)
	toSign.Write(tsBytes)
	toSign.Write(iv)
	toSign.Write(ciphertext)

	// 7. Calculate the HMAC-SHA256 signature
	mac := hmac.New(sha256.New, signingKey)
	mac.Write(toSign.Bytes())
	signature := mac.Sum(nil)

	// 8. Concatenate the signature to the payload
	finalPayload := append(toSign.Bytes(), signature...)

	// 9. Base64URL encode the result
	encoded := make([]byte, base64.URLEncoding.EncodedLen(len(finalPayload)))
	base64.URLEncoding.Encode(encoded, finalPayload)

	return encoded, nil
}

// decrypt implements the Fernet specification for decrypting a token.
// It verifies the HMAC-SHA256 signature before decrypting with AES-128-CBC.
func decrypt(token []byte, key []byte) ([]byte, error) {
	// 1. Validate the key
	if len(key) != 32 {
		return nil, errors.New("decryption key must be 32 bytes")
	}
	signingKey := key[:16]
	encryptionKey := key[16:]

	// 2. Base64URL decode the token
	decoded, err := base64.URLEncoding.DecodeString(string(token))
	if err != nil {
		return nil, fmt.Errorf("failed to decode base64: %w", err)
	}

	// 3. Check for minimum length (Version + Timestamp + IV + HMAC)
	// 1 + 8 + 16 + 32 = 57 bytes
	if len(decoded) < 57 {
		return nil, errors.New("invalid token: too short")
	}

	// 4. Extract the signature and the payload to verify
	signature := decoded[len(decoded)-32:]
	payload := decoded[:len(decoded)-32]

	// 5. Recalculate the HMAC and verify it in constant time
	mac := hmac.New(sha256.New, signingKey)
	mac.Write(payload)
	expectedSignature := mac.Sum(nil)

	if subtle.ConstantTimeCompare(signature, expectedSignature) != 1 {
		return nil, errors.New("invalid signature: HMAC mismatch")
	}

	// 6. Parse the verified payload
	// Check version byte
	if payload[0] != fernetVersion {
		return nil, fmt.Errorf("invalid token version: got %x, want %x", payload[0], fernetVersion)
	}

	// Note: The original Fernet spec includes a TTL check on the timestamp.
	// For a direct translation of the Python function, we omit it, but in a
	// real-world application, you should validate the timestamp here.
	// timestamp := binary.BigEndian.Uint64(payload[1:9])

	iv := payload[9:25]
	ciphertext := payload[25:]

	// 7. Decrypt the ciphertext with AES-128-CBC
	block, err := aes.NewCipher(encryptionKey)
	if err != nil {
		return nil, fmt.Errorf("failed to create AES cipher: %w", err)
	}

	// CBC decryption requires the ciphertext length to be a multiple of the block size.
	if len(ciphertext)%aes.BlockSize != 0 {
		return nil, errors.New("ciphertext is not a multiple of the block size")
	}

	decrypted := make([]byte, len(ciphertext))
	mode := cipher.NewCBCDecrypter(block, iv)
	mode.CryptBlocks(decrypted, ciphertext)

	// 8. Unpad the decrypted message
	message, err := pkcs7Unpad(decrypted, aes.BlockSize)
	if err != nil {
		return nil, fmt.Errorf("failed to unpad message: %w", err)
	}

	return message, nil
}

// --- Helper Functions ---

// pkcs7Pad pads the data to a multiple of blockSize using PKCS7 padding.
func pkcs7Pad(data []byte, blockSize int) ([]byte, error) {
	if blockSize <= 0 || blockSize > 255 {
		return nil, fmt.Errorf("invalid block size: %d", blockSize)
	}
	padding := blockSize - len(data)%blockSize
	padtext := bytes.Repeat([]byte{byte(padding)}, padding)
	return append(data, padtext...), nil
}

// pkcs7Unpad removes PKCS7 padding from the data.
func pkcs7Unpad(data []byte, blockSize int) ([]byte, error) {
	length := len(data)
	if length == 0 {
		return nil, errors.New("cannot unpad empty data")
	}
	if length%blockSize != 0 {
		return nil, errors.New("data is not block-aligned")
	}
	unpadding := int(data[length-1])
	if unpadding > length || unpadding == 0 {
		return nil, errors.New("invalid padding")
	}
	// Check that all padding bytes are correct
	for i := 0; i < unpadding; i++ {
		if data[length-1-i] != byte(unpadding) {
			return nil, errors.New("invalid padding byte")
		}
	}
	return data[:(length - unpadding)], nil
}

// --- Example Usage ---

func main() {
	// In Python, Fernet.generate_key() creates a 32-byte random key and
	// then base64URL-encodes it for display. The functions themselves
	// expect the raw 32 bytes after decoding.
	// We will generate a raw 32-byte key here.
	key := make([]byte, 32)
	if _, err := rand.Read(key); err != nil {
		panic(fmt.Sprintf("failed to generate key: %v", err))
	}

	// The message to encrypt
	msg := []byte("This is a secret message.")

	fmt.Printf("Original Message: %s\n", msg)
	fmt.Printf("Encryption Key (base64): %s\n\n", base64.URLEncoding.EncodeToString(key))

	// Encrypt the message
	ciphertext, err := encrypt(msg, key)
	if err != nil {
		panic(fmt.Sprintf("Encryption failed: %v", err))
	}
	fmt.Printf("Encrypted Token: %s\n", ciphertext)

	// Decrypt the message
	decryptedMsg, err := decrypt(ciphertext, key)
	if err != nil {
		panic(fmt.Sprintf("Decryption failed: %v", err))
	}
	fmt.Printf("Decrypted Message: %s\n\n", decryptedMsg)

	// --- Test failure case (wrong key) ---
	fmt.Println("--- Testing Decryption with Wrong Key ---")
	wrongKey := make([]byte, 32)
	if _, err := rand.Read(wrongKey); err != nil {
		panic(fmt.Sprintf("failed to generate wrong key: %v", err))
	}

	_, err = decrypt(ciphertext, wrongKey)
	if err != nil {
		fmt.Printf("Successfully caught error: %v\n", err)
	} else {
		fmt.Println("Error: Decryption succeeded with the wrong key!")
	}
}