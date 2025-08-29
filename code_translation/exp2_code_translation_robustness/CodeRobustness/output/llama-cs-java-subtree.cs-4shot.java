void serialize(DataOutput out) {}
void addAll(NGit.Util.BlockList<T> src) { if (src.size == 0) { } int srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) { addAll(src.directory, 0, srcDirIdx); } if (src.tailBlkIdx != 0) { addAll(src.tailBlock, 0, src.tailBlkIdx); } }
void writeByte(byte b) { if (outerInstance.upto == outerInstance.blockSize) { if (outerInstance.currentBlock != null) { outerInstance.blocks.add(outerInstance.currentBlock); outerInstance.blockEnd.add(outerInstance.upto); } outerInstance.currentBlock = new byte[outerInstance.blockSize]; outerInstance.upto = 0; } outerInstance.currentBlock[outerInstance.upto++] = (byte) b; }
ObjectId getObjectId() { return objectId; }
DeleteDomainEntryResponse deleteDomainEntry(DeleteDomainEntryRequest request) {      options = new InvokeOptions();      options.setRequestMarshaller();      options.setResponseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.instance);      return invoke(DeleteDomainEntryResponse.class);  }
long ramBytesUsed() { return fst == null ? 0 : fst.getSizeInBytes(); }
String getFullMessage() { byte[] raw = buffer; int msgB = RawParseUtils.tagMessage(); if (msgB < 0) { return ""; } Encoding enc = RawParseUtils.parseEncoding(); return RawParseUtils.decode(enc, msgB, raw, raw.length); }
POIFSFileSystem() { HeaderBlock headerBlock = new HeaderBlock(); _propertyTable = new PropertyTable(); _documents = new ArrayList(); _root = null; }
void init(int address) { slice = pool.Buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; Debug.assert(slice != null); upto = address & ByteBlockPool.BYTE_BLOCK_MASK; offset = 0; address; Debug.assert(upto < slice.length); }
SubmoduleAddCommand setPath(String path) {this.path = path; return this;}
ListIngestionsResponse listIngestions(ListIngestionsRequest request) {      InvokeOptions options = new InvokeOptions();      options.setRequestMarshaller(ListIngestionsRequestMarshaller.INSTANCE);      options.setResponseUnmarshaller(ListIngestionsResponseUnmarshaller.INSTANCE);      return client.invoke(options, request, ListIngestionsResponse.class);  }
public QueryParserTokenManager(ICharStream stream, int lexState) { this(stream); SwitchTo(lexState); }
public GetShardIteratorResult getShardIterator(GetShardIteratorRequest request) {request = beforeClientExecution(request);return executeGetShardIterator(request);}
public ModifyStrategyRequest() { this("", "", "", "", ""); }
boolean ready() {synchronized(lock){if(in == null)throw new IOException();try{return bytes.hasRemaining() || in.available() > 0;}catch(IOException e){return false;}}}
EscherOptRecord getOptRecord() { }
public synchronized int read(byte[] buffer, int offset, int length) {      if (buffer == null) {          throw new NullPointerException();      }      if (offset < 0 || length < 0 || offset + length > buffer.length) {          throw new ArrayIndexOutOfBoundsException();      }      if (length == 0) {          return 0;      }      int copylen = Math.min(count - pos, length);      System.arraycopy(buf, pos, buffer, offset, copylen);      pos += copylen;      return copylen;  }
OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) { this.sentenceOp = sentenceOp; }
void print(String str) { write(str != null ? str : ""); }
public NotImplementedFunctionException(String functionName, Throwable cause) { super("Not implemented function: " + functionName, cause); this.functionName = functionName; }
V next() { return nextEntry.value; }
void readBytes(byte[] b, int offset, int len, boolean useBuffer) {      int available = bufferLength - bufferPosition;      if (len <= available) {          if (len > 0) {              System.arraycopy(buffer, bufferPosition, b, offset, len);          }          bufferPosition += len;      } else {          if (available > 0) {              System.arraycopy(buffer, bufferPosition, b, offset, available);              offset += available;              len -= available;              bufferPosition += available;          }          if (useBuffer && len < bufferSize) {              refill();              if (bufferLength < len) {                  System.arraycopy(buffer, 0, b, offset, bufferLength);                  throw new EOFException("End of stream reached");              } else {                  System.arraycopy(buffer, 0, b, offset, len);                  bufferPosition += len;              }          } else {              long after = bufferStart + bufferPosition + len;              if (after > length) {                  throw new EOFException("End of stream reached");              }              readInternal(b, offset, len);              bufferStart += len;              bufferPosition = 0;              bufferLength = 0;          }      }  }
TagQueueResponse tagQueue(TagQueueRequest request) { options = new InvokeOptions(); options.setRequestMarshaller(TagQueueRequestMarshaller.instance); options.setResponseUnmarshaller(); return invoke(TagQueueResponse.class); }
void remove() { throw new UnsupportedOperationException(); }
ModifyCacheSubnetGroupResponse modifyCacheSubnetGroup() { options = new InvokeOptions(); options.setRequestMarshaller(ModifyCacheSubnetGroupRequestMarshaller.instance); options.setResponseUnmarshaller(ModifyCacheSubnetGroupResponseUnmarshaller.instance); return invoke(ModifyCacheSubnetGroupResponse.class); }
void setParams(String params) {StringTokenizer st = new StringTokenizer(params, " ");String culture = "";String ignore = "";if (st.hasMoreTokens()) culture = st.nextToken();if (st.hasMoreTokens()) culture = culture + " " + st.nextToken();if (st.hasMoreTokens()) ignore = st.nextToken();}
public DeleteDocumentationVersionResult deleteDocumentationVersion(DeleteDocumentationVersionRequest request) {request = beforeClientExecution(request);return executeDeleteDocumentationVersion(request);}
public boolean equals(Object obj) { if (!(obj instanceof FacetLabel)) return false; FacetLabel other = (FacetLabel) obj; if (length() != other.length()) return false; for (int i = length() - 1; i >= 0; i--) { if (!Arrays.equals(components[i], other.components[i], String.CASE_INSENSITIVE_ORDER)) return false; } return true; }
public GetInstanceAccessDetailsResult getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) {request = beforeClientExecution(request);return executeGetInstanceAccessDetails(request);}
HSSFPolygon createPolygon() { HSSFPolygon shape = new HSSFPolygon(); shape.setParent(); shape.setAnchor(anchor); shapes.add(shape); onCreate(); return shape; }
String getSheetName() { return getBoundSheetRec().getSheetname(); }
GetDashboardResponse getDashboard(GetDashboardRequest request) { options = new InvokeOptions(); GetDashboardRequestMarshaller.Instance; options.setResponseUnmarshaller(GetDashboardResponseUnmarshaller.Instance); return invoke(GetDashboardResponse.class); }
public AssociateSigninDelegateGroupsWithAccountResult associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) {request = beforeClientExecution(request);return executeAssociateSigninDelegateGroupsWithAccount(request);}
void addMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < mbr.getNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.setColumn(j + mbr.getFirstColumn()); br.setRow(mbr.getRow()); br.setXFIndex(mbr.getXFAt(j)); insertCell(br); } }
String quote(String string) { StringBuilder sb = new StringBuilder(); sb.append("\\"); int apos = 0; int k; while ((k = string.indexOf("\\", apos)) >= 0) { sb.append(string.substring(apos, k)).append("\\\\"); apos = k + 1; } return sb.append(string.substring(apos)).append("\\").toString(); }
public ByteBuffer putInt(int value) { throw new ReadOnlyBufferException(); }
ArrayPtg(Object[][] values2d) {      int nColumns = values2d[0].length;      int nRows = values2d.length;      _nColumns = (short) nColumns;      _nRows = (short) nRows;      Object[] vv = new Object[_nColumns * _nRows];      for (int r = 0; r < nRows; r++) {          Object[] rowData = values2d[r];          for (int c = 0; c < nColumns; c++) {              vv[GetValueIndex(r, c)] = rowData[c];          }      }      _arrayValues = vv;      _reserved0Int = 0;      _reserved1Short = 0;      _reserved2Byte = 0;  }
GetIceServerConfigResult getIceServerConfig(GetIceServerConfigRequest request) { request = beforeClientExecution(request); return executeGetIceServerConfig(request); }
String toString() { StringBuilder sb = new StringBuilder(); sb.append(getClass().getSimpleName()).append(" ").append(GetValueAsString).append(" "); return sb.toString(); }
public String toString() { return " " + _parentQuery + " "; }
void incRef() { refCount.incrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResult updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) {request = beforeClientExecution(request);return executeUpdateConfigurationSetSendingEnabled(request);}
int nextXBATChainOffset = getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE;
void multiplyByPowerOfTen(int pow10) { TenPower tp = TenPower.getInstance(Math.abs(pow10)); if (pow10 > 0) { mulShift(tp._divisor, tp._divisorShift); } else { mulShift(tp._multiplicand, tp._multiplierShift); } }
public String toString() {StringBuilder builder = new StringBuilder();int length = path.length();builder.append(File.separator);for (int i = 0; i < length; i++) {builder.append(path.getComponent(i));if (i < (length - 1)) {builder.append(File.separator);}}return builder.toString();}
void withFetcher() { Fetcher fetcher = new Fetcher(); fetcher.setRoleName(); }
void setProgressMonitor() { ProgressMonitor pm; }
void reset() { if (true) { ptr = 0; if (!eof) { parseEntry(); } } }
E previous() { if (iterator.previousIndex() >= start) { return iterator.previous(); } throw new NoSuchElementException(); }
String getNewPrefix() { return newPrefix; }
int indexOfValue(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1; }
List<CharsRef> uniqueStems(char[] word, int length) { List<CharsRef> stems = stem(word, length); if (stems.size() < 2) { return stems; } CharArraySet terms = new CharArraySet(dictionary, 8); List<CharsRef> deduped = new ArrayList<>(); for (CharsRef s : stems) { if (!terms.contains(s)) { deduped.add(s); terms.add(s); } } return deduped; }
GetGatewayResponsesResponse getGatewayResponses() { options = new InvokeOptions(); options.setRequestMarshaller(GetGatewayResponsesRequestMarshaller.instance); options.setResponseUnmarshaller(GetGatewayResponsesResponseUnmarshaller.instance); return invoke(GetGatewayResponsesResponse.class); }
void setPosition(long position) { currentBlockIndex = (int) (position >> outerInstance.blockBits); currentBlock = outerInstance.blocks; currentBlockUpto = (int) (position & outerInstance.blockMask); }
long skip(long n) { int s = (int) Math.max(0, Math.min(n, available())); ptr += s; return s; }
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { this.bootstrapActionConfig = bootstrapActionConfig; }
void serialize(ILittleEndianOutput out1) {      out1.writeShort();      out1.writeShort();      out1.writeShort();      out1.writeShort();      out1.writeShort(field_6_author.length);      out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00);      if (field_5_hasMultibyte) {          StringUtil.putUnicodeLE(out1, field_6_author);      } else {          StringUtil.putCompressedUnicode(out1, field_6_author);      }      if (field_7_padding != null) {          out1.writeByte(Integer.parseInt(field_7_padding, Locale.ROOT));      }  }
int lastIndexOf() { return lastIndexOf("", 0); }
boolean add() { return addLastImpl(); }
void unsetSection(String section, String subsection) {      ConfigSnapshot;      ConfigSnapshot;      do {          SrcState state = getState();          Res res = state.unsetSection(section, subsection);      } while (!state.compareAndSet(state, state));  }
String getTagName() { return tagName; }
void addSubRecord(int index) { subrecords.add(index, null); }
boolean remove() {synchronized (mutex) {return c.remove();}}
TokenStream create() { return new DoubleMetaphoneFilter(); }
public long getLength() { return inCoreLength(); }
void setValue() { Value newValue; }
Pair(ContentSource oldSource, ContentSource newSource) { this.oldSource = oldSource; this.newSource = newSource; }
int get(int i) { if (i < 0 || i >= entries.length) { throw new IndexOutOfBoundsException(); } return entries[i]; }
public CreateRepoRequest() { super(" ", " ", " ", " ", ""); this.setUriPattern(); this.setMethod(MethodType.PUT); }
boolean isDeltaBaseAsOffset() { }
void remove() {      if (expectedModCount == list.modCount) {          if (lastLink != null) {              java.util.LinkedList.Link<ET> next_1 = lastLink.next;              java.util.LinkedList.Link<ET> previous_1 = lastLink.previous;              next_1.previous = previous_1;              previous_1.next = next_1;              if (lastLink == link) {                  pos--;              }              link = previous_1;              lastLink = null;              expectedModCount++;              list._size--;              list.modCount++;          } else {              throw new NoSuchElementException();          }      } else {          throw new java.util.ConcurrentModificationException();      }  }
MergeShardsResponse mergeShards(MergeShardsRequest request) {      options = new InvokeOptions();      options.setRequestMarshaller(MergeShardsRequestMarshaller.instance);      options.setResponseUnmarshaller(MergeShardsResponseUnmarshaller.instance);      return (MergeShardsResponse) invoke(options).getResponse();  }
AllocateHostedConnectionResponse allocateHostedConnection(AllocateHostedConnectionRequest request) {      options = new InvokeOptions();      options.setRequestMarshaller();      options.setResponseUnmarshaller(AllocateHostedConnectionResponseUnmarshaller.getInstance());      return invoke(AllocateHostedConnectionResponse.class);  }
int getBeginIndex() { return start; }
WeightedTerm getTerms(Query query) { return getTerms(); }
public ByteBuffer compact() { throw new ReadOnlyBufferException(); }
void decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) {for(int i = 0;i < iterations;++i){int byte0 = blocks[blocksOffset++] & 0xFF;values[valuesOffset++] = (int)((byte0 >> 2) & 0x3F);int byte1 = blocks[blocksOffset++] & 0xFF;values[valuesOffset++] = ((byte0 & 3) << 4) | ((byte1 >> 4) & 0xF);int byte2 = blocks[blocksOffset++] & 0xFF;values[valuesOffset++] = ((byte1 & 0xF) << 2) | ((byte2 >> 6) & 0x3);values[valuesOffset++] = byte2 & 0x3F;}}
String getHumanishName() {      String s = getPath();      if (s == null || s.isEmpty()) {          throw new ArgumentException();      }      String[] elements;      if ("".equals(s) || LOCAL_FILE.matcher(s).matches()) {          elements = s.split("\\\\" + File.separator);      } else {          elements = s.split(" ");      }      if (elements.length == 0) {          throw new ArgumentException();      }      String result = elements[elements.length - 1];      if (Constants.DOT_GIT.equals(result)) {          result = elements[elements.length - 2];      } else {          if (result.endsWith(Constants.DOT_GIT_EXT)) {              result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length());          }      }      return result;  }
DescribeNotebookInstanceLifecycleConfigResult describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) {request = beforeClientExecution(request);return executeDescribeNotebookInstanceLifecycleConfig(request);}
public String getAccessKeySecret() { return accessSecret; }
public CreateVpnConnectionResult createVpnConnection(CreateVpnConnectionRequest request) {request = beforeClientExecution(request);return executeCreateVpnConnection(request);}
DescribeVoicesResponse describeVoices(DescribeVoicesRequest request) {      InvokeOptions options = new InvokeOptions();      options.setRequestMarshaller(DescribeVoicesRequestMarshaller.INSTANCE);      options.setResponseUnmarshaller(DescribeVoicesResponseUnmarshaller.INSTANCE);      return invoke(DescribeVoicesResponse.class, options);  }
ListMonitoringExecutionsResult listMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = beforeClientExecution(request); return executeListMonitoringExecutions(request); }
DescribeJobRequest(String vaultName, String jobId) { this.vaultName = vaultName; this.jobId = jobId; }
EscherRecord getEscherRecord() { return escherRecords[]; }
GetApisResponse getApis(GetApisRequest request) {return executeGetApis(request);}
public DeleteSmsChannelResult deleteSmsChannel(DeleteSmsChannelRequest request) {request = beforeClientExecution(request);return executeDeleteSmsChannel(request);}
TrackingRefUpdate getTrackingRefUpdate() { }
void print(boolean b) { print(); }
IQueryNode getChild() { return getChildren(); }
NotIgnoredFilter(int workdirTreeIndex) { this.index = workdirTreeIndex; }
AreaRecord(RecordInputStream in1) { field_1_formatFlags = in1.readShort(); }
public GetThumbnailRequest() { this("", "", "", "", ""); }
DescribeTransitGatewayVpcAttachmentsResponse describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) {      InvokeOptions options = new InvokeOptions();      options.setResponseUnmarshaller(DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance());      return invoke(DescribeTransitGatewayVpcAttachmentsRequestMarshaller.getInstance(), options, DescribeTransitGatewayVpcAttachmentsResponse.class);  }
public PutVoiceConnectorStreamingConfigurationResponse putVoiceConnectorStreamingConfiguration() {      options = new InvokeOptions();      options.setRequestMarshaller(PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance());      options.setResponseUnmarshaller(PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance());      return invoke(PutVoiceConnectorStreamingConfigurationResponse.class);  }
OrdRange getOrdRange(String dim) { OrdRange result; prefixToOrdRange.tryGetValue(dim, out result); }
public String toString() { String symbol = ""; if (startIndex < ((ICharStream) getInputStream()).size()) { symbol = ((ICharStream) getInputStream()).getText(Interval.of(startIndex, getInputStream().getIndex() - 1)); symbol = Utils.escapeWhitespace(symbol, false); } return String.format(Locale.getDefault(), "%s[%s]", getClass().getSimpleName(), symbol); }
E peek() { return peekFirstImpl(); }
CreateWorkspacesResponse createWorkspaces(CreateWorkspacesRequest request) {      options = new InvokeOptions();      CreateWorkspacesRequestMarshaller.Instance;      options.ResponseUnmarshaller = CreateWorkspacesResponseUnmarshaller.Instance;      return invoke(CreateWorkspacesResponse.class);  }
public Object clone() { NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = this.field_1_formatIndex; return rec; }
DescribeRepositoriesResult describeRepositories(DescribeRepositoriesRequest request) { request = beforeClientExecution(request); return executeDescribeRepositories(request); }
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
TokenStream create() { return new HyphenatedWordsFilter(); }
public CreateDistributionWithTagsResult createDistributionWithTags(CreateDistributionWithTagsRequest request) { request = beforeClientExecution(request); return executeCreateDistributionWithTags(request); }
public RandomAccessFile(String fileName, String mode) throws FileNotFoundException { throw new UnsupportedOperationException(); }
DeleteWorkspaceImageResult deleteWorkspaceImage(DeleteWorkspaceImageRequest request) { request = beforeClientExecution(request); return executeDeleteWorkspaceImage(request); }
String toHex() { return toHex((long) value); }
public UpdateDistributionResult updateDistribution(UpdateDistributionRequest request) { request = beforeClientExecution(request); return executeUpdateDistribution(request); }
HSSFColor getColor(short index) { if (index == 0) return HSSFColor.AUTOMATIC; else { byte[] b = palette.getColor(index); if (b != null) { return new CustomColor(b[0], b[1], b[2]); } } return null; }
ValueEval evaluate(ValueEval operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(); }
public void serialize(ILittleEndianOutput out1) {out1.writeShort((short) field_1_number_crn_records);out1.writeShort((short) field_2_sheet_table_index);}
DescribeDBEngineVersionsResponse describeDBEngineVersions() { return describeDBEngineVersions(new DescribeDBEngineVersionsRequest()); }
FormatRun(short character, short fontIndex) { this._character = character; this._fontIndex = fontIndex; }
byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) {      byte[] result = new byte[length * 2];      int end = offset + length;      int resultIndex = 0;      for (int i = offset; i < end; i++) {          char ch = chars[i];          result[resultIndex++] = (byte) (ch >> 8);          result[resultIndex++] = (byte) ch;      }      return result;  }
public UploadArchiveResult uploadArchive(UploadArchiveRequest request) {request = beforeClientExecution(request);return executeUploadArchive(request);}
List getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex, -1); }
public boolean equals(Object obj) {if (this == obj) {return true;} if (obj == null) {return false;} if (getClass() != obj.getClass()) {return false;} AutomatonQuery other = (AutomatonQuery) obj; if (!m_compiled.equals(other.m_compiled)) {return false;} if (m_term == null) {if (other.m_term != null) {return false;} } else if (!m_term.equals(other.m_term)) {return false;} return true;}
SpanQuery makeSpanClause() {      List<SpanQuery> spanQueries = new ArrayList<>();      for (Map.Entry<SpanQuery, Float> wsq : weightBySpanQuery.entrySet()) {          spanQueries.add(wsq.getKey());      }      return spanQueries.size() == 1 ? spanQueries.get(0) : new SpanOrQuery(spanQueries.toArray(new SpanQuery[0]));  }
public StashCreateCommand stashCreate() { return new StashCreateCommand(); }
FieldInfo fieldInfo(String fieldName) { FieldInfo ret; byName.tryGetValue(fieldName, ret); return ret; }
public DescribeEventSourceResult describeEventSource(DescribeEventSourceRequest request) {request = beforeClientExecution(request);return executeDescribeEventSource(request);}
GetDocumentAnalysisResponse getDocumentAnalysis(GetDocumentAnalysisRequest request) {      options = new InvokeOptions();      options.setRequestMarshaller(GetDocumentAnalysisRequestMarshaller.instance);      options.setResponseUnmarshaller(GetDocumentAnalysisResponseUnmarshaller.instance);      return invoke(GetDocumentAnalysisResponse.class, options);  }
public CancelUpdateStackResult cancelUpdateStack(CancelUpdateStackRequest request) {      request = beforeClientExecution(request);      return executeCancelUpdateStack(request);  }
public ModifyLoadBalancerAttributesResult modifyLoadBalancerAttributes() {return executeModifyLoadBalancerAttributes();}
public SetInstanceProtectionResult setInstanceProtection(SetInstanceProtectionRequest request) {request = beforeClientExecution(request);return executeSetInstanceProtection(request);}
public ModifyDBProxyResult modifyDBProxy(ModifyDBProxyRequest request) {request = beforeClientExecution(request);return executeModifyDBProxy(request);}
void add(char[] output, int offset, int len, int endOffset, int posLength) {      if (count == outputs.length) {         CharsRef[] next = new CharsRef[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_OBJECT_REF)];          System.arraycopy(outputs, 0, next, 0, count);          outputs = next;      }      if (count == endOffsets.length) {         int[] next = new int[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_INT32)];          System.arraycopy(endOffsets, 0, next, 0, count);          endOffsets = next;      }      if (count == posLengths.length) {         int[] next = new int[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_INT32)];          System.arraycopy(posLengths, 0, next, 0, count);          posLengths = next;      }      if (outputs[count] == null) {         outputs[count] = new CharsRef();      }      outputs[count].copyChars(output, offset, 0, len);      endOffsets[count] = endOffset;      posLengths[count] = posLength;      count++;  }
public FetchLibrariesRequest() { this("", "", "", "", ""); }
boolean exists() { return false; }
FilterOutputStream(OutputStream out) { this.out = out; }
public ScaleClusterRequest() { super(" ", " ", " ", " ", " "); UriPattern = " "; Method = ; }
IDataValidationConstraint createTimeConstraint(int operatorType, String formula1, String formula2) { return DVConstraint.createTimeConstraint(operatorType, formula1, formula2); }
ListObjectParentPathsResponse listObjectParentPaths() {options = new InvokeOptions();options.setRequestMarshaller(ListObjectParentPathsRequestMarshaller.instance);options.setResponseUnmarshaller(ListObjectParentPathsResponseUnmarshaller.instance);return invoke(ListObjectParentPathsResponse.class);}
public DescribeCacheSubnetGroupsResult describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) {request = beforeClientExecution(request);return executeDescribeCacheSubnetGroups(request);}
void setSharedFormula(boolean flag) { field_5_options(flag); }
boolean isReuseObjects() { }
IErrorNode addErrorNode(IToken badToken) { ErrorNodeImpl t = new ErrorNodeImpl(); addChild(); return t; }
public LatvianStemFilterFactory(Map<?, ?> args, String factoryName) throws ArgumentException { super(args); if (!args.isEmpty()) { throw new IllegalArgumentException(String.valueOf(args)); } }
RemoveSourceIdentifierFromSubscriptionResult removeSourceIdentifierFromSubscription() {      RemoveSourceIdentifierFromSubscriptionRequest request = new RemoveSourceIdentifierFromSubscriptionRequest();      return executeRemoveSourceIdentifierFromSubscription(request);  }
public TokenFilterFactory forName(String name, Map<String, String> args) { return loader.newInstance(name, args); }
public AddAlbumPhotosRequest() { this("", "", "", "", ""); Protocol(); }
GetThreatIntelSetResponse getThreatIntelSet() {return invoke(GetThreatIntelSetRequest.builder().build(), new InvokeOptions().withRequestMarshaller(GetThreatIntelSetRequestMarshaller.getInstance()).withResponseUnmarshaller(GetThreatIntelSetResponseMarshaller.getInstance()));}
TreeFilter clone() { return new AndTreeFilter.Binary(this, b.clone()); }
boolean equals() { return o instanceof ; }
boolean hasArray() { return protectedHasArray; }
UpdateContributorInsightsResponse updateContributorInsights(UpdateContributorInsightsRequest request) {      options = new InvokeOptions();      options.setRequestMarshaller(UpdateContributorInsightsRequestMarshaller.instance);      options.setResponseUnmarshaller(UpdateContributorInsightsResponseUnmarshaller.instance);      return invoke(UpdateContributorInsightsResponse.class, options);  }
void unwriteProtectWorkbook() { records.remove(); fileShare = null; writeProtect = null; }
public SolrSynonymParser(boolean dedup, Analyzer analyzer) { super(dedup, analyzer); this.expand = true; }
RequestSpotInstancesResponse requestSpotInstances(RequestSpotInstancesRequest request) {      InvokeOptions options = new InvokeOptions();      options.setRequestMarshaller(RequestSpotInstancesRequestMarshaller.INSTANCE);      options.setResponseUnmarshaller(RequestSpotInstancesResponseUnmarshaller.INSTANCE);      return invoke(options, request);  }
byte getObjectData() { return findObjectRecord().getObjectData(); }
public GetContactAttributesResult getContactAttributes(GetContactAttributesRequest request) {request = beforeClientExecution(request);return executeGetContactAttributes(request);}
String toString() { return getKey() + "" + getValue(); }
ListTextTranslationJobsResponse listTextTranslationJobs() {      options = new InvokeOptions();      options.setRequestMarshaller(ListTextTranslationJobsRequestMarshaller.instance);      options.setResponseUnmarshaller(ListTextTranslationJobsResponseUnmarshaller.instance);      return invoke(ListTextTranslationJobsResponse.class);  }
GetContactMethodsResponse getContactMethods(GetContactMethodsRequest request) {return invoke(request, new InvokeOptions().withResponseUnmarshaller(GetContactMethodsResponseUnmarshaller.getInstance()));}
short lookupIndexByName(String name) { FunctionMetadata fd = getInstance().getFunctionByNameInternal(); return fd == null ? (short) -1 : fd.getIndex(); }
public DescribeAnomalyDetectorsResult describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) {request = beforeClientExecution(request);return executeDescribeAnomalyDetectors(request);}
String insertId(ObjectId changeId) { return insertId(changeId, "", ""); }
long getObjectSize(AnyObjectId objectId, int typeHint) { long sz = db.getObjectSize(objectId, typeHint); if (sz < 0) { if (typeHint == OBJ_ANY) { throw new MissingObjectException(new AnyObjectId(), ""); } throw new MissingObjectException(objectId.copy(), ""); } return sz; }
public ImportInstallationMediaResult importInstallationMedia(ImportInstallationMediaRequest request) { request = beforeClientExecution(request); return executeImportInstallationMedia(request); }
PutLifecycleEventHookExecutionStatusResult putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) {      request = beforeClientExecution(request);      return executePutLifecycleEventHookExecutionStatus(request);  }
public NumberPtg(ILittleEndianInput in1) { field_1_value(); }
GetFieldLevelEncryptionConfigResponse getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) {      options = new InvokeOptions();      options.setRequestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.instance());      options.setResponseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.instance());      return invoke(GetFieldLevelEncryptionConfigResponse.class, options);  }
public DescribeDetectorResponse describeDetector(DescribeDetectorRequest request) {DescribeDetectorRequest request = beforeClientExecution(request); return executeDescribeDetector(request);}
ReportInstanceStatusResponse reportInstanceStatus(ReportInstanceStatusRequest request) {      request = beforeClientExecution(request);      return executeReportInstanceStatus(request);  }
public DeleteAlarmResult deleteAlarm(DeleteAlarmRequest request) {request = beforeClientExecution(request);return executeDeleteAlarm(request);}
TokenStream create() { return new PortugueseStemFilter(); }
public FtCblsSubRecord() { reserved = new byte[] {}; }
boolean remove() {synchronized (mutex) {return c.remove();}}
GetDedicatedIpResponse getDedicatedIp(GetDedicatedIpRequest request) {      InvokeOptions options = new InvokeOptions();      options.setRequestMarshaller(GetDedicatedIpRequest.getMarshaller());      options.setResponseUnmarshaller(GetDedicatedIpResponseUnmarshaller.getInstance());      return invoke(options, request);  }
@Override public String toString() { return precedence + " "; }
ListStreamProcessorsResponse listStreamProcessors() {return executeListStreamProcessors(new ListStreamProcessorsRequest());}
public DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) {this.loadBalancerName = loadBalancerName; this.policyName = policyName;}
public WindowProtectRecord(int options) { this.options = options; }
UnbufferedCharStream(int bufferSize) { n = 0; data = new int[bufferSize]; }
GetOperationsResponse getOperations(GetOperationsRequest request) {      InvokeOptions options = new InvokeOptions();      options.setRequestMarshaller(GetOperationsRequestMarshaller.instance);      options.setResponseUnmarshaller(GetOperationsResponseUnmarshaller.instance);      return invoke(options, request);  }
void copyRawTo(byte[] b) { NB.encodeInt32(b, 0, i); NB.encodeInt32(b, o + 4, i); NB.encodeInt32(b, o + 8, i); NB.encodeInt32(b, o + 12, i); NB.encodeInt32(b, o + 16, i); }
public WindowOneRecord(RecordInputStream in1) {      field_1_h_hold = in1.readShort();      field_2_v_hold = in1.readShort();      field_3_width = in1.readShort();      field_4_height = in1.readShort();      field_5_options = in1.readShort();      field_6_active_sheet = in1.readShort();      field_7_first_visible_tab = in1.readShort();      field_8_num_selected_tabs = in1.readShort();      field_9_tab_width_ratio = in1.readShort();  }
public StopWorkspacesResult stopWorkspaces(StopWorkspacesRequest request) {request = beforeClientExecution(request);return executeStopWorkspaces(request);}
public void close() throws IOException { if (isOpen) { isOpen = false; try { dump(); } finally { try { channel.truncate(); } finally { try { channel.close(); } finally { /* no-op */ } } } } }
DescribeMatchmakingRuleSetsResult describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) {      request = beforeClientExecution(request);      return executeDescribeMatchmakingRuleSets(request);  }
String getPronunciation(int wordId, char surface, int off, int len) { return null; }
String getPath() { return pathStr; }
double devsq(double[] v) { double r = Double.NaN; if (v != null && v.length >= 1) { double m = 0; double s = 0; int n = v.length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s / (n - 1); } return r; }
DescribeResizeResponse describeResize(DescribeResizeRequest request) { options = new InvokeOptions(); options.setRequestMarshaller(DescribeResizeRequestMarshaller.instance); options.setResponseUnmarshaller(ResponseUnmarshaller.getInstance()); return invoke(DescribeResizeResponse.class, options); }
boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
int end() { return end(); }
void traverse(ICellHandler handler) { int firstRow = range.getFirstRow(); int lastRow = range.getLastRow(); int firstColumn = range.getFirstColumn(); int lastColumn = range.getLastColumn(); int width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); IRow currentRow = null; ICell currentCell = null; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ++ctx.rowNumber) { currentRow = sheet.getRow(ctx.rowNumber); if (currentRow == null) { continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ++ctx.colNumber) { currentCell = currentRow.getCell(ctx.colNumber); if (currentCell == null) { continue; } if (isEmpty() && !traverseEmptyCells) { continue; } handler.onCell((ctx.rowNumber - firstRow) * width + (ctx.colNumber - firstColumn), currentCell); } }
int getReadIndex() { return _readIndex; }
int compareTo(ScoreTerm other) { if (Term.bytesEquals(other.Term)) { return 0; } if (this.Boost == other.Boost) { return this.Term.compareTo(other.Term); } else { return this.Boost.compareTo(other.Boost); } }
int normalize(char[] s, int len) {      for (int i = 0; i < len; i++) {          switch (s[i]) {              case FARSI_YEH:              case YEH_BARREE:                  s[i] = YEH;                  break;              case KEHEH:                  s[i] = KAF;                  break;              case HEH_YEH:              case HEH_GOAL:                  s[i] = HEH;                  break;              case HAMZA_ABOVE:                  len = StemmerUtil.delete(s, i, 1);                  i--;                  break;              default:                  break;          }      }      return len;  }
void serialize(DataOutput out) throws IOException { }
DiagnosticErrorListener(boolean exactOnly) { this.exactOnly = exactOnly; }
KeySchemaElement(String attributeName, KeyType keyType) { this._attributeName = attributeName; this._keyType = keyType; }
GetAssignmentResponse getAssignment(GetAssignmentRequest request) {      options = new InvokeOptions();      options.setRequestMarshaller(GetAssignmentRequestMarshaller.instance);      options.setResponseUnmarshaller(GetAssignmentResponseUnmarshaller.instance);      return invoke(GetAssignmentResponse.class, options);  }
public boolean hasObject() { return findOffset() != -1; }
GroupingSearch setAllGroups() { allGroups = true; return this; }
void setMultiValued(String dimName, boolean v) {synchronized (this) {DimConfig fieldType = fieldTypes.get(dimName);if (fieldType == null) {fieldTypes.put(dimName, new DimConfig().setIsMultiValued(v));} else {fieldType.setIsMultiValued(v);}}}
int getCellsVal() { int size = 0; for (char c : cells.keySet()) { Cell e = at(); if (e.cmd >= 0) { } } return size; }
public DeleteVoiceConnectorResult deleteVoiceConnector(DeleteVoiceConnectorRequest request) {request = beforeClientExecution(request);return executeDeleteVoiceConnector(request);}
DeleteLifecyclePolicyResponse deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) {      request = beforeClientExecution(request);      return executeDeleteLifecyclePolicy(request);  }
