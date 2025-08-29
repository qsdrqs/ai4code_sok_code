void serialize(ILittleEndianOutput out1) { }
public void addAll(BlockList<T> src) { if (src.size == 0) {} int srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) { addAll(src.directory); } if (src.tailBlkIdx != 0) { addAll(src.tailBlock, 0, src.tailBlkIdx); } }
public void writeByte(byte b) { if (outerInstance.upto == outerInstance.currentBlock.length) { if (outerInstance.currentBlock != null) { outerInstance.blocks.add(outerInstance.currentBlock); outerInstance.blockEnd.add(outerInstance.upto); } outerInstance.currentBlock = new byte[outerInstance.blockSize]; outerInstance.upto = 0; } outerInstance.currentBlock[outerInstance.upto++] = b; }
public ObjectId getObjectId() { return objectId; }
public DeleteDomainEntryResult deleteDomainEntry(DeleteDomainEntryRequest request) {request = beforeClientExecution(request);return executeDeleteDomainEntry(request);}
public long ramBytesUsed() { return ? 0 : fst.getSizeInBytes(); }
public String getFullMessage() { byte[] raw = buffer; int msgB = RawParseUtils.tagMessage(); if (msgB < 0) { return ""; } Encoding enc = RawParseUtils.parseEncoding(); return RawParseUtils.decode(0, 0, raw.length, enc); }
public POIFSFileSystem() { HeaderBlock headerBlock = new HeaderBlock(); _property_table = new PropertyTable(); _documents = new ArrayList(); _root = null; }
void init(int address) { slice = pool.Buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; assert slice != null; upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; assert upto < ByteBlockPool.BYTE_BLOCK_MASK; }
public SubmoduleAddCommand setPath(String path) { this.path = path; return this; }
public ListIngestionsResult listIngestions(ListIngestionsRequest request) {request = beforeClientExecution(request);return executeListIngestions(request);}
public QueryParserTokenManager(ICharStream stream, int lexState) { this(); switchTo(); }
public GetShardIteratorResult getShardIterator(GetShardIteratorRequest request) { request = beforeClientExecution(request); return executeGetShardIterator(request); }
public ModifyStrategyRequest() { this(" ", " ", " ", " ", " "); method(); }
public boolean ready() { synchronized (lock) { if (in == null) { throw new java.io.IOException(" "); } try { return bytes.hasRemaining() || in.available() > 0; } catch (java.io.IOException e) { return false; } } }
public EscherOptRecord getOptRecord() { }
public int read(byte[] buffer, int offset, int length) { synchronized (this) { if (buffer == null) { throw new NullPointerException(" "); } if (offset < 0 || length < 0 || offset + length > buffer.length) { throw new IndexOutOfBoundsException(); } if (length == 0) { return 0; } int copylen = count - pos < length ? count - pos : length; for (int i = 0; i < copylen; i++) { buffer[offset + i] = (byte) buffer[pos + i]; } pos += copylen; return copylen; } }
public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) { this.sentenceOp = sentenceOp; }
void print(String str) { write(str == null ? Sharpen.StringHelper.getValueOf(null) : str); }
public class NotImplementedFunctionException extends BaseException { public NotImplementedFunctionException(String functionName, Throwable cause) { super(functionName, cause); this.functionName = functionName; } }
public V next() { return nextEntry.value; }
void readBytes(byte[] b, int offset, int len, boolean useBuffer) {int available = bufferLength - bufferPosition;if (len <= available) {if (len > 0) {System.arraycopy(buffer, bufferPosition, b, offset, len);} bufferPosition += len;} else {if (available > 0) {System.arraycopy(buffer, bufferPosition, b, offset, available);offset += available;len -= available;bufferPosition += available;} if (useBuffer && len < bufferSize) {refill();if (bufferLength < len) {System.arraycopy(buffer, 0, b, offset, bufferLength);throw new EOFException("End of stream");} else {System.arraycopy(buffer, 0, b, offset, len);bufferPosition = len;}} else {long after = bufferStart + bufferPosition + len;if (after > length) {throw new EOFException("End of stream");} readInternal(b, offset, len);bufferStart = after;bufferPosition = 0;bufferLength = 0;}}}
public TagQueueResult tagQueue(TagQueueRequest request) { request = beforeClientExecution(request); return executeTagQueue(request); }
private void remove() { throw new UnsupportedOperationException(); }
public ModifyCacheSubnetGroupResponse modifyCacheSubnetGroup() { options = new InvokeOptions(); options.setRequestMarshaller(ModifyCacheSubnetGroupRequestMarshaller.Instance); options.setResponseUnmarshaller(ModifyCacheSubnetGroupResponseUnmarshaller.Instance); return invoke(ModifyCacheSubnetGroupResponse.class); }
void setParams(String params) { StringTokenizer st = new StringTokenizer(params, " "); if (st.hasMoreTokens()) culture = st.nextToken(); if (st.hasMoreTokens()) culture += " " + st.nextToken(); if (st.hasMoreTokens()) ignore = st.nextToken(); }
public DeleteDocumentationVersionResult deleteDocumentationVersion(DeleteDocumentationVersionRequest request) { request = beforeClientExecution(request); return executeDeleteDocumentationVersion(request); }
public boolean equals(Object obj) { if (!(obj instanceof FacetLabel)) return false; FacetLabel other = (FacetLabel) obj; if (length != other.length) return false; for (int i = length - 1; i >= 0; i--) { if (!components[i].equals(other.components[i])) return false; } return true; }
public GetInstanceAccessDetailsResult getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { request = beforeClientExecution(request); return executeGetInstanceAccessDetails(request); }
public HSSFPolygon createPolygon() { HSSFPolygon shape = new HSSFPolygon(); shape.Parent; Anchor anchor = shape.Anchor; shapes.add(); onCreate(); return shape; }
public String getSheetName() { return getBoundSheetRec().sheetname; }
public GetDashboardResult getDashboard(GetDashboardRequest request) {request = beforeClientExecution(request);return executeGetDashboard(request);}
public AssociateSigninDelegateGroupsWithAccountResult associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) {request = beforeClientExecution(request);return executeAssociateSigninDelegateGroupsWithAccount(request);}
void addMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < mbr.getNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.setColumn(j + mbr.getFirstColumn()); br.setRow(mbr.getRow()); mbr.getXFAt(); insertCell(); } }
public String quote(String string) { StringBuilder sb = new StringBuilder(); sb.append('\\'); int apos = 0; int k; while((k = string.indexOf('\\', apos)) >= 0) { sb.append(string.substring(apos, k + 2)).append("\\\\\\"); apos = k + 2; } return sb.append(string.substring(apos)).append('\\').toString(); }
public ByteBuffer putInt(int value) { throw new ReadOnlyBufferException(); }
public ArrayPtg(Object[][] values2d) {int nColumns = values2d[0].length;int nRows = values2d.length;_nColumns = (short) nColumns;_nRows = (short) nRows;Object[] vv = new Object[_nColumns * _nRows];for (int r = 0; r < nRows; r++) {Object[] rowData = values2d[r];for (int c = 0; c < nColumns; c++) {vv[getValueIndex(r, c)] = rowData[c];}}_arrayValues = vv;_reserved0Int = 0;_reserved1Short = 0;_reserved2Byte = 0;}
public GetIceServerConfigResult getIceServerConfig(GetIceServerConfigRequest request) { request = beforeClientExecution(request); return executeGetIceServerConfig(request); }
public String toString() { StringBuilder sb = new StringBuilder(); sb.append(getClass().getName()).append(" "); sb.append(getValueAsString()); sb.append(" "); return sb.toString(); }
public String toString() { return " " + _parentQuery + " "; }
void incRef() { refCount.incrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResult updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { request = beforeClientExecution(request); return executeUpdateConfigurationSetSendingEnabled(request); }
public int getNextXBATChainOffset() { return getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE; }
void multiplyByPowerOfTen(int pow10) { TenPower tp = TenPower.getInstance(Math.abs()); if () { mulShift(tp._divisor, tp._divisorShift); } else { mulShift(tp._multiplicand, tp._multiplierShift); } }
public String toString() { StringBuilder builder = new StringBuilder(); int length = getLength(); builder.append(java.io.File.separatorChar); for (int i = 0; i < length; i++) { builder.append(getComponent()); if (i < (length - 1)) { builder.append(java.io.File.separatorChar); } } return builder.toString(); }
public void withFetcher() { Fetcher fetcher = new Fetcher(); fetcher.setRoleName(); }
public void setProgressMonitor() { ProgressMonitor pm; }
public void reset() { if (condition) { ptr = 0; if (!eof) { parseEntry(); } } }
public E previous() { if (iterator.previousIndex() >= start) { return iterator.previous(); } throw new java.util.NoSuchElementException(); }
public String getNewPrefix() { return newPrefix; }
public int indexOfValue(int value) {for (int i = 0; i < mSize; ) {if (mValues[i] == value) {return i;}} return -1;}
public List<CharsRef> uniqueStems(char[] word, int length) { List<CharsRef> stems = stem(); if (stems.size() < 2) return stems; CharArraySet terms = new CharArraySet(8, dictionary); List<CharsRef> deduped = new ArrayList<>(); for (CharsRef s : stems) { if (!terms.contains(s)) { deduped.add(s); terms.add(s); } } return deduped; }
public GetGatewayResponsesResult getGatewayResponses() { return executeGetGatewayResponses(beforeClientExecution(new GetGatewayResponsesRequest())); }
public void setPosition(long position) { currentBlockIndex = (int) (position >> outerInstance.blockBits); currentBlock = outerInstance.blocks; currentBlockUpto = (int) (position & ...); }
public long skip(long n) { int s = (int) (available(), Math.max()); /* ptr s */ return s; }
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { setBootstrapActionConfig(bootstrapActionConfig); }
public void serialize(LittleEndianOutput out1) {out1.writeShort();out1.writeShort();out1.writeShort();out1.writeShort();field_6_author.length;out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00);if (field_5_hasMultibyte){StringUtil.putUnicodeLE();}else{StringUtil.putCompressedUnicode();}if (field_7_padding != null){out1.writeByte(Integer.parseInt(field_7_padding));}}
public int lastIndexOf() { return lastIndexOf(0, 0); }
private boolean add() { return addLastImpl(); }
public void unsetSection(String section, String subsection) { ConfigSnapshot; ConfigSnapshot; do { src.state().get(); res.unsetSection(section, subsection); } while (!state.compareAndSet()); }
public String getTagName() { return tagName; }
public void addSubRecord(int index) { subrecords.insert(index); }
public boolean remove() { synchronized (mutex) { return c.remove(); } }
public TokenStream create() { return new DoubleMetaphoneFilter(); }
public long getLength() { return inCoreLength(); }
void setValue() { value newValue; }
public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
public int get(int i) { if (i < 0 || i >= entries.length) throw new ArrayIndexOutOfBoundsException(); return entries[i]; }
public CreateRepoRequest() { super(" ", " ", " ", " ", " "); MethodType = Method.PUT; }
boolean isDeltaBaseAsOffset() { }
public void remove() { if (expectedModCount == list.modCount) { if (lastLink != null) { java.util.LinkedList.Link<ET> next_1 = lastLink.next; java.util.LinkedList.Link<ET> previous_1 = lastLink.previous; next_1.previous = previous_1; previous_1.next = next_1; if (lastLink == link) { pos--; } link = previous_1; lastLink = null; expectedModCount++; list._size--; list.modCount++; } else { throw new java.util.NoSuchElementException(); } } else { throw new java.util.ConcurrentModificationException(); } }
public MergeShardsResponse mergeShards(MergeShardsRequest request) { options = new InvokeOptions(); options.setRequestMarshaller(MergeShardsRequestMarshaller.INSTANCE); options.setResponseUnmarshaller(MergeShardsResponseUnmarshaller.INSTANCE); return (MergeShardsResponse) invoke(options); }
public AllocateHostedConnectionResult allocateHostedConnection(AllocateHostedConnectionRequest request) { request = beforeClientExecution(request); return executeAllocateHostedConnection(request); }
int getBeginIndex() { return start; }
public WeightedTerm getTerms(Query query) { return getTerms(); }
public ByteBuffer compact() { throw new ReadOnlyBufferException(); }
void decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = byte0 >> 2; int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 63; } }
public String getHumanishName() { String s = getPath(); if (s == null || s.isEmpty()) { throw new IllegalArgumentException(); } String[] elements; if (" ".equals(s) || LOCAL_FILE.matcher(s).matches()) { elements = s.split(" |" + Pattern.quote(File.separator)); } else { elements = s.split(" "); } if (elements.length == 0) { throw new IllegalArgumentException(); } String result = elements[elements.length - 1]; if (Constants.DOT_GIT.equals(result)) { result = elements[elements.length - 2]; } else if (result.endsWith(Constants.DOT_GIT_EXT)) { result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); } return result; }
public DescribeNotebookInstanceLifecycleConfigResponse describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance()); return invoke(request, options); }
public String getAccessKeySecret() { return AccessSecret; }
public CreateVpnConnectionResult createVpnConnection(CreateVpnConnectionRequest request) { request = beforeClientExecution(request); return executeCreateVpnConnection(request); }
public DescribeVoicesResult describeVoices(DescribeVoicesRequest request) { request = beforeClientExecution(request); return executeDescribeVoices(request); }
public ListMonitoringExecutionsResult listMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = beforeClientExecution(request); return executeListMonitoringExecutions(request); }
public DescribeJobRequest(String vaultName, String jobId) {_vaultName = vaultName; _jobId = jobId;}
public EscherRecord[] getEscherRecord() { return escherRecords; }
public GetApisResult getApis(GetApisRequest request) { request = beforeClientExecution(request); return executeGetApis(request); }
public DeleteSmsChannelResult deleteSmsChannel(DeleteSmsChannelRequest request) { request = beforeClientExecution(request); return executeDeleteSmsChannel(request); }
public TrackingRefUpdate getTrackingRefUpdate() { }
public final void print(boolean b) { print(); }
public IQueryNode getChild() { return getChildren(); }
public NotIgnoredFilter(int workdirTreeIndex) { this.index = workdirTreeIndex; }
public AreaRecord(RecordInputStream in1) { field_1_formatFlags = in1.readShort(); }
public GetThumbnailRequest() { this(" ", " ", " ", " ", " "); Protocol; }
public DescribeTransitGatewayVpcAttachmentsResult describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { request = beforeClientExecution(request); return executeDescribeTransitGatewayVpcAttachments(request); }
public PutVoiceConnectorStreamingConfigurationResponse putVoiceConnectorStreamingConfiguration() { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(PutVoiceConnectorStreamingConfigurationRequestMarshaller.INSTANCE); options.setResponseUnmarshaller(PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.INSTANCE); return invoke(options); }
public OrdRange getOrdRange(String dim) { return prefixToOrdRange.get(dim); }
public String toString() {String symbol = "";if (/*missing condition*/ && startIndex < ((ICharStream)InputStream).size()) {symbol = ((ICharStream)InputStream).getText(Interval.of(, ));symbol = Utils.escapeWhitespace(, );}return String.format(java.util.Locale.getDefault(), " ", Antlr4RuntimeLexerNoViableAltException.class.getName(), );}
public E peek() { return peekFirstImpl(); }
public CreateWorkspacesResult createWorkspaces(CreateWorkspacesRequest request) { request = beforeClientExecution(request); return executeCreateWorkspaces(request); }
public Object clone() { NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = this.field_1_formatIndex; return rec; }
public DescribeRepositoriesResult describeRepositories(DescribeRepositoriesRequest request) { request = beforeClientExecution(request); return executeDescribeRepositories(request); }
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public TokenStream create() { return new HyphenatedWordsFilter(); }
public CreateDistributionWithTagsResult createDistributionWithTags(CreateDistributionWithTagsRequest request) { request = beforeClientExecution(request); return executeCreateDistributionWithTags(request); }
public RandomAccessFile(String fileName, String mode) { super(new File(fileName), mode); throw new NotImplementedException(); }
public DeleteWorkspaceImageResponse deleteWorkspaceImage(DeleteWorkspaceImageRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.INSTANCE); options.setResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.INSTANCE); return invoke(request, options); }
public String toHex() { return toHex((long) value); }
public UpdateDistributionResponse updateDistribution(UpdateDistributionRequest request) { options = new InvokeOptions(); UpdateDistributionRequestMarshaller.INSTANCE; options.setResponseUnmarshaller(UpdateDistributionResponseUnmarshaller.INSTANCE); return invoke(UpdateDistributionResponse.class); }
public HSSFColor getColor(short index) { if (index == this.index) return HSSFColor.AUTOMATIC.getInstance(); else { byte[] b = palette.getColor(); if (b != null) { return new CustomColor(b); } } return null; }
public ValueEval evaluate(ValueEval operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(); }
public void serialize(ILittleEndianOutput out1) { out1.writeShort((short) field_1_number_crn_records); ((short) field_2_sheet_table_index); }
public DescribeDBEngineVersionsResponse describeDBEngineVersions() { return describeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
public FormatRun(short character, short fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte) (ch >> 8); result[resultIndex++] = (byte) ch; } return result; }
public UploadArchiveResult uploadArchive(UploadArchiveRequest request) { request = beforeClientExecution(request); return executeUploadArchive(request); }
public List getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(-1); }
public boolean equals(Object obj) {if (this == obj) return true;if (!super.equals(obj)) return false;if (getClass() != obj.getClass()) return false;AutomatonQuery other = (AutomatonQuery) obj;if (!m_compiled.equals(other.m_compiled)) return false;if (m_term == null) {if (other.m_term != null) return false;} else if (!m_term.equals(other.m_term)) return false;return true;}
public SpanQuery makeSpanClause() { List<SpanQuery> spanQueries = new ArrayList<>(); for (Map.Entry<SpanQuery, ?> entry : weightBySpanQuery.entrySet()) { entry.getKey().boost(); spanQueries.add(entry.getKey()); } if (spanQueries.size() == 1) return spanQueries.get(0); else return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); }
public StashCreateCommand getStashCreate() { return new StashCreateCommand(); }
public FieldInfo fieldInfo(String fieldName) { return byName.get(fieldName); }
public DescribeEventSourceResult describeEventSource(DescribeEventSourceRequest request) { request = beforeClientExecution(request); return executeDescribeEventSource(request); }
public GetDocumentAnalysisResponse getDocumentAnalysis(GetDocumentAnalysisRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetDocumentAnalysisRequestMarshaller.INSTANCE); options.setResponseUnmarshaller(ResponseUnmarshaller.INSTANCE); return invoke(request, options); }
public CancelUpdateStackResult cancelUpdateStack(CancelUpdateStackRequest request) {request = beforeClientExecution(request);return executeCancelUpdateStack(request);}
public ModifyLoadBalancerAttributesResult modifyLoadBalancerAttributes() { return executeModifyLoadBalancerAttributes(new ModifyLoadBalancerAttributesRequest()); }
public SetInstanceProtectionResult setInstanceProtection(SetInstanceProtectionRequest request) { request = beforeClientExecution(request); return executeSetInstanceProtection(request); }
public ModifyDBProxyResult modifyDBProxy(ModifyDBProxyRequest request) { request = beforeClientExecution(request); return executeModifyDBProxy(request); }
public void add(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.length) { CharsRef[] next = new CharsRef[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_OBJECT_REF)]; System.arraycopy(outputs, 0, next, 0, count); outputs = next; } if (count == endOffsets.length) { int[] next = new int[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.length) { int[] next = new int[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRef(); } outputs[count].copyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
public FetchLibrariesRequest() { super(" ", " ", " ", " ", " "); Protocol; }
public boolean exists() { return false; }
public FilterOutputStream(OutputStream java) { this.out = java; }
public ScaleClusterRequest() { this(" ", " ", " ", " ", " "); this.UriPattern = " "; this.Method = null; }
public IDataValidationConstraint createTimeConstraint(int operatorType, String formula2) { return DVConstraint.createTimeConstraint(operatorType, formula2); }
public ListObjectParentPathsResult listObjectParentPaths() { return executeListObjectParentPaths(); }
public DescribeCacheSubnetGroupsResponse describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeCacheSubnetGroupsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance()); return invoke(DescribeCacheSubnetGroupsResponse.class, request, options); }
public void setSharedFormula(boolean flag) { field_5_options(); }
public boolean isReuseObjects() {}
public ErrorNode addErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(); addChild(); t.getParent(); return t; }
public LatvianStemFilterFactory(Map<String, String> args) { super(args); if (args.size() > 0) { throw new IllegalArgumentException(" " + args); } }
public RemoveSourceIdentifierFromSubscriptionResponse removeSourceIdentifierFromSubscription() { return executeRemoveSourceIdentifierFromSubscription(beforeClientExecution(new RemoveSourceIdentifierFromSubscriptionRequest())); }
public TokenFilterFactory forName(Map<String, String> args) { return loader.newInstance(); }
public AddAlbumPhotosRequest() { this(" ", " ", " ", " ", " "); protocol(); }
public GetThreatIntelSetResult getThreatIntelSet(GetThreatIntelSetRequest request) {request = beforeClientExecution(request);return executeGetThreatIntelSet(request);}
public TreeFilter clone() { return new AndTreeFilter.Binary(() -> {}, b.clone()); }
public boolean equals() { return o instanceof ; }
public boolean hasArray() { return protectedHasArray; }
public UpdateContributorInsightsResponse updateContributorInsights(UpdateContributorInsightsRequest request) { request = beforeClientExecution(request); return executeUpdateContributorInsights(request); }
public void unwriteProtectWorkbook() { records.remove(); ; fileShare = null; writeProtect = null; }
public SolrSynonymParser(boolean dedup, Analyzer analyzer) { super(dedup, analyzer); expand.expand(); }
RequestSpotInstancesResponse requestSpotInstances(RequestSpotInstancesRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(RequestSpotInstancesRequestMarshaller.INSTANCE); options.setResponseUnmarshaller(RequestSpotInstancesResponseUnmarshaller.INSTANCE); return invoke(null, null); }
private byte getbjectData() { return findObjectRecord().objectData; }
public GetContactAttributesResult getContactAttributes(GetContactAttributesRequest request) { request = beforeClientExecution(request); return executeGetContactAttributes(request); }
public String toString() { return getKey() + getValue(); }
public ListTextTranslationJobsResult listTextTranslationJobs() { return executeListTextTranslationJobs(); }
public GetContactMethodsResult getContactMethods(GetContactMethodsRequest request) {request = beforeClientExecution(request);return executeGetContactMethods(request);}
public short lookupIndexByName(String name) { FunctionMetadata fd = GetInstance().getFunctionByNameInternal(); if (fd == null) { return -1; } return 0; }
public DescribeAnomalyDetectorsResult describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeAnomalyDetectorsRequestMarshaller.INSTANCE); options.setResponseUnmarshaller(DescribeAnomalyDetectorsResponseUnmarshaller.INSTANCE); return invoke(request, DescribeAnomalyDetectorsResult.class); }
public String insertId(ObjectId changeId) { return insertId(null, null, null); }
public long getObjectSize(AnyObjectId objectId, int typeHint) { long sz = db.getObjectSize(objectId, typeHint); if (sz < 0) { if (typeHint == OBJ_ANY) { throw new MissingObjectException(); } throw new MissingObjectException(objectId.copy()); } return sz; }
public ImportInstallationMediaResult importInstallationMedia(ImportInstallationMediaRequest request) { request = beforeClientExecution(request); return executeImportInstallationMedia(request); }
public PutLifecycleEventHookExecutionStatusResponse putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(PutLifecycleEventHookExecutionStatusRequestMarshaller.Instance); options.setResponseUnmarshaller(PutLifecycleEventHookExecutionStatusResponseUnmarshaller.Instance); return invoke(request, options); }
public NumberPtg(LittleEndianInput in1) { field_1_value(); }
public GetFieldLevelEncryptionConfigResult getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) {request = beforeClientExecution(request);return executeGetFieldLevelEncryptionConfig(request);}
public DescribeDetectorResponse describeDetector(DescribeDetectorRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeDetectorRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeDetectorResponseUnmarshaller.getInstance()); return invoke(DescribeDetectorResponse.class, request, options); }
public ReportInstanceStatusResponse reportInstanceStatus(ReportInstanceStatusRequest request) { options = new InvokeOptions(); options.setRequestMarshaller(ReportInstanceStatusRequestMarshaller.Instance); options.setResponseUnmarshaller(ReportInstanceStatusResponseUnmarshaller.Instance); return new ReportInstanceStatusResponse(options); }
public DeleteAlarmResponse deleteAlarm(DeleteAlarmRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteAlarmRequestMarshaller.INSTANCE); options.setResponseUnmarshaller(DeleteAlarmResponseUnmarshaller.INSTANCE); return executeDeleteAlarm(request); }
public TokenStream create() { return new PortugueseStemFilter(); }
public FtCblsSubRecord() { reserved = new byte[0]; }
public boolean remove() { synchronized(mutex) { return c.remove(); } }
public GetDedicatedIpResult getDedicatedIp(GetDedicatedIpRequest request) { request = beforeClientExecution(request); return executeGetDedicatedIp(request); }
public String toString() { return precedence + " "; }
public ListStreamProcessorsResult listStreamProcessors() { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListStreamProcessorsRequestMarshaller.INSTANCE); options.setResponseUnmarshaller(ListStreamProcessorsResponseUnmarshaller.INSTANCE); return invoke(options, ListStreamProcessorsResult.class); }
public DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) { _loadBalancerName = loadBalancerName; _policyName = policyName; }
private WindowProtectRecord(int options) { setOptions(options); }
public UnbufferedCharStream(int bufferSize) { n = 0; data = new int[bufferSize]; }
public GetOperationsResult getOperations(GetOperationsRequest request) { request = beforeClientExecution(request); return executeGetOperations(request); }
public void copyRawTo(byte[] b) { NB.encodeInt32(b, 0); NB.encodeInt32(b, o + 4); NB.encodeInt32(b, o + 8); NB.encodeInt32(b, o + 12); NB.encodeInt32(b, o + 16); }
public WindowOneRecord(RecordInputStream in1) { field_1_h_hold = in1.readShort(); field_2_v_hold = in1.readShort(); field_3_width = in1.readShort(); field_4_height = in1.readShort(); field_5_options = in1.readShort(); field_6_active_sheet = in1.readShort(); field_7_first_visible_tab = in1.readShort(); field_8_num_selected_tabs = in1.readShort(); field_9_tab_width_ratio = in1.readShort(); }
public StopWorkspacesResult stopWorkspaces(StopWorkspacesRequest request) { request = beforeClientExecution(request); return executeStopWorkspaces(request); }
void close() throws IOException {if (isOpen) {isOpen;try {dump();} finally {try {channel.truncate();} finally {try {channel.close();} finally {}}}}}
public DescribeMatchmakingRuleSetsResult describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { request = beforeClientExecution(request); return executeDescribeMatchmakingRuleSets(request); }
String getPronunciation(final int wordId, final char surface, final int off, final int len) { return null; }
public String getPath() { return pathStr; }
public static double devsq(double[] v) { double r = Double.NaN; if (v != null && v.length >= 1) { double m = 0; double s = 0; int n = v.length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } return r; }
public DescribeResizeResponse describeResize(DescribeResizeRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeResizeRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeResizeResponseUnmarshaller.getInstance()); request = beforeClientExecution(request); return executeDescribeResize(request); }
public boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
public int getEnd() { return end(); }
public void traverse(CellHandler handler) { int firstRow = range.getFirstRow(); int lastRow = range.getLastRow(); int firstColumn = range.getFirstColumn(); int lastColumn = range.getLastColumn(); int width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); Row currentRow = null; Cell currentCell = null; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ++ctx.rowNumber) { currentRow = sheet.getRow(ctx.rowNumber); if (currentRow == null) { continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ++ctx.colNumber) { currentCell = currentRow.getCell(ctx.colNumber); if (currentCell == null) { continue; } if (currentCell.isEmpty() && !traverseEmptyCells) { continue; } (ctx.rowNumber - firstRow) * width + (ctx.colNumber - firstColumn + 1); handler.onCell(); } } }
public int getReadIndex() { return _ReadIndex; }
public int compareTo(ScoreTerm other) {if (term.bytesEquals(other.term)) return 0; if (boost == other.boost) return term.compareTo(other.term); else return Double.compare(boost, other.boost);}
public int normalize(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = StemmerUtil.delete(s, i, len); i--; break; default: break; } } return len; }
public void serialize(LittleEndianOutput out) { }
public DiagnosticErrorListener(boolean exactOnly) { this.exactOnly = exactOnly; }
public KeySchemaElement(String attributeName, KeyType keyType) { this._attributeName = attributeName; this._keyType = keyType; }
public GetAssignmentResult getAssignment(GetAssignmentRequest request) { request = beforeClientExecution(request); return executeGetAssignment(request); }
boolean hasObject() { return findOffset() != -1; }
public GroupingSearch setAllGroups() { return this; }
public void setMultiValued(String dimName, boolean v) { synchronized(fieldTypes) { if (!fieldTypes.containsKey(dimName)) { fieldTypes.put(dimName, new DimConfig()); fieldTypes.get(dimName).isMultiValued = v; } else { fieldTypes.get(dimName).isMultiValued = v; } } }
private int getCellsVal() { int size = 0; for (Character c : cells.keySet()) { Cell e = at(); if (e.cmd >= 0) {} } return size; }
public DeleteVoiceConnectorResult deleteVoiceConnector(DeleteVoiceConnectorRequest request) { request = beforeClientExecution(request); return executeDeleteVoiceConnector(request); }
public DeleteLifecyclePolicyOutput deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) {request = beforeClientExecution(request);return executeDeleteLifecyclePolicy(request);}
