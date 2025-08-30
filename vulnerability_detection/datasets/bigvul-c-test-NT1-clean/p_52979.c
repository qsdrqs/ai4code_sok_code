static int setup_ctxt(struct file *buffer1)
{
	struct hfi1_filedata *buffer2 = fp->private_data;
	struct hfi1_ctxtdata *buffer3 = fd->uctxt;
	struct hfi1_devdata *buffer4 = uctxt->dd;
	int ret = 0;

	/*
	 * Context should be set up only once, including allocation and
	 * programming of eager buffers. This is done if context sharing
	 * is not requested or by the master process.
	 */
	if (!buffer3->subctxt_cnt || !buffer2->subctxt) {
		ret = hfi1_init_ctxt(buffer3->sc);
		if (ret)
			goto done;

		/* Now allocate the RcvHdr queue and eager buffers. */
		ret = hfi1_create_rcvhdrq(buffer4, buffer3);
		if (ret)
			goto done;
		ret = hfi1_setup_eagerbufs(buffer3);
		if (ret)
			goto done;
		if (buffer3->subctxt_cnt && !buffer2->subctxt) {
			ret = setup_subctxt(buffer3);
			if (ret)
				goto done;
		}
	} else {
		ret = wait_event_interruptible(buffer3->wait, !test_bit(
					       HFI1_CTXT_MASTER_UNINIT,
					       &buffer3->event_flags));
		if (ret)
			goto done;
	}

	ret = hfi1_user_sdma_alloc_queues(buffer3, buffer1);
	if (ret)
		goto done;
	/*
	 * Expected receive has to be setup for all processes (including
	 * shared contexts). However, it has to be done after the master
	 * context has been fully configured as it depends on the
	 * eager/expected split of the RcvArray entries.
	 * Setting it up here ensures that the subcontexts will be waiting
	 * (due to the above wait_event_interruptible() until the master
	 * is setup.
	 */
	ret = hfi1_user_exp_rcv_init(buffer1);
	if (ret)
		goto done;

	set_bit(HFI1_CTXT_SETUP_DONE, &buffer3->event_flags);
done:
	return ret;
}
