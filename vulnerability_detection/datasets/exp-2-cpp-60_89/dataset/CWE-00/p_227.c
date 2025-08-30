UnicodeBlockCanvas::GlyphPick UnicodeBlockCanvas::FindBestGlyph(
    const rgba_t *top, const rgba_t *bottom) const {
    if (N == 1) {
        if (*top == *bottom ||
            (is_transparent(*top) && is_transparent(*bottom))) {
            return {*top, *bottom, kBackground};
        }
        if (use_upper_half_block_) return {*top, *bottom, kUpperBlock};
        return {*bottom, *top, kLowerBlock};
    }
    // N == 2
    const LinearColor tl(top[0]);
    const LinearColor tr(top[1]);
    const LinearColor bl(bottom[0]);
    const LinearColor br(bottom[1]);

    // If we're all transparent at the top and/or bottom, the choices
    // we can make for foreground and background are limited.
    // Even though this adds branches, special casing is worthile.
    if (is_transparent(top[0]) && is_transparent(top[1]) &&
        is_transparent(bottom[0]) && is_transparent(bottom[1])) {
        return {bottom[0], top[0], kBackground};
    }
    if (is_transparent(top[0]) && is_transparent(top[1])) {
        return {linear_average({bl, br}).repack(), top[0], kLowerBlock};
    }
    if (is_transparent(bottom[0]) && is_transparent(bottom[1])) {
        return {linear_average({tl, tr}).repack(), bottom[0], kUpperBlock};
    }

    struct Result {
        LinearColor fg, bg;
        BlockChoice block = kBackground;
    } best;
    float best_distance = 1e12;
    for (int b = 0; b < 8; ++b) {
        float d;  // Sum of color distance for each sub-block to average color
        LinearColor fg, bg;
        // We can't fix all the blocks that the user tries to work around
        // with TIMG_USE_UPPER_BLOCK. But fix the half-blocks at least.
        const BlockChoice block =
            (BlockChoice)(b < 7 ? b
                                : (use_upper_half_block_ ? kUpperBlock
                                                         : kLowerBlock));
        // clang-format off
        switch (block) {
        case kBackground:      d = avd(&bg, {tl, tr, bl, br}); fg = bg;   break;
        case kTopLeft:         d = avd(&bg, {tr, bl, br});     fg = tl;   break;
        case kTopRight:        d = avd(&bg, {tl, bl, br});     fg = tr;   break;
        case kBotLeft:         d = avd(&bg, {tl, tr, br});     fg = bl;   break;
        case kBotRight:        d = avd(&bg, {tl, tr, bl});     fg = br;   break;
        case kLeftBar:         d = avd(&bg, {tr, br})+avd(&fg, {tl, bl}); break;
        case kTopLeftBotRight: d = avd(&bg, {tr, bl})+avd(&fg, {tl, br}); break;
        case kLowerBlock:      d = avd(&bg, {tl, tr})+avd(&fg, {bl, br}); break;
        case kUpperBlock:      d = avd(&bg, {bl, br})+avd(&fg, {tl, tr}); break;
        }
        // clang-format on
        if (d < best_distance) {
            best = {fg, bg, block};
            if (d < 1) break;  // Essentially zero.
            best_distance = d;
        }
    }
    return {best.fg.repack(), best.bg.repack(), best.block};
}