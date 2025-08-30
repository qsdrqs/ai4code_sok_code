void CLASS sinar_4shot_load_raw()
{
  ushort *pixel;
  unsigned shot, row, col, r, c;

  if (raw_image)
  {
    shot = LIM(shot_select, 1, 4) - 1;
    fseek(ifp, data_offset + shot * 4, SEEK_SET);
    fseek(ifp, get4(), SEEK_SET);
    unpacked_load_raw();
    return;
  }
  pixel = (ushort *)calloc(raw_width, sizeof *pixel);
  merror(pixel, "sinar_4shot_load_raw()");
#ifdef LIBRAW_LIBRARY_BUILD
  try
  {
#endif
    for (shot = 0; shot < 4; shot++)
    {
#ifdef LIBRAW_LIBRARY_BUILD
      checkCancel();
#endif
      fseek(ifp, data_offset + shot * 4, SEEK_SET);
      fseek(ifp, get4(), SEEK_SET);
      for (row = 0; row < raw_height; row++)
      {
        read_shorts(pixel, raw_width);
        if ((r = row - top_margin - (shot >> 1 & 1)) >= height)
          continue;
        for (col = 0; col < raw_width; col++)
        {
          if ((c = col - left_margin - (shot & 1)) >= width)
            continue;
          image[r * width + c][(row & 1) * 3 ^ (~col & 1)] = pixel[col];
        }
      }
    }
#ifdef LIBRAW_LIBRARY_BUILD
  }
  catch (...)
  {
    free(pixel);
    throw;
  }
#endif
  free(pixel);
  mix_green = 1;
}