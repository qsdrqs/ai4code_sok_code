CopyInterps(CompatInfo *info, bool needSymbol, enum xkb_match_operation pred,
            struct collect *collect)
{
    SymInterpInfo *si;

    darray_foreach(si, info->interps)
        if (si->interp.match == pred &&
            (si->interp.sym != XKB_KEY_NoSymbol) == needSymbol)
            darray_append(collect->sym_interprets, si->interp);
}
