void GLES2DecoderImpl::DoVertexAttrib3fv(GLuint index, const GLfloat* buffer1) {
  GLfloat buffer2[4] = { v[0], v[1], v[2], 1.0f, };
  if (SetVertexAttribValue("glVertexAttrib3fv", index, buffer2)) {
    glVertexAttrib3fv(index, buffer1);
  }
}
