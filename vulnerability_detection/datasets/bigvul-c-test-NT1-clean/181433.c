brcmf_cfg80211_mgmt_tx(struct wiphy *wiphy, struct wireless_dev *buffer1,
		       struct cfg80211_mgmt_tx_params *buffer2, u64 *cookie)
{
	struct brcmf_cfg80211_info *buffer3 = wiphy_to_cfg(wiphy);
	struct ieee80211_channel *buffer4 = params->chan;
	const u8 *buffer5 = params->buf;
	size_t len = params->len;
	const struct ieee80211_mgmt *buffer6;
	struct brcmf_cfg80211_vif *buffer7;
	s32 err = 0;
	s32 ie_offset;
	s32 ie_len;
	struct brcmf_fil_action_frame_le *buffer8;
	struct brcmf_fil_af_params_le *buffer9;
	bool ack;
	s32 chan_nr;
	u32 freq;

	brcmf_dbg(TRACE, "Enter\n");

	*cookie = 0;

	buffer6 = (const struct ieee80211_mgmt *)buffer5;

	if (!ieee80211_is_mgmt(buffer6->frame_control)) {
		brcmf_err("Driver only allows MGMT packet type\n");
		return -EPERM;
	}

	buffer7 = container_of(buffer1, struct brcmf_cfg80211_vif, buffer1);

	if (ieee80211_is_probe_resp(buffer6->frame_control)) {
		/* Right now the only reason to get a probe response */
		/* is for p2p listen response or for p2p GO from     */
		/* wpa_supplicant. Unfortunately the probe is send   */
		/* on primary ndev, while dongle wants it on the p2p */
		/* vif. Since this is only reason for a probe        */
		/* response to be sent, the vif is taken from cfg.   */
		/* If ever desired to send proberesp for non p2p     */
		/* response then data should be checked for          */
		/* "DIRECT-". Note in future supplicant will take    */
		/* dedicated p2p wdev to do this and then this 'hack'*/
		/* is not needed anymore.                            */
		ie_offset =  DOT11_MGMT_HDR_LEN +
			     DOT11_BCN_PRB_FIXED_LEN;
		ie_len = len - ie_offset;
		if (buffer7 == buffer3->p2p.bss_idx[P2PAPI_BSSCFG_PRIMARY].vif)
			buffer7 = buffer3->p2p.bss_idx[P2PAPI_BSSCFG_DEVICE].vif;
		err = brcmf_vif_set_mgmt_ie(buffer7,
					    BRCMF_VNDR_IE_PRBRSP_FLAG,
					    &buffer5[ie_offset],
					    ie_len);
 		cfg80211_mgmt_tx_status(buffer1, *cookie, buffer5, len, true,
 					GFP_KERNEL);
 	} else if (ieee80211_is_action(buffer6->frame_control)) {
 		buffer9 = kzalloc(sizeof(*buffer9), GFP_KERNEL);
 		if (buffer9 == NULL) {
 			brcmf_err("unable to allocate frame\n");
			err = -ENOMEM;
			goto exit;
		}
		buffer8 = &buffer9->action_frame;
		/* Add the packet Id */
		buffer8->packet_id = cpu_to_le32(*cookie);
		/* Add BSSID */
		memcpy(&buffer8->da[0], &buffer6->da[0], ETH_ALEN);
		memcpy(&buffer9->bssid[0], &buffer6->bssid[0], ETH_ALEN);
		/* Add the length exepted for 802.11 header  */
		buffer8->len = cpu_to_le16(len - DOT11_MGMT_HDR_LEN);
		/* Add the channel. Use the one specified as parameter if any or
		 * the current one (got from the firmware) otherwise
		 */
		if (buffer4)
			freq = buffer4->center_freq;
		else
			brcmf_fil_cmd_int_get(buffer7->ifp, BRCMF_C_GET_CHANNEL,
					      &freq);
		chan_nr = ieee80211_frequency_to_channel(freq);
		buffer9->channel = cpu_to_le32(chan_nr);

		memcpy(buffer8->data, &buffer5[DOT11_MGMT_HDR_LEN],
		       le16_to_cpu(buffer8->len));

		brcmf_dbg(TRACE, "Action frame, cookie=%lld, len=%d, freq=%d\n",
			  *cookie, le16_to_cpu(buffer8->len), freq);

		ack = brcmf_p2p_send_action_frame(buffer3, cfg_to_ndev(buffer3),
						  buffer9);

		cfg80211_mgmt_tx_status(buffer1, *cookie, buffer5, len, ack,
					GFP_KERNEL);
		kfree(buffer9);
	} else {
		brcmf_dbg(TRACE, "Unhandled, fc=%04x!!\n", buffer6->frame_control);
		brcmf_dbg_hex_dump(true, buffer5, len, "payload, len=%zu\n", len);
	}

exit:
	return err;
}
