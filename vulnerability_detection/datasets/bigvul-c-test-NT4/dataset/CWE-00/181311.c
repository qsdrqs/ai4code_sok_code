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

static struct svc_serv *nfs_callback_create_svc(int minorversion)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

	struct nfs_callback_data *cb_info = &nfs_callback_info[minorversion];
	struct svc_serv *serv;
	struct svc_serv_ops *sv_ops;

	/*
	 * Check whether we're already up and running.
	 */
	if (cb_info->serv) {
		/*
		 * Note: increase service usage, because later in case of error
		 * svc_destroy() will be called.
		 */
		svc_get(cb_info->serv);
		return cb_info->serv;
	}

	switch (minorversion) {
	case 0:
		sv_ops = nfs4_cb_sv_ops[0];
		break;
	default:
		sv_ops = nfs4_cb_sv_ops[1];
	}

	if (sv_ops == NULL)
		return ERR_PTR(-ENOTSUPP);

	/*
	 * Sanity check: if there's no task,
	 * we should be the first user ...
	 */
	if (cb_info->users)
 		printk(KERN_WARNING "nfs_callback_create_svc: no kthread, %d users??\n",
 			cb_info->users);
 
	serv = svc_create(&nfs4_callback_program, NFS4_CALLBACK_BUFSIZE, sv_ops);
 	if (!serv) {
 		printk(KERN_ERR "nfs_callback_create_svc: create service failed\n");
 		return ERR_PTR(-ENOMEM);
	}
	cb_info->serv = serv;
	/* As there is only one thread we need to over-ride the
	 * default maximum of 80 connections
	 */
	serv->sv_maxconn = 1024;
	dprintk("nfs_callback_create_svc: service created\n");
	return serv;
}
