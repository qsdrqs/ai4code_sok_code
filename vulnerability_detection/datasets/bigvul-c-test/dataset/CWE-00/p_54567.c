static void visor_close(struct usb_serial_port *port)
{
	unsigned char *transfer_buffer;

	usb_serial_generic_close(port);
	usb_kill_urb(port->interrupt_in_urb);

	transfer_buffer = kmalloc(0x12, GFP_KERNEL);
	if (!transfer_buffer)
		return;
	usb_control_msg(port->serial->dev,
					 usb_rcvctrlpipe(port->serial->dev, 0),
					 VISOR_CLOSE_NOTIFICATION, 0xc2,
					 0x0000, 0x0000,
					 transfer_buffer, 0x12, 300);
	kfree(transfer_buffer);
}
