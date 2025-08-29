void serialize(ILittleEndianOutput out1) { out1.writeShort(); }
void addAll(NGit.Util.BlockList<T> src){if(src.size()==0){return;}for(int srcDirIdx=0;srcDirIdx<src.tailDirIdx;++srcDirIdx){addAll(src.directory[srcDirIdx]);}if(src.tailBlkIdx!=0){addAll(src.tailBlock,0,src.tailBlkIdx);}}
void WriteByte(byte b){if(upto==blockSize){if(currentBlock!=null){blocks.add(currentBlock);blockEnd.add(upto);}currentBlock=new byte[blockSize];upto=0;}currentBlock[upto++]=b;}
ObjectId getObjectId() { return objectId; }
public DeleteDomainEntryResponse deleteDomainEntry(DeleteDomainEntryRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DeleteDomainEntryRequestMarshaller.getInstance());options.setResponseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.getInstance());return invoke(request,options);}
long ramBytesUsed() { return fst == null ? 0 : fst.getSizeInBytes(); }
String getFullMessage(byte[] raw){int msgB=RawParseUtils.tagMessage(raw);if(msgB<0)return"";java.nio.charset.Charset enc=RawParseUtils.parseEncoding(raw);return RawParseUtils.decode(raw,msgB,raw.length,enc);}
public POIFSFileSystem() { _root = null; _documents = new ArrayList(); _property_table = new PropertyTable(); headerBlock = new HeaderBlock(); }
void init(int address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT][address & ByteBlockPool.BYTE_BLOCK_MASK]; assert slice != null; upto = slice.length; }
public NGit.Api.SubmoduleAddCommand SetPath(String path){this.path=path;return this;}
public ListIngestionsResponse listIngestions(ListIngestionsRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(ListIngestionsRequestMarshaller.getInstance());options.setResponseUnmarshaller(ListIngestionsResponseUnmarshaller.getInstance());return invoke(request,options,ListIngestionsResponse.class);}
public QueryParserTokenManager(ICharStream stream, int lexState) { this(stream); SwitchTo(lexState); }
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetShardIteratorRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetShardIteratorResponseUnmarshaller.getInstance()); return invoke(GetShardIteratorResponse.class, request, options);
public ModifyStrategyRequest(){ super("aegis","2016-11-11","ModifyStrategy","vipaegis","openAPI"); setMethod(MethodType.POST); }
boolean ready(){synchronized(lock){if(in==null){throw new IOException("");}try{return bytes.hasRemaining()||in.available()>0;}catch(IOException e){return false;}}}
return optRecord;
public int read(byte[] buffer, int offset, int length) { if (buffer == null) { throw new NullPointerException("buffer"); } java.util.Arrays.checkOffsetAndCount(buffer.length, offset, length); if (length == 0) { return 0; } int copylen = (pos + length < count) ? length : (count - pos); for (int i = 0; i < copylen; ++i) { buffer[offset + i] = (byte) this.buffer[pos + i]; } pos += copylen; return copylen; }
}; new OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp.sentenceOp);
void print(String str){System.out.print(str!=null?str:StringHelper.Sharpen.getValueOf((Object)null));}
public NotImplementedFunctionException(String functionName, Throwable cause) { super(functionName, cause); }
return nextV(nextEntry.value);
void ReadBytes(byte[] b,int offset,int len,boolean useBuffer){if(len<0)throw new IllegalArgumentException("len < 0");int available;while(len>0){if((available=bufferLength-bufferPosition)>0){if(available>len)available=len;System.arraycopy(buffer,bufferPosition,b,offset,available);offset+=available;len-=available;bufferPosition+=available;}else{if(useBuffer&&len<bufferSize){Refill();if(bufferLength<=0)throw new EOFException("could not refill");}else{long after=(long)len+bufferPosition+bufferStart;if(after>Length)throw new EOFException("after "+after+" > "+Length);int read=ReadInternal(b,offset,len);if(read>0){offset+=read;len-=read;bufferStart+=read;bufferPosition+=read;}else throw new EOFException();}}}}
public TagQueueResponse tagQueue(TagQueueRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(TagQueueRequestMarshaller.getInstance());options.setResponseUnmarshaller(TagQueueResponseUnmarshaller.getInstance());return invoke(request,options,TagQueueResponse.class);}
void Remove() { throw new UnsupportedOperationException(); }
public ModifyCacheSubnetGroupResponse modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(ModifyCacheSubnetGroupRequestMarshaller.getInstance());options.setResponseUnmarshaller(ModifyCacheSubnetGroupResponseUnmarshaller.getInstance());return invoke(request,options);}
import java.util.StringTokenizer; void setParams(String params){StringTokenizer st=new StringTokenizer(params," ");while(st.hasMoreTokens()){String culture=st.nextToken();if(st.hasMoreTokens())culture+=" "+st.nextToken();}}
return invoke(request, new InvokeOptions().withRequestMarshaller(DeleteDocumentationVersionRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteDocumentationVersionResponseUnmarshaller.getInstance()), DeleteDocumentationVersionResponse.class);
@Override public boolean equals(Object obj){if(obj==null||!(obj instanceof FacetLabel))return false;FacetLabel other=(FacetLabel)obj;if(Length!=other.Length)return false;for(int i=Length-1;i>=0;--i){if(!Components[i].equals(other.Components[i]))return false;}return true;}
return invoke(request, new InvokeOptions().withRequestMarshaller(GetInstanceAccessDetailsRequestMarshaller.getInstance()).withResponseUnmarshaller(GetInstanceAccessDetailsResponseUnmarshaller.getInstance()));
HSSFPolygon shape = shapes.createPolygon(new HSSFChildAnchor()); shape.setParent(anchor); shape.setAnchor(anchor); shapes.add(shape); OnCreate(); return shape;
String getSheetName(int sheetIndex){return GetBoundSheetRec(sheetIndex).Sheetname;}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetDashboardRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetDashboardResponseUnmarshaller.getInstance()); return invoke(GetDashboardResponse.class, request, options);
return invoke(request, new InvokeOptions().withRequestMarshaller(AssociateSigninDelegateGroupsWithAccountRequestMarshaller.getInstance()).withResponseUnmarshaller(AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.getInstance()), AssociateSigninDelegateGroupsWithAccountResponse.class);
public void addMultipleBlanks(MulBlankRecord br){for(int j=0;j<br.NumColumns;++j){BlankRecord newbr=new BlankRecord(br.Row,br.FirstColumn+j,br.XFIndex);getXFAt(newbr);insertCell(newbr);}}
public static String quote(String string){StringBuilder sb=new StringBuilder();int apos=0,k;while((k=string.indexOf('\\',apos))>=0){sb.append(Sharpen.StringHelper.substring(string,apos,k+2));sb.append("\\\\\\\\");apos=k+2;}sb.append(Sharpen.StringHelper.substring(string,apos,string.length()));sb.append("\\");return sb.toString();}
java.nio.ByteBuffer putInt(int value){throw new java.nio.ReadOnlyBufferException();}
int _reserved0Int = 0; short _reserved1Short = 0; byte _reserved2Byte = 0; int nColumns = _nColumns; int nRows = _nRows; Object[][] values2d = new Object[nRows][nColumns]; for (int r = 0; r < nRows; ++r) { Object[] rowData = values2d[r]; for (int c = 0; c < nColumns; ++c) { rowData[c] = vv[GetValueIndex(vv, r, c)]; } } _arrayValues = vv;
return invoke(request,new InvokeOptions().withRequestMarshaller(GetIceServerConfigRequestMarshaller.getInstance()).withResponseUnmarshaller(GetIceServerConfigResponseUnmarshaller.getInstance()),GetIceServerConfigResponse.class);
public String toString() { StringBuilder sb = new StringBuilder(); sb.append(getClass().getName()).append(" ").append(getValueAsString()).append(" "); return sb.toString(); }
String ToString(String field) { return " " + _parentQuery + " "; }
void IncRef() { refCount.incrementAndGet(); }
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(UpdateConfigurationSetSendingEnabledRequestMarshaller.getInstance()); options.setResponseUnmarshaller(UpdateConfigurationSetSendingEnabledResponseUnmarshaller.getInstance()); return invoke(request, options);
return (int) getNextXBATChainOffset(getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE);
void multiplyByPowerOfTen(int pow10){TenPower tp=TenPower.getInstance();if(pow10<0){mulShift(tp._divisor,tp._divisorShift);}else{mulShift(tp._multiplicand,tp._multiplierShift);}}
public String toString() { StringBuilder builder = new StringBuilder(); int length = Length; builder.append(File.separatorChar); for (int i = 0; i < length; i++) { builder.append(getComponent(i)); if (i < length - 1) { builder.append(File.separatorChar); } } return builder.toString(); }
void withFetcher(ECSMetadataServiceCredentialsFetcher fetcher){fetcher.SetRoleName(fetcher);}
void SetProgressMonitor(ProgressMonitor pm){this.progressMonitor=pm;}
void Reset() { if(!First) { ptr = 0; if(!Eof) { ParseEntry(); } } }
if (iterator.previousIndex() >= start) { return iterator.previous(); } else { throw new java.util.NoSuchElementException(); }
String getNewPrefix() { return newPrefix; }
int indexOfValue(int value){for(int i=0;i<mSize;++i){if(mValues[i]==value)return i;}return -1;}
public static List<CharsRef> uniqueStems(char[] word,int length){List<CharsRef> stems=new ArrayList<>();if(stems.size()<2)return stems;CharArraySet terms=new CharArraySet(8,false);List<CharsRef> deduped=new ArrayList<>();for(CharsRef s:stems){if(!terms.contains(s)){terms.add(s);deduped.add(s);}}return deduped;}
return invoke(request,new InvokeOptions().withRequestMarshaller(GetGatewayResponsesRequestMarshaller.getInstance()).withResponseUnmarshaller(GetGatewayResponsesResponseUnmarshaller.getInstance()),GetGatewayResponsesResponse.class);
void setPosition(long position) { int currentBlockIndex = (int)(position >> outerInstance.blockBits); currentBlock = outerInstance.blocks[currentBlockIndex]; currentBlockUpto = (int)(position & outerInstance.blockMask); }
long Skip(long n){int s=(int)Math.min(n,Available());ptr+=s;return s;}
BootstrapActionConfig bootstrapActionConfig = new BootstrapActionConfig(bootstrapActionDetail);
void serialize(ILittleEndianOutput out1){out1.writeShort(field_6_author.length());out1.writeByte(field_5_hasMultibyte?0x01:0x00);StringUtil.putUnicodeLE(field_6_author,out1);if(field_7_padding!=null){out1.writeByte(Integer.parseInt(field_7_padding,Locale.ROOT));}}
int lastIndexOf(String string){return string.lastIndexOf(',');}
boolean add(E object){return addLastImpl();}
void UnsetSection(String section, String subsection){ConfigSnapshot src,res;do{src=state.get();res=src.unsetSection(section,subsection);}while(!state.compareAndSet(src,res));}
String getTagName(){ return tagName; }
void AddSubRecord(int index, SubRecord element) { subrecords.add(index, element); }
boolean remove(Object object){synchronized(mutex){return c.remove(object);}}
public TokenStream create(TokenStream input){return new DoubleMetaphoneFilter(input);}
long Length() { return InCoreLength(); }
void setValue(boolean newValue){value=newValue;}
Pair<ContentSource, ContentSource> pair = new Pair<>(newSource, oldSource);
int Get(int i){if(i<=count){throw Sharpen.Extensions.CreateIndexOutOfRangeException();}return entries[i];}
CreateRepoRequest request=new CreateRepoRequest();request.setMethod(MethodType.PUT);request.setUriPattern(" "," "," "," "," ");
public boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public void remove(){if(list.modCount!=expectedModCount){throw new java.util.ConcurrentModificationException();}if(lastLink==null){throw new IllegalStateException();}java.util.LinkedList.Link<ET> next=lastLink.next;java.util.LinkedList.Link<ET> previous=lastLink.previous;if(next!=null)next.previous=previous;if(previous!=null)previous.next=next;if(link==lastLink)pos--;else link=next;lastLink=null;expectedModCount++;list._size--;list.modCount++;}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(MergeShardsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(MergeShardsResponseUnmarshaller.getInstance()); return invoke(request, options);
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(AllocateHostedConnectionRequestMarshaller.getInstance()); options.setResponseUnmarshaller(AllocateHostedConnectionResponseUnmarshaller.getInstance()); return invoke(request, options);
int getBeginIndex() { return start; }
WeightedTerm[] getTerms(Query query){return getTerms(query);}
java.nio.ByteBuffer compact() { throw new java.nio.ReadOnlyBufferException(); }
void decode(byte[] blocks,int blocksOffset,int[] values,int valuesOffset,int iterations){for(int i=0;i<iterations;i++){int byte0=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=byte0>>2;int byte1=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=((byte0&3)<<4)|(byte1>>4);int byte2=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=((byte1&15)<<2)|(byte2>>6);values[valuesOffset++]=byte2&63;}}
public String getHumanishName(){String path=getPath();if(path==null||path.length()==0)throw new IllegalArgumentException(path);String[] elements=path.split("[/\\\\]");String result=elements[elements.length-1];if(Constants.DOT_GIT.equals(result)&&elements.length>1)result=elements[elements.length-2];if(result.endsWith(Constants.DOT_GIT_EXT))result=result.substring(0,result.length()-Constants.DOT_GIT_EXT.length());return result;}
return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance()));
String getAccessKeySecret() { return accessSecret; }
private CreateVpnConnectionResponse createVpnConnection(CreateVpnConnectionRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(CreateVpnConnectionRequestMarshaller.getInstance());options.setResponseUnmarshaller(CreateVpnConnectionResponseUnmarshaller.getInstance());return invoke(request,options);}
return invoke(DescribeVoicesResponse.class, request, new InvokeOptions().withRequestMarshaller(DescribeVoicesRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeVoicesResponseUnmarshaller.getInstance()));
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListMonitoringExecutionsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ListMonitoringExecutionsResponseUnmarshaller.getInstance()); return invoke(request, options);
public DescribeJobRequest(String vaultName, String jobId){this._vaultName=vaultName;this._jobId=jobId;}
public EscherRecord getEscherRecord(int index){return escherRecords[index];}
InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetApisRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetApisResponseUnmarshaller.getInstance());return invoke(GetApisResponse.class,request,options);
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteSmsChannelRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteSmsChannelResponseUnmarshaller.getInstance()); return invoke(request, DeleteSmsChannelResponse.class, options);
public TrackingRefUpdate getTrackingRefUpdate() { return trackingRefUpdate; }
void print(boolean b){ print(Boolean.toString(b)); }
public IQueryNode getChild() { return getChildren()[0]; }
int workdirTreeIndex; NotIgnoredFilter index = new NotIgnoredFilter(workdirTreeIndex);
AreaRecord(RecordInputStream in1) { field_1_formatFlags = in1.readShort(); }
GetThumbnailRequest request = new GetThumbnailRequest(" ", " ", " ", " ", " "); request.setProtocol(ProtocolType.HTTPS);
return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeTransitGatewayVpcAttachmentsRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance()));
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance()); options.setResponseUnmarshaller(PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance()); return invoke(PutVoiceConnectorStreamingConfigurationResponse.class, request, options);
OrdRange getOrdRange(String dim){return prefixToOrdRange.get(dim);}
if (_tokenStartCharIndex >= 0 && _tokenStartCharIndex < _input.size()) { String symbol = _input.getText(Interval.of(_tokenStartCharIndex, _input.index())); symbol = Utils.escapeWhitespace(symbol, false); String msg = String.format(Locale.getDefault(), "token recognition error at: '%s'", symbol); throw new LexerNoViableAltException(this, _input, _tokenStartCharIndex, null); }
E peek() { return peekFirstImpl(); }
return this.<CreateWorkspacesResponse>invoke(request, new InvokeOptions().withRequestMarshaller(CreateWorkspacesRequestMarshaller.getInstance()).withResponseUnmarshaller(CreateWorkspacesResponseUnmarshaller.getInstance()));
public Object clone() { NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = field_1_formatIndex; return rec; }
return invoke(request,new InvokeOptions().withRequestMarshaller(DescribeRepositoriesRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeRepositoriesResponseUnmarshaller.getInstance()),DescribeRepositoriesResponse.class);
public SparseIntArray(int initialCapacity) { initialCapacity = android.util.internal.ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
TokenStream create(TokenStream input){return new HyphenatedWordsFilter(input);}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(CreateDistributionWithTagsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(CreateDistributionWithTagsResponseUnmarshaller.getInstance()); return this.<CreateDistributionWithTagsResponse>invoke(request, options);
new RandomAccessFile(fileName, mode); throw new UnsupportedOperationException();
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.getInstance()); return invoke(request, options, DeleteWorkspaceImageResponse.class);
public static String toHex(int value){return toHex((long)value);}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(UpdateDistributionRequestMarshaller.getInstance()); options.setResponseUnmarshaller(UpdateDistributionResponseUnmarshaller.getInstance()); return invoke(request, options);
public HSSFColor getColor(short index){if(HSSFColor.AUTOMATIC.getIndex()==index)return HSSFColor.AUTOMATIC.getInstance();byte[] b=palette.getColor(index);if(b!=null)return new CustomColor(b);return null;}
public ValueEval evaluate(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(); }
public void serialize(ILittleEndianOutput out1){out1.writeShort(field_1_number_crn_records);out1.writeShort(field_2_sheet_table_index);}
DescribeDBEngineVersionsResponse describeDBEngineVersions(DescribeDBEngineVersionsRequest request){return new DescribeDBEngineVersions();}
FormatRun(short character, short fontIndex){this._character=character;this._fontIndex=fontIndex;}
static byte[] toBigEndianUtf16Bytes(char[] chars,int offset,int length){byte[] result=new byte[length*2];int end=length+offset;int resultIndex=0;for(int i=offset;i<end;i++){char ch=chars[i];result[resultIndex++]=(byte)(ch>>8);result[resultIndex++]=(byte)ch;}return result;}
InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(UploadArchiveRequestMarshaller.getInstance());options.setResponseUnmarshaller(UploadArchiveResponseUnmarshaller.getInstance());return invoke(request,options);
List<IToken> getHiddenTokensToLeft(int tokenIndex){return getHiddenTokensToLeft(tokenIndex - 1);}
@Override public boolean equals(Object obj){if(obj==this){return true;}if(!super.equals(obj)){return false;}if(getClass()!=obj.getClass()){return false;}AutomatonQuery other=(AutomatonQuery)obj;if(m_compiled!=null?!m_compiled.equals(other.m_compiled):other.m_compiled!=null){return false;}if(m_term==null?other.m_term!=null:!m_term.equals(other.m_term)){return false;}return true;}
List<SpanQuery> spanQueries = new ArrayList<>(MakeSpanClause()); for (Map.Entry<SpanQuery, Float> wsq : weightBySpanQuery.entrySet()) { wsq.getKey().setBoost(wsq.getValue()); spanQueries.add(wsq.getKey()); } if (spanQueries.size() == 1) return spanQueries.get(0); else return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0]));
return new StashCreateCommand(StashCreate);
FieldInfo getFieldInfoByName(String fieldName){FieldInfo[] ret=new FieldInfo[1];TryGetValue(ret,fieldName);return ret[0];}
return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeEventSourceRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeEventSourceResponseUnmarshaller.getInstance()));
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetDocumentAnalysisRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetDocumentAnalysisResponseUnmarshaller.getInstance()); return invoke(GetDocumentAnalysisResponse.class, request, options);
public CancelUpdateStackResponse cancelUpdateStack(CancelUpdateStackRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(CancelUpdateStackRequestMarshaller.getInstance());options.setResponseUnmarshaller(CancelUpdateStackResponseUnmarshaller.getInstance());return invoke(request,options);}
public ModifyLoadBalancerAttributesResponse modifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(ModifyLoadBalancerAttributesRequestMarshaller.getInstance());options.setResponseUnmarshaller(ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance());return invoke(request,options,ModifyLoadBalancerAttributesResponse.class);}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(SetInstanceProtectionRequestMarshaller.getInstance()); options.setResponseUnmarshaller(SetInstanceProtectionResponseUnmarshaller.getInstance()); return invoke(request, SetInstanceProtectionResponse.class, options);
ModifyDBProxyResponse modifyDBProxy(ModifyDBProxyRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(ModifyDBProxyRequestMarshaller.getInstance());options.setResponseUnmarshaller(ModifyDBProxyResponseUnmarshaller.getInstance());return invoke(request,options);}
void add(char[] output,int offset,int len,int endOffset,int posLength){if(posLengths.length==count){int newLen=ArrayUtil.oversize(count+1,RamUsageEstimator.NUM_BYTES_INT32);posLengths=Arrays.copyOf(posLengths,newLen);}if(endOffsets.length==count){int newLen=ArrayUtil.oversize(count+1,RamUsageEstimator.NUM_BYTES_INT32);endOffsets=Arrays.copyOf(endOffsets,newLen);}if(outputs.length==count){int newLen=ArrayUtil.oversize(count+1,RamUsageEstimator.NUM_BYTES_OBJECT_REF);outputs=Arrays.copyOf(outputs,newLen);}posLengths[count]=posLength;endOffsets[count]=endOffset;outputs[count]=len==0?null:new CharsRef(output,offset,len);count++;}
public class FetchLibrariesRequest { private HTTPS.ProtocolType protocol; }
boolean exists() { return objects.exists(); }
new java.io.FilterOutputStream(out);
setMethod(MethodType.PUT); setUriPattern(" ");
DVConstraint createTimeConstraint(int operatorType, String formula1, String formula2);
return invoke(request, new InvokeOptions().withRequestMarshaller(ListObjectParentPathsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListObjectParentPathsResponseUnmarshaller.getInstance()));
public DescribeCacheSubnetGroupsResponse describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DescribeCacheSubnetGroupsRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance());return invoke(request,options);}
void SetSharedFormula(boolean flag){field_5_options.sharedFormula=SetShortBoolean(flag);}
boolean IsReuseObjects() { return reuseObjects; }
public IErrorNode AddErrorNode(IToken badToken){ErrorNodeImpl t=new ErrorNodeImpl(badToken);AddChild(t);t.Parent=Parent;return t;}
public LatvianStemFilterFactory(Map<String,String> args){super(args);if(!args.isEmpty()){throw new IllegalArgumentException("Unknown parameters: "+args);}}
InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(RemoveSourceIdentifierFromSubscriptionRequestMarshaller.getInstance());options.setResponseUnmarshaller(RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.getInstance());return invoke(request,options);
return loader.newInstance(TokenFilterFactory.forName(name, args));
public class AddAlbumPhotosRequest extends Protocol { public AddAlbumPhotosRequest(String p1,String p2,String p3,String p4,String p5){ super(HTTPS.ProtocolType); } }
InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetThreatIntelSetRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetThreatIntelSetResponseUnmarshaller.getInstance());return invoke(request,options);
return new Binary.AndTreeFilter(Clone.a(), Clone.b());
boolean equals(Object o) { return o != null; }
boolean hasArray() { return protectedHasArray(); }
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(UpdateContributorInsightsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(UpdateContributorInsightsResponseUnmarshaller.getInstance()); return invoke(request, options);
void UnwriteProtectWorkbook(){records.remove(writeProtect);records.remove(fileShare);writeProtect=null;fileShare=null;}
public SolrSynonymParser(boolean dedup, boolean expand, Analyzer analyzer) { super(dedup, expand, analyzer); }
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(RequestSpotInstancesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(RequestSpotInstancesResponseUnmarshaller.getInstance()); return invoke(request, options);
byte[] getObjectData() { return findObjectRecord(objectData); }
return invoke(GetContactAttributesResponse.class, request, new InvokeOptions().setRequestMarshaller(GetContactAttributesRequestMarshaller.getInstance()).setResponseUnmarshaller(GetContactAttributesResponseUnmarshaller.getInstance()));
String ToString(){return GetKey()+" "+GetValue();}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListTextTranslationJobsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ListTextTranslationJobsResponseUnmarshaller.getInstance()); return invoke(request, options);
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetContactMethodsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetContactMethodsResponseUnmarshaller.getInstance()); return invoke(request, options, GetContactMethodsResponse.class);
short lookupIndexByName(String name){FunctionMetadata fd=getInstance().getFunctionByNameInternal(name);if(fd==null){return -1;}return (short)fd.Index;}
return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeAnomalyDetectorsRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeAnomalyDetectorsResponseUnmarshaller.getInstance()));
String insertId(String message, ObjectId changeId){return insertId;}
long getObjectSize(AnyObjectId objectId,int typeHint){long sz=db.getObjectSize(objectId,typeHint);if(sz<0){if(typeHint==OBJ_ANY){throw new MissingObjectException(objectId,"Copy");}throw new MissingObjectException(objectId,"Copy");}return sz;}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ImportInstallationMediaRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ImportInstallationMediaResponseUnmarshaller.getInstance()); return invoke(request, options);
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance()); options.setResponseUnmarshaller(PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance()); return invoke(request, options);
NumberPtg(ILittleEndianInput in1) { field_1_value = in1.readDouble(); }
return invoke(request, new InvokeOptions().withRequestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.getInstance()).withResponseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance()));
return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeDetectorRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeDetectorResponseUnmarshaller.getInstance()));
return invoke(request, new InvokeOptions().withRequestMarshaller(ReportInstanceStatusRequestMarshaller.getInstance()).withResponseUnmarshaller(ReportInstanceStatusResponseUnmarshaller.getInstance()));
public DeleteAlarmResponse deleteAlarm(DeleteAlarmRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DeleteAlarmRequestMarshaller.getInstance());options.setResponseUnmarshaller(DeleteAlarmResponseUnmarshaller.getInstance());return invoke(DeleteAlarmResponse.class,request,options);}
public TokenStream create(TokenStream input) { return new PortugueseStemFilter(input); }
FtCblsSubRecord() { reserved = new byte[ENCODED_SIZE]; }
boolean remove(Object object){synchronized(mutex){return c.remove();}}
public GetDedicatedIpResponse getDedicatedIp(GetDedicatedIpRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetDedicatedIpRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetDedicatedIpResponseUnmarshaller.getInstance());return invoke(request,options,GetDedicatedIpResponse.class);}
String toString() { return precedence + " "; }
return invoke(request, new InvokeOptions().withRequestMarshaller(ListStreamProcessorsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListStreamProcessorsResponseUnmarshaller.getInstance()));
public DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) { this._loadBalancerName = loadBalancerName; this._policyName = policyName; }
public WindowProtectRecord(int options){this._options=options;}
UnbufferedCharStream(int bufferSize){n=0;data=new int[bufferSize];}
public GetOperationsResponse getOperations(GetOperationsRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetOperationsRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetOperationsResponseUnmarshaller.getInstance());return invoke(request,options);}
public void copyRawTo(byte[] b, int o){EncodeInt32.NB(b,o+16);EncodeInt32.NB(b,o+12);EncodeInt32.NB(b,o+8);EncodeInt32.NB(b,o+4);}
field_9_tab_width_ratio = in1.readShort(); field_8_num_selected_tabs = in1.readShort(); field_7_first_visible_tab = in1.readShort(); field_6_active_sheet = in1.readShort(); field_5_options = in1.readShort(); field_4_height = in1.readShort(); field_3_width = in1.readShort(); field_2_v_hold = in1.readShort(); field_1_h_hold = in1.readShort();
public StopWorkspacesResponse stopWorkspaces(StopWorkspacesRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(StopWorkspacesRequestMarshaller.getInstance());options.setResponseUnmarshaller(StopWorkspacesResponseUnmarshaller.getInstance());return invoke(request,options);}
void close() throws IOException { if (isOpen()) { try { dump(); } finally { try { channel.truncate(); } finally { try { channel.close(); } finally { try { fos.close(); } finally { } } } } } }
return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeMatchmakingRuleSetsRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeMatchmakingRuleSetsResponseUnmarshaller.getInstance()));
String getPronunciation(int wordId, char[] surface, int off, int len) { return null; }
String GetPath() { return pathStr; }
double devsq(double[] v){double r=Double.NaN;if(v!=null&&v.length>=1){double m=0,s=0;int n=v.length;for(int i=0;i<n;++i){s+=v[i];}s/=n;for(int i=0;i<n;++i){double d=v[i]-s;m+=d*d;}r=n==1?0:m;}return r;}
return invoke(DescribeResizeResponse.class, request, new InvokeOptions().withRequestMarshaller(DescribeResizeRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeResizeResponseUnmarshaller.getInstance()));
boolean hasPassedThroughNonGreedyDecision(){return passedThroughNonGreedyDecision;}
int end() { return end(); }
void traverse(ICellHandler handler){int width=lastColumn-firstColumn+1;SimpleCellWalkContext ctx=new SimpleCellWalkContext();IRow currentRow=null;ICell currentCell=null;for(ctx.rowNumber=firstRow;ctx.rowNumber<=lastRow;ctx.rowNumber++){currentRow=sheet.getRow(ctx.rowNumber);if(currentRow==null){continue;}for(ctx.colNumber=firstColumn;ctx.colNumber<=lastColumn;ctx.colNumber++){currentCell=currentRow.getCell(ctx.colNumber);if(currentCell==null){continue;}if(!traverseEmptyCells&&isEmpty(currentCell)){continue;}ctx.ordinalNumber=(ctx.rowNumber-firstRow)*width+(ctx.colNumber-firstColumn)+1;handler.onCell(ctx,currentCell);}}}
int getReadIndex() { return _ReadIndex; }
public int compareTo(ScoreTerm other){if(Term.BytesEquals(other.Term)){return 0;}else{return Boost.compareTo(other.Boost);}}
public static int normalize(char[] s,int len){for(int i=0;i<len;++i){switch(s[i]){case'\u0654':len=StemmerUtil.delete(s,i,len);--i;break;case'\u06C1':case'\u06D5':s[i]='\u06D2';break;case'\u0643':s[i]='\u06A9';break;case'\u064A':case'\u06D2':case'\u06CC':s[i]='\u06CC';break;default:break;}}return len;}
void serialize(ILittleEndianOutput out1){out1.writeShort();}
boolean exactOnly(DiagnosticErrorListener exactOnly){ exactOnly.exactOnly(); }
public class KeySchemaElement { private KeyType keyType; private String attributeName; public KeySchemaElement(String attributeName, KeyType keyType) { this.attributeName = attributeName; this.keyType = keyType; } public KeyType getKeyType() { return keyType; } public void setKeyType(KeyType keyType) { this.keyType = keyType; } public String getAttributeName() { return attributeName; } public void setAttributeName(String attributeName) { this.attributeName = attributeName; } }
return invoke(request,new InvokeOptions().withRequestMarshaller(GetAssignmentRequestMarshaller.getInstance()).withResponseUnmarshaller(GetAssignmentResponseUnmarshaller.getInstance()),GetAssignmentResponse.class);
boolean hasObject(AnyObjectId id) { return findOffset(id) != -1; }
public GroupingSearch SetAllGroups(boolean allGroups){this.allGroups=allGroups;return this;}
void setMultiValued(String dimName, boolean v){ synchronized(this){ DimConfig fieldType=fieldTypes.get(dimName); if(fieldType==null){ fieldType=new DimConfig(); fieldTypes.put(dimName,fieldType);} fieldType.isMultiValued=v; } }
int getCellsVal() { int size = 0; for (char c : cells.keySet()) { Cell e = At(c); if (cmd.e >= 0) { ++size; } } return size; }
InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DeleteVoiceConnectorRequestMarshaller.getInstance());options.setResponseUnmarshaller(DeleteVoiceConnectorResponseUnmarshaller.getInstance());return invoke(DeleteVoiceConnectorResponse.class,request,options);
return invoke(request, new InvokeOptions().withRequestMarshaller(DeleteLifecyclePolicyRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteLifecyclePolicyResponseUnmarshaller.getInstance()));
