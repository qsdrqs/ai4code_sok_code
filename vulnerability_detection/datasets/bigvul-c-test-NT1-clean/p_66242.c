static int mailimf_time_parse(const char * buffer1, size_t length,
			      size_t * buffer2, 
			      int * buffer3, int * buffer4,
			      int * buffer5,
			      int * buffer6)
{
  size_t cur_token;
  int hour;
  int min;
  int sec;
  int zone;
  int r;

  cur_token = * buffer2;

  r = mailimf_cfws_parse(buffer1, length, &cur_token);
  if ((r != MAILIMF_NO_ERROR) && (r != MAILIMF_ERROR_PARSE))
    return r;

  r = mailimf_time_of_day_parse(buffer1, length, &cur_token,
				&hour, &min, &sec);
  if (r != MAILIMF_NO_ERROR)
    return r;

  r = mailimf_fws_parse(buffer1, length, &cur_token);
  if ((r != MAILIMF_NO_ERROR) && (r != MAILIMF_ERROR_PARSE))
    return r;

  r = mailimf_zone_parse(buffer1, length, &cur_token, &zone);
  if (r == MAILIMF_NO_ERROR) {
    /* do nothing */
  }
  else if (r == MAILIMF_ERROR_PARSE) {
    zone = 0;
  }
  else {
    return r;
  }

  * buffer3 = hour;
  * buffer4 = min;
  * buffer5 = sec;
  * buffer6 = zone;

  * buffer2 = cur_token;

  return MAILIMF_NO_ERROR;
}
