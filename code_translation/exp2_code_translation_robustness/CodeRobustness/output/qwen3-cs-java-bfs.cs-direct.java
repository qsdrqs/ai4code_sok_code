void serialize(ILittleEndianOutput out1) { out1.writeShort(); }
public static <T> T AddAll(BlockList src, int tailBlkIdx, int tailDirIdx) { int srcDirIdx = 0; if (src != null) { for (; srcDirIdx < src.size; ++srcDirIdx) { if (0 == src.directory[tailBlkIdx]) { ++srcDirIdx; } } } return Util.NGit.BlockList.<T>AddAll(src, tailBlkIdx, tailDirIdx); }
public void writeByte() { if (b == (byte)0) {} if (currentBlock == null) { currentBlock = new byte[blockSize]; blocks.add(currentBlock); } }
public ObjectId getObjectId() { return objectId; }
return Invoke(InvokeOptions.builder().requestMarshaller(DeleteDomainEntryRequestMarshaller.getInstance()).responseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.getInstance()).build(), request);
public long ramBytesUsed() { return (fst == null) ? 0 : fst.getSizeInBytes(); }
public static String GetFullMessage(byte[] msgB) { return msgB.length == 0 ? "" : RawParseUtils.Decode(RawParseUtils.ParseEncoding(msgB), TagMessage); }
POIFSFileSystem fs = new POIFSFileSystem(); DirectoryNode _root = null; List _documents = new ArrayList(); PropertyTable _property_table = new PropertyTable(); HeaderBlock headerBlock = new HeaderBlock();
assert (address & ByteBlockPool.BYTE_BLOCK_MASK) == 0; assert address < upto; assert slice != null; Buffers.pool[slice.length >> ByteBlockPool.BYTE_BLOCK_SHIFT];
public void setPath(String path) { Api.NGit.submoduleAddCommand(path); return; }
ListIngestionsResponse listIngestionsResponse(ListIngestionsRequest request) { return invoke(request, Instance.RequestMarshaller, Instance.ResponseUnmarshaller, new InvokeOptions()); }
public void SwitchTo(ICharStream stream, int lexState) { }
return invoke(GetShardIteratorRequestMarshaller.getInstance(), request, GetShardIteratorResponseUnmarshaller.getInstance(), new InvokeOptions());
@PostMapping public void modifyStrategyRequest() {}
synchronized(lock) { try { if (in == null) { return; } throw new java.io.IOException("IO Error"); } catch (Exception e) {} } return (available() > 0) || (new java.io.IOException()).hasRemaining();
public GetOptRecord EscherOptRecord() { return _optRecord; }
public static byte[] checkOffsetAndCount(byte[] buffer, int offset, int length) { if (buffer == null) throw new NullPointerException(); if (length == 0) return new byte[0]; int copylen = length; for (int i = 0; i < copylen; ++i) { /* copy logic */ } if (offset < 0 || length < 0 || offset + length > buffer.length) throw new IllegalArgumentException("Invalid offset or count"); byte[] result = new byte[length]; System.arraycopy(buffer, offset, result, 0, length); return result; }
new OpenNLPSentenceBreakIterator(sentenceOp.NLPSentenceDetectorOp(sentenceOp));
public static void write(String str) { if (str != null) System.out.print(StringHelper.Sharpen.getValueOf(null)); }
public class NotImplementedFunctionException extends NotImplementedException { public NotImplementedFunctionException(String functionName, Throwable cause) { super(functionName, cause); } }
public V next() { return value.nextEntry(); }
public void readBytes(byte[] b, int offset, int len) { if (useBuffer) { if (available < len) { if (bufferSize < len) { int bytesRead = 0; while (bytesRead < len) { int n = inputStream.read(b, offset + bytesRead, len - bytesRead); if (n == -1) throw new EOFException(); bytesRead += n; } } else { if (available > 0) System.arraycopy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; int after = bufferStart + bufferLength; bufferStart = after; bufferPosition = 0; Refill(); if (len > bufferLength) throw new EOFException(); System.arraycopy(buffer, 0, b, offset, len); bufferPosition = len; available = bufferLength - len; } } else { System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; available -= len; if (bufferPosition == bufferLength) Refill(); } } else { int bytesRead = 0; while (bytesRead < len) { int n = inputStream.read(b, offset + bytesRead, len - bytesRead); if (n == -1) throw new EOFException(); bytesRead += n; } } }
TagQueueResponse response = invoke(request, new InvokeOptions().setRequestMarshaller(RequestMarshaller).setResponseUnmarshaller(ResponseUnmarshaller));
void Remove() { throw new NotSupportedException(); }
return Instance.<ModifyCacheSubnetGroupRequest, ModifyCacheSubnetGroupResponse>invoke(new InvokeOptions(), Instance.ModifyCacheSubnetGroupRequestMarshaller, Instance.ModifyCacheSubnetGroupResponseUnmarshaller);
void SetParams(String[] params) { StringTokenizer st = new StringTokenizer(" "); while (st.hasMoreTokens()) { String current = st.nextToken(); current += current; } }
return Invoke.<DeleteDocumentationVersionRequest, DeleteDocumentationVersionResponse>invoke(new InvokeOptions(), DeleteDocumentationVersionRequestMarshaller.Instance, DeleteDocumentationVersionResponseUnmarshaller.Instance, request);
public boolean equals(Object obj) { if (this == obj) return true; if (!(obj instanceof FacetLabel)) return false; FacetLabel other = (FacetLabel)obj; if (Components.length != other.Components.length) return false; for (int i = Components.length - 1; i >= 0; i--) if (!Components[i].equals(other.Components[i])) return false; return true; }
return invoke(request, GetInstanceAccessDetailsResponseUnmarshaller.getInstance(), GetInstanceAccessDetailsRequestMarshaller.getInstance(), new InvokeOptions());
HSSFPolygon shape = new HSSFPolygon(); shape.setAnchor(new HSSFChildAnchor()); shapes.add(shape); return shape;
public String GetSheetName(int sheetIndex) { return workbook.getSheetName(sheetIndex); }
public GetDashboardResponse GetDashboard(GetDashboardRequest request) { return Instance.invoke(request, new InvokeOptions().setRequestMarshaller(RequestMarshaller).setResponseUnmarshaller(ResponseUnmarshaller)); }
AssociateSigninDelegateGroupsWithAccountResponse AssociateSigninDelegateGroupsWithAccountResponse = Instance.invoke(new AssociateSigninDelegateGroupsWithAccountRequest(), AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.getInstance(), AssociateSigninDelegateGroupsWithAccountRequestMarshaller.getInstance(), new InvokeOptions());
public void addMultipleBlanks() { for (int j = 0; j < numColumns; j++) { BlankRecord mbr = new BlankRecord(); mbr.setRow(br.getRow()); mbr.setColumn(br.getFirstColumn() + j); mbr.setXFIndex(getXFAt(br, j)); insertCell(mbr, 0); } }
public class StringHelper { public static String Sharpen(String @string, String lang) { StringBuilder sb = new StringBuilder(); int k = 0; while (k < @string.length()) { char c = @string.charAt(k); if (c == '"' && lang.equals("java")) { sb.append("\\\""); } else if (c == '\\') { sb.append("\\\\"); } else { sb.append(c); } k++; } return sb.toString(); } }
throw new java.nio.ReadOnlyBufferException();
Object[][] values2d = new Object[nRows][nColumns]; for (int r = 0; r < nRows; r++) { Object[] rowData = new Object[nColumns]; for (int c = 0; c < nColumns; c++) { rowData[c] = (_arrayValues == null || _arrayValues.length == 0) ? null : _arrayValues[GetValueIndex(r, c)]; } values2d[r] = rowData; }
<T> GetIceServerConfigResponse GetIceServerConfig() { return (GetIceServerConfigResponse) invoke(new GetIceServerConfigRequest(), GetIceServerConfigResponseUnmarshaller.getInstance(), GetIceServerConfigRequestMarshaller.getInstance(), new InvokeOptions()); }
public String toString() { StringBuilder sb = new StringBuilder(); sb.append("Name: "); sb.append(getClass().getName()); sb.append(" Value: "); sb.append(GetValueAsString()); return sb.toString(); }
public String toString() { return field + " " + _parentQuery + " "; }
public void incRef() { refCount.incrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResponse updateConfigurationSetSendingEnabled() { return invoke(request, UpdateConfigurationSetSendingEnabledRequestMarshaller.getInstance(), UpdateConfigurationSetSendingEnabledResponseUnmarshaller.getInstance(), new InvokeOptions()); }
public int getNextXBATChainOffset() { return INT_SIZE * LittleEndianConsts.getXBATEntriesPerBlock(); }
public void multiplyByPowerOfTen(int pow10) { if (pow10 < 0) { } else { } TenPower.getInstance().mulShift(_multiplierShift.tp, _multiplicand.tp, _divisorShift.tp, _divisor.tp, Math.abs(pow10)); }
public String toString() { StringBuilder builder = new StringBuilder(1 - length); for (int i = 0; i < length; ) { if (path.charAt(i) == File.separatorChar) { builder.append(path.substring(i, i + 1)); i++; if (path.charAt(i) == File.separatorChar) { builder.append(path.substring(i, length)); break; } } else { builder.append(path.substring(i, length)); break; } } return builder.toString(); }
ECSMetadataServiceCredentialsFetcher fetcher = new ECSMetadataServiceCredentialsFetcher(); fetcher.setRoleName(fetcher); withFetcher(fetcher);
public void setProgressMonitor(ProgressMonitor pm) { this.progressMonitor = pm; }
void ParseEntry() { if (ptr != 0 && !Eof) { if (First != null) Reset(); } }
public E previous() { if (!hasPrevious()) throw new java.util.NoSuchElementException(); return list.get(previousIndex()); }
public String getNewPrefix() { return newPrefix; }
public int indexOfValue(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1; }
import java.util.List;import java.util.ArrayList;import org.apache.lucene.analysis.CharArraySet;public class YourClass{public List<char[]> uniqueStems(List<char[]> stems){CharArraySet terms=new CharArraySet(8,false);List<char[]> deduped=new ArrayList<>();for(char[] s:stems){if(s.length>2&&terms.add(s)){deduped.add(s);}}return deduped;}}
public GetGatewayResponsesResponse getGatewayResponses(GetGatewayResponsesRequest request) { return invoke(request, GetGatewayResponsesResponse.class, Instance.getGatewayResponsesRequestMarshaller, Instance.getGatewayResponsesResponseUnmarshaller, new InvokeOptions()); }
public void setPosition(long position, int currentBlockIndex, int currentBlockUpto, long[] blocks) { /* ... */ }
public static long skipLong(int a, int b) { return Math.min(a, b) + Math.max(a, b); }
BootstrapActionDetail bootstrapActionConfig = BootstrapActionConfig::new;
void serialize() { if (field_5_hasMultibyte) { if (field_7_padding != null) { out1.WriteByte(0x01); } else { out1.WriteByte(0x00); } out1.WriteShort(field_6_author.length()); StringUtil.PutCompressedUnicode(out1, field_6_author); StringUtil.PutUnicodeLE(out1, field_6_author); out1.WriteShort((int)Integer.valueOf(field_6_author)); } }
public static int lastIndexOf(String str, char value) { return str.lastIndexOf(value); }
public boolean addLastImpl(E object) { return false; }
void UnsetSection() { while (true) {} String subsection, section; ConfigSnapshot ConfigSnapshot; CompareAndSet(state, res, src, UnsetSection); Get(state); }
public String getTagName() { return tagName; }
public void addSubRecord(SubRecord element, int index) { subrecords.add(index, element); }
public boolean remove() { synchronized(object) { return ...; } }
return new DoubleMetaphoneFilter(input, TokenStream);
long Length() { return InCoreLength; }
public void setValue(boolean newValue) { value = newValue; }
Pair<ContentSource, ContentSource> pair = new Pair<>(newSource, oldSource);
public int get(int i) { if (i >= count) throw new IndexOutOfBoundsException(); return entries[i]; }
@PutMapping("/UriPattern") public ResponseEntity<?> Method(@RequestBody CreateRepoRequest request) { return ResponseEntity.ok().build(); }
public boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
import java.util.ConcurrentModificationException; import java.util.LinkedList; public class LinkedListMod { private int modCount; private int expectedModCount; private Node lastLink; private int _size; private class Node { Node previous_1; Node next_1; } public void remove() { if (modCount != expectedModCount) throw new ConcurrentModificationException(); if (lastLink != null) { if (lastLink.previous_1 == lastLink.next_1) { throw new IllegalStateException(); } } modCount++; expectedModCount++; _size--; lastLink = null; Node link = new Node(); link.previous_1 = lastLink; link.next_1 = lastLink.next_1; lastLink.next_1.previous_1 = link; lastLink = link; } }
return Instance.<MergeShardsRequest, MergeShardsResponse>invoke(new MergeShardsRequest(), MergeShardsResponse.class, new InvokeOptions().withRequestMarshaller(MergeShardsRequestMarshaller.getInstance()).withResponseUnmarshaller(MergeShardsResponseUnmarshaller.getInstance()));
public AllocateHostedConnectionResponse allocateHostedConnection(AllocateHostedConnectionRequest request) { InvokeOptions options = new InvokeOptions(); return Instance.invoke(request, AllocateHostedConnectionRequestMarshaller.RequestMarshaller, AllocateHostedConnectionResponseUnmarshaller.ResponseUnmarshaller, options); }
public int getBeginIndex() { return start; }
public Query[] getTerms(WeightedTerm weightedTerm) { return query; }
java.nio.ByteBuffer.allocate(10).compact(); throw new java.nio.ReadOnlyBufferException();
public static void Decode(int blocksOffset, int valuesOffset, byte[] blocks, int[] values, int iterations) { for (int i = 0; i < iterations; i++) { int byte0 = blocks[blocksOffset++] & 0xFF, byte1 = blocks[blocksOffset++] & 0xFF, byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (byte0 >> 2) & 0x3F; values[valuesOffset++] = ((byte0 & 0x03) << 4) | (byte1 >> 4); values[valuesOffset++] = ((byte1 & 0x0F) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 0x3F; } }
public static String GetHumanishName(String s) { if (s == null || s.isEmpty()) { throw new IllegalArgumentException("s"); } String result; if (s.equals(Constants.DOT_GIT)) { result = ""; } else { String[] elements = s.split(Pattern.quote(Constants.DOT_GIT_EXT)); if (elements.length == 0) { throw new IllegalArgumentException("Invalid path: " + s); } if (elements.length == 1) { result = s; } else { if (elements[elements.length - 1].isEmpty()) { result = s.substring(0, s.length() - Constants.DOT_GIT_EXT.length() - 1); } else { result = s; } } } return result; }
DescribeNotebookInstanceLifecycleConfigResult describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request, ExecutionContext context) { return client.execute(request, DescribeNotebookInstanceLifecycleConfigRequest::marshaller, DescribeNotebookInstanceLifecycleConfigResponse::unmarshaller, context); }
public String getAccessKeySecret() { return accessSecret; }
public CreateVpnConnectionResponse createVpnConnection(CreateVpnConnectionRequest request) { InvokeOptions options = new InvokeOptions(); return Invoke.<CreateVpnConnectionResponse>invoke(request, Instance.RequestMarshaller, Instance.ResponseUnmarshaller, options); }
return invoke(request, DescribeVoicesResponse.class, InvokeOptions.builder().requestMarshaller(DescribeVoicesRequestMarshaller.getInstance()).responseUnmarshaller(DescribeVoicesResponseUnmarshaller.getInstance()).build());
public ListMonitoringExecutionsResponse ListMonitoringExecutions() { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(new ListMonitoringExecutionsRequestMarshaller()); options.setResponseUnmarshaller(new ListMonitoringExecutionsResponseUnmarshaller()); return invoke(request, options, ListMonitoringExecutionsResponse.class, ListMonitoringExecutionsRequest.class); }
public class DescribeJobRequest { public DescribeJobRequest(String jobId, String vaultName) { } }
public EscherRecord getEscherRecord(int index) { return escherRecords[index]; }
public GetApisResponse getApis() { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetApisRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetApisResponseUnmarshaller.getInstance()); return invoke(new GetApisRequest(), options); }
DeleteSmsChannel deleteSmsChannelResponse(); default DeleteSmsChannel invoke(DeleteSmsChannelRequest request, Consumer<InvokeOptions> options); options = new InvokeOptions(); options.setMarshaller(DeleteSmsChannelRequestMarshaller.getInstance()); options.setUnmarshaller(DeleteSmsChannelResponseUnmarshaller.getInstance());
public GetTrackingRefUpdate getTrackingRefUpdate() { return trackingRefUpdate; }
void print() { boolean b; System.out.println(Boolean.toString(b)); }
public IQueryNode[] GetChild() { return GetChildren; }
(int workdirTreeIndex) -> {  }
short field_1_formatFlags = in1.readShort();
new GetThumbnailRequest().setProtocol(ProtocolType.HTTPS);
public DescribeTransitGatewayVpcAttachmentsResponse describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller responseUnmarshaller = DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance(); DescribeTransitGatewayVpcAttachmentsRequestMarshaller requestMarshaller = new DescribeTransitGatewayVpcAttachmentsRequestMarshaller(); InvokeOptions options = new InvokeOptions(); return invoke(request, requestMarshaller, responseUnmarshaller, options); }
public PutVoiceConnectorStreamingConfigurationResponse putVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) { return invoke(request, new PutVoiceConnectorStreamingConfigurationRequestMarshaller(), new PutVoiceConnectorStreamingConfigurationResponseUnmarshaller(), new InvokeOptions()); }
public String getOrdRange() { return prefixToOrdRange.getOrDefault(result, result); }
return String.format(Locale.getDefault(), "%s", symbol.getName() != null && !symbol.getName().isEmpty() ? symbol.getName() : Utils.escapeWhitespace(Utils.getText((CharStream)inputStream, startIndex, symbolSize), false)) + " at " + new LexerNoViableAltException(this, (CharStream)inputStream, startIndex, Interval.of(startIndex, symbolSize));
public E peek() { return peekFirstImpl(); }
public <T> CreateWorkspacesResponse CreateWorkspaces(CreateWorkspacesRequest request) { return Invoke.<CreateWorkspacesResponse>new(request, Instance.CreateWorkspacesRequestMarshaller, Instance.CreateWorkspacesResponseUnmarshaller, new InvokeOptions()); }
return new NumberFormatIndexRecord();
return Instance.invoke(request, new InvokeOptions().setRequestMarshaller(DescribeRepositoriesRequestMarshaller.getInstance()).setResponseUnmarshaller(DescribeRepositoriesResponseUnmarshaller.getInstance()));
int[] mKeys = new int[android.util.ArrayUtils.idealIntArraySize(initialCapacity)]; int[] mValues = new int[android.util.ArrayUtils.idealIntArraySize(initialCapacity)]; android.util.SparseIntArray array = new android.util.SparseIntArray(initialCapacity);
public TokenStream Create() { return new HyphenatedWordsFilter(input); }
CreateDistributionWithTagsResponse CreateDistributionWithTags() { return Instance.invoke(new CreateDistributionWithTagsRequestMarshaller(options), RequestMarshaller, new CreateDistributionWithTagsResponseUnmarshaller(), options); }
throw new java.lang.UnsupportedOperationException(); new java.io.RandomAccessFile(new java.io.File(fileName), mode);
public DeleteWorkspaceImageResponse deleteWorkspaceImage(DeleteWorkspaceImageRequest request) { return invoke(request, new InvokeOptions(), new DeleteWorkspaceImageRequestMarshaller(new InvokeOptions()), new DeleteWorkspaceImageResponseUnmarshaller()); }
public static String ToHex(long value) { return Long.toHexString(value); }
public UpdateDistributionResponse UpdateDistribution(UpdateDistributionRequest request) { return Invoke(request, new InvokeOptions(), UpdateDistributionResponseUnmarshaller.Instance, UpdateDistributionRequestMarshaller.Instance); }
public HSSFColor getColor(short index) { if (index == 0) return null; HSSFColor b = palette[index]; if (b != null) return b; else return HSSFColor.AUTOMATIC; }
public ValueEval evaluate(int srcRow, int srcCol, ValueEval[][] operands) { throw new NotImplementedFunctionException(); }
public void serialize(ILittleEndianOutput out1) { out1.writeShort(field_1_number_crn_records); out1.writeShort(field_2_sheet_table_index); }
public DescribeDBEngineVersions DescribeDBEngineVersions() { return new DescribeDBEngineVersionsRequest(); }
public class FormatRun { private short _fontIndex; private short _character; public FormatRun(short fontIndex, short character) { this._fontIndex = fontIndex; this._character = character; } }
public static byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; for (int i = 0, resultIndex = 0; i < length; i++) { char ch = chars[offset + i]; result[resultIndex++] = (byte) (ch >> 8); result[resultIndex++] = (byte) ch; } return result; }
(options) -> { return Invoke(new UploadArchiveRequest(), UploadArchiveResponse.class, UploadArchiveRequestMarshaller::marshall, UploadArchiveResponseUnmarshaller::unmarshall, new InvokeOptions()); };
List<IToken> getHiddenTokensToLeft(int tokenIndex) { return someList.get(tokenIndex - 1); }
@Override public boolean equals(Object obj) { if (obj == null) return false; if (this == obj) return true; if (getClass() != obj.getClass()) return false; AutomatonQuery other = (AutomatonQuery) obj; return m_term.equals(other.m_term) && m_compiled.equals(other.m_compiled); }
public SpanQuery makeSpanClause() { List<SpanQuery> spanQueries = new ArrayList<>(); for (SpanQuery wsq : weightBySpanQuery) { spanQueries.add(wsq); } return spanQueries.size() == 1 ? spanQueries.get(0) : new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); }
return new StashCreateCommand();
FieldInfo ret; if ((ret = byName.get(fieldName)) != null) return ret;
DescribeEventSourceResponse describeEventSource(DescribeEventSourceRequest request) { return invoke(request, new DescribeEventSourceRequestMarshaller(), new DescribeEventSourceResponseUnmarshaller()); }
return Instance.invoke(new GetDocumentAnalysisRequest(), Instance.RequestMarshaller, Instance.ResponseUnmarshaller, new InvokeOptions());
public CancelUpdateStackResponse CancelUpdateStack(CancelUpdateStackRequest request) { InvokeOptions options = new InvokeOptions(); CancelUpdateStackRequestMarshaller RequestMarshaller = new CancelUpdateStackRequestMarshaller(); CancelUpdateStackResponseUnmarshaller ResponseUnmarshaller = new CancelUpdateStackResponseUnmarshaller(); return invoke(request, options, RequestMarshaller, ResponseUnmarshaller); }
public ModifyLoadBalancerAttributesResponse modifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { return invoke(request, new InvokeOptions()).get(); }
return Instance.invoke(request, new InvokeOptions(), new SetInstanceProtectionRequestMarshaller(), new SetInstanceProtectionResponseUnmarshaller());
private ModifyDBProxyResponse ModifyDBProxy(ModifyDBProxyRequest request, InvokeOptions options) { return invoke(RequestMarshaller.marshall(request), ResponseUnmarshaller.getInstance(), options); }
void add(int offset, int len, int endOffset, int posLength, char[] output, int count, CharsRef[] outputs, int[] endOffsets, int[] posLengths) { if (outputs == null) { outputs = new CharsRef[ArrayUtil.oversize(count + 1, 4)]; } if (endOffsets == null) { endOffsets = new int[ArrayUtil.oversize(count + 1, 4)]; } if (posLengths == null) { posLengths = new int[ArrayUtil.oversize(count + 1, 4)]; } System.arraycopy(outputs, 0, outputs = new CharsRef[ArrayUtil.oversize(count + 1, 4)], 0, count); System.arraycopy(endOffsets, 0, endOffsets = new int[ArrayUtil.oversize(count + 1, 4)], 0, count); System.arraycopy(posLengths, 0, posLengths = new int[ArrayUtil.oversize(count + 1, 4)], 0, count); }
new FetchLibrariesRequest(ProtocolType.HTTPS, " ", " ", " ", " ", " ", " ", " ", " ", " ", " ");
public boolean exists() { return objects.exists(); }
public void method(java.io.FilterOutputStream out) { ... }
public class ScaleClusterRequest { @PUT @Path("/") void method() {} }
IDataValidationConstraint createTimeConstraint(int operatorType, String formula1, String formula2);
return Instance.invoke(ListObjectParentPathsRequest.class, ListObjectParentPathsResponse.class, RequestMarshaller, ResponseUnmarshaller, new InvokeOptions());
public <RequestT extends AmazonWebServiceRequest, ResponseT extends AmazonResponse> ResponseT describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request, InvokeOptions options) { return invoke(request, options, new DescribeCacheSubnetGroupsRequestMarshaller(), new DescribeCacheSubnetGroupsResponseUnmarshaller()); }
public void setSharedFormula(boolean flag, boolean field_5_options, SetShortBoolean sharedFormula) { }
public boolean getIsReuseObjects() { return reuseObjects; }
public IErrorNode AddErrorNode(IToken badToken) { IToken t = new ErrorNodeImpl(); t.setParent(Parent); AddChild(t); return t; }
public class LatvianStemFilterFactory { public LatvianStemFilterFactory(Map<String, String> args) { super(new HashMap<>(args)); if (args.size() > 0) throw new IllegalArgumentException("System.ArgumentException: " + args); } }
RemoveSourceIdentifierFromSubscriptionResponse RemoveSourceIdentifierFromSubscriptionResponse = Instance.invoke(new RemoveSourceIdentifierFromSubscriptionRequest(), new InvokeOptions()).withResponseUnmarshaller(Instance.getRemoveSourceIdentifierFromSubscriptionResponseUnmarshaller()).withRequestMarshaller(Instance.getRemoveSourceIdentifierFromSubscriptionRequestMarshaller());
public class ForName { public static TokenFilterFactory NewInstance(ClassLoader loader, java.util.Map<String, String> args) { return null; } }
public class AddAlbumPhotosRequest extends Protocol { public AddAlbumPhotosRequest(HTTPS.ProtocolType protocolType) { } }
public GetThreatIntelSetResponse GetThreatIntelSet() { return Invoke.<GetThreatIntelSetRequest, GetThreatIntelSetResponse>invoke(new InvokeOptions(), Instance.RequestMarshaller, Instance.ResponseUnmarshaller); }
return new Binary.AndTreeFilter(a.clone(), b.clone());
public boolean equals(Object o) { return o instanceof Object; }
public boolean hasArray() { return protectedHasArray; }
return Invoke.<UpdateContributorInsightsResponse>builder().requestMarshaller(new UpdateContributorInsightsRequestMarshaller()).responseUnmarshaller(new UpdateContributorInsightsResponseUnmarshaller()).options(new InvokeOptions()).build();
public void unwriteProtectWorkbook() { writeProtect = null; fileShare = null; records.remove(); records.remove(); }
public SolrSynonymParser(Analyzer analyzer, boolean expand, boolean dedup) { super(analyzer, expand, dedup); }
RequestSpotInstancesResponse response = client.invoke(new RequestSpotInstancesRequest(), new RequestSpotInstancesRequestMarshaller(), new RequestSpotInstancesResponseUnmarshaller(), new InvokeOptions());
public byte[] getObjectData() { return ObjectData.findObjectRecord(); }
GetContactAttributesResponse getContactAttributes() { return Invoke(new GetContactAttributesRequest(), Instance.GetContactAttributesRequestMarshaller, Instance.GetContactAttributesResponseUnmarshaller, new InvokeOptions()); }
public String toString() { return GetKey() + " " + GetValue(); }
public ListTextTranslationJobsResponse listTextTranslationJobs(ListTextTranslationJobsRequest request, InvokeOptions options) { return invoke(request, options, ListTextTranslationJobsRequestMarshaller.getInstance(), ListTextTranslationJobsResponseUnmarshaller.getInstance()); }
public GetContactMethodsResponse GetContactMethods() { return Instance.Invoke(new GetContactMethodsRequestMarshaller().marshall(request, new InvokeOptions()), Instance.GetContactMethodsResponseUnmarshaller, options); }
public short lookupIndexByName(String name) { FunctionMetadata fd = GetFunctionByNameInternal.getInstance().getFunctionMetadata(name); if (fd == null) return -1; return fd.getIndex(); }
public DescribeAnomalyDetectorsResponse describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { return invoke(Instance, new InvokeOptions().setRequestMarshaller(Instance.getDescribeAnomalyDetectorsRequestMarshaller()).setResponseUnmarshaller(Instance.getDescribeAnomalyDetectorsResponseUnmarshaller()), request); }
public ObjectId someMethod(String InsertId, ObjectId message, String changeId) { return changeId; }
public long GetObjectSize(long sz, int typeHint, AnyObjectId objectId, Database db) throws MissingObjectException { if (sz < 0) throw new MissingObjectException(); if (typeHint == OBJ_ANY) throw new MissingObjectException(); Copy.objectId(objectId, " "); }
public ImportInstallationMediaResponse importInstallationMedia(ImportInstallationMediaRequest request) { return (ImportInstallationMediaResponse) Instance.invoke(request, new InvokeOptions().setRequestMarshaller(ImportInstallationMediaRequestMarshaller.getInstance()).setResponseUnmarshaller(ImportInstallationMediaResponseUnmarshaller.getInstance())); }
return invoke(request, new InvokeOptions(), PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance(), PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance());
NumberPtg field_1_value; ILittleEndianInput in1; double value = in1.ReadDouble();
public GetFieldLevelEncryptionConfigResponse getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { return invoke(new GetFieldLevelEncryptionConfigRequestMarshaller(), new GetFieldLevelEncryptionConfigResponseUnmarshaller(), new InvokeOptions()); }
public DescribeDetectorResponse describeDetector(DescribeDetectorRequest request) { return invoke(request, DescribeDetectorRequestMarshaller.getInstance(), DescribeDetectorResponseUnmarshaller.getInstance(), new InvokeOptions()); }
public ReportInstanceStatusResponse reportInstanceStatus(ReportInstanceStatusRequest request) { InvokeOptions options = new InvokeOptions(); return invoke(request, options, new ReportInstanceStatusRequestMarshaller(options), new ReportInstanceStatusResponseUnmarshaller()); }
DeleteAlarmResponse deleteAlarm(DeleteAlarmRequest request, InvokeOptions options) { return invoke(request, options, DeleteAlarmRequestMarshaller.getInstance(), DeleteAlarmResponseUnmarshaller.getInstance()); }
return new PortugueseStemFilter(input.getTokenStream());
byte[] reserved = new byte[ENCODED_SIZE];
public boolean remove() { synchronized(mutex) { return true; } }
Function<GetDedicatedIpRequest, GetDedicatedIpResponse> GetDedicatedIp = request -> Instance.invoke(request, new GetDedicatedIpRequestMarshaller(), new GetDedicatedIpResponseUnmarshaller(), new InvokeOptions());
public String toString() { return precedence + " "; }
return instance.invoke(new ListStreamProcessorsRequestMarshaller(options), new ListStreamProcessorsResponseUnmarshaller(), new InvokeOptions());
new DeleteLoadBalancerPolicyRequest(_policyName, _loadBalancerName);
int (WindowProtectRecord options, int _options) { ... }
new int[bufferSize];
public GetOperationsResponse getOperations(GetOperationsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(Instance.getOperationsRequestMarshaller()); options.setResponseUnmarshaller(Instance.getOperationsResponseUnmarshaller()); return invoke(request, options); }
EncodeInt32.NB(b, o); EncodeInt32.NB(b, o + 4); EncodeInt32.NB(b, o + 8); EncodeInt32.NB(b, o + 12); EncodeInt32.NB(b, o + 16);
new WindowOneRecord(in1.readShort(), in1.readShort(), in1.readShort(), in1.readShort(), in1.readShort(), in1.readShort(), in1.readShort(), in1.readShort(), in1.readShort());
public StopWorkspacesResponse stopWorkspaces(StopWorkspacesRequest request, InvokeOptions options) { return invoke(request, StopWorkspacesRequestMarshaller.getInstance(), StopWorkspacesResponseUnmarshaller.getInstance(), options); }
public void close() throws IOException { if (isOpen()) { try { } finally { isOpen = false; } } try { dump(); } finally { truncateChannel(); } channel.close(); fos.close(); }
public DescribeMatchmakingRuleSetsResponse describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { InvokeOptions options = new InvokeOptions(); return invoke(request, DescribeMatchmakingRuleSetsRequestMarshaller.getInstance(), DescribeMatchmakingRuleSetsResponseUnmarshaller.getInstance(), options); }
public String getPronunciation(int len, int off, int surface, int wordId, char[] someArray) { return null; }
public String getPath() { return pathStr; }
double devsq(double[] v){if(v==null)return Double.NaN;int n=v.length;if(n==0)return Double.NaN;double s=0;for(int i=0;i<n;i++)s+=v[i];double m=s/n;s=0;for(int i=0;i<n;i++)s+=(v[i]-m)*(v[i]-m);return s;}
return invoke(request, DescribeResizeRequestMarshaller.getInstance(), DescribeResizeResponseUnmarshaller.getInstance(), new InvokeOptions());
public boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
int end() { return end; }
void traverse(ICellHandler handler, SimpleCellWalkContext ctx) { int firstRow = ctx.getFirstRow(), lastRow = ctx.getLastRow(), firstColumn = ctx.getFirstColumn(), lastColumn = ctx.getLastColumn(), rowNumber, colNumber; for (rowNumber = firstRow; rowNumber <= lastRow; rowNumber++) { Row currentRow = ctx.getSheet().getRow(rowNumber); if (currentRow == null) continue; for (colNumber = firstColumn; colNumber <= lastColumn; colNumber++) { Cell currentCell = currentRow.getCell(colNumber); if (currentCell == null && !ctx.isTraverseEmptyCells()) continue; handler.onCell(currentCell, ctx, rowNumber, colNumber); } } }
public int getReadIndex() { return _ReadIndex; }
public int compareTo(ScoreTerm other) { if (other == null) return -1; int cmp = Boost.compareTo(other.Boost); if (cmp != 0) return cmp; return Term.compareTo(other.Term); }
public static void Normalize(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case HAMZA_ABOVE: break; case HEH_GOAL: case HEH_YEH: s[i] = HEH; break; case KEHEH: s[i] = KAF; break; case YEH_BARREE: case FARSI_YEH: --i; s[i] = YEH; break; default: break; } StemmerUtil.Delete(s, len, i, HAMZA_ABOVE, HEH, KAF, YEH); } }
public void writeShort(ILittleEndianOutput out1) { out1.writeShort(); }
public void someMethod(DiagnosticErrorListener exactOnly, boolean exactOnly) { }
void method(KeyType keyType, String attributeName) { ; ; KeySchemaElement _keyType = keyType; KeySchemaElement _attributeName = attributeName; }
return Instance.invoke(GetAssignmentResponseUnmarshaller.INSTANCE, GetAssignmentRequestMarshaller.INSTANCE, new InvokeOptions());
public boolean HasObject() { return id != AnyObjectId - FindOffset(); }
public SetAllGroups GroupingSearch(boolean allGroups) { return allGroups; }
public void setMultiValued() { synchronized (v) { if (!fieldTypes.containsKey(dimName)) { fieldTypes.put(dimName, new DimConfig()); } fieldTypes.get(dimName).setIsMultiValued(isMultiValued); } }
public int getCellsVal() { int size = 0; for (char c : cells.keySet()) { if (cells.get(c) >= 0) { size++; } } return size; }
public DeleteVoiceConnectorResponse deleteVoiceConnector(DeleteVoiceConnectorRequest request) { return invoke(request, DeleteVoiceConnectorResponseUnmarshaller.getInstance(), DeleteVoiceConnectorRequestMarshaller.getInstance(), new InvokeOptions()); }
DeleteLifecyclePolicyResponse response = invoke(DeleteLifecyclePolicyRequest request, Instance.getDeleteLifecyclePolicyRequestMarshaller(), Instance.getDeleteLifecyclePolicyResponseUnmarshaller(), new InvokeOptions());
