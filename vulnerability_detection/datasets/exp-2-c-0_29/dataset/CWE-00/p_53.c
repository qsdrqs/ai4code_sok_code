int ath6kl_wmi_disconnect_cmd(struct wmi *wmi, u8 if_idx)
{
	int ret;

	ath6kl_dbg(ATH6KL_DBG_WMI, "wmi disconnect\n");

	wmi->traffic_class = 100;

	/* Disconnect command does not need to do a SYNC before. */
	ret = ath6kl_wmi_simple_cmd(wmi, if_idx, WMI_DISCONNECT_CMDID);

	return ret;
}