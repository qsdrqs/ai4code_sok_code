void GLES2DecoderImpl::DoCopyTexImage2D(
  GLenum target,
  GLint level,
  GLenum internal_format,
  GLint x,
  GLint y,
  GLsizei width,
  GLsizei height,
  GLint border) {
  TextureManager::TextureInfo* info = GetTextureInfoForTarget(target);
  if (!info) {
    SetGLError(GL_INVALID_OPERATION,
               "glCopyTexImage2D: unknown texture for target");
    return;
  }
  if (!texture_manager()->ValidForTarget(target, level, width, height, 1) ||
      border != 0) {
    SetGLError(GL_INVALID_VALUE, "glCopyTexImage2D: dimensions out of range");
    return;
  }

  CopyRealGLErrorsToWrapper();
  glCopyTexImage2D(target, level, internal_format, x, y, width, height, border);
  GLenum error = glGetError();
  if (error == GL_NO_ERROR) {
    texture_manager()->SetLevelInfo(
        info,  target, level, internal_format, width, height, 1, border,
        internal_format, GL_UNSIGNED_BYTE);
  }
}
