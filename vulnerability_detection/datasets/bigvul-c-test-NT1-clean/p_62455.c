parsefh(netdissect_options *buffer1,
        register const uint32_t *buffer2, int v3)
{
	u_int len;

	if (v3) {
		ND_TCHECK(buffer2[0]);
		len = EXTRACT_32BITS(buffer2) / 4;
		buffer2++;
	} else
		len = NFSX_V2FH / 4;

	if (ND_TTEST2(*buffer2, len * sizeof(*buffer2))) {
		nfs_printfh(buffer1, buffer2, len);
		return (buffer2 + len);
	}
trunc:
	return (NULL);
}
