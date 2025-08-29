void serialize(ILittleEndianOutput out1) {();}
void addAll(BlockList<T> src) {if (src.size == 0) {return;} int srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) {addAll(src.directory[srcDirIdx], BLOCK_SIZE);} if (src.tailBlkIdx != 0) {addAll(src.tailBlock, src.tailBlkIdx);} }
void writeByte(byte b){if(outerInstance.upto==outerInstance.blockSize){if(outerInstance.currentBlock!=null){outerInstance.blocks.add(outerInstance.currentBlock);outerInstance.blockEnd.add(outerInstance.upto);}outerInstance.currentBlock=new byte[outerInstance.blockSize];outerInstance.upto=0;}outerInstance.currentBlock[outerInstance.upto++]=b;}
public ObjectId getObjectId() { return objectId; }
public DeleteDomainEntryResult deleteDomainEntry(DeleteDomainEntryRequest request) {request = beforeClientExecution(request);return executeDeleteDomainEntry(request);}
long ramBytesUsed() { return fst == null ? 0 : fst.getSizeInBytes(); }
public String getFullMessage() {byte[] raw = buffer;int msgB = RawParseUtils.tagMessage(raw,0);if (msgB < 0){return "";}Charset enc = RawParseUtils.parseEncoding(raw);return RawParseUtils.decode(enc, raw, msgB, raw.length);}
public POIFSFileSystem() {HeaderBlock headerBlock = new HeaderBlock();setPropertyTable(new PropertyTable());setDocuments(new ArrayList<>());setRoot(null);}
public void init(int address) {slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT];assert slice != null;upto = address & ByteBlockPool.BYTE_BLOCK_MASK;offset0 = address;assert upto < ByteBlockPool.BYTE_BLOCK_SIZE;}
public SubmoduleAddCommand setPath(String path) { this.path = path; return this; }
public ListIngestionsResult listIngestions(ListIngestionsRequest request) {final InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(ListIngestionsRequestMarshaller.getInstance());options.setResponseUnmarshaller(ListIngestionsResponseUnmarshaller.getInstance());return invoke(request, options);}
public QueryParserTokenManager(ICharStream stream, int lexState) {this();switchTo();}
public GetShardIteratorResult getShardIterator(GetShardIteratorRequest request) {request = beforeClientExecution(request);return executeGetShardIterator(request);}
void modifyStrategyRequest() : ( " " , " " , " " , " " , " " ) { method(); }
boolean ready() {synchronized(lock){if(in==null)throw new IOException(" ");try{return bytes.hasRemaining()||in.available()>0;}catch(IOException e){return false;}}}
EscherOptRecord getOptRecord() {}
int read(byte[] buffer,int offset,int length){synchronized(this){if(buffer==null){throw new NullPointerException(" ");}java.util.Arrays.checkOffsetAndCount(buffer.length,offset,length);if(length==0){return 0;}int copylen=count-pos<length?count-pos:length;for(int i=0;i<copylen;i++){buffer[offset+i]=(byte)this.buffer[pos+i];}pos+=copylen;return copylen;}}
OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) { this.sentenceOp = sentenceOp; }
void print(String str) {write(str != null ? str : Sharpen.StringHelper.getValueOf(null));}
public NotImplementedFunctionException(String functionName, Throwable cause){super(functionName,cause);this.functionName=functionName;}
V next() { return nextEntry.value; }
void readBytes(byte[] b,int offset,int len,boolean useBuffer){int available=bufferLength-bufferPosition;if(len<=available){if(len>0)System.arraycopy(buffer,bufferPosition,b,offset,len);bufferPosition+=len;}else{if(available>0){System.arraycopy(buffer,bufferPosition,b,offset,available);offset+=available;len-=available;bufferPosition+=available;}if(useBuffer&&len<bufferSize){refill();if(bufferLength<len){System.arraycopy(buffer,bufferPosition,b,offset,bufferLength);throw new EOFException(""+len);}else{System.arraycopy(buffer,bufferPosition,b,offset,len);bufferPosition+=len;}}else{long after=bufferStart+bufferPosition+len;if(after>length)throw new EOFException(" "+len);readInternal(b,offset,len);bufferStart=after;bufferPosition=0;bufferLength=0;}}}
public TagQueueResult tagQueue(TagQueueRequest request) {request = beforeClientExecution(request); return executeTagQueue(request);}
void remove() { throw new UnsupportedOperationException(); }
public ModifyCacheSubnetGroupResult modifyCacheSubnetGroup() {return modifyCacheSubnetGroup(new ModifyCacheSubnetGroupRequest());}
void setParams(String params){setParams();String culture="";StringTokenizer st=new StringTokenizer(params," ");if(st.hasMoreTokens())culture=st.nextToken();if(st.hasMoreTokens())culture+=" "+st.nextToken();if(st.hasMoreTokens())st.nextToken();}
public DeleteDocumentationVersionResult deleteDocumentationVersion(DeleteDocumentationVersionRequest request) {request = beforeClientExecution(request);return executeDeleteDocumentationVersion(request);}
boolean equals(Object obj){if(!(obj instanceof FacetLabel)){return false;}FacetLabel other=(FacetLabel)obj;if(Length!=other.Length){return false;}for(int i=Length-1;i>=0;i--){if(!Components[i].equals(other.Components[i])){return false;}}return true;}
public GetInstanceAccessDetailsResult getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) {request = beforeClientExecution(request);return executeGetInstanceAccessDetails(request);}
HSSFPolygon createPolygon() { HSSFPolygon shape = new HSSFPolygon(null, null); shape.Parent; shape.Anchor anchor; shapes.add(); onCreate(); return shape; }
String getSheetName() {return getBoundSheetRec().getSheetname();}
public GetDashboardResult getDashboard(GetDashboardRequest request) {request = beforeClientExecution(request); return executeGetDashboard(request);}
public AssociateSigninDelegateGroupsWithAccountResponse associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(AssociateSigninDelegateGroupsWithAccountRequestMarshaller.getInstance());options.setResponseUnmarshaller(AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.getInstance());return invoke(request,options);}
void addMultipleBlanks(MulBlankRecord mbr){for(int j=0;j<mbr.numColumns;j++){BlankRecord br=new BlankRecord();br.column=j+mbr.firstColumn;br.row=mbr.row;br.xfIndex=mbr.getXFAt();insertCell(br);}}
public String quote(String string) { java.lang.StringBuilder sb = new java.lang.StringBuilder(); sb.append("\\\""); int apos = 0; int k; while ((k = string.indexOf("\\\"", apos)) >= 0) { sb.append(string.substring(apos, k + 1)).append("\\\\\\\""); apos = k + 1; } return sb.append(string.substring(apos)).append("\\\"").toString(); }
public java.nio.ByteBuffer putInt(int value){throw new java.nio.ReadOnlyBufferException();}
public ArrayPtg(Object[][] values2d){int nColumns=values2d[0].length;int nRows=values2d.length;_nColumns=(short)nColumns;_nRows=(short)nRows;Object[] vv=new Object[_nColumns*_nRows];for(int r=0;r<nRows;r++){Object[] rowData=values2d[r];for(int c=0;c<nColumns;c++){vv[getValueIndex(r,c)]=rowData[c];}}_arrayValues=vv;_reserved0Int=0;_reserved1Short=0;_reserved2Byte=0;}
public GetIceServerConfigResult getIceServerConfig(GetIceServerConfigRequest request) {request = beforeClientExecution(request);return executeGetIceServerConfig(request);}
public String toString() {StringBuilder sb = new StringBuilder(); sb.append(getClass().getSimpleName()).append(" "); sb.append(getValueAsString()); sb.append(" "); return sb.toString();}
public String toString() { return " " + _parentQuery + " "; }
void incRef() {refCount.incrementAndGet();}
public UpdateConfigurationSetSendingEnabledResult updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(UpdateConfigurationSetSendingEnabledRequestMarshaller.getInstance());options.setResponseUnmarshaller(UpdateConfigurationSetSendingEnabledResponseUnmarshaller.getInstance());return invoke(request,options);}
int getNextXBATChainOffset() { return getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE; }
void multiplyByPowerOfTen(int pow10){TenPower tp=TenPower.getInstance(Math.abs());if(){mulShift(tp._divisor,tp._divisorShift);}else{mulShift(tp._multiplicand,tp._multiplierShift);} }
public String toString() {StringBuilder builder=new StringBuilder();int length=length;builder.append(File.separatorChar);for(int i=0;i<length;i++){builder.append(getComponent());if(i<(length-1)){builder.append(File.separatorChar);}}return builder.toString();}
void withFetcher() { Fetcher fetcher; fetcher.setRoleName(); }
void setProgressMonitor() { progressMonitor pm; }
void reset() { if () { ptr = 0; if (!eof) { parseEntry(); } } }
public E previous() { if (iterator.previousIndex() >= start) { return iterator.previous(); } throw new java.util.NoSuchElementException(); }
public String getNewPrefix() {return newPrefix;}
int indexOfValue(int value){for(int i=0;i<mSize;i++){if(mValues[i]==value){return i;}}return -1;}
private List<CharsRef> uniqueStems(char[] word,int length){List<CharsRef> stems=stem(word,length);if(stems.size()<2){return stems;}CharArraySet terms=new CharArraySet(8,dictionary.ignoreCase);List<CharsRef> deduped=new ArrayList<>();for(CharsRef s:stems){if(!terms.contains(s)){deduped.add(s);terms.add(s);}}return deduped;}
public GetGatewayResponsesResult getGatewayResponses(GetGatewayResponsesRequest request) {request = beforeClientExecution(request);return executeGetGatewayResponses(request);}
void setPosition(long position) { currentBlockIndex = (int) (position >> outerInstance.blockBits); currentBlock = outerInstance.blocks[currentBlockIndex]; currentBlockUpto = (int) (position & outerInstance.blockMask); }
public long skip(long n) {int s = (int)Math.max(0, Math.min(available(), n));ptr += s;return s;}
BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { this._bootstrapActionConfig = bootstrapActionConfig; }
public void serialize(LittleEndianOutput out){out.writeShort();out.writeShort();out.writeShort();out.writeShort();field_6_author.length();out.writeByte(field_5_hasMultibyte?0x01:0x00);if(field_5_hasMultibyte){StringUtil.putUnicodeLE( , );}else{StringUtil.putCompressedUnicode( , );}if(field_7_padding!=null){out.writeByte(Integer.parseInt( , 10));}}
int lastIndexOf() { return lastIndexOf( , ); }
boolean add() { return addLastImpl(); }
void unsetSection(String section,String subsection){ConfigSnapshot src,res;do{src=state.get();res=unsetSection(src,section,subsection);}while(!state.compareAndSet(src,res));}
String getTagName() { return tagName; }
void addSubRecord(int index, SubRecord subRecord) { subrecords.add(index, subRecord); }
boolean remove() { synchronized (mutex) { return c.remove(); } }
public TokenStream create() { return new DoubleMetaphoneFilter( , , ); }
public long getLength() { return inCoreLength(); }
void setValue() { value newValue; }
public Pair(ContentSource oldSource, ContentSource newSource) {this.oldSource = oldSource; this.newSource = newSource;}
int get(int i) {if (i < 0 || i >= entries.length) {throw new IndexOutOfBoundsException();}return entries[i];}
public CreateRepoRequest() { super(" ", " ", " ", " ", " "); setUriPattern(" "); setMethod(MethodType.PUT); }
boolean isDeltaBaseAsOffset() {}
public void remove() { if (expectedModCount == list.modCount) { if (lastLink != null) { java.util.LinkedList.Link<ET> next = lastLink.next; java.util.LinkedList.Link<ET> previous = lastLink.previous; next.previous = previous; previous.next = next; if (lastLink == link) { pos--; } link = previous; lastLink = null; expectedModCount++; list._size--; list.modCount++; } else { throw new IllegalStateException(); } } else { throw new java.util.ConcurrentModificationException(); } }
public MergeShardsResponse mergeShards(MergeShardsRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(MergeShardsRequestMarshaller.getInstance());options.setResponseUnmarshaller(MergeShardsResponseUnmarshaller.getInstance());return invoke(request, options);}
public AllocateHostedConnectionResponse allocateHostedConnection(AllocateHostedConnectionRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(AllocateHostedConnectionRequestMarshaller.getInstance());options.setResponseUnmarshaller(AllocateHostedConnectionResponseUnmarshaller.getInstance());return invoke(request,options);}
public int getBeginIndex() {return start;}
WeightedTerm getTerms(Query query) {return getTerms( , );}
public ByteBuffer compact() {throw new ReadOnlyBufferException();}
void decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = byte0 >>> 2; int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >>> 4); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >>> 6); values[valuesOffset++] = byte2 & 63; } }
public String getHumanishName() { if (getPath() == null || "".equals(getPath())) throw new IllegalArgumentException(); String s = getPath(); String[] elements; if ("".equals(host) || LOCAL_FILE.matcher(s).matches()) elements = s.split("[/\\\\]"); else elements = s.split("/"); if (elements.length == 0) throw new IllegalArgumentException(); String result = elements[elements.length - 1]; if (Constants.DOT_GIT.equals(result)) result = elements[elements.length - 2]; else { if (result.endsWith(Constants.DOT_GIT_EXT)) result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); } return result; }
public DescribeNotebookInstanceLifecycleConfigResult describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request){request = beforeClientExecution(request);return executeDescribeNotebookInstanceLifecycleConfig(request);}
