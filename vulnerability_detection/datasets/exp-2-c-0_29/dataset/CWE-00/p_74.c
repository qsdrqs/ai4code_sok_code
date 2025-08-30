static double filter_hanning(const double x)
{
	/* A Cosine windowing function */
	return(0.5 + 0.5 * cos(M_PI * x));
}