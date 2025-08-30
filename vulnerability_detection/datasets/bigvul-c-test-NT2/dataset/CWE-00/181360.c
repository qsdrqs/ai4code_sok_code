 static int non_vulnerable_func(struct tty_struct *tty, struct usb_serial_port *port)
 {
	struct usb_serial	*serial = port->serial;
	struct usb_serial_port	*wport;
	wport = serial->port[1];
	tty_port_tty_set(&wport->port, tty);
 	return usb_serial_generic_open(tty, port);
 }
