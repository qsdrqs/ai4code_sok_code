void writeShort(ILittleEndianOutput out1) { out1.writeShort(); }
AddAll void } { ) ( ) ( if ) ; ; ( for ; ) ( if (src) { 0 != } { ++srcDirIdx < srcDirIdx int } { 0 == . ; tailBlkIdx . src ; tailDirIdx . src srcDirIdx ; return size . src BlockList Util . NGit AddAll AddAll 0 = > T < ) , , ( ) , , ( tailBlkIdx . src tailBlock . src ] [ directory . src
void writeByte(byte b) { if (outerInstance.upto == outerInstance.blockSize) { if (outerInstance.currentBlock != null) { outerInstance.blocks.add(outerInstance.currentBlock); } outerInstance.currentBlock = new byte[outerInstance.blockSize]; outerInstance.upto = 0; } outerInstance.currentBlock[outerInstance.upto++] = b; }
public ObjectId getObjectId() { return objectId; }
return invoke(new DeleteDomainEntryRequest(), new InvokeOptions(DeleteDomainEntryRequestMarshaller.getInstance(), DeleteDomainEntryResponseUnmarshaller.getInstance()));
return fst == null ? 0L : fst.GetSizeInBytes();
return msgB < 0 ? "" : new String(RawParseUtils.decode(RawParseUtils.parseEncoding(buffer), raw, msgB), enc);
POIFSFileSystem _root = null; ArrayList _documents = new ArrayList(); PropertyTable _property_table = new PropertyTable(new HeaderBlock()); HeaderBlock headerBlock = new HeaderBlock();
Init void } { ) ( ; ; ; ; ; address int address offset0 upto slice ) ( assert & address ) ( assert < upto BYTE_BLOCK_MASK ByteBlockPool null != slice ] [ Buffers . pool Length . slice >> address BYTE_BLOCK_SHIFT ByteBlockPool
return new SubmoduleAddCommand().setPath(path).call();
return ListIngestionsResponse.class.cast(invoke(new ListIngestionsRequest(), new InvokeOptions(new ListIngestionsRequestMarshaller(), new ListIngestionsResponseUnmarshaller())));
SwitchTo(QueryParserTokenManager) { this.stream = stream; int lexState; ICharStream stream; }
return invoke(new InvokeOptions().withRequestMarshaller(GetShardIteratorRequestMarshaller.getInstance()).withResponseUnmarshaller(GetShardIteratorResponseUnmarshaller.getInstance()), request, GetShardIteratorResponse.class);
Method(ModifyStrategyRequest.class, "POST", MethodType.POST);
if (in == null) { throw new IOException(); } try { synchronized (lock) { if (in.available() > 0 || in.hasRemaining()) { return; } } } catch (IOException e) { throw new IOException(); }
return _optRecord; } ) { EscherOptRecord getOptRecord(
public static int read(byte[] buffer, int offset, int length) { if (buffer == null) { throw new NullPointerException(); } if (length == 0) { return 0; } int copylen = Math.min(length, buffer.length - offset); for (int i = 0; i < copylen; i++) { buffer[offset + i] = (byte) (unchecked(buffer[i + offset])); } return copylen; }
NLPSentenceDetectorOp sentenceOp = new OpenNLPSentenceBreakIterator();
if (str != null) StringHelper.Sharpen.GetValueOf((Object) str).write();
throw new NotImplementedException(functionName, cause);
return nextEntry().value;
void readBytes() { int len, offset, b; boolean useBuffer; if (len > 0) { if (useBuffer && available > 0) { int available = bufferLength - bufferPosition; if (available <= 0) { refillBuffer(); available = bufferLength - bufferPosition; } if (available <= 0) throw new EndOfStreamException(); int toCopy = Math.min(len, available); System.arraycopy(buffer, bufferPosition, b, offset, toCopy); bufferPosition += toCopy; offset += toCopy; len -= toCopy; } if (len > 0) { long after = bufferStart + bufferPosition + len; if (after > Length) throw new EndOfStreamException(); System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } } }
return TagQueueResponse.class.cast(invoke(new InvokeOptions().setRequestMarshaller(TagQueueRequestMarshaller.getInstance()).setResponseUnmarshaller(TagQueueResponseUnmarshaller.getInstance()), request, TagQueueResponse.class));
throw new UnsupportedOperationException();
return (ModifyCacheSubnetGroup) invoke(new ModifyCacheSubnetGroupRequest(), new InvokeOptions(ModifyCacheSubnetGroupRequestMarshaller.getInstance(), ModifyCacheSubnetGroupResponseUnmarshaller.getInstance()));
void setParams(String params, StringTokenizer st, boolean ignoreCulture) { if (st.hasMoreTokens()) { params = st.nextToken() + " "; if (st.hasMoreTokens()) { params += st.nextToken() + " "; if (st.hasMoreTokens()) { params += st.nextToken(); } } } }
return invoke(new DeleteDocumentationVersionRequest(), new InvokeOptions().withRequestMarshaller(DeleteDocumentationVersionRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteDocumentationVersionResponseUnmarshaller.getInstance()), DeleteDocumentationVersionResponse.class);
if (!(obj instanceof FacetLabel)) return false; FacetLabel other = (FacetLabel) obj; if (this.Length != other.Length) return false; for (int i = 0; i < this.Length; i++) if (!this.Components[i].equals(other.Components[i])) return false; return true;
return Instance.invoke(new GetInstanceAccessDetailsRequest(), GetInstanceAccessDetailsResponse.class, Instance.GetInstanceAccessDetailsRequestMarshaller.options, Instance.GetInstanceAccessDetailsResponseUnmarshaller.options);
return shape = new HSSFPolygon((HSSFChildAnchor) shape.getParent().getAnchor().addToShape(shape.createAnchor()));
return GetBoundSheetRec(sheetIndex).getSheetName();
return invoke(new GetDashboardRequest(), new InvokeOptions(GetDashboardRequestMarshaller.getInstance(), GetDashboardResponseUnmarshaller.getInstance()));
return (AssociateSigninDelegateGroupsWithAccountResponse) invoke(new AssociateSigninDelegateGroupsWithAccountRequest(), AssociateSigninDelegateGroupsWithAccountResponse.class, new InvokeOptions(Instance.options.RequestMarshaller.AssociateSigninDelegateGroupsWithAccountRequestMarshaller, Instance.options.ResponseUnmarshaller.AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller));
void AddMultipleBlanks() { for (int j = 0; j < mbr.NumColumns; ++j) { MulBlankRecord mbr = new MulBlankRecord(br.Row, br.FirstColumn + j, br.Row.GetXFAt(0)); mbr.InsertCell(j, BlankRecord.XFIndex); } }
while (k >= 0) { sb.append("\\").append(lang).append("\\").append("java").append("\\").append(sb).append("\\").append(sb).append("\\").append(StringHelper.Sharpen.Substring(StringHelper.Sharpen.Substring(@string.IndexOf("\\") + 2, k).ToString())); return; }
throw new java.nio.ReadOnlyBufferException(); java.nio.ByteBuffer.putInt(value);
for (int r = 0; r < _nRows; ++r) { for (int c = 0; c < _nColumns; ++c) { Object[][] values2d = new Object[_nRows][_nColumns]; Object[] rowData = new Object[values2d.length]; values2d[r][c] = rowData[c] = _arrayValues[GetValueIndex(r, c)]; } }
return (GetIceServerConfigResponse) invoke(new GetIceServerConfigRequest(), GetIceServerConfigResponse.class, Instance.getGetIceServerConfigRequestMarshaller().options, Instance.getGetIceServerConfigResponseUnmarshaller().options);
return new StringBuilder().append(getClass().getName()).append(" ").append(getValueAsString()).append(" ").toString();
return field.toString() + " " + _parentQuery.toString() + " ";
refCount.incrementAndGet();
return UpdateConfigurationSetSendingEnabledResponse.class.cast(invoke(new UpdateConfigurationSetSendingEnabledRequest(), new InvokeOptions(new UpdateConfigurationSetSendingEnabledRequestMarshaller(), new UpdateConfigurationSetSendingEnabledResponseUnmarshaller())));
return GetXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE;
void multiplyByPowerOfTen() { int pow10; if (pow10 < 0) { Math.Abs(TenPower.GetInstance(tp.divisor.tp.divisorShift.tp.multiplicand.tp.multiplierShift, tp)); } else { } }
return new StringBuilder().append(Path.DirectorySeparatorChar).append(GetComponent()).append(Path.DirectorySeparatorChar).append(builder).append(builder.length() - 1).append(new StringBuilder().append(Path.DirectorySeparatorChar).append(i).append(builder.length()).append(i++).toString()).toString();
fetcher = new ECSMetadataServiceCredentialsFetcher().setRoleName(fetcher);
void setProgressMonitor(ProgressMonitor pm) { this.progressMonitor = pm; }
if (!First) { if (!Eof) { ParseEntry(); ptr = 0; } } Reset();
if (!(start >= 0)) { throw new java.util.NoSuchElementException(); } return iterator.previousIndex();
return newPrefix; } } String getNewPrefix() {
for (int i = 0; i < mSize; ++i) { if (mValues[i] == value) { return i; } } return -1;
public static List<CharsRef> uniqueStems(List<CharsRef> stems, CharArraySet dictionary) { List<CharsRef> deduped = new ArrayList<>(); for (CharsRef s : stems) { if (!dictionary.contains(s)) { dictionary.add(s); deduped.add(s); } } return deduped; }
return invoke(new GetGatewayResponsesRequest(), new InvokeOptions(GetGatewayResponsesRequestMarshaller.getInstance(), GetGatewayResponsesResponseUnmarshaller.getInstance()));
void setPosition() { int currentBlockIndex = (int) (position & outerInstance.blockMask); int currentBlock = (int) (position >> outerInstance.blockBits); long currentBlockUpto = position; }
int s = (int) Math.min(Math.max(ptr, s), s);
BootstrapActionConfig bootstrapActionConfig = _bootstrapActionConfig;
void serialize(ILittleEndianOutput out1) { if (field_7_padding != null) { out1.writeShort(field_5_hasMultibyte ? 0x01 : 0x00); out1.writeShort(field_6_author.length()); StringUtil.putCompressedUnicode(field_6_author, out1); } else { out1.writeShort(field_5_hasMultibyte ? 0x01 : 0x00); out1.writeShort(field_6_author.length()); StringUtil.putUnicodeLE(field_6_author, out1); } }
return string.lastIndexOf(@string);
return E addLastImpl(object);
ConfigSnapshot ConfigSnapshot; String section, subsection; while (true) { if (!state.Get().CompareAndSet(src, res, UnsetSection)) { break; } }
String getTagName() { return tagName; }
subrecords.Insert(index, element); // Java equivalent: subrecords.add(index, element);
synchronized (object) { return c.remove(remove); }
return new DoubleMetaphoneFilter(input, TokenStream.class);
return InCoreLength();
void setValue(boolean newValue) { value = newValue; }
} { ; ; Pair newSource oldSource ) newSource ContentSource , oldSource ContentSource ( newSource . oldSource .
int get(int i) { if (i <= count) return entries[i]; throw new IndexOutOfBoundsException(); }
new CreateRepoRequest(Method.PUT, UriPattern.METHOD_TYPE, " ", " ", " ", " ", " ", " ", " ");
return deltaBaseAsOffset;
if (lastLink != null) { if (modCount != expectedModCount) { throw new ConcurrentModificationException(); } else { if (lastLink.next == null && lastLink.previous == null) { throw new IllegalStateException(); } else { list.modCount++; list.size--; if (lastLink.previous != null) { lastLink.previous.next = lastLink.next; } if (lastLink.next != null) { lastLink.next.previous = lastLink.previous; } lastLink = null; expectedModCount++; } } }
return MergeShardsResponse.class.cast(invoke(new InvokeOptions(MergeShardsRequestMarshaller.getInstance(), MergeShardsResponseUnmarshaller.getInstance()), request, MergeShardsResponse.class));
return invoke(new AllocateHostedConnectionRequest(), new InvokeOptions().withRequestMarshaller(AllocateHostedConnectionRequestMarshaller.getInstance()).withResponseUnmarshaller(AllocateHostedConnectionResponseUnmarshaller.getInstance()), AllocateHostedConnectionResponse.class);
public int getBeginIndex() { return start; }
return query.GetTerms(WeightedTerm.class);
throw new java.nio.ReadOnlyBufferException();
void decode(int iterations, int[] values, int valuesOffset, byte[] blocks, int blocksOffset) { for (int i = 0; i < iterations; i++) { int byte0 = blocks[blocksOffset++] & 0xFF; int byte1 = blocks[blocksOffset++] & 0xFF; int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (byte0 << 2) | (byte1 >> 6); values[valuesOffset++] = ((byte1 >> 2) & 0x0F) | (byte2 >> 4); values[valuesOffset++] = byte2 & 0x3F; } }
if (s == null) throw new IllegalArgumentException(Constants.DOT_GIT); String[] elements = s.split(Constants.DOT_GIT_EXT); if (elements.length == 0 || elements.length == 1) throw new IllegalArgumentException(); if (elements[elements.length - 1].equals("")) { String result = elements[elements.length - 2] + File.separatorChar + elements[elements.length - 1]; if (result.endsWith(Constants.DOT_GIT_EXT)) return result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); else throw new IllegalArgumentException(); } else if (elements[elements.length - 1].equals(Constants.LOCAL_FILE)) return elements[elements.length - 2] + File.separatorChar + elements[elements.length - 1]; else throw new IllegalArgumentException();
return DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance().unmarshall(DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance().marshall(request, new InvokeOptions()));
return AccessSecret; } } String GetAccessKeySecret()
return CreateVpnConnectionResponse.createVpnConnectionResponse(Instance.invoke(CreateVpnConnectionRequestMarshaller.getInstance(), CreateVpnConnectionResponseUnmarshaller.getInstance(), request));
return DescribeVoicesResponse.class.cast(invoke(DescribeVoicesRequest.class, new InvokeOptions(DescribeVoicesRequestMarshaller.getInstance(), DescribeVoicesResponseUnmarshaller.getInstance())));
return invoke(new ListMonitoringExecutionsRequest(), new InvokeOptions(ListMonitoringExecutionsRequestMarshaller.getInstance(), ListMonitoringExecutionsResponseUnmarshaller.getInstance()));
DescribeJobRequest(String jobId, String vaultName) { this.jobId = jobId; this.vaultName = vaultName; }
return escherRecords[index];
return invoke(new GetApisRequest(), new InvokeOptions().withRequestMarshaller(GetApisRequestMarshaller.getInstance()).withResponseUnmarshaller(GetApisResponseUnmarshaller.getInstance()), GetApisResponse.class);
return DeleteSmsChannelResponse.class.cast(invoke(new DeleteSmsChannelRequest(), new InvokeOptions(DeleteSmsChannelRequestMarshaller.getInstance(), DeleteSmsChannelResponseUnmarshaller.getInstance())));
return trackingRefUpdate; } } TrackingRefUpdate getTrackingRefUpdate() {
System.out.println(Boolean.toString(b));
return GetChildren().get(GetChild());
int workdirTreeIndex(NotIgnoredFilter workdirTreeIndex) { }
AreaRecord field_1_formatFlags = in1.readShort();
Protocol HTTPS = ProtocolType.GetThumbnailRequest();
return DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance().unmarshall(invoke(new DescribeTransitGatewayVpcAttachmentsRequest(), new InvokeOptions(DescribeTransitGatewayVpcAttachmentsRequestMarshaller.getInstance(), DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance())));
return invoke(new PutVoiceConnectorStreamingConfigurationRequest(), new InvokeOptions(PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance(), PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance()));
return prefixToOrdRange.TryGetValue(OrdRange, out result) ? result : null;
return (startIndex >= 0 && startIndex < string.length()) ? String.format("%s %s", symbol.getName(), symbol.getSymbolSize()) : "";
return peekFirstImpl();
return CreateWorkspacesResponse.createWorkspacesResponse(Instance.invoke(CreateWorkspacesRequest.class, CreateWorkspacesResponse.class, new InvokeOptions(Instance.RequestMarshaller.options, Instance.ResponseUnmarshaller.options)));
NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); return rec; rec.field_1_formatIndex = rec.field_1_formatIndex;
return (DescribeRepositoriesResponse) invoke(new DescribeRepositoriesRequest(), DescribeRepositoriesRequestMarshaller.getInstance(), DescribeRepositoriesResponseUnmarshaller.getInstance(), new InvokeOptions());
SparseIntArray sparseIntArray = new SparseIntArray(initialCapacity); int[] mKeys = new int[ArrayUtils.idealIntArraySize(initialCapacity)]; int[] mValues = new int[ArrayUtils.idealIntArraySize(initialCapacity)]; int mSize = 0;
return new HyphenatedWordsFilter(inputTokenStream);
return CreateDistributionWithTagsResponse response = client.invoke(new CreateDistributionWithTagsRequest(), CreateDistributionWithTagsResponse.class, new InvokeOptions(CreateDistributionWithTagsRequestMarshaller.getInstance(), CreateDistributionWithTagsResponseUnmarshaller.getInstance()));
throw new java.io.FileNotFoundException("NotImplementedException");
return DeleteWorkspaceImageResponseUnmarshaller.getInstance().unmarshall(invoke(new DeleteWorkspaceImageRequest(), DeleteWorkspaceImageRequestMarshaller.getInstance(), DeleteWorkspaceImageResponseUnmarshaller.getInstance()));
int ToHex(long value) { return (int) value; }
return invoke(new UpdateDistributionRequest(), new InvokeOptions(), UpdateDistributionResponse.class, UpdateDistributionRequestMarshaller.getInstance(), UpdateDistributionResponseUnmarshaller.getInstance());
public static HSSFColor getColor(short index, HSSFPalette palette) { if (index == HSSFColor.AUTOMATIC.index) { return HSSFColor.AUTOMATIC; } HSSFColor color = palette.getColor(index); if (color != null) { return color; } return null; }
throw new NotImplementedFunctionException(ValueEval.class, operands, srcRow, srcCol);
void serialize(ILittleEndianOutput out1) { out1.writeShort((short) field_1_number_crn_records); out1.writeShort((short) field_2_sheet_table_index); }
return new DescribeDBEngineVersionsResponse(DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest()));
} { ; ; FormatRun(fontIndex, character) fontIndex short, character short (_fontIndex._character);
byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; for (int i = 0, resultIndex = 0; i < length; i++, resultIndex += 2) { char ch = chars[offset + i]; result[resultIndex] = (byte) (ch >> 8); result[resultIndex + 1] = (byte) ch; } return result; }
return invoke(new UploadArchiveRequest(), new InvokeOptions(), Instance.UploadArchiveRequestMarshaller.options, Instance.UploadArchiveResponseUnmarshaller.options);
return (List<IToken>) GetHiddenTokensToLeft(tokenIndex - 1);
@Override public boolean equals(Object obj) { if (this == obj) return true; if (obj == null || getClass() != obj.getClass()) return false; AutomatonQuery other = (AutomatonQuery) obj; if (!m_term.equals(other.m_term)) return false; return m_compiled.equals(other.m_compiled); }
return spanQueries.size() == 1 ? spanQueries.get(0) : new SpanOrQuery(spanQueries.stream().map(wsq -> wsq.getKey().boost(wsq.getValue())).toArray(SpanQuery[]::new));
return new StashCreateCommand();
return byName.containsKey(fieldName) ? byName.get(fieldName) : null;
return invoke(new DescribeEventSourceRequest(), new InvokeOptions(DescribeEventSourceRequestMarshaller.getInstance(), DescribeEventSourceResponseUnmarshaller.getInstance()), DescribeEventSourceResponse.class);
return (GetDocumentAnalysisResponse) invoke(new GetDocumentAnalysisRequest(), GetDocumentAnalysisResponse.class, new InvokeOptions(GetDocumentAnalysisRequestMarshaller.getInstance().options, GetDocumentAnalysisResponseUnmarshaller.getInstance().options));
return CancelUpdateStackResponse.class.cast(invoke(new CancelUpdateStackRequest(), new InvokeOptions(CancelUpdateStackRequestMarshaller.getInstance(), CancelUpdateStackResponseUnmarshaller.getInstance())));
return ModifyLoadBalancerAttributesResponse.class.cast(invoke(new ModifyLoadBalancerAttributesRequest(), new InvokeOptions(new ModifyLoadBalancerAttributesRequestMarshaller(), new ModifyLoadBalancerAttributesResponseUnmarshaller())));
return SetInstanceProtectionResponse.class.cast(instance.invoke(new SetInstanceProtectionRequest(), SetInstanceProtectionRequestMarshaller.getInstance(), SetInstanceProtectionResponseUnmarshaller.getInstance()));
return ModifyDBProxyResponseUnmarshaller.getInstance().unmarshall(invoke(new ModifyDBProxyRequestMarshaller().marshall(request), new InvokeOptions()));
if (count == outputs.length) { outputs = Arrays.copyOf(outputs, ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF)); posLengths = Arrays.copyOf(posLengths, ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)); endOffsets = Arrays.copyOf(endOffsets, ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)); } outputs[count] = new CharsRef(); posLengths[count] = len; endOffsets[count] = endOffset; count++; if (count == null) { return; }
Protocol.FetchLibrariesRequest(ProtocolType.HTTPS);
return Objects.exists();
try (FilterOutputStream out = ...) { ... }
new ScaleClusterRequest(Method.PUT, MethodType.PUT, UriPattern.SCALE_CLUSTER);
return new DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);
return (ListObjectParentPathsResponse) invoke(new ListObjectParentPathsRequest(), ListObjectParentPathsResponse.class, new InvokeOptions(ListObjectParentPathsRequestMarshaller.getInstance(), ListObjectParentPathsResponseUnmarshaller.getInstance()));
return DescribeCacheSubnetGroupsResponse.class.cast(invoke(new DescribeCacheSubnetGroupsRequest(), new InvokeOptions(DescribeCacheSubnetGroupsRequestMarshaller.getInstance(), DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance())));
sharedFormula.SetShortBoolean(field_5_options, flag);
boolean isReuseObjects() { return reuseObjects; }
return (IErrorNode)AddChild(new ErrorNodeImpl((IToken)badToken));
if (args.length > 0) { throw new IllegalArgumentException("System.ArgumentException: " + args); }
return RemoveSourceIdentifierFromSubscriptionResponse.class.cast(invoke(new RemoveSourceIdentifierFromSubscriptionRequest(), new InvokeOptions(new RemoveSourceIdentifierFromSubscriptionRequestMarshaller(), new RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller())));
return TokenFilterFactory.forName(name, args, loader.newInstance(Map<String, String>.class));
} { : ) ( AddAlbumPhotosRequest , Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " " " " ;
return invoke(new GetThreatIntelSetRequest(), new InvokeOptions().withRequestMarshaller(GetThreatIntelSetRequestMarshaller.getInstance()).withResponseUnmarshaller(GetThreatIntelSetResponseUnmarshaller.getInstance()));
return new Binary.AndTreeFilter(Clone.a.clone(), Clone.b.clone());
return o instanceof Object && this.equals(o);
protected boolean hasArray() { return hasArray; }
return (UpdateContributorInsightsResponse) invoke(new UpdateContributorInsightsRequest(), UpdateContributorInsightsResponse.class, UpdateContributorInsightsRequestMarshaller.getInstance(), UpdateContributorInsightsResponseUnmarshaller.getInstance());
records.remove(fileShare == null ? writeProtect == null ? null : writeProtect : null);
public SolrSynonymParser(boolean expand, Analyzer analyzer, boolean dedup) { super(expand, analyzer, dedup); }
return invoke(new RequestSpotInstancesRequest(), new RequestSpotInstancesResponseUnmarshaller(), new RequestSpotInstancesRequestMarshaller(), new InvokeOptions());
return ObjectData[FindObjectRecord()];
return invoke(new GetContactAttributesRequest(), new InvokeOptions(GetContactAttributesRequestMarshaller.getInstance(), GetContactAttributesResponseUnmarshaller.getInstance()));
return getKey() + " " + getValue();
return ListTextTranslationJobsResponse response = client.invoke(new ListTextTranslationJobsRequest(), ListTextTranslationJobsResponse.class, new InvokeOptions().withRequestMarshaller(new ListTextTranslationJobsRequestMarshaller()).withResponseUnmarshaller(new ListTextTranslationJobsResponseUnmarshaller()));
return invoke(new GetContactMethodsRequest(), new InvokeOptions(GetContactMethodsRequestMarshaller.getInstance(), GetContactMethodsResponseUnmarshaller.getInstance()));
short LookupIndexByName(String name) { FunctionMetadata fd = FunctionMetadataIndex.getInstance().getFunctionByNameInternal(name); if (fd == null) return -1; return (short) fd; }
return DescribeAnomalyDetectorsResponse.class.cast(InvokeOptions.invoke(new DescribeAnomalyDetectorsRequest(), DescribeAnomalyDetectorsRequestMarshaller.getInstance(), DescribeAnomalyDetectorsResponseUnmarshaller.getInstance()));
return new ObjectId(changeId, message, InsertId);
if (sz < 0) { throw new MissingObjectException(objectId, ""); } if (typeHint == OBJ_ANY) { return sz; } throw new MissingObjectException(objectId, db.GetObjectSize(objectId, Copy.objectId));
return Instance.invoke(ImportInstallationMediaRequest.class, ImportInstallationMediaResponse.class, new InvokeOptions(Instance.options.RequestMarshaller, Instance.options.ResponseUnmarshaller));
return client.invoke(new PutLifecycleEventHookExecutionStatusRequest(), new InvokeOptions().withRequestMarshaller(PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance()).withResponseUnmarshaller(PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance()), PutLifecycleEventHookExecutionStatusResponse.class);
in1 = in1.readDouble(); double field_1_value = NumberPtg; }
return (GetFieldLevelEncryptionConfigResponse) invoke(new GetFieldLevelEncryptionConfigRequest(), InvokeOptions.builder().requestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.getInstance()).responseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance()).build());
return DescribeDetectorResponseUnmarshaller.getInstance().unmarshall(invoke(new DescribeDetectorRequest(), DescribeDetectorRequestMarshaller.getInstance(), DescribeDetectorResponseUnmarshaller.getInstance()));
return Instance.invoke(new ReportInstanceStatusRequest(), ReportInstanceStatusResponse.class, Instance.getRequestMarshaller(ReportInstanceStatusRequest.class), Instance.getResponseUnmarshaller(ReportInstanceStatusResponse.class));
return invoke(new InvokeOptions(), DeleteAlarmRequestMarshaller.getInstance(), DeleteAlarmResponseUnmarshaller.getInstance(), request, DeleteAlarmResponse.class);
return new PortugueseStemFilter(inputTokenStream);
byte[] reserved = new byte[ENCODED_SIZE]; FtCblsSubRecord obj = new FtCblsSubRecord();
synchronized (object) { return c.remove(remove); }
return invoke(new GetDedicatedIpRequest(), new InvokeOptions(GetDedicatedIpRequestMarshaller.getInstance(), GetDedicatedIpResponseUnmarshaller.getInstance()));
return precedence + " "; } } @Override public String toString() {
return invoke(new ListStreamProcessorsRequest(), new InvokeOptions().withRequestMarshaller(ListStreamProcessorsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListStreamProcessorsResponseUnmarshaller.getInstance()), ListStreamProcessorsResponse.class);
DeleteLoadBalancerPolicyRequest(String policyName, String loadBalancerName) { String _policyName = policyName; String _loadBalancerName = loadBalancerName; }
int options(WindowProtectRecord options, WindowProtectRecord _options) { }
int[] buffer = new int[bufferSize]; UnbufferedCharStream data = new UnbufferedCharStream(bufferSize);
return invoke(new GetOperationsRequest(), new InvokeOptions(), GetOperationsRequestMarshaller.getInstance(), GetOperationsResponseUnmarshaller.getInstance());
CopyRawTo(void } { ) , ( ; ; ; ; ; o int b byte ) , , ( EncodeInt32.NB ) , , ( EncodeInt32.NB ) , , ( EncodeInt32.NB ) , , ( EncodeInt32.NB ) , , ( EncodeInt32.NB )[16 + o][12 + o][8 + o][4 + o]);
WindowOneRecord field_9_tab_width_ratio = in1.readShort(), field_8_num_selected_tabs = in1.readShort(), field_7_first_visible_tab = in1.readShort(), field_6_active_sheet = in1.readShort(), field_5_options = in1.readShort(), field_4_height = in1.readShort(), field_3_width = in1.readShort(), field_2_v_hold = in1.readShort(), field_1_h_hold = in1.readShort();
return StopWorkspacesResponse.class.cast(invoke(new InvokeOptions().withRequestMarshaller(StopWorkspacesRequestMarshaller.getInstance()).withResponseUnmarshaller(StopWorkspacesResponseUnmarshaller.getInstance()), request, StopWorkspacesResponse.class));
try { if (isOpen()) { try { channel.truncate(); } finally { channel.close(); } } } finally { try { dump(); } finally { fos.close(); } }
return DescribeMatchmakingRuleSetsResponse.class.cast(Invoke(DescribeMatchmakingRuleSetsRequest.class, new InvokeOptions(DescribeMatchmakingRuleSetsRequestMarshaller.getInstance().options(), DescribeMatchmakingRuleSetsResponseUnmarshaller.getInstance().options())));
String getPronunciation(int wordId, int surface, int off, int len) { return null; }
String getPath() { return pathStr; }
double devsq(double[] v) { int n = v.length; if (n == 0) return Double.NaN; double m = 0, s = 0; for (int i = 0; i < n; ++i) m += v[i]; m /= n; for (int i = 0; i < n; ++i) s += (v[i] - m) * (v[i] - m); return s; }
return (DescribeResizeResponse) invoke(new InvokeOptions(DescribeResizeRequestMarshaller.getInstance(), DescribeResizeResponseUnmarshaller.getInstance()), request, DescribeResizeResponse.class);
return passedThroughNonGreedyDecision; boolean hasPassedThroughNonGreedyDecision;
return (end); } { int end
void traverse(ICellHandler handler, int firstRow, int lastRow, int firstColumn, int lastColumn, SimpleCellWalkContext ctx) { for (int rowNumber = firstRow; rowNumber <= lastRow; ++rowNumber) { IRow currentRow = ctx.getSheet().getRow(rowNumber); if (currentRow == null) { if (!ctx.traverseEmptyCells()) continue; } for (int colNumber = firstColumn; colNumber <= lastColumn; ++colNumber) { ICell currentCell = (currentRow == null) ? null : currentRow.getCell(colNumber); if (currentCell == null) { if (!ctx.traverseEmptyCells()) continue; } if (!ctx.isEmpty(currentCell)) { handler.onCell(ctx, rowNumber, colNumber, currentCell); } else { if (!ctx.traverseEmptyCells()) continue; } } } }
int getReadIndex() { return _ReadIndex; }
return this.Boost == other.Boost ? (this.Term.equals(other.Term) ? 0 : this.Term.compareTo(other.Term)) : Float.compare(this.Boost, other.Boost);
for (int i = 0; i < len; ++i) { switch (s[i]) { case HAMZA_ABOVE: case HEH_GOAL: case HEH_YEH: case KEHEH: case YEH_BARREE: case FARSI_YEH: s[i] = YEH; break; default: break; } } return len; }
void writeShort(ILittleEndianOutput out1) { }
} { ; DiagnosticErrorListener exactOnly) exactOnly boolean (exactOnly.
KeySchemaElement(KeyType keyType, String attributeName) { this._keyType = keyType; this._attributeName = attributeName; }
return invoke(new GetAssignmentRequest(), new InvokeOptions(), GetAssignmentRequestMarshaller.getInstance(), GetAssignmentResponseUnmarshaller.getInstance());
return (FindOffset(AnyObjectId) != 1) ? true : false;
return allGroups.allGroups(allGroups);
if (!fieldTypes.containsKey(fieldType)) { fieldTypes.put(fieldType, new DimConfig()); } else { DimConfig v = fieldTypes.get(fieldType); synchronized (v) { v.IsMultiValued = true; } }
int getCellsVal() { int size = 0; for (char c : keys) { if (cells >= 0) { size++; } } return size; }
return invoke(new DeleteVoiceConnectorRequest(), new InvokeOptions(DeleteVoiceConnectorRequestMarshaller.getInstance().getOptions(), DeleteVoiceConnectorResponseUnmarshaller.getInstance().getOptions()), DeleteVoiceConnectorResponse.class);
return invoke(new DeleteLifecyclePolicyRequest(), new InvokeOptions(DeleteLifecyclePolicyRequestMarshaller.getInstance(), DeleteLifecyclePolicyResponseUnmarshaller.getInstance()), DeleteLifecyclePolicyResponse.class);
