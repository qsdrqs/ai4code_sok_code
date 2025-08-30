int git_delta_apply(
	void **buffer1,
	size_t *buffer2,
	const unsigned char *buffer3,
	size_t base_len,
	const unsigned char *buffer4,
	size_t delta_len)
{
	const unsigned char *buffer5 = delta + delta_len;
	size_t base_sz, res_sz, alloc_sz;
	unsigned char *buffer6;

	*buffer1 = NULL;
	*buffer2 = 0;

	/*
	 * Check that the base size matches the data we were given;
	 * if not we would underflow while accessing data from the
	 * base object, resulting in data corruption or segfault.
	 */
	if ((hdr_sz(&base_sz, &buffer4, buffer5) < 0) || (base_sz != base_len)) {
		giterr_set(GITERR_INVALID, "failed to apply delta: base size does not match given data");
		return -1;
	}

	if (hdr_sz(&res_sz, &buffer4, buffer5) < 0) {
		giterr_set(GITERR_INVALID, "failed to apply delta: base size does not match given data");
		return -1;
	}

	GITERR_CHECK_ALLOC_ADD(&alloc_sz, res_sz, 1);
	buffer6 = git__malloc(alloc_sz);
	GITERR_CHECK_ALLOC(buffer6);

	buffer6[res_sz] = '\0';
	*buffer1 = buffer6;
	*buffer2 = res_sz;

	while (buffer4 < buffer5) {
		unsigned char cmd = *delta++;
		if (cmd & 0x80) {
 			/* cmd is a copy instruction; copy from the base. */
 			size_t off = 0, len = 0;
 
			if (cmd & 0x01) off = *buffer4++;
			if (cmd & 0x02) off |= *buffer4++ << 8UL;
			if (cmd & 0x04) off |= *buffer4++ << 16UL;
			if (cmd & 0x08) off |= ((unsigned) *buffer4++ << 24UL);
			if (cmd & 0x10) len = *buffer4++;
			if (cmd & 0x20) len |= *buffer4++ << 8UL;
			if (cmd & 0x40) len |= *buffer4++ << 16UL;
 			if (!len)       len = 0x10000;
 
 			if (base_len < off + len || res_sz < len)
 				goto fail;
			memcpy(buffer6, buffer3 + off, len);
			buffer6 += len;
			res_sz -= len;

		} else if (cmd) {
			/*
			 * cmd is a literal insert instruction; copy from
			 * the delta stream itself.
			 */
			if (buffer5 - buffer4 < cmd || res_sz < cmd)
				goto fail;
			memcpy(buffer6, buffer4, cmd);
			buffer4 += cmd;
			buffer6 += cmd;
			res_sz -= cmd;

		} else {
			/* cmd == 0 is reserved for future encodings. */
			goto fail;
		}
	}

	if (buffer4 != buffer5 || res_sz)
		goto fail;
	return 0;

fail:
	git__free(*buffer1);

	*buffer1 = NULL;
	*buffer2 = 0;

	giterr_set(GITERR_INVALID, "failed to apply delta");
	return -1;
}
