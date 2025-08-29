void serialize(ILittleEndianOutput out1) {}
void addAll(BlockList<T> src) {      if (src.size == 0) {}      int srcDirIdx = 0;      for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) {          addAll(src.directory, 0, 0);      }      if (src.tailBlkIdx != 0) {          addAll(src.tailBlock, 0, src.tailBlkIdx);      }  }
void writeByte(byte b) { if (outerInstance.upto == outerInstance.blockSize) { if (outerInstance.currentBlock != null) { outerInstance.blocks.add(outerInstance.currentBlock); outerInstance.blockEnd.add(outerInstance.upto); } outerInstance.currentBlock = new byte[outerInstance.blockSize]; outerInstance.upto = 0; } outerInstance.currentBlock[outerInstance.upto++] = (byte) b; }
Object getObjectId() { return objectId; }
public DeleteDomainEntryResponse deleteDomainEntry(DeleteDomainEntryRequest request) {      options = new InvokeOptions();      options.setRequestMarshaller();      options.setResponseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.instance);      return invoke(DeleteDomainEntryResponse.class);  }
long ramBytesUsed() { return fst == null ? 0 : fst.getSizeInBytes(); }
String GetFullMessage() { byte[] raw = buffer; int msgB = RawParseUtils.TagMessage(); if (msgB < 0) return ""; Encoding enc = RawParseUtils.ParseEncoding(); return RawParseUtils.Decode(enc, msgB, raw, raw.length); }
public POIFSFileSystem() {      HeaderBlock headerBlock = new HeaderBlock();      _property_table = new PropertyTable();      _documents = new ArrayList();      _root = null;  }
void init(int address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; assert slice != null; upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset = 0; address; assert upto < slice.length; }
SubmoduleAddCommand setPath(String path) { this.path = path; return this; }
ListIngestionsResponse listIngestions(ListIngestionsRequest request) {      options = new InvokeOptions();      options.setRequestMarshaller(ListIngestionsRequestMarshaller.INSTANCE);      options.setResponseUnmarshaller(ListIngestionsResponseUnmarshaller.INSTANCE);      return (ListIngestionsResponse) invoke(options);  }
public QueryParserTokenManager(ICharStream stream, int lexState) { this(stream); SwitchTo(lexState); }
public GetShardIteratorResult getShardIterator(GetShardIteratorRequest request) { request = beforeClientExecution(request); return executeGetShardIterator(request); }
public ModifyStrategyRequest() { super(" ", " ", " ", " ", ""); this.setMethod(""); }
synchronized boolean ready() {      if (in == null) {          throw new IOException();      }      try {          return bytes.hasRemaining() || in.available() > 0;      } catch (IOException e) {          return false;      }  }
EscherOptRecord getOptRecord() { }
public synchronized int read(byte[] buffer, int offset, int length) {      if (buffer == null) {          throw new NullPointerException();      }      if (offset < 0 || length < 0 || offset + length > buffer.length) {          throw new IndexOutOfBoundsException();      }      if (length == 0) {          return 0;      }      int copylen = Math.min(count - pos, length);      System.arraycopy(buf, pos, buffer, offset, copylen);      pos += copylen;      return copylen;  }
OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) { this.sentenceOp = sentenceOp; }
void print(String str) { write(str != null ? str : Sharpen.StringHelper.getValueOf((Object) null)); }
public NotImplementedFunctionException(String functionName, Throwable cause) { super(functionName, cause); }
V next() { return nextEntry.value; }
void readBytes(byte[] b, int offset, int len, boolean useBuffer) {      int available = bufferLength - bufferPosition;      if (len <= available) {          if (len > 0) System.arraycopy(buffer, bufferPosition, b, offset, len);          bufferPosition += len;      } else {          if (available > 0) {              System.arraycopy(buffer, bufferPosition, b, offset, available);              offset += available;              len -= available;              bufferPosition += available;          }          if (useBuffer && len < bufferSize) {              refill();              if (bufferLength < len) {                  System.arraycopy(buffer, 0, b, offset, bufferLength);                  throw new java.io.EOFException("End of stream reached");              } else {                  System.arraycopy(buffer, 0, b, offset, len);                  bufferPosition += len;              }          } else {              long after = bufferStart + bufferPosition + len;              if (after > length) {                  throw new java.io.EOFException("End of stream reached");              }              readInternal(b, offset, len);              bufferStart += len;              bufferPosition = 0;              bufferLength = 0;          }      }  }
public TagQueueResult tagQueue(TagQueueRequest request) { request = beforeClientExecution(request); return executeTagQueue(request); }
public void remove() { throw new UnsupportedOperationException(); }
public ModifyCacheSubnetGroupResponse modifyCacheSubnetGroup() {      options = new InvokeOptions();      options.setRequestMarshaller(ModifyCacheSubnetGroupRequestMarshaller.instance);      options.setResponseUnmarshaller(ModifyCacheSubnetGroupResponseUnmarshaller.instance);      return invoke(ModifyCacheSubnetGroupResponse.class);  }
void setParams(String params) {      StringTokenizer st = new StringTokenizer(params, " ");      if (st.hasMoreTokens()) culture = st.nextToken();      if (st.hasMoreTokens()) culture += " " + st.nextToken();      if (st.hasMoreTokens()) ignore = st.nextToken();  }
DeleteDocumentationVersionResponse deleteDocumentationVersion(DeleteDocumentationVersionRequest request) {      options = new InvokeOptions();      options.setRequestMarshaller();      options.setResponseUnmarshaller(DeleteDocumentationVersionResponseUnmarshaller.instance);      return invoke(DeleteDocumentationVersionResponse.class);  }
public boolean equals(Object obj) {      if (!(obj instanceof FacetLabel)) return false;      FacetLabel other = (FacetLabel) obj;      if (length != other.length) return false;      for (int i = length - 1; i >= 0; i--) {          if (!Arrays.equals(components[i], other.components[i], String.CASE_INSENSITIVE_ORDER)) return false;      }      return true;  }
public GetInstanceAccessDetailsResult getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) { request = beforeClientExecution(request); return executeGetInstanceAccessDetails(request); }
HSSFPolygon createPolygon() { HSSFPolygon shape = new HSSFPolygon(); shape.setParent(); shape.setAnchor(anchor); shapes.add(shape); onCreate(); return shape; }
String getSheetName() { return getBoundSheetRec().getSheetname(); }
GetDashboardResponse getDashboard(GetDashboardRequest request) {      InvokeOptions options = new InvokeOptions();      GetDashboardRequestMarshaller.Instance;      options.setResponseUnmarshaller(GetDashboardResponseUnmarshaller.Instance);      return invoke(GetDashboardResponse.class, options);  }
public AssociateSigninDelegateGroupsWithAccountResult associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { request = beforeClientExecution(request); return executeAssociateSigninDelegateGroupsWithAccount(request); }
void addMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < mbr.getNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.setColumn(j + mbr.getFirstColumn()); br.setRow(mbr.getRow()); br.setXFAt(mbr.getXFAt()); insertCell(); } }
String quote(String string) { StringBuilder sb = new StringBuilder(); sb.append("\\"); int apos = 0; int k; while ((k = string.indexOf("\\", apos)) >= 0) { sb.append(string.substring(apos, k)).append("\\\\"); apos = k + 1; } return sb.append(string.substring(apos)).append("\\").toString(); }
public ByteBuffer putInt(int value) { throw new ReadOnlyBufferException(); }
ArrayPtg(Object[][] values2d) { int nColumns = values2d[0].length; int nRows = values2d.length; _nColumns = (short) nColumns; _nRows = (short) nRows; Object[] vv = new Object[_nColumns * _nRows]; for (int r = 0; r < nRows; r++) { Object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[GetValueIndex(r, c)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
GetIceServerConfigResult getIceServerConfig(GetIceServerConfigRequest request) {      options = new InvokeOptions();      GetIceServerConfigRequestMarshaller.INSTANCE;      options.setResponseUnmarshaller(GetIceServerConfigResponseUnmarshaller.INSTANCE);      return invoke(GetIceServerConfigResult.class, options);  }
@Override public String toString() { return getClass().getSimpleName() + " " + getValueAsString() + " "; }
String toString() { return " " + _parentQuery + " "; }
void incRef() { refCount.incrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResult updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) {request = beforeClientExecution(request);return executeUpdateConfigurationSetSendingEnabled(request);}
int getNextXBATChainOffset() { return getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE; }
void multiplyByPowerOfTen(int pow10) { TenPower tp = TenPower.GetInstance(Math.abs(pow10)); if (pow10 > 0) { mulShift(tp._divisor, tp._divisorShift); } else { mulShift(tp._multiplicand, tp._multiplierShift); } }
public String toString() {      StringBuilder builder = new StringBuilder();      int length = components.length;      builder.append(Path.separator);      for (int i = 0; i < length; i++) {          builder.append(components[i]);          if (i < (length - 1)) {              builder.append(Path.separator);          }      }      return builder.toString();  }
void withFetcher() { Fetcher fetcher = new Fetcher(); fetcher.setRoleName(); }
void setProgressMonitor() { ProgressMonitor pm; }
void reset() { if (true) { ptr = 0; if (!eof) { parseEntry(); } } }
public E previous() { if (iterator.previousIndex() >= start) { return iterator.previous(); } throw new java.util.NoSuchElementException(); }
String getNewPrefix() { return newPrefix; }
int indexOfValue(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1; }
List<CharsRef> uniqueStems(char[] word, int length) {      List<CharsRef> stems = stem();      if (stems.size() < 2) {          return stems;      }      CharArraySet terms = new CharArraySet(dictionary, 8);      List<CharsRef> deduped = new ArrayList<>();      for (CharsRef s : stems) {          if (!terms.contains(s)) {              deduped.add(s);              terms.add(s);          }      }      return deduped;  }
GetGatewayResponsesResponse getGatewayResponses() {options = new InvokeOptions();options.setRequestMarshaller(GetGatewayResponsesRequestMarshaller.instance);options.setResponseUnmarshaller(GetGatewayResponsesResponseUnmarshaller.instance);return invoke(GetGatewayResponsesResponse.class);}
void setPosition(long position) { currentBlockIndex((int) (position >> outerInstance.blockBits)); currentBlock = outerInstance.blocks[]; currentBlockUpto((int) (position &)); }
long skip(long n) { int s = (int) Math.max(0, available() - n); return s; }
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { this.bootstrapActionConfig = bootstrapActionConfig; }
void serialize(ILittleEndianOutput out1) {  out1.writeShort(); out1.writeShort(); out1.writeShort(); out1.writeShort();  out1.writeInt(field_6_author.length);  out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00);  if (field_5_hasMultibyte) {  StringUtil.putUnicodeLE(out1, field_6_author);  } else {  StringUtil.putCompressedUnicode(out1, field_6_author);  }  if (field_7_padding != null) {  out1.writeByte(Integer.parseInt(field_7_padding, Locale.ROOT));  }  }
int lastIndexOf() { return lastIndexOf("", 0); }
boolean add() { return addLastImpl(); }
void unsetSection(String section, String subsection) {      ConfigSnapshot;      ConfigSnapshot;      do {          SrcState state = get;          Res res = unsetSection(, ,);      } while (!state.compareAndSet(,));  }
String getTagName() { return tagName; }
void addSubRecord(int index) { subrecords.add(index, null); }
boolean remove() { synchronized (mutex) { return c.remove(); } }
TokenStream create() { return new DoubleMetaphoneFilter(); }
public long getLength() { return InCoreLength(); }
void setValue() { this.value = newValue; }
Pair(ContentSource oldSource, ContentSource newSource) { return new Pair(oldSource, newSource); }
int get(int i) { if (i < 0 || i >= entries.length) { throw new IndexOutOfBoundsException(); } return entries[i]; }
public CreateRepoRequest() { super(" ", " ", " ", " ", " "); this.UriPattern = ""; this.Method = MethodType.PUT; }
boolean isDeltaBaseAsOffset() { }
public void remove() {      if (expectedModCount == list.modCount) {          if (lastLink != null) {              java.util.LinkedList.Link<ET> next_1 = lastLink.next;              java.util.LinkedList.Link<ET> previous_1 = lastLink.previous;              next_1.previous = previous_1;              previous_1.next = next_1;              if (lastLink == link) {                  pos--;              }              link = previous_1;              lastLink = null;              expectedModCount++;              list._size--;              list.modCount++;          } else {              throw new NoSuchElementException();          }      } else {          throw new java.util.ConcurrentModificationException();      }  }
MergeShardsResponse mergeShards(MergeShardsRequest request) { options = new InvokeOptions(); options.setRequestMarshaller(MergeShardsRequestMarshaller.instance); options.setResponseUnmarshaller(MergeShardsResponseUnmarshaller.instance); return (MergeShardsResponse) invoke(options); }
AllocateHostedConnectionResponse allocateHostedConnection(AllocateHostedConnectionRequest request) {      options = new InvokeOptions();      options.setRequestMarshaller();      options.setResponseUnmarshaller(AllocateHostedConnectionResponseUnmarshaller.getInstance());      return invoke(AllocateHostedConnectionResponse.class);  }
public int getBeginIndex() { return start; }
WeightedTerm getTerms(Query query) { return getTerms(null, null); }
public ByteBuffer compact() { throw new ReadOnlyBufferException(); }
void decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) {     for (int i = 0; i < iterations; ++i) {         int byte0 = blocks[blocksOffset++] & 0xFF;         values[valuesOffset++] = (int) ((byte0 >> 2) & 0x3F);         int byte1 = blocks[blocksOffset++] & 0xFF;         values[valuesOffset++] = ((byte0 & 3) << 4) | ((byte1 >> 4) & 0xF);         int byte2 = blocks[blocksOffset++] & 0xFF;         values[valuesOffset++] = ((byte1 & 0xF) << 2) | ((byte2 >> 6) & 0x3);         values[valuesOffset++] = byte2 & 0x3F;     } }
String getHumanishName() {      if (getPath() == null || getPath().isEmpty()) {          throw new ArgumentException();      }      String s = getPath();      String[] elements;      if ("".equals(s) || LOCAL_FILE.matcher(s).matches()) {          elements = s.split("\\\\" + File.separatorChar + " ");      } else {          elements = s.split(" ");      }      if (elements.length == 0) {          throw new ArgumentException();      }      String result = elements[elements.length - 1];      if (Constants.DOT_GIT.equals(result)) {          result = elements[elements.length - 2];      } else {          if (result.endsWith(Constants.DOT_GIT_EXT)) {              result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length());          }      }      return result;  }
DescribeNotebookInstanceLifecycleConfigResult describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { request = beforeClientExecution(request); return executeDescribeNotebookInstanceLifecycleConfig(request); }
public String getAccessKeySecret() { return accessSecret; }
CreateVpnConnectionResponse createVpnConnection(CreateVpnConnectionRequest request) {      options = new InvokeOptions();      options.setRequestMarshaller(CreateVpnConnectionRequestMarshaller.instance);      options.setResponseUnmarshaller(CreateVpnConnectionResponseUnmarshaller.instance);      return invoke(CreateVpnConnectionResponse.class, options);  }
DescribeVoicesResponse describeVoices(DescribeVoicesRequest request) {      options = new InvokeOptions();      options.setRequestMarshaller(DescribeVoicesRequestMarshaller.instance);      options.setResponseUnmarshaller(DescribeVoicesResponseUnmarshaller.instance);      return invoke(DescribeVoicesResponse.class, options);  }
ListMonitoringExecutionsResult listMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = beforeClientExecution(request); return executeListMonitoringExecutions(request); }
DescribeJobRequest(String vaultName, String jobId) { this.vaultName = vaultName; this.jobId = jobId; }
EscherRecord getEscherRecord() { return escherRecords[]; }
GetApisResponse getApis(GetApisRequest request) {      options = new InvokeOptions();      options.setRequestMarshaller();      options.setResponseUnmarshaller(GetApisResponseUnmarshaller.instance);      return invoke(GetApisResponse.class);  }
DeleteSmsChannelResponse deleteSmsChannel(DeleteSmsChannelRequest request) { options = new InvokeOptions(); options.setRequestMarshaller(); options.setResponseUnmarshaller(DeleteSmsChannelResponseUnmarshaller.instance); return invoke(DeleteSmsChannelResponse.class); }
TrackingRefUpdate getTrackingRefUpdate() { }
void print(boolean b) { print(); }
IQueryNode GetChild() { return GetChildren[]; }
NotIgnoredFilter(int workdirTreeIndex) { this.index = workdirTreeIndex; }
public AreaRecord(RecordInputStream in1) { field_1_formatFlags = in1.readShort(); }
public GetThumbnailRequest() { this("", "", "", "", ""); Protocol(); }
DescribeTransitGatewayVpcAttachmentsResult describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { request = beforeClientExecution(request); return executeDescribeTransitGatewayVpcAttachments(request); }
PutVoiceConnectorStreamingConfigurationResponse putVoiceConnectorStreamingConfiguration() {      options = new InvokeOptions();      options.setRequestMarshaller(PutVoiceConnectorStreamingConfigurationRequestMarshaller.instance);      options.setResponseUnmarshaller(PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.instance);      return invoke(PutVoiceConnectorStreamingConfigurationResponse.class);  }
OrdRange getOrdRange(String dim) { OrdRange result; prefixToOrdRange.tryGetValue(dim, out result); return result; }
@Override public String toString() { String symbol = ""; if (startIndex < ((ICharStream) inputStream).size()) { symbol = ((ICharStream) inputStream).getText(Interval.of(startIndex, getInputPosition())); symbol = Utils.escapeWhitespace(symbol, false); } return String.format(Locale.getDefault(), "%s[%s]", getClass().getSimpleName(), symbol); }
public E peek() { return peekFirstImpl(); }
CreateWorkspacesResult createWorkspaces(CreateWorkspacesRequest request) { request = beforeClientExecution(request); return executeCreateWorkspaces(request); }
public Object clone() {      NumberFormatIndexRecord rec = new NumberFormatIndexRecord();      rec.field_1_formatIndex = this.field_1_formatIndex;      return rec;  }
public DescribeRepositoriesResult describeRepositories(DescribeRepositoriesRequest request) {      request = beforeClientExecution(request);      return executeDescribeRepositories(request);  }
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
TokenStream create() { return new HyphenatedWordsFilter(); }
CreateDistributionWithTagsResponse createDistributionWithTags(CreateDistributionWithTagsRequest request) {      options = new InvokeOptions();      options.setRequestMarshaller(CreateDistributionWithTagsRequestMarshaller.INSTANCE);      options.setResponseUnmarshaller(CreateDistributionWithTagsResponseUnmarshaller.INSTANCE);      return invoke(CreateDistributionWithTagsResponse.class, options);  }
public RandomAccessFile(String fileName, String mode) throws java.io.FileNotFoundException { super(); throw new java.lang.UnsupportedOperationException(); }
public DeleteWorkspaceImageResult deleteWorkspaceImage(DeleteWorkspaceImageRequest request) { request = beforeClientExecution(request); return executeDeleteWorkspaceImage(request); }
String toHex() { return toHex((long) value); }
UpdateDistributionResponse updateDistribution(UpdateDistributionRequest request) {      options = new InvokeOptions();      UpdateDistributionRequestMarshaller.INSTANCE;      options.setResponseUnmarshaller(UpdateDistributionResponseUnmarshaller.INSTANCE);      return invoke(UpdateDistributionResponse.class, options);  }
HSSFColor getColor(short index) { if (index == HSSFColor.Index) return HSSFColor.Automatic.getInstance(); else { byte[] b = palette.getColor(); if (b != null) { return new CustomColor((byte)0, (byte)0, (byte)0); } } return null; }
ValueEval evaluate(ValueEval operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(); }
public void serialize(ILittleEndianOutput out1) {out1.writeShort((short) field_1_number_crn_records);out1.writeShort((short) field_2_sheet_table_index);}
public DescribeDBEngineVersionsResponse describeDBEngineVersions() { return describeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
public FormatRun(short character, short fontIndex) { this._character = character; this._fontIndex = fontIndex; }
byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) {      byte[] result = new byte[length * 2];      int end = offset + length;      int resultIndex = 0;      for (int i = offset; i < end; i++) {          char ch = chars[i];          result[resultIndex++] = (byte) (ch >> 8);          result[resultIndex++] = (byte) ch;      }      return result;  }
UploadArchiveResponse uploadArchive(UploadArchiveRequest request) {      options = new InvokeOptions();      options.setRequestMarshaller(UploadArchiveRequestMarshaller.INSTANCE);      options.setResponseUnmarshaller(UploadArchiveResponseUnmarshaller.INSTANCE);      return invoke(UploadArchiveResponse.class, options);  }
List getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex, -1); }
public boolean equals(Object obj) {      if (this == obj) {          return true;      }      if (obj == null || getClass() != obj.getClass()) {          return false;      }      AutomatonQuery other = (AutomatonQuery) obj;      if (!m_compiled.equals(other.m_compiled)) {          return false;      }      if (m_term == null) {          if (other.m_term != null) {              return false;          }      } else if (!m_term.equals(other.m_term)) {          return false;      }      return true;  }
SpanQuery makeSpanClause() {      List<SpanQuery> spanQueries = new ArrayList<>();      for (Map.Entry<SpanQuery, Float> wsq : weightBySpanQuery.entrySet()) {          wsq.getKey().setBoost(wsq.getValue());          spanQueries.add(wsq.getKey());      }      return spanQueries.size() == 1 ? spanQueries.get(0) : new SpanOrQuery(spanQueries.toArray(new SpanQuery[0]));  }
public StashCreateCommand stashCreate() { return new StashCreateCommand(); }
FieldInfo fieldInfo(String fieldName) { return byName.get(fieldName); }
DescribeEventSourceResponse describeEventSource(DescribeEventSourceRequest request) {      options = new InvokeOptions();      DescribeEventSourceRequestMarshaller.INSTANCE;      options.setResponseUnmarshaller(DescribeEventSourceResponseUnmarshaller.INSTANCE);      return invoke(DescribeEventSourceResponse.class);  }
public GetDocumentAnalysisResult getDocumentAnalysis(GetDocumentAnalysisRequest request) {      request = beforeClientExecution(request);      return executeGetDocumentAnalysis(request);  }
CancelUpdateStackResponse cancelUpdateStack(CancelUpdateStackRequest request) { options = new InvokeOptions(); CancelUpdateStackRequestMarshaller.instance; options.responseUnmarshaller = CancelUpdateStackResponseUnmarshaller.instance; return invoke(CancelUpdateStackResponse.class, options); }
public ModifyLoadBalancerAttributesResponse modifyLoadBalancerAttributes() {      options = new InvokeOptions();      options.setRequestMarshaller(ModifyLoadBalancerAttributesRequestMarshaller.INSTANCE);      options.setResponseUnmarshaller(ModifyLoadBalancerAttributesResponseUnmarshaller.INSTANCE);      return invoke(ModifyLoadBalancerAttributesResponse.class);  }
SetInstanceProtectionResponse setInstanceProtection(SetInstanceProtectionRequest request) {      options = new InvokeOptions();      options.setRequestMarshaller(SetInstanceProtectionRequestMarshaller.instance());      options.setResponseUnmarshaller(SetInstanceProtectionResponseUnmarshaller.instance());      return invoke(SetInstanceProtectionResponse.class, options);  }
ModifyDBProxyResponse modifyDBProxy(ModifyDBProxyRequest request) {      options = new InvokeOptions();      options.setRequestMarshaller(ModifyDBProxyRequestMarshaller.instance);      options.setResponseUnmarshaller(ModifyDBProxyResponseUnmarshaller.instance);      return (ModifyDBProxyResponse) invoke(request, options).getResponse();  }
void add(char[] output, int offset, int len, int endOffset, int posLength) {      if (count == outputs.length) {          CharsRef[] next = new CharsRef[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_OBJECT_REF)];          System.arraycopy(outputs, 0, next, 0, count);          outputs = next;      }      if (count == endOffsets.length) {          int[] next = new int[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_INT32)];          System.arraycopy(endOffsets, 0, next, 0, count);          endOffsets = next;      }      if (count == posLengths.length) {          int[] next = new int[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_INT32)];          System.arraycopy(posLengths, 0, next, 0, count);          posLengths = next;      }      if (outputs[count] == null) {          outputs[count] = new CharsRef();      }      outputs[count].copyChars(output, offset, len);      endOffsets[count] = endOffset;      posLengths[count] = posLength;      count++;  }
public FetchLibrariesRequest() { super(" ", " ", " ", " ", " "); Protocol(); }
boolean exists() { return true; }
public FilterOutputStream(OutputStream out) { this.out = out; }
public ScaleClusterRequest() { super(" ", " ", " ", " ", " "); this.setUriPattern(" "); this.setMethod(); }
IDataValidationConstraint createTimeConstraint(int operatorType, String formula1, String formula2) { return DVConstraint.createTimeConstraint(operatorType, formula1, formula2); }
ListObjectParentPathsResponse listObjectParentPaths() {      options = new InvokeOptions();      options.setRequestMarshaller(ListObjectParentPathsRequestMarshaller.instance);      options.setResponseUnmarshaller(ListObjectParentPathsResponseUnmarshaller.instance);      return invoke(ListObjectParentPathsResponse.class);  }
public DescribeCacheSubnetGroupsResponse describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) {      options = new InvokeOptions();      options.setRequestMarshaller(DescribeCacheSubnetGroupsRequestMarshaller.instance);      options.setResponseUnmarshaller(DescribeCacheSubnetGroupsResponseUnmarshaller.instance);      return invoke(DescribeCacheSubnetGroupsResponse.class, options);  }
void setSharedFormula(boolean flag) { field_5_options.set(flag); }
boolean isReuseObjects() { }
ErrorNodeImpl AddErrorNode(IToken badToken) {      ErrorNodeImpl t = new ErrorNodeImpl();      AddChild();      return t;  }
public LatvianStemFilterFactory(Map<?, ?> args, String name) { super(args); if (!args.isEmpty()) { throw new IllegalArgumentException(" " + args); } }
RemoveSourceIdentifierFromSubscriptionResult removeSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) { request = beforeClientExecution(request); return executeRemoveSourceIdentifierFromSubscription(request); }
TokenFilterFactory forName(Map<String, String> args) { return loader.newInstance(); }
public AddAlbumPhotosRequest() { super(" ", " ", " ", " ", ""); }
public GetThreatIntelSetResult getThreatIntelSet() {return executeGetThreatIntelSet();}
TreeFilter clone() { return new AndTreeFilter.Binary(() -> {}, b.clone()); }
boolean equals() { return o instanceof ; }
boolean hasArray() { return protectedHasArray; }
UpdateContributorInsightsResponse updateContributorInsights(UpdateContributorInsightsRequest request) {      options = new InvokeOptions();      options.setRequestMarshaller(UpdateContributorInsightsRequestMarshaller.INSTANCE);      options.setResponseUnmarshaller(UpdateContributorInsightsResponseUnmarshaller.INSTANCE);      return invoke(options, request);  }
void unwriteProtectWorkbook() { records.remove(); fileShare = null; writeProtect = null; }
public SolrSynonymParser(boolean dedup, Analyzer analyzer) { super(dedup, analyzer); this.expand = true; }
RequestSpotInstancesResult requestSpotInstances(RequestSpotInstancesRequest request) { request = beforeClientExecution(request); return executeRequestSpotInstances(request); }
byte getObjectData() { return findObjectRecord().getObjectData(); }
GetContactAttributesResponse getContactAttributes(GetContactAttributesRequest request) { options = new InvokeOptions(); GetContactAttributesRequestMarshaller.INSTANCE; options.setResponseUnmarshaller(GetContactAttributesResponseUnmarshaller.INSTANCE); return invoke(GetContactAttributesResponse.class, options); }
@Override public String toString() { return getKey() + "" + getValue(); }
ListTextTranslationJobsResponse listTextTranslationJobs() throws Exception {      options = new InvokeOptions();      options.setRequestMarshaller(ListTextTranslationJobsRequestMarshaller.instance);      options.setResponseUnmarshaller(ListTextTranslationJobsResponseUnmarshaller.instance);      return invoke(ListTextTranslationJobsResponse.class);  }
GetContactMethodsResponse getContactMethods(GetContactMethodsRequest request) {      InvokeOptions options = new InvokeOptions();      GetContactMethodsRequestMarshaller.Instance;      options.setResponseUnmarshaller(GetContactMethodsResponseUnmarshaller.Instance);      return invoke(GetContactMethodsResponse.class, options);  }
short lookupIndexByName(String name) {      FunctionMetadata fd = getInstance().getFunctionByNameInternal();      return fd == null ? (short) -1 : (short) fd.hashCode();  }
public DescribeAnomalyDetectorsResult describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) {request = beforeClientExecution(request);return executeDescribeAnomalyDetectors(request);}
String insertId(ObjectId changeId) { return insertId(changeId, null, null); }
long getObjectSize(AnyObjectId objectId, int typeHint) { long sz = db.getObjectSize(objectId, typeHint); if (sz < 0) { if (typeHint == OBJ_ANY) { throw new MissingObjectException(objectId.toString(), ""); } throw new MissingObjectException(objectId.copy(), ""); } return sz; }
ImportInstallationMediaResponse importInstallationMedia(ImportInstallationMediaRequest request) {      options = new InvokeOptions();      options.setRequestMarshaller(ImportInstallationMediaRequestMarshaller.instance);      options.setResponseUnmarshaller(ImportInstallationMediaResponseUnmarshaller.instance);      return invoke(options);  }
PutLifecycleEventHookExecutionStatusResponse putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) {      options = new InvokeOptions();      options.setRequestMarshaller(PutLifecycleEventHookExecutionStatusRequestMarshaller.instance);      options.setResponseUnmarshaller(PutLifecycleEventHookExecutionStatusResponseUnmarshaller.instance);      return client.invoke(options).getResponse();  }
public NumberPtg(ILittleEndianInput in1) { field_1_value(); }
GetFieldLevelEncryptionConfigResponse getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) {      InvokeOptions options = new InvokeOptions();      options.setRequestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.instance);      options.setResponseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.instance);      return invoke(GetFieldLevelEncryptionConfigResponse.class, options);  }
public DescribeDetectorResult describeDetector(DescribeDetectorRequest request) { request = beforeClientExecution(request); return executeDescribeDetector(request); }
public ReportInstanceStatusResult reportInstanceStatus(ReportInstanceStatusRequest request) { request = beforeClientExecution(request); return executeReportInstanceStatus(request); }
DeleteAlarmResponse deleteAlarm(DeleteAlarmRequest request) {      InvokeOptions options = new InvokeOptions();      options.setRequestMarshaller(DeleteAlarmRequestMarshaller.instance);      options.setResponseUnmarshaller(DeleteAlarmResponseUnmarshaller.instance);      return invoke(DeleteAlarmResponse.class, options);  }
public TokenStream create() { return new PortugueseStemFilter(); }
FtCblsSubRecord() { this.reserved = new byte[0]; }
boolean remove() { synchronized (mutex) { return c.remove(); } }
public GetDedicatedIpResult getDedicatedIp(GetDedicatedIpRequest request) { request = beforeClientExecution(request); return executeGetDedicatedIp(request); }
@Override public String toString() { return precedence + " "; }
ListStreamProcessorsResponse listStreamProcessors() { options = new InvokeOptions(); options.setRequestMarshaller(ListStreamProcessorsRequestMarshaller.instance); options.setResponseUnmarshaller(ListStreamProcessorsResponseUnmarshaller.instance); return invoke(ListStreamProcessorsResponse.class); }
public DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) { _loadBalancerName = loadBalancerName; _policyName = policyName; }
public WindowProtectRecord(int options) { _options = options; }
public UnbufferedCharStream(int bufferSize) { n = 0; data = new int[bufferSize]; }
public GetOperationsResult getOperations(GetOperationsRequest request) { request = beforeClientExecution(request); return executeGetOperations(request); }
void CopyRawTo(byte[] b) { NB.EncodeInt32(b, 0, 0); NB.EncodeInt32(b, o + 4, 0); NB.EncodeInt32(b, o + 8, 0); NB.EncodeInt32(b, o + 12, 0); NB.EncodeInt32(b, o + 16, 0); }
public WindowOneRecord(RecordInputStream in1) {      field_1_h_hold = in1.readShort();      field_2_v_hold = in1.readShort();      field_3_width = in1.readShort();      field_4_height = in1.readShort();      field_5_options = in1.readShort();      field_6_active_sheet = in1.readShort();      field_7_first_visible_tab = in1.readShort();      field_8_num_selected_tabs = in1.readShort();      field_9_tab_width_ratio = in1.readShort();  }
StopWorkspacesResponse stopWorkspaces(StopWorkspacesRequest request) {      options = new InvokeOptions();      options.setRequestMarshaller(StopWorkspacesRequestMarshaller.INSTANCE);      options.setResponseUnmarshaller(StopWorkspacesResponseUnmarshaller.INSTANCE);      return invoke(StopWorkspacesResponse.class, options);  }
void close() throws IOException { if (isOpen) { isOpen; try { dump(); } finally { try { channel.truncate(); } finally { try { channel.close(); } finally { /* empty */ } } } } }
DescribeMatchmakingRuleSetsResponse describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) {      InvokeOptions options = new InvokeOptions();      options.setRequestMarshaller();      options.setResponseUnmarshaller(DescribeMatchmakingRuleSetsResponseUnmarshaller.getInstance());      return invoke(DescribeMatchmakingRuleSetsResponse.class, options);  }
String getPronunciation(int wordId, char surface, int off, int len) { return null; }
public String getPath() { return pathStr; }
double devsq(double[] v) {      double r = Double.NaN;      if (v != null && v.length >= 1) {          double m = 0;          double s = 0;          int n = v.length;          for (int i = 0; i < n; i++) {              m += v[i];          }          m /= n;          s = 0;          for (int i = 0; i < n; i++) {              s += (v[i] - m) * (v[i] - m);          }          r = (n == 1) ? 0 : s / (n - 1);      }      return r;  }
public DescribeResizeResult describeResize(DescribeResizeRequest request) { request = beforeClientExecution(request); return executeDescribeResize(request); }
public boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
int end { return end(); }
void traverse(ICellHandler handler) {      int firstRow = range.getFirstRow(), lastRow = range.getLastRow(), firstColumn = range.getFirstColumn(), lastColumn = range.getLastColumn(), width = lastColumn - firstColumn + 1;      SimpleCellWalkContext ctx = new SimpleCellWalkContext();      IRow currentRow;      ICell currentCell;      for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ++ctx.rowNumber) {          currentRow = sheet.getRow(ctx.rowNumber);          if (currentRow == null) continue;          for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ++ctx.colNumber) {              currentCell = currentRow.getCell(ctx.colNumber);              if (currentCell == null) continue;              if (isEmpty() && !traverseEmptyCells) continue;              handler.onCell((ctx.rowNumber - firstRow) * width + (ctx.colNumber - firstColumn), currentCell);          }      }  }
int getReadIndex() { return _readIndex; }
int compareTo(ScoreTerm other) { return term.bytesEquals(other.term) ? 0 : boost.compareTo(other.boost) == 0 ? term.compareTo(other.term) : boost.compareTo(other.boost); }
int Normalize(char[] s, int len) {      for (int i = 0; i < len; i++) {          switch (s[i]) {              case FARSI_YEH:              case YEH_BARREE:                  s[i] = YEH;                  break;              case KEHEH:                  s[i] = KAF;                  break;              case HEH_YEH:              case HEH_GOAL:                  s[i] = HEH;                  break;              case HAMZA_ABOVE:                  len = StemmerUtil.Delete(s, i, 1);                  i--;                  break;              default:                  break;          }      }      return len;  }
void serialize(ILittleEndianOutput out1) {}
DiagnosticErrorListener(boolean exactOnly) { this.exactOnly = exactOnly; }
public KeySchemaElement(String attributeName, KeyType keyType) { _attributeName = attributeName; _keyType = keyType; }
GetAssignmentResponse getAssignment(GetAssignmentRequest request) {      InvokeOptions options = new InvokeOptions();      options.setRequestMarshaller(GetAssignmentRequestMarshaller.getInstance());      options.setResponseUnmarshaller(GetAssignmentResponseUnmarshaller.getInstance());      return invoke(options, request);  }
boolean hasObject() { return findOffset() != -1; }
GroupingSearch setAllGroups() { this.allGroups = allGroups; return this; }
synchronized void setMultiValued(String dimName, boolean v) {      if (!fieldTypes.containsKey(dimName)) {          fieldTypes.put(dimName, new DimConfig(v));      } else {          fieldTypes.get(dimName).setMultiValued(v);      }  }
int getCellsVal() { int size = 0; for (char c : cells.keySet()) { Cell e = at(); if (e.cmd >= 0) { size++; } } return size; }
DeleteVoiceConnectorResponse deleteVoiceConnector(DeleteVoiceConnectorRequest request) {      options = new InvokeOptions();      options.setRequestMarshaller();      options.setResponseUnmarshaller(DeleteVoiceConnectorResponseUnmarshaller.getInstance());      return invoke(DeleteVoiceConnectorResponse.class);  }
DeleteLifecyclePolicyResult deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) {  request = beforeClientExecution(request);  return executeDeleteLifecyclePolicy(request);  }
