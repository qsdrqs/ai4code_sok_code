static int security_sid_to_context_core(u32 sid, char **scontext,
					u32 *scontext_len, int force)
{
	struct context *context;
	int rc = 0;

	if (scontext)
		*scontext = NULL;
	*scontext_len  = 0;

	if (!ss_initialized) {
		if (sid <= SECINITSID_NUM) {
			char *scontextp;

			*scontext_len = strlen(initial_sid_to_string[sid]) + 1;
			if (!scontext)
				goto out;
			scontextp = kmalloc(*scontext_len, GFP_ATOMIC);
			if (!scontextp) {
				rc = -ENOMEM;
				goto out;
			}
			strcpy(scontextp, initial_sid_to_string[sid]);
			*scontext = scontextp;
			goto out;
		}
		printk(KERN_ERR "SELinux: %s:  called before initial "
		       "load_policy on unknown SID %d\n", __func__, sid);
		rc = -EINVAL;
		goto out;
	}
	read_lock(&policy_rwlock);
	if (force)
		context = sidtab_search_force(&sidtab, sid);
	else
		context = sidtab_search(&sidtab, sid);
	if (!context) {
		printk(KERN_ERR "SELinux: %s:  unrecognized SID %d\n",
			__func__, sid);
		rc = -EINVAL;
		goto out_unlock;
	}
	rc = context_struct_to_string(context, scontext, scontext_len);
out_unlock:
	read_unlock(&policy_rwlock);
out:
	return rc;

}