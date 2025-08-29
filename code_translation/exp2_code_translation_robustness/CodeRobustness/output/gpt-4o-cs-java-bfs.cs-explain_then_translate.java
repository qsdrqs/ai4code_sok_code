void serialize() { out1.writeShort(); }
AddAll void } { ) ( ) ( if ) ; ; ( for ; ) ( if src } { 0 != } { ++ srcDirIdx < srcDirIdx int } { 0 == . ; tailBlkIdx . src ; tailDirIdx . src srcDirIdx ; return size . src BlockList Util . NGit AddAll AddAll 0 = > T < ) , , ( ) , , ( tailBlkIdx . src tailBlock . src ] [ directory . src
void writeByte(byte b) { if (outerInstance.currentBlock == null) { outerInstance.currentBlock = new byte[outerInstance.blockSize]; outerInstance.blocks.add(outerInstance.currentBlock); outerInstance.upto = 0; } outerInstance.currentBlock[outerInstance.upto++] = b; if (outerInstance.upto == outerInstance.blockEnd) { outerInstance.upto = 0; outerInstance.currentBlock = null; } }
ObjectId GetObjectId() { return objectId; }
return DeleteDomainEntryResponse invoke(DeleteDomainEntryRequest request, new InvokeOptions()     .withRequestMarshaller(DeleteDomainEntryRequestMarshaller.getInstance())     .withResponseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.getInstance()));
return fst == null ? 0 : fst.GetSizeInBytes();
String GetFullMessage(byte[] raw, int msgB, Charset enc) { String buffer = RawParseUtils.TagMessage(raw, msgB); if (buffer.isEmpty()) return ""; return RawParseUtils.Decode(buffer, RawParseUtils.ParseEncoding(enc)); }
HeaderBlock headerBlock = new HeaderBlock(); PropertyTable _property_table = new PropertyTable(headerBlock); ArrayList _documents = new ArrayList(); Object _root = null;
Init void } { ) ( ; ; ; ; ; address int address offset0 upto slice ) ( Assert . Debug & address ) ( Assert . Debug < upto BYTE_BLOCK_MASK . ByteBlockPool null != slice ] [ Buffers . pool Length . slice >> address BYTE_BLOCK_SHIFT . ByteBlockPool
Api.NGit.path(path); return SubmoduleAddCommand.SetPath(path);
return invoke(new ListIngestionsRequest(), new InvokeOptions().withRequestMarshaller(ListIngestionsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListIngestionsResponseUnmarshaller.getInstance()));
; SwitchTo QueryParserTokenManager ) ( { this : ) stream ( ) lexState int , stream ICharStream (
return invoke(new GetShardIteratorRequest(), new GetShardIteratorResponseUnmarshaller(), new GetShardIteratorRequestMarshaller(), new InvokeOptions());
} { : ) ( ModifyStrategyRequest ; Method ) , , , , ( POST . MethodType " " " " " " " " " " "
synchronized (lock) { if (in == null) { throw new IOException(); } if (in.available() <= 0 || !bytes.hasRemaining()) { throw new IOException(); } return; }
EscherOptRecord getOptRecord() { return _optRecord; }
if (buffer == null) throw new NullPointerException(); if (length == 0) return; if (offset < 0 || length < 0 || offset + length > buffer.length) throw new ArrayIndexOutOfBoundsException(); int copylen = 0; for (int i = 0; i < length; ++i) { buffer[offset + i] = buffer[pos + i]; copylen++; } return copylen;
} { ; OpenNLPSentenceBreakIterator sentenceOp ) sentenceOp NLPSentenceDetectorOp ( sentenceOp .
if (str != null) { write(StringHelper.Sharpen(GetValueOf((Object) null))); }
public class NotImplementedFunctionException extends RuntimeException { public NotImplementedFunctionException(String functionName, Throwable cause) { super(functionName, cause); } }
return nextEntry().value;
void readBytes(byte[] b, int offset, int len, boolean useBuffer) { if (len <= 0) { return; } if (len > available) { throw new EndOfStreamException(); } if (useBuffer && available > 0) { int toCopy = Math.min(len, available); System.arraycopy(buffer, bufferPosition, b, offset, toCopy); bufferPosition += toCopy; available -= toCopy; len -= toCopy; offset += toCopy; } if (len > 0) { if (len > bufferSize) { throw new EndOfStreamException(); } refillBuffer(); if (len > available) { throw new EndOfStreamException(); } System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; available -= len; } }
return invoke(request, new InvokeOptions().withRequestMarshaller(TagQueueRequestMarshaller.getInstance()).withResponseUnmarshaller(TagQueueResponseUnmarshaller.getInstance()), TagQueueResponse.class);
throw new UnsupportedOperationException();
return ModifyCacheSubnetGroupResponse invoke(new ModifyCacheSubnetGroupRequest(), new InvokeOptions().setRequestMarshaller(ModifyCacheSubnetGroupRequestMarshaller.getInstance()).setResponseUnmarshaller(ModifyCacheSubnetGroupResponseUnmarshaller.getInstance()));
SetParams void } { ) ( ) ( if ) ( if ) ( if ; ; ; ; @params string ; ; ; StringTokenizer string culture ignore ) ( MoveNext . st culture ) ( MoveNext . st culture ) ( MoveNext . st st " " ) ( SetParams . Current . st + Current . st = Current . st " " StringTokenizer new ) , ( " "
return invoke(request, new InvokeOptions().withRequestMarshaller(DeleteDocumentationVersionRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteDocumentationVersionResponseUnmarshaller.getInstance()));
@Override public boolean equals(Object obj) { if (!(obj instanceof FacetLabel)) return false; FacetLabel other = (FacetLabel) obj; if (this.Components.length != other.Components.length) return false; for (int i = this.Components.length - 1; i >= 0; --i) { if (!this.Components[i].equals(other.Components[i])) return false; } return true; }
return Invoke(request, new InvokeOptions() {{ setRequestMarshaller(Instance.GetInstanceAccessDetailsRequestMarshaller); setResponseUnmarshaller(Instance.GetInstanceAccessDetailsResponseUnmarshaller); }});
return (HSSFPolygon) parentShape.createPolygon().setAnchor(new HSSFChildAnchor());
String GetSheetName(int sheetIndex) { return Sheetname.GetBoundSheetRec(sheetIndex); }
return invoke(new GetDashboardRequest(), new InvokeOptions().withRequestMarshaller(GetDashboardRequestMarshaller.getInstance()).withResponseUnmarshaller(GetDashboardResponseUnmarshaller.getInstance()));
return invoke(request, new InvokeOptions().withRequestMarshaller(AssociateSigninDelegateGroupsWithAccountRequestMarshaller.getInstance()).withResponseUnmarshaller(AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.getInstance()));
void AddMultipleBlanks() { for (int j = 0; j < NumColumns; ++j) { BlankRecord br = new BlankRecord(); br.Row = mbr.Row; br.Column = mbr.FirstColumn + j; br.XFIndex = mbr.GetXFAt(0); mbr.InsertCell(br); } }
quote string } { ) ( ; return ) ( while ; ; ; ; @string string } { 0 >= int int ) ( toString . ; ; ) ( apos ) ( append . sb sb StringBuilder . apos k 0 = " \\ " = lang . java ) ( append . 2 + k ) ( append . new " \\ " " \\ \\ \\ " ) , ( indexOf . @string ) ( StringBuilder . ) ( append . sb ) ( append . sb " \\ " lang . java ) , ( substring . ) , , ( substring . StringHelper . Sharpen 2 + k StringHelper . Sharpen
throw new java.nio.ReadOnlyBufferException();
} { ; ; ; ; ) ; ; ( for ; ; ; ; ; ArrayPtg 0 _reserved2Byte 0 _reserved1Short 0 _reserved0Int vv _arrayValues } { ++ r nRows < r int _nRows _nColumns int int ) Object ( ) ; ; ( for ; r vv Object nRows ) short ( nColumns ) short ( nRows nColumns ] [ ] [ } { ++ c nColumns < c int 0 = = ] [ = = ; c rowData Object new Length . values2d Length . 0 = = ] [ Object values2d rowData vv values2d ] [ ] [ ] [ ] [ ] [ _nRows * _nColumns GetValueIndex ) , (
return invoke(new GetIceServerConfigRequest(), new InvokeOptions().setRequestMarshaller(GetIceServerConfigRequestMarshaller.getInstance()).setResponseUnmarshaller(GetIceServerConfigResponseUnmarshaller.getInstance()));
return new StringBuilder().append(getClass().getSimpleName()).append(" ").append(getValueAsString()).append(" ").toString();
return field + " " + _parentQuery + " ";
refCount = IncrementAndGet(refCount);
return invoke(request, new InvokeOptions().withRequestMarshaller(UpdateConfigurationSetSendingEnabledRequestMarshaller.getInstance()).withResponseUnmarshaller(UpdateConfigurationSetSendingEnabledResponseUnmarshaller.getInstance()));
return INT_SIZE * LittleEndianConsts.GetXBATEntriesPerBlock();
multiplyByPowerOfTen void } { ) ( else ) ( if ; int pow10 } { } { 0 < pow10 TenPower ; ; tp mulShift mulShift = ) , ( ) , ( tp._multiplierShift tp._multiplicand tp._divisorShift tp._divisor ) ( GetInstance.TenPower ) ( Math.abs
return new StringBuilder(path).append(File.separatorChar).length() > 0 && path.charAt(path.length() - 1) != File.separatorChar ? new StringBuilder(path).append(File.separatorChar).toString() : path;
fetcher.setRoleName(fetcher);
void setProgressMonitor(ProgressMonitor pm) { progressMonitor = pm; }
Reset void } { ) ( ) ( if } { First ! ) ( if ; } { Eof ! 0 ptr ; ParseEntry ) (
throw new NoSuchElementException(); if (start >= previousIndex()) { return iterator.previous(); }
return newPrefix;
int indexOfValue(int value) { for (int i = 0; i < mSize; ++i) { if (mValues[i] == value) { return i; } } return -1; }
List<CharsRef> UniqueStems(List<CharsRef> stems) { Set<CharsRef> terms = new HashSet<>(); List<CharsRef> deduped = new ArrayList<>(); for (CharsRef s : stems) { if (!terms.contains(s)) { terms.add(s); deduped.add(s); } } return deduped; }
return invoke(request, new InvokeOptions().withRequestMarshaller(GetGatewayResponsesRequestMarshaller.getInstance()).withResponseUnmarshaller(GetGatewayResponsesResponseUnmarshaller.getInstance()));
outerInstance.blocks[(int)(position >> outerInstance.blockBits) & outerInstance.blockMask];
long s; return; ; s long ptr int s = (int) (Math.min(Available, Math.max()));
} { ; BootstrapActionDetail bootstrapActionConfig _bootstrapActionConfig ) bootstrapActionConfig BootstrapActionConfig (
void serialize() { if (field_7_padding != null) { ILittleEndianOutput out1; out1.writeShort(0); out1.writeShort(0); out1.writeShort(0); out1.writeShort(0); out1.writeShort(0); out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00); StringUtil.putCompressedUnicode(field_6_author, out1); StringUtil.putUnicodeLE(field_6_author, out1); Convert.toInt32(CultureInfo.InvariantCulture); } }
return string.lastIndexOf(@string, lastIndexOf);
boolean addLastImpl() { return @object; }
ConfigSnapshot ConfigSnapshot; string section; string subsection; while (do; ; ) UnsetSection void } { ) , ( ; CompareAndSet.state(res, src, UnsetSection); Get.state();
string GetTagName() { return tagName; }
subrecords.add(index, SubRecord);
synchronized(object) { return remove(); }
return new DoubleMetaphoneFilter(input);
long Length() { return InCoreLength; }
void SetValue(boolean newValue) { value = newValue; }

if (i <= count) return entries[i]; throw Extensions.Sharpen.CreateIndexOutOfRangeException();
} { : ) ( CreateRepoRequest ; ; Method UriPattern ) , , , , ( PUT . MethodType " " " " " " " " " " " "
boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
if (modCount != expectedModCount) { throw new ConcurrentModificationException(); } else { if (lastLink != null) { if (lastLink.previous != null) { lastLink.previous.next = lastLink.next; } if (lastLink.next != null) { lastLink.next.previous = lastLink.previous; } list._size--; expectedModCount++; lastLink = null; } else { throw new IllegalStateException(); } }
return client.invoke(new MergeShardsRequest(), new InvokeOptions().withRequestMarshaller(MergeShardsRequestMarshaller.getInstance()).withResponseUnmarshaller(MergeShardsResponseUnmarshaller.getInstance()), MergeShardsResponse.class);
return invoke(request, new InvokeOptions().withRequestMarshaller(AllocateHostedConnectionRequestMarshaller.getInstance()).withResponseUnmarshaller(AllocateHostedConnectionResponseUnmarshaller.getInstance()));
int getBeginIndex() { return start; }
List<WeightedTerm> getTerms(Query query) { return query.getTerms(); }
throw new java.nio.ReadOnlyBufferException();
void Decode(int iterations, int[] values, int valuesOffset, byte[] blocks, int blocksOffset) { for (int i = 0; i < iterations; i++) { int byte0 = blocks[blocksOffset++] & 0xFF; int byte1 = blocks[blocksOffset++] & 0xFF; int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (byte0 << 2) | (byte1 >> 6); values[valuesOffset++] = ((byte1 >> 2) & 0xFF); values[valuesOffset++] = ((byte1 & 3) << 6) | (byte2 & 0x3F); } }
if (s == null) throw new IllegalArgumentException(); String[] elements = s.split(Constants.DOT_GIT_EXT); if (elements.length == 0) throw new IllegalArgumentException(); if (elements[elements.length - 1].equals(Constants.DOT_GIT)) throw new IllegalArgumentException(); String result = elements[elements.length - 1]; if (result.equals(Constants.DOT_GIT)) throw new IllegalArgumentException(); if (result.endsWith(Constants.DOT_GIT_EXT)) result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); if (result.equals("")) throw new IllegalArgumentException(); return result;
return DescribeNotebookInstanceLifecycleConfigResponse.class.cast(AmazonWebServiceClient.invoke(new DescribeNotebookInstanceLifecycleConfigRequest(), new InvokeOptions().withRequestMarshaller(DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance())));
string GetAccessKeySecret()  {     return AccessSecret; }
return Invoke(new CreateVpnConnectionRequest(), new InvokeOptions().withRequestMarshaller(CreateVpnConnectionRequestMarshaller.getInstance()).withResponseUnmarshaller(CreateVpnConnectionResponseUnmarshaller.getInstance()));
return Invoke(new DescribeVoicesRequest(), new InvokeOptions().setRequestMarshaller(DescribeVoicesRequestMarshaller.getInstance()).setResponseUnmarshaller(DescribeVoicesResponseUnmarshaller.getInstance()));
return ListMonitoringExecutionsResponse.class.cast(invoke(new ListMonitoringExecutionsRequest(), new InvokeOptions().withRequestMarshaller(ListMonitoringExecutionsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListMonitoringExecutionsResponseUnmarshaller.getInstance())));
} { ; ; DescribeJobRequest jobId _jobId vaultName _vaultName ) jobId String , vaultName String (
EscherRecord getEscherRecord(int index) { return escherRecords[index]; }
return Invoke(new GetApisRequest(), new InvokeOptions().setRequestMarshaller(GetApisRequestMarshaller.getInstance()).setResponseUnmarshaller(GetApisResponseUnmarshaller.getInstance()));
return invoke(new DeleteSmsChannelRequest(), new InvokeOptions().withRequestMarshaller(DeleteSmsChannelRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteSmsChannelResponseUnmarshaller.getInstance()));
return trackingRefUpdate;
void print(boolean b) { System.out.println(Boolean.toString(b)); }
public IList<IQueryNode> GetChildNodes() {     return GetChildren(); }
} { ; NotIgnoredFilter workdirTreeIndex ) workdirTreeIndex int ( index .
field_1_formatFlags = in1.readShort();
} { : ) ( GetThumbnailRequest ; Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " "
return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeTransitGatewayVpcAttachmentsRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance()));
return invoke(new PutVoiceConnectorStreamingConfigurationRequest(), new InvokeOptions().withRequestMarshaller(PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance()).withResponseUnmarshaller(PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance()));
return prefixToOrdRange.containsKey(OrdRange) ? prefixToOrdRange.get(OrdRange) : null;
return string.Empty + (typeof(LexerNoViableAltException) + Utils.EscapeWhitespace(InputStream.GetText(Interval.Of(InputStream)), string) && Name.symbol >= 0 < startIndex; string.Format(CultureInfo.CurrentCulture, "symbol Size = {0}", symbol));
peekFirstImpl(); return (peek);
return Invoke(new CreateWorkspacesRequest(), new InvokeOptions().setRequestMarshaller(CreateWorkspacesRequestMarshaller.getInstance()).setResponseUnmarshaller(CreateWorkspacesResponseUnmarshaller.getInstance()));
return new NumberFormatIndexRecord() {{ field_1_formatIndex = field_1_formatIndex; }};
return invoke(new DescribeRepositoriesRequest(), new InvokeOptions().withRequestMarshaller(DescribeRepositoriesRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeRepositoriesResponseUnmarshaller.getInstance()));
new int[android.util.ArrayUtils.idealIntArraySize(initialCapacity)], new int[android.util.ArrayUtils.idealIntArraySize(initialCapacity)], int initialCapacity, int mSize, SparseIntArray mKeys, SparseIntArray mValues;
return new HyphenatedWordsFilter(input);
return Invoke(new CreateDistributionWithTagsRequest(), new InvokeOptions().withRequestMarshaller(CreateDistributionWithTagsRequestMarshaller.getInstance()).withResponseUnmarshaller(CreateDistributionWithTagsResponseUnmarshaller.getInstance()));
throw new java.io.RandomAccessFile(fileName, mode); new System.NotImplementedException();
return invoke(request, new InvokeOptions().withRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.getInstance()));
String ToHex(long value) { return Long.toHexString(value).toUpperCase(); }
return invoke(updateDistributionRequest, new InvokeOptions().withRequestMarshaller(UpdateDistributionRequestMarshaller.getInstance()).withResponseUnmarshaller(UpdateDistributionResponseUnmarshaller.getInstance()));
return palette.GetColor(index) != null ? palette.GetColor(index) : HSSFColor.AUTOMATIC;
throw new NotImplementedFunctionException();
out1.writeShort(field_1_number_crn_records); out1.writeShort(field_2_sheet_table_index);
return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());
} { ; ; FormatRun fontIndex character ) fontIndex short , character short ( _fontIndex . _character .
byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[2 * length]; int resultIndex = 0; for (int i = 0; i < length; i++) { char ch = chars[offset + i]; result[resultIndex++] = (byte) (ch >> 8); result[resultIndex++] = (byte) ch; } return result; }
return (UploadArchiveResponse) invoke(request, new InvokeOptions().withRequestMarshaller(UploadArchiveRequestMarshaller.getInstance()).withResponseUnmarshaller(UploadArchiveResponseUnmarshaller.getInstance()));
List<IToken> GetHiddenTokensToLeft(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex - 1); }
@Override public boolean equals(Object obj) { if (obj == null) return false; if (getClass() != obj.getClass()) return false; AutomatonQuery other = (AutomatonQuery) obj; if (m_term != null ? !m_term.equals(other.m_term) : other.m_term != null) return false; return m_compiled != null ? m_compiled.equals(other.m_compiled) : other.m_compiled == null; }
SpanQuery makeSpanClause(SpanQuery wsq) { List<SpanQuery> spanQueries = new ArrayList<>(); for (var entry : weightBySpanQuery.entrySet()) { if (entry.getValue().equals(wsq)) { spanQueries.add(entry.getKey().boost(entry.getValue().getBoost())); } } return spanQueries.size() == 1 ? spanQueries.get(0) : new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); }
return new StashCreateCommand();
return byName.containsKey(fieldName) ? (ret = byName.get(fieldName)) != null : false;
return Invoke(new DescribeEventSourceRequest(), new InvokeOptions().withRequestMarshaller(DescribeEventSourceRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeEventSourceResponseUnmarshaller.getInstance()));
return invoke(request, new InvokeOptions().withRequestMarshaller(GetDocumentAnalysisRequestMarshaller.getInstance()).withResponseUnmarshaller(GetDocumentAnalysisResponseUnmarshaller.getInstance()));
return invoke(request, new InvokeOptions().setRequestMarshaller(Instance.CancelUpdateStackRequestMarshaller).setResponseUnmarshaller(Instance.CancelUpdateStackResponseUnmarshaller));
return ModifyLoadBalancerAttributesResponse.class.cast(invoke(request, new InvokeOptions().withRequestMarshaller(ModifyLoadBalancerAttributesRequestMarshaller.getInstance()).withResponseUnmarshaller(ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance())));
return (SetInstanceProtectionResponse) invoke(new SetInstanceProtectionRequest(), new InvokeOptions().withRequestMarshaller(Instance.SetInstanceProtectionRequestMarshaller).withResponseUnmarshaller(Instance.SetInstanceProtectionResponseUnmarshaller));
return Invoke(new ModifyDBProxyRequest(), new InvokeOptions().setRequestMarshaller(ModifyDBProxyRequestMarshaller.getInstance()).setResponseUnmarshaller(ModifyDBProxyResponseUnmarshaller.getInstance()));
void Add(int offset, int len, int endOffset, int posLength, CharsRef output) {      if (count == outputs.length) {          outputs = Arrays.copyOf(outputs, ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF));          endOffsets = Arrays.copyOf(endOffsets, ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32));          posLengths = Arrays.copyOf(posLengths, ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32));      }      outputs[count] = new CharsRef(output);      endOffsets[count] = endOffset;      posLengths[count] = posLength;      count++;  }
} { : ) ( FetchLibrariesRequest ; Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " " "
return objects.Exists();
} { ; FilterOutputStream @out ) java ( @out . . .
} { : ) ( ScaleClusterRequest ; ; Method UriPattern ) , , , , ( HttpMethod.PUT " " " " " " " " " " " "
DVConstraint CreateTimeConstraint(int operatorType, String formula1, String formula2);
return invoke(request, new InvokeOptions().withRequestMarshaller(ListObjectParentPathsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListObjectParentPathsResponseUnmarshaller.getInstance()));
return DescribeCacheSubnetGroupsResponse.class.cast(invoke(new DescribeCacheSubnetGroupsRequest(), new InvokeOptions().withRequestMarshaller(DescribeCacheSubnetGroupsRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance())));
SetSharedFormula void } { ) ( ; flag boolean field_5_options ) , ( SetShortBoolean . sharedFormula
boolean isReuseObjects() { return reuseObjects; }
return (t = new ErrorNodeImpl(badToken)); Parent.addChild(t);
throw new IllegalArgumentException(args + " ");
return (RemoveSourceIdentifierFromSubscriptionResponse) invoke(request, new InvokeOptions().withRequestMarshaller(RemoveSourceIdentifierFromSubscriptionRequestMarshaller.getInstance()).withResponseUnmarshaller(RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.getInstance()));
ForName TokenFilterFactory } { ) , ( ; return args name string Map ) , ( NewInstance . loader > String , String <
} { : ) ( AddAlbumPhotosRequest ; Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " " "
return invoke(request, new InvokeOptions().withRequestMarshaller(GetThreatIntelSetRequestMarshaller.getInstance()).withResponseUnmarshaller(GetThreatIntelSetResponseUnmarshaller.getInstance()));
return new Binary.AndTreeFilter(Clone.a().Clone.b());
return (o instanceof Object);
boolean protectedHasArray() { return hasArray; }
return Invoke(new UpdateContributorInsightsRequest(), UpdateContributorInsightsResponse.class, new InvokeOptions().withRequestMarshaller(UpdateContributorInsightsRequestMarshaller.getInstance()).withResponseUnmarshaller(UpdateContributorInsightsResponseUnmarshaller.getInstance()));
UnwriteProtectWorkbook void } { ) ( ; ; ; ; null writeProtect null fileShare ) ( Remove . records ) ( Remove . records
} { ; SolrSynonymParser expand ) analyzer , dedup ( base : expand . ) analyzer Analyzer , expand boolean , dedup boolean (
return invoke(new RequestSpotInstancesRequest(), new InvokeOptions().withRequestMarshaller(RequestSpotInstancesRequestMarshaller.getInstance()).withResponseUnmarshaller(RequestSpotInstancesResponseUnmarshaller.getInstance()));
GetObjectData() { return ObjectData.FindObjectRecord(); }
return invoke(request, new InvokeOptions().withRequestMarshaller(GetContactAttributesRequestMarshaller.getInstance()).withResponseUnmarshaller(GetContactAttributesResponseUnmarshaller.getInstance()), GetContactAttributesResponse.class);
return GetKey() + " " + GetValue();
return invoke(new ListTextTranslationJobsRequest(), new InvokeOptions().withRequestMarshaller(ListTextTranslationJobsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListTextTranslationJobsResponseUnmarshaller.getInstance()));
return (GetContactMethodsResponse) invoke(request, new InvokeOptions().withRequestMarshaller(GetContactMethodsRequestMarshaller.getInstance()).withResponseUnmarshaller(GetContactMethodsResponseUnmarshaller.getInstance()));
short LookupIndexByName(String name) { FunctionMetadata fd = GetFunctionByNameInternal.GetInstance().fd; if (fd == null) return -1; return (short) fd.Index; }
return Invoke(new DescribeAnomalyDetectorsRequest(), new InvokeOptions().setRequestMarshaller(DescribeAnomalyDetectorsRequestMarshaller.Instance).setResponseUnmarshaller(DescribeAnomalyDetectorsResponseUnmarshaller.Instance));
return changeId;
long GetObjectSize(AnyObjectId objectId, int typeHint) { long sz = db.GetObjectSize(Copy.objectId, typeHint); if (sz < 0) { throw new MissingObjectException(Copy.objectId, ""); } if (typeHint == OBJ_ANY) { throw new MissingObjectException(objectId, ""); } return sz; }
return ImportInstallationMediaResponse.class.cast(invoke(ImportInstallationMediaRequest, new InvokeOptions().withRequestMarshaller(ImportInstallationMediaRequestMarshaller.getInstance()).withResponseUnmarshaller(ImportInstallationMediaResponseUnmarshaller.getInstance())));
return client.invoke(request, new InvokeOptions().withRequestMarshaller(PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance()).withResponseUnmarshaller(PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance()));
} { ; NumberPtg field_1_value ) in1 ILittleEndianInput ( ) ( readDouble . in1
return (GetFieldLevelEncryptionConfigResponse) invoke(request, new InvokeOptions().withRequestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.getInstance()).withResponseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance()));
return DescribeDetectorResponse describeDetector(DescribeDetectorRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(DescribeDetectorRequestMarshaller.getInstance()); options.setResponseUnmarshaller(DescribeDetectorResponseUnmarshaller.getInstance()); return invoke(request, options); }
return ReportInstanceStatusResponse.class.cast(Instance.invoke(new ReportInstanceStatusRequest(), ReportInstanceStatusRequestMarshaller.getInstance(), ReportInstanceStatusResponseUnmarshaller.getInstance()));
return DeleteAlarmResponse invoke(DeleteAlarmRequest request, new InvokeOptions().withRequestMarshaller(DeleteAlarmRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteAlarmResponseUnmarshaller.getInstance()));
return new PortugueseStemFilter(input);
byte[] reserved = new byte[ENCODED_SIZE];
synchronized (object) { return remove.c(); }
return Invoke(new GetDedicatedIpRequest(), new InvokeOptions().withRequestMarshaller(GetDedicatedIpRequestMarshaller.getInstance()).withResponseUnmarshaller(GetDedicatedIpResponseUnmarshaller.getInstance()));
public override string ToString()  {     return precedence + " "; }
return invoke(new ListStreamProcessorsRequest(), new InvokeOptions().withRequestMarshaller(ListStreamProcessorsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListStreamProcessorsResponseUnmarshaller.getInstance()));
} { ; ; DeleteLoadBalancerPolicyRequest(String policyName, String loadBalancerName) { this.policyName = policyName; this.loadBalancerName = loadBalancerName; }

int[] data = new int[bufferSize]; UnbufferedCharStream stream = new UnbufferedCharStream(bufferSize);
return invoke(new GetOperationsRequest(), new InvokeOptions().withRequestMarshaller(GetOperationsRequestMarshaller.getInstance()).withResponseUnmarshaller(GetOperationsResponseUnmarshaller.getInstance()));
CopyRawTo void } { ) , ( ; ; ; ; ; o int b byte ) , , ( EncodeInt32 . NB ) , , ( EncodeInt32 . NB ) , , ( EncodeInt32 . NB ) , , ( EncodeInt32 . NB ) , , ( EncodeInt32 . NB ] [ 16 + o 12 + o 8 + o 4 + o
field_1_h_hold = in1.readShort(); field_2_v_hold = in1.readShort(); field_3_width = in1.readShort(); field_4_height = in1.readShort(); field_5_options = in1.readShort(); field_6_active_sheet = in1.readShort(); field_7_first_visible_tab = in1.readShort(); field_8_num_selected_tabs = in1.readShort(); field_9_tab_width_ratio = in1.readShort();
return invoke(request, new InvokeOptions().withRequestMarshaller(StopWorkspacesRequestMarshaller.getInstance()).withResponseUnmarshaller(StopWorkspacesResponseUnmarshaller.getInstance()));
throws IOException close void } { ) ( ) isOpen ( if } { try ; finally } { isOpen } { ; try dump finally } { ) ( } { ; try finally } { ) ( truncate . channel } { ; ; ) ( close . channel ) ( close . fos
return DescribeMatchmakingRuleSetsResponse.class.cast(invoke(request, new InvokeOptions().withRequestMarshaller(DescribeMatchmakingRuleSetsRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeMatchmakingRuleSetsResponseUnmarshaller.getInstance())));
String GetPronunciation(int wordId, String surface, int off, int len) { return null; }
string GetPath() { return pathStr; }
double devsq(double[] v) { if (v == null || v.length < 2) return Double.NaN; double s = 0, m = 0; int n = v.length; for (int i = 0; i < n; ++i) s += v[i]; m = s / n; s = 0; for (int i = 0; i < n; ++i) s += (v[i] - m) * (v[i] - m); return s; }
return invoke(request, new InvokeOptions().withRequestMarshaller(DescribeResizeRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeResizeResponseUnmarshaller.getInstance()));
boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
end int } { ) ( ; return end ) (
void traverse(SimpleCellWalkContext ctx, ICellHandler handler, int firstRow, int lastRow, int firstColumn, int lastColumn) { for (int rowNumber = firstRow; rowNumber <= lastRow; ++rowNumber) { IRow currentRow = ctx.sheet.GetRow(rowNumber); if (currentRow == null) { if (!ctx.traverseEmptyCells) continue; } for (int colNumber = firstColumn; colNumber <= lastColumn; ++colNumber) { ICell currentCell = (currentRow == null) ? null : currentRow.GetCell(colNumber); if (currentCell == null || ctx.IsEmpty(currentCell)) { if (!ctx.traverseEmptyCells) continue; } int ordinalNumber = (rowNumber - firstRow) * (lastColumn - firstColumn + 1) + (colNumber - firstColumn); handler.OnCell(ctx, currentCell, rowNumber, colNumber, ordinalNumber); } } }
int GetReadIndex() { return _ReadIndex; }
if (this.Boost == other.Boost) { if (this.Term.CompareTo(other.Term) == 0) { return 0; } return this.Term.CompareTo(other.Term); } return Float.compare(this.Boost, other.Boost);
for (int i = 0; i < len; ++i) { switch (s[i]) { case HAMZA_ABOVE: s[i] = 0; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case KEHEH: s[i] = KAF; break; case YEH_BARREE: case FARSI_YEH: s[i] = YEH; break; default: break; } } len = StemmerUtil.Delete(s, len, i);
void writeShort(ILittleEndianOutput out1) { }
} { ; DiagnosticErrorListener exactOnly ) exactOnly boolean ( exactOnly .
) { ; ; KeySchemaElement keyType _keyType attributeName _attributeName ) keyType KeyType , attributeName String (
return Invoke(new InvokeOptions() {{ RequestMarshaller = GetAssignmentRequestMarshaller.Instance; ResponseUnmarshaller = GetAssignmentResponseUnmarshaller.Instance; }}, request);
boolean hasObject() { return AnyObjectId != FindOffset - 1; }
boolean SetAllGroups(boolean allGroups) { return allGroups; }
if (fieldTypes.containsKey(fieldType)) { DimConfig dimConfig = fieldTypes.get(fieldType); dimConfig.setIsMultiValued(v); }
return size = 0; for (char c : cells.keySet()) { if (cmd >= 0) { size++; } }
return invoke(request, new InvokeOptions().withRequestMarshaller(DeleteVoiceConnectorRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteVoiceConnectorResponseUnmarshaller.getInstance()));
return invoke(request, new InvokeOptions().withRequestMarshaller(DeleteLifecyclePolicyRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteLifecyclePolicyResponseUnmarshaller.getInstance()), DeleteLifecyclePolicyRequest.class, DeleteLifecyclePolicyResponse.class);
