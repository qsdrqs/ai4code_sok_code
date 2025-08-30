ULONG HierarchicalBitmapRequester::BufferedLines(const struct RectangleRequest *rr) const
{
#if ACCUSOFT_CODE
  ULONG maxlines = m_ulPixelHeight;
  
  for(UBYTE i = rr->rr_usFirstComponent;i <= rr->rr_usLastComponent;i++) {
    class Component *comp = m_pFrame->ComponentOf(i);
    UBYTE suby            = comp->SubYOf();
    ULONG lines;
    // Since the user here asks for complete(!) lines and the highpass comes last
    // in the codestream, ask the highpass about how many lines are buffered.
    // These lines are counted in subsampled lines.
    lines = m_pLargestScale->BufferedLines(i);
    if (lines >= m_pulHeight[i]) {
      lines = m_ulPixelHeight;
    } else if (suby > 1 && lines > 0) {
      lines = ((lines - 1) * suby) & (-8); // one additional subsampled line, actually,;
    } else {
      lines = (lines * suby) & (-8); 
    }
    if (lines < maxlines)
      maxlines = lines;
  }

  return maxlines;
#else
  NOREF(rr);
  return 0;
#endif
}