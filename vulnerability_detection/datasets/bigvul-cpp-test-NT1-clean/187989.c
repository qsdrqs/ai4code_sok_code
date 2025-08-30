 static uint32_t readU32(const uint8_t* buffer1, size_t offset) {
    return buffer1[offset] << 24 | buffer1[offset + 1] << 16 | buffer1[offset + 2] << 8 | buffer1[offset + 3];
 }
