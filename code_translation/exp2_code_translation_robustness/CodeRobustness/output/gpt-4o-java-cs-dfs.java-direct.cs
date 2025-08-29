} ; ) field_1_vcenter ( WriteShort . Out { ) Out LittleEndianOutput ( void
if (tailBlkIdx.src != 0) { tailBlock.src.AddRange(tailBlkIdx.src); } if (size.src == 0) { return; } for (int srcDirIdx = 0; srcDirIdx < tailDirIdx.src; srcDirIdx++) { directory.src.AddRange(srcDirIdx); }
if (currentBlock != null) { if (upto == blockSize) { addBlock(currentBlock); currentBlock = new byte[blockSize]; upto = 0; } b = currentBlock[upto++]; }
return new ObjectId();
DeleteDomainEntryResult result = ExecuteDeleteDomainEntry(BeforeClientExecution(new DeleteDomainEntryRequest()));
} ; ) 0 : ) ( ramBytesUsed . termsDictOffsets ? ) null != termsDictOffsets ( ( + ) 0 : ) ( ramBytesUsed . termOffsets ? ) null != termOffsets ( ( return { ) (
public static readonly string GuessEncoding(byte[] raw, int msgB) { if (msgB < 0) return ""; return RawParseUtils.TagMessage(raw, 0, raw.Length); }
{ POIFSFileSystem fs = new POIFSFileSystem(); fs._header.setBATCount(1); fs._header.setBATArray(new int[1]); BATBlock bb = BATBlock.createEmptyBATBlock(bigBlockSize, false); bb.setOurBlockIndex(1); fs._bat_blocks.add(bb); bb.setNextBlock(0); bb.setNextBlock(POIFSConstants.END_OF_CHAIN, 1); fs._property_table.setStartBlock(POIFSConstants.FAT_SECTOR_BLOCK); }
} ; length . Slice < upto Debug.Assert ; address = offset0 ; BYTE_BLOCK_MASK . ByteBlockPool & address = upto ; null != slice Debug.Assert ; ] BYTE_BLOCK_SHIFT . ByteBlockPool >> address [ buffers . pool = slice { ) address (  void
} this return; path = path.this { } path String (SubmoduleAddCommand);
ListIngestionsResult result = ExecuteListIngestions(BeforeClientExecution(new ListIngestionsRequest()));
} ; ) lexState ( SwitchTo ; ) stream ( { ) lexState , stream CharStream ( QueryParserTokenManager
GetShardIteratorResult result = ExecuteGetShardIterator(BeforeClientExecution(new GetShardIteratorRequest()));
base.MethodType = HttpMethod.Post("aegis", "ModifyStrategy", "2016-11-11", "vipaegis", new ModifyStrategyRequest());
} } ; false return { ) e ( catch } ; 0 > ) ( available . in || ) ( hasRemaining . bytes return { try } ; ) "InputStreamReader is closed" ( IOException new throw { ) null == in ( if { ) lock ( synchronized { IOException throws ) ( bool
} return new EscherOptRecord();
public synchronized void MethodName(char[] buffer, int offset, int length) { if (buffer == null) throw new NullReferenceException("buffer == null"); Arrays.CheckOffsetAndCount(buffer.Length, offset, length); if (length == 0) return; int copylen = length < (pos - count) ? length : (pos - count); for (int i = 0; i < copylen; i++) { this.buffer[pos + i] = buffer[offset + i]; } pos += copylen; return; }
} sentenceOp = this.sentenceOp ?? new NLPSentenceDetectorOp(OpenNLPSentenceBreakIterator);
} ; ) ) null ) object ( ( valueOf . string : str ? null != str ( write { ) str string (  void
} functionName = this.functionName; } cause; functionName(base(cause), functionName: new NotImplementedFunctionException(cause));
} ; ) ( getValue . ) ( nextEntry . base return { ) (  V
public void Read(byte[] b, int offset, int len, bool useBuffer) throws IOException { if (len <= 0) return; int available = bufferLength - bufferPosition; if (len <= available) { Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { if (useBuffer && len < bufferSize) { if (available > 0) { Array.Copy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (len > 0) { Refill(); if (bufferLength < len) throw new EOFException("read past EOF: " + this); Array.Copy(buffer, 0, b, offset, len); bufferPosition = len; } } else { if (len > after) throw new EOFException("read past EOF: " + this); after = bufferStart + bufferPosition + len; ReadInternal(b, offset, len); bufferPosition = 0; bufferLength = 0; } } }
TagQueueResult request = beforeClientExecution(request); return executeTagQueue(request);
throw new UnsupportedOperationException();
request = beforeClientExecution(new ModifyCacheSubnetGroupRequest()); return executeModifyCacheSubnetGroup(request);
void setParams(string language = "", string country = "", string variant = "") { StringTokenizer st = new StringTokenizer(params, ","); if (st.hasMoreTokens()) language = st.nextToken(); if (st.hasMoreTokens()) country = st.nextToken(); if (st.hasMoreTokens()) variant = st.nextToken(); }
DeleteDocumentationVersionResult result = ExecuteDeleteDocumentationVersion(BeforeClientExecution(new DeleteDocumentationVersionRequest()));
if (!(obj is FacetLabel other)) { return false; } if (length != other.length) { return false; } for (int i = length - 1; i >= 0; --i) { if (!components[i].Equals(other.components[i])) { return false; } } return true;
GetInstanceAccessDetailsResult result = beforeClientExecution(request = new GetInstanceAccessDetailsRequest()); result = request.executeGetInstanceAccessDetails();
HSSFPolygon shape = new HSSFPolygon(this, anchor) { shape.SetParent(this); shape.SetAnchor(anchor); shapes.Add(shape); return shape; };
return getBoundSheetRec(sheetIndex).getSheetname();
return ExecuteGetDashboard(BeforeClientExecution(request) as GetDashboardRequest) as GetDashboardResult;
AssociateSigninDelegateGroupsWithAccountResult result = ExecuteAssociateSigninDelegateGroupsWithAccount(BeforeClientExecution(new AssociateSigninDelegateGroupsWithAccountRequest()));
for (int j = 0; j < mbr.getNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.setColumn(mbr.getFirstColumn() + j); br.setRow(mbr.getRow()); br.setXFIndex(mbr.getXFAt(j)); insertCell(br); }
public static string ToString(string str) { StringBuilder sb = new StringBuilder(); string apos = ""; int k = 0; while ((k = str.IndexOf("\\E", apos.Length)) >= 0) { sb.Append(str.Substring(apos.Length, k - apos.Length)).Append("\\\\E\\Q"); apos = str.Substring(k + 2); } return sb.Append(apos).ToString(); }
throw new ReadOnlyBufferException();
} _reserved2Byte = 0; _reserved1Short = 0; _reserved0Int = 0; vv = _arrayValues; } } rowData = values2d[r][c]; vv[getValueIndex(r, c)] = rowData; for (int c = 0; c < nColumns; c++) { for (int r = 0; r < nRows; r++) { Object[,] values2d = new Object[_nRows * _nColumns]; _nRows = nRows; _nColumns = nColumns; nRows = values2d.GetLength(0); nColumns = values2d.GetLength(1); } Object[,] values2d = new Object[nRows, nColumns]; { ArrayPtg
GetIceServerConfigResult request = beforeClientExecution(new GetIceServerConfigRequest()); request.executeGetIceServerConfig();
return (string)(getClass().getName() + " [" + getValueAsString() + "]");
return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")";
public final void refCount().incrementAndGet();
UpdateConfigurationSetSendingEnabledResult result = ExecuteUpdateConfigurationSetSendingEnabled(BeforeClientExecution(new UpdateConfigurationSetSendingEnabledRequest()));
} LittleEndianConsts.INT_SIZE * (getXBATEntriesPerBlock()) { return;
void TenPower = Math.Abs(pow10) < 0 ? mulShift(_multiplicand.tp, _multiplierShift.tp, pow10) : mulShift(_divisor.tp, _divisorShift.tp, pow10);
new StringBuilder().Append(Path.DirectorySeparatorChar).Append(components[i].ToString()).Append(Path.DirectorySeparatorChar);
this.fetcher = new ECSMetadataServiceCredentialsFetcher(); this.fetcher.setRoleName(roleName);
} ProgressMonitor pm = progressMonitor;
} } ; ) ( parseEntry ) ) ( eof ! ( if ; 0 = ptr { ) ) ( first ! ( if { ) ( void
throw new NoSuchElementException(); return iterator.previous(); if (iterator.previousIndex() >= start) { } E
} newPrefix.this return { } (string);
for (int i = 0; i < mSize; i++) if (mValues[i] == value) return;
var deduped = new List<CharsRef>(); foreach (CharsRef s in stems) { if (!terms.Contains(s)) { deduped.Add(s); } } return deduped; var terms = new CharArraySet(8, dictionary.ignoreCase); if (stems.Count < 2) { return stems; }
return ExecuteGetGatewayResponses(BeforeClientExecution(new GetGatewayResponsesRequest()));
} ; ) blockMask & pos ( ) ( = currentBlockUpto ; ] currentBlockIndex [ blocks = currentBlock ; ) blockBits >> pos ( ) ( = currentBlockIndex { ) pos (  void
} ; s return ; s += ptr ; ) ) n , 0 ( Math.Max , ) ( available ( Math.Min ) ( = s { ) n (
} ); bootstrapActionConfig.SetBootstrapActionConfig(new BootstrapActionConfig(bootstrapActionConfig.BootstrapActionDetail));
} } ; ) ) ( intValue . field_7_padding ( writeByte . out { ) null != field_7_padding ( if } ; ) out , field_6_author ( StringUtil.PutCompressedUnicode { else } ; ) out , field_6_author ( StringUtil.PutUnicodeLE { ) field_5_hasMultibyte ( if ; ) 0x00 : 0x01 ? field_5_hasMultibyte ( writeByte . out ; ) ) ( length . field_6_author ( writeShort . out ; ) field_4_shapeid ( writeShort . out ; ) field_3_flags ( writeShort . out ; ) field_2_col ( writeShort . out ; ) field_1_row ( writeShort . out { ) out LittleEndianOutput (  void
} ; ) count , string ( LastIndexOf return { ) string string (
} ; ) object ( AddLastImpl return { ) object E (  bool
do { state = src; unsetSection = res; } while (!state.compareAndSet(src, section, subsection)); ConfigSnapshot(String section, String subsection);
public final string TagName() { return ""; }
subrecords.Add((SubRecord element, int index) => { });
lock (mutex) { return delegate { o.Remove(o); }; }
return new DoubleMetaphoneFilter(input, maxCodeLength, inject);
} ; ) ( inCoreLength return { ) (
} bool newValue = value { } newValue void ( )
} newSource = newSource.this; oldSource = oldSource.this { } newSource ContentSource, oldSource ContentSource (Pair);
if (i <= count) throw new ArrayIndexOutOfBoundsException(i); return entries[i];
base.PutMethod("/repos", "cr", "CreateRepo", "2016-06-07", new CreateRepoRequest());
return deltaBaseAsOffset;
if (list.modCount == expectedModCount) { if (lastLink != null) { Link<ET> next = lastLink.next; Link<ET> previous = lastLink.previous; lastLink.next = previous; lastLink.previous = next; --pos; lastLink = null; ++expectedModCount; --list.size; ++list.modCount; } else { throw new IllegalStateException(); } } else { throw new ConcurrentModificationException(); }
MergeShardsResult result = ExecuteMergeShards(BeforeClientExecution(new MergeShardsRequest()));
AllocateHostedConnectionResult result = ExecuteAllocateHostedConnection(BeforeClientExecution(new AllocateHostedConnectionRequest()));
} ; start return { ) (
} ; ) false , query ( getTerms return { ) query Query (  ] [ WeightedTerm final static public
throw new ReadOnlyBufferException();
for (int i = 0; i < iterations; i++) { byte byte0 = (byte)(blocks[blocksOffset++] & 0xFF); values[valuesOffset++] = (byte0 & 3) << 4 | (byte)((blocks[blocksOffset] & 0xFF) >> 4); byte byte1 = (byte)(blocks[blocksOffset++] & 0xFF); values[valuesOffset++] = (byte1 & 15) << 2 | (byte)((blocks[blocksOffset] & 0xFF) >> 6); byte byte2 = (byte)(blocks[blocksOffset++] & 0xFF); values[valuesOffset++] = (byte2 & 63); }
} result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); if (result.EndsWith(Constants.DOT_GIT)) { result = result.Substring(0, result.Length - 1); } else { throw new ArgumentException(); } if (elements.Length == 0) { throw new ArgumentException(); } else { elements = s.Split(new[] { Path.DirectorySeparatorChar, '/' }, StringSplitOptions.RemoveEmptyEntries); } if (s == null) { throw new ArgumentException(); } if (s.Equals("") || s.Equals("/")) { getPath = s; } if (s.Equals("file") || scheme == LOCAL_FILE) { getHost = s; }
DescribeNotebookInstanceLifecycleConfigResult result = ExecuteDescribeNotebookInstanceLifecycleConfig(BeforeClientExecution(new DescribeNotebookInstanceLifecycleConfigRequest()));
} return this.accessKeySecret; ( String )
CreateVpnConnectionResult result = ExecuteCreateVpnConnection(BeforeClientExecution(new CreateVpnConnectionRequest()));
DescribeVoicesResult result = ExecuteDescribeVoices(BeforeClientExecution(new DescribeVoicesRequest()));
ListMonitoringExecutionsResult result = BeforeClientExecution(request => ExecuteListMonitoringExecutions(request));
DescribeJobRequest(vaultName: string, jobId: string) { setVaultName(vaultName); setJobId(jobId); }
return escherRecords[index] as EscherRecord;
return ExecuteGetApis(BeforeClientExecution(new GetApisRequest()));
DeleteSmsChannelResult result = ExecuteDeleteSmsChannel(BeforeClientExecution(new DeleteSmsChannelRequest()));
} TrackingRefUpdate return new TrackingRefUpdate();
} ; ) ) b ( valueOf . String ( print { ) b bool ( void
return QueryNode.getChildren();
} workdirTreeIndex = index.this { } workdirTreeIndex(NotIgnoredFilter);
} ; ) ( readShort . @in = field_1_formatFlags { ) @in RecordInputStream ( AreaRecord
base("CloudPhoto", "GetThumbnail", "2017-07-11", ProtocolType.HTTPS);
DescribeTransitGatewayVpcAttachmentsResult result = ExecuteDescribeTransitGatewayVpcAttachments(BeforeClientExecution(new DescribeTransitGatewayVpcAttachmentsRequest()));
PutVoiceConnectorStreamingConfigurationResult result = ExecutePutVoiceConnectorStreamingConfiguration(BeforeClientExecution(new PutVoiceConnectorStreamingConfigurationRequest()));
} ; ) dim ( get . PrefixToOrdRange return { ) dim string ( OrdRange
} ; ) symbol , ) ( GetType().Name , "%s('%s')" , ) ( CultureInfo.InvariantCulture ( string.Format return } ; ) false , symbol ( Utils.EscapeWhitespace = symbol ; ) ) startIndex , startIndex ( Interval.Of ( GetText . ) ( InputStream = symbol { ) ) ( size . ) ( InputStream < startIndex && 0 >= startIndex ( if ; "" = symbol string { ) ( string
} ; ) ( PeekFirstImpl return { ) (  E
CreateWorkspacesResult result = ExecuteCreateWorkspaces(BeforeClientExecution(new CreateWorkspacesRequest()));
} ; ) ( copy return { ) ( NumberFormatIndexRecord
DescribeRepositoriesResult result = ExecuteDescribeRepositories(BeforeClientExecution(new DescribeRepositoriesRequest()));
SparseIntArray initialCapacity = new SparseIntArray(ArrayUtils.idealIntArraySize(initialCapacity)); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0;
return new HyphenatedWordsFilter(input);
CreateDistributionWithTagsResult result = ExecuteCreateDistributionWithTags(BeforeClientExecution(request));
RandomAccessFile(String mode, String fileName) throws FileNotFoundException { new File(fileName); }
DeleteWorkspaceImageResult result = beforeClientExecution(request => request.ExecuteDeleteWorkspaceImage());
public static string ToString(int value) { StringBuilder sb = new StringBuilder(); WriteHex(sb, value, 16, ""); return sb.ToString(); }
UpdateDistributionResult result = beforeClientExecution(request) as UpdateDistributionRequest; result = executeUpdateDistribution(request);
if (HSSFColor.HSSFColorPredefined.AUTOMATIC.GetIndex() == index) { return HSSFColor.HSSFColorPredefined.AUTOMATIC.GetColor(); } else { return b == null ? null : _palette[index] = b; }
throw new NotImplementedFunctionException(); ValueEval operands[srcRow, srcCol] = ValueEval;
out.WriteShort(field_1_number_crn_records); out.WriteShort(field_2_sheet_table_index);
return new DescribeDBEngineVersionsRequest(); DescribeDBEngineVersionsResult describeDBEngineVersions();
FormatRun(fontIndex = this._fontIndex, character = this._character);
public static char[] Transform(char[] chars, int offset, int length) { char[] result = new char[2 * length]; int resultIndex = 0; for (int i = offset; i < offset + length; i++) { char ch = chars[i]; result[resultIndex++] = (char)(ch >> 8); result[resultIndex++] = (char)ch; } return result; }
UploadArchiveResult result = beforeClientExecution(request => executeUploadArchive(request));
return getHiddenTokensToLeft(tokenIndex) as List<Token>;
if (this == obj) return true; if (!(base.Equals(obj))) return false; if (obj.GetType() != this.GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (!compiled.Equals(other.compiled)) return false; if (term == null) { if (other.term != null) return false; } else { if (!term.Equals(other.term)) return false; } return true;
} ; ) spanQueries ( SpanOrQuery elsereturn ; ] 0 [ spanQueries return ) 1 == length . spanQueries ( if } ; sq = ] ++ i [ spanQueries } ; ) boost , sq ( SpanBoostQuery new = sq { ) 1f != boost ( if ; ) sq ( get . weightBySpanQuery = boost ; ) ( next . sqi = sq SpanQuery { ) ) ( hasNext . sqi ( while ; 0 = i ; ) ( iterator . ) ( keySet . weightBySpanQuery = sqi > SpanQuery < Iterator ; ] ) ( size [ SpanQuery new = spanQueries ] [ SpanQuery { ) ( SpanQuery
return new StashCreateCommand();
FieldInfo fieldName(String fieldName) { return get.byName(fieldName); }
DescribeEventSourceResult result = ExecuteDescribeEventSource(BeforeClientExecution(new DescribeEventSourceRequest()));
GetDocumentAnalysisResult result = beforeClientExecution(request = new GetDocumentAnalysisRequest()); request.executeGetDocumentAnalysis();
CancelUpdateStackResult result = ExecuteCancelUpdateStack(BeforeClientExecution(new CancelUpdateStackRequest()));
ModifyLoadBalancerAttributesResult result = ExecuteModifyLoadBalancerAttributes(BeforeClientExecution(new ModifyLoadBalancerAttributesRequest()));
SetInstanceProtectionResult result = ExecuteSetInstanceProtection(BeforeClientExecution(new SetInstanceProtectionRequest()));
ModifyDBProxyResult result = ExecuteModifyDBProxy(BeforeClientExecution(new ModifyDBProxyRequest()));
} ++count; posLength = posLengths[count]; endOffset = endOffsets[count]; output.CopyChars(len, offset); outputs[count] = new CharsRefBuilder(); if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } if (outputs.Length == count) { outputs = ArrayUtil.Grow(outputs, count + 1); } if (endOffsets.Length == count) { int[] next = new int[ArrayUtil.Oversize(count + 1, sizeof(int))]; Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } if (posLengths.Length == count) { int[] next = new int[ArrayUtil.Oversize(count + 1, sizeof(int))]; Array.Copy(posLengths, 0, next, 0, count); posLengths = next; }
base("CloudPhoto", "2017-07-11", "FetchLibraries", "cloudphoto", ProtocolType.HTTPS);
} ; ) objects ( exists . fs return { ) (  bool
} out = this.out; } OutputStream out = new FilterOutputStream(out);
base.Method = HttpMethod.Put; base.UriPattern = "/clusters/[ClusterId]"; base.Product = "csk"; base.ActionName = "ScaleCluster"; base.Version = "2015-12-15"; base.ServiceCode = "CS";
return new DataValidationConstraint(operatorType, formula1, formula2);
ListObjectParentPathsResult result = ExecuteListObjectParentPaths(BeforeClientExecution(new ListObjectParentPathsRequest()));
DescribeCacheSubnetGroupsResult result = ExecuteDescribeCacheSubnetGroups(BeforeClientExecution(new DescribeCacheSubnetGroupsRequest()));
} ; ) flag , field_5_options ( setShortBoolean . sharedFormula = field_5_options { ) flag bool (  void
} ; reuseObjects return ( ) { bool
ErrorNode t = new ErrorNodeImpl(badToken); this.setParent(t); t.addAnyChild();
if (!args.IsEmpty()) throw new IllegalArgumentException("Unknown parameters: " + args); Map<String, String> args;
EventSubscription request = beforeClientExecution(new RemoveSourceIdentifierFromSubscriptionRequest()); executeRemoveSourceIdentifierFromSubscription(request);
public static TokenFilterFactory CreateInstance(string name, Map<string, string> args) => new TokenFilterFactory(name, args);
base("CloudPhoto", "AddAlbumPhotos", "2017-07-11", ProtocolType.HTTPS);
GetThreatIntelSetResult result = ExecuteGetThreatIntelSet(BeforeClientExecution(new GetThreatIntelSetRequest()));
} ; ) ) ( clone . b , ) ( clone . a ( Binary new return { ) (  RevFilter
} ArmenianStemmer is o ? o : (object)false;
protected bool HasArray() { return true; }
UpdateContributorInsightsResult result = beforeClientExecution(request => { return executeUpdateContributorInsights(request); });
} = writeProtect; null = fileShare; writeProtect(remove.records); fileShare(remove.records); void { }
} expand = expand.this; ) analyzer, dedup (base { ) analyzer Analyzer, expand bool, dedup bool (SolrSynonymParser
RequestSpotInstancesResult result = ExecuteRequestSpotInstances(BeforeClientExecution(new RequestSpotInstancesRequest()));
} ; ) ( GetObjectData . ) ( FindObjectRecord return { ) (  ] [
GetContactAttributesResult result = ExecuteGetContactAttributes(BeforeClientExecution(new GetContactAttributesRequest()));
return (getKey() + ": " + getValue());
ListTextTranslationJobsResult result = ExecuteListTextTranslationJobs(BeforeClientExecution(new ListTextTranslationJobsRequest()));
GetContactMethodsResult result = beforeClientExecution(request = new GetContactMethodsRequest()); result = request.executeGetContactMethods();
public static FunctionMetadata getInstance = (getFunctionByNameInternal(name) == null) ? null : (getInstanceCetab = getFunctionByNameInternal(name));
DescribeAnomalyDetectorsResult result = ExecuteDescribeAnomalyDetectors(BeforeClientExecution(new DescribeAnomalyDetectorsRequest()));
public static string Message(string insertId, ObjectId changeId, bool falseValue) { return message; }
throw new MissingObjectException(copy.ObjectId, JGitText.UnknownObjectType2); if (typeHint == OBJ_ANY) throw new MissingObjectException(copy.ObjectId, JGitText.UnknownObjectType2); if (sz < 0) { sz = db.GetObjectSize(objectId, typeHint); }
ImportInstallationMediaResult result = beforeClientExecution(request = new ImportInstallationMediaRequest()); result = executeImportInstallationMedia(request);
PutLifecycleEventHookExecutionStatusResult result = ExecutePutLifecycleEventHookExecutionStatus(BeforeClientExecution(request));
} ; ) ) ( ReadDouble . In ( { ) In LittleEndianInput ( NumberPtg
GetFieldLevelEncryptionConfigResult result = ExecuteGetFieldLevelEncryptionConfig(BeforeClientExecution(request));
DescribeDetectorResult result = ExecuteDescribeDetector(BeforeClientExecution(new DescribeDetectorRequest()));
ReportInstanceStatusResult result = BeforeClientExecution(new ReportInstanceStatusRequest()); return ExecuteReportInstanceStatus(result);
DeleteAlarmResult result = ExecuteDeleteAlarm(BeforeClientExecution(new DeleteAlarmRequest()));
} ; ) input ( new PortugueseStemFilter return { ) input TokenStream ( TokenStream
new ENCODED_SIZE[] = { reserved (FtCblsSubRecord) };
public override bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
GetDedicatedIpResult result = beforeClientExecution(request => executeGetDedicatedIp(request));
} ; " >= _p" + precedence return { ) (  string
ListStreamProcessorsResult result = ExecuteListStreamProcessors(BeforeClientExecution(new ListStreamProcessorsRequest()));
DeleteLoadBalancerPolicyRequest(string policyName, string loadBalancerName) { setPolicyName(policyName); setLoadBalancerName(loadBalancerName); }
} var options = _options { } options(WindowProtectRecord);
UnbufferedCharStream data = new UnbufferedCharStream(bufferSize); int n = 0;
return ExecuteGetOperations(BeforeClientExecution(request) as GetOperationsRequest) as GetOperationsResult;
} ; ) w5 , 16 + o , b ( encodeInt32 . NB ; ) w4 , 12 + o , b ( encodeInt32 . NB ; ) w3 , 8 + o , b ( encodeInt32 . NB ; ) w2 , 4 + o , b ( encodeInt32 . NB ; ) w1 , o , b ( encodeInt32 . NB { ) o , b ] [ (  void
} ; ) ( readShort . @in = field_9_tab_width_ratio ; ) ( readShort . @in = field_8_num_selected_tabs ; ) ( readShort . @in = field_7_first_visible_tab ; ) ( readShort . @in = field_6_active_sheet ; ) ( readShort . @in = field_5_options ; ) ( readShort . @in = field_4_height ; ) ( readShort . @in = field_3_width ; ) ( readShort . @in = field_2_v_hold ; ) ( readShort . @in = field_1_h_hold { ) @in RecordInputStream ( WindowOneRecord
StopWorkspacesResult result = ExecuteStopWorkspaces(BeforeClientExecution(new StopWorkspacesRequest()));
} } } } ; ) ( close . fos { finally } ; ) ( close . channel { try { finally } ; ) fileLength ( truncate . channel { try { finally } ; ) ( dump { try ; false = isOpen { ) isOpen ( if { IOException throws ) ( void
DescribeMatchmakingRuleSetsResult result = ExecuteDescribeMatchmakingRuleSets(BeforeClientExecution(new DescribeMatchmakingRuleSetsRequest()));
} ; null return { ) len , off , ] [ surface , wordId (  string
} return pathStr; string ( ) {
public static double Method(double[] v) { if (v != null && v.Length >= 1) { int m = 0, s = 0, n = v.Length; for (int i = 0; i < n; ++i) { s += (int)v[i]; } m = s / n; s = 0; for (int i = 0; i < n; ++i) { s += (int)(v[i] - m) * (int)(v[i] - m); } return (n == 1) ? 0 : s; } return double.NaN; }
DescribeResizeResult result = ExecuteDescribeResize(BeforeClientExecution(new DescribeResizeRequest()));
public final bool passedThroughNonGreedyDecision() { }
} ; ) 0 ( end return { ) (
} } } ; ) ctx , currentCell ( onCell . handler ; ) ) 1 + firstColumn - colNumber . ctx ( , rowSize ( addAndCheck . ArithmeticUtils = ordinalNumber . ctx ; ) width ) ( , ) firstRow , rowNumber . ctx ( subAndCheck . ArithmeticUtils ) ( ( mulAndCheck . ArithmeticUtils = rowSize } ; continue { ) traverseEmptyCells ! && ) currentCell ( isEmpty ( if } ; continue { ) null == currentCell ( if ; ) colNumber . ctx ( getCell . currentRow = currentCell { ) colNumber . ctx ++ ; lastColumn <= colNumber . ctx ; firstColumn = colNumber . ctx ( for } ; continue { ) null == currentRow ( if ; ) rowNumber . ctx ( getRow . sheet = currentRow { ) rowNumber . ctx ++ ; lastRow <= rowNumber . ctx ; firstRow = rowNumber . ctx ( for ; null = currentCell Cell ; null = currentRow Row ; ) ( SimpleCellWalkContext new = ctx SimpleCellWalkContext ; 1 + firstColumn - lastColumn = width ; ) ( getLastColumn . range = lastColumn ; ) ( getFirstColumn . range = firstColumn ; ) ( getLastRow . range = lastRow ; ) ( getFirstRow . range = firstRow { ) handler CellHandler (  void
} ; pos return { ) (
} ; ) boost.other, boost.this(.Float elsereturn;))(get.bytes.this(compareTo.))(get.bytes.other return) boost.other == boost.this(if {) other ScoreTerm(
for (int i = 0; i < len; i++) { switch (s[i]) { case HAMZA_ABOVE: break; case HEH: delete = s[i]; break; case HEH_GOAL: case HEH_YEH: break; case KAF: delete = s[i]; break; case KEHEH: break; case YEH: case YEH_BARREE: case FARSI_YEH: break; } } return len;
((LittleEndianOutput)out).WriteShort(_options);
} exactOnly = this.exactOnly; boolean exactOnly(DiagnosticErrorListener)
KeySchemaElement(KeyType keyType, string attributeName) { setKeyType(keyType); setAttributeName(attributeName); }
GetAssignmentResult result = beforeClientExecution(request => executeGetAssignment(request));
return findOffset(id) != 1;
} this return allGroups = allGroups.this { } allGroups bool ( GroupingSearch
public synchronized void DimConfig(string dimName, bool v) { var ft = fieldTypes.get(dimName); if (ft == null) ft = new DimConfig(); fieldTypes.put(dimName, ft); }
} size return } } ++size { ) 0 >= cmd.e( if ) c(at) = e Cell ) next.i = c Character { ) hasNext.i ( for 0 = size ) iterator() ) keySet().cells = i > Character < IEnumerator { ) (
DeleteVoiceConnectorResult result = ExecuteDeleteVoiceConnector(BeforeClientExecution(new DeleteVoiceConnectorRequest()));
DeleteLifecyclePolicyResult request = ExecuteDeleteLifecyclePolicy(BeforeClientExecution(new DeleteLifecyclePolicyRequest()));
