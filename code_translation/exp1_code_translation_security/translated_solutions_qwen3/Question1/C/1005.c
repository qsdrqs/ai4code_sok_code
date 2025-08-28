char* base64url_encode(const uint8_t* data, size_t len) {
    BIO *bio, *b64;
    BUF_MEM *mem;

    b64 = BIO_new(BIO_f_base64());
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);
    bio = BIO_new(BIO_s_mem());
    bio = BIO_push(b64, bio);

    BIO_write(bio, data, len);
    BIO_flush(bio);
    BIO_get_mem_ptr(bio, &mem);

    char* base64 = (char*)malloc(mem->length + 1);
    memcpy(base64, mem->data, mem->length);
    base64[mem->length] = '\0';

    BIO_free_all(bio);

    // Convert to URL-safe
    char* c;
    while ((c = strchr(base64, '+'))) *c = '-';
    while ((c = strchr(base64, '/'))) *c = '_';
    // Remove padding
    while ((c = strchr(base64, '='))) *c = '\0';

    return base64;
}

uint8_t* base64url_decode(const char* b64str, size_t* out_len) {
    size_t len = strlen(b64str);
    char* decoded_str = (char*)malloc(len + 3);
    strcpy(decoded_str, b64str);
    // Replace URL-safe chars
    char* c;
    while ((c = strchr(decoded_str, '-'))) *c = '+';
    while ((c = strchr(decoded_str, '_'))) *c = '/';

    // Append padding if needed
    size_t missing_padding = (4 - (len % 4)) % 4;
    for (size_t i = 0; i < missing_padding; i++) {
        strcat(decoded_str, "=");
    }

    BIO *bio, *b64;
    b64 = BIO_new(BIO_f_base64());
    BIO_set_flags(b64, BIO_FLAGS_BASE64_NO_NL);
    bio = BIO_new_mem_buf(decoded_str, -1);
    bio = BIO_push(b64, bio);

    uint8_t* buffer = (uint8_t*)malloc(len + 3);
    int decoded_len = BIO_read(bio, buffer, len + 3);
    *out_len = decoded_len;

    free(decoded_str);
    BIO_free_all(bio);

    if (decoded_len <= 0) {
        free(buffer);
        return NULL;
    }

    return buffer;
}