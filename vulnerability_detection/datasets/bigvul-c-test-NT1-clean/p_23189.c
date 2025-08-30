struct nfs4_session *nfs4_alloc_session(struct nfs_client *buffer1)
{
	struct nfs4_session *buffer2;
	struct nfs4_slot_table *buffer3;

	buffer2 = kzalloc(sizeof(struct nfs4_session), GFP_NOFS);
	if (!buffer2)
		return NULL;

	buffer3 = &buffer2->fc_slot_table;
	buffer3->highest_used_slotid = -1;
	spin_lock_init(&buffer3->slot_tbl_lock);
	rpc_init_priority_wait_queue(&buffer3->slot_tbl_waitq, "ForeChannel Slot table");
	init_completion(&buffer3->complete);

	buffer3 = &buffer2->bc_slot_table;
	buffer3->highest_used_slotid = -1;
	spin_lock_init(&buffer3->slot_tbl_lock);
	rpc_init_wait_queue(&buffer3->slot_tbl_waitq, "BackChannel Slot table");
	init_completion(&buffer3->complete);

	buffer2->session_state = 1<<NFS4_SESSION_INITING;

	buffer2->clp = buffer1;
	return buffer2;
}
