out1.writeShort();
public void AddAll() { for (int srcDirIdx = 0; srcDirIdx < src.directory.size(); srcDirIdx++) { if (src.directory[srcDirIdx] == null) continue; Block tailBlock = src.directory[srcDirIdx]; int tailBlkIdx = src.tailBlkIdx; int tailDirIdx = src.tailDirIdx; if (srcDirIdx != 0) return; NGit.Util.BlockList<T> src; }
public void WriteByte(byte b) { if (outerInstance.upto == outerInstance.blockSize) { if (outerInstance.currentBlock != null) { outerInstance.blocks.Add(outerInstance.currentBlock); } outerInstance.currentBlock = new byte[outerInstance.blockSize]; outerInstance.upto = 0; } outerInstance.currentBlock[outerInstance.upto++] = b; }
public ObjectId getObjectId() { return objectId; }
return client.invoke(new InvokeRequest().withRequestMarshaller(DeleteDomainEntryRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.getInstance()), request, DeleteDomainEntryResponse.class);
return fst == null ? 0 : fst.getSizeInBytes();
public String getFullMessage() { if (buffer.length == 0) return ""; byte[] raw = buffer; int msgB = RawParseUtils.tagMessage(raw, 0); Charset enc = RawParseUtils.parseEncoding(raw); return RawParseUtils.decode(enc, raw, msgB, raw.length); }
} { ) ( POIFSFileSystem ; ; ; ; null _root _documents _property_table HeaderBlock ArrayList new PropertyTable new headerBlock ) ( ) ( = HeaderBlock new ) (
Debug.Assert(slice != null); Debug.Assert(address < upto); Debug.Assert((address & ByteBlockPool.BYTE_BLOCK_MASK) == 0); int offset0 = address; byte[] slice = pool.Buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; void Init() { }
return path; } public SubmoduleAddCommand setPath(String path) { this.path = path;
return client.invoke(new InvokeOptions().withRequestMarshaller(ListIngestionsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListIngestionsResponseUnmarshaller.getInstance()), request);
this.stream = stream; this.lexState = lexState;
GetShardIteratorResponse getShardIterator(GetShardIteratorRequest request) { return invoke(request, GetShardIteratorRequestMarshaller.getInstance(), GetShardIteratorResponseUnmarshaller.getInstance(), new InvokeOptions()); }
@PostMapping public ResponseEntity<String> modifyStrategy(@RequestBody ModifyStrategyRequest request) { return ResponseEntity.ok(""); }
synchronized(lock) { try { if(@in == null) { return; } throw new IOException(); return; } catch(IOException e) { if(bytes.hasRemaining() || @in.available() > 0) { throw new IOException(); } return; } }
public EscherOptRecord getOptRecord() { return _optRecord; }
public int read(byte[] buffer, int offset, int length) { if (buffer == null) throw new java.util.Arrays.ArgumentNullException(); if (offset < 0 || length < 0) throw new java.util.Arrays.ArgumentException(); if (buffer.length - offset < length) throw new java.util.Arrays.ArgumentException(); if (length == 0) return 0; int copylen = Math.min(count - pos, length); if (copylen <= 0) return -1; System.arraycopy(this.buffer, pos, buffer, offset, copylen); pos += copylen; return copylen; }
} { ; OpenNLPSentenceBreakIterator sentenceOp ) sentenceOp NLPSentenceDetectorOp ( sentenceOp .
System.out.print(str != null ? Sharpen.StringHelper.GetValueOf(str) : null);
} { ; NotImplementedFunctionException functionName ) cause , functionName ( super : functionName . ) cause NotImplementedException , functionName String (
return nextEntry().value;
void ReadBytes(byte[] b, int offset, int len, boolean useBuffer) { if (len < 0) throw new IllegalArgumentException(); if (len == 0) return; long after = bufferStart + bufferPosition + len; if (useBuffer && len < bufferLength && after <= Length) { if (bufferPosition + len > bufferSize) Refill(); System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { if (bufferPosition > 0) { int available = bufferLength - bufferPosition; if (available > 0) { if (available >= len) { System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; return; } else { System.arraycopy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition = bufferLength; } } } ReadInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } }
TagQueueResponse response = client.tagQueue(TagQueueRequest.builder().build());
Remove void } { ) ( ; throw new NotSupportedException ( )
return client.modifyCacheSubnetGroup(request);
public void setParams(String params) { if (params != null) { StringTokenizer st = new StringTokenizer(params, " "); if (st.hasMoreTokens()) st.nextToken(); if (st.hasMoreTokens()) st.nextToken(); if (st.hasMoreTokens()) st.nextToken(); } }
return invoke(new InvokeOptions(DeleteDocumentationVersionRequestMarshaller.getInstance(), DeleteDocumentationVersionResponseUnmarshaller.getInstance()), request);
public boolean equals(Object obj) { if (!(obj instanceof FacetLabel)) return false; FacetLabel other = (FacetLabel) obj; if (other.length != length) return false; for (int i = length - 1; i >= 0; i--) { if (!components[i].equals(other.components[i])) return false; } return true; }
return invoke(new InvokeOptions(RequestMarshaller.getInstance().GetInstanceAccessDetailsRequestMarshaller, ResponseUnmarshaller.getInstance().GetInstanceAccessDetailsResponseUnmarshaller, options), request);
public HSSFPolygon CreatePolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(null, anchor); shape.Parent = this; shape.Anchor = anchor; shapes.Add(shape); OnCreate(shape); return shape; }
public String getSheetName(int sheetIndex) { return GetBoundSheetRec(sheetIndex).getSheetname(); }
return invoke(new InvokeOptions(RequestMarshaller.GetDashboardRequestMarshaller.Instance, ResponseUnmarshaller.GetDashboardResponseUnmarshaller.Instance), request);
return Invoke(new InvokeOptions(AssociateSigninDelegateGroupsWithAccountRequestMarshaller.Instance, AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.Instance), request);
for(int j = 0; j < mbr.NumColumns; j++) { BlankRecord br = new BlankRecord(); br.Column = mbr.FirstColumn + j; br.Row = mbr.Row; br.XFIndex = mbr.GetXFAt(j); mbr.InsertCell(br); } AddMultipleBlanks();
while (true) { int k = string.indexOf("\\"); if (k < 0) { sb.append(string); return sb.toString(); } sb.append(string.substring(0, k)); sb.append("\\\\"); string = string.substring(k + 2); }
java.nio.ByteBuffer.putInt(int value); throw new java.nio.ReadOnlyBufferException();
for(int r = 0; r < nRows; r++) { for(int c = 0; c < nColumns; c++) { Object[] rowData = new Object[values2d.length]; if(values2d.length == 0) rowData[c] = values2d[r][c]; } } Object[][] vv = _arrayValues; ArrayPtg arrayPtg = new ArrayPtg((short)nRows, (short)nColumns, vv); int _nRows = nRows; int _nColumns = nColumns; int _reserved0Int = 0; short _reserved1Short = 0; byte _reserved2Byte = 0; GetValueIndex(r * _nColumns + c);
return client.invoke(new InvokeRequest().withRequestMarshaller(GetIceServerConfigRequestMarshaller.getInstance()).withResponseUnmarshaller(GetIceServerConfigResponseUnmarshaller.getInstance()).withOptions(options), request);
public String toString() { StringBuilder sb = new StringBuilder(); sb.append(getClass().getName()).append(" ").append(getValueAsString()).append(" "); return sb.toString(); }
public String toString() { return field + " " + _parentQuery + " "; }
refCount.incrementAndGet();
UpdateConfigurationSetSendingEnabledResponse response = client.updateConfigurationSetSendingEnabled(request);
return LittleEndianConsts.INT_SIZE * GetXBATEntriesPerBlock();
void multiplyByPowerOfTen() { int pow10; if () else (); TenPower tp; if (pow10 < 0) { tp = TenPower.GetInstance(Math.abs()); mulShift = mulShift(tp._multiplicand, tp._multiplierShift, tp._divisor, tp._divisorShift); } }
StringBuilder builder = new StringBuilder(); for(int i = 0; i < length; i++) { if(GetComponent() == Path.DirectorySeparatorChar) { builder.append(Path.DirectorySeparatorChar); } builder.append(GetComponent()); } return builder.toString();
fetcher.setRoleName();
void setProgressMonitor(ProgressMonitor pm) { this.progressMonitor = pm; }
if(!First()){if(!Eof()){ptr=0;ParseEntry();}}Reset();
if (previousIndex() < 0) throw new java.util.NoSuchElementException(); return iterator.previous();
public String getNewPrefix() { return newPrefix; }
public int indexOfValue(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1; }
#pragma warning disable 612, 618 CharArraySet dictionary = new CharArraySet(8, true); IList<CharsRef> stems = new List<CharsRef>(); foreach (CharsRef word in terms) { if (word.length < 2) continue; stems.Add(Stem(word)); } if (stems.Count == 0) return new CharArraySet(0, true); stems = stems.Where(s => s != null).ToList(); IList<CharsRef> deduped = new List<CharsRef>(); foreach (CharsRef s in stems) { if (!deduped.Contains(s)) deduped.Add(s); } return new CharArraySet(deduped, true); #pragma warning restore 612, 618
return client.invoke(new InvokeRequest().withRequestMarshaller(GetGatewayResponsesRequestMarshaller.getInstance()).withResponseUnmarshaller(GetGatewayResponsesResponseUnmarshaller.getInstance()), request, GetGatewayResponsesResponse.class);
void setPosition(long position) { currentBlockIndex = (int) (position >> outerInstance.blockBits); currentBlock = outerInstance.blocks[currentBlockIndex]; currentBlockUpto = (int) (position & outerInstance.blockMask); }
return Math.max(0, Math.min(available, (int)s)); long n; int ptr = s;
} { ; BootstrapActionDetail bootstrapActionConfig _bootstrapActionConfig ) bootstrapActionConfig BootstrapActionConfig (
void Serialize(ILittleEndianOutput out1) { if (field_7_padding != null) { } else { out1.writeByte(field_5_hasMultibyte ? 0x01 : 0x00); out1.writeShort(Convert.toInt32(CultureInfo.getInvariantCulture())); StringUtil.putUnicodeLE(StringUtil.putCompressedUnicode(out1.writeByte(field_6_author.length()))); out1.writeShort(); out1.writeShort(); out1.writeShort(); out1.writeShort(); out1.writeShort(); } if (field_5_hasMultibyte) { } }
string.lastIndexOf(string, lastIndexOf);
add boolean } { ) ( ; return object E addLastImpl ) (
UnsetSection void } { ) , ( ; ) ( while do ; ; subsection String section String ! } { ConfigSnapshot ConfigSnapshot ; ; ) , ( compareAndSet . state res src UnsetSection ) , , ( ) ( get . state
public String getTagName() { return tagName; }
subrecords.add(index, new SubRecord());
synchronized(object) { return c.remove(); }
return new DoubleMetaphoneFilter(input);
return InCoreLength();
void setValue(boolean newValue) { value = newValue; }
} { ; ; Pair newSource oldSource ) newSource ContentSource , oldSource ContentSource ( newSource . oldSource .
if (i < 0 || i >= count) throw new IndexOutOfBoundsException(); return entries[i];
} { : ) ( CreateRepoRequest ; ; Method UriPattern ) , , , , ( PUT . MethodType " " " " " " " " " " " "
public boolean isDeltaBaseAsOffset() { return deltaBaseAsOffset; }
public void remove() { if (lastLink == null) throw new IllegalStateException(); if (list.modCount != expectedModCount) throw new ConcurrentModificationException(); Link<ET> previous_1 = lastLink.previous; Link<ET> next_1 = lastLink.next; if (previous_1 != null) previous_1.next = next_1; else list.link = next_1; if (next_1 != null) next_1.previous = previous_1; else list.lastLink = previous_1; list._size--; list.modCount++; expectedModCount++; lastLink = null; pos--; }
return invoke(new InvokeOptions(RequestMarshaller.Instance.MergeShardsRequestMarshaller, options, ResponseUnmarshaller.Instance.MergeShardsResponseUnmarshaller, options), request);
return client.invoke(new InvokeOptions().withRequestMarshaller(AllocateHostedConnectionRequestMarshaller.getInstance()).withResponseUnmarshaller(AllocateHostedConnectionResponseUnmarshaller.getInstance()), request);
public int getBeginIndex() { return start; }
return query.GetTerms();
throw new java.nio.ReadOnlyBufferException(); ByteBuffer.compact();
public void Decode(int[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; i++) { int byte0 = (int) blocks[blocksOffset++] & 0xFF; int byte1 = (int) blocks[blocksOffset++] & 0xFF; int byte2 = (int) blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = byte0 & 63; values[valuesOffset++] = ((byte0 >> 6) & 3) | ((byte1 & 15) << 2); values[valuesOffset++] = ((byte1 >> 4) & 15) | ((byte2 & 3) << 4); values[valuesOffset++] = (byte2 >> 2) & 63; } }
public String getHumanishName() { String result; if (s == null) throw new ArgumentException(); String[] elements = s.split(Sharpen.Runtime.separatorChar == '\\' ? "\\\\" : String.valueOf(Sharpen.Runtime.separatorChar)); if (elements.length == 0) throw new ArgumentException(); result = elements[elements.length - 1]; if (result.equals(Constants.DOT_GIT)) result = elements[elements.length - 2]; if (result.endsWith(Constants.DOT_GIT_EXT)) result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); if (LOCAL_FILE.matcher(result).matches() || result.equals("") || result.equals(".")) throw new ArgumentException(); return result; }
DescribeNotebookInstanceLifecycleConfigResponse describeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = DescribeNotebookInstanceLifecycleConfigRequestMarshaller.Instance; options.ResponseUnmarshaller = DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.Instance; return Invoke<DescribeNotebookInstanceLifecycleConfigResponse>(request, options); }
public String getAccessKeySecret() { return AccessSecret; }
return invoke(new InvokeOptions(CreateVpnConnectionRequestMarshaller.getInstance(), CreateVpnConnectionResponseUnmarshaller.getInstance()), request);
return client.invoke(new InvokeRequest().withRequestMarshaller(DescribeVoicesRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeVoicesResponseUnmarshaller.getInstance()), request, options);
return invoke(new InvokeOptions(ListMonitoringExecutionsRequestMarshaller.getInstance(), ListMonitoringExecutionsResponseUnmarshaller.getInstance(), options), request);
public DescribeJobRequest(String jobId, String vaultName) { this._jobId = jobId; this._vaultName = vaultName; }
public EscherRecord getEscherRecord(int index) { return escherRecords[index]; }
return invoke(new InvokeOptions(RequestMarshaller.GetApisRequestMarshaller.Instance, ResponseUnmarshaller.GetApisResponseUnmarshaller.Instance, options), request);
return invoke(new InvokeOptions(DeleteSmsChannelRequestMarshaller.getInstance(), DeleteSmsChannelResponseUnmarshaller.getInstance(), options), request);
TrackingRefUpdate getTrackingRefUpdate() { return trackingRefUpdate; }
System.out.println(b.toString());
GetChild IQueryNode } { ) ( ; return ] [ GetChildren ) (
} { ; NotIgnoredFilter workdirTreeIndex ) workdirTreeIndex int ( index .
} { ; AreaRecord field_1_formatFlags ) in1 RecordInputStream ( ) ( readShort . in1
} { : ) ( GetThumbnailRequest ; Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " "
DescribeTransitGatewayVpcAttachmentsResponse response = invoke(new InvokeOptions(DescribeTransitGatewayVpcAttachmentsRequestMarshaller.getInstance(), DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance()), request);
return Invoke(new InvokeOptions(PutVoiceConnectorStreamingConfigurationRequestMarshaller.Instance, PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.Instance), request);
return prefixToOrdRange.get(dim);
return String.format(CultureInfo.getCurrentCulture(), "LexerNoViableAltException('%s')", startIndex >= 0 && startIndex < input.size() ? Utils.escapeWhitespace(input.getText(Interval.of(startIndex, startIndex)), false) : "");
peek E } { ) ( ; return peekFirstImpl ) (
return client.invoke(new InvokeRequest().withRequestMarshaller(CreateWorkspacesRequestMarshaller.getInstance()).withResponseUnmarshaller(CreateWorkspacesResponseUnmarshaller.getInstance()).withOptions(options), request);
NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = field_1_formatIndex; return rec;
return client.invoke(new InvokeRequest().withRequestMarshaller(DescribeRepositoriesRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeRepositoriesResponseUnmarshaller.getInstance()), request, options);
android.util.internal.ArrayUtils.idealIntArraySize(new int[initialCapacity], new int[initialCapacity], initialCapacity, mKeys, mValues, mSize, SparseIntArray);
return new HyphenatedWordsFilter(input);
return invoke(new InvokeOptions().withRequestMarshaller(CreateDistributionWithTagsRequestMarshaller.getInstance()).withResponseUnmarshaller(CreateDistributionWithTagsResponseUnmarshaller.getInstance()), request);
throw new java.io.FileNotFoundException();
return invoke(new InvokeOptions().withRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.getInstance()), request);
public static String toHex(long value) { return Long.toHexString(value); }
return invoke(new InvokeOptions(UpdateDistributionRequestMarshaller.getInstance(), UpdateDistributionResponseUnmarshaller.getInstance()), request);
public Color getColor(short index) { if (index == HSSFColor.Automatic.Index) return HSSFColor.Automatic.getInstance(); byte b = palette.getColor(index); if (b != null) return new CustomColor(b); else return null; }
throw new NotImplementedFunctionException(); public ValueEval evaluate(ValueEval[] operands, int srcRow, int srcCol) {
out1.writeShort((short)field_1_number_crn_records); out1.writeShort((short)field_2_sheet_table_index);
return describeDBEngineVersions(new DescribeDBEngineVersionsRequest()).getDescribeDBEngineVersionsResponse();
} { ; ; FormatRun fontIndex character ) fontIndex short , character short ( _fontIndex . _character .
public static byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int resultIndex = 0; for (int i = 0; i < length; i++) { char ch = chars[offset + i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
return invoke(new InvokeOptions(UploadArchiveRequestMarshaller.getInstance(), UploadArchiveResponseUnmarshaller.getInstance()), request);
public List<IToken> GetHiddenTokensToLeft(int tokenIndex) { return GetHiddenTokensToLeft(tokenIndex, -1); }
public boolean equals(Object obj) { if (obj == null) return false; if (!(obj instanceof AutomatonQuery)) return false; if (obj == this) return false; AutomatonQuery other = (AutomatonQuery) obj; if (!getClass().equals(obj.getClass())) return false; if (m_term == null) { if (other.m_term != null) return false; } else if (!m_term.equals(other.m_term)) return false; if (!m_compiled.equals(other.m_compiled)) return false; return true; }
List<SpanQuery> spanQueries = new ArrayList<>(); for (Map.Entry<SpanQuery, Float> wsq : weightBySpanQuery.entrySet()) { spanQueries.add(new BoostQuery(wsq.getKey(), wsq.getValue())); } if (spanQueries.size() == 1) { return spanQueries.get(0); } else { return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0])); }
return new StashCreateCommand();
FieldInfo fieldInfo; return byName.tryGetValue(fieldName, out fieldInfo);
return client.invoke(new InvokeRequest().withRequestMarshaller(DescribeEventSourceRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeEventSourceResponseUnmarshaller.getInstance()), request, DescribeEventSourceResponse.class);
return getInstance().invoke(new InvokeOptions(GetDocumentAnalysisRequestMarshaller.getInstance().options, GetDocumentAnalysisResponseUnmarshaller.getInstance().options), request, GetDocumentAnalysisResponse.class);
return invoke(new InvokeOptions(CancelUpdateStackRequestMarshaller.getInstance(), CancelUpdateStackResponseUnmarshaller.getInstance()), request);
return client.invoke(new InvokeRequest().withRequestMarshaller(ModifyLoadBalancerAttributesRequestMarshaller.getInstance()).withResponseUnmarshaller(ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance()).withRequest(request), ModifyLoadBalancerAttributesResponse.class);
return invoke(new InvokeOptions(SetInstanceProtectionRequestMarshaller.getInstance(), SetInstanceProtectionResponseUnmarshaller.getInstance()), request);
return invoke(new InvokeOptions().withRequestMarshaller(ModifyDBProxyRequestMarshaller.getInstance()).withResponseUnmarshaller(ModifyDBProxyResponseUnmarshaller.getInstance()), request);
void Add(int offset, int len, int endOffset, int posLength) { if (count == outputs.length) { CharsRef[] newOutputs = new CharsRef[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF)]; int[] newEndOffsets = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; int[] newPosLengths = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(outputs, 0, newOutputs, 0, count); System.arraycopy(endOffsets, 0, newEndOffsets, 0, count); System.arraycopy(posLengths, 0, newPosLengths, 0, count); outputs = newOutputs; endOffsets = newEndOffsets; posLengths = newPosLengths; } outputs[count] = new CharsRef(); CharsRef.copyChars(output, offset, len, outputs[count]); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
} { : ) ( FetchLibrariesRequest ; Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " "
return objects.Exists();
} { ; FilterOutputStream out ) java ( out . . .
} { : ) ( ScaleClusterRequest ; ; Method UriPattern ) , , , , ( PUT . MethodType " " " " " " " " " " " "
return DVConstraint.CreateTimeConstraint(operatorType, String formula1, String formula2);
ListObjectParentPathsResponse response = client.listObjectParentPaths(ListObjectParentPathsRequest.builder().build());
return invoke(new InvokeOptions().withRequestMarshaller(DescribeCacheSubnetGroupsRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance()), request);
sharedFormula.SetShortBoolean(field_5_options, flag); void SetSharedFormula() { }
public boolean isReuseObjects() { return reuseObjects; }
public ErrorNode addErrorNode(Token badToken) { ErrorNodeImpl t = new ErrorNodeImpl(badToken); t.parent = this; addChild(t); return t; }
} { ) ( if LatvianStemFilterFactory } { 0 > ) args ( super : ; throw size() . args ) String , Map ( new < String > ) ( IllegalArgumentException args + " "
return client.invoke(new InvokeRequest().withRequestMarshaller(RemoveSourceIdentifierFromSubscriptionRequestMarshaller.getInstance()).withResponseUnmarshaller(RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.getInstance()).withOptions(options), request);
return loader.newInstance(name, args);
} { : ) ( AddAlbumPhotosRequest ; Protocol ) , , , , ( HTTPS . ProtocolType " " " " " " " " " "
return invoke(new InvokeOptions(GetThreatIntelSetRequestMarshaller.getInstance(), GetThreatIntelSetResponseUnmarshaller.getInstance(), options), request);
return new AndTreeFilter(a.clone(), b.clone());
public boolean equals(Object o) { return this == o; }
boolean hasArray; return protectedHasArray();
return UpdateContributorInsightsResponse.invoke(UpdateContributorInsightsRequestMarshaller.getInstance(), UpdateContributorInsightsResponseUnmarshaller.getInstance(), request, new InvokeOptions(options));
void UnwriteProtectWorkbook() { records.Remove(fileShare, null); records.Remove(writeProtect, null); }
} { ; SolrSynonymParser expand ) analyzer , dedup ( base : expand . ) analyzer Analyzer , expand boolean , dedup boolean (
RequestSpotInstancesResponse response = client.requestSpotInstances(RequestSpotInstancesRequest.builder().build());
getObjectData() { return ObjectData.findObjectRecord(); }
return client.invoke(new InvokeRequest().withRequestMarshaller(GetContactAttributesRequestMarshaller.getInstance()).withResponseUnmarshaller(GetContactAttributesResponseUnmarshaller.getInstance()).withOptions(options), request, GetContactAttributesResponse.class);
public String toString() { return getKey() + " " + getValue(); }
return invoke(new InvokeOptions().withRequestMarshaller(ListTextTranslationJobsRequestMarshaller.getInstance()).withResponseUnmarshaller(ListTextTranslationJobsResponseUnmarshaller.getInstance()), request, ListTextTranslationJobsResponse.class);
return invoke(new InvokeOptions(RequestMarshaller.GetContactMethodsRequestMarshaller.Instance, ResponseUnmarshaller.GetContactMethodsResponseUnmarshaller.Instance), request);
public short LookupIndexByName(String name) { FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name); if (fd == null) return -1; return fd.Index; }
DescribeAnomalyDetectorsResponse describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { return invoke(request, DescribeAnomalyDetectorsRequestMarshaller.getInstance(), DescribeAnomalyDetectorsResponseUnmarshaller.getInstance(), new InvokeOptions()); }
return new ObjectId(changeId, message, insertId);
long sz = db.getObjectSize(objectId.copy(), OBJ_ANY); if (sz < 0) throw new MissingObjectException(objectId.copy(), ""); if (typeHint != OBJ_ANY && sz == 0) throw new MissingObjectException(objectId, typeHint); return sz;
return invoke(new InvokeOptions().withRequestMarshaller(ImportInstallationMediaRequestMarshaller.getInstance()).withResponseUnmarshaller(ImportInstallationMediaResponseUnmarshaller.getInstance()), request);
return Invoke(new InvokeOptions(PutLifecycleEventHookExecutionStatusRequestMarshaller.Instance, PutLifecycleEventHookExecutionStatusResponseUnmarshaller.Instance), request);
} { ; NumberPtg field_1_value ) in1 LittleEndianInput ( ) ( readDouble . in1
return invoke(new InvokeOptions().withRequestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.getInstance()).withResponseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance()), request);
return client.invoke(new InvokeRequest().withRequestMarshaller(DescribeDetectorRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeDetectorResponseUnmarshaller.getInstance()), request, options);
return invoke(new InvokeOptions(ReportInstanceStatusRequestMarshaller.getInstance(), ReportInstanceStatusResponseUnmarshaller.getInstance(), options), request);
return invoke(new InvokeOptions().withRequestMarshaller(DeleteAlarmRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteAlarmResponseUnmarshaller.getInstance()), request);
return new PortugueseStemFilter(input);
} { ) ( FtCblsSubRecord ; reserved new byte [ ENCODED_SIZE ]
synchronized(object) { return c.remove(); }
return invoke(new InvokeOptions(GetDedicatedIpRequestMarshaller.getInstance(), GetDedicatedIpResponseUnmarshaller.getInstance()), request);
public String toString() { return precedence + " "; }
return invoke(new InvokeOptions(RequestMarshaller.ListStreamProcessorsRequestMarshaller.Instance, ResponseUnmarshaller.ListStreamProcessorsResponseUnmarshaller.Instance, options), request);
} { ; ; DeleteLoadBalancerPolicyRequest policyName _policyName loadBalancerName _loadBalancerName ) policyName String , loadBalancerName String (
} { ; WindowProtectRecord options _options ) options int (
} { ; ; UnbufferedCharStream data 0 n ) bufferSize int ( new int ] bufferSize [
return invoke(new InvokeOptions(RequestMarshaller.getInstance(), ResponseUnmarshaller.getInstance()), request);
NB.EncodeInt32(o, b, o + 4); NB.EncodeInt32(o, b, o + 8); NB.EncodeInt32(o, b, o + 12); NB.EncodeInt32(o, b, o + 16); CopyRawTo();
WindowOneRecord field_9_tab_width_ratio; field_8_num_selected_tabs; field_7_first_visible_tab; field_6_active_sheet; field_5_options; field_4_height; field_3_width; field_2_v_hold; field_1_h_hold; RecordInputStream in1; field_1_h_hold = in1.readShort(); field_2_v_hold = in1.readShort(); field_3_width = in1.readShort(); field_4_height = in1.readShort(); field_5_options = in1.readShort(); field_6_active_sheet = in1.readShort(); field_7_first_visible_tab = in1.readShort(); field_8_num_selected_tabs = in1.readShort(); field_9_tab_width_ratio = in1.readShort();
return client.invoke(new InvokeRequest().withRequestMarshaller(StopWorkspacesRequestMarshaller.getInstance()).withResponseUnmarshaller(StopWorkspacesResponseUnmarshaller.getInstance()), request);
try { if (isOpen()) { dump(); } } finally { try { channel.truncate(); } finally { try { fos.close(); channel.close(); } finally { } } }
DescribeMatchmakingRuleSetsResponse describeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = DescribeMatchmakingRuleSetsRequestMarshaller.Instance; options.ResponseUnmarshaller = DescribeMatchmakingRuleSetsResponseUnmarshaller.Instance; return Invoke<DescribeMatchmakingRuleSetsResponse>(request, options); }
public String getPronunciation(int wordId, char[] surface, int off, int len) { return null; }
public String getPath() { return pathStr; }
public static double devsq(double[] v) { if (v == null || v.length <= 1) return Double.NaN; double s = 0; for (int i = 0; i < v.length; i++) s += v[i]; double m = s / v.length; s = 0; for (int i = 0; i < v.length; i++) s += (v[i] - m) * (v[i] - m); return s; }
return DescribeResizeResponse.invoke(DescribeResizeRequestMarshaller.getInstance().marshall(request), new InvokeOptions(DescribeResizeResponseUnmarshaller.getInstance()));
boolean hasPassedThroughNonGreedyDecision; return passedThroughNonGreedyDecision;
end int } { ) ( ; return end ) (
for (int rowNumber = firstRow; rowNumber <= lastRow; rowNumber++) { IRow currentRow = sheet.GetRow(rowNumber); if (currentRow == null) { currentRow = null; } for (int colNumber = firstColumn; colNumber <= lastColumn; colNumber++) { ICell currentCell = currentRow.GetCell(colNumber); if (currentCell == null) { currentCell = null; } if (!traverseEmptyCells && currentCell.IsEmpty()) { continue; } SimpleCellWalkContext ctx = new SimpleCellWalkContext(range.FirstRow(), range.FirstColumn(), range.LastRow(), range.LastColumn() + 1); ctx.rowNumber = rowNumber - firstRow; ctx.colNumber = colNumber - firstColumn; ctx.ordinalNumber = ctx.rowNumber * width + ctx.colNumber; handler.OnCell(currentCell, ctx); } }
public int getReadIndex() { return _readIndex; }
public int compareTo(ScoreTerm other) { if (other == null) return 1; if (!Term.equals(other.Term)) return Term.compareTo(other.Term); return Double.compare(Boost, other.Boost); }
public static int normalize(char[] s, int len) { for (int i = 0; i < len; i++) { switch (s[i]) { case HAMZA_ABOVE: s[i] = 0; break; case HEH_GOAL: case HEH_YEH: s[i] = HEH; break; case KEHEH: s[i] = KAF; break; case YEH_BARREE: case FARSI_YEH: s[i] = YEH; break; default: break; } } len = StemmerUtil.delete(s, len, 0); return len; }
out1.writeShort();
} { ; DiagnosticErrorListener exactOnly ) exactOnly boolean ( exactOnly .
} { ; ; KeySchemaElement keyType _keyType attributeName _attributeName ) keyType KeyType , attributeName String (
return invoke(new InvokeOptions(RequestMarshaller.GetAssignmentRequestMarshaller.Instance, ResponseUnmarshaller.GetAssignmentResponseUnmarshaller.Instance), options, request);
return FindOffset(id) != AnyObjectId - 1;
public void setAllGroups(boolean allGroups) { this.allGroups = allGroups; }
synchronized void setMultiValued(String dimName, boolean v) { DimConfig fieldType; if (fieldTypes.containsKey(dimName)) { fieldType = fieldTypes.get(dimName); } else { fieldType = new DimConfig(); fieldTypes.put(dimName, fieldType); } fieldType.setMultiValued(v); }
public int getCellsVal() { int size = 0; for (char c : cmd.toCharArray()) { if (cells.containsKey(c)) { size++; } } return size; }
return invoke(new InvokeOptions().withRequestMarshaller(DeleteVoiceConnectorRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteVoiceConnectorResponseUnmarshaller.getInstance()), request);
DeleteLifecyclePolicyResponse deleteLifecyclePolicy(DeleteLifecyclePolicyRequest request) { InvokeOptions options = new InvokeOptions(); options.RequestMarshaller = DeleteLifecyclePolicyRequestMarshaller.Instance; options.ResponseUnmarshaller = DeleteLifecyclePolicyResponseUnmarshaller.Instance; return Invoke<DeleteLifecyclePolicyResponse>(request, options); }
