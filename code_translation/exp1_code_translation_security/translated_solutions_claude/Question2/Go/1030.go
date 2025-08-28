package main

import (
	"crypto/rand"
	"fmt"
	"math/big"

	"golang.org/x/crypto/sha3"
)

// ECDSA implementation for secp256k1
type Point struct {
	X, Y *big.Int
}

type Signature struct {
	R, S *big.Int
}

// secp256k1 parameters
var (
	// Field prime
	P = mustParseBig("0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFC2F")
	// Curve order
	N = mustParseBig("0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141")
	// Generator point
	Gx = mustParseBig("0x79BE667EF9DCBBAC55A06295CE870B07029BFCDB2DCE28D959F2815B16F81798")
	Gy = mustParseBig("0x483ADA7726A3C4655DA4FBFC0E1108A8FD17B448A68554199C47D08FFB10D4B8")
	G  = &Point{X: Gx, Y: Gy}
)

func mustParseBig(s string) *big.Int {
	n, ok := new(big.Int).SetString(s, 0)
	if !ok {
		panic("failed to parse big int: " + s)
	}
	return n
}

// Modular inverse using extended Euclidean algorithm
func modInverse(a, m *big.Int) *big.Int {
	return new(big.Int).ModInverse(a, m)
}

// Point addition on secp256k1
func pointAdd(p1, p2 *Point) *Point {
	if p1 == nil {
		return p2
	}
	if p2 == nil {
		return p1
	}
	if p1.X.Cmp(p2.X) == 0 {
		if p1.Y.Cmp(p2.Y) == 0 {
			return pointDouble(p1)
		}
		return nil // Point at infinity
	}

	// s = (y2 - y1) / (x2 - x1) mod p
	dy := new(big.Int).Sub(p2.Y, p1.Y)
	dx := new(big.Int).Sub(p2.X, p1.X)
	dx.Mod(dx, P)
	s := new(big.Int).Mul(dy, modInverse(dx, P))
	s.Mod(s, P)

	// x3 = s^2 - x1 - x2 mod p
	x3 := new(big.Int).Mul(s, s)
	x3.Sub(x3, p1.X)
	x3.Sub(x3, p2.X)
	x3.Mod(x3, P)

	// y3 = s(x1 - x3) - y1 mod p
	y3 := new(big.Int).Sub(p1.X, x3)
	y3.Mul(y3, s)
	y3.Sub(y3, p1.Y)
	y3.Mod(y3, P)

	return &Point{X: x3, Y: y3}
}

// Point doubling on secp256k1
func pointDouble(p *Point) *Point {
	if p == nil {
		return nil
	}

	// s = (3 * x^2) / (2 * y) mod p
	three := big.NewInt(3)
	two := big.NewInt(2)
	
	s := new(big.Int).Mul(p.X, p.X)
	s.Mul(s, three)
	
	denom := new(big.Int).Mul(two, p.Y)
	s.Mul(s, modInverse(denom, P))
	s.Mod(s, P)

	// x3 = s^2 - 2*x mod p
	x3 := new(big.Int).Mul(s, s)
	x3.Sub(x3, new(big.Int).Mul(two, p.X))
	x3.Mod(x3, P)

	// y3 = s(x - x3) - y mod p
	y3 := new(big.Int).Sub(p.X, x3)
	y3.Mul(y3, s)
	y3.Sub(y3, p.Y)
	y3.Mod(y3, P)

	return &Point{X: x3, Y: y3}
}

// Scalar multiplication using double-and-add
func pointMul(k *big.Int, p *Point) *Point {
	if k.Sign() == 0 {
		return nil
	}
	if k.Cmp(big.NewInt(1)) == 0 {
		return &Point{X: new(big.Int).Set(p.X), Y: new(big.Int).Set(p.Y)}
	}

	var result *Point
	addend := &Point{X: new(big.Int).Set(p.X), Y: new(big.Int).Set(p.Y)}
	
	for i := 0; i < k.BitLen(); i++ {
		if k.Bit(i) == 1 {
			result = pointAdd(result, addend)
		}
		addend = pointDouble(addend)
	}
	
	return result
}

func sha3_256Hash(msg string) *big.Int {
	hasher := sha3.New256()
	hasher.Write([]byte(msg))
	hashBytes := hasher.Sum(nil)
	return new(big.Int).SetBytes(hashBytes)
}

func signECDSAsecp256k1(msg string, privKey *big.Int) *Signature {
	msgHash := sha3_256Hash(msg)
	
	for {
		// Generate random k
		k, err := rand.Int(rand.Reader, N)
		if err != nil {
			panic(err)
		}
		if k.Sign() == 0 {
			continue
		}

		// Calculate r = (k * G).x mod n
		point := pointMul(k, G)
		if point == nil {
			continue
		}
		r := new(big.Int).Mod(point.X, N)
		if r.Sign() == 0 {
			continue
		}

		// Calculate s = k^(-1) * (msgHash + r * privKey) mod n
		kInv := modInverse(k, N)
		s := new(big.Int).Mul(r, privKey)
		s.Add(s, msgHash)
		s.Mul(s, kInv)
		s.Mod(s, N)
		
		if s.Sign() == 0 {
			continue
		}

		return &Signature{R: r, S: s}
	}
}

func verifyECDSAsecp256k1(msg string, signature *Signature, pubKey *Point) bool {
	msgHash := sha3_256Hash(msg)
	
	// Check if r and s are in valid range
	if signature.R.Sign() <= 0 || signature.R.Cmp(N) >= 0 {
		return false
	}
	if signature.S.Sign() <= 0 || signature.S.Cmp(N) >= 0 {
		return false
	}

	// Calculate w = s^(-1) mod n
	w := modInverse(signature.S, N)
	
	// Calculate u1 = msgHash * w mod n
	u1 := new(big.Int).Mul(msgHash, w)
	u1.Mod(u1, N)
	
	// Calculate u2 = r * w mod n
	u2 := new(big.Int).Mul(signature.R, w)
	u2.Mod(u2, N)
	
	// Calculate point = u1 * G + u2 * pubKey
	point1 := pointMul(u1, G)
	point2 := pointMul(u2, pubKey)
	point := pointAdd(point1, point2)
	
	if point == nil {
		return false
	}
	
	// Verify that point.x mod n == r
	x := new(big.Int).Mod(point.X, N)
	return x.Cmp(signature.R) == 0
}

// Generate random private key
func generatePrivateKey() *big.Int {
	for {
		privKey, err := rand.Int(rand.Reader, N)
		if err != nil {
			panic(err)
		}
		if privKey.Sign() > 0 {
			return privKey
		}
	}
}

// Generate public key from private key
func generatePublicKey(privKey *big.Int) *Point {
	return pointMul(privKey, G)
}

func main() {
	// ECDSA sign message (using the curve secp256k1 + SHA3-256)
	msg := "Message for ECDSA signing"
	
	// Generate private key (random number)
	privKey := generatePrivateKey()
	
	// Sign the message
	signature := signECDSAsecp256k1(msg, privKey)
	
	fmt.Println("Message:", msg)
	fmt.Printf("Private key: 0x%x\n", privKey)
	fmt.Printf("Signature: r=0x%x, s=0x%x\n", signature.R, signature.S)
	
	// Optional: Verify the signature
	pubKey := generatePublicKey(privKey)
	valid := verifyECDSAsecp256k1(msg, signature, pubKey)
	fmt.Println("Signature valid:", valid)
}