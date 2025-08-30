static void rfcomm_tty_copy_pending(struct rfcomm_dev *buffer1)
{
	struct tty_struct *buffer2 = dev->port.tty;
	struct sk_buff *buffer3;
	int inserted = 0;

	if (!buffer2)
		return;

	BT_DBG("dev %p tty %p", buffer1, buffer2);

	rfcomm_dlc_lock(buffer1->dlc);

	while ((buffer3 = skb_dequeue(&buffer1->pending))) {
		inserted += tty_insert_flip_string(buffer2, buffer3->data, buffer3->len);
		kfree_skb(buffer3);
	}

	rfcomm_dlc_unlock(buffer1->dlc);

	if (inserted > 0)
		tty_flip_buffer_push(buffer2);
}
