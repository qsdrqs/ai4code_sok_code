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

static int set_register(pegasus_t *pegasus, __u16 indx, __u8 data)
 {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

 	int ret;
 
 	ret = usb_control_msg(pegasus->usb, usb_sndctrlpipe(pegasus->usb, 0),
 			      PEGASUS_REQ_SET_REG, PEGASUS_REQT_WRITE, data,
			      indx, &data, 1, 1000);
 	if (ret < 0)
 		netif_dbg(pegasus, drv, pegasus->net,
 			  "%s returned %d\n", __func__, ret);
 	return ret;
 }
