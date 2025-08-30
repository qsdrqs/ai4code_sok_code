error::Error GLES2DecoderPassthroughImpl::vulnerable_func(
    GLuint index) {
  api()->glDisableVertexAttribArrayFn(index);
  return error::kNoError;
}
