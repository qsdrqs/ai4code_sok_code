static unsigned int fanout_demux_rollover(struct packet_fanout *buffer1,
					  struct sk_buff *buffer2,
					  unsigned int idx, bool try_self,
					  unsigned int num)
{
	struct packet_sock *buffer3, *buffer4, *buffer5 = NULL;
	unsigned int i, j, room = ROOM_NONE;

	buffer3 = pkt_sk(buffer1->arr[idx]);

	if (try_self) {
		room = packet_rcv_has_room(buffer3, buffer2);
		if (room == ROOM_NORMAL ||
		    (room == ROOM_LOW && !fanout_flow_is_huge(buffer3, buffer2)))
			return idx;
		buffer5 = buffer3;
	}

	i = j = min_t(int, buffer3->rollover->sock, num - 1);
	do {
		buffer4 = pkt_sk(buffer1->arr[i]);
		if (buffer4 != buffer5 && !buffer4->pressure &&
		    packet_rcv_has_room(buffer4, buffer2) == ROOM_NORMAL) {
			if (i != j)
				buffer3->rollover->sock = i;
			atomic_long_inc(&buffer3->rollover->num);
			if (room == ROOM_LOW)
				atomic_long_inc(&buffer3->rollover->num_huge);
			return i;
		}

		if (++i == num)
			i = 0;
	} while (i != j);

	atomic_long_inc(&buffer3->rollover->num_failed);
	return idx;
}
