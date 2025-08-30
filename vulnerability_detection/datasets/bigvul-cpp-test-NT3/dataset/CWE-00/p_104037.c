bool GLES2DecoderImpl::vulnerable_func(GLuint client_id) {
  const TextureManager::TextureInfo* info = GetTextureInfo(client_id);
  return info && info->IsValid();
}
