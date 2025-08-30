bitset_set_range(ScanEnv *env, BitSetRef bs, int from, int to)
{
  int i;
  for (i = from; i <= to && i < SINGLE_BYTE_SIZE; i++) {
    BITSET_SET_BIT_CHKDUP(bs, i);
  }
}
