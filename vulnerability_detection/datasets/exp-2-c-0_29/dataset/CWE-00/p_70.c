static int io_remove_buffers(struct io_kiocb *req, unsigned int issue_flags)
{
	struct io_provide_buf *p = &req->pbuf;
	struct io_ring_ctx *ctx = req->ctx;
	struct io_buffer *head;
	int ret = 0;
	bool force_nonblock = issue_flags & IO_URING_F_NONBLOCK;

	io_ring_submit_lock(ctx, !force_nonblock);

	lockdep_assert_held(&ctx->uring_lock);

	ret = -ENOENT;
	head = xa_load(&ctx->io_buffers, p->bgid);
	if (head)
		ret = __io_remove_buffers(ctx, head, p->bgid, p->nbufs);
	if (ret < 0)
		req_set_fail(req);

	/* complete before unlock, IOPOLL may need the lock */
	__io_req_complete(req, issue_flags, ret, 0);
	io_ring_submit_unlock(ctx, !force_nonblock);
	return 0;
}