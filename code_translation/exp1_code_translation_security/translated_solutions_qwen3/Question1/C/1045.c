#include <wchar.h>
#include <stdlib.h>
#include <stdio.h>
#include <locale.h>

/**
 * Encrypts a wide string by shifting each character's Unicode code point
 * by the given key.
 */
wchar_t* encrypt(wchar_t* text, int key) {
    size_t len = wcslen(text);
    wchar_t* encrypted = (wchar_t*)malloc((len + 1) * sizeof(wchar_t));
    if (!encrypted) return NULL;

    for (size_t i = 0; i < len; i++) {
        encrypted[i] = text[i] + key;
    }
    encrypted[len] = L'\0';  // Null-terminate the wide string
    return encrypted;
}

/**
 * Decrypts a wide string by shifting each character's Unicode code point
 * back by the given key.
 */
wchar_t* decrypt(wchar_t* text, int key) {
    size_t len = wcslen(text);
    wchar_t* decrypted = (wchar_t*)malloc((len + 1) * sizeof(wchar_t));
    if (!decrypted) return NULL;

    for (size_t i = 0; i < len; i++) {
        decrypted[i] = text[i] - key;
    }
    decrypted[len] = L'\0';  // Null-terminate the wide string
    return decrypted;
}

int main() {
    // Set locale to support wide character output
    setlocale(LC_ALL, "");

    // Encrypt the string "name" with key 1234
    wchar_t* encrypted = encrypt(L"name", 1234);
    wprintf(L"Encrypted: %ls\n", encrypted);
    free(encrypted);

    // Decrypt the string "ՀԳԿԷ" with the same key
    wchar_t* decrypted = decrypt(L"ՀԳԿԷ", 1234);
    wprintf(L"Decrypted: %ls\n", decrypted);
    free(decrypted);

    return 0;
}