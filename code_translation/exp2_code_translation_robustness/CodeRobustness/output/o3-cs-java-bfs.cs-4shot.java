public void serialize(LittleEndianOutput out1) {out1.writeShort(field_1_options);}
public void addAll(BlockList<? extends T> src){if(src==null||src.size==0)return;for(int srcDirIdx=0;srcDirIdx<src.tailDirIdx;++srcDirIdx)addDirectory(src.directory[srcDirIdx]);int tailBlkIdx=src.tailBlkIdx;if(tailBlkIdx!=0){Object[] srcBlk=src.tailBlock;for(int i=0;i<tailBlkIdx;i++)add((T)srcBlk[i]);}}
public void writeByte(byte b){if(outerInstance.upto==outerInstance.blockSize){outerInstance.blocks.add(outerInstance.currentBlock);outerInstance.currentBlock=new byte[outerInstance.blockSize];outerInstance.upto=0;outerInstance.blockEnd+=outerInstance.blockSize;}outerInstance.currentBlock[outerInstance.upto++]=b;}
public ObjectId getObjectId() {return objectId;}
public DeleteDomainEntryResult deleteDomainEntry(DeleteDomainEntryRequest request) {request = beforeClientExecution(request);return executeDeleteDomainEntry(request);}
public long ramBytesUsed() { return fst == null ? 0 : fst.getSizeInBytes(); }
public String getFullMessage() {final int msgB=RawParseUtils.tagMessage(raw,0);if(msgB<0)return "";return RawParseUtils.decode(RawParseUtils.parseEncoding(raw),raw,msgB,raw.length);}
public POIFSFileSystem(){_root=null;_documents=new ArrayList();HeaderBlock headerBlock=new HeaderBlock();_property_table=new PropertyTable(headerBlock);}
public void init(int address){slice=ByteBlockPool.buffers[address>>BYTE_BLOCK_SHIFT];assert slice!=null;offset0=address&BYTE_BLOCK_MASK;assert offset0<slice.length;upto=slice.length;}
public SubmoduleAddCommand setPath(String path) {this.path = path; return this;}
public ListIngestionsResult listIngestions(ListIngestionsRequest request) {request = beforeClientExecution(request);return executeListIngestions(request);}
public QueryParserTokenManager(ICharStream stream,int lexState){this(stream);SwitchTo(lexState);}
public GetShardIteratorResult getShardIterator(GetShardIteratorRequest request) {request = beforeClientExecution(request);return executeGetShardIterator(request);}
modifyStrategyRequest.setMethod(MethodType.POST);
boolean ready(){synchronized(lock){try{if(in==null){return false;}return in.available()>0||bytes.hasRemaining();}catch(IOException e){throw new IOException(e);}}}
public EscherOptRecord getOptRecord() {return _optRecord;}
public int read(byte[] buffer, int offset, int count) {if (buffer == null) {throw new NullPointerException("buffer");}if (offset < 0 || count < 0) {throw new IndexOutOfBoundsException();}if (offset > buffer.length - count) {throw new IllegalArgumentException();}synchronized (this) {int copylen = getLength();if (copylen == 0) {return 0;}if (copylen > count) {copylen = count;}System.arraycopy(this.buffer, pos, buffer, offset, copylen);pos += copylen;return copylen;}}
OpenNLPSentenceBreakIterator(sentenceOp); sentenceOp.NLPSentenceDetectorOp(sentenceOp);
public void print(Object object){String str=Sharpen.StringHelper.getValueOf(object);write(str!=null?str:"null");}
public class NotImplementedFunctionException extends NotImplementedException{private final String functionName;public NotImplementedFunctionException(String functionName,Throwable cause){super(functionName,cause);this.functionName=functionName;}}
return value(nextEntry());
private void readBytes(byte[] b,int offset,int len,boolean useBuffer){if(len<0){throw new IllegalArgumentException("len must be non-negative: "+len);}long after=bufferStart+bufferPosition+len;if(after>length){throw new java.io.EOFException();}int available=bufferLength-bufferPosition;if(len<=available){if(useBuffer){System.arraycopy(buffer,bufferPosition,b,offset,len);bufferPosition+=len;return;}}if(len<bufferSize){if(available>0){System.arraycopy(buffer,bufferPosition,b,offset,available);offset+=available;len-=available;}refill();if(len<=bufferLength){System.arraycopy(buffer,0,b,offset,len);bufferPosition=len;return;}else{System.arraycopy(buffer,0,b,offset,bufferLength);offset+=bufferLength;len-=bufferLength;bufferPosition=bufferLength;}}readInternal(b,offset,len,useBuffer);}
public TagQueueResult tagQueue(TagQueueRequest request) {request = beforeClientExecution(request);return executeTagQueue(request);}
public void remove() { throw new UnsupportedOperationException(); }
public ModifyCacheSubnetGroupResult modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) {request = beforeClientExecution(request);return executeModifyCacheSubnetGroup(request);}
public void setParams(String params) { StringTokenizer st = new StringTokenizer(params, " "); if (st.hasMoreTokens()) culture = st.nextToken(); if (st.hasMoreTokens()) ignore = st.nextToken(); if (st.hasMoreTokens()) culture = culture + " " + st.nextToken(); }
public DeleteDocumentationVersionResult deleteDocumentationVersion(DeleteDocumentationVersionRequest request){request = beforeClientExecution(request);return executeDeleteDocumentationVersion(request);}
public boolean equals(Object obj) { if (this == obj) return true; if (!(obj instanceof FacetLabel)) return false; FacetLabel other = (FacetLabel) obj; if (other.Length != Length) return false; for (int i = Length - 1; i >= 0; --i) { if (!Components[i].equals(other.Components[i])) return false; } return true; }
public GetInstanceAccessDetailsResult getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) {request = beforeClientExecution(request);return executeGetInstanceAccessDetails(request);}
public HSSFPolygon createPolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape = (HSSFPolygon) createShape(shape); shape.setAnchor(anchor); shapes.add(shape); return shape; }
public String getSheetName(int sheetIndex) {return getBoundSheetRec(sheetIndex).getSheetname();}
public GetDashboardResult getDashboard(GetDashboardRequest request) {request = beforeClientExecution(request);return executeGetDashboard(request);}
public AssociateSigninDelegateGroupsWithAccountResult associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) {request = beforeClientExecution(request);return executeAssociateSigninDelegateGroupsWithAccount(request);}
public void addMultipleBlanks(MulBlankRecord mbr){for(int k=0;k<mbr.getNumColumns();k++){BlankRecord br=new BlankRecord();br.setRow(mbr.getRow());br.setColumn((short)(mbr.getFirstColumn()+k));br.setXFIndex(mbr.getXFAt(k));insertCell(br);}}
public static String quote(String str){return str.replace("\\","\\\\");}
public java.nio.ByteBuffer putInt(int value){throw new java.nio.ReadOnlyBufferException();}
Object[][] values2d=new Object[_nRows][_nColumns];for(int r=0;r<_nRows;++r){Object[] rowData=vv[r];for(int c=0;c<_nColumns;++c){values2d[r][c]=rowData[c];}}
public GetIceServerConfigResult getIceServerConfig(GetIceServerConfigRequest request){request = beforeClientExecution(request);return executeGetIceServerConfig(request);}
public String toString() {StringBuilder sb = new StringBuilder();sb.append(getClass().getName()).append(" = ").append(getValueAsString()).append(" ");return sb.toString();}
@Override public String toString() { return fieldString + " " + _parentQuery + " "; }
void incRef() { refCount.incrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResult updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) {request = beforeClientExecution(request);return executeUpdateConfigurationSetSendingEnabled(request);}
int getNextXBATChainOffset() {return getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE;}
void multiplyByPowerOfTen(int pow10){TenPower tp=TenPower.getInstance(Math.abs(pow10));tp.mulShift=mulShift(tp._divisor,tp._divisorShift,tp._multiplicand,tp._multiplierShift);if(pow10<0){}else{}}
public String toString() {int length = length();StringBuilder builder = new StringBuilder(length - 1);for (int i = 0; i < length; i++){if (i > 0){builder.append(File.separatorChar);}builder.append(getComponent(i));}return builder.toString();}
void withFetcher(ECSMetadataServiceCredentialsFetcher fetcher) {fetcher.setRoleName(fetcher);}
public void setProgressMonitor(ProgressMonitor pm) { this.progressMonitor = pm; }
public void reset() {if (parseEntry(ptr, 0) != EOF) {if (!first()) {}}}
public E previous() { if (start >= iterator.previousIndex()) { throw new NoSuchElementException(); } return iterator.previous(); }
public String getNewPrefix() { return newPrefix; }
int indexOfValue(int value) {for (int i = 0; i < mSize; ++i) {if (value == mValues[i]) return i;} return -1;}
public List<CharsRef> uniqueStems(List<Stem> stems, CharArraySet dictionary) {CharArraySet deduped = new CharArraySet(dictionary, 8);List<CharsRef> terms = new ArrayList<CharsRef>();for (Stem s : stems) {int length = s.length;if (length < 2) {continue;}CharsRef word = new CharsRef(s.stem, 0, length);if (!deduped.contains(word)) {deduped.add(word);terms.add(word);}}return terms;}
public GetGatewayResponsesResponse getGatewayResponses(GetGatewayResponsesRequest request) {final InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(GetGatewayResponsesRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetGatewayResponsesResponseUnmarshaller.getInstance());return invoke(request, options);}
public void setPosition(long position) {currentBlockIndex = (int)(position >> outerInstance.blockBits);currentBlock = outerInstance.blocks[currentBlockIndex];currentBlockUpto = (int)(position & outerInstance.blockMask);}
public long skip(long n){int s=(int)Math.max(Math.min(Available,n),0);ptr+=s;return s;}
public BootstrapActionDetail bootstrapActionConfig(BootstrapActionConfig _bootstrapActionConfig) { ; }
public void serialize(LittleEndianOutput out){out.writeShort(field_1_row);out.writeShort(field_2_col);out.writeShort(field_3_flags);out.writeInt(field_4_shapeid);out.writeShort((short)field_6_author.length());out.writeByte(field_5_hasMultibyte?0x01:0x00);if(field_5_hasMultibyte){StringUtil.putUnicodeLE(field_6_author,out);}else{StringUtil.putCompressedUnicode(field_6_author,out);}if(field_7_padding!=null){out.writeByte(Integer.parseInt(field_7_padding,10));}}
int lastIndexOf(String string){return lastIndexOf(string,);}
public boolean add(E object){addLastImpl(object);return true;}
void unsetSection(String section,String subsection){ConfigSnapshot src,res;do{src=state.get();res=src.unsetSection(section,subsection);}while(!state.compareAndSet(src,res));}
public String getTagName() {return tagName;}
public void addSubRecord(int index, SubRecord element){subrecords.add(index, element);}
public boolean remove(Object object) {synchronized (mutex) {return c.remove(object);} }
public TokenStream create(TokenStream input){return new DoubleMetaphoneFilter(input,4,false);}
public long length() {return inCoreLength();}
void setValue(boolean newValue) {value = newValue;}
Pair<ContentSource, ContentSource> pair = new Pair<>(newSource.getContentSource(), oldSource.getContentSource());
int get(int i) {if (i >= count) {throw new IndexOutOfBoundsException();} return entries[i];}
public CreateRepoRequest() {super(MethodType.PUT, UriPattern, "", "", "", "");}
public boolean isDeltaBaseAsOffset() {return deltaBaseAsOffset;}
public void remove() { if (expectedModCount != list.modCount) { throw new ConcurrentModificationException(); } if (lastReturned == null) { throw new IllegalStateException(); } LinkedList.Link<ET> link = lastReturned; list.unlink(link); if (next == link) { next = link.next; } else { previous = link.previous; --pos; } lastReturned = null; ++expectedModCount; }
public MergeShardsResult mergeShards(MergeShardsRequest request) {request = beforeClientExecution(request);return executeMergeShards(request);}
public Connection allocateHostedConnection(AllocateHostedConnectionRequest request) {request = beforeClientExecution(request);return executeAllocateHostedConnection(request);}
public int getBeginIndex() { return start; }
return Query.getTerms(query);
public ByteBuffer compact(){throw new java.nio.ReadOnlyBufferException();}
void decode(int iterations,int[] values,int valuesOffset,int[] blocks,int blocksOffset){for(int i=0;i<iterations;++i){int byte0=(blocks[blocksOffset]>>>15)&0xFF,byte1=(blocks[blocksOffset]>>>3)&0xFF,byte2=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=(byte2&63)|byte2|byte1;values[valuesOffset++]=((blocks[blocksOffset]>>>2)&0xFF)<<2;values[valuesOffset++]=((blocks[blocksOffset]>>>4)&0xFF)<<4;values[valuesOffset++]=((blocks[blocksOffset++]>>>6)&0xFF)<<2;}}
public String getHumanishName() {String result = getPath();if (result == null) {return result;}if (result.length() == 0) {throw new IllegalArgumentException(result);}String[] elements = Matcher.LOCAL_FILE.split(result);if (elements.length > 1 || elements[0].equals("")) {if (elements.length > 1 && elements[elements.length - 1].equals("")) {result = elements[elements.length - 2];} else {result = elements[elements.length - 1];}}if (File.separatorChar == '\\') {if (result.length() > 2 && result.matches("[A-Za-z]:")) {result = result.substring(0, result.length() - 2);}}if (result.endsWith(Constants.DOT_GIT_EXT)) {result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length());} else {if (result.equals(Constants.DOT_GIT) || result.equals("")) {throw new IllegalArgumentException(elements[elements.length - 2]);}}return result;}
public DescribeNotebookInstanceLifecycleConfigResponse describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) {InvokeOptions options = new InvokeOptions();options.requestMarshaller = DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance();options.responseUnmarshaller = DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance();return invoke(request, options);}
public String getAccessKeySecret() { return accessSecret; }
public CreateVpnConnectionResponse createVpnConnection(CreateVpnConnectionRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(CreateVpnConnectionRequestMarshaller.getInstance());options.setResponseUnmarshaller(CreateVpnConnectionResponseUnmarshaller.getInstance());return invoke(request,options);}
public DescribeVoicesResult describeVoices(DescribeVoicesRequest request) {request = beforeClientExecution(request);return executeDescribeVoices(request);}
public ListMonitoringExecutionsResult listMonitoringExecutions(ListMonitoringExecutionsRequest request) {request = beforeClientExecution(request);return executeListMonitoringExecutions(request);}
public DescribeJobRequest(String vaultName,String jobId){this._jobId=jobId;this._vaultName=vaultName;}
public EscherRecord getEscherRecord(int index) { return escherRecords[index]; }
public GetApisResult getApis(GetApisRequest request) {request = beforeClientExecution(request);return executeGetApis(request);}
public DeleteSmsChannelResult deleteSmsChannel(DeleteSmsChannelRequest request) {request = beforeClientExecution(request);return executeDeleteSmsChannel(request);}
public GetTrackingRefUpdate getTrackingRefUpdate() {return trackingRefUpdate;}
void print() {boolean b; print(b.toString());}
public IQueryNode getChild() { return getChildren()[0]; }
}{ int index = workdirTreeIndex; NotIgnoredFilter(workdirTreeIndex); }
public AreaRecord(RecordInputStream in1){field_1_formatFlags=in1.readShort();}
Protocol.getThumbnailRequest(ProtocolType.HTTPS, "", "", "", "");
public DescribeTransitGatewayVpcAttachmentsResponse describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DescribeTransitGatewayVpcAttachmentsRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance());return invoke(request,options);}
public PutVoiceConnectorStreamingConfigurationResponse putVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance());options.setResponseUnmarshaller(PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance());return invoke(request,options);}
public OrdRange getOrdRange(String prefix){return prefixToOrdRange.get(prefix);}
@Override public String toString() {String symbol = "";if (startIndex >= 0 && startIndex < input.size()) {symbol = input.getText(Interval.of(startIndex, startIndex));symbol = Utils.escapeWhitespace(symbol, false);}return String.format(java.util.Locale.getDefault(), "%s('%s' at %d)", LexerNoViableAltException.class.getSimpleName(), symbol, startIndex);}
public E peek() { return peekFirstImpl(); }
public CreateWorkspacesResponse createWorkspaces(CreateWorkspacesRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(CreateWorkspacesRequestMarshaller.getInstance());options.setResponseUnmarshaller(CreateWorkspacesResponseUnmarshaller.getInstance());return invoke(request, options);}
public Object clone(){NumberFormatIndexRecord rec=new NumberFormatIndexRecord();rec.field_1_formatIndex=field_1_formatIndex;return rec;}
public DescribeRepositoriesResponse describeRepositories(DescribeRepositoriesRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DescribeRepositoriesRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeRepositoriesResponseUnmarshaller.getInstance());return invoke(request,options);}
public SparseIntArray(int initialCapacity) {if (initialCapacity == 0) {mKeys = EmptyArray.INT; mValues = EmptyArray.INT;} else {initialCapacity = com.android.internal.util.ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity];} mSize = 0;}
public TokenStream create(TokenStream input) {return new HyphenatedWordsFilter(input);}
public CreateDistributionWithTagsResponse createDistributionWithTags(CreateDistributionWithTagsRequest request){InvokeOptions options=new InvokeOptions();options.requestMarshaller=CreateDistributionWithTagsRequestMarshaller.getInstance();options.responseUnmarshaller=CreateDistributionWithTagsResponseUnmarshaller.getInstance();return invoke(request,options);}
RandomAccessFile file = new RandomAccessFile(fileName, mode); throw new UnsupportedOperationException();
public DeleteWorkspaceImageResponse deleteWorkspaceImage(DeleteWorkspaceImageRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.getInstance());options.setResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.getInstance());return invoke(request,options);}
public static String toHex(int value){return toHex((long)value);}
public UpdateDistributionResult updateDistribution(UpdateDistributionRequest request) {request = beforeClientExecution(request);return executeUpdateDistribution(request);}
public HSSFColor getColor(short index){if(index==HSSFColor.AUTOMATIC.index){return HSSFColor.AUTOMATIC.getInstance();}byte[] b=palette.getColor(index);if(b!=null){return new HSSFColor.CustomColor(b);}return null;}
public ValueEval evaluate(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(); }
public void serialize(LittleEndianOutput out1) {out1.writeShort(field_1_number_crn_records);out1.writeShort(field_2_sheet_table_index);}
public DescribeDBEngineVersionsResponse describeDBEngineVersions() {return describeDBEngineVersions(new DescribeDBEngineVersionsRequest());}
public FormatRun(short character, short fontIndex){this._character=character;this._fontIndex=fontIndex;}
static byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int resultIndex = 0; int end = offset + length; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte) (ch >>> 8); result[resultIndex++] = (byte) ch; } return result; }
public UploadArchiveResult uploadArchive(UploadArchiveRequest request) {request = beforeClientExecution(request);return executeUploadArchive(request);}
return (List<IToken>) getHiddenTokensToLeft(tokenIndex - 1);
@Override public boolean equals(Object obj){if(this==obj)return true;if(obj==null)return false;if(getClass()!=obj.getClass())return false;AutomatonQuery other=(AutomatonQuery)obj;if(m_term!=null?!m_term.equals(other.m_term):other.m_term!=null)return false;return m_compiled!=null?m_compiled.equals(other.m_compiled):other.m_compiled==null;}
private SpanQuery makeSpanClause(Iterable<WeightBySpanQuery> weightBySpanQuery){List<SpanQuery> spanQueries=new ArrayList<>();for(WeightBySpanQuery wsq:weightBySpanQuery){spanQueries.add(new SpanBoostQuery(wsq.getKey(),wsq.getBoost().getValue()));}return spanQueries.size()==1?spanQueries.get(0):new SpanOrQuery(spanQueries.toArray(new SpanQuery[0]));}
return new StashCreateCommand(StashCreate);
FieldInfo fieldInfo(String fieldName) { FieldInfo ret = byName.get(fieldName); return ret; }
public DescribeEventSourceResult describeEventSource(DescribeEventSourceRequest request){request = beforeClientExecution(request);return executeDescribeEventSource(request);}
public GetDocumentAnalysisResponse getDocumentAnalysis(final GetDocumentAnalysisRequest request){final InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(GetDocumentAnalysisRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetDocumentAnalysisResponseUnmarshaller.getInstance());return invoke(request,options);}
public CancelUpdateStackResult cancelUpdateStack(CancelUpdateStackRequest request) {request = beforeClientExecution(request);return executeCancelUpdateStack(request);}
public ModifyLoadBalancerAttributesResponse modifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(ModifyLoadBalancerAttributesRequestMarshaller.getInstance());options.setResponseUnmarshaller(ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance());return invoke(request,options);}
public SetInstanceProtectionResult setInstanceProtection(SetInstanceProtectionRequest request) {request = beforeClientExecution(request);return executeSetInstanceProtection(request);}
public ModifyDBProxyResponse modifyDBProxy(ModifyDBProxyRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(ModifyDBProxyRequestMarshaller.getInstance());options.setResponseUnmarshaller(ModifyDBProxyResponseUnmarshaller.getInstance());return invoke(request, options);}
public void add(char[] chars,int offset,int len,int posLength,int endOffset,CharsRef output){if(output==null){throw new NullPointerException();}if(count==inputChars.length){int next=ArrayUtil.oversize(count+1,RamUsageEstimator.NUM_BYTES_OBJECT_REF);inputChars=Arrays.copyOf(inputChars,next);posLengths=Arrays.copyOf(posLengths,next);endOffsets=Arrays.copyOf(endOffsets,next);outputs=Arrays.copyOf(outputs,next);}inputChars[count]=Arrays.copyOfRange(chars,offset,offset+len);posLengths[count]=posLength;endOffsets[count]=endOffset;outputs[count]=CharsRef.deepCopyOf(output);++count;}
} { : ) ( FetchLibrariesRequest , Protocol ) , , , , ( ProtocolType.HTTPS " " " " " " " " " "
public boolean exists(){return objects.exists();}
FilterOutputStream out;
public ScaleClusterResult scaleCluster(ScaleClusterRequest request) {request = beforeClientExecution(request); return executeScaleCluster(request);}
public DataValidationConstraint createTimeConstraint(int operatorType, String formula1, String formula2){return DVConstraint.createTimeConstraint(operatorType, formula1, formula2);}
public ListObjectParentPathsResult listObjectParentPaths(ListObjectParentPathsRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(ListObjectParentPathsRequestMarshaller.getInstance());options.setResponseUnmarshaller(ListObjectParentPathsResponseUnmarshaller.getInstance());return invoke(request, options);}
public DescribeCacheSubnetGroupsResult describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) {request = beforeClientExecution(request);return executeDescribeCacheSubnetGroups(request);}
void setSharedFormula(boolean flag) { field_5_options = sharedFormula.setShortBoolean(field_5_options, flag); }
public boolean isReuseObjects() { return reuseObjects; }
public ErrorNode addErrorNode(Token badToken) {ErrorNodeImpl t = new ErrorNodeImpl(badToken);parent.addChild(t);return t;}
public LatvianStemFilterFactory(Map<String,String> args) {super(args);if (args.size() > 0){throw new IllegalArgumentException("Unknown parameters: " + args);} }
public RemoveSourceIdentifierFromSubscriptionResult removeSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) {request = beforeClientExecution(request);return executeRemoveSourceIdentifierFromSubscription(request);}
return forName(TokenFilterFactory.class, name, args, loader.<String,String>newInstance());
AddAlbumPhotosRequest request = new AddAlbumPhotosRequest().withProtocol(Protocol.HTTPS);
public GetThreatIntelSetResult getThreatIntelSet(GetThreatIntelSetRequest request) {request = beforeClientExecution(request);return executeGetThreatIntelSet(request);}
public TreeFilter clone() {return new Binary.AndTreeFilter(Clone.a(), Clone.b());}
public boolean equals(Object o){return o instanceof Object;}
protected boolean hasArray() {return hasArray;}
public UpdateContributorInsightsResult updateContributorInsights(UpdateContributorInsightsRequest request) {request = beforeClientExecution(request);return executeUpdateContributorInsights(request);}
void unwriteProtectWorkbook() {records.remove(writeProtect);records.remove(fileShare);fileShare = null;writeProtect = null;}
public SolrSynonymParser(boolean expand, Analyzer analyzer, boolean dedup){super(expand, analyzer, dedup);}
public RequestSpotInstancesResult requestSpotInstances(RequestSpotInstancesRequest request) {request = beforeClientExecution(request);return executeRequestSpotInstances(request);}
byte[] getObjectData() { return ObjectData[findObjectRecord()]; }
public GetContactAttributesResult getContactAttributes(GetContactAttributesRequest request) { request = beforeClientExecution(request); return executeGetContactAttributes(request); }
public String toString() { return getKey() + " " + getValue(); }
public ListTextTranslationJobsResult listTextTranslationJobs(ListTextTranslationJobsRequest request) {request = beforeClientExecution(request);return executeListTextTranslationJobs(request);}
public GetContactMethodsResult getContactMethods(GetContactMethodsRequest request) {request = beforeClientExecution(request);return executeGetContactMethods(request);}
short lookupIndexByName(String name){FunctionMetadata fd=getInstance().getFunctionByNameInternal(name);if(fd==null)return-1;return fd.getIndex();}
public DescribeAnomalyDetectorsResponse describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DescribeAnomalyDetectorsRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeAnomalyDetectorsResponseUnmarshaller.getInstance());return invoke(request,options);}
public String insertId(String message, ObjectId changeId) {return insertId(message, changeId);}
public long getObjectSize(AnyObjectId objectId, int typeHint) throws MissingObjectException, IOException {long sz = db.getObjectSize(objectId, typeHint);if (sz < 0) throw new MissingObjectException(objectId.copy(), typeHint);return sz;}
public ImportInstallationMediaResult importInstallationMedia(ImportInstallationMediaRequest request) {request = beforeClientExecution(request);return executeImportInstallationMedia(request);}
public PutLifecycleEventHookExecutionStatusResult putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) {request = beforeClientExecution(request);return executePutLifecycleEventHookExecutionStatus(request);}
public NumberPtg(ILittleEndianInput in1) {field_1_value = in1.readDouble();}
public GetFieldLevelEncryptionConfigResult getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) {request = beforeClientExecution(request);return executeGetFieldLevelEncryptionConfig(request);}
public DescribeDetectorResult describeDetector(DescribeDetectorRequest request) {request = beforeClientExecution(request);return executeDescribeDetector(request);}
public ReportInstanceStatusResult reportInstanceStatus(ReportInstanceStatusRequest request){request = beforeClientExecution(request);return executeReportInstanceStatus(request);}
public DeleteAlarmResult deleteAlarm(DeleteAlarmRequest request) {request = beforeClientExecution(request);return executeDeleteAlarm(request);}
public TokenStream create(TokenStream input){return new PortugueseStemFilter(input);}
private byte[] reserved = new byte[ENCODED_SIZE];
synchronized (mutex) { Object object = c.remove(); return; }
public GetDedicatedIpResult getDedicatedIp(GetDedicatedIpRequest request) {request = beforeClientExecution(request);return executeGetDedicatedIp(request);}
public String toString() { return " " + precedence; }
public ListStreamProcessorsResult listStreamProcessors(ListStreamProcessorsRequest request) {request = beforeClientExecution(request);return executeListStreamProcessors(request);}
public DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName){this._loadBalancerName=loadBalancerName;this._policyName=policyName;}
WindowProtectRecord options(int options, WindowProtectRecord _options) {}
int[] data = new int[bufferSize]; int n = 0;
public GetOperationsResponse getOperations(GetOperationsRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(GetOperationsRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetOperationsResponseUnmarshaller.getInstance());return invoke(request, options);}
public void copyRawTo(byte[] b, int o) {EncodeInt32.NB(b, o, value1);EncodeInt32.NB(b, o + 4, value2);EncodeInt32.NB(b, o + 8, value3);EncodeInt32.NB(b, o + 12, value4);EncodeInt32.NB(b, o + 16, value5);}
public WindowOneRecord(RecordInputStream in1){this.field_1_h_hold=in1.readShort();this.field_2_v_hold=in1.readShort();this.field_3_width=in1.readShort();this.field_4_height=in1.readShort();this.field_5_options=in1.readShort();this.field_6_active_sheet=in1.readShort();this.field_7_first_visible_tab=in1.readShort();this.field_8_num_selected_tabs=in1.readShort();this.field_9_tab_width_ratio=in1.readShort();}
public StopWorkspacesResult stopWorkspaces(StopWorkspacesRequest request) {request = beforeClientExecution(request);return executeStopWorkspaces(request);}
public void close() throws IOException {if (isOpen()) {try {dump();} finally {try {channel.truncate();} finally {channel.close();fos.close();}}}}
public DescribeMatchmakingRuleSetsResponse describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request){InvokeOptions options=new InvokeOptions();options.setRequestMarshaller(DescribeMatchmakingRuleSetsRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeMatchmakingRuleSetsResponseUnmarshaller.getInstance());return invoke(request,options);}
public String getPronunciation(int wordId, char[] surface, int off, int len) {return null;}
public String getPath() {return pathStr;}
public static double devsq(double[] v){if(v!=null&&v.length>=1){int n=v.length;double s=0;for(int i=0;i<n;++i){s+=v[i];}double m=s/n;double r=0;for(int i=0;i<n;++i){double d=v[i]-m;r+=d*d;}return r;}return Double.NaN;}
public DescribeResizeResult describeResize(DescribeResizeRequest request) {request = beforeClientExecution(request);return executeDescribeResize(request);}
boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
int end() { return end; }
public void traverse(Sheet sheet, ICellHandler handler, boolean traverseEmptyCells, int firstRow, int lastRow, int firstColumn, int lastColumn){SimpleCellWalkContext ctx=new SimpleCellWalkContext(firstRow,lastRow,firstColumn,lastColumn);int width=lastColumn-firstColumn+1;for(int rowNumber=firstRow;rowNumber<=lastRow;++rowNumber){ctx.rowNumber=rowNumber;IRow currentRow=sheet.getRow(rowNumber);if(currentRow==null){if(!traverseEmptyCells)continue;for(int colNumber=firstColumn;colNumber<=lastColumn;++colNumber){ctx.colNumber=colNumber;ctx.ordinalNumber=(rowNumber-firstRow)*width+(colNumber-firstColumn);handler.onCell(null,ctx);}continue;}for(int colNumber=firstColumn;colNumber<=lastColumn;++colNumber){ctx.colNumber=colNumber;ctx.ordinalNumber=(rowNumber-firstRow)*width+(colNumber-firstColumn);ICell currentCell=currentRow.getCell(colNumber);if(currentCell==null){if(!traverseEmptyCells)continue;handler.onCell(null,ctx);continue;}handler.onCell(currentCell,ctx);}}}
int getReadIndex() { return _ReadIndex; }
public int compareTo(ScoreTerm other){if(this.Boost!=other.Boost){return this.Boost.compareTo(other.Boost);}if(this.Term.bytesEquals(other.Term)){return 0;}else{return this.Term.compareTo(other.Term);} }
public int normalize(char[] s,int len){for(int i=0;i<len;++i){switch(s[i]){default:break;case HAMZA_ABOVE:s[i]=0;break;case HEH_GOAL:case HEH_YEH:s[i]=HEH;break;case KEHEH:s[i]=KAF;break;case YEH_BARREE:case FARSI_YEH:s[i]=YEH;break;}}return len;}
public void serialize(ILittleEndianOutput out1){out1.writeShort();}
public DiagnosticErrorListener(boolean exactOnly){super(exactOnly);}
public KeySchemaElement(String attributeName, KeyType keyType){this._attributeName=attributeName;this._keyType=keyType;}
public GetAssignmentResult getAssignment(GetAssignmentRequest request) {request = beforeClientExecution(request);return executeGetAssignment(request);}
public boolean hasObject(AnyObjectId id) { return findOffset(id) != -1; }
public boolean allGroups(boolean allGroups) { return groupingSearch.setAllGroups(allGroups); }
void setMultiValued(String dimName, boolean v) { synchronized (this) { DimConfig fieldType = fieldTypes.get(dimName); if (fieldType == null) { fieldType = new DimConfig(); fieldTypes.put(dimName, fieldType); } fieldType.setMultiValued(v); } }
public int getCellsVal(){int size=0;for(char c:cells.keySet()){int cell=cells.get(c);if(cell>=0){cmd.At(size++).e=0;}}return size;}
public DeleteVoiceConnectorResult deleteVoiceConnector(DeleteVoiceConnectorRequest request) {request = beforeClientExecution(request);return executeDeleteVoiceConnector(request);}
public DeleteLifecyclePolicyResult deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) {request = beforeClientExecution(request);return executeDeleteLifecyclePolicy(request);}
