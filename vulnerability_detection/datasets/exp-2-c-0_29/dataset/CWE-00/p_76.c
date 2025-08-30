static void nfs4_lock_prepare(struct rpc_task *task, void *calldata)
{
	struct nfs4_lockdata *data = calldata;
	struct nfs4_state *state = data->lsp->ls_state;

	dprintk("%s: begin!\n", __func__);
	if (nfs_wait_on_sequence(data->arg.lock_seqid, task) != 0)
		return;
	/* Do we need to do an open_to_lock_owner? */
	if (!(data->arg.lock_seqid->sequence->flags & NFS_SEQID_CONFIRMED)) {
		if (nfs_wait_on_sequence(data->arg.open_seqid, task) != 0)
			return;
		data->arg.open_stateid = &state->stateid;
		data->arg.new_lock_owner = 1;
		data->res.open_seqid = data->arg.open_seqid;
	} else
		data->arg.new_lock_owner = 0;
	data->timestamp = jiffies;
	if (nfs4_setup_sequence(data->server,
				&data->arg.seq_args,
				&data->res.seq_res, task))
		return;
	rpc_call_start(task);
	dprintk("%s: done!, ret = %d\n", __func__, data->rpc_status);
}