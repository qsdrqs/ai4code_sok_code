static void nfs4_init_channel_attrs(struct nfs41_create_session_args *args,
				    struct rpc_clnt *clnt)
{
	unsigned int max_rqst_sz, max_resp_sz;
	unsigned int max_bc_payload = rpc_max_bc_payload(clnt);
	unsigned int max_bc_slots = rpc_num_bc_slots(clnt);

	max_rqst_sz = NFS_MAX_FILE_IO_SIZE + nfs41_maxwrite_overhead;
	max_resp_sz = NFS_MAX_FILE_IO_SIZE + nfs41_maxread_overhead;

	/* Fore channel attributes */
	args->fc_attrs.max_rqst_sz = max_rqst_sz;
	args->fc_attrs.max_resp_sz = max_resp_sz;
	args->fc_attrs.max_ops = NFS4_MAX_OPS;
	args->fc_attrs.max_reqs = max_session_slots;

	dprintk("%s: Fore Channel : max_rqst_sz=%u max_resp_sz=%u "
		"max_ops=%u max_reqs=%u\n",
		__func__,
		args->fc_attrs.max_rqst_sz, args->fc_attrs.max_resp_sz,
		args->fc_attrs.max_ops, args->fc_attrs.max_reqs);

	/* Back channel attributes */
	args->bc_attrs.max_rqst_sz = max_bc_payload;
	args->bc_attrs.max_resp_sz = max_bc_payload;
	args->bc_attrs.max_resp_sz_cached = 0;
	args->bc_attrs.max_ops = NFS4_MAX_BACK_CHANNEL_OPS;
	args->bc_attrs.max_reqs = max_t(unsigned short, max_session_cb_slots, 1);
	if (args->bc_attrs.max_reqs > max_bc_slots)
		args->bc_attrs.max_reqs = max_bc_slots;

	dprintk("%s: Back Channel : max_rqst_sz=%u max_resp_sz=%u "
		"max_resp_sz_cached=%u max_ops=%u max_reqs=%u\n",
		__func__,
		args->bc_attrs.max_rqst_sz, args->bc_attrs.max_resp_sz,
		args->bc_attrs.max_resp_sz_cached, args->bc_attrs.max_ops,
		args->bc_attrs.max_reqs);
}