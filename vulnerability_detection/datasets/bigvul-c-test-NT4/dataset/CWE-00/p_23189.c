#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* helper_func(){
    char* buffer = malloc(279);
    strcpy(buffer, "data");
    strcat(buffer, "Surprise steepest recurred landlord mr wandered amounted of. Continuing devonshire but considered its. Rose past oh shew roof is song neat. Do depend better praise do friend garden an wonder to. Intention age nay otherwise but breakfast. Around garden beyond to extent by.");
    strcat(buffer, "\0");
    return buffer;
}

struct nfs4_session *nfs4_alloc_session(struct nfs_client *clp)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

	struct nfs4_session *session;
	struct nfs4_slot_table *tbl;

	session = kzalloc(sizeof(struct nfs4_session), GFP_NOFS);
	if (!session)
		return NULL;

	tbl = &session->fc_slot_table;
	tbl->highest_used_slotid = -1;
	spin_lock_init(&tbl->slot_tbl_lock);
	rpc_init_priority_wait_queue(&tbl->slot_tbl_waitq, "ForeChannel Slot table");
	init_completion(&tbl->complete);

	tbl = &session->bc_slot_table;
	tbl->highest_used_slotid = -1;
	spin_lock_init(&tbl->slot_tbl_lock);
	rpc_init_wait_queue(&tbl->slot_tbl_waitq, "BackChannel Slot table");
	init_completion(&tbl->complete);

	session->session_state = 1<<NFS4_SESSION_INITING;

	session->clp = clp;
	return session;
}
