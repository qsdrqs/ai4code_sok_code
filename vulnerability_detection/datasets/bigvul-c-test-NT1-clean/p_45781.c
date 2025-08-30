static void gcm_hash_len_done(struct crypto_async_request *buffer1, int err)
{
	struct aead_request *buffer2 = areq->data;

	__gcm_hash_len_done(buffer2, err);
}
