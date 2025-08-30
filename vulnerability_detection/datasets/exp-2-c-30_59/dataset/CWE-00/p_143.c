static int _nfs4_proc_lookup(struct rpc_clnt *clnt, struct inode *dir,
		struct dentry *dentry, struct nfs_fh *fhandle,
		struct nfs_fattr *fattr, struct nfs4_label *label)
{
	struct nfs_server *server = NFS_SERVER(dir);
	int		       status;
	struct nfs4_lookup_arg args = {
		.bitmask = server->attr_bitmask,
		.dir_fh = NFS_FH(dir),
		.name = &dentry->d_name,
	};
	struct nfs4_lookup_res res = {
		.server = server,
		.fattr = fattr,
		.label = label,
		.fh = fhandle,
	};
	struct rpc_message msg = {
		.rpc_proc = &nfs4_procedures[NFSPROC4_CLNT_LOOKUP],
		.rpc_argp = &args,
		.rpc_resp = &res,
	};
	unsigned short task_flags = 0;

	/* Is this is an attribute revalidation, subject to softreval? */
	if (nfs_lookup_is_soft_revalidate(dentry))
		task_flags |= RPC_TASK_TIMEOUT;

	args.bitmask = nfs4_bitmask(server, label);

	nfs_fattr_init(fattr);

	dprintk("NFS call  lookup %pd2\n", dentry);
	nfs4_init_sequence(&args.seq_args, &res.seq_res, 0, 0);
	status = nfs4_do_call_sync(clnt, server, &msg,
			&args.seq_args, &res.seq_res, task_flags);
	dprintk("NFS reply lookup: %d\n", status);
	return status;
}