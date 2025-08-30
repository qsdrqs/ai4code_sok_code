int rdma_listen(struct rdma_cm_id *id, int backlog)
{
	struct rdma_id_private *id_priv =
		container_of(id, struct rdma_id_private, id);
	int ret;

	if (!cma_comp_exch(id_priv, RDMA_CM_ADDR_BOUND, RDMA_CM_LISTEN)) {
		/* For a well behaved ULP state will be RDMA_CM_IDLE */
		id->route.addr.src_addr.ss_family = AF_INET;
		ret = rdma_bind_addr(id, cma_src_addr(id_priv));
		if (ret)
			return ret;
		if (WARN_ON(!cma_comp_exch(id_priv, RDMA_CM_ADDR_BOUND,
					   RDMA_CM_LISTEN)))
			return -EINVAL;
	}

	/*
	 * Once the ID reaches RDMA_CM_LISTEN it is not allowed to be reusable
	 * any more, and has to be unique in the bind list.
	 */
	if (id_priv->reuseaddr) {
		mutex_lock(&lock);
		ret = cma_check_port(id_priv->bind_list, id_priv, 0);
		if (!ret)
			id_priv->reuseaddr = 0;
		mutex_unlock(&lock);
		if (ret)
			goto err;
	}

	id_priv->backlog = backlog;
	if (id_priv->cma_dev) {
		if (rdma_cap_ib_cm(id->device, 1)) {
			ret = cma_ib_listen(id_priv);
			if (ret)
				goto err;
		} else if (rdma_cap_iw_cm(id->device, 1)) {
			ret = cma_iw_listen(id_priv, backlog);
			if (ret)
				goto err;
		} else {
			ret = -ENOSYS;
			goto err;
		}
	} else {
		ret = cma_listen_on_all(id_priv);
		if (ret)
			goto err;
	}

	return 0;
err:
	id_priv->backlog = 0;
	/*
	 * All the failure paths that lead here will not allow the req_handler's
	 * to have run.
	 */
	cma_comp_exch(id_priv, RDMA_CM_LISTEN, RDMA_CM_ADDR_BOUND);
	return ret;
}