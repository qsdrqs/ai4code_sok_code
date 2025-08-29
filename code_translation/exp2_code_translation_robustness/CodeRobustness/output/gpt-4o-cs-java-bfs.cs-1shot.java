void serialize(ILittleEndianOutput out1) { out1.writeShort(); }
addAll void } { ) ( ) ( if ) ; ; ( for ; ) ( if src } { 0 != } { ++ srcDirIdx < srcDirIdx int } { 0 == . ; tailBlkIdx . src ; tailDirIdx . src srcDirIdx ; return size . src BlockList Util . NGit addAll addAll 0 = > T < ) , , ( ) , , ( tailBlkIdx . src tailBlock . src ] [ directory . src
void writeByte(byte b) { if (b == 0) { if (outerInstance.upto == outerInstance.blockSize) { outerInstance.currentBlock = new byte[outerInstance.blockSize]; outerInstance.blocks.add(outerInstance.currentBlock); outerInstance.blockEnd.add(outerInstance.upto); outerInstance.upto = 0; } outerInstance.currentBlock[outerInstance.upto++] = b; } }
public ObjectId getObjectId() { return objectId; }
public DeleteDomainEntryResult deleteDomainEntry(DeleteDomainEntryRequest request) {request = beforeClientExecution(request);return executeDeleteDomainEntry(request);}
return fst == null ? 0L : fst.getSizeInBytes();
public String getFullMessage() throws IOException { byte[] raw = RawParseUtils.decode(buffer, RawParseUtils.tagMessage(buffer)); if (raw.length == 0) return ""; return new String(raw, RawParseUtils.parseEncoding(buffer)); }
POIFSFileSystem _root = null; ArrayList _documents = new ArrayList(); PropertyTable _property_table = new PropertyTable(new HeaderBlock()); HeaderBlock headerBlock = new HeaderBlock();
void init() { assert address < ByteBlockPool.BYTE_BLOCK_MASK && address >= 0; assert slice != null; int offset0 = address & ByteBlockPool.BYTE_BLOCK_MASK; int upto = slice.length >> ByteBlockPool.BYTE_BLOCK_SHIFT; }
return SubmoduleAddCommand.setPath(path).call();
return (ListIngestionsResponse) invoke(new InvokeOptions().setRequestMarshaller(Instance.ListIngestionsRequestMarshaller).setResponseUnmarshaller(Instance.ListIngestionsResponseUnmarshaller), request);
SwitchTo(QueryParserTokenManager)({this:)(stream())lexState,int,stream,ICharStream(;
return (GetShardIteratorResponse) invoke(new InvokeOptions().setRequestMarshaller(Instance.GetShardIteratorRequestMarshaller).setResponseUnmarshaller(Instance.GetShardIteratorResponseUnmarshaller), request, GetShardIteratorResponse.class);
} { : ) ( ModifyStrategyRequest ; Method ) , , , , ( POST . MethodType " " " " " " " " " "
public final boolean ready() { synchronized (lock) { try { if (in == null) { throw new IOException(); } return in.available() > 0 || System.in.hasRemaining(); } catch (IOException e) { return false; } } }
return _optRecord; } EscherOptRecord getOptRecord() {
public int read(byte[] buffer, int offset, int length) {if (buffer == null) throw new NullPointerException("buffer");if (length == 0) return 0;int copylen = Math.min(length, this.length - pos);for (int i = 0; i < copylen; ++i) buffer[offset + i] = this.buffer[pos + i];pos += copylen;return copylen;}
} { ; OpenNLPSentenceBreakIterator sentenceOp ) sentenceOp NLPSentenceDetectorOp ( sentenceOp .
if (str != null) StringHelper.Sharpen(str); System.out.print(str);
throw new UnsupportedOperationException(functionName, cause);
return nextEntry.next().value();
public void readBytes(byte[] b, int offset, int len) throws IOException {if (len <= 0) {return;} if (useBuffer) {int available = bufferLength - bufferPosition; if (available > 0) {if (available >= len) {System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; return;} System.arraycopy(buffer, bufferPosition, b, offset, available); bufferPosition += available; offset += available; len -= available;} if (len > bufferSize) {long after = bufferStart + bufferPosition + len; if (after > Length) {throw new EOFException();} System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; return;} RefillBuffer(); available = bufferLength - bufferPosition; if (available <= 0) {throw new EOFException();} if (available >= len) {System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; return;} System.arraycopy(buffer, bufferPosition, b, offset, available); bufferPosition += available; offset += available; len -= available;} else {ReadInternal(b, offset, len);}}
return (TagQueueResponse) invoke(new InvokeOptions().setRequestMarshaller(TagQueueRequestMarshaller.getInstance()).setResponseUnmarshaller(TagQueueResponseUnmarshaller.getInstance()), request, TagQueueResponse.class);
throw new UnsupportedOperationException();
return (ModifyCacheSubnetGroupResponse) invoke(new ModifyCacheSubnetGroupRequest(), ModifyCacheSubnetGroupRequestMarshaller.getInstance(), ModifyCacheSubnetGroupResponseUnmarshaller.getInstance());
public void SetParams(String @params, StringTokenizer st, boolean ignore, String culture) { if (st.MoveNext()) { st = new StringTokenizer(st.Current + " ", " "); if (st.MoveNext()) { st = new StringTokenizer(st.Current + " ", " "); if (st.MoveNext()) { st = new StringTokenizer(st.Current + " ", " "); } } } }
DeleteDocumentationVersionResponse deleteDocumentationVersion(DeleteDocumentationVersionRequest request) { return (DeleteDocumentationVersionResponse) invoke(request, new InvokeOptions().withRequestMarshaller(DeleteDocumentationVersionRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteDocumentationVersionResponseUnmarshaller.getInstance())); }
@Override public boolean equals(Object obj) { if (this == obj) return true; if (!(obj instanceof FacetLabel)) return false; FacetLabel other = (FacetLabel) obj; if (this.length != other.length) return false; for (int i = this.length - 1; i >= 0; i--) { if (!this.components[i].equals(other.components[i])) return false; } return true; }
return (GetInstanceAccessDetailsResponse) Instance.invoke(request, new InvokeOptions().withRequestMarshaller(Instance.GetInstanceAccessDetailsRequestMarshaller.getInstance()).withResponseUnmarshaller(Instance.GetInstanceAccessDetailsResponseUnmarshaller.getInstance()));
HSSFPolygon shape = new HSSFPolygon(); shape.setParentAnchor(anchor); shape.setAnchor((HSSFChildAnchor) anchor); shapes.add(shape); return shape;
return getBoundSheetRec(sheetIndex).getSheetName();
return invoke(new GetDashboardRequest(), new InvokeOptions().withRequestMarshaller(GetDashboardRequestMarshaller.getInstance()).withResponseUnmarshaller(GetDashboardResponseUnmarshaller.getInstance()));
return AssociateSigninDelegateGroupsWithAccountResponse instance.invoke(new AssociateSigninDelegateGroupsWithAccountRequest(), AssociateSigninDelegateGroupsWithAccountResponse.class, AssociateSigninDelegateGroupsWithAccountRequestMarshaller.getInstance(), AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.getInstance());
void addMultipleBlanks() { for (int j = 0; j < mbr.NumColumns; ++j) { MulBlankRecord mbr = new MulBlankRecord(br.Row, br.FirstColumn + j, new BlankRecord(br.Row, br.Column, XFIndex.getXFAt(0))); } }
public String toString() {StringBuilder sb = new StringBuilder();int k = 0;while (k >= 0) {sb.append("\\").append("java.lang").append("\\").append(StringHelper.Sharpen.Substring(@string, k + 2, StringHelper.Sharpen.Substring(@string, @string.IndexOf("\\", k + 2))));k = @string.IndexOf("\\", k + 2);}return sb.toString();}
throw new java.nio.ReadOnlyBufferException(); ByteBuffer.putInt(value);
} { ; ; ; ; ) ; ; ( for ; ; ; ; ; ArrayPtg 0 _reserved2Byte 0 _reserved1Short 0 _reserved0Int vv _arrayValues } { ++ r nRows < r int _nRows _nColumns int int ) Object ( ) ; ; ( for ; r vv Object nRows ) short ( nColumns ) short ( nRows nColumns ] [ ] [ } { ++ c nColumns < c int 0 = = ] [ = = ; c rowData Object new Length . values2d Length . 0 = = ] [ Object values2d rowData vv values2d ] [ ] [ ] [ ] [ ] [ _nRows * _nColumns getValueIndex ) , (
return (GetIceServerConfigResponse) instance.invoke(new InvokeOptions().withRequestMarshaller(GetIceServerConfigRequestMarshaller.instance).withResponseUnmarshaller(GetIceServerConfigResponseUnmarshaller.instance), request);
return new StringBuilder().append(getClass().getName()).append(" ").append(getValueAsString()).toString();
public String toString() { return field + " " + _parentQuery + " "; }
refCount.incrementAndGet();
public UpdateConfigurationSetSendingEnabledResult updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) {request = beforeClientExecution(request);return executeUpdateConfigurationSetSendingEnabled(request);}
return GetXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE;
void multiplyByPowerOfTen() { int pow10; if (pow10 < 0) { TenPower tp = TenPower.GetInstance(Math.abs(pow10)); mulShift = _divisor.tp(_divisorShift, _multiplicand.tp(_multiplierShift, tp)); } else { } }
return new StringBuilder().append(Path.DirectorySeparatorChar).append(GetComponent()).append(Path.DirectorySeparatorChar).append(builder).append(length - 1).append(new StringBuilder().append(Path.DirectorySeparatorChar).append(i).append(builder.ToString()).toString());
fetcher = new ECSMetadataServiceCredentialsFetcher(); fetcher.setRoleName(fetcher);
void setProgressMonitor(ProgressMonitor pm) { this.progressMonitor = pm; }
void reset() { if (!first) { if (!eof) { ptr = 0; parseEntry(); } } }
if (!(start >= 0)) { throw new NoSuchElementException(); } return util.iterator().previousIndex().iterator().previous();
return getNewPrefix();
int indexOfValue(int value) { for (int i = 0; i < mSize; ++i) { if (mValues[i] == value) { return i; } } return -1; }
public List<CharsRef> uniqueStems(List<CharsRef> stems) {CharArraySet terms = new CharArraySet(8, true);List<CharsRef> deduped = new ArrayList<>();for (CharsRef s : stems) {if (!terms.contains(s)) {terms.add(s);deduped.add(s);}}return deduped;}
return (GetGatewayResponsesResponse) invoke(new InvokeOptions().withRequestMarshaller(Instance.GetGatewayResponsesRequestMarshaller).withResponseUnmarshaller(Instance.GetGatewayResponsesResponseUnmarshaller), request, GetGatewayResponsesResponse.class);
void setPosition(int position) { currentBlockIndex = position >> outerInstance.blockBits; currentBlock = outerInstance.blocks[currentBlockIndex]; currentBlockUpto = position & outerInstance.blockMask; }
return Math.min(Math.max((int) s, (int) n), (int) Available);
} { ; BootstrapActionDetail bootstrapActionConfig _bootstrapActionConfig ) bootstrapActionConfig BootstrapActionConfig (
void serialize(ILittleEndianOutput out1) { if (field_7_padding != null) { if (field_5_hasMultibyte) { out1.writeByte(0x01); } else { out1.writeByte(0x00); } out1.writeShort(field_6_author.length()); StringUtil.putCompressedUnicode(field_6_author, out1); } else { out1.writeShort(0); } }
return string.lastIndexOf(@string);
add boolean addLastImpl(Object E) { return; }
void unsetSection() { ConfigSnapshot configSnapshot; String section, subsection; while (true) { if (!compareAndSet(state, src, res)) { continue; } state = get(); } }
String getTagName() { return tagName; }
subrecords.Insert(index, SubRecord element);
synchronized (mutex) { return object.remove(c); }
return new DoubleMetaphoneFilter(input, true, true);
return (InCoreLength); } long Length
public void setValue(boolean newValue) { this.value = newValue; }
} { ; ; Pair newSource oldSource ) newSource ContentSource , oldSource ContentSource ( newSource . oldSource .
public int get(int i) {if (i <= count) {return entries[i];} throw new IndexOutOfBoundsException();}
} { : ) ( CreateRepoRequest ; ; Method UriPattern ) , , , , ( PUT . MethodType " " " " " " " " " " " "
return deltaBaseAsOffset;
if (expectedModCount != modCount) throw new ConcurrentModificationException(); if (lastLink == null) throw new IllegalStateException(); LinkedList.Link<E> link = lastLink; if (link.previous != null) link.previous.next = link.next; else list.first = link.next; if (link.next != null) link.next.previous = link.previous; else list.last = link.previous; lastLink = null; list.size--; modCount++; expectedModCount++;
return (MergeShardsResponse) invoke(new InvokeOptions().setRequestMarshaller(MergeShardsRequestMarshaller.getInstance()).setResponseUnmarshaller(MergeShardsResponseUnmarshaller.getInstance()), request, MergeShardsResponse.class);
public AllocateHostedConnectionResult allocateHostedConnection(AllocateHostedConnectionRequest request) {request = beforeClientExecution(request);return executeAllocateHostedConnection(request);}
public int getBeginIndex() { return start; }
return query.getTerms(WeightedTerm.class);
throw new java.nio.ReadOnlyBufferException();
void decode(int[] values, int valuesOffset, byte[] blocks, int blocksOffset, int iterations) { for (int i = 0; i < iterations; i++) { values[valuesOffset++] = ((blocks[blocksOffset] & 0xFF) << 2) | ((blocks[blocksOffset + 1] & 0xFF) >> 6); values[valuesOffset++] = ((blocks[blocksOffset + 1] & 0x3F) << 4) | ((blocks[blocksOffset + 2] & 0xFF) >> 4); values[valuesOffset++] = ((blocks[blocksOffset + 2] & 0x0F) << 6) | (blocks[blocksOffset + 3] & 0x3F); blocksOffset += 4; } }
public String getHumanishName() { if (elements == null) throw new IllegalArgumentException(Constants.DOT_GIT); String s = elements.split(Constants.DOT_GIT_EXT)[0]; if (s == null) throw new IllegalArgumentException(Constants.DOT_GIT_EXT); String[] result = s.split(File.separatorChar == '\\' ? "\\\\" : File.separator); if (result.length == 0 || result[result.length - 1].isEmpty()) return ""; return result[result.length - 1]; }
return invoke(new InvokeOptions().withRequestMarshaller(DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance()), request, DescribeNotebookInstanceLifecycleConfigResponse.class);
return getAccessKeySecret();
return CreateVpnConnectionResponse.class.cast(invoke(new InvokeOptions().withRequestMarshaller(CreateVpnConnectionRequestMarshaller.getInstance()).withResponseUnmarshaller(CreateVpnConnectionResponseUnmarshaller.getInstance()), request));
DescribeVoices describeVoices(DescribeVoicesRequest request) { return (DescribeVoicesResponse) invoke(request, new InvokeOptions(DescribeVoicesRequestMarshaller.getInstance(), DescribeVoicesResponseUnmarshaller.getInstance())); }
ListMonitoringExecutionsResponse listMonitoringExecutions(ListMonitoringExecutionsRequest request) { return (ListMonitoringExecutionsResponse) invoke(request, new InvokeOptions(ListMonitoringExecutionsRequestMarshaller.getInstance(), ListMonitoringExecutionsResponseUnmarshaller.getInstance())); }
} { ; ; DescribeJobRequest(String jobId, String vaultName) { this.jobId = jobId; this.vaultName = vaultName; } }
public EscherRecord getEscherRecord(int index) { return escherRecords[index]; }
return invoke(new GetApisRequest(), new InvokeOptions().withRequestMarshaller(GetApisRequestMarshaller.getInstance()).withResponseUnmarshaller(GetApisResponseUnmarshaller.getInstance()), GetApisResponse.class);
return (DeleteSmsChannelResponse) invoke(new DeleteSmsChannelRequest(), new InvokeOptions().withRequestMarshaller(DeleteSmsChannelRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteSmsChannelResponseUnmarshaller.getInstance()));
return trackingRefUpdate;
void print() { System.out.print(Boolean.toString(b)); }
return getChildren()[getChild()];
} { ; NotIgnoredFilter workdirTreeIndex ) workdirTreeIndex int ( index .
} { ; AreaRecord field_1_formatFlags = in1.readShort(); }
} { : ) ( GetThumbnailRequest , Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " "
DescribeTransitGatewayVpcAttachmentsResponse describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeTransitGatewayVpcAttachmentsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance()); return invoke(request, options); }
PutVoiceConnectorStreamingConfigurationResponse response = (PutVoiceConnectorStreamingConfigurationResponse) invoke(new PutVoiceConnectorStreamingConfigurationRequest(), new InvokeOptions(PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance(), PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance()));
return prefixToOrdRange.tryGetValue(ordRange, result) ? result : null;
@Override public String toString() { if (startIndex >= 0 && startIndex < getText().length()) { return Utils.escapeWhitespace(getText().substring(startIndex, symbol.getStopIndex() + 1), false); } return ""; }
return peekFirstImpl();
return CreateWorkspacesResponse.class.cast(invoke(CreateWorkspacesRequest.class, CreateWorkspacesResponse.class, new InvokeOptions(CreateWorkspacesRequestMarshaller.getInstance(), CreateWorkspacesResponseUnmarshaller.getInstance())));
return new NumberFormatIndexRecord(); NumberFormatIndexRecord rec = rec; rec.field_1_formatIndex = field_1_formatIndex;
DescribeRepositoriesResponse describeRepositories(DescribeRepositoriesRequest request) {return (DescribeRepositoriesResponse) invoke(request, new InvokeOptions(Instance.RequestMarshaller.options, Instance.ResponseUnmarshaller.options));}
SparseIntArray sparseIntArray = new SparseIntArray(initialCapacity); int[] mKeys = new int[initialCapacity]; int[] mValues = new int[initialCapacity]; int mSize = ArrayUtils.idealIntArraySize(initialCapacity);
public final TokenStream createTokenStream() { return new HyphenatedWordsFilter(input); }
return invoke(new CreateDistributionWithTagsRequest(), new InvokeOptions().withRequestMarshaller(CreateDistributionWithTagsRequestMarshaller.getInstance()).withResponseUnmarshaller(CreateDistributionWithTagsResponseUnmarshaller.getInstance()), CreateDistributionWithTagsResponse.class);
throw new java.io.RandomAccessFile(fileName, mode);
return invoke(new InvokeOptions().withRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.getInstance()), request, DeleteWorkspaceImageResponse.class);
public String toHex(long value) { return Integer.toHexString((int) value); }
return invoke(new UpdateDistributionRequest(), UpdateDistributionResponse.class, UpdateDistributionRequestMarshaller.getInstance(), UpdateDistributionResponseUnmarshaller.getInstance());
public HSSFColor getColor(short index) { if (index == HSSFColor.AUTOMATIC.getIndex()) { return HSSFColor.AUTOMATIC.getInstance(); } else { byte[] b = palette.getColor(index); if (b != null) { return new HSSFColor.CustomColor(index, b); } return null; } }
throw new NotImplementedFunctionException(operands, srcRow, srcCol);
void serialize(ILittleEndianOutput out1) { out1.writeShort((short) field_1_number_crn_records); out1.writeShort((short) field_2_sheet_table_index); }
return new DescribeDBEngineVersionsResponse(DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest()));
} { ; ; formatRun(fontIndex, character); fontIndex = (short) _fontIndex; character = (short) _character;
byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int resultIndex = 0; for (int i = 0; i < length; i++) { char ch = chars[offset + i]; result[resultIndex++] = (byte) (ch >> 8); result[resultIndex++] = (byte) ch; } return result; }
public UploadArchiveResult uploadArchive(UploadArchiveRequest request) {request = beforeClientExecution(request);return executeUploadArchive(request);}
return GetHiddenTokensToLeft(tokenIndex - 1, (List<IToken>) null);
@Override public boolean equals(Object obj) { if (this == obj) return true; if (obj == null || getClass() != obj.getClass()) return false; AutomatonQuery other = (AutomatonQuery) obj; if (!m_term.equals(other.m_term)) return false; if (!m_compiled.equals(other.m_compiled)) return false; return true; }
public SpanQuery makeSpanClause() {List<SpanQuery> spanQueries = new ArrayList<>();for (WeightedSpanQuery wsq : weightBySpanQuery) {spanQueries.add(wsq.getKey().boost(wsq.getValue()));}if (spanQueries.size() == 1) {return spanQueries.get(0);}return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0]));}
return new StashCreateCommand();
FieldInfo ret; return byName.TryGetValue(fieldName, out ret) ? ret : null;
public DescribeEventSourceResult describeEventSource(DescribeEventSourceRequest request) {request = beforeClientExecution(request);return executeDescribeEventSource(request);}
return GetDocumentAnalysisResponse.class.cast(invoke(new InvokeOptions(new GetDocumentAnalysisRequestMarshaller(), new GetDocumentAnalysisResponseUnmarshaller()), request));
return (CancelUpdateStackResponse) invoke(new CancelUpdateStackRequest(), new InvokeOptions(new CancelUpdateStackRequestMarshaller(), new CancelUpdateStackResponseUnmarshaller()));
public ModifyLoadBalancerAttributesResult modifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) {request = beforeClientExecution(request);return executeModifyLoadBalancerAttributes(request);}
SetInstanceProtectionResponse setInstanceProtection(SetInstanceProtectionRequest request) { return (SetInstanceProtectionResponse) invoke(request, new InvokeOptions(SetInstanceProtectionRequestMarshaller.getInstance(), SetInstanceProtectionResponseUnmarshaller.getInstance())); }
ModifyDBProxy modifyDBProxyResponse = (ModifyDBProxy) invoke(new InvokeOptions(ModifyDBProxyRequestMarshaller.getInstance(), ModifyDBProxyResponseUnmarshaller.getInstance()), request);
void add(int posLength, int endOffset, int len, int offset, char[] output) { if (outputs == null) { outputs = new CharsRef[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF)]; endOffsets = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; posLengths = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; } if (count == outputs.length) { CharsRef[] nextOutputs = new CharsRef[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF)]; int[] nextEndOffsets = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; int[] nextPosLengths = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(outputs, 0, nextOutputs, 0, count); System.arraycopy(endOffsets, 0, nextEndOffsets, 0, count); System.arraycopy(posLengths, 0, nextPosLengths, 0, count); outputs = nextOutputs; endOffsets = nextEndOffsets; posLengths = nextPosLengths; } outputs[count] = new CharsRef(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
} { : ) ( FetchLibrariesRequest , Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " "
return objects.exists();
} { ; FilterOutputStream out ) java ( out . . .
public ScaleClusterResult scaleCluster(ScaleClusterRequest request) {request = beforeClientExecution(request);return executeScaleCluster(request);}
return DVConstraint.createTimeConstraint(operatorType, formula1, formula2);
ListObjectParentPathsResponse response = (ListObjectParentPathsResponse) invoke(new InvokeOptions().withRequestMarshaller(ListObjectParentPathsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListObjectParentPathsResponseUnmarshaller.getInstance()), request);
return (DescribeCacheSubnetGroupsResponse) invoke(new DescribeCacheSubnetGroupsRequest(), DescribeCacheSubnetGroupsResponse.class, DescribeCacheSubnetGroupsRequestMarshaller.getInstance(), DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance());
setSharedFormula(sharedFormula, field_5_options, flag);
public boolean isReuseObjects() { return reuseObjects; }
public IErrorNode addErrorNode(IToken t) { ErrorNodeImpl errorNode = new ErrorNodeImpl(t); addChild(errorNode); return errorNode; }
if (args.size() > 0) { throw new IllegalArgumentException("System.ArgumentException: " + args); }
public RemoveSourceIdentifierFromSubscriptionResult removeSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request) {request = beforeClientExecution(request);return executeRemoveSourceIdentifierFromSubscription(request);}
return TokenFilterFactory.forName(name, args, loader.newInstance(String.class, String.class));
} { : ) ( AddAlbumPhotosRequest , Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " "
public GetThreatIntelSetResponse getThreatIntelSet(GetThreatIntelSetRequest request) {request = beforeClientExecution(request);return executeGetThreatIntelSet(request);}
return new Binary.AndTreeFilter(Clone(a), Clone(b));
public boolean equals(Object o) { return this == o; }
protected boolean hasArray() { return hasArray; }
public UpdateContributorInsightsResult updateContributorInsights(UpdateContributorInsightsRequest request) {request = beforeClientExecution(request);return executeUpdateContributorInsights(request);}
unwriteProtectWorkbook(void) { remove.records(fileShare.writeProtect(null, null)); }
} { ; SolrSynonymParser expand ) analyzer , dedup ( base : expand . ) analyzer Analyzer , expand boolean , dedup boolean (
return (RequestSpotInstancesResponse) instance.invoke(request, new InvokeOptions(instance.RequestSpotInstancesRequestMarshaller, instance.RequestSpotInstancesResponseUnmarshaller));
return ObjectData.FindObjectRecord(byte[].class);
public GetContactAttributesResult getContactAttributes(GetContactAttributesRequest request) {request = beforeClientExecution(request);return executeGetContactAttributes(request);}
return getKey() + " " + getValue().toString();
ListTextTranslationJobsResponse listTextTranslationJobs(ListTextTranslationJobsRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(ListTextTranslationJobsRequestMarshaller.getInstance()); options.setResponseUnmarshaller(ListTextTranslationJobsResponseUnmarshaller.getInstance()); return invoke(request, options); }
return invoke(new InvokeOptions().withRequestMarshaller(GetContactMethodsRequestMarshaller.getInstance()).withResponseUnmarshaller(GetContactMethodsResponseUnmarshaller.getInstance()), request, GetContactMethodsResponse.class);
short LookupIndexByName(String name) { FunctionMetadata fd = FunctionMetadataIndex.getInstance().getFunctionByNameInternal(name); if (fd == null) return -1; return fd; }
public DescribeAnomalyDetectors describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(DescribeAnomalyDetectorsRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeAnomalyDetectorsResponseUnmarshaller.getInstance());return invoke(request, options);}
InsertId string } { ) , ( ; return changeId ObjectId message string InsertId ) , , (
if ((sz = db.GetObjectSize(objectId, typeHint)) < 0) throw new MissingObjectException(objectId, ""); return sz;
return importInstallationMediaResponse((ImportInstallationMediaRequest) invoke(new InvokeOptions(), ImportInstallationMediaRequestMarshaller.getInstance(), ImportInstallationMediaResponseUnmarshaller.getInstance()));
public PutLifecycleEventHookExecutionStatusResult putLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request) {request = beforeClientExecution(request);return executePutLifecycleEventHookExecutionStatus(request);}
} { ; NumberPtg field_1_value ) in1 ILittleEndianInput ( ) ( readDouble . in1
return (GetFieldLevelEncryptionConfigResponse) invoke(new InvokeOptions().setRequestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.getInstance()).setResponseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance()), request, GetFieldLevelEncryptionConfigResponse.class);
public DescribeDetectorResult describeDetector(DescribeDetectorRequest request) {request = beforeClientExecution(request);return executeDescribeDetector(request);}
return (ReportInstanceStatusResponse) invoke(new ReportInstanceStatusRequest(), new InvokeOptions().withRequestMarshaller(Instance.ReportInstanceStatusRequestMarshaller.getInstance()).withResponseUnmarshaller(Instance.ReportInstanceStatusResponseUnmarshaller.getInstance()));
return DeleteAlarmResponse.class.cast(invoke(new InvokeOptions().withRequestMarshaller(DeleteAlarmRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteAlarmResponseUnmarshaller.getInstance()), request));
return new PortugueseStemFilter(input);
new FtCblsSubRecord() { byte[] reserved = new byte[ENCODED_SIZE]; }
synchronized (mutex) { return c.remove(object); }
return (GetDedicatedIp) invoke(new InvokeOptions().setRequestMarshaller(Instance.GetDedicatedIpRequestMarshaller).setResponseUnmarshaller(Instance.GetDedicatedIpResponseUnmarshaller), request, GetDedicatedIpResponse.class);
public String toString() { return precedence + " "; }
public ListStreamProcessorsResult listStreamProcessors(ListStreamProcessorsRequest request) {request = beforeClientExecution(request);return executeListStreamProcessors(request);}
public DeleteLoadBalancerPolicyRequest deleteLoadBalancerPolicy(String policyName, String loadBalancerName) { this.policyName = policyName; this.loadBalancerName = loadBalancerName; return this; }
int options(WindowProtectRecord options) { return _options; }
UnbufferedCharStream data = new UnbufferedCharStream(new int[bufferSize], 0, bufferSize);
return (GetOperationsResponse) invoke(new InvokeOptions().setRequestMarshaller(GetOperationsRequestMarshaller.getInstance()).setResponseUnmarshaller(GetOperationsResponseUnmarshaller.getInstance()), request, GetOperationsResponse.class);
void copyRawTo(byte[] b, int o) { EncodeInt32(NB, b, o); EncodeInt32(NB, b, o + 4); EncodeInt32(NB, b, o + 8); EncodeInt32(NB, b, o + 12); EncodeInt32(NB, b, o + 16); }
public WindowOneRecord(RecordInputStream in1) {setField1HHold(in1.readShort()); setField2VHold(in1.readShort()); setField3Width(in1.readShort()); setField4Height(in1.readShort()); setField5Options(in1.readShort()); setField6ActiveSheet(in1.readShort()); setField7FirstVisibleTab(in1.readShort()); setField8NumSelectedTabs(in1.readShort()); setField9TabWidthRatio(in1.readShort());}
return StopWorkspacesResponse.class.cast(Invoke(new InvokeOptions(StopWorkspacesRequestMarshaller.getInstance(), StopWorkspacesResponseUnmarshaller.getInstance()), request));
try { if (isOpen()) { try { dump(); } finally { try { channel.truncate(); } finally { channel.close(); } } } } finally { fos.close(); }
return DescribeMatchmakingRuleSetsResponse.class.cast(invoke(new InvokeOptions(DescribeMatchmakingRuleSetsRequestMarshaller.getInstance(), DescribeMatchmakingRuleSetsResponseUnmarshaller.getInstance()), request));
public String getPronunciation(int wordId, int surface, int off, int len) { return null; }
public String getPath() { return pathStr; }
double devsq(double[] v) {int n = v.length;double m = 0, s = 0;if (n == 0) return Double.NaN;for (int i = 0; i < n; ++i) {m += v[i];}m /= n;for (int i = 0; i < n; ++i) {s += (v[i] - m) * (v[i] - m);}return s;}
return (DescribeResizeResponse) invoke(new DescribeResizeRequest(), DescribeResizeRequestMarshaller.getInstance(), DescribeResizeResponseUnmarshaller.getInstance());
return passedThroughNonGreedyDecision; } } boolean hasPassedThroughNonGreedyDecision() {
end int } { ) ( ; return end ) (
void Traverse(ICellHandler handler, SimpleCellWalkContext ctx, int firstRow, int lastRow, int firstColumn, int lastColumn, int width) { for (int rowNumber = firstRow; rowNumber <= lastRow; ++rowNumber) { IRow currentRow = ctx.sheet.GetRow(rowNumber); if (currentRow == null) { if (!ctx.traverseEmptyCells) continue; currentRow = null; } for (int colNumber = firstColumn; colNumber <= lastColumn; ++colNumber) { ICell currentCell = currentRow == null ? null : currentRow.GetCell(colNumber); if (currentCell == null) { if (!ctx.traverseEmptyCells) continue; } int ordinalNumber = (rowNumber - firstRow) * width + (colNumber - firstColumn) + 1; handler.OnCell(ctx, ordinalNumber, currentCell); } } }
public int getReadIndex() { return _ReadIndex; }
public int compareTo(ScoreTerm other) { if (this.term.compareTo(other.term) != 0) return this.term.compareTo(other.term); if (this.boost.compareTo(other.boost) != 0) return this.boost.compareTo(other.boost); return 0; }
for (int i = 0; i < s.length; i++) { switch (s[i]) { case HAMZA_ABOVE: case HEH_GOAL: case HEH_YEH: case KEHEH: case YEH_BARREE: case FARSI_YEH: s[i] = 'KAF'; s[i + 1] = 'HEH'; s[i + 2] = 'YEH'; StemmerUtil.delete(s, i, s.length); len--; break; default: break; } } return len;
void serialize() { out1.writeShort(out1); }
} { ; DiagnosticErrorListener exactOnly ) exactOnly boolean ( exactOnly .
} { ; ; KeySchemaElement keyType _keyType attributeName _attributeName ) keyType KeyType , attributeName String (
public GetAssignmentResult getAssignment(GetAssignmentRequest request) {request = beforeClientExecution(request);return executeGetAssignment(request);}
return (FindOffset(AnyObjectId) != -1) ? true : false;
public final boolean setAllGroups(boolean allGroups) { return allGroups; }
void setMultiValued(String dimName, boolean v) { synchronized (this) { DimConfig fieldType = fieldTypes.get(dimName); if (fieldType == null) { fieldType = new DimConfig(); fieldTypes.put(dimName, fieldType); } fieldType.isMultiValued = v; } }
int getCellsVal() { int size = 0; for (char c : cells.keySet()) { if (cmd.at(size) >= 0) { size++; } } return size; }
public DeleteVoiceConnectorResult deleteVoiceConnector(DeleteVoiceConnectorRequest request) {request = beforeClientExecution(request);return executeDeleteVoiceConnector(request);}
return invoke(new DeleteLifecyclePolicyRequest(), new InvokeOptions().withRequestMarshaller(DeleteLifecyclePolicyRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteLifecyclePolicyResponseUnmarshaller.getInstance()), DeleteLifecyclePolicyResponse.class);
