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

static void rfcomm_tty_copy_pending(struct rfcomm_dev *dev)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

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
