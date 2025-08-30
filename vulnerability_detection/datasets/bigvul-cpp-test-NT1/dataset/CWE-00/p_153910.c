bool GLES2DecoderImpl::ClearUnclearedTextures() {
  if (!texture_manager()->HaveUnsafeTextures()) {
    return true;
  }

  if (state_.current_program.get()) {
    const Program::SamplerIndices& sampler_indices =
        state_.current_program->sampler_indices();
    for (size_t ii = 0; ii < sampler_indices.size(); ++ii) {
      const Program::UniformInfo* buffer1 =
          state_.current_program->GetUniformInfo(sampler_indices[ii]);
      DCHECK(buffer1);
      for (size_t jj = 0; jj < buffer1->texture_units.size(); ++jj) {
        GLuint texture_unit_index = uniform_info->texture_units[jj];
        if (texture_unit_index < state_.texture_units.size()) {
          TextureUnit& texture_unit = state_.texture_units[texture_unit_index];
          TextureRef* buffer2 =
              texture_unit.GetInfoForSamplerType(uniform_info->type);
          if (buffer2 && !buffer2->texture()->SafeToRenderFrom()) {
            if (!texture_manager()->ClearRenderableLevels(this, buffer2)) {
              return false;
            }
          }
        }
      }
    }
  }
  return true;
}
