void serialize(LittleEndianOutput out1) { }
void addAll(NGit.Util.BlockList<T> src) { if (src.size == 0) { } int srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) { addAll(src.directory[], , ); } if (src.tailBlkIdx != 0) { addAll(src.tailBlock, , src.tailBlkIdx); } }
void writeByte(byte b) { if (outerInstance.upto == outerInstance.blockSize) { if (outerInstance.currentBlock != null) { outerInstance.blocks.add(outerInstance.currentBlock); outerInstance.blockEnd.add(outerInstance.upto); } outerInstance.currentBlock = new byte[outerInstance.blockSize]; outerInstance.upto = 0; } outerInstance.currentBlock[outerInstance.upto++] = (byte) b; }
ObjectId getObjectId() { return objectId; }
DeleteDomainEntryResponse deleteDomainEntry(DeleteDomainEntryRequest request) { options = new InvokeOptions(); options.requestMarshaller; options.responseUnmarshaller DeleteDomainEntryResponseUnmarshaller.instance; return invoke<DeleteDomainEntryResponse>(, ); }
long ramBytesUsed() { return fst == null ? 0 : fst.getSizeInBytes(); }
String getFullMessage() { byte[] raw = buffer; int msgB = RawParseUtils.tagMessage(); if (msgB < 0) { return ""; } Charset enc = RawParseUtils.parseEncoding(); return RawParseUtils.decode(enc, raw, msgB, raw.length); }
POIFSFileSystem() { HeaderBlock headerBlock = new HeaderBlock(); _property_table = new PropertyTable(); _documents = new ArrayList(); _root = null; }
void Init(int address) { slice = pool.Buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; assert slice != null; upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; assert upto < slice.length; }
public SubmoduleAddCommand setPath(String path) { this.path = path; return this; }
ListIngestionsResponse ListIngestions(ListIngestionsRequest request) { options = new InvokeOptions(); options.RequestMarshaller = ListIngestionsRequestMarshaller.Instance; options.ResponseUnmarshaller = ListIngestionsResponseUnmarshaller.Instance; return invoke(request, options); }
QueryParserTokenManager(CharStream stream, int lexState) { this.switchTo(); }
GetShardIteratorResponse GetShardIterator(GetShardIteratorRequest request) { options = new InvokeOptions(); options.RequestMarshaller = GetShardIteratorRequestMarshaller.Instance; options.ResponseUnmarshaller = GetShardIteratorResponseUnmarshaller.Instance; return invoke(request, options); }
ModifyStrategyRequest() : ("", "", "", "", "") { Method; }
boolean ready() { synchronized(lock) { if(in == null) { throw new java.io.IOException(""); } try { return bytes.hasRemaining() || in.available() > 0; } catch(java.io.IOException e) { return false; } } }
EscherOptRecord getOptRecord ( ) { }
int read(byte[] buffer, int offset, int length) { synchronized(this) { if (buffer == null) { throw new IllegalArgumentException(""); } java.util.Arrays.checkOffsetAndCount(buffer.length, offset, length); if (length == 0) { return 0; } int copylen = count - pos < length ? count - pos : length; { for (int i = 0; i < copylen; i++) { buffer[offset + i] = (byte) this.buffer[pos + i]; } } pos += copylen; return copylen; } }
OpenNLPSentenceBreakIterator ( NLPSentenceDetectorOp sentenceOp ) { this.sentenceOp = sentenceOp; }
void print(String str) { write(str != null ? str : String.valueOf((Object) null)); }
NotImplementedFunctionException(String functionName, Throwable cause) { super(functionName, cause); this.functionName = functionName; }
V next() { return nextEntry.value; }
void readBytes(byte[] b, int offset, int len, boolean useBuffer) { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) { System.arraycopy(buffer, bufferPosition, b, offset, len); } bufferPosition += len; } else { if (available > 0) { System.arraycopy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { refill(); if (bufferLength < len) { System.arraycopy(buffer, 0, b, offset, bufferLength); throw new EOFException("read past EOF: " + this); } else { System.arraycopy(buffer, 0, b, offset, len); bufferPosition += len; } } else { long after = bufferStart + bufferPosition + len; if (after > length()) { throw new EOFException("read past EOF: " + this); } readInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
TagQueueResponse tagQueue(TagQueueRequest request) { options = new InvokeOptions(); options.requestMarshaller = TagQueueRequestMarshaller.getInstance(); options.responseUnmarshaller = TagQueueResponseUnmarshaller.getInstance(); return invoke(request, options); }
void remove() { throw new UnsupportedOperationException(); }
ModifyCacheSubnetGroupResponse modifyCacheSubnetGroup() { options = new InvokeOptions(); options.requestMarshaller = ModifyCacheSubnetGroupRequestMarshaller.instance; options.responseUnmarshaller = ModifyCacheSubnetGroupResponseUnmarshaller.instance; return invoke(ModifyCacheSubnetGroupResponse.class); }
void setParams(String params) { setParams(); String culture = ""; StringTokenizer st = new StringTokenizer(params, " "); if (st.hasMoreTokens()) culture = st.nextToken(); if (st.hasMoreTokens()) culture = "" + st.nextToken(); if (st.hasMoreTokens()) String ignore = st.nextToken(); }
public DeleteDocumentationVersionResult deleteDocumentationVersion(DeleteDocumentationVersionRequest request) {request = beforeClientExecution(request);return executeDeleteDocumentationVersion(request);}
boolean equals(Object obj) { if (!(obj instanceof FacetLabel)) { return false; } FacetLabel other = (FacetLabel) obj; if (length != other.length) { return false; } for (int i = length - 1; i >= 0; i--) { if (!components[i].equals(other.components[i])) { return false; } } return true; }
GetInstanceAccessDetailsResponse GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller; options.ResponseUnmarshaller = GetInstanceAccessDetailsResponseUnmarshaller.Instance; return Invoke<GetInstanceAccessDetailsResponse>(request, options); }
HSSFPolygon CreatePolygon ( ) { HSSFPolygon shape = new HSSFPolygon ( , ) ; shape . Parent ; shape . Anchor anchor ; shapes . Add ( ) ; OnCreate ( ) ; return shape ; }
String getSheetName ( ) { return getBoundSheetRec ( ) . getSheetname ( ) ; }
public GetDashboardResult getDashboard(GetDashboardRequest request) { request = beforeClientExecution(request); return executeGetDashboard(request); }
AssociateSigninDelegateGroupsWithAccountResponse associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(AssociateSigninDelegateGroupsWithAccountRequestMarshaller.getInstance()); options.setResponseUnmarshaller(AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.getInstance()); return invoke(AssociateSigninDelegateGroupsWithAccountResponse.class, request, options); }
void addMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < mbr.getNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.setColumn(j + mbr.getFirstColumn()); br.setRow(mbr.getRow()); br.setXFIndex(mbr.getXFAt(j)); insertCell(br); } }
String quote(String string) { StringBuilder sb = new StringBuilder(); sb.append("\""); int apos = 0; int k; while ((k = string.indexOf("\"", apos)) >= 0) { sb.append(string.substring(apos, k + 1)).append("\\\""); apos = k + 1; } return sb.append(string.substring(apos)).append("\"").toString(); }
java.nio.ByteBuffer putInt(int value) { throw new ReadOnlyBufferException(); }
ArrayPtg(Object[][] values2d) { int nColumns = values2d[0].length; int nRows = values2d.length; _nColumns = (short) nColumns; _nRows = (short) nRows; Object[] vv = new Object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { Object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[GetValueIndex(r, c)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
public GetIceServerConfigResult getIceServerConfig(GetIceServerConfigRequest request) { request = beforeClientExecution(request); return executeGetIceServerConfig(request); }
String toString() { StringBuilder sb = new StringBuilder(); sb.append(getClass().getName()).append(" "); sb.append(getValueAsString()); sb.append(" "); return sb.toString(); }
public String toString() { return " " + _parentQuery + " "; }
void incRef() { refCount.incrementAndGet(); }
UpdateConfigurationSetSendingEnabledResponse updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){InvokeOptions options=new InvokeOptions();options.requestMarshaller=UpdateConfigurationSetSendingEnabledRequestMarshaller.getInstance();options.responseUnmarshaller=UpdateConfigurationSetSendingEnabledResponseUnmarshaller.getInstance();return invoke(options);}
int getNextXBATChainOffset() { return getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE; }
void multiplyByPowerOfTen(int pow10) { TenPower tp = TenPower.getInstance(Math.abs()); if () { mulShift(tp._divisor, tp._divisorShift); } else { mulShift(tp._multiplicand, tp._multiplierShift); } }
String toString() { StringBuilder builder = new StringBuilder(); int length = this.length(); builder.append(File.separatorChar); for (int i = 0; i < length; i++) { builder.append(this.getComponent(i)); if (i < (length - 1)) { builder.append(File.separatorChar); } } return builder.toString(); }
void withFetcher() { .fetcher fetcher; .fetcher.setRoleName(); }
void setProgressMonitor() { ProgressMonitor pm; }
void reset() { if () { ptr = 0; if (!eof()) { parseEntry(); } } }
E previous() { if (iterator.previousIndex() >= start) { return iterator.previous(); } throw new java.util.NoSuchElementException(); }
String getNewPrefix() { return newPrefix; }
int indexOfValue(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1; }
List<CharsRef> uniqueStems(char[] word, int length) { List<CharsRef> stems = stem(word, length); if (stems.size() < 2) { return stems; } CharArraySet terms = new CharArraySet(8, dictionary.getIgnoreCase()); List<CharsRef> deduped = new ArrayList<CharsRef>(); for (CharsRef s : stems) { if (!terms.contains(s)) { deduped.add(s); terms.add(s); } } return deduped; }
GetGatewayResponsesResponse getGatewayResponses() { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetGatewayResponsesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetGatewayResponsesResponseUnmarshaller.getInstance()); return invoke(GetGatewayResponsesResponse.class, options); }
void setPosition(long position) { currentBlockIndex = (int)(position >> outerInstance.blockBits); currentBlock = outerInstance.blocks[currentBlockIndex]; currentBlockUpto = (int)(position & outerInstance.blockMask); }
long skip(long n) { int s = (int) (available(), Math.max(, )); ptr s; return s; }
BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { this._bootstrapActionConfig = bootstrapActionConfig; }
void serialize(LittleEndianOutput out1) { out1.writeShort(); out1.writeShort(); out1.writeShort(); out1.writeShort(); (field_6_author.length()); out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00); if (field_5_hasMultibyte) { StringUtil.putUnicodeLE(, ); } else { StringUtil.putCompressedUnicode(, ); } if (field_7_padding != null) { out1.writeByte(Integer.parseInt(, Locale.ROOT)); } }
int lastIndexOf() { return lastIndexOf(, ); }
boolean add() { return addLastImpl(); }
void unsetSection(String section, String subsection) { ConfigSnapshot; ConfigSnapshot; do { src state.get(); res unsetSection(,,); } while (!state.compareAndSet(,)); }
String getTagName() { return tagName; }
void addSubRecord(int index, ) { subrecords.add(, ); }
boolean remove() { synchronized(mutex) { return c.remove(); } }
TokenStream create() { return new DoubleMetaphoneFilter(, ,); }
long getLength() { return inCoreLength(); }
void setValue() { value newValue; }
Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
int get(int i) { if () { throw new IndexOutOfBoundsException(); } return entries[]; }
CreateRepoRequest ( ) : ( " " , " " , " " , " " , " " ) { UriPattern ; Method MethodType . PUT ; }
boolean isDeltaBaseAsOffset() { }
void remove() { if (expectedModCount == list.modCount) { if (lastLink != null) { java.util.LinkedList.Link<ET> next_1 = lastLink.next; java.util.LinkedList.Link<ET> previous_1 = lastLink.previous; next_1.previous = previous_1; previous_1.next = next_1; if (lastLink == link) { pos--; } link = previous_1; lastLink = null; expectedModCount++; list._size--; list.modCount++; } else { throw new IllegalStateException(); } } else { throw new java.util.ConcurrentModificationException(); } }
MergeShardsResponse mergeShards(MergeShardsRequest request) { options = new InvokeOptions(); options.requestMarshaller = MergeShardsRequestMarshaller.getInstance(); options.responseUnmarshaller = MergeShardsResponseUnmarshaller.getInstance(); return invoke(request, options); }
AllocateHostedConnectionResponse AllocateHostedConnection(AllocateHostedConnectionRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller; options.ResponseUnmarshaller = AllocateHostedConnectionResponseUnmarshaller.Instance; return Invoke<AllocateHostedConnectionResponse>(, ); }
int getBeginIndex() { return start; }
WeightedTerm GetTerms ( Query query ) { return GetTerms ( , ) ; }
java.nio.ByteBuffer compact() { throw new java.nio.ReadOnlyBufferException(); }
void decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (int) ((byte0 & 0xFF) >>> 2); int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte0 & 3) << 4) | ((int) ((byte1 & 0xFF) >>> 4)); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte1 & 15) << 2) | ((int) ((byte2 & 0xFF) >>> 6)); values[valuesOffset++] = byte2 & 63; } }
String getHumanishName() { if (String.valueOf("").equals(getPath()) || getPath() == null) { throw new IllegalArgumentException(); } String s = getPath(); String[]; if (" ".equals() || LOCAL_FILE.matcher().matches()) { elements(" \\" + File.separatorChar + " "); } else { elements = s.split(" "); } if (elements.length == 0) { throw new IllegalArgumentException(); } String result = elements[elements.length - 1]; if (Constants.DOT_GIT.equals()) { result = elements[elements.length - 2]; } else { if (result.endsWith(Constants.DOT_GIT_EXT)) { result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); } } return result; }
DescribeNotebookInstanceLifecycleConfigResult describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance()); return invoke(request, options); }
String getAccessKeySecret() { return accessSecret; }
CreateVpnConnectionResponse createVpnConnection(CreateVpnConnectionRequest request) { options = new InvokeOptions(); options.requestMarshaller = CreateVpnConnectionRequestMarshaller.getInstance(); options.responseUnmarshaller = CreateVpnConnectionResponseUnmarshaller.getInstance(); return invoke(CreateVpnConnectionResponse.class, request, options); }
DescribeVoicesResponse DescribeVoices(DescribeVoicesRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = DescribeVoicesRequestMarshaller.Instance; options.ResponseUnmarshaller = DescribeVoicesResponseUnmarshaller.Instance; return Invoke<DescribeVoicesResponse>(request, options); }
ListMonitoringExecutionsResponse listMonitoringExecutions(ListMonitoringExecutionsRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller; options.responseUnmarshaller = ListMonitoringExecutionsResponseUnmarshaller.getInstance(); return invoke(ListMonitoringExecutionsResponse.class, options); }
public DescribeJobRequest(String vaultName, String jobId) { this.vaultName = vaultName; this.jobId = jobId; }
EscherRecord GetEscherRecord() { return escherRecords[]; }
public GetApisResult getApis(GetApisRequest request) { request = beforeClientExecution(request); return executeGetApis(request); }
DeleteSmsChannelResponse deleteSmsChannel(DeleteSmsChannelRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller; options.responseUnmarshaller = DeleteSmsChannelResponseUnmarshaller.getInstance(); return invoke(DeleteSmsChannelResponse.class, options); }
TrackingRefUpdate getTrackingRefUpdate() { }
void print(boolean b) { print(); }
IQueryNode getChild() { return getChildren(); }
NotIgnoredFilter(int workdirTreeIndex) { this.index = workdirTreeIndex; }
AreaRecord(RecordInputStream in1) { field_1_formatFlags = in1.readShort(); }
GetThumbnailRequest() : ("", "", "", "", "") { Protocol; }
public DescribeTransitGatewayVpcAttachmentsResponse describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { InvokeOptions options = new InvokeOptions(); DescribeTransitGatewayVpcAttachmentsRequestMarshaller.getInstance(); options.setResponseUnmarshaller(DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance()); return invoke(request, options); }
PutVoiceConnectorStreamingConfigurationResponse putVoiceConnectorStreamingConfiguration() { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance()); options.setResponseUnmarshaller(PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance()); return invoke(options); }
OrdRange getOrdRange(String dim) { OrdRange result; return prefixToOrdRange.get(dim); }
String toString() { String symbol = ""; if (startIndex >= 0 && startIndex < ((CharStream) getInputStream()).size()) { symbol = ((CharStream) getInputStream()).getText(Interval.of(startIndex, startIndex)); symbol = Utils.escapeWhitespace(symbol, false); } return String.format(Locale.getDefault(), "%s('%s')", LexerNoViableAltException.class.getSimpleName(), symbol); }
E peek() { return peekFirstImpl(); }
CreateWorkspacesResponse createWorkspaces(CreateWorkspacesRequest request) { options = new InvokeOptions(); CreateWorkspacesRequestMarshaller.getInstance(); options.responseUnmarshaller = CreateWorkspacesResponseUnmarshaller.getInstance(); return invoke(CreateWorkspacesResponse.class); }
Object clone() { NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = field_1_formatIndex; return rec; }
DescribeRepositoriesResponse describeRepositories(DescribeRepositoriesRequest request) { options = new InvokeOptions(); options.requestMarshaller; options.responseUnmarshaller = DescribeRepositoriesResponseUnmarshaller.instance; return invoke<DescribeRepositoriesResponse>(,); }
SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
TokenStream create() { return new HyphenatedWordsFilter(); }
CreateDistributionWithTagsResponse createDistributionWithTags(CreateDistributionWithTagsRequest request) { options = new InvokeOptions(); options.requestMarshaller = CreateDistributionWithTagsRequestMarshaller.instance; options.responseUnmarshaller; return invoke(CreateDistributionWithTagsResponse.class, , ); }
RandomAccessFile(String fileName, String mode) throws FileNotFoundException { throw new UnsupportedOperationException(); }
DeleteWorkspaceImageResponse deleteWorkspaceImage(DeleteWorkspaceImageRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.getInstance()); return invoke(DeleteWorkspaceImageResponse.class, request, options); }
String toHex() { return toHex((long) value); }
UpdateDistributionResponse updateDistribution(UpdateDistributionRequest request) { options = new InvokeOptions(); UpdateDistributionRequestMarshaller.getInstance(); options.responseUnmarshaller = UpdateDistributionResponseUnmarshaller.getInstance(); return invoke(UpdateDistributionResponse.class, options); }
HSSFColor getColor(short index) { if (index == .index) return HSSFColor.Automatic.getInstance(); else { byte[] b = palette.getColor(); if (b != null) { return new CustomColor(, ); } } return null; }
ValueEval evaluate(ValueEval operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(); }
void serialize(LittleEndianOutput out) { out.writeShort((short) field1NumberCrnRecords); out.writeShort((short) field2SheetTableIndex); }
public DescribeDBEngineVersionsResponse describeDBEngineVersions() { return describeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
FormatRun(short character, short fontIndex) { this._character = character; this._fontIndex = fontIndex; }
byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
UploadArchiveResponse uploadArchive(UploadArchiveRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(UploadArchiveRequestMarshaller.getInstance()); options.setResponseUnmarshaller(UploadArchiveResponseUnmarshaller.getInstance()); return invoke(request, options); }
List getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex, -1); }
public boolean equals(Object obj) { if (this == obj) { return true; } if (!super.equals(obj)) { return false; } if (this.getClass() != obj.getClass()) { return false; } AutomatonQuery other = (AutomatonQuery) obj; if (!m_compiled.equals(other.m_compiled)) { return false; } if (m_term == null) { if (other.m_term != null) { return false; } } else if (!m_term.equals(other.m_term)) { return false; } return true; }
SpanQuery MakeSpanClause() { List<SpanQuery> spanQueries = new ArrayList<SpanQuery>(); for (wsq : weightBySpanQuery) { wsq.getKey().setBoost(); spanQueries.add(wsq.getKey()); } if (spanQueries.size() == 1) return spanQueries.get(0); else return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); }
StashCreateCommand getStashCreate() { return new StashCreateCommand(); }
FieldInfo fieldInfo(String fieldName) { FieldInfo ret; byName.get(fieldName); return ret; }
public DescribeEventSourceResult describeEventSource(DescribeEventSourceRequest request) { request = beforeClientExecution(request); return executeDescribeEventSource(request); }
GetDocumentAnalysisResponse getDocumentAnalysis(GetDocumentAnalysisRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = GetDocumentAnalysisRequestMarshaller.getInstance(); options.responseUnmarshaller = GetDocumentAnalysisResponseUnmarshaller.getInstance(); return invoke(GetDocumentAnalysisResponse.class, request, options); }
CancelUpdateStackResponse cancelUpdateStack(CancelUpdateStackRequest request) {options = new InvokeOptions();CancelUpdateStackRequestMarshaller.instance;options.responseUnmarshaller = CancelUpdateStackResponseUnmarshaller.instance;return invoke(request, options);}
ModifyLoadBalancerAttributesResponse modifyLoadBalancerAttributes() { options = new InvokeOptions(); options.requestMarshaller = ModifyLoadBalancerAttributesRequestMarshaller.instance; options.responseUnmarshaller = ModifyLoadBalancerAttributesResponseUnmarshaller.instance; return invoke(ModifyLoadBalancerAttributesResponse.class); }
public SetInstanceProtectionResult setInstanceProtection(SetInstanceProtectionRequest request) { request = beforeClientExecution(request); return executeSetInstanceProtection(request); }
ModifyDBProxyResponse modifyDBProxy(ModifyDBProxyRequest request) { options = new InvokeOptions(); options.RequestMarshaller = ModifyDBProxyRequestMarshaller.Instance; options.ResponseUnmarshaller = ModifyDBProxyResponseUnmarshaller.Instance; return invoke(request, options); }
void add(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.length) { CharsRef[] next = new CharsRef[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_OBJECT_REF)]; System.arraycopy(outputs, 0, next, 0, count); outputs = next; } if (count == endOffsets.length) { int[] next = new int[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.length) { int[] next = new int[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRef(); } outputs[count].copyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
FetchLibrariesRequest() : ("", "", "", "", "") { Protocol; }
boolean exists() { return (); }
FilterOutputStream(OutputStream out) { this.out = out; }
public ScaleClusterRequest() {super("", "", "", "");setUriPattern("");setMethod();}
IDataValidationConstraint createTimeConstraint(int operatorType, String formula1, String formula2) { return DVConstraint.createTimeConstraint(operatorType, formula1, formula2); }
ListObjectParentPathsResponse listObjectParentPaths() { options = new InvokeOptions(); options.requestMarshaller = ListObjectParentPathsRequestMarshaller.getInstance(); options.responseUnmarshaller = ListObjectParentPathsResponseUnmarshaller.getInstance(); return invoke(ListObjectParentPathsResponse.class); }
DescribeCacheSubnetGroupsResponse describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = DescribeCacheSubnetGroupsRequestMarshaller.getInstance(); options.responseUnmarshaller = DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance(); return invoke(options); }
void setSharedFormula(boolean flag) { field_5_options(, ); }
boolean isReuseObjects() { }
IErrorNode AddErrorNode ( IToken badToken ) { ErrorNodeImpl t = new ErrorNodeImpl ( ) ; AddChild ( ) ; t . Parent ; }
LatvianStemFilterFactory(Map<String, String> args) { super(args); if (args.size() > 0) { throw new IllegalArgumentException("" + args); } }
RemoveSourceIdentifierFromSubscriptionResult removeSourceIdentifierFromSubscription() { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(RemoveSourceIdentifierFromSubscriptionRequestMarshaller.getInstance()); options.setResponseUnmarshaller(RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.getInstance()); return invoke(RemoveSourceIdentifierFromSubscriptionResult.class, options); }
TokenFilterFactory ForName ( , Map < String , String > args ) { return loader . newInstance ( , ) ; }
AddAlbumPhotosRequest() : ("", "", "", "", "") { Protocol; }
GetThreatIntelSetResponse getThreatIntelSet() { options = new InvokeOptions(); options.RequestMarshaller = GetThreatIntelSetRequestMarshaller.Instance; options.ResponseUnmarshaller = GetThreatIntelSetResponseUnmarshaller.Instance; return invoke<GetThreatIntelSetResponse>(,); }
TreeFilter clone() { return new AndTreeFilter.Binary(a.clone(), b.clone()); }
boolean equals(Object o) { return o instanceof ; }
boolean hasArray() { return protectedHasArray; }
UpdateContributorInsightsResponse updateContributorInsights(UpdateContributorInsightsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(UpdateContributorInsightsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(UpdateContributorInsightsResponseUnmarshaller.getInstance()); return invoke(UpdateContributorInsightsResponse.class, request, options); }
void unwriteProtectWorkbook() { records.remove(); fileShare = null; writeProtect = null; }
SolrSynonymParser(boolean dedup, Analyzer analyzer) { super(dedup, analyzer); this.expand = expand; }
RequestSpotInstancesResponse requestSpotInstances(RequestSpotInstancesRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = RequestSpotInstancesRequestMarshaller.getInstance(); options.responseUnmarshaller = RequestSpotInstancesResponseUnmarshaller.getInstance(); return invoke(RequestSpotInstancesResponse.class, request, options); }
byte getObjectData() { return findObjectRecord().objectData; }
public GetContactAttributesResult getContactAttributes(GetContactAttributesRequest request) {request = beforeClientExecution(request);return executeGetContactAttributes(request);}
public String toString() { return getKey() + "=" + getValue(); }
ListTextTranslationJobsResponse ListTextTranslationJobs() { options = new InvokeOptions(); options.RequestMarshaller = ListTextTranslationJobsRequestMarshaller.Instance; options.ResponseUnmarshaller = ListTextTranslationJobsResponseUnmarshaller.Instance; return Invoke<ListTextTranslationJobsResponse>(,); }
GetContactMethodsResponse getContactMethods(GetContactMethodsRequest request) { options = new InvokeOptions(); GetContactMethodsRequestMarshaller.getInstance(); options.responseUnmarshaller = GetContactMethodsResponseUnmarshaller.getInstance(); return invoke(GetContactMethodsResponse.class); }
short LookupIndexByName ( String name ) { FunctionMetadata fd = GetInstance ( ) . GetFunctionByNameInternal ( ) ; if ( fd == null ) { return - 1 ; } return ( short ) ; }
DescribeAnomalyDetectorsResponse describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { options = new InvokeOptions(); options.requestMarshaller = DescribeAnomalyDetectorsRequestMarshaller.instance; options.responseUnmarshaller = DescribeAnomalyDetectorsResponseUnmarshaller.instance; return invoke(request, options); }
String insertId(ObjectId changeId) { return insertId(); }
long getObjectSize(AnyObjectId objectId, int typeHint) { long sz = db.getObjectSize(objectId, typeHint); if (sz < 0) { if (typeHint == OBJ_ANY) { throw new MissingObjectException(objectId.copy(), ""); } throw new MissingObjectException(objectId.copy(), typeHint); } return sz; }
public ImportInstallationMediaResult importInstallationMedia(ImportInstallationMediaRequest request) { request = beforeClientExecution(request); return executeImportInstallationMedia(request); }
PutLifecycleEventHookExecutionStatusResponse putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance()); options.setResponseUnmarshaller(PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance()); return invoke(request, options); }
NumberPtg(LittleEndianInput in1) { field_1_value(); }
GetFieldLevelEncryptionConfigResponse getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance()); return invoke(request, options); }
DescribeDetectorResponse describeDetector(DescribeDetectorRequest request) { options = new InvokeOptions(); options.RequestMarshaller = DescribeDetectorRequestMarshaller.Instance; DescribeDetectorResponseUnmarshaller.Instance; return invoke(DescribeDetectorResponse.class, , ); }
ReportInstanceStatusResponse reportInstanceStatus(ReportInstanceStatusRequest request) {options = new InvokeOptions();options.requestMarshaller = ReportInstanceStatusRequestMarshaller.instance;options.responseUnmarshaller = ReportInstanceStatusResponseUnmarshaller.instance;return invoke(request, options);}
DeleteAlarmResponse deleteAlarm(DeleteAlarmRequest request) { options = new InvokeOptions(); options.requestMarshaller = DeleteAlarmRequestMarshaller.getInstance(); options.responseUnmarshaller = DeleteAlarmResponseUnmarshaller.getInstance(); return invoke(DeleteAlarmResponse.class, options); }
TokenStream create() { return new PortugueseStemFilter(); }
FtCblsSubRecord() { reserved = new byte[0]; }
boolean remove() { synchronized(mutex) { return c.remove(); } }
GetDedicatedIpResponse getDedicatedIp(GetDedicatedIpRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = GetDedicatedIpRequestMarshaller.getInstance(); options.responseUnmarshaller = GetDedicatedIpResponseUnmarshaller.getInstance(); return invoke(request, options); }
public String toString() { return precedence + " "; }
public ListStreamProcessorsResult listStreamProcessors() { return executeListStreamProcessors(new ListStreamProcessorsRequest()); }
public DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) { this.loadBalancerName = loadBalancerName; this.policyName = policyName; }
WindowProtectRecord(int options) { this._options = options; }
UnbufferedCharStream(int bufferSize) { n = 0; data = new int[bufferSize]; }
public GetOperationsResult getOperations(GetOperationsRequest request) { request = beforeClientExecution(request); return executeGetOperations(request); }
void copyRawTo(byte[] b) { NB.encodeInt32(, , ); NB.encodeInt32(, o + 4, ); NB.encodeInt32(, o + 8, ); NB.encodeInt32(, o + 12, ); NB.encodeInt32(, o + 16, ); }
WindowOneRecord(RecordInputStream in1) { field_1_h_hold = in1.readShort(); field_2_v_hold = in1.readShort(); field_3_width = in1.readShort(); field_4_height = in1.readShort(); field_5_options = in1.readShort(); field_6_active_sheet = in1.readShort(); field_7_first_visible_tab = in1.readShort(); field_8_num_selected_tabs = in1.readShort(); field_9_tab_width_ratio = in1.readShort(); }
StopWorkspacesResponse stopWorkspaces(StopWorkspacesRequest request) { options = new InvokeOptions(); options.requestMarshaller = StopWorkspacesRequestMarshaller.getInstance(); StopWorkspacesResponseUnmarshaller.getInstance(); return invoke(StopWorkspacesResponse.class); }
void close() throws IOException { if (isOpen) { isOpen = false; try { dump(); } finally { try { channel.truncate(channel.position()); } finally { try { channel.close(); } finally { super.close(); } } } } }
public DescribeMatchmakingRuleSetsResult describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { request = beforeClientExecution(request); return executeDescribeMatchmakingRuleSets(request); }
String getPronunciation(int wordId, char surface, int off, int len) { return null; }
String getPath() { return pathStr; }
double devsq(double[] v) { double r = Double.NaN; if (v != null && v.length >= 1) { double m = 0; double s = 0; int n = v.length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } return r; }
DescribeResizeResponse DescribeResize(DescribeResizeRequest request) { options = new InvokeOptions(); options.RequestMarshaller = DescribeResizeRequestMarshaller.Instance; options.ResponseUnmarshaller = DescribeResizeResponseUnmarshaller.Instance; return Invoke<DescribeResizeResponse>(request, options); }
boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
int end() { return end(); }
void traverse(ICellHandler handler) { int firstRow = range.getFirstRow(); int lastRow = range.getLastRow(); int firstColumn = range.getFirstColumn(); int lastColumn = range.getLastColumn(); int width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); IRow currentRow = null; ICell currentCell = null; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ++ctx.rowNumber) { currentRow = sheet.getRow(ctx.rowNumber); if (currentRow == null) { continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ++ctx.colNumber) { currentCell = currentRow.getCell(ctx.colNumber); if (currentCell == null) { continue; } if (isEmpty() && !traverseEmptyCells) { continue; } int cellIndex = (ctx.rowNumber - firstRow) * width + (ctx.colNumber - firstColumn + 1); handler.onCell(currentCell, ctx); } } }
int getReadIndex() { return _ReadIndex; }
int compareTo(ScoreTerm other) { if (term.bytesEquals(other.term)) { return 0; } if (boost == other.boost) { return term.compareTo(other.term); } else { return boost.compareTo(other.boost); } }
int normalize(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = StemmerUtil.delete(s, i, len); i--; break; default: break; } } return len; }
void serialize(LittleEndianOutput out1) { }
DiagnosticErrorListener(boolean exactOnly) { setExactOnly(exactOnly); }
KeySchemaElement(String attributeName, KeyType keyType) { _attributeName = attributeName; _keyType = keyType; }
GetAssignmentResponse getAssignment(GetAssignmentRequest request) { options = new InvokeOptions(); options.RequestMarshaller = GetAssignmentRequestMarshaller.Instance; GetAssignmentResponseUnmarshaller.Instance; return invoke(GetAssignmentResponse.class, , ); }
boolean hasObject() { return findOffset() != -1; }
GroupingSearch setAllGroups() { this.allGroups = allGroups; return this; }
void setMultiValued(String dimName, boolean v) { synchronized(this) { DimConfig fieldType = fieldTypes.get(dimName); if (fieldType == null) { fieldTypes.put(dimName, new DimConfig(v)); } else { fieldType.setMultiValued(v); } } }
int getCellsVal() { int size = 0; for (char c : cells.keySet()) { Cell e = at(); if (e.cmd >= 0) { ; } } return size; }
DeleteVoiceConnectorResponse DeleteVoiceConnector ( DeleteVoiceConnectorRequest request ) { options = new InvokeOptions ( ) ; options . RequestMarshaller ; options . ResponseUnmarshaller DeleteVoiceConnectorResponseUnmarshaller . Instance ; return Invoke < DeleteVoiceConnectorResponse > ( , ) ; }
public DeleteLifecyclePolicyResult deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { request = beforeClientExecution(request); return executeDeleteLifecyclePolicy(request); }
