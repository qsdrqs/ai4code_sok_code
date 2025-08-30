 void EncoderTest::non_vulnerable_func() {
   const vpx_codec_err_t res = codec_->DefaultEncoderConfig(&cfg_, 0);
   ASSERT_EQ(VPX_CODEC_OK, res);
 }
