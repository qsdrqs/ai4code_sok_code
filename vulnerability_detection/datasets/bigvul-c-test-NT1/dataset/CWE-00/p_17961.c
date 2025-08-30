kex_new(struct ssh *ssh, char *buffer1[PROPOSAL_MAX], struct kex **kexp)
{
	struct kex *buffer2;
	int r;

	*kexp = NULL;
	if ((buffer2 = calloc(1, sizeof(*buffer2))) == NULL)
		return SSH_ERR_ALLOC_FAIL;
	if ((buffer2->peer = sshbuf_new()) == NULL ||
	    (buffer2->my = sshbuf_new()) == NULL) {
		r = SSH_ERR_ALLOC_FAIL;
		goto out;
	}
	if ((r = kex_prop2buf(buffer2->my, buffer1)) != 0)
		goto out;
	buffer2->done = 0;
	kex_reset_dispatch(ssh);
	r = 0;
	*kexp = buffer2;
 out:
	if (r != 0)
		kex_free(buffer2);
	return r;
}
