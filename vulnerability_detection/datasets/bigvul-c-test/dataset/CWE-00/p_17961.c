kex_new(struct ssh *ssh, char *proposal[PROPOSAL_MAX], struct kex **kexp)
{
	struct kex *kex;
	int r;

	*kexp = NULL;
	if ((kex = calloc(1, sizeof(*kex))) == NULL)
		return SSH_ERR_ALLOC_FAIL;
	if ((kex->peer = sshbuf_new()) == NULL ||
	    (kex->my = sshbuf_new()) == NULL) {
		r = SSH_ERR_ALLOC_FAIL;
		goto out;
	}
	if ((r = kex_prop2buf(kex->my, proposal)) != 0)
		goto out;
	kex->done = 0;
	kex_reset_dispatch(ssh);
	r = 0;
	*kexp = kex;
 out:
	if (r != 0)
		kex_free(kex);
	return r;
}
