void PacketReader::getLabelFromContent(const vector<uint8_t>& content, uint16_t& frompos, string& ret, int recurs) 
{
  if(recurs > 100) // the forward reference-check below should make this test 100% obsolete
    throw MOADNSException("Loop");

  int pos = frompos;
  // it is tempting to call reserve on ret, but it turns out it creates a malloc/free storm in the loop
  for(;;) {
    unsigned char labellen=content.at(frompos++);

    if(!labellen) {
      if(ret.empty())
              ret.append(1,'.');
      break;
    }
    else if((labellen & 0xc0) == 0xc0) {
      uint16_t offset=256*(labellen & ~0xc0) + (unsigned int)content.at(frompos++) - sizeof(dnsheader);
      //        cout<<"This is an offset, need to go to: "<<offset<<endl;

      if(offset >= pos)
        throw MOADNSException("forward reference during label decompression");
      return getLabelFromContent(content, offset, ret, ++recurs);
    }
    else if(labellen > 63) 
      throw MOADNSException("Overly long label during label decompression ("+lexical_cast<string>((unsigned int)labellen)+")");
    else {
      // XXX FIXME THIS MIGHT BE VERY SLOW!

      for(string::size_type n = 0 ; n < labellen; ++n, frompos++) {
        if(content.at(frompos)=='.' || content.at(frompos)=='\\') {
          ret.append(1, '\\');
          ret.append(1, content[frompos]);
        }
        else if(content.at(frompos)==' ') {
          ret+="\\032";
        }
        else 
          ret.append(1, content[frompos]);
      }
      ret.append(1,'.');
    }
    if (ret.length() > 1024)
      throw MOADNSException("Total name too long");
  }
}