MagickExport Image *DisposeImages(const Image *images,ExceptionInfo *exception)
{
  Image
    *dispose_image,
    *dispose_images;

  RectangleInfo
    bounds;

  register Image
    *image,
    *next;

  /*
    Run the image through the animation sequence
  */
  assert(images != (Image *) NULL);
  assert(images->signature == MagickCoreSignature);
  if (images->debug != MagickFalse)
    (void) LogMagickEvent(TraceEvent,GetMagickModule(),"%s",images->filename);
  assert(exception != (ExceptionInfo *) NULL);
  assert(exception->signature == MagickCoreSignature);
  image=GetFirstImageInList(images);
  dispose_image=CloneImage(image,image->page.width,image->page.height,
    MagickTrue,exception);
  if (dispose_image == (Image *) NULL)
    return((Image *) NULL);
  dispose_image->page=image->page;
  dispose_image->page.x=0;
  dispose_image->page.y=0;
  dispose_image->dispose=NoneDispose;
  dispose_image->background_color.alpha=(MagickRealType) TransparentAlpha;
  (void) SetImageBackgroundColor(dispose_image,exception);
  dispose_images=NewImageList();
  for (next=image; image != (Image *) NULL; image=GetNextImageInList(image))
  {
    Image
      *current_image;

    /*
      Overlay this frame's image over the previous disposal image.
    */
    current_image=CloneImage(dispose_image,0,0,MagickTrue,exception);
    if (current_image == (Image *) NULL)
      {
        dispose_images=DestroyImageList(dispose_images);
        dispose_image=DestroyImage(dispose_image);
        return((Image *) NULL);
      }
    (void) CompositeImage(current_image,next,
      next->alpha_trait != UndefinedPixelTrait ? OverCompositeOp : CopyCompositeOp,
      MagickTrue,next->page.x,next->page.y,exception);
    /*
      Handle Background dispose: image is displayed for the delay period.
    */
    if (next->dispose == BackgroundDispose)
      {
        bounds=next->page;
        bounds.width=next->columns;
        bounds.height=next->rows;
        if (bounds.x < 0)
          {
            bounds.width+=bounds.x;
            bounds.x=0;
          }
        if ((ssize_t) (bounds.x+bounds.width) > (ssize_t) current_image->columns)
          bounds.width=current_image->columns-bounds.x;
        if (bounds.y < 0)
          {
            bounds.height+=bounds.y;
            bounds.y=0;
          }
        if ((ssize_t) (bounds.y+bounds.height) > (ssize_t) current_image->rows)
          bounds.height=current_image->rows-bounds.y;
        ClearBounds(current_image,&bounds,exception);
      }
    /*
      Select the appropriate previous/disposed image.
    */
    if (next->dispose == PreviousDispose)
      current_image=DestroyImage(current_image);
    else
      {
        dispose_image=DestroyImage(dispose_image);
        dispose_image=current_image;
        current_image=(Image *) NULL;
      }
    /*
      Save the dispose image just calculated for return.
    */
    {
      Image
        *dispose;

      dispose=CloneImage(dispose_image,0,0,MagickTrue,exception);
      if (dispose == (Image *) NULL)
        {
          dispose_images=DestroyImageList(dispose_images);
          dispose_image=DestroyImage(dispose_image);
          return((Image *) NULL);
        }
      (void) CloneImageProfiles(dispose,next);
      (void) CloneImageProperties(dispose,next);
      (void) CloneImageArtifacts(dispose,next);
      dispose->page.x=0;
      dispose->page.y=0;
      dispose->dispose=next->dispose;
      AppendImageToList(&dispose_images,dispose);
    }
  }
  dispose_image=DestroyImage(dispose_image);
  return(GetFirstImageInList(dispose_images));
}