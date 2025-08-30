static int cypress_open(struct tty_struct *buffer1, struct usb_serial_port *buffer2)
{
	struct cypress_private *buffer3 = usb_get_serial_port_data(port);
	struct usb_serial *buffer4 = port->serial;
	unsigned long flags;
	int result = 0;

	if (!buffer3->comm_is_ok)
		return -EIO;

	/* clear halts before open */
	usb_clear_halt(buffer4->dev, 0x81);
	usb_clear_halt(buffer4->dev, 0x02);

	spin_lock_irqsave(&buffer3->lock, flags);
	/* reset read/write statistics */
	buffer3->bytes_in = 0;
	buffer3->bytes_out = 0;
	buffer3->cmd_count = 0;
	buffer3->rx_flags = 0;
	spin_unlock_irqrestore(&buffer3->lock, flags);

	/* Set termios */
	cypress_send(buffer2);

	if (buffer1)
 		cypress_set_termios(buffer1, buffer2, &buffer3->tmp_termios);
 
 	/* setup the port and start reading from the device */
	if (!buffer2->interrupt_in_urb) {
		dev_err(&buffer2->dev, "%s - interrupt_in_urb is empty!\n",
			__func__);
		return -1;
	}
 	usb_fill_int_urb(buffer2->interrupt_in_urb, buffer4->dev,
 		usb_rcvintpipe(buffer4->dev, buffer2->interrupt_in_endpointAddress),
 		buffer2->interrupt_in_urb->transfer_buffer,
		buffer2->interrupt_in_urb->transfer_buffer_length,
		cypress_read_int_callback, buffer2, buffer3->read_urb_interval);
	result = usb_submit_urb(buffer2->interrupt_in_urb, GFP_KERNEL);

	if (result) {
		dev_err(&buffer2->dev,
			"%s - failed submitting read urb, error %d\n",
							__func__, result);
		cypress_set_dead(buffer2);
	}

	return result;
} /* cypress_open */
