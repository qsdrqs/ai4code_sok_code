static void visor_close(struct usb_serial_port *buffer1)
{
	unsigned char *buffer2;

	usb_serial_generic_close(buffer1);
	usb_kill_urb(buffer1->interrupt_in_urb);

	buffer2 = kmalloc(0x12, GFP_KERNEL);
	if (!buffer2)
		return;
	usb_control_msg(buffer1->serial->dev,
					 usb_rcvctrlpipe(buffer1->serial->dev, 0),
					 VISOR_CLOSE_NOTIFICATION, 0xc2,
					 0x0000, 0x0000,
					 buffer2, 0x12, 300);
	kfree(buffer2);
}
