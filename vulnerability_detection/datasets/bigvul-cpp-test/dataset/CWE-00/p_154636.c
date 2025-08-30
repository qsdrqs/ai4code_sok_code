error::Error GLES2DecoderPassthroughImpl::DoDisableVertexAttribArray(
    GLuint index) {
  api()->glDisableVertexAttribArrayFn(index);
  return error::kNoError;
}
