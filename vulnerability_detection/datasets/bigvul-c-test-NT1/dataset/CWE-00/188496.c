int main(int argc, char **buffer1) {
 int frame_cnt = 0;
 FILE *buffer2 = NULL;
 vpx_codec_ctx_t codec;
 VpxVideoReader *buffer3 = NULL;
 const VpxVideoInfo *buffer4 = NULL;
 const VpxInterface *buffer5 = NULL;

  exec_name = buffer1[0];

 if (argc != 3)
    die("Invalid number of arguments.");

  buffer3 = vpx_video_reader_open(buffer1[1]);
 if (!buffer3)
    die("Failed to open %s for reading.", buffer1[1]);

 if (!(buffer2 = fopen(buffer1[2], "wb")))
    die("Failed to open %s for writing.", buffer1[2]);

  buffer4 = vpx_video_reader_get_info(buffer3);

  buffer5 = get_vpx_decoder_by_fourcc(buffer4->codec_fourcc);

   if (!buffer5)
     die("Unknown input codec.");
 
  printf("Using %s\n", vpx_codec_iface_name(buffer5->interface()));
 
  if (vpx_codec_dec_init(&codec, buffer5->interface(), NULL, 0))
     die_codec(&codec, "Failed to initialize decoder");
 
   while (vpx_video_reader_read_frame(buffer3)) {
 vpx_codec_iter_t iter = NULL;
 vpx_image_t *buffer6 = NULL;
 size_t frame_size = 0;
 const unsigned char *buffer7 = vpx_video_reader_get_frame(reader,
 &frame_size);
 if (vpx_codec_decode(&codec, buffer7, (unsigned int)frame_size, NULL, 0))
      die_codec(&codec, "Failed to decode frame");

 while ((buffer6 = vpx_codec_get_frame(&codec, &iter)) != NULL) {
 unsigned char buffer8[16];

      get_image_md5(buffer6, buffer8);
      print_md5(buffer2, buffer8);
      fprintf(buffer2, "  img-%dx%d-%04d.i420\n",
              buffer6->d_w, buffer6->d_h, ++frame_cnt);
 }
 }

  printf("Processed %d frames.\n", frame_cnt);
 if (vpx_codec_destroy(&codec))
    die_codec(&codec, "Failed to destroy codec.");

  vpx_video_reader_close(buffer3);

  fclose(buffer2);
 return EXIT_SUCCESS;
}
