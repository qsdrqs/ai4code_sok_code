void Serialize(ILittleEndianOutput out1) { out1.WriteShort(); }
public void addAll(NGit.Util.BlockList<T> src) { if (src.size == 0) { return; } for (int srcDirIdx = 0; srcDirIdx < src.tailDirIdx; srcDirIdx++) { addAll(src.directory[srcDirIdx]); } if (src.tailBlkIdx != 0) { addAll(src.tailBlock, 0, src.tailBlkIdx); } }
void writeByte(byte b) { if (upto == blockSize) { if (currentBlock != null) { blocks.add(currentBlock); blockEnd.add(upto); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
public ObjectId getObjectId() { return objectId; }
public DeleteDomainEntryResponse deleteDomainEntry(DeleteDomainEntryRequest request) { return invoke(request, new InvokeOptions().withRequestMarshaller(DeleteDomainEntryRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.getInstance())); }
public long ramBytesUsed() { return fst == null ? 0L : fst.getSizeInBytes(); }
string GetFullMessage() {     byte[] raw = buffer;     int msgB = RawParseUtils.TagMessage(raw, 0);     if (msgB < 0)     {         return string.Empty;     }     Encoding enc = RawParseUtils.ParseEncoding(raw);     return RawParseUtils.Decode(enc, raw, msgB, raw.Length); }
this.headerBlock = new HeaderBlock(); this._property_table = new PropertyTable(); this._documents = new ArrayList<>(); this._root = null;
void init(int address) { slice = pool.buffers[address >> BYTE_BLOCK_SHIFT]; assert slice != null; upto = address & BYTE_BLOCK_MASK; offset0 = address; }
public SubmoduleAddCommand setPath(String path) { this.path = path; return this; }
public ListIngestionsResponse listIngestions(ListIngestionsRequest request) { return this.invoke(request, new InvokeOptions.Builder().requestMarshaller(ListIngestionsRequestMarshaller.getInstance()).responseUnmarshaller(ListIngestionsResponseUnmarshaller.getInstance()).build()); }
public QueryParserTokenManager(ICharStream stream, int lexState) { this(stream); SwitchTo(lexState); }
public GetShardIteratorResponse getShardIterator(GetShardIteratorRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetShardIteratorRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetShardIteratorResponseUnmarshaller.getInstance()); return this.<GetShardIteratorResponse>invoke(request, options); }
new ModifyStrategyRequest().setMethod(MethodType.POST);
public boolean ready() throws java.io.IOException { synchronized (lock) { if (in == null) { throw new java.io.IOException("Stream is closed"); } try { return bytes.hasRemaining() || in.available() > 0; } catch (java.io.IOException e) { return false; } } }
public EscherOptRecord getOptRecord() { return _optRecord; }
public synchronized int read(byte[] buffer, int offset, int length) { if (buffer == null) { throw new NullPointerException("buffer"); } if (offset < 0 || length < 0 || length > buffer.length - offset) { throw new IndexOutOfBoundsException(); } if (length == 0) { return 0; } int copylen = Math.min(length, count - pos); if (copylen <= 0) { return 0; } System.arraycopy(internalBuffer, pos, buffer, offset, copylen); pos += copylen; return copylen; }
public class NLPSentenceDetectorOp { private final opennlp.tools.sentdetect.SentenceDetectorME sentenceDetector; public NLPSentenceDetectorOp(String modelPath) throws java.io.IOException { try (java.io.InputStream modelIn = new java.io.FileInputStream(modelPath)) { this.sentenceDetector = new opennlp.tools.sentdetect.SentenceDetectorME(new opennlp.tools.sentdetect.SentenceModel(modelIn)); } } public String[] detectSentences(String paragraph) { return this.sentenceDetector.sentDetect(paragraph); } }
void print(string str)  {      write(str != null ? str : Sharpen.StringHelper.GetValueOf((object)null));  }
public class NotImplementedFunctionException extends UnsupportedOperationException { public NotImplementedFunctionException(String message, Throwable cause) { super(message, cause); } }
V next() { return nextEntry.value; }
public void readBytes(byte[] b, int offset, int len, boolean useBuffer) throws java.io.IOException { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) { System.arraycopy(buffer, bufferPosition, b, offset, len); } bufferPosition += len; } else { if (available > 0) { System.arraycopy(buffer, bufferPosition, b, offset, available); len -= available; offset += available; bufferPosition += available; } if (useBuffer && len < bufferSize) { refill(); if (bufferLength < len) { System.arraycopy(buffer, 0, b, offset, bufferLength); throw new java.io.EOFException(); } else { System.arraycopy(buffer, 0, b, offset, len); bufferPosition = len; } } else { long after = bufferStart + bufferPosition + len; if (after > length) { throw new java.io.EOFException(); } readInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
return Invoke<TagQueueResponse>(request, new InvokeOptions { RequestMarshaller = TagQueueRequestMarshaller.Instance, ResponseUnmarshaller = TagQueueResponseUnmarshaller.Instance });
void remove() { throw new UnsupportedOperationException(); }
ModifyCacheSubnetGroupResponse response = elastiCacheClient.modifyCacheSubnetGroup(request);
public void setParams(String paramsString) { java.util.StringTokenizer st = new java.util.StringTokenizer(paramsString); if (st.hasMoreTokens()) { this.culture = st.nextToken(); } if (st.hasMoreTokens()) { this.culture += " " + st.nextToken(); } if (st.hasMoreTokens()) { this.ignore = st.nextToken(); } }
public DeleteDocumentationVersionResponse DeleteDocumentationVersion(DeleteDocumentationVersionRequest request) {     var options = new InvokeOptions     {         RequestMarshaller = DeleteDocumentationVersionRequestMarshaller.Instance,         ResponseUnmarshaller = DeleteDocumentationVersionResponseUnmarshaller.Instance     };     return Invoke<DeleteDocumentationVersionResponse>(request, options); }
@Override public boolean equals(Object obj) { if (!(obj instanceof FacetLabel)) { return false; } FacetLabel other = (FacetLabel) obj; if (this.components.length != other.components.length) { return false; } for (int i = this.components.length - 1; i >= 0; i--) { if (!this.components[i].equals(other.components[i])) { return false; } } return true; }
public GetInstanceAccessDetailsResponse getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetInstanceAccessDetailsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetInstanceAccessDetailsResponseUnmarshaller.getInstance()); return invoke(request, options); }
HSSFPolygon shape = new HSSFPolygon(parent, new HSSFChildAnchor());
String GetSheetName(int sheetIndex)  {      return GetBoundSheetRec(sheetIndex).Sheetname;  }
return invoke(request, new InvokeOptions().withRequestMarshaller(GetDashboardRequestMarshaller.getInstance()).withResponseUnmarshaller(GetDashboardResponseUnmarshaller.getInstance()));
public AssociateSigninDelegateGroupsWithAccountResponse AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) {     var options = new InvokeOptions     {         RequestMarshaller = AssociateSigninDelegateGroupsWithAccountRequestMarshaller.Instance,         ResponseUnmarshaller = AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.Instance     };     return Invoke<AssociateSigninDelegateGroupsWithAccountResponse>(options); }
void addMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < mbr.numColumns; ++j) { BlankRecord br = new BlankRecord(); br.column = mbr.firstColumn + j; br.row = mbr.row; br.xfIndex = mbr.getXfAt(); insertCell(br); } }
public static String quote(String string) { StringBuilder sb = new StringBuilder(); sb.append("\""); int apos = 0; int k; while ((k = string.indexOf("\"", apos)) >= 0) { sb.append(string.substring(apos, k)); sb.append("\\\""); apos = k + 1; } sb.append(string.substring(apos)); sb.append("\""); return sb.toString(); }
public ByteBuffer putInt(int value) { throw new java.nio.ReadOnlyBufferException(); }
public ArrayPtg(Object[][] values2d) { int nColumns = values2d[0].length; int nRows = values2d.length; _nColumns = (short) nColumns; _nRows = (short) nRows; Object[] vv = new Object[_nRows * _nColumns]; for (int r = 0; r < nRows; r++) { Object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[getValueIndex(c, r)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
public GetIceServerConfigResponse GetIceServerConfig(GetIceServerConfigRequest request) {     var options = new InvokeOptions();     options.RequestMarshaller = GetIceServerConfigRequestMarshaller.Instance;     options.ResponseUnmarshaller = GetIceServerConfigResponseUnmarshaller.Instance;     return Invoke<GetIceServerConfigResponse>(request, options); }
public override string ToString() {     StringBuilder sb = new StringBuilder();     sb.Append(this.GetType().Name);     sb.Append(" ");     sb.Append(GetValueAsString());     sb.Append(" ");     return sb.ToString(); }
@Override public String toString() { return field + " " + _parentQuery; }
public void incRef() { refCount.incrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResponse UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) {     var options = new InvokeOptions     {         RequestMarshaller = UpdateConfigurationSetSendingEnabledRequestMarshaller.Instance,         ResponseUnmarshaller = UpdateConfigurationSetSendingEnabledResponseUnmarshaller.Instance     };     return Invoke<UpdateConfigurationSetSendingEnabledResponse>(request, options); }
private int getNextXbatChainOffset() { return getXbatEntriesPerBlock() * LittleEndianConsts.INT_SIZE; }
void multiplyByPowerOfTen(int pow10)  {     TenPower tp = TenPower.GetInstance(Math.Abs(pow10));     if (pow10 < 0)      {         mulShift(tp._divisor, tp._divisorShift);     }      else      {         mulShift(tp._multiplicand, tp._multiplierShift);     } }
public string ToString() {     StringBuilder builder = new StringBuilder();     int length = this.Length;      builder.Append(Path.DirectorySeparatorChar);     for (int i = 0; i < length; i++)     {         builder.Append(this.GetComponent(i));          if (i < (length - 1))         {             builder.Append(Path.DirectorySeparatorChar);         }     }     return builder.ToString(); }
EcsCredentialsProvider provider = EcsCredentialsProvider.builder().roleName("YourRoleName").build();
public void setProgressMonitor(ProgressMonitor pm) { this.progressMonitor = pm; }
void Reset() { if (!First) { ptr = 0; if (!Eof) { ParseEntry(); } } }
E previous() { if (iterator.previousIndex() >= start) { return iterator.previous(); } throw new java.util.NoSuchElementException(); }
String getNewPrefix() { return newPrefix; }
int indexOfValue(int value) {{ for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } } return -1; }
public java.util.List<CharsRef> uniqueStems(java.util.List<CharsRef> stems) { if (stems.size() < 2) { return stems; } java.util.Set<CharsRef> terms = new org.apache.lucene.analysis.util.CharArraySet(stems.size(), false); java.util.List<CharsRef> deduped = new java.util.ArrayList<>(); for (CharsRef s : stems) { if (!terms.contains(s)) { deduped.add(s); terms.add(s); } } return deduped; }
public GetGatewayResponsesResponse getGatewayResponses(GetGatewayResponsesRequest getGatewayResponsesRequest) { return this.invoke(getGatewayResponsesRequest, new GetGatewayResponsesRequestMarshaller(), GetGatewayResponsesResponseUnmarshaller.getInstance()); }
void setPosition(long position) { currentBlockIndex = (int) (position >> outerInstance.blockBits); currentBlock = outerInstance.blocks[currentBlockIndex]; currentBlockUpto = (int) (position & outerInstance.blockMask); }
public long skip(long n) { int s = (int)Math.min(n, Math.max(0, available)); return s; }
public BootstrapActionConfig(BootstrapActionDetail bootstrapActionConfig) { }
public void serialize(LittleEndianOutput out) { out.writeShort(); out.writeShort(); out.writeShort(); out.writeShort(); out.writeShort(field_6_author.length()); out.writeByte(field_5_hasMultibyte ? 0x01 : 0x00); if (field_5_hasMultibyte) { StringUtil.putUnicodeLE(field_6_author, out); } else { StringUtil.putCompressedUnicode(field_6_author, out); } if (field_7_padding != null) { out.writeByte(Integer.parseInt()); } }
int lastIndexOf(String str) { return str.lastIndexOf(')'); }
public boolean add(E object) { return addLastImpl(object); }
} ; ) ) , ( CompareAndSet . state ! ( while } ; ) , , ( UnsetSection res ; ) ( Get . state src { do ; ConfigSnapshot ; ConfigSnapshot { ) subsection String , section String ( UnsetSection void
String GetTagName() { return tagName; }
void addSubRecord(int index, SubRecord element) { subrecords.add(index, element); }
public boolean remove(Object object) { synchronized (mutex) { return c.remove(object); } }
public TokenStream create(TokenStream input) { return new DoubleMetaphoneFilter(input); }
public long getLength() { return InCoreLength; }
void SetValue(boolean newValue) { value = newValue; }
new Pair(oldSource.getContentSource(), newSource.getContentSource());
int Get(int i)  {      if (count <= i)      {          throw Sharpen.Extensions.CreateIndexOutOfRangeException();      }      return entries[i];  }
public record CreateRepoRequest() {}
boolean IsDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public void remove() { if (list.modCount != expectedModCount) throw new ConcurrentModificationException(); if (lastReturned == null) throw new IllegalStateException(); Node<E> lastNext = lastReturned.next; list.unlink(lastReturned); if (next == lastReturned) next = lastNext; else nextIndex--; lastReturned = null; expectedModCount++; }
public MergeShardsResponse mergeShards(MergeShardsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(MergeShardsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(MergeShardsResponseUnmarshaller.getInstance()); return this.<MergeShardsResponse>invoke(request, options); }
public AllocateHostedConnectionResponse allocateHostedConnection(AllocateHostedConnectionRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(AllocateHostedConnectionRequestMarshaller.getInstance()); options.setResponseUnmarshaller(AllocateHostedConnectionResponseUnmarshaller.getInstance()); return invoke(request, options); }
int getBeginIndex()  {      return start;  }
public WeightedTerm[] GetTerms(Query query) {     // In a real implementation, this would contain logic     // to extract terms from the query object.     return new WeightedTerm[0]; }
public ByteBuffer compact() { throw new java.nio.ReadOnlyBufferException(); }
void decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; i++) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = byte0 >>> 2; int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >>> 4); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >>> 6); values[valuesOffset++] = byte2 & 63; } }
public static String getHumanishName(String s) { if (s == null || s.isEmpty()) { throw new IllegalArgumentException(); } String[] elements; if (s.equals(" ") || LOCAL_FILE.matcher(s).matches()) { elements = s.split(" |" + java.util.regex.Pattern.quote(java.io.File.separator) + "|\\\\"); } else { elements = s.split("/"); } if (elements.length == 0) { throw new IllegalArgumentException(); } String result = elements[elements.length - 1]; if (elements.length > 1 && result.equals(Constants.DOT_GIT)) { result = elements[elements.length - 2]; } if (result.endsWith(Constants.DOT_GIT_EXT)) { result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); } return result; }
public DescribeNotebookInstanceLifecycleConfigResponse describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { return this.client.describeNotebookInstanceLifecycleConfig(request); }
public String getAccessKeySecret() { return accessSecret; }
public CreateVpnConnectionResponse createVpnConnection(CreateVpnConnectionRequest request) { return invoke(request, new InvokeOptions(CreateVpnConnectionRequestMarshaller.getInstance(), CreateVpnConnectionResponseUnmarshaller.getInstance())); }
public DescribeVoicesResponse DescribeVoices(DescribeVoicesRequest request) {     var options = new InvokeOptions     {         RequestMarshaller = DescribeVoicesRequestMarshaller.Instance,         ResponseUnmarshaller = DescribeVoicesResponseUnmarshaller.Instance     };     return Invoke<DescribeVoicesResponse>(request, options); }
public ListMonitoringExecutionsResponse ListMonitoringExecutions(ListMonitoringExecutionsRequest request) {     var options = new InvokeOptions     {         RequestMarshaller = ListMonitoringExecutionsRequestMarshaller.Instance,         ResponseUnmarshaller = ListMonitoringExecutionsResponseUnmarshaller.Instance     };     return Invoke<ListMonitoringExecutionsResponse>(request, options); }
public DescribeJobRequest(String vaultName, String jobId) { this.vaultName = vaultName; this.jobId = jobId; }
EscherRecord getEscherRecord(int index) { return escherRecords.get(index); }
public GetApisResponse getApis(GetApisRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetApisRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetApisResponseUnmarshaller.getInstance()); return invoke(request, options); }
return invoke(request, new InvokeOptions().withRequestMarshaller(DeleteSmsChannelRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteSmsChannelResponseUnmarshaller.getInstance()));
TrackingRefUpdate getTrackingRefUpdate() { return trackingRefUpdate; }
} ; ) ) ( toString . b ( System.out.println { ) b boolean ( System.out.println void
IQueryNode getChild() { return getChildren()[0]; }
new NotIgnoredFilter(index.workdirTreeIndex);
field_1_formatFlags = in1.readShort();
GetThumbnailRequest request = new GetThumbnailRequest(); request.setProtocol(ProtocolType.HTTPS);
public DescribeTransitGatewayVpcAttachmentsResponse DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request)  {     var options = new InvokeOptions      {         RequestMarshaller = DescribeTransitGatewayVpcAttachmentsRequestMarshaller.Instance,         ResponseUnmarshaller = DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.Instance     };     return Invoke<DescribeTransitGatewayVpcAttachmentsResponse>(request, options); }
public PutVoiceConnectorStreamingConfigurationResponse putVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) { return invoke(request, new InvokeOptions(PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance(), PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance())); }
OrdRange getOrdRange(String dim) { return prefixToOrdRange.get(dim); }
} ; ) , getName ( ) . ) LexerNoViableAltException . class , " " , Locale . getDefault ( ) ( format . String return } ; ) , ( escapeWhitespace . Utils symbol ; ) ) , ( of . Interval ( getText . ) inputStream ) CharStream ( ( symbol { ) size ( ) . ) inputStream ) CharStream ( ( < startIndex && 0 >= startIndex ( if ; "" = symbol String { ) ( toString String
E peek() { return peekFirstImpl(); }
public CreateWorkspacesResponse CreateWorkspaces(CreateWorkspacesRequest request)  {     var options = new InvokeOptions      {         RequestMarshaller = CreateWorkspacesRequestMarshaller.Instance,         ResponseUnmarshaller = CreateWorkspacesResponseUnmarshaller.Instance     };     return Invoke<CreateWorkspacesResponse>(request, options); }
public Object clone() { NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = this.field_1_formatIndex; return rec; }
public DescribeRepositoriesResponse describeRepositories(DescribeRepositoriesRequest request) { return ecrClient.describeRepositories(request); }
public SparseIntArray(int initialCapacity) { initialCapacity = android.util.internal.ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
public TokenStream create(TokenStream input) { return new HyphenatedWordsFilter(input); }
CreateDistributionWithTagsResponse response = cloudFrontClient.createDistributionWithTags(request);
public java.io.RandomAccessFile createFile(String fileName, String mode) { throw new UnsupportedOperationException(); }
public DeleteWorkspaceImageResponse deleteWorkspaceImage(DeleteWorkspaceImageRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.getInstance()); return this.<DeleteWorkspaceImageResponse>invoke(options); }
String ToHex(int value) { return ToHex((long)value); }
return invoke(request, new InvokeOptions().withRequestMarshaller(UpdateDistributionRequestMarshaller.getInstance()).withResponseUnmarshaller(UpdateDistributionResponseUnmarshaller.getInstance()));
HSSFColor getColor(short index) { if (index == HSSFColor.HSSFColorPredefined.AUTOMATIC.getIndex()) { return HSSFColor.HSSFColorPredefined.AUTOMATIC.getColor(); } else { byte[] b = palette.getColor(index); if (b != null) { return new CustomColor(b); } } return null; }
public ValueEval evaluate(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(); }
void serialize(LittleEndianOutput out1) { out1.writeShort((short) field_1_number_crn_records); out1.writeShort((short) field_2_sheet_table_index); }
return rdsClient.describeDBEngineVersions(DescribeDBEngineVersionsRequest.builder().build());
public class FormatRun { public short character; public short fontIndex; public FormatRun(short character, short fontIndex) { this.character = character; this.fontIndex = fontIndex; } }
public byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int resultIndex = 0; for (int i = offset; i < offset + length; i++) { char ch = chars[i]; result[resultIndex++] = (byte) (ch >> 8); result[resultIndex++] = (byte) ch; } return result; }
return Invoke<UploadArchiveRequest, UploadArchiveResponse>(request, new InvokeOptions {     RequestMarshaller = UploadArchiveRequestMarshaller.Instance,     ResponseUnmarshaller = UploadArchiveResponseUnmarshaller.Instance });
List<IToken> getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex - 1); }
@Override public boolean equals(Object obj) { if (this == obj) return true; if (!super.equals(obj)) return false; if (getClass() != obj.getClass()) return false; AutomatonQuery other = (AutomatonQuery) obj; if (!m_compiled.equals(other.m_compiled)) return false; if (m_term == null) { if (other.m_term != null) { return false; } } else if (!m_term.equals(other.m_term)) { return false; } return true; }
public SpanQuery makeSpanClause() { List<SpanQuery> spanQueries = new ArrayList<>(); for (Map.Entry<SpanQuery, Float> wsq : weightBySpanQuery.entrySet()) { spanQueries.add(new SpanBoostQuery(wsq.getKey(), wsq.getValue())); } if (spanQueries.size() == 1) { return spanQueries.get(0); } else { return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); } }
return new StashCreateCommand(StashCreate);
// Assuming 'byName' is a class member like: Dictionary<string, FieldInfo> byName; public FieldInfo GetFieldByName(string fieldName)  {     FieldInfo ret;     byName.TryGetValue(fieldName, out ret);     return ret; }
public DescribeEventSourceResponse DescribeEventSource(DescribeEventSourceRequest request) {     var options = new InvokeOptions     {         RequestMarshaller = DescribeEventSourceRequestMarshaller.Instance,         ResponseUnmarshaller = DescribeEventSourceResponseUnmarshaller.Instance     };     return this.Invoke<DescribeEventSourceResponse>(request, options); }
return clientHandler.execute(new ClientExecutionParams<GetDocumentAnalysisRequest, GetDocumentAnalysisResponse>().withInput(request).withMarshaller(new GetDocumentAnalysisRequestMarshaller()).withResponseHandler(new GetDocumentAnalysisResponseHandler()));
public CancelUpdateStackResponse cancelUpdateStack(CancelUpdateStackRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(CancelUpdateStackRequestMarshaller.getInstance()); options.setResponseUnmarshaller(CancelUpdateStackResponseUnmarshaller.getInstance()); return invoke(request, options); }
public ModifyLoadBalancerAttributesResponse ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) {     var options = new InvokeOptions     {         RequestMarshaller = ModifyLoadBalancerAttributesRequestMarshaller.Instance,         ResponseUnmarshaller = ModifyLoadBalancerAttributesResponseUnmarshaller.Instance     };     return Invoke<ModifyLoadBalancerAttributesResponse>(request, options); }
SetInstanceProtectionResponse response = ec2Client.setInstanceProtection(SetInstanceProtectionRequest.builder().instanceIds(instanceIds).protectedFromScaleIn(true).build());
public ModifyDBProxyResponse modifyDBProxy(ModifyDBProxyRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ModifyDBProxyRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ModifyDBProxyResponseUnmarshaller.getInstance()); return this.<ModifyDBProxyResponse>invoke(request, options); }
public void add(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.length) { CharsRef[] next = new CharsRef[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF)]; System.arraycopy(outputs, 0, next, 0, count); outputs = next; } if (count == endOffsets.length) { int[] next = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.length) { int[] next = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRef(); } outputs[count].copyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
public FetchLibrariesRequest() { super(" ", " ", " ", " ", " "); this.protocol = ProtocolType.HTTPS; }
boolean exists() { return objects.stream().anyMatch(item -> /* some condition */); }
FilterOutputStream out = new FilterOutputStream(System.out) {};
@PutMapping("/your/uri/pattern") public ResponseEntity<Void> scaleCluster(@RequestBody ScaleClusterRequest request);
public IDataValidationConstraint createTimeConstraint(int operatorType, String formula1, String formula2) { return new DVConstraint(operatorType, formula1, formula2); }
public ListObjectParentPathsResponse listObjectParentPaths(ListObjectParentPathsRequest request) { return invoke(request, new InvokeOptions.Builder().requestMarshaller(ListObjectParentPathsRequestMarshaller.getInstance()).responseUnmarshaller(ListObjectParentPathsResponseUnmarshaller.getInstance()).build()); }
return elastiCacheClient.describeCacheSubnetGroups(request);
public void SetSharedFormula(bool flag) {     field_5_options = (short)sharedFormula.SetShortBoolean(field_5_options, flag); }
bool IsReuseObjects() {     return reuseObjects; }
public IErrorNode addErrorNode(IToken badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); t.Parent.AddChild(t); return t; }
public LatvianStemFilterFactory(java.util.Map<String, String> args) { super(args); if (args.size() > 0) { throw new IllegalArgumentException(" " + args); } }
RemoveSourceIdentifierFromSubscriptionResponse response = client.removeSourceIdentifierFromSubscription(request);
public static TokenFilterFactory forName(String name, java.util.Map<String, String> args) { return loader.newInstance(name, args); }
public class AddAlbumPhotosRequest { private ProtocolType protocol = ProtocolType.HTTPS; public ProtocolType getProtocol() { return protocol; } public void setProtocol(ProtocolType protocol) { this.protocol = protocol; } } enum ProtocolType { HTTPS }
public GetThreatIntelSetResponse GetThreatIntelSet(GetThreatIntelSetRequest request) {     InvokeOptions options = new InvokeOptions();     options.RequestMarshaller = GetThreatIntelSetRequestMarshaller.Instance;     options.ResponseUnmarshaller = GetThreatIntelSetResponseUnmarshaller.Instance;     return Invoke<GetThreatIntelSetResponse>(request, options); }
public TreeFilter clone() { return new AndTreeFilter(a.clone(), b.clone()); }
public boolean equals(Object o) { return o != null; }
protected boolean hasArray() { return hasArray; }
public UpdateContributorInsightsResponse updateContributorInsights(UpdateContributorInsightsRequest request) { return this.invoke(request, UpdateContributorInsightsRequestMarshaller.getInstance(), UpdateContributorInsightsResponseUnmarshaller.getInstance()); }
void unwriteProtectWorkbook() { records.remove(); records.remove(); fileShare = null; writeProtect = null; }
public SolrSynonymParser(Analyzer analyzer, boolean expand, boolean dedup) { super(analyzer); }
// This is a conceptual reconstruction of the scrambled code's intent. public RequestSpotInstancesResponse RequestSpotInstances(RequestSpotInstancesRequest request) {     var options = new InvokeOptions     {         RequestMarshaller = RequestSpotInstancesRequestMarshaller.Instance,         ResponseUnmarshaller = RequestSpotInstancesResponseUnmarshaller.Instance     };     return Invoke<RequestSpotInstancesResponse>(request, options); }
public byte[] GetObjectData()  {     return FindObjectRecord().ObjectData; }
public GetContactAttributesResponse getContactAttributes(GetContactAttributesRequest request) { return this.<GetContactAttributesResponse>invoke(request, new InvokeOptions(GetContactAttributesRequestMarshaller.getInstance(), GetContactAttributesResponseUnmarshaller.getInstance())); }
@Override public String toString() { return getKey() + " " + getValue(); }
public ListTextTranslationJobsResponse listTextTranslationJobs(ListTextTranslationJobsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListTextTranslationJobsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ListTextTranslationJobsResponseUnmarshaller.getInstance()); return this.<ListTextTranslationJobsResponse>invoke(request, options); }
public GetContactMethodsResponse getContactMethods(GetContactMethodsRequest request) { return this.invoke(request, new GetContactMethodsRequestMarshaller(), GetContactMethodsResponseUnmarshaller.getInstance()); }
short LookupIndexByName(String name)  {      FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name);      if (fd == null)      {          return -1;      }      return (short)fd.Index;  }
public DescribeAnomalyDetectorsResponse describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { return invoke(request, DescribeAnomalyDetectorsRequestMarshaller.getInstance(), DescribeAnomalyDetectorsResponseUnmarshaller.getInstance()); }
public ObjectId insertId(String message, ObjectId changeId) { return new ObjectId(); }
public long getObjectSize(AnyObjectId objectId, int typeHint) throws MissingObjectException { long sz = db.getObjectSize(objectId, typeHint); if (sz < 0) { if (typeHint == OBJ_ANY) { throw new MissingObjectException(objectId.copy(), " "); } throw new MissingObjectException(objectId.copy(), " "); } return sz; }
public ImportInstallationMediaResponse importInstallationMedia(ImportInstallationMediaRequest request) { return invoke(request, new ImportInstallationMediaRequestMarshaller(), ImportInstallationMediaResponseUnmarshaller.getInstance()); }
return invoke(request, new InvokeOptions(PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance(), PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance()));
field_1_value = in1.readDouble();
public GetFieldLevelEncryptionConfigResponse GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) {     return Invoke<GetFieldLevelEncryptionConfigResponse>(request, new InvokeOptions     {         RequestMarshaller = GetFieldLevelEncryptionConfigRequestMarshaller.Instance,         ResponseUnmarshaller = GetFieldLevelEncryptionConfigResponseUnmarshaller.Instance     }); }
return invoke(request, new InvokeOptions().requestMarshaller(DescribeDetectorRequestMarshaller.getInstance()).responseUnmarshaller(DescribeDetectorResponseUnmarshaller.getInstance()));
ReportInstanceStatusResponse response = ec2Client.reportInstanceStatus(request);
public DeleteAlarmResponse DeleteAlarm(DeleteAlarmRequest request) {     var options = new InvokeOptions     {         RequestMarshaller = DeleteAlarmRequestMarshaller.Instance,         ResponseUnmarshaller = DeleteAlarmResponseUnmarshaller.Instance     };     return Invoke<DeleteAlarmResponse>(request, options); }
public TokenStream create(TokenStream input) { return new PortugueseStemFilter(input); }
public FtCblsSubRecord() { reserved = new byte[ENCODED_SIZE]; }
boolean remove(Object object) { synchronized (mutex) { return c.remove(object); } }
return invoke(request, new InvokeOptions().requestMarshaller(GetDedicatedIpRequestMarshaller.getInstance()).responseUnmarshaller(GetDedicatedIpResponseUnmarshaller.getInstance()));
public override string ToString() {     return " " + precedence; }
public ListStreamProcessorsResponse ListStreamProcessors(ListStreamProcessorsRequest request) {     var options = new InvokeOptions     {         RequestMarshaller = ListStreamProcessorsRequestMarshaller.Instance,         ResponseUnmarshaller = ListStreamProcessorsResponseUnmarshaller.Instance     };     return Invoke<ListStreamProcessorsResponse>(request, options); }
public DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) { this.loadBalancerName = loadBalancerName; this.policyName = policyName; }
public WindowProtectRecord(int options) { this.options = options; }
public UnbufferedCharStream(int bufferSize) { n = 0; data = new int[bufferSize]; }
public GetOperationsResponse GetOperations(GetOperationsRequest request) {     return Invoke<GetOperationsResponse>(request, new InvokeOptions     {         RequestMarshaller = GetOperationsRequestMarshaller.Instance,         ResponseUnmarshaller = GetOperationsResponseUnmarshaller.Instance     }); }
void CopyRawTo(byte[] b, int o) { NB.EncodeInt32(o + 4); NB.EncodeInt32(o + 8); NB.EncodeInt32(o + 12); NB.EncodeInt32(o + 16); }
public WindowOneRecord(RecordInputStream in1) { field_1_h_hold = in1.readShort(); field_2_v_hold = in1.readShort(); field_3_width = in1.readShort(); field_4_height = in1.readShort(); field_5_options = in1.readShort(); field_6_active_sheet = in1.readShort(); field_7_first_visible_tab = in1.readShort(); field_8_num_selected_tabs = in1.readShort(); field_9_tab_width_ratio = in1.readShort(); }
public StopWorkspacesResponse stopWorkspaces(StopWorkspacesRequest request) { return workspacesClient.stopWorkspaces(request); }
void close() throws IOException { if (isOpen) { try { dump(); } finally { try { channel.truncate(0); } finally { try { channel.close(); } finally { fos.close(); } } } } }
public DescribeMatchmakingRuleSetsResponse describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeMatchmakingRuleSetsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeMatchmakingRuleSetsResponseUnmarshaller.getInstance()); return invoke(request, options); }
public String getPronunciation(int wordId, char[] surface, int off, int len) { return null; }
public String getPath() { return pathStr; }
public static double devsq(double[] v) { double r = Double.NaN; if (v != null && v.length >= 1) { double m = 0; double s = 0; int n = v.length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } return r; }
public DescribeResizeResponse describeResize(DescribeResizeRequest request) { return invoke(request, new InvokeOptions<>().withMarshaller(DescribeResizeRequestMarshaller.getInstance()).withResponseHandler(DescribeResizeResponseUnmarshaller.getInstance())); }
boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
int end() { return end(); }
void traverse(ICellHandler handler) { int firstRow = range.getFirstRow(); int lastRow = range.getLastRow(); int firstColumn = range.getFirstColumn(); int lastColumn = range.getLastColumn(); int width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); Row currentRow = null; Cell currentCell = null; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ctx.rowNumber++) { currentRow = sheet.getRow(ctx.rowNumber); if (currentRow == null) { continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ctx.colNumber++) { currentCell = currentRow.getCell(ctx.colNumber); if (currentCell == null) { continue; } if (isEmpty(currentCell) && !traverseEmptyCells) { continue; } ctx.ordinalNumber = (ctx.rowNumber - firstRow) * width + (ctx.colNumber - firstColumn + 1); handler.onCell(ctx, currentCell); } } }
int getReadIndex() { return _readIndex; }
public int compareTo(ScoreTerm other) { int termCmp = this.term.compareTo(other.term); if (termCmp != 0) { return termCmp; } return this.boost.compareTo(other.boost); }
int normalize(char[] s, int len) { for (int i = 0; i < len; ++i) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = StemmerUtil.delete(s, i, len); --i; break; default: break; } } return len; }
void serialize(ILittleEndianOutput out1) { out1.writeShort(someValue); }
} ; exactOnly exactOnly . {  ) exactOnly boolean ( DiagnosticErrorListener
public KeySchemaElement(String attributeName, KeyType keyType) { this.attributeName = attributeName; this.keyType = keyType; }
return invoke(request, new InvokeOptions().withRequestMarshaller(GetAssignmentRequestMarshaller.getInstance()).withResponseUnmarshaller(GetAssignmentResponseUnmarshaller.getInstance()));
boolean HasObject(AnyObjectId id) { return FindOffset(id) != -1; }
public boolean setAllGroups(GroupingSearch allGroups) { this.allGroups = allGroups; return true; }
public void setMultiValued(String dimName, boolean v) { synchronized (fieldTypes) { fieldTypes.computeIfAbsent(dimName, k -> new DimConfig()).setIsMultiValued(v); } }
int GetCellsVal() { int size = 0; for (Character c : cells.keySet()) { Cell e = At(c); if (e.cmd >= 0) { size++; } } return size; }
public DeleteVoiceConnectorResponse DeleteVoiceConnector(DeleteVoiceConnectorRequest request) {     var options = new InvokeOptions     {         RequestMarshaller = DeleteVoiceConnectorRequestMarshaller.Instance,         ResponseUnmarshaller = DeleteVoiceConnectorResponseUnmarshaller.Instance     };     return Invoke<DeleteVoiceConnectorResponse>(request, options); }
public DeleteLifecyclePolicyResponse deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { return invoke(new InvokeOptions().withRequestMarshaller(DeleteLifecyclePolicyRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteLifecyclePolicyResponseUnmarshaller.getInstance())); }
