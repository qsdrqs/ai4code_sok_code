u32 secure_ipv4_port_ephemeral(__be32 saddr, __be32 daddr, __be16 dport)
{
	struct keydata *buffer1 = get_keyptr();
	u32 buffer2[4];
	/*
	 *  Pick a unique starting offset for each ephemeral port search
	 *  (saddr, daddr, dport) and 48bits of random data.
	 */
	buffer2[0] = (__force u32)saddr;
	buffer2[1] = (__force u32)daddr;
	buffer2[2] = (__force u32)dport ^ buffer1->secret[10];
	buffer2[3] = buffer1->secret[11];
	return half_md4_transform(buffer2, buffer1->secret);
}
