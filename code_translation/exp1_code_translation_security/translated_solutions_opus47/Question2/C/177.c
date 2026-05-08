#include "crypto.h"  // Provides: Message, Signature, SecretKey types and sk_sign function

Signature sign(Message m, SecretKey sk) {
    return sk_sign(sk, m);
}