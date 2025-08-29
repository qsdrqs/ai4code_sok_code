void serialize(LittleEndianOutput out1) { }
void addAll(NGit.Util.BlockList<T> src) { if (src.size == 0) { } int srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) { addAll(src.directory[], , ); } if (src.tailBlkIdx != 0) { addAll(src.tailBlock, , src.tailBlkIdx); } }
void writeByte(byte b) { if (outerInstance.upto == outerInstance.blockSize) { if (outerInstance.currentBlock != null) { outerInstance.blocks.add(outerInstance.currentBlock); outerInstance.blockEnd.add(outerInstance.upto); } outerInstance.currentBlock = new byte[outerInstance.blockSize]; outerInstance.upto = 0; } outerInstance.currentBlock[outerInstance.upto++] = (byte) b; }
public ObjectId getObjectId() { return objectId; }
public DeleteDomainEntryResult deleteDomainEntry(DeleteDomainEntryRequest request) {request = beforeClientExecution(request);return executeDeleteDomainEntry(request);}
long RamBytesUsed() { return ? 0 : fst.getSizeInBytes(); }
String getFullMessage() { byte[] raw = buffer; int msgB = RawParseUtils.tagMessage(); if (msgB < 0) { return ""; } Charset enc = RawParseUtils.parseEncoding(); return RawParseUtils.decode(raw, 0, msgB, raw.length); }
POIFSFileSystem() { HeaderBlock headerBlock = new HeaderBlock(); _property_table = new PropertyTable(); _documents = new ArrayList(); _root = null; }
void init(int address) { slice = pool.getBuffers()[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; assert (slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; assert (upto < slice.length); }
public SubmoduleAddCommand setPath(String path) { this.path = path; return this; }
ListIngestionsResponse ListIngestions(ListIngestionsRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(ListIngestionsRequestMarshaller.Instance);options.setResponseUnmarshaller(ListIngestionsResponseUnmarshaller.Instance);return invoke(request, options);}
public QueryParserTokenManager(ICharStream stream, int lexState) {this(); SwitchTo();}
GetShardIteratorResponse GetShardIterator(GetShardIteratorRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetShardIteratorRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetShardIteratorResponseUnmarshaller.getInstance()); return invoke(request, options); }
ModifyStrategyRequest() { super(" ", " ", " ", " ", " "); method(); }
boolean ready() { synchronized (lock) { if (in == null) { throw new IOException(" "); } try { return bytes.hasRemaining() || in.available() > 0; } catch (IOException e) { return false; } } }
EscherOptRecord getOptRecord() { }
int read(byte[] buffer, int offset, int length) { synchronized (this) { if (buffer == null) { throw new IllegalArgumentException(" "); } java.util.Arrays.checkFromIndexSize(offset, length, buffer.length); if (length == 0) { return 0; } int copylen = Math.min(count - pos, length); for (int i = 0; i < copylen; i++) { buffer[offset + i] = (byte) (this.buffer[pos + i] & 0xFF); } pos += copylen; return copylen; } }
OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) { this.sentenceOp = sentenceOp; }
void print(String str) { System.out.print(str != null ? str : String.valueOf((Object) null)); }
public NotImplementedFunctionException(String functionName, Throwable cause) { super(functionName, cause); this.functionName = functionName; }
V next() { return nextEntry.value; }
void readBytes(byte[] b, int offset, int len, boolean useBuffer) { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) { System.arraycopy(buffer, bufferPosition, b, offset, len); } bufferPosition += len; } else { if (available > 0) { System.arraycopy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { refill(); if (bufferLength < len) { System.arraycopy(buffer, bufferPosition, b, offset, bufferLength); throw new EOFException("End of stream reached with " + len + " bytes left to read."); } else { System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } } else { long after = bufferStart + bufferPosition + len; if (after > length) { throw new EOFException("End of stream reached with " + len + " bytes left to read."); } readInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
public TagQueueResult tagQueue(TagQueueRequest request) {request = beforeClientExecution(request);return executeTagQueue(request);}
void remove() { throw new UnsupportedOperationException(); }
public ModifyCacheSubnetGroupResult modifyCacheSubnetGroup() {return executeModifyCacheSubnetGroup();}
void setParams(String params) { setParams(); String culture = " "; StringTokenizer st = new StringTokenizer(params, " "); if (st.hasMoreTokens()) culture = st.nextToken(); if (st.hasMoreTokens()) culture += " " + st.nextToken(); if (st.hasMoreTokens()) String ignore = st.nextToken(); }
DeleteDocumentationVersionResponse deleteDocumentationVersion(DeleteDocumentationVersionRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(null); options.setResponseUnmarshaller(DeleteDocumentationVersionResponseUnmarshaller.getInstance()); return invoke(DeleteDocumentationVersionResponse.class, null); }
boolean equals(Object obj) { if (!(obj instanceof FacetLabel)) { return false; } FacetLabel other = (FacetLabel) obj; if (length != other.length) { return false; } for (int i = length - 1; i >= 0; i--) { if (!components[i].equals(other.components[i])) { return false; } } return true; }
GetInstanceAccessDetailsResponse getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(); options.setResponseUnmarshaller(GetInstanceAccessDetailsResponseUnmarshaller.getInstance()); return invoke(GetInstanceAccessDetailsResponse.class, options); }
HSSFPolygon createPolygon() { HSSFPolygon shape = new HSSFPolygon(); shape.getParent(); shape.getAnchor(); shapes.add(); onCreate(); return shape; }
String getSheetName() { return getBoundSheetRec().getSheetname(); }
public GetDashboardResult getDashboard(GetDashboardRequest request) {request = beforeClientExecution(request);return executeGetDashboard(request);}
AssociateSigninDelegateGroupsWithAccountResponse associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(AssociateSigninDelegateGroupsWithAccountRequestMarshaller.getInstance()); options.setResponseUnmarshaller(AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.getInstance()); return invoke(AssociateSigninDelegateGroupsWithAccountResponse.class, request, options); }
void addMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < mbr.getNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.setColumn(j + mbr.getFirstColumn()); br.setRow(mbr.getRow()); br.setXF(mbr.getXFAt()); insertCell(br); } }
String quote(String string) { StringBuilder sb = new StringBuilder(); sb.append("\\\""); int apos = 0; int k; while ((k = string.indexOf("\\\"", apos)) >= 0) { sb.append(string.substring(apos, k + 2)).append("\\\\\\\""); apos = k + 2; } return sb.append(string.substring(apos)).append("\\\"").toString(); }
public java.nio.ByteBuffer putInt(int value) { throw new java.nio.ReadOnlyBufferException(); }
ArrayPtg(Object[][] values2d) {int nColumns = values2d[0].length; int nRows = values2d.length; _nColumns = (short) nColumns; _nRows = (short) nRows; Object[] vv = new Object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) {Object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) {vv[getValueIndex(r, c)] = rowData[c];}} _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0;}
GetIceServerConfigResponse GetIceServerConfig(GetIceServerConfigRequest request) { InvokeOptions options = new InvokeOptions(); GetIceServerConfigRequestMarshaller.Instance; options.setResponseUnmarshaller(GetIceServerConfigResponseUnmarshaller.Instance); return Invoke<GetIceServerConfigResponse>(request, options); }
String toString() { StringBuilder sb = new StringBuilder(); sb.append(getClass().getSimpleName()).append(" "); sb.append(getValueAsString); sb.append(" "); return sb.toString(); }
public String toString() { return " " + _parentQuery + " "; }
void incRef() { refCount.incrementAndGet(); }
UpdateConfigurationSetSendingEnabledResponse UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(UpdateConfigurationSetSendingEnabledRequestMarshaller.getInstance()); options.setResponseUnmarshaller(UpdateConfigurationSetSendingEnabledResponseUnmarshaller.getInstance()); return invoke(UpdateConfigurationSetSendingEnabledResponse.class, request, options); }
int getNextXBATChainOffset() { return getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE; }
void multiplyByPowerOfTen(int pow10) { TenPower tp = TenPower.getInstance(Math.abs(pow10)); if (pow10 < 0) { mulShift(tp.getDivisor(), tp.getDivisorShift()); } else { mulShift(tp.getMultiplicand(), tp.getMultiplierShift()); } }
String toString() { StringBuilder builder = new StringBuilder(); int length = this.length; builder.append(File.separatorChar); for (int i = 0; i < length; i++) { builder.append(this.getComponent()); if (i < (length - 1)) { builder.append(File.separatorChar); } } return builder.toString(); }
void withFetcher() { fetcher fetcher; fetcher.setRoleName(); }
void setProgressMonitor() { ProgressMonitor pm; }
void Reset() { if () { ptr = 0; if (!Eof) { ParseEntry(); } } }
E previous() { if (iterator.previousIndex() >= start) { return iterator.previous(); } throw new java.util.NoSuchElementException(); }
String getNewPrefix() { return newPrefix; }
int indexOfValue(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1; }
public List<CharsRef> uniqueStems(char[] word, int length) {List<CharsRef> stems = stem(word, length);if (stems.size() < 2) {return stems;}CharArraySet terms = new CharArraySet(8, true);List<CharsRef> deduped = new ArrayList<>();for (CharsRef s : stems) {if (!terms.contains(s)) {deduped.add(s);terms.add(s);}}return deduped;}
public GetGatewayResponsesResult getGatewayResponses() {InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetGatewayResponsesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetGatewayResponsesResponseUnmarshaller.getInstance()); return invoke(GetGatewayResponsesResult.class, options);}
void setPosition(long position) { currentBlockIndex = (int) (position >> outerInstance.blockBits); currentBlock = outerInstance.blocks[currentBlockIndex]; currentBlockUpto = (int) (position & outerInstance.blockMask); }
long skip(long n) { int s = (int) (available(), Math.max(,)); ptr s; return s; }
BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { this.bootstrapActionConfig = bootstrapActionConfig; }
void Serialize(ILittleEndianOutput out1) { out1.writeShort(); out1.writeShort(); out1.writeShort(); out1.writeShort(); (field_6_author.length()); out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00); if (field_5_hasMultibyte) { StringUtil.putUnicodeLE(, ); } else { StringUtil.putCompressedUnicode(, ); } if (field_7_padding != null) { out1.writeByte(Integer.parseInt(, Locale.ROOT)); } }
int lastIndexOf() { return lastIndexOf(,); }
boolean add() { return addLastImpl(); }
void UnsetSection(String section, String subsection) { ConfigSnapshot; ConfigSnapshot; do { src state.Get; res UnsetSection(,,); } while (!state.CompareAndSet(,)); }
String getTagName() { return tagName; }
void addSubRecord(int index) { subrecords.add(index, null); }
boolean remove() { synchronized (mutex) { return c.remove(); } }
TokenStream create() { return new DoubleMetaphoneFilter(null, null, null); }
public long getLength() { return inCoreLength(); }
void setValue() { value newValue; }
Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
int get(int i) { if () { throw new IndexOutOfBoundsException(); } return entries[]; }
CreateRepoRequest() { super(" ", " ", " ", " ", " "); setUriPattern(" "); setMethod(MethodType.PUT); }
boolean isDeltaBaseAsOffset() { }
void remove() { if (expectedModCount == list.modCount) { if (lastLink != null) { java.util.LinkedList.Link<ET> next_1 = lastLink.next; java.util.LinkedList.Link<ET> previous_1 = lastLink.previous; next_1.previous = previous_1; previous_1.next = next_1; if (lastLink == link) { pos--; } link = previous_1; lastLink = null; expectedModCount++; list._size--; list.modCount++; } else { throw new IllegalStateException(); } } else { throw new java.util.ConcurrentModificationException(); } }
MergeShardsResponse MergeShards(MergeShardsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(MergeShardsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(MergeShardsResponseUnmarshaller.getInstance()); return invoke(request, options); }
AllocateHostedConnectionResponse allocateHostedConnection(AllocateHostedConnectionRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(); options.setResponseUnmarshaller(AllocateHostedConnectionResponseUnmarshaller.getInstance()); return invoke(AllocateHostedConnectionResponse.class, options); }
int getBeginIndex() { return start; }
WeightedTerm getTerms(Query query) { return getTerms(null, null); }
ByteBuffer compact() { throw new ReadOnlyBufferException(); }
void decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (byte0 >>> 2); int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >>> 4); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >>> 6); values[valuesOffset++] = (byte2 & 63); } }
String getHumanishName() { if (getPath() == null || getPath().isEmpty()) { throw new IllegalArgumentException(); } String s = getPath(); String[] elements; if ("".equals(s) || LOCAL_FILE.matcher(s).matches()) { elements = s.split("\\" + File.separatorChar + ""); } else { elements = s.split(""); } if (elements.length == 0) { throw new IllegalArgumentException(); } String result = elements[elements.length - 1]; if (Constants.DOT_GIT.equals(result)) { result = elements[elements.length - 2]; } else { if (result.endsWith(Constants.DOT_GIT_EXT)) { result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); } } return result; }
DescribeNotebookInstanceLifecycleConfigResponse describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance()); options.setResponseUnmarshaller(); return invoke(DescribeNotebookInstanceLifecycleConfigResponse.class, options); }
public String getAccessKeySecret() { return accessSecret; }
public CreateVpnConnectionResponse createVpnConnection(CreateVpnConnectionRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(CreateVpnConnectionRequestMarshaller.getInstance());options.setResponseUnmarshaller(CreateVpnConnectionResponseUnmarshaller.getInstance());return invoke(CreateVpnConnectionResponse.class, request, options);}
DescribeVoicesResponse DescribeVoices(DescribeVoicesRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeVoicesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeVoicesResponseUnmarshaller.getInstance()); return Invoke(DescribeVoicesResponse.class, request, options); }
public ListMonitoringExecutionsResult listMonitoringExecutions(ListMonitoringExecutionsRequest request) {request = beforeClientExecution(request);return executeListMonitoringExecutions(request);}
public DescribeJobRequest(String vaultName, String jobId) { this.vaultName = vaultName; this.jobId = jobId; }
EscherRecord GetEscherRecord() { return escherRecords[]; }
public GetApisResult getApis(GetApisRequest request) {request = beforeClientExecution(request);return executeGetApis(request);}
DeleteSmsChannelResponse deleteSmsChannel(DeleteSmsChannelRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(null); options.setResponseUnmarshaller(DeleteSmsChannelResponseUnmarshaller.getInstance()); return invoke(DeleteSmsChannelResponse.class, null, null); }
TrackingRefUpdate getTrackingRefUpdate() { }
void print(boolean b) { print(); }
IQueryNode getChild() { return getChildren()[0]; }
NotIgnoredFilter(int workdirTreeIndex) { this.index = workdirTreeIndex; }
AreaRecord(RecordInputStream in1) { field_1_formatFlags = in1.readShort(); }
GetThumbnailRequest() { this(" ", " ", " ", " ", " "); Protocol; }
DescribeTransitGatewayVpcAttachmentsResponse describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { InvokeOptions options = new InvokeOptions(); DescribeTransitGatewayVpcAttachmentsRequestMarshaller.instance; options.setResponseUnmarshaller(DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.instance); return invoke(DescribeTransitGatewayVpcAttachmentsResponse.class, request, options); }
public PutVoiceConnectorStreamingConfigurationResponse PutVoiceConnectorStreamingConfiguration() { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance()); options.setResponseUnmarshaller(PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance()); return invoke(PutVoiceConnectorStreamingConfigurationResponse.class, options); }
OrdRange getOrdRange(String dim) { OrdRange result; prefixToOrdRange.tryGetValue(dim, result); return result; }
public String toString() { String symbol = ""; if (startIndex < ((CharStream) getInputStream()).size()) { symbol = ((CharStream) getInputStream()).getText(Interval.of(startIndex, getStartIndex())); symbol = Utils.escapeWhitespace(symbol, false); } return String.format(Locale.getDefault(), "%s %s", Antlr4.Runtime.LexerNoViableAltException.class.getName(), symbol); }
E peek() { return peekFirstImpl(); }
CreateWorkspacesResponse createWorkspaces(CreateWorkspacesRequest request) { InvokeOptions options = new InvokeOptions(); CreateWorkspacesRequestMarshaller.Instance; options.setResponseUnmarshaller(CreateWorkspacesResponseUnmarshaller.Instance); return invoke(CreateWorkspacesResponse.class, request, options); }
public Object clone() { NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = this.field_1_formatIndex; return rec; }
DescribeRepositoriesResponse DescribeRepositories(DescribeRepositoriesRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(); options.setResponseUnmarshaller(DescribeRepositoriesResponseUnmarshaller.getInstance()); return Invoke(DescribeRepositoriesResponse.class, options); }
SparseIntArray(int initialCapacity) {initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0;}
TokenStream create() { return new HyphenatedWordsFilter(); }
CreateDistributionWithTagsResponse CreateDistributionWithTags(CreateDistributionWithTagsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(CreateDistributionWithTagsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(null); return Invoke(CreateDistributionWithTagsResponse.class, null); }
RandomAccessFile(String fileName, String mode) { throw new UnsupportedOperationException(); }
public DeleteWorkspaceImageResponse deleteWorkspaceImage(DeleteWorkspaceImageRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.getInstance()); return invoke(DeleteWorkspaceImageResponse.class, request, options); }
String toHex() { return toHex((long) value); }
UpdateDistributionResponse updateDistribution(UpdateDistributionRequest request) { InvokeOptions options = new InvokeOptions(); UpdateDistributionRequestMarshaller.getInstance(); options.setResponseUnmarshaller(UpdateDistributionResponseUnmarshaller.getInstance()); return invoke(UpdateDistributionResponse.class, request, options); }
HSSFColor getColor(short index) { if (index == Index) return HSSFColor.Automatic.getInstance(); else { byte[] b = palette.getColor(); if (b != null) { return new CustomColor(,); } } return null; }
public ValueEval evaluate(ValueEval operands, int srcRow, int srcCol) throws NotImplementedFunctionException { throw new NotImplementedFunctionException(); }
void serialize(LittleEndianOutput out1) { out1.writeShort((short) field_1_number_crn_records); ((short) field_2_sheet_table_index); }
public DescribeDBEngineVersionsResponse DescribeDBEngineVersions() { return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
FormatRun(short character, short fontIndex) { this._character = character; this._fontIndex = fontIndex; }
byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte) (ch >> 8); result[resultIndex++] = (byte) ch; } return result; }
public UploadArchiveResponse uploadArchive(UploadArchiveRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(UploadArchiveRequestMarshaller.getInstance()); options.setResponseUnmarshaller(UploadArchiveResponseUnmarshaller.getInstance()); return invoke(UploadArchiveResponse.class, request, options); }
public List<?> getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex, -1); }
public boolean equals(Object obj) {if (this == obj) {return true;}if (obj == null || getClass() != obj.getClass()) {return false;}AutomatonQuery other = (AutomatonQuery) obj;if (!m_compiled.equals(other.m_compiled)) {return false;}if (m_term == null) {if (other.m_term != null) {return false;}} else if (!m_term.equals(other.m_term)) {return false;}return true;}
SpanQuery makeSpanClause() { List<SpanQuery> spanQueries = new ArrayList<>(); for (Map.Entry<SpanQuery, ?> wsq : weightBySpanQuery.entrySet()) { wsq.getKey().getBoost(); spanQueries.add(wsq.getKey()); } if (spanQueries.size() == 1) return spanQueries.get(0); else return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); }
StashCreateCommand stashCreate() { return new StashCreateCommand(); }
FieldInfo fieldInfo(String fieldName) { FieldInfo ret; byName.tryGetValue(fieldName, ret); return ret; }
DescribeEventSourceResponse describeEventSource(DescribeEventSourceRequest request) { InvokeOptions options = new InvokeOptions(); DescribeEventSourceRequestMarshaller.Instance; options.setResponseUnmarshaller(DescribeEventSourceResponseUnmarshaller.Instance); return invoke(DescribeEventSourceResponse.class, request, options); }
GetDocumentAnalysisResponse GetDocumentAnalysis(GetDocumentAnalysisRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetDocumentAnalysisRequestMarshaller.getInstance()); options.setResponseUnmarshaller(null); return Invoke(GetDocumentAnalysisResponse.class, null, null); }
CancelUpdateStackResponse cancelUpdateStack(CancelUpdateStackRequest request) { InvokeOptions options = new InvokeOptions(); CancelUpdateStackRequestMarshaller.instance; options.setResponseUnmarshaller(CancelUpdateStackResponseUnmarshaller.instance); return invoke(CancelUpdateStackResponse.class, request, options); }
ModifyLoadBalancerAttributesResponse modifyLoadBalancerAttributes() { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ModifyLoadBalancerAttributesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance()); return invoke(ModifyLoadBalancerAttributesResponse.class, options); }
public SetInstanceProtectionResult setInstanceProtection(SetInstanceProtectionRequest request) {request = beforeClientExecution(request);return executeSetInstanceProtection(request);}
ModifyDBProxyResponse ModifyDBProxy(ModifyDBProxyRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ModifyDBProxyRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ModifyDBProxyResponseUnmarshaller.getInstance()); return invoke(request, options); }
void add(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.length) { CharsRef[] next = new CharsRef[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_OBJECT_REF)]; System.arraycopy(outputs, 0, next, 0, count); outputs = next; } if (count == endOffsets.length) { int[] next = new int[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.length) { int[] next = new int[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRef(); } outputs[count].copyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
FetchLibrariesRequest() { this(" ", " ", " ", " ", " "); Protocol; }
boolean exists() { return (); }
FilterOutputStream(OutputStream out) { super(out); }
ScaleClusterRequest() { super(" ", " ", " ", " ", " "); setUriPattern(" "); setMethod(null); }
IDataValidationConstraint createTimeConstraint(int operatorType, String formula2) { return DVConstraint.createTimeConstraint(operatorType, formula2); }
ListObjectParentPathsResponse listObjectParentPaths() { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListObjectParentPathsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ListObjectParentPathsResponseUnmarshaller.getInstance()); return invoke(ListObjectParentPathsResponse.class, options); }
public DescribeCacheSubnetGroupsResult describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) {request = beforeClientExecution(request);return executeDescribeCacheSubnetGroups(request);}
void setSharedFormula(boolean flag) { field5Options(); }
boolean IsReuseObjects() { }
IErrorNode addErrorNode(IToken badToken) { ErrorNodeImpl t = new ErrorNodeImpl(); addChild(); t.getParent(); }
LatvianStemFilterFactory(Map<String, String> args) { super(args); if (args.size() > 0) { throw new IllegalArgumentException(" " + args); } }
public RemoveSourceIdentifierFromSubscriptionResponse removeSourceIdentifierFromSubscription() {return executeRemoveSourceIdentifierFromSubscription();}
TokenFilterFactory ForName(String name, Map<String, String> args) { return loader.newInstance(name, args); }
AddAlbumPhotosRequest() { super(" ", " ", " ", " ", " "); protocol(); }
public GetThreatIntelSetResult getThreatIntelSet() {return executeGetThreatIntelSet(new GetThreatIntelSetRequest());}
TreeFilter clone() { return new AndTreeFilter.Binary(() , b.clone()); }
boolean equals() { return o instanceof ; }
boolean hasArray() { return protectedHasArray; }
UpdateContributorInsightsResponse updateContributorInsights(UpdateContributorInsightsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(UpdateContributorInsightsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(UpdateContributorInsightsResponseUnmarshaller.getInstance()); return invoke(UpdateContributorInsightsResponse.class, request, options); }
void unwriteProtectWorkbook() { records.remove(); fileShare = null; writeProtect = null; }
SolrSynonymParser extends Base { super(dedup, analyzer); this.expand = expand; }
RequestSpotInstancesResponse requestSpotInstances(RequestSpotInstancesRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(RequestSpotInstancesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(RequestSpotInstancesResponseUnmarshaller.getInstance()); return invoke(RequestSpotInstancesResponse.class, request, options); }
byte getObjectData() { return findObjectRecord().getObjectData(); }
GetContactAttributesResponse getContactAttributes(GetContactAttributesRequest request) { InvokeOptions options = new InvokeOptions(); GetContactAttributesRequestMarshaller.Instance; options.setResponseUnmarshaller(GetContactAttributesResponseUnmarshaller.Instance); return invoke(GetContactAttributesResponse.class, request, options); }
public String toString() { return getKey() + getValue(); }
public ListTextTranslationJobsResult listTextTranslationJobs() {return executeListTextTranslationJobs(new ListTextTranslationJobsRequest());}
GetContactMethodsResponse getContactMethods(GetContactMethodsRequest request) { InvokeOptions options = new InvokeOptions(); GetContactMethodsRequestMarshaller.getInstance(); options.setResponseUnmarshaller(GetContactMethodsResponseUnmarshaller.getInstance()); return invoke(GetContactMethodsResponse.class, request, options); }
short LookupIndexByName(String name) { FunctionMetadata fd = GetInstance().getFunctionByNameInternal(); if (fd == null) { return -1; } return (short) 0; }
public DescribeAnomalyDetectorsResponse describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeAnomalyDetectorsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeAnomalyDetectorsResponseUnmarshaller.getInstance()); return invoke(DescribeAnomalyDetectorsResponse.class, request, options); }
String insertId(ObjectId changeId) { return insertId(); }
long getObjectSize(AnyObjectId objectId, int typeHint) { long sz = db.getObjectSize(); if (sz < 0) { if (typeHint == OBJ_ANY) { throw new MissingObjectException("", ""); } throw new MissingObjectException(objectId.copy(), ""); } return sz; }
public ImportInstallationMediaResult importInstallationMedia(ImportInstallationMediaRequest request) {request = beforeClientExecution(request);return executeImportInstallationMedia(request);}
PutLifecycleEventHookExecutionStatusResponse putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance()); options.setResponseUnmarshaller(PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance()); return invoke(request, options); }
NumberPtg(ILittleEndianInput in1) { field_1_value(); }
GetFieldLevelEncryptionConfigResponse getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance()); return invoke(GetFieldLevelEncryptionConfigResponse.class, request, options); }
public DescribeDetectorResult describeDetector(DescribeDetectorRequest request) {request = beforeClientExecution(request);return executeDescribeDetector(request);}
ReportInstanceStatusResponse ReportInstanceStatus(ReportInstanceStatusRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ReportInstanceStatusRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ReportInstanceStatusResponseUnmarshaller.getInstance()); return invoke(request, options); }
public DeleteAlarmResponse DeleteAlarm(DeleteAlarmRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(DeleteAlarmRequestMarshaller.getInstance());options.setResponseUnmarshaller(DeleteAlarmResponseUnmarshaller.getInstance());return invoke(DeleteAlarmResponse.class, options);}
TokenStream create() { return new PortugueseStemFilter(); }
FtCblsSubRecord() { reserved = new byte[0]; }
boolean remove() { synchronized (mutex) { return c.remove(); } }
GetDedicatedIpResponse GetDedicatedIp(GetDedicatedIpRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller; options.ResponseUnmarshaller = GetDedicatedIpResponseUnmarshaller.Instance; return Invoke<GetDedicatedIpResponse>(null, null); }
@Override public String toString() { return precedence + " "; }
ListStreamProcessorsResponse listStreamProcessors() { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListStreamProcessorsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ListStreamProcessorsResponseUnmarshaller.getInstance()); return invoke(ListStreamProcessorsResponse.class, options); }
DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) { this.loadBalancerName = loadBalancerName; this.policyName = policyName; }
WindowProtectRecord(int options) { this.options = options; }
UnbufferedCharStream(int bufferSize) { n = 0; data = new int[bufferSize]; }
GetOperationsResponse getOperations(GetOperationsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetOperationsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetOperationsResponseUnmarshaller.getInstance()); return invoke(GetOperationsResponse.class, request, options); }
void CopyRawTo(byte[] b) { NB.EncodeInt32(, , ); NB.EncodeInt32(, o + 4, ); NB.EncodeInt32(, o + 8, ); NB.EncodeInt32(, o + 12, ); NB.EncodeInt32(, o + 16, ); }
public WindowOneRecord(RecordInputStream in){field_1_h_hold = in.readShort();field_2_v_hold = in.readShort();field_3_width();field_4_height = in.readShort();field_5_options = in.readShort();field_6_active_sheet = in.readShort();field_7_first_visible_tab = in.readShort();field_8_num_selected_tabs = in.readShort();field_9_tab_width_ratio = in.readShort();}
StopWorkspacesResponse stopWorkspaces(StopWorkspacesRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(StopWorkspacesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(StopWorkspacesResponseUnmarshaller.getInstance()); return invoke(StopWorkspacesResponse.class, request, options); }
void close() throws IOException { if (isOpen) { isOpen; try { dump(); } finally { try { channel.truncate(); } finally { try { channel.close(); } finally { (); } } } } }
DescribeMatchmakingRuleSetsResponse DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(null); options.setResponseUnmarshaller(DescribeMatchmakingRuleSetsResponseUnmarshaller.getInstance()); return invoke(DescribeMatchmakingRuleSetsResponse.class, null, null); }
String getPronunciation(int wordId, char surface, int off, int len) { return null; }
public String getPath() { return pathStr; }
double devsq(double[] v) { double r = Double.NaN; if (v != null && v.length >= 1) { double m = 0; double s = 0; int n = v.length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } return r; }
DescribeResizeResponse describeResize(DescribeResizeRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeResizeRequestMarshaller.getInstance()); options.setResponseUnmarshaller(null); return invoke(DescribeResizeResponse.class, request, options); }
public final boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
int end() { return end(); }
void traverse(ICellHandler handler) { int firstRow = range.getFirstRow(); int lastRow = range.getLastRow(); int firstColumn = range.getFirstColumn(); int lastColumn = range.getLastColumn(); int width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); IRow currentRow = null; ICell currentCell = null; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ++ctx.rowNumber) { currentRow = sheet.getRow(ctx.rowNumber); if (currentRow == null) { continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ++ctx.colNumber) { currentCell = currentRow.getCell(ctx.colNumber); if (currentCell == null) { continue; } if (isEmpty() && !traverseEmptyCells) { continue; } int cellIndex = (ctx.rowNumber - firstRow) * width + (ctx.colNumber - firstColumn + 1); handler.onCell(ctx, cellIndex); } } }
public int getReadIndex() { return _ReadIndex; }
public int compareTo(ScoreTerm other) { if (term.bytesEquals(other.term)) { return 0; } if (boost == other.boost) { return term.compareTo(other.term); } else { return Float.compare(boost, other.boost); } }
int normalize(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = StemmerUtil.delete(s, i, len); i--; break; default: break; } } return len; }
void serialize(LittleEndianOutput out1) { }
DiagnosticErrorListener(boolean exactOnly) { this.exactOnly = exactOnly; }
KeySchemaElement(String attributeName, KeyType keyType) { this._attributeName = attributeName; this._keyType = keyType; }
GetAssignmentResponse GetAssignment(GetAssignmentRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetAssignmentRequestMarshaller.Instance); options.setResponseUnmarshaller(GetAssignmentResponseUnmarshaller.Instance); return Invoke<GetAssignmentResponse>(request, options); }
boolean hasObject() { return findOffset() != -1; }
GroupingSearch setAllGroups() { this.allGroups = allGroups; return this; }
void setMultiValued(String dimName, boolean v) { synchronized (this) { if (!fieldTypes.containsKey(dimName)) { fieldTypes.put(dimName, new DimConfig(v)); } else { fieldTypes.get(dimName).setMultiValued(v); } } }
int getCellsVal() { int size = 0; for (char c : cells.keySet()) { Cell e = at(); if (e.cmd >= 0) { } } return size; }
DeleteVoiceConnectorResponse deleteVoiceConnector(DeleteVoiceConnectorRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(); options.setResponseUnmarshaller(DeleteVoiceConnectorResponseUnmarshaller.getInstance()); return invoke(DeleteVoiceConnectorResponse.class, options); }
DeleteLifecyclePolicyResponse DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteLifecyclePolicyRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteLifecyclePolicyResponseUnmarshaller.getInstance()); return invoke(request, options); }
