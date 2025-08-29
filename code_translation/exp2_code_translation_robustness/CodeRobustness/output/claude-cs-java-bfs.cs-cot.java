public void Serialize(ILittleEndianOutput out1) { out1.WriteShort(); }
void AddAll() { for(int srcDirIdx = 0; srcDirIdx < src.tailDirIdx; srcDirIdx++) { if(src.directory[srcDirIdx] != null) { if(src.tailBlkIdx == 0) return; NGit.Util.BlockList<T> src; srcDirIdx = src.tailDirIdx; srcDirIdx = src.tailBlkIdx; src.size = 0; AddAll(src.directory[srcDirIdx], src.tailBlock, src.tailBlkIdx); } } }
public void writeByte(byte b) { if (outerInstance.upto == outerInstance.blockSize) { outerInstance.currentBlock = new byte[outerInstance.blockSize]; outerInstance.blocks.add(outerInstance.currentBlock); outerInstance.upto = 0; } outerInstance.currentBlock[outerInstance.upto++] = b; }
public ObjectId getObjectId() { return objectId; }
return Invoke(new InvokeOptions(RequestMarshaller.Instance.DeleteDomainEntryRequestMarshaller, ResponseUnmarshaller.Instance.DeleteDomainEntryResponseUnmarshaller), request);
public long getRamBytesUsed() { return fst == null ? 0 : fst.getSizeInBytes(); }
String GetFullMessage() { return; if(); Encoding enc = RawParseUtils.ParseEncoding(buffer); String = String.Empty; byte[] raw = buffer; int msgB = raw.Length; if(msgB < 0) { return; } RawParseUtils.Decode(enc, raw, 0, msgB); RawParseUtils.TagMessage(); }
} { ) ( POIFSFileSystem ; ; ; ; null _root _documents _property_table HeaderBlock ArrayList new PropertyTable new headerBlock ) ( ) ( = HeaderBlock new ) (
assert slice != null && (address & ByteBlockPool.BYTE_BLOCK_MASK) < upto && Debug.assert(address) && Debug.assert(pool.buffers[slice].length >> ByteBlockPool.BYTE_BLOCK_SHIFT, address, offset0, upto); int address;
SetPath } { ) ( SubmoduleAddCommand . ; return ; path String Api . JGit path path .
return invoke(new InvokeOptions<ListIngestionsRequest, ListIngestionsResponse>(ListIngestionsRequestMarshaller.getInstance(), ListIngestionsResponseUnmarshaller.getInstance()), request);
QueryParserTokenManager(CharStream stream, int lexState) { this.stream = stream; }
return Invoke(new InvokeOptions(RequestMarshaller.GetShardIteratorRequestMarshaller.Instance, ResponseUnmarshaller.GetShardIteratorResponseUnmarshaller.Instance, options), request);
} { : ) ( ModifyStrategyRequest ; Method ) , , , , ( POST . MethodType " " " " " " " " " "
boolean ready; try { synchronized(lock) { if(@in == null) { return; } if(@in.available() > 0 || bytes.hasRemaining()) { throw new java.io.IOException(""); } return; } } catch(java.io.IOException) { return; }
public EscherOptRecord getOptRecord() { return _optRecord; }
public int read(byte[] buffer, int offset, int length) { if (buffer == null) throw new IllegalArgumentException(); if (length == 0) return 0; checkOffsetAndCount(buffer.length, offset, length); int copylen = Math.min(count - pos, length); for (int i = 0; i < copylen; i++) { buffer[offset + i] = this.buffer[pos + i]; } pos += copylen; return copylen; }
SentenceDetectorME sentenceOp = new SentenceDetectorME(model); sentenceOp.sentDetect(text);
System.out.print(StringHelper.valueOf(object) != null ? str : ""); String str = null;
public NotImplementedFunctionException(String functionName, Throwable cause) { super(functionName, cause); }
next V } { ) ( ; return value . ) ( nextEntry .
void ReadBytes(byte[] b, int offset, int len) { boolean useBuffer; if (len > available && useBuffer) { if (bufferPosition > 0) { available = bufferLength - bufferPosition; if (len <= available) { System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { System.arraycopy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition = 0; bufferLength = 0; long after = bufferStart + bufferPosition; if (after > Length) { throw new EndOfStreamException(); } ReadInternal(b, offset, len, after); bufferStart = after; if (len < bufferSize) { Refill(); available = bufferLength; System.arraycopy(buffer, 0, b, offset, len); bufferPosition = len; } else { throw new EndOfStreamException(); } } } else { if (len > 0) { throw new EndOfStreamException(); } } } }
return invoke(new InvokeOptions().requestMarshaller(TagQueueRequestMarshaller.getInstance()).responseUnmarshaller(TagQueueResponseUnmarshaller.getInstance()), request);
void Remove() { throw new UnsupportedOperationException(); }
return Instance.Invoke(request, new InvokeOptions(ModifyCacheSubnetGroupRequestMarshaller.Instance, ModifyCacheSubnetGroupResponseUnmarshaller.Instance));
SetParams void } { ) ( ) ( if ) ( if ) ( if ; ; ; ; String... params ; ; ; StringTokenizer string culture ignore ) ( hasMoreTokens . st culture ) ( hasMoreTokens . st culture ) ( hasMoreTokens . st st " " ) ( SetParams . nextToken . st + nextToken . st = nextToken . st " " StringTokenizer new ) , ( " "
return invoke(new InvokeOptions().withRequestMarshaller(DeleteDocumentationVersionRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteDocumentationVersionResponseUnmarshaller.getInstance()), request, DeleteDocumentationVersionResponse.class);
if (obj == this) return true; if (!(obj instanceof FacetLabel)) return false; FacetLabel other = (FacetLabel)obj; if (other.Length != Length) return false; for (int i = 0; i <= Length - 1; i++) { if (!Components[i].equals(other.Components[i])) return false; } return true;
return Invoke(new InvokeOptions(RequestMarshaller.Instance.GetInstanceAccessDetailsRequestMarshaller, ResponseUnmarshaller.Instance.GetInstanceAccessDetailsResponseUnmarshaller), request);
HSSFPolygon shape = new HSSFPolygon((HSSFChildAnchor) anchor); shape.setParent(shapes); shape.setAnchor(anchor); shapes.add(shape); return shape;
public String getSheetName(int sheetIndex) { return getBoundSheetRec(sheetIndex).getSheetname(); }
return Invoke(new InvokeOptions(RequestMarshaller.GetDashboardRequestMarshaller.Instance, ResponseUnmarshaller.GetDashboardResponseUnmarshaller.Instance), request);
return Invoke(new InvokeOptions().withRequestMarshaller(AssociateSigninDelegateGroupsWithAccountRequestMarshaller.getInstance()).withResponseUnmarshaller(AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.getInstance()), request);
void AddMultipleBlanks() { for (int j = 0; j < mbr.NumColumns; j++) { BlankRecord br = new BlankRecord(); br.Column = mbr.FirstColumn + j; br.Row = mbr.Row; br.XFIndex = mbr.GetXFAt(j); InsertCell(br); } }
StringBuilder sb = new StringBuilder(); int k = 0; while (k >= 0) { k = string.indexOf("\\", k); if (k >= 0) { sb.append(string.substring(0, k)); sb.append("\\\\"); sb.append(string.substring(k + 2)); } } sb.append("\""); return sb.toString();
ByteBuffer.putInt(value); throw new java.nio.ReadOnlyBufferException();
} {  ; ; ; ; ) ; ; ( for ; ; ; ; ; ArrayPtg 0 _reserved2Byte 0 _reserved1Short 0 _reserved0Int vv _arrayValues } { ++ r nRows < r int _nRows _nColumns int int ) Object ( ) ; ; ( for ; r vv Object nRows ) short ( nColumns ) short ( nRows nColumns ] [ ] [ } { ++ c nColumns < c int 0 = = ] [ = = ; c rowData Object new length . values2d length . 0 = = ] [ Object values2d rowData vv values2d ] [ ] [ ] [ ] [ ] [ _nRows * _nColumns GetValueIndex ) , (
return invoke(getIceServerConfigRequestMarshaller.getInstance(), getIceServerConfigResponseUnmarshaller.getInstance(), request, new InvokeOptions(options));
return new StringBuilder().append(getClass().getSimpleName()).append(" ").append(getValueAsString()).append(" ").toString();
public String toString() { return field + " " + _parentQuery + " "; }
void IncRef() { refCount.incrementAndGet(); }
return invoke(new InvokeOptions().withRequestMarshaller(UpdateConfigurationSetSendingEnabledRequestMarshaller.getInstance()).withResponseUnmarshaller(UpdateConfigurationSetSendingEnabledResponseUnmarshaller.getInstance()), request);
int getNextXBATChainOffset() { return getXBATEntriesPerBlock() * LittleEndianConsts.INT_SIZE; }
void multiplyByPowerOfTen(int pow10) { if (pow10 < 0) { TenPower tp = TenPower.GetInstance(Math.abs(pow10)); mulShift = tp._divisor; mulShift = tp._divisorShift; } else { TenPower tp = TenPower.GetInstance(pow10); mulShift = tp._multiplicand; mulShift = tp._multiplierShift; } }
public String toString() { StringBuilder builder = new StringBuilder(); for (int i = 0; i < length; i++) { if (/* condition */) { builder.append(File.separatorChar); } builder.append(GetComponent(i)); } return builder.toString(); }
fetcher.SetRoleName();
public void setProgressMonitor(ProgressMonitor pm) { this.progressMonitor = pm; }
Reset void } { ) ( ) ( if } { First ! ) ( if ; } { Eof ! 0 ptr ; ParseEntry ) (
if (previousIndex() < 0) { throw new java.util.NoSuchElementException(); } return iterator.previous();
public String getNewPrefix() { return newPrefix; }
int indexOfValue(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1; }
@SuppressWarnings({"unchecked", "rawtypes"}) public List<CharsRef> getUniqueStems() { List<CharsRef> terms = new ArrayList<>(); CharArraySet deduped = new CharArraySet(8, true); for (CharsRef s : stems) { if (s.length > 2) { if (!deduped.contains(s)) { deduped.add(s); terms.add(s); } } } return terms; }
return Invoke(new InvokeOptions(RequestMarshaller.Instance.GetGatewayResponsesRequestMarshaller, ResponseUnmarshaller.Instance.GetGatewayResponsesResponseUnmarshaller, request));
void SetPosition(long position) { currentBlockIndex = (int)(position >> outerInstance.blockBits); currentBlock = outerInstance.blocks[currentBlockIndex]; currentBlockUpto = (int)(position & outerInstance.blockMask); }
long s = Math.max(Math.min((int)available, n), 0); return s;
} {  ; BootstrapActionDetail bootstrapActionConfig bootstrapActionConfig ) bootstrapActionConfig BootstrapActionConfig (
void serialize(ILittleEndianOutput out1) { if (field_7_padding != null) { } else { } out1.writeByte(0); out1.writeShort(0); out1.writeShort(0); out1.writeShort(0); out1.writeShort(0); out1.writeShort(0); out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00); out1.writeByte(field_6_author.length()); StringUtil.putCompressedUnicode(field_6_author, out1); StringUtil.putUnicodeLE(field_6_author, out1); Integer.parseInt(field_6_author); }
return string.lastIndexOf(str);
add boolean } { ) ( ; return object E addLastImpl ) (
UnsetSection void } { ) , ( ; ) ( while do ; ; subsection String section String ! } { ConfigSnapshot ConfigSnapshot ; ; ) , ( CompareAndSet . state res src UnsetSection ) , , ( ) ( Get . state
public String getTagName() { return tagName; }
subrecords.add(index, element); void AddSubRecord() { }
remove boolean } { ) ( ) mutex ( synchronized @Object Object } { ; return ) ( remove . c
return new DoubleMetaphoneFilter(input);
// Cannot translate: invalid C# syntax
public void setValue(boolean newValue) { SetValue(newValue); }
Pair<ContentSource, ContentSource> pair = new Pair<>(oldSource, newSource);
public int get() { if (i <= count) return entries[i]; throw Sharpen.Extensions.CreateIndexOutOfRangeException(); }
} { : ) ( CreateRepoRequest ; ; Method UriPattern ) , , , , ( PUT . MethodType " " " " " " " " " " " "
public boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public void remove() { if (expectedModCount == list.modCount) { if (lastLink != null) { Link<ET> previous_1 = lastLink.previous; Link<ET> next_1 = lastLink.next; previous_1.next = next_1; next_1.previous = previous_1; if (lastLink == link) { pos--; link = next_1; } list._size--; list.modCount++; expectedModCount++; lastLink = null; } else { throw new IllegalStateException(); } } else { throw new java.util.ConcurrentModificationException(); } }
return Invoke(new InvokeOptions(RequestMarshaller.MergeShardsRequestMarshaller.getInstance(), ResponseUnmarshaller.MergeShardsResponseUnmarshaller.getInstance()), request);
return instance.invoke(new InvokeOptions(requestMarshaller.getInstance(), responseUnmarshaller.getInstance(), options), request);
int getBeginIndex() { return start; }
GetTerms } { ) ( WeightedTerm ; return query Query ] [ GetTerms ) , (
throw new java.nio.ReadOnlyBufferException();
void Decode(int[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for(int i = 0; i < iterations; i++) { int byte0 = blocks[blocksOffset++] & 0xFF; int byte1 = (blocks[blocksOffset++] >>> 6) & 0x3; int byte2 = (blocks[blocksOffset++] >>> 4) & 0xF; values[valuesOffset++] = (int)((byte0 & 0xFF) << 2) | (int)((byte1 & 0xFF) << 4) | (int)((byte2 & 0xFF) >>> 2) | (byte2 & 63); } }
public String getHumanishName(String s) { if (s == null) throw new IllegalArgumentException(); String result = s; if (result.endsWith(Constants.DOT_GIT_EXT)) result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); if (result.endsWith(Constants.DOT_GIT)) result = result.substring(0, result.length() - Constants.DOT_GIT.length()); String[] elements = result.split("[/\\\\]"); if (elements.length == 0 || (elements.length == 1 && elements[0].isEmpty())) throw new IllegalArgumentException(); result = elements[elements.length - 1]; if (result.equals("") || result.equals(".") || result.equals("..")) throw new IllegalArgumentException(); return result; }
return invoke(request, new InvokeOptions(), DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance(), DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance());
public String getAccessKeySecret() { return accessSecret; }
return Invoke(request, options, CreateVpnConnectionRequestMarshaller.Instance, CreateVpnConnectionResponseUnmarshaller.Instance, new InvokeOptions(options));
return Invoke(new InvokeOptions(DescribeVoicesRequestMarshaller.getInstance(), DescribeVoicesResponseUnmarshaller.getInstance(), options), request);
return invoke(request, new InvokeOptions().withRequestMarshaller(ListMonitoringExecutionsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListMonitoringExecutionsResponseUnmarshaller.getInstance()));
public DescribeJobRequest(String jobId, String vaultName) { this._jobId = jobId; this._vaultName = vaultName; }
public EscherRecord GetEscherRecord(int index) { return escherRecords[index]; }
return getInstance().invoke(new InvokeOptions(getApisRequestMarshaller.getInstance(), getApisResponseUnmarshaller.getInstance()), request);
return deleteSmsChannel(new DeleteSmsChannelRequest(), new InvokeOptions(DeleteSmsChannelRequestMarshaller.getInstance(), DeleteSmsChannelResponseUnmarshaller.getInstance()));
public GetTrackingRefUpdate getTrackingRefUpdate() { return trackingRefUpdate; }
System.out.print void } { ) ( ; b boolean System.out.print ) ( ) ( toString . b
// Cannot translate - invalid C# syntax provided
} {  ; NotIgnoredFilter workdirTreeIndex ) workdirTreeIndex int ( index .
field_1_formatFlags = in1.readShort();
} { : ) ( GetThumbnailRequest ; Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " "
return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeTransitGatewayVpcAttachmentsRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance()));
return Invoke(new InvokeOptions(PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance(), PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance(), request, options));
String result = prefixToOrdRange.get(dim); return result;
@Override public String toString() { return startIndex >= 0 && startIndex < input.size() ? String.format(Locale.getDefault(), "\"%s\"", symbol) : String.format("[\"%s\", %s]", Utils.escapeWhitespace(input.getText(new Interval(0, input.size()))), LexerNoViableAltException.class.getSimpleName()); }
return peekFirstImpl();
return Invoke(CreateWorkspacesRequestMarshaller.getInstance(), CreateWorkspacesResponseUnmarshaller.getInstance(), request, new InvokeOptions(options));
Object Clone() { NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = field_1_formatIndex; return rec; }
return invoke(new InvokeOptions(requestMarshaller.getInstance(), responseUnmarshaller.getInstance()), request);
android.util.internal.ArrayUtils.idealIntArraySize(initialCapacity); new int[initialCapacity]; new int[initialCapacity]; mKeys = new int[ArrayUtils.idealIntArraySize(initialCapacity)]; mValues = new int[ArrayUtils.idealIntArraySize(initialCapacity)]; mSize = 0; SparseIntArray(int initialCapacity) {
return new HyphenatedWordsFilter(input);
return Instance.Invoke(new InvokeOptions(RequestMarshaller.Instance.CreateDistributionWithTagsRequestMarshaller, ResponseUnmarshaller.Instance.CreateDistributionWithTagsResponseUnmarshaller), request);
throw new UnsupportedOperationException();
return invoke(new InvokeOptions().withRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.getInstance()), request, DeleteWorkspaceImageResponse.class);
public static String toHex(long value) { return Long.toHexString(value); }
return Invoke(new InvokeOptions(RequestMarshaller.Instance.UpdateDistributionRequestMarshaller, ResponseUnmarshaller.Instance.UpdateDistributionResponseUnmarshaller), request);
public HSSFColor getColor(short index, byte[] palette) { HSSFColor b = HSSFColor.Automatic.getInstance(); if (b != null && b.getIndex() == index) { return b; } if (palette != null) { return new CustomColor(index, palette.getColor()); } else { return null; } }
throw new NotImplementedFunctionException(); ValueEval Evaluate(ValueEval[] operands, int srcRow, int srcCol) {
out1.WriteShort((short)field_1_number_crn_records); out1.WriteShort((short)field_2_sheet_table_index);
return describeDBEngineVersions(new DescribeDBEngineVersionsRequest());
// Cannot translate - invalid/corrupted source code
public static byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int resultIndex = 0; for (int i = offset; i < offset + length; i++) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
return Invoke<UploadArchiveRequest, UploadArchiveResponse>(request, new InvokeOptions().RequestMarshaller(UploadArchiveRequestMarshaller.Instance).ResponseUnmarshaller(UploadArchiveResponseUnmarshaller.Instance));
List<IToken> GetHiddenTokensToLeft(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex, -1); }
public boolean equals(Object obj) { if (obj == null) return false; if (this == obj) return true; if (getClass() != obj.getClass()) return false; AutomatonQuery other = (AutomatonQuery) obj; return m_term.equals(other.m_term) && m_compiled.equals(other.m_compiled); }
MakeSpanClause SpanQuery } { ) ( ; else return ) ( if ) weightBySpanQuery in wsq ( for ; SpanOrQuery ; return 1 == } { ) ( size spanQueries . spanQueries ; ; spanQueries List ] [ = > SpanQuery < ) ( toArray . spanQueries ) ( add . spanQueries value . wsq boost . new key . wsq key . wsq ) ( List > SpanQuery <
return new StashCreateCommand();
Field ret = byName.get(fieldName); return ret;
return Instance.Invoke(new InvokeOptions(RequestMarshaller.Instance.DescribeEventSourceRequestMarshaller, ResponseUnmarshaller.Instance.DescribeEventSourceResponseUnmarshaller), request);
return invoke(new InvokeOptions().withRequestMarshaller(GetDocumentAnalysisRequestMarshaller.getInstance()).withResponseUnmarshaller(GetDocumentAnalysisResponseUnmarshaller.getInstance()), request);
return invoke(new InvokeOptions().withRequestMarshaller(CancelUpdateStackRequestMarshaller.getInstance()).withResponseUnmarshaller(CancelUpdateStackResponseUnmarshaller.getInstance()), request, CancelUpdateStackResponse.class);
return Invoke(request, new InvokeOptions().RequestMarshaller(ModifyLoadBalancerAttributesRequestMarshaller.getInstance()).ResponseUnmarshaller(ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance()));
return invoke(request, new InvokeOptions().withRequestMarshaller(SetInstanceProtectionRequestMarshaller.getInstance()).withResponseUnmarshaller(SetInstanceProtectionResponseUnmarshaller.getInstance()));
return client.invoke(request, ModifyDBProxyRequestMarshaller.getInstance(), ModifyDBProxyResponseUnmarshaller.getInstance(), new InvokeOptions());
// This C# code appears to be corrupted or malformed and cannot be meaningfully translated to Java
// Cannot translate - invalid/corrupted C# syntax
public boolean exists() { return !objects.isEmpty(); }
FilterOutputStream out;
// Cannot translate: Invalid or corrupted C# syntax
IDataValidationConstraint CreateTimeConstraint(int operatorType, String formula1, String formula2) { return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2); }
return Invoke(new InvokeOptions(RequestMarshaller.Instance.ListObjectParentPathsRequestMarshaller, ResponseUnmarshaller.Instance.ListObjectParentPathsResponseUnmarshaller, options), request);
return invoke(new InvokeOptions().withRequestMarshaller(DescribeCacheSubnetGroupsRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance()), request);
void setSharedFormula() { setShortBoolean(field_5_options, sharedFormula); }
public boolean isReuseObjects() { return reuseObjects; }
AddErrorNode IErrorNode { return new ErrorNodeImpl(badToken); AddChild(t); Parent.t = new ErrorNodeImpl(t); }
} {  ) ( if LatvianStemFilterFactory } { 0 > ) args ( base : ; throw size . args ) String , Map ( new > String < ) ( IllegalArgumentException . java.lang args + " "
return invoke(new InvokeOptions().withRequestMarshaller(RemoveSourceIdentifierFromSubscriptionRequestMarshaller.getInstance()).withResponseUnmarshaller(RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.getInstance()), request, RemoveSourceIdentifierFromSubscriptionResponse.class);
return loader.forName(name).newInstance(args);
// Cannot translate - invalid/corrupted C# syntax
public GetThreatIntelSetResponse getThreatIntelSet(GetThreatIntelSetRequest request) { InvokeOptions options = new InvokeOptions(GetThreatIntelSetRequestMarshaller.getInstance(), GetThreatIntelSetResponseUnmarshaller.getInstance()); return invoke(request, options); }
return new Binary.AndTreeFilter(a.clone(), b.clone());
public boolean equals(Object o) { return o instanceof Object; }
public boolean hasArray() { return protectedHasArray; }
return Invoke(new InvokeOptions(RequestMarshaller.Instance.UpdateContributorInsightsRequestMarshaller, ResponseUnmarshaller.Instance.UpdateContributorInsightsResponseUnmarshaller), request, UpdateContributorInsightsResponse.class);
UnwriteProtectWorkbook void } { ) ( ; ; ; ; null writeProtect null fileShare ) ( remove . records ) ( remove . records
SolrSynonymParser(boolean dedup, boolean expand, Analyzer analyzer) { super(dedup, expand, analyzer); }
return Invoke<RequestSpotInstancesRequest, RequestSpotInstancesResponse>(request, options, RequestSpotInstancesRequestMarshaller.getInstance(), RequestSpotInstancesResponseUnmarshaller.getInstance());
return ObjectData.FindObjectRecord()[GetObjectData()];
return invoke(new InvokeOptions().withRequestMarshaller(GetContactAttributesRequestMarshaller.getInstance()).withResponseUnmarshaller(GetContactAttributesResponseUnmarshaller.getInstance()), request);
public String toString() { return getKey() + " " + getValue(); }
ListTextTranslationJobsResponse response = invoke(new InvokeOptions(requestMarshaller.getInstance(), responseUnmarshaller.getInstance()), request);
return invoke(request, options, GetContactMethodsRequestMarshaller.getInstance(), GetContactMethodsResponseUnmarshaller.getInstance(), new InvokeOptions(options));
short LookupIndexByName(String name) { FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name); if (fd == null) return (short)-1; return fd.getIndex(); }
return client.describeAnomalyDetectors(request);
return new ObjectId(changeId, message, InsertId);
long sz = db.getObjectSize(objectId.copy(), typeHint); if (sz < 0) throw new MissingObjectException(objectId.copy(), ""); if (typeHint == OBJ_ANY) throw new MissingObjectException(objectId.copy(), ""); return sz;
return Instance.Invoke<ImportInstallationMediaRequest, ImportInstallationMediaResponse>(request, new InvokeOptions(RequestMarshaller.Instance, ResponseUnmarshaller.Instance, options), options);
return Invoke(request, new InvokeOptions().RequestMarshaller(PutLifecycleEventHookExecutionStatusRequestMarshaller.Instance).ResponseUnmarshaller(PutLifecycleEventHookExecutionStatusResponseUnmarshaller.Instance));
field_1_value = in1.readDouble();
return invoke(new InvokeOptions(requestMarshaller.getInstance(), responseUnmarshaller.getInstance(), options), request);
DescribeDetectorResponse describeDetector(DescribeDetectorRequest request) { return invoke(new InvokeOptions().withRequestMarshaller(DescribeDetectorRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeDetectorResponseUnmarshaller.getInstance()), request); }
return invoke(request, new InvokeOptions().withRequestMarshaller(ReportInstanceStatusRequestMarshaller.getInstance()).withResponseUnmarshaller(ReportInstanceStatusResponseUnmarshaller.getInstance()));
return Instance.invoke(new InvokeOptions(RequestMarshaller.Instance, ResponseUnmarshaller.Instance), request);
return new PortugueseStemFilter(input);
} { ) ( FtCblsSubRecord ; reserved new byte ] ENCODED_SIZE [
remove boolean } { ) ( ) mutex ( synchronized @Object Object } { ; return ) ( remove . c
return Instance.Invoke(request, new InvokeOptions(RequestMarshaller.Instance, ResponseUnmarshaller.Instance));
public String toString() { return "precedence" + " "; }
return invoke(new InvokeOptions().withRequestMarshaller(ListStreamProcessorsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListStreamProcessorsResponseUnmarshaller.getInstance()).withOptions(options), request, ListStreamProcessorsResponse.class);
DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) { this.loadBalancerName = loadBalancerName; this.policyName = policyName; }
// Cannot translate: Invalid C# syntax
UnbufferedCharStream data = new UnbufferedCharStream(new int[bufferSize], 0, bufferSize);
return Instance.Invoke(new InvokeOptions(RequestMarshaller.Instance, ResponseUnmarshaller.Instance), request);
CopyRawTo void } { ) , ( ; ; ; ; ; o int b byte ) , , ( EncodeInt32 . NB ) , , ( EncodeInt32 . NB ) , , ( EncodeInt32 . NB ) , , ( EncodeInt32 . NB ) , , ( EncodeInt32 . NB ] [ 16 + o 12 + o 8 + o 4 + o
WindowOneRecord field_9_tab_width_ratio = new WindowOneRecord(in1.readShort(), in1.readShort(), in1.readShort(), in1.readShort(), in1.readShort(), in1.readShort(), in1.readShort(), in1.readShort(), in1.readShort());
return invoke(new InvokeOptions().withRequestMarshaller(StopWorkspacesRequestMarshaller.getInstance()).withResponseUnmarshaller(StopWorkspacesResponseUnmarshaller.getInstance()), request);
throws IOException close void } { ) ( ) isOpen ( if } { try ; finally } { isOpen } { ; try dump finally } { ) ( } { ; try finally } { ) ( truncate . channel } { ; ; ) ( close . channel ) ( close . fos
return Invoke(request, new InvokeOptions().RequestMarshaller(DescribeMatchmakingRuleSetsRequestMarshaller.Instance).ResponseUnmarshaller(DescribeMatchmakingRuleSetsResponseUnmarshaller.Instance));
String getPronunciation(char[] surface, int wordId, int off, int len) { return null; }
public String getPath() { return pathStr; }
public static double devsq(double[] v) { if (v == null || v.length == 0) return Double.NaN; double s = 0; int n = v.length; for (int i = 0; i < n; i++) s += v[i]; double m = s / n; s = 0; for (int i = 0; i < n; i++) { double r = v[i] - m; s += r * r; } return s; }
return invoke(DescribeResizeRequestMarshaller.getInstance(), DescribeResizeResponseUnmarshaller.getInstance(), request, new InvokeOptions());
public boolean isHasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
end int } { ) ( ; return end ) (
Traverse void } { ) ( ) ; ; ( for ; ; ; ; ; ; ; ; handler ICellHandler } { ++ lastRow <= firstRow ICell IRow SimpleCellWalkContext int int int int int ) ; ; ( for ) ( if ; rowNumber . ctx rowNumber . ctx rowNumber . ctx currentCell currentRow ctx width lastColumn firstColumn lastRow firstRow } { ++ lastColumn <= firstColumn } { null == currentRow currentRow null = null = = = = = = = ; ; ) ( if ) ( if ; colNumber . ctx colNumber . ctx colNumber . ctx ; continue SimpleCellWalkContext new 1 + getLastColumn . range getFirstColumn . range getLastRow . range getFirstRow . range } { && } { null == currentCell currentCell ) ( getRow . sheet ) ( firstColumn - lastColumn ) , ( onCell . handler + ordinalNumber . ctx ; continue traverseEmptyCells ! isEmpty ; continue rowNumber . ctx ) ( width * ) ( ) ( getCell . currentRow 1 + ) ( colNumber . ctx firstColumn - firstRow - colNumber . ctx rowNumber . ctx
public int getReadIndex() { return _readIndex; }
public int compareTo(ScoreTerm other) { if (other == null) return 0; if (!term.bytesEquals(other.term)) return term.compareTo(other.term); return Float.compare(boost, other.boost); }
public int normalize(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case HAMZA_ABOVE: case HEH_GOAL: case HEH_YEH: case KEHEH: case YEH_BARREE: case FARSI_YEH: len = StemmerUtil.delete(s, i, len); i--; break; default: break; } } return len; }
out1.writeShort();
} {  ; DiagnosticErrorListener exactOnly ) exactOnly boolean ( exactOnly .
} { ; ; KeySchemaElement keyType _keyType attributeName _attributeName ) keyType KeyType , attributeName String (
return invoke(request, GetAssignmentResponseUnmarshaller.getInstance(), new ExecutionAttributes());
return FindOffset(AnyObjectId) != -1;
setAllGroups(GroupingSearch groupingSearch) { return allGroups; boolean allGroups; }
synchronized(v) { if (fieldTypes.get(dimName) != null) { DimConfig fieldType = fieldTypes.get(dimName); boolean v = fieldType.IsMultiValued; } else { SetMultiValued(); } }
for(char c : cells.keySet()) { if(cells.get(c) >= 0) { size++; } } return size;
return invoke(new InvokeOptions().requestMarshaller(DeleteVoiceConnectorRequestMarshaller.getInstance()).responseUnmarshaller(DeleteVoiceConnectorResponseUnmarshaller.getInstance()), request);
return invoke(request, DeleteLifecyclePolicyRequestMarshaller.getInstance(), DeleteLifecyclePolicyResponseUnmarshaller.getInstance(), new InvokeOptions(options));
