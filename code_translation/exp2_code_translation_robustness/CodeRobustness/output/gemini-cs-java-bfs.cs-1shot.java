void serialize(ILittleEndianOutput out1) { out1.writeShort(); }
public void addAll(BlockList<T> src) { if (src.size() == 0) return; for (int srcDirIdx = 0; srcDirIdx < src.tailDirIdx; srcDirIdx++) addAll((BlockList<T>) src.directory[srcDirIdx]); if (src.tailBlkIdx != 0) addAll(src.tailBlock, 0, src.tailBlkIdx); }
void writeByte(byte b) { if (upto == blockSize) { blockEnd = upto; blocks.add(currentBlock); currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
public ObjectId getObjectId() { return objectId; }
public DeleteDomainEntryResult deleteDomainEntry(DeleteDomainEntryRequest request) {request = beforeClientExecution(request);return executeDeleteDomainEntry(request);}
public long ramBytesUsed() { return fst == null ? 0 : fst.getSizeInBytes(); }
public String getFullMessage() { byte[] raw = buffer.getBuffer(); String msgB = ""; int tag = RawParseUtils.tagMessage(raw, raw.length); if (tag < 0) { return msgB; } java.nio.charset.Charset enc = RawParseUtils.parseEncoding(raw); msgB = RawParseUtils.decode(enc, raw, tag, raw.length); return msgB; }
public POIFSFileSystem() { headerBlock = new HeaderBlock(); _property_table = new PropertyTable(headerBlock); _documents = new ArrayList(); _root = null; }
assert address >> ByteBlockPool.BYTE_BLOCK_SHIFT < pool.buffers.length; assert (address & ByteBlockPool.BYTE_BLOCK_MASK) < upto;
public SubmoduleAddCommand setPath(String path) { this.path = path; return this; }
public ListIngestionsResponse listIngestions(ListIngestionsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListIngestionsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ListIngestionsResponseUnmarshaller.getInstance()); return invoke(request, options); }
public QueryParserTokenManager(CharStream stream, int lexState){this(stream);SwitchTo(lexState);}
public GetShardIteratorResponse getShardIterator(GetShardIteratorRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetShardIteratorRequestMarshaller.instance); options.setResponseUnmarshaller(GetShardIteratorResponseUnmarshaller.instance); return invoke(request, options); }
} { : ) ( ModifyStrategyRequest ; Method ) , , , , ( POST . MethodType " " " " " " " " " "
ready boolean } { ) ( ) lock ( synchronized } { try ) ( if catch } { } { null == in } { ) ( ; return ; throw ; return IOException . || new io . java 0 > ) ( IOException . ) ( hasRemaining . bytes " " io . java ) ( available . in
public EscherOptRecord getOptRecord() { return _optRecord; }
public synchronized int read(byte[] b, int off, int len) { if (b == null) { throw new NullPointerException(); } else if (off < 0 || len < 0 || len > b.length - off) { throw new IndexOutOfBoundsException(); } if (pos >= count) { return 0; } int copylen = Math.min(len, count - pos); System.arraycopy(buf, pos, b, off, copylen); pos += copylen; return copylen; }
sentenceOp.NLPSentenceDetectorOp(sentenceOp, new OpenNLPSentenceBreakIterator());
void print(String str) { write(str != null ? str : "null"); }
public NotImplementedFunctionException(String functionName, Throwable cause) { super(functionName, cause); }
return nextEntry.getValue();
public final void readFully(byte[] b, int off, int len) throws IOException { if (len < 0) throw new IndexOutOfBoundsException(); int n = 0; while (n < len) { int count = in.read(b, off + n, len - n); if (count < 0) throw new EOFException(); n += count; } }
public TagQueueResponse tagQueue(TagQueueRequest request){return invoke(request, new InvokeOptions().withRequestMarshaller(TagQueueRequestMarshaller.getInstance()).withResponseUnmarshaller(TagQueueResponseUnmarshaller.getInstance()));}
public void remove() { throw new UnsupportedOperationException(); }
public ModifyCacheSubnetGroupResponse modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = ModifyCacheSubnetGroupRequestMarshaller.instance; options.responseUnmarshaller = ModifyCacheSubnetGroupResponseUnmarshaller.instance; return invoke(request, options); }
SetParams void } { ) ( ) ( if ) ( if ) ( if ; ; ; ; params string ; ; ; StringTokenizer string culture ignore ) ( MoveNext . st culture ) ( MoveNext . st culture ) ( MoveNext . st st " " ) ( SetParams . Current . st + Current . st = Current . st " " StringTokenizer new ) , ( " "
return invoke<DeleteDocumentationVersionResponse>(request, new InvokeOptions().withRequestMarshaller(DeleteDocumentationVersionRequestMarshaller.getInstance()).withResponseMarshaller(DeleteDocumentationVersionResponseUnmarshaller.getInstance()));
public boolean equals(Object obj) { if (this == obj) return true; if (!(obj instanceof FacetLabel)) return false; FacetLabel other = (FacetLabel) obj; return java.util.Arrays.equals(components, other.components); }
public GetInstanceAccessDetailsResponse getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetInstanceAccessDetailsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetInstanceAccessDetailsResponseUnmarshaller.getInstance()); return invoke(request, options); }
public HSSFPolygon createPolygon(HSSFClientAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(null, anchor); shape.setParent(this); shape.setAnchor(anchor); addShape(shape); return shape; }
String getSheetName(int sheetIndex) { return getBoundSheetRec().getSheetname(); }
GetDashboardResponse getDashboard(GetDashboardRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetDashboardRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetDashboardResponseUnmarshaller.getInstance()); return invoke(request, options); }
public AssociateSigninDelegateGroupsWithAccountResponse associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(AssociateSigninDelegateGroupsWithAccountRequestMarshaller.getInstance()); options.setResponseUnmarshaller(AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.getInstance()); return invoke(request, options); }
void addMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < mbr.getNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.setRow(mbr.getRow()); br.setColumn(mbr.getFirstColumn() + j); br.setXfIndex(mbr.getXfAt(j)); insertCell(br); } }
return "\"" + string.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
java.nio.ByteBuffer putInt(int value) { throw new java.nio.ReadOnlyBufferException(); }
for (int r = 0; r < nRows; r++) { for (int c = 0; c < nColumns; c++) { Object vv = values2d[r][c]; _arrayValues[r][c] = vv; } }
public GetIceServerConfigResponse getIceServerConfig(GetIceServerConfigRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetIceServerConfigRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetIceServerConfigResponseUnmarshaller.getInstance()); return invoke(request, options); }
@Override public String toString() { return new StringBuilder().append(getClass().getSimpleName()).append(" = ").append(getValueAsString()).append(" ").toString(); }
public String toString() { return field + " " + _parentQuery + " " + string; }
void incRef() { refCount.incrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResponse updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(UpdateConfigurationSetSendingEnabledRequestMarshaller.getInstance()); options.setResponseUnmarshaller(UpdateConfigurationSetSendingEnabledResponseUnmarshaller.getInstance()); return invoke(request, options); }
int getNextXBATChainOffset() { return getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE; }
void multiplyByPowerOfTen(int pow10) { if (pow10 > 0) { TenPower tp = TenPower.getInstance(pow10); mulShift(_multiplicand, tp._multiplicand); mulShift(_multiplierShift, tp._multiplierShift); } else { TenPower tp = TenPower.getInstance(Math.abs(pow10)); mulShift(_divisor, tp._multiplicand); mulShift(_divisorShift, tp._multiplierShift); } }
public String toString() { StringBuilder builder = new StringBuilder(); for (int i = 0; i < length; ++i) { if (i > 0) { builder.append(java.io.File.separatorChar); } builder.append(GetComponent(i)); } return builder.toString(); }
public SetRoleNameResult setRoleName(SetRoleNameRequest request) {request = beforeClientExecution(request);return executeSetRoleName(request);}
public void setProgressMonitor(ProgressMonitor progressMonitor) {}
reset void } { ) ( ) ( if } { first ! ) ( if ; } { eof ! 0 ptr ; parseEntry ) (
E previous() { if (start >= iterator.previousIndex) { throw new java.util.NoSuchElementException(); } return iterator.previous(); }
String GetNewPrefix() { return newPrefix; }
indexOfValue int } { ) ( ; return } { value int 1 - ) ; ; ( for } { ++ i mSize < i int ) ( if i } { value == 0 = ; i return mValues ] [
private List<CharsRef> uniqueStems(List<CharsRef> stems) { if (stems.size() < 2) { return stems; } List<CharsRef> deduped = new ArrayList<>(); CharArraySet terms = new CharArraySet(8, dictionary.ignoreCase); for (CharsRef s : stems) { if (!terms.contains(s)) { deduped.add(s); terms.add(s); } } return deduped; }
public GetGatewayResponsesResponse getGatewayResponses(GetGatewayResponsesRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetGatewayResponsesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetGatewayResponsesResponseUnmarshaller.getInstance()); return invoke(request, options); }
void setPosition(long position) { currentBlockIndex = (int) (position >> outerInstance.blockBits); currentBlock = outerInstance.blocks[currentBlockIndex]; currentBlockUpto = (int) (position & outerInstance.blockMask); }
return Math.max(0, (int) Math.min(n, available - ptr));
BootstrapActionConfig(BootstrapActionDetail bootstrapActionConfig) { _bootstrapActionConfig = bootstrapActionConfig; }
void serialize(LittleEndianOutput out1) { out1.writeShort(0); out1.writeShort(0); out1.writeShort(0); out1.writeShort(0); out1.writeShort(0); out1.writeByte(field_6_author.length()); out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00); if (field_5_hasMultibyte) StringUtil.putUnicodeLE(field_6_author, out1); else StringUtil.putCompressedUnicode(field_6_author, out1); if (field_7_padding != null) {} }
int lastIndexOf(String string) { return string.lastIndexOf(','); }
boolean addLastImpl(E object) { return add(); }
public void unsetSection(String section, String subsection) { ConfigSnapshot src, res; do { src = state.get(); res = src.unsetSection(section, subsection); } while (!state.compareAndSet(src, res)); }
public String getTagName() { return tagName; }
void addSubRecord(int index, SubRecord element) { subrecords.add(index, element); }
public boolean remove(Object object) { synchronized (mutex) { return c.remove(object); } }
public TokenStream create(TokenStream input) { return new DoubleMetaphoneFilter(input); }
public long Length() { return InCoreLength(); }
public void setValue(boolean newValue) { this.value = newValue; }
new Pair(newSource, oldSource)
public int get(int i) { if (i >= count) throw new IndexOutOfBoundsException(); return entries[i]; }
} { : ) ( CreateRepoRequest ; ; method uriPattern ) , , , , ( PUT . MethodType " " " " " " " " " " " "
public boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public void remove() { checkForComodification(); if (lastReturned == null) throw new IllegalStateException(); Node<E> lastNext = lastReturned.next; unlink(lastReturned); if (next == lastReturned) next = lastNext; else nextIndex--; lastReturned = null; expectedModCount++; }
return invoke<MergeShardsRequest, MergeShardsResponse>(request, MergeShardsRequestMarshaller.instance, MergeShardsResponseUnmarshaller.instance);
public AllocateHostedConnectionResponse allocateHostedConnection(AllocateHostedConnectionRequest request) { InvokeOptions options = new InvokeOptions(); return invoke(new AllocateHostedConnectionRequestMarshaller(), new AllocateHostedConnectionResponseUnmarshaller(), options).getAwsResponse(); }
int getBeginIndex() { return start; }
public WeightedTerm[] getTerms(Query query) { return executeGetTerms(query); }
java.nio.ByteBuffer compact() { throw new java.nio.ReadOnlyBufferException(); }
public void decode(int iterations, int[] values, int valuesOffset, byte[] blocks, int blocksOffset) { for (int i = 0; i < iterations; i++) { int byte0; int byte1; int byte2; byte0 = ((blocks[blocksOffset++] & 0xFF) >>> 6) & 3; byte1 = ((blocks[blocksOffset++] & 0xFF) >>> 4) & 15; byte2 = blocks[blocksOffset++] & 63; values[valuesOffset++] = (byte0 << 4) | ((byte1 & 0xFF) >>> 2); values[valuesOffset++] = ((byte1 & 0xFF) << 2) | byte2; values[valuesOffset++] = byte0 | byte1 | byte2; } }
public String getHumanishName() { String p = getPath(); if (".".equals(p)) { p = userHome.getPath(); } String[] elements = p.split(java.util.regex.Pattern.quote(java.io.File.separator)); String result = elements[elements.length - 1]; if (result.isEmpty() && elements.length > 1) { result = elements[elements.length - 2]; } if (org.eclipse.jgit.lib.Constants.DOT_GIT.equals(result)) { return ".git"; } if (result.endsWith(org.eclipse.jgit.lib.Constants.DOT_GIT_EXT)) { return result.substring(0, result.length() - org.eclipse.jgit.lib.Constants.DOT_GIT_EXT.length()); } return result; }
public DescribeNotebookInstanceLifecycleConfigResponse DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = DescribeNotebookInstanceLifecycleConfigRequestMarshaller.Instance; options.ResponseUnmarshaller = DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.Instance; return Invoke(request, options); }
public String getAccessKeySecret() { return accessSecret; }
public CreateVpnConnectionResponse CreateVpnConnection(CreateVpnConnectionRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = CreateVpnConnectionRequestMarshaller.Instance; options.ResponseUnmarshaller = CreateVpnConnectionResponseUnmarshaller.Instance; return this.<CreateVpnConnectionResponse, CreateVpnConnectionRequest>Invoke(request, options); }
public DescribeVoicesResult describeVoices(DescribeVoicesRequest request) {request = beforeClientExecution(request);return executeDescribeVoices(request);}
public ListMonitoringExecutionsResponse listMonitoringExecutions(ListMonitoringExecutionsRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = ListMonitoringExecutionsRequestMarshaller.instance; options.responseUnmarshaller = ListMonitoringExecutionsResponseUnmarshaller.instance; return invoke(request, options); }
public DescribeJobRequest(String vaultName, String jobId) { this._vaultName = vaultName; this._jobId = jobId; }
public EscherRecord getEscherRecord(int index) { return escherRecords[index]; }
public GetApisResponse getApis(GetApisRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetApisRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetApisResponseUnmarshaller.getInstance()); return invoke(request, options); }
public DeleteSmsChannelResponse DeleteSmsChannel(DeleteSmsChannelRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteSmsChannelRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteSmsChannelResponseUnmarshaller.getInstance()); return invoke(request, options); }
public TrackingRefUpdate getTrackingRefUpdate() { return trackingRefUpdate; }
void print(boolean b) { print(Boolean.toString(b)); }
IQueryNode[] getChild() { return getChildren(); }
} {  ; NotIgnoredFilter workdirTreeIndex ) workdirTreeIndex int ( index .
field_1_formatFlags = in1.readShort();
} { extends ) ( GetThumbnailRequest ; Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " "
public DescribeTransitGatewayVpcAttachmentsResponse describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeTransitGatewayVpcAttachmentsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance()); return invoke(request, options); }
public PutVoiceConnectorStreamingConfigurationResult putVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) {request = beforeClientExecution(request);return executePutVoiceConnectorStreamingConfiguration(request);}
public OrdRange getOrdRange(String dim) { return prefixToOrdRange.get(dim); }
toString String } { ) ( ; return ) ( if ; } { && string ) , , , ( String.format ; ; < startIndex 0 >= startIndex symbol .getName() " " Locale.getDefault() symbol symbol .size() = ) ( getClass() ) ( "" LexerNoViableAltException . ) , ( Utils.escapeWhitespace ) ( getInputStream().getText ) CharStream ( org.antlr.v4.runtime ) ( ) , ( Interval.of getInputStream() ) CharStream (
E peek() { return peekFirstImpl(); }
public CreateWorkspacesResponse createWorkspaces(CreateWorkspacesRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(CreateWorkspacesRequestMarshaller.getInstance()); options.setResponseUnmarshaller(CreateWorkspacesResponseUnmarshaller.getInstance()); return invoke(request, options); }
public Object clone() { NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = field_1_formatIndex; return rec; }
public DescribeRepositoriesResponse describeRepositories(DescribeRepositoriesRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeRepositoriesRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeRepositoriesResponseUnmarshaller.getInstance())); }
public SparseIntArray(int initialCapacity) { initialCapacity = com.android.internal.util.ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public TokenStream createTokenStream(TokenStream input) { return new HyphenatedWordsFilter(input); }
public CreateDistributionWithTagsResult createDistributionWithTags(CreateDistributionWithTagsRequest request) {request = beforeClientExecution(request);return executeCreateDistributionWithTags(request);}
public RandomAccessFile(String fileName, String mode) { throw new UnsupportedOperationException(); }
public DeleteWorkspaceImageResponse deleteWorkspaceImage(DeleteWorkspaceImageRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.getInstance()); return invoke(request, options); }
toHex String } { ) ( ; return value int toHex ) , ( value ) long (
public UpdateDistributionResult updateDistribution(UpdateDistributionRequest request) {request = beforeClientExecution(request);return executeUpdateDistribution(request);}
public HSSFColor getColor(short index) { byte[] b = palette.getColor(index); if (b != null) { return new CustomColor(index, b); } else { if (index == HSSFColor.HSSFColorPredefined.AUTOMATIC.getIndex()) { return HSSFColor.HSSFColorPredefined.AUTOMATIC.getColor(); } return null; } }
public ValueEval evaluate(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(); }
public void serialize(LittleEndianOutput out1) { out1.writeShort((short)field_1_number_crn_records); out1.writeShort((short)field_2_sheet_table_index); }
DescribeDBEngineVersionsResponse describeDBEngineVersions() { return describeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
} {  ; ; FormatRun fontIndex character ) fontIndex short , character short ( _fontIndex . _character .
public byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte) (ch >> 8); result[resultIndex++] = (byte) ch; } return result; }
public UploadArchiveResponse uploadArchive(UploadArchiveRequest request) { return this.<UploadArchiveResponse>invoke(request, new InvokeOptions(), UploadArchiveRequestMarshaller.getInstance(), UploadArchiveResponseUnmarshaller.getInstance()); }
List<IToken> getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex - 1); }
@Override public boolean equals(Object obj) { if (this == obj) return true; if (obj == null) return false; if (getClass() != obj.getClass()) return false; AutomatonQuery other = (AutomatonQuery) obj; if (m_term == null) { if (other.m_term != null) return false; } else if (!m_term.equals(other.m_term)) return false; if (!m_compiled.equals(other.m_compiled)) return false; return true; }
if (weightBySpanQuery.size() == 1) { Map.Entry<SpanQuery, Float> wsq = weightBySpanQuery.get(0); wsq.getKey().setBoost(wsq.getValue()); return wsq.getKey(); } else { List<SpanQuery> spanQueries = new ArrayList<>(); for (Map.Entry<SpanQuery, Float> wsq : weightBySpanQuery) { wsq.getKey().setBoost(wsq.getValue()); spanQueries.add(wsq.getKey()); } return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); }
StashCreate StashCreateCommand() { return new StashCreateCommand(); }
return byName.get(fieldName);
public DescribeEventSourceResponse describeEventSource(DescribeEventSourceRequest request) { return this.<DescribeEventSourceResponse>invoke(request, new InvokeOptions().requestMarshaller(DescribeEventSourceRequestMarshaller.getInstance()).responseUnmarshaller(DescribeEventSourceResponseUnmarshaller.getInstance())); }
public GetDocumentAnalysisResult getDocumentAnalysis(GetDocumentAnalysisRequest request) {request = beforeClientExecution(request);return executeGetDocumentAnalysis(request);}
public CancelUpdateStackResponse cancelUpdateStack(CancelUpdateStackRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(CancelUpdateStackRequestMarshaller.getInstance()); options.setResponseUnmarshaller(CancelUpdateStackResponseUnmarshaller.getInstance()); return invoke(request, options); }
public ModifyLoadBalancerAttributesResult modifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { return invoke(request, new ModifyLoadBalancerAttributesRequestMarshaller(), ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance()); }
public SetInstanceProtectionResult setInstanceProtection(SetInstanceProtectionRequest request) {request = beforeClientExecution(request);return executeSetInstanceProtection(request);}
public ModifyDBProxyResponse modifyDBProxy(ModifyDBProxyRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ModifyDBProxyRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ModifyDBProxyResponseUnmarshaller.getInstance()); return invoke(request, options); }
public void add(CharsRef output, int offset, int len, int endOffset, int posLength) { if (outputs == null) { outputs = new CharsRef[1]; endOffsets = new int[1]; posLengths = new int[1]; } if (count == outputs.length) { CharsRef[] next = new CharsRef[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF)]; System.arraycopy(outputs, 0, next, 0, count); outputs = next; } if (count == endOffsets.length) { int[] next = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.length) { int[] next = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(posLengths, 0, next, 0, count); posLengths = next; } outputs[count] = new CharsRef(); outputs[count].copyChars(output.chars, output.offset + offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
} { : ) ( FetchLibrariesRequest ; Protocol ) , , , , ( HTTPS . protocolType " " " " " " " " " "
public boolean exists() { return objects.exists(); }
} {  ; FilterOutputStream out ) java ( out . . .
public ScaleClusterResult scaleCluster(ScaleClusterRequest request) {request = beforeClientExecution(request);return executeScaleCluster(request);}
public IDataValidationConstraint createTimeConstraint(int operatorType, String formula1, String formula2) { return DVConstraint.createTimeConstraint(operatorType, formula1, formula2); }
public ListObjectParentPathsResponse listObjectParentPaths(ListObjectParentPathsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListObjectParentPathsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ListObjectParentPathsResponseUnmarshaller.getInstance()); return invoke(request, options); }
public DescribeCacheSubnetGroupsResult describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) {request = beforeClientExecution(request);return executeDescribeCacheSubnetGroups(request);}
public void setSharedFormula(boolean flag) { field_5_options = sharedFormula.setShortBoolean(field_5_options, flag); }
public final boolean IsReuseObjects() { return reuseObjects; }
public IErrorNode AddErrorNode(IToken badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); Parent.AddChild(t); return t; }
public LatvianStemFilterFactory(Map<String, String> args) { super(args); if (!args.isEmpty()) { throw new IllegalArgumentException("Unknown parameters: " + args); } }
public RemoveSourceIdentifierFromSubscriptionResponse removeSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(RemoveSourceIdentifierFromSubscriptionRequestMarshaller.getInstance()); options.setResponseUnmarshaller(RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.getInstance()); return invoke(request, options); }
public TokenFilterFactory forName(String name, Map<String, String> args){return loader.newInstance(name, args);}
public AddAlbumPhotosResult addAlbumPhotos(AddAlbumPhotosRequest request) { request = beforeClientExecution(request); return executeAddAlbumPhotos(request); }
public GetThreatIntelSetResponse getThreatIntelSet(GetThreatIntelSetRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetThreatIntelSetRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetThreatIntelSetResponseUnmarshaller.getInstance()); return invoke(request, options); }
public TreeFilter clone() { return new Binary.AndTreeFilter(a.clone(), b.clone()); }
public boolean equals(Object o) { return o != null; }
protected boolean hasArray() { return protectedHasArray(); }
public UpdateContributorInsightsResult updateContributorInsights(UpdateContributorInsightsRequest request) {request = beforeClientExecution(request);return executeUpdateContributorInsights(request);}
void unwriteProtectWorkbook() { records.remove(fileShare); records.remove(writeProtect); }
} {  ; SolrSynonymParser expand ) analyzer , dedup ( base : expand . ) analyzer analyzer , expand boolean , dedup boolean (
public RequestSpotInstancesResponse RequestSpotInstances(RequestSpotInstancesRequest request) { return Invoke(request, new InvokeOptions(RequestSpotInstancesRequestMarshaller.Instance, RequestSpotInstancesResponseUnmarshaller.Instance)); }
byte[] getObjectData() { return ObjectData.findObjectRecord(); }
public final GetContactAttributesResponse GetContactAttributes(GetContactAttributesRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = GetContactAttributesRequestMarshaller.Instance; options.ResponseUnmarshaller = GetContactAttributesResponseUnmarshaller.Instance; return this.<GetContactAttributesRequest, GetContactAttributesResponse>Invoke(request, options); }
public String toString() { return GetKey() + " " + GetValue(); }
public ListTextTranslationJobsResult listTextTranslationJobs(ListTextTranslationJobsRequest request) {request = beforeClientExecution(request);return executeListTextTranslationJobs(request);}
public GetContactMethodsResponse getContactMethods(GetContactMethodsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetContactMethodsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetContactMethodsResponseUnmarshaller.getInstance()); return invoke(request, options); }
public short lookupIndexByName(String name) { FunctionMetadata fd = getInstance().getFunctionByNameInternal(name); if (fd == null) { return -1; } return fd.getIndex(); }
public DescribeAnomalyDetectorsResponse describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { return invoke(request, new DescribeAnomalyDetectorsRequestMarshaller(), DescribeAnomalyDetectorsResponseUnmarshaller.getInstance()); }
return insertId(changeId, message, (ObjectId) string.InsertId);
public long getObjectSize(AnyObjectId objectId, int typeHint) throws MissingObjectException { long sz = db.getObjectSize(objectId.copy(), typeHint); if (sz < 0) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.copy(), " "); throw new MissingObjectException(objectId.copy(), typeHint); } return sz; }
public ImportInstallationMediaResponse importInstallationMedia(ImportInstallationMediaRequest request) { InvokeOptions options = new InvokeOptions(); return this.<ImportInstallationMediaResponse>invoke(request, ImportInstallationMediaRequestMarshaller.INSTANCE, ImportInstallationMediaResponseUnmarshaller.INSTANCE, options); }
public PutLifecycleEventHookExecutionStatusResult putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) {request = beforeClientExecution(request);return executePutLifecycleEventHookExecutionStatus(request);}
field_1_value = in1.readDouble();
public GetFieldLevelEncryptionConfigResponse getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance()); return invoke(request, options); }
public DescribeDetectorResult describeDetector(DescribeDetectorRequest request) { return invoke(request, DescribeDetectorRequestMarshaller.getInstance(), new DescribeDetectorResultUnmarshaller()); }
public ReportInstanceStatusResult reportInstanceStatus(ReportInstanceStatusRequest request) {request = beforeClientExecution(request);return executeReportInstanceStatus(request);}
public DeleteAlarmResponse deleteAlarm(DeleteAlarmRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteAlarmRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteAlarmResponseUnmarshaller.getInstance()); return invoke(request, options); }
public TokenStream create(TokenStream input) { return new PortugueseStemFilter(input); }
reserved = new byte[ENCODED_SIZE];
boolean remove(Object object) { synchronized (mutex) { return c.remove(); } }
public GetDedicatedIpResponse getDedicatedIp(GetDedicatedIpRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetDedicatedIpRequestMarshaller.Instance); options.setResponseUnmarshaller(GetDedicatedIpResponseUnmarshaller.Instance); return invoke(request, options); }
String toString() { return precedence + " "; }
public ListStreamProcessorsResult listStreamProcessors(ListStreamProcessorsRequest request) {request = beforeClientExecution(request);return executeListStreamProcessors(request);}
public DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) { this.loadBalancerName = loadBalancerName; this.policyName = policyName; }
public WindowProtectRecord(int options) { this._options = options; }
[ bufferSize ] int new ( int bufferSize ) n 0 data UnbufferedCharStream ; ; { }
public GetOperationsResponse getOperations(GetOperationsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetOperationsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetOperationsResponseUnmarshaller.getInstance()); return invoke(request, options); }
getUnknownFields().writeTo(output);
public WindowOneRecord(RecordInputStream in1) { field_1_h_hold = in1.readShort(); field_2_v_hold = in1.readShort(); field_3_width = in1.readShort(); field_4_height = in1.readShort(); field_5_options = in1.readShort(); field_6_active_sheet = in1.readShort(); field_7_first_visible_tab = in1.readShort(); field_8_num_selected_tabs = in1.readShort(); field_9_tab_width_ratio = in1.readShort(); }
public StopWorkspacesResult stopWorkspaces(StopWorkspacesRequest request) {request = beforeClientExecution(request);return executeStopWorkspaces(request);}
public void close() throws IOException { if (isOpen()) { try { ; } finally { isOpen = false; try { dump(); } finally { try { } finally { channel.truncate(0); channel.close(); fos.close(); } } } } }
public DescribeMatchmakingRuleSetsResponse describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeMatchmakingRuleSetsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeMatchmakingRuleSetsResponseUnmarshaller.getInstance()); return this.<DescribeMatchmakingRuleSetsRequest, DescribeMatchmakingRuleSetsResponse>invoke(request, options); }
public String getPronunciation(int wordId, char[] surface, int off, int len) { return null; }
public String getPath() { return pathStr; }
double devsq(double[] v) { if (v == null || v.length == 0) return Double.NaN; int n = v.length; if (n == 1) return 0; double s = 0; for (int i = 0; i < n; i++) s += v[i]; double m = s / n; double r = 0; for (int i = 0; i < n; i++) r += (v[i] - m) * (v[i] - m); return r; }
public DescribeResizeResponse describeResize(DescribeResizeRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeResizeRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeResizeResponseUnmarshaller.getInstance())); }
public boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
end int } { ) ( ; return end ) (
public void traverse(Sheet sheet, CellRangeAddress range, CellHandler handler) { int firstRow = range.getFirstRow(); int lastRow = range.getLastRow(); int firstColumn = range.getFirstColumn(); int lastColumn = range.getLastColumn(); final int width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); ctx.sheet = sheet; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ++ctx.rowNumber) { Row currentRow = sheet.getRow(ctx.rowNumber); if (currentRow == null) { if (traverseEmptyCells) { for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ++ctx.colNumber) { ctx.ordinalNumber = (long) (ctx.rowNumber - firstRow) * width + (ctx.colNumber - firstColumn); handler.onCell(null, ctx); } } continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ++ctx.colNumber) { Cell currentCell = currentRow.getCell(ctx.colNumber); ctx.ordinalNumber = (long) (ctx.rowNumber - firstRow) * width + (ctx.colNumber - firstColumn); if (currentCell == null) { if (traverseEmptyCells) { handler.onCell(null, ctx); } continue; } if (!traverseEmptyCells && isEmpty(currentCell)) { continue; } handler.onCell(currentCell, ctx); } } }
int getReadIndex() { return _readIndex; }
public int compareTo(ScoreTerm other) { return java.util.Comparator.comparing(ScoreTerm::getBoost).thenComparing(ScoreTerm::getTerm).compare(this, other); }
public int normalize(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = StemmerUtil.delete(s, i, len); i--; break; default: break; } } return len; }
void Serialize(ILittleEndianOutput out1) { out1.WriteShort(); }
protected final boolean exactOnly;
public KeySchemaElement(String attributeName, KeyType keyType) { this.attributeName = attributeName; this.keyType = keyType; }
public GetAssignmentResponse getAssignment(GetAssignmentRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetAssignmentRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetAssignmentResponseUnmarshaller.getInstance()); return invoke(request, options); }
boolean hasObject() { return findOffset(-1) != AnyObjectId.id; }
public GroupingSearch setAllGroups(boolean allGroups) { this.allGroups = allGroups; return this; }

getCellsVal int } { ) ( ; size return ) : c char ( for ; } { keySet() . cells int ) ( if ; size } { 0 >= Cell 0 = ; cmd . e e ++ size = at ) (
public DeleteVoiceConnectorResult deleteVoiceConnector(DeleteVoiceConnectorRequest request) {request = beforeClientExecution(request);return executeDeleteVoiceConnector(request);}
public DeleteLifecyclePolicyResponse deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteLifecyclePolicyRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteLifecyclePolicyResponseUnmarshaller.getInstance()); return this.<DeleteLifecyclePolicyResponse>invoke(request, options); }
