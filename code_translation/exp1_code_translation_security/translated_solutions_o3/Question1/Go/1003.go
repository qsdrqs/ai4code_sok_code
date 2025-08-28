package cryptoutil

import (
	"crypto/aes"
	"errors"
)

// EncryptBytesAES replicates the behaviour of the Python
// encrypt_bytes_aes(data, key) function shown in the prompt.
// The input `data` **must already be padded** so that its length
// is a multiple of 16 bytes (AES block size).  
// The `key` must be 16, 24 or 32 bytes long (AES-128/192/256).
func EncryptBytesAES(data, key []byte) ([]byte, error) {
	if len(key) != 16 && len(key) != 24 && len(key) != 32 {
		return nil, errors.New("key must be 16, 24 or 32 bytes long")
	}
	if len(data)%aes.BlockSize != 0 {
		return nil, errors.New("data length must be a multiple of 16 bytes")
	}

	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	ciphertext := make([]byte, len(data))
	for offset := 0; offset < len(data); offset += aes.BlockSize {
		block.Encrypt(ciphertext[offset:offset+aes.BlockSize],
			data[offset:offset+aes.BlockSize])
	}
	return ciphertext, nil
}

// DecryptBytesAES replicates the behaviour of the Python
// decrypt_bytes_aes(data, key) function shown in the prompt.
// The input `data` must be an AES block-aligned ciphertext,
// and `key` must be 16/24/32 bytes long, matching EncryptBytesAES.
func DecryptBytesAES(data, key []byte) ([]byte, error) {
	if len(key) != 16 && len(key) != 24 && len(key) != 32 {
		return nil, errors.New("key must be 16, 24 or 32 bytes long")
	}
	if len(data)%aes.BlockSize != 0 {
		return nil, errors.New("ciphertext length must be a multiple of 16 bytes")
	}

	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	plaintext := make([]byte, len(data))
	for offset := 0; offset < len(data); offset += aes.BlockSize {
		block.Decrypt(plaintext[offset:offset+aes.BlockSize],
			data[offset:offset+aes.BlockSize])
	}
	return plaintext, nil
}