void serialize(LittleEndianOutput out) { }
void addAll(NGit.Util.BlockList<T> src) { if (src.size == 0) { } int srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) { addAll(src.directory[], , ); } if (src.tailBlkIdx != 0) { addAll(src.tailBlock, , src.tailBlkIdx); } }
void writeByte(byte b) { if (outerInstance.upto == outerInstance.blockSize) { if (outerInstance.currentBlock != null) { outerInstance.blocks.add(outerInstance.currentBlock); outerInstance.blockEnd.add(outerInstance.upto); } outerInstance.currentBlock = new byte[outerInstance.blockSize]; outerInstance.upto = 0; } outerInstance.currentBlock[outerInstance.upto++] = (byte) b; }
ObjectId getObjectId() { return objectId; }
public DeleteDomainEntryResult deleteDomainEntry(DeleteDomainEntryRequest request) {request = beforeClientExecution(request);return executeDeleteDomainEntry(request);}
long ramBytesUsed() { return fst == null ? 0 : fst.ramBytesUsed(); }
String getFullMessage() { byte[] raw = buffer; int msgB = RawParseUtils.tagMessage(raw, 0); if (msgB < 0) { return ""; } Charset enc = RawParseUtils.parseEncoding(raw); return RawParseUtils.decode(enc, raw, msgB, raw.length); }
POIFSFileSystem() { HeaderBlock headerBlock = new HeaderBlock(); _property_table = new PropertyTable(); _documents = new ArrayList(); _root = null; }
void init(int address) {slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; assert slice != null; upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; assert upto < slice.length;}
public SubmoduleAddCommand setPath(String path) { this.path = path; return this; }
ListIngestionsResponse listIngestions(ListIngestionsRequest request) { options = new InvokeOptions(); options.requestMarshaller = ListIngestionsRequestMarshaller.instance; options.responseUnmarshaller = ListIngestionsResponseUnmarshaller.instance; return invoke(request, options); }
QueryParserTokenManager(CharStream stream, int lexState) { this(stream); SwitchTo(lexState); }
GetShardIteratorResult getShardIterator(GetShardIteratorRequest request) { request = beforeClientExecution(request); return executeGetShardIterator(request); }
ModifyStrategyRequest() : ("", "", "", "", "") { method(); }
boolean ready() throws IOException { synchronized (lock) { if (in == null) { throw new IOException(""); } try { return bytes.hasRemaining() || in.available() > 0; } catch (IOException e) { return false; } } }
EscherOptRecord getOptRecord() { }
int read(byte[] buffer, int offset, int length) { synchronized(this) { if (buffer == null) { throw new IllegalArgumentException(""); } java.util.Arrays.checkOffsetAndCount(buffer.length, offset, length); if (length == 0) { return 0; } int copylen = count - pos < length ? count - pos : length; { for (int i = 0; i < copylen; i++) { buffer[offset + i] = (byte)this.buffer[pos + i]; } } pos += copylen; return copylen; } }
OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) { this.sentenceOp = sentenceOp; }
void print(String str) { write(str != null ? str : String.valueOf((Object) null)); }
NotImplementedFunctionException(String functionName, Throwable cause) { super(functionName, cause); this.functionName = functionName; }
V next() { return nextEntry.value; }
void readBytes(byte[] b, int offset, int len, boolean useBuffer) { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) { System.arraycopy(buffer, bufferPosition, b, offset, len); } bufferPosition += len; } else { if (available > 0) { System.arraycopy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { refill(); if (bufferLength < len) { System.arraycopy(buffer, 0, b, offset, bufferLength); throw new EOFException("read past EOF: " + this); } else { System.arraycopy(buffer, 0, b, offset, len); bufferPosition += len; } } else { long after = bufferStart + bufferPosition + len; if (after > length()) { throw new EOFException("read past EOF: " + this); } readInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
TagQueueResult tagQueue(TagQueueRequest request) { request = beforeClientExecution(request); return executeTagQueue(request); }
void remove() { throw new UnsupportedOperationException(); }
ModifyCacheSubnetGroupResult modifyCacheSubnetGroup() { return executeModifyCacheSubnetGroup(); }
void setParams(String params) { setParams(); String culture = ""; StringTokenizer st = new StringTokenizer(params, " "); if (st.hasMoreTokens()) culture = st.nextToken(); if (st.hasMoreTokens()) culture = "" + st.nextToken(); if (st.hasMoreTokens()) st.nextToken(); }
public DeleteDocumentationVersionResult deleteDocumentationVersion(DeleteDocumentationVersionRequest request) {request = beforeClientExecution(request);return executeDeleteDocumentationVersion(request);}
boolean equals(Object obj) { if (!(obj instanceof FacetLabel)) { return false; } FacetLabel other = (FacetLabel) obj; if (length != other.length) { return false; } for (int i = length - 1; i >= 0; i--) { if (!components[i].equals(other.components[i])) { return false; } } return true; }
GetInstanceAccessDetailsResponse getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) {InvokeOptions options = new InvokeOptions();options.requestMarshaller;options.responseUnmarshaller = GetInstanceAccessDetailsResponseUnmarshaller.getInstance();return invoke(request, options);}
HSSFPolygon createPolygon ( ) { HSSFPolygon shape = new HSSFPolygon ( , ) ; shape . setParent ; shape . setAnchor anchor ; shapes . add ( ) ; onCreate ( ) ; return shape ; }
String getSheetName() { return getBoundSheetRec().getSheetname(); }
public GetDashboardResult getDashboard(GetDashboardRequest request) {request = beforeClientExecution(request);return executeGetDashboard(request);}
AssociateSigninDelegateGroupsWithAccountResult associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) {request = beforeClientExecution(request);return executeAssociateSigninDelegateGroupsWithAccount(request);}
void addMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < mbr.getNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.setColumn(j + mbr.getFirstColumn()); br.setRow(mbr.getRow()); br.setXFIndex(mbr.getXFAt(j)); insertCell(br); } }
String quote(String string) { StringBuilder sb = new StringBuilder(); sb.append("\""); int apos = 0; int k; while ((k = string.indexOf("\"", apos)) >= 0) { sb.append(string.substring(apos, k + 1)).append("\\\""); apos = k + 1; } return sb.append(string.substring(apos)).append("\"").toString(); }
java.nio.ByteBuffer putInt(int value) { throw new ReadOnlyBufferException(); }
ArrayPtg(Object[][]) { int nColumns = values2d[].length; int nRows = values2d.length; _nColumns = (short)nColumns; _nRows = (short)nRows; Object[] vv = new Object[_nColumns * _nRows]; for(int r = 0; r < nRows; r++) { Object[] rowData = values2d[r]; for(int c = 0; c < nColumns; c++) { vv[getValueIndex(r,c)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
public GetIceServerConfigResult getIceServerConfig(GetIceServerConfigRequest request) { request = beforeClientExecution(request); return executeGetIceServerConfig(request); }
public String toString() { StringBuilder sb = new StringBuilder(); sb.append(getClass().getSimpleName()).append(" "); sb.append(getValueAsString()); sb.append(" "); return sb.toString(); }
public String toString() { return " " + parentQuery + " "; }
void incRef() { refCount.incrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResult updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { request = beforeClientExecution(request); return executeUpdateConfigurationSetSendingEnabled(request); }
int getNextXBATChainOffset() { return getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE; }
void multiplyByPowerOfTen(int pow10) {TenPower tp = TenPower.getInstance(Math.abs(pow10));if (pow10 < 0) {mulShift(tp.divisor, tp.divisorShift);} else {mulShift(tp.multiplicand, tp.multiplierShift);}}
public String toString() { StringBuilder builder = new StringBuilder(); int length = getLength(); builder.append(File.separatorChar); for (int i = 0; i < length; i++) { builder.append(getComponent(i)); if (i < (length - 1)) { builder.append(File.separatorChar); } } return builder.toString(); }
void withFetcher() {.fetcher fetcher; .fetcher.setRoleName();}
void setProgressMonitor() { ProgressMonitor pm; }
void reset() { if () { ptr = 0; if (!eof) { parseEntry(); } } }
E previous() { if ( iterator . previousIndex ( ) >= start ) { return iterator . previous ( ) ; } throw new java . util . NoSuchElementException ( ) ; }
String getNewPrefix() { return newPrefix; }
int indexOfValue(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1; }
List<CharsRef> uniqueStems(char[] word, int length) { List<CharsRef> stems = stem(word, length); if (stems.size() < 2) { return stems; } CharArraySet terms = new CharArraySet(8, dictionary.getIgnoreCase()); List<CharsRef> deduped = new ArrayList<CharsRef>(); for (CharsRef s : stems) { if (!terms.contains(s.chars, s.offset, s.length)) { deduped.add(s); terms.add(s.chars, s.offset, s.length); } } return deduped; }
public GetGatewayResponsesResult getGatewayResponses() { return executeGetGatewayResponses(new GetGatewayResponsesRequest()); }
void setPosition(long position) { currentBlockIndex = (int) (position >> outerInstance.blockBits); currentBlock = outerInstance.blocks[currentBlockIndex]; currentBlockUpto = (int) (position & outerInstance.blockMask); }
long skip(long n) { int s = (int) Math.min(available(), Math.max(0, n)); ptr += s; return s; }
BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { this.bootstrapActionConfig = bootstrapActionConfig; }
void serialize(LittleEndianOutput out1) {out1.writeShort();out1.writeShort();out1.writeShort();out1.writeShort();(field_6_author.length());out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00);if (field_5_hasMultibyte) {StringUtil.putUnicodeLE(,);} else {StringUtil.putCompressedUnicode(,);}if (field_7_padding != null) {out1.writeByte(Integer.parseInt(,));}}
int lastIndexOf() { return lastIndexOf(,); }
boolean add() { return addLastImpl(); }
void unsetSection(String section, String subsection) { ConfigSnapshot; ConfigSnapshot; do { src state.get(); res unsetSection(, ,); } while (!state.compareAndSet(, )); }
String getTagName() { return tagName; }
void addSubRecord(int index) { subrecords.add(index); }
boolean remove() { synchronized (mutex) { return c.remove(); } }
TokenStream create() { return new DoubleMetaphoneFilter(, ,); }
long getLength() { return inCoreLength(); }
void setValue() { value newValue; }
Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
int get(int i) { if (i < 0 || i >= entries.length) { throw new IndexOutOfBoundsException(); } return entries[i]; }
CreateRepoRequest() : ("", "", "", "", "") { uriPattern; method = MethodType.PUT; }
boolean isDeltaBaseAsOffset() { }
void remove() { if (expectedModCount == list.modCount) { if (lastLink != null) { java.util.LinkedList.Link<ET> next_1 = lastLink.next; java.util.LinkedList.Link<ET> previous_1 = lastLink.previous; next_1.previous = previous_1; previous_1.next = next_1; if (lastLink == link) { pos--; } link = previous_1; lastLink = null; expectedModCount++; list._size--; list.modCount++; } else { throw new IllegalStateException(); } } else { throw new java.util.ConcurrentModificationException(); } }
public MergeShardsResult mergeShards(MergeShardsRequest request) {request = beforeClientExecution(request);return executeMergeShards(request);}
public AllocateHostedConnectionResult allocateHostedConnection(AllocateHostedConnectionRequest request) {request = beforeClientExecution(request);return executeAllocateHostedConnection(request);}
int getBeginIndex() { return start; }
WeightedTerm getTerms(Query query) { return getTerms(, ); }
java.nio.ByteBuffer compact() { throw new java.nio.ReadOnlyBufferException(); }
void decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (int) ((byte0 & 0xFF) >>> 2); int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte0 & 3) << 4) | ((int) ((byte1 & 0xFF) >>> 4)); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte1 & 15) << 2) | ((int) ((byte2 & 0xFF) >>> 6)); values[valuesOffset++] = byte2 & 63; } }
String getHumanishName() { if ("".equals(getPath()) || getPath() == null) { throw new IllegalArgumentException(); } String s = getPath(); String[] elements; if ("/".equals(File.separator) || LOCAL_FILE.matcher(s).matches()) { elements = s.split("[/\\" + File.separator + "]"); } else { elements = s.split("/"); } if (elements.length == 0) { throw new IllegalArgumentException(); } String result = elements[elements.length - 1]; if (Constants.DOT_GIT.equals(result)) { result = elements[elements.length - 2]; } else { if (result.endsWith(Constants.DOT_GIT_EXT)) { result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); } } return result; }
public DescribeNotebookInstanceLifecycleConfigResult describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { request = beforeClientExecution(request); return executeDescribeNotebookInstanceLifecycleConfig(request); }
String getAccessKeySecret() { return accessSecret; }
public CreateVpnConnectionResult createVpnConnection(CreateVpnConnectionRequest request) { request = beforeClientExecution(request); return executeCreateVpnConnection(request); }
public DescribeVoicesResult describeVoices(DescribeVoicesRequest request) { request = beforeClientExecution(request); return executeDescribeVoices(request); }
ListMonitoringExecutionsResult listMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = beforeClientExecution(request); return executeListMonitoringExecutions(request); }
DescribeJobRequest(String vaultName, String jobId) { this.vaultName = vaultName; this.jobId = jobId; }
EscherRecord getEscherRecord() { return escherRecords[]; }
GetApisResponse getApis(GetApisRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(); options.setResponseUnmarshaller(GetApisResponseUnmarshaller.getInstance()); return invoke(request, options); }
DeleteSmsChannelResult deleteSmsChannel(DeleteSmsChannelRequest request) { request = beforeClientExecution(request); return executeDeleteSmsChannel(request); }
TrackingRefUpdate getTrackingRefUpdate() { }
void print(boolean b) { print(); }
IQueryNode getChild() { return getChildren()[0]; }
NotIgnoredFilter(int workdirTreeIndex) { this.index = workdirTreeIndex; }
AreaRecord(RecordInputStream in1) { field_1_formatFlags = in1.readShort(); }
GetThumbnailRequest() : ("", "", "", "", "") { protocol; }
DescribeTransitGatewayVpcAttachmentsResult describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) {request = beforeClientExecution(request);return executeDescribeTransitGatewayVpcAttachments(request);}
public PutVoiceConnectorStreamingConfigurationResult putVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) {request = beforeClientExecution(request);return executePutVoiceConnectorStreamingConfiguration(request);}
OrdRange getOrdRange(String dim) { OrdRange result = prefixToOrdRange.get(dim); return result; }
public String toString() { String symbol = ""; if (startIndex >= 0 && startIndex < ((CharStream) input).size()) { symbol = ((CharStream) input).getText(Interval.of(startIndex, startIndex)); symbol = Utils.escapeWhitespace(symbol, false); } return String.format(Locale.getDefault(), "LexerNoViableAltException('%s')", symbol); }
E peek() { return peekFirstImpl(); }
CreateWorkspacesResult createWorkspaces(CreateWorkspacesRequest request) { options = new InvokeOptions(); CreateWorkspacesRequestMarshaller.getInstance(); options.setResponseUnmarshaller(CreateWorkspacesResponseUnmarshaller.getInstance()); return executeCreateWorkspaces(request); }
Object clone() { NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = field_1_formatIndex; return rec; }
DescribeRepositoriesResult describeRepositories(DescribeRepositoriesRequest request) { request = beforeClientExecution(request); return executeDescribeRepositories(request); }
SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
TokenStream create() { return new HyphenatedWordsFilter(); }
CreateDistributionWithTagsResult createDistributionWithTags(CreateDistributionWithTagsRequest request) { request = beforeClientExecution(request); return executeCreateDistributionWithTags(request); }
RandomAccessFile(String fileName, String mode) throws FileNotFoundException { throw new UnsupportedOperationException(); }
public DeleteWorkspaceImageResult deleteWorkspaceImage(DeleteWorkspaceImageRequest request) {request = beforeClientExecution(request);return executeDeleteWorkspaceImage(request);}
String toHex() { return toHex((long) value); }
public UpdateDistributionResult updateDistribution(UpdateDistributionRequest request) {request = beforeClientExecution(request);return executeUpdateDistribution(request);}
HSSFColor getColor(short index) { if (index == HSSFColor.AUTOMATIC.getIndex()) return HSSFColor.AUTOMATIC.getInstance(); else { byte[] b = palette.getColor(index); if (b != null) { return new CustomColor(index, b); } } return null; }
ValueEval evaluate(ValueEval operands, int srcRow, int srcCol) { throw new NotImplementedException(); }
void serialize(LittleEndianOutput out) { out.writeShort((short) field_1_number_crn_records); out.writeShort((short) field_2_sheet_table_index); }
DescribeDBEngineVersionsResult describeDBEngineVersions() { return describeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
FormatRun(short character, short fontIndex) { this._character = character; this._fontIndex = fontIndex; }
byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
public UploadArchiveResult uploadArchive(UploadArchiveRequest request) { request = beforeClientExecution(request); return executeUploadArchive(request); }
List getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex, -1); }
boolean equals(Object obj) { if (this == obj) { return true; } if (!super.equals(obj)) { return false; } if (this.getClass() != obj.getClass()) { return false; } AutomatonQuery other = (AutomatonQuery) obj; if (!m_compiled.equals(other.m_compiled)) { return false; } if (m_term == null) { if (other.m_term != null) { return false; } } else if (!m_term.equals(other.m_term)) { return false; } return true; }
SpanQuery makeSpanClause() { List<SpanQuery> spanQueries = new ArrayList<SpanQuery>(); for (wsq : weightBySpanQuery) { wsq.getKey().getBoost(); spanQueries.add(wsq.getKey()); } if (spanQueries.size() == 1) return spanQueries.get(0); else return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); }
StashCreateCommand getStashCreate() { return new StashCreateCommand(); }
FieldInfo fieldInfo(String fieldName) { FieldInfo ret = byName.get(fieldName); return ret; }
DescribeEventSourceResponse describeEventSource(DescribeEventSourceRequest request) { options = new InvokeOptions(); DescribeEventSourceRequestMarshaller.getInstance(); options.responseUnmarshaller = DescribeEventSourceResponseUnmarshaller.getInstance(); return invoke(DescribeEventSourceResponse.class); }
GetDocumentAnalysisResponse getDocumentAnalysis(GetDocumentAnalysisRequest request) { request = beforeClientExecution(request); return executeGetDocumentAnalysis(request); }
CancelUpdateStackResponse cancelUpdateStack(CancelUpdateStackRequest request) { options = new InvokeOptions(); CancelUpdateStackRequestMarshaller.getInstance(); options.responseUnmarshaller = CancelUpdateStackResponseUnmarshaller.getInstance(); return invoke(CancelUpdateStackResponse.class, options); }
ModifyLoadBalancerAttributesResult modifyLoadBalancerAttributes() { request = beforeClientExecution(request); return executeModifyLoadBalancerAttributes(request); }
SetInstanceProtectionResponse setInstanceProtection(SetInstanceProtectionRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(SetInstanceProtectionRequestMarshaller.getInstance()); options.setResponseUnmarshaller(SetInstanceProtectionResponseUnmarshaller.getInstance()); return invoke(request, options); }
public ModifyDBProxyResult modifyDBProxy(ModifyDBProxyRequest request) {request = beforeClientExecution(request);return executeModifyDBProxy(request);}
void add(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.length) { CharsRef[] next = new CharsRef[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_OBJECT_REF)]; System.arraycopy(outputs, 0, next, 0, outputs.length); outputs = next; } if (count == endOffsets.length) { int[] next = new int[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(endOffsets, 0, next, 0, endOffsets.length); endOffsets = next; } if (count == posLengths.length) { int[] next = new int[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(posLengths, 0, next, 0, posLengths.length); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRef(); } outputs[count].copyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
FetchLibrariesRequest() : ("", "", "", "", "") { protocol; }
boolean exists() { return (); }
FilterOutputStream(OutputStream out) { this.out = out; }
ScaleClusterRequest() : this("", "", "", "", "") { setUriPattern(""); setMethod(); }
IDataValidationConstraint createTimeConstraint(int operatorType, String formula1, String formula2) { return DVConstraint.createTimeConstraint(operatorType, formula1, formula2); }
public ListObjectParentPathsResult listObjectParentPaths() { request = beforeClientExecution(request); return executeListObjectParentPaths(request); }
DescribeCacheSubnetGroupsResponse describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeCacheSubnetGroupsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(); return invoke(DescribeCacheSubnetGroupsResponse.class); }
void setSharedFormula(boolean flag) { field_5_options(, ); }
boolean isReuseObjects() { }
ErrorNode addErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(); addChild(); t.parent; }
LatvianStemFilterFactory(Map<String,String> args) : super(args) { if (args.size() > 0) { throw new IllegalArgumentException("" + args); } }
RemoveSourceIdentifierFromSubscriptionResult removeSourceIdentifierFromSubscription() { request = beforeClientExecution(request); return executeRemoveSourceIdentifierFromSubscription(request); }
TokenFilterFactory forName ( , Map < String , String > args ) { return loader . newInstance ( , ) ; }
AddAlbumPhotosRequest() : ("", "", "", "", "") { Protocol; }
GetThreatIntelSetResult getThreatIntelSet() { request = beforeClientExecution(request); return executeGetThreatIntelSet(request); }
TreeFilter clone() { return new AndTreeFilter.Binary(a.clone(), b.clone()); }
boolean equals(Object o) { return o instanceof ; }
boolean hasArray() { return protectedHasArray; }
public UpdateContributorInsightsResult updateContributorInsights(UpdateContributorInsightsRequest request) { request = beforeClientExecution(request); return executeUpdateContributorInsights(request); }
void unwriteProtectWorkbook() { records.remove(); fileShare = null; writeProtect = null; }
SolrSynonymParser : super ( dedup , analyzer ) {  . expand expand ; }
public RequestSpotInstancesResult requestSpotInstances(RequestSpotInstancesRequest request) {request = beforeClientExecution(request);return executeRequestSpotInstances(request);}
byte getObjectData() { return findObjectRecord().objectData; }
GetContactAttributesResponse getContactAttributes(GetContactAttributesRequest request) { InvokeOptions options = new InvokeOptions(); GetContactAttributesRequestMarshaller.getInstance(); options.setResponseUnmarshaller(GetContactAttributesResponseUnmarshaller.getInstance()); return invoke(request, options); }
String toString() { return getKey() + getValue(); }
public ListTextTranslationJobsResult listTextTranslationJobs() {return executeListTextTranslationJobs();}
public GetContactMethodsResult getContactMethods(GetContactMethodsRequest request) { request = beforeClientExecution(request); return executeGetContactMethods(request); }
short lookupIndexByName(String name) { FunctionMetadata fd = getInstance().getFunctionByNameInternal(); if (fd == null) { return -1; } return (short); }
public DescribeAnomalyDetectorsResult describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) {request = beforeClientExecution(request);return executeDescribeAnomalyDetectors(request);}
String insertId(ObjectId changeId) { return insertId(); }
long getObjectSize(AnyObjectId objectId, int typeHint) throws IOException { long sz = db.getObjectSize(objectId, typeHint); if (sz < 0) { if (typeHint == OBJ_ANY) { throw new MissingObjectException(objectId.copy(), ""); } throw new MissingObjectException(objectId.copy(), typeHint); } return sz; }
ImportInstallationMediaResult importInstallationMedia(ImportInstallationMediaRequest request) { options = new InvokeOptions(); options.requestMarshaller = ImportInstallationMediaRequestMarshaller.getInstance(); options.responseUnmarshaller = ImportInstallationMediaResponseUnmarshaller.getInstance(); return invoke(request, options); }
public PutLifecycleEventHookExecutionStatusResult putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) {request = beforeClientExecution(request);return executePutLifecycleEventHookExecutionStatus(request);}
NumberPtg(LittleEndianInput in1) { field_1_value(); }
public GetFieldLevelEncryptionConfigResult getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) {request = beforeClientExecution(request);return executeGetFieldLevelEncryptionConfig(request);}
DescribeDetectorResponse describeDetector(DescribeDetectorRequest request) { options = new InvokeOptions(); options.RequestMarshaller = DescribeDetectorRequestMarshaller.Instance; options.ResponseUnmarshaller = DescribeDetectorResponseUnmarshaller.Instance; return invoke(request, options); }
ReportInstanceStatusResult reportInstanceStatus(ReportInstanceStatusRequest request) {request = beforeClientExecution(request);return executeReportInstanceStatus(request);}
public DeleteAlarmResult deleteAlarm(DeleteAlarmRequest request) {request = beforeClientExecution(request);return executeDeleteAlarm(request);}
TokenStream create() { return new PortugueseStemFilter(); }
FtCblsSubRecord() { reserved = new byte[0]; }
boolean remove() { synchronized (mutex) { return c.remove(); } }
public GetDedicatedIpResult getDedicatedIp(GetDedicatedIpRequest request) { request = beforeClientExecution(request); return executeGetDedicatedIp(request); }
public String toString() { return precedence + " " ; }
public ListStreamProcessorsResult listStreamProcessors() { return executeListStreamProcessors(new ListStreamProcessorsRequest()); }
DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) { this.loadBalancerName = loadBalancerName; this.policyName = policyName; }
WindowProtectRecord(int options) { this.options = options; }
UnbufferedCharStream(int bufferSize) { n = 0; data = new int[bufferSize]; }
GetOperationsResponse getOperations(GetOperationsRequest request) { options = new InvokeOptions(); options.RequestMarshaller = GetOperationsRequestMarshaller.Instance; options.ResponseUnmarshaller = GetOperationsResponseUnmarshaller.Instance; return executeGetOperations(request); }
void copyRawTo(byte[] b, int o) { NB.encodeInt32(b, o, h1); NB.encodeInt32(b, o + 4, h2); NB.encodeInt32(b, o + 8, h3); NB.encodeInt32(b, o + 12, h4); NB.encodeInt32(b, o + 16, h5); }
WindowOneRecord(RecordInputStream in1) { field_1_h_hold = in1.readShort(); field_2_v_hold = in1.readShort(); field_3_width = in1.readShort(); field_4_height = in1.readShort(); field_5_options = in1.readShort(); field_6_active_sheet = in1.readShort(); field_7_first_visible_tab = in1.readShort(); field_8_num_selected_tabs = in1.readShort(); field_9_tab_width_ratio = in1.readShort(); }
public StopWorkspacesResult stopWorkspaces(StopWorkspacesRequest request) {request = beforeClientExecution(request);return executeStopWorkspaces(request);}
void close() throws IOException { if (isOpen) { isOpen = false; try { dump(); } finally { try { channel.truncate(0); } finally { try { channel.close(); } finally { cleanup(); } } } } }
public DescribeMatchmakingRuleSetsResult describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { request = beforeClientExecution(request); return executeDescribeMatchmakingRuleSets(request); }
String getPronunciation(int wordId, char surface, int off, int len) { return null; }
String getPath() { return pathStr; }
double devsq(double[] v) { double r = Double.NaN; if (v != null && v.length >= 1) { double m = 0; double s = 0; int n = v.length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } return r; }
DescribeResizeResponse describeResize(DescribeResizeRequest request) { options = new InvokeOptions(); options.requestMarshaller = DescribeResizeRequestMarshaller.instance; options.responseUnmarshaller = DescribeResizeResponseUnmarshaller.instance; return invoke(request, options); }
boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
int end() { return end(); }
void traverse(ICellHandler handler) { int firstRow = range.getFirstRow(); int lastRow = range.getLastRow(); int firstColumn = range.getFirstColumn(); int lastColumn = range.getLastColumn(); int width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); IRow currentRow = null; ICell currentCell = null; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ++ctx.rowNumber) { currentRow = sheet.getRow(ctx.rowNumber); if (currentRow == null) { continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ++ctx.colNumber) { currentCell = currentRow.getCell(ctx.colNumber); if (currentCell == null) { continue; } if (isEmpty() && !traverseEmptyCells) { continue; } int cellIndex = (ctx.rowNumber - firstRow) * width + (ctx.colNumber - firstColumn + 1); handler.onCell(currentCell, ctx); } } }
public final int getReadIndex() {return _ReadIndex;}
int compareTo(ScoreTerm other) { if (term.bytesEquals(other.term)) { return 0; } if (boost == other.boost) { return term.compareTo(other.term); } else { return boost.compareTo(other.boost); } }
int normalize(char[] s, int len) {for (int i = 0; i < len; i++) {switch (s[i]) {case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = StemmerUtil.delete(s, i, len); i--; break; default: break;}} return len;}
void serialize(LittleEndianOutput out) { }
DiagnosticErrorListener(boolean exactOnly) { this.exactOnly = exactOnly; }
KeySchemaElement(String attributeName, KeyType keyType) { this._attributeName = attributeName; this._keyType = keyType; }
public GetAssignmentResult getAssignment(GetAssignmentRequest request) { request = beforeClientExecution(request); return executeGetAssignment(request); }
boolean hasObject() { return findOffset() != -1; }
GroupingSearch setAllGroups() { this.allGroups = true; return this; }
void setMultiValued(String dimName, boolean v) { synchronized(this) { DimConfig fieldType = fieldTypes.get(dimName); if (fieldType == null) { fieldTypes.put(dimName, new DimConfig(v)); } else { fieldType.setMultiValued(v); } } }
int getCellsVal() { int size = 0; for (char c : cells.keySet()) { Cell e = at(); if (e.cmd >= 0) { ; } } return size; }
public DeleteVoiceConnectorResult deleteVoiceConnector(DeleteVoiceConnectorRequest request) {request = beforeClientExecution(request);return executeDeleteVoiceConnector(request);}
public DeleteLifecyclePolicyResult deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) {request = beforeClientExecution(request);return executeDeleteLifecyclePolicy(request);}
