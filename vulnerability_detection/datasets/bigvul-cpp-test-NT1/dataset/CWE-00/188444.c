long Chapters::Atom::ParseDisplay(
    IMkvReader* buffer1,
    long long pos,
    long long size)
{
    if (!ExpandDisplaysArray())
        return -1;
    Display& d = m_displays[m_displays_count++];
    d.Init();
    return d.Parse(buffer1, pos, size);
}
