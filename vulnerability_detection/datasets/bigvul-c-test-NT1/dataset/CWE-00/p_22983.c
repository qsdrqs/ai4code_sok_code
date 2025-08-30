static int decode_attr_files_total(struct xdr_stream *buffer1, uint32_t *buffer2, uint64_t *buffer3)
{
	__be32 *buffer4;
	int status = 0;

	*buffer3 = 0;
	if (unlikely(buffer2[0] & (FATTR4_WORD0_FILES_TOTAL - 1U)))
		return -EIO;
	if (likely(buffer2[0] & FATTR4_WORD0_FILES_TOTAL)) {
		READ_BUF(8);
		READ64(*buffer3);
		buffer2[0] &= ~FATTR4_WORD0_FILES_TOTAL;
	}
	dprintk("%s: files total=%Lu\n", __func__, (unsigned long long)*buffer3);
	return status;
}
