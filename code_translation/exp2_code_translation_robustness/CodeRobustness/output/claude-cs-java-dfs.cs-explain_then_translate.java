void Serialize(ILittleEndianOutput out1) { out1.WriteShort(); }
void AddAll(NGit.Util.BlockList<T> src) { if (src.size == 0) { return; } int srcDirIdx = 0; for (srcDirIdx; srcDirIdx < src.tailDirIdx; ++srcDirIdx) { AddAll(src.directory[srcDirIdx]); } if (src.tailBlkIdx != 0) { AddAll(src.tailBlock, 0, src.tailBlkIdx); } }
void WriteByte(byte b) { if (outerInstance.upto == outerInstance.blockSize) { if (outerInstance.currentBlock != null) { outerInstance.blocks.Add(outerInstance.currentBlock); outerInstance.blockEnd.Add(outerInstance.upto); } outerInstance.currentBlock = new byte[outerInstance.blockSize]; outerInstance.upto = 0; } outerInstance.currentBlock[outerInstance.upto++] = b; }
// Cannot translate - invalid/malformed C# syntax
public DeleteDomainEntryResponse deleteDomainEntry(DeleteDomainEntryRequest request) { InvokeOptions options = new InvokeOptions().withRequestMarshaller(DeleteDomainEntryRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.getInstance()); return invoke(request, options); }
return fst == null ? 0 : fst.GetSizeInBytes();
String GetFullMessage() { byte[] raw = buffer; int msgB = RawParseUtils.TagMessage(raw, 0); if (msgB < 0) { return string.Empty; } Encoding enc = RawParseUtils.ParseEncoding(raw); return RawParseUtils.Decode(enc, raw, msgB, raw.Length); }
POIFSFileSystem(HeaderBlock headerBlock = new HeaderBlock(), PropertyTable _property_table = new PropertyTable(), ArrayList _documents = new ArrayList(), Object _root = null);
void Init(int address) { slice = pool.Buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; Debug.Assert(upto < slice.Length); }
return path.SetPath(path);
public ListIngestionsResponse ListIngestions(ListIngestionsRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = ListIngestionsRequestMarshaller.Instance; options.ResponseUnmarshaller = ListIngestionsResponseUnmarshaller.Instance; return Invoke(options); }
public QueryParserTokenManager(CharStream stream) { this.SwitchTo(int lexState, CharStream stream); }
public GetShardIteratorResponse GetShardIterator(GetShardIteratorRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = GetShardIteratorRequestMarshaller.Instance; options.ResponseUnmarshaller = GetShardIteratorResponseUnmarshaller.Instance; return Invoke<GetShardIteratorResponse>(options); }
// Unable to translate - invalid/incomplete C# syntax
boolean ready() { synchronized(lock) { if(in == null) { throw new System.IO.IOException(""); } try { return bytes.hasRemaining() || in.available() > 0; } catch(System.IO.IOException) { return false; } } }
return GetOptRecord();
public int read(byte[] buffer, int offset, int length) { synchronized(this) { if (buffer == null) { throw new NullPointerException(); } java.util.Arrays.checkOffsetAndCount(buffer.length, offset, length); if (length == 0) { return 0; } int copylen = Math.min(count - pos, length); if (copylen <= 0) { return 0; } for (int i = 0; i < copylen; i++) { buffer[offset + i] = (byte) buffer[pos + i]; } pos += copylen; return copylen; } }
} ; sentenceOp sentenceOp . {  ) sentenceOp NLPSentenceDetectorOp ( OpenNLPSentenceBreakIterator
} ; ) ) null ) object ( ( GetValueOf . StringHelper . Sharpen : str ? null != str ( write { ) str string ( print void
class NotImplementedFunctionException extends NotImplementedException { public NotImplementedFunctionException(String functionName, Throwable cause) { super(functionName, cause); } }
// Cannot translate: Invalid C# syntax
void ReadBytes(byte[] b, int offset, int len, boolean useBuffer) { if (useBuffer && len < bufferSize) { if (len <= available) { int available = bufferLength - bufferPosition; if (available > 0) { System.arraycopy(buffer, bufferPosition, b, offset, available); len -= available; offset += available; bufferPosition += available; } } else { if (len > 0) { System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } if (bufferLength < len) { System.arraycopy(buffer, bufferPosition, b, offset, len); throw new RuntimeException("EndOfStreamException"); } else { System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } } Refill(); } else { long after = bufferStart + bufferPosition + len; if (after > Length) throw new RuntimeException("EndOfStreamException"); bufferStart = after; bufferPosition = 0; bufferLength = 0; } }
TagQueueResponse TagQueue(TagQueueRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = TagQueueRequestMarshaller.Instance; options.ResponseUnmarshaller = TagQueueResponseUnmarshaller.Instance; return Invoke(request, options); }
void Remove() { throw new UnsupportedOperationException(); }
ModifyCacheSubnetGroupResponse ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = ModifyCacheSubnetGroupRequestMarshaller.Instance; options.ResponseUnmarshaller = ModifyCacheSubnetGroupResponseUnmarshaller.Instance; return Invoke<ModifyCacheSubnetGroupResponse>(options); }
void SetParams(String params) { StringTokenizer st = new StringTokenizer(params, " "); if(st.hasMoreTokens()) st.nextToken(); if(st.hasMoreTokens()) params + " " + st.nextToken(); if(st.hasMoreTokens()) st.nextToken() + " " + st.nextToken(); if(st.hasMoreTokens()) st.nextToken(); }
public DeleteDocumentationVersionResponse deleteDocumentationVersion(DeleteDocumentationVersionRequest request) { InvokeOptions options = new InvokeOptions().withRequestMarshaller(DeleteDocumentationVersionRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteDocumentationVersionResponseUnmarshaller.getInstance()); return invoke(options); }
public boolean equals(Object obj) { if (!(obj instanceof FacetLabel)) { return false; } FacetLabel other = (FacetLabel) obj; if (Length != other.Length) { return false; } for (int i = Length - 1; i >= 0; i--) { if (!Components[i].equals(other.Components[i])) { return false; } } return true; }
public GetInstanceAccessDetailsResponse GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = GetInstanceAccessDetailsRequestMarshaller.Instance; options.ResponseUnmarshaller = GetInstanceAccessDetailsResponseUnmarshaller.Instance; return Invoke<GetInstanceAccessDetailsResponse>(request, options); }
HSSFPolygon shape = new HSSFPolygon((HSSFChildAnchor) anchor); shape.setParent(shapes); shape.setAnchor(anchor); shapes.add(shape); shape.onCreate(); return shape;
String GetSheetName(int sheetIndex) { return GetBoundSheetRec(sheetIndex).Sheetname; }
public GetDashboardResponse getDashboard(GetDashboardRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetDashboardRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetDashboardResponseUnmarshaller.getInstance()); return invoke(options); }
return Invoke(request, new InvokeOptions().withRequestMarshaller(AssociateSigninDelegateGroupsWithAccountRequestMarshaller.getInstance()).withResponseUnmarshaller(AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.getInstance()));
void AddMultipleBlanks(MulBlankRecord mbr) { for(int j = 0; j < mbr.NumColumns; j++) { BlankRecord br = new BlankRecord(); br.XFIndex = mbr.GetXFAt(j); br.Row = mbr.Row; br.Column = mbr.FirstColumn + j; InsertCell(br); } }
public static String quote(String string) { StringBuilder sb = new StringBuilder(); sb.append("\""); int apos = 0; int k; while ((k = string.indexOf("\\", apos)) >= 0) { sb.append(string.substring(apos, k)); sb.append("\\\\"); apos = k + 2; } sb.append(string.substring(apos)); return sb.toString(); }
throw new java.nio.ReadOnlyBufferException();
ArrayPtg(Object[][] values2d) { int nColumns = values2d[0].length; int nRows = values2d.length; _nColumns = (short) nColumns; _nRows = (short) nRows; Object[] vv = new Object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { Object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[GetValueIndex(r, c)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
GetIceServerConfigResponse GetIceServerConfig(GetIceServerConfigRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = GetIceServerConfigRequestMarshaller.Instance; options.ResponseUnmarshaller = GetIceServerConfigResponseUnmarshaller.Instance; return Invoke<GetIceServerConfigResponse>(request, options); }
String ToString() { StringBuilder sb = new StringBuilder(); sb.Append(GetType().Name); sb.Append(" "); sb.Append(GetValueAsString()); sb.Append(" "); return sb.ToString(); }
return field + " " + parentQuery + " ";
void IncRef() { refCount.IncrementAndGet(); }
UpdateConfigurationSetSendingEnabledResponse UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = UpdateConfigurationSetSendingEnabledRequestMarshaller.Instance; options.ResponseUnmarshaller = UpdateConfigurationSetSendingEnabledResponseUnmarshaller.Instance; return Invoke<UpdateConfigurationSetSendingEnabledResponse>(options); }
} ; INT_SIZE . LittleEndianConsts * ) ( GetXBATEntriesPerBlock return { ) ( GetNextXBATChainOffset int
void multiplyByPowerOfTen(int pow10) { TenPower tp = TenPower.GetInstance(Math.abs(pow10)); if (pow10 < 0) { mulShift(_divisor.tp, _divisorShift.tp); } else { mulShift(_multiplicand.tp, _multiplierShift.tp); } }
String toString() { StringBuilder builder = new StringBuilder(); int length = this.length; builder.append(File.separatorChar); for(int i = 0; i < length; i++) { builder.append(getComponent(i)); if(i < (length - 1)) { builder.append(File.separatorChar); } } return builder.toString(); }
// Cannot translate: Invalid/corrupted C# syntax
void SetProgressMonitor(ProgressMonitor pm) { }
// Cannot translate: Invalid/corrupted C# syntax
if (iterator.previousIndex() >= start) { return iterator.previous(); } ; throw new java.util.NoSuchElementException();
// Cannot translate invalid C# syntax
public int indexOfValue(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1; }
List<CharsRef> UniqueStems(char[] word, int length) { IList<CharsRef> stems = Stem(word, length); if (stems.Count < 2) { return stems; } CharArraySet terms = new CharArraySet(new CharArraySet(dictionary, 8), true); #pragma warning disable 612, 618 List<CharsRef> deduped = new List<CharsRef>(); #pragma warning restore 612, 618 foreach (CharsRef s in stems) { if (!terms.Contains(s)) { deduped.Add(s); terms.Add(s); } } return deduped; }
GetGatewayResponsesResponse getGatewayResponses(GetGatewayResponsesRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = GetGatewayResponsesRequestMarshaller.getInstance(); options.responseUnmarshaller = GetGatewayResponsesResponseUnmarshaller.getInstance(); return invoke(options); }
void SetPosition(long position) { currentBlockIndex = (int)(position >> outerInstance.blockBits); currentBlock = outerInstance.blocks[currentBlockUpto]; currentBlockUpto = (int)(position & outerInstance.blockMask); }
long Skip(long n) { int s = (int) Math.min(Math.max(Available(), ptr), s); return s; }
} ; BootstrapActionConfig _bootstrapActionConfig; BootstrapActionDetail bootstrapActionConfig(BootstrapActionConfig bootstrapActionConfig) {
void Serialize(LittleEndianOutput out1) { out1.writeShort(); out1.writeShort(); out1.writeShort(); out1.writeShort(); out1.writeShort(); out1.writeShort(field_6_author.length()); out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00); if (field_5_hasMultibyte) { StringUtil.putUnicodeLE(field_6_author, out1); } else { StringUtil.putCompressedUnicode(field_6_author, out1); } if (field_7_padding != null) { out1.writeByte(Integer.parseInt(field_7_padding, CultureInfo.getInvariantCulture())); } }
int lastIndexOf(String string) { return string.lastIndexOf('@'); }
// Invalid syntax - cannot translate malformed code
void UnsetSection(String section, String subsection) { ConfigSnapshot src, res; do { src = state.Get(); res = src.UnsetSection(section, subsection); } while (!state.CompareAndSet(src, res)); }
public String getTagName() { return tagName; }
void AddSubRecord(int index, SubRecord element) { subrecords.Insert(index, element); }
// Cannot translate: Invalid C# syntax provided
return new DoubleMetaphoneFilter(input);
// Cannot translate - invalid syntax
void setValue(boolean newValue) { value = newValue; }
new Pair<ContentSource, ContentSource>(oldSource, newSource);
if (i <= count) { throw Sharpen.Extensions.CreateIndexOutOfRangeException(); } return entries[i];
} ; PUT . MethodType Method ; " " UriPattern { ) " " , " " , " " , " " , " " ( : ) ( CreateRepoRequest
// Cannot translate invalid C# syntax
void remove() { if (expectedModCount == list.modCount) { if (lastLink != null) { java.util.LinkedList<ET>.Link next_1 = lastLink.next; java.util.LinkedList<ET>.Link previous_1 = lastLink.previous; previous_1.next = next_1; next_1.previous = previous_1; if (lastLink == link) { pos--; } lastLink = null; expectedModCount++; list._size--; list.modCount++; } else { throw new System.InvalidOperationException(); } } else { throw new java.util.ConcurrentModificationException(); } }
return Invoke(new InvokeOptions().withRequestMarshaller(MergeShardsRequestMarshaller.getInstance()).withResponseUnmarshaller(MergeShardsResponseUnmarshaller.getInstance()), request);
AllocateHostedConnectionResponse response = AllocateHostedConnection(request, new InvokeOptions().setRequestMarshaller(AllocateHostedConnectionRequestMarshaller.getInstance()).setResponseUnmarshaller(AllocateHostedConnectionResponseUnmarshaller.getInstance()));
// Cannot translate - invalid/malformed source code
WeightedTerm[] GetTerms(Query query) { return ...; }
throw new java.nio.ReadOnlyBufferException();
void Decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for(int i = 0; i < iterations; i++) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (int)((uint)byte0 >> 2); int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte0 & 3) << 4) | (int)((uint)byte1 >> 4); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte1 & 15) << 2) | (int)((uint)byte2 >> 6); values[valuesOffset++] = byte2 & 63; } }
public static String getHumanishName(String path) { if (path.equals("") || path == null) throw new IllegalArgumentException(); String s = path; if (s.equals("") || LOCAL_FILE.matcher(s).matches()) { String[] elements = s.split("\\\\" + File.separatorChar + ""); } else { String[] elements = s.split(""); } if (elements.length == 0) throw new IllegalArgumentException(); String result = elements[elements.length - 1]; if (Constants.DOT_GIT.equals(result)) { result = elements[elements.length - 2]; } else { if (result.endsWith(Constants.DOT_GIT_EXT)) { result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); } } return result; }
return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance()));
public String getAccessKeySecret() { return accessSecret; }
public CreateVpnConnectionResponse createVpnConnection(CreateVpnConnectionRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = CreateVpnConnectionRequestMarshaller.getInstance(); options.responseUnmarshaller = CreateVpnConnectionResponseUnmarshaller.getInstance(); return invoke(options); }
DescribeVoicesResponse response = describeVoices(new DescribeVoicesRequest(), new InvokeOptions().withRequestMarshaller(DescribeVoicesRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeVoicesResponseUnmarshaller.getInstance()));
public ListMonitoringExecutionsResponse ListMonitoringExecutions(ListMonitoringExecutionsRequest request) { InvokeOptions options = new InvokeOptions().withRequestMarshaller(ListMonitoringExecutionsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListMonitoringExecutionsResponseUnmarshaller.getInstance()); return invoke(options); }
public DescribeJobRequest(String vaultName, String jobId) { this._vaultName = vaultName; this._jobId = jobId; }
EscherRecord GetEscherRecord(int index) { return escherRecords[index]; }
public GetApisResponse GetApis(GetApisRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = GetApisRequestMarshaller.Instance; options.ResponseUnmarshaller = GetApisResponseUnmarshaller.Instance; return Invoke(request, options); }
return Invoke(options = new InvokeOptions() {{ RequestMarshaller = DeleteSmsChannelRequestMarshaller.Instance; ResponseUnmarshaller = DeleteSmsChannelResponseUnmarshaller.Instance; }}, request);
} ; trackingRefUpdate return { ) ( GetTrackingRefUpdate TrackingRefUpdate
} ; ) ) ( toString . b ( print { ) b boolean ( print void
IQueryNode GetChild(); IQueryNode[] GetChildren();
} ; workdirTreeIndex index . {  ) workdirTreeIndex int ( NotIgnoredFilter
public AreaRecord(RecordInputStream in1) { field_1_formatFlags = in1.readShort(); }
// Cannot translate - invalid C# syntax provided
DescribeTransitGatewayVpcAttachmentsResponse response = invoke(new InvokeOptions().withRequestMarshaller(DescribeTransitGatewayVpcAttachmentsRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance()), request);
PutVoiceConnectorStreamingConfigurationResponse PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = PutVoiceConnectorStreamingConfigurationRequestMarshaller.Instance; options.ResponseUnmarshaller = PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.Instance; return Invoke<PutVoiceConnectorStreamingConfigurationResponse>(request, options); }
OrdRange GetOrdRange(String dim) { OrdRange result; prefixToOrdRange.TryGetValue(dim, out result); return result; }
if (startIndex >= 0 && startIndex < ((ICharStream)InputStream).Size()) { symbol = ((ICharStream)InputStream).GetText(Interval.Of(startIndex, ((ICharStream)InputStream).Size())); symbol = Utils.EscapeWhitespace(symbol); } else { symbol = ""; } return String.format(CultureInfo.getCurrentCulture(), "", typeof(LexerNoViableAltException), symbol.toString());
} ; ) ( peekFirstImpl return { ) ( peek E
CreateWorkspacesResponse CreateWorkspaces(CreateWorkspacesRequest request) { InvokeOptions options = new InvokeOptions().withRequestMarshaller(CreateWorkspacesRequestMarshaller.getInstance()).withResponseUnmarshaller(CreateWorkspacesResponseUnmarshaller.getInstance()); return invoke(options); }
NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = field_1_formatIndex; return rec;
public DescribeRepositoriesResponse describeRepositories(DescribeRepositoriesRequest request) { InvokeOptions options = new InvokeOptions().withRequestMarshaller(DescribeRepositoriesRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeRepositoriesResponseUnmarshaller.getInstance()); return invoke(options); }
SparseIntArray(int initialCapacity) { initialCapacity = android.util.internal.ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
return new HyphenatedWordsFilter(input);
public CreateDistributionWithTagsResponse createDistributionWithTags(CreateDistributionWithTagsRequest request) { InvokeOptions options = new InvokeOptions().withRequestMarshaller(CreateDistributionWithTagsRequestMarshaller.getInstance()).withResponseUnmarshaller(CreateDistributionWithTagsResponseUnmarshaller.getInstance()); return invoke(options); }
throw new UnsupportedOperationException();
public DeleteWorkspaceImageResponse deleteWorkspaceImage(DeleteWorkspaceImageRequest request) { InvokeOptions options = new InvokeOptions().withRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.getInstance()); return invoke(options); }
public String toHex(int value) { return toHex((long) value); }
return invoke(request, new InvokeOptions().withRequestMarshaller(UpdateDistributionRequestMarshaller.getInstance()).withResponseUnmarshaller(UpdateDistributionResponseUnmarshaller.getInstance()));
HSSFColor GetColor(short index) { if (index == HSSFColor.AUTOMATIC.getIndex()) return HSSFColor.AUTOMATIC.getInstance(); else { byte b = palette.getColor(); if (b != null) { return new CustomColor(); } ; return null; } ; }
ValueEval Evaluate(ValueEval[][] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(); }
void Serialize(LittleEndianOutput out1) { out1.writeShort((short)field_1_number_crn_records); out1.writeShort((short)field_2_sheet_table_index); }
return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());
public FormatRun(short fontIndex, short character) { this._fontIndex = fontIndex; this._character = character; }
byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[2 * length]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
return Invoke(new InvokeOptions() {{ RequestMarshaller = UploadArchiveRequestMarshaller.Instance; ResponseUnmarshaller = UploadArchiveResponseUnmarshaller.Instance; }}).UploadArchive(request);
List<IToken> GetHiddenTokensToLeft(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex - 1); }
public boolean equals(Object obj) { if (this == obj) return true; if (obj == null) return false; if (getClass() != obj.getClass()) return false; AutomatonQuery other = (AutomatonQuery) obj; if (!m_compiled.equals(other.m_compiled)) return false; if (m_term == null) { if (other.m_term != null) return false; } else if (!m_term.equals(other.m_term)) return false; return true; }
SpanQuery MakeSpanClause() { List<SpanQuery> spanQueries = new ArrayList<SpanQuery>(); for (WeightBySpanQuery wsq : weightBySpanQuery) { spanQueries.add(wsq.getKey().boost(wsq.getValue())); } if (spanQueries.size() == 1) { return spanQueries.get(0); } else { return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); } }
// Cannot translate: Original C# code is syntactically invalid
FieldInfo fieldInfo(String fieldName) { FieldInfo ret; if (byName.containsKey(fieldName)) { ret = byName.get(fieldName); } else { ret = null; } return ret; }
DescribeEventSourceResponse DescribeEventSource(DescribeEventSourceRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = DescribeEventSourceRequestMarshaller.Instance; options.ResponseUnmarshaller = DescribeEventSourceResponseUnmarshaller.Instance; return Invoke<DescribeEventSourceResponse>(request, options); }
GetDocumentAnalysisResponse GetDocumentAnalysis(GetDocumentAnalysisRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = GetDocumentAnalysisRequestMarshaller.Instance; options.ResponseUnmarshaller = GetDocumentAnalysisResponseUnmarshaller.Instance; return Invoke(request, options); }
CancelUpdateStackResponse cancelUpdateStack(CancelUpdateStackRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(CancelUpdateStackRequestMarshaller.getInstance()); options.setResponseUnmarshaller(CancelUpdateStackResponseUnmarshaller.getInstance()); return invoke(request, options); }
ModifyLoadBalancerAttributesResponse ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = ModifyLoadBalancerAttributesRequestMarshaller.Instance; options.ResponseUnmarshaller = ModifyLoadBalancerAttributesResponseUnmarshaller.Instance; return Invoke<ModifyLoadBalancerAttributesResponse>(options); }
public SetInstanceProtectionResponse setInstanceProtection(SetInstanceProtectionRequest request) { InvokeOptions options = new InvokeOptions().withRequestMarshaller(SetInstanceProtectionRequestMarshaller.getInstance()).withResponseUnmarshaller(SetInstanceProtectionResponseUnmarshaller.getInstance()); return invoke(options); }
public ModifyDBProxyResponse ModifyDBProxy(ModifyDBProxyRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = ModifyDBProxyRequestMarshaller.Instance; options.ResponseUnmarshaller = ModifyDBProxyResponseUnmarshaller.Instance; return Invoke<ModifyDBProxyResponse>(options); }
void Add(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.length) { CharsRef[] next = new CharsRef[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF)]; System.arraycopy(outputs, 0, next, 0, count); outputs = next; } if (count == endOffsets.length) { int[] next = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.length) { int[] next = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRef(); } outputs[count].copyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; ++count; }
// Cannot translate: Invalid C# syntax provided
public boolean exists() { return objects != null && !objects.isEmpty(); }
// Cannot translate: Invalid/corrupted source code
PUT.MethodType Method; "" UriPattern { ) "" , "" , "" , "" , "" ( : ) ( ScaleClusterRequest
IDataValidationConstraint CreateTimeConstraint(int operatorType, String formula1, String formula2) { return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2); }
ListObjectParentPathsResponse ListObjectParentPaths(ListObjectParentPathsRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = ListObjectParentPathsRequestMarshaller.Instance; options.ResponseUnmarshaller = ListObjectParentPathsResponseUnmarshaller.Instance; return Invoke<ListObjectParentPathsResponse>(options); }
return invoke(new DescribeCacheSubnetGroupsRequest(request), new InvokeOptions().withRequestMarshaller(DescribeCacheSubnetGroupsRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance()));
void SetSharedFormula(boolean flag) { field_5_options = SetShortBoolean.sharedFormula; }
boolean isReuseObjects() { return reuseObjects; }
IErrorNode t = new ErrorNodeImpl(badToken); Parent.AddChild(t); return t;
class LatvianStemFilterFactory extends BaseClass implements Map<String, String> { public LatvianStemFilterFactory(Map<String, String> args) { super(args); if (args.size() > 0) { throw new IllegalArgumentException(" " + args); } } }
RemoveSourceIdentifierFromSubscriptionResponse RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = RemoveSourceIdentifierFromSubscriptionRequestMarshaller.Instance; options.ResponseUnmarshaller = RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.Instance; return Invoke<RemoveSourceIdentifierFromSubscriptionResponse>(request, options); }
return TokenFilterFactory.ForName(String name, Map<String, String> args) { NewInstance.loader };
} ; HTTPS . ProtocolType Protocol { ) " " , " " , " " , " " , " " ( : ) ( AddAlbumPhotosRequest
public GetThreatIntelSetResponse getThreatIntelSet(GetThreatIntelSetRequest request) { InvokeOptions options = new InvokeOptions().withRequestMarshaller(GetThreatIntelSetRequestMarshaller.getInstance()).withResponseUnmarshaller(GetThreatIntelSetResponseUnmarshaller.getInstance()); return invoke(options); }
return new Binary.AndTreeFilter(Clone.a(), Clone.b());
public override bool Equals(object o)
protected boolean hasArray() { return true; }
UpdateContributorInsightsResponse UpdateContributorInsights(UpdateContributorInsightsRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = UpdateContributorInsightsRequestMarshaller.Instance; options.ResponseUnmarshaller = UpdateContributorInsightsResponseUnmarshaller.Instance; return Invoke(request, options); }
} ; null writeProtect ; null fileShare ; ) ( Remove . records ; ) ( Remove . records { ) ( UnwriteProtectWorkbook void
SolrSynonymParser(boolean dedup, boolean expand, Analyzer analyzer) : base(analyzer, dedup) { }
return invoke(new InvokeOptions().withRequestMarshaller(RequestSpotInstancesRequestMarshaller.getInstance()).withResponseUnmarshaller(RequestSpotInstancesResponseUnmarshaller.getInstance()), request);
// Cannot translate invalid/incomplete C# code fragments
public GetContactAttributesResponse GetContactAttributes(GetContactAttributesRequest request) { InvokeOptions options = new InvokeOptions().withRequestMarshaller(GetContactAttributesRequestMarshaller.getInstance()).withResponseUnmarshaller(GetContactAttributesResponseUnmarshaller.getInstance()); return invoke(options); }
public String toString() { return GetKey() + " " + GetValue(); }
public ListTextTranslationJobsResponse listTextTranslationJobs(ListTextTranslationJobsRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = ListTextTranslationJobsRequestMarshaller.getInstance(); options.responseUnmarshaller = ListTextTranslationJobsResponseUnmarshaller.getInstance(); return invoke(options); }
return Invoke(request, new InvokeOptions() {{ RequestMarshaller = GetContactMethodsRequestMarshaller.Instance; ResponseUnmarshaller = GetContactMethodsResponseUnmarshaller.Instance; }});
short LookupIndexByName(String name) { FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name); if (fd == null) { return -1; }; return (short)(fd.Index); };
return Invoke(new InvokeOptions() { RequestMarshaller = DescribeAnomalyDetectorsRequestMarshaller.Instance, ResponseUnmarshaller = DescribeAnomalyDetectorsResponseUnmarshaller.Instance }, request);
public String insertId(String message, ObjectId changeId) { return insertId; }
long GetObjectSize(AnyObjectId objectId, int typeHint) { long sz = db.GetObjectSize(objectId, typeHint); if (sz < 0) { if (typeHint == OBJ_ANY) { throw new MissingObjectException(objectId.Copy(), ""); } ; throw new MissingObjectException(objectId.Copy(), ""); } ; return sz; }
public ImportInstallationMediaResponse ImportInstallationMedia(ImportInstallationMediaRequest request) { InvokeOptions options = new InvokeOptions().withRequestMarshaller(ImportInstallationMediaRequestMarshaller.getInstance()).withResponseUnmarshaller(ImportInstallationMediaResponseUnmarshaller.getInstance()); return invoke(request, options); }
public PutLifecycleEventHookExecutionStatusResponse putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance()); options.setResponseUnmarshaller(PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance()); return invoke(request, options); }
NumberPtg(ILittleEndianInput in1) { field_1_value = in1.ReadDouble(); }
GetFieldLevelEncryptionConfigResponse GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = GetFieldLevelEncryptionConfigRequestMarshaller.Instance; options.ResponseUnmarshaller = GetFieldLevelEncryptionConfigResponseUnmarshaller.Instance; return Invoke<GetFieldLevelEncryptionConfigResponse>(options); }
return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeDetectorRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeDetectorResponseUnmarshaller.getInstance()));
return this.<ReportInstanceStatusResponse>invoke(request, new InvokeOptions().withRequestMarshaller(ReportInstanceStatusRequestMarshaller.getInstance()).withResponseUnmarshaller(ReportInstanceStatusResponseUnmarshaller.getInstance()));
public DeleteAlarmResponse deleteAlarm(DeleteAlarmRequest request) { InvokeOptions options = new InvokeOptions().withRequestMarshaller(DeleteAlarmRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteAlarmResponseUnmarshaller.getInstance()); return invoke(request, options); }
return new PortugueseStemFilter(input);
byte[] reserved = new byte[ENCODED_SIZE];
// Cannot translate: Invalid C# syntax provided
GetDedicatedIpResponse GetDedicatedIp(GetDedicatedIpRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = GetDedicatedIpRequestMarshaller.Instance; options.ResponseUnmarshaller = GetDedicatedIpResponseUnmarshaller.Instance; return Invoke<GetDedicatedIpResponse>(options); }
return "(" + precedence + ")".toString();
ListStreamProcessorsResponse ListStreamProcessors(ListStreamProcessorsRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = ListStreamProcessorsRequestMarshaller.Instance; options.ResponseUnmarshaller = ListStreamProcessorsResponseUnmarshaller.Instance; return Invoke<ListStreamProcessorsResponse>(options); }
public DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) { this._loadBalancerName = loadBalancerName; this._policyName = policyName; }
} ; options _options {  ) options int ( WindowProtectRecord
UnbufferedCharStream(int bufferSize) { int[] data = new int[bufferSize]; int n = 0; }
public GetOperationsResponse GetOperations(GetOperationsRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = GetOperationsRequestMarshaller.Instance; options.ResponseUnmarshaller = GetOperationsResponseUnmarshaller.Instance; return Invoke<GetOperationsResponse>(options); }
void CopyRawTo(byte[] b, int o) { NB.EncodeInt32(, o); NB.EncodeInt32(, o + 4); NB.EncodeInt32(, o + 8); NB.EncodeInt32(, o + 12); NB.EncodeInt32(, o + 16); }
WindowOneRecord(RecordInputStream in1) { field_1_h_hold = in1.ReadShort(); field_2_v_hold = in1.ReadShort(); field_3_width = in1.ReadShort(); field_4_height = in1.ReadShort(); field_5_options = in1.ReadShort(); field_6_active_sheet = in1.ReadShort(); field_7_first_visible_tab = in1.ReadShort(); field_8_num_selected_tabs = in1.ReadShort(); field_9_tab_width_ratio = in1.ReadShort(); }
StopWorkspacesResponse stopWorkspaces(StopWorkspacesRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = StopWorkspacesRequestMarshaller.Instance; options.ResponseUnmarshaller = StopWorkspacesResponseUnmarshaller.Instance; return Invoke<StopWorkspacesResponse>(request, options); }
} } } } } ; ) ( close . fos { finally } ; ) ( close . channel { try { finally } ; ) ( truncate . channel { try { finally } ; ) ( dump { try ; isOpen { ) isOpen ( if { throws IOException ) ( close void
public DescribeMatchmakingRuleSetsResponse describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = DescribeMatchmakingRuleSetsRequestMarshaller.getInstance(); options.responseUnmarshaller = DescribeMatchmakingRuleSetsResponseUnmarshaller.getInstance(); return invoke(options); }
string GetPronunciation(int wordId, char[] surface, int off, int len) { return null; }
// Cannot translate - original C# code is malformed and incomplete
public static double devsq(double[] v) { double r = Double.NaN; if (v != null && v.length >= 1) { double m = 0; double s = 0; int n = v.length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } return r; }
DescribeResizeResponse DescribeResize(DescribeResizeRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = DescribeResizeRequestMarshaller.Instance; options.ResponseUnmarshaller = DescribeResizeResponseUnmarshaller.Instance; return Invoke<DescribeResizeResponse>(request, options); }
boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
} ; ) ( end return { ) ( end int
void Traverse(ICellHandler handler) { int firstRow = range.FirstRow; int lastRow = range.LastRow; int firstColumn = range.FirstColumn; int lastColumn = range.LastColumn; int width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); IRow currentRow = null; ICell currentCell = null; for(ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ctx.rowNumber++) { currentRow = sheet.GetRow(ctx.rowNumber); if(currentRow == null) { continue; } for(ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ctx.colNumber++) { currentCell = currentRow.GetCell(ctx.colNumber); if(currentCell == null) { continue; } if(currentCell.IsEmpty() && !traverseEmptyCells) { continue; } ctx.ordinalNumber = (ctx.rowNumber - firstRow) * width + (ctx.colNumber - firstColumn) + 1; handler.OnCell(ctx); } } }
public int getReadIndex() { return _readIndex; }
public int compareTo(ScoreTerm other) { if (Term.bytesEquals(other.Term)) return 0; if (Boost == other.Boost) return Term.compareTo(other.Term); else return Boost.compareTo(other.Boost); }
int Normalize(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = StemmerUtil.Delete(s, i, len); i--; break; default: break; } } return len; }
void Serialize(ILittleEndianOutput out1) { out1.WriteShort(); }
// Cannot translate invalid/incomplete C# syntax
public KeySchemaElement(KeyType keyType, String attributeName) { this._keyType = keyType; this._attributeName = attributeName; }
public GetAssignmentResponse GetAssignment(GetAssignmentRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = GetAssignmentRequestMarshaller.Instance; options.ResponseUnmarshaller = GetAssignmentResponseUnmarshaller.Instance; return Invoke<GetAssignmentResponse>(options); }
boolean hasObject(AnyObjectId id) { return findOffset() != -1; }
} ; return ; allGroups allGroups . { ) allGroups bool ( SetAllGroups GroupingSearch
void SetMultiValued(String dimName, boolean v) { synchronized(this) { if (!fieldTypes.containsKey(dimName)) { fieldTypes.put(dimName, new DimConfig()); } fieldTypes.get(dimName).IsMultiValued = v; } }
int GetCellsVal() { int size = 0; for (char c : cells.keySet()) { Cell e = At(c); if (e.cmd >= 0) { size++; } } return size; }
public DeleteVoiceConnectorResponse DeleteVoiceConnector(DeleteVoiceConnectorRequest request) { InvokeOptions options = new InvokeOptions().withRequestMarshaller(DeleteVoiceConnectorRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteVoiceConnectorResponseUnmarshaller.getInstance()); return invoke(request, options); }
public DeleteLifecyclePolicyResponse deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { InvokeOptions options = new InvokeOptions().withRequestMarshaller(DeleteLifecyclePolicyRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteLifecyclePolicyResponseUnmarshaller.getInstance()); return invoke(request, options); }
