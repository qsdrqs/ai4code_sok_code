void serialize(LittleEndianOutput out1) {}
void addAll(final NGit.Util.BlockList<T> src){if(src.size==0){}int srcDirIdx=0;for(;srcDirIdx<src.tailDirIdx;srcDirIdx++){addAll(src.directory[srcDirIdx],0,BLOCK_SIZE);}if(src.tailBlkIdx!=0){addAll(src.tailBlock,0,src.tailBlkIdx);}}
void writeByte(byte b) { if (outerInstance.upto == outerInstance.blockSize) { if (outerInstance.currentBlock != null) { outerInstance.blocks.add(outerInstance.currentBlock); outerInstance.blockEnd.add(outerInstance.upto); } outerInstance.currentBlock = new byte[outerInstance.blockSize]; outerInstance.upto = 0; } outerInstance.currentBlock[outerInstance.upto++] = b; }
public ObjectId getObjectId() { return objectId; }
DeleteDomainEntryResponse deleteDomainEntry(DeleteDomainEntryRequest request) { options = new InvokeOptions(); options.ResponseUnmarshaller = DeleteDomainEntryResponseUnmarshaller.Instance; return invoke(request); }
long ramBytesUsed() { return fst == null ? 0L : fst.getSizeInBytes(); }
String getFullMessage() { byte[] raw = buffer; int msgB = RawParseUtils.tagMessage(raw, 0); if (msgB < 0) { return ""; } Charset enc = RawParseUtils.parseEncoding(raw); return RawParseUtils.decode(enc, raw, msgB, raw.length); }
public POIFSFileSystem() { _header = new HeaderBlock(); _property_table = new PropertyTable(); _documents = new ArrayList(); _root = null; }
void init(int address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; assert slice != null; upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; assert upto < ; }
public SubmoduleAddCommand setPath(String path) { this.path = path; return this; }
public ListIngestionsResult listIngestions(ListIngestionsRequest request) {request = beforeClientExecution(request);return executeListIngestions(request);}
public QueryParserTokenManager(CharStream stream, int lexState) { this(stream); SwitchTo(lexState); }
public GetShardIteratorResponse getShardIterator(GetShardIteratorRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetShardIteratorRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetShardIteratorResponseUnmarshaller.getInstance()); return invoke(request, options); }
public ModifyStrategyRequest() { super(" ", " ", " ", " ", " "); }
boolean ready() throws java.io.IOException { synchronized (lock) { if (in == null) { throw new java.io.IOException(" "); } try { return bytes.hasRemaining() || in.available() > 0; } catch (java.io.IOException e) { return false; } } }
EscherOptRecord getOptRecord() { }
public int read(byte[] buffer, int offset, int length) { synchronized(this) { if (buffer == null) { throw new IllegalArgumentException(" "); } java.util.Objects.checkFromIndexSize(offset, length, buffer.length); if (length == 0) { return 0; } int copylen = count - pos < length ? count - pos : length; for (int i = 0; i < copylen; i++) { buffer[offset + i] = (byte) buf[pos + i]; } pos += copylen; return copylen; } }
OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) { this.sentenceOp = sentenceOp; }
void print(String str) { write(str != null ? str : "null"); }
public NotImplementedFunctionException(String functionName, Throwable cause) { super(functionName, cause); this.functionName = functionName; }
V next() { return nextEntry.value; }
void readBytes(byte[] b, int offset, int len, boolean useBuffer) { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) { System.arraycopy(buffer, bufferPosition, b, offset, len); } bufferPosition += len; } else { if (available > 0) { System.arraycopy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { refill(); if (bufferLength < len) { System.arraycopy(buffer, 0, b, offset, bufferLength); throw new java.io.EOFException(); } else { System.arraycopy(buffer, 0, b, offset, len); bufferPosition = len; } } else { long after = bufferStart + bufferPosition + len; if (after > length()) { throw new java.io.EOFException(" "); } readInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
public TagQueueResponse tagQueue(TagQueueRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(TagQueueRequestMarshaller.Instance); options.setResponseUnmarshaller(TagQueueResponseUnmarshaller.Instance); return invoke(request, options); }
void remove() { throw new UnsupportedOperationException(); }
ModifyCacheSubnetGroupResponse modifyCacheSubnetGroup() { options = new InvokeOptions(); options.setRequestMarshaller(ModifyCacheSubnetGroupRequestMarshaller.Instance); options.setResponseUnmarshaller(ModifyCacheSubnetGroupResponseUnmarshaller.Instance); return invoke(); }
void setParams(String params) { culture = " "; StringTokenizer st = new StringTokenizer(params, " "); if (st.hasMoreTokens()) culture = st.nextToken(); if (st.hasMoreTokens()) culture += " " + st.nextToken(); if (st.hasMoreTokens()) ignore = st.nextToken(); }
public DeleteDocumentationVersionResponse deleteDocumentationVersion(DeleteDocumentationVersionRequest request) { return invoke(request, DeleteDocumentationVersionResponseUnmarshaller.getInstance()); }
public boolean equals(Object obj) { if (!(obj instanceof FacetLabel)) { return false; } FacetLabel other = (FacetLabel) obj; if (length != other.length) { return false; } for (int i = length - 1; i >= 0; i--) { if (!components[i].equals(other.components[i])) { return false; } } return true; }
public GetInstanceAccessDetailsResponse getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetInstanceAccessDetailsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetInstanceAccessDetailsResponseUnmarshaller.getInstance()); return invoke(request, options); }
HSSFPolygon createPolygon() { HSSFPolygon shape = new HSSFPolygon(); shapes.add(shape); onCreate(); return shape; }
String getSheetName() { return getBoundSheetRec().getSheetName(); }
public GetDashboardResponse getDashboard(GetDashboardRequest request) { return invoke(request, new GetDashboardResponseUnmarshaller()); }
public AssociateSigninDelegateGroupsWithAccountResult associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) {request = beforeClientExecution(request);return executeAssociateSigninDelegateGroupsWithAccount(request);}
void addMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < mbr.getNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.setColumn((short) (j + mbr.getFirstColumn())); br.setRow(mbr.getRow()); br.setXFIndex(mbr.getXFAt(j)); insertCell(br); } }
String quote(String string) { return "\\" + string.replace("\\", "\\\\\\") + "\\"; }
java.nio.ByteBuffer putInt(int value) { throw new java.nio.ReadOnlyBufferException(); }
public ArrayPtg(Object[][] values2d) { int nColumns = values2d[0].length; int nRows = values2d.length; _nColumns = (short) nColumns; _nRows = (short) nRows; Object[] vv = new Object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { Object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[getValueIndex(c, r)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
public GetIceServerConfigResponse getIceServerConfig(GetIceServerConfigRequest request) { InvokeOptions options = new InvokeOptions(); GetIceServerConfigRequestMarshaller.Instance; options.responseUnmarshaller = GetIceServerConfigResponseUnmarshaller.Instance; return invoke(); }
public String toString() { StringBuilder sb = new StringBuilder(); sb.append(this.getClass().getSimpleName()).append(" ").append(getValueAsString()).append(" "); return sb.toString(); }
@Override public String toString() { return " " + _parentQuery + " "; }
void incRef() { refCount.incrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResult updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) {request = beforeClientExecution(request);return executeUpdateConfigurationSetSendingEnabled(request);}
int getNextXBATChainOffset() { return getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE; }
void multiplyByPowerOfTen(int pow10) { TenPower tp = TenPower.getInstance(Math.abs(pow10)); if (pow10 < 0) { mulShift(tp._divisor, tp._divisorShift); } else { mulShift(tp._multiplicand, tp._multiplierShift); } }
public String toString() { StringBuilder builder = new StringBuilder(); int length = getLength(); builder.append(File.separator); for (int i = 0; i < length; i++) { builder.append(getComponent(i)); if (i < (length - 1)) { builder.append(File.separator); } } return builder.toString(); }
void withFetcher() { Fetcher fetcher = new Fetcher(); fetcher.setRoleName(); }
void setProgressMonitor() { ProgressMonitor pm; }
void reset() { ptr = 0; if (!isEof()) { parseEntry(); } }
public E previous() { if (iterator.previousIndex() >= start) { return iterator.previous(); } throw new java.util.NoSuchElementException(); }
String GetNewPrefix() { return newPrefix; }
int indexOfValue(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1; }
public List<CharsRef> uniqueStems(char[] word, int length) { List<CharsRef> stems = stem(word, length); if (stems.size() < 2) { return stems; } CharArraySet terms = new CharArraySet(); List<CharsRef> deduped = new ArrayList<>(); for (CharsRef s : stems) { if (!terms.contains(s)) { deduped.add(s); terms.add(s); } } return deduped; }
public GetGatewayResponsesResponse getGatewayResponses() { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetGatewayResponsesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetGatewayResponsesResponseUnmarshaller.getInstance()); return invoke(new GetGatewayResponsesRequest(), options); }
void setPosition(long position) { currentBlockIndex = (int) (position >> outerInstance.blockBits); currentBlock = outerInstance.blocks[currentBlockIndex]; currentBlockUpto = (int) (position & outerInstance.blockMask); }
long skip(long n) { int s = (int) (available(), Math.max(,)); ptr s; return s; }
BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { _bootstrapActionConfig bootstrapActionConfig; }
public void serialize(LittleEndianOutput out1) { out1.writeShort(0); out1.writeShort(0); out1.writeShort(0); out1.writeShort(0); field_6_author.length(); out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00); if (field_5_hasMultibyte) { StringUtil.putUnicodeLE(field_6_author, out1); } else { StringUtil.putCompressedUnicode(field_6_author, out1); } if (field_7_padding != null) { out1.writeByte(Integer.parseInt(field_7_padding.toString())); } }
int lastIndexOf() { return lastIndexOf(null, 0); }
boolean add() { return addLastImpl(); }
void UnsetSection(String section, String subsection) { ConfigSnapshot src, res; do { src = state.get(); res = src.UnsetSection(section, subsection); } while (!state.compareAndSet(src, res)); }
String getTagName() { return tagName; }
void addSubRecord(int index, Object record) { subrecords.add(index, record); }
boolean remove() { synchronized (mutex) { return c.remove(); } }
TokenStream create() { return new DoubleMetaphoneFilter(input, maxCodeLength, inject); }
long getLength() { return inCoreLength(); }
void setValue() { value newValue; }
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
int get(int i) { if (i >= entries.length) { throw new IndexOutOfBoundsException(); } return entries[i]; }
public CreateRepoRequest() { this(" ", " ", " ", " ", " "); getUriPattern(); setMethod(MethodType.PUT); }
boolean IsDeltaBaseAsOffset ( ) { }
void remove(){if(expectedModCount==list.modCount){if(lastLink!=null){java.util.LinkedList.Link<ET> next_1=lastLink.next;java.util.LinkedList.Link<ET> previous_1=lastLink.previous;next_1.previous=previous_1;previous_1.next=next_1;if(lastLink==link){pos--;}link=previous_1;lastLink=null;expectedModCount++;list.size--;list.modCount++;}else{throw new IllegalStateException();}}else{throw new java.util.ConcurrentModificationException();}}
MergeShardsResponse mergeShards(MergeShardsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(MergeShardsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(MergeShardsResponseUnmarshaller.getInstance()); return invoke(request, options); }
public AllocateHostedConnectionResult allocateHostedConnection(AllocateHostedConnectionRequest request) {request = beforeClientExecution(request);return executeAllocateHostedConnection(request);}
int getBeginIndex() { return start; }
WeightedTerm getTerms(Query query) { return getTerms(); }
public java.nio.ByteBuffer compact() { throw new java.nio.ReadOnlyBufferException(); }
void decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = byte0 >>> 2; int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >>> 4); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >>> 6); values[valuesOffset++] = byte2 & 0x3F; } }
public String getHumanishName() { if (getPath() == null || getPath().isEmpty()) { throw new IllegalArgumentException(); } String s = getPath(); String[] elements; if (s.contains("/") || s.contains("\\")) { elements = s.split("[/\\\\]"); } else { elements = s.split(" "); } if (elements.length == 0) { throw new IllegalArgumentException(); } String result = elements[elements.length - 1]; if (Constants.DOT_GIT.equals(result)) { result = elements[elements.length - 2]; } else { if (result.endsWith(Constants.DOT_GIT_EXT)) { result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); } } return result; }
public DescribeNotebookInstanceLifecycleConfigResponse describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance()); return invoke(request, options); }
public String getAccessKeySecret() { return accessSecret; }
public CreateVpnConnectionResponse createVpnConnection(CreateVpnConnectionRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(CreateVpnConnectionRequestMarshaller.getInstance()); options.setResponseUnmarshaller(CreateVpnConnectionResponseUnmarshaller.getInstance()); return this.<CreateVpnConnectionResponse>invoke(request, options); }
public final DescribeVoicesResponse describeVoices(DescribeVoicesRequest request) { options = new InvokeOptions(); options.setRequestMarshaller(DescribeVoicesRequestMarshaller.Instance); options.setResponseUnmarshaller(DescribeVoicesResponseUnmarshaller.Instance); return invoke(request, options); }
public ListMonitoringExecutionsResponse listMonitoringExecutions(ListMonitoringExecutionsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListMonitoringExecutionsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ListMonitoringExecutionsResponseUnmarshaller.getInstance()); return invoke(request, options); }
public DescribeJobRequest(String vaultName, String jobId) { setVaultName(vaultName); setJobId(jobId); }
public EscherRecord getEscherRecord() { return escherRecords.get(0); }
public GetApisResponse getApis(GetApisRequest request) { InvokeOptions options = new InvokeOptions(); options.setResponseUnmarshaller(GetApisResponseUnmarshaller.getInstance()); return invoke(request, options); }
public DeleteSmsChannelResponse deleteSmsChannel(DeleteSmsChannelRequest request) { return invoke(request, DeleteSmsChannelResponseUnmarshaller.getInstance()); }
public TrackingRefUpdate getTrackingRefUpdate() { }
void print(boolean b) { print(); }
IQueryNode getChild() { return getChildren()[0]; }
NotIgnoredFilter(int workdirTreeIndex) { this.index = workdirTreeIndex; }
public AreaRecord(RecordInputStream in) { field_1_formatFlags = in.readShort(); }
public GetThumbnailRequest() { this(" ", " ", " ", " ", " "); getProtocol(); }
public DescribeTransitGatewayVpcAttachmentsResponse describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { return invoke(request, DescribeTransitGatewayVpcAttachmentsRequestMarshaller.getInstance(), DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance()); }
PutVoiceConnectorStreamingConfigurationResponse putVoiceConnectorStreamingConfiguration() { options = new InvokeOptions(); options.setRequestMarshaller(PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance()); options.setResponseUnmarshaller(PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance()); return invoke(); }
OrdRange getOrdRange(String dim) { return prefixToOrdRange.get(dim); }
@Override public String toString() { String symbol = ""; if (startIndex >= 0 && startIndex < getInputStream().size()) { symbol = getInputStream().getText(Interval.of(startIndex, startIndex)); symbol = Utils.escapeWhitespace(symbol, false); } return String.format(Locale.getDefault(), "%s('%s')", getClass().getSimpleName(), symbol); }
public E peek() { return peekFirstImpl(); }
public CreateWorkspacesResult createWorkspaces(CreateWorkspacesRequest request) {request = beforeClientExecution(request);return executeCreateWorkspaces(request);}
public Object Clone() { NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = field_1_formatIndex; return rec; }
DescribeRepositoriesResponse describeRepositories(DescribeRepositoriesRequest request) { options = new InvokeOptions(); options.requestMarshaller; options.responseUnmarshaller = DescribeRepositoriesResponseUnmarshaller.instance; return invoke(); }
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
TokenStream create() { return new HyphenatedWordsFilter(); }
public CreateDistributionWithTagsResponse createDistributionWithTags(CreateDistributionWithTagsRequest request) { return cloudFront.createDistributionWithTags(request); }
public RandomAccessFile(String fileName, String mode) { throw new UnsupportedOperationException(); }
DeleteWorkspaceImageResponse deleteWorkspaceImage(DeleteWorkspaceImageRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.getInstance()); return invoke(request, options); }
String ToHex() { return ToHex((long) value); }
public UpdateDistributionResponse updateDistribution(UpdateDistributionRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(UpdateDistributionRequestMarshaller.getInstance()); options.setResponseUnmarshaller(UpdateDistributionResponseUnmarshaller.getInstance()); return invoke(request, options); }
HSSFColor getColor(short index) { if (index == HSSFColor.Automatic.index) return HSSFColor.Automatic.getInstance(); else { byte[] b = palette.getColor(index); if (b != null) { return new CustomColor(index, b); } } return null; }
ValueEval evaluate(ValueEval operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(); }
void serialize(ILittleEndianOutput out) { out.writeShort((short) field_1_number_crn_records); out.writeShort((short) field_2_sheet_table_index); }
public DescribeDBEngineVersionsResponse describeDBEngineVersions() { return describeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
public FormatRun(short character, short fontIndex) { this._character = character; this._fontIndex = fontIndex; }
byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; { for (int i = offset; i < end; ) { char ch = chars[i++]; result[resultIndex++] = (byte) (ch >> 8); result[resultIndex++] = (byte) ch; } } return result; }
UploadArchiveResponse uploadArchive(UploadArchiveRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(UploadArchiveRequestMarshaller.getInstance()); UploadArchiveResponseUnmarshaller.getInstance(); return invoke(); }
List getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex, -1); }
@Override public boolean equals(Object obj) { if (this == obj) { return true; } if (obj == null) { return false; } if (getClass() != obj.getClass()) { return false; } AutomatonQuery other = (AutomatonQuery) obj; if (!m_compiled.equals(other.m_compiled)) { return false; } if (m_term == null) { if (other.m_term != null) { return false; } } else if (!m_term.equals(other.m_term)) { return false; } return true; }
SpanQuery makeSpanClause() { List<SpanQuery> spanQueries = new ArrayList<>(); for (Map.Entry<SpanQuery, ?> wsq : weightBySpanQuery.entrySet()) { wsq.getKey().getBoost(); spanQueries.add(wsq.getKey()); } if (spanQueries.size() == 1) return spanQueries.get(0); else return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); }
StashCreateCommand stashCreate() { return new StashCreateCommand(); }
public FieldInfo fieldInfo(String fieldName) { return byName.get(fieldName); }
public DescribeEventSourceResponse describeEventSource(DescribeEventSourceRequest request) { InvokeOptions options = new InvokeOptions(); options.setResponseUnmarshaller(DescribeEventSourceResponseUnmarshaller.Instance); return invoke(request, options); }
GetDocumentAnalysisResponse GetDocumentAnalysis(GetDocumentAnalysisRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = GetDocumentAnalysisRequestMarshaller.Instance; options.ResponseUnmarshaller = GetDocumentAnalysisResponseUnmarshaller.Instance; return Invoke(request, options); }
public CancelUpdateStackResponse cancelUpdateStack(CancelUpdateStackRequest request) { InvokeOptions options = new InvokeOptions(); CancelUpdateStackRequestMarshaller.getInstance(); options.setResponseUnmarshaller(CancelUpdateStackResponseUnmarshaller.getInstance()); return invoke(request, options); }
ModifyLoadBalancerAttributesResponse modifyLoadBalancerAttributes() { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = ModifyLoadBalancerAttributesRequestMarshaller.instance; options.responseUnmarshaller = ModifyLoadBalancerAttributesResponseUnmarshaller.instance; return invoke(new ModifyLoadBalancerAttributesRequest(), options); }
public SetInstanceProtectionResult setInstanceProtection(SetInstanceProtectionRequest request) { request = beforeClientExecution(request); return executeSetInstanceProtection(request); }
public ModifyDBProxyResponse modifyDBProxy(ModifyDBProxyRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ModifyDBProxyRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ModifyDBProxyResponseUnmarshaller.getInstance()); return invoke(request, options); }
void add(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.length) { CharsRef[] next = new CharsRef[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_OBJECT_REF)]; System.arraycopy(outputs, 0, next, 0, count); outputs = next; } if (count == endOffsets.length) { int[] next = new int[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_INT)]; System.arraycopy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.length) { int[] next = new int[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_INT)]; System.arraycopy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRef(); } outputs[count].copyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
public FetchLibrariesRequest() { this(" ", " ", " ", " ", " "); }
boolean exists() { return false; }
public FilterOutputStream(OutputStream out) { this.out = out; }
public ScaleClusterRequest() { super(" ", " ", " ", " ", " "); uriPattern(" "); method(); }
IDataValidationConstraint createTimeConstraint(int operatorType, String formula1, String formula2) { return DVConstraint.createTimeConstraint(operatorType, formula1, formula2); }
public ListObjectParentPathsResponse listObjectParentPaths() { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListObjectParentPathsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ListObjectParentPathsResponseUnmarshaller.getInstance()); return invoke(options, ListObjectParentPathsResponse.class); }
public DescribeCacheSubnetGroupsResponse DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = DescribeCacheSubnetGroupsRequestMarshaller.Instance; options.ResponseUnmarshaller = DescribeCacheSubnetGroupsResponseUnmarshaller.Instance; return this.<DescribeCacheSubnetGroupsResponse>Invoke(request, options); }
public void setSharedFormula(boolean flag) { field_5_options = sharedFormula.setShortBoolean(field_5_options, flag); }
boolean isReuseObjects() {}
ErrorNode addErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); addChild(t); return t; }
public LatvianStemFilterFactory(Map<String, String> args) { super(args); if (args.size() > 0) { throw new IllegalArgumentException(" " + args); } }
public RemoveSourceIdentifierFromSubscriptionResult removeSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) { request = beforeClientExecution(request); return executeRemoveSourceIdentifierFromSubscription(request); }
TokenFilterFactory ForName( , Map<String, String> args) { return loader.NewInstance( , ); }
public AddAlbumPhotosRequest() { this(" ", " ", " ", " ", " "); Protocol; }
public GetThreatIntelSetResult getThreatIntelSet(GetThreatIntelSetRequest request) { request = beforeClientExecution(request); return executeGetThreatIntelSet(request); }
TreeFilter clone() { return new AndTreeFilter.Binary(a.clone(), b.clone()); }
public boolean equals(Object o) { return o instanceof ClassName; }
boolean hasArray() { return protectedHasArray; }
public UpdateContributorInsightsResponse updateContributorInsights(UpdateContributorInsightsRequest request) { return invoke(request, UpdateContributorInsightsRequestMarshaller.getInstance(), UpdateContributorInsightsResponseUnmarshaller.getInstance()); }
void UnwriteProtectWorkbook() { records.remove(); fileShare = null; writeProtect = null; }
public SolrSynonymParser(boolean dedup, boolean expand, Analyzer analyzer) { super(dedup, analyzer); this.expand = expand; }
public RequestSpotInstancesResponse requestSpotInstances(RequestSpotInstancesRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = RequestSpotInstancesRequestMarshaller.Instance; options.responseUnmarshaller = RequestSpotInstancesResponseUnmarshaller.Instance; return invoke(request, options); }
byte getObjectData(){return findObjectRecord().getObjectData();}
GetContactAttributesResponse getContactAttributes(GetContactAttributesRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetContactAttributesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetContactAttributesResponseUnmarshaller.getInstance()); return invoke(request, options); }
String toString() { return getKey() + + getValue(); }
public ListTextTranslationJobsResult listTextTranslationJobs(ListTextTranslationJobsRequest request) { request = beforeClientExecution(request); return executeListTextTranslationJobs(request); }
public GetContactMethodsResponse getContactMethods(GetContactMethodsRequest request) { return invoke(request, new GetContactMethodsResponseUnmarshaller()); }
short lookupIndexByName(String name) { FunctionMetadata fd = getInstance().getFunctionByNameInternal(name); if (fd == null) { return -1; } return fd.getIndex(); }
public DescribeAnomalyDetectorsResponse describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeAnomalyDetectorsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeAnomalyDetectorsResponseUnmarshaller.getInstance()); return invoke(request, options); }
String insertId( , ObjectId changeId) { return insertId( , , ); }
long getObjectSize(AnyObjectId objectId, int typeHint) throws IOException { long sz = db.getObjectSize(this, objectId, typeHint); if (sz < 0) { if (typeHint == Constants.OBJ_ANY) { throw new MissingObjectException(objectId.copy(), "object"); } throw new MissingObjectException(objectId.copy(), typeHint); } return sz; }
public ImportInstallationMediaResponse importInstallationMedia(ImportInstallationMediaRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ImportInstallationMediaRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ImportInstallationMediaResponseUnmarshaller.getInstance()); return invoke(request, options); }
public PutLifecycleEventHookExecutionStatusResult putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) {request = beforeClientExecution(request);return executePutLifecycleEventHookExecutionStatus(request);}
NumberPtg(ILittleEndianInput in1) { field_1_value(); }
public GetFieldLevelEncryptionConfigResponse getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance()); return invoke(request, options); }
public DescribeDetectorResult describeDetector(DescribeDetectorRequest request) { request = beforeClientExecution(request); return executeDescribeDetector(request); }
public ReportInstanceStatusResponse reportInstanceStatus(ReportInstanceStatusRequest request) { return invoke(request, ReportInstanceStatusRequestMarshaller.getInstance(), ReportInstanceStatusResponseUnmarshaller.getInstance()); }
public DeleteAlarmResponse deleteAlarm(DeleteAlarmRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteAlarmRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteAlarmResponseUnmarshaller.getInstance()); return invoke(request, options); }
TokenStream create() { return new PortugueseStemFilter(); }
FtCblsSubRecord() { reserved = new byte[0]; }
boolean remove() { synchronized (mutex) { return c.remove(); } }
GetDedicatedIpResponse getDedicatedIp(GetDedicatedIpRequest request) { InvokeOptions options = new InvokeOptions(); options.setResponseUnmarshaller(GetDedicatedIpResponseUnmarshaller.getInstance()); return invoke(request, options); }
public String toString() { return precedence + " "; }
public ListStreamProcessorsResult listStreamProcessors(ListStreamProcessorsRequest request) { request = beforeClientExecution(request); return executeListStreamProcessors(request); }
public DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) { this._loadBalancerName = loadBalancerName; this._policyName = policyName; }
WindowProtectRecord(int options) { this._options = options; }
UnbufferedCharStream(int bufferSize) { n = 0; data = new int[bufferSize]; }
public GetOperationsResponse getOperations(GetOperationsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetOperationsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetOperationsResponseUnmarshaller.getInstance()); return invoke(request, options); }
void copyRawTo(byte[] b, int o) { NB.EncodeInt32(, , ); NB.EncodeInt32(, o + 4, ); NB.EncodeInt32(, o + 8, ); NB.EncodeInt32(, o + 12, ); NB.EncodeInt32(, o + 16, ); }
public WindowOneRecord(RecordInputStream in) { field_1_h_hold = in.readShort(); field_2_v_hold = in.readShort(); field_3_width = in.readShort(); field_4_height = in.readShort(); field_5_options = in.readShort(); field_6_active_sheet_index = in.readShort(); field_7_first_visible_tab_index = in.readShort(); field_8_num_selected_tabs = in.readShort(); field_9_tab_width_ratio = in.readShort(); }
public StopWorkspacesResult stopWorkspaces(StopWorkspacesRequest request) { request = beforeClientExecution(request); return executeStopWorkspaces(request); }
void close() throws IOException { if (isOpen) { isOpen = false; try { dump(); } finally { try { channel.truncate(); } finally { channel.close(); } } } }
public DescribeMatchmakingRuleSetsResponse describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { final InvokeOptions options = new InvokeOptions(); options.requestMarshaller = DescribeMatchmakingRuleSetsRequestMarshaller.INSTANCE; options.responseUnmarshaller = DescribeMatchmakingRuleSetsResponseUnmarshaller.INSTANCE; return invoke(request, options); }
String getPronunciation(int wordId, char surface, int off, int len){return null;}
public String getPath() { return pathStr; }
double devsq(double[] v) { double r = Double.NaN; if (v != null && v.length >= 1) { double m = 0; double s = 0; int n = v.length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } return r; }
public DescribeResizeResult describeResize(DescribeResizeRequest request) {request = beforeClientExecution(request);return executeDescribeResize(request);}
public boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
int getEnd() { return end(); }
void traverse(ICellHandler handler) { int firstRow = range.getFirstRow(); int lastRow = range.getLastRow(); int firstColumn = range.getFirstColumn(); int lastColumn = range.getLastColumn(); int width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); IRow currentRow = null; ICell currentCell = null; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ++ctx.rowNumber) { currentRow = sheet.getRow(ctx.rowNumber); if (currentRow == null) { continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ++ctx.colNumber) { currentCell = currentRow.getCell(ctx.colNumber); if (currentCell == null) { continue; } if (isEmpty() && !traverseEmptyCells) { continue; } handler.onCell(currentCell, (ctx.rowNumber - firstRow) * width + (ctx.colNumber - firstColumn + 1)); } } }
int getReadIndex() { return _ReadIndex; }
public int compareTo(ScoreTerm other) { if (term.bytesEquals(other.term)) { return 0; } if (boost == other.boost) { return term.compareTo(other.term); } else { return Float.compare(boost, other.boost); } }
int normalize(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = StemmerUtil.delete(s, i, len); i--; break; default: break; } } return len; }
void serialize(ILittleEndianOutput out) { ( ) ; }
public DiagnosticErrorListener(boolean exactOnly) { this.exactOnly = exactOnly; }
KeySchemaElement(String attributeName, KeyType keyType) { this._attributeName = attributeName; this._keyType = keyType; }
public GetAssignmentResult getAssignment(GetAssignmentRequest request) {request = beforeClientExecution(request);return executeGetAssignment(request);}
boolean hasObject() { return findOffset() != -1; }
GroupingSearch setAllGroups() { return this; }
void setMultiValued(String dimName, boolean v) { synchronized (this) { DimConfig fieldType = fieldTypes.get(dimName); if (fieldType == null) { fieldType = new DimConfig(); fieldTypes.put(dimName, fieldType); } fieldType.isMultiValued = v; } }
int getCellsVal() { int size = 0; for (char c : cells.keySet()) { Cell e = at(); if (e.cmd >= 0) { ; } } return size; }
public DeleteVoiceConnectorResult deleteVoiceConnector(DeleteVoiceConnectorRequest request) {request = beforeClientExecution(request);return executeDeleteVoiceConnector(request);}
public DeleteLifecyclePolicyResponse deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteLifecyclePolicyRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteLifecyclePolicyResponseUnmarshaller.getInstance()); return invoke(request, options); }
