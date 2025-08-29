public void serialize(ILittleEndianOutput out1){out1.writeShort(0);}
public static <T> void addAll(NGit.Util.BlockList<T> src, Directory[] directory, T tailBlock, int tailBlkIdx){if(src!=null){for(int srcDirIdx=0;srcDirIdx<src.size();++srcDirIdx){if(src.get(srcDirIdx)!=null){addAll(src.get(srcDirIdx),directory[srcDirIdx],tailBlock,tailBlkIdx);}}}}
void writeByte(byte b){if(outerInstance.upto.get(outerInstance.currentBlock)==outerInstance.blockSize){outerInstance.currentBlock++;outerInstance.blocks.add(new byte[outerInstance.blockSize]);outerInstance.upto.add(0);outerInstance.blockEnd.add(outerInstance.blockSize);}int pos=outerInstance.upto.get(outerInstance.currentBlock);outerInstance.blocks.get(outerInstance.currentBlock)[pos]=b;outerInstance.upto.set(outerInstance.currentBlock,pos+1);}
public ObjectId getObjectId() { return objectId; }
public DeleteDomainEntryResponse deleteDomainEntry(DeleteDomainEntryRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DeleteDomainEntryRequestMarshaller.getInstance());options.setResponseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.getInstance());return invoke(request,options);}
long RamBytesUsed() { return fst == null ? 0 : fst.getSizeInBytes(); }
public String getFullMessage() {byte[] raw = buffer;int msgB = RawParseUtils.tagMessage(raw, 0);if (msgB < 0) {return "";}Encoding enc = RawParseUtils.parseEncoding(raw);return RawParseUtils.decode(enc, raw, msgB, raw.length);}
public POIFSFileSystem() {_root = null; _documents = new ArrayList<>(); _property_table = new PropertyTable(new HeaderBlock()); headerBlock = new HeaderBlock();}
void init() {int address=0;int offset0=address&ByteBlockPool.BYTE_BLOCK_MASK;assert address<upto;assert upto<ByteBlockPool.BYTE_BLOCK_MASK;assert slice!=null;slice=ByteBlockPool.Buffers[address>>ByteBlockPool.BYTE_BLOCK_SHIFT];}
public SubmoduleAddCommand setPath(String path) { this.path = path; return this; }
public ListIngestionsResult listIngestions(ListIngestionsRequest request){request=beforeClientExecution(request);return executeListIngestions(request);}
public QueryParserTokenManager(ICharStream stream, int lexState) { this(stream); SwitchTo(lexState); }
public GetShardIteratorResponse getShardIterator(GetShardIteratorRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetShardIteratorRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetShardIteratorResponseUnmarshaller.getInstance());return invoke(request,options);}
public ModifyStrategyResult modifyStrategy(ModifyStrategyRequest request){request=beforeClientExecution(request);return executeModifyStrategy(request);}
public boolean ready() { synchronized (lock) { try { if (in == null) return false; return in.available() > 0 || bytes.hasRemaining(); } catch (IOException e) { throw e; } } }
public EscherOptRecord getOptRecord() { return _optRecord; }
public int read(byte[] dst,int offset,int length){synchronized(this){if(dst==null){throw new NullPointerException("buffer");}java.util.Arrays.checkOffsetAndCount(dst.length,offset,length);int copylen=count-pos;if(copylen>length){copylen=length;}if(copylen<=0){return 0;}System.arraycopy(buffer,pos,dst,offset,copylen);pos+=copylen;return copylen;}}
{ OpenNLPSentenceBreakIterator(sentenceOp.NLPSentenceDetectorOp(sentenceOp)); }
void print(String str) { write(String.valueOf(str != null ? str : null)); }
public NotImplementedFunctionException(String functionName, Throwable cause) {super(functionName, cause);}
V next() { return nextEntry().value; }
public void readBytes(byte[] b,int offset,int len,boolean useBuffer){int available=bufferLength-bufferPosition;if(available>=len){System.arraycopy(buffer,bufferPosition,b,offset,len);bufferPosition+=len;return;}if(available>0&&useBuffer){System.arraycopy(buffer,bufferPosition,b,offset,available);bufferPosition+=available;offset+=available;len-=available;}if(len<=0)return;if(len>bufferSize){long after=bufferStart+bufferPosition+len;if(after>length)throw new EOFException(after+" "+length);readInternal(b,offset,len);bufferStart=after;return;}refill();if(bufferLength<len)throw new EOFException();System.arraycopy(buffer,bufferPosition,b,offset,len);bufferPosition+=len;}
public TagQueueResponse tagQueue(TagQueueRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(TagQueueRequestMarshaller.getInstance());options.setResponseUnmarshaller(TagQueueResponseUnmarshaller.getInstance());return invoke(request,options);}
public void remove() { throw new UnsupportedOperationException(); }
public ModifyCacheSubnetGroupResult modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) {request = beforeClientExecution(request);return executeModifyCacheSubnetGroup(request);}
public void setParams(String params,StringTokenizer st,String culture,boolean ignore){if(st.moveNext(culture));if(st.moveNext(culture));if(st.moveNext("st st"));SetParams.current=st.current+" ";new StringTokenizer(" ");}
public DeleteDocumentationVersionResponse deleteDocumentationVersion(DeleteDocumentationVersionRequest request){InvokeOptions options=new InvokeOptions();options.requestMarshaller=DeleteDocumentationVersionRequestMarshaller.getInstance();options.responseUnmarshaller=DeleteDocumentationVersionResponseUnmarshaller.getInstance();return invoke(request,options);}
public boolean equals(Object obj){if(this==obj)return true;if(obj==null||!(obj instanceof FacetLabel))return false;FacetLabel other=(FacetLabel)obj;if(other.length!=length)return false;for(int i=length-1;i>=0;i--){if(!components[i].equals(other.components[i]))return false;}return true;}
public GetInstanceAccessDetailsResponse getInstanceAccessDetails(GetInstanceAccessDetailsRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetInstanceAccessDetailsRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetInstanceAccessDetailsResponseUnmarshaller.getInstance());return invoke(request,options);}
public HSSFPolygon createPolygon(HSSFChildAnchor anchor){HSSFPolygon shape=new HSSFPolygon(this,anchor);shapes.add(shape);shape.onCreate(anchor);return shape;}
public String getSheetName(int sheetIndex){ return getBoundSheetRec(sheetIndex).getSheetname(); }
public GetDashboardResponse getDashboard(GetDashboardRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetDashboardRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetDashboardResponseUnmarshaller.getInstance());return invoke(request,options);}
public AssociateSigninDelegateGroupsWithAccountResponse associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(AssociateSigninDelegateGroupsWithAccountRequestMarshaller.getInstance());options.setResponseUnmarshaller(AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.getInstance());return invoke(request, options);}
private void addMultipleBlanks(MulBlankRecord mbr){int row=mbr.getRow();int colIx=mbr.getFirstColumn();for(int j=0;j<mbr.getNumColumns();j++){BlankRecord br=new BlankRecord();br.setRow(row);br.setColumn((short)(colIx+j));br.setXFIndex(mbr.getXFAt(j));insertCell(br);}}
public static String quote(String s){StringBuilder sb=new StringBuilder();int k=0;while((k=s.indexOf("\\",k))>=0){sb.append(s.substring(0,k));sb.append("\\\\");k+=2;}sb.append(s.substring(k));return sb.toString();}
public java.nio.ByteBuffer putInt(int value){throw new java.nio.ReadOnlyBufferException();}
private static final int _reserved0Int=0; private static final short _reserved1Short=0; private static final byte _reserved2Byte=0; private final short _nRows; private final short _nColumns; private final Object[] _arrayValues; public ArrayPtg(Object[][] values2d){int nRows=values2d.length;int nColumns=values2d[0].length;_nRows=(short)nRows;_nColumns=(short)nColumns;Object[] vv=new Object[_nRows*_nColumns];for(int r=0;r<nRows;++r){Object[] rowData=values2d[r];for(int c=0;c<nColumns;++c){Object value=rowData[c];vv[GetValueIndex(r,c)]=value==null?"":value;}}_arrayValues=vv;}
public GetIceServerConfigResult getIceServerConfig(GetIceServerConfigRequest request){request=beforeClientExecution(request);return executeGetIceServerConfig(request);}
public String toString(){StringBuilder sb=new StringBuilder();sb.append(getClass().getSimpleName());sb.append(" ");sb.append(getValueAsString());sb.append(" ");return sb.toString();}
public String toString() { return field + " " + _parentQuery + " "; }
public final void incRef() { refCount.incrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResponse updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(UpdateConfigurationSetSendingEnabledRequestMarshaller.getInstance());options.setResponseUnmarshaller(UpdateConfigurationSetSendingEnabledResponseUnmarshaller.getInstance());return invoke(request,options);}
private int getNextXBATChainOffset() { return LittleEndianConsts.INT_SIZE * getXBATEntriesPerBlock(); }
void multiplyByPowerOfTen(int pow10){if(pow10<0){TenPower tp=TenPower.GetInstance(Math.abs(pow10));_divisor=tp._divisor;_divisorShift=tp._divisorShift;}else{TenPower tp=TenPower.GetInstance(pow10);_multiplicand=tp._multiplicand;_multiplierShift=tp._multiplierShift;}}
public String toString(){StringBuilder builder=new StringBuilder(length-1);for(int i=0;i<length;++i){if(File.separatorChar==getComponent(i)){builder.append(File.separatorChar);}else{builder.append(getComponent(i));}}return builder.toString();}
withFetcher void } { ) ( ; ; fetcher ECSMetadataServiceCredentialsFetcher fetcher ) ( setRoleName . fetcher . fetcher .
void setProgressMonitor(ProgressMonitor pm) { progressMonitor = pm; }
void Reset() { if (!First()) { if (!Eof(ptr, 0)) ParseEntry(); } }
public E previous() { if (start >= iterator.previousIndex()) { throw new java.util.NoSuchElementException(); } return iterator.previous(); }
String getNewPrefix(){return newPrefix;}
int indexOfValue(int value){for(int i=0;i<mSize;++i){if(mValues[i]==value){return i;}}return -1;}
public List<CharsRef> uniqueStems(char[] word,int length,CharArraySet dictionary){if(length<2)return Collections.emptyList();List<CharsRef> stems=stem(word,length);List<CharsRef> deduped=new ArrayList<>();for(CharsRef s:stems)if(!dictionary.contains(s)){deduped.add(s);dictionary.add(s);}return deduped;}
public GetGatewayResponsesResponse getGatewayResponses(GetGatewayResponsesRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetGatewayResponsesRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetGatewayResponsesResponseUnmarshaller.getInstance());return invoke(request,options);}
void setPosition(long position){currentBlockIndex=(int)(position>>outerInstance.blockBits);currentBlock=outerInstance.blocks[currentBlockIndex];currentBlockUpto=(int)(position & outerInstance.blockMask);this.position=position;}
{ long n; long s; int ptr = (int) s; s = Math.min(Available, Math.max(Max, (int) s)); return s; }
public BootstrapActionDetail(final BootstrapActionConfig bootstrapActionConfig){this._bootstrapActionConfig=bootstrapActionConfig;}
public void serialize(LittleEndianOutput out){out.writeShort(field_1_row);out.writeShort(field_2_col);out.writeShort(field_3_flags);out.writeShort(field_4_shapeid);out.writeShort(field_6_author.length());out.writeByte(field_5_hasMultibyte?(byte)0x01:(byte)0x00);if(field_5_hasMultibyte){StringUtil.putUnicodeLE(field_6_author,out);}else{StringUtil.putCompressedUnicode(field_6_author,out);}if(field_7_padding!=null){out.write(field_7_padding);} }
static int lastIndexOf(String string, String substr){return string.lastIndexOf(substr);}
public boolean add(E object) { return addLastImpl(object); }
void unsetSection(String section,String subsection){ConfigSnapshot src,res;while(true){src=state.get();res=src.unsetSection(section,subsection);if(state.compareAndSet(src,res))break;}}
String getTagName() { return tagName; }
void AddSubRecord(int index, SubRecord element){subrecords.add(index, element);}
public boolean remove(){synchronized(mutex){return object.remove();}}
@Override public TokenStream create(TokenStream input){return new DoubleMetaphoneFilter(input,maxCodeLength,inject);}
long Length() { return InCoreLength(); }
void setValue(boolean newValue){value=newValue;}
Pair<ContentSource, ContentSource> pair = new Pair<ContentSource, ContentSource>(newSource.contentSource, oldSource.contentSource);
int get(int i){if(i<=count){return entries[i];}throw new IndexOutOfBoundsException();}
public static final Method<CreateRepoRequest> PUT = new Method<>(MethodType.PUT, URI_PATTERN, " ", " ", " ", " ", " ");
boolean IsDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public void remove() {if(expectedModCount!=list.modCount) throw new java.util.ConcurrentModificationException(); if(lastLink==null) throw new java.lang.IllegalStateException(); if(previous_1.link==lastLink) {previous_1.link=next_1;next_1.previous_1=previous_1;--list._size;--pos;} else {previous_1.next_1=next_1;next_1.previous_1=previous_1;--list._size;} lastLink=null;++expectedModCount;}
public MergeShardsResponse mergeShards(MergeShardsRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(MergeShardsRequestMarshaller.getInstance());options.setResponseUnmarshaller(MergeShardsResponseUnmarshaller.getInstance());return invoke(request,options,MergeShardsResponse.class);}
public AllocateHostedConnectionResponse allocateHostedConnection(AllocateHostedConnectionRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(AllocateHostedConnectionRequestMarshaller.getInstance()); options.setResponseUnmarshaller(AllocateHostedConnectionResponseUnmarshaller.getInstance()); return invoke(request, options); }
public int getBeginIndex() { return start; }
return getTerms(query, (WeightedTerm[]) query.getTerms());
ByteBuffer.compact(); throw new java.nio.ReadOnlyBufferException();
public static void decode(int iterations,int valuesOffset,int[] values,int blocksOffset,byte[] blocks){for(int i=0;i<iterations;i++){int v0=values[valuesOffset++]&0x3F;int v1=values[valuesOffset++]&0x3F;int v2=values[valuesOffset++]&0x3F;int v3=values[valuesOffset++]&0x3F;blocks[blocksOffset++]=(byte)((v0<<2)|(v1>>4));blocks[blocksOffset++]=(byte)((v1<<4)|(v2>>2));blocks[blocksOffset++]=(byte)((v2<<6)|v3);}}
public String getHumanishName() { String s = getPath(); if (s == null) { throw new IllegalArgumentException("Path must be specified"); } if (s.endsWith("/") || s.endsWith("\\")) { s = s.substring(0, s.length() - 1); } String[] elements = s.split("[/\\\\]"); String result = elements[elements.length - 1]; if (result.endsWith(Constants.DOT_GIT_EXT)) { result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); } else if (result.endsWith(Constants.DOT_GIT)) { result = result.substring(0, result.length() - Constants.DOT_GIT.length()); } if (result.length() == 0) { throw new IllegalArgumentException("Cannot determine repository name from " + s); } return result; }
public DescribeNotebookInstanceLifecycleConfigResponse describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DescribeNotebookInstanceLifecycleConfigRequestMarshaller.INSTANCE);options.setResponseUnmarshaller(DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.INSTANCE);return invoke(request,options);}
public String getAccessKeySecret() { return accessSecret; }
public CreateVpnConnectionResult createVpnConnection(CreateVpnConnectionRequest request) {request = beforeClientExecution(request);return executeCreateVpnConnection(request);}
public DescribeVoicesResponse describeVoices(DescribeVoicesRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DescribeVoicesRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeVoicesResponseUnmarshaller.getInstance());return invoke(request,options);}
public ListMonitoringExecutionsResponse listMonitoringExecutions(ListMonitoringExecutionsRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(ListMonitoringExecutionsRequestMarshaller.getInstance());options.setResponseUnmarshaller(ListMonitoringExecutionsResponseUnmarshaller.getInstance());return invoke(request,options);}
public DescribeJobRequest(String jobId, String vaultName) {_jobId = jobId; _vaultName = vaultName;}
public EscherRecord getEscherRecord(int index){return escherRecords[index];}
public GetApisResponse getApis(GetApisRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetApisRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetApisResponseUnmarshaller.getInstance());return invoke(request,options);}
public DeleteSmsChannelResponse deleteSmsChannel(DeleteSmsChannelRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(DeleteSmsChannelRequestMarshaller.getInstance());options.setResponseUnmarshaller(DeleteSmsChannelResponseUnmarshaller.getInstance());return invoke(request, options);}
return trackingRefUpdate(getTrackingRefUpdate());
public void print() { boolean b; print(Boolean.toString(b)); }
public IQueryNode getChild() { return getChildren()[0]; }
int index(NotIgnoredFilter workdirTreeIndex);
field_1_formatFlags = in1.readShort();
GetThumbnailRequest request=new GetThumbnailRequest().setProtocol(Protocol.HTTPS).setParam1("").setParam2("").setParam3("").setParam4("");
public DescribeTransitGatewayVpcAttachmentsResult describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request){request=beforeClientExecution(request);return executeDescribeTransitGatewayVpcAttachments(request);}
public PutVoiceConnectorStreamingConfigurationResponse putVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance());options.setResponseUnmarshaller(PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance());return invoke(request,options);}
OrdRange result = prefixToOrdRange.get(prefix); if (result != null) { return result; }
public String toString() { if (startIndex >= 0 && symbol != null && symbol.getName() != null) { String text = ((org.antlr.v4.runtime.CharStream) inputStream).getText(org.antlr.v4.runtime.misc.Interval.of(startIndex, symbol.getSize())); String formatted = String.format(java.util.Locale.getDefault(), "%s", text); return org.antlr.v4.runtime.misc.Utils.escapeWhitespace(formatted, false); } return ""; }
public E peek() { return peekFirstImpl(); }
public CreateWorkspacesResult createWorkspaces(CreateWorkspacesRequest request) {request = beforeClientExecution(request);return executeCreateWorkspaces(request);}
public Object clone(){NumberFormatIndexRecord rec=new NumberFormatIndexRecord();rec.field_1_formatIndex=field_1_formatIndex;return rec;}
public DescribeRepositoriesResult describeRepositories(DescribeRepositoriesRequest request) { request = beforeClientExecution(request); return executeDescribeRepositories(request); }
public SparseIntArray(int initialCapacity){if(initialCapacity==0){mKeys=EmptyArray.INT;mValues=EmptyArray.INT;}else{initialCapacity=com.android.internal.util.ArrayUtils.idealIntArraySize(initialCapacity);mKeys=new int[initialCapacity];mValues=new int[initialCapacity];}mSize=0;}
public TokenStream create(TokenStream input){return new HyphenatedWordsFilter(input);}
public CreateDistributionWithTagsResponse createDistributionWithTags(CreateDistributionWithTagsRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(CreateDistributionWithTagsRequestMarshaller.getInstance());options.setResponseUnmarshaller(CreateDistributionWithTagsResponseUnmarshaller.getInstance());return invoke(request,options);}
throw new UnsupportedOperationException();
public DeleteWorkspaceImageResult deleteWorkspaceImage(DeleteWorkspaceImageRequest request) {request = beforeClientExecution(request);return executeDeleteWorkspaceImage(request);}
public static String toHex(long value){return toHex((int)value);}
private UpdateDistributionResponse updateDistribution(UpdateDistributionRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(UpdateDistributionRequestMarshaller.getInstance());options.setResponseUnmarshaller(UpdateDistributionResponseUnmarshaller.getInstance());return invoke(request,options);}
protected HSSFColor getColor(short index){if(index==HSSFColor.AUTOMATIC.index){return HSSFColor.AUTOMATIC.getInstance();}else{byte[] b=palette.getColor(index);if(b!=null){return new HSSFColor.CustomColor(index,b[0],b[1],b[2]);}return null;}}
public ValueEval evaluate(ValueEval[] operands, int srcRow, int srcCol) {throw new NotImplementedFunctionException();}
public void serialize(ILittleEndianOutput out1){out1.writeShort((short)field_1_number_crn_records);out1.writeShort((short)field_2_sheet_table_index);}
public DescribeDBEngineVersionsResponse describeDBEngineVersions() { return describeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
public FormatRun(short fontIndex, short character) { this._fontIndex = fontIndex; this._character = character; }
public static byte[] toBigEndianUtf16Bytes(char[] chars,int offset,int length){byte[] result=new byte[length*2];int end=offset+length;int resultIndex=0;for(int i=offset;i<end;++i){char ch=chars[i];result[resultIndex++]=(byte)(ch>>8);result[resultIndex++]=(byte)ch;}return result;}
public UploadArchiveResult uploadArchive(UploadArchiveRequest request) {request = beforeClientExecution(request);return executeUploadArchive(request);}
public List<Token> getHiddenTokensToLeft(int tokenIndex) {return getHiddenTokensToLeft(tokenIndex - 1);}
@Override public boolean equals(Object obj){if(this==obj){return true;}if(obj==null){return false;}if(getClass()!=obj.getClass()){return false;}AutomatonQuery other=(AutomatonQuery)obj;if(m_term!=null?!m_term.equals(other.m_term):other.m_term!=null){return false;}if(m_compiled!=null?!m_compiled.equals(other.m_compiled):other.m_compiled!=null){return false;}return true;}
public SpanQuery makeSpanClause(WeightedSpanQuery wsq){List<SpanQuery> spanQueries=new ArrayList<SpanQuery>();for(SpanQuery weightBySpanQuery:wsq)spanQueries.add(weightBySpanQuery);return spanQueries.size()==1?spanQueries.get(0):new SpanOrQuery(spanQueries.toArray(new SpanQuery[0]));}
StashCreate StashCreateCommand() { return new StashCreateCommand(); }
FieldInfo ret = byName.get(fieldName); return ret;
public DescribeEventSourceResponse describeEventSource(DescribeEventSourceRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DescribeEventSourceRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeEventSourceResponseUnmarshaller.getInstance());return invoke(request,options);}
public GetDocumentAnalysisResponse getDocumentAnalysis(GetDocumentAnalysisRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetDocumentAnalysisRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetDocumentAnalysisResponseUnmarshaller.getInstance());return invoke(request,options);}
public CancelUpdateStackResponse cancelUpdateStack(CancelUpdateStackRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(CancelUpdateStackRequestMarshaller.getInstance());options.setResponseUnmarshaller(CancelUpdateStackResponseUnmarshaller.getInstance());return invoke(request, options);}
public ModifyLoadBalancerAttributesResponse modifyLoadBalancerAttributes(final ModifyLoadBalancerAttributesRequest request) {final InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(ModifyLoadBalancerAttributesRequestMarshaller.getInstance());options.setResponseUnmarshaller(ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance());return invoke(request, options);}
public SetInstanceProtectionResult setInstanceProtection(SetInstanceProtectionRequest request) {request = beforeClientExecution(request);return executeSetInstanceProtection(request);}
public ModifyDBProxyResponse modifyDBProxy( ModifyDBProxyRequest request ) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller( ModifyDBProxyRequestMarshaller.getInstance() ); options.setResponseUnmarshaller( ModifyDBProxyResponseUnmarshaller.getInstance() ); return invoke( request , options ); }
void add(char[] chars,int offset,int len,int posLength,int endOffset){if(posLengths==null){posLengths=new int[8];endOffsets=new int[8];outputs=new CharsRef[8];}if(count==posLengths.length){posLengths=ArrayUtil.grow(posLengths,count+1);}if(count==endOffsets.length){endOffsets=ArrayUtil.grow(endOffsets,count+1);}if(count==outputs.length){outputs=new CharsRef[ArrayUtil.oversize(count+1,RamUsageEstimator.NUM_BYTES_OBJECT_REF)];}posLengths[count]=posLength;endOffsets[count]=endOffset;CharsRef output=new CharsRef();CopyChars(chars,offset,len,output);outputs[count++]=output;}
new FetchLibrariesRequest(Protocol, null, null, null, HTTPS.ProtocolType, " ", " ", " ", " ", " ");
public boolean exists() { return objects.exists(); }
} {  ; FilterOutputStream out ) java ( out . . .
new ScaleClusterRequest(Method.UriPattern, MethodType.PUT, " ", " ", " ", " ", " ");
public static DataValidationConstraint createTimeConstraint(int operatorType, String formula1, String formula2) { return DVConstraint.createTimeConstraint(operatorType, formula1, formula2); }
public ListObjectParentPathsResponse listObjectParentPaths(ListObjectParentPathsRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(ListObjectParentPathsRequestMarshaller.getInstance());options.setResponseUnmarshaller(ListObjectParentPathsResponseUnmarshaller.getInstance());return invoke(request,options,ListObjectParentPathsResponse.class);}
public DescribeCacheSubnetGroupsResponse describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DescribeCacheSubnetGroupsRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance());return invoke(request,options);}
void SetSharedFormula(boolean flag){sharedFormula.SetShortBoolean(field_5_options, flag);}
public boolean IsReuseObjects(){return reuseObjects;}
public IErrorNode addErrorNode(IToken badToken){ErrorNodeImpl t=new ErrorNodeImpl(badToken);Parent.addChild(t);return t;}
public LatvianStemFilterFactory(Map<String, String> args){super(args);if(args.size()>0)throw new IllegalArgumentException("Unknown parameters: "+args);}
public RemoveSourceIdentifierFromSubscriptionResult removeSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) {request = beforeClientExecution(request);return executeRemoveSourceIdentifierFromSubscription(request);}
public static TokenFilterFactory forName(String name, Map<String,String> args, ClassLoader loader){return (TokenFilterFactory)newInstance(name,args,loader);}
public AddAlbumPhotosResult addAlbumPhotos(AddAlbumPhotosRequest request){request=beforeClientExecution(request);return executeAddAlbumPhotos(request);}
public GetThreatIntelSetResponse getThreatIntelSet(GetThreatIntelSetRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetThreatIntelSetRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetThreatIntelSetResponseUnmarshaller.getInstance());return invoke(request,options);}
public TreeFilter clone() { return new Binary.AndTreeFilter(Clone.a(), Clone.b()); }
public boolean equals(Object o) {return o instanceof Object;}
protected boolean hasArray() { return hasArray; }
public UpdateContributorInsightsResponse UpdateContributorInsights(UpdateContributorInsightsRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(UpdateContributorInsightsRequestMarshaller.getInstance());options.setResponseUnmarshaller(UpdateContributorInsightsResponseUnmarshaller.getInstance());return invoke(request,options);}
void unwriteProtectWorkbook(){records.remove(fileShare);records.remove(writeProtect);fileShare=null;writeProtect=null;}
public SolrSynonymParser(boolean dedup, boolean expand, Analyzer analyzer){super(dedup, expand, analyzer);}
public RequestSpotInstancesResult requestSpotInstances(RequestSpotInstancesRequest request){request=beforeClientExecution(request);return executeRequestSpotInstances(request);}
public byte getObjectData(byte index){return findObjectRecord(objectData[index]);}
InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetContactAttributesRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetContactAttributesResponseUnmarshaller.getInstance());return invoke(request,options,GetContactAttributesRequest.class,GetContactAttributesResponse.class);
public String toString() {return getKey() + " " + getValue();}
public ListTextTranslationJobsResult listTextTranslationJobs(ListTextTranslationJobsRequest request) {request = beforeClientExecution(request);return executeListTextTranslationJobs(request);}
public GetContactMethodsResponse getContactMethods(GetContactMethodsRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetContactMethodsRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetContactMethodsResponseUnmarshaller.getInstance());return invoke(request,options);}
public short lookupIndexByName(String name){FunctionMetadata fd=GetInstance().getFunctionByNameInternal(name);if(fd==null){return (short)-1;}return fd.getIndex();}
public DescribeAnomalyDetectorsResponse describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DescribeAnomalyDetectorsRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeAnomalyDetectorsResponseUnmarshaller.getInstance());return invoke(request,options);}
public String insertId(String message, ObjectId changeId){return changeId.toString();}
long getObjectSize(AnyObjectId objectId,int typeHint){long sz=db.getObjectSize(objectId);if(sz<0){throw new MissingObjectException(objectId.copy()," ",db.getObjectSize(objectId.copy()));}if(typeHint==OBJ_ANY){throw new MissingObjectException(objectId.copy()," ",db.getObjectSize(objectId));}return sz;}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ImportInstallationMediaRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ImportInstallationMediaResponseUnmarshaller.getInstance()); return invoke(request, options);
public PutLifecycleEventHookExecutionStatusResponse putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance());options.setResponseUnmarshaller(PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance());return Invoke.invoke(request,options);}
field_1_value = in1.readDouble();
public GetFieldLevelEncryptionConfigResponse getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance());return invoke(request, options);}
public DescribeDetectorResponse describeDetector(DescribeDetectorRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DescribeDetectorRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeDetectorResponseUnmarshaller.getInstance());return invoke(request,options);}
public ReportInstanceStatusResponse reportInstanceStatus(ReportInstanceStatusRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(ReportInstanceStatusRequestMarshaller.getInstance());options.setResponseUnmarshaller(ReportInstanceStatusResponseUnmarshaller.getInstance());return invoke(request,options);}
public DeleteAlarmResponse deleteAlarm(DeleteAlarmRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DeleteAlarmRequestMarshaller.getInstance());options.setResponseUnmarshaller(DeleteAlarmResponseUnmarshaller.getInstance());return invoke(request,options);}
public TokenStream create(TokenStream input) { return new PortugueseStemFilter(input); }
public FtCblsSubRecord() { reserved = new byte[ENCODED_SIZE]; }
public boolean remove() { synchronized (mutex) { return c.remove(); } }
public GetDedicatedIpResponse getDedicatedIp(GetDedicatedIpRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetDedicatedIpRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetDedicatedIpResponseUnmarshaller.getInstance()); return invoke(request, options); }
public String toString() { return " " + precedence; }
public ListStreamProcessorsResult listStreamProcessors(ListStreamProcessorsRequest request) {request = beforeClientExecution(request);return executeListStreamProcessors(request);}
public DeleteLoadBalancerPolicyRequest(String loadBalancerName,String policyName){this.loadBalancerName=loadBalancerName;this.policyName=policyName;}
int options(WindowProtectRecord _options);
} { ; ; UnbufferedCharStream data = new UnbufferedCharStream(0, n, bufferSize); int[] buffer = new int[bufferSize];
public GetOperationsResponse getOperations(final GetOperationsRequest request){final InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetOperationsRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetOperationsResponseUnmarshaller.getInstance());return invoke(request,options);}
void CopyRawTo(byte[] b, int o){EncodeInt32.NB(b, o + 4);EncodeInt32.NB(b, o + 8);EncodeInt32.NB(b, o + 12);EncodeInt32.NB(b, o + 16);}
public WindowOneRecord(RecordInputStream in1){this.field_1_h_hold=in1.readShort();this.field_2_v_hold=in1.readShort();this.field_3_width=in1.readShort();this.field_4_height=in1.readShort();this.field_5_options=in1.readShort();this.field_6_active_sheet=in1.readShort();this.field_7_first_visible_tab=in1.readShort();this.field_8_num_selected_tabs=in1.readShort();this.field_9_tab_width_ratio=in1.readShort();}
public StopWorkspacesResponse stopWorkspaces(StopWorkspacesRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(StopWorkspacesRequestMarshaller.getInstance());options.setResponseUnmarshaller(StopWorkspacesResponseUnmarshaller.getInstance());return invoke(request,options);}
public void close() throws IOException { if (isOpen()) { try { dump(); } finally { try { channel.truncate(); } finally { channel.close(); fos.close(); } } } }
public DescribeMatchmakingRuleSetsResult describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) {request = beforeClientExecution(request);return executeDescribeMatchmakingRuleSets(request);}
public static String getPronunciation(int wordId,char[] surface,int off,int len){return null;}
String getPath(){return pathStr;}
static double devsq(double[] v){if(v==null||v.length<1)return Double.NaN;int n=v.length;double m=0;for(int i=0;i<n;++i)m+=v[i];m/=n;double r=0;for(int i=0;i<n;++i){double d=v[i]-m;r+=d*d;}return r;}
public DescribeResizeResponse describeResize(DescribeResizeRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DescribeResizeRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeResizeResponseUnmarshaller.getInstance());return invoke(request,options);}
public boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
int end() { return end(); }
public void traverse(ICellHandler handler, Sheet sheet, int firstRow, int lastRow, int firstColumn, int lastColumn, boolean traverseEmptyCells){int width=lastColumn-firstColumn+1;for(int rowNumber=firstRow;rowNumber<=lastRow;++rowNumber){Row currentRow=sheet.getRow(rowNumber);if(currentRow==null){if(!traverseEmptyCells)continue;SimpleCellWalkContext ctx=new SimpleCellWalkContext(firstRow,lastRow,firstColumn,lastColumn,rowNumber,-1,width*(rowNumber-firstRow));handler.onCell(null,ctx);continue;}for(int colNumber=firstColumn;colNumber<=lastColumn;++colNumber){Cell currentCell=currentRow.getCell(colNumber);if(currentCell==null||currentCell.isEmpty()){if(!traverseEmptyCells)continue;}SimpleCellWalkContext ctx=new SimpleCellWalkContext(firstRow,lastRow,firstColumn,lastColumn,rowNumber,colNumber,width*(rowNumber-firstRow)+(colNumber-firstColumn));handler.onCell(currentCell,ctx);}}}
int GetReadIndex() {return _ReadIndex;}
public int compareTo(ScoreTerm other){if(this==other)return 0;if(boost==other.boost)return term.bytesEquals(other.term)?0:term.compareTo(other.term);return Float.compare(other.boost,boost);}
public int normalize(char[] s,int len){for(int i=0;i<len;i++){switch(s[i]){case HAMZA_ABOVE:len=StemmerUtil.delete(s,i,len);i--;break;case HEH_GOAL:case HEH_YEH:s[i]=HEH;break;case KEHEH:s[i]=KAF;break;case YEH_BARREE:case FARSI_YEH:s[i]=YEH;break;default:break;}}return len;}
void Serialize(ILittleEndianOutput out1) { out1.WriteShort(); }
} {  ; DiagnosticErrorListener exactOnly ) exactOnly boolean ( exactOnly .
public KeySchemaElement(KeyType keyType, String attributeName) { this._keyType = keyType; this._attributeName = attributeName; }
public GetAssignmentResponse getAssignment(GetAssignmentRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetAssignmentRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetAssignmentResponseUnmarshaller.getInstance()); return invoke(request, options); }
boolean hasObject() { return findOffset(anyObjectId) != -1; }
public GroupingSearch setAllGroups(boolean allGroups){this.allGroups=allGroups;return this;}
void SetMultiValued(String dimName, boolean v) { synchronized (this) { DimConfig fieldType = fieldTypes.get(dimName); if (fieldType == null) { fieldType = new DimConfig(); fieldTypes.put(dimName, fieldType); } fieldType.isMultiValued = v; } }
public int GetCellsVal(){int size=0;for(char c:cells.keySet()){if(Cell>=0){cmd.e[size++]=At();}}return size;}
public void deleteVoiceConnector(DeleteVoiceConnectorRequest request) {request = beforeClientExecution(request);executeDeleteVoiceConnector(request);}
public DeleteLifecyclePolicyResponse deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DeleteLifecyclePolicyRequestMarshaller.getInstance());options.setResponseUnmarshaller(DeleteLifecyclePolicyResponseUnmarshaller.getInstance());return invoke(request,options);}
