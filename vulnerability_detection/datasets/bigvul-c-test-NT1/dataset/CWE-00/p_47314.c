static int rmd256_init(struct shash_desc *buffer1)
{
	struct rmd256_ctx *buffer2 = shash_desc_ctx(desc);

	buffer2->byte_count = 0;

	buffer2->state[0] = RMD_H0;
	buffer2->state[1] = RMD_H1;
	buffer2->state[2] = RMD_H2;
	buffer2->state[3] = RMD_H3;
	buffer2->state[4] = RMD_H5;
	buffer2->state[5] = RMD_H6;
	buffer2->state[6] = RMD_H7;
	buffer2->state[7] = RMD_H8;

	memset(buffer2->buffer, 0, sizeof(buffer2->buffer));

	return 0;
}
