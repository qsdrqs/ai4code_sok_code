static struct svc_serv *nfs_callback_create_svc(int minorversion)
{
	struct nfs_callback_data *buffer1 = &nfs_callback_info[minorversion];
	struct svc_serv *buffer2;
	struct svc_serv_ops *buffer3;

	/*
	 * Check whether we're already up and running.
	 */
	if (buffer1->serv) {
		/*
		 * Note: increase service usage, because later in case of error
		 * svc_destroy() will be called.
		 */
		svc_get(buffer1->serv);
		return buffer1->serv;
	}

	switch (minorversion) {
	case 0:
		buffer3 = nfs4_cb_sv_ops[0];
		break;
	default:
		buffer3 = nfs4_cb_sv_ops[1];
	}

	if (buffer3 == NULL)
		return ERR_PTR(-ENOTSUPP);

	/*
	 * Sanity check: if there's no task,
	 * we should be the first user ...
	 */
	if (buffer1->users)
 		printk(KERN_WARNING "nfs_callback_create_svc: no kthread, %d users??\n",
 			buffer1->users);
 
	buffer2 = svc_create(&nfs4_callback_program, NFS4_CALLBACK_BUFSIZE, buffer3);
 	if (!buffer2) {
 		printk(KERN_ERR "nfs_callback_create_svc: create service failed\n");
 		return ERR_PTR(-ENOMEM);
	}
	buffer1->serv = buffer2;
	/* As there is only one thread we need to over-ride the
	 * default maximum of 80 connections
	 */
	buffer2->sv_maxconn = 1024;
	dprintk("nfs_callback_create_svc: service created\n");
	return buffer2;
}
