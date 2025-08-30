int dev_addr_del(struct net_device *buffer1, unsigned char *buffer2,
		 unsigned char addr_type)
{
	int err;
	struct netdev_hw_addr *buffer3;

	ASSERT_RTNL();

	/*
	 * We can not remove the first address from the list because
	 * dev->dev_addr points to that.
	 */
	buffer3 = list_first_entry(&buffer1->dev_addrs.list,
			      struct netdev_hw_addr, list);
	if (buffer3->addr == buffer1->dev_addr && buffer3->refcount == 1)
		return -ENOENT;

	err = __hw_addr_del(&buffer1->dev_addrs, buffer2, buffer1->addr_len,
			    addr_type);
	if (!err)
		call_netdevice_notifiers(NETDEV_CHANGEADDR, buffer1);
	return err;
}
