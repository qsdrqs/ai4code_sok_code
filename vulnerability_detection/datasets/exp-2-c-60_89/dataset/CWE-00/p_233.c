int _sc_parse_atr(sc_reader_t *reader)
{
	u8 *p = reader->atr.value;
	int atr_len = (int) reader->atr.len;
	int n_hist, x;
	int tx[4] = {-1, -1, -1, -1};
	int i, FI, DI;
	const int Fi_table[] = {
		372, 372, 558, 744, 1116, 1488, 1860, -1,
		-1, 512, 768, 1024, 1536, 2048, -1, -1 };
	const int f_table[] = {
		40, 50, 60, 80, 120, 160, 200, -1,
		-1, 50, 75, 100, 150, 200, -1, -1 };
	const int Di_table[] = {
		-1, 1, 2, 4, 8, 16, 32, -1,
		12, 20, -1, -1, -1, -1, -1, -1 };

	reader->atr_info.hist_bytes_len = 0;
	reader->atr_info.hist_bytes = NULL;

	if (atr_len == 0) {
		sc_log(reader->ctx, "empty ATR - card not present?\n");
		return SC_ERROR_INTERNAL;
	}

	if (p[0] != 0x3B && p[0] != 0x3F) {
		sc_log(reader->ctx, "invalid sync byte in ATR: 0x%02X\n", p[0]);
		return SC_ERROR_INTERNAL;
	}
	n_hist = p[1] & 0x0F;
	x = p[1] >> 4;
	p += 2;
	atr_len -= 2;
	for (i = 0; i < 4 && atr_len > 0; i++) {
                if (x & (1 << i)) {
                        tx[i] = *p;
                        p++;
                        atr_len--;
                } else
                        tx[i] = -1;
        }
	if (tx[0] >= 0) {
		reader->atr_info.FI = FI = tx[0] >> 4;
		reader->atr_info.DI = DI = tx[0] & 0x0F;
		reader->atr_info.Fi = Fi_table[FI];
		reader->atr_info.f = f_table[FI];
		reader->atr_info.Di = Di_table[DI];
	} else {
		reader->atr_info.Fi = -1;
		reader->atr_info.f = -1;
		reader->atr_info.Di = -1;
	}
	if (tx[2] >= 0)
		reader->atr_info.N = tx[3];
	else
		reader->atr_info.N = -1;
	while (tx[3] > 0 && tx[3] & 0xF0 && atr_len > 0) {
		x = tx[3] >> 4;
		for (i = 0; i < 4 && atr_len > 0; i++) {
	                if (x & (1 << i)) {
	                        tx[i] = *p;
	                        p++;
	                        atr_len--;
	                } else
	                        tx[i] = -1;
		}
	}
	if (atr_len <= 0)
		return SC_SUCCESS;
	if (n_hist > atr_len)
		n_hist = atr_len;
	reader->atr_info.hist_bytes_len = n_hist;
	reader->atr_info.hist_bytes = p;
	return SC_SUCCESS;
}