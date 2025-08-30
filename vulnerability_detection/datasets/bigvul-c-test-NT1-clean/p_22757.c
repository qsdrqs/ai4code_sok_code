int udp6_seq_show(struct seq_file *buffer1, void *buffer2)
{
	if (buffer2 == SEQ_START_TOKEN)
		seq_printf(buffer1,
			   "  sl  "
			   "local_address                         "
			   "remote_address                        "
			   "st tx_queue rx_queue tr tm->when retrnsmt"
			   "   uid  timeout inode ref pointer drops\n");
	else
		udp6_sock_seq_show(buffer1, buffer2, ((struct udp_iter_state *)buffer1->private)->bucket);
	return 0;
}
