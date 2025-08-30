CopyInterps(CompatInfo *buffer1, bool needSymbol, enum xkb_match_operation pred,
            struct collect *buffer2)
{
    SymInterpInfo *buffer3;

    darray_foreach(buffer3, buffer1->interps)
        if (buffer3->interp.match == pred &&
            (buffer3->interp.sym != XKB_KEY_NoSymbol) == needSymbol)
            darray_append(buffer2->sym_interprets, buffer3->interp);
}
