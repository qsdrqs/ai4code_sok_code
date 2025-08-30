ASS_Renderer *ass_renderer_init(ASS_Library *library)
{
    int error;
    FT_Library ft;
    ASS_Renderer *priv = 0;
    int vmajor, vminor, vpatch;

    error = FT_Init_FreeType(&ft);
    if (error) {
        ass_msg(library, MSGL_FATAL, "%s failed", "FT_Init_FreeType");
        goto ass_init_exit;
    }

    FT_Library_Version(ft, &vmajor, &vminor, &vpatch);
    ass_msg(library, MSGL_V, "Raster: FreeType %d.%d.%d",
           vmajor, vminor, vpatch);

    priv = calloc(1, sizeof(ASS_Renderer));
    if (!priv) {
        FT_Done_FreeType(ft);
        goto ass_init_exit;
    }

    priv->library = library;
    priv->ftlibrary = ft;

#if (defined(__i386__) || defined(__x86_64__)) && CONFIG_ASM
    if (has_avx2())
        priv->engine = &ass_bitmap_engine_avx2;
    else if (has_sse2())
        priv->engine = &ass_bitmap_engine_sse2;
    else
        priv->engine = &ass_bitmap_engine_c;
#else
    priv->engine = &ass_bitmap_engine_c;
#endif

#if CONFIG_RASTERIZER
    rasterizer_init(&priv->rasterizer, 16);
#endif

    priv->cache.font_cache = ass_font_cache_create();
    priv->cache.bitmap_cache = ass_bitmap_cache_create();
    priv->cache.composite_cache = ass_composite_cache_create();
    priv->cache.outline_cache = ass_outline_cache_create();
    priv->cache.glyph_max = GLYPH_CACHE_MAX;
    priv->cache.bitmap_max_size = BITMAP_CACHE_MAX_SIZE;
    priv->cache.composite_max_size = COMPOSITE_CACHE_MAX_SIZE;

    priv->text_info.max_bitmaps = MAX_BITMAPS_INITIAL;
    priv->text_info.max_glyphs = MAX_GLYPHS_INITIAL;
    priv->text_info.max_lines = MAX_LINES_INITIAL;
    priv->text_info.n_bitmaps = 0;
    priv->text_info.combined_bitmaps = calloc(MAX_BITMAPS_INITIAL, sizeof(CombinedBitmapInfo));
    priv->text_info.glyphs = calloc(MAX_GLYPHS_INITIAL, sizeof(GlyphInfo));
    priv->text_info.lines = calloc(MAX_LINES_INITIAL, sizeof(LineInfo));

    priv->settings.font_size_coeff = 1.;
    priv->settings.selective_style_overrides = ASS_OVERRIDE_BIT_SELECTIVE_FONT_SCALE;

    priv->shaper = ass_shaper_new(0);
    ass_shaper_info(library);
#ifdef CONFIG_HARFBUZZ
    priv->settings.shaper = ASS_SHAPING_COMPLEX;
#else
    priv->settings.shaper = ASS_SHAPING_SIMPLE;
#endif

  ass_init_exit:
    if (priv)
        ass_msg(library, MSGL_V, "Initialized");
    else
        ass_msg(library, MSGL_ERR, "Initialization failed");

    return priv;
}
