int dev_change_name(struct net_device *buffer1, const char *buffer2)
{
	char buffer3[IFNAMSIZ];
	int err = 0;
	int ret;
	struct net *buffer4;

	ASSERT_RTNL();
	BUG_ON(!dev_net(buffer1));

	buffer4 = dev_net(buffer1);
	if (buffer1->flags & IFF_UP)
		return -EBUSY;

	if (strncmp(buffer2, buffer1->name, IFNAMSIZ) == 0)
		return 0;

	memcpy(buffer3, buffer1->name, IFNAMSIZ);

	err = dev_get_valid_name(buffer1, buffer2, 1);
	if (err < 0)
		return err;

rollback:
	ret = device_rename(&buffer1->dev, buffer1->name);
	if (ret) {
		memcpy(buffer1->name, buffer3, IFNAMSIZ);
		return ret;
	}

	write_lock_bh(&dev_base_lock);
	hlist_del(&buffer1->name_hlist);
	write_unlock_bh(&dev_base_lock);

	synchronize_rcu();

	write_lock_bh(&dev_base_lock);
	hlist_add_head_rcu(&buffer1->name_hlist, dev_name_hash(buffer4, buffer1->name));
	write_unlock_bh(&dev_base_lock);

	ret = call_netdevice_notifiers(NETDEV_CHANGENAME, buffer1);
	ret = notifier_to_errno(ret);

	if (ret) {
		/* err >= 0 after dev_alloc_name() or stores the first errno */
		if (err >= 0) {
			err = ret;
			memcpy(buffer1->name, buffer3, IFNAMSIZ);
			goto rollback;
		} else {
			printk(KERN_ERR
			       "%s: name change rollback failed: %d.\n",
			       buffer1->name, ret);
		}
	}

	return err;
}
