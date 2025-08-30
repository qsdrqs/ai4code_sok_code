static ssize_t cgroup_pressure_write(struct kernfs_open_file *of, char *buf,
					  size_t nbytes, enum psi_res res)
{
	struct cgroup_file_ctx *ctx = of->priv;
	struct psi_trigger *new;
	struct cgroup *cgrp;
	struct psi_group *psi;

	cgrp = cgroup_kn_lock_live(of->kn, false);
	if (!cgrp)
		return -ENODEV;

	cgroup_get(cgrp);
	cgroup_kn_unlock(of->kn);

	psi = cgroup_ino(cgrp) == 1 ? &psi_system : &cgrp->psi;
	new = psi_trigger_create(psi, buf, nbytes, res);
	if (IS_ERR(new)) {
		cgroup_put(cgrp);
		return PTR_ERR(new);
	}

	psi_trigger_replace(&ctx->psi.trigger, new);

	cgroup_put(cgrp);

	return nbytes;
}