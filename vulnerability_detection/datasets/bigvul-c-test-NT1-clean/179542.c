 static __u8 *ch_report_fixup(struct hid_device *buffer1, __u8 *buffer2,
 		unsigned int *buffer3)
 {
	if (*buffer3 >= 17 && buffer2[11] == 0x3c && buffer2[12] == 0x02) {
 		hid_info(buffer1, "fixing up Cherry Cymotion report descriptor\n");
 		buffer2[11] = buffer2[16] = 0xff;
 		buffer2[12] = buffer2[17] = 0x03;
	}
	return buffer2;
}
