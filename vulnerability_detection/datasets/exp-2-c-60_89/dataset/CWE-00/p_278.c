void b43_dummy_transmission(struct b43_wldev *dev, bool ofdm, bool pa_on)
{
	struct b43_phy *phy = &dev->phy;
	unsigned int i, max_loop;
	u16 value;
	u32 buffer[5] = {
		0x00000000,
		0x00D40000,
		0x00000000,
		0x01000000,
		0x00000000,
	};

	if (ofdm) {
		max_loop = 0x1E;
		buffer[0] = 0x000201CC;
	} else {
		max_loop = 0xFA;
		buffer[0] = 0x000B846E;
	}

	for (i = 0; i < 5; i++)
		b43_ram_write(dev, i * 4, buffer[i]);

	b43_write16(dev, B43_MMIO_XMTSEL, 0x0000);

	if (dev->dev->core_rev < 11)
		b43_write16(dev, B43_MMIO_WEPCTL, 0x0000);
	else
		b43_write16(dev, B43_MMIO_WEPCTL, 0x0100);

	value = (ofdm ? 0x41 : 0x40);
	b43_write16(dev, B43_MMIO_TXE0_PHYCTL, value);
	if (phy->type == B43_PHYTYPE_N || phy->type == B43_PHYTYPE_LP ||
	    phy->type == B43_PHYTYPE_LCN)
		b43_write16(dev, B43_MMIO_TXE0_PHYCTL1, 0x1A02);

	b43_write16(dev, B43_MMIO_TXE0_WM_0, 0x0000);
	b43_write16(dev, B43_MMIO_TXE0_WM_1, 0x0000);

	b43_write16(dev, B43_MMIO_XMTTPLATETXPTR, 0x0000);
	b43_write16(dev, B43_MMIO_XMTTXCNT, 0x0014);
	b43_write16(dev, B43_MMIO_XMTSEL, 0x0826);
	b43_write16(dev, B43_MMIO_TXE0_CTL, 0x0000);

	if (!pa_on && phy->type == B43_PHYTYPE_N)
		; /*b43_nphy_pa_override(dev, false) */

	switch (phy->type) {
	case B43_PHYTYPE_N:
	case B43_PHYTYPE_LCN:
		b43_write16(dev, B43_MMIO_TXE0_AUX, 0x00D0);
		break;
	case B43_PHYTYPE_LP:
		b43_write16(dev, B43_MMIO_TXE0_AUX, 0x0050);
		break;
	default:
		b43_write16(dev, B43_MMIO_TXE0_AUX, 0x0030);
	}
	b43_read16(dev, B43_MMIO_TXE0_AUX);

	if (phy->radio_ver == 0x2050 && phy->radio_rev <= 0x5)
		b43_radio_write16(dev, 0x0051, 0x0017);
	for (i = 0x00; i < max_loop; i++) {
		value = b43_read16(dev, B43_MMIO_TXE0_STATUS);
		if (value & 0x0080)
			break;
		udelay(10);
	}
	for (i = 0x00; i < 0x0A; i++) {
		value = b43_read16(dev, B43_MMIO_TXE0_STATUS);
		if (value & 0x0400)
			break;
		udelay(10);
	}
	for (i = 0x00; i < 0x19; i++) {
		value = b43_read16(dev, B43_MMIO_IFSSTAT);
		if (!(value & 0x0100))
			break;
		udelay(10);
	}
	if (phy->radio_ver == 0x2050 && phy->radio_rev <= 0x5)
		b43_radio_write16(dev, 0x0051, 0x0037);
}