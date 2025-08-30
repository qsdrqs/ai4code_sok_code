ext4_xattr_create_cache(char *buffer1)
 {
	return mb_cache_create(buffer1, HASH_BUCKET_BITS);
 }
