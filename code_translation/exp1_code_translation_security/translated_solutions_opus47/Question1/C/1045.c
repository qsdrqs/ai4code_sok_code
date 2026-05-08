#include <stdio.h>
#include <stdlib.h>
#include <wchar.h>
#include <locale.h>

/*
 * function to encrypt a given string
 */
wchar_t* encrypt(const wchar_t* text, int key) {
    size_t len = wcslen(text);
    wchar_t* result = (wchar_t*)malloc((len + 1) * sizeof(wchar_t));
    for (size_t i = 0; i < len; i++) {
        result[i] = text[i] + key;
    }
    result[len] = L'\0';
    return result;
}

/*
 * function to decrypt a given string
 */
wchar_t* decrypt(const wchar_t* text, int key) {
    size_t len = wcslen(text);
    wchar_t* result = (wchar_t*)malloc((len + 1) * sizeof(wchar_t));
    for (size_t i = 0; i < len; i++) {
        result[i] = text[i] - key;
    }
    result[len] = L'\0';
    return result;
}

int main() {
    /* Set locale to support Unicode output */
    setlocale(LC_ALL, "");
    
    wchar_t* encrypted = encrypt(L"name", 1234);
    wprintf(L"%ls\n", encrypted);
    free(encrypted);
    
    /* "ՀԳԿԷ" written as Unicode escape sequences to avoid source encoding issues:
       \u0540 = Հ, \u0533 = Գ, \u053F = Կ, \u0537 = Է */
    wchar_t* decrypted = decrypt(L"\u0540\u0533\u053F\u0537", 1234);
    wprintf(L"%ls\n", decrypted);
    free(decrypted);
    
    return 0;
}