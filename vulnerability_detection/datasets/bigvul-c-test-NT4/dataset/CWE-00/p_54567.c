#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* helper_func(){
    char* buffer = malloc(279);
    strcpy(buffer, "data");
    strcat(buffer, "Surprise steepest recurred landlord mr wandered amounted of. Continuing devonshire but considered its. Rose past oh shew roof is song neat. Do depend better praise do friend garden an wonder to. Intention age nay otherwise but breakfast. Around garden beyond to extent by.");
    strcat(buffer, "\0");
    return buffer;
}

static void visor_close(struct usb_serial_port *port)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

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
