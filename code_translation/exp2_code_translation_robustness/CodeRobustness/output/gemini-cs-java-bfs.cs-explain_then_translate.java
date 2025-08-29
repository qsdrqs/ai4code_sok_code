void serialize(ILittleEndianOutput out1) { out1.writeShort(); }
public static <T> void addAll(BlockList<T> src, BlockList<T> dst) { int srcDirIdx = 0; if (src.tailDirIdx == 0) { dst.addAll(src); return; } for (; srcDirIdx < src.tailDirIdx; ++srcDirIdx) { dst.addAll(src.directory[srcDirIdx], 0, BlockList.BLOCK_SIZE); } if (0 != src.tailBlkIdx) { T[] tailBlock = src.directory[srcDirIdx]; dst.addAll(tailBlock, 0, src.tailBlkIdx); } }
public void writeByte(byte b) { if (currentBlock == null || upto == blockSize) { currentBlock = new byte[blockSize]; blocks.add(currentBlock); upto = 0; } currentBlock[upto++] = b; }
ObjectId getObjectId() { return objectId; }
return invoke(request, new InvokeOptions().setRequestMarshaller(DeleteDomainEntryRequestMarshaller.Instance).setResponseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.Instance));
public long ramBytesUsed() { return fst == null ? 0L : fst.getSizeInBytes(); }
public String getFullMessage() { byte[] raw = getRawBuffer(); int msgB = RawParseUtils.tagMessage(raw, 0); if (msgB < 0) return ""; java.nio.charset.Charset enc = RawParseUtils.parseEncoding(raw); return RawParseUtils.decode(enc, raw, msgB, raw.length); }
headerBlock = new HeaderBlock(); _property_table = new PropertyTable(); _documents = new ArrayList(); _root = null;
assert address < upto; assert (address & ByteBlockPool.BYTE_BLOCK_MASK) == 0; int slice = address >> ByteBlockPool.BYTE_BLOCK_SHIFT; assert slice < pool.Buffers.length; assert pool.Buffers[slice] != null;
git.submoduleAdd().setPath(path);
public ListIngestionsResponse ListIngestions(ListIngestionsRequest request) {     var options = new InvokeOptions();     options.RequestMarshaller = ListIngestionsRequestMarshaller.Instance;     options.ResponseUnmarshaller = ListIngestionsResponseUnmarshaller.Instance;     return Invoke<ListIngestionsRequest, ListIngestionsResponse>(request, options); }
public void SwitchTo(int lexState)
public GetShardIteratorResponse GetShardIterator(GetShardIteratorRequest request) {     var options = new InvokeOptions     {         RequestMarshaller = GetShardIteratorRequestMarshaller.Instance,         ResponseUnmarshaller = GetShardIteratorResponseUnmarshaller.Instance     };     return Invoke<GetShardIteratorRequest, GetShardIteratorResponse>(request, options); }
modifyStrategyRequest.setMethod(MethodType.POST);
public synchronized boolean ready() { try { return in != null && in.available() > 0; } catch (java.io.IOException e) { return false; } }
public EscherOptRecord getOptRecord() { return _optRecord; }
if (buffer == null) { throw new NullPointerException(); } else if (offset < 0 || count < 0 || count > buffer.length - offset) { throw new IndexOutOfBoundsException(); } int toRead = Math.min(count, this.length - this.position); if (toRead <= 0) { return 0; } System.arraycopy(this.internalBuffer, this.position, buffer, offset, toRead); this.position += toRead; return toRead;
NLPSentenceDetectorOp sentenceOp = new OpenNLPSentenceBreakIterator();
public void print(String str) { if (str != null) { System.out.println(str); } }
public class NotImplementedFunctionException extends UnsupportedOperationException { public NotImplementedFunctionException(String functionName, Throwable cause) { super("Function not implemented: " + functionName, cause); } }
return nextEntry.getValue();
public void readBytes(byte[] b, int offset, int len) throws java.io.IOException { int available = bufferLength - bufferPosition; if (available >= len) { System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { if (available > 0) { System.arraycopy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; } if (len < bufferSize) { refill(); if (bufferLength < len) { throw new java.io.EOFException(); } System.arraycopy(buffer, 0, b, offset, len); bufferPosition = len; } else { int bytesRead = readInternal(b, offset, len); if (bytesRead < len) { throw new java.io.EOFException(); } } } }
return this.<TagQueueResponse>invoke(request, new InvokeOptions.Builder().requestMarshaller(TagQueueRequestMarshaller.getInstance()).responseUnmarshaller(TagQueueResponseUnmarshaller.getInstance()).build());
void remove() { throw new UnsupportedOperationException(); }
public ModifyCacheSubnetGroupResult modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(ModifyCacheSubnetGroupRequestMarshaller.getInstance()).withResponseUnmarshaller(ModifyCacheSubnetGroupResultUnmarshaller.getInstance())); }
public void setParams(String input) { java.util.StringTokenizer st = new java.util.StringTokenizer(input); if (st.hasMoreTokens()) { this.param1 = st.nextToken(); } if (st.hasMoreTokens()) { this.param2 = st.nextToken(); } if (st.hasMoreTokens()) { this.param3 = st.nextToken(); } }
public DeleteDocumentationVersionResponse DeleteDocumentationVersion(DeleteDocumentationVersionRequest request)  {     return Invoke<DeleteDocumentationVersionRequest, DeleteDocumentationVersionResponse>(request, new InvokeOptions      {         RequestMarshaller = DeleteDocumentationVersionRequestMarshaller.Instance,         ResponseUnmarshaller = DeleteDocumentationVersionResponseUnmarshaller.Instance     }); }
@Override public boolean equals(Object obj) { if (obj == null || !(obj instanceof FacetLabel)) { return false; } FacetLabel other = (FacetLabel) obj; if (this.components.length != other.components.length) { return false; } for (int i = this.components.length - 1; i >= 0; --i) { if (!this.components[i].equals(other.components[i])) { return false; } } return true; }
public GetInstanceAccessDetailsResponse getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(GetInstanceAccessDetailsRequestMarshaller.getInstance()).withResponseUnmarshaller(GetInstanceAccessDetailsResponseUnmarshaller.getInstance())); }
public HSSFPolygon createPolygon(HSSFChildAnchor anchor) { return patriarch.createPolygon(anchor); }
public String getSheetName(int sheetIndex) { return getBoundSheetRec(sheetIndex).getSheetname(); }
public GetDashboardResponse getDashboard(GetDashboardRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetDashboardRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetDashboardResponseUnmarshaller.getInstance()); return invoke(request, options); }
// The original code is a jumbled version of this line: return Invoke<AssociateSigninDelegateGroupsWithAccountResponse>(request, new InvokeOptions {      RequestMarshaller = AssociateSigninDelegateGroupsWithAccountRequestMarshaller.Instance,      ResponseUnmarshaller = AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.Instance  });
void addMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < mbr.getNumColumns(); ++j) { BlankRecord br = new BlankRecord(); br.setRow(mbr.getRow()); br.setColumn(mbr.getFirstColumn() + j); br.setXFIndex(mbr.getXFAt(j)); insertCell(br); } }
public static String quote(String s) { StringBuilder sb = new StringBuilder(); int lastIndex = 0; int currentIndex; while ((currentIndex = s.indexOf('\\', lastIndex)) != -1) { sb.append(s.substring(lastIndex, currentIndex)); sb.append("\\\\"); lastIndex = currentIndex + 1; } sb.append(s.substring(lastIndex)); return sb.toString(); }
throw new java.nio.ReadOnlyBufferException();
Object[][] values2d = new Object[nRows][nColumns]; for (int r = 0; r < nRows; ++r) { for (int c = 0; c < nColumns; ++c) { values2d[r][c] = vv[r][c]; } }
return invoke(request, new InvokeOptions().withRequestMarshaller(GetIceServerConfigRequestMarshaller.getInstance()).withResponseUnmarshaller(GetIceServerConfigResponseUnmarshaller.getInstance()));
@Override public String toString() { StringBuilder sb = new StringBuilder(); sb.append(this.getClass().getSimpleName()); sb.append(" = "); sb.append(this.getValueAsString()); return sb.toString(); }
public String toString() { return (String)field + " " + _parentQuery + " "; }
void incRef() { refCount.incrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResponse UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) {     var options = new InvokeOptions();     options.RequestMarshaller = UpdateConfigurationSetSendingEnabledRequestMarshaller.Instance;     options.ResponseUnmarshaller = UpdateConfigurationSetSendingEnabledResponseUnmarshaller.Instance;          return Invoke<UpdateConfigurationSetSendingEnabledResponse>(request, options); }
int GetNextXBATChainOffset()  {      return GetXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE;  }
void multiplyByPowerOfTen(int pow10) { if (pow10 < 0) { TenPower tp = TenPower.getInstance(Math.abs(pow10)); this._divisor = tp.divisor; this._divisorShift = tp.divisorShift; } else { TenPower tp = TenPower.getInstance(pow10); this._multiplicand = tp.multiplicand; this._multiplierShift = tp.multiplierShift; } }
public String removeTrailingSeparator(String path) { if (path == null || path.isEmpty()) { return path; } int length = path.length(); if (length > 1 && path.charAt(length - 1) == java.io.File.separatorChar) { return path.substring(0, length - 1); } return path; }
public void withFetcher(ECSMetadataServiceCredentialsFetcher fetcher) { fetcher.setRoleName(); }
void setProgressMonitor(ProgressMonitor progressMonitor) {}
// Reconstructed C# Code for analysis void ParseEntry() {     if (!Eof())     {         if (!First())         {             ptr = 0;             Reset();         }     } }
public E previous() { if (cursor == 0) throw new java.util.NoSuchElementException(); return elementData[--cursor]; }
String getNewPrefix() { return newPrefix; }
int indexOfValue(int value) { for (int i = 0; i < mSize; ++i) { if (mValues[i] == value) { return i; } } return -1; }
public static java.util.List<org.apache.lucene.util.CharsRef> uniqueStems(java.util.List<org.apache.lucene.util.CharsRef> stems) { java.util.List<org.apache.lucene.util.CharsRef> deduped = new java.util.ArrayList<>(); org.apache.lucene.analysis.util.CharArraySet terms = new org.apache.lucene.analysis.util.CharArraySet(stems.size(), false); for (org.apache.lucene.util.CharsRef s : stems) { if (terms.add(s)) { deduped.add(s); } } return deduped; }
public GetGatewayResponsesResponse getGatewayResponses(GetGatewayResponsesRequest request) { return invoke(request, new InvokeOptions<>().withRequestMarshaller(GetGatewayResponsesRequestMarshaller.INSTANCE).withResponseUnmarshaller(GetGatewayResponsesResponseUnmarshaller.INSTANCE)); }
void setPosition(long position) { currentBlockIndex = (int) (position >> blockBits); currentBlock = blocks[currentBlockIndex]; currentBlockUpto = (int) (position & blockMask); }
long s = (long)Math.max(ptr, Math.min(n, Available));
public void setBootstrapActionConfig(BootstrapActionConfig bootstrapActionConfig)
public void serialize(LittleEndianOutput out) { out.writeShort(field_1); out.writeShort(field_2); out.writeShort(field_3); out.writeShort(field_4); out.writeShort(field_6_author.length()); out.writeByte(field_5_hasMultibyte ? 1 : 0); if (field_5_hasMultibyte) { StringUtil.putUnicodeLE(field_6_author, out); } else { StringUtil.putCompressedUnicode(field_6_author, out); } if (field_7_padding != null) { out.write(field_7_padding); } }
public int lastIndexOf(String string) { return string.lastIndexOf(','); }
boolean add(E o) { return addLastImpl(o); }
UnsetSection void } { ) , ( ; ) ( while do ; ; subsection String section String ! } { ConfigSnapshot ConfigSnapshot ; ; ) , ( CompareAndSet . state res src UnsetSection ) , , ( ) ( Get . state
String getTagName() { return tagName; }
void AddSubRecord(int index, SubRecord element)  {     subrecords.Insert(index, element); }
boolean remove(Object obj) { synchronized (mutex) { return c.remove(); } }
public TokenStream create(TokenStream input) { return new DoubleMetaphoneFilter(input, 4, false); }
public long Length() { return InCoreLength(); }
void setValue(boolean newValue) { value = newValue; }
public Pair(ContentSource newSource, ContentSource oldSource) { this.newSource = newSource; this.oldSource = oldSource; }
public int get(int i) { if (i >= count) { throw new IndexOutOfBoundsException(); } return entries[i]; }
public class CreateRepoRequest { public static final String METHOD_TYPE = "PUT"; public static final String URI_PATTERN = "/your/uri/pattern"; }
public boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public void remove() { if (modCount != expectedModCount) throw new java.util.ConcurrentModificationException(); if (lastReturned == null) throw new java.lang.IllegalStateException(); Node<E> next = lastReturned.next; Node<E> prev = lastReturned.previous; if (prev == null) first = next; else prev.next = next; if (next == null) last = prev; else next.previous = prev; size--; modCount++; expectedModCount++; lastReturned = null; }
public MergeShardsResponse MergeShards(MergeShardsRequest request)  {     return Invoke<MergeShardsRequest, MergeShardsResponse>(request, new InvokeOptions      {         RequestMarshaller = MergeShardsRequestMarshaller.Instance,         ResponseUnmarshaller = MergeShardsResponseUnmarshaller.Instance     }); }
return invoke(request, new InvokeOptions(AllocateHostedConnectionRequestMarshaller.INSTANCE, AllocateHostedConnectionResponseUnmarshaller.INSTANCE));
int getBeginIndex()  {      return start;  }
WeightedTerm[] getTerms(Query query) { return query.getTerms(); }
throw new java.nio.ReadOnlyBufferException();
public static void decode(int[] values, int valuesOffset, byte[] blocks, int blocksOffset, int iterations) { for (int i = 0; i < iterations; i++) { int byte0 = blocks[blocksOffset++] & 0xFF; int byte1 = blocks[blocksOffset++] & 0xFF; int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = byte0 >> 2; values[valuesOffset++] = ((byte0 & 0x03) << 4) | (byte1 >> 4); values[valuesOffset++] = ((byte1 & 0x0F) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 0x3F; } }
public static String getHumanishName(String s) { if (s == null || s.isEmpty()) return ""; String[] elements = s.split("[/\\\\]"); if (elements.length == 0) return ""; String result = elements[elements.length - 1]; if (result.isEmpty() && elements.length >= 2) result = elements[elements.length - 2]; if (result.endsWith(".git")) result = result.substring(0, result.length() - 4); return result; }
public DescribeNotebookInstanceLifecycleConfigResponse describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { return invoke(request, DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance(), DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance()); }
public String getAccessKeySecret() { return accessKeySecret; }
return Invoke<CreateVpnConnectionResponse>(request, new InvokeOptions  {      RequestMarshaller = CreateVpnConnectionRequestMarshaller.Instance,      ResponseUnmarshaller = CreateVpnConnectionResponseUnmarshaller.Instance  });
public DescribeVoicesResponse describeVoices(DescribeVoicesRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeVoicesRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeVoicesResponseUnmarshaller.getInstance())); }
return invoke(request, InvokeOptions.builder().requestMarshaller(ListMonitoringExecutionsRequestMarshaller.getInstance()).responseUnmarshaller(ListMonitoringExecutionsResponseUnmarshaller.getInstance()).build());
public DescribeJobRequest(String vaultName, String jobId) { this._vaultName = vaultName; this._jobId = jobId; }
EscherRecord GetEscherRecord(int index) {     return escherRecords[index]; }
public GetApisResponse getApis(GetApisRequest request) { return this.<GetApisResponse>invoke(request, new InvokeOptions().withRequestMarshaller(GetApisRequestMarshaller.getInstance()).withResponseUnmarshaller(GetApisResponseUnmarshaller.getInstance())); }
public DeleteSmsChannelResponse deleteSmsChannel(DeleteSmsChannelRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteSmsChannelRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteSmsChannelResponseUnmarshaller.getInstance()); return invoke(request, options); }
TrackingRefUpdate getTrackingRefUpdate() { return trackingRefUpdate; }
void print(boolean b) { print(Boolean.toString(b)); }
public IQueryNode getChild() { return getChildren().get(0); }
NotIgnoredFilter workdirTreeIndex = new NotIgnoredFilter(index);
field_1_formatFlags = in1.readShort();
} { : ) ( GetThumbnailRequest ; Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " "
public DescribeTransitGatewayVpcAttachmentsResponse describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest describeTransitGatewayVpcAttachmentsRequest) { return ec2Client.describeTransitGatewayVpcAttachments(describeTransitGatewayVpcAttachmentsRequest); }
return invoke(request, new InvokeOptions(PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance(), PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance()));
public OrdRange getOrdRange(String dim) { return prefixToOrdRange.get(dim); }
String toString() { ) ; return; if () ; } { && String.format( , , , ) ; ; startIndex >= 0 < symbol.getName() + " " + Locale.getDefault() symbol.size() = symbol.getClass() "" new LexerNoViableAltException( , Utils.escapeWhitespace( charStream.getText( (org.antlr.v4.runtime.CharStream)inputStream, Interval.of() ) ) );
public E peek() { return peekFirstImpl(); }
public CreateWorkspacesResponse createWorkspaces(CreateWorkspacesRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(CreateWorkspacesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(CreateWorkspacesResponseUnmarshaller.getInstance()); return this.<CreateWorkspacesRequest, CreateWorkspacesResponse>invoke(request, options); }
public Object clone() { NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = this.field_1_formatIndex; return rec; }
public DescribeRepositoriesResponse describeRepositories(DescribeRepositoriesRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeRepositoriesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeRepositoriesResponseUnmarshaller.getInstance()); return invoke(request, options); }
initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0;
public TokenStream create(TokenStream input) { return new HyphenatedWordsFilter(input); }
public CreateDistributionWithTagsResponse CreateDistributionWithTags(CreateDistributionWithTagsRequest request) {     var options = new InvokeOptions();     options.RequestMarshaller = CreateDistributionWithTagsRequestMarshaller.Instance;     options.ResponseUnmarshaller = CreateDistributionWithTagsResponseUnmarshaller.Instance;     return Invoke<CreateDistributionWithTagsResponse>(request, options); }
public void fileOperation(String fileName, String mode) { throw new UnsupportedOperationException("Not implemented yet."); }
public DeleteWorkspaceImageResponse deleteWorkspaceImage(DeleteWorkspaceImageRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.getInstance())); }
public String toHex(int value) { return Integer.toHexString(value); }
return invoke(request, new InvokeOptions().withRequestMarshaller(UpdateDistributionRequestMarshaller.INSTANCE).withResponseUnmarshaller(UpdateDistributionResponseUnmarshaller.INSTANCE));
public org.apache.poi.hssf.util.HSSFColor getColor(short index) { if (index == org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined.AUTOMATIC.getIndex()) { return org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined.AUTOMATIC.getColor(); } byte[] b = palette.getColor(index); if (b != null) { return new org.apache.poi.hssf.util.HSSFColor.Custom(b); } else { return null; } }
public ValueEval evaluate(ValueEval[] operands, int srcRow, int srcCol) { throw new UnsupportedOperationException(); }
public void serialize(LittleEndianOutput out1) { out1.writeShort((short) field_1_number_crn_records); out1.writeShort((short) field_2_sheet_table_index); }
public DescribeDBEngineVersionsResponse describeDBEngineVersions() { return describeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
public class FormatRun { public final short _fontIndex; public final short _character; public FormatRun(short fontIndex, short character) { this._fontIndex = fontIndex; this._character = character; } }
public static byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int resultIndex = 0; for (int i = offset; i < offset + length; i++) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
return this.<UploadArchiveResponse>invoke(request, new InvokeOptions().withRequestMarshaller(UploadArchiveRequestMarshaller.getInstance()).withResponseUnmarshaller(UploadArchiveResponseUnmarshaller.getInstance()));
List<IToken> getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex - 1); }
@Override public boolean equals(Object obj) { if (obj == null || getClass() != obj.getClass()) return false; AutomatonQuery other = (AutomatonQuery) obj; if (m_term == null) { if (other.m_term != null) return false; } else if (!m_term.equals(other.m_term)) return false; if (m_compiled == null) { if (other.m_compiled != null) return false; } else if (!m_compiled.equals(other.m_compiled)) return false; return true; }
public org.apache.lucene.search.span.SpanQuery makeSpanClause(java.util.Map<org.apache.lucene.search.span.SpanQuery, Float> weightBySpanQuery) { java.util.List<org.apache.lucene.search.span.SpanQuery> spanQueries = new java.util.ArrayList<>(); for (java.util.Map.Entry<org.apache.lucene.search.span.SpanQuery, Float> wsq : weightBySpanQuery.entrySet()) { spanQueries.add(new org.apache.lucene.search.span.SpanBoostQuery(wsq.getKey(), wsq.getValue())); } if (spanQueries.size() == 1) { return spanQueries.get(0); } else { return new org.apache.lucene.search.span.SpanOrQuery(spanQueries.toArray(new org.apache.lucene.search.span.SpanQuery[0])); } }
StashCreateCommand StashCreate() { return new StashCreateCommand(); }
return byName.get(fieldName);
public DescribeEventSourceResponse DescribeEventSource(DescribeEventSourceRequest request) {     var options = new InvokeOptions();     options.RequestMarshaller = DescribeEventSourceRequestMarshaller.Instance;     options.ResponseUnmarshaller = DescribeEventSourceResponseUnmarshaller.Instance;     return Invoke<DescribeEventSourceRequest, DescribeEventSourceResponse>(request, options); }
public GetDocumentAnalysisResponse getDocumentAnalysis(GetDocumentAnalysisRequest request) { return this.<GetDocumentAnalysisRequest, GetDocumentAnalysisResponse>invoke(request, new InvokeOptions(), GetDocumentAnalysisRequestMarshaller.getInstance(), GetDocumentAnalysisResponseUnmarshaller.getInstance()); }
public CancelUpdateStackResponse cancelUpdateStack(CancelUpdateStackRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(CancelUpdateStackRequestMarshaller.getInstance()); options.setResponseUnmarshaller(CancelUpdateStackResponseUnmarshaller.getInstance()); return invoke(request, options); }
public ModifyLoadBalancerAttributesResponse modifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ModifyLoadBalancerAttributesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance()); return this.<ModifyLoadBalancerAttributesResponse>invoke(request, options); }
public SetInstanceProtectionResponse setInstanceProtection(SetInstanceProtectionRequest request) { return this.invoke(request, new InvokeOptions().withRequestMarshaller(SetInstanceProtectionRequestMarshaller.getInstance()).withResponseUnmarshaller(SetInstanceProtectionResponseUnmarshaller.getInstance())); }
return Invoke<ModifyDBProxyRequest, ModifyDBProxyResponse>(request, new InvokeOptions {     RequestMarshaller = ModifyDBProxyRequestMarshaller.Instance,     ResponseUnmarshaller = ModifyDBProxyResponseUnmarshaller.Instance });
public void add(CharsRef output, int offset, int len, int endOffset, int posLength) { if (count == outputs.length) { CharsRef[] nextOutputs = new CharsRef[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF)]; System.arraycopy(outputs, 0, nextOutputs, 0, count); outputs = nextOutputs; } if (count == endOffsets.length) { int[] nextEndOffsets = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(endOffsets, 0, nextEndOffsets, 0, count); endOffsets = nextEndOffsets; } if (count == posLengths.length) { int[] nextPosLengths = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(posLengths, 0, nextPosLengths, 0, count); posLengths = nextPosLengths; } if (outputs[count] == null) { outputs[count] = new CharsRef(); } outputs[count].copyChars(output.chars, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
} { : ) ( FetchLibrariesRequest ; Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " "
boolean exists() { return objects.exists(); }
public void processStream(FilterOutputStream out) {}
@PutMapping("/api/clusters/scale") public ResponseEntity<Void> scaleCluster(@RequestBody ScaleClusterRequest request) { /* Business logic to scale the cluster */ return ResponseEntity.ok().build(); }
public IDataValidationConstraint createTimeConstraint(int operatorType, String formula1, String formula2) { return DVConstraint.createTimeConstraint(operatorType, formula1, formula2); }
public ListObjectParentPathsResponse listObjectParentPaths(ListObjectParentPathsRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(ListObjectParentPathsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListObjectParentPathsResponseUnmarshaller.getInstance())); }
public DescribeCacheSubnetGroupsResponse describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { return this.<DescribeCacheSubnetGroupsRequest, DescribeCacheSubnetGroupsResponse>invoke(request, new InvokeOptions(), DescribeCacheSubnetGroupsRequestMarshaller.INSTANCE, DescribeCacheSubnetGroupsResponseUnmarshaller.INSTANCE); }
public void setSharedFormula(boolean flag) { field_5_options = sharedFormula.setShortBoolean(field_5_options, flag); }
public boolean isReuseObjects() { return reuseObjects; }
public ErrorNode addErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); addChild(t); return t; }
public LatvianStemFilterFactory(java.util.Map<String, String> args) { super(args); if (args.size() > 0) { throw new IllegalArgumentException("Unknown parameters: " + args); } }
public RemoveSourceIdentifierFromSubscriptionResponse removeSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) { return invoke(request, new RemoveSourceIdentifierFromSubscriptionRequestMarshaller(), new RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller()); }
public TokenFilterFactory forName(String name, Map<String, String> args) { return loader.newInstance(name, args); }
} { : ) ( AddAlbumPhotosRequest ; Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " "
public GetThreatIntelSetResponse getThreatIntelSet(GetThreatIntelSetRequest request) { return client.getThreatIntelSet(request); }
public TreeFilter Clone()  {     return new Binary.AndTreeFilter(a.Clone(), b.Clone()); }
public boolean equals(Object o) { return o instanceof Object; }
protected boolean hasArray() { return protectedHasArray; }
public UpdateContributorInsightsResponse updateContributorInsights(UpdateContributorInsightsRequest request) { return invoke(request, new ExecutionHandler(UpdateContributorInsightsRequestMarshaller.getInstance(), UpdateContributorInsightsResponseUnmarshaller.getInstance())); }
void unwriteProtectWorkbook() { records.remove(writeProtect); records.remove(fileShare); writeProtect = null; fileShare = null; }
public SolrSynonymParser(boolean expand, boolean dedup, Analyzer analyzer) { super(expand, dedup, analyzer); }
public RequestSpotInstancesResponse RequestSpotInstances(RequestSpotInstancesRequest request) {     var options = new InvokeOptions();     options.RequestMarshaller = RequestSpotInstancesRequestMarshaller.Instance;     options.ResponseUnmarshaller = RequestSpotInstancesResponseUnmarshaller.Instance;          return Invoke<RequestSpotInstancesResponse>(request, options); }
public byte[] getObjectData() { return ObjectData.findObjectRecord(); }
public GetContactAttributesResponse getContactAttributes(GetContactAttributesRequest request) { InvokeOptions options = new InvokeOptions(); return invoke(request, options, GetContactAttributesRequestMarshaller.getInstance(), GetContactAttributesResponseUnmarshaller.getInstance()); }
public string ToString() {     return GetKey() + " " + GetValue(); }
public ListTextTranslationJobsResponse ListTextTranslationJobs(ListTextTranslationJobsRequest request) {     return Invoke<ListTextTranslationJobsRequest, ListTextTranslationJobsResponse>(request, new InvokeOptions     {         RequestMarshaller = ListTextTranslationJobsRequestMarshaller.Instance,         ResponseUnmarshaller = ListTextTranslationJobsResponseUnmarshaller.Instance     }); }
public GetContactMethodsResponse getContactMethods(GetContactMethodsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetContactMethodsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetContactMethodsResponseUnmarshaller.getInstance()); return invoke(request, options, GetContactMethodsResponse.class); }
short LookupIndexByName(String name) {     // Assumes GetInstance is a static method on some class returning a singleton instance.     FunctionMetadata fd = SomeClass.GetInstance().GetFunctionByNameInternal(name);      if (fd == null)     {         return -1;     }          return fd.Index; }
public DescribeAnomalyDetectorsResponse describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { return invoke(request, new InvokeOptions(DescribeAnomalyDetectorsRequestMarshaller.getInstance(), DescribeAnomalyDetectorsResponseUnmarshaller.getInstance())); }
public String insertId(String message, ObjectId changeId) { return changeId.toString(); }
public long getObjectSize(AnyObjectId objectId, int typeHint) throws MissingObjectException { long sz = db.getObjectSize(objectId.copy(), typeHint); if (sz < 0) { throw new MissingObjectException(objectId.copy(), "any"); } return sz; }
return invoke(request, ImportInstallationMediaRequestMarshaller.getInstance(), ImportInstallationMediaResponseUnmarshaller.getInstance());
public PutLifecycleEventHookExecutionStatusResponse putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance()); options.setResponseUnmarshaller(PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance()); return invoke(request, options); }
field_1_value = in1.readDouble();
public GetFieldLevelEncryptionConfigResponse getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { return invoke(request, GetFieldLevelEncryptionConfigRequestMarshaller.getInstance(), GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance()); }
public DescribeDetectorResponse DescribeDetector(DescribeDetectorRequest request) {     return Invoke<DescribeDetectorRequest, DescribeDetectorResponse>(request, new InvokeOptions     {         RequestMarshaller = DescribeDetectorRequestMarshaller.Instance,         ResponseUnmarshaller = DescribeDetectorResponseUnmarshaller.Instance     }); }
public ReportInstanceStatusResponse reportInstanceStatus(ReportInstanceStatusRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(ReportInstanceStatusRequestMarshaller.getInstance()).withResponseUnmarshaller(ReportInstanceStatusResponseUnmarshaller.getInstance())); }
return this.invoke(request, new InvokeOptions.Builder().requestMarshaller(DeleteAlarmRequestMarshaller.getInstance()).responseUnmarshaller(DeleteAlarmResponseUnmarshaller.getInstance()).build());
public TokenStream create(TokenStream input) { return new PortugueseStemFilter(input); }
private byte[] reserved = new byte[ENCODED_SIZE];
public boolean remove(Object object) { synchronized (mutex) { return c.remove(object); } }
public GetDedicatedIpResponse getDedicatedIp(GetDedicatedIpRequest request) { InvokeOptions options = new InvokeOptions(); return invoke(request, options, GetDedicatedIpRequestMarshaller.getInstance(), GetDedicatedIpResponseUnmarshaller.getInstance()); }
@Override public String toString() { return precedence + " "; }
return invoke(request, new InvokeOptions().withRequestMarshaller(ListStreamProcessorsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListStreamProcessorsResponseUnmarshaller.getInstance()));
public DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) { this.loadBalancerName = loadBalancerName; this.policyName = policyName; }
public MyClass(WindowProtectRecord options) { this.options = options; }
int[] data = new int[bufferSize];
public GetOperationsResponse getOperations(GetOperationsRequest request) { return invoke(request, new InvokeOptions.Builder().requestMarshaller(GetOperationsRequestMarshaller.getInstance()).responseUnmarshaller(GetOperationsResponseUnmarshaller.getInstance()).build()); }
void copyRawTo(byte[] b, int o) { EncodeInt32.NB(value1, b, o); EncodeInt32.NB(value2, b, o + 4); EncodeInt32.NB(value3, b, o + 8); EncodeInt32.NB(value4, b, o + 12); EncodeInt32.NB(value5, b, o + 16); }
public WindowOneRecord(RecordInputStream in1) { field_1_h_hold = in1.readShort(); field_2_v_hold = in1.readShort(); field_3_width = in1.readShort(); field_4_height = in1.readShort(); field_5_options = in1.readShort(); field_6_active_sheet = in1.readShort(); field_7_first_visible_tab = in1.readShort(); field_8_num_selected_tabs = in1.readShort(); field_9_tab_width_ratio = in1.readShort(); }
return invoke(request, new InvokeOptions().withRequestMarshaller(StopWorkspacesRequestMarshaller.getInstance()).withResponseUnmarshaller(StopWorkspacesResponseUnmarshaller.getInstance()));
public void close() throws java.io.IOException { try { if (isOpen()) { dump(); channel.truncate(0); } } finally { try { if (channel != null) channel.close(); } finally { if (fos != null) fos.close(); } } }
return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeMatchmakingRuleSetsRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeMatchmakingRuleSetsResponseUnmarshaller.getInstance()));
string GetPronunciation(int wordId, char[] surface, int off, int len) {     return null; }
String getPath() { return pathStr; }
public static double devSq(double[] v) { if (v == null || v.length == 0) return Double.NaN; int n = v.length; if (n == 1) return 0.0; double s = 0.0; for (int i = 0; i < n; i++) s += v[i]; double m = s / n; s = 0.0; for (int i = 0; i < n; i++) s += (v[i] - m) * (v[i] - m); return s; }
return invoke(request, new InvokeOptions().withMarshaller(DescribeResizeRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeResizeResponseUnmarshaller.getInstance()));
public boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
int end() { return end; }
public void traverse(ISheet sheet, int firstRow, int lastRow, int firstColumn, int lastColumn, boolean traverseEmptyCells, ICellHandler handler) { int width = lastColumn - firstColumn + 1; for (int rowNumber = firstRow; rowNumber <= lastRow; ++rowNumber) { IRow currentRow = sheet.getRow(rowNumber); for (int colNumber = firstColumn; colNumber <= lastColumn; ++colNumber) { ICell currentCell = (currentRow != null) ? currentRow.getCell(colNumber) : null; if (currentCell != null || traverseEmptyCells) { SimpleCellWalkContext ctx = new SimpleCellWalkContext(rowNumber, colNumber, (rowNumber - firstRow) * width + (colNumber - firstColumn)); handler.onCell(currentCell, ctx); } } } }
int getReadIndex() { return _ReadIndex; }
@Override public int compareTo(ScoreTerm other) { if (this.getTerm().compareTo(other.getTerm()) == 0) { return this.getBoost().compareTo(other.getBoost()); } else { return this.getTerm().compareTo(other.getTerm()); } }
public int normalize(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = StemmerUtil.delete(s, i, len); i--; break; default: break; } } return len; }
// Reconstructed C# code void Serialize(ILittleEndianOutput out1)  {     out1.WriteShort(0);  }
public DiagnosticErrorListener(boolean exactOnly) { this.exactOnly = exactOnly; }
public KeySchemaElement(KeyType keyType, String attributeName) { this.keyType = keyType; this.attributeName = attributeName; }
public GetAssignmentResponse getAssignment(GetAssignmentRequest request) { return this.<GetAssignmentRequest, GetAssignmentResponse>invoke(request, new InvokeOptions().withRequestMarshaller(GetAssignmentRequestMarshaller.getInstance()).withResponseUnmarshaller(GetAssignmentResponseUnmarshaller.getInstance())); }
boolean HasObject() { return FindOffset(id.AnyObjectId()) != -1; }
public class GroupingSearch { private boolean allGroups; public void SetAllGroups(boolean allGroups) { this.allGroups = allGroups; return; } }
public void setMultiValued(String dimName, boolean v) { synchronized (fieldTypes) { DimConfig fieldType = fieldTypes.get(dimName); if (fieldType != null) { fieldType.setMultiValued(v); } else { fieldType = new DimConfig(); fieldType.setMultiValued(v); fieldTypes.put(dimName, fieldType); } } }
public int getCellsVal(String cmd, java.util.Map<Character, Integer> cells) { int size = 0; for (char c : cmd.toCharArray()) { if (cells.containsKey(c) && cells.get(c) >= 0) { size++; } } return size; }
public DeleteVoiceConnectorResponse deleteVoiceConnector(DeleteVoiceConnectorRequest request) { return client.deleteVoiceConnector(request); }
public DeleteLifecyclePolicyResponse deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { return invoke(request, new InvokeOptions(DeleteLifecyclePolicyRequestMarshaller.getInstance(), DeleteLifecyclePolicyResponseUnmarshaller.getInstance())); }
