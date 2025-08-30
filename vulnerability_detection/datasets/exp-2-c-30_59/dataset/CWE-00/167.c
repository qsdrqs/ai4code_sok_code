static void chrc_write_cb(struct gatt_db_attribute *attrib,
					unsigned int id, uint16_t offset,
					const uint8_t *value, size_t len,
					uint8_t opcode, struct bt_att *att,
					void *user_data)
{
	struct external_chrc *chrc = user_data;
	struct btd_device *device;
	struct queue *queue;
	DBusMessageIter iter;

	if (chrc->attrib != attrib) {
		error("Write callback called with incorrect attribute");
		goto fail;
	}

	device = att_get_device(att);
	if (!device) {
		error("Unable to find device object");
		goto fail;
	}

	if (!(chrc->props & BT_GATT_CHRC_PROP_WRITE_WITHOUT_RESP))
		queue = chrc->pending_writes;
	else
		queue = NULL;

	if (opcode == BT_ATT_OP_PREP_WRITE_REQ) {
		if (!device_is_trusted(device) && !chrc->prep_authorized &&
						chrc->req_prep_authorization)
			send_write(device, attrib, chrc->proxy, queue,
					id, value, len, offset,
					bt_att_get_link_type(att), true, true);
		else
			gatt_db_attribute_write_result(attrib, id, 0);

		return;
	}

	if (opcode == BT_ATT_OP_EXEC_WRITE_REQ)
		chrc->prep_authorized = false;

	if (chrc->write_io) {
		if (sock_io_send(chrc->write_io, value, len) < 0) {
			error("Unable to write: %s", strerror(errno));
			goto fail;
		}

		gatt_db_attribute_write_result(attrib, id, 0);
		return;
	}

	if (g_dbus_proxy_get_property(chrc->proxy, "WriteAcquired", &iter)) {
		if (acquire_write(chrc, device, attrib, id, value, len,
						bt_att_get_link_type(att)))
			return;
	}

	if (send_write(device, attrib, chrc->proxy, queue, id, value, len,
			offset, bt_att_get_link_type(att), false, false))
		return;

fail:
	gatt_db_attribute_write_result(attrib, id, BT_ATT_ERROR_UNLIKELY);
}