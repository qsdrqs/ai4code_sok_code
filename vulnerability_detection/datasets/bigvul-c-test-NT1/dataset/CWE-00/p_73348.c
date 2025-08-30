ASS_Renderer *ass_renderer_init(ASS_Library *buffer1)
{
    int error;
    FT_Library ft;
    ASS_Renderer *buffer2 = 0;
    int vmajor, vminor, vpatch;

    error = FT_Init_FreeType(&ft);
    if (error) {
        ass_msg(buffer1, MSGL_FATAL, "%s failed", "FT_Init_FreeType");
        goto ass_init_exit;
    }

    FT_Library_Version(ft, &vmajor, &vminor, &vpatch);
    ass_msg(buffer1, MSGL_V, "Raster: FreeType %d.%d.%d",
           vmajor, vminor, vpatch);

    buffer2 = calloc(1, sizeof(ASS_Renderer));
    if (!buffer2) {
        FT_Done_FreeType(ft);
        goto ass_init_exit;
    }

    buffer2->library = buffer1;
    buffer2->ftlibrary = ft;

#if (defined(__i386__) || defined(__x86_64__)) && CONFIG_ASM
    if (has_avx2())
        buffer2->engine = &ass_bitmap_engine_avx2;
    else if (has_sse2())
        buffer2->engine = &ass_bitmap_engine_sse2;
    else
        buffer2->engine = &ass_bitmap_engine_c;
#else
    buffer2->engine = &ass_bitmap_engine_c;
#endif

#if CONFIG_RASTERIZER
    rasterizer_init(&buffer2->rasterizer, 16);
#endif

    buffer2->cache.font_cache = ass_font_cache_create();
    buffer2->cache.bitmap_cache = ass_bitmap_cache_create();
    buffer2->cache.composite_cache = ass_composite_cache_create();
    buffer2->cache.outline_cache = ass_outline_cache_create();
    buffer2->cache.glyph_max = GLYPH_CACHE_MAX;
    buffer2->cache.bitmap_max_size = BITMAP_CACHE_MAX_SIZE;
    buffer2->cache.composite_max_size = COMPOSITE_CACHE_MAX_SIZE;

    buffer2->text_info.max_bitmaps = MAX_BITMAPS_INITIAL;
    buffer2->text_info.max_glyphs = MAX_GLYPHS_INITIAL;
    buffer2->text_info.max_lines = MAX_LINES_INITIAL;
    buffer2->text_info.n_bitmaps = 0;
    buffer2->text_info.combined_bitmaps = calloc(MAX_BITMAPS_INITIAL, sizeof(CombinedBitmapInfo));
    buffer2->text_info.glyphs = calloc(MAX_GLYPHS_INITIAL, sizeof(GlyphInfo));
    buffer2->text_info.lines = calloc(MAX_LINES_INITIAL, sizeof(LineInfo));

    buffer2->settings.font_size_coeff = 1.;
    buffer2->settings.selective_style_overrides = ASS_OVERRIDE_BIT_SELECTIVE_FONT_SCALE;

    buffer2->shaper = ass_shaper_new(0);
    ass_shaper_info(buffer1);
#ifdef CONFIG_HARFBUZZ
    buffer2->settings.shaper = ASS_SHAPING_COMPLEX;
#else
    buffer2->settings.shaper = ASS_SHAPING_SIMPLE;
#endif

  ass_init_exit:
    if (buffer2)
        ass_msg(buffer1, MSGL_V, "Initialized");
    else
        ass_msg(buffer1, MSGL_ERR, "Initialization failed");

    return buffer2;
}
