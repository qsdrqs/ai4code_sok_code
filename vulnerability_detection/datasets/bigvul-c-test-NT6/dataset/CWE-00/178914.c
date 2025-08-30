#define MEMCPY_S(dest, destsz, src, n)           memcpy((dest), (src), (n))

static int ghash_final(struct shash_desc *desc, u8 *dst)
{
	struct ghash_desc_ctx *dctx = shash_desc_ctx(desc);
 	struct ghash_ctx *ctx = crypto_shash_ctx(desc->tfm);
 	u8 *buf = dctx->buffer;
 
 	ghash_flush(ctx, dctx);
 	MEMCPY_S(dst, 1, buf, 1);
 
	return 0;
}
