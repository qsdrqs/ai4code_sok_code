static void vulnerable_func(int sub_api, struct libusb_device_handle *dev_handle) {
	usbi_dbg("unsupported API call for 'close'");
}
