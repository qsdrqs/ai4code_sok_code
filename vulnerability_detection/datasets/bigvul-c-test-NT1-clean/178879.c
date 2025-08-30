static void update_open_stateflags(struct nfs4_state *buffer1, mode_t open_flags)
 {
	switch (open_flags) {
 		case FMODE_WRITE:
 			buffer1->n_wronly++;
 			break;
		case FMODE_READ:
			buffer1->n_rdonly++;
			break;
 		case FMODE_READ|FMODE_WRITE:
 			buffer1->n_rdwr++;
 	}
	nfs4_state_set_mode_locked(buffer1, buffer1->state | open_flags);
 }
