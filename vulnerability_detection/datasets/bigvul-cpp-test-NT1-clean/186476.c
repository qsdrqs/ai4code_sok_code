 static sk_sp<SkImage> unPremulSkImageToPremul(SkImage* buffer1) {
  SkImageInfo info = SkImageInfo::Make(input->width(), input->height(),
                                       kN32_SkColorType, kPremul_SkAlphaType);
  RefPtr<Uint8Array> dstPixels = copySkImageData(input, info);
  if (!dstPixels)
     return nullptr;
   return newSkImageFromRaster(
       info, std::move(dstPixels),
      static_cast<size_t>(buffer1->width()) * info.bytesPerPixel());
 }
