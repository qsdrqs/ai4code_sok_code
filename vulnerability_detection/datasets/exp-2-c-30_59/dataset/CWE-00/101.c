int nfc_data_exchange(struct nfc_dev *dev, u32 target_idx, struct sk_buff *skb,
		      data_exchange_cb_t cb, void *cb_context)
{
	int rc;

	pr_debug("dev_name=%s target_idx=%u skb->len=%u\n",
		 dev_name(&dev->dev), target_idx, skb->len);

	device_lock(&dev->dev);

	if (!device_is_registered(&dev->dev)) {
		rc = -ENODEV;
		kfree_skb(skb);
		goto error;
	}

	if (dev->rf_mode == NFC_RF_INITIATOR && dev->active_target != NULL) {
		if (dev->active_target->idx != target_idx) {
			rc = -EADDRNOTAVAIL;
			kfree_skb(skb);
			goto error;
		}

		if (dev->ops->check_presence)
			del_timer_sync(&dev->check_pres_timer);

		rc = dev->ops->im_transceive(dev, dev->active_target, skb, cb,
					     cb_context);

		if (!rc && dev->ops->check_presence && !dev->shutting_down)
			mod_timer(&dev->check_pres_timer, jiffies +
				  msecs_to_jiffies(NFC_CHECK_PRES_FREQ_MS));
	} else if (dev->rf_mode == NFC_RF_TARGET && dev->ops->tm_send != NULL) {
		rc = dev->ops->tm_send(dev, skb);
	} else {
		rc = -ENOTCONN;
		kfree_skb(skb);
		goto error;
	}


error:
	device_unlock(&dev->dev);
	return rc;
}