 static __u8 *non_vulnerable_func(struct hid_device *hdev, __u8 *rdesc,
 		unsigned int *rsize)
 {
	if (*rsize >= 17 && rdesc[11] == 0x3c && rdesc[12] == 0x02) {
 		hid_info(hdev, "fixing up Cherry Cymotion report descriptor\n");
 		rdesc[11] = rdesc[16] = 0xff;
 		rdesc[12] = rdesc[17] = 0x03;
	}
	return rdesc;
}
