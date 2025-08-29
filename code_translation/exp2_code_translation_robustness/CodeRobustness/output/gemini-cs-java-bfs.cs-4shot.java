void serialize(ILittleEndianOutput out1) { out1.writeShort(); }
public void addAll(BlockList src) { if (src.size == 0) return; for (int srcDirIdx = 0; srcDirIdx < src.tailDirIdx; ++srcDirIdx) addAll(src.directory[srcDirIdx]); if (src.tailBlkIdx != 0) addAll(src.tailBlock, 0, src.tailBlkIdx); }
public void writeByte(byte b) {if (currentBlock == null || upto == blockSize) {if (currentBlock != null) {blocks.add(currentBlock);}currentBlock = new byte[blockSize];upto = 0;}currentBlock[upto++] = b;}
ObjectId getObjectId() { return objectId; }
public DeleteDomainEntryResult deleteDomainEntry(DeleteDomainEntryRequest request) {request = beforeClientExecution(request);return executeDeleteDomainEntry(request);}
public long ramBytesUsed() { return fst == null ? 0 : fst.getSizeInBytes(); }
public String getFullMessage() { String buffer; int msgB = RawParseUtils.tagMessage(raw, 0); if (msgB < 0) { buffer = ""; } else { Charset enc = RawParseUtils.parseEncoding(raw); buffer = RawParseUtils.decode(enc, raw, msgB, raw.length - msgB); } return buffer; }
public POIFSFileSystem() { HeaderBlock headerBlock = new HeaderBlock(); _property_table = new PropertyTable(headerBlock); _documents = new ArrayList(); _root = null; }
public void init(int address, int upto) {slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; assert slice != null; assert upto < slice.length; offset0 = address & ByteBlockPool.BYTE_BLOCK_MASK;}
public SubmoduleAddCommand setPath(String path) { this.path = path; return this; }
public ListIngestionsResult listIngestions(ListIngestionsRequest request) {request = beforeClientExecution(request);return executeListIngestions(request);}
public QueryParserTokenManager(CharStream stream, int lexState){this(stream);switchTo(lexState);}
public GetShardIteratorResult getShardIterator(GetShardIteratorRequest request) {request = beforeClientExecution(request);return executeGetShardIterator(request);}
public ModifyStrategyResult modifyStrategy(ModifyStrategyRequest request) {request = beforeClientExecution(request);return executeModifyStrategy(request);}
public boolean isEnd() throws IOException { return in.available() == 0 && bytes.remaining() == 0; }
public EscherOptRecord getOptRecord() { return _optRecord; }
public synchronized int read(byte b[], int off, int len) { if (b == null) { throw new NullPointerException(); } else if (off < 0 || len < 0 || len > b.length - off) { throw new IndexOutOfBoundsException(); } if (pos >= count) { return -1; } int avail = count - pos; if (len > avail) { len = avail; } if (len <= 0) { return 0; } System.arraycopy(buf, pos, b, off, len); pos += len; return len; }
public OpenNLPSentenceBreakIterator(SentenceDetector sentenceDetector) { this.sentenceDetector = sentenceDetector; }
void print(String str) { write(String.valueOf(str)); }
} { ; NotImplementedFunctionException functionName ) cause , functionName ( super : functionName . ) cause NotImplementedException , functionName String (
return nextEntry().next().value;
public void readBytes(byte[] b, int offset, int len, boolean useBuffer) { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) { System.arraycopy(buffer, bufferPosition, b, offset, len); } bufferPosition += len; } else { if (available > 0) { System.arraycopy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { refill(); if (bufferLength < len) { System.arraycopy(buffer, 0, b, offset, bufferLength); throw new RuntimeException("read past EOF"); } else { System.arraycopy(buffer, 0, b, offset, len); bufferPosition = len; } } else { long after = bufferStart + bufferPosition + len; if (after > length()) { throw new RuntimeException("read past EOF"); } readInternal(b, offset, len); bufferStart = after; bufferPosition = 0; } } }
public TagQueueResponse tagQueue(TagQueueRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(TagQueueRequestMarshaller.getInstance()); options.setResponseUnmarshaller(TagQueueResponseUnmarshaller.getInstance()); return invoke(request, options); }
void remove() { throw new UnsupportedOperationException(); }
public ModifyCacheSubnetGroupResult modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) {request = beforeClientExecution(request);return executeModifyCacheSubnetGroup(request);}
public void setParams(String params) { StringTokenizer st = new StringTokenizer(params, " "); if (st.hasMoreTokens()) { culture = st.nextToken(); } if (st.hasMoreTokens()) { ignore = st.nextToken(); } if (st.hasMoreTokens()) { string = st.nextToken(); } }
return invoke(request, new InvokeOptions().withRequestMarshaller(DeleteDocumentationVersionRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteDocumentationVersionResponseUnmarshaller.getInstance()));
public boolean equals(Object obj) { if (obj == null || !(obj instanceof FacetLabel)) return false; FacetLabel other = (FacetLabel) obj; if (this.getComponents().length != other.getComponents().length) return false; for (int i = this.getComponents().length - 1; i >= 0; --i) { if (!this.getComponents()[i].equals(other.getComponents()[i])) return false; } return true; }
public GetInstanceAccessDetailsResult getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) {request = beforeClientExecution(request);return executeGetInstanceAccessDetails(request);}
HSSFPolygon shape = new HSSFPolygon(parent, anchor); shapes.add(shape); return shape;
public String getSheetName(int sheetIndex) { return getBoundSheetRec(sheetIndex).getSheetName(); }
public GetDashboardResponse getDashboard(GetDashboardRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetDashboardRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetDashboardResponseUnmarshaller.getInstance()); return invoke(options, request); }
public AssociateSigninDelegateGroupsWithAccountResult associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) {request = beforeClientExecution(request);return executeAssociateSigninDelegateGroupsWithAccount(request);}
void addMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < mbr.getNumColumns(); ++j) { BlankRecord br = new BlankRecord(); br.setColumn(mbr.getFirstColumn() + j); br.setRow(mbr.getRow()); br.setXFIndex(mbr.getXFAt(mbr.getRow())); insertCell(br); } }
public String quote(String string) { StringBuilder sb = new StringBuilder(); int k = 0; while ((k = StringHelper.Sharpen.IndexOf(string, "\\", k)) >= 0) { sb.append(StringHelper.Sharpen.Substring(string, 0, k)); sb.append("\\\\"); string = StringHelper.Sharpen.Substring(string, k + 2); k = 0; } sb.append(string); return sb.toString(); }
java.nio.ByteBuffer putInt(int value) { throw new java.nio.ReadOnlyBufferException(); }
public ArrayPtg(Object[][] values2d){_reserved0Int = 0;_reserved1Short = 0;_reserved2Byte = 0;int nRows = values2d.length;int nColumns = values2d[0].length;_nRows = (short)nRows;_nColumns = (short)nColumns;Object[] vv = new Object[_nRows * _nColumns];for (int r = 0; r < nRows; r++){Object[] rowData = values2d[r];for (int c = 0; c < nColumns; c++){vv[GetValueIndex(c, r)] = rowData[c];}}_arrayValues = vv;}
public GetIceServerConfigResult getIceServerConfig(GetIceServerConfigRequest request) {request = beforeClientExecution(request);return executeGetIceServerConfig(request);}
public String toString() { return new StringBuilder().append(getClass().getSimpleName()).append(" = ").append(getValueAsString()).toString(); }
public String toString() { return _parentQuery + " " + field; }
void incRef() { refCount.incrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResponse updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = UpdateConfigurationSetSendingEnabledRequestMarshaller.Instance; options.ResponseUnmarshaller = UpdateConfigurationSetSendingEnabledResponseUnmarshaller.Instance; return invoke(request, options); }
public int getNextXBATChainOffset() { return getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE; }
void multiplyByPowerOfTen(int pow10) { TenPower tp = TenPower.getInstance(Math.abs(pow10)); if (pow10 < 0) { _divisor.mulShift(tp, _divisorShift); } else { _multiplicand.mulShift(tp, _multiplierShift); } }
public String toString() { StringBuilder builder = new StringBuilder(); for (int i = 0; i < length; i++) { if (i > 0) { builder.append(File.separatorChar); } builder.append(getComponent(i)); } return builder.toString(); }
withFetcher void } { ) ( ; ; fetcher ECSMetadataServiceCredentialsFetcher fetcher ) ( setRoleName . fetcher . fetcher .
public void setProgressMonitor(ProgressMonitor pm) { this.progressMonitor = pm; }
void parseEntry() { if (!first()) { reset(); } if (!eof()) { ptr = 0; } }
public E previous() { if (iterator.previousIndex() < 0) { throw new java.util.NoSuchElementException(); } return iterator.previous(); }
public String getNewPrefix() { return newPrefix; }
public int indexOfValue(int value) { for (int i = 0; i < mSize; ++i) { if (mValues[i] == value) { return i; } } return -1; }
private static List<CharsRef> uniqueStems(List<CharsRef> stems) { if (stems.size() < 2) { return stems; } CharArraySet terms = new CharArraySet(8, false); List<CharsRef> deduped = new ArrayList<>(); for (CharsRef s : stems) { if (!terms.contains(s)) { terms.add(s); deduped.add(s); } } return deduped; }
public GetGatewayResponsesResponse getGatewayResponses(GetGatewayResponsesRequest request) { return invoke(request, new InvokeOptions().setRequestMarshaller(GetGatewayResponsesRequestMarshaller.getInstance()).setResponseUnmarshaller(GetGatewayResponsesResponseUnmarshaller.getInstance())); }
void setPosition(long position) { currentBlockIndex = (int) (position >> outerInstance.blockBits); currentBlock = outerInstance.blocks[currentBlockIndex]; currentBlockUpto = (int) (position & outerInstance.blockMask); }
long s = Math.max(0, Math.min(n, (long)available - ptr)); ptr += (int)s; return s;
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) {
public void serialize(LittleEndianOutput out1) { out1.writeShort(getInitialBytes()); out1.writeShort(0); out1.writeShort(0); out1.writeShort(0); out1.writeShort(0); out1.writeByte(field_6_author.length()); out1.writeByte(field_5_hasMultibyte ? 1 : 0); if (field_5_hasMultibyte) { StringUtil.putUnicodeLE(field_6_author, out1); } else { StringUtil.putCompressedUnicode(field_6_author, out1); } }
public int lastIndexOf(String string) { return string.lastIndexOf(','); }
public boolean add(E object) { return addLastImpl(); }
void unsetSection(String section, String subsection) { ConfigSnapshot src, res; do { src = state.get(); res = src.unsetSection(section, subsection); } while (!state.compareAndSet(src, res)); }
public String getTagName() { return tagName; }
void addSubRecord(int index, SubRecord element) { subrecords.add(index, element); }
public boolean remove(Object object) { synchronized (mutex) { return c.remove(object); } }
@Override public TokenStream create(TokenStream input) { return new DoubleMetaphoneFilter(input, maxCodeLength, inject); }
long length() { return inCoreLength(); }
public void setValue(boolean newValue) { value = newValue; }
Pair(ContentSource newSource, ContentSource oldSource) { this.newSource = newSource; this.oldSource = oldSource; }
public int get(int i) { if (count <= i) throw new IndexOutOfBoundsException(); return entries[i]; }
public CreateRepoResult createRepo(CreateRepoRequest request) {request = beforeClientExecution(request);return executeCreateRepo(request);}
public boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public void remove() {Link link = lastLink.previous;if (list.modCount != expectedModCount) {throw new ConcurrentModificationException();} else if (lastLink == null) {throw new IllegalStateException();}link.next = lastLink.next;link.next.previous = link;list.size--;list.modCount++;expectedModCount++;lastLink = null;pos--;}
public MergeShardsResult mergeShards(MergeShardsRequest request) {request = beforeClientExecution(request);return executeMergeShards(request);}
public AllocateHostedConnectionResult allocateHostedConnection(AllocateHostedConnectionRequest request) {request = beforeClientExecution(request);return executeAllocateHostedConnection(request);}
public int getBeginIndex() { return start; }
public WeightedTerm[] getTerms(Query query) { return query.getTerms(); }
ByteBuffer compact() { throw new java.nio.ReadOnlyBufferException(); }
void decode(int iterations, int[] values, int valuesOffset, byte[] blocks, int blocksOffset) { for (int i = 0; i < iterations; i++) { byte byte0 = blocks[blocksOffset++]; byte byte1 = blocks[blocksOffset++]; byte byte2 = blocks[blocksOffset++]; values[valuesOffset++] = (byte0 & 0xFF) >> 2; values[valuesOffset++] = ((byte0 & 3) << 4) | ((byte1 & 0xFF) >> 4); values[valuesOffset++] = ((byte1 & 15) << 2) | ((byte2 & 0xFF) >> 6); values[valuesOffset++] = byte2 & 63; } }
public String getHumanishName() { String result = getDirectory().getName(); if (Constants.DOT_GIT.equals(result)) { result = getDirectory().getParentFile().getName(); } else if (result.endsWith(Constants.DOT_GIT_EXT)) { result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); } return result; }
public DescribeNotebookInstanceLifecycleConfigResponse describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { InvokeOptions options = new InvokeOptions(); options.setMarshaller(DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance()); return invoke(request, options); }
public String getAccessKeySecret() { return accessSecret; }
public CreateVpnConnectionResult createVpnConnection(CreateVpnConnectionRequest request) {request = beforeClientExecution(request);return executeCreateVpnConnection(request);}
public DescribeVoicesResult describeVoices(DescribeVoicesRequest request) {request = beforeClientExecution(request);return executeDescribeVoices(request);}
public ListMonitoringExecutionsResult listMonitoringExecutions(ListMonitoringExecutionsRequest request) {request = beforeClientExecution(request);return executeListMonitoringExecutions(request);}
public DescribeJobResult describeJob(String vaultName, String jobId) {return describeJob(new DescribeJobRequest(vaultName, jobId));}
public EscherRecord getEscherRecord(int index) { return escherRecords[index]; }
public GetApisResult getApis(GetApisRequest request) { request = beforeClientExecution(request); return executeGetApis(request); }
public DeleteSmsChannelResult deleteSmsChannel(DeleteSmsChannelRequest request) {request = beforeClientExecution(request);return executeDeleteSmsChannel(request);}
public TrackingRefUpdate getTrackingRefUpdate() { return trackingRefUpdate; }
void print(boolean b) { print(Boolean.toString(b)); }
QueryNode getChild() { return getChildren()[0]; }
workdirTreeIndex.notIgnored(index);
field_1_formatFlags = in1.readShort();
request.setProtocol(ProtocolType.HTTPS);
public DescribeTransitGatewayVpcAttachmentsResponse describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeTransitGatewayVpcAttachmentsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance()); return invoke(request, options); }
public PutVoiceConnectorStreamingConfigurationResult putVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) {request = beforeClientExecution(request);return executePutVoiceConnectorStreamingConfiguration(request);}
public OrdRange getOrdRange(String dim) { return prefixToOrdRange.get(dim); }
public String toString() { String symbol = ""; if (startIndex >= 0 && startIndex < getInputStream().size()) { symbol = getInputStream().getText(Interval.of(startIndex, startIndex)); } return String.format(Locale.getDefault(), "%s('%s')", LexerNoViableAltException.class.getSimpleName(), symbol); }
public E peek() { return peekFirstImpl(); }
public CreateWorkspacesResult createWorkspaces(CreateWorkspacesRequest request) {request = beforeClientExecution(request);return executeCreateWorkspaces(request);}
public Object clone() { NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = field_1_formatIndex; return rec; }
public DescribeRepositoriesResponse describeRepositories(DescribeRepositoriesRequest request) { return invoke(request, DescribeRepositoriesRequestMarshaller.getInstance(), DescribeRepositoriesResponseUnmarshaller.getInstance()); }
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public TokenStream create(TokenStream input) { return new HyphenatedWordsFilter(input); }
public CreateDistributionWithTagsResponse createDistributionWithTags(CreateDistributionWithTagsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(CreateDistributionWithTagsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(CreateDistributionWithTagsResponseUnmarshaller.getInstance()); return invoke(request, options); }
public RandomAccessFile(String fileName, String mode) { throw new UnsupportedOperationException(); }
public DeleteWorkspaceImageResult deleteWorkspaceImage(DeleteWorkspaceImageRequest request) {request = beforeClientExecution(request);return executeDeleteWorkspaceImage(request);}
public String toHex(long value) { return Long.toHexString(value); }
public UpdateDistributionResponse updateDistribution(UpdateDistributionRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = UpdateDistributionRequestMarshaller.instance; options.responseUnmarshaller = UpdateDistributionResponseUnmarshaller.instance; return invoke(request, options); }
public HSSFColor getColor(short index){if (index == HSSFColor.Automatic.getIndex()){return HSSFColor.Automatic.getInstance();}byte[] b = palette.getColor(index);if (b != null){return new CustomColor(index, b);}else{return null;}}
public ValueEval evaluate(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(); }
public void serialize(LittleEndianOutput out1) { out1.writeShort(field_1_number_crn_records); out1.writeShort(field_2_sheet_table_index); }
DescribeDBEngineVersionsResponse describeDBEngineVersions() { return describeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
public FormatRun(short fontIndex, short character) { this._fontIndex = fontIndex; this._character = character; }
public static byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; for (int i = offset, resultIndex = 0; i < offset + length; i++, resultIndex += 2) { char ch = chars[i]; result[resultIndex] = (byte) (ch >> 8); result[resultIndex + 1] = (byte) ch; } return result; }
public UploadArchiveResponse uploadArchive(UploadArchiveRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(UploadArchiveRequestMarshaller.INSTANCE); options.setResponseUnmarshaller(UploadArchiveResponseUnmarshaller.INSTANCE); return invoke(request, options); }
public List<IToken> getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex, -1); }
public boolean equals(Object obj) { if (this == obj) return true; if (obj == null) return false; if (getClass() != obj.getClass()) return false; AutomatonQuery other = (AutomatonQuery) obj; if (m_term == null) { if (other.m_term != null) return false; } else if (!m_term.equals(other.m_term)) return false; if (m_compiled == null) { if (other.m_compiled != null) return false; } else if (!m_compiled.equals(other.m_compiled)) return false; return true; }
private SpanQuery makeSpanClause(Map<SpanQuery, Float> weightBySpanQuery) { List<SpanQuery> spanQueries = new ArrayList<>(); for (Map.Entry<SpanQuery, Float> wsq : weightBySpanQuery.entrySet()) { spanQueries.add(new SpanBoostQuery(wsq.getKey(), wsq.getValue())); } if (spanQueries.size() == 1) { return spanQueries.get(0); } return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); }
StashCreateCommand StashCreate() { return new StashCreateCommand(); }
public FieldInfo getField(String fieldName) { return byName.get(fieldName); }
public DescribeEventSourceResult describeEventSource(DescribeEventSourceRequest request) {request = beforeClientExecution(request);return executeDescribeEventSource(request);}
public GetDocumentAnalysisResult getDocumentAnalysis(GetDocumentAnalysisRequest request) {request = beforeClientExecution(request);return executeGetDocumentAnalysis(request);}
public CancelUpdateStackResult cancelUpdateStack(CancelUpdateStackRequest request) {request = beforeClientExecution(request);return executeCancelUpdateStack(request);}
public ModifyLoadBalancerAttributesResult modifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) {request = beforeClientExecution(request);return executeModifyLoadBalancerAttributes(request);}
public SetInstanceProtectionResponse setInstanceProtection(SetInstanceProtectionRequest request) { return invoke(new Marshaller.RequestMarshaller<SetInstanceProtectionRequest>(request, SetInstanceProtectionRequestMarshaller.getInstance()), new Marshaller.ResponseUnmarshaller<SetInstanceProtectionResponse>(SetInstanceProtectionResponseUnmarshaller.getInstance())); }
public ModifyDBProxyResult modifyDBProxy(ModifyDBProxyRequest request) {request = beforeClientExecution(request);return executeModifyDBProxy(request);}
public void add(CharsRef output, int offset, int len, int endOffset, int posLength) { if (count == outputs.length) { int next = ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF); CharsRef[] nextOutputs = new CharsRef[next]; System.arraycopy(outputs, 0, nextOutputs, 0, count); outputs = nextOutputs; } if (count == endOffsets.length) { int next = ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32); int[] nextEndOffsets = new int[next]; System.arraycopy(endOffsets, 0, nextEndOffsets, 0, count); endOffsets = nextEndOffsets; } if (count == posLengths.length) { int next = ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32); int[] nextPosLengths = new int[next]; System.arraycopy(posLengths, 0, nextPosLengths, 0, count); posLengths = nextPosLengths; } if (output == null) { outputs[count] = null; } else { outputs[count] = new CharsRef(); outputs[count].copyChars(output.chars, offset, len); } endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
public FetchLibrariesRequest() {super(" ", " ", " ", " ", " ", " ");setProtocol(ProtocolType.HTTPS);}
boolean exists() { return objects.exists(); }
} {  ; FilterOutputStream out ) java ( out . . .
}
public IDataValidationConstraint createTimeConstraint(int operatorType, String formula1, String formula2){return DVConstraint.createTimeConstraint(operatorType, formula1, formula2);}
public ListObjectParentPathsResult listObjectParentPaths(ListObjectParentPathsRequest request) {request = beforeClientExecution(request);return executeListObjectParentPaths(request);}
public DescribeCacheSubnetGroupsResult describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) {request = beforeClientExecution(request);return executeDescribeCacheSubnetGroups(request);}
void setSharedFormula(boolean flag) { field_5_options.setShortBoolean(sharedFormula, flag); }
public boolean isReuseObjects() { return reuseObjects; }
public ErrorNode addErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); parent.addChild(t); return t; }
public LatvianStemFilterFactory(Map<String, String> args) { super(args); if (!args.isEmpty()) { throw new IllegalArgumentException("Unknown parameters: " + args); } }
public RemoveSourceIdentifierFromSubscriptionResult removeSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) { request = beforeClientExecution(request); return executeRemoveSourceIdentifierFromSubscription(request); }
public static TokenFilterFactory forName(String name, Map<String, String> args) { return loader.newInstance(name, args); }
public AddAlbumPhotosRequest() { setProtocol(ProtocolType.HTTPS); }
public GetThreatIntelSetResult getThreatIntelSet(GetThreatIntelSetRequest request) { request = beforeClientExecution(request); return executeGetThreatIntelSet(request); }
public TreeFilter clone() { return new Binary.AndTreeFilter(a.clone(), b.clone()); }
public boolean equals(Object o) { return o != null; }
public boolean hasArray() { return protectedHasArray(); }
public UpdateContributorInsightsResult updateContributorInsights(UpdateContributorInsightsRequest request) {request = beforeClientExecution(request);return executeUpdateContributorInsights(request);}
public void unwriteProtectWorkbook() { records.remove(fileShare); records.remove(writeProtect); fileShare = null; writeProtect = null; }
public SolrSynonymParser(boolean expand, Analyzer analyzer, boolean dedup) {
public SpotInstances requestSpotInstances(RequestSpotInstancesRequest request) { request = beforeClientExecution(request); return executeRequestSpotInstances(request); }
public byte[] getObjectData() { return ObjectData.findObjectRecord(); }
public GetContactAttributesResult getContactAttributes(GetContactAttributesRequest request) {request = beforeClientExecution(request);return executeGetContactAttributes(request);}
public String toString() { return getKey() + " " + getValue(); }
public ListTextTranslationJobsResponse listTextTranslationJobs(ListTextTranslationJobsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListTextTranslationJobsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ListTextTranslationJobsResponseUnmarshaller.getInstance()); return invoke(request, options); }
public GetContactMethodsResult getContactMethods(GetContactMethodsRequest request) {request = beforeClientExecution(request);return executeGetContactMethods(request);}
public short lookupIndexByName(String name) { FunctionMetadata fd = getInstance().getFunctionByNameInternal(name); if (fd == null) { return (short) -1; } return fd.getIndex(); }
public DescribeAnomalyDetectorsResponse describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeAnomalyDetectorsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeAnomalyDetectorsResponseUnmarshaller.getInstance()); return invoke(request, options); }
public ObjectId insertId(String message, ObjectId changeId) { return changeId; }
public long getObjectSize(AnyObjectId objectId, int typeHint) throws MissingObjectException, IOException { long sz = db.getObjectSize(objectId.copy(), typeHint); if (sz < 0) { if (typeHint == Constants.OBJ_ANY) throw new MissingObjectException(objectId.copy(), " "); else throw new MissingObjectException(objectId.copy(), typeHint); } return sz; }
public ImportInstallationMediaResult importInstallationMedia(ImportInstallationMediaRequest request) {request = beforeClientExecution(request);return executeImportInstallationMedia(request);}
public PutLifecycleEventHookExecutionStatusResult putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) {request = beforeClientExecution(request);return executePutLifecycleEventHookExecutionStatus(request);}
public NumberPtg(LittleEndianInput in1) { field_1_value = in1.readDouble(); }
public GetFieldLevelEncryptionConfigResult getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.getInstance()).withResponseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance())); }
public DescribeDetectorResult describeDetector(DescribeDetectorRequest request) {request = beforeClientExecution(request);return executeDescribeDetector(request);}
public ReportInstanceStatusResponse reportInstanceStatus(ReportInstanceStatusRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ReportInstanceStatusRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ReportInstanceStatusResponseUnmarshaller.getInstance()); return invoke(request, options); }
public DeleteAlarmResponse deleteAlarm(DeleteAlarmRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteAlarmRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteAlarmResponseUnmarshaller.getInstance()); return invoke(request, options); }
public TokenStream create(TokenStream input) { return new PortugueseStemFilter(input); }
reserved = new byte[ENCODED_SIZE];
boolean remove(Object object) { synchronized(mutex) { return c.remove(); } }
public GetDedicatedIpResponse getDedicatedIp(GetDedicatedIpRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetDedicatedIpRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetDedicatedIpResponseUnmarshaller.getInstance()); return invoke(request, options); }
public String toString() { return " " + precedence; }
public ListStreamProcessorsResponse listStreamProcessors(ListStreamProcessorsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListStreamProcessorsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ListStreamProcessorsResponseUnmarshaller.getInstance()); return invoke(request, options); }
public DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) { setLoadBalancerName(loadBalancerName); setPolicyName(policyName); }
int options(WindowProtectRecord _options) { ; }
UnbufferedCharStream(int bufferSize) { n = 0; data = new int[bufferSize]; }
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetOperationsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetOperationsResponseUnmarshaller.getInstance()); return invoke(request, options);
void copyRawTo(byte[] b, int o) { EncodeInt32.NB(descriptorProtoCount, b, o); EncodeInt32.NB(enumDescriptorProtoCount, b, o + 4); EncodeInt32.NB(serviceDescriptorProtoCount, b, o + 8); EncodeInt32.NB(extensionDescriptorProtoCount, b, o + 12); EncodeInt32.NB(options, b, o + 16); }
public WindowOneRecord(RecordInputStream in1) { field_1_h_hold = in1.readShort(); field_2_v_hold = in1.readShort(); field_3_width = in1.readShort(); field_4_height = in1.readShort(); field_5_options = in1.readShort(); field_6_active_sheet = in1.readShort(); field_7_first_visible_tab = in1.readShort(); field_8_num_selected_tabs = in1.readShort(); field_9_tab_width_ratio = in1.readShort(); }
public StopWorkspacesResult stopWorkspaces(StopWorkspacesRequest request) {request = beforeClientExecution(request);return executeStopWorkspaces(request);}
throws IOException close void } { ) ( ) isOpen ( if } { try ; finally } { isOpen } { ; try dump finally } { ) ( } { ; try finally } { ) ( truncate . channel } { ; ; ) ( close . channel ) ( close . fos
public DescribeMatchmakingRuleSetsResult describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) {request = beforeClientExecution(request);return executeDescribeMatchmakingRuleSets(request);}
public String getPronunciation(int wordId, char[] surface, int off, int len) { return null; }
public String getPath() { return pathStr; }
double devsq(double[] v) { if (v == null || v.length == 0) return Double.NaN; int n = v.length; double m = 0; for (int i = 0; i < n; i++) m += v[i]; m /= n; double s = 0; for (int i = 0; i < n; i++) s += (v[i] - m) * (v[i] - m); return n > 1 ? s / (n - 1) : 0; }
public DescribeResizeResult describeResize(DescribeResizeRequest request) {request = beforeClientExecution(request);return executeDescribeResize(request);}
boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
int end() { return end(); }
public void traverse(ICellHandler handler, boolean traverseEmptyCells) { int firstRow = range.getFirstRow(); int lastRow = range.getLastRow(); int firstColumn = range.getFirstColumn(); int lastColumn = range.getLastColumn(); int width = 1 + lastColumn - firstColumn; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); Row currentRow; Cell currentCell; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ctx.rowNumber++) { currentRow = sheet.getRow(ctx.rowNumber); if (currentRow == null) { if (traverseEmptyCells) { for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ctx.colNumber++) { ctx.ordinalNumber = (ctx.rowNumber - firstRow) * width + (ctx.colNumber - firstColumn); handler.onCell(null, ctx); } } continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ctx.colNumber++) { currentCell = currentRow.getCell(ctx.colNumber); ctx.ordinalNumber = (ctx.rowNumber - firstRow) * width + (ctx.colNumber - firstColumn); if (currentCell == null) { if (traverseEmptyCells) { handler.onCell(null, ctx); } continue; } if (!traverseEmptyCells && currentCell.getCellType() == CellType.BLANK) { continue; } handler.onCell(currentCell, ctx); } } }
public int getReadIndex() { return _readIndex; }
public int compareTo(ScoreTerm other) { if (term.bytes.equals(other.term.bytes)) { return 0; } if (boost == other.boost) { return term.compareTo(other.term); } else { return Float.compare(boost, other.boost); } }
public int normalize(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = StemmerUtil.delete(s, i, len); i--; break; default: break; } } return len; }
void serialize(LittleEndianOutput out1) { out1.writeShort(); }
public DiagnosticErrorListener(boolean exactOnly) { this.exactOnly = exactOnly; }
public KeySchemaElement(String attributeName, KeyType keyType) {setAttributeName(attributeName);setKeyType(keyType);}
public GetAssignmentResponse getAssignment(GetAssignmentRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetAssignmentRequestMarshaller.instance); options.setResponseUnmarshaller(GetAssignmentResponseUnmarshaller.instance); return invoke(request, options); }
boolean hasObject(AnyObjectId id) { return findOffset() != -1; }
public GroupingSearch setAllGroups(boolean allGroups) { this.allGroups = allGroups; return this; }
public void setMultiValued(String dimName, boolean v) {synchronized (fieldTypes) {fieldTypes.computeIfAbsent(dimName, k -> new DimConfig()).setMultiValued(v);}}
public int getCellsVal() { return cells.size(); }
public DeleteVoiceConnectorResult deleteVoiceConnector(DeleteVoiceConnectorRequest request) { request = beforeClientExecution(request); return executeDeleteVoiceConnector(request); }
public DeleteLifecyclePolicyResult deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) {request = beforeClientExecution(request);return executeDeleteLifecyclePolicy(request);}
