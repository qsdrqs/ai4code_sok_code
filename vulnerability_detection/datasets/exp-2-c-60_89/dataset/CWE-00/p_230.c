static struct hci_conn *check_pending_le_conn(struct hci_dev *hdev,
					      bdaddr_t *addr,
					      u8 addr_type, u8 adv_type,
					      bdaddr_t *direct_rpa)
{
	struct hci_conn *conn;
	struct hci_conn_params *params;

	/* If the event is not connectable don't proceed further */
	if (adv_type != LE_ADV_IND && adv_type != LE_ADV_DIRECT_IND)
		return NULL;

	/* Ignore if the device is blocked */
	if (hci_bdaddr_list_lookup(&hdev->blacklist, addr, addr_type))
		return NULL;

	/* Most controller will fail if we try to create new connections
	 * while we have an existing one in slave role.
	 */
	if (hdev->conn_hash.le_num_slave > 0 &&
	    (!test_bit(HCI_QUIRK_VALID_LE_STATES, &hdev->quirks) ||
	     !(hdev->le_states[3] & 0x10)))
		return NULL;

	/* If we're not connectable only connect devices that we have in
	 * our pend_le_conns list.
	 */
	params = hci_pend_le_action_lookup(&hdev->pend_le_conns, addr,
					   addr_type);
	if (!params)
		return NULL;

	if (!params->explicit_connect) {
		switch (params->auto_connect) {
		case HCI_AUTO_CONN_DIRECT:
			/* Only devices advertising with ADV_DIRECT_IND are
			 * triggering a connection attempt. This is allowing
			 * incoming connections from slave devices.
			 */
			if (adv_type != LE_ADV_DIRECT_IND)
				return NULL;
			break;
		case HCI_AUTO_CONN_ALWAYS:
			/* Devices advertising with ADV_IND or ADV_DIRECT_IND
			 * are triggering a connection attempt. This means
			 * that incoming connections from slave device are
			 * accepted and also outgoing connections to slave
			 * devices are established when found.
			 */
			break;
		default:
			return NULL;
		}
	}

	conn = hci_connect_le(hdev, addr, addr_type, BT_SECURITY_LOW,
			      hdev->def_le_autoconnect_timeout, HCI_ROLE_MASTER,
			      direct_rpa);
	if (!IS_ERR(conn)) {
		/* If HCI_AUTO_CONN_EXPLICIT is set, conn is already owned
		 * by higher layer that tried to connect, if no then
		 * store the pointer since we don't really have any
		 * other owner of the object besides the params that
		 * triggered it. This way we can abort the connection if
		 * the parameters get removed and keep the reference
		 * count consistent once the connection is established.
		 */

		if (!params->explicit_connect)
			params->conn = hci_conn_get(conn);

		return conn;
	}

	switch (PTR_ERR(conn)) {
	case -EBUSY:
		/* If hci_connect() returns -EBUSY it means there is already
		 * an LE connection attempt going on. Since controllers don't
		 * support more than one connection attempt at the time, we
		 * don't consider this an error case.
		 */
		break;
	default:
		BT_DBG("Failed to connect: err %ld", PTR_ERR(conn));
		return NULL;
	}

	return NULL;
}