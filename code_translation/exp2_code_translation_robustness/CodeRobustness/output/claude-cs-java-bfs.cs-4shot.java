out1.writeShort();
public void addAll(BlockList<T> src) { if (src.size == 0) return; int srcDirIdx = src.tailDirIdx; for (; srcDirIdx != 0; srcDirIdx++) { if (src.directory[srcDirIdx] != null) { addAll(src.directory[srcDirIdx], src.tailBlkIdx); } } }
public void writeByte(byte b) { if (outerInstance.upto == outerInstance.blockSize) { if (outerInstance.currentBlock != null) { outerInstance.blocks.add(outerInstance.currentBlock); outerInstance.blockEnd += outerInstance.blockSize; } outerInstance.currentBlock = new byte[outerInstance.blockSize]; outerInstance.upto = 0; } outerInstance.currentBlock[outerInstance.upto++] = b; }
return objectId;
public DeleteDomainEntryResult deleteDomainEntry(DeleteDomainEntryRequest request) { request = beforeClientExecution(request); return executeDeleteDomainEntry(request); }
return fst == null ? 0 : fst.ramBytesUsed();
return RawParseUtils.decode(RawParseUtils.parseEncoding(raw), raw, 0, raw.length);
} { ) ( POIFSFileSystem ; ; ; ; null _root _documents _property_table HeaderBlock ArrayList new PropertyTable new headerBlock ) ( ) ( = HeaderBlock new ) (
Init void } { ) ( ; ; ; ; ; address int address offset0 upto slice ) ( assert . debug & address ) ( assert . debug < upto BYTE_BLOCK_MASK . ByteBlockPool null != slice ] [ buffers . pool length . slice >> address BYTE_BLOCK_SHIFT . ByteBlockPool
return this.setPath(path);
public ListIngestionsResult listIngestions(ListIngestionsRequest request) {request = beforeClientExecution(request);return executeListIngestions(request);}
public QueryParserTokenManager(ICharStream stream, int lexState) { this.stream = stream; }
public GetShardIteratorResult getShardIterator(GetShardIteratorRequest request) {request = beforeClientExecution(request);return executeGetShardIterator(request);}
} { : ) ( ModifyStrategyRequest ; Method ) , , , , ( POST . MethodType " " " " " " " " " "
synchronized(lock) { try { if(in == null) return; if(bytes.hasRemaining() && in.available() > 0) return; } catch(IOException e) { throw new IOException(); } }
public EscherOptRecord getOptRecord() { return _optRecord; }
public int read(byte[] buffer, int offset, int length) { if (buffer == null) throw new NullPointerException(); if (offset < 0 || length < 0 || offset + length > buffer.length) throw new IndexOutOfBoundsException(); if (length == 0) return 0; synchronized (lock) { int copylen = Math.min(length, count - pos); if (copylen <= 0) return -1; System.arraycopy(buf, pos, buffer, offset, copylen); pos += copylen; return copylen; } }
} {  ; OpenNLPSentenceBreakIterator sentenceOp ) sentenceOp NLPSentenceDetectorOp ( sentenceOp .
Sharpen.StringHelper.getValueOf(object).write(str != null ? str : null);
public NotImplementedFunctionException(String functionName, Throwable cause) { super(functionName, cause); }
return value.nextEntry().next();
public void readBytes(byte[] b, int offset, int len) {if (len < 0) throw new IllegalArgumentException();if (len == 0) return;boolean useBuffer;int available = bufferLength - bufferPosition;if (available >= len) {useBuffer = true;} else {useBuffer = false;}if (useBuffer) {System.arraycopy(buffer, bufferPosition, b, offset, len);bufferPosition += len;} else {long after = bufferStart + bufferPosition + len;if (after > length()) throw new EndOfStreamException();readInternal(b, offset, len);bufferStart = after;bufferPosition = 0;bufferLength = 0;}}
return invoke(new InvokeOptions(options, TagQueueRequestMarshaller.getInstance(), TagQueueResponseUnmarshaller.getInstance()), request);
Remove void } { ) ( ; throw new NotSupportedException ) (
public ModifyCacheSubnetGroupResult modifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request) {request = beforeClientExecution(request);return executeModifyCacheSubnetGroup(request);}
setParams void } { ) ( ) ( if ) ( if ) ( if ; ; ; ; params String ; ; ; StringTokenizer String culture ignore ) ( moveNext . st culture ) ( moveNext . st culture ) ( moveNext . st st " " ) ( setParams . current . st + current . st = current . st " " StringTokenizer new ) , ( " "
public DeleteDocumentationVersionResult deleteDocumentationVersion(DeleteDocumentationVersionRequest request) { request = beforeClientExecution(request); return executeDeleteDocumentationVersion(request); }
public boolean equals(Object obj) { if (!(obj instanceof FacetLabel)) return false; FacetLabel other = (FacetLabel) obj; if (other.length != length) return false; for (int i = length - 1; i >= 0; i--) { if (!components[i].equals(other.components[i])) return false; } return true; }
public GetInstanceAccessDetailsResult getInstanceAccessDetails(GetInstanceAccessDetailsRequest request) {request = beforeClientExecution(request);return executeGetInstanceAccessDetails(request);}
public HSSFPolygon createPolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(null, anchor); shape.setParent(this); shape.setAnchor(anchor); shapes.add(shape); onCreate(shape); return shape; }
return workbook.getSheetName(sheetIndex);
public GetDashboardResult getDashboard(GetDashboardRequest request) { request = beforeClientExecution(request); return executeGetDashboard(request); }
return invoke(new InvokeOptions().requestMarshaller(AssociateSigninDelegateGroupsWithAccountRequestMarshaller.getInstance()).responseUnmarshaller(AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.getInstance()), request);
for (int j = 0; j < mbr.getNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.setColumn(mbr.getFirstColumn() + j); br.setRow(mbr.getRow()); br.setXFIndex(mbr.getXFAt(j)); insertCell(br); }
quote string } { ) ( ; return ) ( while ; ; ; ; string string } { 0 >= int int ) ( toString . ; ; ) ( apos ) ( append . sb sb StringBuilder . apos k 0 = " \\ " = lang . java ) ( append . 2 + k ) ( append . new " \\ " " \\ \\ \\ " ) , ( indexOf . string ) ( StringBuilder . ) ( append . sb ) ( append . sb " \\ " lang . java ) , ( substring . ) , , ( substring . StringHelper . Sharpen 2 + k StringHelper . Sharpen
java.nio.ByteBuffer.putInt(int value) { throw new java.nio.ReadOnlyBufferException(); }
public ArrayPtg(Object[][] values2d) { int nRows = values2d.length; int nColumns = (nRows > 0) ? values2d[0].length : 0; _nRows = (short) nRows; _nColumns = (short) nColumns; _arrayValues = new Object[_nRows * _nColumns]; if (nColumns > 0) { for (int r = 0; r < nRows; r++) { Object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { _arrayValues[getValueIndex(c, r)] = rowData[c]; } } } _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
public GetIceServerConfigResult getIceServerConfig(GetIceServerConfigRequest request) {request = beforeClientExecution(request);return executeGetIceServerConfig(request);}
public String toString() { StringBuilder sb = new StringBuilder(); sb.append(getClass().getName()); sb.append(" "); sb.append(getValueAsString()); return sb.toString(); }
public String toString() { return field + " " + _parentQuery + " "; }
refCount.incrementAndGet();
public UpdateConfigurationSetSendingEnabledResult updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) {request = beforeClientExecution(request);return executeUpdateConfigurationSetSendingEnabled(request);}
return LittleEndianConsts.INT_SIZE * getXBATEntriesPerBlock();
void multiplyByPowerOfTen(int pow10) { if (pow10 < 0) { TenPower tp = TenPower.getInstance(Math.abs(pow10)); mulShift = tp.multiplicand; mulShift = tp.multiplierShift; tp.divisor = tp.divisorShift; } else { } }
public String toString() { StringBuilder builder = new StringBuilder(); for (int i = 0; i < length; i++) { if (i > 0) { builder.append(File.separatorChar); } builder.append(getComponent(i)); } return builder.toString(); }
withFetcher void } { ) ( ; ; fetcher ECSMetadataServiceCredentialsFetcher fetcher ) ( setRoleName . fetcher . fetcher .
void setProgressMonitor(ProgressMonitor pm) { progressMonitor = pm; }
if (!eof) { ptr = 0; } if (!first()) { } if (parseEntry()) { } void reset() { }
if (previousIndex() >= start) { throw new java.util.NoSuchElementException(); } return iterator.previous();
return newPrefix;
public int indexOfValue(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1; }
public List<CharsRef> uniqueStems(List<CharsRef> stems) { CharArraySet terms = new CharArraySet(8, false); List<CharsRef> deduped = new ArrayList<>(); for (CharsRef s : stems) { if (s.length < 2) { continue; } if (!terms.contains(s)) { terms.add(s); deduped.add(s); } } return deduped; }
return invoke(new InvokeOptions().requestMarshaller(GetGatewayResponsesRequestMarshaller.getInstance()).responseUnmarshaller(GetGatewayResponsesResponseUnmarshaller.getInstance()), request);
void setPosition(long position) { currentBlockUpto = (int) (position >> outerInstance.blockBits); currentBlock = blocks[(int) (position & outerInstance.blockMask)]; currentBlockIndex = (int) (position & outerInstance.blockMask); }
Skip long } { ) ( ; s return ; ; n long s ptr int s = ) int ( ) , ( min . Math available ) , ( max . Math ) (
} {  ; BootstrapActionDetail bootstrapActionConfig _bootstrapActionConfig ) bootstrapActionConfig BootstrapActionConfig (
public void serialize(LittleEndianOutput out1) { out1.writeShort(field_1_author_len); out1.writeShort(field_2_key_len); out1.writeShort(field_3_comment_len); out1.writeShort(field_4_title_len); out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00); StringUtil.putCompressedUnicode(field_6_author, out1); if (field_7_padding != null) { out1.write(field_7_padding); } }
return string.lastIndexOf(int);
add boolean } { ) ( ; return object E addLastImpl ) (
UnsetSection void } { ) , ( ; ) ( while do ; ; subsection String section String ! } { ConfigSnapshot ConfigSnapshot ; ; ) , ( compareAndSet . state res src UnsetSection ) , , ( ) ( get . state
public String getTagName() { return tagName; }
subrecords.add(index, element);
remove boolean } { ) ( ) mutex ( synchronized object object } { ; return ) ( remove . c
return new DoubleMetaphoneFilter(input);
return length();
setValue(boolean newValue) { value = newValue; }
} {  ; ; Pair newSource oldSource ) newSource ContentSource , oldSource ContentSource ( newSource . oldSource .
public int get(int i) { if (i >= count) throw new IndexOutOfBoundsException(); return entries[i]; }
} { : ) ( CreateRepoRequest ; ; Method UriPattern ) , , , , ( PUT . MethodType " " " " " " " " " " " "
return deltaBaseAsOffset;
public void remove() { if (lastLink == null) throw new IllegalStateException(); if (list.modCount != expectedModCount) throw new ConcurrentModificationException(); if (lastLink.next != null) lastLink.next.previous = lastLink.previous; if (lastLink.previous != null) lastLink.previous.next = lastLink.next; else list.first = lastLink.next; list.size--; list.modCount++; expectedModCount++; if (pos > 0) pos--; lastLink = null; }
return invoke(new InvokeOptions().withRequestMarshaller(MergeShardsRequestMarshaller.getInstance()).withResponseUnmarshaller(MergeShardsResponseUnmarshaller.getInstance()), request, MergeShardsResponse.class);
public AllocateHostedConnectionResult allocateHostedConnection(AllocateHostedConnectionRequest request) {request = beforeClientExecution(request);return executeAllocateHostedConnection(request);}
return start;
GetTerms } { ) ( WeightedTerm ; return query Query ] [ GetTerms ) , (
throw new java.nio.ReadOnlyBufferException();
public void decode(int[] values, int valuesOffset, int[] blocks, int blocksOffset, int iterations) { for (int i = 0; i < iterations; ++i) { final int byte0 = blocks[blocksOffset++] & 0xFF; final int byte1 = blocks[blocksOffset++] & 0xFF; final int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (byte0 >>> 6) | ((byte1 & 15) << 2); values[valuesOffset++] = ((byte1 >>> 4) & 3) | ((byte2 & 63) << 2); } }
public static String getHumanishName(String s) { if (s == null) throw new IllegalArgumentException(); String[] elements = s.split(File.separator); if (elements.length == 0) throw new IllegalArgumentException(); String result = elements[elements.length - 1]; if (result.equals("") || result.equals(".") || result.equals("..")) { if (elements.length > 1) result = elements[elements.length - 2]; else throw new IllegalArgumentException(); } if (result.endsWith(Constants.DOT_GIT_EXT)) result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); if (result.equals(Constants.DOT_GIT)) throw new IllegalArgumentException(); if (result.equals("")) throw new IllegalArgumentException(); return result; }
return invoke(request, DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance(), DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance(), new InvokeOptions(options));
public String getAccessKeySecret() { return accessSecret; }
public CreateVpnConnectionResult createVpnConnection(CreateVpnConnectionRequest request) {request = beforeClientExecution(request);return executeCreateVpnConnection(request);}
public DescribeVoicesResponse describeVoices(DescribeVoicesRequest request) {InvokeOptions options = new InvokeOptions();options.RequestMarshaller = DescribeVoicesRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeVoicesResponseUnmarshaller.Instance;return invoke(request, options);}
public ListMonitoringExecutionsResult listMonitoringExecutions(ListMonitoringExecutionsRequest request) {request = beforeClientExecution(request);return executeListMonitoringExecutions(request);}
public DescribeJobRequest(String jobId, String vaultName) { setJobId(jobId); setVaultName(vaultName); }
return escherRecords[index];
return invoke(new InvokeOptions(GetApisRequestMarshaller.getInstance(), GetApisResponseUnmarshaller.getInstance()), request);
public DeleteSmsChannelResponse deleteSmsChannel(DeleteSmsChannelRequest request) {var options = new InvokeOptions();options.setRequestMarshaller(DeleteSmsChannelRequestMarshaller.getInstance());options.setResponseUnmarshaller(DeleteSmsChannelResponseUnmarshaller.getInstance());return invoke(request, options);}
return trackingRefUpdate;
System.out.println(b.toString());
return getChildren()[getChild(IQueryNode)];
} {  ; NotIgnoredFilter workdirTreeIndex ) workdirTreeIndex int ( index .
} { ; AreaRecord field_1_formatFlags ) in1 RecordInputStream ( ) ( readShort . in1
} { : ) ( GetThumbnailRequest ; Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " "
public DescribeTransitGatewayVpcAttachmentsResult describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) {request = beforeClientExecution(request);return executeDescribeTransitGatewayVpcAttachments(request);}
public PutVoiceConnectorStreamingConfigurationResult putVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) { request = beforeClientExecution(request); return executePutVoiceConnectorStreamingConfiguration(request); }
return prefixToOrdRange.get(dim);
public String toString() { if (symbol == null) return "[@" + getTokenIndex() + ",<no text>,<" + tokenNames[getType()] + ">,channel=" + getChannel() + ",<" + startIndex + ":" + stopIndex + ">]"; String txt = getText(); if (txt != null) { txt = txt.replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t"); } else { txt = "<no text>"; } return "[@" + getTokenIndex() + ",'" + txt + "',<" + tokenNames[getType()] + ">,channel=" + getChannel() + ",<" + startIndex + ":" + stopIndex + ">]"; }
return peekFirstImpl();
public CreateWorkspacesResult createWorkspaces(CreateWorkspacesRequest request) { request = beforeClientExecution(request); return executeCreateWorkspaces(request); }
NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = field_1_formatIndex; return rec;
public DescribeRepositoriesResult describeRepositories(DescribeRepositoriesRequest request) {request = beforeClientExecution(request);return executeDescribeRepositories(request);}
SparseIntArray(int initialCapacity) { mKeys = new int[ArrayUtils.idealIntArraySize(initialCapacity)]; mValues = new int[ArrayUtils.idealIntArraySize(initialCapacity)]; mSize = 0; }
return new HyphenatedWordsFilter(input);
public CreateDistributionWithTagsResult createDistributionWithTags(CreateDistributionWithTagsRequest request) {request = beforeClientExecution(request);return executeCreateDistributionWithTags(request);}
throw new java.io.FileNotFoundException();
public DeleteWorkspaceImageResult deleteWorkspaceImage(DeleteWorkspaceImageRequest request) {request = beforeClientExecution(request);return executeDeleteWorkspaceImage(request);}
public static String toHex(long value) { return Long.toHexString(value); }
return invoke(new InvokeOptions(UpdateDistributionRequestMarshaller.getInstance(), UpdateDistributionResponseUnmarshaller.getInstance(), options), request);
public Color getColor(short index) { if (index == HSSFColor.AUTOMATIC.getIndex()) { return HSSFColor.AUTOMATIC.getInstance(); } else { byte b = palette.getColor(index); if (b != null) { return new HSSFColor.CustomColor(b); } else { return null; } } }
throw new NotImplementedFunctionException(); ValueEval evaluate(ValueEval[] operands, int srcRow, int srcCol) {
public void serialize(LittleEndianOutput out1) { out1.writeShort((short)field_1_number_crn_records); out1.writeShort((short)field_2_sheet_table_index); }
return describeDBEngineVersions(new DescribeDBEngineVersionsRequest());
} { ; ; FormatRun fontIndex character ) fontIndex short , character short ( _fontIndex . _character .
public static byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int resultIndex = 0; for (int i = 0; i < length; i++) { char ch = chars[offset + i]; result[resultIndex++] = (byte) (ch >> 8); result[resultIndex++] = (byte) ch; } return result; }
public UploadArchiveResult uploadArchive(UploadArchiveRequest request) {request = beforeClientExecution(request);return executeUploadArchive(request);}
public List<Token> getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex, -1); }
public boolean equals(Object obj) { if (obj == null) return false; if (!(obj instanceof AutomatonQuery)) return false; AutomatonQuery other = (AutomatonQuery) obj; if (!getClass().equals(obj.getClass())) return false; if (!m_term.equals(other.m_term)) return false; if (m_compiled != null ? !m_compiled.equals(other.m_compiled) : other.m_compiled != null) return false; return true; }
return spanQueries.size() == 1 ? spanQueries.get(0) : new SpanOrQuery(spanQueries.toArray(new SpanQuery[0]));
return new StashCreateCommand();
return byName.get(fieldName);
public DescribeEventSourceResult describeEventSource(DescribeEventSourceRequest request) {request = beforeClientExecution(request);return executeDescribeEventSource(request);}
public GetDocumentAnalysisResult getDocumentAnalysis(GetDocumentAnalysisRequest request) {request = beforeClientExecution(request);return executeGetDocumentAnalysis(request);}
public CancelUpdateStackResult cancelUpdateStack(CancelUpdateStackRequest request) { request = beforeClientExecution(request); return executeCancelUpdateStack(request); }
return invoke(new InvokeOptions().withRequestMarshaller(ModifyLoadBalancerAttributesRequestMarshaller.getInstance()).withResponseUnmarshaller(ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance()), request);
public SetInstanceProtectionResult setInstanceProtection(SetInstanceProtectionRequest request) {request = beforeClientExecution(request);return executeSetInstanceProtection(request);}
public ModifyDBProxyResult modifyDBProxy(ModifyDBProxyRequest request) {request = beforeClientExecution(request);return executeModifyDBProxy(request);}
public void add(CharsRef output, int offset, int len, int endOffset, int posLength) { if (count == outputs.length) { CharsRef[] newOutputs = new CharsRef[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF)]; System.arraycopy(outputs, 0, newOutputs, 0, count); outputs = newOutputs; int[] newEndOffsets = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(endOffsets, 0, newEndOffsets, 0, count); endOffsets = newEndOffsets; int[] newPosLengths = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(posLengths, 0, newPosLengths, 0, count); posLengths = newPosLengths; } outputs[count] = new CharsRef(); outputs[count].copyChars(output.chars, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
} { : ) ( FetchLibrariesRequest ; Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " "
return objects.exists();
} { ; FilterOutputStream out ) java ( out . . .
} { : ) ( ScaleClusterRequest ; ; Method UriPattern ) , , , , ( PUT . MethodType " " " " " " " " " " " "
return DVConstraint.createTimeConstraint(operatorType, formula1, formula2);
public ListObjectParentPathsResult listObjectParentPaths(ListObjectParentPathsRequest request) { request = beforeClientExecution(request); return executeListObjectParentPaths(request); }
public DescribeCacheSubnetGroupsResult describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) { request = beforeClientExecution(request); return executeDescribeCacheSubnetGroups(request); }
setSharedFormula(sharedFormula.setShortBoolean(field_5_options, flag));
return reuseObjects;
public ErrorNode addErrorNode(Token t) { ErrorNodeImpl errorNode = new ErrorNodeImpl(t); errorNode.parent = this; addChild(errorNode); return errorNode; }
} {  ) ( if LatvianStemFilterFactory } { 0 > ) args ( super ; throw size . args ) String , Map ( new > String < ) ( IllegalArgumentException args + " "
public RemoveSourceIdentifierFromSubscriptionResult removeSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) {request = beforeClientExecution(request);return executeRemoveSourceIdentifierFromSubscription(request);}
return TokenFilterFactory.forName(name, args).newInstance(loader);
} { : ) ( AddAlbumPhotosRequest ; Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " "
public GetThreatIntelSetResult getThreatIntelSet(GetThreatIntelSetRequest request) {request = beforeClientExecution(request);return executeGetThreatIntelSet(request);}
return new AndTreeFilter(a.clone(), b.clone());
return o instanceof Object;
boolean hasArray() { return protectedHasArray(); }
public UpdateContributorInsightsResult updateContributorInsights(UpdateContributorInsightsRequest request) {request = beforeClientExecution(request);return executeUpdateContributorInsights(request);}
unwriteProtectWorkbook void } { ) ( ; ; ; ; null writeProtect null fileShare ) ( remove . records ) ( remove . records
} { ; SolrSynonymParser expand ) analyzer , dedup ( base : expand . ) analyzer Analyzer , expand boolean , dedup boolean (
public RequestSpotInstancesResult requestSpotInstances(RequestSpotInstancesRequest request) {request = beforeClientExecution(request);return executeRequestSpotInstances(request);}
return ObjectData.FindObjectRecord(byte)[].getData();
public GetContactAttributesResult getContactAttributes(GetContactAttributesRequest request) {request = beforeClientExecution(request);return executeGetContactAttributes(request);}
public String toString() { return getKey() + " " + getValue(); }
public ListTextTranslationJobsResult listTextTranslationJobs(ListTextTranslationJobsRequest request) {request = beforeClientExecution(request);return executeListTextTranslationJobs(request);}
public GetContactMethodsResult getContactMethods(GetContactMethodsRequest request) { request = beforeClientExecution(request); return executeGetContactMethods(request); }
short lookupIndexByName(String name) { if (name == null) return -1; FunctionMetadata fd = FunctionMetadata.getInstance().getFunctionByNameInternal(name); return fd.getIndex(); }
public DescribeAnomalyDetectorsResult describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) {request = beforeClientExecution(request);return executeDescribeAnomalyDetectors(request);}
InsertId string } { ) , ( ; return changeId ObjectId message string InsertId ) , , (
long getObjectSize(AnyObjectId objectId, int typeHint) throws MissingObjectException { long sz = db.getObjectSize(objectId.copy(), typeHint); if (sz < 0) { throw new MissingObjectException(objectId.copy(), ""); } if (typeHint == OBJ_ANY) { return sz; } throw new MissingObjectException(objectId.copy(), typeHint); }
public ImportInstallationMediaResult importInstallationMedia(ImportInstallationMediaRequest request) {request = beforeClientExecution(request);return executeImportInstallationMedia(request);}
public PutLifecycleEventHookExecutionStatusResult putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) {request = beforeClientExecution(request);return executePutLifecycleEventHookExecutionStatus(request);}
field_1_value = in1.readDouble();
public GetFieldLevelEncryptionConfigResult getFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request) {request = beforeClientExecution(request);return executeGetFieldLevelEncryptionConfig(request);}
return invoke(request, DescribeDetectorRequestMarshaller.getInstance(), DescribeDetectorResponseUnmarshaller.getInstance(), new InvokeOptions(options));
return invoke(new InvokeOptions(options, ReportInstanceStatusRequestMarshaller.getInstance(), ReportInstanceStatusResponseUnmarshaller.getInstance()), request);
public DeleteAlarmResult deleteAlarm(DeleteAlarmRequest request) {request = beforeClientExecution(request);return executeDeleteAlarm(request);}
return new PortugueseStemFilter(input);
} { ) ( FtCblsSubRecord ; reserved new byte ] ENCODED_SIZE [
synchronized(object) { return c.remove(); }
public GetDedicatedIpResult getDedicatedIp(GetDedicatedIpRequest request) {request = beforeClientExecution(request);return executeGetDedicatedIp(request);}
return precedence + " " + ToString();
public ListStreamProcessorsResponse listStreamProcessors(ListStreamProcessorsRequest request) {InvokeOptions options = new InvokeOptions();options.RequestMarshaller = ListStreamProcessorsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListStreamProcessorsResponseUnmarshaller.Instance;return Invoke<ListStreamProcessorsResponse>(request, options);}
public DeleteLoadBalancerPolicyRequest(String policyName, String loadBalancerName) { this.policyName = policyName; this.loadBalancerName = loadBalancerName; }
} {  ; WindowProtectRecord options _options ) options int (
} { ; ; UnbufferedCharStream data 0 n ) bufferSize int ( new int ] bufferSize [
return invoke(new InvokeOptions(RequestMarshaller.getInstance().getOperationsRequestMarshaller(options), ResponseUnmarshaller.getInstance().getOperationsResponseUnmarshaller(options)), request).getOperationsResponse();
NB.encodeInt32(o, NB.encodeInt32(o + 4, NB.encodeInt32(o + 8, NB.encodeInt32(o + 12, NB.encodeInt32(o + 16, b, int)))));
} {  ; ; ; ; ; ; ; ; ; WindowOneRecord field_9_tab_width_ratio field_8_num_selected_tabs field_7_first_visible_tab field_6_active_sheet field_5_options field_4_height field_3_width field_2_v_hold field_1_h_hold ) in1 RecordInputStream ( ) ( readShort . in1 ) ( readShort . in1 ) ( readShort . in1 ) ( readShort . in1 ) ( readShort . in1 ) ( readShort . in1 ) ( readShort . in1 ) ( readShort . in1 ) ( readShort . in1
public StopWorkspacesResult stopWorkspaces(StopWorkspacesRequest request) {request = beforeClientExecution(request);return executeStopWorkspaces(request);}
throws IOException close void } { ) ( ) isOpen ( if } { try ; finally } { isOpen } { ; try dump finally } { ) ( } { ; try finally } { ) ( truncate . channel } { ; ; ) ( close . channel ) ( close . fos
public DescribeMatchmakingRuleSetsResult describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) {request = beforeClientExecution(request);return executeDescribeMatchmakingRuleSets(request);}
char[] surface, int wordId, int off, int len) { return null; }
return pathStr;
public static double devsq(double[] v) { if (v == null || v.length <= 1) return Double.NaN; double s = 0; int n = v.length; for (int i = 0; i < n; i++) { s += v[i]; } double m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } return s; }
public DescribeResizeResult describeResize(DescribeResizeRequest request) {request = beforeClientExecution(request);return executeDescribeResize(request);}
return passedThroughNonGreedyDecision;
end int } { ) ( ; return end ) (
public void traverse(ICellHandler handler, CellRangeAddress range, ISheet sheet) { SimpleCellWalkContext ctx = new SimpleCellWalkContext(); int firstRow = range.getFirstRow(); int lastRow = range.getLastRow(); int firstColumn = range.getFirstColumn(); int lastColumn = range.getLastColumn(); int width = lastColumn - firstColumn + 1; ctx.rowNumber = 0; ctx.colNumber = 0; ctx.ordinalNumber = 0; for (int rowNumber = firstRow; rowNumber <= lastRow; rowNumber++) { IRow currentRow = sheet.getRow(rowNumber); ctx.rowNumber = rowNumber - firstRow; if (currentRow == null) { currentRow = null; for (int colNumber = firstColumn; colNumber <= lastColumn; colNumber++) { ICell currentCell = null; ctx.colNumber = colNumber - firstColumn; if (currentCell == null) { if (!traverseEmptyCells) continue; } handler.onCell(currentCell, ctx); ctx.ordinalNumber++; } } else { for (int colNumber = firstColumn; colNumber <= lastColumn; colNumber++) { ICell currentCell = currentRow.getCell(colNumber); ctx.colNumber = colNumber - firstColumn; if (currentCell == null) { if (!traverseEmptyCells) continue; } if (currentCell != null && currentCell.isEmpty()) continue; handler.onCell(currentCell, ctx); ctx.ordinalNumber++; } } } }
public int getReadIndex() { return _ReadIndex; }
public int compareTo(ScoreTerm other) { if (Term.compareTo(other.Term) == 0) { return other.Boost.compareTo(Boost); } else { return Term.bytesEquals(other.Term) ? 0 : Term.compareTo(other.Term); } }
public int normalize(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case HAMZA_ABOVE: break; case HEH_GOAL: case HEH_YEH: break; case KEHEH: break; case YEH_BARREE: case FARSI_YEH: s[i] = YEH; break; default: break; } } len = StemmerUtil.delete(s, len, HEH); len = StemmerUtil.delete(s, len, KAF); return len; }
out1.writeShort();
} {  ; DiagnosticErrorListener exactOnly ) exactOnly boolean ( exactOnly .
} { ; ; KeySchemaElement keyType _keyType attributeName _attributeName ) keyType KeyType , attributeName String (
return invoke(new InvokeOptions(options, RequestMarshaller.getInstance().GetAssignmentRequestMarshaller, ResponseUnmarshaller.getInstance().GetAssignmentResponseUnmarshaller), request, GetAssignmentRequest, GetAssignmentResponse).GetAssignmentResponse();
public boolean hasObject(AnyObjectId id) { return findOffset(id) != -1; }
public GroupingSearch setAllGroups(boolean allGroups) {this.allGroups = allGroups;return this;}
setMultiValued void } { ) , ( ) ( lock v boolean dimName String } { else ) ( if } { } { ! ; ; v ) , ( tryGetValue . fieldTypes isMultiValued . fieldType DimConfig new fieldTypes out } { ] [ fieldType DimConfig v isMultiValued
int getCellsVal() { return size; } for (char c : in) { if (cells.size() >= 0) { Cell e = cmd.At(size++); } }
return invoke(new InvokeOptions().withRequestMarshaller(DeleteVoiceConnectorRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteVoiceConnectorResponseUnmarshaller.getInstance()), request);
public DeleteLifecyclePolicyResult deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) {request = beforeClientExecution(request);return executeDeleteLifecyclePolicy(request);}
