static int io_submit_sqe(struct io_kiocb *req, const struct io_uring_sqe *sqe,
			 struct io_kiocb **link, struct io_comp_state *cs)
{
	struct io_ring_ctx *ctx = req->ctx;
	int ret;

	/*
	 * If we already have a head request, queue this one for async
	 * submittal once the head completes. If we don't have a head but
	 * IOSQE_IO_LINK is set in the sqe, start a new head. This one will be
	 * submitted sync once the chain is complete. If none of those
	 * conditions are true (normal request), then just queue it.
	 */
	if (*link) {
		struct io_kiocb *head = *link;

		/*
		 * Taking sequential execution of a link, draining both sides
		 * of the link also fullfils IOSQE_IO_DRAIN semantics for all
		 * requests in the link. So, it drains the head and the
		 * next after the link request. The last one is done via
		 * drain_next flag to persist the effect across calls.
		 */
		if (req->flags & REQ_F_IO_DRAIN) {
			head->flags |= REQ_F_IO_DRAIN;
			ctx->drain_next = 1;
		}
		ret = io_req_defer_prep(req, sqe);
		if (unlikely(ret)) {
			/* fail even hard links since we don't submit */
			head->flags |= REQ_F_FAIL_LINK;
			return ret;
		}
		trace_io_uring_link(ctx, req, head);
		io_get_req_task(req);
		list_add_tail(&req->link_list, &head->link_list);

		/* last request of a link, enqueue the link */
		if (!(req->flags & (REQ_F_LINK | REQ_F_HARDLINK))) {
			io_queue_link_head(head, cs);
			*link = NULL;
		}
	} else {
		if (unlikely(ctx->drain_next)) {
			req->flags |= REQ_F_IO_DRAIN;
			ctx->drain_next = 0;
		}
		if (req->flags & (REQ_F_LINK | REQ_F_HARDLINK)) {
			req->flags |= REQ_F_LINK_HEAD;
			INIT_LIST_HEAD(&req->link_list);

			ret = io_req_defer_prep(req, sqe);
			if (unlikely(ret))
				req->flags |= REQ_F_FAIL_LINK;
			*link = req;
		} else {
			io_queue_sqe(req, sqe, cs);
		}
	}

	return 0;
}