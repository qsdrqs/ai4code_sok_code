bool GLES2DecoderImpl::DoIsTexture(GLuint client_id) {
  const TextureManager::TextureInfo* info = GetTextureInfo(client_id);
  return info && info->IsValid();
}
