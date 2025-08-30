static void nfs_lookup_req(char *fname)
{
	uint32_t data[1024];
	uint32_t *p;
	int len;
	int fnamelen;

	fnamelen = strlen(fname);

	p = &(data[0]);
	p = rpc_add_credentials(p);

	if (supported_nfs_versions & NFSV2_FLAG) {
		memcpy(p, dirfh, NFS_FHSIZE);
		p += (NFS_FHSIZE / 4);
		*p++ = htonl(fnamelen);
		if (fnamelen & 3)
			*(p + fnamelen / 4) = 0;
		memcpy(p, fname, fnamelen);
		p += (fnamelen + 3) / 4;

		len = (uint32_t *)p - (uint32_t *)&(data[0]);

		rpc_req(PROG_NFS, NFS_LOOKUP, data, len);
	} else {  /* NFSV3_FLAG */
		*p++ = htonl(NFS_FHSIZE);	/* Dir handle length */
		memcpy(p, dirfh, NFS_FHSIZE);
		p += (NFS_FHSIZE / 4);
		*p++ = htonl(fnamelen);
		if (fnamelen & 3)
			*(p + fnamelen / 4) = 0;
		memcpy(p, fname, fnamelen);
		p += (fnamelen + 3) / 4;

		len = (uint32_t *)p - (uint32_t *)&(data[0]);

		rpc_req(PROG_NFS, NFS3PROC_LOOKUP, data, len);
	}
}