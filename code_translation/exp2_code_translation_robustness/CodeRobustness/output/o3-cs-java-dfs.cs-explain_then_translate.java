public void serialize(ILittleEndianOutput out1){out1.writeShort();}
public void addAll(BlockList<T> src){if(src.size()==0)return;int srcDirIdx=0;for(;srcDirIdx<src.tailDirIdx;++srcDirIdx)addAll(src.directory[srcDirIdx],BLOCK_SIZE);if(src.tailBlkIdx!=0)addAll(src.tailBlock,src.tailBlkIdx);}
void writeByte(byte b) { if (upto == blockSize) { if (currentBlock != null) { blocks.add(currentBlock); blockEnd.add(upto); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
ObjectId getObjectId() { return objectId; }
public DeleteDomainEntryResponse deleteDomainEntry(DeleteDomainEntryRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DeleteDomainEntryRequestMarshaller.getInstance());options.setResponseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.getInstance());return invoke(request,options);}
public long ramBytesUsed() { return fst == null ? 0 : fst.getSizeInBytes(); }
String getFullMessage(byte[] raw){int msgB=RawParseUtils.tagMessage(raw,0);if(msgB<0)return "";java.nio.charset.Charset enc=RawParseUtils.parseEncoding(raw);return RawParseUtils.decode(raw,msgB,raw.length,enc);}
public POIFSFileSystem() { HeaderBlock headerBlock = new HeaderBlock(); PropertyTable _property_table = new PropertyTable(); ArrayList _documents = new ArrayList(); _root = null; }
public void init(int address){int slice=address&ByteBlockPool.BYTE_BLOCK_MASK;int upto=address>>ByteBlockPool.BYTE_BLOCK_SHIFT;assert upto<pool.buffers.length;byte[] buffer=pool.buffers[upto];assert buffer!=null;int offset0=slice;}
public SubmoduleAddCommand SetPath(string path) {     this.path = path;     return this; }
public ListIngestionsResponse listIngestions(ListIngestionsRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(ListIngestionsRequestMarshaller.getInstance());options.setResponseUnmarshaller(ListIngestionsResponseUnmarshaller.getInstance());return invoke(request,options);}
public QueryParserTokenManager(ICharStream stream, int lexState){this(stream);SwitchTo(lexState);}
public GetShardIteratorResponse getShardIterator(GetShardIteratorRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetShardIteratorRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetShardIteratorResponseUnmarshaller.getInstance());return invoke(request,options);}
public MethodType getMethod(){return MethodType.POST;}
boolean ready(){synchronized(lock){if(in==null){throw new IOException(" ");}try{return bytes.hasRemaining()||in.available()>0;}catch(IOException e){return false;}}}
public EscherOptRecord getOptRecord() { return _optRecord; }
int read(byte[] buffer, int offset, int length) { synchronized(this) { if (buffer == null) throw new NullPointerException("buffer"); java.util.Arrays.checkOffsetAndCount(buffer.length, offset, length); if (length == 0) return 0; int copylen = (count - pos) < length ? (count - pos) : length; for (int i = 0; i < copylen; i++) { buffer[offset + i] = (byte) this.buffer[pos + i]; } pos += copylen; return copylen; } }
public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) { this.sentenceOp = sentenceOp; }
void print(String str){System.out.print(str!=null?str:String.valueOf((Object)null));}
public class NotImplementedFunctionException extends UnsupportedOperationException { public NotImplementedFunctionException(String functionName, Throwable cause) { super(functionName, cause); } }
V next() { return nextEntry(value); }
void readBytes(byte[] b,int offset,int len,boolean useBuffer){int available=bufferLength-bufferPosition;if(len<=available){System.arraycopy(buffer,bufferPosition,b,offset,len);bufferPosition+=len;return;}if(available>0){System.arraycopy(buffer,bufferPosition,b,offset,available);offset+=available;len-=available;bufferPosition+=available;}if(useBuffer&&len<bufferSize){refill();if(bufferStart+bufferPosition+len>Length){throw new java.io.EOFException("after "+(bufferStart+bufferPosition)+" Length "+Length);}System.arraycopy(buffer,0,b,offset,len);bufferPosition=len;return;}readInternal(b,offset,len);}
return invoke(request, new InvokeOptions().setRequestMarshaller(TagQueueRequestMarshaller.getInstance()).setResponseUnmarshaller(TagQueueResponseUnmarshaller.getInstance()));
void remove() { throw new UnsupportedOperationException(); }
public ModifyCacheSubnetGroupResponse modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(ModifyCacheSubnetGroupRequestMarshaller.getInstance());options.setResponseUnmarshaller(ModifyCacheSubnetGroupResponseUnmarshaller.getInstance());return invoke(request,options);}
void setParams(String... culture){StringTokenizer st=new StringTokenizer(String.join(" ",culture)," ");while(st.hasMoreTokens()){String current=st.nextToken();if("ignore".equals(current))continue;if(st.hasMoreTokens()){current+=" "+st.nextToken();}}}
return invoke(request, new InvokeOptions().withRequestMarshaller(DeleteDocumentationVersionRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteDocumentationVersionResponseUnmarshaller.getInstance()), DeleteDocumentationVersionResponse.class);
@Override public boolean equals(Object obj){ if(!(obj instanceof FacetLabel)) return false; FacetLabel other=(FacetLabel)obj; if(length!=other.length) return false; for(int i=length-1;i>=0;i--){ if(!components[i].equals(other.components[i])) return false; } return true; }
public GetInstanceAccessDetailsResponse getInstanceAccessDetails(GetInstanceAccessDetailsRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetInstanceAccessDetailsRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetInstanceAccessDetailsResponseUnmarshaller.getInstance());return invoke(request,options);}
public HSSFPolygon createPolygon(HSSFChildAnchor anchor){HSSFPolygon shape=new HSSFPolygon(this,anchor);shapes.add(shape);onCreate(shape);return shape;}
public String getSheetName(int sheetIndex) { return getBoundSheetRec(sheetIndex).Sheetname; }
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetDashboardRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetDashboardResponseUnmarshaller.getInstance()); return invoke(request, options);
return invoke(AssociateSigninDelegateGroupsWithAccountResponse.class, request, new InvokeOptions().withRequestMarshaller(AssociateSigninDelegateGroupsWithAccountRequestMarshaller.getInstance()).withResponseUnmarshaller(AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.getInstance()));
public void addMultipleBlanks(MulBlankRecord mbr){for(int j=0;j<mbr.getNumColumns();j++){BlankRecord br=new BlankRecord();br.setRow(mbr.getRow());br.setColumn((short)(mbr.getFirstColumn()+j));br.setXFIndex(mbr.getXFAt(j));insertCell(br);}}
public static String quote(String str){StringBuilder sb=new StringBuilder();int apos=0,k;while((k=str.indexOf('\\',apos))>=0){sb.append(str.substring(apos,k+2));sb.append("\\\\\\\\");apos=k+2;}sb.append(str.substring(apos));return sb.toString();}
public void putInt(int value){throw new java.nio.ReadOnlyBufferException();}
public ArrayPtg(Object[][] values2d){_reserved0Int=0;_reserved1Short=0;_reserved2Byte=0;int nRows=values2d.length;int nColumns=values2d[0].length;_nRows=(short)nRows;_nColumns=(short)nColumns;Object[] vv=new Object[_nRows*_nColumns];for(int r=0;r<nRows;++r){for(int c=0;c<nColumns;++c){vv[getValueIndex(r,c)]=values2d[r][c];}}_arrayValues=vv;}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetIceServerConfigRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetIceServerConfigResponseUnmarshaller.getInstance()); return invoke(request, options);
@Override public String toString(){ StringBuilder sb=new StringBuilder(); sb.append(getClass().getSimpleName()).append(" ").append(getValueAsString()).append(" "); return sb.toString(); }
return field.toString() + " " + _parentQuery + " ";
void incRef() { refCount.incrementAndGet(); }
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(UpdateConfigurationSetSendingEnabledRequestMarshaller.getInstance()); options.setResponseUnmarshaller(UpdateConfigurationSetSendingEnabledResponseUnmarshaller.getInstance()); return invoke(request, options);
int getNextXBATChainOffset() { return (int)(getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE); }
void multiplyByPowerOfTen(int pow10){TenPower.tp=GetInstance.TenPower(Math.abs(pow10));if(pow10<0){mulShift(_divisor.tp,_divisorShift.tp);}else{mulShift(_multiplicand.tp,_multiplierShift.tp);}}
public String toString() { StringBuilder builder = new StringBuilder(); int length = Length; builder.append(java.io.File.separatorChar); for (int i = 0; i < length; ++i) { builder.append(getComponent(i)); if (i < length - 1) { builder.append(java.io.File.separatorChar); } } return builder.toString(); }
void withFetcher(ECSMetadataServiceCredentialsFetcher fetcher){fetcher.setRoleName();}
void setProgressMonitor(ProgressMonitor pm){progressMonitor=pm;}
void reset() { if (!first()) { ptr = 0; if (!eof()) { parseEntry(); } } }
E previous() { if (iterator.previousIndex() >= start) { return iterator.previous(); } throw new java.util.NoSuchElementException(); }
String getNewPrefix(){return newPrefix;}
int indexOfValue(int value){for(int i=0;i<mSize;++i){if(mValues[i]==value)return i;}return -1;}
public List<CharsRef> uniqueStems(char[] word,int length){List<CharsRef> stems=stem(word,length);if(stems.size()<2)return stems;List<CharsRef> deduped=new ArrayList<>();CharArraySet terms=new CharArraySet(8,true);for(CharsRef s:stems)if(!terms.contains(s)){terms.add(s);deduped.add(s);}return deduped;}
public GetGatewayResponsesResponse getGatewayResponses(GetGatewayResponsesRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetGatewayResponsesRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetGatewayResponsesResponseUnmarshaller.getInstance());return invoke(request,options);}
void setPosition(long position){currentBlockIndex=(int)(position>>outerInstance.blockBits);currentBlock=outerInstance.blocks[currentBlockIndex];currentBlockUpto=(int)(position&outerInstance.blockMask);}
long skip(long n){int s=(int)Math.min(available(),n);ptr+=s;return s;}
private BootstrapActionDetail bootstrapActionConfig; public BootstrapActionDetail getBootstrapActionConfig() { return this.bootstrapActionConfig; } public void setBootstrapActionConfig(BootstrapActionDetail bootstrapActionConfig) { this.bootstrapActionConfig = bootstrapActionConfig; }
public void serialize(ILittleEndianOutput out1){out1.writeShort(field_1_row);out1.writeShort(field_2_col);out1.writeShort(field_3_flags);out1.writeShort(field_4_shapeId);out1.writeShort(field_6_author.length());out1.writeByte(field_5_hasMultibyte?0x01:0x00);if(field_5_hasMultibyte){StringUtil.putUnicodeLE(field_6_author,out1);}else{StringUtil.putCompressedUnicode(field_6_author,out1);}if(field_7_padding!=null){for(byte b:field_7_padding){out1.writeByte(b);}}}
public static int lastIndexOf(String string) { return string.lastIndexOf(','); }
boolean add(E object){return addLastImpl(object);}
void unsetSection(String section,String subsection){ConfigSnapshot src,res;do{src=state.get();res=src.unsetSection(section,subsection);}while(!state.compareAndSet(src,res));}
String getTagName() { return tagName; }
void addSubRecord(int index, SubRecord element) { subrecords.add(index, element); }
public boolean remove(Object object){ synchronized(mutex){ return c.remove(object); } }
public TokenStream create(TokenStream input){ return new DoubleMetaphoneFilter(input,maxCodeLength,inject); }
long Length() { return InCoreLength(); }
void setValue(boolean newValue){value=newValue;}
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
int get(int i){if(i<=count){throw Sharpen.Extensions.CreateIndexOutOfRangeException();}return entries[i];}
new CreateRepoRequest(MethodType.PUT, " ", " ", " ", " ", " ");
boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public void remove() { if (list.modCount == expectedModCount) { if (lastLink != null) { Link<ET> next = lastLink.next; Link<ET> previous = lastLink.previous; if (lastLink == link) { pos--; link = next; } previous.next = next; next.previous = previous; lastLink = null; expectedModCount++; list._size--; list.modCount++; } else { throw new IllegalStateException(); } } else { throw new ConcurrentModificationException(); } }
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(MergeShardsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(MergeShardsResponseUnmarshaller.getInstance()); return invoke(request, options);
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(AllocateHostedConnectionRequestMarshaller.getInstance()); options.setResponseUnmarshaller(AllocateHostedConnectionResponseUnmarshaller.getInstance()); return invoke(request, options);
int getBeginIndex() { return start; }
public WeightedTerm[] getTerms(Query query){return getTerms(query);}
java.nio.ByteBuffer.compact(); throw new java.nio.ReadOnlyBufferException();
static void decode(byte[] blocks,int blocksOffset,int[] values,int valuesOffset,int iterations){for(int i=0;i<iterations;i++){int byte0=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=byte0>>>2;int byte1=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=((byte0&0x03)<<4)|(byte1>>>4);int byte2=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=((byte1&0x0F)<<2)|(byte2>>>6);values[valuesOffset++]=byte2&0x3F;}}
public String getHumanishName(){String p=getPath();if(p==null||p.isEmpty())throw new IllegalArgumentException("Empty path");String[] segs;"file".equalsIgnoreCase(getScheme())?(segs=p.split("\\\\")).equals(null):segs=p.split("/");if("file".equalsIgnoreCase(getScheme())&&segs.length==1)segs=p.split("/");String r=segs[segs.length-1];if(r.endsWith(Constants.DOT_GIT_EXT))r=r.substring(0,r.length()-Constants.DOT_GIT_EXT.length());if(r.equals(Constants.DOT_GIT))r="dotGit";return r;}
public DescribeNotebookInstanceLifecycleConfigResponse describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance());return invoke(request,options);}
public String getAccessKeySecret(){return AccessSecret;}
public CreateVpnConnectionResponse createVpnConnection(CreateVpnConnectionRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(CreateVpnConnectionRequestMarshaller.getInstance());options.setResponseUnmarshaller(CreateVpnConnectionResponseUnmarshaller.getInstance());return invoke(request,options);}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeVoicesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeVoicesResponseUnmarshaller.getInstance()); return invoke(request, options);
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListMonitoringExecutionsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ListMonitoringExecutionsResponseUnmarshaller.getInstance()); return invoke(request, options);
public DescribeJobRequest(String jobId,String vaultName){this._jobId=jobId;this._vaultName=vaultName;}
public EscherRecord getEscherRecord(int index) { return escherRecords[index]; }
public GetApisResponse getApis(GetApisRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetApisRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetApisResponseUnmarshaller.getInstance());return invoke(request,options);}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteSmsChannelRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteSmsChannelResponseUnmarshaller.getInstance()); return invoke(request, options, DeleteSmsChannelResponse.class);
public TrackingRefUpdate getTrackingRefUpdate() { return trackingRefUpdate; }
void print(boolean b){print(Boolean.toString(b));}
IQueryNode getChild() { return getChildren().get(0); }
index = new NotIgnoredFilter(workdirTreeIndex);
public AreaRecord(RecordInputStream in1){ field_1_formatFlags = in1.readShort(); }
public ProtocolType Protocol { get; set; } = ProtocolType.HTTPS;
public DescribeTransitGatewayVpcAttachmentsResponse describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DescribeTransitGatewayVpcAttachmentsRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance());return invoke(request,options);}
InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance());options.setResponseUnmarshaller(PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance());return invoke(PutVoiceConnectorStreamingConfigurationResponse.class,request,options);
OrdRange getOrdRange(String dim){return prefixToOrdRange.get(dim);}
return String.format(Locale.getDefault(), "%s: '%s'", LexerNoViableAltException.class.getSimpleName(), Utils.escapeWhitespace(inputStream.toString(Interval.of(startIndex, inputStream.size() - 1)), false));
E peek() { return peekFirstImpl(); }
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(CreateWorkspacesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(CreateWorkspacesResponseUnmarshaller.getInstance()); return invoke(request, options);
public Object clone(){NumberFormatIndexRecord rec=new NumberFormatIndexRecord();rec.field_1_formatIndex=field_1_formatIndex;return rec;}
public DescribeRepositoriesResponse describeRepositories(DescribeRepositoriesRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeRepositoriesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeRepositoriesResponseUnmarshaller.getInstance()); return invoke(request, options); }
public SparseIntArray(int initialCapacity){initialCapacity=android.util.ArrayUtils.idealIntArraySize(initialCapacity);mKeys=new int[initialCapacity];mValues=new int[initialCapacity];mSize=0;}
public TokenStream create(TokenStream input) { return new HyphenatedWordsFilter(input); }
public CreateDistributionWithTagsResponse createDistributionWithTags(CreateDistributionWithTagsRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(CreateDistributionWithTagsRequestMarshaller.getInstance());options.setResponseUnmarshaller(CreateDistributionWithTagsResponseUnmarshaller.getInstance());return invoke(request,options);}
{ throw new UnsupportedOperationException(); new java.io.RandomAccessFile(new java.io.File(fileName), mode); }
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.getInstance()); return invoke(request, options, DeleteWorkspaceImageResponse.class);
String ToHex(int value) { return ToHex((long) value); }
return invoke(UpdateDistributionResponse.class, request, new InvokeOptions().withRequestMarshaller(UpdateDistributionRequestMarshaller.getInstance()).withResponseUnmarshaller(UpdateDistributionResponseUnmarshaller.getInstance()));
public HSSFColor getColor(short index){if(HSSFColor.AUTOMATIC.getIndex()==index){return HSSFColor.AUTOMATIC.getInstance();}byte[] b=palette.getColor(index);return b!=null?new CustomColor(b):null;}
public ValueEval evaluate(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(); }
public void serialize(ILittleEndianOutput out1){out1.writeShort(field_1_number_crn_records);out1.writeShort(field_2_sheet_table_index);}
return new DescribeDBEngineVersionsResponse(new DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest()));
class FormatRun { private short _character; private short _fontIndex; public FormatRun(short character, short fontIndex) { this._character = character; this._fontIndex = fontIndex; } }
public static byte[] toBigEndianUtf16Bytes(char[] chars,int offset,int length){byte[] result=new byte[length*2];int end=offset+length;int resultIndex=0;for(int i=offset;i<end;i++){char ch=chars[i];result[resultIndex++]=(byte)(ch>>8);result[resultIndex++]=(byte)ch;}return result;}
return invoke(request, new InvokeOptions().withRequestMarshaller(UploadArchiveRequestMarshaller.getInstance()).withResponseUnmarshaller(UploadArchiveResponseUnmarshaller.getInstance()), UploadArchiveResponse.class);
List<IToken> getHiddenTokensToLeft(int tokenIndex);
public boolean equals(Object obj){if(obj==null)return false;if(getClass()!=obj.getClass())return false;AutomatonQuery other=(AutomatonQuery)obj;if(!java.util.Objects.equals(m_compiled,other.m_compiled))return false;if(m_term==null){if(other.m_term!=null)return false;}else{if(!m_term.equals(other.m_term))return false;}return true;}
SpanQuery makeSpanClause(Map<SpanQuery,Float> weightBySpanQuery){List<SpanQuery> spanQueries=new ArrayList<>();for(Map.Entry<SpanQuery,Float> wsq:weightBySpanQuery.entrySet()){SpanQuery sq=wsq.getKey();sq.setBoost(wsq.getValue());spanQueries.add(sq);}return spanQueries.size()==1?spanQueries.get(0):new SpanOrQuery(spanQueries.toArray(new SpanQuery[0]));}
return new StashCreateCommand(StashCreate);
public FieldInfo getFieldInfo(String fieldName){FieldInfo ret=byName.get(fieldName);return ret;}
public DescribeEventSourceResponse describeEventSource(DescribeEventSourceRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DescribeEventSourceRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeEventSourceResponseUnmarshaller.getInstance());return invoke(request,options);}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetDocumentAnalysisRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetDocumentAnalysisResponseUnmarshaller.getInstance()); return invoke(request, options, GetDocumentAnalysisResponse.class);
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(CancelUpdateStackRequestMarshaller.getInstance()); options.setResponseUnmarshaller(CancelUpdateStackResponseUnmarshaller.getInstance()); return invoke(request, options);
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ModifyLoadBalancerAttributesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance()); return invoke(request, options);
public SetInstanceProtectionResponse setInstanceProtection(SetInstanceProtectionRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(SetInstanceProtectionRequestMarshaller.getInstance());options.setResponseUnmarshaller(SetInstanceProtectionResponseUnmarshaller.getInstance());return invoke(request,options);}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ModifyDBProxyRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ModifyDBProxyResponseUnmarshaller.getInstance()); return invoke(request, options);
void add(char[] output,int offset,int len,int endOffset,int posLength){if(outputs==null){outputs=new CharsRef[ArrayUtil.oversize(count+1,RamUsageEstimator.NUM_BYTES_OBJECT_REF)];}if(count==outputs.length){CharsRef[] next=new CharsRef[ArrayUtil.oversize(count+1,RamUsageEstimator.NUM_BYTES_OBJECT_REF)];System.arraycopy(outputs,0,next,0,count);outputs=next;}if(posLengths==null){posLengths=new int[ArrayUtil.oversize(count+1,RamUsageEstimator.NUM_BYTES_INT32)];}if(count==posLengths.length){int[] next=new int[ArrayUtil.oversize(count+1,RamUsageEstimator.NUM_BYTES_INT32)];System.arraycopy(posLengths,0,next,0,count);posLengths=next;}if(endOffsets==null){endOffsets=new int[ArrayUtil.oversize(count+1,RamUsageEstimator.NUM_BYTES_INT32)];}if(count==endOffsets.length){int[] next=new int[ArrayUtil.oversize(count+1,RamUsageEstimator.NUM_BYTES_INT32)];System.arraycopy(endOffsets,0,next,0,count);endOffsets=next;}outputs[count]=CharsRef.copyChars(output,offset,len);endOffsets[count]=endOffset;posLengths[count]=posLength;++count;}
public class FetchLibrariesRequest {     public HTTPS.ProtocolType Protocol { get; set; } }
boolean exists() { return objects.exists(); }
java.io.FilterOutputStream out = new java.io.FilterOutputStream(System.out);
public ScaleClusterRequest() { super("EHPC","2017-07-14","ScaleCluster","ehpc","openAPI"); setUriPattern("/clusters/[ClusterId]/scale"); setMethod(MethodType.PUT); }
public IDataValidationConstraint createTimeConstraint(int operatorType, String formula1, String formula2) { return DVConstraint.createTimeConstraint(operatorType, formula1, formula2); }
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListObjectParentPathsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ListObjectParentPathsResponseUnmarshaller.getInstance()); return invoke(request, options);
public DescribeCacheSubnetGroupsResponse describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DescribeCacheSubnetGroupsRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance());return invoke(request,options);}
public void setSharedFormula(boolean flag) { field_5_options.setShortBoolean(sharedFormula, flag); }
boolean isReuseObjects() { return reuseObjects; }
IErrorNode t = addErrorNode(new ErrorNodeImpl(badToken)); parent.addChild(t); return t; }
public LatvianStemFilterFactory(java.util.Map<String,String> args){super(args);if(args.size()>0){throw new IllegalArgumentException("Unknown parameters: "+args);}}
public RemoveSourceIdentifierFromSubscriptionResponse removeSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(RemoveSourceIdentifierFromSubscriptionRequestMarshaller.getInstance());options.setResponseUnmarshaller(RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.getInstance());return invoke(request,options);}
public TokenFilterFactory forName(String name, java.util.Map<String, String> args) { return loader.newInstance().forName(TokenFilterFactory.class, name, args); }
private HTTPS.ProtocolType protocol = HTTPS.ProtocolType.AddAlbumPhotosRequest;
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetThreatIntelSetRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetThreatIntelSetResponseUnmarshaller.getInstance()); return invoke(request, options);
TreeFilter Clone() {     return new Binary.AndTreeFilter(a.Clone(), b.Clone()); }
public boolean equals(Object o){return o instanceof MyClass;}
boolean hasArray(){return protectedHasArray();}
return invoke(request, new InvokeOptions().withRequestMarshaller(UpdateContributorInsightsRequestMarshaller.getInstance()).withResponseUnmarshaller(UpdateContributorInsightsResponseUnmarshaller.getInstance()));
void unwriteProtectWorkbook() { records.remove(); records.remove(); fileShare = null; writeProtect = null; }
public SolrSynonymParser(boolean dedup, boolean expand, Analyzer analyzer){super(dedup, expand, analyzer);}
return invoke(request,new InvokeOptions().withRequestMarshaller(RequestSpotInstancesRequestMarshaller.getInstance()).withResponseUnmarshaller(RequestSpotInstancesResponseUnmarshaller.getInstance()),RequestSpotInstancesResponse.class);
byte[] getObjectData() { return findObjectRecord(objectData); }
return invoke(request,new InvokeOptions().withRequestMarshaller(GetContactAttributesRequestMarshaller.getInstance()).withResponseUnmarshaller(GetContactAttributesResponseUnmarshaller.getInstance()));
public String toString() { return GetKey() + " " + GetValue(); }
return invoke(ListTextTranslationJobsResponse.class, request, InvokeOptions.builder().requestMarshaller(ListTextTranslationJobsRequestMarshaller.getInstance()).responseUnmarshaller(ListTextTranslationJobsResponseUnmarshaller.getInstance()).build());
public GetContactMethodsResponse getContactMethods(GetContactMethodsRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetContactMethodsRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetContactMethodsResponseUnmarshaller.getInstance());return invoke(request,options);}
short lookupIndexByName(String name){FunctionMetadata fd=getInstance().getFunctionByNameInternal(name);if(fd==null)return-1;return(short)fd.index;}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeAnomalyDetectorsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeAnomalyDetectorsResponseUnmarshaller.getInstance()); return invoke(request, options);
public String insertId(String message, ObjectId changeId) { return insertId(message, changeId); }
long getObjectSize(AnyObjectId objectId, int typeHint) throws MissingObjectException { long sz = db.getObjectSize(objectId, typeHint); if (sz < 0) { if (typeHint == OBJ_ANY) { throw new MissingObjectException(objectId.copy(), "Copy"); } else { throw new MissingObjectException(objectId.copy(), typeHint); } } return sz; }
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ImportInstallationMediaRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ImportInstallationMediaResponseUnmarshaller.getInstance()); return invoke(request, options);
return invoke(PutLifecycleEventHookExecutionStatusResponse.class, request, new InvokeOptions().withRequestMarshaller(PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance()).withResponseUnmarshaller(PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance()));
NumberPtg(ILittleEndianInput in1) { field_1_value = readDouble(in1); }
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance()); return invoke(request, options);
return invoke(request,new InvokeOptions().withRequestMarshaller(DescribeDetectorRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeDetectorResponseUnmarshaller.getInstance()));
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ReportInstanceStatusRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ReportInstanceStatusResponseUnmarshaller.getInstance()); return invoke(request, options);
return invoke(DeleteAlarmResponse.class, request, new InvokeOptions().withRequestMarshaller(DeleteAlarmRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteAlarmResponseUnmarshaller.getInstance()));
public TokenStream create(TokenStream input){ return new PortugueseStemFilter(input); }
public FtCblsSubRecord(){reserved=new byte[ENCODED_SIZE];}
public boolean remove(Object object){ synchronized(mutex){ return c.remove(object); } }
public GetDedicatedIpResponse getDedicatedIp(GetDedicatedIpRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetDedicatedIpRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetDedicatedIpResponseUnmarshaller.getInstance());return invoke(request,options,GetDedicatedIpResponse.class);}
public String toString() { return precedence + " "; }
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListStreamProcessorsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ListStreamProcessorsResponseUnmarshaller.getInstance()); return invoke(request, options, ListStreamProcessorsResponse.class);
public class DeleteLoadBalancerPolicyRequest { private String policyName; private String loadBalancerName; public DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) { this.policyName = policyName; this.loadBalancerName = loadBalancerName; } }
WindowProtectRecord(int options){_options = options;}
public UnbufferedCharStream(int n,int bufferSize){this.n=0;data=new int[bufferSize];}
public GetOperationsResponse getOperations(GetOperationsRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetOperationsRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetOperationsResponseUnmarshaller.getInstance());return invoke(request,options);}
void copyRawTo(byte[] b,int o){EncodeInt32.NB(b,o);EncodeInt32.NB(b,o+4);EncodeInt32.NB(b,o+8);EncodeInt32.NB(b,o+12);EncodeInt32.NB(b,o+16);}
field_1_h_hold=in.readShort(); field_2_v_hold=in.readShort(); field_3_width=in.readShort(); field_4_height=in.readShort(); field_5_options=in.readShort(); field_6_active_sheet=in.readShort(); field_7_first_visible_tab=in.readShort(); field_8_num_selected_tabs=in.readShort(); field_9_tab_width_ratio=in.readShort();
return invoke(StopWorkspacesResponse.class, request, new InvokeOptions().withRequestMarshaller(StopWorkspacesRequestMarshaller.getInstance()).withResponseUnmarshaller(StopWorkspacesResponseUnmarshaller.getInstance()));
public void close() throws IOException { if (isOpen) { try { dump(); } finally { try { channel.truncate(0); } finally { channel.close(); fos.close(); } } } }
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeMatchmakingRuleSetsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeMatchmakingRuleSetsResponseUnmarshaller.getInstance()); return invoke(DescribeMatchmakingRuleSetsResponse.class, request, options);
public String GetPronunciation(int wordId, char[] surface, int off, int len) { return null; }
String getPath() { return pathStr; }
public static double devsq(double[] v){double r=Double.NaN;if(v!=null&&v.length>=1){double m=0,s=0;int n=v.length;for(int i=0;i<n;++i)s+=v[i];m=s/n;s=0;for(int i=0;i<n;++i)s+=(v[i]-m)*(v[i]-m);r=(n==1?0:s);}return r;}
public DescribeResizeResponse describeResize(DescribeResizeRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DescribeResizeRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeResizeResponseUnmarshaller.getInstance());return invoke(request,options);}
public boolean hasPassedThroughNonGreedyDecision(){return passedThroughNonGreedyDecision;}
int end(){return end();}
public void traverse(ICellHandler handler){int firstRow=range.getFirstRow();int lastRow=range.getLastRow();int firstColumn=range.getFirstColumn();int lastColumn=range.getLastColumn();int width=lastColumn-firstColumn+1;SimpleCellWalkContext ctx=new SimpleCellWalkContext();IRow currentRow=null;ICell currentCell=null;for(ctx.rowNumber=firstRow;ctx.rowNumber<=lastRow;ctx.rowNumber++){currentRow=sheet.getRow(ctx.rowNumber);if(currentRow==null){continue;}for(ctx.colNumber=firstColumn;ctx.colNumber<=lastColumn;ctx.colNumber++){currentCell=currentRow.getCell(ctx.colNumber);if(currentCell==null){continue;}if(!isEmpty(currentCell)||traverseEmptyCells){ctx.ordinalNumber=(ctx.rowNumber-firstRow)*width+(ctx.colNumber-firstColumn)+1;handler.onCell(ctx,currentCell);}}}}
int getReadIndex(){return _ReadIndex;}
public int compareTo(ScoreTerm other){if(Term.BytesEquals(other.Term))return 0;if(Boost==other.Boost){return Term.compareTo(other.Term);}else{return Float.compare(Boost,other.Boost);} }
public static int normalize(char[] s,int len){for(int i=0;i<len;++i){switch(s[i]){case '\u0654':len=StemmerUtil.delete(s,i,len);--i;break;case '\u06C1':case '\u06C2':s[i]='\u0647';break;case '\u06A9':s[i]='\u0643';break;case '\u06CC':case '\u06D2':s[i]='\u064A';break;default:break;}}return len;}
void serialize(ILittleEndianOutput out1){ out1.writeShort(); }
public DiagnosticErrorListener(boolean exactOnly){this.exactOnly=exactOnly;}
public class KeySchemaElement{private KeyType keyType;private String attributeName;public KeySchemaElement(){}public KeySchemaElement(KeyType keyType,String attributeName){this.keyType=keyType;this.attributeName=attributeName;}public KeyType getKeyType(){return keyType;}public void setKeyType(KeyType keyType){this.keyType=keyType;}public String getAttributeName(){return attributeName;}public void setAttributeName(String attributeName){this.attributeName=attributeName;}}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetAssignmentRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetAssignmentResponseUnmarshaller.getInstance()); return invoke(GetAssignmentResponse.class, request, options);
boolean hasObject(AnyObjectId id) { return findOffset(id) != -1; }
public GroupingSearch setAllGroups(boolean allGroups) { this.allGroups = allGroups; return this; }
void setMultiValued(String dimName, boolean v) { synchronized (fieldTypes) { DimConfig fieldType = fieldTypes.get(dimName); if (fieldType == null) { fieldType = new DimConfig(); fieldTypes.put(dimName, fieldType); } fieldType.isMultiValued = v; } }
int getCellsVal() { int size = 0; for (char c : cells.keySet()) { Cell e = At(c); if (e.cmd >= 0) ++size; } return size; }
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteVoiceConnectorRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteVoiceConnectorResponseUnmarshaller.getInstance()); return invoke(DeleteVoiceConnectorResponse.class, request, options);
return invoke(request, new InvokeOptions().withRequestMarshaller(DeleteLifecyclePolicyRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteLifecyclePolicyResponseUnmarshaller.getInstance()));
