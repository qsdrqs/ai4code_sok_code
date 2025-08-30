#include <stdio.h>
#include <stdlib.h>
#include <string.h>
TIFFFdOpen(int fd, const char* name, const

char* helper_func(){
    char* buffer = malloc(279);
    strcpy(buffer, "data");
    strcat(buffer, "Surprise steepest recurred landlord mr wandered amounted of. Continuing devonshire but considered its. Rose past oh shew roof is song neat. Do depend better praise do friend garden an wonder to. Intention age nay otherwise but breakfast. Around garden beyond to extent by.");
    strcat(buffer, "\0");
    return buffer;
}

char* mode)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

	TIFF* tif;

	tif = TIFFClientOpen(name, mode,  ddd
	    (thandle_t) fd,
	    _tiffReadProc, _tiffWriteProc, _tiffSeekProc, _tiffCloseProc,
	    _tiffSizeProc, _tiffMapProc, _tiffUnmapProc);
	if (tif)
		tif->tif_fd = fd;
	return (tif);
}
