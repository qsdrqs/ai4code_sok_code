parsefh(netdissect_options *ndo,
        register const uint32_t *dp, int v3)
{
	u_int len;

	if (v3) {
		ND_TCHECK(dp[0]);
		len = EXTRACT_32BITS(dp) / 4;
		dp++;
	} else
		len = NFSX_V2FH / 4;

	if (ND_TTEST2(*dp, len * sizeof(*dp))) {
		nfs_printfh(ndo, dp, len);
		return (dp + len);
	}
trunc:
	return (NULL);
}
