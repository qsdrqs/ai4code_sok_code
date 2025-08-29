// Cannot translate invalid/malformed code
public void addAll() { for(int srcDirIdx = 0; srcDirIdx < src.size; srcDirIdx++) { if(src.tailDirIdx == 0) { if(src.tailBlkIdx != 0) { return; } src.directory[src.tailBlock]; } } }
public void writeByte(byte b) { currentBlock[upto++] = b; if (upto == outerInstance.blockSize) { outerInstance.blocks.add(currentBlock); currentBlock = new byte[outerInstance.blockSize]; upto = 0; } }
public ObjectId getObjectId() { return objectId; }
return Invoke(request, new InvokeOptions(RequestMarshaller.DeleteDomainEntryRequestMarshaller.getInstance(), ResponseUnmarshaller.DeleteDomainEntryResponseUnmarshaller.getInstance()));
return fst == null ? 0 : fst.getSizeInBytes();
public String getFullMessage() { byte[] raw = buffer; Encoding enc = RawParseUtils.parseEncoding(raw); byte[] msgB = RawParseUtils.tagMessage(raw); if (msgB.length == 0) return ""; return RawParseUtils.decode(enc, msgB, 0, msgB.length); }
POIFSFileSystem _root; ArrayList _documents; PropertyTable _property_table; HeaderBlock headerBlock = new HeaderBlock(); PropertyTable propertyTable = new PropertyTable(headerBlock); ArrayList documents = new ArrayList();
// Cannot translate: Code syntax is severely corrupted and unintelligible
return SubmoduleAddCommand.setPath(path);
return invoke(request, new InvokeOptions().withRequestMarshaller(ListIngestionsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListIngestionsResponseUnmarshaller.getInstance()));
// Cannot translate - invalid/malformed source code
return invoke(GetShardIteratorRequestMarshaller.getInstance(), GetShardIteratorResponseUnmarshaller.getInstance(), request, new InvokeOptions(options));
// Cannot translate invalid/malformed C# code
// Cannot translate: provided C# code is corrupted or invalid
public EscherOptRecord GetOptRecord { get { return _optRecord; } }
public int read(byte[] buffer, int offset, int length) { if (buffer == null) throw new IllegalArgumentException(); if (length == 0) return 0; java.util.Arrays.checkOffsetAndCount(buffer.length, offset, length); int copylen = Math.min(length, count - pos); for (int i = 0; i < copylen; i++) buffer[offset + i] = this.buffer[pos + i]; pos += copylen; return copylen; }
} {  ; OpenNLPSentenceBreakIterator sentenceOp ) sentenceOp NLPSentenceDetectorOp ( sentenceOp .
System.out.print(str != null ? StringHelper.getValueOf(str) : null);
public class NotImplementedFunctionException extends RuntimeException { public NotImplementedFunctionException(String functionName, Throwable cause) { super(functionName, cause); } }
next V } { ) ( ; return value . ) ( nextEntry .
void readBytes(byte[] b, int offset, int len, boolean useBuffer) throws EndOfStreamException { int available = bufferLength - bufferPosition; if (useBuffer && available > 0) { if (len <= available) { System.arraycopy(buffer, bufferStart + bufferPosition, b, offset, len); bufferPosition += len; } else { System.arraycopy(buffer, bufferStart + bufferPosition, b, offset, available); bufferPosition = bufferLength; readBytes(b, offset + available, len - available, useBuffer); } } else { if (len < bufferSize) { refill(); available = bufferLength - bufferPosition; if (len > available) throw new EndOfStreamException(); System.arraycopy(buffer, bufferStart + bufferPosition, b, offset, len); bufferPosition += len; } else { long after = bufferStart + bufferPosition + len; if (after > length) throw new EndOfStreamException(); readInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
return Invoke(request, new InvokeOptions().RequestMarshaller(TagQueueRequestMarshaller.Instance).ResponseUnmarshaller(TagQueueResponseUnmarshaller.Instance));
throw new UnsupportedOperationException();
return Invoke(request, new InvokeOptions(RequestMarshaller.ModifyCacheSubnetGroupRequestMarshaller.Instance, ResponseUnmarshaller.ModifyCacheSubnetGroupResponseUnmarshaller.Instance, options));
void SetParams(String params) { StringTokenizer st = new StringTokenizer(params, " "); if (st.hasMoreTokens()) { st.nextToken(); } if (st.hasMoreTokens()) { st.nextToken(); } if (st.hasMoreTokens()) { st.nextToken(); } }
return Invoke(new InvokeOptions(DeleteDocumentationVersionRequestMarshaller.Instance, DeleteDocumentationVersionResponseUnmarshaller.Instance, options), request);
public boolean equals(Object obj) { if (obj == null || !(obj instanceof FacetLabel)) return false; FacetLabel other = (FacetLabel) obj; if (other.length != this.length) return false; for (int i = 0; i < this.length; i++) { if (!this.components[i].equals(other.components[i])) return false; } return true; }
return invoke(new InvokeOptions(RequestMarshaller.getInstance().getInstanceAccessDetailsRequestMarshaller(), ResponseUnmarshaller.getInstance().getInstanceAccessDetailsResponseUnmarshaller()), request);
HSSFPolygon shape = new HSSFPolygon(); shape.setAnchor(anchor); shape.onCreate(anchor); shapes.add(shape); shape.setParent(shape.getAnchor()); return shape;
String GetSheetName(int sheetIndex) { return Sheetname.GetBoundSheetRec(sheetIndex); }
return invoke(request, new InvokeOptions().withRequestMarshaller(GetDashboardRequestMarshaller.getInstance()).withResponseUnmarshaller(GetDashboardResponseUnmarshaller.getInstance()));
public AssociateSigninDelegateGroupsWithAccountResponse associateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) { InvokeOptions options = new InvokeOptions(); return invoke(request, options, AssociateSigninDelegateGroupsWithAccountRequestMarshaller.getInstance(), AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.getInstance()); }
void AddMultipleBlanks(MulBlankRecord mbr) { for(int j = 0; j < mbr.NumColumns; j++) { BlankRecord br = new BlankRecord(); br.Row = mbr.Row; br.Column = mbr.FirstColumn + j; br.XFIndex = mbr.GetXFAt(j); InsertCell(br); } }
String quote = ""; StringBuilder sb = new StringBuilder(); int k = 0; while(true) { if(k >= 0) { sb.append("\\"); sb.append(string.substring(0, k)); sb.append("\\\\"); k = string.indexOf("\\", k + 2); } return sb.toString(); }
ByteBuffer.putInt(value); throw new java.nio.ReadOnlyBufferException();
for(int r = 0; r < nRows; r++) { for(int c = 0; c < nColumns; c++) { Object[] rowData = new Object[values2d.length]; if(values2d.length == 0) rowData[c] = values2d[r][c]; } } Object[][] vv = new Object[nRows][nColumns]; int _nRows = (short)nRows; int _nColumns = (short)nColumns; int _reserved0Int = 0; short _reserved1Short = 0; byte _reserved2Byte = 0; ArrayPtg arrayValues = vv; for(int r = 0; r < _nRows; r++) { for(int c = 0; c < _nColumns; c++) { getValueIndex(_nRows * _nColumns); } }
return Invoke<GetIceServerConfigResponse>(new InvokeOptions(options) { RequestMarshaller = GetIceServerConfigRequestMarshaller.Instance, ResponseUnmarshaller = GetIceServerConfigResponseUnmarshaller.Instance }, request);
public String toString() { StringBuilder sb = new StringBuilder(); sb.append(getClass().getSimpleName()).append(" ").append(getValueAsString()).append(" "); return sb.toString(); }
public String toString() { return field + " " + _parentQuery + " "; }
refCount.incrementAndGet();
return invoke(UpdateConfigurationSetSendingEnabledRequestMarshaller.getInstance(), UpdateConfigurationSetSendingEnabledResponseUnmarshaller.getInstance(), request, new InvokeOptions());
return GetXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE;
void multiplyByPowerOfTen() { if (pow10 < 0) { TenPower tp = TenPower.getInstance(Math.abs(pow10)); mulShift = mulShift(tp._divisor, tp._divisorShift, tp._multiplicand, tp._multiplierShift); } else { } }
StringBuilder builder = new StringBuilder(); for(int i = 0; i < length; i++) { if(i < length - 1) { builder.append(getComponent(i)).append(File.separatorChar); } else { builder.append(getComponent(i)); } } return builder.toString();
// Cannot translate - original C# code is malformed and not syntactically valid
void SetProgressMonitor(ProgressMonitor pm) { }
Reset void } { ) ( ) ( if } { First ! ) ( if ; } { Eof ! 0 ptr ; ParseEntry ) (
if (start >= 0) { throw new java.util.NoSuchElementException(); } return iterator.previous(); return iterator.previousIndex();
public string GetNewPrefix { get { return newPrefix; } }
public int indexOfValue(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1; }
List<CharsRef> deduped = new ArrayList<>(); CharArraySet terms = new CharArraySet(8, true); for (CharsRef s : stems) { if (s.length >= 2) { if (!terms.contains(s)) { terms.add(s); deduped.add(s); } } } return deduped;
return Instance.Invoke(new InvokeOptions(RequestMarshaller.GetGatewayResponsesRequestMarshaller.Instance, ResponseUnmarshaller.GetGatewayResponsesResponseUnmarshaller.Instance, options), request);
void SetPosition(long position) { currentBlockIndex = (int)(position >> outerInstance.blockBits); currentBlock = outerInstance.blocks[currentBlockIndex]; currentBlockUpto = (int)(position & outerInstance.blockMask); }
Skip long } { ) ( ; s return ; ; n long s ptr int s = ) int ( ) , ( Min . Math Available ) , ( Max . Math ) (
// Cannot translate: Invalid/corrupted C# syntax
public void serialize(LittleEndianOutput out1) { out1.writeShort(field_1); out1.writeShort(field_2); out1.writeShort(field_3); out1.writeShort(field_4); out1.writeShort(field_5_hasMultibyte ? 0x01 : 0x00); out1.writeByte(field_6_author.length()); if (field_5_hasMultibyte) { StringUtil.putUnicodeLE(field_6_author, out1); } else { StringUtil.putCompressedUnicode(field_6_author, out1); } if (field_7_padding != null) { out1.write(field_7_padding); } }
return string.lastIndexOf();
add boolean } { ) ( ; return object E addLastImpl ) (
UnsetSection void } { ) , ( ; ) ( while do ; ; subsection String section String ! } { ConfigSnapshot ConfigSnapshot ; ; ) , ( compareAndSet . state res src UnsetSection ) , , ( ) ( get . state
public string GetTagName { get; return tagName; }
subrecords.add(index, element);
// Invalid/corrupted input - cannot translate meaningless text to Java
return new DoubleMetaphoneFilter(input);
// Cannot translate - original C# code is malformed
void setValue(boolean newValue) { value = newValue; }
// Cannot translate malformed/corrupted code
if (i > count) throw Sharpen.Extensions.CreateIndexOutOfRangeException(); return entries[i];
// Cannot translate - invalid/corrupted source code
public boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public void remove() { if (modCount != expectedModCount) throw new java.util.ConcurrentModificationException(); if (lastLink == null) throw new IllegalStateException(); if (lastLink == list.head) list.head = lastLink.next; else lastLink.previous.next = lastLink.next; if (lastLink == list.tail) list.tail = lastLink.previous; else lastLink.next.previous = lastLink.previous; list.size--; modCount++; expectedModCount++; lastLink = null; pos--; }
return Instance.Invoke(MergeShardsRequestMarshaller.getInstance(), MergeShardsResponseUnmarshaller.getInstance(), request, new InvokeOptions());
return Invoke<AllocateHostedConnectionRequest, AllocateHostedConnectionResponse>(request, AllocateHostedConnectionRequestMarshaller.Instance, AllocateHostedConnectionResponseUnmarshaller.Instance, new InvokeOptions(options));
public int getBeginIndex() { return start; }
// Cannot translate - invalid C# syntax
throw new java.nio.ReadOnlyBufferException();
void Decode(int[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; i++) { byte byte0 = (byte) blocks[blocksOffset++]; byte byte1 = (byte) blocks[blocksOffset++]; byte byte2 = (byte) blocks[blocksOffset++]; values[valuesOffset++] = (int) byte0 & 0x3F; values[valuesOffset++] = ((int) byte0 >> 6 & 0x03) | ((int) byte1 & 0x0F) << 2; values[valuesOffset++] = ((int) byte1 >> 4 & 0x0F) | ((int) byte2 & 0x03) << 4; values[valuesOffset++] = (int) byte2 >> 2 & 0x3F; } }
public String getHumanishName(String s) { if (s == null) throw new IllegalArgumentException(); String[] elements = s.split("[/\\\\]"); if (elements.length == 0) throw new IllegalArgumentException(); String result = elements[elements.length - 1]; if (result.endsWith(Constants.DOT_GIT_EXT)) result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); if (result.equals(Constants.DOT_GIT)) { if (elements.length > 1) result = elements[elements.length - 2]; else throw new IllegalArgumentException(); } if (result.equals("") || result.equals(".") || result.equals("..")) throw new IllegalArgumentException(); return result; }
DescribeNotebookInstanceLifecycleConfigResponse describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { InvokeOptions options = new InvokeOptions(); return invoke(request, options, DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance(), DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance()); }
public String getAccessKeySecret() { return accessSecret; }
return invoke(request, CreateVpnConnectionRequestMarshaller.getInstance(), CreateVpnConnectionResponseUnmarshaller.getInstance(), new InvokeOptions());
public DescribeVoicesResponse describeVoices(DescribeVoicesRequest request) { InvokeOptions options = new InvokeOptions(); return invoke(request, options, DescribeVoicesRequestMarshaller.getInstance(), DescribeVoicesResponseUnmarshaller.getInstance()); }
return invoke(ListMonitoringExecutionsRequestMarshaller.getInstance(), ListMonitoringExecutionsResponseUnmarshaller.getInstance(), request, new InvokeOptions(options));
public DescribeJobRequest(String jobId, String vaultName) { this._jobId = jobId; this._vaultName = vaultName; }
public EscherRecord getEscherRecord(int index) { return escherRecords[index]; }
return Invoke(new InvokeOptions(request, RequestMarshaller.Instance, ResponseUnmarshaller.GetApisRequestMarshaller.Instance, options), GetApisResponse.class);
return invoke(deleteSmsChannelRequest, new InvokeOptions(), DeleteSmsChannelRequestMarshaller.getInstance(), DeleteSmsChannelResponseUnmarshaller.getInstance());
public TrackingRefUpdate getTrackingRefUpdate() { return trackingRefUpdate; }
boolean b; System.out.println(String.valueOf(b));
GetChild IQueryNode } { ) ( ; return ] [ GetChildren ) (
} {  ; NotIgnoredFilter workdirTreeIndex ) workdirTreeIndex int ( index .
field_1_formatFlags = in1.readShort();
// Cannot translate malformed C# code
return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeTransitGatewayVpcAttachmentsRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance()));
return invoke(new InvokeOptions(PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance(), PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance()), request);
return prefixToOrdRange.containsKey(dim) ? (result = prefixToOrdRange.get(dim)) != null : false;
public String toString() { if (startIndex >= 0 && startIndex < symbol.getName().length()) { String text = Utils.escapeWhitespace(((CharStream)inputStream).getText(Interval.of(startIndex, startIndex))); return String.format(Locale.getDefault(), "LexerNoViableAltException('%s')", text.isEmpty() ? "<empty>" : text); } return String.format(Locale.getDefault(), "LexerNoViableAltException"); }
return peekFirstImpl();
return invoke(new InvokeOptions(CreateWorkspacesRequestMarshaller.getInstance(), CreateWorkspacesResponseUnmarshaller.getInstance(), request, options));
NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = field_1_formatIndex; return rec;
return invoke(new InvokeOptions(DescribeRepositoriesRequestMarshaller.getInstance(), DescribeRepositoriesResponseUnmarshaller.getInstance(), options), request);
public class SparseIntArray { private int mSize; private int[] mValues; private int[] mKeys; public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.idealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; } }
return new HyphenatedWordsFilter(input);
return Invoke(request, new InvokeOptions().withRequestMarshaller(CreateDistributionWithTagsRequestMarshaller.getInstance()).withResponseUnmarshaller(CreateDistributionWithTagsResponseUnmarshaller.getInstance()));
throw new java.io.IOException("Not implemented");
return Invoke(request, DeleteWorkspaceImageRequestMarshaller.getInstance(), DeleteWorkspaceImageResponseUnmarshaller.getInstance(), new InvokeOptions(options));
public String toHex(int value) { return Integer.toHexString(value); }
return invoke(new InvokeOptions().withRequestMarshaller(UpdateDistributionRequestMarshaller.getInstance()).withResponseUnmarshaller(UpdateDistributionResponseUnmarshaller.getInstance()), request);
public HSSFColor getColor(short index) { if (index == HSSFColor.AUTOMATIC.getIndex()) return HSSFColor.AUTOMATIC.getInstance(); byte b = palette.getColor(index); if (b != null) return new CustomColor(index, b); else return null; }
throw new NotImplementedFunctionException("Evaluate", srcRow, srcCol, operands);
void Serialize(ILittleEndianOutput out1) { out1.WriteShort((short)field_1_number_crn_records); out1.WriteShort((short)field_2_sheet_table_index); }
return describeDBEngineVersions(new DescribeDBEngineVersionsRequest());
} {  ; ; FormatRun fontIndex character ) fontIndex short , character short ( _fontIndex . _character .
public static byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int resultIndex = 0; int end = offset + length; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
return Invoke(request, UploadArchiveRequestMarshaller.getInstance(), UploadArchiveResponseUnmarshaller.getInstance(), new InvokeOptions());
List<Token> getHiddenTokensToLeft(int tokenIndex)
@Override public boolean equals(Object obj) { if (this == obj) return true; if (obj == null) return false; if (getClass() != obj.getClass()) return false; AutomatonQuery other = (AutomatonQuery) obj; if (m_term == null) { if (other.m_term != null) return false; } else if (!m_term.equals(other.m_term)) return false; if (m_compiled == null) { if (other.m_compiled != null) return false; } else if (!m_compiled.equals(other.m_compiled)) return false; return true; }
List<SpanQuery> spanQueries = new List<SpanQuery>(); for(wsq : weightBySpanQuery) { spanQueries.Add(new SpanQuery(wsq.Key, wsq.Boost.Value)); } spanQueries = spanQueries.ToArray(); if(spanQueries.Count == 1) { return spanQueries[0]; } else { return new SpanOrQuery(spanQueries); }
StashCreate StashCreateCommand() { return new StashCreateCommand(); }
FieldInfo ret; byName.get(fieldName); return ret;
return Invoke(new InvokeOptions(RequestMarshaller.DescribeEventSourceRequestMarshaller.Instance, ResponseUnmarshaller.DescribeEventSourceResponseUnmarshaller.Instance, options), request);
return invoke(request, GetDocumentAnalysisRequestMarshaller.getInstance(), GetDocumentAnalysisResponseUnmarshaller.getInstance(), new InvokeOptions());
return invoke(request, CancelUpdateStackRequestMarshaller.getInstance(), CancelUpdateStackResponseUnmarshaller.getInstance(), new InvokeOptions(options));
return Invoke(new InvokeOptions(ModifyLoadBalancerAttributesRequestMarshaller.Instance, ModifyLoadBalancerAttributesResponseUnmarshaller.Instance), request);
return invoke(request, SetInstanceProtectionRequestMarshaller.getInstance(), SetInstanceProtectionResponseUnmarshaller.getInstance(), new InvokeOptions(options));
return invoke(request, new InvokeOptions().withRequestMarshaller(ModifyDBProxyRequestMarshaller.getInstance()).withResponseUnmarshaller(ModifyDBProxyResponseUnmarshaller.getInstance()));
void add(CharsRef output, int offset, int len, int endOffset, int posLength) { if (count == outputs.length) { int next = ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF); CharsRef[] newOutputs = new CharsRef[next]; System.arraycopy(outputs, 0, newOutputs, 0, count); outputs = newOutputs; int[] newEndOffsets = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(endOffsets, 0, newEndOffsets, 0, count); endOffsets = newEndOffsets; int[] newPosLengths = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(posLengths, 0, newPosLengths, 0, count); posLengths = newPosLengths; } outputs[count] = new CharsRef(); copyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
// Cannot translate invalid C# syntax
return objects.exists();
// Cannot translate: Invalid or incomplete source code provided
} { : ) ( ScaleClusterRequest ; ; Method UriPattern ) , , , , ( PUT . MethodType " " " " " " " " " " " "
IDataValidationConstraint CreateTimeConstraint(int operatorType, String formula1, String formula2) { return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2); }
return Invoke(ListObjectParentPathsRequestMarshaller.getInstance(), ListObjectParentPathsResponseUnmarshaller.getInstance(), request, new InvokeOptions(options));
return invoke(new DescribeCacheSubnetGroupsRequest(), DescribeCacheSubnetGroupsRequestMarshaller.getInstance(), DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance(), new InvokeOptions());
void SetSharedFormula() { sharedFormula.SetShortBoolean(field_5_options, flag); }
public boolean isReuseObjects() { return reuseObjects; }
return AddChild(new ErrorNodeImpl(badToken)); IErrorNode AddErrorNode(IToken badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); Parent.AddChild(t); return t; }
if (args.size() > 0) { throw new IllegalArgumentException("args " + args); }
return Invoke(request, RemoveSourceIdentifierFromSubscriptionRequestMarshaller.getInstance(), RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.getInstance(), new InvokeOptions());
TokenFilterFactory.forName(name, loader).newInstance(args);
} { : ) ( AddAlbumPhotosRequest ; Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " "
return Instance.Invoke(request, new InvokeOptions(RequestMarshaller.Instance.GetThreatIntelSetRequestMarshaller, ResponseUnmarshaller.Instance.GetThreatIntelSetResponseUnmarshaller));
return AndTreeFilter.create(a.clone(), b.clone());
public override bool Equals(object o) {     return this == o; // or some comparison logic }
boolean hasArray() { return protectedHasArray; }
return Invoke(request, new InvokeOptions().withRequestMarshaller(UpdateContributorInsightsRequestMarshaller.getInstance()).withResponseUnmarshaller(UpdateContributorInsightsResponseUnmarshaller.getInstance()));
void unwriteProtectWorkbook() { records.remove(writeProtect); records.remove(fileShare); }
SolrSynonymParser(boolean dedup, boolean expand, Analyzer analyzer, Analyzer base) { }
return invoke(request, new InvokeOptions().withRequestMarshaller(RequestSpotInstancesRequestMarshaller.getInstance()).withResponseUnmarshaller(RequestSpotInstancesResponseUnmarshaller.getInstance()));
GetObjectData } { ) ( byte ; return ] [ ObjectData . FindObjectRecord ) (
return Invoke(request, new InvokeOptions().withRequestMarshaller(GetContactAttributesRequestMarshaller.getInstance()).withResponseUnmarshaller(GetContactAttributesResponseUnmarshaller.getInstance()));
public String toString() { return GetKey() + " " + GetValue(); }
return Invoke(new InvokeOptions(RequestMarshaller.Instance.ListTextTranslationJobsRequestMarshaller, ResponseUnmarshaller.Instance.ListTextTranslationJobsResponseUnmarshaller, options), request);
return invoke(request, new InvokeOptions().withRequestMarshaller(GetContactMethodsRequestMarshaller.getInstance()).withResponseUnmarshaller(GetContactMethodsResponseUnmarshaller.getInstance()));
short LookupIndexByName(String name) { FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name); if (fd == null) return (short)0; return (short)(fd.Index - 1); }
return invoke(request, DescribeAnomalyDetectorsRequestMarshaller.getInstance(), DescribeAnomalyDetectorsResponseUnmarshaller.getInstance(), new InvokeOptions());
String insertId; ObjectId changeId; return changeId; String message;
public long getObjectSize(AnyObjectId objectId, int typeHint) throws MissingObjectException { long sz = db.getObjectSize(objectId.copy(), typeHint); if (sz < 0) { throw new MissingObjectException(objectId.copy(), ""); } if (typeHint == OBJ_ANY) { throw new MissingObjectException(objectId.copy(), ""); } return sz; }
return Invoke(request, new InvokeOptions().withRequestMarshaller(ImportInstallationMediaRequestMarshaller.getInstance()).withResponseUnmarshaller(ImportInstallationMediaResponseUnmarshaller.getInstance()));
return Invoke(PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance(), PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance(), request, new InvokeOptions(options));
field_1_value = in1.readDouble();
return invoke(request, new InvokeOptions().withRequestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.getInstance()).withResponseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance()));
return Invoke(request, new InvokeOptions(), DescribeDetectorRequestMarshaller.getInstance(), DescribeDetectorResponseUnmarshaller.getInstance());
return invoke(request, new InvokeOptions().withRequestMarshaller(ReportInstanceStatusRequestMarshaller.getInstance()).withResponseUnmarshaller(ReportInstanceStatusResponseUnmarshaller.getInstance()));
return Invoke(new InvokeOptions(RequestMarshaller.DeleteAlarmRequestMarshaller.Instance, ResponseUnmarshaller.DeleteAlarmResponseUnmarshaller.Instance, options), request);
return new PortugueseStemFilter(input);
// Cannot translate: Original C# code is syntactically invalid
// Invalid/corrupted input - cannot translate meaningless text to Java
return Invoke(GetDedicatedIpRequestMarshaller.getInstance(), GetDedicatedIpResponseUnmarshaller.getInstance(), request, new InvokeOptions(options));
public String toString() { return precedence + " "; }
return invoke(new InvokeOptions().withRequestMarshaller(ListStreamProcessorsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListStreamProcessorsResponseUnmarshaller.getInstance()), request);
public DeleteLoadBalancerPolicyRequest(String policyName, String loadBalancerName) { this._policyName = policyName; this._loadBalancerName = loadBalancerName; }
} {  ; WindowProtectRecord options _options ) options int (
// Cannot translate: malformed C# syntax
return Invoke(request, new InvokeOptions().withRequestMarshaller(GetOperationsRequestMarshaller.getInstance()).withResponseUnmarshaller(GetOperationsResponseUnmarshaller.getInstance()));
NB.EncodeInt32(b, o); NB.EncodeInt32(b, o+4); NB.EncodeInt32(b, o+8); NB.EncodeInt32(b, o+12); NB.EncodeInt32(b, o+16); CopyRawTo();
WindowOneRecord(RecordInputStream in1) { field_1_h_hold = in1.readShort(); field_2_v_hold = in1.readShort(); field_3_width = in1.readShort(); field_4_height = in1.readShort(); field_5_options = in1.readShort(); field_6_active_sheet = in1.readShort(); field_7_first_visible_tab = in1.readShort(); field_8_num_selected_tabs = in1.readShort(); field_9_tab_width_ratio = in1.readShort(); }
return Invoke<StopWorkspacesRequest, StopWorkspacesResponse>(request, new InvokeOptions(RequestMarshaller.Instance.StopWorkspacesRequestMarshaller, ResponseUnmarshaller.Instance.StopWorkspacesResponseUnmarshaller, options));
throws IOException { try { if (isOpen()) { } } finally { try { dump(); } finally { try { channel.truncate(); } finally { channel.close(); fos.close(); } } } }
return invoke(new InvokeOptions().withRequestMarshaller(DescribeMatchmakingRuleSetsRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeMatchmakingRuleSetsResponseUnmarshaller.getInstance()), request, DescribeMatchmakingRuleSetsResponse.class);
string GetPronunciation(char[] surface, int wordId, int off, int len) { return null; }
public string GetPath { get { return pathStr; } }
public static double calculateVariance(double[] v) { if (v == null || v.length == 0) return Double.NaN; double sum = 0; for (int i = 0; i < v.length; i++) { sum += v[i]; } double mean = sum / v.length; double devsq = 0; for (int i = 0; i < v.length; i++) { double diff = v[i] - mean; devsq += diff * diff; } return v.length == 1 ? 0 : devsq / (v.length - 1); }
return invoke(request, new InvokeOptions(), DescribeResizeRequestMarshaller.getInstance(), DescribeResizeResponseUnmarshaller.getInstance());
boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
end int } { ) ( ; return end ) (
for (int rowNumber = firstRow; rowNumber <= lastRow; rowNumber++) { IRow currentRow = sheet.getRow(rowNumber); if (currentRow == null) { currentRow = null; } for (int colNumber = firstColumn; colNumber <= lastColumn; colNumber++) { ICell currentCell = null; if (currentRow != null) { currentCell = currentRow.getCell(colNumber); } SimpleCellWalkContext ctx = new SimpleCellWalkContext(rowNumber, colNumber, rowNumber - firstRow, colNumber - firstColumn, width, range.getFirstRow(), range.getLastRow(), range.getFirstColumn(), range.getLastColumn()); if (currentCell == null) { if (!traverseEmptyCells) { continue; } } else { if (currentCell.isEmpty()) { continue; } } handler.onCell(currentCell, ctx); ctx.ordinalNumber++; } }
public int getReadIndex() { return _ReadIndex; }
public int compareTo(ScoreTerm other) { if (other == null) return 1; int termComparison = this.term.compareTo(other.term); if (termComparison != 0) return termComparison; return Double.compare(this.boost, other.boost); }
int normalize(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case HAMZA_ABOVE: len = StemmerUtil.delete(s, i, len); i--; break; case HEH_GOAL: case HEH_YEH: s[i] = HEH; break; case KEHEH: s[i] = KAF; break; case YEH_BARREE: case FARSI_YEH: s[i] = YEH; break; default: break; } } return len; }
// Cannot translate invalid/malformed code
} {  ; DiagnosticErrorListener exactOnly ) exactOnly boolean ( exactOnly .
public KeySchemaElement(KeyType keyType, String attributeName) { this.keyType = keyType; this.attributeName = attributeName; }
return Invoke(new InvokeOptions(RequestMarshaller.GetAssignmentRequestMarshaller.Instance, ResponseUnmarshaller.GetAssignmentResponseUnmarshaller.Instance), request);
return FindOffset(id) != AnyObjectId - 1;
public void setAllGroups(boolean allGroups) { this.allGroups = allGroups; return; }
synchronized(lock) { if(fieldTypes.containsKey(dimName)) { DimConfig fieldType = fieldTypes.get(dimName); boolean v = fieldType.isMultiValued(); } else { boolean v = false; } }
// Unable to translate - source code is syntactically invalid and incomprehensible
return Invoke(request, new InvokeOptions(DeleteVoiceConnectorRequestMarshaller.getInstance(), DeleteVoiceConnectorResponseUnmarshaller.getInstance()));
return Invoke(new InvokeOptions(RequestMarshaller.DeleteLifecyclePolicyRequestMarshaller.Instance, ResponseUnmarshaller.DeleteLifecyclePolicyResponseUnmarshaller.Instance, options), request);
