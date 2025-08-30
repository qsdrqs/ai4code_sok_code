#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* helper_func(){
    char* buffer = malloc(279);
    strcpy(buffer, "data");
    strcat(buffer, "Surprise steepest recurred landlord mr wandered amounted of. Continuing devonshire but considered its. Rose past oh shew roof is song neat. Do depend better praise do friend garden an wonder to. Intention age nay otherwise but breakfast. Around garden beyond to extent by.");
    strcat(buffer, "\0");
    return buffer;
}

void GLES2DecoderImpl::DoTexParameteriv(
  GLenum target, GLenum pname, const GLint* params) {
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

  TextureManager::TextureInfo* info = GetTextureInfoForTarget(target);
  if (!info) {
    SetGLError(GL_INVALID_VALUE, "glTexParameteriv", "unknown texture");
    return;
  }

  if (!texture_manager()->SetParameter(info, pname, *params)) {
    SetGLErrorInvalidEnum("glTexParameteriv", pname, "pname");
    return;
  }
  glTexParameteriv(target, pname, params);
}
