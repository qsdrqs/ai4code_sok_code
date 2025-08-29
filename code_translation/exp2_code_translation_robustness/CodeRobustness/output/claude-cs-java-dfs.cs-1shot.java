void serialize(ILittleEndianOutput out1) { out1.writeShort(); }
void AddAll(NGit.Util.BlockList<T> src) { if (src.size() == 0) { return; } int srcDirIdx = 0; for (srcDirIdx; srcDirIdx < src.tailDirIdx; srcDirIdx++) { AddAll(src.directory[srcDirIdx]); } if (src.tailBlkIdx != 0) { AddAll(src.tailBlock.src, src.tailBlkIdx); } }
void writeByte(byte b) { if (outerInstance.upto == outerInstance.blockSize) { if (outerInstance.currentBlock != null) { outerInstance.blocks.add(outerInstance.currentBlock); outerInstance.blockEnd.add(outerInstance.upto); } outerInstance.currentBlock = new byte[outerInstance.blockSize]; outerInstance.upto = 0; } outerInstance.currentBlock[outerInstance.upto++] = b; }
return getObjectId();
DeleteDomainEntryResponse DeleteDomainEntry(DeleteDomainEntryRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = DeleteDomainEntryRequestMarshaller.Instance; options.ResponseUnmarshaller = DeleteDomainEntryResponseUnmarshaller.Instance; return Invoke<DeleteDomainEntryResponse>(options); }
long ramBytesUsed() { return fst == null ? 0 : fst.getSizeInBytes(); }
public String getFullMessage() { byte[] raw = buffer; int msgB = RawParseUtils.tagMessage(raw, 0); if (msgB < 0) { return string.empty; } Encoding enc = RawParseUtils.parseEncoding(raw); return RawParseUtils.decode(enc, raw, msgB, raw.length); }
}; null _root; )(ArrayList new _documents; )(PropertyTable new _property_table; )(HeaderBlock new = headerBlock HeaderBlock{ )(POIFSFileSystem
void Init(int address) { slice = pool.Buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; Debug.Assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; Debug.Assert(upto < slice.Length); }
} ; return ; path path . { ) path string ( setPath SubmoduleAddCommand . api . jgit
public ListIngestionsResult listIngestions(ListIngestionsRequest request) { request = beforeClientExecution(request); return executeListIngestions(request); }
; ) ( SwitchTo { ) stream ( this : ) lexState int , stream ICharStream ( QueryParserTokenManager
public GetShardIteratorResponse getShardIterator(GetShardIteratorRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = GetShardIteratorRequestMarshaller.getInstance(); options.responseUnmarshaller = GetShardIteratorResponseUnmarshaller.getInstance(); return invoke(options); }
} ; POST . MethodType Method { ) " " , " " , " " , " " , " " ( : ) ( ModifyStrategyRequest
} } } ; return { ) IOException . IO . System ( catch } ; 0 > ) ( available . in || ) ( hasRemaining . bytes return { try } ; ) " " ( IOException . IO . System new throw { ) null == in ( if { ) lock ( lock { ) ( ready boolean
return ((EscherOptRecord) getOptRecord());
int read(byte[] buffer, int offset, int length) { synchronized(this) { if (buffer == null) { throw new IllegalArgumentException(""); } java.util.Arrays.checkOffsetAndCount(buffer.length, offset, length); if (length == 0) { return 0; } int copylen = count - pos < length ? count - pos : length; for (int i = 0; i < copylen; i++) { buffer[offset + i] = (byte) (unchecked(buffer[pos + i])); } pos += copylen; return copylen; } }
} ; sentenceOp sentenceOp . {  ) sentenceOp NLPSentenceDetectorOp ( OpenNLPSentenceBreakIterator
} ; ) ) null ) object ( ( GetValueOf . StringHelper . Sharpen : str ? null != str ( write { ) str string ( print void
} ; functionName functionName . {  ) cause , functionName ( super : ) cause NotImplementedException , functionName String ( NotImplementedFunctionException
return nextEntry.getValue();
void readBytes(byte[] b, int offset, int len, boolean useBuffer) { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) { System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } } else { if (available > 0) { System.arraycopy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (len < bufferSize && useBuffer) { refill(); if (bufferLength < len) { System.arraycopy(buffer, bufferPosition, b, offset, bufferLength); throw new EOFException(); } else { System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } } else { long after = bufferStart + bufferPosition + len; if (after > length) { throw new EOFException(); } else { long after = bufferStart + bufferPosition + len; bufferPosition = 0; bufferLength = 0; } } } }
public TagQueueResult tagQueue(TagQueueRequest request) {request = beforeClientExecution(request);return executeTagQueue(request);}
public void Remove() { throw new UnsupportedOperationException(); }
public ModifyCacheSubnetGroupResult modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) {request = beforeClientExecution(request);return executeModifyCacheSubnetGroup(request);}
void setParams(String params) { setParams(params); String culture = " "; StringTokenizer st = new StringTokenizer(","); if (st.moveNext()) { culture = st.current(); } if (st.moveNext()) { culture = " " + st.current(); } if (st.moveNext()) { culture = st.current(); } }
DeleteDocumentationVersionResponse DeleteDocumentationVersion(DeleteDocumentationVersionRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = DeleteDocumentationVersionRequestMarshaller.Instance; options.ResponseUnmarshaller = DeleteDocumentationVersionResponseUnmarshaller.Instance; return Invoke<DeleteDocumentationVersionResponse>(options); }
public boolean equals(Object obj) { if (!(obj instanceof FacetLabel)) { return false; } FacetLabel other = (FacetLabel) obj; if (length != other.length) { return false; } for (int i = length - 1; i >= 0; i--) { if (!components[i].equals(other.components[i], StringComparison.Ordinal)) { return false; } } return true; }
public GetInstanceAccessDetailsResponse getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = GetInstanceAccessDetailsRequestMarshaller.getInstance(); options.responseUnmarshaller = GetInstanceAccessDetailsResponseUnmarshaller.getInstance(); return invoke(options); }
HSSFPolygon shape = new HSSFPolygon((HSSFChildAnchor) anchor); shape.setParent(this); shapes.add(shape); onCreate(shape); return shape;
String GetSheetName(int sheetIndex) { return GetBoundSheetRec(sheetIndex).Sheetname; }
GetDashboardResponse getDashboard(GetDashboardRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = GetDashboardRequestMarshaller.getInstance(); options.responseUnmarshaller = GetDashboardResponseUnmarshaller.getInstance(); return invoke(options); }
AssociateSigninDelegateGroupsWithAccountResponse AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = AssociateSigninDelegateGroupsWithAccountRequestMarshaller.Instance; options.ResponseUnmarshaller = AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.Instance; return Invoke<AssociateSigninDelegateGroupsWithAccountResponse>(options); }
void addMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < mbr.numColumns; j++) { BlankRecord br = new BlankRecord(mbr.row, mbr.firstColumn + j, mbr.getXFAt(j)); insertCell(br); } }
public static String quote(String string) { java.lang.StringBuilder sb = new java.lang.StringBuilder(); sb.append("\""); int apos = 0; int k; while ((k = string.indexOf("\\", apos)) >= 0) { sb.append(Sharpen.StringHelper.substring(string, apos, k + 2)); sb.append("\\\\\\"); apos = k + 2; } sb.append(Sharpen.StringHelper.substring(string, apos)); sb.append("\""); return sb.toString(); }
java.nio.ByteBuffer.putInt(int value) { throw new java.nio.ReadOnlyBufferException(); }
ArrayPtg(Object[][] values2d) { int nColumns = values2d[0].Length; int nRows = values2d.Length; _nColumns = (short)nColumns; _nRows = (short)nRows; Object[] vv = new Object[_nColumns * _nRows]; for(int r = 0; r < nRows; ++r) { Object[] rowData = values2d[r]; for(int c = 0; c < nColumns; ++c) { vv[GetValueIndex(c, r)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
GetIceServerConfigResponse GetIceServerConfig(GetIceServerConfigRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = GetIceServerConfigRequestMarshaller.Instance; options.ResponseUnmarshaller = GetIceServerConfigResponseUnmarshaller.Instance; return Invoke<GetIceServerConfigResponse>(options); }
StringBuilder sb = new StringBuilder(); sb.append(getClass().getName()); sb.append(" "); sb.append(getValueAsString()); sb.append(" "); return sb.toString();
} ; " " + _parentQuery + " " return { ) field string ( toString string
void IncRef() { refCount.IncrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResult updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { request = beforeClientExecution(request); return executeUpdateConfigurationSetSendingEnabled(request); }
return GetXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE; } int GetNextXBATChainOffset() {
void multiplyByPowerOfTen(int pow10) { TenPower tp = TenPower.getInstance(Math.abs(pow10)); if (pow10 < 0) { mulShift(_divisor.tp, _divisorShift.tp); } else { mulShift(_multiplicand.tp, _multiplierShift.tp); } }
StringBuilder builder = new StringBuilder(); int length = length(); builder.append(Path.directorySeparatorChar); for(int i = 0; i < length; i++) { builder.append(getComponent(i)); if(i < (length - 1)) { builder.append(Path.directorySeparatorChar); } } return builder.toString();
} ; ) ( SetRoleName . fetcher . ; fetcher fetcher . { ) fetcher ECSMetadataServiceCredentialsFetcher ( withFetcher void
void setProgressMonitor(ProgressMonitor pm) { this.pm = pm; }
} } } ; ) ( parseEntry { ) eof ! ( if ; 0 ptr { ) first ! ( if { ) ( reset void
if (iterator.previousIndex() >= start) { return iterator.previous(); } ; throw new java.util.NoSuchElementException();
return getNewPrefix();
public int indexOfValue(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1; }
List<CharsRef> stems = new ArrayList<CharsRef>(); if (stems.size() < 2) { return stems; } CharArraySet terms = new CharArraySet(8, dictionary); for (CharsRef s : stems) { if (!terms.contains(s)) { deduped.add(s); terms.add(s); } } return deduped;
public GetGatewayResponsesResult getGatewayResponses(GetGatewayResponsesRequest request) { request = beforeClientExecution(request); return executeGetGatewayResponses(request); }
void setPosition(long position) { currentBlockIndex = (int) (position >> outerInstance.blockBits); currentBlock = outerInstance.blocks[currentBlockUpto]; currentBlockUpto = (int) (position & outerInstance.blockMask); }
}; return s; ptr;)), (Math.max, )(available(Math.min) int( = s int {) n long( skip long
}; BootstrapActionConfig bootstrapActionConfig; BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { this.bootstrapActionConfig = bootstrapActionConfig;
void serialize(ILittleEndianOutput out1) { out1.writeShort(); out1.writeShort(); out1.writeShort(); out1.writeShort(); out1.writeShort(); out1.writeShort(field_6_author.length()); out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00); if (field_5_hasMultibyte) { StringUtil.putUnicodeLE(, ); } else { StringUtil.putCompressedUnicode(, ); } if (field_7_padding != null) { out1.writeByte(Convert.toInt32(CultureInfo.invariantCulture, )); } }
return string.lastIndexOf('@');
} ; ) ( addLastImpl return { ) object E ( add boolean
void unsetSection(String section, String subsection) { ConfigSnapshot src; do { src = state.get(); ConfigSnapshot res = unsetSection(src, section, subsection); } while (!state.compareAndSet(src, res)); }
return getTagName();
public void addSubRecord(int index, SubRecord element) {subrecords.add(index, element);}
} } ; ) ( remove . c return { ) mutex ( lock { ) @object object ( remove bool
return new DoubleMetaphoneFilter(input);} public TokenStream create(TokenStream input) {
return (long) InCoreLength();
void setValue(boolean newValue) { value = newValue; }
}; newSource newSource.; oldSource oldSource.{ ) newSource ContentSource, oldSource ContentSource( Pair
int Get(int i) { if (count <= i) { throw Sharpen.Extensions.CreateIndexOutOfRangeException(); } return entries[]; }
} ; PUT . MethodType Method ; " " UriPattern { ) " " , " " , " " , " " , " " ( : ) ( CreateRepoRequest
}; return isDeltaBaseAsOffset(); boolean
void remove() { if (expectedModCount == list.modCount) { if (lastLink != null) { java.util.LinkedList.Link<ET> next_1 = lastLink.next; java.util.LinkedList.Link<ET> previous_1 = lastLink.previous; next_1.previous = previous_1; previous_1.next = next_1; if (lastLink == link) { pos--; } lastLink = null; expectedModCount++; list._size--; list.modCount++; } else { throw new System.InvalidOperationException(); } } else { throw new java.util.ConcurrentModificationException(); } }
MergeShardsResponse MergeShards(MergeShardsRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = MergeShardsRequestMarshaller.Instance; options.ResponseUnmarshaller = MergeShardsResponseUnmarshaller.Instance; return Invoke<MergeShardsResponse>(options); }
AllocateHostedConnectionResponse allocateHostedConnection(AllocateHostedConnectionRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = AllocateHostedConnectionRequestMarshaller.getInstance(); options.responseUnmarshaller = AllocateHostedConnectionResponseUnmarshaller.getInstance(); return invoke(options); }
} ; start return { ) ( getBeginIndex int
return GetTerms(query);
java.nio.ByteBuffer.compact() { throw new java.nio.ReadOnlyBufferException(); }
void decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (int)((uint)(byte0 >> 2)); int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (int)((uint)(((byte0 & 3) << 4) | ((byte1 >> 4)))); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (int)((uint)(((byte1 & 15) << 2) | ((byte2 >> 6)))); values[valuesOffset++] = byte2 & 63; } }
if (path.getPath() == null || path.getPath().equals("")) throw new IllegalArgumentException(); String s = path.getPath(); if (s.equals("") || LOCAL_FILE.matcher(s).matches()) { String[] elements = s.split(File.separator + "\\\\"); } else { String[] elements = s.split(" "); } if (elements.length == 0) throw new IllegalArgumentException(); String result = elements[elements.length - 1]; if (Constants.DOT_GIT.equals(result)) { result = elements[elements.length - 2]; } else { if (result.endsWith(Constants.DOT_GIT_EXT)) { result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); } } return result;
public DescribeNotebookInstanceLifecycleConfigResult describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) {request = beforeClientExecution(request);return executeDescribeNotebookInstanceLifecycleConfig(request);}
public String getAccessKeySecret() { return accessSecret; }
CreateVpnConnectionResponse CreateVpnConnection(CreateVpnConnectionRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = CreateVpnConnectionRequestMarshaller.Instance; options.ResponseUnmarshaller = CreateVpnConnectionResponseUnmarshaller.Instance; return Invoke<CreateVpnConnectionResponse>(options); }
DescribeVoicesResponse describeVoices(DescribeVoicesRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = DescribeVoicesRequestMarshaller.instance; options.responseUnmarshaller = DescribeVoicesResponseUnmarshaller.instance; return invoke(options); }
} ; ) , ( > ListMonitoringExecutionsResponse < Invoke return ; Instance . ListMonitoringExecutionsResponseUnmarshaller ResponseUnmarshaller . options ; Instance . ListMonitoringExecutionsRequestMarshaller RequestMarshaller . options ; ) ( InvokeOptions new = options { ) request ListMonitoringExecutionsRequest ( ListMonitoringExecutions ListMonitoringExecutionsResponse
public DescribeJobRequest(String vaultName, String jobId) { this._vaultName = vaultName; this._jobId = jobId; }
return escherRecords[index]; } public EscherRecord getEscherRecord(int index) {
GetApisResponse GetApis(GetApisRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = GetApisRequestMarshaller.Instance; options.ResponseUnmarshaller = GetApisResponseUnmarshaller.Instance; return Invoke<GetApisResponse>(options); }
return Invoke<DeleteSmsChannelResponse>(request, options = new InvokeOptions() { RequestMarshaller = DeleteSmsChannelRequestMarshaller.Instance, ResponseUnmarshaller = DeleteSmsChannelResponseUnmarshaller.Instance });
}; return getTrackingRefUpdate();
} ; ) ) ( toString . b ( print { ) b boolean ( print void
} ; ] [ ) ( getChildren return { ) ( getChild IQueryNode
}; workdirTreeIndex index.{) workdirTreeIndex int( NotIgnoredFilter
AreaRecord(RecordInputStream in1) { field_1_formatFlags = in1.readShort(); }
} ; HTTPS . ProtocolType Protocol { ) " " , " " , " " , " " , " " ( : ) ( GetThumbnailRequest
public DescribeTransitGatewayVpcAttachmentsResult describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) {request = beforeClientExecution(request);return executeDescribeTransitGatewayVpcAttachments(request);}
PutVoiceConnectorStreamingConfigurationResponse PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = PutVoiceConnectorStreamingConfigurationRequestMarshaller.Instance; options.ResponseUnmarshaller = PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.Instance; return Invoke<PutVoiceConnectorStreamingConfigurationResponse>(options); }
} ; return result ; prefixToOrdRange.tryGetValue(out result) ; OrdRange getOrdRange(String dim) { OrdRange
if (startIndex >= 0 && startIndex < ((ICharStream) inputStream).size()) { String symbol = ((ICharStream) inputStream).getText(Interval.of(startIndex, startIndex)); symbol = Utils.escapeWhitespace(symbol, false); return String.format(CultureInfo.getCurrentCulture(), "\"%s\"", symbol); } else { String symbol = ""; return String.format(CultureInfo.getCurrentCulture(), "\"%s\"", Utils.escapeWhitespace(symbol, false)); } throw new LexerNoViableAltException(this, ((ICharStream) inputStream), startIndex, null);
return peekFirstImpl();
CreateWorkspacesResponse createWorkspaces(CreateWorkspacesRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = CreateWorkspacesRequestMarshaller.instance; options.responseUnmarshaller = CreateWorkspacesResponseUnmarshaller.instance; return invoke(options); }
} ; rec return ; field_1_formatIndex field_1_formatIndex . rec ; ) ( NumberFormatIndexRecord new = rec NumberFormatIndexRecord { ) ( clone Object
public DescribeRepositoriesResponse DescribeRepositories(DescribeRepositoriesRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = DescribeRepositoriesRequestMarshaller.Instance; options.ResponseUnmarshaller = DescribeRepositoriesResponseUnmarshaller.Instance; return Invoke<DescribeRepositoriesResponse>(options); }
SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
return new HyphenatedWordsFilter(input); } public TokenStream create(TokenStream input) {
public CreateDistributionWithTagsResponse createDistributionWithTags(CreateDistributionWithTagsRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = CreateDistributionWithTagsRequestMarshaller.Instance; options.ResponseUnmarshaller = CreateDistributionWithTagsResponseUnmarshaller.Instance; return invoke(options); }
throw new UnsupportedOperationException();} new java.io.RandomAccessFile(fileName, mode) {
DeleteWorkspaceImageResponse DeleteWorkspaceImage(DeleteWorkspaceImageRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = DeleteWorkspaceImageRequestMarshaller.Instance; options.ResponseUnmarshaller = DeleteWorkspaceImageResponseUnmarshaller.Instance; return Invoke<DeleteWorkspaceImageResponse>(options); }
return Long.toHexString((long)value);} public static String toHex(int value) {
public UpdateDistributionResult updateDistribution(UpdateDistributionRequest request) { request = beforeClientExecution(request); return executeUpdateDistribution(request); }
HSSFColor GetColor(short index) { if (index == HSSFColor.Automatic.Index) return HSSFColor.Automatic.GetInstance(); else { byte b = palette.GetColor(); if (b != null) { return new CustomColor(,); } ; return null } }
ValueEval Evaluate(ValueEval[][] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(); }
void serialize(LittleEndianOutput out1) { out1.writeShort((short)(field_1_number_crn_records)); out1.writeShort((short)(field_2_sheet_table_index)); }
return describeDBEngineVersions(new DescribeDBEngineVersionsRequest());
FormatRun(short fontIndex, short character) { this._fontIndex = fontIndex; this._character = character; }
byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte) ((ch >> 8)); result[resultIndex++] = (byte) (ch); } return result; }
public UploadArchiveResult uploadArchive(UploadArchiveRequest request) {request = beforeClientExecution(request);return executeUploadArchive(request);}
IList<IToken> GetHiddenTokensToLeft(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex, -1); }
if (this == obj) return true; if (obj == null) return false; if (getClass() != obj.getClass()) return false; AutomatonQuery other = (AutomatonQuery) obj; if (!m_compiled.equals(other.m_compiled)) return false; if (m_term == null) { if (other.m_term != null) return false; } else if (!m_term.equals(other.m_term)) return false; return true;
SpanQuery MakeSpanClause() { List<SpanQuery> spanQueries = new ArrayList<SpanQuery>(); for (Map.Entry<SpanQuery, Float> wsq : weightBySpanQuery.entrySet()) { wsq.getKey().setBoost(wsq.getValue()); spanQueries.add(wsq.getKey()); } if (spanQueries.size() == 1) { return spanQueries.get(0); } else { return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); } }
return new StashCreateCommand().StashCreate();
public FieldInfo fieldInfo(String fieldName) {return byName.get(fieldName);}
public DescribeEventSourceResult describeEventSource(DescribeEventSourceRequest request) {request = beforeClientExecution(request);return executeDescribeEventSource(request);}
public GetDocumentAnalysisResponse GetDocumentAnalysis(GetDocumentAnalysisRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = GetDocumentAnalysisRequestMarshaller.Instance; options.ResponseUnmarshaller = GetDocumentAnalysisResponseUnmarshaller.Instance; return Invoke<GetDocumentAnalysisResponse>(options); }
CancelUpdateStackResponse CancelUpdateStack(CancelUpdateStackRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = CancelUpdateStackRequestMarshaller.Instance; options.ResponseUnmarshaller = CancelUpdateStackResponseUnmarshaller.Instance; return Invoke<CancelUpdateStackResponse>(options); }
ModifyLoadBalancerAttributesResponse modifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = ModifyLoadBalancerAttributesRequestMarshaller.getInstance(); options.responseUnmarshaller = ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance(); return invoke(options); }
SetInstanceProtectionResponse setInstanceProtection(SetInstanceProtectionRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = SetInstanceProtectionRequestMarshaller.getInstance(); options.responseUnmarshaller = SetInstanceProtectionResponseUnmarshaller.getInstance(); return invoke(options); }
ModifyDBProxyResponse ModifyDBProxy(ModifyDBProxyRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = ModifyDBProxyRequestMarshaller.Instance; options.ResponseUnmarshaller = ModifyDBProxyResponseUnmarshaller.Instance; return Invoke<ModifyDBProxyResponse>(options); }
void Add(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.length) { CharsRef[] next = new CharsRef[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF)]; System.arraycopy(outputs, 0, next, 0, count); outputs = next; } if (count == endOffsets.length) { int[] next = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.length) { int[] next = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRef(); } outputs[count].copyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; ++count; }
} ; HTTPS . ProtocolType Protocol { ) " " , " " , " " , " " , " " ( : ) ( FetchLibrariesRequest
return objects.Exists();
} ; @out @out . {  ) . . java ( FilterOutputStream
// Cannot translate - invalid C# syntax
return DVConstraint.CreateTimeConstraint(int operatorType, String formula1, String formula2);
ListObjectParentPathsResponse listObjectParentPaths(ListObjectParentPathsRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = ListObjectParentPathsRequestMarshaller.getInstance(); options.responseUnmarshaller = ListObjectParentPathsResponseUnmarshaller.getInstance(); return invoke(options); }
public DescribeCacheSubnetGroupsResult describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) {request = beforeClientExecution(request);return executeDescribeCacheSubnetGroups(request);}
} ; ) , ( SetShortBoolean . sharedFormula field_5_options { ) flag boolean ( SetSharedFormula void
public boolean isReuseObjects() { return reuseObjects; }
IErrorNode t = new ErrorNodeImpl(badToken); Parent.AddChild(t); return t;
public LatvianStemFilterFactory(Map<String, String> args) { super(args); if (args.size() > 0) { throw new IllegalArgumentException("Unknown parameters: " + args); } }
public RemoveSourceIdentifierFromSubscriptionResult removeSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = RemoveSourceIdentifierFromSubscriptionRequestMarshaller.Instance; options.ResponseUnmarshaller = RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.Instance; return Invoke<RemoveSourceIdentifierFromSubscriptionResult>(options); }
return TokenFilterFactory.forName(name, new HashMap<String, String>(args));
} ; HTTPS . ProtocolType Protocol { ) " " , " " , " " , " " , " " ( : ) ( AddAlbumPhotosRequest
GetThreatIntelSetResponse getThreatIntelSet(GetThreatIntelSetRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = GetThreatIntelSetRequestMarshaller.Instance; options.ResponseUnmarshaller = GetThreatIntelSetResponseUnmarshaller.Instance; return invoke<GetThreatIntelSetResponse>(options); }
return new AndTreeFilter(a.clone(), b.clone());
boolean equals(Object o) { return o instanceof
protected boolean hasArray() { return ; } ) (
}; ), ( > UpdateContributorInsightsResponse < invoke return ; instance . updateContributorInsightsResponseUnmarshaller responseUnmarshaller . options ; instance . updateContributorInsightsRequestMarshaller requestMarshaller . options ; ) ( invokeOptions new = options { ) request UpdateContributorInsightsRequest ( updateContributorInsights UpdateContributorInsightsResponse
} ; null writeProtect ; null fileShare ; ) ( Remove . records ; ) ( Remove . records { ) ( UnwriteProtectWorkbook void
} ; expand expand . {  ) analyzer , dedup ( base : ) analyzer Analyzer , expand boolean , dedup boolean ( SolrSynonymParser
public RequestSpotInstancesResult requestSpotInstances(RequestSpotInstancesRequest request) {request = beforeClientExecution(request);return executeRequestSpotInstances(request);}
return FindObjectRecord().GetObjectData();
} ; ) , ( > GetContactAttributesResponse < invoke return ; getInstance . GetContactAttributesResponseUnmarshaller responseUnmarshaller . options ; getInstance . GetContactAttributesRequestMarshaller requestMarshaller . options ; ) ( new InvokeOptions = options { ) request GetContactAttributesRequest ( getContactAttributes GetContactAttributesResponse
return GetKey() + " " + GetValue();
public ListTextTranslationJobsResponse listTextTranslationJobs(ListTextTranslationJobsRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = ListTextTranslationJobsRequestMarshaller.Instance; options.ResponseUnmarshaller = ListTextTranslationJobsResponseUnmarshaller.Instance; return invoke(options); }
public GetContactMethodsResponse GetContactMethods(GetContactMethodsRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = GetContactMethodsRequestMarshaller.Instance; options.ResponseUnmarshaller = GetContactMethodsResponseUnmarshaller.Instance; return Invoke<GetContactMethodsResponse>(options); }
short LookupIndexByName(String name) { FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name); if (fd == null) { return -1; } return (short)fd.Index; }
DescribeAnomalyDetectorsResponse DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { options = new InvokeOptions(); options.RequestMarshaller = DescribeAnomalyDetectorsRequestMarshaller.Instance; options.ResponseUnmarshaller = DescribeAnomalyDetectorsResponseUnmarshaller.Instance; return Invoke<DescribeAnomalyDetectorsResponse>(options); }
}; ), ,(insertId return {) changeId ObjectId, message String( insertId String
long getObjectSize(AnyObjectId objectId, int typeHint) { long sz = db.getObjectSize(objectId, typeHint); if (sz < 0) { if (typeHint == OBJ_ANY) { throw new MissingObjectException(objectId.copy(), ""); } throw new MissingObjectException(objectId.copy(), ""); } return sz; }
ImportInstallationMediaResponse ImportInstallationMedia(ImportInstallationMediaRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = ImportInstallationMediaRequestMarshaller.Instance; options.ResponseUnmarshaller = ImportInstallationMediaResponseUnmarshaller.Instance; return Invoke<ImportInstallationMediaResponse>(options); }
PutLifecycleEventHookExecutionStatusResponse putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = PutLifecycleEventHookExecutionStatusRequestMarshaller.Instance; options.ResponseUnmarshaller = PutLifecycleEventHookExecutionStatusResponseUnmarshaller.Instance; return invoke(options); }
NumberPtg(LittleEndianInput in1) { field_1_value = in1.readDouble(); }
public GetFieldLevelEncryptionConfigResult getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { request = beforeClientExecution(request); return executeGetFieldLevelEncryptionConfig(request); }
public DescribeDetectorResult describeDetector(DescribeDetectorRequest request) {request = beforeClientExecution(request);return executeDescribeDetector(request);}
ReportInstanceStatusResponse reportInstanceStatus(ReportInstanceStatusRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = ReportInstanceStatusRequestMarshaller.getInstance(); options.responseUnmarshaller = ReportInstanceStatusResponseUnmarshaller.getInstance(); return invoke(options); }
DeleteAlarmResponse DeleteAlarm(DeleteAlarmRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = DeleteAlarmRequestMarshaller.Instance; options.ResponseUnmarshaller = DeleteAlarmResponseUnmarshaller.Instance; return Invoke<DeleteAlarmResponse>(request, options); }
return new PortugueseStemFilter(input);
} ; ] ENCODED_SIZE [ byte new reserved { ) ( FtCblsSubRecord
} } ; ) ( remove . c return { ) mutex ( lock { ) object object ( remove boolean
public GetDedicatedIpResult getDedicatedIp(GetDedicatedIpRequest request) { request = beforeClientExecution(request); return executeGetDedicatedIp(request); }
} ; " " + precedence return { ) ( toString string
public ListStreamProcessorsResponse listStreamProcessors(ListStreamProcessorsRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = ListStreamProcessorsRequestMarshaller.getInstance(); options.responseUnmarshaller = ListStreamProcessorsResponseUnmarshaller.getInstance(); return invoke(request, options); }
public DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) { this._loadBalancerName = loadBalancerName; this._policyName = policyName; }
} ; options _options {  ) options int ( WindowProtectRecord
UnbufferedCharStream(int bufferSize) { n = 0; data = new int[bufferSize]; }
public GetOperationsResponse GetOperations(GetOperationsRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = GetOperationsRequestMarshaller.Instance; options.ResponseUnmarshaller = GetOperationsResponseUnmarshaller.Instance; return Invoke<GetOperationsResponse>(options); }
void CopyRawTo(byte[] b, int o) { NB.EncodeInt32(, o); NB.EncodeInt32(o + 4, ); NB.EncodeInt32(o + 8, ); NB.EncodeInt32(o + 12, ); NB.EncodeInt32(o + 16, ); }
WindowOneRecord(RecordInputStream in1) { field_1_h_hold = in1.readShort(); field_2_v_hold = in1.readShort(); field_3_width = in1.readShort(); field_4_height = in1.readShort(); field_5_options = in1.readShort(); field_6_active_sheet = in1.readShort(); field_7_first_visible_tab = in1.readShort(); field_8_num_selected_tabs = in1.readShort(); field_9_tab_width_ratio = in1.readShort(); }
public StopWorkspacesResult stopWorkspaces(StopWorkspacesRequest request) { request = beforeClientExecution(request); return executeStopWorkspaces(request); }
} } } } } ; ) ( close . fos { finally } ; ) ( close . channel { try { finally } ; ) ( truncate . channel { try { finally } ; ) ( dump { try ; isOpen { ) isOpen ( if { throws IOException ) ( close void
public DescribeMatchmakingRuleSetsResult describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { request = beforeClientExecution(request); return executeDescribeMatchmakingRuleSets(request); }
public String getPronunciation(int wordId, char[] surface, int off, int len) { return null; }
return GetPath().toString();
double devsq(double[] v) { double r = Double.NaN; if (v != null && v.length >= 1) { double m = 0; double s = 0; int n = v.length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } return r; }
DescribeResizeResponse DescribeResize(DescribeResizeRequest request) { options = new InvokeOptions(); options.RequestMarshaller = DescribeResizeRequestMarshaller.Instance; options.ResponseUnmarshaller = DescribeResizeResponseUnmarshaller.Instance; return Invoke<DescribeResizeResponse>(options); }
boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
} ; ) ( end return { ) ( end int
void Traverse(ICellHandler handler) { int firstRow = range.FirstRow; int lastRow = range.LastRow; int firstColumn = range.FirstColumn; int lastColumn = range.LastColumn; int width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); IRow currentRow = null; ICell currentCell = null; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ctx.rowNumber++) { currentRow = sheet.GetRow(ctx.rowNumber); if (currentRow == null) { continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ctx.colNumber++) { currentCell = currentRow.GetCell(ctx.colNumber); if (currentCell == null) { continue; } if (IsEmpty() && !traverseEmptyCells) { continue; } ctx.ordinalNumber = (ctx.rowNumber - firstRow) * width + (ctx.colNumber - firstColumn) + 1; handler.OnCell(ctx); } } }
return getReadIndex();
public int compareTo(ScoreTerm other) { if (term.bytesEquals(other.term)) { return 0; } if (boost == other.boost) { return term.compareTo(other.term); } else { return boost.compareTo(other.boost); } }
public static int normalize(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = StemmerUtil.delete(s, i--, len); break; default: break; } } return len; }
void Serialize(ILittleEndianOutput out1) { out1.WriteShort(); }
} ; exactOnly exactOnly . {  ) exactOnly boolean ( DiagnosticErrorListener
}; KeyType keyType; String attributeName; public KeySchemaElement(KeyType keyType, String attributeName) {
public GetAssignmentResult getAssignment(GetAssignmentRequest request) {request = beforeClientExecution(request);return executeGetAssignment(request);}
boolean hasObject(AnyObjectId id) { return findOffset(id) != -1; }
} ; return ; allGroups allGroups . { ) allGroups boolean ( setAllGroups GroupingSearch
void setMultiValued(String dimName, boolean v) { synchronized(this) { if (!fieldTypes.tryGetValue(dimName, out DimConfig fieldType)) { fieldTypes[dimName] = new DimConfig(); } fieldType.isMultiValued = v; } }
int getCellsVal() { int size = 0; for (char c : cells.keySet()) { Cell e = cells.get(c); if (e.cmd >= 0) { size++; } } return size; }
return invoke(new InvokeOptions().withRequestMarshaller(DeleteVoiceConnectorRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteVoiceConnectorResponseUnmarshaller.getInstance()), request);
public DeleteLifecyclePolicyResult deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { request = beforeClientExecution(request); return executeDeleteLifecyclePolicy(request); }
