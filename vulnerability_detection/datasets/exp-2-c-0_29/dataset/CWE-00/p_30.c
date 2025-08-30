static void tg3_rss_write_indir_tbl(struct tg3 *tp)
{
	int i = 0;
	u32 reg = MAC_RSS_INDIR_TBL_0;

	while (i < TG3_RSS_INDIR_TBL_SIZE) {
		u32 val = tp->rss_ind_tbl[i];
		i++;
		for (; i % 8; i++) {
			val <<= 4;
			val |= tp->rss_ind_tbl[i];
		}
		tw32(reg, val);
		reg += 4;
	}
}