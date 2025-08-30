static Image *ReadOneJNGImage(MngInfo *buffer1,
    const ImageInfo *buffer2, ExceptionInfo *buffer3)
{
  Image
    *buffer4,
    *buffer5,
    *buffer6,
    *buffer7;

  ImageInfo
    *buffer8,
    *buffer9;

  MagickBooleanType
    logging;

  int
    unique_filenames;

  ssize_t
    y;

  MagickBooleanType
    status;

  png_uint_32
    jng_height,
    jng_width;

  png_byte
    jng_color_type,
    jng_image_sample_depth,
    jng_image_compression_method,
    jng_image_interlace_method,
    jng_alpha_sample_depth,
    jng_alpha_compression_method,
    jng_alpha_filter_method,
    jng_alpha_interlace_method;

  register const PixelPacket
    *buffer10;

  register ssize_t
    i,
    x;

  register PixelPacket
    *buffer11;

  register unsigned char
    *buffer12;

  unsigned int
    read_JSEP,
    reading_idat;

  size_t
    length;

  jng_alpha_compression_method=0;
  jng_alpha_sample_depth=8;
  jng_color_type=0;
  jng_height=0;
  jng_width=0;
  buffer4=(Image *) NULL;
  buffer5=(Image *) NULL;
  buffer8=(ImageInfo *) NULL;
  buffer9=(ImageInfo *) NULL;
  unique_filenames=0;

  logging=LogMagickEvent(CoderEvent,GetMagickModule(),
    "  Enter ReadOneJNGImage()");

  buffer6=buffer1->image;

  if (GetAuthenticPixelQueue(buffer6) != (PixelPacket *) NULL)
    {
      /*
        Allocate next image structure.
      */
      if (logging != MagickFalse)
        (void) LogMagickEvent(CoderEvent,GetMagickModule(),
           "  AcquireNextImage()");

      AcquireNextImage(buffer2,buffer6);

      if (GetNextImageInList(buffer6) == (Image *) NULL)
        return(DestroyImageList(buffer6));

      buffer6=SyncNextImageInList(buffer6);
    }
  buffer1->image=buffer6;

  /*
    Signature bytes have already been read.
  */

  read_JSEP=MagickFalse;
  reading_idat=MagickFalse;
  for (;;)
  {
    char
      buffer13[MaxTextExtent];

    unsigned char
      *buffer14;

    unsigned int
      count;

    /*
      Read a new JNG chunk.
    */
    status=SetImageProgress(buffer6,LoadImagesTag,TellBlob(buffer6),
      2*GetBlobSize(buffer6));

    if (status == MagickFalse)
      break;

    buffer13[0]='\0';
    (void) ConcatenateMagickString(buffer13,"errr",MaxTextExtent);
    length=ReadBlobMSBLong(buffer6);
    count=(unsigned int) ReadBlob(buffer6,4,(unsigned char *) buffer13);

    if (logging != MagickFalse)
      (void) LogMagickEvent(CoderEvent,GetMagickModule(),
        "  Reading JNG chunk type %c%c%c%c, length: %.20g",
        buffer13[0],buffer13[1],buffer13[2],buffer13[3],(double) length);

    if (length > PNG_UINT_31_MAX || count == 0)
      ThrowReaderException(CorruptImageError,"CorruptImage");

    buffer12=NULL;
    buffer14=(unsigned char *) NULL;
 
     if (length != 0)
       {
         buffer14=(unsigned char *) AcquireQuantumMemory(length+MagickPathExtent,
           sizeof(*buffer14));
 
        if (buffer14 == (unsigned char *) NULL)
          ThrowReaderException(ResourceLimitError,"MemoryAllocationFailed");

        for (i=0; i < (ssize_t) length; i++)
        {
          int
            c;

          c=ReadBlobByte(buffer6);
          if (c == EOF)
            break;
          buffer14[i]=(unsigned char) c;
        }

        buffer12=buffer14;
      }

    (void) ReadBlobMSBLong(buffer6);  /* read crc word */

    if (memcmp(buffer13,mng_JHDR,4) == 0)
      {
        if (length == 16)
          {
            jng_width=(size_t) ((buffer12[0] << 24) | (buffer12[1] << 16) |
              (buffer12[2] << 8) | buffer12[3]);
            jng_height=(size_t) ((buffer12[4] << 24) | (buffer12[5] << 16) |
              (buffer12[6] << 8) | buffer12[7]);
            if ((jng_width == 0) || (jng_height == 0))
              ThrowReaderException(CorruptImageError,"NegativeOrZeroImageSize");
            jng_color_type=buffer12[8];
            jng_image_sample_depth=buffer12[9];
            jng_image_compression_method=buffer12[10];
            jng_image_interlace_method=buffer12[11];

            buffer6->interlace=jng_image_interlace_method != 0 ? PNGInterlace :
              NoInterlace;

            jng_alpha_sample_depth=buffer12[12];
            jng_alpha_compression_method=buffer12[13];
            jng_alpha_filter_method=buffer12[14];
            jng_alpha_interlace_method=buffer12[15];

            if (logging != MagickFalse)
              {
                (void) LogMagickEvent(CoderEvent,GetMagickModule(),
                  "    jng_width:      %16lu,    jng_height:     %16lu\n"
                  "    jng_color_type: %16d,     jng_image_sample_depth: %3d\n"
                  "    jng_image_compression_method:%3d",
                  (unsigned long) jng_width, (unsigned long) jng_height,
                  jng_color_type, jng_image_sample_depth,
                  jng_image_compression_method);

                (void) LogMagickEvent(CoderEvent,GetMagickModule(),
                  "    jng_image_interlace_method:  %3d"
                  "    jng_alpha_sample_depth:      %3d",
                  jng_image_interlace_method,
                  jng_alpha_sample_depth);

                (void) LogMagickEvent(CoderEvent,GetMagickModule(),
                  "    jng_alpha_compression_method:%3d\n"
                  "    jng_alpha_filter_method:     %3d\n"
                  "    jng_alpha_interlace_method:  %3d",
                  jng_alpha_compression_method,
                  jng_alpha_filter_method,
                  jng_alpha_interlace_method);
              }
          }

        if (length != 0)
          buffer14=(unsigned char *) RelinquishMagickMemory(buffer14);

        continue;
      }


    if ((reading_idat == MagickFalse) && (read_JSEP == MagickFalse) &&
        ((memcmp(buffer13,mng_JDAT,4) == 0) || (memcmp(buffer13,mng_JdAA,4) == 0) ||
         (memcmp(buffer13,mng_IDAT,4) == 0) || (memcmp(buffer13,mng_JDAA,4) == 0)))
      {
        /*
           o create color_image
           o open color_blob, attached to color_image
           o if (color type has alpha)
               open alpha_blob, attached to alpha_image
        */

        if (logging != MagickFalse)
          (void) LogMagickEvent(CoderEvent,GetMagickModule(),
            "    Creating color_blob.");

        buffer9=(ImageInfo *)AcquireMagickMemory(sizeof(ImageInfo));

        if (buffer9 == (ImageInfo *) NULL)
          ThrowReaderException(ResourceLimitError,"MemoryAllocationFailed");

        GetImageInfo(buffer9);
        buffer5=AcquireImage(buffer9);

        if (buffer5 == (Image *) NULL)
          ThrowReaderException(ResourceLimitError,"MemoryAllocationFailed");

        (void) AcquireUniqueFilename(buffer5->filename);
        unique_filenames++;
        status=OpenBlob(buffer9,buffer5,WriteBinaryBlobMode,
          buffer3);

        if (status == MagickFalse)
          {
            buffer5=DestroyImage(buffer5);
            return(DestroyImageList(buffer6));
          }

        if ((buffer2->ping == MagickFalse) && (jng_color_type >= 12))
          {
            buffer8=(ImageInfo *)
              AcquireMagickMemory(sizeof(ImageInfo));

            if (buffer8 == (ImageInfo *) NULL)
              {
                buffer5=DestroyImage(buffer5);
                ThrowReaderException(ResourceLimitError,
                  "MemoryAllocationFailed");
              }

            GetImageInfo(buffer8);
            buffer4=AcquireImage(buffer8);

            if (buffer4 == (Image *) NULL)
              {
                buffer8=DestroyImageInfo(buffer8);
                buffer5=DestroyImage(buffer5);
                ThrowReaderException(ResourceLimitError,
                  "MemoryAllocationFailed");
              }

            if (logging != MagickFalse)
              (void) LogMagickEvent(CoderEvent,GetMagickModule(),
                "    Creating alpha_blob.");

            (void) AcquireUniqueFilename(buffer4->filename);
            unique_filenames++;
            status=OpenBlob(buffer8,buffer4,WriteBinaryBlobMode,
              buffer3);

            if (status == MagickFalse)
              {
                buffer4=DestroyImage(buffer4);
                buffer8=DestroyImageInfo(buffer8);
                buffer5=DestroyImage(buffer5);
                return(DestroyImageList(buffer6));
              }

            if (jng_alpha_compression_method == 0)
              {
                unsigned char
                  buffer15[18];

                if (logging != MagickFalse)
                  (void) LogMagickEvent(CoderEvent,GetMagickModule(),
                    "    Writing IHDR chunk to alpha_blob.");

                (void) WriteBlob(buffer4,8,(const unsigned char *)
                  "\211PNG\r\n\032\n");

                (void) WriteBlobMSBULong(buffer4,13L);
                PNGType(buffer15,mng_IHDR);
                LogPNGChunk(logging,mng_IHDR,13L);
                PNGLong(buffer15+4,jng_width);
                PNGLong(buffer15+8,jng_height);
                buffer15[12]=jng_alpha_sample_depth;
                buffer15[13]=0; /* color_type gray */
                buffer15[14]=0; /* compression method 0 */
                buffer15[15]=0; /* filter_method 0 */
                buffer15[16]=0; /* interlace_method 0 */
                (void) WriteBlob(buffer4,17,buffer15);
                (void) WriteBlobMSBULong(buffer4,crc32(0,buffer15,17));
              }
          }
        reading_idat=MagickTrue;
      }

    if (memcmp(buffer13,mng_JDAT,4) == 0)
      {
        /* Copy chunk to color_image->blob */

        if (logging != MagickFalse)
          (void) LogMagickEvent(CoderEvent,GetMagickModule(),
            "    Copying JDAT chunk data to color_blob.");

        if (length != 0)
          {
            (void) WriteBlob(buffer5,length,buffer14);
            buffer14=(unsigned char *) RelinquishMagickMemory(buffer14);
          }

        continue;
      }

    if (memcmp(buffer13,mng_IDAT,4) == 0)
      {
        png_byte
           buffer16[5];

        /* Copy IDAT header and chunk data to alpha_image->blob */

        if (buffer4 != NULL && buffer2->ping == MagickFalse)
          {
            if (logging != MagickFalse)
              (void) LogMagickEvent(CoderEvent,GetMagickModule(),
                "    Copying IDAT chunk data to alpha_blob.");

            (void) WriteBlobMSBULong(buffer4,(size_t) length);
            PNGType(buffer16,mng_IDAT);
            LogPNGChunk(logging,mng_IDAT,length);
            (void) WriteBlob(buffer4,4,buffer16);
            (void) WriteBlob(buffer4,length,buffer14);
            (void) WriteBlobMSBULong(buffer4,
              crc32(crc32(0,buffer16,4),buffer14,(uInt) length));
          }

        if (length != 0)
          buffer14=(unsigned char *) RelinquishMagickMemory(buffer14);

        continue;
      }

    if ((memcmp(buffer13,mng_JDAA,4) == 0) || (memcmp(buffer13,mng_JdAA,4) == 0))
      {
        /* Copy chunk data to alpha_image->blob */

        if (buffer4 != NULL && buffer2->ping == MagickFalse)
          {
            if (logging != MagickFalse)
              (void) LogMagickEvent(CoderEvent,GetMagickModule(),
                "    Copying JDAA chunk data to alpha_blob.");

            (void) WriteBlob(buffer4,length,buffer14);
          }

        if (length != 0)
          buffer14=(unsigned char *) RelinquishMagickMemory(buffer14);

        continue;
      }

    if (memcmp(buffer13,mng_JSEP,4) == 0)
      {
        read_JSEP=MagickTrue;

        if (length != 0)
          buffer14=(unsigned char *) RelinquishMagickMemory(buffer14);

        continue;
      }

    if (memcmp(buffer13,mng_bKGD,4) == 0)
      {
        if (length == 2)
          {
            buffer6->background_color.red=ScaleCharToQuantum(buffer12[1]);
            buffer6->background_color.green=buffer6->background_color.red;
            buffer6->background_color.blue=buffer6->background_color.red;
          }

        if (length == 6)
          {
            buffer6->background_color.red=ScaleCharToQuantum(buffer12[1]);
            buffer6->background_color.green=ScaleCharToQuantum(buffer12[3]);
            buffer6->background_color.blue=ScaleCharToQuantum(buffer12[5]);
          }

        buffer14=(unsigned char *) RelinquishMagickMemory(buffer14);
        continue;
      }

    if (memcmp(buffer13,mng_gAMA,4) == 0)
      {
        if (length == 4)
          buffer6->gamma=((float) mng_get_long(buffer12))*0.00001;

        buffer14=(unsigned char *) RelinquishMagickMemory(buffer14);
        continue;
      }

    if (memcmp(buffer13,mng_cHRM,4) == 0)
      {
        if (length == 32)
          {
            buffer6->chromaticity.white_point.x=0.00001*mng_get_long(buffer12);
            buffer6->chromaticity.white_point.y=0.00001*mng_get_long(&buffer12[4]);
            buffer6->chromaticity.red_primary.x=0.00001*mng_get_long(&buffer12[8]);
            buffer6->chromaticity.red_primary.y=0.00001*mng_get_long(&buffer12[12]);
            buffer6->chromaticity.green_primary.x=0.00001*mng_get_long(&buffer12[16]);
            buffer6->chromaticity.green_primary.y=0.00001*mng_get_long(&buffer12[20]);
            buffer6->chromaticity.blue_primary.x=0.00001*mng_get_long(&buffer12[24]);
            buffer6->chromaticity.blue_primary.y=0.00001*mng_get_long(&buffer12[28]);
          }

        buffer14=(unsigned char *) RelinquishMagickMemory(buffer14);
        continue;
      }

    if (memcmp(buffer13,mng_sRGB,4) == 0)
      {
        if (length == 1)
          {
            buffer6->rendering_intent=
              Magick_RenderingIntent_from_PNG_RenderingIntent(buffer12[0]);
            buffer6->gamma=1.000f/2.200f;
            buffer6->chromaticity.red_primary.x=0.6400f;
            buffer6->chromaticity.red_primary.y=0.3300f;
            buffer6->chromaticity.green_primary.x=0.3000f;
            buffer6->chromaticity.green_primary.y=0.6000f;
            buffer6->chromaticity.blue_primary.x=0.1500f;
            buffer6->chromaticity.blue_primary.y=0.0600f;
            buffer6->chromaticity.white_point.x=0.3127f;
            buffer6->chromaticity.white_point.y=0.3290f;
          }

        buffer14=(unsigned char *) RelinquishMagickMemory(buffer14);
        continue;
      }

    if (memcmp(buffer13,mng_oFFs,4) == 0)
      {
        if (length > 8)
          {
            buffer6->page.x=(ssize_t) mng_get_long(buffer12);
            buffer6->page.y=(ssize_t) mng_get_long(&buffer12[4]);

            if ((int) buffer12[8] != 0)
              {
                buffer6->page.x/=10000;
                buffer6->page.y/=10000;
              }
          }

        if (length != 0)
          buffer14=(unsigned char *) RelinquishMagickMemory(buffer14);

        continue;
      }

    if (memcmp(buffer13,mng_pHYs,4) == 0)
      {
        if (length > 8)
          {
            buffer6->x_resolution=(double) mng_get_long(buffer12);
            buffer6->y_resolution=(double) mng_get_long(&buffer12[4]);
            if ((int) buffer12[8] == PNG_RESOLUTION_METER)
              {
                buffer6->units=PixelsPerCentimeterResolution;
                buffer6->x_resolution=buffer6->x_resolution/100.0f;
                buffer6->y_resolution=buffer6->y_resolution/100.0f;
              }
          }

        buffer14=(unsigned char *) RelinquishMagickMemory(buffer14);
        continue;
      }

#if 0
    if (memcmp(buffer13,mng_iCCP,4) == 0)
      {
        /* To do: */
        if (length != 0)
          buffer14=(unsigned char *) RelinquishMagickMemory(buffer14);

        continue;
      }
#endif

    if (length != 0)
      buffer14=(unsigned char *) RelinquishMagickMemory(buffer14);

    if (memcmp(buffer13,mng_IEND,4))
      continue;

    break;
  }


  /* IEND found */

  /*
    Finish up reading image data:

       o read main image from color_blob.

       o close color_blob.

       o if (color_type has alpha)
            if alpha_encoding is PNG
               read secondary image from alpha_blob via ReadPNG
            if alpha_encoding is JPEG
               read secondary image from alpha_blob via ReadJPEG

       o close alpha_blob.

       o copy intensity of secondary image into
         opacity samples of main image.

       o destroy the secondary image.
  */

  if (buffer9 == (ImageInfo *) NULL)
    {
      assert(buffer5 == (Image *) NULL);
      assert(buffer4 == (Image *) NULL);
      return(DestroyImageList(buffer6));
    }

  if (buffer5 == (Image *) NULL)
    {
      assert(buffer4 == (Image *) NULL);
      return(DestroyImageList(buffer6));
    }

  (void) SeekBlob(buffer5,0,SEEK_SET);

  if (logging != MagickFalse)
    (void) LogMagickEvent(CoderEvent,GetMagickModule(),
      "    Reading jng_image from color_blob.");

  assert(buffer9 != (ImageInfo *) NULL);
  (void) FormatLocaleString(buffer9->filename,MaxTextExtent,"%s",
    buffer5->filename);

  buffer9->ping=MagickFalse;   /* To do: avoid this */
  buffer7=ReadImage(buffer9,buffer3);

  (void) RelinquishUniqueFileResource(buffer5->filename);
  unique_filenames--;
  buffer5=DestroyImage(buffer5);
  buffer9=DestroyImageInfo(buffer9);

  if (buffer7 == (Image *) NULL)
    return(DestroyImageList(buffer6));

  if (logging != MagickFalse)
    (void) LogMagickEvent(CoderEvent,GetMagickModule(),
      "    Copying jng_image pixels to main image.");
  buffer6->columns=jng_width;
  buffer6->rows=jng_height;
  length=buffer6->columns*sizeof(PixelPacket);

  status=SetImageExtent(buffer6,buffer6->columns,buffer6->rows);
  if (status == MagickFalse)
    {
      InheritException(buffer3,&buffer6->exception);
      return(DestroyImageList(buffer6));
    }

  for (y=0; y < (ssize_t) buffer6->rows; y++)
  {
    buffer10=GetVirtualPixels(buffer7,0,y,buffer6->columns,1,&buffer6->exception);
    buffer11=GetAuthenticPixels(buffer6,0,y,buffer6->columns,1,buffer3);
    (void) CopyMagickMemory(buffer11,buffer10,length);

    if (SyncAuthenticPixels(buffer6,buffer3) == MagickFalse)
      break;
  }

  buffer7=DestroyImage(buffer7);

  if (buffer2->ping == MagickFalse)
    {
     if (jng_color_type >= 12)
       {
         if (jng_alpha_compression_method == 0)
           {
             png_byte
               buffer17[5];
             (void) WriteBlobMSBULong(buffer4,0x00000000L);
             PNGType(buffer17,mng_IEND);
             LogPNGChunk(logging,mng_IEND,0L);
             (void) WriteBlob(buffer4,4,buffer17);
             (void) WriteBlobMSBULong(buffer4,crc32(0,buffer17,4));
           }

         (void) SeekBlob(buffer4,0,SEEK_SET);

         if (logging != MagickFalse)
           (void) LogMagickEvent(CoderEvent,GetMagickModule(),
             "    Reading opacity from alpha_blob.");

         (void) FormatLocaleString(buffer8->filename,MaxTextExtent,
           "%s",buffer4->filename);

         buffer7=ReadImage(buffer8,buffer3);

         if (buffer7 != (Image *) NULL)
           for (y=0; y < (ssize_t) buffer6->rows; y++)
           {
             buffer10=GetVirtualPixels(buffer7,0,y,buffer6->columns,1,
                &buffer6->exception);
             buffer11=GetAuthenticPixels(buffer6,0,y,buffer6->columns,1,buffer3);

             if (buffer6->matte != MagickFalse)
               for (x=(ssize_t) buffer6->columns; x != 0; x--,buffer11++,buffer10++)
                  SetPixelOpacity(buffer11,QuantumRange-
                      GetPixelRed(buffer10));

             else
               for (x=(ssize_t) buffer6->columns; x != 0; x--,buffer11++,buffer10++)
               {
                  SetPixelAlpha(buffer11,GetPixelRed(buffer10));
                  if (GetPixelOpacity(buffer11) != OpaqueOpacity)
                    buffer6->matte=MagickTrue;
               }

             if (SyncAuthenticPixels(buffer6,buffer3) == MagickFalse)
               break;
           }
         (void) RelinquishUniqueFileResource(buffer4->filename);
         unique_filenames--;
         buffer4=DestroyImage(buffer4);
         buffer8=DestroyImageInfo(buffer8);
         if (buffer7 != (Image *) NULL)
           buffer7=DestroyImage(buffer7);
       }
    }

  /* Read the JNG image.  */

  if (buffer1->mng_type == 0)
    {
      buffer1->mng_width=jng_width;
      buffer1->mng_height=jng_height;
    }

  if (buffer6->page.width == 0 && buffer6->page.height == 0)
    {
      buffer6->page.width=jng_width;
      buffer6->page.height=jng_height;
    }

  if (buffer6->page.x == 0 && buffer6->page.y == 0)
    {
      buffer6->page.x=buffer1->x_off[buffer1->object_id];
      buffer6->page.y=buffer1->y_off[buffer1->object_id];
    }

  else
    {
      buffer6->page.y=buffer1->y_off[buffer1->object_id];
    }

  buffer1->image_found++;
  status=SetImageProgress(buffer6,LoadImagesTag,2*TellBlob(buffer6),
    2*GetBlobSize(buffer6));

  if (status == MagickFalse)
    return(DestroyImageList(buffer6));

  if (logging != MagickFalse)
    (void) LogMagickEvent(CoderEvent,GetMagickModule(),
      "  exit ReadOneJNGImage(); unique_filenames=%d",unique_filenames);

  return(buffer6);
}
