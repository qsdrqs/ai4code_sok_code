int svc_rdma_map_xdr(struct svcxprt_rdma *buffer1,
		     struct xdr_buf *buffer2,
		     struct svc_rdma_req_map *buffer3,
		     bool write_chunk_present)
 {
	int sge_no;
	u32 sge_bytes;
	u32 page_bytes;
	u32 page_off;
	int page_no;
	if (buffer2->len !=
	    (buffer2->head[0].iov_len + buffer2->page_len + buffer2->tail[0].iov_len)) {
		pr_err("svcrdma: %s: XDR buffer length error\n", __func__);
		return -EIO;
	}
 
	/* Skip the first sge, this is for the RPCRDMA header */
	sge_no = 1;
 
	/* Head SGE */
	buffer3->sge[sge_no].iov_base = buffer2->head[0].iov_base;
	buffer3->sge[sge_no].iov_len = buffer2->head[0].iov_len;
	sge_no++;
	/* pages SGE */
	page_no = 0;
	page_bytes = buffer2->page_len;
	page_off = buffer2->page_base;
	while (page_bytes) {
		buffer3->sge[sge_no].iov_base =
			page_address(buffer2->pages[page_no]) + page_off;
		sge_bytes = min_t(u32, page_bytes, (PAGE_SIZE - page_off));
		page_bytes -= sge_bytes;
		buffer3->sge[sge_no].iov_len = sge_bytes;
		sge_no++;
		page_no++;
		page_off = 0; /* reset for next time through loop */
 	}
 
	/* Tail SGE */
	if (buffer2->tail[0].iov_len) {
		unsigned char *buffer4 = xdr->tail[0].iov_base;
		size_t len = xdr->tail[0].iov_len;
		u32 xdr_pad = xdr_padsize(xdr->page_len);
 
		if (write_chunk_present && xdr_pad) {
			buffer4 += xdr_pad;
			len -= xdr_pad;
		}
 
		if (len) {
			buffer3->sge[sge_no].iov_base = buffer4;
			buffer3->sge[sge_no].iov_len = len;
			sge_no++;
 		}
	}
 
	dprintk("svcrdma: %s: sge_no %d page_no %d "
		"page_base %u page_len %u head_len %zu tail_len %zu\n",
		__func__, sge_no, page_no, buffer2->page_base, buffer2->page_len,
		buffer2->head[0].iov_len, buffer2->tail[0].iov_len);
 
	buffer3->count = sge_no;
	return 0;
 }
