static int mailimf_time_parse(const char * message, size_t length,
			      size_t * indx, 
			      int * phour, int * pmin,
			      int * psec,
			      int * pzone)
{
  size_t cur_token;
  int hour;
  int min;
  int sec;
  int zone;
  int r;

  cur_token = * indx;

  r = mailimf_cfws_parse(message, length, &cur_token);
  if ((r != MAILIMF_NO_ERROR) && (r != MAILIMF_ERROR_PARSE))
    return r;

  r = mailimf_time_of_day_parse(message, length, &cur_token,
				&hour, &min, &sec);
  if (r != MAILIMF_NO_ERROR)
    return r;

  r = mailimf_fws_parse(message, length, &cur_token);
  if ((r != MAILIMF_NO_ERROR) && (r != MAILIMF_ERROR_PARSE))
    return r;

  r = mailimf_zone_parse(message, length, &cur_token, &zone);
  if (r == MAILIMF_NO_ERROR) {
    /* do nothing */
  }
  else if (r == MAILIMF_ERROR_PARSE) {
    zone = 0;
  }
  else {
    return r;
  }

  * phour = hour;
  * pmin = min;
  * psec = sec;
  * pzone = zone;

  * indx = cur_token;

  return MAILIMF_NO_ERROR;
}
