static noinline_for_stack int rtnl_fill_vfinfo(struct sk_buff *skb,
					       struct net_device *dev,
					       int vfs_num,
					       struct nlattr *vfinfo)
{
	struct ifla_vf_rss_query_en vf_rss_query_en;
	struct ifla_vf_link_state vf_linkstate;
	struct ifla_vf_spoofchk vf_spoofchk;
	struct ifla_vf_tx_rate vf_tx_rate;
	struct ifla_vf_stats vf_stats;
	struct ifla_vf_trust vf_trust;
	struct ifla_vf_vlan vf_vlan;
	struct ifla_vf_rate vf_rate;
	struct nlattr *vf, *vfstats;
	struct ifla_vf_mac vf_mac;
	struct ifla_vf_info ivi;

	/* Not all SR-IOV capable drivers support the
	 * spoofcheck and "RSS query enable" query.  Preset to
	 * -1 so the user space tool can detect that the driver
	 * didn't report anything.
	 */
	ivi.spoofchk = -1;
	ivi.rss_query_en = -1;
	ivi.trusted = -1;
	memset(ivi.mac, 0, sizeof(ivi.mac));
	/* The default value for VF link state is "auto"
	 * IFLA_VF_LINK_STATE_AUTO which equals zero
	 */
	ivi.linkstate = 0;
	if (dev->netdev_ops->ndo_get_vf_config(dev, vfs_num, &ivi))
		return 0;

	vf_mac.vf =
		vf_vlan.vf =
		vf_rate.vf =
		vf_tx_rate.vf =
		vf_spoofchk.vf =
		vf_linkstate.vf =
		vf_rss_query_en.vf =
		vf_trust.vf = ivi.vf;

	memcpy(vf_mac.mac, ivi.mac, sizeof(ivi.mac));
	vf_vlan.vlan = ivi.vlan;
	vf_vlan.qos = ivi.qos;
	vf_tx_rate.rate = ivi.max_tx_rate;
	vf_rate.min_tx_rate = ivi.min_tx_rate;
	vf_rate.max_tx_rate = ivi.max_tx_rate;
	vf_spoofchk.setting = ivi.spoofchk;
	vf_linkstate.link_state = ivi.linkstate;
	vf_rss_query_en.setting = ivi.rss_query_en;
	vf_trust.setting = ivi.trusted;
	vf = nla_nest_start(skb, IFLA_VF_INFO);
	if (!vf) {
		nla_nest_cancel(skb, vfinfo);
		return -EMSGSIZE;
	}
	if (nla_put(skb, IFLA_VF_MAC, sizeof(vf_mac), &vf_mac) ||
	    nla_put(skb, IFLA_VF_VLAN, sizeof(vf_vlan), &vf_vlan) ||
	    nla_put(skb, IFLA_VF_RATE, sizeof(vf_rate),
		    &vf_rate) ||
	    nla_put(skb, IFLA_VF_TX_RATE, sizeof(vf_tx_rate),
		    &vf_tx_rate) ||
	    nla_put(skb, IFLA_VF_SPOOFCHK, sizeof(vf_spoofchk),
		    &vf_spoofchk) ||
	    nla_put(skb, IFLA_VF_LINK_STATE, sizeof(vf_linkstate),
		    &vf_linkstate) ||
	    nla_put(skb, IFLA_VF_RSS_QUERY_EN,
		    sizeof(vf_rss_query_en),
		    &vf_rss_query_en) ||
	    nla_put(skb, IFLA_VF_TRUST,
		    sizeof(vf_trust), &vf_trust))
		return -EMSGSIZE;
	memset(&vf_stats, 0, sizeof(vf_stats));
	if (dev->netdev_ops->ndo_get_vf_stats)
		dev->netdev_ops->ndo_get_vf_stats(dev, vfs_num,
						&vf_stats);
	vfstats = nla_nest_start(skb, IFLA_VF_STATS);
	if (!vfstats) {
		nla_nest_cancel(skb, vf);
		nla_nest_cancel(skb, vfinfo);
		return -EMSGSIZE;
	}
	if (nla_put_u64(skb, IFLA_VF_STATS_RX_PACKETS,
			vf_stats.rx_packets) ||
	    nla_put_u64(skb, IFLA_VF_STATS_TX_PACKETS,
			vf_stats.tx_packets) ||
	    nla_put_u64(skb, IFLA_VF_STATS_RX_BYTES,
			vf_stats.rx_bytes) ||
	    nla_put_u64(skb, IFLA_VF_STATS_TX_BYTES,
			vf_stats.tx_bytes) ||
	    nla_put_u64(skb, IFLA_VF_STATS_BROADCAST,
			vf_stats.broadcast) ||
	    nla_put_u64(skb, IFLA_VF_STATS_MULTICAST,
			vf_stats.multicast))
		return -EMSGSIZE;
	nla_nest_end(skb, vfstats);
	nla_nest_end(skb, vf);
	return 0;
}