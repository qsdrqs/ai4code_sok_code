Variant getImageSize(const req::ptr<File>& stream, VRefParam imageinfo) {
  int itype = 0;
  struct gfxinfo *result = nullptr;
  auto imageInfoPtr = imageinfo.getVariantOrNull();
  if (imageInfoPtr) {
    *imageInfoPtr = Array::Create();
  }

  itype = php_getimagetype(stream);
  switch( itype) {
  case IMAGE_FILETYPE_GIF:
    result = php_handle_gif(stream);
    break;
  case IMAGE_FILETYPE_JPEG:
    {
      Array infoArr;
      if (imageInfoPtr) {
        infoArr = Array::Create();
      }
      result = php_handle_jpeg(stream, infoArr);
      if (imageInfoPtr) {
        *imageInfoPtr = infoArr;
      }
    }
    break;
  case IMAGE_FILETYPE_PNG:
    result = php_handle_png(stream);
    break;
  case IMAGE_FILETYPE_SWF:
    result = php_handle_swf(stream);
    break;
  case IMAGE_FILETYPE_SWC:
    result = php_handle_swc(stream);
    break;
  case IMAGE_FILETYPE_PSD:
    result = php_handle_psd(stream);
    break;
  case IMAGE_FILETYPE_BMP:
    result = php_handle_bmp(stream);
    break;
  case IMAGE_FILETYPE_TIFF_II:
    result = php_handle_tiff(stream, 0);
    break;
  case IMAGE_FILETYPE_TIFF_MM:
    result = php_handle_tiff(stream, 1);
    break;
  case IMAGE_FILETYPE_JPC:
    result = php_handle_jpc(stream);
    break;
  case IMAGE_FILETYPE_JP2:
    result = php_handle_jp2(stream);
    break;
  case IMAGE_FILETYPE_IFF:
    result = php_handle_iff(stream);
    break;
  case IMAGE_FILETYPE_WBMP:
    result = php_handle_wbmp(stream);
    break;
  case IMAGE_FILETYPE_XBM:
    result = php_handle_xbm(stream);
    break;
  case IMAGE_FILETYPE_ICO:
    result = php_handle_ico(stream);
    break;
  default:
  case IMAGE_FILETYPE_UNKNOWN:
    break;
  }

  if (result) {
    DArrayInit ret(7);
    ret.set(0, (int64_t)result->width);
    ret.set(1, (int64_t)result->height);
    ret.set(2, itype);
    char *temp;
    php_vspprintf(&temp, 0, "width=\"%d\" height=\"%d\"",
                  result->width, result->height);
    ret.set(3, String(temp, CopyString));
    if (temp) IM_FREE(temp);
    if (result->bits != 0) {
      ret.set(s_bits, (int64_t)result->bits);
    }
    if (result->channels != 0) {
      ret.set(s_channels, (int64_t)result->channels);
    }
    ret.set(s_mime, (char*)php_image_type_to_mime_type(itype));
    IM_FREE(result);
    return ret.toVariant();
  } else {
    return false;
  }
}