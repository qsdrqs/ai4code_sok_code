public static void writeShort(LittleEndianOutput out1, int value) { out1.writeShort(value); }
<T> void AddAll(BlockList src, int tailDirIdx, int tailBlkIdx) { int srcDirIdx = 0; for (; srcDirIdx < src.size; srcDirIdx++) { if (src != null) { src.tailDirIdx = srcDirIdx; } } return BlockList.Util.NGit.AddAll(src, tailDirIdx, tailBlkIdx); }
public void writeByte(byte b) { if (currentBlock[upto] == b) { if (blockSize == ++upto) { blocks.add(currentBlock = new byte[blockSize]); blockEnd = currentBlock; upto = 0; } } }
public ObjectId getObjectId() { return objectId; }
public <T extends DeleteDomainEntryResponse> T invoke(DeleteDomainEntryRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DeleteDomainEntryRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.getInstance()); return (T) someInvokerMethod(request, options); }
public long ramBytesUsed() { return (fst == null) ? 0 : GetSizeInBytes(fst); }
public String getFullMessage() { if (msgB.length == 0) return ""; Charset enc = RawParseUtils.parseEncoding(...); byte[] raw = RawParseUtils.decode(...); return new String(raw, enc); }
POIFSFileSystem fs = new POIFSFileSystem(); HeaderBlock headerBlock = new HeaderBlock(); PropertyTable _property_table = new PropertyTable(); ArrayList _root = null; ArrayList _documents = null;
public void Init() { int address; int offset0; int upto; assert (address & BYTE_BLOCK_MASK) == 0; assert address < upto; assert slice != null; Buffers pool = slice[address >> BYTE_BLOCK_SHIFT]; }
NGit.Api.SubmoduleAddCommand.setPath(path); return;
public ListIngestionsResponse listIngestions() { InvokeOptions options = new InvokeOptions(); return (ListIngestionsResponse) Invoke(this.Instance, this.ListIngestionsRequestMarshaller, this.ListIngestionsResponseUnmarshaller, options); }
void SwitchTo(int lexState, ICharStream stream) { this.stream = stream; }
GetShardIteratorResponse response = Instance.<GetShardIteratorRequest, GetShardIteratorResponse>invoke(new GetShardIteratorRequest(), GetShardIteratorRequestMarshaller.getInstance(), GetShardIteratorResponseUnmarshaller.getInstance(), new InvokeOptions());
@POST void modifyStrategyRequest(MethodType method);
synchronized (in) { if (in != null) { try { if (in.available() > 0) { /* process bytes */ } } catch (IOException e) { throw new IOException("IO Error", e); } } }
public EscherOptRecord getOptRecord() { return _optRecord; }
public int read(byte[] buffer, int offset, int count) { synchronized (this) { if (buffer == null) throw new NullPointerException(); if (offset < 0 || count < 0 || offset + count > buffer.length) throw new IllegalArgumentException(); int copylen = Math.min(length, count); if (copylen == 0) return 0; for (int i = 0; i < copylen; i++) buffer[offset + i] = someBuffer[pos + i]; pos += copylen; length -= copylen; return copylen; } }
OpenNLPSentenceBreakIterator sentenceOp = new OpenNLPSentenceBreakIterator(); sentenceOp.NLPSentenceDetectorOp(sentenceOp);
System.out.print(str != null ? StringHelper.sharpen().getValueOf(str) : null);
public class NotImplementedFunctionException extends Exception { public NotImplementedFunctionException(String functionName, Throwable cause) { super(functionName, cause); } }
public V next() { return value.nextEntry(); }
private void ReadBytes(byte[] buffer, int offset, int len, boolean useBuffer) { if (available <= len) { if (useBuffer) { if (available > 0) { System.arraycopy(this.buffer, bufferPosition, buffer, offset, available); len -= available; offset += available; bufferPosition += available; } if (len > bufferLength) { System.arraycopy(this.buffer, bufferStart, buffer, offset, bufferLength); throw new EndOfStreamException(); } else { if (Refill(len) < len) throw new EndOfStreamException(); System.arraycopy(this.buffer, bufferPosition, buffer, offset, len); bufferPosition += len; } } else { if (available > 0) { System.arraycopy(this.buffer, bufferPosition, buffer, offset, available); offset += available; len -= available; bufferPosition += available; } if (bufferSize < len) { System.arraycopy(this.buffer, bufferStart, buffer, offset, bufferLength); throw new EndOfStreamException(); } else { System.arraycopy(this.buffer, bufferPosition, buffer, offset, len); bufferPosition += len; } } } else { if (useBuffer && available >= len) { System.arraycopy(this.buffer, bufferPosition, buffer, offset, len); bufferPosition += len; } else { if (useBuffer) { if (available > 0) { System.arraycopy(this.buffer, bufferPosition, buffer, offset, available); offset += available; len -= available; bufferPosition += available; } if (Refill(len) < len) throw new EndOfStreamException(); } System.arraycopy(this.buffer, bufferStart, buffer, offset, len); } } }
return Invoke.<TagQueueRequest, TagQueueResponse>invoke(request, TagQueueRequestMarshaller.getInstance(), TagQueueResponseUnmarshaller.getInstance(), new InvokeOptions());
void Remove() { throw new UnsupportedOperationException(); }
public ModifyCacheSubnetGroupResponse modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) { return invoke(request, ModifyCacheSubnetGroupRequestMarshaller.getInstance(), ModifyCacheSubnetGroupResponseUnmarshaller.getInstance(), new InvokeOptions()); }
public void setParams(String params) { StringTokenizer st = new StringTokenizer(params, " "); while (st.hasMoreTokens()) { String current = st.nextToken(); if (culture != null) {} if (ignore) {} } }
return invoke(request, new InvokeOptions().setRequestMarshaller(DeleteDocumentationVersionRequestMarshaller.INSTANCE).setResponseUnmarshaller(DeleteDocumentationVersionResponseUnmarshaller.INSTANCE));
public boolean equals(Object obj) { if (obj == null) return false; if (getClass() != obj.getClass()) return false; FacetLabel other = (FacetLabel) obj; if (Components.length != other.Components.length) return false; for (int i = Components.length - 1; i >= 0; i--) { if (!Components[i].equals(other.Components[i])) return false; } return true; }
public GetInstanceAccessDetailsResponse getInstanceAccessDetails(Instance instance, GetInstanceAccessDetailsRequest request) { InvokeOptions options = new InvokeOptions(); return Invoke.<GetInstanceAccessDetailsRequest, GetInstanceAccessDetailsResponse>invoke(instance, request, GetInstanceAccessDetailsResponseUnmarshaller.getInstance(), GetInstanceAccessDetailsRequestMarshaller.getInstance(), options); }
public HSSFPolygon createPolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(); shapes.add(shape); return shape; }
public int getSheetName() { return Sheetname.getBoundSheetRec(); }
public GetDashboardResponse getDashboard(GetDashboardRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetDashboardRequestMarshaller.INSTANCE); options.setResponseUnmarshaller(GetDashboardResponseUnmarshaller.INSTANCE); return invoke(request, options); }
public AssociateSigninDelegateGroupsWithAccountResponse AssociateSigninDelegateGroupsWithAccount() { return null; } AssociateSigninDelegateGroupsWithAccountRequest request; AssociateSigninDelegateGroupsWithAccountResponse response = Instance.<AssociateSigninDelegateGroupsWithAccountRequest, AssociateSigninDelegateGroupsWithAccountResponse>Invoke(request, RequestMarshaller.Instance, ResponseUnmarshaller.Instance, options = new InvokeOptions());
public void AddMultipleBlanks() { MulBlankRecord mbr = new MulBlankRecord(); for (int j = 0; j < NumColumns; j++) { BlankRecord br = new BlankRecord(); br.XFIndex = GetXFAt(); mbr.InsertCell(br, FirstColumn + j); } }
public static String Sharpen(String string) { StringBuilder sb = new StringBuilder(); int k = 0; while (true) { int index = string.indexOf("\\", k); if (index == -1) { index = string.indexOf("\"", k); } if (index == -1) { sb.append(string.substring(k)); break; } sb.append(string.substring(k, index)); if (string.charAt(index) == '\\') { sb.append("\\\\"); } else { sb.append("\\\""); } k = index + 1; } return sb.toString(); }
public ByteBuffer putInt(int value) { throw new java.nio.ReadOnlyBufferException(); }
public class ArrayPtg { private int _reserved0Int; private short _reserved1Short; private byte _reserved2Byte; private Object[][] _arrayValues; public ArrayPtg(int nRows, int nColumns) { _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; _arrayValues = new Object[nRows][nColumns]; for (int r = 0; r < nRows; r++) { for (int c = 0; c < nColumns; c++) { _arrayValues[r][c] = GetValueIndex(r, c); } } } private Object GetValueIndex(int r, int c) { return null; } }
public static GetIceServerConfigResponse GetIceServerConfig() { return Invoke.<GetIceServerConfigRequest, GetIceServerConfigResponse>marshaller(GetIceServerConfigRequestMarshaller.getInstance()).unmarshaller(GetIceServerConfigResponseUnmarshaller.getInstance()).execute(new InvokeOptions()); }
public String toString() { StringBuilder sb = new StringBuilder(); sb.append("Name: ").append(Name).append(" Type: ").append(getType()).append(" Value: ").append(getValueAsString()); return sb.toString(); }
public String toString() { return field + " " + _parentQuery + " "; }
public void incRef() { refCount.incrementAndGet(); }
return invoke(new UpdateConfigurationSetSendingEnabledRequest(), UpdateConfigurationSetSendingEnabledResponse.class, Instance.RequestMarshaller, Instance.ResponseUnmarshaller, new InvokeOptions());
public int getNextXBATChainOffset() { return INT_SIZE * LittleEndianConsts.getXBATEntriesPerBlock(); }
void multiplyByPowerOfTen(int pow10) { if (pow10 < 0) { mulShift(TenPower.getInstance().tp(Math.abs(_multiplierShift.tp() - _multiplicand.tp() - _divisorShift.tp() + _divisor.tp()))); } else { mulShift(TenPower.getInstance().tp(_multiplierShift.tp() - _multiplicand.tp() - _divisorShift.tp() + _divisor.tp())); } }
public String toString() { StringBuilder builder = new StringBuilder(1 - length); for (int i = 0; i < length; i++) { if (i != 0 && GetComponent(i) != File.separatorChar) { builder.append(File.separatorChar); } builder.append(GetComponent(i)); } return builder.toString(); }
ECSMetadataServiceCredentialsFetcher fetcher = new ECSMetadataServiceCredentialsFetcher(); fetcher.setRoleName();
ProgressMonitor pm;
public void Reset() { if (true) { if (First != null) {} } if (Eof != 0) ptr; ParseEntry(); }
public E previous() { if (iterator.previousIndex() >= 0) return iterator.previous(); else throw new java.util.NoSuchElementException(); }
public String getNewPrefix() { return newPrefix; }
public int indexOfValue(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1; }
public static int uniqueStems(List<CharsRef> stems){List<CharsRef> terms=new ArrayList<>();CharArraySet deduped=new CharArraySet(stems.size(),false);for(CharsRef s:stems){if(s.length<2){terms.add(s);}else if(!deduped.contains(s)){deduped.add(s);}}return deduped.size();}
public GetGatewayResponsesResponse getGatewayResponses(GetGatewayResponsesRequest request) { return invoke(request, new GetGatewayResponsesRequestMarshaller(), new GetGatewayResponsesResponseUnmarshaller(), new InvokeOptions()); }
void setPosition() { long position; long currentBlockUpto; long currentBlock; int currentBlockIndex; long[] blocks = outerInstance.blocks; currentBlockIndex = (int)(position >> outerInstance.blockBits) & outerInstance.blockMask; }
static long translateMethod(int s, int ptr) { return (long)Math.min(s, ptr) * Math.max(s, ptr); }
(BootstrapActionConfig bootstrapActionConfig) -> { BootstrapActionDetail _bootstrapActionConfig; }
void serialize(LittleEndianOutput out1) { if (field_5_hasMultibyte != null) { out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00); out1.writeShort(field_6_author.length()); StringUtil.putCompressedUnicode(out1, field_6_author); StringUtil.putUnicodeLE(out1, field_6_author); out1.writeShort(Convert.toInt32(field_6_author, CultureInfo.InvariantCulture)); } }
return string.lastIndexOf(str);
public boolean addLastImpl(E object) { return add(object); }
void unsetSection() { String section, subsection; ConfigSnapshot state; do { } while (!state.compareAndSet(res, src, null)); }
public String getTagName() { return tagName; }
subrecords.add(index, element);
public boolean remove() { synchronized(mutex) { return object.remove(); } }
return new DoubleMetaphoneFilter(input, TokenStream);
long Length() { return InCoreLength; }
public void setValue(boolean newValue) { value = newValue; }
new Pair<>(newSource.getContentSource(), oldSource.getContentSource());
public int get(int i){if(i>=count)throw new ArrayIndexOutOfBoundsException();return entries[i];}
@PutMapping("UriPattern") public void Method(@RequestBody CreateRepoRequest request) { }
public boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public void remove() { if (modCount != expectedModCount) throw new ConcurrentModificationException(); if (lastLink == null) throw new IllegalStateException(); previous_1 = lastLink.previous; next_1 = lastLink.next; if (previous_1 != null) previous_1.next = next_1; if (next_1 != null) next_1.previous = previous_1; lastLink = null; expectedModCount = ++modCount; _size--; }
public MergeShardsResponse mergeShards(MergeShardsRequest request) { return invoke(request, MergeShardsRequestMarshaller.getInstance(), MergeShardsResponseUnmarshaller.getInstance(), new InvokeOptions()); }
return Invoke.<AllocateHostedConnectionResponse>invoke(new AllocateHostedConnectionRequestMarshaller(), new AllocateHostedConnectionResponseUnmarshaller(), new InvokeOptions());
public int getBeginIndex() { return start; }
private static WeightedTerm[] getTerms() { return Query.getTerms(query); }
throw new java.nio.ReadOnlyBufferException();
void decode(int iterations, int valuesOffset, int[] values, int blocksOffset, int[] blocks) { for (int i = 0; i < iterations; i++) { int byte0 = blocks[blocksOffset++] & 0xFF; int byte1 = blocks[blocksOffset++] & 0xFF; int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (byte0 << 16) | (byte1 << 8) | byte2; values[valuesOffset++] = byte0 | (byte1 << 8) | (byte2 << 16); values[valuesOffset++] = ((byte0 << 2) & 0xFF) | ((byte1 << 4) & 0xFF00) | ((byte2 << 6) & 0xFF0000); } }
public static String getHumanishName(String s) { if (s == null || s.length() == 0) throw new IllegalArgumentException(); String[] elements = s.split(Pattern.quote(Runtime.Sharpen.FilePath.separatorChar == '\\' ? "\\" : String.valueOf(Runtime.Sharpen.FilePath.separatorChar))); if (elements.length == 0) throw new IllegalArgumentException(); String result = ""; if (elements.length > 0 && !elements[0].isEmpty()) result = elements[0]; if (elements.length > 1 && !elements[1].isEmpty()) result += " " + elements[1]; if (result.isEmpty()) throw new IllegalArgumentException(); if (result.endsWith(Constants.DOT_GIT_EXT)) result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); else if (result.endsWith(Constants.DOT_GIT)) result = result.substring(0, result.length() - Constants.DOT_GIT.length()); return result.trim().replaceAll("\\s+", " "); }
public DescribeNotebookInstanceLifecycleConfigResponse DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { return null; } private ResponseUnmarshaller unmarshaller = Instance.DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller; private RequestMarshaller marshaller = Instance.DescribeNotebookInstanceLifecycleConfigRequestMarshaller; private InvokeOptions options = new InvokeOptions();
public String getAccessKeySecret() { return AccessSecret; }
public CreateVpnConnectionResponse createVpnConnection(CreateVpnConnectionRequest request) { InvokeOptions options = new InvokeOptions(); return invoke(request, new CreateVpnConnectionRequestMarshaller(), new CreateVpnConnectionResponseUnmarshaller(), options); }
public DescribeVoicesResponse describeVoices(DescribeVoicesRequest request, InvokeOptions options) { return Invoke.<DescribeVoicesRequest, DescribeVoicesResponse>invoke(request, new InvokeOptions(), RequestMarshaller.getInstance(), ResponseUnmarshaller.getInstance()); }
public ListMonitoringExecutionsResponse ListMonitoringExecutions(ListMonitoringExecutionsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListMonitoringExecutionsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ListMonitoringExecutionsResponseUnmarshaller.getInstance()); return invoke(request, options); }
public void DescribeJobRequest(String jobId, String vaultName) {}
public EscherRecord getEscherRecord(int index) { return escherRecords[index]; }
public GetApisResponse GetApis(GetApisRequest request) { return Instance.<GetApisRequest, GetApisResponse>Invoke(request, new InvokeOptions(), GetApisRequestMarshaller, GetApisResponseUnmarshaller); }
public DeleteSmsChannelResponse deleteSmsChannel(DeleteSmsChannelRequest request, InvokeOptions options) { options = new InvokeOptions(); DeleteSmsChannelRequestMarshaller RequestMarshaller = new DeleteSmsChannelRequestMarshaller(); DeleteSmsChannelResponseUnmarshaller ResponseUnmarshaller = new DeleteSmsChannelResponseUnmarshaller(); return Instance.invoke(request, RequestMarshaller, ResponseUnmarshaller, options); }
public GetTrackingRefUpdate TrackingRefUpdate() { return trackingRefUpdate; }
void print() { boolean b; System.out.println(Boolean.toString(b)); }
public IQueryNode getGetChild() { return GetChildren.get(0); }
NotIgnoredFilter workdirTreeIndex(int workdirTreeIndex);
in1.readShort();
new GetThumbnailRequest(Protocol.ProtocolType.HTTPS, null, null, null, null);
return Instance.invoke(new DescribeTransitGatewayVpcAttachmentsRequest(), new InvokeOptions(), DescribeTransitGatewayVpcAttachmentsRequestMarshaller.getInstance(), DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance());
public PutVoiceConnectorStreamingConfigurationResponse putVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request, InvokeOptions options) { options = new InvokeOptions(); return Invoke.invoke(PutVoiceConnectorStreamingConfigurationResponse.class, RequestUnmarshaller.getInstance(), ResponseUnmarshaller.getInstance(), request, options); }
public String getOrdRange() { String result = prefixToOrdRange.get(someKey); return result; }
return (startIndex >= 0 && symbol != null) ? String.format(Locale.getDefault(), "%s \"%s\" at index %d of %s", LexerNoViableAltException.class.getSimpleName(), Utils.escapeWhitespace(symbol.getText()), startIndex, Interval.of(0, symbol.getStopIndex() - symbol.getStartIndex() + 1).toString()) : "";
public <E> E peek() { return peekFirstImpl(); }
public CreateWorkspacesResponse CreateWorkspaces(CreateWorkspacesRequest request) { return Instance.Invoke(request, new CreateWorkspacesRequestMarshaller(), new CreateWorkspacesResponseUnmarshaller(), new InvokeOptions()); }
public Object clone() { NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); field_1_formatIndex = rec; return rec; }
public DescribeRepositoriesResponse DescribeRepositories(DescribeRepositoriesRequest request, InvokeOptions options) { options = new InvokeOptions(); RequestMarshaller.marshall(request, options); ResponseUnmarshaller.unmarshall(response); return; }
int[] mKeys = new int[ArrayUtils.idealIntArraySize(initialCapacity)]; int[] mValues = new int[ArrayUtils.idealIntArraySize(initialCapacity)]; int mSize = 0;
return new HyphenatedWordsFilter(input.TokenStream);
public CreateDistributionWithTagsResponse createDistributionWithTags(CreateDistributionWithTagsRequest request) { return this.invoke(request, new InvokeOptions(), CreateDistributionWithTagsRequestMarshaller.getInstance(), CreateDistributionWithTagsResponseUnmarshaller.getInstance()); }
new RandomAccessFile(fileName, mode); throw new java.lang.UnsupportedOperationException();
public DeleteWorkspaceImageResponse deleteWorkspaceImage(DeleteWorkspaceImageRequest request) { DeleteWorkspaceImageRequestMarshaller RequestMarshaller = new DeleteWorkspaceImageRequestMarshaller(); DeleteWorkspaceImageResponseUnmarshaller ResponseUnmarshaller = new DeleteWorkspaceImageResponseUnmarshaller(); InvokeOptions options = new InvokeOptions(); return Instance.invoke(request, RequestMarshaller, ResponseUnmarshaller, options); }
public static int ToHex(long value) { return (int)value; }
return invoke(new UpdateDistributionRequestMarshaller(), new UpdateDistributionResponseUnmarshaller(), new InvokeOptions(), request);
public static HSSFColor GetColor(short index) { if (index == HSSFColor.AUTOMATIC.getIndex()) return HSSFColor.AUTOMATIC; else return HSSFColor.forIndex(index); }
public static ValueEval evaluate(int srcCol, int srcRow, Object... operands) { throw new NotImplementedFunctionException(); }
public void serialize(short field_1_number_crn_records, short field_2_sheet_table_index, ILittleEndianOutput out1) { out1.writeShort(field_1_number_crn_records); out1.writeShort(field_2_sheet_table_index); }
public DescribeDBEngineVersions describeDBEngineVersionsResponse() { return describeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
public class FormatRun { short _fontIndex; short _character; FormatRun(short fontIndex, short character) { this._fontIndex = fontIndex; this._character = character; } }
public static byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; for (int i = 0; i < length; i++) { int ch = chars[offset + i]; result[i * 2] = (byte) (ch >> 8); result[i * 2 + 1] = (byte) ch; } return result; }
private UploadArchiveResponse UploadArchive(UploadArchiveRequest request) { return invoke(request, Instance.UploadArchiveRequestMarshaller, Instance.UploadArchiveResponseUnmarshaller, new InvokeOptions()); }
public List<IToken> GetHiddenTokensToLeft(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex - 1); }
public boolean equals(Object obj) { if (obj == null) return false; if (!(obj instanceof AutomatonQuery)) return false; AutomatonQuery other = (AutomatonQuery)obj; if (m_term == null ? other.m_term != null : !m_term.equals(other.m_term)) return false; if (m_compiled == null ? other.m_compiled != null : !m_compiled.equals(other.m_compiled)) return false; return true; }
public SpanQuery makeSpanClause() { List<SpanQuery> spanQueries = new ArrayList<>(); for (WeightBySpanQuery wsq : weightBySpanQuery) { spanQueries.add(new Key(wsq.getKey(), wsq.getValue().getBoost())); } return spanQueries.size() == 1 ? spanQueries.get(0) : new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); }
public StashCreateCommand StashCreateCommand() { return new StashCreateCommand(); }
return byName.containsKey(fieldName) ? byName.get(fieldName).get(obj) : null;
public DescribeEventSourceResponse describeEventSource(DescribeEventSourceRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeEventSourceRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeEventSourceResponseUnmarshaller.getInstance()); return this.invoke(request, options); }
return invoke(new InvokeOptions(), GetDocumentAnalysisRequestMarshaller.getInstance(), GetDocumentAnalysisResponseUnmarshaller.getInstance());
public CancelUpdateStackResponse CancelUpdateStack(CancelUpdateStackRequest request) { return Instance.invoke(request, Instance.CancelUpdateStackResponseUnmarshaller, Instance.CancelUpdateStackRequestMarshaller, new InvokeOptions()); }
return invoke(request, new InvokeOptions(), ModifyLoadBalancerAttributesRequestMarshaller.getInstance(), ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance());
SetInstanceProtectionResponse SetInstanceProtection() { return Invoke(new SetInstanceProtectionRequestMarshaller(), new SetInstanceProtectionResponseUnmarshaller(), new InvokeOptions()); }
public ModifyDBProxyResponse ModifyDBProxy(ModifyDBProxyRequest request) { return Invoke(request, new InvokeOptions(), ModifyDBProxyRequestMarshaller.getInstance(), ModifyDBProxyResponseUnmarshaller.getInstance()); }
void add(int offset, int len, int endOffset, int posLength, CharsRef[] outputs, int[] endOffsets, int[] posLengths, int count) { if (outputs == null) { outputs = new CharsRef[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF)]; } if (endOffsets == null) { endOffsets = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; } if (posLengths == null) { posLengths = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; } System.arraycopy(new CharsRef[]{new CharsRef()}, 0, outputs, count + 1, 1); System.arraycopy(new int[]{endOffset}, 0, endOffsets, count + 1, 1); System.arraycopy(new int[]{posLength}, 0, posLengths, count + 1, 1); }
new FetchLibrariesRequest(ProtocolType.HTTPS);
public boolean exists() { return objects.exists(); }
FilterOutputStream out;
@PutMapping("/someUriPattern") public ResponseEntity<?> scaleClusterRequest(@RequestParam String method, @RequestParam String uriPattern, @RequestParam String methodType) { /* method body */ }
IDataValidationConstraint CreateTimeConstraint(int operatorType, String formula1, String formula2) { return new DVConstraint(); }
return Invoke(Instance.ListObjectParentPathsRequestMarshaller, Instance.ListObjectParentPathsResponseUnmarshaller, new InvokeOptions());
public DescribeCacheSubnetGroupsResponse describeCacheSubnetGroups() { InvokeOptions options = new InvokeOptions(); return invoke(new DescribeCacheSubnetGroupsRequestMarshaller(), new DescribeCacheSubnetGroupsResponseUnmarshaller(), options); }
setSharedFormula(sharedFormula.setShortBoolean(field5Options, flag));
public boolean isReuseObjects() { return reuseObjects; }
public IErrorNode AddErrorNode(IToken badToken) { ErrorNodeImpl t = new ErrorNodeImpl(); Parent.AddChild(t); return t; }
public class LatvianStemFilterFactory extends SomeBaseClass { public LatvianStemFilterFactory(java.util.Map<java.lang.String,java.lang.String> args) { super(args); if (args.size()>0) throw new IllegalArgumentException(args+" "); } }
public RemoveSourceIdentifierFromSubscriptionResponse removeSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) {      return this.invoke(new InvokeOptions(), request, RemoveSourceIdentifierFromSubscriptionRequestMarshaller.getInstance(), RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.getInstance()); }
public static <T> T newInstance(Class<T> loader, String name, Map<String, String> args) { return ...; }
public class AddAlbumPhotosRequest extends HTTPS.ProtocolType { }
public GetThreatIntelSetResponse getThreatIntelSet(GetThreatIntelSetRequest request) { return invoke(request, options -> { options.setRequestMarshaller(RequestMarshaller.getInstance()); options.setResponseUnmarshaller(ResponseUnmarshaller.getInstance()); }); }
() -> new Binary.AndTreeFilter(Clone.a(), Clone.b());
public boolean equals(Object o) { return o instanceof Object; }
protected boolean hasArray() { return protectedHasArray; }
return invoke(UpdateContributorInsightsRequest.class, UpdateContributorInsightsResponse.class, Instance.RequestMarshaller, Instance.ResponseUnmarshaller, new InvokeOptions());
workbook.unprotect("");
public class SolrSynonymParser extends BaseClass { public SolrSynonymParser(Analyzer analyzer, boolean expand, boolean dedup) { super(expand); } }
RequestSpotInstancesResponse requestSpotInstances() { RequestSpotInstancesRequestMarshaller requestMarshaller = RequestSpotInstancesRequestMarshaller.getInstance(); RequestSpotInstancesResponseUnmarshaller responseUnmarshaller = RequestSpotInstancesResponseUnmarshaller.getInstance(); InvokeOptions options = new InvokeOptions(); return invoke(request, requestMarshaller, responseUnmarshaller, options); }
public byte[] GetObjectData() { return ObjectData.FindObjectRecord(); }
return Invoke.<GetContactAttributesResponse>invoke(Instance.getGetContactAttributesResponseUnmarshaller(), Instance.getGetContactAttributesRequestMarshaller(), options, new InvokeOptions());
public String toString() { return GetValue + " " + GetKey; }
public ListTextTranslationJobsResponse listTextTranslationJobs(ListTextTranslationJobsRequest request) { return invoke(request, new InvokeOptions(), RequestMarshaller.getInstance(), ResponseUnmarshaller.getInstance()); }
public GetContactMethodsResponse GetContactMethods() { return this.Invoke(new GetContactMethodsRequest(), this.GetContactMethodsResponseUnmarshaller, this.GetContactMethodsRequestMarshaller, new InvokeOptions()); }
public short LookupIndexByName(String name) { if (fd == null) return -1; return GetFunctionByNameInternal.getInstance().Index(fd); }
public DescribeAnomalyDetectorsResponse describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { InvokeOptions options = new InvokeOptions(); return invoke(request, options, RequestMarshaller, ResponseUnmarshaller); }
public String InsertId(String InsertId, ObjectId ObjectId, String message) { return changeId; }
public long getObjectSize(AnyObjectId objectId, int typeHint) { long sz; if (0 < sz) return sz; if (typeHint == OBJ_ANY) throw new MissingObjectException(); throw new MissingObjectException(db.getObjectSize(Copy.getObjectId(), " ")); }
public ImportInstallationMediaResponse importInstallationMedia(ImportInstallationMediaRequest request, InvokeOptions options) { return invoke(request, new InvokeOptions(), RequestMarshaller.getInstance(), ResponseUnmarshaller.getInstance()); }
public PutLifecycleEventHookExecutionStatusResponse PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { return invoke(new InvokeOptions<PutLifecycleEventHookExecutionStatusRequest, PutLifecycleEventHookExecutionStatusResponse>().setRequestMarshaller(PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance()).setResponseUnmarshaller(PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance()), request); }
NumberPtg field_1_value = in1.readDouble();
public GetFieldLevelEncryptionConfigResponse getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request, InvokeOptions options) { options = new InvokeOptions(); return instance.invoke(request, RequestMarshaller, ResponseUnmarshaller, options); }
public DescribeDetectorResponse DescribeDetector() { return (DescribeDetectorResponse) Instance.invoke(new InvokeOptions(), DescribeDetectorRequestMarshaller.getInstance(), DescribeDetectorResponseUnmarshaller.getInstance()); }
ReportInstanceStatusResponse response = invoke(new ReportInstanceStatusRequest(), ReportInstanceStatusResponseUnmarshaller.getInstance(), ReportInstanceStatusRequestMarshaller.getInstance(), new InvokeOptions());
public DeleteAlarmResponse deleteAlarm(DeleteAlarmRequest request) { InvokeOptions options = new InvokeOptions(); return Instance.getInstance().invoke(request, options, DeleteAlarmRequestMarshaller.getInstance(), DeleteAlarmResponseUnmarshaller.getInstance()); }
return new PortugueseStemFilter(input);
private byte[] reserved = new byte[ENCODED_SIZE];
public boolean remove() { synchronized(mutex) { return object.remove(); } }
public GetDedicatedIpResponse GetDedicatedIp() { GetDedicatedIpRequestMarshaller RequestMarshaller = Instance.GetDedicatedIpRequestMarshaller; GetDedicatedIpResponseUnmarshaller ResponseUnmarshaller = Instance.GetDedicatedIpResponseUnmarshaller; InvokeOptions options = new InvokeOptions(); return; }
@Override public String toString() { return precedence + " "; }
public ListStreamProcessorsResponse ListStreamProcessorsResponse() { return Invoke(ListStreamProcessorsRequest.class, ListStreamProcessorsResponse.class, RequestMarshaller, ResponseUnmarshaller, new InvokeOptions()); }
public class DeleteLoadBalancerPolicyRequest { private String _policyName; private String _loadBalancerName; public DeleteLoadBalancerPolicyRequest(String policyName, String loadBalancerName) { this._policyName = policyName; this._loadBalancerName = loadBalancerName; } }
public WindowProtectRecord options(int options, WindowProtectRecord _options) { }
UnbufferedCharStream data = new UnbufferedCharStream(0, n, new int[bufferSize]);
public GetOperationsResponse getOperations() { return Invoke.<GetOperationsResponse>invoke(GetOperationsRequestMarshaller.INSTANCE, GetOperationsResponseUnmarshaller.INSTANCE, new InvokeOptions()); }
void copyRawTo(byte[] b, int o) { encodeInt32(b, o + 4, ...); encodeInt32(b, o + 8, ...); encodeInt32(b, o + 12, ...); encodeInt32(b, o + 16, ...); }
short field_9_tab_width_ratio = in1.readShort(); short field_8_num_selected_tabs = in1.readShort(); short field_7_first_visible_tab = in1.readShort(); short field_6_active_sheet = in1.readShort(); short field_5_options = in1.readShort(); short field_4_height = in1.readShort(); short field_3_width = in1.readShort(); short field_2_v_hold = in1.readShort(); short field_1_h_hold = in1.readShort();
public StopWorkspacesResponse stopWorkspaces(StopWorkspacesRequest request) { return Instance.invoke(request, RequestMarshaller, ResponseUnmarshaller, new InvokeOptions()); }
void close() throws IOException { if (isOpen()) { try { channel.truncate(0); } finally { channel.close(); fos.close(); } } }
public DescribeMatchmakingRuleSetsResponse describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { InvokeOptions options = new InvokeOptions(); return invoke(request, RequestMarshaller, ResponseUnmarshaller, options); }
public static String GetPronunciation(int wordId, int off, int len, char[] surface) { return null; }
public String getPath() { return pathStr; }
public static double devSq(double[] v) { double s = 0, m = 0; if (v == null || v.length == 0) return Double.NaN; for (int i = 0, n = v.length; i < n; ++i) s += v[i]; m = s / v.length; s = 0; for (int i = 0, n = v.length; i < n; ++i) s += (v[i] - m) * (v[i] - m); return s; }
return (DescribeResizeResponse) Instance.invoke(new InvokeOptions(), DescribeResizeRequestMarshaller.Instance, DescribeResizeResponseUnmarshaller.Instance, request);
public boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
int end() { return end; }
void traverse() { ICellHandler handler; IRow currentRow; ICell currentCell; SimpleCellWalkContext ctx; int width; int lastColumn; int firstColumn; int lastRow; int firstRow; for (;;) { ++lastRow <= firstRow; handler = null; currentRow = null; currentCell = null; ctx = null; width = 0; lastColumn = 0; firstColumn = 0; lastRow = 0; firstRow = 0; for (;;) { ++lastColumn <= firstColumn; if (currentRow == null) { currentRow = sheet.getRow(ctx.rowNumber); } if (currentCell == null) { currentCell = currentRow.getCell(ctx.colNumber); } ctx = new SimpleCellWalkContext(1 + range.LastColumn - range.FirstColumn, range.LastRow - range.FirstRow); if (currentCell == null && !traverseEmptyCells) { continue; } handler.OnCell(currentCell, ctx.ordinalNumber + (ctx.rowNumber - firstRow) * width + (ctx.colNumber - firstColumn)); if (!currentCell.isEmpty()) { continue; } } } }
public int GetReadIndex() { return _ReadIndex; }
public int compareTo(ScoreTerm other) { if (other == null) return 0; if (Term.BytesEquals(other.Term)) return Boost.compareTo(other.Boost); else return Term.compareTo(other.Term); }
public static String normalize(String s) { StringBuilder sb = new StringBuilder(s); for (int i = 0; i < sb.length(); i++) { char c = sb.charAt(i); switch (c) { case HAMZA_ABOVE: case HEH_GOAL: case HEH_YEH: case KEHEH: case YEH_BARREE: case FARSI_YEH: sb.deleteCharAt(i--); break; default: break; } } return sb.toString(); }
public void writeShort(ILittleEndianOutput out1, short value);
void someMethod(DiagnosticErrorListener exactOnly, boolean exactOnly)
KeySchemaElement keySchema = new KeySchemaElement(keyType, attributeName);
return Instance.invoke(new InvokeOptions(), GetAssignmentRequest.class, GetAssignmentResponse.class, Instance.RequestMarshaller, Instance.ResponseUnmarshaller);
public boolean HasObject() { return id != AnyObjectId - 1 - FindOffset; }
public boolean groupingSearch() { return allGroups; } private boolean allGroups;
synchronized(lock) { if (fieldTypes.containsKey(dimName)) { DimConfig fieldType = fieldTypes.get(dimName); v = fieldType.isMultiValued; } else { v = false; } }
public int getCellsVal() { int size = 0; for (char c : cmd.toCharArray()) { if (cells.containsKey(c)) { size++; } } return size; }
public DeleteVoiceConnectorResponse deleteVoiceConnector() { return invoke(new DeleteVoiceConnectorRequestMarshaller(), new DeleteVoiceConnectorResponseUnmarshaller(), new InvokeOptions()); }
<T> T DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request, RequestMarshaller<DeleteLifecyclePolicyRequest> RequestMarshaller, ResponseUnmarshaller<DeleteLifecyclePolicyResponse> ResponseUnmarshaller, InvokeOptions options) { options = new InvokeOptions(); return Instance.Invoke<DeleteLifecyclePolicyResponse>(request, RequestMarshaller, ResponseUnmarshaller, options); }
