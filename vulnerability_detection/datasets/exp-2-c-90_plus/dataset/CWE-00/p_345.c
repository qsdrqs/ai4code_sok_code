static void gdImageAALine (gdImagePtr im, int x1, int y1, int x2, int y2, int col)
{
	/* keep them as 32bits */
	long x, y, inc, frac;
	long dx, dy,tmp;
	int w, wid, wstart; 
	int thick = im->thick; 

	if (!im->trueColor) {
		/* TBB: don't crash when the image is of the wrong type */
		gdImageLine(im, x1, y1, x2, y2, col);
		return;
	}

	/* TBB: use the clipping rectangle */
	if (clip_1d (&x1, &y1, &x2, &y2, im->cx1, im->cx2) == 0)
		return;
	if (clip_1d (&y1, &x1, &y2, &x2, im->cy1, im->cy2) == 0)
		return;

	dx = x2 - x1;
	dy = y2 - y1;

	if (dx == 0 && dy == 0) {
		/* TBB: allow setting points */
		gdImageSetPixel(im, x1, y1, col);
		return;
	} else {
		double ag;
		/* Cast the long to an int to avoid compiler warnings about truncation.
		 * This isn't a problem as computed dy/dx values came from ints above. */
		ag = fabs(abs((int)dy) < abs((int)dx) ? cos(atan2(dy, dx)) : sin(atan2(dy, dx)));
		if (ag != 0) {
			wid = thick / ag;
		} else {
			wid = 1;
		}
		if (wid == 0) {
			wid = 1;
		}
	}

	/* Axis aligned lines */
	if (dx == 0) {
		gdImageVLine(im, x1, y1, y2, col);
		return;
	} else if (dy == 0) {
		gdImageHLine(im, y1, x1, x2, col);
		return;
	}

	if (abs((int)dx) > abs((int)dy)) {
		if (dx < 0) {
			tmp = x1;
			x1 = x2;
			x2 = tmp;
			tmp = y1;
			y1 = y2;
			y2 = tmp;
			dx = x2 - x1;
			dy = y2 - y1;
		}
		y = y1;
		inc = (dy * 65536) / dx;
		frac = 0;
		/* TBB: set the last pixel for consistency (<=) */
		for (x = x1 ; x <= x2 ; x++) {
			wstart = y - wid / 2;
			for (w = wstart; w < wstart + wid; w++) {
			    gdImageSetAAPixelColor(im, x , w , col , (frac >> 8) & 0xFF);
			    gdImageSetAAPixelColor(im, x , w + 1 , col, (~frac >> 8) & 0xFF);
			}
			frac += inc;
			if (frac >= 65536) {
				frac -= 65536;
				y++;
			} else if (frac < 0) {
				frac += 65536;
				y--;
			}
		}
	} else {
		if (dy < 0) {
			tmp = x1;
			x1 = x2;
			x2 = tmp;
			tmp = y1;
			y1 = y2;
			y2 = tmp;
			dx = x2 - x1;
			dy = y2 - y1;
		}
		x = x1;
		inc = (dx * 65536) / dy;
		frac = 0;
		/* TBB: set the last pixel for consistency (<=) */
		for (y = y1 ; y <= y2 ; y++) {
			wstart = x - wid / 2;
			for (w = wstart; w < wstart + wid; w++) {
			    gdImageSetAAPixelColor(im, w , y  , col, (frac >> 8) & 0xFF);
			    gdImageSetAAPixelColor(im, w + 1, y, col, (~frac >> 8) & 0xFF);
			}
			frac += inc;
			if (frac >= 65536) {
				frac -= 65536;
				x++;
			} else if (frac < 0) {
				frac += 65536;
				x--;
			}
		}
	}
}