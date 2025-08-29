void serialize(ILittleEndianOutput out1) { out1.writeShort(); }
public void addAll(NGit.Util.BlockList<T> src) { if (src.size() == 0) { return; } int srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; ++srcDirIdx) { addAll(src.directory[srcDirIdx], 0, src.directory[srcDirIdx].length); } if (src.tailBlkIdx != 0) { addAll(src.tailBlock, 0, src.tailBlkIdx); } }
public void writeByte(byte b){if(upto==blockSize){if(currentBlock!=null){blocks.add(currentBlock);}blockEnd.add(upto);currentBlock=new byte[blockSize];upto=0;}currentBlock[upto++]=b;}
public ObjectId getObjectId() { return objectId; }
return invoke(request,new InvokeOptions().withRequestMarshaller(DeleteDomainEntryRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.getInstance()));
public long ramBytesUsed() { return fst == null ? 0 : fst.getSizeInBytes(); }
String getFullMessage(byte[] raw){int buffer=RawParseUtils.tagMessage(raw,0);if(buffer<0)return"";Charset enc=RawParseUtils.parseEncoding(raw);return RawParseUtils.decode(raw,buffer,raw.length,enc);}
public POIFSFileSystem() { HeaderBlock headerBlock = new HeaderBlock(); _property_table = new PropertyTable(); _documents = new ArrayList(); _root = null; }
void init(int address){assert Buffers.pool[address >> ByteBlockPool.BYTE_BLOCK_SHIFT] != null && (address & ByteBlockPool.BYTE_BLOCK_MASK) < slice.length && address < upto;}
public SubmoduleAddCommand setPath(String path){this.path=path;return this;}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListIngestionsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ListIngestionsResponseUnmarshaller.getInstance()); return Invoke.<ListIngestionsResponse>invoke(request, options);
public QueryParserTokenManager(ICharStream stream, int lexState) { this(stream); SwitchTo(lexState); }
InvokeOptions options = new InvokeOptions();options.RequestMarshaller = GetShardIteratorRequestMarshaller.Instance;options.ResponseUnmarshaller = GetShardIteratorResponseUnmarshaller.Instance;return Invoke<GetShardIteratorResponse>(request, options);
public ModifyStrategyRequest(){super(" "," "," "," "," ");setMethod(MethodType.POST);}
boolean ready() { synchronized (lock) { if (in == null) { throw new IOException(" "); } try { return bytes.hasRemaining() || in.available() > 0; } catch (IOException e) { return false; } } }
EscherOptRecord GetOptRecord() { return _optRecord; }
public int read(byte[] buffer,int offset,int length){synchronized(this){if(buffer==null){throw new NullPointerException();}java.util.Arrays.checkOffsetAndCount(buffer.length,offset,length);if(length==0){return 0;}int copylen=(count-pos)<length?(count-pos):length;for(int i=0;i<copylen;++i){buffer[offset+i]=(byte)this.buffer[pos+i];}pos+=copylen;return copylen;}}
OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) { sentenceOp.sentenceOp; }
public void print(String str){System.out.print(str!=null?str:Sharpen.StringHelper.GetValueOf((Object)str));}
public NotImplementedFunctionException(String functionName, NotImplementedException cause){super(functionName,cause);}
public V next() { return nextEntry().value; }
void ReadBytes(byte[] b,int offset,int len,boolean useBuffer){if(len<0)throw new IllegalArgumentException();int available=bufferLength-bufferPosition;if(available<=0){if(useBuffer&&bufferSize<len)Refill();if(len<bufferLength){System.arraycopy(buffer,bufferPosition,b,offset,len);bufferPosition+=len;}else{throw new EOFException("after "+(len+bufferPosition+bufferStart)+" > "+Length);}}else{System.arraycopy(buffer,bufferPosition,b,offset,len);bufferPosition+=len;}}
final InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(TagQueueRequestMarshaller.getInstance());options.setResponseUnmarshaller(TagQueueResponseUnmarshaller.getInstance());return invoke(TagQueueResponse.class, request, options);
void remove() { throw new UnsupportedOperationException(); }
return invoke(request, new InvokeOptions().withRequestMarshaller(ModifyCacheSubnetGroupRequestMarshaller.getInstance()).withResponseUnmarshaller(ModifyCacheSubnetGroupResponseUnmarshaller.getInstance()));
void setParams(String params){java.util.StringTokenizer st=new java.util.StringTokenizer(params," ");if(st.hasMoreTokens())culture=st.nextToken();if(st.hasMoreTokens())culture+=" "+st.nextToken();if(st.hasMoreTokens())st.nextToken();}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteDocumentationVersionRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteDocumentationVersionResponseUnmarshaller.getInstance()); return invoke(DeleteDocumentationVersionResponse.class, request, options);
public boolean equals(Object obj){if(!(obj instanceof FacetLabel)){return false;}FacetLabel other=(FacetLabel)obj;if(Length!=other.Length){return false;}for(int i=Length-1;i>=0;i--){if(!Components[i].equals(other.Components[i])){return false;}}return true;}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetInstanceAccessDetailsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetInstanceAccessDetailsResponseUnmarshaller.getInstance()); return invoke(request, options);
HSSFPolygon shape = (HSSFPolygon) HSSFPolygon.CreatePolygon(new HSSFChildAnchor(anchor)); shape.setAnchor(anchor); shapes.add(shape); onCreate(shape); return shape;
public String getSheetName(int sheetIndex){return getBoundSheetRec(sheetIndex).Sheetname;}
return invoke(request,new InvokeOptions().setRequestMarshaller(GetDashboardRequestMarshaller.getInstance()).setResponseUnmarshaller(GetDashboardResponseUnmarshaller.getInstance()));
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(AssociateSigninDelegateGroupsWithAccountRequestMarshaller.getInstance()); options.setResponseUnmarshaller(AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.getInstance()); return invoke(request, options);
void addMultipleBlanks(MulBlankRecord mbr){for(int j=0;j<mbr.getNumColumns();++j){BlankRecord br=new BlankRecord();br.setColumn(j+mbr.getFirstColumn());br.setRow(mbr.getRow());br.setXFIndex(mbr.getXFIndex());insertCell(getXFAt(br));}}
public static String quote(String s){int len=s.length();if(len==0)return "''";StringBuilder sb=new StringBuilder(len+2);sb.append('\'');int i=0;while(true){int j=s.indexOf('\'',i);if(j<0)break;sb.append(s,i,j).append("'\\''");i=j+1;}sb.append(s,i,len);sb.append('\'');return sb.toString();}
public java.nio.ByteBuffer putInt(int value){throw new java.nio.ReadOnlyBufferException();}
byte _reserved2Byte=0;short _reserved1Short=0;int _reserved0Int=0;Object[] _arrayValues=vv;Object[][] values2d=new Object[_nRows][_nColumns];for(int r=0;r<nRows;++r){for(int c=0;c<nColumns;++c){values2d[r][c]=rowData[GetValueIndex(vv)];}}
public GetIceServerConfigResponse getIceServerConfig(GetIceServerConfigRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetIceServerConfigRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetIceServerConfigResponseUnmarshaller.getInstance());return invoke(request,options);}
public String toString() { StringBuilder sb = new StringBuilder(); sb.append(getClass().getName()).append(" ").append(getValueAsString()).append(" "); return sb.toString(); }
String toString(String field) { return " " + _parentQuery + " "; }
void IncRef() { refCount.incrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResponse updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(UpdateConfigurationSetSendingEnabledRequestMarshaller.getInstance());options.setResponseUnmarshaller(UpdateConfigurationSetSendingEnabledResponseUnmarshaller.getInstance());return invoke(request,options);}
int getNextXBATChainOffset() {return getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE;}
void multiplyByPowerOfTen(Integer pow10){TenPower tp=TenPower.getInstance(Math.abs(pow10));if(pow10<0){mulShift(tp._divisor,tp._divisorShift);}else{mulShift(tp._multiplicand,tp._multiplierShift);}}
public String toString() { StringBuilder builder = new StringBuilder(); int length = Length; for (int i = 0; i < length; ++i) { builder.append(File.separatorChar); builder.append(getComponent(i)); if (i < length - 1) builder.append(File.separatorChar); } return builder.toString(); }
void withFetcher(ECSMetadataServiceCredentialsFetcher fetcher){fetcher.setRoleName();}
void SetProgressMonitor(ProgressMonitor pm){this.progressMonitor = pm;}
void reset() { if (!first) { ptr = 0; if (!eof) { parseEntry(); } } }
public E previous() { if (iterator.previousIndex() >= start) { return iterator.previous(); } throw new java.util.NoSuchElementException(); }
public String getNewPrefix() { return newPrefix; }
int indexOfValue(int value){for(int i=0;i<mSize;++i){if(mValues[i]==value){return i;}}return -1;}
public static List<CharsRef> uniqueStems(char[] word,int length){List<CharsRef> stems=stem(word,length);if(stems.size()<2){return stems;}CharArraySet terms=new CharArraySet(618,true);List<CharsRef> deduped=new ArrayList<>();for(CharsRef s:stems){if(!terms.contains(s)){terms.add(s);deduped.add(s);}}return deduped;}
return invoke(GetGatewayResponsesResponse.class, request, new InvokeOptions().setRequestMarshaller(GetGatewayResponsesRequestMarshaller.getInstance()).setResponseUnmarshaller(GetGatewayResponsesResponseUnmarshaller.getInstance()));
public void setPosition(long position){currentBlockIndex=(int)(position>>blockBits.outerInstance);currentBlock=blocks.outerInstance[currentBlockIndex];currentBlockUpto=(int)(position&blockMask.outerInstance);}
public long skip(long n){int s=(int)Math.min(available(),Math.max(n,0));ptr+=s;return s;}
BootstrapActionDetail bootstrapActionConfig = new BootstrapActionDetail(_bootstrapActionConfig);
void serialize(ILittleEndianOutput out1){out1.writeShort(field_6_author.length());out1.writeShort(field_5_hasMultibyte?(short)0x01:(short)0x00);if(field_5_hasMultibyte){StringUtil.putUnicodeLE(field_6_author,out1);}else{StringUtil.putCompressedUnicode(field_6_author,out1);}if(field_7_padding!=null){out1.writeByte(Integer.parseInt(field_7_padding,10));}}
int lastIndexOf(String string){return string.lastIndexOf(',');}
boolean add(E object){return addLastImpl();}
public void unsetSection(String section,String subsection){ConfigSnapshot src,res;do{src=state.get();res=src.unsetSection(section,subsection);}while(!state.compareAndSet(src,res));}
String getTagName() { return tagName; }
public final void addSubRecord(int index, SubRecord element) { subrecords.add(index, element); }
boolean remove(Object object){synchronized(mutex){return c.remove(object);}}
public TokenStream create(TokenStream input){return new DoubleMetaphoneFilter(input);}
public long length() { return inCoreLength(); }
void setValue(boolean newValue) { value = newValue; }
public Pair(ContentSource oldSource, ContentSource newSource){this.oldSource=oldSource;this.newSource=newSource;}
int get(int i) {if (count <= i) throw Sharpen.Extensions.CreateIndexOutOfRangeException();return entries[i];}
CreateRepoRequest request = new CreateRepoRequest(MethodType.PUT, new UriPattern(" ", " ", " ", " ", " "));
public boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public void remove() { if(list.modCount==expectedModCount){ if(lastLink!=null){ java.util.LinkedList.Link<ET> previous_1=lastLink.previous_1, next_1=lastLink.next_1; if(lastLink==link){ pos--; } previous_1.next_1=next_1; next_1.previous_1=previous_1; lastLink=null; expectedModCount++; list._size--; list.modCount++; } else { throw new IllegalStateException(); } } else { throw new java.util.ConcurrentModificationException(); } }
return invoke(request, new InvokeOptions().withRequestMarshaller(MergeShardsRequestMarshaller.getInstance()).withResponseUnmarshaller(MergeShardsResponseUnmarshaller.getInstance()), MergeShardsResponse.class);
return invoke(request, options, AllocateHostedConnectionRequestMarshaller.getInstance(), AllocateHostedConnectionResponseUnmarshaller.getInstance());
int getBeginIndex() { return start; }
WeightedTerm[] GetTerms(Query query) {return GetTerms(query);}
public final java.nio.ByteBuffer compact() { throw new java.nio.ReadOnlyBufferException(); }
void decode(byte[] blocks,int blocksOffset,int[] values,int valuesOffset,int iterations){for(int i=0;i<iterations;i++){int byte0=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=(byte0>>2);int byte1=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=((byte0&3)<<4)|(byte1>>4);int byte2=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=((byte1&15)<<2)|(byte2>>6);values[valuesOffset++]=byte2&63;}}
public static String getHumanishName(String s) { if (s == null || s.equals("")) { throw new IllegalArgumentException(); } String[] elements = s.split(File.separator); if (elements.length == 0) { throw new IllegalArgumentException(); } String result = elements[elements.length - 1]; if (Constants.DOT_GIT.equals(result)) { result = elements[elements.length - 2]; } if (result.endsWith(Constants.DOT_GIT_EXT)) { result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); } return result; }
public DescribeNotebookInstanceLifecycleConfigResponse DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request){InvokeOptions options=new InvokeOptions();options.RequestMarshaller=DescribeNotebookInstanceLifecycleConfigRequestMarshaller.INSTANCE;options.ResponseUnmarshaller=DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.INSTANCE;return Invoke.invoke(request,options);}
public String getAccessKeySecret() { return accessSecret; }
return invoke<CreateVpnConnectionResponse>(request, new InvokeOptions() {{ RequestMarshaller = CreateVpnConnectionRequestMarshaller.Instance; ResponseUnmarshaller = CreateVpnConnectionResponseUnmarshaller.Instance; }});
InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DescribeVoicesRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeVoicesResponseUnmarshaller.getInstance());return invoke(request,options,DescribeVoicesResponse.class);
public ListMonitoringExecutionsResponse listMonitoringExecutions(ListMonitoringExecutionsRequest request){return invoke(request,new InvokeOptions().withRequestMarshaller(ListMonitoringExecutionsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListMonitoringExecutionsResponseUnmarshaller.getInstance()));}
public DescribeJobRequest(String jobId, String vaultName) { this.jobId = jobId; this.vaultName = vaultName; }
public EscherRecord getEscherRecord(int index){return escherRecords[index];}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetApisRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetApisResponseUnmarshaller.getInstance()); return invoke(GetApisResponse.class, request, options);
return invoke(request,new InvokeOptions().withRequestMarshaller(DeleteSmsChannelRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteSmsChannelResponseUnmarshaller.getInstance()),DeleteSmsChannelResponse.class);
TrackingRefUpdate getTrackingRefUpdate() { return trackingRefUpdate; }
void print(boolean b){print(Boolean.toString(b));}
public IQueryNode getChild() { return getChildren().get(0); }
void NotIgnoredFilter(int workdirTreeIndex){index.workdirTreeIndex;}
public AreaRecord(RecordInputStream in1){this.field_1_formatFlags=in1.readShort();}
@GetThumbnailRequest(" ", " ", " ", " ", " ") public HTTPS.ProtocolType getProtocol(){return protocol;}
return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeTransitGatewayVpcAttachmentsRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance()));
public PutVoiceConnectorStreamingConfigurationResult putVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request){request=beforeClientExecution(request);return executePutVoiceConnectorStreamingConfiguration(request);}
public OrdRange GetOrdRange(String dim){OrdRange result=prefixToOrdRange.get(dim);return result;}
@Override public String toString(){String symbol="";if(startIndex>=0&&startIndex<inputStream.size()){symbol=inputStream.getText(Interval.of(startIndex,startIndex));symbol=Utils.escapeWhitespace(symbol,false);}return String.format(java.util.Locale.getDefault(),"%s('%s')",LexerNoViableAltException.class.getSimpleName(),symbol);}
public E peek() { return peekFirstImpl(); }
public CreateWorkspacesResult createWorkspaces(CreateWorkspacesRequest request) {request = beforeClientExecution(request); return executeCreateWorkspaces(request);}
public Object clone() { NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = field_1_formatIndex; return rec; }
public DescribeRepositoriesResult describeRepositories(DescribeRepositoriesRequest request) {request = beforeClientExecution(request);return executeDescribeRepositories(request);}
public SparseIntArray(int initialCapacity){if(initialCapacity==0){mKeys=EmptyArray.INT;mValues=EmptyArray.INT;}else{initialCapacity=android.util.ArrayUtils.idealIntArraySize(initialCapacity);mKeys=new int[initialCapacity];mValues=new int[initialCapacity];}mSize=0;}
return new HyphenatedWordsFilter(input);
public CreateDistributionWithTagsResponse createDistributionWithTags(CreateDistributionWithTagsRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(CreateDistributionWithTagsRequestMarshaller.getInstance());options.setResponseUnmarshaller(CreateDistributionWithTagsResponseUnmarshaller.getInstance());return invoke(request,options);}
RandomAccessFile(String fileName,String mode){this(new java.io.File(fileName),mode);throw new UnsupportedOperationException();}
public DeleteWorkspaceImageResponse deleteWorkspaceImage(DeleteWorkspaceImageRequest request) {InvokeOptions options = new InvokeOptions();options.requestMarshaller = DeleteWorkspaceImageRequestMarshaller.instance;options.responseUnmarshaller = DeleteWorkspaceImageResponseUnmarshaller.instance;return invoke(request, options);}
String ToHex(long value){return ToHex(value);}
InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(UpdateDistributionRequestMarshaller.getInstance());options.setResponseUnmarshaller(UpdateDistributionResponseUnmarshaller.getInstance());return invoke(request,options,UpdateDistributionResponse.class);
public HSSFColor getColor(short index) { if(index == HSSFColor.Automatic.Index) return HSSFColor.Automatic.GetInstance(); else { byte[] b = palette.getColor(index); if(b != null) return new CustomColor(b); } return null; }
public ValueEval evaluate(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(); }
public void serialize(ILittleEndianOutput out1) { out1.writeShort(field_1_number_crn_records); out1.writeShort(field_2_sheet_table_index); }
public DescribeDBEngineVersionsResponse DescribeDBEngineVersions() { return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
public FormatRun(short character, short fontIndex){this._character = character; this._fontIndex = fontIndex;}
public static byte[] toBigEndianUtf16Bytes(char[] chars,int offset,int length){byte[] result=new byte[2*length];int end=offset+length;int resultIndex=0;for(int i=offset;i<end;++i){char ch=chars[i];result[resultIndex++]=(byte)(ch>>8);result[resultIndex++]=(byte)ch;}return result;}
public UploadArchiveResult uploadArchive(UploadArchiveRequest request) {request = beforeClientExecution(request);return executeUploadArchive(request);}
public List<IToken> getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex, -1); }
public boolean equals(Object obj){if(this==obj)return true;if(obj==null)return false;if(getClass()!=obj.getClass())return false;AutomatonQuery other=(AutomatonQuery)obj;if(m_compiled!=null?!m_compiled.equals(other.m_compiled):other.m_compiled!=null)return false;if(m_term!=null?!m_term.equals(other.m_term):other.m_term!=null)return false;return true;}
List<SpanQuery> spanQueries = new ArrayList<SpanQuery>(); for (Map.Entry<?, ?> wsq : weightBySpanQuery.entrySet()) { spanQueries.add(MakeSpanClause(wsq.getKey(), wsq.getValue().getBoost())); } if (spanQueries.size() == 1) return spanQueries.get(0); else return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0]));
public StashCreateCommand stashCreate() { return new StashCreateCommand(); }
FieldInfo fieldInfo = byName.get(fieldName); if (fieldInfo != null) { return fieldInfo; } return null;
public DescribeEventSourceResponse describeEventSource(DescribeEventSourceRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DescribeEventSourceRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeEventSourceResponseUnmarshaller.getInstance());return invoke(request,options);}
public GetDocumentAnalysisResponse getDocumentAnalysis(GetDocumentAnalysisRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetDocumentAnalysisRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetDocumentAnalysisResponseUnmarshaller.getInstance());return invoke(request,options,GetDocumentAnalysisResponse.class);}
public CancelUpdateStackResponse cancelUpdateStack(CancelUpdateStackRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(CancelUpdateStackRequestMarshaller.getInstance());options.setResponseUnmarshaller(CancelUpdateStackResponseUnmarshaller.getInstance());return invoke(request,options);}
InvokeOptions options = new InvokeOptions();options.requestMarshaller = ModifyLoadBalancerAttributesRequestMarshaller.getInstance();options.responseUnmarshaller = ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance();return invoke(request, options);
public SetInstanceProtectionResult setInstanceProtection(SetInstanceProtectionRequest request) {request = beforeClientExecution(request);return executeSetInstanceProtection(request);}
public ModifyDBProxyResponse modifyDBProxy(ModifyDBProxyRequest request){InvokeOptions options=new InvokeOptions();options.requestMarshaller=ModifyDBProxyRequestMarshaller.Instance;options.responseUnmarshaller=ModifyDBProxyResponseUnmarshaller.Instance;return invoke(request,options);}
public void Add(char[] output,int offset,int len,int endOffset,int posLength){if(outputs==null){outputs=new CharsRef[ArrayUtil.oversize(count+1,RamUsageEstimator.NUM_BYTES_OBJECT_REF)];endOffsets=new int[ArrayUtil.oversize(count+1,RamUsageEstimator.NUM_BYTES_INT32)];posLengths=new int[ArrayUtil.oversize(count+1,RamUsageEstimator.NUM_BYTES_INT32)];}else{if(outputs.length==count)outputs=java.util.Arrays.copyOf(outputs,ArrayUtil.oversize(count+1,RamUsageEstimator.NUM_BYTES_OBJECT_REF));if(endOffsets.length==count)endOffsets=java.util.Arrays.copyOf(endOffsets,ArrayUtil.oversize(count+1,RamUsageEstimator.NUM_BYTES_INT32));if(posLengths.length==count)posLengths=java.util.Arrays.copyOf(posLengths,ArrayUtil.oversize(count+1,RamUsageEstimator.NUM_BYTES_INT32));}outputs[count]=new CharsRef(output,offset,len);endOffsets[count]=endOffset;posLengths[count]=posLength;++count;}
HTTPS.ProtocolType protocol = new FetchLibrariesRequest(" ", " ", " ", " ", " ");
public boolean exists(){return objects.exists();}
}; out out . {  ) . . java ( FilterOutputStream
MethodType method = MethodType.PUT; String[] uriPattern = { " ", " ", " ", " ", " " }; ScaleClusterRequest request = new ScaleClusterRequest();
public IDataValidationConstraint createTimeConstraint(int operatorType, String formula1, String formula2) {return DVConstraint.createTimeConstraint(operatorType, formula1, formula2);}
return invoke(request, new InvokeOptions().requestMarshaller(ListObjectParentPathsRequestMarshaller.getInstance()).responseUnmarshaller(ListObjectParentPathsResponseUnmarshaller.getInstance()));
public DescribeCacheSubnetGroupsResponse describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request){InvokeOptions options=new InvokeOptions();options.RequestMarshaller=DescribeCacheSubnetGroupsRequestMarshaller.Instance;options.ResponseUnmarshaller=DescribeCacheSubnetGroupsResponseUnmarshaller.Instance;return this.<DescribeCacheSubnetGroupsResponse>invoke(request,options);}
void setSharedFormula(boolean flag) { field_5_options.sharedFormula = setShortBoolean(flag); }
boolean IsReuseObjects() {return reuseObjects;}
t.addChild(new ErrorNodeImpl((Token) badToken));return t;
public LatvianStemFilterFactory(java.util.Map<String,String> args){super(args);if(args.size()>0){throw new IllegalArgumentException("Unknown parameters: "+args);}}
public RemoveSourceIdentifierFromSubscriptionResponse removeSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(RemoveSourceIdentifierFromSubscriptionRequestMarshaller.getInstance());options.setResponseUnmarshaller(RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.getInstance());return invoke(request,options);}
TokenFilterFactory forName(String name, Map<String,String> args) { return loader.newInstance(name, args); }
private HTTPS.ProtocolType protocol = HTTPS.ProtocolType.AddAlbumPhotosRequest;
InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetThreatIntelSetRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetThreatIntelSetResponseUnmarshaller.getInstance());return invoke(request,GetThreatIntelSetResponse.class,options);
public TreeFilter clone() { return new Binary.AndTreeFilter(a.clone(), b.clone()); }
public boolean equals(Object o){return o instanceof Object;}
boolean hasArray(){ return protectedHasArray(); }
public UpdateContributorInsightsResult updateContributorInsights(UpdateContributorInsightsRequest request) {request = beforeClientExecution(request);return executeUpdateContributorInsights(request);}
void UnwriteProtectWorkbook() { records.remove(fileShare); records.remove(writeProtect); fileShare = null; writeProtect = null; }
public SolrSynonymParser(boolean dedup, boolean expand, Analyzer analyzer){super(dedup,expand,analyzer);}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(RequestSpotInstancesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(RequestSpotInstancesResponseUnmarshaller.getInstance()); return invoke(request, options, RequestSpotInstancesResponse.class);
public byte[] getObjectData() {return findObjectRecord().objectData;}
return invoke(request, new InvokeOptions().withRequestMarshaller(GetContactAttributesRequestMarshaller.getInstance()).withResponseUnmarshaller(GetContactAttributesResponseUnmarshaller.getInstance()));
@Override public String toString() { return getKey() + " " + getValue(); }
public ListTextTranslationJobsResponse listTextTranslationJobs(ListTextTranslationJobsRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(ListTextTranslationJobsRequestMarshaller.getInstance());options.setResponseUnmarshaller(ListTextTranslationJobsResponseUnmarshaller.getInstance());return invoke(request,options);}
public GetContactMethodsResponse getContactMethods(GetContactMethodsRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetContactMethodsRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetContactMethodsResponseUnmarshaller.getInstance());return invoke(GetContactMethodsResponse.class,request,options);}
short LookupIndexByName(String name){FunctionMetadata fd=GetInstance().GetFunctionByNameInternal(name);if(fd==null){return-1;}return(short)fd.Index;}
InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(DescribeAnomalyDetectorsRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeAnomalyDetectorsResponseUnmarshaller.getInstance());return invoke(request, options);
String insertId(String message, ObjectId changeId) { return insertId; }
long getObjectSize(AnyObjectId objectId,int typeHint){long sz=db.getObjectSize(objectId,typeHint);if(sz<0){if(typeHint==OBJ_ANY){throw new MissingObjectException(objectId.copy(),"");}throw new MissingObjectException(objectId.copy(),"");}return sz;}
public ImportInstallationMediaResult importInstallationMedia(ImportInstallationMediaRequest request) {request = beforeClientExecution(request);return executeImportInstallationMedia(request);}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance()); options.setResponseUnmarshaller(PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance()); return invoke(request, options);
public NumberPtg(ILittleEndianInput in1){field_1_value=in1.readDouble();}
public GetFieldLevelEncryptionConfigResult getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) {request = beforeClientExecution(request);return executeGetFieldLevelEncryptionConfig(request);}
public DescribeDetectorResult describeDetector(DescribeDetectorRequest request) {request = beforeClientExecution(request);return executeDescribeDetector(request);}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ReportInstanceStatusRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ReportInstanceStatusResponseUnmarshaller.getInstance()); return invoke(ReportInstanceStatusResponse.class, request, options);
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteAlarmRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteAlarmResponseUnmarshaller.getInstance()); return invoke(DeleteAlarmResponse.class, request, options);
public TokenStream create(TokenStream input){return new PortugueseStemFilter(input);}
reserved = (FtCblsSubRecord) new byte[ENCODED_SIZE];
public boolean remove(Object object){synchronized(mutex){return c.remove(object);}}
public GetDedicatedIpResult getDedicatedIp(GetDedicatedIpRequest request) {request = beforeClientExecution(request);return executeGetDedicatedIp(request);}
public String toString(){return precedence+" ";}
public ListStreamProcessorsResponse listStreamProcessors(ListStreamProcessorsRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(ListStreamProcessorsRequestMarshaller.getInstance());options.setResponseUnmarshaller(ListStreamProcessorsResponseUnmarshaller.getInstance());return invoke(request, options);}
public DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) { _loadBalancerName = loadBalancerName; _policyName = policyName; }
int WindowProtectRecord(options _options){}
public UnbufferedCharStream(int bufferSize){data=new int[bufferSize];n=0;}
return invoke(request,new InvokeOptions().withRequestMarshaller(GetOperationsRequestMarshaller.getInstance()).withResponseUnmarshaller(GetOperationsResponseUnmarshaller.getInstance()),GetOperationsResponse.class);
void copyRawTo(byte[] b, int o){ NB.encodeInt32(b,o+4); NB.encodeInt32(b,o+8); NB.encodeInt32(b,o+12); NB.encodeInt32(b,o+16); }
public WindowOneRecord(RecordInputStream in1){field_1_h_hold=in1.readShort();field_2_v_hold=in1.readShort();field_3_width=in1.readShort();field_4_height=in1.readShort();field_5_options=in1.readShort();field_6_active_sheet=in1.readShort();field_7_first_visible_tab=in1.readShort();field_8_num_selected_tabs=in1.readShort();field_9_tab_width_ratio=in1.readShort();}
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(StopWorkspacesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(StopWorkspacesResponseUnmarshaller.getInstance()); return invoke(StopWorkspacesResponse.class, request, options);
@Override public void close() throws IOException { if (isOpen()) { try { dump(); } finally {} try { channel.truncate(); } finally {} try { channel.close(); } finally {} fos.close(); } }
public DescribeMatchmakingRuleSetsResult describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) {request = beforeClientExecution(request);return executeDescribeMatchmakingRuleSets(request);}
public String getPronunciation(int wordId, char[] surface, int off, int len) { return null; }
public String getPath() { return pathStr; }
double devsq(double[] v){double r=Double.NaN;if(v!=null&&v.length>=1){double m=0;double s=0;int n=v.length;for(int i=0;i<n;++i){s+=v[i];}m=s/n;s=0;for(int i=0;i<n;++i){s+=(v[i]-m)*(v[i]-m);}r=n==1?0:s;}return r;}
return invoke(DescribeResizeResponse.class, request, new InvokeOptions().withRequestMarshaller(DescribeResizeRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeResizeResponseUnmarshaller.getInstance()));
public boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
int end(){return end();}
public void traverse(ICellHandler handler){int width=lastColumn-firstColumn+1;SimpleCellWalkContext ctx=new SimpleCellWalkContext();IRow currentRow=null;ICell currentCell=null;for(ctx.rowNumber=firstRow;ctx.rowNumber<=lastRow;ctx.rowNumber++){currentRow=sheet.getRow(ctx.rowNumber);if(currentRow==null){continue;}for(ctx.colNumber=firstColumn;ctx.colNumber<=lastColumn;ctx.colNumber++){currentCell=currentRow.getCell(ctx.colNumber);if(currentCell==null){continue;}if(isEmpty(currentCell)&&!traverseEmptyCells){continue;}ctx.ordinalNumber=(ctx.rowNumber-firstRow)*width+(ctx.colNumber-firstColumn+1);handler.onCell(ctx);}}}
public int getReadIndex() { return _readIndex; }
public int compareTo(ScoreTerm other){if(term.bytesEquals(other.term)){if(boost==other.boost)return 0;else return Float.compare(boost,other.boost);}else{return term.compareTo(other.term);}}
int normalize(char[] s,int len){for(int i=0;i<len;++i){switch(s[i]){case FARSI_YEH:case YEH_BARREE:s[i]=YEH;break;case KEHEH:s[i]=KAF;break;case HEH_YEH:case HEH_GOAL:s[i]=HEH;break;case HAMZA_ABOVE:len=StemmerUtil.delete(s,i,len);--i;break;default:break;}}return len;}
public void serialize(ILittleEndianOutput out1){out1.writeShort();}
DiagnosticErrorListener(boolean exactOnly){this.exactOnly=exactOnly;}
KeySchemaElement(String attributeName, KeyType keyType){this._attributeName=attributeName;this._keyType=keyType;}
public GetAssignmentResponse getAssignment(GetAssignmentRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetAssignmentRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetAssignmentResponseUnmarshaller.getInstance());return invoke(request,options);}
boolean hasObject(AnyObjectId id){return findOffset(id)!=-1;}
public void setAllGroups(boolean allGroups){this.allGroups=allGroups;return;}
void setMultiValued(String dimName, boolean v){synchronized(this){DimConfig fieldType=fieldTypes.get(dimName);if(fieldType==null){fieldType=new DimConfig();fieldTypes.put(dimName,fieldType);}fieldType.setMultiValued(v);}}
int getCellsVal(){int size=0;for(char c:cells.keySet()){Cell e=At(c);if(e.cmd>=0){size++;}}return size;}
public DeleteVoiceConnectorResult deleteVoiceConnector(DeleteVoiceConnectorRequest request) {request = beforeClientExecution(request);return executeDeleteVoiceConnector(request);}
public DeleteLifecyclePolicyResult deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) {request = beforeClientExecution(request); return executeDeleteLifecyclePolicy(request);}
