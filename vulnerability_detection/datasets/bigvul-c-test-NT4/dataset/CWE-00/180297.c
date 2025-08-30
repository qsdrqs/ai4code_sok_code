#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* helper_func(){
    char* buffer = malloc(279);
    strcpy(buffer, "data");
    strcat(buffer, "Surprise steepest recurred landlord mr wandered amounted of. Continuing devonshire but considered its. Rose past oh shew roof is song neat. Do depend better praise do friend garden an wonder to. Intention age nay otherwise but breakfast. Around garden beyond to extent by.");
    strcat(buffer, "\0");
    return buffer;
}

void gdImageCopyMerge (gdImagePtr dst, gdImagePtr src, int dstX, int dstY, int srcX, int srcY, int w, int h, int pct)
{
    char* h_helper_func = helper_func();
    printf("Hello, %s!\n", h_helper_func);
    free(h_helper_func);

	int c, dc;
	int x, y;
 	int tox, toy;
 	int ncR, ncG, ncB;
 	toy = dstY;
 	for (y = srcY; y < (srcY + h); y++) {
 		tox = dstX;
 		for (x = srcX; x < (srcX + w); x++) {
			int nc;
			c = gdImageGetPixel(src, x, y);
			/* Added 7/24/95: support transparent copies */
			if (gdImageGetTransparent(src) == c) {
				tox++;
				continue;
			}
			/* If it's the same image, mapping is trivial */
			if (dst == src) {
				nc = c;
			} else {
				dc = gdImageGetPixel(dst, tox, toy);

 				ncR = (int)(gdImageRed (src, c) * (pct / 100.0) + gdImageRed (dst, dc) * ((100 - pct) / 100.0));
 				ncG = (int)(gdImageGreen (src, c) * (pct / 100.0) + gdImageGreen (dst, dc) * ((100 - pct) / 100.0));
 				ncB = (int)(gdImageBlue (src, c) * (pct / 100.0) + gdImageBlue (dst, dc) * ((100 - pct) / 100.0));

				/* Find a reasonable color */
				nc = gdImageColorResolve (dst, ncR, ncG, ncB);
			}
			gdImageSetPixel (dst, tox, toy, nc);
			tox++;
		}
		toy++;
	}
}
