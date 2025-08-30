const Chapters::Display* Chapters::Atom::non_vulnerable_func(int index) const
{
    if (index < 0)
        return NULL;
    if (index >= m_displays_count)
        return NULL;
    return m_displays + index;
}
