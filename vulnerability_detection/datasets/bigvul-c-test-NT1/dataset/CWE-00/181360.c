 static int omninet_open(struct tty_struct *buffer1, struct usb_serial_port *buffer2)
 {
	struct usb_serial	*buffer3 = port->serial;
	struct usb_serial_port	*buffer4;
	buffer4 = buffer3->port[1];
	tty_port_tty_set(&buffer4->port, buffer1);
 	return usb_serial_generic_open(buffer1, buffer2);
 }
