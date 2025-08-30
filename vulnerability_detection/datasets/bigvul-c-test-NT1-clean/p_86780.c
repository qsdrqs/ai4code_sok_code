TIFFFdOpen(int fd, const char* buffer1, const char* mode)
{
	TIFF* buffer2;

	buffer2 = TIFFClientOpen(buffer1, mode,  ddd
	    (thandle_t) fd,
	    _tiffReadProc, _tiffWriteProc, _tiffSeekProc, _tiffCloseProc,
	    _tiffSizeProc, _tiffMapProc, _tiffUnmapProc);
	if (buffer2)
		buffer2->tif_fd = fd;
	return (buffer2);
}
