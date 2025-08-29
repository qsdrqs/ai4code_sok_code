out1.writeShort(); } void Serialize ( ILittleEndianOutput out1 ) {
public void AddAll(BlockList<T> src) { if (src.size == 0) return; int srcDirIdx = src.tailDirIdx; int tailBlkIdx = src.tailBlkIdx; for (; srcDirIdx < src.directory.length; srcDirIdx++) { if (srcDirIdx != 0) { tailBlkIdx = 0; } Block<T> tailBlock = src.directory[srcDirIdx]; AddAll(tailBlock, tailBlkIdx); } }
public void writeByte(byte b) { if (outerInstance.upto == outerInstance.blockSize) { outerInstance.currentBlock = new byte[outerInstance.blockSize]; outerInstance.blocks.add(outerInstance.currentBlock); outerInstance.upto = 0; } outerInstance.currentBlock[outerInstance.upto++] = b; }
return objectId;
public DeleteDomainEntryResult deleteDomainEntry(DeleteDomainEntryRequest request) { request = beforeClientExecution(request); return executeDeleteDomainEntry(request); }
return fst == null ? 0 : fst.getSizeInBytes();
public String getFullMessage() { if (raw.length == 0) return ""; byte[] buffer = RawParseUtils.decode(RawParseUtils.parseEncoding(raw), raw); return new String(buffer); }
} { ) ( POIFSFileSystem ; ; ; ; null _root _documents _property_table HeaderBlock ArrayList new PropertyTable new headerBlock ) ( ) ( = HeaderBlock new ) (
pool.buffers[slice] != null & Debug.Assert(address < upto) & Debug.Assert(address); int address; ByteBlockPool.BYTE_BLOCK_SHIFT >> address; slice.length; pool.buffers[slice]; ByteBlockPool.BYTE_BLOCK_MASK < upto; Debug.Assert(address) & Debug.Assert(address offset0 upto slice); void init() { }
return Api.NGit.SubmoduleAddCommand.setPath(path);
return Instance.Invoke(new InvokeOptions(RequestMarshaller.Instance.ListIngestionsRequestMarshaller, ResponseUnmarshaller.Instance.ListIngestionsResponseUnmarshaller, options), request, ListIngestionsRequest, ListIngestionsResponse);
; SwitchTo QueryParserTokenManager ) ( { this : ) stream ( ) lexState int , stream ICharStream (
GetShardIteratorResponse getShardIterator(GetShardIteratorRequest request) { InvokeOptions options = new InvokeOptions(); return invoke(request, options, GetShardIteratorRequestMarshaller.getInstance(), GetShardIteratorResponseUnmarshaller.getInstance()); }
}
ready bool } { ) ( ) lock ( lock } { try ) ( if catch } { } { null == in } { ) ( ; return ; throw ; return IOException . || new IO . System 0 > ) ( IOException . ) ( hasRemaining . bytes " " IO . System ) ( available . in
GetOptRecord EscherOptRecord } { ) ( ; _optRecord return
public int read(byte[] buffer, int offset, int length) { if (buffer == null) throw new java.lang.IllegalArgumentException(); if (offset < 0 || length < 0) throw new java.lang.IllegalArgumentException(); if (offset + length > buffer.length) throw new java.lang.IllegalArgumentException(); int copylen = Math.min(length, this.length - pos); if (copylen <= 0) return -1; System.arraycopy(this.buffer, pos, buffer, offset, copylen); pos += copylen; return copylen; }
} {  ; OpenNLPSentenceBreakIterator sentenceOp ) sentenceOp NLPSentenceDetectorOp ( sentenceOp .
System.out.println(str != null ? StringHelper.valueOf(str) : null);
throw new UnsupportedOperationException(functionName, cause);
return nextEntry().value;
public void readBytes(byte[] b, int offset, int len) { if (len == 0) return; boolean useBuffer = bufferLength > 0 && len < bufferSize; if (useBuffer) { int available = bufferLength - bufferPosition; if (available >= len) { System.arraycopy(buffer, bufferStart + bufferPosition, b, offset, len); bufferPosition += len; } else { if (available > 0) { System.arraycopy(buffer, bufferStart + bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } long after = bufferStart + bufferPosition + len; if (after > length) { throw new EndOfStreamException(); } readInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } else { long after = bufferStart + bufferPosition + len; if (after > length) { throw new EndOfStreamException(); } readInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } }
return Invoke(new InvokeOptions(options, RequestMarshaller.TagQueueRequestMarshaller.Instance, ResponseUnmarshaller.TagQueueResponseUnmarshaller.Instance), request);
throw new UnsupportedOperationException();
return invoke(new InvokeOptions(RequestMarshaller.Instance.ModifyCacheSubnetGroupRequestMarshaller, options, ResponseUnmarshaller.Instance.ModifyCacheSubnetGroupResponseUnmarshaller, options), request);
setParams(String params) { if (params != null) { StringTokenizer st = new StringTokenizer(params, " "); if (st.hasMoreTokens()) { st.nextToken(); } if (st.hasMoreTokens()) { st.nextToken(); } if (st.hasMoreTokens()) { st.nextToken(); } } }
return invoke(request, DeleteDocumentationVersionRequestMarshaller.getInstance(), DeleteDocumentationVersionResponseUnmarshaller.getInstance(), options);
public boolean equals(Object obj) { if (!(obj instanceof FacetLabel)) return false; FacetLabel other = (FacetLabel) obj; if (other.length != length) return false; for (int i = length - 1; i >= 0; i--) { if (!components[i].equals(other.components[i])) return false; } return true; }
GetInstanceAccessDetails GetInstanceAccessDetailsResponse } { ) ( ; return ; ; ; request GetInstanceAccessDetailsRequest > ) , ( GetInstanceAccessDetailsResponse < Invoke Instance . GetInstanceAccessDetailsResponseUnmarshaller ResponseUnmarshaller . options Instance . GetInstanceAccessDetailsRequestMarshaller RequestMarshaller . options options = InvokeOptions new ) (
HSSFPolygon shape = new HSSFPolygon((HSSFChildAnchor) anchor); shape.Parent = shapes; shape.Anchor = anchor; shapes.Add(shape); return shape; HSSFPolygon CreatePolygon(HSSFChildAnchor anchor) {
public String getSheetName(int sheetIndex) { return GetBoundSheetRec(sheetIndex).getSheetname(); }
return invoke(new InvokeOptions(options, GetDashboardRequestMarshaller.getInstance(), GetDashboardResponseUnmarshaller.getInstance()), request);
return invoke(new InvokeOptions().requestMarshaller(AssociateSigninDelegateGroupsWithAccountRequestMarshaller.getInstance()).responseUnmarshaller(AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.getInstance()), request);
for(int j = 0; j < mbr.getNumColumns(); j++) { BlankRecord br = new BlankRecord(mbr.getFirstColumn() + j, mbr.getRow(), mbr.getXFAt(j)); insertCell(br); }
quote string } { ) ( ; return ) ( while ; ; ; ; string string } { 0 >= int int ) ( toString . ; ; ) ( apos ) ( append . sb sb StringBuilder . apos k 0 = " \\ " = lang . java ) ( append . 2 + k ) ( append . new " \\ " " \\ \\ \\ " ) , ( indexOf . string ) ( StringBuilder . ) ( append . sb ) ( append . sb " \\ " lang . java ) , ( substring . ) , , ( substring . StringHelper . Sharpen 2 + k StringHelper . Sharpen
java.nio.ByteBuffer.putInt(int value); throw new java.nio.ReadOnlyBufferException();
for(int r = 0; r < nRows; r++) { for(int c = 0; c < nColumns; c++) { vv[r][c] = values2d[r][c]; } } this._nRows = (short)nRows; this._nColumns = (short)nColumns; this._arrayValues = vv; this._reserved0Int = 0; this._reserved1Short = 0; this._reserved2Byte = 0; ArrayPtg ptg = new ArrayPtg();
return invoke(new InvokeOptions(options, GetIceServerConfigRequestMarshaller.getInstance(), GetIceServerConfigResponseUnmarshaller.getInstance()), request);
return new StringBuilder().append(getName()).append(" ").append(getValueAsString()).append(" ").toString();
return field + " " + _parentQuery + " ";
refCount.incrementAndGet();
public UpdateConfigurationSetSendingEnabledResult updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) { request = beforeClientExecution(request); return executeUpdateConfigurationSetSendingEnabled(request); }
return LittleEndianConsts.INT_SIZE * getXBATEntriesPerBlock();
void multiplyByPowerOfTen(int pow10) { if (pow10 < 0) { TenPower tp = TenPower.getInstance(Math.abs(pow10)); _divisor = tp._multiplicand; _divisorShift = tp._multiplierShift; } else { TenPower tp = TenPower.getInstance(pow10); _multiplicand = tp._multiplicand; _multiplierShift = tp._multiplierShift; } }
public String toString() { StringBuilder builder = new StringBuilder(); for (int i = 0; i < length; i++) { if (i > 0) { builder.append(File.separatorChar); } builder.append(getComponent(i)); } return builder.toString(); }
fetcher.setRoleName();
void setProgressMonitor(ProgressMonitor pm) { progressMonitor = pm; }
if (!First()) { if (!Eof()) { ptr = 0; ParseEntry(); } } void Reset() { }
if (previousIndex() >= start) { throw new java.util.NoSuchElementException(); } return iterator.previous();
return newPrefix;
for(int i = 0; i < mSize; i++) { if(mValues[i] == value) { return i; } } return -1;
List<CharsRef> deduped = new ArrayList<>(); for(CharsRef s : stems) { if(!terms.contains(new CharsRef(s))) { terms.add(new CharsRef(s)); deduped.add(s); } } return deduped;
return invoke(new InvokeOptions(GetGatewayResponsesRequestMarshaller.getInstance(), GetGatewayResponsesResponseUnmarshaller.getInstance()), request);
void setPosition(long position) { currentBlockUpto = (int) (position >> outerInstance.blockBits); currentBlock = blocks[(int) (position >> outerInstance.blockBits)]; currentBlockIndex = (int) (position & outerInstance.blockMask); }
Skip long } { ) ( ; s return ; ; n long s ptr int s = ) int ( ) , ( min . Math available ) , ( max . Math ) (
} {  ; BootstrapActionDetail bootstrapActionConfig _bootstrapActionConfig ) bootstrapActionConfig BootstrapActionConfig (
void serialize(ILittleEndianOutput out1) { if (field_7_padding != null) { out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00); out1.writeShort(field_6_author.length()); out1.writeShort(Convert.toInt32(CultureInfo.invariantCulture)); StringUtil.putUnicodeLE(StringUtil.putCompressedUnicode(out1)); } else { } }
return string.lastIndexOf();
addLastImpl(object); return true;
UnsetSection void } { ) , ( ; ) ( while do ; ; subsection string section string ! } { ConfigSnapshot ConfigSnapshot ; ; ) , ( CompareAndSet . state res src UnsetSection ) , , ( ) ( Get . state
return tagName;
subrecords.insert(index, element);
remove bool } { ) ( ) mutex ( lock object object } { ; return ) ( remove . c
return new DoubleMetaphoneFilter(input);
return inCoreLength();
void setValue(boolean newValue) { value = newValue; }
Pair<ContentSource, ContentSource> pair = new Pair<>(newSource, oldSource);
if (i < 0 || i >= count) { throw Sharpen.Extensions.CreateIndexOutOfRangeException(); } return entries[i];
} { : ) ( CreateRepoRequest ; ; Method UriPattern ) , , , , ( PUT . MethodType " " " " " " " " " " " "
return deltaBaseAsOffset;
public void remove() { if (lastLink == null) throw new IllegalStateException(); if (list.modCount != expectedModCount) throw new ConcurrentModificationException(); Link<ET> next_1 = lastLink.next; Link<ET> previous_1 = lastLink.previous; if (previous_1 == null) { list.voidLinkFirst = next_1; } else { previous_1.next = next_1; } if (next_1 == null) { list.voidLinkLast = previous_1; } else { next_1.previous = previous_1; } lastLink = null; pos--; list._size--; list.modCount++; expectedModCount++; }
return invoke(request, new InvokeOptions().withRequestMarshaller(MergeShardsRequestMarshaller.getInstance()).withResponseUnmarshaller(MergeShardsResponseUnmarshaller.getInstance()));
return invoke(new InvokeOptions(AllocateHostedConnectionRequestMarshaller.getInstance(), AllocateHostedConnectionResponseUnmarshaller.getInstance()), request);
return start; } { int getBeginIndex() {
GetTerms } { ) ( WeightedTerm ; return query Query ] [ GetTerms ) , (
throw new java.nio.ReadOnlyBufferException();
void decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; i++) { byte byte0 = blocks[blocksOffset++]; byte byte1 = blocks[blocksOffset++]; byte byte2 = blocks[blocksOffset++]; values[valuesOffset++] = (int) (byte0 & 0xFF) >> 2; values[valuesOffset++] = (int) (byte0 & 0xFF) << 4 | (int) (byte1 & 0xFF) >> 4; values[valuesOffset++] = (int) (byte1 & 0xFF) << 2 | (int) (byte2 & 0xFF) >> 6; values[valuesOffset++] = (int) byte2 & 63; } }
public static String getHumanishName(String s) { if (s == null) throw new IllegalArgumentException(); String[] elements = s.split(File.separator); if (elements.length == 0) throw new IllegalArgumentException(); String result = elements[elements.length - 1]; if (result.equals("") || result.equals(".") || result.equals("..")) { if (elements.length > 1) result = elements[elements.length - 2]; else throw new IllegalArgumentException(); } if (result.endsWith(Constants.DOT_GIT_EXT)) result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); if (result.equals(Constants.DOT_GIT)) throw new IllegalArgumentException(); return result; }
DescribeNotebookInstanceLifecycleConfigResponse DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = DescribeNotebookInstanceLifecycleConfigRequestMarshaller.Instance; options.ResponseUnmarshaller = DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.Instance; return Invoke<DescribeNotebookInstanceLifecycleConfigResponse>(options, request); }
public String getAccessKeySecret() { return AccessSecret; }
return invoke(new InvokeOptions(CreateVpnConnectionRequestMarshaller.getInstance(), CreateVpnConnectionResponseUnmarshaller.getInstance()), request);
return invoke(new InvokeOptions(DescribeVoicesRequestMarshaller.getInstance(), DescribeVoicesResponseUnmarshaller.getInstance(), options), request);
public ListMonitoringExecutionsResult listMonitoringExecutions(ListMonitoringExecutionsRequest request) { request = beforeClientExecution(request); return executeListMonitoringExecutions(request); }
} { ; ; DescribeJobRequest jobId _jobId vaultName _vaultName ) jobId String , vaultName String (
return escherRecords[index]; } EscherRecord getEscherRecord() {
return Invoke(new InvokeOptions(RequestMarshaller.Instance.GetApisRequestMarshaller, ResponseUnmarshaller.Instance.GetApisResponseUnmarshaller), request);
DeleteSmsChannel DeleteSmsChannelResponse } { ) ( ; return ; ; ; request DeleteSmsChannelRequest > ) , ( DeleteSmsChannelResponse < invoke instance . DeleteSmsChannelResponseUnmarshaller responseUnmarshaller . options instance . DeleteSmsChannelRequestMarshaller requestMarshaller . options options = new InvokeOptions ) (
return trackingRefUpdate;
System.out.println(b.toString());
GetChild IQueryNode } { ) ( ; return ] [ GetChildren ) (
} {  ; NotIgnoredFilter workdirTreeIndex ) workdirTreeIndex int ( index .
} { ; AreaRecord field_1_formatFlags ) in1 RecordInputStream ( ) ( readShort . in1
} { : ) ( getThumbnailRequest ; protocol ) , , , , ( https . protocolType " " " " " " " " " "
DescribeTransitGatewayVpcAttachments describeTransitGatewayVpcAttachmentsResponse } { ) ( ; return ; ; ; request DescribeTransitGatewayVpcAttachmentsRequest > ) , ( DescribeTransitGatewayVpcAttachmentsResponse < invoke instance . DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller responseUnmarshaller . options instance . DescribeTransitGatewayVpcAttachmentsRequestMarshaller requestMarshaller . options options = invokeOptions new ) (
PutVoiceConnectorStreamingConfigurationResponse putVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request) { InvokeOptions options = new InvokeOptions(); options.requestMarshaller = PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance(); options.responseUnmarshaller = PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance(); return invoke(request, options); }
return prefixToOrdRange.get(dim);
@Override public String toString() { if (symbol != null) { String name = symbol.getName(); if (name != null && !name.isEmpty()) { return String.format("'%s'", Utils.escapeWhitespace(name, false)); } } return String.format("<%s>", Utils.escapeWhitespace(((ICharStream)input).getText(Interval.of(startIndex, startIndex)), false)); }
peek E } { ) ( ; return peekFirstImpl ) (
public CreateWorkspacesResult createWorkspaces(CreateWorkspacesRequest request) { request = beforeClientExecution(request); return executeCreateWorkspaces(request); }
NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = field_1_formatIndex; return rec;
return Invoke(new InvokeOptions(RequestMarshaller.DescribeRepositoriesRequestMarshaller.Instance, ResponseUnmarshaller.DescribeRepositoriesResponseUnmarshaller.Instance), request);
} {  ; ; ; ; SparseIntArray 0 mSize mValues mKeys initialCapacity ) initialCapacity int ( new new int int ) ( idealIntArraySize . ] initialCapacity [ ] initialCapacity [ ArrayUtils . internal . util . android
return new HyphenatedWordsFilter(input);
return Invoke(new InvokeOptions(RequestMarshaller.Instance.CreateDistributionWithTagsRequestMarshaller, ResponseUnmarshaller.Instance.CreateDistributionWithTagsResponseUnmarshaller)<CreateDistributionWithTagsRequest, CreateDistributionWithTagsResponse>(request);
throw new java.io.RandomAccessFile(new java.io.File(string fileName, string mode), );} { : ; new System.NotImplementedException()
public DeleteWorkspaceImageResult deleteWorkspaceImage(DeleteWorkspaceImageRequest request) { request = beforeClientExecution(request); return executeDeleteWorkspaceImage(request); }
public String toHex(int value) { return Long.toHexString(value); }
return Invoke(new InvokeOptions(RequestMarshaller.UpdateDistributionRequestMarshaller.Instance, ResponseUnmarshaller.UpdateDistributionResponseUnmarshaller.Instance), request);
byte b = palette.getColor(index); if (b != null) { return HSSFColor.Automatic.getInstance(); } if (index == null) { return; } short index; if (null) return; } HSSFColor getColor() {
throw new NotImplementedFunctionException(); } public ValueEval evaluate(ValueEval[] operands, int srcRow, int srcCol) {
out1.writeShort((short)field_1_number_crn_records);out1.writeShort((short)field_2_sheet_table_index);
return describeDBEngineVersions(new DescribeDBEngineVersionsRequest());
} { ; ; FormatRun fontIndex character ) fontIndex short , character short ( _fontIndex . _character .
public static byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int resultIndex = 0; for (int i = 0; i < length; i++) { char ch = chars[offset + i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
public UploadArchiveResult uploadArchive(UploadArchiveRequest request) { request = beforeClientExecution(request); return executeUploadArchive(request); }
public List<IToken> GetHiddenTokensToLeft(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex, -1); }
public boolean equals(Object obj) { if (obj == null) return false; if (!(obj instanceof AutomatonQuery)) return false; if (obj == this) return true; AutomatonQuery other = (AutomatonQuery) obj; if (!this.getClass().equals(obj.getClass())) return false; if (this.compiled != null ? !this.compiled.equals(other.compiled) : other.compiled != null) return false; return this.term.equals(other.term); }
return spanQueries.size() == 1 ? spanQueries.get(0) : new SpanOrQuery(spanQueries.toArray(new SpanQuery[0]));
StashCreate StashCreateCommand } { ) ( ; return StashCreateCommand new ) (
FieldInfo fieldInfo; return byName.tryGetValue(fieldName, out fieldInfo) ? fieldInfo : null;
return Invoke(new InvokeOptions(RequestMarshaller.Instance.DescribeEventSourceRequestMarshaller, ResponseUnmarshaller.Instance.DescribeEventSourceResponseUnmarshaller), request, DescribeEventSourceResponse.class);
return Invoke(new InvokeOptions(RequestMarshaller.GetDocumentAnalysisRequestMarshaller.Instance, ResponseUnmarshaller.GetDocumentAnalysisResponseUnmarshaller.Instance), request);
CancelUpdateStackResponse cancelUpdateStack(CancelUpdateStackRequest request) { return invoke(new InvokeOptions(CancelUpdateStackRequestMarshaller.getInstance(), CancelUpdateStackResponseUnmarshaller.getInstance(), options)); }
public ModifyLoadBalancerAttributesResult modifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) {request = beforeClientExecution(request);return executeModifyLoadBalancerAttributes(request);}
return Invoke(new InvokeOptions(RequestMarshaller.Instance.SetInstanceProtectionRequestMarshaller, ResponseUnmarshaller.Instance.SetInstanceProtectionResponseUnmarshaller), request);
return invoke(new InvokeOptions(ModifyDBProxyRequestMarshaller.getInstance(), ModifyDBProxyResponseUnmarshaller.getInstance()), request);
public void add(CharsRef output, int offset, int len, int endOffset, int posLength) { if (count == outputs.length) { CharsRef[] newOutputs = new CharsRef[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF)]; System.arraycopy(outputs, 0, newOutputs, 0, count); outputs = newOutputs; int[] newEndOffsets = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(endOffsets, 0, newEndOffsets, 0, count); endOffsets = newEndOffsets; int[] newPosLengths = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(posLengths, 0, newPosLengths, 0, count); posLengths = newPosLengths; } outputs[count] = new CharsRef(); outputs[count].copyChars(output.chars, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
} { : ) ( FetchLibrariesRequest ; Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " "
return objects.Exists();
} { ; FilterOutputStream out ) java ( out . . .
} { : ) ( ScaleClusterRequest ; ; Method UriPattern ) , , , , ( PUT . MethodType " " " " " " " " " " " "
return DVConstraint.CreateTimeConstraint(int operatorType, String formula1, String formula2);
public ListObjectParentPathsResult listObjectParentPaths(ListObjectParentPathsRequest request) { request = beforeClientExecution(request); return executeListObjectParentPaths(request); }
return Invoke(new InvokeOptions(RequestMarshaller.Instance.DescribeCacheSubnetGroupsRequestMarshaller, ResponseUnmarshaller.Instance.DescribeCacheSubnetGroupsResponseUnmarshaller), request);
sharedFormula.SetShortBoolean(field_5_options, flag);
return reuseObjects;
AddErrorNode IErrorNode } { ) ( ; t return ; ; ; badToken IToken AddChild ErrorNodeImpl Parent . t ) ( t = ErrorNodeImpl new ) (
} { ) ( if LatvianStemFilterFactory } { 0 > ) args ( super : ; throw size . args ) String , Map ( new > String < ) ( IllegalArgumentException args + " "
return Invoke(new InvokeOptions(RequestMarshaller.Instance.RemoveSourceIdentifierFromSubscriptionRequestMarshaller, ResponseUnmarshaller.Instance.RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller), request);
ForName TokenFilterFactory } { ) , ( ; return args name string Map ) , ( NewInstance . loader > string , string <
} { : ) ( AddAlbumPhotosRequest ; Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " "
return invoke(new InvokeOptions().requestMarshaller(GetThreatIntelSetRequestMarshaller.getInstance()).responseUnmarshaller(GetThreatIntelSetResponseUnmarshaller.getInstance()), request);
return new AndTreeFilter(a.clone(), b.clone());
return o instanceof Object;
boolean hasArray() { return protectedHasArray(); }
public UpdateContributorInsightsResult updateContributorInsights(UpdateContributorInsightsRequest request) { request = beforeClientExecution(request); return executeUpdateContributorInsights(request); }
UnwriteProtectWorkbook void } { ) ( ; ; ; ; null writeProtect null fileShare ) ( remove . records ) ( remove . records
} {  ; SolrSynonymParser expand ) analyzer , dedup ( base : expand . ) analyzer Analyzer , expand boolean , dedup boolean (
return invoke(new InvokeOptions().withRequestMarshaller(RequestSpotInstancesRequestMarshaller.getInstance()).withResponseUnmarshaller(RequestSpotInstancesResponseUnmarshaller.getInstance()), request);
getObjectData(){return FindObjectRecord().ObjectData;}
return Instance.Invoke(new InvokeOptions(RequestMarshaller.GetContactAttributesRequestMarshaller.Instance, ResponseUnmarshaller.GetContactAttributesResponseUnmarshaller.Instance), request);
return getKey() + " " + getValue();
return invoke(new InvokeOptions(ListTextTranslationJobsRequestMarshaller.getInstance(), ListTextTranslationJobsResponseUnmarshaller.getInstance(), options), request);
public GetContactMethodsResponse getContactMethods(GetContactMethodsRequest request) { request = beforeClientExecution(request); return executeGetContactMethods(request); }
return GetInstance().GetFunctionByNameInternal(name) == null ? (short)-1 : fd.Index;
return Invoke(new InvokeOptions(DescribeAnomalyDetectorsRequestMarshaller.Instance, DescribeAnomalyDetectorsResponseUnmarshaller.Instance), request);
return new InsertResult(insertId, changeId);
public final long GetObjectSize(AnyObjectId objectId, int typeHint) throws MissingObjectException { long sz = db.GetObjectSize(objectId.Copy(), typeHint); if (sz < 0) throw new MissingObjectException(objectId.Copy(), ""); if (typeHint == OBJ_ANY) return sz; if (sz == 0) throw new MissingObjectException(objectId, typeHint); return sz; }
return invoke(new InvokeOptions(ImportInstallationMediaRequestMarshaller.getInstance(), ImportInstallationMediaResponseUnmarshaller.getInstance()), request);
return Invoke(new InvokeOptions(PutLifecycleEventHookExecutionStatusRequestMarshaller.Instance, PutLifecycleEventHookExecutionStatusResponseUnmarshaller.Instance, options), request);
} {  ; NumberPtg field_1_value ) in1 ILittleEndianInput ( ) ( readDouble . in1
return invoke(new InvokeOptions(GetFieldLevelEncryptionConfigRequestMarshaller.getInstance(), GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance()), request);
return invoke(new InvokeOptions(DescribeDetectorRequestMarshaller.getInstance(), DescribeDetectorResponseUnmarshaller.getInstance()), request);
return invoke(new InvokeOptions(options, RequestMarshaller.Instance.ReportInstanceStatusRequestMarshaller, ResponseUnmarshaller.Instance.ReportInstanceStatusResponseUnmarshaller), request, ReportInstanceStatusResponse.class);
public DeleteAlarmResult deleteAlarm(DeleteAlarmRequest request) {request = beforeClientExecution(request);return executeDeleteAlarm(request);}
return new PortugueseStemFilter(input);
} { ) ( FtCblsSubRecord ; reserved new byte ] ENCODED_SIZE [
remove bool } { ) ( ) mutex ( lock @object object } { ; return ) ( remove . c
public GetDedicatedIpResult getDedicatedIp(GetDedicatedIpRequest request) { request = beforeClientExecution(request); return executeGetDedicatedIp(request); }
return precedence + " " + ToString();
return invoke(new InvokeOptions(RequestMarshaller.ListStreamProcessorsRequestMarshaller.Instance, ResponseUnmarshaller.ListStreamProcessorsResponseUnmarshaller.Instance, options), request);
public DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) { setLoadBalancerName(loadBalancerName); setPolicyName(policyName); }
} { ; WindowProtectRecord options _options ) options int (
} { ; ; UnbufferedCharStream data = new UnbufferedCharStream(0, n); int bufferSize; int[] buffer = new int[bufferSize];
return invoke(new InvokeOptions(GetOperationsRequestMarshaller.getInstance(), GetOperationsResponseUnmarshaller.getInstance(), options), request);
CopyRawTo void } { ) , ( ; ; ; ; ; o int b byte ) , , ( EncodeInt32 . NB ) , , ( EncodeInt32 . NB ) , , ( EncodeInt32 . NB ) , , ( EncodeInt32 . NB ) , , ( EncodeInt32 . NB ] [ 16 + o 12 + o 8 + o 4 + o
} { WindowOneRecord field_9_tab_width_ratio field_8_num_selected_tabs field_7_first_visible_tab field_6_active_sheet field_5_options field_4_height field_3_width field_2_v_hold field_1_h_hold ) in1 RecordInputStream ( ) ( readShort . in1 ) ( readShort . in1 ) ( readShort . in1 ) ( readShort . in1 ) ( readShort . in1 ) ( readShort . in1 ) ( readShort . in1 ) ( readShort . in1 ) ( readShort . in1
return invoke(request, StopWorkspacesRequestMarshaller.getInstance(), StopWorkspacesResponseUnmarshaller.getInstance(), options);
} finally { try { if (isOpen()) { close(); } } catch (IOException e) { } } finally { try { dump(); } catch (Exception e) { } } finally { try { channel.truncate(); channel.close(); fos.close(); } catch (IOException e) { } }
public DescribeMatchmakingRuleSetsResult describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { request = beforeClientExecution(request); return executeDescribeMatchmakingRuleSets(request); }
return null; int wordId, char[] surface, int off, int len) { String getPronunciation(
return pathStr;
public static double devsq(double[] v) { if (v == null || v.length == 0) return Double.NaN; double s = 0; int n = v.length; for (int i = 0; i < n; i++) s += v[i]; double m = s / n; s = 0; for (int i = 0; i < n; i++) { double d = v[i] - m; s += d * d; } return s; }
public DescribeResizeResult describeResize(DescribeResizeRequest request) {request = beforeClientExecution(request);return executeDescribeResize(request);}
return passedThroughNonGreedyDecision; } boolean hasPassedThroughNonGreedyDecision() {
end int } { ) ( ; return end ) (
public void traverse(ICellHandler handler) { SimpleCellWalkContext ctx = new SimpleCellWalkContext(range.getFirstRow(), range.getLastRow(), range.getFirstColumn(), range.getLastColumn()); for (int rowNumber = ctx.getFirstRow(); rowNumber <= ctx.getLastRow(); rowNumber++) { IRow currentRow = sheet.getRow(rowNumber); if (currentRow == null) { if (traverseEmptyCells) { for (int colNumber = ctx.getFirstColumn(); colNumber <= ctx.getLastColumn(); colNumber++) { ctx.setRowNumber(rowNumber); ctx.setColNumber(colNumber); ctx.setCurrentCell(null); handler.onCell(null, ctx); ctx.setOrdinalNumber(ctx.getOrdinalNumber() + 1); } } continue; } for (int colNumber = ctx.getFirstColumn(); colNumber <= ctx.getLastColumn(); colNumber++) { ICell currentCell = currentRow.getCell(colNumber); if (currentCell == null) { if (traverseEmptyCells) { ctx.setRowNumber(rowNumber); ctx.setColNumber(colNumber); ctx.setCurrentCell(null); handler.onCell(null, ctx); } } else { ctx.setRowNumber(rowNumber); ctx.setColNumber(colNumber); ctx.setCurrentCell(currentCell); handler.onCell(currentCell, ctx); } ctx.setOrdinalNumber(ctx.getOrdinalNumber() + 1); } } }
public int getReadIndex() { return _ReadIndex; }
public int compareTo(ScoreTerm other) { if (Term.equals(other.Term)) { return Boost.compareTo(other.Boost); } else { return Term.compareTo(other.Term); } }
public static int normalize(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case HAMZA_ABOVE: s[i] = 0; break; case HEH_GOAL: case HEH_YEH: s[i] = HEH; break; case KEHEH: s[i] = KAF; break; case YEH_BARREE: case FARSI_YEH: s[i] = YEH; break; default: break; } } len = StemmerUtil.delete(s, len, YEH, KAF, HEH); return len; }
out1.writeShort();
} {  ; DiagnosticErrorListener exactOnly ) exactOnly bool ( exactOnly .
} { ; ; KeySchemaElement keyType _keyType attributeName _attributeName ) keyType KeyType , attributeName String (
return Invoke(new InvokeOptions(options, RequestMarshaller.GetAssignmentRequestMarshaller.Instance, ResponseUnmarshaller.GetAssignmentResponseUnmarshaller.Instance), request);
return FindOffset(AnyObjectId) != -1;
setAllGroups(groupingSearch); return; allGroups boolean allGroups allGroups.
SetMultiValued void } { ) , ( ) ( synchronized v boolean dimName String } { else ) ( if } { } { ! ; ; v ) , ( TryGetValue . fieldTypes IsMultiValued . fieldType DimConfig new fieldTypes out } { ] [ fieldType DimConfig v IsMultiValued
public int getCellsVal() { int size = 0; if (cells != null) { for (char c : cells.keySet()) { size++; } } return size; }
public DeleteVoiceConnectorResult deleteVoiceConnector(DeleteVoiceConnectorRequest request) {request = beforeClientExecution(request);return executeDeleteVoiceConnector(request);}
return invoke(request, DeleteLifecyclePolicyRequestMarshaller.getInstance(), DeleteLifecyclePolicyResponseUnmarshaller.getInstance(), options);
