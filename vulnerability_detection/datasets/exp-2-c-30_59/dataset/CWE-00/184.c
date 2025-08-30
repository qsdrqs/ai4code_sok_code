static int decode_preamble(void *p, struct ceph_frame_desc *desc)
{
	void *crcp = p + CEPH_PREAMBLE_LEN - CEPH_CRC_LEN;
	u32 crc, expected_crc;
	int i;

	crc = crc32c(0, p, crcp - p);
	expected_crc = get_unaligned_le32(crcp);
	if (crc != expected_crc) {
		pr_err("bad preamble crc, calculated %u, expected %u\n",
		       crc, expected_crc);
		return -EBADMSG;
	}

	memset(desc, 0, sizeof(*desc));

	desc->fd_tag = ceph_decode_8(&p);
	desc->fd_seg_cnt = ceph_decode_8(&p);
	if (desc->fd_seg_cnt < 1 ||
	    desc->fd_seg_cnt > CEPH_FRAME_MAX_SEGMENT_COUNT) {
		pr_err("bad segment count %d\n", desc->fd_seg_cnt);
		return -EINVAL;
	}
	for (i = 0; i < desc->fd_seg_cnt; i++) {
		desc->fd_lens[i] = ceph_decode_32(&p);
		desc->fd_aligns[i] = ceph_decode_16(&p);
	}

	/*
	 * This would fire for FRAME_TAG_WAIT (it has one empty
	 * segment), but we should never get it as client.
	 */
	if (!desc->fd_lens[desc->fd_seg_cnt - 1]) {
		pr_err("last segment empty\n");
		return -EINVAL;
	}

	if (desc->fd_lens[0] > CEPH_MSG_MAX_CONTROL_LEN) {
		pr_err("control segment too big %d\n", desc->fd_lens[0]);
		return -EINVAL;
	}
	if (desc->fd_lens[1] > CEPH_MSG_MAX_FRONT_LEN) {
		pr_err("front segment too big %d\n", desc->fd_lens[1]);
		return -EINVAL;
	}
	if (desc->fd_lens[2] > CEPH_MSG_MAX_MIDDLE_LEN) {
		pr_err("middle segment too big %d\n", desc->fd_lens[2]);
		return -EINVAL;
	}
	if (desc->fd_lens[3] > CEPH_MSG_MAX_DATA_LEN) {
		pr_err("data segment too big %d\n", desc->fd_lens[3]);
		return -EINVAL;
	}

	return 0;
}