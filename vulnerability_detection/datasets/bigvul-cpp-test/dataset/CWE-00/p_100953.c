void TextureMapperGL::bindSurface(BitmapTexture *surfacePointer)
{
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
