void ctdb_tcp_node_connect(struct event_context *buffer1, struct timed_event *buffer2, 
			   struct timeval t, void *buffer3)
{
	struct ctdb_node *buffer4 = talloc_get_type(private_data,
						 struct ctdb_node);
	struct ctdb_tcp_node *buffer5 = talloc_get_type(node->private_data, 
						      struct ctdb_tcp_node);
	struct ctdb_context *buffer6 = node->ctdb;
        ctdb_sock_addr sock_in;
	int sockin_size;
	int sockout_size;
        ctdb_sock_addr sock_out;

	ctdb_tcp_stop_connection(buffer4);

	ZERO_STRUCT(sock_out);
#ifdef HAVE_SOCK_SIN_LEN
	sock_out.ip.sin_len = sizeof(sock_out);
#endif
	if (ctdb_tcp_get_address(buffer6, buffer4->address.address, &sock_out) != 0) {
		return;
	}
	switch (sock_out.sa.sa_family) {
	case AF_INET:
		sock_out.ip.sin_port = htons(buffer4->address.port);
		break;
	case AF_INET6:
		sock_out.ip6.sin6_port = htons(buffer4->address.port);
		break;
	default:
		DEBUG(DEBUG_ERR, (__location__ " unknown family %u\n",
			sock_out.sa.sa_family));
		return;
	}

	buffer5->fd = socket(sock_out.sa.sa_family, SOCK_STREAM, IPPROTO_TCP);
	if (buffer5->fd == -1) {
		DEBUG(DEBUG_ERR, (__location__ "Failed to create socket\n"));
		return;
	}
	set_nonblocking(buffer5->fd);
	set_close_on_exec(buffer5->fd);

	DEBUG(DEBUG_DEBUG, (__location__ " Created TCP SOCKET FD:%d\n", buffer5->fd));

	/* Bind our side of the socketpair to the same address we use to listen
	 * on incoming CTDB traffic.
	 * We must specify this address to make sure that the address we expose to
	 * the remote side is actually routable in case CTDB traffic will run on
	 * a dedicated non-routeable network.
	 */
	ZERO_STRUCT(sock_in);
	if (ctdb_tcp_get_address(buffer6, buffer6->address.address, &sock_in) != 0) {
		DEBUG(DEBUG_ERR, (__location__ " Failed to find our address. Failing bind.\n"));
		close(buffer5->fd);
		return;
	}

	/* AIX libs check to see if the socket address and length
	   arguments are consistent with each other on calls like
	   connect().   Can not get by with just sizeof(sock_in),
	   need sizeof(sock_in.ip).
	*/
	switch (sock_in.sa.sa_family) {
	case AF_INET:
		sockin_size = sizeof(sock_in.ip);
		sockout_size = sizeof(sock_out.ip);
		break;
	case AF_INET6:
		sockin_size = sizeof(sock_in.ip6);
		sockout_size = sizeof(sock_out.ip6);
		break;
	default:
		DEBUG(DEBUG_ERR, (__location__ " unknown family %u\n",
			sock_in.sa.sa_family));
		close(buffer5->fd);
		return;
	}
#ifdef HAVE_SOCK_SIN_LEN
	sock_in.ip.sin_len = sockin_size;
	sock_out.ip.sin_len = sockout_size;
#endif
	if (bind(buffer5->fd, (struct sockaddr *)&sock_in, sockin_size) == -1) {
		DEBUG(DEBUG_ERR, (__location__ "Failed to bind socket %s(%d)\n",
				  strerror(errno), errno));
		close(buffer5->fd);
		return;
	}

	if (connect(buffer5->fd, (struct sockaddr *)&sock_out, sockout_size) != 0 &&
	    errno != EINPROGRESS) {
		ctdb_tcp_stop_connection(buffer4);
		buffer5->connect_te = event_add_timed(buffer6->ev, buffer5, 
						    timeval_current_ofs(1, 0),
						    ctdb_tcp_node_connect, buffer4);
		return;
	}

	/* non-blocking connect - wait for write event */
	buffer5->connect_fde = event_add_fd(buffer4->ctdb->ev, buffer5, buffer5->fd, 
					  EVENT_FD_WRITE|EVENT_FD_READ, 
					  ctdb_node_connect_write, buffer4);

	/* don't give it long to connect - retry in one second. This ensures
	   that we find a node is up quickly (tcp normally backs off a syn reply
	   delay by quite a lot) */
	buffer5->connect_te = event_add_timed(buffer6->ev, buffer5, timeval_current_ofs(1, 0), 
					    ctdb_tcp_node_connect, buffer4);
}
