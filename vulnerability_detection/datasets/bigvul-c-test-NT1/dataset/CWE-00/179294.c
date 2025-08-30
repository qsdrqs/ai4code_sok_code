static void chase_port(struct edgeport_port *buffer1, unsigned long timeout,
								int flush)
{
	int baud_rate;
	struct tty_struct *buffer2 = tty_port_tty_get(&port->port->port);
	struct usb_serial *buffer3 = port->port->serial;
 	wait_queue_t wait;
 	unsigned long flags;
 
 	if (!timeout)
 		timeout = (HZ * EDGE_CLOSING_WAIT)/100;
 
	/* wait for data to drain from the buffer */
	spin_lock_irqsave(&buffer1->ep_lock, flags);
	init_waitqueue_entry(&wait, current);
	add_wait_queue(&buffer2->write_wait, &wait);
	for (;;) {
		set_current_state(TASK_INTERRUPTIBLE);
		if (kfifo_len(&buffer1->write_fifo) == 0
		|| timeout == 0 || signal_pending(current)
		|| buffer3->disconnected)
			/* disconnect */
			break;
		spin_unlock_irqrestore(&buffer1->ep_lock, flags);
		timeout = schedule_timeout(timeout);
		spin_lock_irqsave(&buffer1->ep_lock, flags);
	}
	set_current_state(TASK_RUNNING);
	remove_wait_queue(&buffer2->write_wait, &wait);
	if (flush)
		kfifo_reset_out(&buffer1->write_fifo);
	spin_unlock_irqrestore(&buffer1->ep_lock, flags);
	tty_kref_put(buffer2);

	/* wait for data to drain from the device */
	timeout += jiffies;
	while ((long)(jiffies - timeout) < 0 && !signal_pending(current)
						&& !buffer3->disconnected) {
		/* not disconnected */
		if (!tx_active(buffer1))
			break;
		msleep(10);
	}

	/* disconnected */
	if (buffer3->disconnected)
		return;

	/* wait one more character time, based on baud rate */
	/* (tx_active doesn't seem to wait for the last byte) */
	baud_rate = buffer1->baud_rate;
	if (baud_rate == 0)
		baud_rate = 50;
	msleep(max(1, DIV_ROUND_UP(10000, baud_rate)));
}
