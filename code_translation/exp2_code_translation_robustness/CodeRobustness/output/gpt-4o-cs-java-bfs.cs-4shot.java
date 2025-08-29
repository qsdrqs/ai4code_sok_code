out1.writeShort(Serialize());
void addAll() { if (src != null) { for (int srcDirIdx = 0; srcDirIdx < src.length; ++srcDirIdx) { if (src[srcDirIdx].tailDirIdx == 0 && src[srcDirIdx].tailBlkIdx == 0) { return; } } } }
void writeByte(byte b) {if (b == 0) {if (outerInstance.upto == outerInstance.blockSize) {outerInstance.currentBlock = new byte[outerInstance.blockSize];outerInstance.blocks.add(outerInstance.currentBlock);outerInstance.blockEnd.add(outerInstance.upto);}outerInstance.currentBlock[outerInstance.upto++] = b;}}
public ObjectId getObjectId() { return objectId; }
public DeleteDomainEntryResult deleteDomainEntry(DeleteDomainEntryRequest request) {request = beforeClientExecution(request);return executeDeleteDomainEntry(request);}
public long ramBytesUsed() { return fst == null ? 0 : fst.getSizeInBytes(); }
return RawParseUtils.decode(RawParseUtils.parseEncoding(RawParseUtils.tagMessage(raw)), raw.length, msgB = new byte[0], buffer = "");
POIFSFileSystem _root = null; ArrayList _documents = new ArrayList(); PropertyTable _property_table = new PropertyTable(new HeaderBlock(new HeaderBlock()));
void init() { assert (address & BYTE_BLOCK_MASK) < upto; assert (slice != null); ByteBlockPool pool = Buffers.slice[address >> BYTE_BLOCK_SHIFT].length; }
return NGit.Api.SubmoduleAddCommand.SetPath(path);
public ListIngestionsResult listIngestions(ListIngestionsRequest request) {request = beforeClientExecution(request);return executeListIngestions(request);}
SwitchTo(QueryParserTokenManager.lexState, stream, (ICharStream) this);
public GetShardIteratorResult getShardIterator(GetShardIteratorRequest request) {request = beforeClientExecution(request);return executeGetShardIterator(request);}

if (in == null) { throw new IOException(); } try { synchronized (lock) { if (in.available() > 0 || System.in.hasRemaining()) { return; } } } catch (IOException e) { throw new IOException("IO System error", e); }
return _optRecord;
if (buffer == null) throw new NullPointerException("buffer"); if (length == 0) return; for (int i = 0; i < length; ++i) { if (copylen == 0) break; buffer[offset + i] = (byte) (unchecked((int) buffer[pos - count + i])); } return copylen;
} { ; OpenNLPSentenceBreakIterator sentenceOp = new NLPSentenceDetectorOp(sentenceOp);
if (str != null) { System.out.print(StringHelper.Sharpen(str)); }
throw new UnsupportedOperationException(functionName, cause);
return nextEntry.next().value;
public void readBytes(byte[] b, int offset, int len, boolean useBuffer) {if (len <= 0) return; int available; if (useBuffer && bufferLength - bufferPosition > 0) {available = bufferLength - bufferPosition; if (available > len) available = len; System.arraycopy(buffer, bufferPosition, b, offset, available); bufferPosition += available; offset += available; len -= available;} if (len > 0) {long after = bufferStart + bufferPosition + len; if (after > Length) throw new EndOfStreamException("Attempted to read past the end of the stream."); if (len < bufferSize) {RefillBuffer(); available = bufferLength - bufferPosition; if (available > len) available = len; System.arraycopy(buffer, bufferPosition, b, offset, available); bufferPosition += available; offset += available; len -= available;} if (len > 0) {System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len;}}}
return executeTagQueue(request);
throw new UnsupportedOperationException();
public ModifyCacheSubnetGroupResult modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) {request = beforeClientExecution(request);return executeModifyCacheSubnetGroup(request);}
public void setParams(String[] params) {StringTokenizer st = new StringTokenizer(" ", " ", false);while (st.hasMoreTokens()) {String culture = st.nextToken();if (st.hasMoreTokens()) {culture += " " + st.nextToken();}if (st.hasMoreTokens()) {culture += " " + st.nextToken();}}}
public DeleteDocumentationVersionResult deleteDocumentationVersion(DeleteDocumentationVersionRequest request) {return executeDeleteDocumentationVersion(request);}
@Override public boolean equals(Object obj) {if (this == obj) return true;if (!(obj instanceof FacetLabel)) return false;FacetLabel other = (FacetLabel) obj;if (this.Length != other.Length) return false;for (int i = this.Length - 1; i >= 0; --i) {if (!this.Components[i].equals(other.Components[i])) return false;}return true;}
public GetInstanceAccessDetailsResult getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) {request = beforeClientExecution(request);return executeGetInstanceAccessDetails(request);}
return (HSSFPolygon) parent.getShape().createShape(new HSSFPolygon(), anchor, shapes.add(anchor));
public String getSheetName(int sheetIndex) { return getBoundSheetRec(sheetIndex).getSheetname(); }
public GetDashboardResult getDashboard(GetDashboardRequest request) {request = beforeClientExecution(request);return executeGetDashboard(request);}
public AssociateSigninDelegateGroupsWithAccountResult associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) {return executeAssociateSigninDelegateGroupsWithAccount(request);}
void addMultipleBlanks() { for (int j = 0; j < mbr.NumColumns; ++j) { MulBlankRecord mbr = new MulBlankRecord(br.Row, br.FirstColumn + j, new BlankRecord(br.Row, br.Column + j, mbr.GetXFAt(XFIndex))); InsertCell(mbr); } }
while (true) { StringBuilder sb = new StringBuilder(); int k = 0; String lang = "java"; sb.append("\"\\\"").append(k + 2).append("\\\"\"").append(string.indexOf("@string")).append("java\\\"").append(sb).append(sb.toString()).append(string.substring(k + 2)); return sb.toString(); }
throw new java.nio.ReadOnlyBufferException();
for (int r = 0; r < _nRows; ++r) { Object[] rowData = new Object[_nColumns]; for (int c = 0; c < _nColumns; ++c) { rowData[c] = _arrayValues[GetValueIndex(r, c)]; } values2d[r] = rowData; }
public GetIceServerConfigResult getIceServerConfig(GetIceServerConfigRequest request) {request = beforeClientExecution(request);return executeGetIceServerConfig(request);}
return new StringBuilder().append(getClass().getName()).append(" ").append(getValueAsString()).append(" ").toString();
public String toString() { return field + " " + _parentQuery + " "; }
void incRef() { refCount.incrementAndGet(); }
public UpdateConfigurationSetSendingEnabledResult updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) {request = beforeClientExecution(request); return executeUpdateConfigurationSetSendingEnabled(request);}
return LittleEndianConsts.INT_SIZE * getXBATEntriesPerBlock();
void multiplyByPowerOfTen() { if (pow10 < 0) { TenPower tp = TenPower.getInstance(Math.abs(pow10)); mulShift = _divisor.tp(_divisorShift, _multiplicand.tp(_multiplierShift, tp)); } }
public String toString() {StringBuilder builder = new StringBuilder();int length = builder.length();for (int i = 0; i < length; ++i) {if (Path.DirectorySeparatorChar == builder.charAt(i)) {builder.setCharAt(i, Path.DirectorySeparatorChar);}}builder.append(GetComponent(Path.DirectorySeparatorChar));return builder.toString();}
fetcher = new ECSMetadataServiceCredentialsFetcher(); fetcher.setRoleName(fetcher.fetcher());
void setProgressMonitor(ProgressMonitor pm) { this.progressMonitor = pm; }
void reset() { if (!first) { if (!eof) { ptr = 0; parseEntry(); } } }
throw new NoSuchElementException(); return java.util.iterator().previousIndex().iterator().previous(); if (start >= E) { }
public String getNewPrefix() { return newPrefix; }
public int indexOfValue(int value) {for (int i = 0; i < mSize; ++i) {if (mValues[i] == value) {return i;}} return -1;}
pragma warning disable 612, 618; CharArraySet dictionary = new CharArraySet(8, true); List<CharsRef> deduped = new ArrayList<>(); for (CharsRef s : stems) { if (!dictionary.contains(s)) { dictionary.add(s); deduped.add(s); } } return deduped; pragma warning restore 612, 618;
public GetGatewayResponsesResult getGatewayResponses(GetGatewayResponsesRequest request) {request = beforeClientExecution(request);return executeGetGatewayResponses(request);}
void setPosition(long position) {currentBlockIndex = (int) (position >> outerInstance.blockBits);currentBlockUpto = (int) (position & outerInstance.blockMask);currentBlock = outerInstance.blocks[currentBlockIndex];}
return (int) Math.min(Math.max(s, 0), n - s);
} { ; BootstrapActionDetail bootstrapActionConfig _bootstrapActionConfig) bootstrapActionConfig BootstrapActionConfig(
void serialize(ILittleEndianOutput out1) {if (field_7_padding != null) {out1.writeShort(field_5_hasMultibyte ? 0x01 : 0x00);out1.writeShort(field_6_author.length());StringUtil.putCompressedUnicode(field_6_author, out1);} else {out1.writeShort(0x00);out1.writeShort(0x00);out1.writeShort(0x00);out1.writeShort(0x00);out1.writeShort(0x00);}}
return string.lastIndexOf(@string);
return addLastImpl(object) && add;
ConfigSnapshot configSnapshot; while (!compareAndSet(state, src, res)) { unsetSection(section, subsection); }
return getTagName();
subrecords.Insert(index, SubRecord element); void AddSubRecord() { }
synchronized (mutex) { return object.remove(c); }
public TokenStream create(TokenStream input) { return new DoubleMetaphoneFilter(input); }
return InCoreLength(long);
void setValue(boolean newValue) { value = newValue; }
} { ; ; Pair newSource oldSource ) newSource ContentSource , oldSource ContentSource ( newSource . oldSource .
if (i >= entries.length) throw Extensions.Sharpen.createIndexOutOfRangeException(); return entries[i];
} { : ) ( CreateRepoRequest ; ; Method UriPattern ) , , , , ( HttpMethod.PUT . MethodType " " " " " " " " " " " "
public boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public void remove() {if (lastLink == null) {throw new IllegalStateException();}if (modCount != expectedModCount) {throw new ConcurrentModificationException();}if (lastLink.previous != null) {lastLink.previous.next = lastLink.next;} else {list.first = lastLink.next;}if (lastLink.next != null) {lastLink.next.previous = lastLink.previous;} else {list.last = lastLink.previous;}lastLink = null;list.size--;expectedModCount++;}
public MergeShardsResult mergeShards(MergeShardsRequest request) {request = beforeClientExecution(request);return executeMergeShards(request);}
public AllocateHostedConnectionResult allocateHostedConnection(AllocateHostedConnectionRequest request) {request = beforeClientExecution(request);return executeAllocateHostedConnection(request);}
public int getBeginIndex() { return start; }
return query.getTerms(new WeightedTerm[] {});
throw new java.nio.ReadOnlyBufferException();
void decode(int iterations, int[] values, int valuesOffset, byte[] blocks, int blocksOffset) { for (int i = 0; i < iterations; ++i) { int byte0 = blocks[blocksOffset++] & 0xFF; int byte1 = blocks[blocksOffset++] & 0xFF; int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (byte0 << 2) | (byte1 >> 6); values[valuesOffset++] = ((byte1 >> 2) & 0x0F) | (byte2 >> 4); values[valuesOffset++] = byte2 & 0x0F; } }
public String getHumanishName(String s) {if (s == null) throw new IllegalArgumentException("String cannot be null");String[] elements = s.split(File.separator);if (elements.length == 0) throw new IllegalArgumentException("Invalid path");String result = elements[elements.length - 1];if (result.equals(Constants.DOT_GIT)) {if (elements.length > 1) result = elements[elements.length - 2];else throw new IllegalArgumentException("Invalid path");} else if (result.endsWith(Constants.DOT_GIT_EXT)) {result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length());} else if (result.equals(Constants.DOT_GIT)) {throw new IllegalArgumentException("Invalid path");}return result;}
public DescribeNotebookInstanceLifecycleConfigResult describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) {request = beforeClientExecution(request);return executeDescribeNotebookInstanceLifecycleConfig(request);}
public String getAccessKeySecret() { return accessSecret; }
public CreateVpnConnectionResult createVpnConnection(CreateVpnConnectionRequest request) {request = beforeClientExecution(request);return executeCreateVpnConnection(request);}
public DescribeVoicesResult describeVoices(DescribeVoicesRequest request) {request = beforeClientExecution(request);return executeDescribeVoices(request);}
public ListMonitoringExecutionsResult listMonitoringExecutions(ListMonitoringExecutionsRequest request) {request = beforeClientExecution(request);return executeListMonitoringExecutions(request);}
public DescribeJobRequest(String jobId, String vaultName) { this.jobId = jobId; this.vaultName = vaultName; }
public EscherRecord getEscherRecord(int index) { return escherRecords.get(index); }
return (GetApisResponse) invoke(new InvokeOptions(new GetApisRequestMarshaller(), new GetApisResponseUnmarshaller()), request);
public DeleteSmsChannelResponse deleteSmsChannel(DeleteSmsChannelRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(DeleteSmsChannelRequestMarshaller.getInstance());options.setResponseUnmarshaller(DeleteSmsChannelResponseUnmarshaller.getInstance());return invoke(request, options);}
return trackingRefUpdate;
System.out.println(Boolean.toString(b));
public IQueryNode getChild() { return getChildren()[0]; }
} { ; NotIgnoredFilter workdirTreeIndex ) workdirTreeIndex int ( index .
} { ; AreaRecord field_1_formatFlags) in1 RecordInputStream() (readShort.in1
} { : ) ( GetThumbnailRequest , Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " "
return invoke(new DescribeTransitGatewayVpcAttachmentsRequest(), DescribeTransitGatewayVpcAttachmentsResponse.class, DescribeTransitGatewayVpcAttachmentsRequestMarshaller.getInstance(), DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance());
public PutVoiceConnectorStreamingConfigurationResult putVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) {request = beforeClientExecution(request);return executePutVoiceConnectorStreamingConfiguration(request);}
return prefixToOrdRange.tryGetValue(OrdRange, result) ? result : null;
@Override public String toString() {if (symbol != null && symbol.getStartIndex() >= 0) {return Utils.escapeWhitespace(Antlr4.Runtime.InputStream.getText(Interval.of(symbol.getStartIndex(), symbol.getStopIndex())), false);}return ""; }
return peekFirstImpl();
public CreateWorkspacesResult createWorkspaces(CreateWorkspacesRequest request) {request = beforeClientExecution(request);return executeCreateWorkspaces(request);}
public Object clone() {NumberFormatIndexRecord rec = new NumberFormatIndexRecord();rec.field_1_formatIndex = this.field_1_formatIndex;return rec;}
public DescribeRepositoriesResult describeRepositories(DescribeRepositoriesRequest request) {request = beforeClientExecution(request);return executeDescribeRepositories(request);}
SparseIntArray sparseIntArray = new SparseIntArray(initialCapacity); int[] mKeys = new int[initialCapacity]; int[] mValues = new int[initialCapacity]; int mSize = 0;
return new HyphenatedWordsFilter(input);
return createDistributionWithTags(createDistributionWithTagsRequest);
throw new java.io.RandomAccessFile(fileName, mode);
public DeleteWorkspaceImageResult deleteWorkspaceImage(DeleteWorkspaceImageRequest request) {request = beforeClientExecution(request);return executeDeleteWorkspaceImage(request);}
public static String toHex(long value) { return Integer.toHexString((int) value); }
public UpdateDistributionResult updateDistribution(UpdateDistributionRequest request) {request = beforeClientExecution(request);return executeUpdateDistribution(request);}
public HSSFColor getColor(short index) {if (index == HSSFColor.AUTOMATIC.index) return HSSFColor.AUTOMATIC.getInstance(); HSSFColor b = palette.getColor(index); if (b != null) return b; return null;}
public ValueEval evaluate(ValueEval[] operands, int srcRow, int srcCol) { throw new NotImplementedFunctionException(); }
public void serialize(LittleEndianOutput out1) {out1.writeShort((short) field_1_number_crn_records); out1.writeShort((short) field_2_sheet_table_index);}
return describeDBEngineVersions(new DescribeDBEngineVersionsRequest());
} { ; ; formatRun(fontIndex, character) fontIndex short, character short (_fontIndex._character);
public byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) {byte[] result = new byte[length * 2];int resultIndex = 0;for (int i = 0; i < length; i++) {char ch = chars[offset + i];result[resultIndex++] = (byte) (ch >> 8);result[resultIndex++] = (byte) ch;}return result;}
return invoke(new InvokeOptions().withRequestMarshaller(Instance.UploadArchiveRequestMarshaller).withResponseUnmarshaller(Instance.UploadArchiveResponseUnmarshaller), request, UploadArchiveResponse.class);
public List<IToken> getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex - 1); }
@Override public boolean equals(Object obj) {if (this == obj) return true;if (obj == null || getClass() != obj.getClass()) return false;AutomatonQuery other = (AutomatonQuery) obj;if (!m_term.equals(other.m_term)) return false;if (!m_compiled.equals(other.m_compiled)) return false;return true;}
public SpanQuery makeSpanClause() {List<SpanQuery> spanQueries = new ArrayList<>();for (WeightedSpanQuery wsq : weightBySpanQuery.values()) {spanQueries.add(wsq.getSpanQuery().boost(wsq.getBoost()));}if (spanQueries.size() == 1) {return spanQueries.get(0);}return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0]));}
return new StashCreateCommand();
return byName.get(fieldName);
public DescribeEventSource describeEventSource(DescribeEventSourceRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(DescribeEventSourceRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeEventSourceResponseUnmarshaller.getInstance());return invoke(request, options, DescribeEventSourceResponse.class);}
public GetDocumentAnalysisResult getDocumentAnalysis(GetDocumentAnalysisRequest request) {request = beforeClientExecution(request);return executeGetDocumentAnalysis(request);}
return (CancelUpdateStackResponse) invoke(new InvokeOptions().withRequestMarshaller(Instance.CancelUpdateStackRequestMarshaller).withResponseUnmarshaller(Instance.CancelUpdateStackResponseUnmarshaller), request, CancelUpdateStackResponse.class);
public ModifyLoadBalancerAttributesResponse modifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(ModifyLoadBalancerAttributesRequestMarshaller.getInstance());options.setResponseUnmarshaller(ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance());return invoke(request, options);}
return invoke(new InvokeOptions().withRequestMarshaller(Instance.SetInstanceProtectionRequestMarshaller.getInstance()).withResponseUnmarshaller(Instance.SetInstanceProtectionResponseUnmarshaller.getInstance()), request, SetInstanceProtectionResponse.class);
public ModifyDBProxyResponse modifyDBProxy(ModifyDBProxyRequest request) {request = beforeClientExecution(request);return executeModifyDBProxy(request);}
void add() {if (count == posLengths.length) {int next = ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32);int[] newPosLengths = new int[next];int[] newEndOffsets = new int[next];CharsRef[] newOutputs = new CharsRef[next];System.arraycopy(posLengths, 0, newPosLengths, 0, count);System.arraycopy(endOffsets, 0, newEndOffsets, 0, count);System.arraycopy(outputs, 0, newOutputs, 0, count);posLengths = newPosLengths;endOffsets = newEndOffsets;outputs = newOutputs;}if (posLengths == null) {posLengths = new int[ArrayUtil.oversize(1, RamUsageEstimator.NUM_BYTES_INT32)];}if (endOffsets == null) {endOffsets = new int[ArrayUtil.oversize(1, RamUsageEstimator.NUM_BYTES_INT32)];}if (outputs == null) {outputs = new CharsRef[ArrayUtil.oversize(1, RamUsageEstimator.NUM_BYTES_OBJECT_REF)];}posLengths[count] = posLength;endOffsets[count] = endOffset;outputs[count] = CopyChars.copy(output);count++;}
} { : ) ( FetchLibrariesRequest ; Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " "
return Objects.exists();
} { ; FilterOutputStream out) { out...
} { : ) ( ScaleClusterRequest ; ; Method UriPattern ) , , , , ( PUT . MethodType " " " " " " " " " " " " "
public static DVConstraint createTimeConstraint(int operatorType, String formula1, String formula2) { return DVConstraint.createTimeConstraint(operatorType, formula1, formula2); }
public ListObjectParentPathsResponse listObjectParentPaths(ListObjectParentPathsRequest request) {request = beforeClientExecution(request);return executeListObjectParentPaths(request);}
public DescribeCacheSubnetGroupsResult describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) {request = beforeClientExecution(request);return executeDescribeCacheSubnetGroups(request);}
setSharedFormula(void) { sharedFormula = SetShortBoolean(field_5_options, flag); }
public boolean isReuseObjects() {return reuseObjects;}
public IErrorNode addErrorNode(IToken t) { ErrorNodeImpl errorNode = new ErrorNodeImpl(t); addChild(errorNode); return errorNode; }
if (args.size() > 0) { throw new IllegalArgumentException("Invalid arguments: " + args); } LatvianStemFilterFactory super(args);
RemoveSourceIdentifierFromSubscriptionResponse removeSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) { return (RemoveSourceIdentifierFromSubscriptionResponse) invoke(request, new InvokeOptions(Instance.RemoveSourceIdentifierFromSubscriptionRequestMarshaller.options, Instance.RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.options)); }
return TokenFilterFactory.forName(name, args, loader.newInstance(String.class, String.class));
} { : ) ( AddAlbumPhotosRequest ; Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " "
return invoke(new InvokeOptions().withRequestMarshaller(GetThreatIntelSetRequestMarshaller.getInstance()).withResponseUnmarshaller(GetThreatIntelSetResponseUnmarshaller.getInstance()), request, GetThreatIntelSetResponse.class);
return new Binary.AndTreeFilter(a.clone(), b.clone());
public boolean equals(Object o) { return o instanceof Object; }
protected boolean hasArray() { return protectedHasArray; }
public UpdateContributorInsightsResult updateContributorInsights(UpdateContributorInsightsRequest request) {request = beforeClientExecution(request);return executeUpdateContributorInsights(request);}
UnwriteProtectWorkbook() { records.Remove(writeProtect); records.Remove(fileShare); }
public SolrSynonymParser expand(Analyzer analyzer, boolean dedup) { return new SolrSynonymParser(analyzer, dedup); }
public RequestSpotInstancesResult requestSpotInstances(RequestSpotInstancesRequest request) {request = beforeClientExecution(request);return executeRequestSpotInstances(request);}
return ObjectData.FindObjectRecord(byte[].class);
public GetContactAttributesResult getContactAttributes(GetContactAttributesRequest request) {request = beforeClientExecution(request);return executeGetContactAttributes(request);}
public String toString() { return getKey() + " " + getValue(); }
public ListTextTranslationJobsResult listTextTranslationJobs(ListTextTranslationJobsRequest request) {request = beforeClientExecution(request);return executeListTextTranslationJobs(request);}
return (GetContactMethodsResponse) invoke(new GetContactMethodsRequest(), InvokeOptions.options(new GetContactMethodsRequestMarshaller(), GetContactMethodsResponseUnmarshaller.getInstance()));
short lookupIndexByName(String name) { FunctionMetadata fd = FunctionMetadataIndex.getInstance().getFunctionByNameInternal(name); if (fd == null) { return -1; } return fd; }
return invoke(new DescribeAnomalyDetectorsRequest(), DescribeAnomalyDetectorsResponse.class, DescribeAnomalyDetectorsRequestMarshaller.getInstance(), DescribeAnomalyDetectorsResponseUnmarshaller.getInstance());
insertId(String message, ObjectId changeId) { return insertId; }
if (sz < 0) throw new MissingObjectException(objectId, typeHint); return sz; } long getObjectSize(AnyObjectId objectId, int typeHint) { if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId, ""); return db.getObjectSize(objectId);
public ImportInstallationMediaResult importInstallationMedia(ImportInstallationMediaRequest request) {request = beforeClientExecution(request);return executeImportInstallationMedia(request);}
public PutLifecycleEventHookExecutionStatusResult putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) {request = beforeClientExecution(request);return executePutLifecycleEventHookExecutionStatus(request);}
} { ; NumberPtg field_1_value = in1.readDouble(); } ILittleEndianInput in1;
public GetFieldLevelEncryptionConfigResult getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) {return executeGetFieldLevelEncryptionConfig(beforeClientExecution(request));}
public DescribeDetectorResult describeDetector(DescribeDetectorRequest request) {request = beforeClientExecution(request); return executeDescribeDetector(request);}
return invoke(new ReportInstanceStatusRequest(), new ReportInstanceStatusResponse(), InstanceOptions.ReportInstanceStatusRequestMarshaller, InstanceOptions.ReportInstanceStatusResponseUnmarshaller);
return (DeleteAlarmResponse) invoke(new InvokeOptions().setRequestMarshaller(DeleteAlarmRequestMarshaller.getInstance()).setResponseUnmarshaller(DeleteAlarmResponseUnmarshaller.getInstance()), request, DeleteAlarmResponse.class);
return new PortugueseStemFilter(input);
} { new byte[ENCODED_SIZE]; reserved FtCblsSubRecord; ) (
synchronized (mutex) { return c.remove(object); }
return invoke(new InvokeOptions().withRequestMarshaller(GetDedicatedIpRequestMarshaller.getInstance()).withResponseUnmarshaller(GetDedicatedIpResponseUnmarshaller.getInstance()), request, GetDedicatedIpResponse.class);
return precedence + " ";
return listStreamProcessors(listStreamProcessorsRequest);
public DeleteLoadBalancerPolicyRequest(String policyName, String loadBalancerName) {this.policyName = policyName; this.loadBalancerName = loadBalancerName;}
public WindowProtectRecord(int options) { this._options = options; }
} { ; ; UnbufferedCharStream data = new int[bufferSize]; int n = 0; int bufferSize;
return Instance.invoke(new InvokeOptions(), GetOperationsRequestMarshaller.getInstance(), GetOperationsResponseUnmarshaller.getInstance(), request);
void copyRawTo(byte[] b, int o) { EncodeInt32.NB(b, o, 4 + o, 8 + o, 12 + o, 16 + o); }
WindowOneRecord field_9_tab_width_ratio = in1.readShort(); field_8_num_selected_tabs = in1.readShort(); field_7_first_visible_tab = in1.readShort(); field_6_active_sheet = in1.readShort(); field_5_options = in1.readShort(); field_4_height = in1.readShort(); field_3_width = in1.readShort(); field_2_v_hold = in1.readShort(); field_1_h_hold = in1.readShort();
public StopWorkspacesResult stopWorkspaces(StopWorkspacesRequest request) {request = beforeClientExecution(request);return executeStopWorkspaces(request);}
try { if (isOpen()) { try { dump(); } finally { truncate.channel(); } } } finally { fos.close(); channel.close(); }
public DescribeMatchmakingRuleSetsResult describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) {request = beforeClientExecution(request);return executeDescribeMatchmakingRuleSets(request);}
public String getPronunciation(int wordId, int charOffset, int len) { return null; }
public String getPath() {return pathStr;}
public double devsq(double[] v) {int n = v.length;double m = 0, s = 0;if (n == 1) return Double.NaN;for (int i = 0; i < n; ++i) m += v[i];m /= n;for (int i = 0; i < n; ++i) {double r = v[i] - m;s += r * r;}return s;}
return (DescribeResizeResponse) invoke(new DescribeResizeRequest(), new InvokeOptions(DescribeResizeRequestMarshaller.getInstance(), DescribeResizeResponseUnmarshaller.getInstance()));
return passedThroughNonGreedyDecision;
return end;
void traverse(SimpleCellWalkContext ctx, ICellHandler handler) { for (int rowNumber = ctx.firstRow; rowNumber <= ctx.lastRow; ++rowNumber) { IRow currentRow = ctx.sheet.getRow(rowNumber); if (currentRow == null) { continue; } for (int colNumber = ctx.firstColumn; colNumber <= ctx.lastColumn; ++colNumber) { ICell currentCell = currentRow.getCell(colNumber); if (currentCell == null || ctx.traverseEmptyCells || !ctx.isEmpty(currentCell)) { int ordinalNumber = (rowNumber - ctx.firstRow) * (ctx.lastColumn - ctx.firstColumn + 1) + (colNumber - ctx.firstColumn); handler.onCell(ctx, currentCell, ordinalNumber); } } } }
return _ReadIndex;
public int compareTo(ScoreTerm other) { if (this.Boost == other.Boost) { if (this.Term.equals(other.Term)) { return 0; } return this.Term.compareTo(other.Term); } return Float.compare(this.Boost, other.Boost); }
for (int i = 0; i < len; ++i) { switch (s[i]) { case HAMZA_ABOVE: case HEH_GOAL: case HEH_YEH: case KEHEH: case YEH_BARREE: case FARSI_YEH: s[i] = HEH; s[len--] = KAF; s[len--] = YEH; break; default: break; } } return len;
void serialize(LittleEndianOutput out1) { out1.writeShort(); }
} { ; DiagnosticErrorListener exactOnly ) exactOnly boolean ( exactOnly .
public KeySchemaElement(String attributeName, KeyType keyType) {this._attributeName = attributeName; this._keyType = keyType;}
public GetAssignmentResult getAssignment(GetAssignmentRequest request) {request = beforeClientExecution(request);return executeGetAssignment(request);}
return (findOffset(1 - anyObjectId) != null);
return setAllGroups(groupingSearch, allGroups);
void setMultiValued(String dimName, boolean v) { synchronized (this) { DimConfig fieldType; if (fieldTypes.TryGetValue(dimName, out fieldType)) { fieldType.isMultiValued = v; } } }
int getCellsVal() {int size = 0;for (char c : keys) {if (cmd.at(size) >= 0) {size++;}}return size;}
return (DeleteVoiceConnectorResponse) invoke(new DeleteVoiceConnectorRequest(), new InvokeOptions().withRequestMarshaller(DeleteVoiceConnectorRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteVoiceConnectorResponseUnmarshaller.getInstance()));
public DeleteLifecyclePolicyResult deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) {request = beforeClientExecution(request);return executeDeleteLifecyclePolicy(request);}
