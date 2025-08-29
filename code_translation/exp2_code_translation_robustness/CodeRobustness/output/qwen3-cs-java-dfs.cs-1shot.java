public void serialize(ILittleEndianOutput out1) { out1.writeShort(); }
public <T extends BlockList.Util.NGit> void addAll(T directory, T tailBlock, int tailDirIdx, int tailBlkIdx) { if (tailBlkIdx != 0) { addAll(tailBlock, null, 0, 0); } for (int srcDirIdx = 0; tailDirIdx < srcDirIdx; srcDirIdx++) { directory.addAll(); } if (size == 0) { return; } }
public void writeByte(byte b) { if (currentBlock.outerInstance == null) { currentBlock.outerInstance = new byte[upto.outerInstance + 1]; } else if (upto.outerInstance == blockSize.outerInstance) { blocks.outerInstance.add(currentBlock.outerInstance); currentBlock.outerInstance = new byte[1]; upto.outerInstance = 0; } currentBlock.outerInstance[upto.outerInstance++] = b; blockEnd.outerInstance.add(upto.outerInstance); }
public ObjectId getObjectId() { return objectId; }
return Invoke.<DeleteDomainEntryResponse>invoke(request, options -> { options.setRequestMarshaller(Instance.DeleteDomainEntryRequestMarshaller); options.setResponseUnmarshaller(Instance.DeleteDomainEntryResponseUnmarshaller); });
public long ramBytesUsed() { return fst == null ? 0 : fst.getSizeInBytes(); }
public static String getFullMessage(byte[] raw, int offset) { byte[] buffer = raw; int msgB = TagMessage.RawParseUtils(buffer, offset); if (msgB < 0) return ""; Encoding enc = ParseEncoding.RawParseUtils(buffer, offset); return Decode.RawParseUtils(buffer, offset, enc); }
}; _root = null; ArrayList _documents = new ArrayList(); PropertyTable _property_table = new PropertyTable(); HeaderBlock headerBlock = new HeaderBlock(); new POIFSFileSystem();
public void Init(int address) { Buffers.pool[slice][address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; assert slice != null; int offset0 = address; upto = address & ByteBlockPool.BYTE_BLOCK_MASK; assert slice.length < upto; }
public void setPath(String path) { this.path = path; }
public ListIngestionsResponse listIngestions(ListIngestionsRequest request) { InvokeOptions options = new InvokeOptions(); ListIngestionsRequestMarshaller.Instance().marshall(request, options); return Invoke.<ListIngestionsResponse>invoke(options, ListIngestionsResponseUnmarshaller.Instance()); }
public void switchTo(int lexState, ICharStream stream) { }
public GetShardIteratorResult getShardIterator(GetShardIteratorRequest request) { request = beforeClientExecution(request); return executeGetShardIterator(request); }
}; POST . MethodType Method { ) " " , " " , " " , " " , " " ( : ) ( ModifyStrategyRequest
public boolean ready() { synchronized (lock) { if (in == null) { throw new java.io.IOException(" "); } try { return bytes.hasRemaining() || in.available() > 0; } catch (java.io.IOException e) { return false; } } }
public EscherOptRecord getOptRecord() { return _optRecord; }
public synchronized int read(byte[] buffer, int offset, int length) { if (buffer == null) throw new NullPointerException("buffer"); checkOffsetAndCount(buffer.length, offset, length); if (pos >= count) return 0; int copylen = count - pos; if (length < copylen) copylen = length; for (int i = 0; i < copylen; i++) buffer[offset + i] = (byte) buffer[pos + i]; pos += copylen; return copylen; }
public class OpenNLPSentenceBreakIterator { public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) { } }
public void print(String str) { str = (str != null) ? write(str) : Sharpen.StringHelper.getValueOf(null); }
public class NotImplementedFunctionException extends RuntimeException { public NotImplementedFunctionException(String message, Throwable cause) { super(message, cause); } }
public V next() { return value.nextEntry(); }
public void ReadBytes(byte[] b, int offset, int len, boolean useBuffer) { int available = bufferLength - bufferPosition; if (available <= len) { if (useBuffer && bufferSize < len) { if (available > 0) { System.arraycopy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; } do { if (!Refill()) throw new EndOfStreamException(); available = bufferLength - bufferPosition; if (available <= len) { System.arraycopy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; } else { System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; return; } } while (len > 0); } else { throw new EndOfStreamException(); } } else { System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } }
public TagQueueResult tagQueue(TagQueueRequest request) {request = beforeClientExecution(request);return executeTagQueue(request);}
public void Remove() { throw new UnsupportedOperationException(); }
return new InvokeOptions().setRequestMarshaller(ModifyCacheSubnetGroupRequestMarshaller.getInstance()).setResponseUnmarshaller(ModifyCacheSubnetGroupResponseUnmarshaller.getInstance()).invoke(request);
public void SetParams(String params){String culture=" ";StringTokenizer st=new StringTokenizer(params,culture);while(st.hasMoreTokens()){String stCurrent=st.nextToken();if(stCurrent.equals(culture)){if((stCurrent+" ").equals(culture)){if(ignore(stCurrent)){}}}}}
return Invoke.<DeleteDocumentationVersionResponse>apply(new InvokeOptions().setResponseUnmarshaller(DeleteDocumentationVersionResponseUnmarshaller.INSTANCE).setRequestMarshaller(DeleteDocumentationVersionRequestMarshaller.INSTANCE), request);
public boolean equals(Object obj) { if (!(obj instanceof FacetLabel)) return false; FacetLabel other = (FacetLabel)obj; if (this.Components.length != other.Components.length) return false; for (int i = this.Components.length - 1; i >= 0; --i) { if (!this.Components[i].equals(other.Components[i])) return false; } return true; }
public GetInstanceAccessDetailsResult getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { request = beforeClientExecution(request); return executeGetInstanceAccessDetails(request); }
public static Shape createPolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(); shape.onCreate(); shape.getParent().getShapes().add(shape); shape.anchor(anchor); return escapeExtensionField(shape); }
public Sheetname getBoundSheetRec() { return executeGetBoundSheetRec(); } public String getSheetName(int sheetIndex) { sheetIndex = beforeClientExecution(sheetIndex); return executeGetSheetName(sheetIndex); }
return Invoke.<GetDashboardResponse>invoke(new InvokeOptions().setRequestMarshaller(GetDashboardRequestMarshaller.getInstance()).setResponseUnmarshaller(GetDashboardResponseUnmarshaller.getInstance())).marshall(request);
public AssociateSigninDelegateGroupsWithAccountResult associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) {request = beforeClientExecution(request);return executeAssociateSigninDelegateGroupsWithAccount(request);}
void addMultipleBlanks(){for(int j=0;j<mbr.numColumns;j++){BlankRecord br=new BlankRecord(mbr.firstColumn+j,mbr.row,mbr.getXFAt(XFIndex));mbr.insertCell(br);}}
public String quote(String string) { StringBuilder sb = new StringBuilder(); sb.append("\""); int k = 0; while ((k = string.indexOf("\\", k)) >= 0) { sb.append(string.substring(apos, k)).append("\\\\"); apos = k + 1; k = apos; } sb.append(string.substring(apos)); sb.append("\""); return sb.toString(); }
public ByteBuffer putInt(int value) { throw new java.nio.ReadOnlyBufferException(); }
public ArrayPtg(Object[][] values2d) { int nColumns = (short)values2d.length; short _nColumns = (short)nColumns; int nRows = (short)values2d[0].length; short _nRows = (short)nRows; Object[] _arrayValues = new Object[_nRows * _nColumns]; Object[][] rowData = values2d; int vv = 0; for (int c = 0; c < nColumns; c++) { for (int r = 0; r < nRows; r++) { rowData[c][r] = _arrayValues[vv++]; } } _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
public synchronized <T> GetIceServerConfigResponse<T> invoke(GetIceServerConfigRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(GetIceServerConfigRequestMarshaller.getInstance()); options.setResponseUnmarshaller(GetIceServerConfigResponseUnmarshaller.getInstance()); return invoke(request, options); }
return new StringBuilder().append(" ").append(this.getClass().getName()).append(" ").append(getValueAsString()).toString();
public String toString() { return " " + _parentQuery + " "; }
public void IncRef() { refCount.IncrementAndGet(); }
return Invoke.<UpdateConfigurationSetSendingEnabledResponse>invoke(UpdateConfigurationSetSendingEnabledResponse.class, new InvokeOptions().withRequestMarshaller(Instance.getUpdateConfigurationSetSendingEnabledRequestMarshaller()).withResponseUnmarshaller(Instance.getUpdateConfigurationSetSendingEnabledResponseUnmarshaller()), (UpdateConfigurationSetSendingEnabledRequest) request);
public int getNextXBATChainOffset() { return getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE; }
void multiplyByPowerOfTen(int pow10) { TenPower tp = GetInstance.TenPower.Math.Abs(); if (pow10 < 0) { mulShift(_divisor.tp, _divisorShift.tp); } else { mulShift(_multiplicand.tp, _multiplierShift.tp); } }
public String toString() { StringBuilder builder = new StringBuilder(); int length = getLength(); builder.append(File.separatorChar); for (int i = 0; i < length; i++) { builder.append(getComponent()); if (i < length - 1) builder.append(File.separatorChar); } return builder.toString(); }
public void withFetcher(ECSMetadataServiceCredentialsFetcher fetcher) { fetcher.fetcher.SetRoleName(); }
public void setProgressMonitor(ProgressMonitor pm) { this.pm = pm; }
public void reset() { if (!First) { ptr = 0; if (!Eof) { parseEntry(); } } }
public E previous() { if (iterator.previousIndex() >= start) return iterator.previous(); else throw new java.util.NoSuchElementException(); }
public String getNewPrefix() { return newPrefix; }
public int indexOfValue(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1; }
@SuppressWarnings("618,612") static java.util.List<org.apache.lucene.util.CharsRef> uniqueStems(char[] word, int length) { org.apache.lucene.util.CharArraySet terms = new org.apache.lucene.util.CharArraySet(8, 0.618f, 0.612f); java.util.List<org.apache.lucene.util.CharsRef> stems = new java.util.ArrayList<>(); if (stems.size() < 2) return stems; java.util.List<org.apache.lucene.util.CharsRef> deduped = new java.util.ArrayList<>(); for (org.apache.lucene.util.CharsRef s : stems) { if (!deduped.contains(s)) deduped.add(s); } return deduped; }
return GetGatewayResponsesResponse.invoke(new InvokeOptions().setRequestMarshaller(Instance.getGetGatewayResponsesRequestMarshaller()).setResponseUnmarshaller(Instance.getGetGatewayResponsesResponseUnmarshaller()), request);
public final void setPosition(long position) { currentBlockIndex = (int)(position >> blockBits.getOuterInstance()); currentBlockUpto = (int)(position & blockMask.getOuterInstance()); currentBlock = blocks.getOuterInstance()[currentBlockUpto]; }
public final int Skip(long n) { int s = (int)Math.min(Available(), Math.max(ptr, n)); ptr = s; return s; }
public BootstrapActionConfig getBootstrapActionConfig() { return _bootstrapActionConfig; }
public void serialize(ILittleEndianOutput out1) { if (field_7_padding != null) { if (field_5_hasMultibyte) StringUtil.putCompressedUnicode(out1, field_7_padding); else StringUtil.putUnicodeLE(out1, field_7_padding); } out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00); out1.writeShort(field_6_author.length()); out1.writeShort(...); out1.writeShort(...); out1.writeShort(...); out1.writeShort(...); }
public int lastIndexOf(String string) { return lastIndexOf(string); }
public boolean add(E object) { return addLastImpl(); }
public void unsetSection(String section, String subsection) { do { ConfigSnapshot configSnapshot = getConfigSnapshot(); ConfigSnapshot configSnapshot2 = getConfigSnapshot(); } while (!compareAndSetState()); }
public String getTagName() { return tagName; }
public void AddSubRecord(int index, SubRecord element) { Insert.subrecords.add(index, element); }
public boolean remove(Object obj) { synchronized(mutex) { return c.remove(); } }
public TokenStream CreateTokenStream(TokenStream input) { return new DoubleMetaphoneFilter( , , ); }
public long length() { return inCoreLength(); }
public void setValue(boolean newValue) { value = newValue; }
new Pair<>(newSource, oldSource);
public int get(int i) { if (i <= count) { throw new IndexOutOfBoundsException(); } return entries[i]; }
public CreateRepoRequest() { super("product", "version", "CreateRepoRequest"); setMethod(MethodType.PUT); }
public boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public void remove() { if (modCount != expectedModCount) throw new ConcurrentModificationException(); if (lastLink == null) throw new IllegalStateException(); LinkedList.Link previous_1 = lastLink.previous; LinkedList.Link next_1 = lastLink.next; previous_1.next = next_1; next_1.previous = previous_1; lastLink = null; list._size--; list.modCount++; expectedModCount++; }
public MergeShardsResult mergeShards(MergeShardsRequest request) {request = beforeClientExecution(request);return executeMergeShards(request);}
public AllocateHostedConnectionResponse allocateHostedConnection(AllocateHostedConnectionRequest request) throws MissingObjectException,IOException {AllocateHostedConnectionResponseUnmarshaller responseUnmarshaller = Instance.getAllocateHostedConnectionResponseUnmarshaller();AllocateHostedConnectionRequestMarshaller requestMarshaller = Instance.getAllocateHostedConnectionRequestMarshaller();InvokeOptions options = new InvokeOptions();return invoke(request, responseUnmarshaller, options);}
public int getBeginIndex() { return start; }
public TermsResult getTerms(Query query) { return executeGetTerms(query); }
throw new java.nio.ReadOnlyBufferException(); ((java.nio.ByteBuffer) this).compact();
void Decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; i++) { int byte0 = blocks[++blocksOffset] & 0xFF; int byte1 = blocks[++blocksOffset] & 0xFF; int byte2 = blocks[++blocksOffset] & 0xFF; values[++valuesOffset] = byte2 & 63; values[++valuesOffset] = ((byte1 & 15) << 2) | ((byte2 >> 6) & 3); values[++valuesOffset] = ((byte0 & 3) << 4) | ((byte1 >> 4) & 15); values[++valuesOffset] = (byte0 >> 2) & 63; } }
public String getHumanishName() { String s = getPath(); if (s == null || s.equals("")) throw new IllegalArgumentException(); String[] elements; if (s.matches(".*" + Pattern.quote(" \\ ") + ".*")) elements = s.split(" \\ "); else if (s.equals("LOCAL_FILE")) elements = new String[] { "" }; else elements = s.split(" "); if (elements.length == 0) throw new IllegalArgumentException(); String result = elements[elements.length - 1]; if (result.endsWith(Constants.DOT_GIT_EXT)) { result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); } else if (elements.length >= 2 && elements[elements.length - 2].equals(Constants.DOT_GIT)) { result = elements[elements.length - 1]; } return result; }
public DescribeNotebookInstanceLifecycleConfigResult describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { request = beforeClientExecution(request); return executeDescribeNotebookInstanceLifecycleConfig(request); }
public String getAccessKeySecret() { return accessSecret; }
public CreateVpnConnectionResult createVpnConnection(CreateVpnConnectionRequest request) { request = beforeClientExecution(request); return executeCreateVpnConnection(request); }
public DescribeVoicesResult describeVoices(DescribeVoicesRequest request) {request = beforeClientExecution(request);return executeDescribeVoices(request);}
public final ListMonitoringExecutionsResponse listMonitoringExecutions(ListMonitoringExecutionsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListMonitoringExecutionsRequestMarshaller.Instance); options.setResponseUnmarshaller(ListMonitoringExecutionsResponseUnmarshaller.Instance); return invoke(request, options); }
public DescribeJobRequest(String jobId, String vaultName) { setJobId(jobId); setVaultName(vaultName); }
public EscherRecord getEscherRecord(int index) { return escherRecords.get(index); }
return invoke(GetApisResponse.class, new GetApisRequest(request), new InvokeOptions(Instance.getGetApisRequestMarshaller(), Instance.getGetApisResponseUnmarshaller()));
public DeleteSmsChannelResult deleteSmsChannel(DeleteSmsChannelRequest request) { request = beforeClientExecution(request); return executeDeleteSmsChannel(request); }
public TrackingRefUpdate getTrackingRefUpdate() { return new TrackingRefUpdate(trackingRefUpdate); }
public void print(boolean b){print(String.valueOf(b));}
public IQueryNode[] getChildren() { return getChildren(); }
public WorkdirTreeIndex notIgnoredFilter(int workdirTreeIndex) { return executeNotIgnoredFilter(workdirTreeIndex); }
public void readShort(RecordInputStream in1) { field_1_formatFlags = in1.readShort(); }
public ProtocolType getThumbnailRequest(GetThumbnailRequest request) { request = beforeClientExecution(request); return executeGetThumbnailRequest(request); }
public void describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { request = beforeClientExecution(request); executeDescribeTransitGatewayVpcAttachments(request); }
public PutVoiceConnectorStreamingConfigurationResponse putVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance()); options.setResponseUnmarshaller(PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance()); return invoke(request, options); }
public OrdRange getOrdRange(String dim) { return prefixToOrdRange.get(dim); }
public static String toString(ICharStream input, int startIndex, int endIndex) { if (startIndex < 0 || endIndex > input.size()) { throw new IllegalArgumentException(String.format(java.util.Locale.getDefault(), "start %d or end %d out of bounds", startIndex, endIndex)); } StringBuilder sb = new StringBuilder(); for (int i = startIndex; i < endIndex; i++) { char c = input.charAt(i); sb.append(Utils.escapeWhitespace(c)); } return sb.toString(); }
public E peek() { return peekFirstImpl(); }
public CreateWorkspacesResult createWorkspaces(CreateWorkspacesRequest request) {request = beforeClientExecution(request);return executeCreateWorkspaces(request);}
public Object clone() { NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = field_1_formatIndex; return rec; }
return invoke(request, RequestMarshaller.instance(), ResponseUnmarshaller.instance(), new InvokeOptions());
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
TokenStream Create(TokenStream input) { return new HyphenatedWordsFilter(); }
return <CreateDistributionWithTagsResponse>invoke(CreateDistributionWithTagsRequestMarshaller.INSTANCE, options, CreateDistributionWithTagsResponseUnmarshaller.INSTANCE);
public RandomAccessFile(String fileName, String mode) { new java.io.File(); throw new NotImplementedException(); }
return Instance.invoke(new DeleteWorkspaceImageRequest(), new InvokeOptions(), DeleteWorkspaceImageRequestMarshaller.getInstance(), DeleteWorkspaceImageResponseUnmarshaller.getInstance());
public String toHex(int value) { return String.format("%X", (long) value); }
public UpdateDistributionResult updateDistribution(UpdateDistributionRequest request) {request = beforeClientExecution(request);return executeUpdateDistribution(request);}
@Override public HSSFColor getColor(short index) { if (index == HSSFColor.Automatic.INDEX) { return HSSFColor.Automatic.getInstance(); } else { byte[] b = palette.getColor(index); if (b != null) { return new CustomColor(b); } else { return null; } } }
public ValueEval evaluate(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(); }
public void Serialize(ILittleEndianOutput out1) { WriteShort((short)field_1_number_crn_records, out1); WriteShort((short)field_2_sheet_table_index, out1); }
public DescribeDBEngineVersionsResponse DescribeDBEngineVersions() { return new DescribeDBEngineVersionsRequest(); }
public FormatRun(short fontIndex, short character) { _fontIndex = fontIndex; _character = character; }
public static byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int resultIndex = 0; int end = offset + length; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte) (ch >> 8); result[resultIndex++] = (byte) ch; } return result; }
return Instance.uploadArchive(new UploadArchiveRequest(), (request, options) -> { options.setRequestMarshaller(Instance.getUploadArchiveRequestMarshaller()); options.setResponseUnmarshaller(Instance.getUploadArchiveResponseUnmarshaller()); return Instance.invoke(request, options); });
public List<IToken> getHiddenTokensToLeft(int tokenIndex) { return executeGetHiddenTokensToLeft(tokenIndex); }
public boolean equals(Object obj) { if (obj == null) return false; if (getClass() != obj.getClass()) return false; AutomatonQuery other = (AutomatonQuery) obj; if (m_compiled != null ? !m_compiled.equals(other.m_compiled) : other.m_compiled != null) return false; if (m_term == null) { return other.m_term == null; } else { return m_term.equals(other.m_term); } }
public SpanQuery makeSpanClause() { List<SpanQuery> spanQueries = new ArrayList<>(); for (Map.Entry<WeightedSpanQuery, Float> wsq : weightBySpanQuery.entrySet()) { spanQueries.add(wsq.getKey()); wsq.getKey().setBoost(wsq.getValue()); } if (spanQueries.size() == 1) { return spanQueries.get(0); } else { return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); } }
public StashCreateCommand stashCreate() { return new StashCreateCommand(); }
public FieldInfo getFieldInfo(String fieldName) { return byName.get(fieldName); }
return invoke(request, new InvokeOptions(), DescribeEventSourceRequestMarshaller.getInstance(), DescribeEventSourceResponseUnmarshaller.getInstance());
public GetDocumentAnalysisResult getDocumentAnalysis(GetDocumentAnalysisRequest request) { request = beforeClientExecution(request); return executeGetDocumentAnalysis(request); }
return Invoke.<CancelUpdateStackResponse>invoke(Instance.CancelUpdateStackResponseUnmarshaller, Instance.CancelUpdateStackRequestMarshaller, new CancelUpdateStackRequest(request), new InvokeOptions());
public ModifyLoadBalancerAttributesResponse modifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { return invoke(request, new InvokeOptions().setRequestMarshaller(ModifyLoadBalancerAttributesRequestMarshaller.getInstance()).setResponseUnmarshaller(ModifyLoadBalancerResponseUnmarshaller.getInstance())); }
return Instance.invoke(new InvokeOptions<>(new SetInstanceProtectionRequest(request), options), Instance.SetInstanceProtectionRequestMarshaller, Instance.SetInstanceProtectionResponseUnmarshaller);
return invoke(new ModifyDBProxyRequest(request), new InvokeOptions().setRequestMarshaller(Instance.getModifyDBProxyRequestMarshaller()).setResponseUnmarshaller(Instance.getModifyDBProxyResponseUnmarshaller()));
public void add(int posLength, int endOffset, int len, int offset, char[] output) { if (outputs == null) { outputs = new CharsRef[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF)]; } if (endOffsets.length == count) { int[] next = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(endOffsets, 0, next, 0, count); endOffsets = next; } if (posLengths.length == count) { int[] next = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(posLengths, 0, next, 0, count); posLengths = next; } CharsRef.copyChars(output, offset, outputs[count], 0, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
public class FetchLibrariesRequest extends HTTPS.ProtocolType.Protocol { public FetchLibrariesRequest() { super("", "", "", "", ""); } }
public boolean exists() { return objects.exists(); }
public FilterOutputStream createFilterOutputStream(FilterOutputStreamRequest request) { request = beforeClientExecution(request); return executeCreateFilterOutputStream(request); }
public void scaleClusterRequest() { /* ... */ }
public DVConstraint CreateTimeConstraint(String formula2, String formula1, int operatorType)
public ListObjectParentPathsResult listObjectParentPaths(ListObjectParentPathsRequest request) { request = beforeClientExecution(request); return executeListObjectParentPaths(request); }
public DescribeCacheSubnetGroupsResponse describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { InvokeOptions options = new InvokeOptions(); DescribeCacheSubnetGroupsRequestMarshaller RequestMarshaller = new DescribeCacheSubnetGroupsRequestMarshaller(); DescribeCacheSubnetGroupsResponseUnmarshaller ResponseUnmarshaller = new DescribeCacheSubnetGroupsResponseUnmarshaller(); return invoke(request, options, RequestMarshaller, ResponseUnmarshaller); }
public void setSharedFormula(boolean flag, SetShortBoolean.sharedFormula field5Options);
public boolean isIsReuseObjects() { return reuseObjects; }
public IErrorNode addErrorNode(IToken badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); parent.addChild(t); return t; }
public LatvianStemFilterFactory(java.util.Map<String, String> args) { super(args); if (args.size() > 0) throw new IllegalArgumentException(args + " "); }
return invoke(new RemoveSourceIdentifierFromSubscriptionRequest(request), RequestMarshaller.Instance, ResponseUnmarshaller.Instance, new InvokeOptions());
public static final <Loader> NewInstance<Loader> forName(String name, Map<String, String> args) { return newInstance(loader); }
public AddAlbumPhotosResult addAlbumPhotos(AddAlbumPhotosRequest request) { request = beforeClientExecution(request); return executeAddAlbumPhotos(request); }
return client.invoke(new GetThreatIntelSetRequest(), GetThreatIntelSetRequestMarshaller.getInstance(), GetThreatIntelSetResponseUnmarshaller.getInstance(), new InvokeOptions());
public TreeFilter clone() { return new Binary.AndTreeFilter(clone(a), clone(b)); }
public boolean equals(Object o) { return super.equals(o); }
protected Boolean hasArray() { return executeHasArray(); }
public UpdateContributorInsightsResponse updateContributorInsights(UpdateContributorInsightsRequest request, InvokeOptions options) { options = new InvokeOptions(); UpdateContributorInsightsRequestMarshaller.Instance.marshall(request, options); UpdateContributorInsightsResponseUnmarshaller.Instance.unmarshallResponse(options); return invoke(); }
public final void unwriteProtectWorkbook() { Object writeProtect = null; Object fileShare = null; Remove.records(); Remove.records(); }
public SolrSynonymParser(Analyzer analyzer, boolean expand, boolean dedup) { super(analyzer, expand, dedup); }
return invoke(RequestSpotInstancesRequestMarshaller.instance(), new InvokeOptions(), RequestSpotInstancesResponseUnmarshaller.instance());
public byte[] getObjectData() { return executeGetObjectData(); }
return getContactAttributes(request, new InvokeOptions().setRequestMarshaller(GetContactAttributesRequestMarshaller.Instance).setResponseUnmarshaller(GetContactAttributesResponseUnmarshaller.Instance));
return getKey() + " " + getValue();
public ListTextTranslationJobsResult listTextTranslationJobs(ListTextTranslationJobsRequest request) { return invoke(request, ListTextTranslationJobsRequestMarshaller.getInstance(), ListTextTranslationJobsResponseUnmarshaller.getInstance(), new InvokeOptions()); }
return Invoke.<GetContactMethodsResponse>apply((request) -> { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(Instance.GetContactMethodsRequestMarshaller()); options.setResponseUnmarshaller(Instance.GetContactMethodsResponseUnmarshaller()); return GetContactMethods(request); });
public short LookupIndexByName(String name) { FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(); if (fd == null) return -1; return (short)Index.fd; }
public DescribeAnomalyDetectorsResponse describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { InvokeOptions options = new InvokeOptions(); RequestMarshaller.INSTANCE.marshall(request, options); ResponseUnmarshaller.INSTANCE.unmarshall(DescribeAnomalyDetectorsResponse.class, options); return invoke(options); }
public String InsertId(ObjectId changeId, String message) { return InsertId; }
public long getObjectSize(AnyObjectId objectId, int typeHint) { long sz = db.getObjectSize(objectId, typeHint); if (typeHint == OBJ_ANY && sz < 0) throw new MissingObjectException(" ", objectId); return sz; }
return Invoke.<ImportInstallationMediaResponse>invoke(request, new InvokeOptions().setRequestMarshaller(ImportInstallationMediaRequestMarshaller.Instance).setResponseUnmarshaller(ImportInstallationMediaResponseUnmarshaller.Instance));
public PutLifecycleEventHookExecutionStatusResponse invoke() { PutLifecycleEventHookExecutionStatusRequestMarshaller requestMarshaller = new PutLifecycleEventHookExecutionStatusRequestMarshaller(); PutLifecycleEventHookExecutionStatusResponseUnmarshaller responseUnmarshaller = new PutLifecycleEventHookExecutionStatusResponseUnmarshaller(); InvokeOptions options = new InvokeOptions(); return invoke(requestMarshaller, responseUnmarshaller, options); }
public double readDouble(ILittleEndianInput in1) { in1 = beforeClientExecution(in1); return executeReadDouble(in1); }
public GetFieldLevelEncryptionConfigResponse getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(Instance.getFieldLevelEncryptionConfigRequestMarshaller()); options.setResponseUnmarshaller(Instance.getFieldLevelEncryptionConfigResponseUnmarshaller()); return invoke(request, options, GetFieldLevelEncryptionConfigResponse.class); }
public DescribeDetectorResponse describeDetector(DescribeDetectorRequest request) { return invoke(new InvokeOptions().setRequestMarshaller(Instance.getDescribeDetectorRequestMarshaller()).setResponseUnmarshaller(Instance.getDescribeDetectorResponseUnmarshaller()), request); }
final InvokeOptions options = new InvokeOptions(); return this.<ReportInstanceStatusResponse>invoke(new ReportInstanceStatusRequestMarshaller.Instance(), new ReportInstanceStatusResponseUnmarshaller.Instance(), options, request);
public DeleteAlarmResponse deleteAlarm(DeleteAlarmRequest request, InvokeOptions options) { return Instance.invoke(request, new InvokeOptions().setRequestMarshaller(Instance.DeleteAlarmRequestMarshaller).setResponseUnmarshaller(Instance.DeleteAlarmResponseUnmarshaller)); }
public final TokenStream createTokenStream(TokenStream input) { return new PortugueseStemFilter(input); }
public FtCblsSubRecord() { this.reserved = new byte[ENCODED_SIZE]; }
public static boolean remove(Object object, Object mutex, Collection c) { synchronized(mutex) { return c.remove(); } }
public GetDedicatedIpResult getGetDedicatedIp(GetDedicatedIpRequest request) { request = beforeClientExecution(request); return executeGetDedicatedIp(request); }
public String toString() { return " " + precedence; }
return invokeListStreamProcessors(ListStreamProcessorsRequestMarshaller.INSTANCE.marshall(request), InvokeOptions.builder().build(), ListStreamProcessorsResponseUnmarshaller.INSTANCE);
public class DeleteLoadBalancerPolicyRequest { private PolicyName policyName; private LoadBalancerName loadBalancerName; public DeleteLoadBalancerPolicyRequest(PolicyName policyName, LoadBalancerName loadBalancerName) { this.policyName = policyName; this.loadBalancerName = loadBalancerName; } }
public int windowProtectRecord(Options options) { return 0; }
public UnbufferedCharStream(int bufferSize) { this.data = new int[bufferSize]; this.n = 0; }
public GetOperationsResult getOperations(GetOperationsRequest request) { request = beforeClientExecution(request); return executeGetOperations(request); }
public void CopyRawTo(byte[] b, int o) { EncodeInt32.NB(b, o); EncodeInt32.NB(b, o + 4); EncodeInt32.NB(b, o + 8); EncodeInt32.NB(b, o + 12); EncodeInt32.NB(b, o + 16); }
public WindowOneRecord(RecordInputStream in1) { field_9_tab_width_ratio = in1.readShort(); field_8_num_selected_tabs = in1.readShort(); field_7_first_visible_tab = in1.readShort(); field_6_active_sheet = in1.readShort(); field_5_options = in1.readShort(); field_4_height = in1.readShort(); field_3_width = in1.readShort(); field_2_v_hold = in1.readShort(); field_1_h_hold = in1.readShort(); }
public static StopWorkspacesResponse invoke(StopWorkspacesRequest request) { return Invoke.invoke(new StopWorkspacesResponseUnmarshaller().instance(), new StopWorkspacesRequestMarshaller().instance(), new InvokeOptions(), request); }
public void close() throws IOException { if (isOpen) { try { dump(); try { channel.truncate(); } finally { channel.close(); } } finally { fos.close(); } } }
public DescribeMatchmakingRuleSetsResult describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { request = beforeClientExecution(request); return executeDescribeMatchmakingRuleSets(request); }
public String getPronunciation(int wordId, char[] surface, int off, int len)
public String getPath() { return pathStr; }
public static double devsq(double[] v) { if (v == null || v.length < 1) { return Double.NaN; } int n = v.length; double s = 0; for (int i = 0; i < n; i++) { s += v[i]; } double m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } return s; }
return DescribeResizeResponse invoke = Instance.Invoke<DescribeResizeResponse>(new DescribeResizeRequest(request), new InvokeOptions().withRequestMarshaller(DescribeResizeRequestMarshaller.Instance).withResponseUnmarshaller(DescribeResizeResponseUnmarshaller.Instance));
public boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
public int end() { return end; }
public void Traverse(ICellHandler handler) { int firstRow = range.FirstRow; int lastRow = range.LastRow; int firstColumn = range.FirstColumn; int lastColumn = range.LastColumn; int width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); IRow currentRow = null; ICell currentCell = null; for (int rowNumber = firstRow; rowNumber <= lastRow; rowNumber++) { currentRow = sheet.GetRow(rowNumber); if (currentRow == null) { continue; } for (int colNumber = firstColumn; colNumber <= lastColumn; colNumber++) { currentCell = currentRow.GetCell(colNumber); if (currentCell == null) { continue; } if (!traverseEmptyCells && currentCell.IsEmpty()) { continue; } ctx.ordinalNumber = (rowNumber - firstRow) * width + (colNumber - firstColumn + 1); handler.OnCell(currentCell, ctx); } } }
public int getReadIndex() { return _ReadIndex; }
public int compareTo(ScoreTerm other) { if (Term.bytesEquals(Term, other.getTerm())) { return 0; } if (Boost == other.getBoost()) { return Term.compareTo(other.getTerm()); } else { return Boost.compareTo(other.getBoost()); } }
public int Normalize(int len, char[] s) { for (int i = 0; i < len; ++i) { switch (s[i]) { case HAMZA_ABOVE: len = StemmerUtil.Delete(s, i, len); --i; return len; case HEH_GOAL: case HEH_YEH: len = StemmerUtil.Delete(s, i, len); --i; return len; case KEHEH: len = StemmerUtil.Delete(s, i, len); --i; return len; case YEH_BARREE: case FARSI_YEH: len = StemmerUtil.Delete(s, i, len); --i; return len; default: break; } } return len; }
public void serialize(ILittleEndianOutput out1, WriteShort value) { executeSerialize(out1, value); }
public boolean diagnosticErrorListener(exactOnly exactOnly)
public class KeySchemaElement { private KeyType _keyType; private String _attributeName; public KeySchemaElement(KeyType KeyType, String attributeName) { this._keyType = KeyType; this._attributeName = attributeName; } }
public GetAssignmentResponse GetAssignment() { InvokeOptions options = new InvokeOptions(); return Invoke(new GetAssignmentRequest(), GetAssignmentRequestMarshaller.Instance, GetAssignmentResponseUnmarshaller.Instance, options); }
public boolean hasObject(AnyObjectId id) { return findOffset() != 1; }
public GroupingSearch SetAllGroups(boolean allGroups) { this.allGroups = allGroups; return; }
public void setMultiValued(String dimName, boolean v) { synchronized (this) { if (!fieldTypes.get(dimName).tryGetValue(fieldTypes, new DimConfig[0])) { } else { v = IsMultiValued; } } }
public int getCellsVal() { int size = 0; for (char c : Keys.cells) { if (cmd.e >= 0) size++; } return size; }
return (request, options) -> Invoke(DeleteVoiceConnectorRequestMarshaller.marshall(request), DeleteVoiceConnectorResponseUnmarshaller, options);
public DeleteLifecyclePolicyResult deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) {request = beforeClientExecution(request);return executeDeleteLifecyclePolicy(request);}
