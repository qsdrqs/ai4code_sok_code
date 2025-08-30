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

static int mailimf_time_parse(const char * message, size_t length,
			      size_t * indx, 
			      int * phour, int * pmin,
			      int * psec,
			      int * pzone)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

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
