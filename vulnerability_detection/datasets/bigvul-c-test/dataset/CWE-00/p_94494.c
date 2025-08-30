static void rfcomm_tty_copy_pending(struct rfcomm_dev *dev)
{
	struct tty_struct *tty = dev->port.tty;
	struct sk_buff *skb;
	int inserted = 0;

	if (!tty)
		return;

	BT_DBG("dev %p tty %p", dev, tty);

	rfcomm_dlc_lock(dev->dlc);

	while ((skb = skb_dequeue(&dev->pending))) {
		inserted += tty_insert_flip_string(tty, skb->data, skb->len);
		kfree_skb(skb);
	}

	rfcomm_dlc_unlock(dev->dlc);

	if (inserted > 0)
		tty_flip_buffer_push(tty);
}
