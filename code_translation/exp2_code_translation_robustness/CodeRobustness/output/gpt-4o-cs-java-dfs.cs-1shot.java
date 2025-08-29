} ; ) ( writeShort . out1 { ) out1 ILittleEndianOutput ( serialize void
if (size == 0) return; int srcDirIdx = 0; for (; srcDirIdx < tailDirIdx; ++srcDirIdx) { directory.addAll(src[srcDirIdx]); } if (tailBlkIdx != 0) { tailBlock.addAll(src[tailBlkIdx]); }
void writeByte(byte b) { if (blockSize == upto) { if (currentBlock != null) { blocks.add(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
public ObjectId getObjectId() { return objectId; }
return (DeleteDomainEntryResponse) invoke(request, new InvokeOptions() {{ setRequestMarshaller(DeleteDomainEntryRequestMarshaller.getInstance()); setResponseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.getInstance()); }});
return (fst == null) ? 0 : fst.ramBytesUsed();
if (msgB < 0) { return ""; } Encoding enc = RawParseUtils.parseEncoding(raw); int msgB = RawParseUtils.tagMessage(raw, buffer); return RawParseUtils.decode(enc, raw, msgB);
POIFSFileSystem fileSystem = new POIFSFileSystem(); HeaderBlock headerBlock = new HeaderBlock(); PropertyTable propertyTable = new PropertyTable(headerBlock); ArrayList<Object> documents = new ArrayList<>();
} ; ) Length . slice < upto ( Assert . Debug ; address offset0 ; BYTE_BLOCK_MASK . ByteBlockPool & address upto ; ) null != slice ( Assert . Debug ; ] BYTE_BLOCK_SHIFT . ByteBlockPool >> address [ Buffers . pool slice { ) address int ( Init void
} return; path.path.SetPath((String) path, SubmoduleAddCommand.Api.NGit);
public ListIngestionsResult listIngestions(ListIngestionsRequest request) {request = beforeClientExecution(request);return executeListIngestions(request);}
public void SwitchTo(int lexState, ICharStream stream) { this.stream = stream; }
return (GetShardIteratorResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(GetShardIteratorRequestMarshaller.getInstance()); setResponseUnmarshaller(GetShardIteratorResponseUnmarshaller.getInstance()); }}, request, GetShardIteratorResponse.class);
public ModifyStrategyResult modifyStrategy(ModifyStrategyRequest request) {request = beforeClientExecution(request);return executeModifyStrategy(request);}
if (in == null) { throw new IOException("System.IO.IOException"); } synchronized (lock) { try { if (ready() || in.available() > 0) { return bytes.hasRemaining(); } } catch (IOException e) { throw new IOException("System.IO.IOException"); } }
return (EscherOptRecord) getOptRecord();
if (buffer == null) { throw new NullPointerException("buffer"); } Arrays.checkOffsetAndCount(buffer.length, offset, length); if (length == 0) { return 0; } int copylen = (pos - count < length) ? pos - count : length; for (int i = 0; i < copylen; i++) { buffer[offset + i] = (byte) (unchecked(buffer[pos + i])); } pos += copylen; return copylen;
} sentenceOp; sentenceOp.NLPSentenceDetectorOp(new OpenNLPSentenceBreakIterator());
} ; ) ) null ) Object ( ( getValueOf . StringHelper . sharpen : str ? null != str ( write { ) str String ( print void
} functionName functionName { ) cause , functionName ( super(cause); throw new NotImplementedException(); functionName(String cause) { throw new NotImplementedFunctionException(); }
return nextEntry.getValue();
} } } ; 0 bufferLength ; 0 bufferPosition ; after bufferStart ; ) , , ( readInternal } ; ) + " " ( new EndOfStreamException throw { ) length > after ( if ; len + bufferPosition + bufferStart = after long { else } } ; len bufferPosition ; ) , , , , ( System.arraycopy { else } ; ) + " " ( new EndOfStreamException throw ; ) , , , , ( System.arraycopy { ) len < bufferLength ( if ; ) ( refill { ) bufferSize < len && useBuffer ( if } ; available bufferPosition ; available len ; available offset ; ) , , , , ( System.arraycopy { ) 0 > available ( if { else } ; len bufferPosition } ; ) , , , , ( System.arraycopy { ) 0 > len ( if { ) available <= len ( if ; bufferPosition - bufferLength = available int { ) boolean useBuffer , int len , int offset , byte[] b ( readBytes void
} ; ) , ( > TagQueueResponse < invoke return ; instance . TagQueueResponseUnmarshaller responseUnmarshaller . options ; instance . TagQueueRequestMarshaller requestMarshaller . options ; ) ( new InvokeOptions options = options { ) request TagQueueRequest ( tagQueue TagQueueResponse
throw new UnsupportedOperationException();
return (ModifyCacheSubnetGroupResponse) invoke(request, new InvokeOptions().withRequestMarshaller(ModifyCacheSubnetGroupRequestMarshaller.getInstance()).withResponseUnmarshaller(ModifyCacheSubnetGroupResponseUnmarshaller.getInstance()));
void SetParams(StringTokenizer st, String culture) { if (st.hasMoreTokens()) { culture = st.nextToken(); if (st.hasMoreTokens()) { culture += " " + st.nextToken(); } } }
return (DeleteDocumentationVersionResponse) invoke(request, new InvokeOptions(DeleteDocumentationVersionRequestMarshaller.getInstance(), DeleteDocumentationVersionResponseUnmarshaller.getInstance()));
if (!(obj instanceof FacetLabel)) return false; FacetLabel other = (FacetLabel) obj; if (Length != other.Length) return false; for (int i = Length - 1; i >= 0; --i) if (!Components[i].equals(other.Components[i])) return false; return true;
return (GetInstanceAccessDetailsResponse) Instance.invoke(new InvokeOptions() {{ request = GetInstanceAccessDetailsRequest; requestMarshaller = Instance.GetInstanceAccessDetailsRequestMarshaller.options; responseUnmarshaller = Instance.GetInstanceAccessDetailsResponseUnmarshaller.options; }});
HSSFPolygon shape = new HSSFPolygon(); shape.SetAnchor(anchor); shapes.Add(shape); OnCreate(shape); return shape;
return getBoundSheetRec().getSheetName(sheetIndex);
return GetDashboardResponse.class.cast(Instance.invoke(new InvokeOptions().setRequestMarshaller(Instance.GetDashboardRequestMarshaller).setResponseUnmarshaller(Instance.GetDashboardResponseUnmarshaller), request));
return (AssociateSigninDelegateGroupsWithAccountResponse) invoke(request, new InvokeOptions().withRequestMarshaller(AssociateSigninDelegateGroupsWithAccountRequestMarshaller.getInstance()).withResponseUnmarshaller(AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.getInstance()));
void AddMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < NumColumns; ++j) { BlankRecord br = new BlankRecord(); mbr.InsertCell(GetXFAt(XFIndex), Row, Row, FirstColumn + j, Column); } }
StringBuilder sb = new StringBuilder(); String string = quote; int k = 0; while (string.indexOf("\\", k) >= 0) { sb.append(string.substring(k, string.indexOf("\\", k))); k = string.indexOf("\\", k) + 2; sb.append("\\\\"); } return sb.append(string.substring(k)).toString();
new ByteBuffer().putInt(value); throw new ReadOnlyBufferException();
Object[][] values2d = new Object[_nRows * _nColumns]; int nColumns = values2d.length; int nRows = values2d[0].length; short _nColumns = (short)nColumns; short _nRows = (short)nRows; Object[][] vv = new Object[_nRows][_nColumns]; for (int r = 0; r < nRows; r++) { for (int c = 0; c < nColumns; c++) { vv[r][c] = rowData[r][c]; } }
return (GetIceServerConfigResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.GetIceServerConfigRequestMarshaller); setResponseUnmarshaller(Instance.GetIceServerConfigResponseUnmarshaller); }}, request);
StringBuilder sb = new StringBuilder(); sb.append(getClass().getName()).append(" ").append(getValueAsString()).append(" "); return sb.toString();
} ; " " + _parentQuery + " " return { ) field String ( toString String
} ; ) ( IncrementAndGet . refCount { ) ( IncRef void
UpdateConfigurationSetSendingEnabledResponse response = (UpdateConfigurationSetSendingEnabledResponse) invoke(new InvokeOptions() {{ request = new UpdateConfigurationSetSendingEnabledRequest(); requestMarshaller = UpdateConfigurationSetSendingEnabledRequestMarshaller.getInstance(); responseUnmarshaller = UpdateConfigurationSetSendingEnabledResponseUnmarshaller.getInstance(); }});
} return getNextXBATChainOffset() * LittleEndianConsts.INT_SIZE;
void multiplyByPowerOfTen(int pow10) { TenPower tp = TenPower.GetInstance().Math.Abs(pow10); if (pow10 < 0) { mulShift(_divisor.tp, _divisorShift.tp); } else { mulShift(_multiplicand.tp, _multiplierShift.tp); } }
StringBuilder builder = new StringBuilder(); int length = Path.DirectorySeparatorChar; for (int i = 0; i < length; i++) { builder.append(GetComponent(i)); } return builder.append(Path.DirectorySeparatorChar).toString();
void withFetcher(ECSMetadataServiceCredentialsFetcher fetcher) { fetcher.fetcher().setRoleName(); }
} ProgressMonitor pm; void setProgressMonitor(ProgressMonitor pm) {
if (!Eof) { ParseEntry(); } else { if (ptr == 0) { if (!First) { Reset(); } } }
throw new java.util.NoSuchElementException(); return iterator.previous(); if (iterator.previousIndex() >= start) { E previous;
return newPrefix.getNewPrefix();
int indexOfValue(int value) { for (int i = 0; i < mSize; ++i) { if (mValues[i] == value) { return i; } } return -1; }
return uniqueStems.stream().filter(stem -> !terms.contains(stem)).map(stem -> new CharsRef(stem.toCharArray())).collect(Collectors.toList());
return (GetGatewayResponsesResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.GetGatewayResponsesRequestMarshaller); setResponseUnmarshaller(Instance.GetGatewayResponsesResponseUnmarshaller); }}, request);
} ; ) blockMask.outerInstance & position() int (currentBlockUpto; ] [blocks.outerInstance currentBlock; ) blockBits.outerInstance >> position() int (currentBlockIndex { ) position long (setPosition void
int s = Math.min(Math.max((int) (long) n, (int) (long) Skip), Available); return s;
} BootstrapActionConfig _bootstrapActionConfig; BootstrapActionConfig(BootstrapActionDetail bootstrapActionDetail) { this._bootstrapActionConfig = bootstrapActionDetail; }
} } ; ) ) InvariantCulture . CultureInfo , ( ToInt32 . Convert ( writeByte . out1 { ) null != field_7_padding ( if } ; ) , ( putCompressedUnicode . StringUtil { else } ; ) , ( putUnicodeLE . StringUtil { ) field_5_hasMultibyte ( if ; ) 0x00 : 0x01 ? field_5_hasMultibyte ( writeByte . out1 ; ) length . field_6_author ( writeShort . out1 ; ) ( writeShort . out1 ; ) ( writeShort . out1 ; ) ( writeShort . out1 ; ) ( writeShort . out1 { ) out1 ILittleEndianOutput ( serialize void
} ; ) , ( lastIndexOf return { ) string @string ( lastIndexOf int
return addLastImpl((E) object) ? add : false;
} ; ) ) , ( compareAndSet.state ! ( while } ; ) , , ( unsetSection res ; ) ( get.state src { do ; ConfigSnapshot ; ConfigSnapshot { ) subsection String , section String ( unsetSection void
return tagName;
void addSubRecord(int index, SubRecord element) { insert.subrecords(index, element); }
return remove(object -> { synchronized (mutex) { return remove(object); } });
return new DoubleMetaphoneFilter(input);
return (long) Length(InCoreLength);
void setValue(boolean newValue) { this.value = newValue; }
new Pair<ContentSource, ContentSource>(newSource, oldSource);
throw new IndexOutOfBoundsException(); return entries[i]; if (i <= count) { int i = getInt(); }
} PUT.MethodType method; " " uriPattern { ) " ", " ", " ", " ", " " (: ) (CreateRepoRequest
boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
if (modCount == expectedModCount) { if (lastLink != null) { LinkedList.Link<ET> next = lastLink.next; LinkedList.Link<ET> previous = lastLink.previous; previous.next = next; next.previous = previous; lastLink = null; ++expectedModCount; --_size; ++modCount; } else { throw new IllegalStateException(); } } else { throw new ConcurrentModificationException(); }
return (MergeShardsResponse) invoke(request, new InvokeOptions().withRequestMarshaller(MergeShardsRequestMarshaller.getInstance()).withResponseUnmarshaller(MergeShardsResponseUnmarshaller.getInstance()));
AllocateHostedConnectionResponse response = (AllocateHostedConnectionResponse) Invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.AllocateHostedConnectionRequestMarshaller); setResponseUnmarshaller(Instance.AllocateHostedConnectionResponseUnmarshaller); }}, request);
} return getBeginIndex(); int start {
return getTerms(query);
throw new java.nio.ReadOnlyBufferException(); java.nio.ByteBuffer.compact();
void decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; i++) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (int) ((byte0 >> 2) & 3); int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (int) (((byte0 & 3) << 4) | ((byte1 >> 4) & 15)); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (int) (((byte1 & 15) << 2) | ((byte2 >> 6) & 3)); values[valuesOffset++] = (int) (byte2 & 63); } }
if (s == null || s.equals("")) throw new IllegalArgumentException(); String[] elements = s.split(FilePath.separatorChar + " "); if (elements.length == 0) throw new IllegalArgumentException(); String result = elements[elements.length - 1]; if (Constants.DOT_GIT.equals(result)) result = elements[elements.length - 2]; else if (result.endsWith(Constants.DOT_GIT_EXT)) result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); return result;
return (DescribeNotebookInstanceLifecycleConfigResponse) invoke(new InvokeOptions().withRequestMarshaller(DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance()), request);
} return getAccessKeySecret();
return invoke(new InvokeOptions() {{ setRequestMarshaller(CreateVpnConnectionRequestMarshaller.getInstance()); setResponseUnmarshaller(CreateVpnConnectionResponseUnmarshaller.getInstance()); }}, createVpnConnectionRequest, CreateVpnConnectionResponse.class);
DescribeVoicesResponse describeVoicesResponse = (DescribeVoicesResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.DescribeVoicesRequestMarshaller.getInstance()); setResponseUnmarshaller(Instance.DescribeVoicesResponseUnmarshaller.getInstance()); }}, describeVoicesRequest);
return (ListMonitoringExecutionsResponse) invoke(new ListMonitoringExecutionsRequest(), new InvokeOptions().setRequestMarshaller(ListMonitoringExecutionsRequestMarshaller.getInstance()).setResponseUnmarshaller(ListMonitoringExecutionsResponseUnmarshaller.getInstance()));
} jobId _jobId; vaultName _vaultName { ) jobId String, vaultName String ( DescribeJobRequest
public EscherRecord getEscherRecord(int index) { return escherRecords[index]; }
return (GetApisResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.GetApisRequestMarshaller.options); setResponseUnmarshaller(Instance.GetApisResponseUnmarshaller.options); }}, request);
return (DeleteSmsChannelResponse) invoke(request, new InvokeOptions().withRequestMarshaller(DeleteSmsChannelRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteSmsChannelResponseUnmarshaller.getInstance()));
} ; trackingRefUpdate return { ) ( getTrackingRefUpdate trackingRefUpdate
} ; ) ) ( toString . b ( print { ) b boolean ( print void
return (IQueryNode) GetChild(GetChildren());
} workdirTreeIndex index { workdirTreeIndex int (notIgnoredFilter);
} ; ) ( readShort . in1 field_1_formatFlags { ) in1 RecordInputStream ( AreaRecord
} HTTPS.ProtocolType Protocol { } " " , " " , " " , " " , " " ( ) ( GetThumbnailRequest
public DescribeTransitGatewayVpcAttachmentsResult describeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) {request = beforeClientExecution(request);return executeDescribeTransitGatewayVpcAttachments(request);}
} ; ) , ( > PutVoiceConnectorStreamingConfigurationResponse < Invoke return ; Instance . PutVoiceConnectorStreamingConfigurationResponseUnmarshaller ResponseUnmarshaller . options ; Instance . PutVoiceConnectorStreamingConfigurationRequestMarshaller RequestMarshaller . options ; ) ( InvokeOptions new = options { ) request PutVoiceConnectorStreamingConfigurationRequest ( PutVoiceConnectorStreamingConfiguration PutVoiceConnectorStreamingConfigurationResponse
OrdRange result; if (prefixToOrdRange.tryGetValue(dim, out result)) return result;
} ; ) , Name . ) LexerNoViableAltException . Runtime . Antlr4 ( typeof , " " , CurrentCulture . CultureInfo ( Format . string return } ; ) , ( EscapeWhitespace . Utils symbol ; ) ) , ( Of . Interval ( GetText . ) InputStream ) ICharStream ( ( symbol { ) Size . ) InputStream ) ICharStream ( ( < startIndex && 0 >= startIndex ( if ; Empty . string = symbol string { ) ( ToString string
} ; ) ( peekFirstImpl return { ) ( peek E
CreateWorkspacesResponse response = (CreateWorkspacesResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.CreateWorkspacesRequestMarshaller); setResponseUnmarshaller(Instance.CreateWorkspacesResponseUnmarshaller); }}, request);
NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = field_1_formatIndex; return rec.clone();
DescribeRepositoriesResponse response = (DescribeRepositoriesResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(DescribeRepositoriesRequestMarshaller.getInstance()); setResponseUnmarshaller(DescribeRepositoriesResponseUnmarshaller.getInstance()); }}, request);
SparseIntArray sparseIntArray = new SparseIntArray(idealIntArraySize(initialCapacity)); int[] mKeys = new int[initialCapacity]; int[] mValues = new int[initialCapacity]; int mSize = 0;
return new HyphenatedWordsFilter(input);
public CreateDistributionWithTagsResult createDistributionWithTags(CreateDistributionWithTagsRequest request) {request = beforeClientExecution(request);return executeCreateDistributionWithTags(request);}
throw new UnsupportedOperationException(new RandomAccessFile(fileName, mode));
return ((DeleteWorkspaceImageResponse) invoke(new InvokeOptions().withRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.getInstance()), request));
public static String toHex(int value) { return Long.toHexString(value); }
UpdateDistributionResponse response = (UpdateDistributionResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(UpdateDistributionRequestMarshaller.getInstance()); setResponseUnmarshaller(UpdateDistributionResponseUnmarshaller.getInstance()); }}, request);
public HSSFColor getColor(short index) { if (index == HSSFColor.HSSFColorPredefined.AUTOMATIC.getIndex()) return HSSFColor.HSSFColorPredefined.AUTOMATIC.getInstance(); else { byte[] b = palette.getColor(index); if (b != null) return new CustomColor(b); } return null; }
throw new NotImplementedFunctionException(); ValueEval evaluate(ValueEval[] operands, int srcRow, int srcCol);
} ; ) field_2_sheet_table_index ) short ( ( WriteShort . out1 ; ) field_1_number_crn_records ) short ( ( WriteShort . out1 { ) out1 ILittleEndianOutput ( Serialize void
return new DescribeDBEngineVersionsResponse(new DescribeDBEngineVersionsRequest());
FormatRun(short fontIndex, short character) { this.fontIndex = fontIndex; this.character = character; }
byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int resultIndex = 0; for (int i = offset; i < offset + length; i++) { char ch = chars[i]; result[resultIndex++] = (byte) (ch >> 8); result[resultIndex++] = (byte) (ch); } return result; }
return uploadArchive(uploadArchiveRequest);
public List<IToken> getHiddenTokensToLeft(int tokenIndex) { return getHiddenTokensToLeft(tokenIndex, -1); }
@Override public boolean equals(Object obj) { if (obj == this) { return true; } if (!(obj instanceof AutomatonQuery)) { return false; } AutomatonQuery other = (AutomatonQuery) obj; if (!compiled.equals(other.compiled)) { return false; } if (term == null) { return other.term == null; } else { return term.equals(other.term); } }
List<SpanQuery> spanQueries = new ArrayList<>(); for (var wsq : weightBySpanQuery.keySet()) { SpanQuery spanQuery = MakeSpanClause(wsq); spanQuery.setBoost(weightBySpanQuery.get(wsq)); spanQueries.add(spanQuery); } if (spanQueries.size() == 1) { return spanQueries.get(0); } else { return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); }
return new StashCreateCommand(StashCreate());
FieldInfo fieldInfo = byName.TryGetValue(fieldName, out FieldInfo ret) ? ret : null;
DescribeEventSourceResponse response = (DescribeEventSourceResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.DescribeEventSourceRequestMarshaller.getInstance()); setResponseUnmarshaller(Instance.DescribeEventSourceResponseUnmarshaller.getInstance()); }}, request);
GetDocumentAnalysisResponse response = (GetDocumentAnalysisResponse) Invoke(new InvokeOptions() {{ setRequestMarshaller(GetDocumentAnalysisRequestMarshaller.getInstance()); setResponseUnmarshaller(GetDocumentAnalysisResponseUnmarshaller.getInstance()); }}, request);
CancelUpdateStackResponse response = (CancelUpdateStackResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(CancelUpdateStackRequestMarshaller.getInstance()); setResponseUnmarshaller(CancelUpdateStackResponseUnmarshaller.getInstance()); }}, request, CancelUpdateStackResponse.class);
} ; ) , ( > ModifyLoadBalancerAttributesResponse < invoke return ; Instance . ModifyLoadBalancerAttributesResponseUnmarshaller responseUnmarshaller . options ; Instance . ModifyLoadBalancerAttributesRequestMarshaller requestMarshaller . options ; ) ( InvokeOptions new = options { ) request ModifyLoadBalancerAttributesRequest ( ModifyLoadBalancerAttributes ModifyLoadBalancerAttributesResponse
SetInstanceProtectionResponse response = (SetInstanceProtectionResponse) invoke(new InvokeOptions() { SetInstanceProtectionRequestMarshaller requestMarshaller = Instance.SetInstanceProtectionRequestMarshaller.options; SetInstanceProtectionResponseUnmarshaller responseUnmarshaller = Instance.SetInstanceProtectionResponseUnmarshaller.options; });
ModifyDBProxyResponse response = (ModifyDBProxyResponse) Invoke(new InvokeOptions() {{ RequestMarshaller = ModifyDBProxyRequestMarshaller.Instance; ResponseUnmarshaller = ModifyDBProxyResponseUnmarshaller.Instance; }}, request);
void add(char[] output, int offset, int len, int endOffset, int posLength) { if (outputs.length == count) { CharsRef[] newOutputs = new CharsRef[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF)]; System.arraycopy(outputs, 0, newOutputs, 0, count); outputs = newOutputs; } if (endOffsets.length == count) { int[] newEndOffsets = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(endOffsets, 0, newEndOffsets, 0, count); endOffsets = newEndOffsets; } if (posLengths.length == count) { int[] newPosLengths = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(posLengths, 0, newPosLengths, 0, count); posLengths = newPosLengths; } outputs[count] = new CharsRef(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
} HTTPS.ProtocolType Protocol { } " " , " " , " " , " " , " " ( ) FetchLibrariesRequest
return (Objects.exists());
} ; @out @out . { ) . . java ( FilterOutputStream
} scaleCluster(ScaleClusterRequest request) { return executeScaleCluster(request); }
return new DVConstraint(DataValidationConstraint.OperatorType, formula1, formula2);
ListObjectParentPathsResponse response = (ListObjectParentPathsResponse) instance.invoke(new InvokeOptions() {{ request = new ListObjectParentPathsRequest(); responseUnmarshaller = ListObjectParentPathsResponseUnmarshaller.instance; requestMarshaller = ListObjectParentPathsRequestMarshaller.instance; }});
return (DescribeCacheSubnetGroupsResponse) invoke(new DescribeCacheSubnetGroupsRequest(), new InvokeOptions() {{ setRequestMarshaller(DescribeCacheSubnetGroupsRequestMarshaller.getInstance()); setResponseUnmarshaller(DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance()); }});
} ; ) , ( setShortBoolean . sharedFormula field_5_options { ) flag boolean ( setSharedFormula void
boolean isReuseObjects() { return reuseObjects; }
return t.addChild(new ErrorNodeImpl((IToken) badToken));
if (args.length > 0) { throw new IllegalArgumentException(); } LatvianStemFilterFactory base = new LatvianStemFilterFactory((Map<String, String>) args);
return (RemoveSourceIdentifierFromSubscriptionResponse) invoke(new InvokeOptions().withRequestMarshaller(RemoveSourceIdentifierFromSubscriptionRequestMarshaller.getInstance()).withResponseUnmarshaller(RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.getInstance()), request);
return TokenFilterFactory.forName(name, (IDictionary<String, String>) args).newInstance(loader);
} HTTPS.ProtocolType protocol; AddAlbumPhotosRequest();
return invoke(new GetThreatIntelSetRequest(), new InvokeOptions() { RequestMarshaller = GetThreatIntelSetRequestMarshaller.Instance; ResponseUnmarshaller = GetThreatIntelSetResponseUnmarshaller.Instance; }, GetThreatIntelSetResponse.class);
return new AndTreeFilter(Clone.a(), Clone.b());
return o != null && this.equals(o);
protected boolean hasArray() { return hasArray; }
return (UpdateContributorInsightsResponse) invoke(request, new InvokeOptions(UpdateContributorInsightsRequestMarshaller.getInstance(), UpdateContributorInsightsResponseUnmarshaller.getInstance()));
void unwriteProtectWorkbook() { remove.records(); remove.records(null, fileShare, null, writeProtect); }
} expand expand . { ) analyzer , dedup ( base : ) analyzer Analyzer , expand boolean , dedup boolean ( SolrSynonymParser
RequestSpotInstancesResponse response = (RequestSpotInstancesResponse) Instance.invoke(new InvokeOptions() { RequestMarshaller = Instance.RequestSpotInstancesRequestMarshaller.options; ResponseUnmarshaller = Instance.RequestSpotInstancesResponseUnmarshaller.options; }, request);
return findObjectRecord().getObjectData()[byte];
return (GetContactAttributesResponse) invoke(request, new InvokeOptions().setRequestMarshaller(GetContactAttributesRequestMarshaller.getInstance()).setResponseUnmarshaller(GetContactAttributesResponseUnmarshaller.getInstance()));
return getKey() + " " + getValue().toString();
return (ListTextTranslationJobsResponse) invoke(request, new InvokeOptions().withRequestMarshaller(ListTextTranslationJobsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListTextTranslationJobsResponseUnmarshaller.getInstance()));
return (GetContactMethodsResponse) invoke(new InvokeOptions() {{ requestMarshaller = Instance.GetContactMethodsRequestMarshaller; responseUnmarshaller = Instance.GetContactMethodsResponseUnmarshaller; }}, request);
short LookupIndexByName(String name) { FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(); if (fd == null) return -1; return fd.Index; }
DescribeAnomalyDetectorsResponse describeAnomalyDetectorsResponse = (DescribeAnomalyDetectorsResponse) invoke(new InvokeOptions() {{ request = new DescribeAnomalyDetectorsRequest(); requestMarshaller = Instance.DescribeAnomalyDetectorsRequestMarshaller.options; responseUnmarshaller = Instance.DescribeAnomalyDetectorsResponseUnmarshaller.options; }});
return new InsertId(changeId, message, ObjectId);
} return; } throw new MissingObjectException(Copy.objectId(), ""); throw new MissingObjectException(Copy.objectId(), ""); if (typeHint == OBJ_ANY) { if (sz > 0) { long sz = GetObjectSize.db(objectId, typeHint); return sz; } } long GetObjectSize(AnyObjectId objectId, int typeHint);
return (ImportInstallationMediaResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(ImportInstallationMediaRequestMarshaller.getInstance()); setResponseUnmarshaller(ImportInstallationMediaResponseUnmarshaller.getInstance()); }}, request);
return new PutLifecycleEventHookExecutionStatusResponse(PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance(), PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance(), new InvokeOptions());
} ; ) ( ReadDouble . in1 field_1_value { ) in1 ILittleEndianInput ( NumberPtg
return (GetFieldLevelEncryptionConfigResponse) invoke(new GetFieldLevelEncryptionConfigRequest(), new InvokeOptions() {{ setRequestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.getInstance()); setResponseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance()); }});
return (DescribeDetectorResponse) invoke(request, new InvokeOptions().setRequestMarshaller(DescribeDetectorRequestMarshaller.getInstance()).setResponseUnmarshaller(DescribeDetectorResponseUnmarshaller.getInstance()));
return (ReportInstanceStatusResponse) invoke(request, new InvokeOptions().withRequestMarshaller(Instance.ReportInstanceStatusRequestMarshaller.getInstance()).withResponseUnmarshaller(Instance.ReportInstanceStatusResponseUnmarshaller.getInstance()));
DeleteAlarmResponse response = (DeleteAlarmResponse) invoke(new InvokeOptions() {{ requestMarshaller = Instance.DeleteAlarmRequestMarshaller; responseUnmarshaller = Instance.DeleteAlarmResponseUnmarshaller; }}, request);
return new PortugueseStemFilter(input);
} ; ] ENCODED_SIZE [ byte new reserved { ) ( FtCblsSubRecord
return remove(object -> { synchronized (mutex) { return remove(object); } });
return (GetDedicatedIpResponse) invoke(request, new InvokeOptions().withRequestMarshaller(GetDedicatedIpRequestMarshaller.getInstance()).withResponseUnmarshaller(GetDedicatedIpResponseUnmarshaller.getInstance()));
} ; " " + precedence return { ) ( toString String
ListStreamProcessorsResponse response = (ListStreamProcessorsResponse) invoke(new InvokeOptions() {{ request = new ListStreamProcessorsRequest(); }}, ListStreamProcessorsResponse.class, ListStreamProcessorsRequestMarshaller.getInstance(), ListStreamProcessorsResponseUnmarshaller.getInstance());
public DeleteLoadBalancerPolicyRequest(String policyName, String loadBalancerName) {setPolicyName(policyName);setLoadBalancerName(loadBalancerName);}
} options _options; WindowProtectRecord options(int options) {
} ; ] bufferSize [ int new data ; 0 n {  ) bufferSize int ( UnbufferedCharStream
return GetOperationsResponse.invoke(new InvokeOptions() {{ request = new GetOperationsRequest(); RequestMarshaller = GetOperationsRequestMarshaller.getInstance(); ResponseUnmarshaller = GetOperationsResponseUnmarshaller.getInstance(); }});
} ; ) , 16 + o , ( EncodeInt32 . NB ; ) , 12 + o , ( EncodeInt32 . NB ; ) , 8 + o , ( EncodeInt32 . NB ; ) , 4 + o , ( EncodeInt32 . NB ; ) , , ( EncodeInt32 . NB { ) o int , b ] [ byte ( CopyRawTo void
} ; ) ( ReadShort . in1 field_9_tab_width_ratio ; ) ( ReadShort . in1 field_8_num_selected_tabs ; ) ( ReadShort . in1 field_7_first_visible_tab ; ) ( ReadShort . in1 field_6_active_sheet ; ) ( ReadShort . in1 field_5_options ; ) ( ReadShort . in1 field_4_height ; ) ( ReadShort . in1 field_3_width ; ) ( ReadShort . in1 field_2_v_hold ; ) ( ReadShort . in1 field_1_h_hold {  ) in1 RecordInputStream ( WindowOneRecord
public StopWorkspacesResult stopWorkspaces(StopWorkspacesRequest request) {request = beforeClientExecution(request);return executeStopWorkspaces(request);}
} } } } } ; ) ( close . fos { finally } ; ) ( close . channel { try { finally } ; ) ( truncate . channel { try { finally } ; ) ( dump { try ; isOpen { if (isOpen()) { throw new IOException(); } close();
return new DescribeMatchmakingRuleSetsResponse(DescribeMatchmakingRuleSetsRequestMarshaller.getInstance().marshall(request), DescribeMatchmakingRuleSetsResponseUnmarshaller.getInstance().unmarshall(response), new InvokeOptions());
String GetPronunciation(int wordId, char[] surface, int off, int len) { return null; }
return getPath();
if (v != null && v.length >= 1) { double r = Double.NaN; int n = v.length; double m = 0; double s = 0; for (int i = 0; i < n; ++i) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; ++i) { s += (v[i] - m) * (v[i] - m); } r = s; return r; }
DescribeResizeResponse describeResizeResponse = (DescribeResizeResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.DescribeResizeRequestMarshaller); setResponseUnmarshaller(Instance.DescribeResizeResponseUnmarshaller); }}, describeResizeRequest);
} ; passedThroughNonGreedyDecision return { ) ( hasPassedThroughNonGreedyDecision boolean
} ; ) ( end return { ) ( end int
void traverse(ICellHandler handler) { int firstRow = range.FirstRow; int lastRow = range.LastRow; int firstColumn = range.FirstColumn; int lastColumn = range.LastColumn; int width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); IRow currentRow = null; ICell currentCell = null; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ctx.rowNumber++) { currentRow = sheet.GetRow(ctx.rowNumber); if (currentRow == null) { continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ctx.colNumber++) { currentCell = currentRow.GetCell(ctx.colNumber); if (currentCell == null) { continue; } if (!handler.IsEmpty(currentCell)) { handler.OnCell(ctx, currentCell); } } } }
return getReadIndex();
return (this.Boost == other.Boost) ? (this.Term.BytesEquals(other.Term) ? 0 : this.Term.CompareTo(other.Term)) : Float.compare(this.Boost, other.Boost);
for (int i = 0; i < len; i++) { switch (s[i]) { case HAMZA_ABOVE: break; case HEH: s[i] = HEH_GOAL; break; case HEH_YEH: s[i] = HEH_GOAL; break; case KAF: s[i] = KEHEH; break; case YEH: s[i] = YEH_BARREE; break; case FARSI_YEH: s[i] = YEH_BARREE; break; default: break; } } Normalize(s, len);
void Serialize(ILittleEndianOutput out1) { out1.WriteShort(); }
} exactOnly exactOnly { exactOnly boolean DiagnosticErrorListener;
KeySchemaElement keySchemaElement = new KeySchemaElement().withKeyType(KeyType).withAttributeName(attributeName);
return (GetAssignmentResponse) Invoke(request, new InvokeOptions() {{ setRequestMarshaller(GetAssignmentRequestMarshaller.getInstance()); setResponseUnmarshaller(GetAssignmentResponseUnmarshaller.getInstance()); }});
return FindOffset(id) != 1;
} return allGroups.allGroups(SetAllGroups(GroupingSearch));
void setMultiValued(String dimName, boolean v) { synchronized (fieldTypes) { if (!fieldTypes.containsKey(dimName)) { fieldTypes.put(dimName, new DimConfig()); } if (v != fieldTypes.get(dimName).isMultiValued) { fieldTypes.get(dimName).isMultiValued = v; } } }
int size = 0; for (char c : Keys.cells) { Cell e = At(cmd.e); if (e >= 0) { size++; } } return size;
return ((DeleteVoiceConnectorResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(DeleteVoiceConnectorRequestMarshaller.getInstance()); setResponseUnmarshaller(DeleteVoiceConnectorResponseUnmarshaller.getInstance()); }}, request, DeleteVoiceConnectorResponse.class));
DeleteLifecyclePolicyResponse response = (DeleteLifecyclePolicyResponse) instance.invoke(new InvokeOptions().setRequestMarshaller(Instance.DeleteLifecyclePolicyRequestMarshaller).setResponseUnmarshaller(Instance.DeleteLifecyclePolicyResponseUnmarshaller), request);
