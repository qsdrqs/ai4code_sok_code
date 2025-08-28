package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"math/big"
)

// signString signs data using ECDSA with SHA256
func signString(privateKey *ecdsa.PrivateKey, data []byte) ([]byte, error) {
	// Hash the data using SHA256
	hash := sha256.Sum256(data)
	
	// Sign the hash
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, hash[:])
	if err != nil {
		return nil, err
	}
	
	// Encode the signature in DER format (similar to Python's default format)
	signature := encodeDERSignature(r, s)
	return signature, nil
}

// encodeDERSignature encodes r and s values into DER format
func encodeDERSignature(r, s *big.Int) []byte {
	// This is a simplified DER encoding for ECDSA signature
	// In production, you might want to use a proper ASN.1 library
	
	rBytes := r.Bytes()
	sBytes := s.Bytes()
	
	// Add padding if the first byte has high bit set (to ensure positive integer)
	if len(rBytes) > 0 && rBytes[0]&0x80 != 0 {
		rBytes = append([]byte{0x00}, rBytes...)
	}
	if len(sBytes) > 0 && sBytes[0]&0x80 != 0 {
		sBytes = append([]byte{0x00}, sBytes...)
	}
	
	// Build DER structure: SEQUENCE { INTEGER r, INTEGER s }
	var der []byte
	
	// INTEGER r
	der = append(der, 0x02) // INTEGER tag
	der = append(der, byte(len(rBytes))) // length
	der = append(der, rBytes...) // value
	
	// INTEGER s  
	der = append(der, 0x02) // INTEGER tag
	der = append(der, byte(len(sBytes))) // length
	der = append(der, sBytes...) // value
	
	// SEQUENCE wrapper
	result := []byte{0x30} // SEQUENCE tag
	result = append(result, byte(len(der))) // length
	result = append(result, der...) // content
	
	return result
}

// decodeDERSignature decodes DER signature back to r, s values (equivalent to utils.decode_dss_signature)
func decodeDERSignature(signature []byte) (*big.Int, *big.Int, error) {
	if len(signature) < 6 {
		return nil, nil, fmt.Errorf("signature too short")
	}
	
	if signature[0] != 0x30 {
		return nil, nil, fmt.Errorf("invalid DER signature")
	}
	
	// Skip SEQUENCE header
	pos := 2
	
	// Read r
	if signature[pos] != 0x02 {
		return nil, nil, fmt.Errorf("invalid r integer")
	}
	pos++
	rLen := int(signature[pos])
	pos++
	r := new(big.Int).SetBytes(signature[pos : pos+rLen])
	pos += rLen
	
	// Read s
	if signature[pos] != 0x02 {
		return nil, nil, fmt.Errorf("invalid s integer")
	}
	pos++
	sLen := int(signature[pos])
	pos++
	s := new(big.Int).SetBytes(signature[pos : pos+sLen])
	
	return r, s
}

// Example usage (equivalent to the commented Python code)
func main() {
	// Generate a private key using P-384 curve (equivalent to SECP384R1)
	privateKey, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	if err != nil {
		panic(err)
	}
	
	// Sign some data
	data := []byte("hello world")
	signature, err := signString(privateKey, data)
	if err != nil {
		panic(err)
	}
	
	// Decode the signature (equivalent to utils.decode_dss_signature)
	r, s, err := decodeDERSignature(signature)
	if err != nil {
		panic(err)
	}
	
	fmt.Printf("r: %s\n", r.String())
	fmt.Printf("s: %s\n", s.String())
}