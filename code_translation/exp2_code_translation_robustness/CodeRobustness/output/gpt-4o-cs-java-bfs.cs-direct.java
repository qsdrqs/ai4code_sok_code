void WriteShort(ILittleEndianOutput out1) { out1.Serialize(); }
void addAll() { if (src != null) { for (int srcDirIdx = 0; srcDirIdx < src.size(); ++srcDirIdx) { if (src.tailDirIdx == 0) { return; } Util.BlockList.addAll(src.tailBlock[src.tailBlkIdx], src.directory); } } }
void writeByte(byte b) { if (b != 0) { if (++outerInstance.upto == outerInstance.blockSize) { outerInstance.currentBlock = new byte[outerInstance.blockSize]; outerInstance.blocks.add(outerInstance.currentBlock); outerInstance.blockEnd = outerInstance.blockSize; outerInstance.upto = 0; } outerInstance.currentBlock[outerInstance.upto] = b; } }
ObjectId GetObjectId() { return objectId; }
return (DeleteDomainEntryResponse) invoke(new DeleteDomainEntryRequest(), new InvokeOptions(new DeleteDomainEntryRequestMarshaller(), new DeleteDomainEntryResponseUnmarshaller()));
return fst == null ? 0 : fst.GetSizeInBytes();
String GetFullMessage() { if (RawParseUtils.ParseEncoding(buffer) != null) { return RawParseUtils.Decode(RawParseUtils.TagMessage(buffer, raw, msgB = 0, raw.length), enc); } return ""; }
POIFSFileSystem _root = null; ArrayList _documents = new ArrayList(); PropertyTable _property_table = new PropertyTable(new HeaderBlock()); HeaderBlock headerBlock = new HeaderBlock();
void init() { assert Debug.assertTrue(address & address < upto && slice != null && ByteBlockPool.BYTE_BLOCK_MASK > 0 && ByteBlockPool.BYTE_BLOCK_SHIFT > 0 && Buffers.pool[slice >> ByteBlockPool.BYTE_BLOCK_SHIFT].length >= upto); }
return SubmoduleAddCommand.SetPath(path).path(path).path(path);
return (ListIngestionsResponse) invoke(new ListIngestionsRequest(), new InvokeOptions(ListIngestionsRequestMarshaller.getInstance(), ListIngestionsResponseUnmarshaller.getInstance()));
; SwitchTo QueryParserTokenManager ) ( { this : ) stream ( ) lexState int , stream ICharStream (
return Invoke(new InvokeOptions().withRequestMarshaller(GetShardIteratorRequestMarshaller.getInstance()).withResponseUnmarshaller(GetShardIteratorResponseUnmarshaller.getInstance()), request, GetShardIteratorResponse.class);
} { : ) ( ModifyStrategyRequest , Method ) , , , , ( "POST" . MethodType " " " " " " " " " "
if (in == null) { throw new IOException(); } try { synchronized (lock) { if (in.available() > 0 || in.hasRemaining()) { return; } } } catch (IOException e) { throw new IOException(); }
return _optRecord; } EscherOptRecord getOptRecord() {
int copylen = 0; if (buffer == null) throw new NullPointerException(); if (length == 0) return; for (int i = 0; i < copylen; ++i) buffer[offset + i] = (byte) (unchecked((int) buffer[pos - count + i])); return copylen;
} { ; OpenNLPSentenceBreakIterator sentenceOp = new NLPSentenceDetectorOp(sentenceOp);
if (str != null) System.out.print(StringHelper.Sharpen.GetValueOf((Object) null));
throw new NotImplementedFunctionException(functionName, cause);
return nextEntry().value;
void readBytes(int b, int offset, int len, boolean useBuffer) { int available; if (len > 0) { if (useBuffer && bufferPosition > 0) { available = bufferLength - bufferPosition; if (available > 0) { if (available < len) { System.arraycopy(buffer, bufferPosition, b, offset, available); bufferPosition += available; offset += available; len -= available; } else { System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; return; } } } if (len > bufferSize) { long after = readInternal(b, offset, len); if (after < len) { throw new EndOfStreamException("End of stream reached with " + (len - after) + " bytes left to read."); } } else { refillBuffer(); if (bufferLength < len) { long after = readInternal(b, offset, len); if (after < len) { throw new EndOfStreamException("End of stream reached with " + (len - after) + " bytes left to read."); } } else { System.arraycopy(buffer, 0, b, offset, len); bufferPosition = len; } } } }
return (TagQueueResponse) invoke(new InvokeOptions(TagQueueRequestMarshaller.getInstance(), TagQueueResponseUnmarshaller.getInstance()), request, TagQueueResponse.class);
throw new UnsupportedOperationException();
return (ModifyCacheSubnetGroup) invoke(new ModifyCacheSubnetGroupRequest(), ModifyCacheSubnetGroupResponse.class, new InvokeOptions(ModifyCacheSubnetGroupRequestMarshaller.getInstance(), ModifyCacheSubnetGroupResponseUnmarshaller.getInstance()));
void SetParams(String @params) { StringTokenizer st = new StringTokenizer(@params, " "); if (st.hasMoreTokens()) { String culture = st.nextToken(); if (st.hasMoreTokens()) { culture += " " + st.nextToken(); if (st.hasMoreTokens()) { culture += " " + st.nextToken(); } } } }
return invoke(new DeleteDocumentationVersionRequest(), new InvokeOptions().withRequestMarshaller(DeleteDocumentationVersionRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteDocumentationVersionResponseUnmarshaller.getInstance()), DeleteDocumentationVersionResponse.class);
@Override public boolean equals(Object obj) { if (this == obj) return true; if (obj == null || getClass() != obj.getClass()) return false; FacetLabel other = (FacetLabel) obj; if (this.Length != other.Length) return false; for (int i = 0; i < this.Length; i++) if (!this.Components[i].equals(other.Components[i])) return false; return true; }
GetInstanceAccessDetailsResponse response = (GetInstanceAccessDetailsResponse) invoke(new GetInstanceAccessDetailsRequest(), Instance.GetInstanceAccessDetailsRequestMarshaller.getInstance(), Instance.GetInstanceAccessDetailsResponseUnmarshaller.getInstance());
HSSFPolygon shape = new HSSFPolygon(); shape.setParentAnchor(anchor); shape.setAnchor((HSSFChildAnchor) anchor); shapes.add(shape); return shape;
return GetBoundSheetRec(sheetIndex).getSheetName();
return Invoke(new GetDashboardRequest(), GetDashboardResponse.class, new InvokeOptions(GetDashboardRequestMarshaller.getInstance(), GetDashboardResponseUnmarshaller.getInstance()));
return AssociateSigninDelegateGroupsWithAccountResponse.class.cast(invoke(new AssociateSigninDelegateGroupsWithAccountRequest(), new InvokeOptions(AssociateSigninDelegateGroupsWithAccountRequestMarshaller.getInstance(), AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.getInstance())));
void AddMultipleBlanks() { for (int j = 0; j < mbr.NumColumns; ++j) { MulBlankRecord mbr = new MulBlankRecord(br.Row, br.FirstColumn + j, br.Row.GetXFAt(0)); mbr.InsertCell(j, BlankRecord.XFIndex); } }
while (int k = 0; k >= 0; k += 2) { StringBuilder sb = new StringBuilder(); sb.append("\\").append(java.lang.String.valueOf(k)).append("\\\\"); return sb.toString(); }
throw new java.nio.ReadOnlyBufferException(); java.nio.ByteBuffer.putInt(value);
} { ; ; ; ; ) ; ; ( for ; ; ; ; ; ArrayPtg 0 _reserved2Byte 0 _reserved1Short 0 _reserved0Int vv _arrayValues } { ++ r nRows < r int _nRows _nColumns int int ) Object ( ) ; ; ( for ; r vv Object nRows ) short ( nColumns ) short ( nRows nColumns ] [ ] [ } { ++ c nColumns < c int 0 = = ] [ = = ; c rowData Object new Length . values2d Length . 0 = = ] [ Object values2d rowData vv values2d ] [ ] [ ] [ ] [ ] [ _nRows * _nColumns GetValueIndex ) , (
return (GetIceServerConfigResponse) invoke(new GetIceServerConfigRequest(), new InvokeOptions(GetIceServerConfigRequestMarshaller.getInstance(), GetIceServerConfigResponseUnmarshaller.getInstance()));
return new StringBuilder().append(getClass().getName()).append(" ").append(getValueAsString()).append(" ").toString();
return field + " " + _parentQuery + " ";
refCount.IncrementAndGet();
return invoke(new UpdateConfigurationSetSendingEnabledRequest(), new InvokeOptions().withRequestMarshaller(UpdateConfigurationSetSendingEnabledRequestMarshaller.getInstance()).withResponseUnmarshaller(UpdateConfigurationSetSendingEnabledResponseUnmarshaller.getInstance()), UpdateConfigurationSetSendingEnabledResponse.class);
return GetXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE;
void multiplyByPowerOfTen() { if (pow10 < 0) { TenPower tp = TenPower.GetInstance(Math.abs(pow10), _divisor.tp, _divisorShift.tp, _multiplicand.tp, _multiplierShift.tp); } else { } }
return new StringBuilder().append(Path.DirectorySeparatorChar).append(GetComponent()).append(Path.DirectorySeparatorChar).append(new StringBuilder().append(Path.DirectorySeparatorChar).append(GetComponent()).append(Path.DirectorySeparatorChar).toString()).toString();
try (ECSMetadataServiceCredentialsFetcher fetcher = new ECSMetadataServiceCredentialsFetcher()) { fetcher.setRoleName(fetcher.fetcher()); }
void setProgressMonitor(ProgressMonitor pm) { this.progressMonitor = pm; }
void Reset() { if (!First) { if (!Eof) { ptr = 0; ParseEntry(); } } }
if (!(start >= 0)) { throw new NoSuchElementException(); } return util.iterator().previousIndex();
return newPrefix; } } String getNewPrefix() {
for (int i = 0; i < mSize; ++i) { if (mValues[i] == value) { return i; } } return -1;
List<CharsRef> UniqueStems(List<CharsRef> stems, CharArraySet dictionary) { List<CharsRef> deduped = new ArrayList<>(); for (CharsRef s : stems) { if (!dictionary.contains(s)) { dictionary.add(s); deduped.add(s); } } return deduped; }
return client.invoke(new GetGatewayResponsesRequest(), new InvokeOptions(GetGatewayResponsesRequestMarshaller.getInstance(), GetGatewayResponsesResponseUnmarshaller.getInstance()));
void setPosition(int position) { currentBlockIndex = position >> outerInstance.blockBits; currentBlock = outerInstance.blocks[currentBlockIndex]; currentBlockUpto = position & outerInstance.blockMask; }
return Math.min(Math.max(s, (int) ptr), (int) s);
} { ; BootstrapActionDetail bootstrapActionConfig _bootstrapActionConfig ) bootstrapActionConfig BootstrapActionConfig (
void serialize(ILittleEndianOutput out1) { if (field_7_padding != null) { out1.writeShort(field_5_hasMultibyte ? 0x01 : 0x00); out1.writeShort(field_6_author.length()); StringUtil.putCompressedUnicode(field_6_author, out1); } else { out1.writeShort(0x00); } }
return string.lastIndexOf(@string);
boolean add(Object E) { return addLastImpl(); }
void UnsetSection() { ConfigSnapshot ConfigSnapshot; String section, subsection; while (true) { if (!state.Get().CompareAndSet(src, res)) break; } }
String getTagName() { return tagName; }
subrecords.Insert(index, SubRecord element) { void AddSubRecord }
synchronized (object) { return c.remove(); }
return new DoubleMetaphoneFilter(input, TokenStream.class);
return InCoreLength();
void setValue(boolean newValue) { value = newValue; }
} {  ; ; Pair newSource oldSource ) newSource ContentSource , oldSource ContentSource ( newSource . oldSource .
if (i < 0 || i >= count) throw new IndexOutOfBoundsException(); return entries[i];
new CreateRepoRequest(Method.PUT, "UriPattern", MethodType.PUT);
return deltaBaseAsOffset;
if (modCount != expectedModCount) throw new ConcurrentModificationException(); if (lastLink != null) { if (lastLink.previous_1 == null) { list._size--; list.modCount++; expectedModCount++; list = new java.util.LinkedList<>(); } else { throw new java.util.InvalidOperationException(); } } else { throw new java.util.InvalidOperationException(); }
return Invoke(new MergeShardsRequest(), MergeShardsResponse.class, Instance.options, Instance.RequestMarshaller, Instance.ResponseUnmarshaller);
return (AllocateHostedConnectionResponse) invoke(new AllocateHostedConnectionRequest(), AllocateHostedConnectionResponse.class, new InvokeOptions(AllocateHostedConnectionRequestMarshaller.getInstance(), AllocateHostedConnectionResponseUnmarshaller.getInstance()));
int getBeginIndex() { return start; }
return query.GetTerms(new WeightedTerm());
throw new java.nio.ReadOnlyBufferException();
void decode(int iterations, int valuesOffset, int[] values, int blocksOffset, byte[] blocks) { for (int i = 0; i < iterations; i++) { values[valuesOffset++] = ((blocks[blocksOffset] & 0xFF) << 2) | ((blocks[blocksOffset + 1] & 0xFF) >>> 6); values[valuesOffset++] = ((blocks[blocksOffset + 1] & 0xFF) << 4) | ((blocks[blocksOffset + 2] & 0xFF) >>> 4); values[valuesOffset++] = ((blocks[blocksOffset + 2] & 0xFF) << 6) | ((blocks[blocksOffset + 3] & 0xFF) >>> 2); blocksOffset += 4; } }
String GetHumanishName(String s) { if (s == null) throw new IllegalArgumentException(); String[] elements = s.split(java.io.File.separator); if (elements.length == 0) throw new IllegalArgumentException(); if (Constants.DOT_GIT.equals(elements[elements.length - 1])) { if (elements.length > 1) return elements[elements.length - 2]; else throw new IllegalArgumentException(); } else if (elements[elements.length - 1].endsWith(Constants.DOT_GIT_EXT)) { return elements[elements.length - 1].substring(0, elements[elements.length - 1].length() - Constants.DOT_GIT_EXT.length()); } else if (elements.length > 1 && Matcher.LOCAL_FILE.equals(elements[elements.length - 2])) { return elements[elements.length - 1]; } else if (elements.length > 1 && elements[elements.length - 1].equals("")) { return elements[elements.length - 2]; } else { return elements[elements.length - 1]; } }
return DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance().unmarshall(invoke(new DescribeNotebookInstanceLifecycleConfigRequest(), DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance(), DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance()));
String GetAccessKeySecret() { return AccessSecret; }
return (CreateVpnConnectionResponse) invoke(new InvokeOptions(CreateVpnConnectionRequestMarshaller.getInstance(), CreateVpnConnectionResponseUnmarshaller.getInstance()), request, CreateVpnConnectionRequest.class, CreateVpnConnectionResponse.class);
return DescribeVoicesResponseUnmarshaller.getInstance().unmarshall(DescribeVoicesRequestMarshaller.getInstance().marshall(new DescribeVoicesRequest(), options), DescribeVoicesResponse.class);
return client.invoke(new ListMonitoringExecutionsRequest(), ListMonitoringExecutionsResponse.class, new ListMonitoringExecutionsRequestMarshaller(), new ListMonitoringExecutionsResponseUnmarshaller());
} {  ; ; DescribeJobRequest(String jobId, String vaultName) { this.jobId = jobId; this.vaultName = vaultName; }
return escherRecords[index];
return Invoke(new InvokeOptions(), GetApisRequest.class, GetApisResponse.class, GetApisRequestMarshaller.getInstance(), GetApisResponseUnmarshaller.getInstance());
return invoke(new DeleteSmsChannelRequest(), new InvokeOptions().withRequestMarshaller(DeleteSmsChannelRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteSmsChannelResponseUnmarshaller.getInstance()), DeleteSmsChannelResponse.class);
return trackingRefUpdate; } } TrackingRefUpdate getTrackingRefUpdate() {
System.out.println(Boolean.toString(b));
return GetChildren()[GetChild()];
} { ; NotIgnoredFilter workdirTreeIndex ) workdirTreeIndex int ( index .
} { AreaRecord field_1_formatFlags; in1 = new RecordInputStream(); in1.readShort();
} { : ) ( GetThumbnailRequest , Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " " " " " ;
return DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance().unmarshall(invoke(new DescribeTransitGatewayVpcAttachmentsRequestMarshaller().marshall(request), new InvokeOptions()));
return invoke(new PutVoiceConnectorStreamingConfigurationRequest(), new InvokeOptions().withRequestMarshaller(PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance()).withResponseUnmarshaller(PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance()), PutVoiceConnectorStreamingConfigurationResponse.class);
return prefixToOrdRange.TryGetValue(OrdRange, out String result) ? result : null;
return string != null && !string.isEmpty() ? String.format("%s %s", symbol.getName(), symbol.getSize()) : "";
return peekFirstImpl();
return CreateWorkspacesResponse.createWorkspacesResponse((CreateWorkspacesRequest) InvokeOptions.invoke(new CreateWorkspacesRequestMarshaller().options(), new CreateWorkspacesResponseUnmarshaller().options()));
NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); return rec; rec.field_1_formatIndex;
return (DescribeRepositoriesResponse) invoke(new DescribeRepositoriesRequest(), DescribeRepositoriesResponse.class, new DescribeRepositoriesRequestMarshaller(), new DescribeRepositoriesResponseUnmarshaller());
SparseIntArray sparseIntArray = new SparseIntArray(initialCapacity); int[] mKeys = new int[ArrayUtils.idealIntArraySize(initialCapacity)]; int[] mValues = new int[ArrayUtils.idealIntArraySize(initialCapacity)]; int mSize = 0;
return new HyphenatedWordsFilter(input);
return CreateDistributionWithTagsResponse.class.cast(invoke(new InvokeOptions(CreateDistributionWithTagsRequestMarshaller.getInstance(), CreateDistributionWithTagsResponseUnmarshaller.getInstance()), request));
throw new java.io.FileNotFoundException("NotImplementedException");
return invoke(new DeleteWorkspaceImageRequest(), new InvokeOptions().withRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.getInstance()), DeleteWorkspaceImageResponse.class);
int ToHex(long value) { return (int) value; }
return UpdateDistributionResponse.class.cast(invoke(new InvokeOptions(UpdateDistributionRequestMarshaller.getInstance(), UpdateDistributionResponseUnmarshaller.getInstance()), request, UpdateDistributionResponse.class));
public static HSSFColor getColor(short index) { if (index == HSSFColor.AUTOMATIC.getIndex()) { return HSSFColor.AUTOMATIC.getInstance(); } byte[] b = palette.getColor(index); if (b != null) { return new CustomColor(b); } return null; }
throw new NotImplementedFunctionException(); ValueEval Evaluate(ValueEval[] operands, int srcRow, int srcCol) { }
void serialize(ILittleEndianOutput out1) { out1.writeShort((short) field_1_number_crn_records); out1.writeShort((short) field_2_sheet_table_index); }
return new DescribeDBEngineVersions(DescribeDBEngineVersionsResponse(DescribeDBEngineVersionsRequest));
} { ; ; FormatRun fontIndex character) fontIndex short, character short (_fontIndex._character.
byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int resultIndex = 0; for (int i = offset; i < offset + length; i++) { char ch = chars[i]; result[resultIndex++] = (byte) (ch >> 8); result[resultIndex++] = (byte) ch; } return result; }
return invoke(new UploadArchiveRequest(), new InvokeOptions(), UploadArchiveResponse.class, UploadArchiveRequestMarshaller.getInstance(), UploadArchiveResponseUnmarshaller.getInstance());
return (List<IToken>) GetHiddenTokensToLeft(tokenIndex - 1);
@Override public boolean equals(Object obj) { if (this == obj) return true; if (obj == null || getClass() != obj.getClass()) return false; AutomatonQuery other = (AutomatonQuery) obj; if (m_term != null ? !m_term.equals(other.m_term) : other.m_term != null) return false; return m_compiled != null ? m_compiled.equals(other.m_compiled) : other.m_compiled == null; }
List<SpanQuery> spanQueries = new ArrayList<>(); for (var wsq : weightBySpanQuery) { spanQueries.add(wsq.getKey().boost(wsq.getValue())); } return spanQueries.size() == 1 ? spanQueries.get(0) : new SpanOrQuery(spanQueries.toArray(new SpanQuery[0]));
return new StashCreateCommand();
return byName.containsKey(fieldName) ? byName.get(fieldName) : null;
return Invoke(new DescribeEventSourceRequest(), DescribeEventSourceResponse.class, new InvokeOptions(DescribeEventSourceRequestMarshaller.getInstance(), DescribeEventSourceResponseUnmarshaller.getInstance()));
return Invoke(new InvokeOptions() {{ setRequestMarshaller(GetDocumentAnalysisRequestMarshaller.getInstance()); setResponseUnmarshaller(GetDocumentAnalysisResponseUnmarshaller.getInstance()); }}, request, GetDocumentAnalysisResponse.class);
return (CancelUpdateStackResponse) invoke(new InvokeOptions(), CancelUpdateStackRequest.class, CancelUpdateStackResponse.class, CancelUpdateStackRequestMarshaller.getInstance(), CancelUpdateStackResponseUnmarshaller.getInstance());
return (ModifyLoadBalancerAttributesResponse) invoke(new ModifyLoadBalancerAttributesRequest(), ModifyLoadBalancerAttributesResponse.class, ModifyLoadBalancerAttributesRequestMarshaller.getInstance(), ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance());
return Invoke(new SetInstanceProtectionRequest(), new InvokeOptions().withRequestMarshaller(SetInstanceProtectionRequestMarshaller.getInstance()).withResponseUnmarshaller(SetInstanceProtectionResponseUnmarshaller.getInstance()));
return Invoke(new ModifyDBProxyRequest(), ModifyDBProxyResponse.class, Instance.options, Instance.RequestMarshaller, Instance.ResponseUnmarshaller);
void add() { if (count == outputs.length) { outputs = Arrays.copyOf(outputs, ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF)); posLengths = Arrays.copyOf(posLengths, ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)); endOffsets = Arrays.copyOf(endOffsets, ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)); } outputs[count] = new CharsRef(); posLengths[count] = 0; endOffsets[count] = 0; count++; }
} { : ) ( FetchLibrariesRequest , Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " " " " ;
return Objects.exists();
} { ; FilterOutputStream out ) Java ( out . . .
} { : ) ( ScaleClusterRequest ; ; Method UriPattern ) , , , , ( HttpMethod.PUT . MethodType " " " " " " " " " " " "
return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);
return (ListObjectParentPathsResponse) invoke(new ListObjectParentPathsRequest(), new InvokeOptions(ListObjectParentPathsRequestMarshaller.getInstance(), ListObjectParentPathsResponseUnmarshaller.getInstance()));
DescribeCacheSubnetGroupsResponse response = (DescribeCacheSubnetGroupsResponse) invoke(new DescribeCacheSubnetGroupsRequest(), DescribeCacheSubnetGroupsRequestMarshaller.getInstance(), DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance());
void setSharedFormula(boolean flag) { this.field_5_options = SharedFormula.setShortBoolean(flag); }
boolean isReuseObjects() { return reuseObjects; }
IErrorNode AddErrorNode(IToken t) { ErrorNodeImpl errorNode = new ErrorNodeImpl(t); AddChild(errorNode); return errorNode; }
if (args.length > 0) throw new IllegalArgumentException(" " + args, new HashMap<String, String>()); LatvianStemFilterFactory();
return RemoveSourceIdentifierFromSubscriptionResponse = client.invoke(new RemoveSourceIdentifierFromSubscriptionRequest(), RemoveSourceIdentifierFromSubscriptionResponse.class, new InvokeOptions().withRequestMarshaller(RemoveSourceIdentifierFromSubscriptionRequestMarshaller.getInstance()).withResponseUnmarshaller(RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.getInstance()));
ForName TokenFilterFactory { } ) , ( ; return args name String Map<String, String> ) , ( newInstance . loader > String , String <
} { : ) ( AddAlbumPhotosRequest , Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " " " " " ;
return invoke(new GetThreatIntelSetRequest(), new InvokeOptions().withRequestMarshaller(GetThreatIntelSetRequestMarshaller.getInstance()).withResponseUnmarshaller(GetThreatIntelSetResponseUnmarshaller.getInstance()), GetThreatIntelSetResponse.class);
return new Binary.AndTreeFilter(Clone.a.clone(), Clone.b.clone());
return o instanceof Object;
protected boolean hasArray() { return protectedHasArray; }
return invoke(new UpdateContributorInsightsRequest(), new InvokeOptions(UpdateContributorInsightsRequestMarshaller.getInstance(), UpdateContributorInsightsResponseUnmarshaller.getInstance()));
UnwriteProtectWorkbook void { } () { records.remove(fileShare.writeProtect(null, null, null, null)); }
} { ; SolrSynonymParser expand ) analyzer , dedup ( base : expand . ) analyzer Analyzer , boolean expand , boolean dedup (
return invoke(new RequestSpotInstancesRequest(), new InvokeOptions().withRequestMarshaller(RequestSpotInstancesRequestMarshaller.getInstance()).withResponseUnmarshaller(RequestSpotInstancesResponseUnmarshaller.getInstance()), RequestSpotInstancesResponse.class);
return ObjectData[FindObjectRecord()];
return (GetContactAttributesResponse) invoke(new InvokeOptions(GetContactAttributesRequestMarshaller.getInstance(), GetContactAttributesResponseUnmarshaller.getInstance()), request, GetContactAttributesResponse.class);
return GetKey() + " " + GetValue();
ListTextTranslationJobsResponse response = (ListTextTranslationJobsResponse) invoke(new ListTextTranslationJobsRequest(), ListTextTranslationJobsResponse.class, new ListTextTranslationJobsRequestMarshaller(), new ListTextTranslationJobsResponseUnmarshaller());
return Invoke(new InvokeOptions(), GetContactMethodsRequestMarshaller.Instance, GetContactMethodsResponseUnmarshaller.Instance, request);
short LookupIndexByName(String name) { FunctionMetadata fd = FunctionMetadataIndex.GetInstance().GetFunctionByNameInternal(name); if (fd == null) return -1; return (short) fd; }
return DescribeAnomalyDetectorsResponse.class.cast(invoke(new DescribeAnomalyDetectorsRequest(), new InvokeOptions(new DescribeAnomalyDetectorsRequestMarshaller(), new DescribeAnomalyDetectorsResponseUnmarshaller())));
return new ObjectId(changeId, message, InsertId);
long GetObjectSize(int typeHint, AnyObjectId objectId) { long sz = Copy.db.GetObjectSize(Copy.objectId, " "); if (sz < 0) { throw new MissingObjectException(objectId, OBJ_ANY); } if (typeHint == OBJ_ANY) { throw new MissingObjectException(objectId, OBJ_ANY); } return sz; }
return (ImportInstallationMediaResponse) instance.invoke(request, ImportInstallationMediaRequestMarshaller.getInstance(), ImportInstallationMediaResponseUnmarshaller.getInstance());
return client.invoke(new PutLifecycleEventHookExecutionStatusRequest(), new InvokeOptions().withRequestMarshaller(PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance()).withResponseUnmarshaller(PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance()), PutLifecycleEventHookExecutionStatusResponse.class);
} { ; double field_1_value = in1.readDouble(); in1
return Invoke(new InvokeOptions().withRequestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.getInstance()).withResponseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance()), request, GetFieldLevelEncryptionConfigResponse.class);
return (DescribeDetectorResponse) Instance.invoke(new DescribeDetectorRequest(), Instance.getRequestMarshaller(DescribeDetectorRequestMarshaller.options), Instance.getResponseUnmarshaller(DescribeDetectorResponseUnmarshaller.options));
return (ReportInstanceStatusResponse) invoke(new ReportInstanceStatusRequest(), Instance.ReportInstanceStatusRequestMarshaller.getInstance(), Instance.ReportInstanceStatusResponseUnmarshaller.getInstance(), new InvokeOptions());
return invoke(new DeleteAlarmRequest(), new InvokeOptions().withRequestMarshaller(DeleteAlarmRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteAlarmResponseUnmarshaller.getInstance()), DeleteAlarmResponse.class);
return new PortugueseStemFilter(input);
byte[] reserved = new byte[ENCODED_SIZE]; FtCblsSubRecord ftCblsSubRecord = new FtCblsSubRecord();
synchronized (object) { return c.remove(); }
return Invoke(new GetDedicatedIpRequest(), GetDedicatedIpResponse.class, new InvokeOptions(GetDedicatedIpRequestMarshaller.getInstance(), GetDedicatedIpResponseUnmarshaller.getInstance()));
return precedence + " ";
return invoke(new ListStreamProcessorsRequest(), new InvokeOptions(ListStreamProcessorsRequestMarshaller.getInstance(), ListStreamProcessorsResponseUnmarshaller.getInstance()), ListStreamProcessorsResponse.class);
DeleteLoadBalancerPolicyRequest(String policyName, String loadBalancerName) { String _policyName = policyName; String _loadBalancerName = loadBalancerName; }
} { ; WindowProtectRecord options _options ) options int (
new int[bufferSize];
return Invoke(new InvokeOptions(), GetOperationsRequestMarshaller.Instance, GetOperationsResponseUnmarshaller.Instance).apply(request);
CopyRawTo(void } { ) , ( ; ; ; ; ; o int b byte ) , , ( EncodeInt32.NB ) , , ( EncodeInt32.NB ) , , ( EncodeInt32.NB ) , , ( EncodeInt32.NB ) , , ( EncodeInt32.NB )[16 + o][12 + o][8 + o][4 + o);
WindowOneRecord field_9_tab_width_ratio, field_8_num_selected_tabs, field_7_first_visible_tab, field_6_active_sheet, field_5_options, field_4_height, field_3_width, field_2_v_hold, field_1_h_hold; { field_1_h_hold = in1.readShort(); field_2_v_hold = in1.readShort(); field_3_width = in1.readShort(); field_4_height = in1.readShort(); field_5_options = in1.readShort(); field_6_active_sheet = in1.readShort(); field_7_first_visible_tab = in1.readShort(); field_8_num_selected_tabs = in1.readShort(); field_9_tab_width_ratio = in1.readShort(); }
return StopWorkspacesResponse.class.cast(invoke(new StopWorkspacesRequest(), new InvokeOptions(StopWorkspacesRequestMarshaller.getInstance(), StopWorkspacesResponseUnmarshaller.getInstance())));
try { if (isOpen()) { try { truncate.channel(); } finally { try { dump(); } finally { if (isOpen()) { close.fos(); } } } } } finally { close.channel(); } } catch (IOException e) { throw e; }
return DescribeMatchmakingRuleSetsResponse.class.cast(Invoke(DescribeMatchmakingRuleSetsRequest.class, DescribeMatchmakingRuleSetsResponse.class, new InvokeOptions(DescribeMatchmakingRuleSetsRequestMarshaller.Instance.options, DescribeMatchmakingRuleSetsResponseUnmarshaller.Instance.options)));
return null; } String getPronunciation(int wordId, int surface, int off, int len, char[] charArray) {
String getPath() { return pathStr; }
double devsq(double[] v) { double m = 0, s = 0; int n = v.length; if (n == 0) return Double.NaN; for (int i = 0; i < n; ++i) m += v[i]; m /= n; for (int i = 0; i < n; ++i) s += (v[i] - m) * (v[i] - m); return s; }
return (DescribeResizeResponse) invoke(new DescribeResizeRequest(), DescribeResizeResponse.class, new InvokeOptions(DescribeResizeRequestMarshaller.getInstance(), DescribeResizeResponseUnmarshaller.getInstance()));
return passedThroughNonGreedyDecision; boolean hasPassedThroughNonGreedyDecision;
return end();
void traverse(ICellHandler handler, SimpleCellWalkContext ctx, int firstRow, int lastRow, int firstColumn, int lastColumn, int width) { for (int rowNumber = firstRow; rowNumber <= lastRow; ++rowNumber) { IRow currentRow = ctx.getSheet().getRow(rowNumber); if (currentRow == null) { continue; } for (int colNumber = firstColumn; colNumber <= lastColumn; ++colNumber) { ICell currentCell = currentRow.getCell(colNumber); if (currentCell == null) { if (!ctx.traverseEmptyCells()) { continue; } } if (!ctx.isEmpty(currentCell)) { handler.onCell(ctx, rowNumber, colNumber, currentCell, (rowNumber - firstRow) * width + (colNumber - firstColumn)); } } } }
int getReadIndex() { return _ReadIndex; }
return Term.CompareTo(other.Term) != 0 ? Term.CompareTo(other.Term) : (Boost == other.Boost ? 0 : (Boost > other.Boost ? 1 : -1));
Normalize(int[] s) { int len = s.length; for (int i = 0; i < len; ++i) { switch (s[i]) { case HAMZA_ABOVE: s[i] = 0; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case KEHEH: s[i] = KAF; break; case YEH_BARREE: case FARSI_YEH: s[i] = YEH; break; default: break; } } return StemmerUtil.Delete(s, len); }
out1.writeShort();
} {  ; DiagnosticErrorListener exactOnly ) exactOnly boolean ( exactOnly .
} { ; ; KeySchemaElement keyType _keyType attributeName _attributeName ) keyType KeyType , attributeName String (
return Invoke(new InvokeOptions(), GetAssignmentRequestMarshaller.Instance, GetAssignmentResponseUnmarshaller.Instance).GetAssignment(request);
boolean hasObject = (FindOffset() - 1 != AnyObjectId); return hasObject;
return allGroups; } } boolean allGroups() { return allGroups; setAllGroups groupingSearch;
void setMultiValued(String dimName, boolean v) { synchronized (this) { if (!fieldTypes.containsKey(dimName)) { fieldTypes.put(dimName, new DimConfig()); } DimConfig fieldType = fieldTypes.get(dimName); fieldType.isMultiValued = v; } }
int GetCellsVal() { int size = 0; for (char c : cells.Keys) { if (cmd.At(size) >= 0) { size++; } } return size; }
return invoke(new DeleteVoiceConnectorRequest(), new InvokeOptions().withRequestMarshaller(DeleteVoiceConnectorRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteVoiceConnectorResponseUnmarshaller.getInstance()), DeleteVoiceConnectorResponse.class);
return invoke(new DeleteLifecyclePolicyRequest(), new InvokeOptions().withRequestMarshaller(DeleteLifecyclePolicyRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteLifecyclePolicyResponseUnmarshaller.getInstance()), DeleteLifecyclePolicyResponse.class);
