public void serialize(ILittleEndianOutput out1) {out1.writeShort(field_1_vcenter);}
public void addAll(NGit.Util.BlockList<T> src) {if (src.size == 0) return;int srcDirIdx = 0;for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) addAll(src.directory[srcDirIdx], 0, BLOCK_SIZE);if (src.tailBlkIdx != 0) addAll(src.tailBlock, 0, src.tailBlkIdx);}
public void writeByte(byte b) {if (outerInstance.upto == outerInstance.blockSize) {if (outerInstance.currentBlock != null) {outerInstance.blocks.add(outerInstance.currentBlock);outerInstance.blockEnd.add(outerInstance.upto);}outerInstance.currentBlock = new byte[outerInstance.blockSize];outerInstance.upto = 0;}outerInstance.currentBlock[outerInstance.upto++] = b;}
public ObjectId getObjectId() {return objectId;}
public DeleteDomainEntryResult deleteDomainEntry(DeleteDomainEntryRequest request) {request = beforeClientExecution(request);return executeDeleteDomainEntry(request);}
public long ramBytesUsed() {return fst == null ? 0 : fst.getSizeInBytes();}
public String getFullMessage() { byte[] raw = buffer; int msgB = RawParseUtils.tagMessage(raw, 0); if (msgB < 0) { return ""; } Charset enc = RawParseUtils.parseEncoding(raw); return RawParseUtils.decode(enc, raw, msgB, raw.length); }
public POIFSFileSystem() {HeaderBlock headerBlock = new HeaderBlock(bigBlockSize); _property_table = new PropertyTable(headerBlock); _documents = new ArrayList(); _root = null;}
public void init(int address) {slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT];assert slice != null;upto = address & ByteBlockPool.BYTE_BLOCK_MASK;offset0 = address;assert upto < slice.length;}
public SubmoduleAddCommand setPath(String path) {this.path = path; return this;}
public ListIngestionsResponse listIngestions(ListIngestionsRequest request) {var options = new InvokeOptions();options.requestMarshaller = ListIngestionsRequestMarshaller.instance;options.responseUnmarshaller = ListIngestionsResponseUnmarshaller.instance;return invoke(request, options);}
public QueryParserTokenManager(ICharStream stream, int lexState) { this(stream); switchTo(lexState); }
public GetShardIteratorResult getShardIterator(GetShardIteratorRequest request) {request = beforeClientExecution(request);return executeGetShardIterator(request);}
public ModifyStrategyRequest() {super("aegis", "2016-11-11", "ModifyStrategy", "vipaegis");setMethod(MethodType.POST);}
public boolean ready() {synchronized (lock) {if (in == null) {throw new IOException("InputStreamReader is closed");}try {return bytes.hasRemaining() || in.available() > 0;}catch (IOException) {return false;}}}
protected EscherOptRecord getOptRecord() {return _optRecord;}
public synchronized int read(byte[] buffer, int offset, int length) {      if (buffer == null) {         throw new NullPointerException("buffer == null");     }     if (offset < 0 || length < 0 || offset + length > buffer.length) {         throw new IndexOutOfBoundsException();     }     if (length == 0) {         return 0;     }     int copylen = Math.min(count - pos, length);     System.arraycopy(buffer, pos, buffer, offset, copylen);     pos += copylen;     return copylen; }
public OpenNLPSentenceBreakIterator(NLPSentenceDetectorOp sentenceOp) {this.sentenceOp = sentenceOp;}
public void print(String str) {write(str != null ? str : String.valueOf((Object)null));}
public NotImplementedFunctionException(String functionName, NotImplementedException cause) {super(functionName, cause); this.functionName = functionName;}
public V next() { return nextEntry().getValue(); }
public final void readBytes(byte[] b, int offset, int len, boolean useBuffer) {     int available = bufferLength - bufferPosition;     if (len <= available) {         if (len > 0) {             System.arraycopy(m_buffer, bufferPosition, b, offset, len);         }         bufferPosition += len;     } else {         if (available > 0) {             System.arraycopy(m_buffer, bufferPosition, b, offset, available);             offset += available;             len -= available;             bufferPosition += available;         }         if (useBuffer && len < bufferSize) {             refill();             if (bufferLength < len) {                 System.arraycopy(m_buffer, 0, b, offset, bufferLength);                 throw new EOFException("read past EOF: " + this);             } else {                 System.arraycopy(m_buffer, 0, b, offset, len);                 bufferPosition = len;             }         } else {             long after = bufferStart + bufferPosition + len;             if (after > length) {                 throw new EOFException("read past EOF: " + this);             }             readInternal(b, offset, len);             bufferStart = after;             bufferPosition = 0;             bufferLength = 0;         }     } }
public TagQueueResponse tagQueue(TagQueueRequest request) {request = beforeClientExecution(request);return executeTagQueue(request);}
public void remove() {throw new UnsupportedOperationException();}
public ModifyCacheSubnetGroupResponse modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(ModifyCacheSubnetGroupRequestMarshaller.INSTANCE);options.setResponseUnmarshaller(ModifyCacheSubnetGroupResponseUnmarshaller.INSTANCE);return invoke(request, options);}
public void setParams(String params) {super.setParams(params); culture = ""; String ignore; StringTokenizer st = new StringTokenizer(params, ","); if (st.hasMoreTokens()) culture = st.nextToken(); if (st.hasMoreTokens()) culture += "-" + st.nextToken(); if (st.hasMoreTokens()) ignore = st.nextToken(); }
public DeleteDocumentationVersionResult deleteDocumentationVersion(DeleteDocumentationVersionRequest request) {request = beforeClientExecution(request);return executeDeleteDocumentationVersion(request);}
public boolean equals(Object obj) {if (!(obj instanceof FacetLabel)) {return false;}FacetLabel other = (FacetLabel) obj;if (length != other.length) {return false;}for (int i = length - 1; i >= 0; i--) {if (!components[i].equals(other.components[i])) {return false;}}return true;}
public GetInstanceAccessDetailsResult getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) {request = beforeClientExecution(request);return executeGetInstanceAccessDetails(request);}
public HSSFPolygon createPolygon(HSSFChildAnchor anchor) {HSSFPolygon shape = new HSSFPolygon(this, anchor);shape.setParent(this);shape.setAnchor(anchor);shapes.add(shape);onCreate(shape);return shape;}
public String getSheetName(int sheetIndex) {return getBoundSheetRec(sheetIndex).getSheetname();}
public GetDashboardResult getDashboard(GetDashboardRequest request) {request = beforeClientExecution(request);return executeGetDashboard(request);}
public AssociateSigninDelegateGroupsWithAccountResponse associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) {      request = beforeClientExecution(request);      return executeAssociateSigninDelegateGroupsWithAccount(request);  }
public void addMultipleBlanks(MulBlankRecord mbr) {for (int j = 0; j < mbr.numColumns; j++) {BlankRecord br = new BlankRecord();br.column = j + mbr.firstColumn;br.row = mbr.row;br.xfIndex = mbr.getXFAt(j);insertCell(br);}}
public static String quote(String string) {StringBuilder sb = new StringBuilder();sb.append("\\Q");int apos = 0;int k;while ((k = string.indexOf("\\E", apos)) >= 0){sb.append(string.substring(apos, k + 2)).append("\\\\E\\Q");apos = k + 2;}return sb.append(string.substring(apos)).append("\\E").toString();}
public java.nio.ByteBuffer putInt(int value) { throw new java.nio.ReadOnlyBufferException(); }
public ArrayPtg(Object[][] values2d) {     int nColumns = values2d[0].length;     int nRows = values2d.length;     _nColumns = (short) nColumns;     _nRows = (short) nRows;     Object[] vv = new Object[_nColumns * _nRows];     for (int r = 0; r < nRows; r++) {         Object[] rowData = values2d[r];         for (int c = 0; c < nColumns; c++) {             vv[GetValueIndex(c, r)] = rowData[c];         }     }     _arrayValues = vv;     _reserved0Int = 0;     _reserved1Short = 0;     _reserved2Byte = 0; }
public GetIceServerConfigResult getIceServerConfig(GetIceServerConfigRequest request) {request = beforeClientExecution(request);return executeGetIceServerConfig(request);}
public String toString() { StringBuilder sb = new StringBuilder(64); sb.append(getClass().getSimpleName()).append(" [").append(getValueAsString()).append("]"); return sb.toString(); }
public String toString(String field) {return "ToChildBlockJoinQuery (" + parentQuery + ")";}
public void incRef() {refCount.incrementAndGet();}
public UpdateConfigurationSetSendingEnabledResult updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) {request = beforeClientExecution(request);return executeUpdateConfigurationSetSendingEnabled(request);}
public int getNextXBATChainOffset() { return getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE; }
public void multiplyByPowerOfTen(int pow10) {TenPower tp = TenPower.getInstance(Math.abs(pow10)); if (pow10 < 0) {mulShift(tp._divisor, tp._divisorShift); } else {mulShift(tp._multiplicand, tp._multiplierShift); }}
public String toString() {StringBuilder builder = new StringBuilder();int length = this.length;builder.append(File.separatorChar);for (int i = 0; i < length; i++) {builder.append(this.getComponent(i));if (i < (length - 1)) {builder.append(File.separatorChar);}}return builder.toString();}
public void withFetcher(ECSMetadataServiceCredentialsFetcher fetcher) {this.fetcher = fetcher; this.fetcher.setRoleName(roleName);}
public void setProgressMonitor(ProgressMonitor pm) { progressMonitor = pm; }
public void reset() {if (!first) {ptr = 0;if (!eof) {parseEntry();}}}
public E previous() {if (iterator.previousIndex() >= start) {return iterator.previous();} throw new java.util.NoSuchElementException();}
public String getNewPrefix() {return this.newPrefix;}
public int indexOfValue(int value) {for (int i = 0; i < mSize; i++) {if (mValues[i] == value) {return i;}} return -1;}
public List<CharsRef> uniqueStems(char[] word, int length) {List<CharsRef> stems = stem(word, length);if (stems.size() < 2) {return stems;}Set<CharsRef> terms = new CharArraySet(LuceneVersion.LUCENE_CURRENT, 8, dictionary.ignoreCase);List<CharsRef> deduped = new ArrayList<>();for (CharsRef s : stems) {if (!terms.contains(s)) {deduped.add(s);terms.add(s);}}return deduped;}
public GetGatewayResponsesResult getGatewayResponses(GetGatewayResponsesRequest request) {request = beforeClientExecution(request);return executeGetGatewayResponses(request);}
public void setPosition(long position) {currentBlockIndex = (int) (position >> outerInstance.blockBits); currentBlock = outerInstance.blocks[currentBlockIndex]; currentBlockUpto = (int) (position & outerInstance.blockMask);}
public long skip(long n) {int s = (int)Math.min(available(), Math.max(0, n));ptr += s;return s;}
public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) {_bootstrapActionConfig = bootstrapActionConfig;}
public void serialize(ILittleEndianOutput out1) {     out1.writeShort(field_1_row);     out1.writeShort(field_2_col);     out1.writeShort(field_3_flags);     out1.writeShort(field_4_shapeid);     out1.writeShort(field_6_author.length);     out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00);     if (field_5_hasMultibyte) {         StringUtil.putUnicodeLE(field_6_author, out1);     } else {         StringUtil.putCompressedUnicode(field_6_author, out1);     }     if (field_7_padding != null) {         out1.writeByte(Integer.parseInt(field_7_padding));     } }
public int lastIndexOf(String string) {return lastIndexOf(string, count);}
public boolean add(E object) {return addLastImpl(object);}
public void unsetSection(String section, String subsection) {ConfigSnapshot src; ConfigSnapshot res; do {src = state.get(); res = unsetSection(src, section, subsection);} while (!state.compareAndSet(src, res));}
public String getTagName() {return tagName;}
public void addSubRecord(int index, SubRecord element) {subrecords.add(index, element);}
public boolean remove(Object object) {synchronized (mutex) {return c.remove(object);}}
public TokenStream create(TokenStream input) {return new DoubleMetaphoneFilter(input, maxCodeLength, inject);}
public long length() {return inCoreLength();}
public void setValue(boolean newValue) {value = newValue;}
public Pair(ContentSource oldSource, ContentSource newSource) {this.oldSource = oldSource; this.newSource = newSource;}
public int get(int i) {if (count <= i) {throw new IndexOutOfBoundsException(i);}return entries[i];}
public CreateRepoRequest() {super("cr", "2016-06-07", "CreateRepo", "cr", "openAPI"); uriPattern = "/repos"; method = MethodType.PUT;}
public boolean isDeltaBaseAsOffset() {return deltaBaseAsOffset;}
public void remove() {      if (expectedModCount == list.modCount) {          if (lastLink != null) {              java.util.LinkedList.Link<ET> next = lastLink.next;              java.util.LinkedList.Link<ET> previous = lastLink.previous;              next.previous = previous;              previous.next = next;              if (lastLink == link) {                  pos--;              }              link = previous;              lastLink = null;              expectedModCount++;              list._size--;              list.modCount++;          } else {              throw new java.lang.IllegalStateException();          }      } else {          throw new java.util.ConcurrentModificationException();      }  }
public MergeShardsResponse mergeShards(MergeShardsRequest request) {     InvokeOptions options = new InvokeOptions();     options.setRequestMarshaller(MergeShardsRequestMarshaller.INSTANCE);     options.setResponseUnmarshaller(MergeShardsResponseUnmarshaller.INSTANCE);     return invoke(request, options); }
public AllocateHostedConnectionResponse allocateHostedConnection(AllocateHostedConnectionRequest request) {     InvokeOptions options = new InvokeOptions();     options.setRequestMarshaller(AllocateHostedConnectionRequestMarshaller.INSTANCE);     options.setResponseUnmarshaller(AllocateHostedConnectionResponseUnmarshaller.INSTANCE);     return invoke(request, options); }
public int getBeginIndex() {return start;}
public static WeightedTerm[] getTerms(Query query) {return getTerms(query, false);}
public java.nio.ByteBuffer compact() { throw new java.nio.ReadOnlyBufferException(); }
public void decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) {for (int i = 0; i < iterations; ++i){int byte0 = blocks[blocksOffset++] & 0xFF;values[valuesOffset++] = (int)((byte0 & 0xFF) >>> 2);int byte1 = blocks[blocksOffset++] & 0xFF;values[valuesOffset++] = ((byte0 & 3) << 4) | ((byte1 & 0xFF) >>> 4);int byte2 = blocks[blocksOffset++] & 0xFF;values[valuesOffset++] = ((byte1 & 15) << 2) | ((byte2 & 0xFF) >>> 6);values[valuesOffset++] = byte2 & 63;}}
public String getHumanishName() {      String s = getPath();      if (s == null || s.isEmpty()) {          throw new IllegalArgumentException();      }      String[] elements;      if ("file".equals(scheme) || LOCAL_FILE.matcher(s).matches()) {          elements = s.split("[\\\\/" + File.separator + "/]");      } else {          elements = s.split("/");      }      if (elements.length == 0) {          throw new IllegalArgumentException();      }      String result = elements[elements.length - 1];      if (Constants.DOT_GIT.equals(result)) {          result = elements[elements.length - 2];      } else if (result.endsWith(Constants.DOT_GIT_EXT)) {          result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length());      }      return result;  }
public DescribeNotebookInstanceLifecycleConfigResult describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) {request = beforeClientExecution(request);return executeDescribeNotebookInstanceLifecycleConfig(request);}
public String getAccessKeySecret() {return accessSecret;}
public CreateVpnConnectionResponse createVpnConnection(CreateVpnConnectionRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(CreateVpnConnectionRequestMarshaller.INSTANCE);options.setResponseUnmarshaller(CreateVpnConnectionResponseUnmarshaller.INSTANCE);return invoke(request, options);}
public DescribeVoicesResult describeVoices(DescribeVoicesRequest request) {request = beforeClientExecution(request);return executeDescribeVoices(request);}
public ListMonitoringExecutionsResult listMonitoringExecutions(ListMonitoringExecutionsRequest request) {request = beforeClientExecution(request);return executeListMonitoringExecutions(request);}
public DescribeJobRequest(String vaultName, String jobId) {_vaultName = vaultName; _jobId = jobId;}
public EscherRecord getEscherRecord(int index) {return escherRecords[index];}
public GetApisResult getApis(GetApisRequest request) {request = beforeClientExecution(request);return executeGetApis(request);}
public DeleteSmsChannelResult deleteSmsChannel(DeleteSmsChannelRequest request) {request = beforeClientExecution(request);return executeDeleteSmsChannel(request);}
public TrackingRefUpdate getTrackingRefUpdate() {return trackingRefUpdate;}
public void print(boolean b) {print(Boolean.toString(b));}
public IQueryNode getChild(){return getChildren()[0];}
public NotIgnoredFilter(int workdirTreeIndex) {this.index = workdirTreeIndex;}
public AreaRecord(RecordInputStream in1) {field_1_formatFlags = in1.readShort();}
public GetThumbnailRequest() {super("CloudPhoto", "2017-07-11", "GetThumbnail", "cloudphoto", "openAPI"); setProtocol(ProtocolType.HTTPS);}
public DescribeTransitGatewayVpcAttachmentsResult describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) {request = beforeClientExecution(request);return executeDescribeTransitGatewayVpcAttachments(request);}
public PutVoiceConnectorStreamingConfigurationResult putVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) {request = beforeClientExecution(request);return executePutVoiceConnectorStreamingConfiguration(request);}
public OrdRange getOrdRange(String dim) {OrdRange result; prefixToOrdRange.tryGetValue(dim, out result); return result;}
public String toString() {String symbol = "";if (startIndex >= 0 && startIndex < ((ICharStream)inputStream).size()) {symbol = ((ICharStream)inputStream).getText(Interval.of(startIndex, startIndex));symbol = Utils.escapeWhitespace(symbol, false);}return String.format(Locale.getDefault(), "%s('%s')", getClass().getSimpleName(), symbol);}
public E peek() {return peekFirstImpl();}
public CreateWorkspacesResponse createWorkspaces(CreateWorkspacesRequest request) {request = beforeClientExecution(request);return executeCreateWorkspaces(request);}
public Object clone() {NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = field_1_formatIndex; return rec;}
public DescribeRepositoriesResult describeRepositories(DescribeRepositoriesRequest request) {request = beforeClientExecution(request);return executeDescribeRepositories(request);}
public SparseIntArray(int initialCapacity) {initialCapacity = android.util.ArrayUtils.idealIntArraySize(initialCapacity);mKeys = new int[initialCapacity];mValues = new int[initialCapacity];mSize = 0;}
@Override public TokenStream create(TokenStream input) {return new HyphenatedWordsFilter(input);}
public CreateDistributionWithTagsResult createDistributionWithTags(CreateDistributionWithTagsRequest request) {request = beforeClientExecution(request);return executeCreateDistributionWithTags(request);}
public RandomAccessFile(String fileName, String mode) throws java.io.FileNotFoundException {this(new java.io.File(fileName), mode);}
public DeleteWorkspaceImageResult deleteWorkspaceImage(DeleteWorkspaceImageRequest request) {request = beforeClientExecution(request);return executeDeleteWorkspaceImage(request);}
public static String toHex(int value) {return toHex((long) value, 8);}
public UpdateDistributionResult updateDistribution(UpdateDistributionRequest request) {request = beforeClientExecution(request);return executeUpdateDistribution(request);}
public HSSFColor getColor(short index) {if (index == HSSFColor.Automatic.index) return HSSFColor.Automatic.getInstance(); else {byte[] b = palette.getColor(index); if (b != null) {return new CustomColor(index, b);}} return null;}
@Override public ValueEval evaluate(ValueEval[] operands, int srcRow, int srcCol) {throw new NotImplementedFunctionException(_functionName);}
@Override public void serialize(ILittleEndianOutput out) {out.writeShort((short)field_1_number_crn_records);out.writeShort((short)field_2_sheet_table_index);}
public DescribeDBEngineVersionsResult describeDBEngineVersions() {return describeDBEngineVersions(new DescribeDBEngineVersionsRequest());}
public FormatRun(short character, short fontIndex) {this._character = character; this._fontIndex = fontIndex;}
public static byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) {byte[] result = new byte[length * 2];int end = offset + length;int resultIndex = 0;for (int i = offset; i < end; ++i) {char ch = chars[i];result[resultIndex++] = (byte)(ch >> 8);result[resultIndex++] = (byte)ch;}return result;}
public UploadArchiveResult uploadArchive(UploadArchiveRequest request) {request = beforeClientExecution(request);return executeUploadArchive(request);}
public List<IToken> getHiddenTokensToLeft(int tokenIndex) {return getHiddenTokensToLeft(tokenIndex, -1);}
public boolean equals(Object obj) {if (this == obj) return true;if (!super.equals(obj)) return false;if (getClass() != obj.getClass()) return false;AutomatonQuery other = (AutomatonQuery)obj;if (!m_compiled.equals(other.m_compiled)) return false;if (m_term == null) return other.m_term == null;else return m_term.equals(other.m_term);}
public SpanQuery makeSpanClause() {List<SpanQuery> spanQueries = new ArrayList<>();for (Map.Entry<SpanQuery, Float> entry : weightBySpanQuery.entrySet()) {entry.getKey().setBoost(entry.getValue());spanQueries.add(entry.getKey());}return spanQueries.size() == 1 ? spanQueries.get(0) : new SpanOrQuery(spanQueries.toArray(new SpanQuery[0]));}
public StashCreateCommand stashCreate() {return new StashCreateCommand(repo);}
public FieldInfo getFieldInfo(String fieldName) {FieldInfo ret;byName.tryGetValue(fieldName, out ret);return ret;}
public DescribeEventSourceResult describeEventSource(DescribeEventSourceRequest request) {request = beforeClientExecution(request);return executeDescribeEventSource(request);}
public GetDocumentAnalysisResponse getDocumentAnalysis(GetDocumentAnalysisRequest request) {return invoke(request, new InvokeOptions(GetDocumentAnalysisRequestMarshaller.instance, GetDocumentAnalysisResponseUnmarshaller.instance));}
public CancelUpdateStackResult cancelUpdateStack(CancelUpdateStackRequest request) {request = beforeClientExecution(request);return executeCancelUpdateStack(request);}
public ModifyLoadBalancerAttributesResult modifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) {request = beforeClientExecution(request);return executeModifyLoadBalancerAttributes(request);}
public SetInstanceProtectionResponse setInstanceProtection(SetInstanceProtectionRequest request) {      final InvokeOptions options = new InvokeOptions();     options.setRequestMarshaller(SetInstanceProtectionRequestMarshaller.getInstance());     options.setResponseUnmarshaller(SetInstanceProtectionResponseUnmarshaller.getInstance());     return invoke(request, options); }
public ModifyDBProxyResponse modifyDBProxy(ModifyDBProxyRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(ModifyDBProxyRequestMarshaller.INSTANCE);options.setResponseUnmarshaller(ModifyDBProxyResponseUnmarshaller.INSTANCE);return invoke(request, options);}
public void add(char[] output, int offset, int len, int endOffset, int posLength) {if (count == outputs.length) {CharsRef[] next = new CharsRef[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_OBJECT_REF)];System.arraycopy(outputs, 0, next, 0, count);outputs = next;}if (count == endOffsets.length) {int[] next = new int[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_INT32)];System.arraycopy(endOffsets, 0, next, 0, count);endOffsets = next;}if (count == posLengths.length) {int[] next = new int[ArrayUtil.oversize(1 + count, RamUsageEstimator.NUM_BYTES_INT32)];System.arraycopy(posLengths, 0, next, 0, count);posLengths = next;}if (outputs[count] == null) {outputs[count] = new CharsRef();}outputs[count].copyChars(output, offset, len);endOffsets[count] = endOffset;posLengths[count] = posLength;count++;}
public FetchLibrariesRequest() {super("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto", "openAPI"); this.Protocol = ProtocolType.HTTPS;}
public boolean exists() {return objects.exists();}
public FilterOutputStream(java.io.OutputStream out) {this.out = out;}
public ScaleClusterRequest() {super("CS", "2015-12-15", "ScaleCluster", "cs", "openAPI"); this.uriPattern = "/clusters/[ClusterId]"; this.method = MethodType.PUT;}
public IDataValidationConstraint createTimeConstraint(int operatorType, String formula1, String formula2) {return DVConstraint.createTimeConstraint(operatorType, formula1, formula2);}
public ListObjectParentPathsResponse listObjectParentPaths(ListObjectParentPathsRequest request) {     InvokeOptions options = new InvokeOptions();     options.setRequestMarshaller(ListObjectParentPathsRequestMarshaller.INSTANCE);     options.setResponseUnmarshaller(ListObjectParentPathsResponseUnmarshaller.INSTANCE);     return invoke(request, options); }
public DescribeCacheSubnetGroupsResult describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) {request = beforeClientExecution(request);return executeDescribeCacheSubnetGroups(request);}
public void setSharedFormula(boolean flag) {field_5_options = sharedFormula.setShortBoolean(field_5_options, flag);}
public boolean isReuseObjects() { return reuseObjects; }
public IErrorNode addErrorNode(IToken badToken) {ErrorNodeImpl t = new ErrorNodeImpl(badToken); addChild(t); t.setParent(this); return t;}
public LatvianStemFilterFactory(Map<String, String> args) throws ArgumentException {super(args); if (!args.isEmpty()) {throw new ArgumentException("Unknown parameters: " + args);}}
public RemoveSourceIdentifierFromSubscriptionResponse removeSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(RemoveSourceIdentifierFromSubscriptionRequestMarshaller.getInstance());options.setResponseUnmarshaller(RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.getInstance());return invoke(request, options);}
public static TokenFilterFactory forName(String name, Map<String, String> args) {return loader.newInstance(name, args);}
public AddAlbumPhotosRequest() {super("CloudPhoto", "2017-07-11", "AddAlbumPhotos", "cloudphoto", "openAPI");this.protocol = ProtocolType.HTTPS;}
public GetThreatIntelSetResponse getThreatIntelSet(GetThreatIntelSetRequest request) {request = beforeClientExecution(request);return executeGetThreatIntelSet(request);}
public AndTreeFilter clone() {return new AndTreeFilter.Binary(a.clone(), b.clone());}
public boolean equals(Object o) {return o instanceof ArmenianStemmer;}
public final boolean hasArray() {return protectedHasArray();}
public UpdateContributorInsightsResult updateContributorInsights(UpdateContributorInsightsRequest request) {request = beforeClientExecution(request);return executeUpdateContributorInsights(request);}
public void unwriteProtectWorkbook() {records.remove(fileShare); records.remove(WriteProtect); fileShare = null; writeProtect = null;}
public SolrSynonymParser(boolean dedup, boolean expand, Analyzer analyzer) {super(dedup, analyzer); this.expand = expand;}
public RequestSpotInstancesResult requestSpotInstances(RequestSpotInstancesRequest request) {request = beforeClientExecution(request);return executeRequestSpotInstances(request);}
public byte[] getObjectData(){return findObjectRecord().getObjectData();}
public GetContactAttributesResponse getContactAttributes(GetContactAttributesRequest request) {request = beforeClientExecution(request);return executeGetContactAttributes(request);}
public String toString() {return getKey() + ": " + getValue();}
public ListTextTranslationJobsResult listTextTranslationJobs(ListTextTranslationJobsRequest request) {request = beforeClientExecution(request);return executeListTextTranslationJobs(request);}
public GetContactMethodsResponse getContactMethods(GetContactMethodsRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(GetContactMethodsRequestMarshaller.getInstance());options.setResponseUnmarshaller(GetContactMethodsResponseUnmarshaller.getInstance());return invoke(request, options);}
public static short lookupIndexByName(String name) {FunctionMetadata fd = getInstance().getFunctionByNameInternal(name); return fd == null ? -1 : (short) fd.index;}
public DescribeAnomalyDetectorsResult describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) {request = beforeClientExecution(request);return executeDescribeAnomalyDetectors(request);}
public static String insertId(String message, ObjectId changeId) {return insertId(message, changeId, false);}
public long getObjectSize(AnyObjectId objectId, int typeHint) {long sz = db.getObjectSize(this, objectId);if (sz < 0) {if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.copy(), "unknown");throw new MissingObjectException(objectId.copy(), typeHint);}return sz;}
public ImportInstallationMediaResponse importInstallationMedia(ImportInstallationMediaRequest request) {final InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(ImportInstallationMediaRequestMarshaller.getInstance());options.setResponseUnmarshaller(ImportInstallationMediaResponseUnmarshaller.getInstance());return invoke(request, options);}
public PutLifecycleEventHookExecutionStatusResult putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) {request = beforeClientExecution(request);return executePutLifecycleEventHookExecutionStatus(request);}
public NumberPtg(ILittleEndianInput in) {field_1_value = in.readDouble();}
public GetFieldLevelEncryptionConfigResult getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) {request = beforeClientExecution(request);return executeGetFieldLevelEncryptionConfig(request);}
public DescribeDetectorResult describeDetector(DescribeDetectorRequest request) {request = beforeClientExecution(request);return executeDescribeDetector(request);}
public ReportInstanceStatusResult reportInstanceStatus(ReportInstanceStatusRequest request) {request = beforeClientExecution(request);return executeReportInstanceStatus(request);}
public DeleteAlarmResult deleteAlarm(DeleteAlarmRequest request) {request = beforeClientExecution(request);return executeDeleteAlarm(request);}
public TokenStream create(TokenStream input) {return new PortugueseStemFilter(input);}
public FtCblsSubRecord() { reserved = new byte[ENCODED_SIZE]; }
public synchronized boolean remove(Object object) {return c.remove(object);}
public GetDedicatedIpResult getDedicatedIp(GetDedicatedIpRequest request) {request = beforeClientExecution(request);return executeGetDedicatedIp(request);}
@Override public String toString() {return precedence + " >= _p";}
public ListStreamProcessorsResponse listStreamProcessors(ListStreamProcessorsRequest request) {request = beforeClientExecution(request);return executeListStreamProcessors(request);}
public DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) {_loadBalancerName = loadBalancerName; _policyName = policyName;}
public WindowProtectRecord(int options) {this.options = options;}
public UnbufferedCharStream(int bufferSize) {n = 0; data = new int[bufferSize];}
public GetOperationsResponse getOperations(GetOperationsRequest request) {request = beforeClientExecution(request);return executeGetOperations(request);}
public void copyRawTo(byte[] b, int o) {NB.encodeInt32(b, o, w1); NB.encodeInt32(b, o + 4, w2); NB.encodeInt32(b, o + 8, w3); NB.encodeInt32(b, o + 12, w4); NB.encodeInt32(b, o + 16, w5);}
public WindowOneRecord(RecordInputStream in1) {field_1_h_hold = in1.readShort();field_2_v_hold = in1.readShort();field_3_width = in1.readShort();field_4_height = in1.readShort();field_5_options = in1.readShort();field_6_active_sheet = in1.readShort();field_7_first_visible_tab = in1.readShort();field_8_num_selected_tabs = in1.readShort();field_9_tab_width_ratio = in1.readShort();}
public StopWorkspacesResult stopWorkspaces(StopWorkspacesRequest request) {request = beforeClientExecution(request);return executeStopWorkspaces(request);}
public void close() throws IOException {if (isOpen) {isOpen = false;try {dump();}finally {try {channel.truncate(fileLength);}finally {try {channel.close();}finally {fos.close();}}}}}
public DescribeMatchmakingRuleSetsResult describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) {request = beforeClientExecution(request);return executeDescribeMatchmakingRuleSets(request);}
public String getPronunciation(int wordId, char[] surface, int off, int len) {return null;}
public String getPath(){return pathStr;}
public static double devsq(double[] v) {double r = Double.NaN;if (v != null && v.length >= 1){double m = 0;double s = 0;int n = v.length;for (int i = 0; i < n; i++)s += v[i];m = s / n;s = 0;for (int i = 0; i < n; i++)s += Math.pow((v[i] - m), 2);r = (n == 1)? 0: s;}return r;}
public DescribeResizeResult describeResize(DescribeResizeRequest request) {request = beforeClientExecution(request);return executeDescribeResize(request);}
public boolean hasPassedThroughNonGreedyDecision(){return passedThroughNonGreedyDecision;}
public int end(){return end(0);}
public void traverse(ICellHandler handler) { int firstRow = range.getFirstRow(); int lastRow = range.getLastRow(); int firstColumn = range.getFirstColumn(); int lastColumn = range.getLastColumn(); int width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); IRow currentRow = null; ICell currentCell = null; for (ctx.setRowNumber(firstRow); ctx.getRowNumber() <= lastRow; ctx.setRowNumber(ctx.getRowNumber() + 1)) { currentRow = sheet.getRow(ctx.getRowNumber()); if (currentRow == null) { continue; } for (ctx.setColNumber(firstColumn); ctx.getColNumber() <= lastColumn; ctx.setColNumber(ctx.getColNumber() + 1)) { currentCell = currentRow.getCell(ctx.getColNumber()); if (currentCell == null) { continue; } if (isEmpty(currentCell) && !traverseEmptyCells) { continue; } ctx.setOrdinalNumber((ctx.getRowNumber() - firstRow) * width + (ctx.getColNumber() - firstColumn + 1)); handler.onCell(currentCell, ctx); } }
public int getReadIndex() {return _ReadIndex;}
public int compareTo(ScoreTerm other) {if (Term.bytesEquals(other.term)) {return 0;} else if (this.boost == other.boost) {return other.term.compareTo(this.term);} else {return Integer.compare(this.boost, other.boost);}}
public int normalize(char[] s, int len) {for (int i = 0; i < len; i++) {switch (s[i]) {case FARSI_YEH:case YEH_BARREE:s[i] = YEH;break;case KEHEH:s[i] = KAF;break;case HEH_YEH:case HEH_GOAL:s[i] = HEH;break;case HAMZA_ABOVE:len = StemmerUtil.delete(s, i, len);i--;break;default:break;}}return len;}
public void serialize(ILittleEndianOutput out1) {out1.writeShort(_options);}
public DiagnosticErrorListener(boolean exactOnly) {this.exactOnly = exactOnly;}
public KeySchemaElement(String attributeName, KeyType keyType) {_attributeName = attributeName; _keyType = keyType;}
public GetAssignmentResult getAssignment(GetAssignmentRequest request) {request = beforeClientExecution(request);return executeGetAssignment(request);}
public boolean hasObject(AnyObjectId id) {return findOffset(id) != -1;}
public GroupingSearch setAllGroups(boolean allGroups) {this.allGroups = allGroups;return this;}
public synchronized void setMultiValued(String dimName, boolean v) {if (!fieldTypes.containsKey(dimName)) {fieldTypes.put(dimName, new DimConfig().setIsMultiValued(v));} else {fieldTypes.get(dimName).setIsMultiValued(v);}}
public int getCellsVal(){int size = 0;for(char c : cells.keySet()){Cell e = at(c);if(e.cmd >= 0){size++;}}return size;}
public DeleteVoiceConnectorResult deleteVoiceConnector(DeleteVoiceConnectorRequest request) {request = beforeClientExecution(request);return executeDeleteVoiceConnector(request);}
public DeleteLifecyclePolicyResponse deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) {request = beforeClientExecution(request);return executeDeleteLifecyclePolicy(request);}
