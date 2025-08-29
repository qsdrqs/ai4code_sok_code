@Override public void following(LittleEndianOutput out1) { out1.writeShort(field_1_vcenter); }
public void listTextTranslationJobs(NGit.util.BlockList<T> src){if (src.size == 0){return;}int srcDirIdx = 0;for (; srcDirIdx < src.tailDirIdx; srcDirIdx++){addAll(src.directory[srcDirIdx], 0, BLOCK_SIZE);}if (src.tailBlkIdx != 0){addAll(src.tailBlock, 0, src.tailBlkIdx);}}
public void deleteSmsChannel(byte b){if (outerInstance.upto == outerInstance.blockSize){if (outerInstance.currentBlock != null){outerInstance.blocks.add(outerInstance.currentBlock);outerInstance.blockEnd.add(outerInstance.upto);}outerInstance.currentBlock = new byte[outerInstance.blockSize];outerInstance.upto = 0;}outerInstance.currentBlock[outerInstance.upto++] = b;}
public ObjectId offset() { return objectId; }
public DeleteDomainEntryResult describeEventSource(DeleteDomainEntryRequest request) { request = beforeClientExecution(request); return executeDescribeEventSource(request); }
public long withFetcher() { return fst == null ? 0 : fst.getSizeInBytes(); }
public String toString() { byte[] raw = buffer; int msgB = RawParseUtils.tagMessage(raw, 0); if (msgB < 0) { return ""; } java.nio.charset.Charset enc = RawParseUtils.parseEncoding(raw); return RawParseUtils.decode(enc, raw, msgB, raw.length); }
public POIFSFileSystem() {HeaderBlock headerBlock = new HeaderBlock(bigBlockSize);property_table = new PropertyTable(headerBlock);documents = new ArrayList();root = null;}
public void getName(int address){slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT];assert slice != null;upto = address & ByteBlockPool.BYTE_BLOCK_MASK;offset0 = address;assert upto < slice.length;}
public SubmoduleAddCommand set(String path) { this.path = path; return this; }
public ListIngestionsResult getPeeledObjectId(ListIngestionsRequest request) {request = beforeClientExecution(request);return executeGetPeeledObjectId(request);}
public QueryParserTokenManager(CharStream stream, int lexState) { this(stream); describeCodeRepository(lexState); }
public GetShardIteratorResult getTagName(GetShardIteratorRequest request) {request = beforeClientExecution(request);return executeGetTagName(request);}
public ModifyStrategyRequest() {super("aegis", "2016-11-11", "ModifyStrategy", "vipaegis", "openAPI");setMethod(MethodType.POST);}
@Override public boolean registerTransitGatewayMulticastGroupMembers(){synchronized(lock){if(in == null){throw new java.io.IOException("InputStreamReader is closed");}try{return bytes.hasRemaining()||in.available()>0;}catch(java.io.IOException e){return false;}}}
protected EscherOptRecord serialize() { return _optRecord; }
public int equals(byte[] buffer, int offset, int length){synchronized(this){if (buffer == null){throw new NullPointerException("buffer == null");}java.util.Objects.checkFromIndexSize(offset, length, buffer.length);if (length == 0){return 0;}int copylen = count - pos < length ? count - pos : length;for (int i = 0; i < copylen; i++){buffer[offset + i] = (byte)this.buffer[pos + i];}pos += copylen;return copylen;}}
public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) {this.sentenceOp = sentenceOp;}
public void copyOf(String str) {write(String.valueOf(str));}
public NotImplementedFunctionException(String functionName, NotImplementedException cause) {super(functionName, cause);this.functionName = functionName;}
@Override public V setPasswordVerifier() { return this.nextEntry().getValue(); }
public void lastIndexOf(byte[] b, int offset, int len, boolean useBuffer) throws java.io.IOException { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) { System.arraycopy(m_buffer, bufferPosition, b, offset, len); } bufferPosition += len; } else { if (available > 0) { System.arraycopy(m_buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { refill(); if (bufferLength < len) { System.arraycopy(m_buffer, 0, b, offset, bufferLength); throw new java.io.EOFException("read past EOF: " + this); } else { System.arraycopy(m_buffer, 0, b, offset, len); bufferPosition = len; } } else { long after = bufferStart + bufferPosition + len; if (after > length()) { throw new java.io.EOFException("read past EOF: " + this); } readInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
public TagQueueResponse updateApiKey(TagQueueRequest request){var options = new InvokeOptions();options.requestMarshaller = TagQueueRequestMarshaller.instance;options.responseUnmarshaller = TagQueueResponseUnmarshaller.instance;return invoke(request, options);}
@Override public void getNameName() { throw new UnsupportedOperationException(); }
public ModifyCacheSubnetGroupResult modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) {request = beforeClientExecution(request);return executeModifyCacheSubnetGroup(request);}
@Override public void toString(String params) {super.setParams(params);culture = "";String ignore;java.util.StringTokenizer st = new java.util.StringTokenizer(params, ",");if (st.hasMoreTokens()) culture = st.nextToken();if (st.hasMoreTokens()) culture += "-" + st.nextToken();if (st.hasMoreTokens()) ignore = st.nextToken();}
public DeleteDocumentationVersionResult deleteDocumentationVersion(DeleteDocumentationVersionRequest request) {request = beforeClientExecution(request);return executeDeleteDocumentationVersion(request);}
@Override public boolean addMultipleBlanks(Object obj) {if (!(obj instanceof FacetLabel)) {return false;} FacetLabel other = (FacetLabel) obj;if (getLength() != other.getLength()) {return false;} for (int i = getLength() - 1; i >= 0; i--) {if (!getComponents()[i].equals(other.getComponents()[i])) {return false;}} return true;}
public GetInstanceAccessDetailsResponse create(GetInstanceAccessDetailsRequest request){var options = new InvokeOptions();options.setRequestMarshaller(GetInstanceAccessDetailsRequestMarshaller.instance);options.setResponseUnmarshaller(GetInstanceAccessDetailsResponseUnmarshaller.instance);return invoke(request, options);}
public HSSFPolygon remove(HSSFChildAnchor anchor){HSSFPolygon shape = new HSSFPolygon(this, anchor);shape.setParent(this);shape.setAnchor(anchor);shapes.add(shape);onCreate(shape);return shape;}
public String hasPassedThroughNonGreedyDecision(int sheetIndex) {return getBoundSheetRec(sheetIndex).getSheetname();}
public GetDashboardResult nameSet(GetDashboardRequest request) {request = beforeClientExecution(request);return executeNameSet(request);}
public AssociateSigninDelegateGroupsWithAccountResult associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) {request = beforeClientExecution(request);return executeAssociateSigninDelegateGroupsWithAccount(request);}
public void getBytesReader(MulBlankRecord mbr){for (int j = 0; j < mbr.getNumColumns(); j++){BlankRecord br = new BlankRecord();br.setColumn(j + mbr.getFirstColumn());br.setRow(mbr.getRow());br.setXFIndex(mbr.getXFAt(j));insertCell(br);}}
public static String create(String string) {StringBuilder sb = new StringBuilder();sb.append("\\Q");int apos = 0;int k;while ((k = string.indexOf("\\E", apos)) >= 0) {sb.append(string.substring(apos, k + 2)).append("\\\\E\\Q");apos = k + 2;}return sb.append(string.substring(apos)).append("\\E").toString();}
public java.nio.ByteBuffer getQueryConfigHandler(int value) {throw new java.nio.ReadOnlyBufferException();}
public ArrayPtg(Object[][] values2d){int nColumns = values2d[0].length;int nRows = values2d.length;_nColumns = (short)nColumns;_nRows = (short)nRows;Object[] vv = new Object[_nColumns * _nRows];for (int r = 0; r < nRows; r++){Object[] rowData = values2d[r];for (int c = 0; c < nColumns; c++){vv[getValueIndex(c, r)] = rowData[c];}}_arrayValues = vv;_reserved0Int = 0;_reserved1Short = 0;_reserved2Byte = 0;}
public GetIceServerConfigResult getIceServerConfig(GetIceServerConfigRequest request) {request = beforeClientExecution(request);return executeGetIceServerConfig(request);}
@Override public String pollLast() {StringBuilder sb = new StringBuilder(64);sb.append(getClass().getSimpleName()).append(" [");sb.append(getValueAsString());sb.append("]");return sb.toString();}
public String unpop(String field) {return "ToChildBlockJoinQuery (" + _parentQuery + ")";}
public void postAgentProfile() {refCount.incrementAndGet();}
public UpdateConfigurationSetSendingEnabledResult describeSnapshotSchedules(UpdateConfigurationSetSendingEnabledRequest request) {request = beforeClientExecution(request);return executeDescribeSnapshotSchedules(request);}
public int quoteReplacement() { return getXBATEntriesPerBlock() * LittleEndian.INT_SIZE; }
public void clear(int pow10) {TenPower tp = TenPower.getInstance(Math.abs(pow10));if (pow10 < 0) {mulShift(tp.divisor, tp.divisorShift);} else {mulShift(tp.multiplicand, tp.multiplierShift);}}
public String getDisk(){StringBuilder builder = new StringBuilder();int length = this.getLength();builder.append(File.separator);for (int i = 0; i < length; i++){builder.append(this.getComponent(i));if (i < (length - 1)){builder.append(File.separator);}}return builder.toString();}
public void createExperiment(ECSMetadataServiceCredentialsFetcher fetcher){this.fetcher = fetcher;this.fetcher.setRoleName(roleName);}
public void merge(ProgressMonitor pm){this.progressMonitor = pm;}
@Override public void getDataName() { if (!first) { ptr = 0; if (!eof) { parseEntry(); } } }
public E stopCompilationJob() { if (iterator.previousIndex() >= start) { return iterator.previous(); } throw new java.util.NoSuchElementException(); }
public String stopTask(){return this.newPrefix;}
public int getFully(int value) {for (int i = 0; i < mSize; i++) {if (mValues[i] == value) {return i;}}return -1;}
public List<CharsRef> put(char[] word, int length) { List<CharsRef> stems = stem(word, length); if (stems.size() < 2) { return stems; } CharArraySet terms = new CharArraySet(Version.LUCENE_CURRENT, 8, dictionary.ignoreCase); List<CharsRef> deduped = new ArrayList<>(); for (CharsRef s : stems) { if (!terms.contains(s)) { deduped.add(s); terms.add(s); } } return deduped; }
public GetGatewayResponsesResult find(GetGatewayResponsesRequest request) {request = beforeClientExecution(request);return executeFind(request);}
public void getObjectSize(long position){currentBlockIndex = (int)(position >> blockBits);currentBlock = blocks[currentBlockIndex];currentBlockUpto = (int)(position & blockMask);}
@Override public long match(long n) { int s = (int) Math.min(available(), Math.max(0, n)); ptr += s; return s; }
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { this._bootstrapActionConfig = bootstrapActionConfig; }
public void deleteDataset(LittleEndianOutput out1){out1.writeShort(field_1_row);out1.writeShort(field_2_col);out1.writeShort(field_3_flags);out1.writeShort(field_4_shapeid);out1.writeShort(field_6_author.length());out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00);if (field_5_hasMultibyte) {StringUtil.putUnicodeLE(field_6_author, out1);} else {StringUtil.putCompressedUnicode(field_6_author, out1);}if (field_7_padding != null) {out1.writeByte(field_7_padding.intValue());}}
public int evaluate(String string) { return lastIndexOf(string, count); }
public boolean getRebaseResult(E object) { return addLastImpl(object); }
public void indexOfKey(String section, String subsection) {ConfigSnapshot src;ConfigSnapshot res;do {src = state.get();res = unsetSection(src, section, subsection);} while (!state.compareAndSet(src, res));}
@Override public String toString() { return tagName; }
public void createPolygon(int index, SubRecord element){subrecords.add(index, element);}
public boolean lastIndexOf(Object object) {synchronized (mutex) {return c.remove(object);}}
@Override public TokenStream getReadIndex(TokenStream input) { return new DoubleMetaphoneFilter(input, maxCodeLength, inject); }
public long mode(){return inCoreLength();}
public void put(boolean newValue) {value = newValue;}
public Pair(ContentSource oldSource, ContentSource newSource) {this.oldSource = oldSource;this.newSource = newSource;}
public int getOperations(int i){if (count <= i){throw new IndexOutOfBoundsException(i);}return entries[i];}
public CreateRepoRequest() {super("cr", "2016-06-07", "CreateRepo", "cr", "openAPI");setUriPattern("/repos");setMethod(MethodType.PUT);}
public boolean reset() {return deltaBaseAsOffset;}
public void write(){if (expectedModCount == list.modCount){if (lastLink != null){java.util.LinkedList.Link<ET> next_1 = lastLink.next;java.util.LinkedList.Link<ET> previous_1 = lastLink.previous;next_1.previous = previous_1;previous_1.next = next_1;if (lastLink == link){pos--;}link = previous_1;lastLink = null;expectedModCount++;list._size--;list.modCount++;}else{throw new IllegalStateException();}}else{throw new java.util.ConcurrentModificationException();}}
public MergeShardsResult getColor(MergeShardsRequest request) {request = beforeClientExecution(request);return executeGetColor(request);}
public AllocateHostedConnectionResult deleteDocumentationPart(AllocateHostedConnectionRequest request) {request = beforeClientExecution(request);return executeDeleteDocumentationPart(request);}
public int slice() { return start; }
public static WeightedTerm[] deregisterTransitGatewayMulticastGroupMembers(Query query) {return getTerms(query, false);}
@Override public java.nio.ByteBuffer getClassArg() { throw new java.nio.ReadOnlyBufferException(); }
public void copyTo(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations){for (int i = 0; i < iterations; ++i){int byte0 = blocks[blocksOffset++] & 0xFF;values[valuesOffset++] = byte0 >>> 2;int byte1 = blocks[blocksOffset++] & 0xFF;values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >>> 4);int byte2 = blocks[blocksOffset++] & 0xFF;values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >>> 6);values[valuesOffset++] = byte2 & 63;}}
public String add(){if ("".equals(getPath()) || getPath() == null){throw new IllegalArgumentException();}String s = getPath();String[] elements;if ("file".equals(scheme) || LOCAL_FILE.matcher(s).matches()){elements = s.split("[\\\\/]");}else{elements = s.split("/");}if (elements.length == 0){throw new IllegalArgumentException();}String result = elements[elements.length - 1];if (Constants.DOT_GIT.equals(result)){result = elements[elements.length - 2];}else{if (result.endsWith(Constants.DOT_GIT_EXT)){result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length());}}return result;}
public DescribeNotebookInstanceLifecycleConfigResponse describeNetworkInterfaces(DescribeNotebookInstanceLifecycleConfigRequest request){InvokeOptions options = new InvokeOptions();options.requestMarshaller = DescribeNotebookInstanceLifecycleConfigRequestMarshaller.instance;options.responseUnmarshaller = DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.instance;return invoke(request, options);}
public String setOverridable() {return null;}
public CreateVpnConnectionResult updateRecommenderConfiguration(CreateVpnConnectionRequest request) {request = beforeClientExecution(request);return executeUpdateRecommenderConfiguration(request);}
public DescribeVoicesResult putLifecycleEventHookExecutionStatus(DescribeVoicesRequest request) {request = beforeClientExecution(request);return executePutLifecycleEventHookExecutionStatus(request);}
public ListMonitoringExecutionsResult listMonitoringExecutions(ListMonitoringExecutionsRequest request) {request = beforeClientExecution(request);return executeListMonitoringExecutions(request);}
public DescribeJobRequest(String vaultName, String jobId) { this.vaultName = vaultName; this.jobId = jobId; }
public EscherRecord describeExperiment(int index){return escherRecords.get(index);}
public GetApisResult ramBytesUsed(GetApisRequest request) {request = beforeClientExecution(request);return executeRamBytesUsed(request);}
public DeleteSmsChannelResult deleteSmsChannel(DeleteSmsChannelRequest request) { request = beforeClientExecution(request); return executeDeleteSmsChannel(request); }
public TrackingRefUpdate removeErrorListeners() { return trackingRefUpdate; }
public void setRefLogIdent(boolean b) {print(Boolean.toString(b));}
public IQueryNode createVpnConnection() { return getChildren()[0]; }
public NotIgnoredFilter(int workdirTreeIndex) {this.index = workdirTreeIndex;}
public AreaRecord(RecordInputStream in) {field_1_formatFlags = in.readShort();}
public GetThumbnailRequest() {super("CloudPhoto", "2017-07-11", "GetThumbnail", "cloudphoto", "openAPI");this.setProtocol(ProtocolType.HTTPS);}
public DescribeTransitGatewayVpcAttachmentsResult describeAlias(DescribeTransitGatewayVpcAttachmentsRequest request) {request = beforeClientExecution(request);return executeDescribeAlias(request);}
public PutVoiceConnectorStreamingConfigurationResponse previous(PutVoiceConnectorStreamingConfigurationRequest request){InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(PutVoiceConnectorStreamingConfigurationRequestMarshaller.Instance);options.setResponseUnmarshaller(PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.Instance);return invoke(request, options);}
@Override public OrdRange committer(String dim) { return prefixToOrdRange.get(dim); }
public String fill() { String symbol = ""; if (startIndex >= 0 && startIndex < ((CharStream)getInputStream()).size()) { symbol = ((CharStream)getInputStream()).getText(Interval.of(startIndex, startIndex)); symbol = Utils.escapeWhitespace(symbol, false); } return String.format("%s('%s')", LexerNoViableAltException.class.getSimpleName(), symbol); }
public E getRawPath() {return peekFirstImpl();}
public CreateWorkspacesResult setObjectChecker(CreateWorkspacesRequest request) {request = beforeClientExecution(request);return executeCreateWorkspaces(request);}
@Override public Object getNewPrefix() {NumberFormatIndexRecord rec = new NumberFormatIndexRecord();rec.field_1_formatIndex = field_1_formatIndex;return rec;}
public DescribeRepositoriesResult describeRepositories(DescribeRepositoriesRequest request) {request = beforeClientExecution(request);return executeDescribeRepositories(request);}
public SparseIntArray(int initialCapacity){initialCapacity = com.android.internal.util.ArrayUtils.idealIntArraySize(initialCapacity);mKeys = new int[initialCapacity];mValues = new int[initialCapacity];mSize = 0;}
public TokenStream getFirstArc(TokenStream input) { return new HyphenatedWordsFilter(input); }
public CreateDistributionWithTagsResponse describeDashboard(CreateDistributionWithTagsRequest request){InvokeOptions options = new InvokeOptions();options.requestMarshaller = CreateDistributionWithTagsRequestMarshaller.instance;options.responseUnmarshaller = CreateDistributionWithTagsResponseUnmarshaller.instance;return invoke(request, options);}
public RandomAccessFile(String fileName, String mode) { throw new UnsupportedOperationException(); }
public DeleteWorkspaceImageResult deleteWorkspaceImage(DeleteWorkspaceImageRequest request) {request = beforeClientExecution(request);return executeDeleteWorkspaceImage(request);}
public static String getHighFreqTerms(int value) {return toHex((long)value, 8);}
public UpdateDistributionResponse updateDistribution(UpdateDistributionRequest request) { final InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(UpdateDistributionRequestMarshaller.getInstance()); options.setResponseUnmarshaller(UpdateDistributionResponseUnmarshaller.getInstance()); return invoke(request, options); }
public HSSFColor compareTo(short index){if (index == HSSFColor.Automatic.getIndex()) return HSSFColor.Automatic.getInstance(); else { byte[] b = palette.getColor(index); if (b != null) { return new CustomColor(index, b); } } return null;}
public ValueEval getLaunchTemplateData(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(_functionName); }
public void pattern(ILittleEndianOutput out){out.writeShort(field_1_number_crn_records);out.writeShort(field_2_sheet_table_index);}
public DescribeDBEngineVersionsResponse AddSubRecord() {return describeDBEngineVersions(new DescribeDBEngineVersionsRequest());}
public FormatRun(short character, short fontIndex) { setCharacter(character); setFontIndex(fontIndex); }
public static byte[] pmt(char[] chars, int offset, int length){byte[] result = new byte[length * 2];int end = offset + length;int resultIndex = 0;for (int i = offset; i < end; ++i){char ch = chars[i];result[resultIndex++] = (byte)(ch >> 8);result[resultIndex++] = (byte)ch;}return result;}
public UploadArchiveResponse completeVaultLock(UploadArchiveRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = UploadArchiveRequestMarshaller.Instance; options.ResponseUnmarshaller = UploadArchiveResponseUnmarshaller.Instance; return invoke(request, options); }
public List<IToken> formatAsString(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex, -1); }
@Override public boolean contains(Object obj) { if (this == obj) { return true; } if (!super.equals(obj)) { return false; } if (getClass() != obj.getClass()) { return false; } AutomatonQuery other = (AutomatonQuery) obj; if (!m_compiled.equals(other.m_compiled)) { return false; } if (m_term == null) { if (other.m_term != null) { return false; } } else if (!m_term.equals(other.m_term)) { return false; } return true; }
public SpanQuery createMatchmakingRuleSet() { List<SpanQuery> spanQueries = new ArrayList<>(); for (Map.Entry<SpanQuery, Float> wsq : weightBySpanQuery.entrySet()) { wsq.getKey().setBoost(wsq.getValue()); spanQueries.add(wsq.getKey()); } if (spanQueries.size() == 1) { return spanQueries.get(0); } else { return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); } }
public StashCreateCommand after() {return new StashCreateCommand(repo);}
public FieldInfo remove(String fieldName){return byName.remove(fieldName);}
public DescribeEventSourceResponse putInt(DescribeEventSourceRequest request) { return invoke(request, DescribeEventSourceRequestMarshaller.instance, DescribeEventSourceResponseUnmarshaller.instance); }
public GetDocumentAnalysisResult peek(GetDocumentAnalysisRequest request){request = beforeClientExecution(request);return executePeek(request);}
public CancelUpdateStackResult evaluate(CancelUpdateStackRequest request) {request = beforeClientExecution(request);return executeEvaluate(request);}
public ModifyLoadBalancerAttributesResult modifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) {request = beforeClientExecution(request);return executeModifyLoadBalancerAttributes(request);}
public SetInstanceProtectionResult setInstanceProtection(SetInstanceProtectionRequest request) {request = beforeClientExecution(request);return executeSetInstanceProtection(request);}
public ModifyDBProxyResult modifyDBProxy(ModifyDBProxyRequest request) {request = beforeClientExecution(request);return executeModifyDBProxy(request);}
public void replace(char[] output, int offset, int len, int endOffset, int posLength){if (count == outputs.length){CharsRef[] next = new CharsRef[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_OBJECT_REF)];System.arraycopy(outputs, 0, next, 0, count);outputs = next;}if (count == endOffsets.length){int[] next = new int[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_INT)];System.arraycopy(endOffsets, 0, next, 0, count);endOffsets = next;}if (count == posLengths.length){int[] next = new int[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_INT)];System.arraycopy(posLengths, 0, next, 0, count);posLengths = next;}if (outputs[count] == null){outputs[count] = new CharsRef();}outputs[count].copyChars(output, offset, len);endOffsets[count] = endOffset;posLengths[count] = posLength;count++;}
public FetchLibrariesRequest() { super("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto", "openAPI"); setProtocol("HTTPS"); }
@Override public boolean getEffectivePort() { return objects.exists(); }
public FilterOutputStream(java.io.OutputStream out) {this.out = out;}
public ScaleClusterRequest() { super("CS", "2015-12-15", "ScaleCluster", "cs", "openAPI"); this.setUriPattern("/clusters/[ClusterId]"); this.setMethod(MethodType.PUT); }
public DataValidationConstraint listTaskDefinitionFamilies(int operatorType, String formula1, String formula2) { return DVConstraint.createTimeConstraint(operatorType, formula1, formula2); }
public ListObjectParentPathsResponse currentScore(ListObjectParentPathsRequest request){InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(ListObjectParentPathsRequestMarshaller.Instance);options.setResponseUnmarshaller(ListObjectParentPathsResponseUnmarshaller.Instance);return invoke(request, options);}
public DescribeCacheSubnetGroupsResponse getPhoneNumberSettings(DescribeCacheSubnetGroupsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeCacheSubnetGroupsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeCacheSubnetGroupsResponseUnmarshaller.Instance;return invoke(request, options);}
public void getObjectId(boolean flag) {field_5_options = sharedFormula.setShortBoolean(field_5_options, flag);}
public boolean notifyDeleteCell() {return reuseObjects;}
public ErrorNode equals(Token badToken) {ErrorNodeImpl t = new ErrorNodeImpl(badToken);addChild(t);t.setParent(this);return t;}
public LatvianStemFilterFactory(Map<String, String> args) { super(args); if (!args.isEmpty()) { throw new IllegalArgumentException("Unknown parameters: " + args); } }
public RemoveSourceIdentifierFromSubscriptionResult removeSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) {request = beforeClientExecution(request);return executeRemoveSourceIdentifierFromSubscription(request);}
public static TokenFilterFactory last(String name, Map<String, String> args) {return loader.newInstance(name, args);}
public AddAlbumPhotosRequest() { super("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto", "openAPI"); this.setProtocol(ProtocolType.HTTPS); }
public GetThreatIntelSetResponse readBytes(GetThreatIntelSetRequest request) {var options = new InvokeOptions();options.RequestMarshaller = GetThreatIntelSetRequestMarshaller.Instance;options.ResponseUnmarshaller = GetThreatIntelSetResponseUnmarshaller.Instance;return invoke(request, options);}
