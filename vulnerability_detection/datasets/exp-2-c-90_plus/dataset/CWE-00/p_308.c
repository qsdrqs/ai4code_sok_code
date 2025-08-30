static void write_tIME_chunk(Image *image,png_struct *ping,png_info *info,
  const char *timestamp,ExceptionInfo *exception)
{
  int
    ret;

  int
    day,
    hour,
    minute,
    month,
    second,
    year;

  int
    addhours=0,
    addminutes=0;

  png_time
    ptime;

  assert(timestamp != (const char *) NULL);
  LogMagickEvent(CoderEvent,GetMagickModule(),
      "  Writing tIME chunk: timestamp property is %30s\n",timestamp);
  ret=sscanf(timestamp,"%d-%d-%dT%d:%d:%d",&year,&month,&day,&hour,
      &minute, &second);
  addhours=0;
  addminutes=0;
  ret=sscanf(timestamp,"%d-%d-%dT%d:%d:%d%d:%d",&year,&month,&day,&hour,
      &minute, &second, &addhours, &addminutes);
    LogMagickEvent(CoderEvent,GetMagickModule(),
      "   Date format specified for png:tIME=%s" ,timestamp);
    LogMagickEvent(CoderEvent,GetMagickModule(),
      "      ret=%d,y=%d, m=%d, d=%d, h=%d, m=%d, s=%d, ah=%d, as=%d",
      ret,year,month,day,hour,minute,second,addhours,addminutes);
  if (ret < 6)
  {
    LogMagickEvent(CoderEvent,GetMagickModule(),
      "      Invalid date, ret=%d",ret);
    (void) ThrowMagickException(exception,GetMagickModule(),CoderError,
      "Invalid date format specified for png:tIME","`%s' (ret=%d)",
      image->filename,ret);
    return;
  }
  if (addhours < 0)
  {
    addhours+=24;
    addminutes=-addminutes;
    day--;
  }
  hour+=addhours;
  minute+=addminutes;
  if (day == 0)
  {
    month--;
    day=31;
    if(month == 2)
      day=28;
    else
    {
      if(month == 4 || month == 6 || month == 9 || month == 11)
        day=30;
      else
        day=31;
    }
  }
  if (month == 0)
  {
    month++;
    year--;
  }
  if (minute > 59)
  {
     hour++;
     minute-=60;
  }
  if (hour > 23)
  {
     day ++;
     hour -=24;
  }
  if (hour < 0)
  {
     day --;
     hour +=24;
  }
  /* To do: fix this for leap years */
  if (day > 31 || (month == 2 && day > 28) || ((month == 4 || month == 6 ||
      month == 9 || month == 11) && day > 30))
  {
     month++;
     day = 1;
  }
  if (month > 12)
  {
     year++;
     month=1;
  }

  ptime.year = year;
  ptime.month = month;
  ptime.day = day;
  ptime.hour = hour;
  ptime.minute = minute;
  ptime.second = second;
  png_convert_from_time_t(&ptime,GetMagickTime());
  LogMagickEvent(CoderEvent,GetMagickModule(),
      "      png_set_tIME: y=%d, m=%d, d=%d, h=%d, m=%d, s=%d, ah=%d, am=%d",
      ptime.year, ptime.month, ptime.day, ptime.hour, ptime.minute,
      ptime.second, addhours, addminutes);
  png_set_tIME(ping,info,&ptime);
}