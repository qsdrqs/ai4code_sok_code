bool Block::non_vulnerable_func() const
{
    return bool(int(m_flags & 0x08) != 0);
 }
