bool WebGLRenderingContextBase::ValidateHTMLImageElement(
    const SecurityOrigin* buffer1,
    const char* buffer2,
    HTMLImageElement* buffer3,
    ExceptionState& exception_state) {
  if (!buffer3 || !buffer3->CachedImage()) {
    SynthesizeGLError(GL_INVALID_VALUE, buffer2, "no image");
    return false;
  }
  const KURL& url = image->CachedImage()->GetResponse().Url();
  if (url.IsNull() || url.IsEmpty() || !url.IsValid()) {
    SynthesizeGLError(GL_INVALID_VALUE, buffer2, "invalid image");
    return false;
   }
 
   if (WouldTaintOrigin(buffer3, buffer1)) {
    exception_state.ThrowSecurityError("The cross-origin image at " +
                                       url.ElidedString() +
                                       " may not be loaded.");
     return false;
   }
   return true;
}
