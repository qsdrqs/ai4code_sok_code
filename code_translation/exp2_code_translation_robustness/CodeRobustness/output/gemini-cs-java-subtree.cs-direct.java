void Serialize(ILittleEndianOutput out1) {}
void addAll(org.eclipse.jgit.util.BlockList<T> src) { if (src.size == 0) {} int srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) { addAll(src.directory[srcDirIdx], 0, BLOCK_SIZE); } if (src.tailBlkIdx != 0) { addAll(src.tailBlock, 0, src.tailBlkIdx); } }
void writeByte(byte b) { if (outerInstance.upto == outerInstance.blockSize) { if (outerInstance.currentBlock != null) { outerInstance.blocks.add(outerInstance.currentBlock); outerInstance.blockEnd.add(outerInstance.upto); } outerInstance.currentBlock = new byte[outerInstance.blockSize]; outerInstance.upto = 0; } outerInstance.currentBlock[outerInstance.upto++] = (byte) b; }
public ObjectId getObjectId() { return objectId; }
public DeleteDomainEntryResponse deleteDomainEntry(DeleteDomainEntryRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteDomainEntryRequestMarshaller.Instance); options.setResponseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.Instance); return invoke(request, options); }
long ramBytesUsed() { return fst == null ? 0 : fst.getSizeInBytes(); }
String getFullMessage() { byte[] raw = buffer; int msgB = RawParseUtils.TagMessage(raw, 0); if (msgB < 0) { return ""; } java.nio.charset.Charset enc = RawParseUtils.ParseEncoding(); return RawParseUtils.Decode(raw, msgB, enc, raw.length); }
POIFSFileSystem() { HeaderBlock headerBlock = new HeaderBlock(); _property_table = new PropertyTable(); _documents = new ArrayList(); _root = null; }
void init(int address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; assert slice != null; upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; assert upto < slice.length; }
public SubmoduleAddCommand setPath(String path) { this.path = path; return this; }
public ListIngestionsResponse listIngestions(ListIngestionsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListIngestionsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ListIngestionsResponseUnmarshaller.getInstance()); return invoke(request, options); }
QueryParserTokenManager(ICharStream stream, int lexState) { this(); SwitchTo(); }
public GetShardIteratorResponse getShardIterator(GetShardIteratorRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetShardIteratorRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetShardIteratorResponseUnmarshaller.getInstance()); return invoke(request, options); }
public ModifyStrategyRequest() { super(" ", " ", " ", " ", " "); Method(); }
boolean ready() throws java.io.IOException { synchronized (lock) { if (in == null) { throw new java.io.IOException(" "); } try { return bytes.hasRemaining() || in.available() > 0; } catch (java.io.IOException e) { return false; } } }
EscherOptRecord getOptRecord() {}
public int read(byte[] buffer, int offset, int length) { synchronized (this) { if (buffer == null) { throw new NullPointerException(); } java.util.Objects.checkFromIndexSize(offset, length, buffer.length); if (length == 0) { return 0; } int copylen = Math.min(length, count - pos); if (copylen <= 0) { return 0; } System.arraycopy(this.buf, pos, buffer, offset, copylen); pos += copylen; return copylen; } }
OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) { this.sentenceOp = sentenceOp; }
void print(String str) { write(String.valueOf(str)); }
public NotImplementedFunctionException(String functionName, Throwable cause) { super(functionName, cause); this.functionName = functionName; }
V next() { return nextEntry.value; }
void readBytes(byte[] b, int offset, int len, boolean useBuffer) throws java.io.IOException { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) { System.arraycopy(buffer, bufferPosition, b, offset, len); } bufferPosition += len; } else { if (available > 0) { System.arraycopy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { refill(); if (bufferLength < len) { System.arraycopy(buffer, 0, b, offset, bufferLength); throw new java.io.EOFException(); } else { System.arraycopy(buffer, 0, b, offset, len); bufferPosition = len; } } else { long after = bufferStart + bufferPosition + len; if (after > length()) { throw new java.io.EOFException(""); } readInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
public TagQueueResponse tagQueue(TagQueueRequest request) { return invoke(request, TagQueueRequestMarshaller.getInstance(), TagQueueResponseUnmarshaller.getInstance()); }
void remove() { throw new UnsupportedOperationException(); }
public ModifyCacheSubnetGroupResponse modifyCacheSubnetGroup() { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ModifyCacheSubnetGroupRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ModifyCacheSubnetGroupResponseUnmarshaller.getInstance()); return invoke(options); }
void SetParams(String params) { java.util.StringTokenizer st = new java.util.StringTokenizer(params, " "); if (st.hasMoreTokens()) culture = st.nextToken(); if (st.hasMoreTokens()) culture += " " + st.nextToken(); if (st.hasMoreTokens()) ignore = st.nextToken(); }
public DeleteDocumentationVersionResponse deleteDocumentationVersion(DeleteDocumentationVersionRequest request) { return invoke(request, new InvokeOptions().withResponseUnmarshaller(DeleteDocumentationVersionResponseUnmarshaller.getInstance())); }
public boolean equals(Object obj) { if (!(obj instanceof FacetLabel)) return false; FacetLabel other = (FacetLabel) obj; if (Length != other.Length) return false; for (int i = Length - 1; i >= 0; i--) { if (!Components[i].equals(other.Components[i])) return false; } return true; }
public GetInstanceAccessDetailsResponse getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { InvokeOptions options = new InvokeOptions(); options.setResponseUnmarshaller(GetInstanceAccessDetailsResponseUnmarshaller.getInstance()); return this.<GetInstanceAccessDetailsResponse>invoke(request, options); }
HSSFPolygon createPolygon() { HSSFPolygon shape = new HSSFPolygon(null, null); shape.getParent(); HSSFAnchor anchor = shape.getAnchor(); shapes.add(shape); onCreate(); return shape; }
String getSheetName() { return getBoundSheetRec().getSheetname(); }
public GetDashboardResponse getDashboard(GetDashboardRequest request) { InvokeOptions options = new InvokeOptions(); options.setResponseUnmarshaller(GetDashboardResponseUnmarshaller.getInstance()); return invoke(request, options); }
public AssociateSigninDelegateGroupsWithAccountResponse associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(AssociateSigninDelegateGroupsWithAccountRequestMarshaller.getInstance()); options.setResponseUnmarshaller(AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.getInstance()); return invoke(request, options); }
void addMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < mbr.getNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.setColumn(j + mbr.getFirstColumn()); br.setRow(mbr.getRow()); br.setXF(mbr.getXFAt()); insertCell(br); } }
String quote(String string) { StringBuilder sb = new StringBuilder(); sb.append("\""); int apos = 0; int k; while ((k = string.indexOf("\"", apos)) != -1) { sb.append(string.substring(apos, k)).append("\\\""); apos = k + 1; } return sb.append(string.substring(apos)).append("\"").toString(); }
java.nio.ByteBuffer putInt(int value) { throw new java.nio.ReadOnlyBufferException(); }
ArrayPtg(Object[][] values2d) { int nColumns = values2d[0].length; int nRows = values2d.length; _nColumns = (short) nColumns; _nRows = (short) nRows; Object[] vv = new Object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { Object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[getValueIndex(c, r)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
public GetIceServerConfigResponse getIceServerConfig(GetIceServerConfigRequest request) { InvokeOptions options = new InvokeOptions(); options.setResponseUnmarshaller(GetIceServerConfigResponseUnmarshaller.getInstance()); return invoke(request, options); }
public String toString() { StringBuilder sb = new StringBuilder(); sb.append(this.getClass().getSimpleName()).append(" ").append(getValueAsString()).append(" "); return sb.toString(); }
public String toString() { return " " + _parentQuery + " "; }
void incRef() { refCount.incrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResponse updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(UpdateConfigurationSetSendingEnabledRequestMarshaller.getInstance()); options.setResponseUnmarshaller(UpdateConfigurationSetSendingEnabledResponseUnmarshaller.getInstance()); return invoke(request, options); }
int getNextXBATChainOffset() { return getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE; }
void multiplyByPowerOfTen(int pow10) { TenPower tp = TenPower.getInstance(Math.abs(pow10)); if (pow10 < 0) { mulShift(tp._divisor, tp._divisorShift); } else { mulShift(tp._multiplicand, tp._multiplierShift); } }
public String toString() { StringBuilder builder = new StringBuilder(); int length = this.length; builder.append(java.io.File.separatorChar); for (int i = 0; i < length; i++) { builder.append(getComponent(i)); if (i < (length - 1)) { builder.append(java.io.File.separatorChar); } } return builder.toString(); }
void withFetcher() { fetcher fetcher; fetcher.setRoleName(); }
void setProgressMonitor() { ProgressMonitor pm; }
void Reset() { if () { ptr = 0; if (!Eof) { ParseEntry(); } } }
E previous() { if (iterator.previousIndex() >= start) { return iterator.previous(); } throw new java.util.NoSuchElementException(); }
public String getNewPrefix() { return newPrefix; }
int indexOfValue(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1; }
List<CharsRef> UniqueStems(char[] word, int length) { List<CharsRef> stems = stem(word, length); if (stems.size() < 2) { return stems; } CharArraySet terms = new CharArraySet(8, false); List<CharsRef> deduped = new ArrayList<>(); for (CharsRef s : stems) { if (!terms.contains(s)) { deduped.add(s); terms.add(s); } } return deduped; }
GetGatewayResponsesResponse getGatewayResponses() { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetGatewayResponsesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetGatewayResponsesResponseUnmarshaller.getInstance()); return invoke(new GetGatewayResponsesRequest(), options); }
void SetPosition(long position) { currentBlockIndex = (int)(position >> outerInstance.blockBits); currentBlock = outerInstance.blocks[currentBlockIndex]; currentBlockUpto = (int)(position & ((1 << outerInstance.blockBits) - 1)); }
long skip(long n) { int s = (int) Math.max(n, available()); return s; }
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { this._bootstrapActionConfig = bootstrapActionConfig; }
void serialize(LittleEndianOutput out1) { out1.writeShort(); out1.writeShort(); out1.writeShort(); out1.writeShort(); (field_6_author.length()); out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00); if (field_5_hasMultibyte) { StringUtil.putUnicodeLE(field_6_author, out1); } else { StringUtil.putCompressedUnicode(field_6_author, out1); } if (field_7_padding != null) { out1.writeByte(Integer.parseInt(field_7_padding.toString())); } }
int lastIndexOf() { return lastIndexOf( , ); }
boolean add() { return addLastImpl(); }
void unsetSection(String section, String subsection) { ConfigSnapshot src, res; do { src = state.get(); res = src.unsetSection(section, subsection); } while (!state.compareAndSet(src, res)); }
String getTagName() { return tagName; }
void AddSubRecord(int index, Object subrecord) { subrecords.add(index, subrecord); }
boolean remove() { synchronized (mutex) { return c.remove(); } }
TokenStream create() { return new DoubleMetaphoneFilter( , , ); }
public long getLength() { return InCoreLength(); }
void setValue() { value newValue; }
record Pair(ContentSource oldSource, ContentSource newSource) {}
int get(int i) { if (i < 0 || i >= entries.length) { throw new IndexOutOfBoundsException(); } return entries[i]; }
public CreateRepoRequest() { super("", "", "", "", ""); uriPattern; this.method = MethodType.PUT; }
boolean isDeltaBaseAsOffset() {}
void remove() { if (expectedModCount == list.modCount) { if (lastLink != null) { java.util.LinkedList.Link<ET> next_1 = lastLink.next; java.util.LinkedList.Link<ET> previous_1 = lastLink.previous; next_1.previous = previous_1; previous_1.next = next_1; if (lastLink == link) { pos--; } link = previous_1; lastLink = null; expectedModCount++; list._size--; list.modCount++; } else { throw new IllegalStateException(); } } else { throw new java.util.ConcurrentModificationException(); } }
public MergeShardsResponse mergeShards(MergeShardsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(MergeShardsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(MergeShardsResponseUnmarshaller.getInstance()); return invoke(request, options); }
public AllocateHostedConnectionResponse allocateHostedConnection(AllocateHostedConnectionRequest request) { InvokeOptions options = new InvokeOptions(); options.setResponseUnmarshaller(AllocateHostedConnectionResponseUnmarshaller.getInstance()); return invoke(request, options); }
int getBeginIndex() { return start; }
WeightedTerm getTerms(Query query) { return getTerms(,); }
public java.nio.ByteBuffer compact() { throw new java.nio.ReadOnlyBufferException(); }
void Decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations){for(int i=0;i<iterations;++i){int byte0=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=byte0>>>2;int byte1=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=((byte0&3)<<4)|(byte1>>>4);int byte2=blocks[blocksOffset++]&0xFF;values[valuesOffset++]=((byte1&15)<<2)|(byte2>>>6);values[valuesOffset++]=byte2&63;}}
public String getHumanishName() { String s = getPath(); if (s == null || s.isEmpty()) { throw new IllegalArgumentException(); } String[] elements = s.split("[\\\\/]"); if (elements.length == 0) { throw new IllegalArgumentException(); } String result = elements[elements.length - 1]; if (Constants.DOT_GIT.equals(result)) { result = elements[elements.length - 2]; } else if (result.endsWith(Constants.DOT_GIT_EXT)) { result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); } return result; }
public DescribeNotebookInstanceLifecycleConfigResponse describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance()); return invoke(request, options); }
public String getAccessKeySecret() { return AccessSecret; }
CreateVpnConnectionResponse createVpnConnection(CreateVpnConnectionRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(CreateVpnConnectionRequestMarshaller.getInstance()); options.setResponseUnmarshaller(CreateVpnConnectionResponseUnmarshaller.getInstance()); return invoke(request, options); }
public DescribeVoicesResponse describeVoices(DescribeVoicesRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeVoicesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeVoicesResponseUnmarshaller.getInstance()); return invoke(request, options, DescribeVoicesResponse.class); }
public ListMonitoringExecutionsResponse listMonitoringExecutions(ListMonitoringExecutionsRequest request) { InvokeOptions options = new InvokeOptions(); options.setResponseUnmarshaller(ListMonitoringExecutionsResponseUnmarshaller.getInstance()); return invoke(request, options); }
public DescribeJobRequest(String vaultName, String jobId) { this.vaultName = vaultName; this.jobId = jobId; }
EscherRecord getEscherRecord() { return escherRecords[0]; }
public GetApisResponse getApis(GetApisRequest request) { InvokeOptions options = new InvokeOptions(); options.setResponseUnmarshaller(GetApisResponseUnmarshaller.getInstance()); return invoke(request, options); }
public DeleteSmsChannelResponse deleteSmsChannel(DeleteSmsChannelRequest request) { InvokeOptions options = new InvokeOptions(); options.setResponseUnmarshaller(DeleteSmsChannelResponseUnmarshaller.getInstance()); return invoke(request, options); }
TrackingRefUpdate getTrackingRefUpdate() {}
void print(boolean b) { print(); }
IQueryNode getChild() { return getChildren().get(0); }
NotIgnoredFilter(int workdirTreeIndex) { this.index = workdirTreeIndex; }
public AreaRecord(RecordInputStream in1) { this.field_1_formatFlags = in1.readShort(); }
public GetThumbnailRequest() { this(" ", " ", " ", " ", " "); Protocol; }
public DescribeTransitGatewayVpcAttachmentsResponse describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { return invoke(request, new DescribeTransitGatewayVpcAttachmentsRequestMarshaller(), new DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller()); }
public PutVoiceConnectorStreamingConfigurationResponse putVoiceConnectorStreamingConfiguration() { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance()); options.setResponseUnmarshaller(PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance()); return invoke(options); }
OrdRange getOrdRange(String dim) { return prefixToOrdRange.get(dim); }
public String toString() { String symbol = ""; if (getStartIndex() >= 0 && getStartIndex() < getInputStream().size()) { symbol = getInputStream().getText(org.antlr.v4.runtime.misc.Interval.of(getStartIndex(), getStartIndex())); symbol = org.antlr.v4.runtime.misc.Utils.escapeWhitespace(symbol, false); } return String.format(java.util.Locale.getDefault(), "%s: '%s'", org.antlr.v4.runtime.LexerNoViableAltException.class.getSimpleName(), symbol); }
public E peek() { return peekFirstImpl(); }
public CreateWorkspacesResponse createWorkspaces(CreateWorkspacesRequest request) { InvokeOptions options = new InvokeOptions(); options.setResponseUnmarshaller(CreateWorkspacesResponseUnmarshaller.getInstance()); return invoke(request, options); }
public Object Clone() { NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = this.field_1_formatIndex; return rec; }
public DescribeRepositoriesResponse describeRepositories(DescribeRepositoriesRequest request) { InvokeOptions options = new InvokeOptions(); options.setResponseUnmarshaller(DescribeRepositoriesResponseUnmarshaller.getInstance()); return this.<DescribeRepositoriesResponse>invoke(request, options); }
SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
TokenStream create() { return new HyphenatedWordsFilter(); }
public CreateDistributionWithTagsResponse createDistributionWithTags(CreateDistributionWithTagsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(CreateDistributionWithTagsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(CreateDistributionWithTagsResponseUnmarshaller.getInstance()); return invoke(request, options); }
public RandomAccessFile(String fileName, String mode) { throw new UnsupportedOperationException(); }
public DeleteWorkspaceImageResponse deleteWorkspaceImage(DeleteWorkspaceImageRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.getInstance()); return invoke(request, options); }
String toHex() { return toHex((long) value); }
public UpdateDistributionResponse updateDistribution(UpdateDistributionRequest request) { return client.updateDistribution(request); }
HSSFColor getColor(short index) { if (index == HSSFColor.Automatic.INDEX) return HSSFColor.Automatic.getInstance(); else { byte[] b = palette.getColor(index); if (b != null) { return new HSSFColor.Custom(index, b); } } return null; }
ValueEval evaluate(ValueEval operands, int srcRow, int srcCol) { throw new UnsupportedOperationException(); }
void Serialize(ILittleEndianOutput out1) { out1.writeShort((short) field_1_number_crn_records); out1.writeShort((short) field_2_sheet_table_index); }
public DescribeDBEngineVersionsResponse describeDBEngineVersions() { return describeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
FormatRun(short character, short fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; for (int i = 0; i < length; i++) { char ch = chars[offset + i]; result[i * 2] = (byte) (ch >> 8); result[i * 2 + 1] = (byte) ch; } return result; }
public UploadArchiveResponse uploadArchive(UploadArchiveRequest request) { return this.<UploadArchiveResponse>invoke(request, UploadArchiveRequestMarshaller.getInstance(), UploadArchiveResponseUnmarshaller.getInstance()); }
List GetHiddenTokensToLeft(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex, -1); }
public boolean equals(Object obj) { if (this == obj) return true; if (obj == null || getClass() != obj.getClass()) return false; AutomatonQuery other = (AutomatonQuery) obj; if (!m_compiled.equals(other.m_compiled)) return false; if (m_term == null) { if (other.m_term != null) return false; } else if (!m_term.equals(other.m_term)) return false; return true; }
SpanQuery makeSpanClause() { List<SpanQuery> spanQueries = new ArrayList<>(); for (Map.Entry<SpanQuery, ?> wsq : weightBySpanQuery.entrySet()) { wsq.getKey().getBoost(); spanQueries.add(wsq.getKey()); } if (spanQueries.size() == 1) return spanQueries.get(0); else return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); }
StashCreateCommand getStashCreate() { return new StashCreateCommand(); }
java.lang.reflect.Field getFieldInfo(String fieldName) { return byName.get(fieldName); }
public DescribeEventSourceResponse describeEventSource(DescribeEventSourceRequest request) { InvokeOptions options = new InvokeOptions(); options.setResponseUnmarshaller(DescribeEventSourceResponseUnmarshaller.getInstance()); return invoke(request, options); }
public GetDocumentAnalysisResponse getDocumentAnalysis(GetDocumentAnalysisRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetDocumentAnalysisRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetDocumentAnalysisResponseUnmarshaller.getInstance()); return invoke(request, options); }
public CancelUpdateStackResponse cancelUpdateStack(CancelUpdateStackRequest request) { return invoke(request, new InvokeOptions().withResponseUnmarshaller(CancelUpdateStackResponseUnmarshaller.getInstance())); }
public ModifyLoadBalancerAttributesResponse modifyLoadBalancerAttributes() { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ModifyLoadBalancerAttributesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance()); return invoke(new ModifyLoadBalancerAttributesRequest(), options); }
public SetInstanceProtectionResponse setInstanceProtection(SetInstanceProtectionRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(SetInstanceProtectionRequestMarshaller.getInstance()); options.setResponseUnmarshaller(SetInstanceProtectionResponseUnmarshaller.getInstance()); return invoke(request, options); }
public ModifyDBProxyResponse modifyDBProxy(ModifyDBProxyRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ModifyDBProxyRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ModifyDBProxyResponseUnmarshaller.getInstance()); return invoke(request, options); }
void add(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.length) { CharsRef[] next = new CharsRef[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_OBJECT_REF)]; System.arraycopy(outputs, 0, next, 0, count); outputs = next; } if (count == endOffsets.length) { int[] next = new int[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_INT)]; System.arraycopy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.length) { int[] next = new int[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_INT)]; System.arraycopy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRef(); } outputs[count].copyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
FetchLibrariesRequest() { this(" ", " ", " ", " ", " "); getProtocol(); }
boolean exists() { return false; }
public FilterOutputStream(OutputStream out) { this.out = out; }
public ScaleClusterRequest() { super("", "", "", "", ""); this.uriPattern = ""; this.method = null; }
IDataValidationConstraint createTimeConstraint(int operatorType, String formula1, String formula2) { return DVConstraint.createTimeConstraint(operatorType, formula1, formula2); }
public ListObjectParentPathsResponse listObjectParentPaths() { return invoke(new ListObjectParentPathsRequest(), new InvokeOptions().withRequestMarshaller(ListObjectParentPathsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListObjectParentPathsResponseUnmarshaller.getInstance())); }
public DescribeCacheSubnetGroupsResponse describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeCacheSubnetGroupsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance()); return invoke(request, options, DescribeCacheSubnetGroupsResponse.class); }
void setSharedFormula(boolean flag) { field_5_options = sharedFormula.setShortBoolean(field_5_options, flag); }
boolean isReuseObjects() { }
IErrorNode addErrorNode(IToken badToken) { ErrorNodeImpl t = new ErrorNodeImpl(); addChild(); t.getParent(); return t; }
public LatvianStemFilterFactory(Map<String, String> args) { super(args); if (args.size() > 0) { throw new IllegalArgumentException(" " + args); } }
public RemoveSourceIdentifierFromSubscriptionResponse removeSourceIdentifierFromSubscription() { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(RemoveSourceIdentifierFromSubscriptionRequestMarshaller.getInstance()); options.setResponseUnmarshaller(RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.getInstance()); return invoke(new RemoveSourceIdentifierFromSubscriptionRequest(), options); }
TokenFilterFactory forName(String name, Map<String, String> args) { return loader.newInstance(name, args); }
public AddAlbumPhotosRequest() { super(" ", " ", " ", " ", " "); }
public GetThreatIntelSetResponse getThreatIntelSet() { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetThreatIntelSetRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetThreatIntelSetResponseUnmarshaller.getInstance()); return invoke(options); }
TreeFilter clone() { return new AndTreeFilter.Binary(null, b.clone()); }
boolean equals() { return o instanceof TypeName; }
boolean hasArray() { return protectedHasArray; }
public UpdateContributorInsightsResponse updateContributorInsights(UpdateContributorInsightsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(UpdateContributorInsightsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(UpdateContributorInsightsResponseUnmarshaller.getInstance()); return invoke(request, options); }
void unwriteProtectWorkbook() { records.remove(); fileShare = null; writeProtect = null; }
public SolrSynonymParser(boolean dedup, Analyzer analyzer, boolean expand) { super(dedup, analyzer); this.expand = expand; }
public RequestSpotInstancesResponse requestSpotInstances(RequestSpotInstancesRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(RequestSpotInstancesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(RequestSpotInstancesResponseUnmarshaller.getInstance()); return invoke(request, options); }
byte getObjectData() { return findObjectRecord().getObjectData(); }
public GetContactAttributesResponse getContactAttributes(GetContactAttributesRequest request) { InvokeOptions options = new InvokeOptions(); GetContactAttributesRequestMarshaller.getInstance(); options.setResponseUnmarshaller(GetContactAttributesResponseUnmarshaller.getInstance()); return this.<GetContactAttributesResponse>invoke(request, options); }
String toString() { return getKey() + +getValue(); }
public ListTextTranslationJobsResponse listTextTranslationJobs() { return invoke(new ListTextTranslationJobsRequest(), new InvokeOptions().withRequestMarshaller(ListTextTranslationJobsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListTextTranslationJobsResponseUnmarshaller.getInstance())); }
public GetContactMethodsResponse getContactMethods(GetContactMethodsRequest request) { InvokeOptions options = new InvokeOptions(); GetContactMethodsRequestMarshaller.getInstance(); options.setResponseUnmarshaller(GetContactMethodsResponseUnmarshaller.getInstance()); return invoke(); }
short lookupIndexByName(String name) { FunctionMetadata fd = getInstance().getFunctionByNameInternal(); if (fd == null) { return -1; } return fd.getIndex(); }
public DescribeAnomalyDetectorsResponse describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeAnomalyDetectorsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeAnomalyDetectorsResponseUnmarshaller.getInstance()); return invoke(request, options); }
String InsertId( , ObjectId changeId) { return InsertId( , , ); }
long getObjectSize(AnyObjectId objectId, int typeHint) { long sz = db.getObjectSize(objectId, typeHint); if (sz < 0) { if (typeHint == OBJ_ANY) { throw new MissingObjectException(objectId, " "); } throw new MissingObjectException(objectId.copy(), typeHint); } return sz; }
public ImportInstallationMediaResponse importInstallationMedia(ImportInstallationMediaRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ImportInstallationMediaRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ImportInstallationMediaResponseUnmarshaller.getInstance()); return invoke(request, options); }
public PutLifecycleEventHookExecutionStatusResponse putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance()); options.setResponseUnmarshaller(PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance()); return invoke(request, options); }
NumberPtg(ILittleEndianInput in1) { field_1_value(); }
public GetFieldLevelEncryptionConfigResponse getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance()); return invoke(request, options); }
public DescribeDetectorResponse describeDetector(DescribeDetectorRequest request) { return invoke(request, DescribeDetectorResponseUnmarshaller.getInstance()); }
public ReportInstanceStatusResponse reportInstanceStatus(ReportInstanceStatusRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ReportInstanceStatusRequestMarshaller.Instance); options.setResponseUnmarshaller(ReportInstanceStatusResponseUnmarshaller.Instance); return invoke(request, options); }
public DeleteAlarmResponse deleteAlarm(DeleteAlarmRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteAlarmRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteAlarmResponseUnmarshaller.getInstance()); return invoke(request, options); }
TokenStream create() { return new PortugueseStemFilter(); }
FtCblsSubRecord() { reserved = new byte[1]; }
boolean remove() { synchronized (mutex) { return c.remove(); } }
public GetDedicatedIpResponse getDedicatedIp(GetDedicatedIpRequest request) { InvokeOptions options = new InvokeOptions(); options.setResponseUnmarshaller(GetDedicatedIpResponseUnmarshaller.getInstance()); return this.<GetDedicatedIpResponse>invoke(request, options); }
public String toString() { return precedence + " "; }
public ListStreamProcessorsResponse listStreamProcessors() { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListStreamProcessorsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ListStreamProcessorsResponseUnmarshaller.getInstance()); return invoke(new ListStreamProcessorsRequest(), options); }
public DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) { this._loadBalancerName = loadBalancerName; this._policyName = policyName; }
WindowProtectRecord(int options) { this._options = options; }
UnbufferedCharStream(int bufferSize) { n = 0; data = new int[bufferSize]; }
public GetOperationsResponse getOperations(GetOperationsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetOperationsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetOperationsResponseUnmarshaller.getInstance()); return invoke(request, options); }
void copyRawTo(byte[] b, ) { NB.encodeInt32(, , ); NB.encodeInt32(, o + 4, ); NB.encodeInt32(, o + 8, ); NB.encodeInt32(, o + 12, ); NB.encodeInt32(, o + 16, ); }
WindowOneRecord(RecordInputStream in1) { field_1_h_hold = in1.readShort(); field_2_v_hold = in1.readShort(); field_3_width = in1.readShort(); field_4_height = in1.readShort(); field_5_options = in1.readShort(); field_6_active_sheet = in1.readShort(); field_7_first_visible_tab = in1.readShort(); field_8_num_selected_tabs = in1.readShort(); field_9_tab_width_ratio = in1.readShort(); }
public StopWorkspacesResponse stopWorkspaces(StopWorkspacesRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(StopWorkspacesRequestMarshaller.getInstance()).withResponseUnmarshaller(StopWorkspacesResponseUnmarshaller.getInstance())); }
void close() throws IOException { if (isOpen) { isOpen; try { dump(); } finally { try { channel.truncate(); } finally { try { channel.close(); } finally { ; } } } } }
public DescribeMatchmakingRuleSetsResponse describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeMatchmakingRuleSetsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeMatchmakingRuleSetsResponseUnmarshaller.getInstance()); return invoke(request, options); }
String GetPronunciation(int wordId, char surface, int off, int len) { return null; }
public String getPath() { return pathStr; }
double devsq(double[] v) { double r = Double.NaN; if (v != null && v.length >= 1) { double m = 0; double s = 0; int n = v.length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } return r; }
public DescribeResizeResponse describeResize(DescribeResizeRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeResizeRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeResizeResponseUnmarshaller.getInstance()); return invoke(request, options); }
boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
int getEnd() { return end(); }
void traverse(ICellHandler handler) { int firstRow = range.getFirstRow(); int lastRow = range.getLastRow(); int firstColumn = range.getFirstColumn(); int lastColumn = range.getLastColumn(); SimpleCellWalkContext ctx = new SimpleCellWalkContext(); IRow currentRow = null; ICell currentCell = null; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ++ctx.rowNumber) { currentRow = sheet.getRow(ctx.rowNumber); if (currentRow == null) { continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ++ctx.colNumber) { currentCell = currentRow.getCell(ctx.colNumber); if (currentCell == null) { continue; } if (currentCell.isEmpty() && !traverseEmptyCells) { continue; } handler.onCell(currentCell, ctx); } } }
public int getReadIndex() { return _ReadIndex; }
public int compareTo(ScoreTerm other) { if (term.equals(other.term)) { return 0; } if (boost == other.boost) { return term.compareTo(other.term); } else { return Float.compare(boost, other.boost); } }
int Normalize(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = StemmerUtil.delete(s, i, len); i--; break; default: break; } } return len; }
void serialize(ILittleEndianOutput out1) {}
DiagnosticErrorListener(boolean exactOnly) { this.exactOnly = exactOnly; }
public KeySchemaElement(String attributeName, KeyType keyType) { this._attributeName = attributeName; this._keyType = keyType; }
public GetAssignmentResponse getAssignment(GetAssignmentRequest request) { return invoke(request, GetAssignmentRequestMarshaller.getInstance(), GetAssignmentResponseUnmarshaller.getInstance()); }
boolean hasObject() { return findOffset() != -1; }
GroupingSearch setAllGroups() { return this; }
void setMultiValued(String dimName, boolean v) { synchronized(this) { fieldTypes.computeIfAbsent(dimName, k -> new DimConfig()).setIsMultiValued(v); } }
int getCellsVal() { int size = 0; for (Character c : cells.keySet()) { Cell e = at(); if (e.cmd >= 0) { ; } } return size; }
public DeleteVoiceConnectorResponse deleteVoiceConnector(DeleteVoiceConnectorRequest request) { InvokeOptions options = new InvokeOptions(); options.setResponseUnmarshaller(DeleteVoiceConnectorResponseUnmarshaller.getInstance()); return invoke(request, options); }
public DeleteLifecyclePolicyResponse deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteLifecyclePolicyRequestMarshaller.instance); options.setResponseUnmarshaller(DeleteLifecyclePolicyResponseUnmarshaller.instance); return invoke(request, options); }
