} ; ) field_1_vcenter ( WriteShort . Out { ) Out LittleEndianOutput ( void
} ; ) tailBlkIdx . src , 0 , tailBlock . src ( addAll ) 0 != tailBlkIdx . src ( if ; ) BLOCK_SIZE , 0 , ] srcDirIdx [ directory . src ( addAll ) ++ srcDirIdx ; tailDirIdx . src < srcDirIdx ; ( for ; 0 = srcDirIdx ; return ) 0 == size . src ( if { ) src > T < BlockList (  void
if (currentBlock != null) { if (upto == blockSize) { AddBlock(currentBlock); currentBlock = new byte[blockSize]; upto = 0; } b = currentBlock[upto++]; }
return new ObjectId();
} ) request ( ExecuteDeleteDomainEntry return ; ) request ( BeforeClientExecution = request { ) request DeleteDomainEntryRequest ( DeleteDomainEntryResult
return (termsDictOffsets != null ? ramBytesUsed.termsDictOffsets : 0) + (termOffsets != null ? ramBytesUsed.termOffsets : 0);
public static string GuessEncoding(byte[] raw, int length) { byte[] msgB = RawParseUtils.TagMessage(raw, 0); if (msgB.Length > 0) return RawParseUtils.Decode(msgB); return ""; }
POIFSFileSystem { true _header.setBATCount(1); _header.setBATArray(new[] { 1 }); BATBlock bb = BATBlock.createEmptyBATBlock(bigBlockSize, false); bb.setOurBlockIndex(1); _bat_blocks.add(bb); bb.setNextBlock(0); bb.setNextBlock(POIFSConstants.END_OF_CHAIN); _property_table.setStartBlock(0); }
} length.slice < upto assert; address = offset0; BYTE_BLOCK_MASK.ByteBlockPool & address = upto; null != slice assert; BYTE_BLOCK_SHIFT.ByteBlockPool >> address [ buffers.pool = slice { address (void);
} this; return; path = path.this { } path String (SubmoduleAddCommand);
ListIngestionsResult result = beforeClientExecution(request => request.ExecuteListIngestions());
} ; ) lexState ( SwitchTo ; ) stream ( { ) lexState , stream CharStream ( QueryParserTokenManager
GetShardIteratorResult result = BeforeClientExecution(new GetShardIteratorRequest()); result.ExecuteGetShardIterator();
Post.MethodType("vipaegis", "ModifyStrategy", "2016-11-11", "aegis", base.ModifyStrategyRequest);
synchronized (lock) { if (in == null) { throw new IOException("InputStreamReader is closed"); } try { return bytes.hasRemaining() || in.available() > 0; } catch (IOException e) { return false; } }
return new EscherOptRecord();
public synchronized void MethodName(char[] buffer, int offset, int length) { if (buffer == null) { throw new NullReferenceException("buffer == null"); } Arrays.CheckOffsetAndCount(buffer.Length, offset, length); if (length == 0) { return; } int copylen = length < (pos - count) ? length : (pos - count); for (int i = 0; i < copylen; i++) { this.buffer[pos + i] = buffer[offset + i]; } pos += copylen; return; }
} sentenceOp = sentenceOp.this { } sentenceOp = new NLPSentenceDetectorOp(OpenNLPSentenceBreakIterator);
} ; ) ) null ) object ( ( valueOf . string : str ? null != str ( write { ) str string (  void
} functionName = this.functionName; } cause; functionName(base) { throw new NotImplementedException(); } functionName(string cause) { throw new NotImplementedException(); }
} ; ) ( GetValue . ) ( NextEntry . base return { ) (  V
public void Read(byte[] b, int offset, int len, bool useBuffer) { if (len <= 0) return; int available = bufferLength - bufferPosition; if (available <= 0) { if (useBuffer && len > bufferSize) throw new IOException("read past EOF: " + this); refill(); available = bufferLength - bufferPosition; if (available <= 0) throw new IOException("read past EOF: " + this); } if (len <= available) { Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { Array.Copy(buffer, bufferPosition, b, offset, available); bufferPosition += available; len -= available; offset += available; if (useBuffer && len > bufferSize) throw new IOException("read past EOF: " + this); refill(); if (len > bufferLength) throw new IOException("read past EOF: " + this); Array.Copy(buffer, 0, b, offset, len); bufferPosition = len; } }
} ; ) request ( ExecuteTagQueue return ; ) request ( BeforeClientExecution = request { ) request TagQueueRequest ( TagQueueResult
throw new UnsupportedOperationException();
request = ExecuteModifyCacheSubnetGroupRequest(BeforeClientExecution(new ModifyCacheSubnetGroupRequest { CacheSubnetGroup }));
void setParams(string language = "", string country = "", string variant = "") { StringTokenizer st = new StringTokenizer(params, ","); if (st.hasMoreTokens()) language = st.nextToken(); if (st.hasMoreTokens()) country = st.nextToken(); if (st.hasMoreTokens()) variant = st.nextToken(); }
DeleteDocumentationVersionResult result = ExecuteDeleteDocumentationVersion(BeforeClientExecution(new DeleteDocumentationVersionRequest()));
if (!(obj is FacetLabel)) { return false; } FacetLabel other = (FacetLabel)obj; if (length != other.length) { return false; } for (int i = length - 1; i >= 0; i--) { if (!components[i].Equals(other.components[i])) { return false; } } return true;
GetInstanceAccessDetailsResult result = beforeClientExecution(new GetInstanceAccessDetailsRequest(request)); result = executeGetInstanceAccessDetails(request);
HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.SetParent(this); shape.SetAnchor(anchor); shapes.Add(shape); return shape;
return getBoundSheetRec(sheetIndex).getSheetname();
return ExecuteGetDashboard(BeforeClientExecution(request)) as GetDashboardResult;
AssociateSigninDelegateGroupsWithAccountResult result = ExecuteAssociateSigninDelegateGroupsWithAccount(BeforeClientExecution(new AssociateSigninDelegateGroupsWithAccountRequest()));
void MulBlankRecord() { for (int j = 0; j < getNumColumns.mbr; j++) { BlankRecord br = new BlankRecord(); br.setColumn(getFirstColumn.mbr + j); br.setRow(getRow.mbr); br.setXFIndex(getXFAt.mbr); insertCell(br); } }
public static string ConvertString(string input) { StringBuilder sb = new StringBuilder(); sb.Append("\\Q"); int apos = 0; int k; while ((k = input.IndexOf("\\E", apos)) >= 0) { sb.Append(input.Substring(apos, k - apos)).Append("\\\\E\\Q"); apos = k + 2; } sb.Append(input.Substring(apos)).Append("\\E"); return sb.ToString(); }
throw new ReadOnlyBufferException();
} 0 = _reserved2Byte; 0 = _reserved1Short; 0 = _reserved0Int; vv = _arrayValues; } } ] c [ rowData = ] ) r, c (getValueIndex[vv { ++c; nColumns < c; c = 0; for; ] r [ values2d = rowData ] [ object { ++r; nRows < r; r = 0; for; ] _nRows * _nColumns [ object new = vv ] [ object; nRows = _nRows; nColumns = _nColumns; values2d.Length = nRows; values2d[0].Length = nColumns { values2d ] [ ] [ object (ArrayPtg
GetIceServerConfigResult request = beforeClientExecution(request(new GetIceServerConfigRequest())); executeGetIceServerConfig(request);
return getClass().getName() + " [" + getValueAsString() + "]";
return "ToChildBlockJoinQuery (" + parentQuery.ToString() + ")";
public sealed override void RefCountIncrementAndGet() { }
} ; ) request ( ExecuteUpdateConfigurationSetSendingEnabled return ; ) request ( BeforeClientExecution = request { ) request UpdateConfigurationSetSendingEnabledRequest ( UpdateConfigurationSetSendingEnabledResult
} (LittleEndianConsts.INT_SIZE * GetXBATEntriesPerBlock()) {
} } ; ) _multiplierShift . tp , _multiplicand . tp ( mulShift { else } ; ) _divisorShift . tp , _divisor . tp ( mulShift { ) 0 < pow10 ( if ; ) ) pow10 ( Math.Abs ( TenPower.GetInstance = tp TenPower { ) pow10 (  void
StringBuilder b = new StringBuilder(); int l = length; for (int i = 0; i < l; i++) { b.Append(Path.DirectorySeparatorChar); b.Append(getComponent(i)); } return b.ToString();
} this return; roleName(setRoleName.fetcher.this); fetcher = fetcher.this { fetcher = new ECSMetadataServiceCredentialsFetcher(InstanceProfileCredentialsProvider); }
} ProgressMonitor pm = new ProgressMonitor();
} } ; ) ( parseEntry ) ) ( eof ! ( if ; 0 == ptr { ) ) ( first ! ( if { ) ( void
} ); throw new NoSuchElementException(); return previous.Iterator(); if (iterator.previousIndex() >= start) { (E)
return this.newPrefix;
for (int i = 0; i < mSize; i++) if (mValues[i] == value) return i; return -1;
var deduped = new List<CharsRef>(); foreach (CharsRef s in stems) { if (!terms.Contains(s)) { deduped.Add(s); terms.Add(s); } } return deduped; if (stems.Count < 2) { return stems; } var terms = new CharArraySet(dictionary, 8, ignoreCase);
GetGatewayResponsesResult request = beforeClientExecution(request); return executeGetGatewayResponses(request);
} ; ) blockMask & pos ( ) ( = currentBlockUpto ; ] currentBlockIndex [ blocks = currentBlock ; ) blockBits >> pos ( ) ( = currentBlockIndex { ) pos (  void
s = Math.Min(Math.Max(0, n), available); s += ptr; return s;
} ); bootstrapActionConfig.SetBootstrapActionConfig(new BootstrapActionConfig { BootstrapActionDetail = bootstrapActionConfig });
} } ; ) ) ( intValue . field_7_padding ( writeByte . out { ) null != field_7_padding ( if } ; ) out , field_6_author ( putCompressedUnicode . StringUtil { else } ; ) out , field_6_author ( putUnicodeLE . StringUtil { ) field_5_hasMultibyte ( if ; ) 0x00 : 0x01 ? field_5_hasMultibyte ( writeByte . out ; ) ) ( length . field_6_author ( writeShort . out ; ) field_4_shapeid ( writeShort . out ; ) field_3_flags ( writeShort . out ; ) field_2_col ( writeShort . out ; ) field_1_row ( writeShort . out { ) out LittleEndianOutput (  void
} ; ) count , string ( LastIndexOf return { ) string string (
} ; ) object ( AddLastImpl return { ) object E ( bool
} ; ) ) res , src ( compareAndSet . state ! ( while } ; ) subsection , section , src ( unsetSection = res ; ) ( get . state = src { do ; , ConfigSnapshot { ) subsection string , section string (  void
public final string TagName() { return ""; }
subrecords.Add((SubRecord element, int index) => { });
lock (mutex) { return delegate { o.Remove(o); }; }
return new DoubleMetaphoneFilter(input, maxCodeLength);
return (inCoreLength);
} bool newValue = value { } void
} newSource = this.newSource; oldSource = this.oldSource; new Pair<ContentSource, ContentSource>(newSource, oldSource);
if (i <= count) throw new ArrayIndexOutOfBoundsException(i); return entries[i];
MethodType.Put("/repos", "cr", "CreateRepo", "2016-06-07", "cr", typeof(CreateRepoRequest));
return deltaBaseAsOffset; // boolean
if (modCount_list == expectedModCount) { if (lastLink != null) { Link<ET> next_lastLink = next; Link<ET> previous_lastLink = previous; previous_lastLink.next = next; next_lastLink.previous = previous; lastLink = null; ++expectedModCount; --size_list; ++modCount_list; } else { throw new IllegalStateException(); } } else { throw new ConcurrentModificationException(); }
} ; ) request ( ExecuteMergeShards return ; ) request ( BeforeClientExecution = request { ) request MergeShardsRequest ( MergeShardsResult
} ; ) request ( ExecuteAllocateHostedConnection return ; ) request ( BeforeClientExecution = request { ) request AllocateHostedConnectionRequest ( AllocateHostedConnectionResult
} ; start return { ) (
} ; ) false , query ( getTerms return { ) query Query (  ] [ WeightedTerm final static public
throw new ReadOnlyBufferException();
for (int i = 0; i < iterations; i++) { byte byte0 = (byte)(blocks[blocksOffset++] & 0xFF); values[valuesOffset++] = (byte0 & 3) << 4 | (byte)((blocks[blocksOffset++] & 0xFF) >> 4); byte byte1 = (byte)(blocks[blocksOffset++] & 0xFF); values[valuesOffset++] = (byte1 & 15) << 2 | (byte)((blocks[blocksOffset++] & 0xFF) >> 6); byte byte2 = (byte)(blocks[blocksOffset++] & 0xFF); values[valuesOffset++] = byte2 & 63; }
if (s == null) throw new ArgumentException(); if (s.Equals("") || s.Equals("/")) { getPath = s; } else if (s.Equals("file") || scheme.Equals("LOCAL_FILE")) { var elements = s.Split(new[] { Path.DirectorySeparatorChar + "/[" + "\\" + "]" }, StringSplitOptions.None); if (elements.Length == 0) throw new ArgumentException(); if (Constants.DOT_GIT.Equals(elements[elements.Length - 1])) { result = elements[elements.Length - 2]; } else if (Constants.DOT_GIT_EXT.EndsWith(elements[elements.Length - 1])) { result = elements[elements.Length - 1].Substring(0, elements[elements.Length - 1].Length - Constants.DOT_GIT_EXT.Length); } else { result = elements[elements.Length - 1]; } }
DescribeNotebookInstanceLifecycleConfigResult result = ExecuteDescribeNotebookInstanceLifecycleConfig(BeforeClientExecution(new DescribeNotebookInstanceLifecycleConfigRequest()));
return this.accessKeySecret;
CreateVpnConnectionResult result = ExecuteCreateVpnConnection(BeforeClientExecution(new CreateVpnConnectionRequest()));
DescribeVoicesResult result = ExecuteDescribeVoices(BeforeClientExecution(new DescribeVoicesRequest()));
ListMonitoringExecutionsResult result = ExecuteListMonitoringExecutions(BeforeClientExecution(new ListMonitoringExecutionsRequest()));
DescribeJobRequest(vaultName: string, jobId: string) { setVaultName(vaultName); setJobId(jobId); }
return escherRecords[index] as EscherRecord;
return ExecuteGetApis(BeforeClientExecution(new GetApisRequest()));
} ; ) request ( ExecuteDeleteSmsChannel return ; ) request ( BeforeClientExecution = request { ) request DeleteSmsChannelRequest ( DeleteSmsChannelResult
} TrackingRefUpdate return; { TrackingRefUpdate ( )
} ; ) ) b ( valueOf . String ( Console.Write { ) b bool ( void
return QueryNode.GetChildren().Get(0);
} workdirTreeIndex = index.this { } workdirTreeIndex(NotIgnoredFilter);
} ; ) ( ReadShort . In = field_1_formatFlags { ) In RecordInputStream ( AreaRecord
HTTPS.ProtocolType("cloudphoto", "GetThumbnail", "2017-07-11", "CloudPhoto", base.GetThumbnailRequest());
DescribeTransitGatewayVpcAttachmentsResult result = ExecuteDescribeTransitGatewayVpcAttachments(BeforeClientExecution(new DescribeTransitGatewayVpcAttachmentsRequest()));
PutVoiceConnectorStreamingConfigurationResult result = ExecutePutVoiceConnectorStreamingConfiguration(BeforeClientExecution(request) as PutVoiceConnectorStreamingConfigurationRequest);
} ; ) dim ( get . PrefixToOrdRange return { ) dim string ( OrdRange
} ; ) symbol , ) ( GetType().Name , "%s('%s')" , ) ( CultureInfo.InvariantCulture ( string.Format return } ; ) false , symbol ( EscapeWhitespace . Utils = symbol ; ) ) startIndex , startIndex ( Interval . Of ( GetText . ) ( GetInputStream = symbol { ) ) ( Count . ) ( GetInputStream < startIndex && 0 >= startIndex ( if ; "" = symbol string { ) (  string
} ; ) ( PeekFirstImpl return { ) (  E
CreateWorkspacesResult result = ExecuteCreateWorkspaces(BeforeClientExecution(new CreateWorkspacesRequest()));
} ; ) ( copy return { ) (  NumberFormatIndexRecord
DescribeRepositoriesResult result = BeforeClientExecution(request => ExecuteDescribeRepositories(request));
SparseIntArray initialCapacity = new SparseIntArray(ArrayUtils.idealIntArraySize(initialCapacity)); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0;
return new HyphenatedWordsFilter(input);
CreateDistributionWithTagsResult result = ExecuteCreateDistributionWithTags(BeforeClientExecution(new CreateDistributionWithTagsRequest()));
RandomAccessFile(String mode, String fileName) throws FileNotFoundException { new File(fileName), mode; }
DeleteWorkspaceImageResult result = ExecuteDeleteWorkspaceImage(BeforeClientExecution(new DeleteWorkspaceImageRequest()));
public static string ValueToString(int value) { StringBuilder sb = new StringBuilder(); WriteHex(sb, value, 16, ""); return sb.ToString(); }
} ; ) request ( ExecuteUpdateDistribution return ; ) request ( BeforeClientExecution = request { ) request UpdateDistributionRequest ( UpdateDistributionResult
if (HSSFColor.HSSFColorPredefined.AUTOMATIC.GetIndex() == index) { return HSSFColor.HSSFColorPredefined.AUTOMATIC.GetColor(); } else { return _palette[index] == null ? null : new CustomColor(_palette[index]); }
throw new NotImplementedFunctionException(); ValueEval[,] operands = new ValueEval[srcRow, srcCol];
out.WriteShort(field_2_sheet_table_index); out.WriteShort(field_1_number_crn_records);
return new DescribeDBEngineVersionsResult() { DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest()); };
FormatRun(fontIndex = this._fontIndex, character = this._character);
public static char[] Transform(char[] chars, int offset, int length) { int end = offset + length; int resultIndex = 0; char[] result = new char[2 * length]; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (char)(ch >> 8); result[resultIndex++] = (char)ch; } return result; }
} ; ) request ( ExecuteUploadArchive return ; ) request ( BeforeClientExecution = request { ) request UploadArchiveRequest ( UploadArchiveResult
return getHiddenTokensToLeft(tokenIndex) as List<Token>;
if (this == obj) return true; if (!(obj is AutomatonQuery other)) return false; if (!base.Equals(obj)) return false; if (GetType() != obj.GetType()) return false; if (!compiled.Equals(other.compiled)) return false; if (term == null) { if (other.term != null) return false; } else { if (!term.Equals(other.term)) return false; } return true;
SpanQuery[] spanQueries = new SpanQuery[weightBySpanQuery.Count]; int i = 0; foreach (var sqi in weightBySpanQuery.Keys) { spanQueries[i++] = sqi; } if (spanQueries.Length == 1) { return spanQueries[0]; } SpanOrQuery sq = new SpanOrQuery(spanQueries); if (boost != 1f) { sq = new SpanBoostQuery(sq, boost); } return sq;
return new StashCreateCommand();
} ; ) fieldName ( get . byName return { ) fieldName string ( FieldInfo
DescribeEventSourceResult result = ExecuteDescribeEventSource(BeforeClientExecution(new DescribeEventSourceRequest()));
GetDocumentAnalysisResult result = ExecuteGetDocumentAnalysis(BeforeClientExecution(new GetDocumentAnalysisRequest()));
return executeCancelUpdateStack(request = beforeClientExecution(new CancelUpdateStackRequest())) as CancelUpdateStackResult;
ModifyLoadBalancerAttributesResult result = ExecuteModifyLoadBalancerAttributes(BeforeClientExecution(new ModifyLoadBalancerAttributesRequest()));
SetInstanceProtectionResult result = ExecuteSetInstanceProtection(BeforeClientExecution(new SetInstanceProtectionRequest()));
ModifyDBProxyResult result = ExecuteModifyDBProxy(BeforeClientExecution(new ModifyDBProxyRequest()));
} ++count; posLength = posLengths[count]; endOffset = endOffsets[count]; outputs[count].CopyChars(len, offset, output); if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } if (posLengths.Length == count) { var next = new int[ArrayUtil.Oversize(count + 1, sizeof(int))]; Array.Copy(posLengths, 0, next, 0, count); posLengths = next; } if (endOffsets.Length == count) { var next = new int[ArrayUtil.Oversize(count + 1, sizeof(int))]; Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } if (outputs.Length == count) { outputs = ArrayUtil.Grow(outputs, count + 1); } posLengths[count] = posLength; endOffsets[count] = endOffset; ++count;
base.ProtocolType = "HTTPS"; base("cloudphoto", "FetchLibraries", "2017-07-11", "CloudPhoto");
} ; ) objects ( exists . fs return { ) (  bool
} out = this.out; using (var outStream = new FilterOutputStream(out)) { }
base.Put.MethodType("setMethod", "/clusters/[ClusterId]", "csk", "ScaleCluster", "2015-12-15", "CS", ScaleClusterRequest);
} ; ) formula2 , formula1 , operatorType ( createTimeConstraint . DVConstraint return { ) formula2 string , formula1 string , operatorType ( DataValidationConstraint
ListObjectParentPathsResult result = BeforeClientExecution(request => ExecuteListObjectParentPaths(request));
DescribeCacheSubnetGroupsResult result = ExecuteDescribeCacheSubnetGroups(BeforeClientExecution(new DescribeCacheSubnetGroupsRequest()));
} ; ) flag , field_5_options ( setShortBoolean . sharedFormula = field_5_options { ) flag boolean (  void
} return reuseObjects; ( ) { bool
ErrorNode t = new ErrorNodeImpl(badToken); t.setParent(this); return t;
if (!args.IsEmpty()) throw new ArgumentException("Unknown parameters: " + args); Dictionary<string, string> args = new Dictionary<string, string>();
ExecuteRemoveSourceIdentifierFromSubscription(request => { var request = new RemoveSourceIdentifierFromSubscriptionRequest(); BeforeClientExecution(request); });
public static TokenFilterFactory NewInstance(string name, Map<string, string> args) { return loader.NewInstance(name, args); }
HTTPS.ProtocolType("cloudphoto", "AddAlbumPhotos", "2017-07-11", "CloudPhoto", base(AddAlbumPhotosRequest));
} ; ) request ( ExecuteGetThreatIntelSet return ; ) request ( BeforeClientExecution = request { ) request GetThreatIntelSetRequest ( GetThreatIntelSetResult
return new Binary() { a = clone.a, b = clone.b };
} ArmenianStemmer is o => { return (object)o is bool; }
public final bool HasArray() { return protected; }
UpdateContributorInsightsResult result = ExecuteUpdateContributorInsights(BeforeClientExecution(new UpdateContributorInsightsRequest()));
} null = writeProtect; null = fileShare; writeProtect(remove.records); fileShare(remove.records) { } (void
} expand = this.expand; analyzer, dedup(super { analyzer Analyzer, bool expand, bool dedup (SolrSynonymParser
return ExecuteRequestSpotInstances(BeforeClientExecution(request)); RequestSpotInstancesResult request = new RequestSpotInstancesRequest();
} ; ) ( GetObjectData . ) ( FindObjectRecord return { ) (  ] [
GetContactAttributesResult result = ExecuteGetContactAttributes(BeforeClientExecution(new GetContactAttributesRequest()));
return $"{getKey()}: {getValue()}";
ListTextTranslationJobsResult result = ExecuteListTextTranslationJobs(BeforeClientExecution(new ListTextTranslationJobsRequest()));
GetContactMethodsResult result = beforeClientExecution(request => executeGetContactMethods(request));
public static FunctionMetadata GetInstance(string name) { FunctionMetadata fd = GetFunctionByNameInternal(name); if (fd == null) { fd = GetFunctionByNameInternal(name); if (fd == null) { return null; } } return fd; }
DescribeAnomalyDetectorsResult result = ExecuteDescribeAnomalyDetectors(BeforeClientExecution(new DescribeAnomalyDetectorsRequest()));
public static string Message(string insertId, ObjectId changeId, string message) { return message; }
if (typeHint == OBJ_ANY) throw new MissingObjectException(copy.ObjectId, JGitText.UnknownObjectType2); if (sz > 0) { sz = db.GetObjectSize(objectId, typeHint); } else throw new MissingObjectException(copy.ObjectId, JGitText.UnknownObjectType2);
return ExecuteImportInstallationMedia(BeforeClientExecution(request) as ImportInstallationMediaRequest) as ImportInstallationMediaResult;
PutLifecycleEventHookExecutionStatusResult result = ExecutePutLifecycleEventHookExecutionStatus(BeforeClientExecution(request));
} ; ) ) ( ReadDouble . In ( { ) In LittleEndianInput ( NumberPtg
GetFieldLevelEncryptionConfigResult result = ExecuteGetFieldLevelEncryptionConfig(BeforeClientExecution(request));
return ExecuteDescribeDetector(BeforeClientExecution(request) as DescribeDetectorRequest) as DescribeDetectorResult;
ReportInstanceStatusResult executeReportInstanceStatus = beforeClientExecution(request => request);
} ); request.ExecuteDeleteAlarm(); return; }); request.BeforeClientExecution = request => { DeleteAlarmRequest request; DeleteAlarmResult
return new PortugueseStemFilter(input as TokenStream);
; ] ENCODED_SIZE [ new = reserved { ) ( FtCblsSubRecord
public override bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
GetDedicatedIpResult result = ExecuteGetDedicatedIp(BeforeClientExecution(new GetDedicatedIpRequest()));
} ; " >= _p" + precedence return { ) (  string
ListStreamProcessorsResult result = BeforeClientExecution(request => ExecuteListStreamProcessors(request));
DeleteLoadBalancerPolicyRequest(string policyName, string loadBalancerName) { setPolicyName(policyName); setLoadBalancerName(loadBalancerName); }
} var options = _options { } options(WindowProtectRecord);
UnbufferedCharStream n = new UnbufferedCharStream(bufferSize);
return ExecuteGetOperations(BeforeClientExecution(new GetOperationsRequest()));
} ; ) w5 , 16 + o , b ( encodeInt32 . NB ; ) w4 , 12 + o , b ( encodeInt32 . NB ; ) w3 , 8 + o , b ( encodeInt32 . NB ; ) w2 , 4 + o , b ( encodeInt32 . NB ; ) w1 , o , b ( encodeInt32 . NB { ) o , b ] [ (  void
new WindowOneRecord { field_1_h_hold = readShort.In(), field_2_v_hold = readShort.In(), field_3_width = readShort.In(), field_4_height = readShort.In(), field_5_options = readShort.In(), field_6_active_sheet = readShort.In(), field_7_first_visible_tab = readShort.In(), field_8_num_selected_tabs = readShort.In(), field_9_tab_width_ratio = readShort.In() };
StopWorkspacesResult result = ExecuteStopWorkspaces(BeforeClientExecution(new StopWorkspacesRequest()));
} } } } ; ) ( close . fos { finally } ; ) ( close . channel { try { finally } ; ) fileLength ( truncate . channel { try { finally } ; ) ( dump { try ; false = isOpen { ) isOpen ( if { IOException throws ) ( void
DescribeMatchmakingRuleSetsResult result = ExecuteDescribeMatchmakingRuleSets(BeforeClientExecution(new DescribeMatchmakingRuleSetsRequest()));
} null return { ) len , off , ] [ surface , wordId ( string
return pathStr; }
public static double Method(double[] v) { if (v != null && v.Length >= 1) { int n = v.Length, m = 0, s = 0; for (int i = 0; i < n; ++i) { s += (int)v[i]; } m = s / n; s = 0; for (int i = 0; i < n; ++i) { s += (int)(v[i] - m) * (int)(v[i] - m); } return s == 0 ? 0 : (double)s / n; } return double.NaN; }
DescribeResizeResult result = beforeClientExecution(request => request.ExecuteDescribeResize());
public final bool PassedThroughNonGreedyDecision() { }
return (0); }
} } } ; ) ctx , currentCell ( onCell . handler ; ) ) 1 + firstColumn - colNumber . ctx ( , rowSize ( AddAndCheck . ArithmeticUtils = ordinalNumber . ctx ; ) width ) ( , ) firstRow , rowNumber . ctx ( SubAndCheck . ArithmeticUtils ) ( ( MulAndCheck . ArithmeticUtils = rowSize } ; continue { ) TraverseEmptyCells ! && ) currentCell ( IsEmpty ( if } ; continue { ) null == currentCell ( if ; ) colNumber . ctx ( GetCell . currentRow = currentCell { ) colNumber . ctx ++ ; lastColumn <= colNumber . ctx ; firstColumn = colNumber . ctx ( for } ; continue { ) null == currentRow ( if ; ) rowNumber . ctx ( GetRow . sheet = currentRow { ) rowNumber . ctx ++ ; lastRow <= rowNumber . ctx ; firstRow = rowNumber . ctx ( for ; null = currentCell Cell ; null = currentRow Row ; ) ( SimpleCellWalkContext new = ctx SimpleCellWalkContext ; 1 + firstColumn - lastColumn = width ; ) ( GetLastColumn . range = lastColumn ; ) ( GetFirstColumn . range = firstColumn ; ) ( GetLastRow . range = lastRow ; ) ( GetFirstRow . range = firstRow { ) handler CellHandler (  void
} ; pos return { ) (
} ; ) boost . other , boost . this (  . float elsereturn ; ) ) ( get . bytes . this ( compareTo . ) ( get . bytes . other return ) boost . other == boost . this ( if { ) other ScoreTerm (
for (int i = 0; i < len; i++) { switch (s[i]) { case HAMZA_ABOVE: break; case HEH: HEH_GOAL: break; case HEH_YEH: break; case KAF: s[i] = KEHEH; break; case YEH: case FARSI_YEH: s[i] = YEH_BARREE; break; } } len = delete(len, i, s); --i; break; }
} ; ) _options ( WriteShort . Out { ) Out LittleEndianOutput ( void
} exactOnly = this.exactOnly; bool exactOnly(DiagnosticErrorListener);
KeySchemaElement(KeyType keyType, string attributeName) { setKeyType(keyType); setAttributeName(attributeName); }
return ExecuteGetAssignment(BeforeClientExecution(request)) as GetAssignmentResult;
return findOffset(id) != -1;
} this; return; allGroups = allGroups.this() { allGroups; bool GroupingSearch;
public synchronized void DimConfig(String dimName, bool v) { DimConfig ft = fieldTypes.get(dimName); if (ft == null) { ft = new DimConfig(); fieldTypes.put(dimName, ft); } ft.multiValued = v; }
var i = cells.Keys.GetEnumerator(); int size = 0; while (i.MoveNext()) { var c = i.Current; var e = Cell.at(c); if (cmd.e >= 0) { ++size; } }
DeleteVoiceConnectorResult result = ExecuteDeleteVoiceConnector(BeforeClientExecution(new DeleteVoiceConnectorRequest()));
DeleteLifecyclePolicyResult result = ExecuteDeleteLifecyclePolicy(BeforeClientExecution(new DeleteLifecyclePolicyRequest()));
