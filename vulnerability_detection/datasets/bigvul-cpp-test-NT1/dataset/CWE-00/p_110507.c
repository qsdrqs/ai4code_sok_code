void GLES2DecoderImpl::DoTexParameteriv(
  GLenum target, GLenum pname, const GLint* buffer1) {
  TextureManager::TextureInfo* buffer2 = GetTextureInfoForTarget(target);
  if (!buffer2) {
    SetGLError(GL_INVALID_VALUE, "glTexParameteriv", "unknown texture");
    return;
  }

  if (!texture_manager()->SetParameter(buffer2, pname, *buffer1)) {
    SetGLErrorInvalidEnum("glTexParameteriv", pname, "pname");
    return;
  }
  glTexParameteriv(target, pname, buffer1);
}
