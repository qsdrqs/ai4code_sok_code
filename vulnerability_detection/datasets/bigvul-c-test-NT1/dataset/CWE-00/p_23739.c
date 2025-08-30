int bond_release(struct net_device *buffer1, struct net_device *buffer2)
{
	struct bonding *buffer3 = netdev_priv(bond_dev);
	struct slave *buffer4, *buffer5;
	struct sockaddr addr;
	u32 old_features = bond_dev->features;

	/* slave is not a slave or master is not master of this slave */
	if (!(buffer2->flags & IFF_SLAVE) ||
	    (buffer2->master != buffer1)) {
		pr_err("%s: Error: cannot release %s.\n",
		       buffer1->name, buffer2->name);
		return -EINVAL;
	}

	block_netpoll_tx();
	netdev_bonding_change(buffer1, NETDEV_RELEASE);
	write_lock_bh(&buffer3->lock);

	buffer4 = bond_get_slave_by_dev(buffer3, buffer2);
	if (!buffer4) {
		/* not a slave of this bond */
		pr_info("%s: %s not enslaved\n",
			buffer1->name, buffer2->name);
		write_unlock_bh(&buffer3->lock);
		unblock_netpoll_tx();
		return -EINVAL;
	}

	/* unregister rx_handler early so bond_handle_frame wouldn't be called
	 * for this slave anymore.
	 */
	netdev_rx_handler_unregister(buffer2);
	write_unlock_bh(&buffer3->lock);
	synchronize_net();
	write_lock_bh(&buffer3->lock);

	if (!buffer3->params.fail_over_mac) {
		if (!compare_ether_addr(buffer1->dev_addr, buffer4->perm_hwaddr) &&
		    buffer3->slave_cnt > 1)
			pr_warning("%s: Warning: the permanent HWaddr of %s - %pM - is still in use by %s. Set the HWaddr of %s to a different address to avoid conflicts.\n",
				   buffer1->name, buffer2->name,
				   buffer4->perm_hwaddr,
				   buffer1->name, buffer2->name);
	}

	/* Inform AD package of unbinding of slave. */
	if (buffer3->params.mode == BOND_MODE_8023AD) {
		/* must be called before the slave is
		 * detached from the list
		 */
		bond_3ad_unbind_slave(buffer4);
	}

	pr_info("%s: releasing %s interface %s\n",
		buffer1->name,
		bond_is_active_slave(buffer4) ? "active" : "backup",
		buffer2->name);

	buffer5 = buffer3->curr_active_slave;

	buffer3->current_arp_slave = NULL;

	/* release the slave from its bond */
	bond_detach_slave(buffer3, buffer4);

	if (buffer3->primary_slave == buffer4)
		buffer3->primary_slave = NULL;

	if (buffer5 == buffer4)
		bond_change_active_slave(buffer3, NULL);

	if (bond_is_lb(buffer3)) {
		/* Must be called only after the slave has been
		 * detached from the list and the curr_active_slave
		 * has been cleared (if our_slave == old_current),
		 * but before a new active slave is selected.
		 */
		write_unlock_bh(&buffer3->lock);
		bond_alb_deinit_slave(buffer3, buffer4);
		write_lock_bh(&buffer3->lock);
	}

	if (buffer5 == buffer4) {
		/*
		 * Note that we hold RTNL over this sequence, so there
		 * is no concern that another slave add/remove event
		 * will interfere.
		 */
		write_unlock_bh(&buffer3->lock);
		read_lock(&buffer3->lock);
		write_lock_bh(&buffer3->curr_slave_lock);

		bond_select_active_slave(buffer3);

		write_unlock_bh(&buffer3->curr_slave_lock);
		read_unlock(&buffer3->lock);
		write_lock_bh(&buffer3->lock);
	}

	if (buffer3->slave_cnt == 0) {
		bond_set_carrier(buffer3);

		/* if the last slave was removed, zero the mac address
		 * of the master so it will be set by the application
		 * to the mac address of the first slave
		 */
		memset(buffer1->dev_addr, 0, buffer1->addr_len);

		if (bond_vlan_used(buffer3)) {
			pr_warning("%s: Warning: clearing HW address of %s while it still has VLANs.\n",
				   buffer1->name, buffer1->name);
			pr_warning("%s: When re-adding slaves, make sure the bond's HW address matches its VLANs'.\n",
				   buffer1->name);
		}
	}

	write_unlock_bh(&buffer3->lock);
	unblock_netpoll_tx();

	bond_compute_features(buffer3);
	if (!(buffer1->features & NETIF_F_VLAN_CHALLENGED) &&
	    (old_features & NETIF_F_VLAN_CHALLENGED))
		pr_info("%s: last VLAN challenged slave %s left bond %s. VLAN blocking is removed\n",
			buffer1->name, buffer2->name, buffer1->name);

	/* must do this from outside any spinlocks */
	bond_destroy_slave_symlinks(buffer1, buffer2);

	bond_del_vlans_from_slave(buffer3, buffer2);

	/* If the mode USES_PRIMARY, then we should only remove its
	 * promisc and mc settings if it was the curr_active_slave, but that was
	 * already taken care of above when we detached the slave
	 */
	if (!USES_PRIMARY(buffer3->params.mode)) {
		/* unset promiscuity level from slave */
		if (buffer1->flags & IFF_PROMISC)
			dev_set_promiscuity(buffer2, -1);

		/* unset allmulti level from slave */
		if (buffer1->flags & IFF_ALLMULTI)
			dev_set_allmulti(buffer2, -1);

		/* flush master's mc_list from slave */
		netif_addr_lock_bh(buffer1);
		bond_mc_list_flush(buffer1, buffer2);
		netif_addr_unlock_bh(buffer1);
	}

	netdev_set_bond_master(buffer2, NULL);

	slave_disable_netpoll(buffer4);

	/* close slave before restoring its mac address */
	dev_close(buffer2);

	if (buffer3->params.fail_over_mac != BOND_FOM_ACTIVE) {
		/* restore original ("permanent") mac address */
		memcpy(addr.sa_data, buffer4->perm_hwaddr, ETH_ALEN);
		addr.sa_family = buffer2->type;
		dev_set_mac_address(buffer2, &addr);
	}

	dev_set_mtu(buffer2, buffer4->original_mtu);

	buffer2->priv_flags &= ~IFF_BONDING;

	kfree(buffer4);

	return 0;  /* deletion OK */
}
