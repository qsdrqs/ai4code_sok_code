void Serialize(ILittleEndianOutput out1) { out1.WriteShort( ) ; }
void addAll(NGit.Util.BlockList<T> src) { if (src.size == 0) { return; } int srcDirIdx = 0; for (srcDirIdx; srcDirIdx < src.tailDirIdx; srcDirIdx++) { addAll(src.directory[srcDirIdx]); } if (src.tailBlkIdx != 0) { addAll(src.tailBlock); } }
void writeByte(byte b) { if (outerInstance.upto == outerInstance.blockSize) { if (outerInstance.currentBlock != null) { outerInstance.blocks.add(outerInstance.currentBlock); outerInstance.blockEnd.add(outerInstance.upto); } outerInstance.currentBlock = new byte[outerInstance.blockSize]; outerInstance.upto = 0; } outerInstance.currentBlock[outerInstance.upto++] = b; }
return getObjectId();
public DeleteDomainEntryResult deleteDomainEntry(DeleteDomainEntryRequest request) {request = beforeClientExecution(request);return executeDeleteDomainEntry(request);}
return fst == null ? 0 : fst.getSizeInBytes();
public String getFullMessage() { byte[] raw = buffer; int msgB = RawParseUtils.tagMessage(raw, 0); if (msgB < 0) { return ""; } Charset enc = RawParseUtils.parseEncoding(raw); return RawParseUtils.decode(enc, raw, msgB, raw.length); }
}; null _root;) (ArrayList new _documents;) (PropertyTable new _property_table;) (HeaderBlock new = headerBlock HeaderBlock {) (POIFSFileSystem
void init(int address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset0 = address; Debug.assert(slice != null); Debug.assert(upto < slice.length); }
return this.setPath(path);
public ListIngestionsResult listIngestions(ListIngestionsRequest request) {request = beforeClientExecution(request);return executeListIngestions(request);}
QueryParserTokenManager(ICharStream stream, int lexState) : this(stream) { SwitchTo(lexState); }
public GetShardIteratorResponse getShardIterator(GetShardIteratorRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = GetShardIteratorRequestMarshaller.Instance; options.ResponseUnmarshaller = GetShardIteratorResponseUnmarshaller.Instance; return invoke(request, options); }
} ; POST . MethodType Method { ) " " , " " , " " , " " , " " ( : ) ( ModifyStrategyRequest
boolean ready() { synchronized(lock) { if (in == null) { throw new SystemIOException(""); } try { return bytes.hasRemaining() || in.available() > 0; } catch (SystemIOException e) { } } }
} ; _optRecord return { ) ( GetOptRecord EscherOptRecord
public int read(byte[] buffer, int offset, int length) { synchronized(this) { if (buffer == null) { throw new NullPointerException(""); } java.util.Arrays.checkOffsetAndCount(buffer.length, offset, length); if (length == 0) { return 0; } int copylen = count - pos < length ? count - pos : length; for (int i = 0; i < copylen; i++) { buffer[offset + i] = (byte) (unchecked((byte) buffer[pos + i])); } pos += copylen; return copylen; } }
} ; sentenceOp sentenceOp . {  ) sentenceOp NLPSentenceDetectorOp ( OpenNLPSentenceBreakIterator
} ; ) ) null ) Object ( ( getValueOf . StringHelper . sharpen : str ? null != str ( write { ) str String ( print void
} ; functionName functionName . {  ) cause , functionName ( super : ) cause NotImplementedException , functionName String ( NotImplementedFunctionException
}; value.) (nextEntry. return {) (next V
void readBytes(byte[] b, int offset, int len, boolean useBuffer) { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) { System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } } else { if (available > 0) { System.arraycopy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (len < bufferSize && useBuffer) { refill(); if (bufferLength < len) { System.arraycopy(buffer, bufferPosition, b, offset, bufferLength); throw new EndOfStreamException(); } else { System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } } else { long after = bufferStart + bufferPosition + len; if (after > length) throw new EndOfStreamException(); readInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
public TagQueueResponse tagQueue(TagQueueRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = TagQueueRequestMarshaller.Instance; options.ResponseUnmarshaller = TagQueueResponseUnmarshaller.Instance; return invoke(options); }
public void remove() { throw new UnsupportedOperationException(); }
public ModifyCacheSubnetGroupResult modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) {request = beforeClientExecution(request);return executeModifyCacheSubnetGroup(request);}
void setParams(String params) { setParams(); String culture = ""; StringTokenizer st = new StringTokenizer(","); if (st.moveNext()) culture = st.current(); if (st.moveNext()) culture = st.current() + " "; if (st.moveNext()) ignore st.current(); }
public DeleteDocumentationVersionResponse deleteDocumentationVersion(DeleteDocumentationVersionRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = DeleteDocumentationVersionRequestMarshaller.Instance; options.ResponseUnmarshaller = DeleteDocumentationVersionResponseUnmarshaller.Instance; return invoke(options); }
public boolean equals(Object obj) { if (!(obj instanceof FacetLabel)) { return false; } FacetLabel other = (FacetLabel) obj; if (length != other.length) { return false; } for (int i = length - 1; i >= 0; i--) { if (!components[i].equals(other.components[i], StringComparison.Ordinal)) { return false; } } return true; }
public GetInstanceAccessDetailsResult getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { request = beforeClientExecution(request); return executeGetInstanceAccessDetails(request); }
HSSFPolygon shape = new HSSFPolygon(null, (HSSFChildAnchor) anchor); shape.setParent(parent); shape.setAnchor(anchor); shapes.add(shape); onCreate(shape); return shape;
public String getSheetName(int sheetIndex) { return getBoundSheetRec(sheetIndex).getSheetname(); }
public GetDashboardResponse getDashboard(GetDashboardRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = GetDashboardRequestMarshaller.getInstance(); options.ResponseUnmarshaller = GetDashboardResponseUnmarshaller.getInstance(); return invoke(options); }
public AssociateSigninDelegateGroupsWithAccountResult associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) {request = beforeClientExecution(request);return executeAssociateSigninDelegateGroupsWithAccount(request);}
void addMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < mbr.getNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.setColumn(mbr.getFirstColumn() + j); br.setRow(mbr.getRow()); br.setXFIndex(mbr.getXFAt(j)); insertCell(br); } }
public static String quote(String string) { java.lang.StringBuilder sb = new java.lang.StringBuilder(); sb.append("\""); int apos = 0; int k; while ((k = string.indexOf("\\", apos)) >= 0) { sb.append(Sharpen.StringHelper.Substring(string, apos, k + 2)); sb.append("\\\\\\"); apos = k + 2; } return sb.append(Sharpen.StringHelper.Substring(string, apos)).append("\"").toString(); }
java.nio.ByteBuffer.putInt(int value) { throw new java.nio.ReadOnlyBufferException(); }
ArrayPtg(Object[][] values2d) { int nColumns = values2d[0].length; int nRows = values2d.length; _nColumns = (short) nColumns; _nRows = (short) nRows; Object[] vv = new Object[_nColumns * _nRows]; for (int r = 0; r < nRows; ++r) { Object[] rowData = values2d[r]; for (int c = 0; c < nColumns; ++c) { vv[getValueIndex(c, r)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
public GetIceServerConfigResponse getIceServerConfig(GetIceServerConfigRequest request) {request = beforeClientExecution(request);return executeGetIceServerConfig(request);}
public String toString() { StringBuilder sb = new StringBuilder(); sb.append(getClass().getName()); sb.append(" "); sb.append(getValueAsString()); sb.append(" "); return sb.toString(); }
} ; " " + _parentQuery + " " return { ) field string ( toString string
void incRef() { refCount.incrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResult updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) {request = beforeClientExecution(request);return executeUpdateConfigurationSetSendingEnabled(request);}
return GetXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE; } int GetNextXBATChainOffset() {
void multiplyByPowerOfTen(int pow10) { TenPower tp = TenPower.getInstance(Math.abs(pow10)); if (pow10 < 0) { mulShift(tp._divisor, tp._divisorShift); } else { mulShift(tp._multiplicand, tp._multiplierShift); } }
public String toString() { StringBuilder builder = new StringBuilder(); int length = this.length; builder.append(File.separatorChar); for (int i = 0; i < length; i++) { builder.append(getComponent(i)); if (i < (length - 1)) { builder.append(File.separatorChar); } } return builder.toString(); }
void withFetcher(ECSMetadataServiceCredentialsFetcher fetcher) { fetcher.fetcher; fetcher.SetRoleName(); }
void setProgressMonitor(ProgressMonitor pm) { this.pm = pm; }
} } } ; ) ( ParseEntry { ) Eof ! ( if ; 0 ptr { ) First ! ( if { ) ( Reset void
if (iterator.previousIndex() >= start) { return iterator.previous(); } throw new java.util.NoSuchElementException();
return getNewPrefix();
public int indexOfValue(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1; }
} ; deduped return } } ; ) ( add . terms ; ) ( add . deduped { ) ) ( contains . terms ! ( if { ) stems in s CharsRef ( for ; ) ( > CharsRef < List new = > < 618 , 612 restore warning pragma ; ) . dictionary , 8 , . 618 , 612 disable warning pragma ( CharArraySet new = terms CharArraySet } ; stems return { ) 2 < size . stems ( if ; ) , ( stem = stems > CharsRef < List { ) length int , word ] [ char ( uniqueStems > CharsRef < List
public GetGatewayResponsesResponse GetGatewayResponses(GetGatewayResponsesRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = GetGatewayResponsesRequestMarshaller.Instance; options.ResponseUnmarshaller = GetGatewayResponsesResponseUnmarshaller.Instance; return Invoke<GetGatewayResponsesResponse>(options); }
void setPosition(long position) { currentBlockIndex = (int)(position() >> outerInstance.blockBits); currentBlock = outerInstance.blocks[currentBlockUpto]; int position = (int)(position() & outerInstance.blockMask); }
return (int) Math.min(Math.max(s.ptr), s.available()), (long) skip(n);
}; bootstrapActionConfig _bootstrapActionConfig { ) bootstrapActionConfig BootstrapActionConfig ( BootstrapActionDetail
public void serialize(LittleEndianOutput out1) { out1.writeShort(); out1.writeShort(); out1.writeShort(); out1.writeShort(); out1.writeShort(); out1.writeShort(field_6_author.length()); out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00); if (field_5_hasMultibyte) { StringUtil.putUnicodeLE(field_6_author, out1); } else { StringUtil.putCompressedUnicode(field_6_author, out1); } if (field_7_padding != null) { out1.write(field_7_padding); } }
return string.lastIndexOf('@');
} ; ) ( addLastImpl return { ) object E ( add boolean
void unsetSection(String section, String subsection) { ConfigSnapshot src, res; do { src = state.get(); res = src.unsetSection(section, subsection); } while (!state.compareAndSet(src, res)); }
public String getTagName() { return tagName; }
void addSubRecord(int index, SubRecord element) { subrecords.add(index, element); }
} } ; ) ( remove . c return { ) mutex ( lock { ) object Object ( remove boolean
return new DoubleMetaphoneFilter(input);} public TokenStream create(TokenStream input) {
return (int) length();
void setValue(boolean newValue) { value = newValue; }
} ; newSource newSource . ; oldSource oldSource . {  ) newSource ContentSource , oldSource ContentSource ( Pair
} ; ] [ entries return } ; ) ( createIndexOutOfRangeException . extensions . sharpen throw { ) i <= count ( if { ) i int ( get int
} ; PUT . MethodType Method ; " " UriPattern { ) " " , " " , " " , " " , " " ( : ) ( CreateRepoRequest
public boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public void remove() {if (expectedModCount == list.modCount) {if (lastLink != null) {java.util.LinkedList.Link<ET> next_1 = lastLink.next; java.util.LinkedList.Link<ET> previous_1 = lastLink.previous; next_1.previous = previous_1; previous_1.next = next_1; if (lastLink == link) {pos--;} lastLink = null; expectedModCount++; list._size--; list.modCount++;} else {throw new IllegalStateException();}} else {throw new ConcurrentModificationException();}}
return invoke(request, new InvokeOptions().withRequestMarshaller(MergeShardsRequestMarshaller.getInstance()).withResponseUnmarshaller(MergeShardsResponseUnmarshaller.getInstance()));
public AllocateHostedConnectionResult allocateHostedConnection(AllocateHostedConnectionRequest request) { request = beforeClientExecution(request); return executeAllocateHostedConnection(request); }
return getBeginIndex();
} ; ) , ( getTerms return { ) query Query ( getTerms ] [ WeightedTerm
throw new java.nio.ReadOnlyBufferException();
void decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (int)((uint)(byte0 >> 2)); int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (int)((uint)(((byte0 & 3) << 4) | ((byte1 >> 4)))); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (int)((uint)(((byte1 & 15) << 2) | ((byte2 >> 6)))); values[valuesOffset++] = byte2 & 63; } }
public String getHumanishName(URI uri) { if (uri.getPath().equals("") || uri.getPath() == null) { throw new IllegalArgumentException(); } String s = uri.getPath(); if (s.equals("") || LOCAL_FILE.matcher(s).matches()) { String[] elements = s.split(File.separator + "\\\\"); } else { String[] elements = s.split("/"); } if (elements.length == 0) { throw new IllegalArgumentException(); } String result = elements[elements.length - 1]; if (Constants.DOT_GIT.equals(result)) { result = elements[elements.length - 2]; } else { if (result.endsWith(Constants.DOT_GIT_EXT)) { result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); } } return result; }
public DescribeNotebookInstanceLifecycleConfigResponse describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance()); return invoke(request, options); }
public String getAccessKeySecret() { return accessSecret; }
public CreateVpnConnectionResult createVpnConnection(CreateVpnConnectionRequest request) { request = beforeClientExecution(request); return executeCreateVpnConnection(request); }
public DescribeVoicesResult describeVoices(DescribeVoicesRequest request) {request = beforeClientExecution(request);return executeDescribeVoices(request);}
public ListMonitoringExecutionsResponse listMonitoringExecutions(ListMonitoringExecutionsRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = ListMonitoringExecutionsRequestMarshaller.getInstance(); options.responseUnmarshaller = ListMonitoringExecutionsResponseUnmarshaller.getInstance(); return invoke(options); }
public DescribeJobRequest(String vaultName, String jobId) { this._vaultName = vaultName; this._jobId = jobId; }
return escherRecords[index];
GetApisResponse getApis(GetApisRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = GetApisRequestMarshaller.Instance; options.ResponseUnmarshaller = GetApisResponseUnmarshaller.Instance; return invoke(options); }
public DeleteSmsChannelResult deleteSmsChannel(DeleteSmsChannelRequest request) { request = beforeClientExecution(request); return executeDeleteSmsChannel(request); }
return getTrackingRefUpdate();
} ; ) ) ( toString . b ( print { ) b boolean ( print void
}[];)(getChildren return{)(getChild IQueryNode
}; workdirTreeIndex index.{) workdirTreeIndex int( NotIgnoredFilter
} ; ) ( readShort . in1 field_1_formatFlags {  ) in1 RecordInputStream ( AreaRecord
} ; HTTPS . ProtocolType Protocol { ) " " , " " , " " , " " , " " ( : ) ( GetThumbnailRequest
public DescribeTransitGatewayVpcAttachmentsResponse describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = DescribeTransitGatewayVpcAttachmentsRequestMarshaller.getInstance(); options.responseUnmarshaller = DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance(); return invoke(options, request); }
public PutVoiceConnectorStreamingConfigurationResponse putVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) { request = beforeClientExecution(request); return executePutVoiceConnectorStreamingConfiguration(request); }
}; return result; prefixToOrdRange.tryGet(result); OrdRange getOrdRange(String dim) {
if (startIndex >= 0 && startIndex < ((ICharStream)InputStream).Size()) { string symbol = ((ICharStream)InputStream).GetText(Interval.Of(startIndex, startIndex)); symbol = Utils.EscapeWhitespace(symbol, false); return string.Format(CultureInfo.CurrentCulture, "no viable alternative at input {0}", symbol); } else { return "no viable alternative at input <EOF>"; }
return peekFirstImpl();
public CreateWorkspacesResult createWorkspaces(CreateWorkspacesRequest request) {request = beforeClientExecution(request);return executeCreateWorkspaces(request);}
} ; rec return ; field_1_formatIndex field_1_formatIndex . rec ; ) ( NumberFormatIndexRecord new = rec NumberFormatIndexRecord { ) ( clone Object
public DescribeRepositoriesResult describeRepositories(DescribeRepositoriesRequest request) {request = beforeClientExecution(request);return executeDescribeRepositories(request);}
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
return new HyphenatedWordsFilter(input);
public CreateDistributionWithTagsResult createDistributionWithTags(CreateDistributionWithTagsRequest request) { request = beforeClientExecution(request); return executeCreateDistributionWithTags(request); }
throw new NotImplementedException();} new java.io.RandomAccessFile(fileName, mode) {
public DeleteWorkspaceImageResult deleteWorkspaceImage(DeleteWorkspaceImageRequest request) {request = beforeClientExecution(request);return executeDeleteWorkspaceImage(request);}
return Long.toHexString((long) value);
public UpdateDistributionResult updateDistribution(UpdateDistributionRequest request) {request = beforeClientExecution(request);return executeUpdateDistribution(request);}
public HSSFColor getColor(short index) { if (index == HSSFColor.Automatic.Index) return HSSFColor.Automatic.getInstance(); else { byte b = palette.getColor(index); if (b != null) { return new CustomColor(b); } ; return null; } }
public ValueEval evaluate(ValueEval[][] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(); }
void serialize(LittleEndianOutput out1) { out1.writeShort((short)(field_1_number_crn_records)); out1.writeShort((short)(field_2_sheet_table_index)); }
return describeDBEngineVersions(new DescribeDBEngineVersionsRequest());
FormatRun(short fontIndex, short character) { this._fontIndex = fontIndex; this._character = character; }
public static byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[2 * length]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; ++i) { char ch = chars[i]; result[resultIndex++] = (byte) (ch >> 8); result[resultIndex++] = (byte) ch; } return result; }
public UploadArchiveResult uploadArchive(UploadArchiveRequest request) {request = beforeClientExecution(request);return executeUploadArchive(request);}
List<Token> getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex, -1); }
public boolean equals(Object obj) { if (this == obj) { return true; } if (!super.equals(obj)) { return false; } if (getClass() != obj.getClass()) { return false; } AutomatonQuery other = (AutomatonQuery) obj; if (!m_compiled.equals(other.m_compiled)) { return false; } if (m_term == null) { return false; } if (other.m_term != null) { return false; } else if (!m_term.equals(other.m_term)) { return false; } return true; }
List<SpanQuery> spanQueries = new ArrayList<SpanQuery>(); for (Map.Entry<SpanQuery, Float> wsq : weightBySpanQuery.entrySet()) { wsq.getKey().setBoost(wsq.getValue()); spanQueries.add(wsq.getKey()); } if (spanQueries.size() == 1) { return spanQueries.get(0); } else { return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); }
return new StashCreateCommand().StashCreate();
FieldInfo fieldInfo = byName.get(fieldName); return fieldInfo;
public DescribeEventSourceResult describeEventSource(DescribeEventSourceRequest request) {request = beforeClientExecution(request);return executeDescribeEventSource(request);}
public GetDocumentAnalysisResponse getDocumentAnalysis(GetDocumentAnalysisRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetDocumentAnalysisRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetDocumentAnalysisResponseUnmarshaller.getInstance()); return invoke(request, options); }
public CancelUpdateStackResult cancelUpdateStack(CancelUpdateStackRequest request) {request = beforeClientExecution(request);return executeCancelUpdateStack(request);}
public ModifyLoadBalancerAttributesResult modifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) {request = beforeClientExecution(request);return executeModifyLoadBalancerAttributes(request);}
public SetInstanceProtectionResponse setInstanceProtection(SetInstanceProtectionRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = SetInstanceProtectionRequestMarshaller.getInstance(); options.responseUnmarshaller = SetInstanceProtectionResponseUnmarshaller.getInstance(); return invoke(request, options); }
public ModifyDBProxyResult modifyDBProxy(ModifyDBProxyRequest request) {request = beforeClientExecution(request);return executeModifyDBProxy(request);}
void Add(char[] output, int offset, int len, int endOffset, int posLength) { if (count == outputs.length) { CharsRef[] next = new CharsRef[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF)]; System.arraycopy(outputs, 0, next, 0, count); outputs = next; } if (count == endOffsets.length) { int[] next = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.length) { int[] next = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRef(); } outputs[count].copyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
} ; HTTPS . ProtocolType Protocol { ) " " , " " , " " , " " , " " ( : ) ( FetchLibrariesRequest
return objects.exists();
} ; out out . {  ) . . java ( FilterOutputStream
} ; PUT . MethodType Method ; " " UriPattern { ) " " , " " , " " , " " , " " ( : ) ( ScaleClusterRequest
return DVConstraint.createTimeConstraint(operatorType, formula1, formula2);
public ListObjectParentPathsResult listObjectParentPaths(ListObjectParentPathsRequest request) {request = beforeClientExecution(request);return executeListObjectParentPaths(request);}
public DescribeCacheSubnetGroupsResponse describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeCacheSubnetGroupsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance()); return invoke(options); }
public void setSharedFormula(boolean flag) { field_5_options = SetShortBoolean.setSharedFormula(field_5_options, flag); }
public boolean isReuseObjects() { return reuseObjects; }
} ; t return ; Parent . t ; ) ( addChild ; ) ( ErrorNodeImpl new = t ErrorNodeImpl { ) badToken Token ( addErrorNode ErrorNode
public LatvianStemFilterFactory(Map<String,String> args) : super(args) { if (args.size() > 0) { throw new IllegalArgumentException("Unknown parameters: " + args); } }
public RemoveSourceIdentifierFromSubscriptionResult removeSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) {request = beforeClientExecution(request);return executeRemoveSourceIdentifierFromSubscription(request);}
return TokenFilterFactory.forName(name, new HashMap<String,String>(args)).newInstance(loader);
} ; HTTPS . ProtocolType Protocol { ) " " , " " , " " , " " , " " ( : ) ( AddAlbumPhotosRequest
public GetThreatIntelSetResult getThreatIntelSet(GetThreatIntelSetRequest request) {request = beforeClientExecution(request);return executeGetThreatIntelSet(request);}
return new AndTreeFilter(a.clone(), b.clone());
public boolean equals(Object o) { return ; }
protected boolean hasArray() { return ; } ) (
public UpdateContributorInsightsResult updateContributorInsights(UpdateContributorInsightsRequest request) { request = beforeClientExecution(request); return executeUpdateContributorInsights(request); }
void unwriteProtectWorkbook() { records.remove(); records.remove(); }
} ; expand expand . {  ) analyzer , dedup ( base : ) analyzer Analyzer , expand boolean , dedup boolean ( SolrSynonymParser
public RequestSpotInstancesResult requestSpotInstances(RequestSpotInstancesRequest request) {request = beforeClientExecution(request);return executeRequestSpotInstances(request);}
return FindObjectRecord().getObjectData();
public GetContactAttributesResponse getContactAttributes(GetContactAttributesRequest request) { InvokeOptions options = new InvokeOptions().requestMarshaller(GetContactAttributesRequestMarshaller.getInstance()).responseUnmarshaller(GetContactAttributesResponseUnmarshaller.getInstance()); return invoke(request, options); }
return getKey() + " " + getValue();
public ListTextTranslationJobsResponse listTextTranslationJobs(ListTextTranslationJobsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListTextTranslationJobsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ListTextTranslationJobsResponseUnmarshaller.getInstance()); return invoke(options); }
public GetContactMethodsResult getContactMethods(GetContactMethodsRequest request) { request = beforeClientExecution(request); return executeGetContactMethods(request); }
short lookupIndexByName(String name) { FunctionMetadata fd = getInstance().getFunctionByNameInternal(); if (fd == null) { return -1; } return (short) fd.index; }
public DescribeAnomalyDetectorsResult describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) {request = beforeClientExecution(request);return executeDescribeAnomalyDetectors(request);}
return insertId(changeId, message);
long getObjectSize(AnyObjectId objectId, int typeHint) { long sz = db.getObjectSize(objectId, typeHint); if (sz < 0) { if (typeHint == OBJ_ANY) { throw new MissingObjectException(objectId, ""); } throw new MissingObjectException(objectId, ""); } return sz; }
public ImportInstallationMediaResult importInstallationMedia(ImportInstallationMediaRequest request) {request = beforeClientExecution(request);return executeImportInstallationMedia(request);}
public PutLifecycleEventHookExecutionStatusResult putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) {request = beforeClientExecution(request);return executePutLifecycleEventHookExecutionStatus(request);}
} ; ) ( readDouble . in1 field_1_value {  ) in1 ILittleEndianInput ( NumberPtg
public GetFieldLevelEncryptionConfigResult getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { request = beforeClientExecution(request); return executeGetFieldLevelEncryptionConfig(request); }
public DescribeDetectorResult describeDetector(DescribeDetectorRequest request) { request = beforeClientExecution(request); return executeDescribeDetector(request); }
public ReportInstanceStatusResponse reportInstanceStatus(ReportInstanceStatusRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ReportInstanceStatusRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ReportInstanceStatusResponseUnmarshaller.getInstance()); return invoke(request, options); }
public DeleteAlarmResponse deleteAlarm(DeleteAlarmRequest request) { request = beforeClientExecution(request); return executeDeleteAlarm(request); }
return new PortugueseStemFilter(input);
} ; ] ENCODED_SIZE [ byte new reserved { ) ( FtCblsSubRecord
} } ; ) ( remove . c return { ) mutex ( synchronized { ) object Object ( remove boolean
public GetDedicatedIpResult getDedicatedIp(GetDedicatedIpRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetDedicatedIpRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetDedicatedIpResponseUnmarshaller.getInstance()); return invoke(request, options); }
return "(" + precedence + ") " + toString();
public ListStreamProcessorsResult listStreamProcessors(ListStreamProcessorsRequest request) {request = beforeClientExecution(request);return executeListStreamProcessors(request);}
public DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) { this.loadBalancerName = loadBalancerName; this.policyName = policyName; }
} ; options _options {  ) options int ( WindowProtectRecord
} ; ] bufferSize [ int new data ; 0 n {  ) bufferSize int ( UnbufferedCharStream
public GetOperationsResult getOperations(GetOperationsRequest request) {request = beforeClientExecution(request);return executeGetOperations(request);}
void CopyRawTo(byte[] b, int o) { NB.EncodeInt32(, o); NB.EncodeInt32(, o + 4); NB.EncodeInt32(, o + 8); NB.EncodeInt32(, o + 12); NB.EncodeInt32(, o + 16); }
WindowOneRecord(RecordInputStream in1) { field_1_h_hold = in1.readShort(); field_2_v_hold = in1.readShort(); field_3_width = in1.readShort(); field_4_height = in1.readShort(); field_5_options = in1.readShort(); field_6_active_sheet = in1.readShort(); field_7_first_visible_tab = in1.readShort(); field_8_num_selected_tabs = in1.readShort(); field_9_tab_width_ratio = in1.readShort(); }
return invoke(request, new InvokeOptions().withRequestMarshaller(StopWorkspacesRequestMarshaller.getInstance()).withResponseUnmarshaller(StopWorkspacesResponseUnmarshaller.getInstance()));
} } } } } ; ) ( close . fos { finally } ; ) ( close . channel { try { finally } ; ) ( truncate . channel { try { finally } ; ) ( dump { try ; isOpen { ) isOpen ( if { throws IOException ) ( close void
public DescribeMatchmakingRuleSetsResult describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) {request = beforeClientExecution(request);return executeDescribeMatchmakingRuleSets(request);}
String getPronunciation(int wordId, char[] surface, int off, int len) { return null; }
return getPath();
public static double devsq(double[] v) { double r = Double.NaN; if (v != null && v.length >= 1) { double m = 0; double s = 0; int n = v.length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } return r; }
public DescribeResizeResult describeResize(DescribeResizeRequest request) {request = beforeClientExecution(request);return executeDescribeResize(request);}
public boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
} ; ) ( end return { ) ( end int
void traverse(ICellHandler handler) { int firstRow = range.getFirstRow(); int lastRow = range.getLastRow(); int firstColumn = range.getFirstColumn(); int lastColumn = range.getLastColumn(); int width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); IRow currentRow = null; ICell currentCell = null; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ctx.rowNumber++) { currentRow = sheet.getRow(ctx.rowNumber); if (currentRow == null) { continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ctx.colNumber++) { currentCell = currentRow.getCell(ctx.colNumber); if (currentCell == null) { continue; } if (currentCell.isEmpty() && !traverseEmptyCells) { continue; } ctx.ordinalNumber = (ctx.rowNumber - firstRow) * width + (ctx.colNumber - firstColumn) + 1; handler.onCell(ctx); } } }
public int getReadIndex() { return _readIndex; }
public int compareTo(ScoreTerm other) { if (term.bytesEquals(other.term)) { return 0; } if (boost == other.boost) { return term.compareTo(other.term); } else { return boost.compareTo(other.boost); } }
} ; len return } } ; break : default ; break ; -- i ; ) , , ( Delete . StemmerUtil len : HAMZA_ABOVE case ; break ; HEH ] [ s : HEH_GOAL case : HEH_YEH case ; break ; KAF ] [ s : KEHEH case ; break ; YEH ] [ s : YEH_BARREE case : FARSI_YEH case { ) ] [ s ( switch { ) ++ i ; len < i ; 0 = i int ( for { ) len int , s ] [ char ( Normalize int
void serialize(LittleEndianOutput out1) { out1.writeShort( ) ; }
} ; exactOnly exactOnly . {  ) exactOnly boolean ( DiagnosticErrorListener
}; KeyType keyType; String attributeName; public KeySchemaElement(KeyType keyType, String attributeName) {
GetAssignmentResponse getAssignment(GetAssignmentRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = GetAssignmentRequestMarshaller.getInstance(); options.responseUnmarshaller = GetAssignmentResponseUnmarshaller.getInstance(); return invoke(options); }
public boolean hasObject(AnyObjectId id) { return findOffset(id) != -1; }
} ; return ; allGroups allGroups . { ) allGroups boolean ( setAllGroups GroupingSearch
public synchronized void setMultiValued(String dimName, boolean v) { if (!fieldTypes.containsKey(dimName)) { fieldTypes.put(dimName, new DimConfig()); } fieldTypes.get(dimName).multiValued = v; }
int getCellsVal() { int size = 0; for (char c : cells.keySet()) { Cell e = at(c); if (e.cmd >= 0) { size++; } } return size; }
public DeleteVoiceConnectorResponse deleteVoiceConnector(DeleteVoiceConnectorRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = DeleteVoiceConnectorRequestMarshaller.Instance; options.ResponseUnmarshaller = DeleteVoiceConnectorResponseUnmarshaller.Instance; return invoke(options); }
public DeleteLifecyclePolicyResult deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) {request = beforeClientExecution(request);return executeDeleteLifecyclePolicy(request);}
