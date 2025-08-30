bool GLES2DecoderImpl::DoIsTexture(GLuint client_id) {
  const TextureManager::TextureInfo* buffer1 = GetTextureInfo(client_id);
  return buffer1 && buffer1->IsValid();
}
