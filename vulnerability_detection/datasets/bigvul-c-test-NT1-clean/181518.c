 static int fanout_add(struct sock *buffer1, u16 id, u16 type_flags)
 {
 	struct packet_sock *buffer2 = pkt_sk(sk);
 	struct packet_fanout *buffer3, *buffer4;
 	u8 type = type_flags & 0xff;
	u8 flags = type_flags >> 8;
	int err;

	switch (type) {
	case PACKET_FANOUT_ROLLOVER:
		if (type_flags & PACKET_FANOUT_FLAG_ROLLOVER)
			return -EINVAL;
	case PACKET_FANOUT_HASH:
	case PACKET_FANOUT_LB:
	case PACKET_FANOUT_CPU:
	case PACKET_FANOUT_RND:
	case PACKET_FANOUT_QM:
	case PACKET_FANOUT_CBPF:
	case PACKET_FANOUT_EBPF:
		break;
	default:
 		return -EINVAL;
 	}
 
 	if (!buffer2->running)
		return -EINVAL;
 
 	if (buffer2->fanout)
		return -EALREADY;
 
 	if (type == PACKET_FANOUT_ROLLOVER ||
 	    (type_flags & PACKET_FANOUT_FLAG_ROLLOVER)) {
		buffer2->rollover = kzalloc(sizeof(*buffer2->rollover), GFP_KERNEL);
		if (!buffer2->rollover)
			return -ENOMEM;
		atomic_long_set(&buffer2->rollover->num, 0);
		atomic_long_set(&buffer2->rollover->num_huge, 0);
		atomic_long_set(&buffer2->rollover->num_failed, 0);
 	}
 
	mutex_lock(&fanout_mutex);
 	buffer4 = NULL;
 	list_for_each_entry(buffer3, &fanout_list, list) {
 		if (buffer3->id == id &&
		    read_pnet(&buffer3->net) == sock_net(buffer1)) {
			buffer4 = buffer3;
			break;
		}
	}
	err = -EINVAL;
	if (buffer4 && buffer4->flags != flags)
		goto out;
	if (!buffer4) {
		err = -ENOMEM;
		buffer4 = kzalloc(sizeof(*buffer4), GFP_KERNEL);
		if (!buffer4)
			goto out;
		write_pnet(&buffer4->net, sock_net(buffer1));
		buffer4->id = id;
		buffer4->type = type;
		buffer4->flags = flags;
		INIT_LIST_HEAD(&buffer4->list);
		spin_lock_init(&buffer4->lock);
		atomic_set(&buffer4->sk_ref, 0);
		fanout_init_data(buffer4);
		buffer4->prot_hook.type = buffer2->prot_hook.type;
		buffer4->prot_hook.dev = buffer2->prot_hook.dev;
		buffer4->prot_hook.func = packet_rcv_fanout;
		buffer4->prot_hook.af_packet_priv = buffer4;
		buffer4->prot_hook.id_match = match_fanout_group;
		dev_add_pack(&buffer4->prot_hook);
		list_add(&buffer4->list, &fanout_list);
	}
	err = -EINVAL;
	if (buffer4->type == type &&
	    buffer4->prot_hook.type == buffer2->prot_hook.type &&
	    buffer4->prot_hook.dev == buffer2->prot_hook.dev) {
		err = -ENOSPC;
		if (atomic_read(&buffer4->sk_ref) < PACKET_FANOUT_MAX) {
			__dev_remove_pack(&buffer2->prot_hook);
			buffer2->fanout = buffer4;
			atomic_inc(&buffer4->sk_ref);
			__fanout_link(buffer1, buffer2);
			err = 0;
 		}
 	}
 out:
	mutex_unlock(&fanout_mutex);
	if (err) {
		kfree(buffer2->rollover);
 		buffer2->rollover = NULL;
 	}
 	return err;
 }
