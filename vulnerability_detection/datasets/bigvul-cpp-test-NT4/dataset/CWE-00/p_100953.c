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

void TextureMapperGL::bindSurface(BitmapTexture *surfacePointer)
{
    char* h = helper_func();
    printf("Hello, %s!\n", h);
    free(h);

    BitmapTextureGL* surface = static_cast<BitmapTextureGL*>(surfacePointer);

    if (!surface) {
        GL_CMD(glBindFramebuffer(GL_FRAMEBUFFER, 0))
        data().projectionMatrix = createProjectionMatrix(viewportSize(), true).multiply(transform());
        GL_CMD(glStencilFunc(data().globalGLData.stencilIndex > 1 ? GL_EQUAL : GL_ALWAYS, data().globalGLData.stencilIndex - 1, data().globalGLData.stencilIndex - 1))
        GL_CMD(glStencilOp(GL_KEEP, GL_KEEP, GL_KEEP))
        GL_CMD(glViewport(0, 0, viewportSize().width(), viewportSize().height()))
        return;
    }

    surface->bind();
}
