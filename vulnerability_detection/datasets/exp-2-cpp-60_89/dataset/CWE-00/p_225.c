void MOADNSParser::init(const char *packet, unsigned int len)
{
  if(len < sizeof(dnsheader))
    throw MOADNSException("Packet shorter than minimal header");
  
  memcpy(&d_header, packet, sizeof(dnsheader));

  if(d_header.opcode != Opcode::Query && d_header.opcode != Opcode::Notify && d_header.opcode != Opcode::Update)
    throw MOADNSException("Can't parse non-query packet with opcode="+ lexical_cast<string>(d_header.opcode));

  d_header.qdcount=ntohs(d_header.qdcount);
  d_header.ancount=ntohs(d_header.ancount);
  d_header.nscount=ntohs(d_header.nscount);
  d_header.arcount=ntohs(d_header.arcount);
  
  uint16_t contentlen=len-sizeof(dnsheader);

  d_content.resize(contentlen);
  copy(packet+sizeof(dnsheader), packet+len, d_content.begin());
  
  unsigned int n=0;

  PacketReader pr(d_content);
  bool validPacket=false;
  try {
    d_qtype = d_qclass = 0; // sometimes replies come in with no question, don't present garbage then

    for(n=0;n < d_header.qdcount; ++n) {
      d_qname=pr.getLabel();
      d_qtype=pr.get16BitInt();
      d_qclass=pr.get16BitInt();
    }

    struct dnsrecordheader ah;
    vector<unsigned char> record;
    validPacket=true;
    for(n=0;n < (unsigned int)(d_header.ancount + d_header.nscount + d_header.arcount); ++n) {
      DNSRecord dr;
      
      if(n < d_header.ancount)
        dr.d_place=DNSRecord::Answer;
      else if(n < d_header.ancount + d_header.nscount)
        dr.d_place=DNSRecord::Nameserver;
      else 
        dr.d_place=DNSRecord::Additional;
      
      unsigned int recordStartPos=pr.d_pos;

      string label=pr.getLabel();
      
      pr.getDnsrecordheader(ah);
      dr.d_ttl=ah.d_ttl;
      dr.d_type=ah.d_type;
      dr.d_class=ah.d_class;
      
      dr.d_label=label;
      dr.d_clen=ah.d_clen;

      dr.d_content=boost::shared_ptr<DNSRecordContent>(DNSRecordContent::mastermake(dr, pr, d_header.opcode));
      d_answers.push_back(make_pair(dr, pr.d_pos));

      if(dr.d_type == QType::TSIG && dr.d_class == 0xff) 
        d_tsigPos = recordStartPos + sizeof(struct dnsheader);
    }

#if 0    
    if(pr.d_pos!=contentlen) {
      throw MOADNSException("Packet ("+d_qname+"|#"+lexical_cast<string>(d_qtype)+") has trailing garbage ("+ lexical_cast<string>(pr.d_pos) + " < " + 
                            lexical_cast<string>(contentlen) + ")");
    }
#endif 
  }
  catch(std::out_of_range &re) {
    if(validPacket && d_header.tc) { // don't sweat it over truncated packets, but do adjust an, ns and arcount
      if(n < d_header.ancount) {
        d_header.ancount=n; d_header.nscount = d_header.arcount = 0;
      }
      else if(n < d_header.ancount + d_header.nscount) {
        d_header.nscount = n - d_header.ancount; d_header.arcount=0;
      }
      else {
        d_header.arcount = n - d_header.ancount - d_header.nscount;
      }
    }
    else {
      throw MOADNSException("Error parsing packet of "+lexical_cast<string>(len)+" bytes (rd="+
                            lexical_cast<string>(d_header.rd)+
                            "), out of bounds: "+string(re.what()));
    }
  }
}