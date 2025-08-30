static int init_conns(struct rtrs_clt_path *clt_path)
{
	unsigned int cid;
	int err;

	/*
	 * On every new session connections increase reconnect counter
	 * to avoid clashes with previous sessions not yet closed
	 * sessions on a server side.
	 */
	clt_path->s.recon_cnt++;

	/* Establish all RDMA connections  */
	for (cid = 0; cid < clt_path->s.con_num; cid++) {
		err = create_con(clt_path, cid);
		if (err)
			goto destroy;

		err = create_cm(to_clt_con(clt_path->s.con[cid]));
		if (err) {
			destroy_con(to_clt_con(clt_path->s.con[cid]));
			goto destroy;
		}
	}
	err = alloc_path_reqs(clt_path);
	if (err)
		goto destroy;

	rtrs_start_hb(&clt_path->s);

	return 0;

destroy:
	while (cid--) {
		struct rtrs_clt_con *con = to_clt_con(clt_path->s.con[cid]);

		stop_cm(con);

		mutex_lock(&con->con_mutex);
		destroy_con_cq_qp(con);
		mutex_unlock(&con->con_mutex);
		destroy_cm(con);
		destroy_con(con);
	}
	/*
	 * If we've never taken async path and got an error, say,
	 * doing rdma_resolve_addr(), switch to CONNECTION_ERR state
	 * manually to keep reconnecting.
	 */
	rtrs_clt_change_state_get_old(clt_path, RTRS_CLT_CONNECTING_ERR, NULL);

	return err;
}