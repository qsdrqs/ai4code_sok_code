} ; ) ( WriteShort . out1 { ) out1 ILittleEndianOutput ( serialize void
if (size == 0) { return; } int srcDirIdx = 0; for (; srcDirIdx < tailDirIdx; ++srcDirIdx) { directory.addAll(src[srcDirIdx]); } if (tailBlkIdx != 0) { tailBlock.addAll(src[tailBlkIdx]); }
void writeByte(byte b) { if (blockSize == upto) { if (currentBlock != null) { blocks.add(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; } currentBlock[upto++] = b; }
return getObjectId();
public DeleteDomainEntryResult deleteDomainEntry(DeleteDomainEntryRequest request) {request = beforeClientExecution(request);return executeDeleteDomainEntry(request);}
return (fst == null) ? 0 : fst.ramBytesUsed();
return ParseEncoding.RawParseUtils.decode(Encoding.RawParseUtils.raw(msgB, 0 < msgB.length ? msgB : TagMessage.RawParseUtils.emptyString), buffer);
POIFSFileSystem fs = new POIFSFileSystem(); HeaderBlock headerBlock = new HeaderBlock(fs); PropertyTable _property_table = new PropertyTable(headerBlock); ArrayList _documents = new ArrayList(); Object _root = null;
} ; ) length . slice < upto ( assert . debug ; address offset0 ; BYTE_BLOCK_MASK . ByteBlockPool & address upto ; ) null != slice ( assert . debug ; ] BYTE_BLOCK_SHIFT . ByteBlockPool >> address [ buffers . pool slice { ) address int ( init void
return; path.setPath(path);
return (ListIngestionsResponse) invoke(new ListIngestionsRequest(), new InvokeOptions() {{ setRequestMarshaller(Instance.ListIngestionsRequestMarshaller); setResponseUnmarshaller(Instance.ListIngestionsResponseUnmarshaller); }});
public QueryParserTokenManager(ICharStream stream, int lexState) {this.SwitchTo(lexState);this.stream = stream;}
return executeGetShardIterator(beforeClientExecution(request));
public MethodType getMethod() { return MethodType.POST; }
if (in == null) throw new IOException("System.IO.IOException"); synchronized (lock) { boolean ready = (in.available() > 0 || bytes.hasRemaining()); return ready; }
return getOptRecord(EscherOptRecord.class);
public int read(byte[] buffer, int offset, int length) {if (buffer == null) {throw new NullPointerException("buffer");}java.util.Arrays.checkOffsetAndCount(buffer.length, offset, length);if (length == 0) {return 0;}synchronized (this) {int copylen = Math.min(length, pos - count);for (int i = 0; i < copylen; ++i) {buffer[offset + i] = (byte) (unchecked(buffer[pos + i]));}pos += copylen;return copylen;}}
} sentenceOp { sentenceOp.NLPSentenceDetectorOp(new OpenNLPSentenceBreakIterator());
})); null) Object((getValueOf.StringHelper.sharpen: str != null ? str : (write) { str; String (System.out.println(void
} functionName functionName { cause, functionName(base: cause, new NotImplementedException(functionName)); NotImplementedFunctionException string functionName; }
return nextEntry.getValue();
public void readBytes(byte[] b, int offset, int len, boolean useBuffer) {int available; if (len > 0) {if (useBuffer && len > bufferSize) {refill();} if (len < bufferLength) {System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len;} else {if (len > after) {throw new EndOfStreamException(" ");} else {System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len;}}} else {if (available > 0) {System.arraycopy(buffer, bufferPosition, b, offset, available); bufferPosition += available;} else {if (len > 0) {System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len;} else {throw new EndOfStreamException(" ");}}}}
TagQueueResponse response = (TagQueueResponse) invoke(request, new InvokeOptions().setRequestMarshaller(TagQueueRequestMarshaller.getInstance()).setResponseUnmarshaller(TagQueueResponseUnmarshaller.getInstance()));
throw new UnsupportedOperationException();
return executeModifyCacheSubnetGroup(beforeClientExecution(request));
StringTokenizer st = new StringTokenizer(culture, " "); while (st.hasMoreTokens()) { String current = st.nextToken(); if (!current.equals(" ")) { setParams(current); } }
return (DeleteDocumentationVersionResponse) invoke(request, new InvokeOptions().withRequestMarshaller(DeleteDocumentationVersionRequestMarshaller.getInstance()).withResponseUnmarshaller(DeleteDocumentationVersionResponseUnmarshaller.getInstance()));
return obj instanceof FacetLabel && Length == ((FacetLabel) obj).Length && java.util.Arrays.equals(Components, ((FacetLabel) obj).Components);
return executeGetInstanceAccessDetails(request);
HSSFPolygon shape = new HSSFPolygon(); HSSFChildAnchor anchor = (HSSFChildAnchor) shape.CreatePolygon(); Parent.AddShape(shape); shape.OnCreate(); return shape;
return getBoundSheetRec(sheetIndex).getSheetName();
public GetDashboardResult getDashboard(GetDashboardRequest request) {request = beforeClientExecution(request);return executeGetDashboard(request);}
return executeAssociateSigninDelegateGroupsWithAccount(beforeClientExecution(request));
void addMultipleBlanks() { for (int j = 0; j < numColumns; j++) { BlankRecord br = new BlankRecord(); br.setXFIndex(getXFAt().getMbr()); br.setRow(row.getMbr()); br.setColumn(row.getMbr() + j); insertCell(br); } }
StringBuilder sb = new StringBuilder(); int k = 0; while ((k = string.indexOf("\\", k)) >= 0) { sb.append(string.substring(0, k)).append("\\\\"); string = string.substring(k + 1); } sb.append(string); return sb.toString();
throw new java.nio.ReadOnlyBufferException();
Object[][] values2d = new Object[_nRows][_nColumns]; for (int r = 0; r < nRows; r++) { for (int c = 0; c < nColumns; c++) { values2d[r][c] = rowData[r][c]; } }
return (GetIceServerConfigResponse) Invoke(new GetIceServerConfigRequest(), new InvokeOptions() {{ setRequestMarshaller(GetIceServerConfigRequestMarshaller.getInstance()); setResponseUnmarshaller(GetIceServerConfigResponseUnmarshaller.getInstance()); }});
StringBuilder sb = new StringBuilder(); sb.append(getClass().getName()).append(" ").append(getValueAsString()).append(" "); return sb.toString();
return " " + _parentQuery + " " + field.toString();
} ; ) ( incrementAndGet . refCount { ) ( incRef void
public UpdateConfigurationSetSendingEnabledResult updateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request) {request = beforeClientExecution(request);return executeUpdateConfigurationSetSendingEnabled(request);}
return (int) (INT_SIZE * LittleEndianConsts.getXBATEntriesPerBlock() + getNextXBATChainOffset());
void multiplyByPowerOfTen(int pow10) { TenPower tp = Math.Abs(TenPower.getInstance().tenPower(pow10)); if (pow10 > 0) { mulShift(_multiplicand.tp, _multiplierShift.tp); } else { mulShift(_divisor.tp, _divisorShift.tp); } }
StringBuilder builder = new StringBuilder(); int length = builder.length(); for (int i = 0; i < length; i++) { builder.append(Path.DirectorySeparatorChar); } return builder.toString();
void withFetcher(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; }
} ; pm progressMonitor { ) pm ProgressMonitor ( setProgressMonitor void
if (!eof) { parseEntry(); } else { if (!first) { ptr = 0; reset(); } }
throw new java.util.NoSuchElementException(); return iterator.previous(); if (iterator.previousIndex() >= start) { E previous; }
return newPrefix.getNewPrefix();
int indexOfValue(int value) {for (int i = 0; i < mSize; ++i) {if (mValues[i] == value) {return i;}} return -1;}
public IList<CharsRef> uniqueStems(char[] word, int length) {CharArraySet terms = new CharArraySet(8, true);List<CharsRef> stems = new ArrayList<>();if (stems.size() < 2) {return stems;}for (CharsRef s : stems) {if (!terms.contains(s)) {terms.add(s);}}return stems;}
return executeGetGatewayResponses(request);
} ; ) blockMask . outerInstance & position ( ) int ( currentBlockUpto ; ] [ blocks . outerInstance currentBlock ; ) blockBits . outerInstance >> position ( ) int ( currentBlockIndex { ) position long ( setPosition void
} ; s return ; s ptr ; ) ) , ( Math.max , ) ( Math.min ( Available ) int ( = s int { ) n long ( Skip long
} bootstrapActionConfig _bootstrapActionConfig { } bootstrapActionConfig bootstrapActionConfig(BootstrapActionDetail bootstrapActionDetail);
} } ; ) ) Locale.ROOT , ( Integer.parseInt ( out1.writeByte { ) field_7_padding != null ( if } ; ) , ( StringUtil.putCompressedUnicode { else } ; ) , ( StringUtil.putUnicodeLE { ) field_5_hasMultibyte ( if ; ) 0x00 : 0x01 ? field_5_hasMultibyte ( out1.writeByte ; ) field_6_author.length ( out1.writeShort ; ) ( out1.writeShort ; ) ( out1.writeShort ; ) ( out1.writeShort ; ) ( out1.writeShort { ) out1 ILittleEndianOutput ( void serialize
return string.lastIndexOf(@string);
} ; ) ( addLastImpl return { ) @object E ( add boolean
}));, (compareAndSet.state!(while});), , (unsetSection res;)(get.state src {do; ConfigSnapshot; ConfigSnapshot {) subsection String, section String (unsetSection void
return getTagName();
public void addSubRecord(SubRecord element, int index) { this.subrecords.add(index, element); }
return (boolean) remove(object -> { synchronized (mutex) { return remove(object); } });
return new DoubleMetaphoneFilter(input);
return (long) InCoreLength();
public void setValue(boolean newValue) { this.value = newValue; }
new Pair<ContentSource, ContentSource>(newSource, oldSource);
throw new IndexOutOfBoundsException(); return entries[i]; if (i <= count) { int i; }
} ; PUT . MethodType Method ; " " UriPattern { ) " " , " " , " " , " " , " " ( : ) ( CreateRepoRequest
public boolean isDeltaBaseAsOffset() {return deltaBaseAsOffset;}
public void remove() {if (modCount == expectedModCount) {if (lastLink != null) {LinkedList.Link<ET> previous = lastLink.previous;LinkedList.Link<ET> next = lastLink.next;if (previous != null) {previous.next = next;} else {list.first = next;}if (next != null) {next.previous = previous;} else {list.last = previous;}lastLink = null;--list._size;++list.modCount;++expectedModCount;} else {throw new IllegalStateException();}} else {throw new ConcurrentModificationException();}}
return executeMergeShards(request);
return allocateHostedConnection(allocateHostedConnectionRequest, new InvokeOptions().withRequestMarshaller(AllocateHostedConnectionRequestMarshaller.getInstance()).withResponseUnmarshaller(AllocateHostedConnectionResponseUnmarshaller.getInstance()));
return getBeginIndex();
return getTerms(query);
throw new java.nio.ReadOnlyBufferException();
void Decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; i++) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (int) ((byte0 >> 2) & 3); int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (int) (((byte0 & 3) << 4) | ((byte1 >> 4) & 15)); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (int) (((byte1 & 15) << 2) | ((byte2 >> 6) & 3)); values[valuesOffset++] = (int) (byte2 & 63); } }
if (s == null || s.equals("")) throw new IllegalArgumentException(); String[] elements = s.split(File.separator); if (elements.length == 0) throw new IllegalArgumentException(); String result = elements[elements.length - 1]; if (result.equals(Constants.DOT_GIT)) result = elements[elements.length - 2]; else if (result.endsWith(Constants.DOT_GIT_EXT)) result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); return result;
return invoke(new InvokeOptions().withRequestMarshaller(DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance()).withResponseUnmarshaller(DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance()), request, DescribeNotebookInstanceLifecycleConfigResponse.class);
return getAccessKeySecret();
return executeCreateVpnConnection(beforeClientExecution(request));
public DescribeVoicesResult describeVoices(DescribeVoicesRequest request) {request = beforeClientExecution(request);return executeDescribeVoices(request);}
return executeListMonitoringExecutions(beforeClientExecution(request));
public DescribeJobRequest(String jobId, String vaultName) {this._jobId = jobId; this._vaultName = vaultName;}
public EscherRecord getEscherRecord(int index) { return escherRecords[index]; }
GetApisResponse response = (GetApisResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.GetApisRequestMarshaller); setResponseUnmarshaller(Instance.GetApisResponseUnmarshaller); }}, request);
return deleteSmsChannel(deleteSmsChannelRequest);
return getTrackingRefUpdate();
} ; ) ) ( toString . b ( print { ) b boolean ( print void
return (IQueryNode) getChild(getChildren());
NotIgnoredFilter workdirTreeIndex = (int) workdirTreeIndex.index();
AreaRecord field_1_formatFlags = in1.readShort();
public GetThumbnailRequest() {super("HTTPS", "ProtocolType", "Protocol", "GetThumbnailRequest");}
return executeDescribeTransitGatewayVpcAttachments(beforeClientExecution(request));
return (PutVoiceConnectorStreamingConfigurationResponse) invoke(new InvokeOptions() {{ request = new PutVoiceConnectorStreamingConfigurationRequest(); RequestMarshaller = PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance(); ResponseUnmarshaller = PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance(); }});
OrdRange getOrdRange(String dim) { return prefixToOrdRange.tryGetValue(dim, out result) ? result : null; }
} ; ) , Name . ) LexerNoViableAltException . Runtime . Antlr4 ( typeof , " " , CurrentCulture . CultureInfo ( Format . string return } ; ) , ( EscapeWhitespace . Utils symbol ; ) ) , ( Of . Interval ( GetText . ) InputStream ) ICharStream ( ( symbol { ) Size . ) InputStream ) ICharStream ( ( < startIndex && 0 >= startIndex ( if ; Empty . string = symbol string { ) ( ToString string
return peekFirstImpl().peek();
return (CreateWorkspacesResponse) invoke(request, new InvokeOptions(CreateWorkspacesRequestMarshaller.getInstance(), CreateWorkspacesResponseUnmarshaller.getInstance()));
NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec = (NumberFormatIndexRecord) rec.clone(); return field_1_formatIndex;
return describeRepositories(request);
SparseIntArray sparseIntArray = new SparseIntArray(initialCapacity); int[] mKeys = new int[initialCapacity]; int[] mValues = new int[initialCapacity]; int mSize = 0;
return new HyphenatedWordsFilter(input);
return executeCreateDistributionWithTags(beforeClientExecution(request));
throw new java.io.IOException(new RandomAccessFile(fileName, mode));
public DeleteWorkspaceImageResult deleteWorkspaceImage(DeleteWorkspaceImageRequest request) {request = beforeClientExecution(request);return executeDeleteWorkspaceImage(request);}
return Long.toHexString((int) value);
UpdateDistributionResponse updateDistribution(UpdateDistributionRequest request) { InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(UpdateDistributionRequestMarshaller.getInstance()); options.setResponseUnmarshaller(UpdateDistributionResponseUnmarshaller.getInstance()); return invoke(request, options); }
return (index == HSSFColor.HSSFColorPredefined.AUTOMATIC.getIndex()) ? HSSFColor.HSSFColorPredefined.AUTOMATIC.getInstance() : (b != null ? palette.getColor(b) : null);
throw new NotImplementedFunctionException(); Evaluate(ValueEval[] operands, int srcRow, int srcCol);
} ); field_2_sheet_table_index = (short) (WriteShort.out1); field_1_number_crn_records = (short) (WriteShort.out1); out1 = (ILittleEndianOutput) Serialize();
public DescribeDBEngineVersionsResponse describeDBEngineVersions(DescribeDBEngineVersionsRequest request) {return describeDBEngineVersions(request);}
FormatRun(short fontIndex, short character) { this.fontIndex = fontIndex; this.character = character; }
public byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) {byte[] result = new byte[length * 2];int resultIndex = 0;for (int i = offset; i < offset + length; i++) {char ch = chars[i];result[resultIndex++] = (byte) (ch >> 8);result[resultIndex++] = (byte) ch;}return result;}
return executeUploadArchive(beforeClientExecution(request));
return (IList<IToken>) getHiddenTokensToLeft(tokenIndex, -1);
@Override public boolean equals(Object obj) {if (obj == this) return true;if (!(obj instanceof AutomatonQuery)) return false;AutomatonQuery other = (AutomatonQuery) obj;if (!compiled.equals(other.compiled)) return false;if (term == null) return other.term == null;if (!term.equals(other.term)) return false;return true;}
List<SpanQuery> spanQueries = new ArrayList<>(); for (var wsq : weightBySpanQuery.entrySet()) { SpanQuery spanQuery = MakeSpanClause(wsq.getKey().getBoost(), wsq.getValue()); spanQueries.add(spanQuery); } if (spanQueries.size() == 1) return spanQueries.get(0); else return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0]));
return new StashCreateCommand(StashCreate());
FieldInfo fieldInfo; if (byName.TryGetValue(fieldName, out fieldInfo)) return fieldInfo;
public DescribeEventSourceResponse describeEventSource(DescribeEventSourceRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(DescribeEventSourceRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeEventSourceResponseUnmarshaller.getInstance());return invoke(request, options);}
public GetDocumentAnalysisResult getDocumentAnalysis(GetDocumentAnalysisRequest request) {request = beforeClientExecution(request);return executeGetDocumentAnalysis(request);}
return (CancelUpdateStackResponse) invoke(request, new InvokeOptions().withRequestMarshaller(CancelUpdateStackRequestMarshaller.getInstance()).withResponseUnmarshaller(CancelUpdateStackResponseUnmarshaller.getInstance()));
return executeModifyLoadBalancerAttributes(beforeClientExecution(request));
public SetInstanceProtectionResult setInstanceProtection(SetInstanceProtectionRequest request) {request = beforeClientExecution(request);return executeSetInstanceProtection(request);}
return executeModifyDBProxy(request);
void add(char[] output, int offset, int len, int endOffset, int posLength) { if (outputs.length == count) { CharsRef[] newOutputs = new CharsRef[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_OBJECT_REF)]; System.arraycopy(outputs, 0, newOutputs, 0, count); outputs = newOutputs; } if (endOffsets.length == count) { int[] newEndOffsets = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(endOffsets, 0, newEndOffsets, 0, count); endOffsets = newEndOffsets; } if (posLengths.length == count) { int[] newPosLengths = new int[ArrayUtil.oversize(count + 1, RamUsageEstimator.NUM_BYTES_INT32)]; System.arraycopy(posLengths, 0, newPosLengths, 0, count); posLengths = newPosLengths; } outputs[count] = new CharsRef(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
} HTTPS.ProtocolType protocol() { return fetchLibrariesRequest(" ", " ", " ", " ", " "); }
return exists && objects.exists();
} ; @out @out . { ) . . java ( FilterOutputStream
} PUT MethodType method; " " String uriPattern; " ", " ", " ", " ", " " (ScaleClusterRequest request);
return new DVConstraint(DataValidationConstraint.createTimeConstraint(operatorType, formula1, formula2));
return executeListObjectParentPaths(request);
public DescribeCacheSubnetGroupsResult describeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) {request = beforeClientExecution(request);return executeDescribeCacheSubnetGroups(request);}
} ; ) , ( SetShortBoolean.sharedFormula field_5_options { ) flag boolean ( setSharedFormula void
boolean isReuseObjects() { return reuseObjects; }
return t.addChild(new ErrorNodeImpl((IToken) badToken));
if (args.length > 0) { throw new IllegalArgumentException(); } super(args); }
return removeSourceIdentifierFromSubscription(request);
return TokenFilterFactory.forName(name, (IDictionary<String, String>) args).newInstance(loader);
} HTTPS.ProtocolType protocol { } " ", " ", " ", " ", " " ( ) ( AddAlbumPhotosRequest
return (GetThreatIntelSetResponse) invoke(new GetThreatIntelSetRequest(), new InvokeOptions() {{ setRequestMarshaller(GetThreatIntelSetRequestMarshaller.getInstance()); setResponseUnmarshaller(GetThreatIntelSetResponseUnmarshaller.getInstance()); }});
return new AndTreeFilter(Clone.a(), Clone.b());
boolean equals(Object o) { return o != null; }
protected boolean hasArray() { return hasArray; }
return (UpdateContributorInsightsResponse) invoke(request, new InvokeOptions().withRequestMarshaller(UpdateContributorInsightsRequestMarshaller.getInstance()).withResponseUnmarshaller(UpdateContributorInsightsResponseUnmarshaller.getInstance()));
} null writeProtect; null fileShare; Remove.records(); Remove.records(); UnwriteProtectWorkbook();
} expand expand . { ) analyzer, dedup ( base : ) analyzer Analyzer, expand boolean, dedup boolean ( SolrSynonymParser
return executeRequestSpotInstances(request);
return findObjectRecord().getObjectData()[byte];
return (GetContactAttributesResponse) Invoke(new InvokeOptions() { RequestMarshaller = GetContactAttributesRequestMarshaller.Instance, ResponseUnmarshaller = GetContactAttributesResponseUnmarshaller.Instance }, request);
return getKey() + " " + getValue().toString();
return executeListTextTranslationJobs(request);
public GetContactMethodsResult getContactMethods(GetContactMethodsRequest request) {request = beforeClientExecution(request);return executeGetContactMethods(request);}
return (short) (fd == null ? -1 : getFunctionByNameInternal().getInstance().getFunctionMetadata().lookupIndexByName(name));
public DescribeAnomalyDetectorsResult describeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(DescribeAnomalyDetectorsRequestMarshaller.getInstance());options.setResponseUnmarshaller(DescribeAnomalyDetectorsResponseUnmarshaller.getInstance());return invoke(request, options);}
return new InsertId(changeId, message, ObjectId);
if (typeHint == OBJ_ANY) { if (sz < 0) throw new MissingObjectException(Copy.objectId(), ""); return sz; } throw new MissingObjectException(Copy.objectId(), ""); long sz = GetObjectSize(db, objectId, typeHint);
return executeImportInstallationMedia(beforeClientExecution(request));
PutLifecycleEventHookExecutionStatusResponse response = (PutLifecycleEventHookExecutionStatusResponse) invoke(new InvokeOptions() {{ request = new PutLifecycleEventHookExecutionStatusRequest(); responseUnmarshaller = PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getInstance(); requestMarshaller = PutLifecycleEventHookExecutionStatusRequestMarshaller.getInstance(); }});
} ; ) ( readDouble . in1 field_1_value { ) in1 ILittleEndianInput ( NumberPtg
return executeGetFieldLevelEncryptionConfig(beforeClientExecution(request));
public DescribeDetectorResult describeDetector(DescribeDetectorRequest request) {request = beforeClientExecution(request);return executeDescribeDetector(request);}
public ReportInstanceStatusResponse reportInstanceStatus(ReportInstanceStatusRequest request) {InvokeOptions options = new InvokeOptions();options.setRequestMarshaller(ReportInstanceStatusRequestMarshaller.getInstance());options.setResponseUnmarshaller(ReportInstanceStatusResponseUnmarshaller.getInstance());return invoke(request, options);}
return deleteAlarm(deleteAlarmRequest);
return new PortugueseStemFilter(input);
} ; ] ENCODED_SIZE [ byte new reserved { ) ( FtCblsSubRecord
synchronized (mutex) { return object.remove(object); }
return (GetDedicatedIpResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(GetDedicatedIpRequestMarshaller.getInstance()); setResponseUnmarshaller(GetDedicatedIpResponseUnmarshaller.getInstance()); }}, request, GetDedicatedIpResponse.class);
return ") " + precedence + " (";
return listStreamProcessors(listStreamProcessorsRequest);
public DeleteLoadBalancerPolicyRequest(String policyName, String loadBalancerName) { this.policyName = policyName; this.loadBalancerName = loadBalancerName; }
} options _options; WindowProtectRecord options = new WindowProtectRecord(int options);
public UnbufferedCharStream(int bufferSize) { this.data = new char[bufferSize]; this.n = 0; }
return executeGetOperations(beforeClientExecution(request));
} ; ) , 16 + o , ( EncodeInt32.NB ; ) , 12 + o , ( EncodeInt32.NB ; ) , 8 + o , ( EncodeInt32.NB ; ) , 4 + o , ( EncodeInt32.NB ; ) , , ( EncodeInt32.NB { ) o int , b ] [ byte ( copyRawTo void
} ; ) ( ReadShort . in1 field_9_tab_width_ratio ; ) ( ReadShort . in1 field_8_num_selected_tabs ; ) ( ReadShort . in1 field_7_first_visible_tab ; ) ( ReadShort . in1 field_6_active_sheet ; ) ( ReadShort . in1 field_5_options ; ) ( ReadShort . in1 field_4_height ; ) ( ReadShort . in1 field_3_width ; ) ( ReadShort . in1 field_2_v_hold ; ) ( ReadShort . in1 field_1_h_hold {  ) in1 RecordInputStream ( WindowOneRecord
return (StopWorkspacesResponse) invoke(request, new InvokeOptions() {{ requestMarshaller = Instance.StopWorkspacesRequestMarshaller.options; responseUnmarshaller = Instance.StopWorkspacesResponseUnmarshaller.options; }});
} } } } } ; ) ( close . fos { finally } ; ) ( close . channel { try { finally } ; ) ( truncate . channel { try { finally } ; ) ( dump { try ; isOpen { ) isOpen ( if { throws IOException ) ( close void
return executeDescribeMatchmakingRuleSets(beforeClientExecution(request));
return null; } int len, int off, char[] surface, int wordId, String GetPronunciation() {
return getPathString();
double devsq(double[] v) { double r = Double.NaN; if (v != null && v.length >= 1) { double m = 0; double s = 0; int n = v.length; for (int i = 0; i < n; ++i) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; ++i) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; } return r; }
return executeDescribeResize(beforeClientExecution(request));
boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
return end();
void traverse(ICellHandler handler) {int firstRow = ctx.getFirstRow();int lastRow = ctx.getLastRow();int firstColumn = ctx.getFirstColumn();int lastColumn = ctx.getLastColumn();int width = lastColumn - firstColumn + 1;SimpleCellWalkContext ctx = new SimpleCellWalkContext();IRow currentRow = null;ICell currentCell = null;for (int rowNumber = firstRow; rowNumber <= lastRow; rowNumber++) {currentRow = sheet.getRow(rowNumber);if (currentRow == null) {continue;}for (int colNumber = firstColumn; colNumber <= lastColumn; colNumber++) {currentCell = currentRow.getCell(colNumber);if (currentCell == null) {continue;}if (!ctx.isEmpty() && ctx.isEmpty(currentCell)) {continue;}handler.onCell(ctx, currentCell, rowNumber - firstRow, colNumber - firstColumn, width * (rowNumber - firstRow) + (colNumber - firstColumn) + 1);}}}
return getReadIndex();
if (Boost == other.Boost) { return Term.BytesEquals(other.Term) ? 0 : Term.CompareTo(other.Term); } else { return Boost > other.Boost ? 1 : -1; }
int len = s.length; for (int i = 0; i < len; i++) { switch (s[i]) { case HAMZA_ABOVE: break; case HEH: s[i] = HEH_GOAL; break; case HEH_YEH: s[i] = HEH_GOAL; break; case KAF: s[i] = KEHEH; break; case YEH: s[i] = YEH_BARREE; break; case FARSI_YEH: s[i] = YEH_BARREE; break; default: break; } }
void serialize(ILittleEndianOutput out1) { out1.writeShort(); }
} exactOnly.exactOnly().diagnosticErrorListener(boolean exactOnly);
KeySchemaElement(String attributeName, KeyType keyType) { this._attributeName = attributeName; this._keyType = keyType; }
public GetAssignmentResult getAssignment(GetAssignmentRequest request) {request = beforeClientExecution(request);return executeGetAssignment(request);}
return findOffset(anyObjectId) != 1;
return; allGroups.setAllGroups(allGroups, GroupingSearch.bool());
} } } ; v isMultiValued . fieldType { else } ; } v isMultiValued { DimConfig new ] [ fieldTypes { ) ) fieldType DimConfig out , ( tryGetValue . fieldTypes ! ( if { ) ( lock { ) v boolean , dimName String ( setMultiValued void
int size = 0; for (char c : cells.Keys) { Cell e = At(cmd.e); if (e >= 0) { size++; } } return size;
return deleteVoiceConnector(deleteVoiceConnectorRequest);
return (DeleteLifecyclePolicyResponse) invoke(new InvokeOptions().setRequestMarshaller(DeleteLifecyclePolicyRequestMarshaller.getInstance()).setResponseUnmarshaller(DeleteLifecyclePolicyResponseUnmarshaller.getInstance()), request);
