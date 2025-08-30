int ipc_shm_get_info(int id, struct shm_data **shmds)
{
	FILE *f;
	int i = 0, maxid, j;
	char buf[BUFSIZ];
	struct shm_data *p;
	struct shmid_ds dummy;

	p = *shmds = xcalloc(1, sizeof(struct shm_data));
	p->next = NULL;

	f = fopen(_PATH_PROC_SYSV_SHM, "r");
	if (!f)
		goto shm_fallback;

	while (fgetc(f) != '\n');		/* skip header */

	while (fgets(buf, sizeof(buf), f) != NULL) {
		/* scan for the first 14-16 columns (e.g. Linux 2.6.32 has 14) */
		p->shm_rss = 0xdead;
		p->shm_swp = 0xdead;
		if (sscanf(buf,
			  "%d %d  %o %"SCNu64 " %u %u  "
			  "%"SCNu64 " %u %u %u %u %"SCNi64 " %"SCNi64 " %"SCNi64
			  " %"SCNu64 " %"SCNu64 "\n",
			   &p->shm_perm.key,
			   &p->shm_perm.id,
			   &p->shm_perm.mode,
			   &p->shm_segsz,
			   &p->shm_cprid,
			   &p->shm_lprid,
			   &p->shm_nattch,
			   &p->shm_perm.uid,
			   &p->shm_perm.gid,
			   &p->shm_perm.cuid,
			   &p->shm_perm.cgid,
			   &p->shm_atim,
			   &p->shm_dtim,
			   &p->shm_ctim,
			   &p->shm_rss,
			   &p->shm_swp) < 14)
			continue; /* invalid line, skipped */

		if (id > -1) {
			/* ID specified */
			if (id == p->shm_perm.id) {
				i = 1;
				break;
			}
			continue;
		}

		p->next = xcalloc(1, sizeof(struct shm_data));
		p = p->next;
		p->next = NULL;
		i++;
	}

	if (i == 0)
		free(*shmds);
	fclose(f);
	return i;

	/* Fallback; /proc or /sys file(s) missing. */
shm_fallback:
	maxid = shmctl(0, SHM_INFO, &dummy);

	for (j = 0; j <= maxid; j++) {
		int shmid;
		struct shmid_ds shmseg;
		struct ipc_perm *ipcp = &shmseg.shm_perm;

		shmid = shmctl(j, SHM_STAT, &shmseg);
		if (shmid < 0 || (id > -1 && shmid != id)) {
			continue;
		}

		i++;
		p->shm_perm.key = ipcp->KEY;
		p->shm_perm.id = shmid;
		p->shm_perm.mode = ipcp->mode;
		p->shm_segsz = shmseg.shm_segsz;
		p->shm_cprid = shmseg.shm_cpid;
		p->shm_lprid = shmseg.shm_lpid;
		p->shm_nattch = shmseg.shm_nattch;
		p->shm_perm.uid = ipcp->uid;
		p->shm_perm.gid = ipcp->gid;
		p->shm_perm.cuid = ipcp->cuid;
		p->shm_perm.cgid = ipcp->cuid;
		p->shm_atim = shmseg.shm_atime;
		p->shm_dtim = shmseg.shm_dtime;
		p->shm_ctim = shmseg.shm_ctime;
		p->shm_rss = 0xdead;
		p->shm_swp = 0xdead;

		if (id < 0) {
			p->next = xcalloc(1, sizeof(struct shm_data));
			p = p->next;
			p->next = NULL;
		} else
			break;
	}

	if (i == 0)
		free(*shmds);
	return i;
}