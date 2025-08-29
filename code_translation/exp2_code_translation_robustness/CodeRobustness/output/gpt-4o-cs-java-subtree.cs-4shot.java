void serialize(LittleEndianOutput out1) {}
void addAll(BlockList<T> src) {if (src.size == 0) return;int srcDirIdx = 0;for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) {addAll(src.directory, , );}if (src.tailBlkIdx != 0) {addAll(src.tailBlock, , src.tailBlkIdx);}}
void writeByte(byte b) { if (outerInstance.upto == outerInstance.blockSize) { if (outerInstance.currentBlock != null) { outerInstance.blocks.add(outerInstance.currentBlock); outerInstance.blockEnd.add(outerInstance.upto); } outerInstance.currentBlock = new byte[outerInstance.blockSize]; outerInstance.upto = 0; } outerInstance.currentBlock[outerInstance.upto++] = (byte) b; }
public ObjectId getObjectId() {return objectId;}
public DeleteDomainEntryResult deleteDomainEntry(DeleteDomainEntryRequest request) {request = beforeClientExecution(request);return executeDeleteDomainEntry(request);}
long ramBytesUsed() { return fst == null ? 0 : fst.getSizeInBytes(); }
public String getFullMessage() {byte[] raw = buffer;int msgB = RawParseUtils.tagMessage();if (msgB < 0) {return ""; }Encoding enc = RawParseUtils.parseEncoding();return RawParseUtils.decode(raw, 0, msgB, raw.length);}
POIFSFileSystem() {HeaderBlock headerBlock = new HeaderBlock(); _property_table = new PropertyTable(); _documents = new ArrayList(); _root = null;}
void init(int address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; assert (slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; assert (upto < slice.length); }
public SubmoduleAddCommand setPath(String path) {this.path = path; return this;}
public ListIngestionsResponse listIngestions(ListIngestionsRequest request) {request = beforeClientExecution(request);return executeListIngestions(request);}
public QueryParserTokenManager(ICharStream stream, int lexState) {this(); switchTo();}
public GetShardIteratorResult getShardIterator(GetShardIteratorRequest request) {request = beforeClientExecution(request);return executeGetShardIterator(request);}
ModifyStrategyRequest() { this(" ", " ", " ", " ", " "); method(); }
boolean ready() { synchronized (lock) { if (in == null) { throw new IOException(" "); } try { return bytes.hasRemaining() || in.available() > 0; } catch (IOException e) { return false; } } }
EscherOptRecord getOptRecord() { }
int read(byte[] buffer, int offset, int length) { synchronized (this) { if (buffer == null) { throw new IllegalArgumentException(" "); } java.util.Arrays.checkFromIndexSize(offset, length, buffer.length); if (length == 0) { return 0; } int copylen = Math.min(count - pos, length); for (int i = 0; i < copylen; i++) { buffer[offset + i] = (byte) (this.buffer[pos + i] & 0xFF); } pos += copylen; return copylen; } }
OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) { this.sentenceOp = sentenceOp; }
void print(String str) {write(str != null ? str : String.valueOf((Object) null));}
public NotImplementedFunctionException(String functionName, Throwable cause) { super(functionName, cause); this.functionName = functionName; }
V next() { return nextEntry.value; }
void readBytes(byte[] b, int offset, int len, boolean useBuffer) {int available = bufferLength - bufferPosition;if (len <= available) {if (len > 0) {System.arraycopy(buffer, bufferPosition, b, offset, len);}bufferPosition += len;} else {if (available > 0) {System.arraycopy(buffer, bufferPosition, b, offset, available);offset += available;len -= available;bufferPosition += available;}if (useBuffer && len < bufferSize) {refill();if (bufferLength < len) {System.arraycopy(buffer, bufferPosition, b, offset, bufferLength);throw new EndOfStreamException("End of stream reached with " + len + " bytes left to read.");} else {System.arraycopy(buffer, bufferPosition, b, offset, len);bufferPosition += len;}} else {long after = bufferStart + bufferPosition + len;if (after > length) {throw new EndOfStreamException("End of stream reached with " + len + " bytes left to read.");}readInternal(b, offset, len);bufferStart = after;bufferPosition = 0;bufferLength = 0;}}}
TagQueueResponse tagQueue(TagQueueRequest request) {request = beforeClientExecution(request);return executeTagQueue(request);}
void remove() { throw new UnsupportedOperationException(); }
ModifyCacheSubnetGroupResponse modifyCacheSubnetGroup() {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(ModifyCacheSubnetGroupRequestMarshaller.getInstance());options.setResponseUnmarshaller(ModifyCacheSubnetGroupResponseUnmarshaller.getInstance());return invoke(ModifyCacheSubnetGroupResponse.class, options);}
void setParams(String params) {setParams();String culture = "";StringTokenizer st = new StringTokenizer(params, " ");if (st.hasMoreTokens()) culture = st.nextToken();if (st.hasMoreTokens()) culture += " " + st.nextToken();if (st.hasMoreTokens()) {String ignore = st.nextToken();}}
public DeleteDocumentationVersionResult deleteDocumentationVersion(DeleteDocumentationVersionRequest request) {request = beforeClientExecution(request);return executeDeleteDocumentationVersion(request);}
public boolean equals(Object obj) {if (!(obj instanceof FacetLabel)) {return false;} FacetLabel other = (FacetLabel) obj; if (length != other.length) {return false;} for (int i = length - 1; i >= 0; i--) {if (!components[i].equals(other.components[i])) {return false;}} return true;}
public GetInstanceAccessDetailsResponse getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) {return executeGetInstanceAccessDetails(request);}
HSSFPolygon createPolygon() { HSSFPolygon shape = new HSSFPolygon(); shape.getParent(); shape.getAnchor(); shapes.add(); onCreate(); return shape; }
String getSheetName() { return getBoundSheetRec().getSheetname(); }
GetDashboardResponse getDashboard(GetDashboardRequest request) {InvokeOptions options = new InvokeOptions(); GetDashboardRequestMarshaller.getInstance(); options.setResponseUnmarshaller(GetDashboardResponseUnmarshaller.getInstance()); return invoke(request, options);}
public AssociateSigninDelegateGroupsWithAccountResult associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) {request = beforeClientExecution(request);return executeAssociateSigninDelegateGroupsWithAccount(request);}
void addMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < mbr.getNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.setColumn(j + mbr.getFirstColumn()); br.setRow(mbr.getRow()); br.setXFIndex(mbr.getXFAt(j)); insertCell(br); } }
String quote(String string) {StringBuilder sb = new StringBuilder();sb.append("\\\"");int apos = 0;int k;while ((k = string.indexOf("\\\"", apos)) >= 0) {sb.append(string.substring(apos, k + 2)).append("\\\\\\\"");apos = k + 2;}return sb.append(string.substring(apos)).append("\\\"").toString();}
@Override public java.nio.ByteBuffer putInt(int value) { throw new java.nio.ReadOnlyBufferException(); }
ArrayPtg(Object[][] values2d) {int nColumns = values2d[0].length; int nRows = values2d.length; _nColumns = (short) nColumns; _nRows = (short) nRows; Object[] vv = new Object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) {Object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) {vv[getValueIndex(r, c)] = rowData[c];}} _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0;}
public GetIceServerConfigResult getIceServerConfig(GetIceServerConfigRequest request) {request = beforeClientExecution(request);return executeGetIceServerConfig(request);}
public String toString() { StringBuilder sb = new StringBuilder(); sb.append(getClass().getSimpleName()).append(" "); sb.append(getValueAsString()).append(" "); return sb.toString(); }
public String toString() { return " " + _parentQuery + " "; }
void incRef() { refCount.incrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResult updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) {request = beforeClientExecution(request);return executeUpdateConfigurationSetSendingEnabled(request);}
int getNextXBATChainOffset() { return getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE; }
void multiplyByPowerOfTen(int pow10) {TenPower tp = TenPower.getInstance(Math.abs(pow10));if (pow10 < 0) {mulShift(tp._divisor, tp._divisorShift);} else {mulShift(tp._multiplicand, tp._multiplierShift);}}
public String toString() { StringBuilder builder = new StringBuilder(); int length = this.length; builder.append(File.separatorChar); for (int i = 0; i < length; i++) { builder.append(this.getComponent()); if (i < (length - 1)) { builder.append(File.separatorChar); } } return builder.toString(); }
void withFetcher() { fetcher fetcher; fetcher.setRoleName(); }
void setProgressMonitor() { ProgressMonitor pm; }
void reset() {if () {ptr = 0; if (!eof) {parseEntry();}}}
E previous() { if (iterator.previousIndex() >= start) return iterator.previous(); throw new java.util.NoSuchElementException(); }
public String getNewPrefix() { return newPrefix; }
int indexOfValue(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1; }
List<CharsRef> uniqueStems(char[] word, int length) { List<CharsRef> stems = stem(word, length); if (stems.size() < 2) { return stems; } CharArraySet terms = new CharArraySet(8, dictionary); List<CharsRef> deduped = new ArrayList<>(); for (CharsRef s : stems) { if (!terms.contains(s)) { deduped.add(s); terms.add(s); } } return deduped; }
public GetGatewayResponsesResult getGatewayResponses() {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(GetGatewayResponsesRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetGatewayResponsesResponseUnmarshaller.getInstance());return invoke(GetGatewayResponsesResult.class, options);}
void setPosition(long position) { currentBlockIndex = (int) (position >> outerInstance.blockBits); currentBlock = outerInstance.blocks[currentBlockIndex]; currentBlockUpto = (int) (position & outerInstance.blockMask); }
long skip(long n) {int s = (int) Math.max(available(), 0); ptr = s; return s;}
BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) {this.bootstrapActionConfig = bootstrapActionConfig;}
void serialize(ILittleEndianOutput out1) { out1.writeShort(); out1.writeShort(); out1.writeShort(); out1.writeShort(); (field_6_author.length()); out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00); if (field_5_hasMultibyte) { StringUtil.putUnicodeLE(, ); } else { StringUtil.putCompressedUnicode(, ); } if (field_7_padding != null) { out1.writeByte(Integer.parseInt(, Locale.ROOT)); } }
int lastIndexOf() { return lastIndexOf(,); }
boolean add() { return addLastImpl(); }
void unsetSection(String section, String subsection) { ConfigSnapshot state; ConfigSnapshot res; do { state = src.get(); res = unsetSection(state, section, subsection); } while (!src.compareAndSet(state, res)); }
public String getTagName() { return tagName; }
void addSubRecord(int index) { subrecords.add(index, null); }
boolean remove() { synchronized (mutex) { return c.remove(); } }
TokenStream create() { return new DoubleMetaphoneFilter(null, null, null); }
public long getLength() { return inCoreLength(); }
void setValue() { value = newValue; }
Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
int get(int i) {if () {throw new IndexOutOfBoundsException();} return entries[];}
CreateRepoRequest() { super(" ", " ", " ", " ", " "); setUriPattern(); setMethod(MethodType.PUT); }
boolean isDeltaBaseAsOffset() {}
void remove() { if (expectedModCount == list.modCount) { if (lastLink != null) { java.util.LinkedList.Link<ET> next_1 = lastLink.next; java.util.LinkedList.Link<ET> previous_1 = lastLink.previous; next_1.previous = previous_1; previous_1.next = next_1; if (lastLink == link) { pos--; } link = previous_1; lastLink = null; expectedModCount++; list._size--; list.modCount++; } else { throw new IllegalStateException(); } } else { throw new java.util.ConcurrentModificationException(); } }
public MergeShardsResult mergeShards(MergeShardsRequest request) {request = beforeClientExecution(request);return executeMergeShards(request);}
public AllocateHostedConnectionResult allocateHostedConnection(AllocateHostedConnectionRequest request) {request = beforeClientExecution(request);return executeAllocateHostedConnection(request);}
public int getBeginIndex() { return start; }
WeightedTerm getTerms(Query query) { return getTerms(); }
public java.nio.ByteBuffer compact() { throw new java.nio.ReadOnlyBufferException(); }
void decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (byte0 >>> 2); int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >>> 4); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >>> 6); values[valuesOffset++] = (byte2 & 63); } }
String getHumanishName() { if (getPath() == null || getPath().isEmpty()) { throw new IllegalArgumentException(); } String s = getPath(); String[] elements; if ("".equals(s) || LOCAL_FILE.matcher(s).matches()) { elements = s.split("\\" + File.separatorChar); } else { elements = s.split(" "); } if (elements.length == 0) { throw new IllegalArgumentException(); } String result = elements[elements.length - 1]; if (Constants.DOT_GIT.equals(result)) { result = elements[elements.length - 2]; } else { if (result.endsWith(Constants.DOT_GIT_EXT)) { result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); } } return result; }
DescribeNotebookInstanceLifecycleConfigResponse describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance());return invoke(request, options);}
public String getAccessKeySecret() { return accessSecret; }
public CreateVpnConnectionResponse createVpnConnection(CreateVpnConnectionRequest request) {InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(CreateVpnConnectionRequestMarshaller.getInstance()); options.setResponseUnmarshaller(CreateVpnConnectionResponseUnmarshaller.getInstance()); return invoke(CreateVpnConnectionResponse.class, request, options);}
DescribeVoicesResult describeVoices(DescribeVoicesRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(DescribeVoicesRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeVoicesResponseUnmarshaller.getInstance());return invoke(request, options);}
ListMonitoringExecutionsResponse listMonitoringExecutions(ListMonitoringExecutionsRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(null);options.setResponseUnmarshaller(ListMonitoringExecutionsResponseUnmarshaller.getInstance());return invoke(ListMonitoringExecutionsResponse.class, options);}
DescribeJobRequest(String vaultName, String jobId) {this.vaultName = vaultName; this.jobId = jobId;}
EscherRecord getEscherRecord() { return escherRecords[]; }
public GetApisResult getApis(GetApisRequest request) {request = beforeClientExecution(request);return executeGetApis(request);}
public DeleteSmsChannelResult deleteSmsChannel(DeleteSmsChannelRequest request) {request = beforeClientExecution(request);return executeDeleteSmsChannel(request);}
TrackingRefUpdate getTrackingRefUpdate() {}
void print(boolean b) { print(); }
IQueryNode getChild() { return getChildren()[0]; }
NotIgnoredFilter(int workdirTreeIndex) {this.index = workdirTreeIndex;}
AreaRecord(RecordInputStream in1) {field_1_formatFlags = in1.readShort();}
GetThumbnailRequest() { this(" ", " ", " ", " ", " "); Protocol; }
public DescribeTransitGatewayVpcAttachmentsResult describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) {request = beforeClientExecution(request);return executeDescribeTransitGatewayVpcAttachments(request);}
public PutVoiceConnectorStreamingConfigurationResult putVoiceConnectorStreamingConfiguration() {return executePutVoiceConnectorStreamingConfiguration(beforeClientExecution(new PutVoiceConnectorStreamingConfigurationRequest()));}
OrdRange getOrdRange(String dim) {OrdRange result; prefixToOrdRange.tryGetValue(dim, result); return result;}
String toString() { String symbol = ""; if (startIndex < ((CharStream) inputStream).size()) { symbol = ((CharStream) inputStream).getText(Interval.of(startIndex, stopIndex)); symbol = Utils.escapeWhitespace(symbol, false); } return String.format(Locale.getDefault(), "%s %s", Antlr4.Runtime.LexerNoViableAltException.class.getName(), symbol); }
E peek() { return peekFirstImpl(); }
public CreateWorkspacesResult createWorkspaces(CreateWorkspacesRequest request) {request = beforeClientExecution(request);return executeCreateWorkspaces(request);}
public Object clone() {NumberFormatIndexRecord rec = new NumberFormatIndexRecord();rec.field_1_formatIndex = field_1_formatIndex;return rec;}
DescribeRepositoriesResponse describeRepositories(DescribeRepositoriesRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller();options.setResponseUnmarshaller(DescribeRepositoriesResponseUnmarshaller.getInstance());return invoke(DescribeRepositoriesResponse.class, request, options);}
SparseIntArray(int initialCapacity) {initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0;}
TokenStream create() { return new HyphenatedWordsFilter(); }
public CreateDistributionWithTagsResult createDistributionWithTags(CreateDistributionWithTagsRequest request) {request = beforeClientExecution(request);return executeCreateDistributionWithTags(request);}
public RandomAccessFile(String fileName, String mode) { super(new File("")); throw new UnsupportedOperationException(); }
public DeleteWorkspaceImageResult deleteWorkspaceImage(DeleteWorkspaceImageRequest request) {request = beforeClientExecution(request);return executeDeleteWorkspaceImage(request);}
String toHex() { return toHex((long) value); }
UpdateDistributionResponse updateDistribution(UpdateDistributionRequest request) {InvokeOptions options = new InvokeOptions(); UpdateDistributionRequestMarshaller.getInstance(); options.setResponseUnmarshaller(UpdateDistributionResponseUnmarshaller.getInstance()); return invoke(UpdateDistributionResponse.class, request, options);}
HSSFColor getColor(short index) {if (index == getIndex()) return HSSFColor.AUTOMATIC.getInstance(); else {byte[] b = palette.getColor(); if (b != null) {return new CustomColor(,);} } return null; }
ValueEval evaluate(ValueEval operands, int srcRow, int srcCol) {throw new NotImplementedFunctionException();}
void serialize(ILittleEndianOutput out1) { out1.writeShort((short) field_1_number_crn_records); (short) field_2_sheet_table_index; }
public DescribeDBEngineVersionsResult describeDBEngineVersions() { return describeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
FormatRun(short character, short fontIndex) {this._character = character; this._fontIndex = fontIndex;}
byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte) (ch >> 8); result[resultIndex++] = (byte) ch; } return result; }
UploadArchiveResponse uploadArchive(UploadArchiveRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(UploadArchiveRequestMarshaller.getInstance());options.setResponseUnmarshaller(UploadArchiveResponseUnmarshaller.getInstance());return invoke(UploadArchiveResponse.class, request, options);}
public List<?> getHiddenTokensToLeft(int tokenIndex) {return getHiddenTokensToLeft(tokenIndex, -1);}
public boolean equals(Object obj) {if (this == obj) {return true;}if (!(obj instanceof AutomatonQuery)) {return false;}if (this.getClass() != obj.getClass()) {return false;}AutomatonQuery other = (AutomatonQuery) obj;if (!m_compiled.equals(other.m_compiled)) {return false;}if (m_term == null) {if (other.m_term != null) {return false;}} else if (!m_term.equals(other.m_term)) {return false;}return true;}
SpanQuery makeSpanClause() {List<SpanQuery> spanQueries = new ArrayList<>();for (Map.Entry<SpanQuery, ?> wsq : weightBySpanQuery.entrySet()) {wsq.getKey().getBoost();spanQueries.add(wsq.getKey());}if (spanQueries.size() == 1) return spanQueries.get(0); else return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0]));}
public StashCreateCommand stashCreate() { return new StashCreateCommand(); }
FieldInfo fieldInfo(String fieldName) {FieldInfo ret; byName.tryGetValue(fieldName, ret); return ret;}
public DescribeEventSourceResult describeEventSource(DescribeEventSourceRequest request) {request = beforeClientExecution(request);return executeDescribeEventSource(request);}
public GetDocumentAnalysisResult getDocumentAnalysis(GetDocumentAnalysisRequest request) {request = beforeClientExecution(request);return executeGetDocumentAnalysis(request);}
CancelUpdateStackResponse cancelUpdateStack(CancelUpdateStackRequest request) { InvokeOptions options = new InvokeOptions(); CancelUpdateStackRequestMarshaller.Instance; options.setResponseUnmarshaller(CancelUpdateStackResponseUnmarshaller.Instance); return invoke(CancelUpdateStackResponse.class, request, options); }
ModifyLoadBalancerAttributesResponse modifyLoadBalancerAttributes() { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ModifyLoadBalancerAttributesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance()); return invoke(ModifyLoadBalancerAttributesResponse.class, options); }
public SetInstanceProtectionResult setInstanceProtection(SetInstanceProtectionRequest request) {request = beforeClientExecution(request);return executeSetInstanceProtection(request);}
ModifyDBProxyResponse modifyDBProxy(ModifyDBProxyRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(ModifyDBProxyRequestMarshaller.getInstance());options.setResponseUnmarshaller(ModifyDBProxyResponseUnmarshaller.getInstance());return invoke(request, options);}
void add(char[] output, int offset, int len, int endOffset, int posLength) {if (count == outputs.length) {CharsRef[] next = new CharsRef[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_OBJECT_REF)];System.arraycopy(outputs, 0, next, 0, outputs.length);outputs = next;}if (count == endOffsets.length) {int[] next = new int[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_INT32)];System.arraycopy(endOffsets, 0, next, 0, endOffsets.length);endOffsets = next;}if (count == posLengths.length) {int[] next = new int[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_INT32)];System.arraycopy(posLengths, 0, next, 0, posLengths.length);posLengths = next;}if (outputs[count] == null) {outputs[count] = new CharsRef();}outputs[count].copyChars(output, offset, len);endOffsets[count] = endOffset;posLengths[count] = posLength;count++;}
FetchLibrariesRequest() { super(" ", " ", " ", " ", " "); protocol(); }
boolean exists() { return (); }
public FilterOutputStream(OutputStream out) { super(out); }
ScaleClusterRequest() { super(" ", " ", " ", " ", " "); setUriPattern(" "); setMethod(MethodType.POST); }
DataValidationConstraint createTimeConstraint(int operatorType, String formula2) { return DVConstraint.createTimeConstraint(operatorType, formula2); }
public ListObjectParentPathsResult listObjectParentPaths() {return executeListObjectParentPaths(beforeClientExecution(new ListObjectParentPathsRequest()));}
public DescribeCacheSubnetGroupsResponse describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(DescribeCacheSubnetGroupsRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance());return invoke(request, options);}
void setSharedFormula(boolean flag) {field_5_options(,);}
public boolean isReuseObjects() {}
public ErrorNode addErrorNode(Token badToken) {ErrorNodeImpl t = new ErrorNodeImpl(); addChild(); t.getParent();}
public LatvianStemFilterFactory(Map<String, String> args) {super(args);if (!args.isEmpty()) {throw new IllegalArgumentException("" + args);}}
public RemoveSourceIdentifierFromSubscriptionResult removeSourceIdentifierFromSubscription() {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(RemoveSourceIdentifierFromSubscriptionRequestMarshaller.getInstance());options.setResponseUnmarshaller(RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.getInstance());return invoke(RemoveSourceIdentifierFromSubscriptionResult.class, options);}
TokenFilterFactory forName(String name, Map<String, String> args) { return loader.newInstance(name, args); }
AddAlbumPhotosRequest() { super(" ", " ", " ", " ", " "); protocol(); }
public GetThreatIntelSetResult getThreatIntelSet() {return executeGetThreatIntelSet(beforeClientExecution(new GetThreatIntelSetRequest()));}
TreeFilter clone() { return new AndTreeFilter.Binary(a.clone(), b.clone()); }
boolean equals() { return o instanceof ; }
boolean hasArray() { return protectedHasArray; }
UpdateContributorInsightsResponse updateContributorInsights(UpdateContributorInsightsRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(UpdateContributorInsightsRequestMarshaller.getInstance());options.setResponseUnmarshaller(UpdateContributorInsightsResponseUnmarshaller.getInstance());return invoke(UpdateContributorInsightsResponse.class, request, options);}
void unwriteProtectWorkbook() { records.remove(); fileShare = null; writeProtect = null; }
SolrSynonymParser extends BaseSolrParser { super(dedup, analyzer); this.expand = expand; }
public RequestSpotInstancesResult requestSpotInstances(RequestSpotInstancesRequest request) {request = beforeClientExecution(request);return executeRequestSpotInstances(request);}
byte getObjectData() { return findObjectRecord().getObjectData(); }
public GetContactAttributesResponse getContactAttributes(GetContactAttributesRequest request) {request = beforeClientExecution(request);return executeGetContactAttributes(request);}
public String toString() { return getKey() + getValue(); }
public ListTextTranslationJobsResponse listTextTranslationJobs() {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(ListTextTranslationJobsRequestMarshaller.getInstance());options.setResponseUnmarshaller(ListTextTranslationJobsResponseUnmarshaller.getInstance());return invoke(ListTextTranslationJobsResponse.class, options);}
GetContactMethodsResponse getContactMethods(GetContactMethodsRequest request) {InvokeOptions options = new InvokeOptions(); GetContactMethodsRequestMarshaller.getInstance(); options.setResponseUnmarshaller(GetContactMethodsResponseUnmarshaller.getInstance()); return invoke(GetContactMethodsResponse.class, request, options); }
short lookupIndexByName(String name) {FunctionMetadata fd = getInstance().getFunctionByNameInternal();if (fd == null) {return -1;}return (short) 0;}
public DescribeAnomalyDetectorsResult describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) {request = beforeClientExecution(request);return executeDescribeAnomalyDetectors(request);}
String insertId(ObjectId changeId) { return insertId(); }
long getObjectSize(AnyObjectId objectId, int typeHint) { long sz = db.getObjectSize(); if (sz < 0) { if (typeHint == OBJ_ANY) { throw new MissingObjectException("", ""); } throw new MissingObjectException(objectId.copy(), ""); } return sz; }
ImportInstallationMediaResponse importInstallationMedia(ImportInstallationMediaRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(ImportInstallationMediaRequestMarshaller.getInstance());options.setResponseUnmarshaller(ImportInstallationMediaResponseUnmarshaller.getInstance());return invoke(request, options);}
PutLifecycleEventHookExecutionStatusResponse putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance());options.setResponseUnmarshaller(PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance());return invoke(request, options);}
NumberPtg(ILittleEndianInput in1) {field_1_value();}
public GetFieldLevelEncryptionConfigResult getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) {request = beforeClientExecution(request);return executeGetFieldLevelEncryptionConfig(request);}
DescribeDetectorResponse DescribeDetector(DescribeDetectorRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(DescribeDetectorRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeDetectorResponseUnmarshaller.getInstance());return invoke(DescribeDetectorResponse.class, request, options);}
public ReportInstanceStatusResult reportInstanceStatus(ReportInstanceStatusRequest request) {request = beforeClientExecution(request);return executeReportInstanceStatus(request);}
public DeleteAlarmResult deleteAlarm(DeleteAlarmRequest request) {request = beforeClientExecution(request);return executeDeleteAlarm(request);}
TokenStream create() { return new PortugueseStemFilter(); }
FtCblsSubRecord() { reserved = new byte[0]; }
public boolean remove() { synchronized (mutex) { return c.remove(); } }
GetDedicatedIpResponse getDedicatedIp(GetDedicatedIpRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller();options.setResponseUnmarshaller(GetDedicatedIpResponseUnmarshaller.getInstance());return invoke(GetDedicatedIpResponse.class, options);}
@Override public String toString() { return precedence + " "; }
public ListStreamProcessorsResult listStreamProcessors() {return executeListStreamProcessors(beforeClientExecution(new ListStreamProcessorsRequest()));}
DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) {this.loadBalancerName = loadBalancerName; this.policyName = policyName;}
public WindowProtectRecord(int options) {this._options = options;}
UnbufferedCharStream(int bufferSize) {n = 0; data = new int[bufferSize];}
public GetOperationsResponse getOperations(GetOperationsRequest request) {return executeGetOperations(request);}
void copyRawTo(byte[] b) { NB.encodeInt32(, , ); NB.encodeInt32(, o + 4, ); NB.encodeInt32(, o + 8, ); NB.encodeInt32(, o + 12, ); NB.encodeInt32(, o + 16, ); }
public WindowOneRecord(RecordInputStream in1) {field_1_h_hold = in1.readShort(); field_2_v_hold = in1.readShort(); field_3_width = in1.readShort(); field_4_height = in1.readShort(); field_5_options = in1.readShort(); field_6_active_sheet = in1.readShort(); field_7_first_visible_tab = in1.readShort(); field_8_num_selected_tabs = in1.readShort(); field_9_tab_width_ratio = in1.readShort();}
public StopWorkspacesResult stopWorkspaces(StopWorkspacesRequest request) {request = beforeClientExecution(request);return executeStopWorkspaces(request);}
void close() throws IOException {if (isOpen) {isOpen; try {dump();} finally {try {channel.truncate();} finally {try {channel.close();} finally {();}}}}}
public DescribeMatchmakingRuleSetsResult describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) {request = beforeClientExecution(request);return executeDescribeMatchmakingRuleSets(request);}
String getPronunciation(int wordId, char surface, int off, int len) { return null; }
public String getPath() { return pathStr; }
double devsq(double[] v) { double r = Double.NaN; if (v != null && v.length >= 1) { double m = 0; double s = 0; int n = v.length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } return r; }
public DescribeResizeResult describeResize(DescribeResizeRequest request) {request = beforeClientExecution(request);return executeDescribeResize(request);}
public boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
int end() { return end(); }
void traverse(ICellHandler handler) {int firstRow = range.getFirstRow();int lastRow = range.getLastRow();int firstColumn = range.getFirstColumn();int lastColumn = range.getLastColumn();int width = lastColumn - firstColumn + 1;SimpleCellWalkContext ctx = new SimpleCellWalkContext();IRow currentRow = null;ICell currentCell = null;for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ++ctx.rowNumber) {currentRow = sheet.getRow(ctx.rowNumber);if (currentRow == null) {continue;}for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ++ctx.colNumber) {currentCell = currentRow.getCell(ctx.colNumber);if (currentCell == null) {continue;}if (isEmpty() && !traverseEmptyCells) {continue;}int cellIndex = (ctx.rowNumber - firstRow) * width + (ctx.colNumber - firstColumn + 1);handler.onCell(ctx, cellIndex);}}}
public int getReadIndex() { return _ReadIndex; }
int compareTo(ScoreTerm other) { if (term.bytesEquals(other.term)) { return 0; } if (boost == other.boost) { return term.compareTo(other.term); } else { return Float.compare(boost, other.boost); } }
int normalize(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = StemmerUtil.delete(s, i, len); i--; break; default: break; } } return len; }
void serialize(ILittleEndianOutput out1) { () ; }
DiagnosticErrorListener(boolean exactOnly) {this.exactOnly = exactOnly;}
KeySchemaElement(String attributeName, KeyType keyType) {this.attributeName = attributeName; this.keyType = keyType;}
GetAssignmentResult getAssignment(GetAssignmentRequest request) {request = beforeClientExecution(request);return executeGetAssignment(request);}
public boolean hasObject() { return findOffset() != -1; }
GroupingSearch setAllGroups() {allGroups = allGroups; return this;}
void setMultiValued(String dimName, boolean v) { synchronized (this) { if (!fieldTypes.containsKey(dimName)) { fieldTypes.put(dimName, new DimConfig(v)); } else { fieldTypes.get(dimName).setMultiValued(v); } } }
int getCellsVal() {int size = 0; for (char c : cells.keySet()) {Cell e = at(); if (e.cmd >= 0) {;} } return size;}
public DeleteVoiceConnectorResult deleteVoiceConnector(DeleteVoiceConnectorRequest request) {request = beforeClientExecution(request);return executeDeleteVoiceConnector(request);}
public DeleteLifecyclePolicyResponse deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) {InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteLifecyclePolicyRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteLifecyclePolicyResponseUnmarshaller.getInstance()); return invoke(request, options);}
