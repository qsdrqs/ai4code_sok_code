public void serialize(LittleEndianOutput out1) {}
void addAll(ngit.util.BlockList<T> src) { if (src.size == 0) {} int srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) { addAll(src.directory[srcDirIdx], 0, Block.SIZE); } if (src.tailBlkIdx != 0) { addAll(src.tailBlock, 0, src.tailBlkIdx); } }
public void writeByte(byte b) { if (outerInstance.upto == outerInstance.currentBlock.length) { if (outerInstance.currentBlock != null) { outerInstance.blocks.add(outerInstance.currentBlock); outerInstance.blockEnd.add(outerInstance.upto); } outerInstance.currentBlock = new byte[outerInstance.blockSize]; outerInstance.upto = 0; } outerInstance.currentBlock[outerInstance.upto++] = (byte) b; }
public ObjectId getObjectId() { return objectId; }
public DeleteDomainEntryResult deleteDomainEntry(DeleteDomainEntryRequest request) { request = beforeClientExecution(request); return executeDeleteDomainEntry(request); }
public long ramBytesUsed() { return fst == null ? 0 : fst.getSizeInBytes(); }
public String getFullMessage() { byte[] raw = buffer; int msgB = RawParseUtils.tagMessage(); if (msgB < 0) { return String.Empty; } Encoding enc = RawParseUtils.parseEncoding(); return RawParseUtils.decode(enc, raw, raw.length); }
public POIFSFileSystem() { HeaderBlock headerBlock = new HeaderBlock(); PropertyTable _property_table = new PropertyTable(); ArrayList _documents = new ArrayList(); Object _root = null; }
void init(int address) { Buffer slice = pool.Buffers[(address >> ByteBlockPool.BYTE_BLOCK_SHIFT)]; assert slice != null; int upto = address & ByteBlockPool.BYTE_BLOCK_MASK; int offset0 = address; assert upto < ; }
public SubmoduleAddCommand setPath(String path) { this.path = path; return this; }
@Override public ListIngestionsResponse listIngestions(ListIngestionsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListIngestionsRequestMarshaller.INSTANCE); options.setResponseUnmarshaller(ListIngestionsResponseUnmarshaller.INSTANCE); return (ListIngestionsResponse) invoke(request, options); }
public QueryParserTokenManager(final ICharStream stream, final int lexState) { this(); SwitchTo(); }
public GetShardIteratorResponse getShardIterator(GetShardIteratorRequest request) { options = new InvokeOptions(); options.setRequestMarshaller(GetShardIteratorRequestMarshaller.INSTANCE); options.setResponseUnmarshaller(GetShardIteratorResponseUnmarshaller.INSTANCE); return null; }
public ModifyStrategyResult modifyStrategyRequest() { ModifyStrategyRequest request = new ModifyStrategyRequest(" ", " ", " ", " ", " "); request = beforeClientExecution(request); return executeModifyStrategyRequest(request); }
public boolean ready() { synchronized (lock) { if (in == null) { throw new IOException(" "); } try { return bytes.hasRemaining() || in.available() > 0; } catch (IOException e) { return false; } } }
EscherOptRecord getOptRecord() { }
public int read(byte[] buffer, int offset, int length) { if (buffer == null) throw new IllegalArgumentException(" "); java.util.Arrays.checkOffsetAndCount(buffer.length, offset, length); if (length == 0) return 0; int copylen = count - pos < length ? count - pos : length; for (int i = 0; i < copylen; i++) { buffer[offset + i] = this.buffer[pos + i]; } pos += copylen; return copylen; }
public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) { this.sentenceOp = sentenceOp; }
void print(String str) { write(str != null ? str : String.valueOf(null)); }
public class NotImplementedFunctionException extends Exception { private String functionName; public NotImplementedFunctionException(String functionName, Throwable cause) { super(functionName, cause); this.functionName = functionName; } }
public V next() { return nextEntry.value; }
public void readBytes(byte[] b, int offset, int len, boolean useBuffer) { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) { System.arraycopy(buffer, bufferPosition, b, offset, len); } bufferPosition += len; } else { if (available > 0) { System.arraycopy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { refill(); if (bufferLength < len) { System.arraycopy(buffer, 0, b, offset, bufferLength); throw new EndOfStreamException(); } else { System.arraycopy(buffer, 0, b, offset, len); bufferPosition += len; } } else { long after = bufferStart + bufferPosition + len; if (after > length) { throw new EndOfStreamException(); } readInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
public TagQueueResponse tagQueue(TagQueueRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(TagQueueRequestMarshaller.INSTANCE); options.setResponseUnmarshaller(TagQueueResponseUnmarshaller.INSTANCE); return invoke(options); }
public void remove() { throw new UnsupportedOperationException(); }
public ModifyCacheSubnetGroupResponse modifyCacheSubnetGroup() { options = new InvokeOptions(); options.RequestMarshaller = ModifyCacheSubnetGroupRequestMarshaller.Instance; options.ResponseUnmarshaller = ModifyCacheSubnetGroupResponseUnmarshaller.Instance; return invoke(ModifyCacheSubnetGroupResponse.class); }
public void SetParams(String params) { super.SetParams(); String culture = " "; StringTokenizer st = new StringTokenizer(params, " "); if (st.hasMoreTokens()) culture = st.nextToken(); if (st.hasMoreTokens()) culture += " " + st.nextToken(); if (st.hasMoreTokens()) st.nextToken(); }
public DeleteDocumentationVersionResponse deleteDocumentationVersion(DeleteDocumentationVersionRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(...); options.setResponseUnmarshaller(DeleteDocumentationVersionResponseUnmarshaller.INSTANCE); return invoke(request, options); }
public boolean equals(Object obj) { if (!(obj instanceof FacetLabel)) return false; FacetLabel other = (FacetLabel) obj; if (Length != other.Length) return false; for (int i = Length - 1; ; i--) { if (!Components[i].equals(other.Components[i])) return false; } return true; }
public GetInstanceAccessDetailsResponse getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetInstanceAccessDetailsRequestMarshaller.INSTANCE); options.setResponseUnmarshaller(GetInstanceAccessDetailsResponseUnmarshaller.INSTANCE); return invoke(request, options); }
public HSSFPolygon createPolygon() { HSSFPolygon shape = new HSSFPolygon(); shape.getParent(); shape.getAnchor(); shapes.add(); onCreate(); return shape; }
public String getSheetName() { return getBoundSheetRec().getSheetname(); }
public GetDashboardResponse getDashboard(GetDashboardRequest request) { InvokeOptions options = new InvokeOptions(); GetDashboardRequestMarshaller.instance; options.setResponseUnmarshaller(GetDashboardResponseUnmarshaller.instance); return invoke(request, options, GetDashboardResponse.class); }
public AssociateSigninDelegateGroupsWithAccountResponse associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(AssociateSigninDelegateGroupsWithAccountRequestMarshaller.INSTANCE); options.setResponseUnmarshaller(AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.INSTANCE); return invoke(AssociateSigninDelegateGroupsWithAccountResponse.class, request, options); }
public void addMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < mbr.getNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.setColumn(j + mbr.getFirstColumn()); br.setRow(mbr.getRow()); mbr.getXFAt(); insertCell(); } }
public String quote(String string) { StringBuilder sb = new StringBuilder(); sb.append("\\"); int apos = 0; int k; while ((k = string.indexOf("\\", apos)) >= 0) { sb.append(Sharpen.StringHelper.substring(string, apos, k + 2)).append("\\\\\\"); apos = k + 2; } return sb.append(Sharpen.StringHelper.substring(string, apos)).append("\\").toString(); }
public ByteBuffer putInt(int value) {throw new ReadOnlyBufferException();}
public ArrayPtg(Object[][] values2d) { int nColumns = values2d[0].length; int nRows = values2d.length; _nColumns = (short) nColumns; _nRows = (short) nRows; Object[] vv = new Object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { Object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[GetValueIndex(r, c)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
public GetIceServerConfigResult getIceServerConfig(GetIceServerConfigRequest request) { request = beforeClientExecution(request); return executeGetIceServerConfig(request); }
public String toString(){StringBuilder sb = new StringBuilder();sb.append(getClass().getSimpleName()).append(" ");sb.append(getValueAsString());sb.append(" ");return sb.toString();}
public String toString() { return " " + _parentQuery + " " ; }
public void incRef() { refCount.incrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResponse updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(UpdateConfigurationSetSendingEnabledRequestMarshaller.INSTANCE); options.setResponseUnmarshaller(UpdateConfigurationSetSendingEnabledResponseUnmarshaller.INSTANCE); return invoke(request, options); }
public int getNextXBATChainOffset() { return getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE; }
public void multiplyByPowerOfTen(int pow10) { TenPower tp = TenPower.getInstance(Math.abs(pow10)); if (tp._condition) { mulShift(tp._divisor, tp._divisorShift); } else { mulShift(tp._multiplicand, tp._multiplierShift); } }
public String toString() { StringBuilder builder = new StringBuilder(); int length = getLength(); builder.append(Path.DirectorySeparatorChar); for (int i = 0; i < length; i++) { builder.append(getComponent(i)); if (i < (length - 1)) { builder.append(Path.DirectorySeparatorChar); } } return builder.toString(); }
void withFetcher() { Fetcher fetcher; fetcher.setRoleName(); }
void setProgressMonitor() { progressMonitor pm; }
private void Reset() { if ( ) { ptr = 0 ; if ( ! Eof ) { ParseEntry ( ) ; } } }
public E previous() { if (iterator.previousIndex() >= start) { return iterator.previous(); } throw new java.util.NoSuchElementException(); }
public String getNewPrefix() { return newPrefix; }
public int indexOfValue(int value) { { for (int i = 0; i < mSize; ) { if (mValues[i] == value) { return i; } } } return -1; }
public List<CharsRef> uniqueStems(char[] word, int length) { List<CharsRef> stems = stem(word, length); if (stems.size() < 2) { return stems; } Set<CharsRef> terms = new HashSet<>(); List<CharsRef> deduped = new ArrayList<>(); for (CharsRef s : stems) { if (!terms.contains(s)) { terms.add(s); deduped.add(s); } } return deduped; }
public GetGatewayResponsesResponse GetGatewayResponses(){options=new InvokeOptions();options.setRequestMarshaller(GetGatewayResponsesRequestMarshaller.Instance());options.setResponseUnmarshaller(GetGatewayResponsesResponseUnmarshaller.Instance());return Invoke(GetGatewayResponsesResponse.class,null,null);}
public void setPosition(long position) { currentBlockIndex = (int)(position >> outerInstance.blockBits); currentBlock = outerInstance.blocks; currentBlockUpto = (int)(position & ((1 << outerInstance.blockBits) - 1)); }
public long skip(long n) { int s = (int) Math.max(n, available()); return s; }
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { this._bootstrapActionConfig = bootstrapActionConfig; }
public void serialize(LittleEndianOutput out1) { out1.writeShort(); out1.writeShort(); out1.writeShort(); out1.writeShort(); field_6_author.length(); out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00); if (field_5_hasMultibyte) { StringUtil.putUnicodeLE(); } else { StringUtil.putCompressedUnicode(); } if (field_7_padding != null) { out1.writeByte((byte) Integer.parseInt(field_7_padding)); } }
public int lastIndexOf() { return lastIndexOf(null, 0); }
public boolean add() { return addLastImpl(); }
public void unsetSection(String section, String subsection) { ConfigSnapshot; ConfigSnapshot; do { state.get(); res.unsetSection(,,,); } while (!state.compareAndSet(,)); }
public String getTagName() { return tagName; }
public void addSubRecord(int index) { insertSubrecord(index); }
public boolean remove() { synchronized(mutex) { return c.remove(); } }
public static TokenStream Create() { return new DoubleMetaphoneFilter(); }
public long getLength() { return InCoreLength(); }
public void setValue() { value newValue ; }
public class Pair { private ContentSource oldSource; private ContentSource newSource; public Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; } }
public int get(int i) { if (i < 0 || i >= entries.length) { throw new ArrayIndexOutOfBoundsException(); } return entries[i]; }
public CreateRepoRequest() { this(" ", " ", " ", " ", " "); this.UriPattern = null; this.Method = MethodType.PUT; }
boolean IsDeltaBaseAsOffset() { }
public void remove() { if (expectedModCount == list.modCount) { if (lastLink != null) { java.util.LinkedList.Link<ET> next_1 = lastLink.next; java.util.LinkedList.Link<ET> previous_1 = lastLink.previous; next_1.previous = previous_1; previous_1.next = next_1; if (lastLink == link) { pos--; } link = previous_1; lastLink = null; expectedModCount++; list._size--; list.modCount++; } else { throw new RuntimeException(); } } else { throw new ConcurrentModificationException(); } }
public MergeShardsResponse mergeShards(MergeShardsRequest request) { options = new InvokeOptions(); options.setRequestMarshaller(MergeShardsRequestMarshaller.Instance); options.setResponseUnmarshaller(MergeShardsResponseUnmarshaller.Instance); return null; }
public AllocateHostedConnectionResponse allocateHostedConnection(AllocateHostedConnectionRequest request) { options = new InvokeOptions(); options.setRequestMarshaller(RequestMarshaller.getInstance()); options.setResponseUnmarshaller(AllocateHostedConnectionResponseUnmarshaller.getInstance()); return invoke(AllocateHostedConnectionResponse.class, options); }
public int getBeginIndex() { return start; }
WeightedTerm GetTerms(Query query) { return GetTerms( , ); }
public ByteBuffer compact() { throw new ReadOnlyBufferException(); }
public void decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (byte0 >> 2); int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 63; } }
public String getHumanishName(){if(getPath()==null||getPath().isEmpty()){throw new IllegalArgumentException();}String s=getPath();String[]elements;if(s.contains(" ")||LOCAL_FILE.matcher(s).matches()){elements=s.split(Pattern.quote(String.valueOf(FilePath.separatorChar)));}else{elements=s.split(" ");}if(elements.length==0){throw new IllegalArgumentException();}String result=elements[elements.length-1];if(Constants.DOT_GIT.equals(result)){result=elements[elements.length-2];}else if(result.endsWith(Constants.DOT_GIT_EXT)){result=result.substring(0,result.length()-Constants.DOT_GIT_EXT.length());}return result;}
public DescribeNotebookInstanceLifecycleConfigResult describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { request = beforeClientExecution(request); return executeDescribeNotebookInstanceLifecycleConfig(request); }
public String getAccessKeySecret() { return AccessSecret; }
public CreateVpnConnectionResponse createVpnConnection(CreateVpnConnectionRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(CreateVpnConnectionRequestMarshaller.INSTANCE); options.setResponseUnmarshaller(CreateVpnConnectionResponseUnmarshaller.INSTANCE); return invoke(request, options); }
public DescribeVoicesResponse describeVoices(DescribeVoicesRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeVoicesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeVoicesResponseUnmarshaller.getInstance()); return invoke(options); }
public ListMonitoringExecutionsResponse listMonitoringExecutions(ListMonitoringExecutionsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(RequestMarshaller); options.setResponseUnmarshaller(ListMonitoringExecutionsResponseUnmarshaller.getInstance()); return invoke(request, options); }
public DescribeJobRequest(String vaultName, String jobId) { _vaultName = vaultName; _jobId = jobId; }
public EscherRecord getEscherRecord() { return escherRecords[0]; }
public GetApisResponse getApis(GetApisRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller; options.responseUnmarshaller = GetApisResponseUnmarshaller.INSTANCE; return invoke(request, options); }
public DeleteSmsChannelResponse deleteSmsChannel(DeleteSmsChannelRequest request) { options = new InvokeOptions(); options.RequestMarshaller; options.ResponseUnmarshaller = DeleteSmsChannelResponseUnmarshaller.Instance; return Invoke.<DeleteSmsChannelResponse>invoke(null, null); }
TrackingRefUpdate getTrackingRefUpdate() { }
public void print(boolean b) { print(); }
public IQueryNode getChild() { return getChildren(); }
public NotIgnoredFilter(int workdirTreeIndex) { this.index = workdirTreeIndex; }
public AreaRecord(RecordInputStream in1) { field_1_formatFlags = in1.readShort(); }
GetThumbnailRequest() { super(" ", " ", " ", " ", " "); Protocol; }
public DescribeTransitGatewayVpcAttachmentsResponse describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { options = new InvokeOptions(); DescribeTransitGatewayVpcAttachmentsRequestMarshaller.INSTANCE; options.responseUnmarshaller = DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.INSTANCE; return invoke(DescribeTransitGatewayVpcAttachmentsResponse.class, request, options); }
public PutVoiceConnectorStreamingConfigurationResponse PutVoiceConnectorStreamingConfiguration(){options=new InvokeOptions();options.setRequestMarshaller(PutVoiceConnectorStreamingConfigurationRequestMarshaller.INSTANCE);options.setResponseUnmarshaller(PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.INSTANCE);return Invoke<PutVoiceConnectorStreamingConfigurationResponse>( , );}
public OrdRange getOrdRange(String dim) { return prefixToOrdRange.get(dim); }
public String toString() { String symbol = ""; if (inputStream != null && startIndex < ((ICharStream)inputStream).size()) { symbol = ((ICharStream)inputStream).getText(Interval.of(startIndex, endIndex)); symbol = Utils.escapeWhitespace(symbol); } return String.format("%s", Antlr4.Runtime.LexerNoViableAltException.class.getSimpleName()); }
public E peek() { return peekFirstImpl(); }
public CreateWorkspacesResponse createWorkspaces(CreateWorkspacesRequest request) { options = new InvokeOptions(); options.setRequestMarshaller(CreateWorkspacesRequestMarshaller.INSTANCE); options.setResponseUnmarshaller(CreateWorkspacesResponseUnmarshaller.INSTANCE); return invoke(CreateWorkspacesResponse.class, null, null); }
public Object clone() { NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = this.field_1_formatIndex; return rec; }
public DescribeRepositoriesResponse DescribeRepositories(DescribeRepositoriesRequest request) { options = new InvokeOptions(); options.RequestMarshaller; options.ResponseUnmarshaller = DescribeRepositoriesResponseUnmarshaller.Instance; return Invoke<DescribeRepositoriesResponse>(request, options); }
public SparseIntArray(int initialCapacity) { initialCapacity = Utils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
TokenStream Create() { return new HyphenatedWordsFilter(); }
public CreateDistributionWithTagsResponse createDistributionWithTags(CreateDistributionWithTagsRequest request) { options = new InvokeOptions(); options.RequestMarshaller = CreateDistributionWithTagsRequestMarshaller.Instance; options.ResponseUnmarshaller; return invoke(options, CreateDistributionWithTagsResponse.class); }
public RandomAccessFile(String fileName, String mode) { super(new File(), ); throw new RuntimeException(); }
public DeleteWorkspaceImageResponse DeleteWorkspaceImage(DeleteWorkspaceImageRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.getInstance()); return invoke(request, options); }
public String toHex() { return toHex((long) value); }
public UpdateDistributionResponse UpdateDistribution(UpdateDistributionRequest request) { return invoke(UpdateDistributionRequestMarshaller.INSTANCE.marshall(request), UpdateDistributionResponseUnmarshaller.INSTANCE); }
public HSSFColor getColor(short index){if(index==.Index)return HSSFColor.Automatic.getInstance();else{byte[]b=palette.getColor();if(b!=null){return new CustomColor();}}return null;}
public ValueEval evaluate(ValueEval operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(); }
public void serialize(ILittleEndianOutput out1) { out1.writeShort((short) field_1_number_crn_records); ((short) field_2_sheet_table_index); }
public DescribeDBEngineVersionsResult describeDBEngineVersions() { return describeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
public FormatRun(short character, short fontIndex) { this._character = character; this._fontIndex = fontIndex; }
public byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) {byte[] result = new byte[length * 2];int end = offset + length;int resultIndex = 0;for (int i = offset; i < end; i++) {char ch = chars[i];result[resultIndex++] = (byte) (ch >> 8);result[resultIndex++] = (byte) ch;}return result;}
public UploadArchiveResult uploadArchive(UploadArchiveRequest request) { request = beforeClientExecution(request); return executeUploadArchive(request); }
public List getHiddenTokensToLeft(int tokenIndex) { return executeGetHiddenTokensToLeft(null, -1); }
public boolean equals(Object obj) { if (this == obj) return true; if (!super.equals(obj)) return false; if (getClass() != obj.getClass()) return false; AutomatonQuery other = (AutomatonQuery) obj; if (!m_compiled.equals(other.m_compiled)) return false; if (m_term == null) { if (other.m_term != null) return false; } else if (!m_term.equals(other.m_term)) return false; return true; }
public SpanQuery makeSpanClause() { List<SpanQuery> spanQueries = new ArrayList<>(); for (Map.Entry<SpanQuery, ?> wsq : weightBySpanQuery.entrySet()) { wsq.getKey().Boost; spanQueries.add(wsq.getKey()); } if (spanQueries.size() == 1) return spanQueries.get(0); else return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); }
public StashCreateCommand stashCreate() { return new StashCreateCommand(); }
public FieldInfo FieldInfo(String fieldName) { return byName.get(fieldName); }
public DescribeEventSourceResponse DescribeEventSource(DescribeEventSourceRequest request) { options = new InvokeOptions(); options.setRequestMarshaller(DescribeEventSourceRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeEventSourceResponseUnmarshaller.getInstance()); return invoke(request, options); }
public GetDocumentAnalysisResponse GetDocumentAnalysis(GetDocumentAnalysisRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetDocumentAnalysisRequestMarshaller.INSTANCE); options.setResponseUnmarshaller(GetDocumentAnalysisResponseUnmarshaller.INSTANCE); return invoke(request, GetDocumentAnalysisResponse.class); }
public CancelUpdateStackResponse cancelUpdateStack(CancelUpdateStackRequest request) { InvokeOptions options = new InvokeOptions(); CancelUpdateStackRequestMarshaller.INSTANCE; options.setResponseUnmarshaller(CancelUpdateStackResponseUnmarshaller.INSTANCE); return invoke(CancelUpdateStackResponse.class, options); }
public ModifyLoadBalancerAttributesResponse ModifyLoadBalancerAttributes() { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ModifyLoadBalancerAttributesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance()); return invoke(ModifyLoadBalancerAttributesResponse.class, options); }
public SetInstanceProtectionResponse setInstanceProtection(SetInstanceProtectionRequest request) { options = new InvokeOptions(); options.setRequestMarshaller(SetInstanceProtectionRequestMarshaller.Instance); options.setResponseUnmarshaller(SetInstanceProtectionResponseUnmarshaller.Instance); return invoke(options); }
public ModifyDBProxyResponse modifyDBProxy(ModifyDBProxyRequest request) { options = new InvokeOptions(); options.setRequestMarshaller(ModifyDBProxyRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ModifyDBProxyResponseUnmarshaller.getInstance()); return null; }
public void add(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.length) { outputs = Arrays.copyOf(outputs, ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_OBJECT_REF)); } if (count == endOffsets.length) { endOffsets = Arrays.copyOf(endOffsets, ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_INT32)); } if (count == posLengths.length) { posLengths = Arrays.copyOf(posLengths, ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_INT32)); } if (outputs[count] == null) { outputs[count] = new CharsRef(); } outputs[count].copyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
public FetchLibrariesRequest() { super(" ", " ", " ", " ", " "); Protocol; }
public boolean exists() { return false; }
public FilterOutputStream(OutputStream java) { this.out = java; }
public ScaleClusterRequest() { super(" ", " ", " ", " ", " "); UriPattern = " "; Method = null; }
public IDataValidationConstraint createTimeConstraint(int operatorType, String formula2) { return DVConstraint.createTimeConstraint(null, null, null); }
public ListObjectParentPathsResponse listObjectParentPaths() { Request<ListObjectParentPathsRequest> request = new DefaultRequest<>(new ListObjectParentPathsRequest(), "AmazonCloudFront"); request.setMarshaller(ListObjectParentPathsRequestMarshaller.getInstance()); return execute(request, ListObjectParentPathsResponseUnmarshaller.getInstance()); }
public final DescribeCacheSubnetGroupsResponse describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { options = new InvokeOptions(); options.setRequestMarshaller(DescribeCacheSubnetGroupsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance()); return invoke(DescribeCacheSubnetGroupsResponse.class, request, options); }
void setSharedFormula(boolean flag) { field_5_options(); }
boolean IsReuseObjects() { }
public IErrorNode addErrorNode(IToken badToken) { ErrorNodeImpl t = new ErrorNodeImpl(); addChild(); t.getParent(); }
public LatvianStemFilterFactory(Map<String, String> args) { super(args); if (args.size() > 0) throw new IllegalArgumentException(" " + args); }
public RemoveSourceIdentifierFromSubscriptionResponse removeSourceIdentifierFromSubscription() { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(RemoveSourceIdentifierFromSubscriptionRequestMarshaller.Instance); options.setResponseUnmarshaller(RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.Instance); return invoke(options, RemoveSourceIdentifierFromSubscriptionResponse.class); }
public TokenFilterFactory forName(Map<String, String> args) { return loader.newInstance(); }
public AddAlbumPhotosRequestResult addAlbumPhotosRequest(AddAlbumPhotosRequest request) { request = beforeClientExecution(request); return executeAddAlbumPhotosRequest(request); }
public GetThreatIntelSetResponse getThreatIntelSet() { options = new InvokeOptions(); options.RequestMarshaller = GetThreatIntelSetRequestMarshaller.Instance; options.ResponseUnmarshaller = GetThreatIntelSetResponseUnmarshaller.Instance; return Invoke<GetThreatIntelSetResponse>(null, null); }
TreeFilter clone() { return new AndTreeFilter.Binary(() -> {}, b.clone()); }
boolean Equals() { return o instanceof Object; }
public boolean hasArray() { return protectedHasArray; }
public UpdateContributorInsightsResponse updateContributorInsights(UpdateContributorInsightsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(UpdateContributorInsightsRequestMarshaller.Instance); options.setResponseUnmarshaller(UpdateContributorInsightsResponseUnmarshaller.Instance); return invoke(UpdateContributorInsightsResponse.class); }
public void unwriteProtectWorkbook() { records.remove(); fileShare = null; writeProtect = null; }
public SolrSynonymParser(boolean dedup, Analyzer analyzer) { super(dedup, analyzer); this.expand = expand; }
public RequestSpotInstancesResult requestSpotInstances(RequestSpotInstancesRequest request) {request = beforeClientExecution(request);return executeRequestSpotInstances(request);}
public byte getObjectData() { return findObjectRecord().ObjectData; }
public GetContactAttributesResult getContactAttributes(GetContactAttributesRequest request) { request = beforeClientExecution(request); return executeGetContactAttributes(request); }
public String toString() { return GetKey() + GetValue(); }
public ListTextTranslationJobsResult listTextTranslationJobs() { return executeListTextTranslationJobs(); }
public GetContactMethodsResult getContactMethods(GetContactMethodsRequest request){request = beforeClientExecution(request);return executeGetContactMethods(request);}
public short lookupIndexByName(String name) { FunctionMetadata fd = FunctionMetadata.getInstance().getFunctionByNameInternal(name); if (fd == null) { return -1; } return (short) 0; }
public DescribeAnomalyDetectorsResponse describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { return describeAnomalyDetectorsWithServiceResponseAsync(request).toBlocking().single().body(); }
String insertId(ObjectId changeId) { return insertId(null, null, null); }
public long getObjectSize(AnyObjectId objectId, int typeHint) { long sz = db.getObjectSize(); if (sz < 0) { if (typeHint == OBJ_ANY) { throw new MissingObjectException(" "); } throw new MissingObjectException(objectId.copy()); } return sz; }
public ImportInstallationMediaResponse ImportInstallationMedia(ImportInstallationMediaRequest request) { options = new InvokeOptions(); options.setRequestMarshaller(ImportInstallationMediaRequestMarshaller.INSTANCE); options.setResponseUnmarshaller(ImportInstallationMediaResponseUnmarshaller.INSTANCE); return null; }
public PutLifecycleEventHookExecutionStatusResult putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) {request = beforeClientExecution(request);return executePutLifecycleEventHookExecutionStatus(request);}
public NumberPtg(ILittleEndianInput in1) { field_1_value(); }
public GetFieldLevelEncryptionConfigResponse getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.INSTANCE); options.setResponseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.INSTANCE); return invoke(request, options); }
public DescribeDetectorResponse DescribeDetector(DescribeDetectorRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeDetectorRequestMarshaller.INSTANCE); options.setResponseUnmarshaller(DescribeDetectorResponseUnmarshaller.INSTANCE); return invoke(request, options); }
public ReportInstanceStatusResponse reportInstanceStatus(ReportInstanceStatusRequest request) throws IOException { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ReportInstanceStatusRequestMarshaller.INSTANCE); options.setResponseUnmarshaller(ReportInstanceStatusResponseUnmarshaller.INSTANCE); return (ReportInstanceStatusResponse) invoke(request, options); }
public DeleteAlarmResult deleteAlarm(DeleteAlarmRequest request) { request = beforeClientExecution(request); return executeDeleteAlarm(request); }
TokenStream create() { return new PortugueseStemFilter(); }
public FtCblsSubRecord() { this.reserved = new byte[0]; }
public boolean remove() { synchronized (mutex) { return c.remove(); } }
public GetDedicatedIpResponse getDedicatedIp(GetDedicatedIpRequest request) { options = new InvokeOptions(); options.setRequestMarshaller(); options.setResponseUnmarshaller(GetDedicatedIpResponseUnmarshaller.getInstance()); return invoke(GetDedicatedIpResponse.class); }
public String toString() { return precedence + " "; }
public ListStreamProcessorsResult listStreamProcessors() { return executeListStreamProcessors(); }
public DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) { this._loadBalancerName = loadBalancerName; this._policyName = policyName; }
public WindowProtectRecord(int options) { this._options = options; }
public UnbufferedCharStream(int bufferSize) { n = 0; data = new int[bufferSize]; }
public GetOperationsResponse getOperations(GetOperationsRequest request) { options = new InvokeOptions(); options.RequestMarshaller = GetOperationsRequestMarshaller.Instance; options.ResponseUnmarshaller = GetOperationsResponseUnmarshaller.Instance; return invoke(request, options); }
public void copyRawTo(byte[] b) { NB.encodeInt32(, , ); NB.encodeInt32(, o + 4, ); NB.encodeInt32(, o + 8, ); NB.encodeInt32(, o + 12, ); NB.encodeInt32(, o + 16, ); }
WindowOneRecord(RecordInputStream in1) { field_1_h_hold = in1.readShort(); field_2_v_hold = in1.readShort(); field_3_width = in1.readShort(); field_4_height = in1.readShort(); field_5_options = in1.readShort(); field_6_active_sheet = in1.readShort(); field_7_first_visible_tab = in1.readShort(); field_8_num_selected_tabs = in1.readShort(); field_9_tab_width_ratio = in1.readShort(); }
public StopWorkspacesResult stopWorkspaces(StopWorkspacesRequest request) {request = beforeClientExecution(request);return executeStopWorkspaces(request);}
public void close() throws IOException { if (isOpen) { isOpen; try { dump(); } finally { try { channel.truncate(); } finally { try { channel.close(); } finally { (); } } } } }
public DescribeMatchmakingRuleSetsResponse describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { options = new InvokeOptions(); options.RequestMarshaller; options.ResponseUnmarshaller = DescribeMatchmakingRuleSetsResponseUnmarshaller.Instance; return invoke(DescribeMatchmakingRuleSetsResponse.class); }
private String getPronunciation(int wordId, char surface, int off, int len) { return null; }
public String getPath() { return pathStr; }
public static double devsq(double[] v) {double r=Double.NaN;if(v!=null&&v.length>=1){double m=0,s=0;int n=v.length;for(int i=0;i<n;i++)s+=v[i];m=s/n;s=0;for(int i=0;i<n;i++)s+=(v[i]-m)*(v[i]-m);r=(n==1)?0:s;}return r;}
public DescribeResizeResult describeResize(DescribeResizeRequest request) { request = beforeClientExecution(request); return executeDescribeResize(request); }
public boolean getHasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
public int end() { return end(); }
public void traverse(ICellHandler handler){int firstRow=range.getFirstRow();int lastRow=range.getLastRow();int firstColumn=range.getFirstColumn();int lastColumn=range.getLastColumn();int width=lastColumn-firstColumn+1;SimpleCellWalkContext ctx=new SimpleCellWalkContext();IRow currentRow=null;ICell currentCell=null;for(ctx.setRowNumber(firstRow);ctx.getRowNumber()<=lastRow;ctx.setRowNumber(ctx.getRowNumber()+1)){currentRow=sheet.getRow(ctx.getRowNumber());if(currentRow==null){continue;}for(ctx.setColNumber(firstColumn);ctx.getColNumber()<=lastColumn;ctx.setColNumber(ctx.getColNumber()+1)){currentCell=currentRow.getCell(ctx.getColNumber());if(currentCell==null){continue;}if(isEmpty()&&!traverseEmptyCells){continue;}(ctx.getRowNumber()-firstRow)*width+(ctx.getColNumber()-firstColumn+1);handler.onCell(null,null);}}}
public int getReadIndex() { return _ReadIndex; }
public int compareTo(ScoreTerm other) { if (Term.bytesEquals(other.Term)) { return 0; } if (this.Boost.equals(other.Boost)) { return this.Term.compareTo(other.Term); } else { return this.Boost.compareTo(other.Boost); } }
public int normalize(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = StemmerUtil.delete(s, i, len); i--; break; default: break; } } return len; }
public void serialize(LittleEndianOutput out1) { }
public DiagnosticErrorListener(boolean exactOnly) {this.exactOnly = exactOnly;}
public KeySchemaElement(String attributeName, KeyType keyType) { _attributeName = attributeName; _keyType = keyType; }
public GetAssignmentResponse getAssignment(GetAssignmentRequest request) { options = new InvokeOptions(); options.setRequestMarshaller(GetAssignmentRequestMarshaller.INSTANCE); options.setResponseUnmarshaller(GetAssignmentResponseUnmarshaller.INSTANCE); return invoke(options, request); }
public boolean hasObject() { return findOffset() != -1; }
public GroupingSearch setAllGroups() { allGroups allGroups; return null; }
public void setMultiValued(String dimName, boolean v) { synchronized (this) { if (!fieldTypes.containsKey(dimName)) { fieldTypes.put(dimName, new DimConfig().setIsMultiValued(v)); } else { fieldTypes.get(dimName).setIsMultiValued(v); } } }
private int getCellsVal() { int size = 0; for (Character c : cells.keySet()) { Cell e = at(); if (e.cmd >= 0) {} } return size; }
public DeleteVoiceConnectorResult deleteVoiceConnector(DeleteVoiceConnectorRequest request) {request = beforeClientExecution(request);return executeDeleteVoiceConnector(request);}
public DeleteLifecyclePolicyResponse deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteLifecyclePolicyRequestMarshaller.INSTANCE); options.setResponseUnmarshaller(DeleteLifecyclePolicyResponseUnmarshaller.INSTANCE); return null; }
