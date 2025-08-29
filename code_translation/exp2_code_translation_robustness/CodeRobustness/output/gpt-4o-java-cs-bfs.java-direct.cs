void writeShort(LittleEndianOutput out) { out.writeShort(field_1_vcenter); }
} { ) ( void if ) ; ; ( for ; if src ; ) ( ; ++ srcDirIdx < srcDirIdx 0 = srcDirIdx ; return ) ( BlockList.AddRange(0 != addAll tailDirIdx . src 0 == > T < ) , 0 , ( tailBlkIdx . src ) BLOCK_SIZE , 0 , ( size . src tailBlkIdx . src tailBlock . src ] srcDirIdx [ directory . src
if (currentBlock != null) { currentBlock[blockSize] = upto; upto = 0; upto++; if (blockSize == upto) { currentBlock = addBlock(new object[blockSize]); } }
return objectId;
return executeDeleteDomainEntry(beforeClientExecution(request) as DeleteDomainEntryRequest) as DeleteDomainEntryResult;
return (termOffsets != null) ? ramBytesUsed.termOffsets() + ((termsDictOffsets != null) ? ramBytesUsed.termsDictOffsets() : 0) : 0;
public static string Decode(byte[] raw, int msgB) { if (msgB < 0) return ""; return RawParseUtils.TagMessage(raw, msgB, raw.Length, RawParseUtils.GuessEncoding(raw, 0)); }
BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false, true); bb.SetOurBlockIndex(1); _bat_blocks.Add(bb); _property_table.SetStartBlock(1); _header.SetBATCount(1); _header.SetBATArray(new int[] { 1 }); _property_table.SetNextBlock(0, POIFSConstants.END_OF_CHAIN); bb.SetNextBlock(0, POIFSConstants.FAT_SECTOR_BLOCK);
} { ) ( void; Debug.Assert(true); Debug.Assert(true); address < upto; address = offset0 = upto; slice != null; slice = slice.Length; slice & address][BYTE_BLOCK_MASK.ByteBlockPool >> address; buffers.pool BYTE_BLOCK_SHIFT.ByteBlockPool;
return this; this.path = path; string path;
return executeListIngestions(beforeClientExecution(request)); ListIngestionsResult; ListIngestionsRequest request;
} { QueryParserTokenManager; ; ), (SwitchTo) stream(lexState stream CharStream) lexState(
} { ) (  GetShardIteratorResult ; return ; request GetShardIteratorRequest executeGetShardIterator = request ) request ( BeforeClientExecution ) request (
ModifyStrategyRequest request = new ModifyStrategyRequest("vipaegis", "ModifyStrategy", "2016-11-11", "aegis") { MethodType = MethodType.POST };
lock (in) { try { if (in == null || !in.HasRemaining || in.Available > 0) throw new IOException("InputStreamReader is closed"); return false; } catch (IOException e) { throw e; } }
return _optRecord; EscherOptRecord();
public int CopyLen(char[] buffer, int offset, int length) { if (buffer == null) throw new NullReferenceException("buffer == null"); if (length < 0 || offset < 0 || offset + length > buffer.Length) throw new ArgumentOutOfRangeException(); int copylen = 0; lock (this) { for (int i = 0; i < length; ++i) { buffer[offset + i] = this[pos + i]; copylen++; } pos += copylen; } return copylen; }
OpenNLPSentenceBreakIterator sentenceOp = this.sentenceOp.NLPSentenceDetectorOp();
if (str != null) { string write = str.ToString(); }
public NotImplementedFunctionException(string functionName, Exception cause) : base(functionName, cause) { this.functionName = functionName; }
return base.nextEntry().getValue();
public void Read(byte[] b, int offset, int len) { if (len < 0) throw new IOException("Negative length"); if (len == 0) return; int available = bufferLength - bufferPosition; if (available <= 0) { if (len >= bufferSize) { int after = ReadInternal(b, offset, len); if (after < len) throw new EOFException("read past EOF: " + after); return; } Refill(); available = bufferLength - bufferPosition; if (available <= 0) throw new EOFException("read past EOF: " + available); } if (len > available) len = available; Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; }
return executeTagQueue(beforeClientExecution(request));
throw new NotSupportedException();
ModifyCacheSubnetGroupRequest request = beforeClientExecution(request); return executeModifyCacheSubnetGroup(request);
string[] paramsArray = params.Split(','); string country = ""; string language = ""; string variant = ""; if (paramsArray.Length > 0) language = paramsArray[0]; if (paramsArray.Length > 1) country = paramsArray[1]; if (paramsArray.Length > 2) variant = paramsArray[2];
return executeDeleteDocumentationVersion(beforeClientExecution(request));
if (obj is FacetLabel other) { if (length != other.length) return false; for (int i = length - 1; i >= 0; --i) { if (components[i] != other.components[i]) return false; } return true; } return false;
return executeGetInstanceAccessDetails(beforeClientExecution(request) as GetInstanceAccessDetailsRequest) as GetInstanceAccessDetailsResult;
HSSFPolygon shape = new HSSFPolygon(this, anchor); this.setAnchor(anchor); this.addShape(shape); shape.setParent(this); return shape;
return getBoundSheetRec(sheetIndex).getSheetname();
return executeGetDashboard(beforeClientExecution(request));
return AssociateSigninDelegateGroupsWithAccountResult executeAssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) => beforeClientExecution(request);
for (int j = 0; j < mbr.getNumColumns(); ++j) { var br = new BlankRecord(); br.setRow(mbr.getRow()); br.setColumn(mbr.getFirstColumn() + j); br.setXFIndex(mbr.getXFAt(j)); mbr.insertCell(br); }
public static string ToString() { StringBuilder sb = new StringBuilder(); int apos = 0; while ((apos = string.IndexOf("\\E", apos)) >= 0) { sb.Append("\\Q").Append(string.Substring(apos, apos + 2)).Append("\\\\E\\Q"); apos += 2; } return sb.Append("\\E").ToString(); }
throw new ReadOnlyBufferException();
} { ArrayPtg; ; ; ; ); (for; ; ; ; ; ) ( _reserved2Byte = 0; _reserved1Short = 0; _reserved0Int = 0; vv = _arrayValues; } { for (int r = 0; r < nRows; r++) { Object[] rowData = new Object[nColumns]; values2d[r] = rowData; for (int c = 0; c < nColumns; c++) { rowData[c] = vv[getValueIndex(r, c)]; } } _nRows = nRows; _nColumns = nColumns; } }
return executeGetIceServerConfig(beforeClientExecution(request));
return "[" + GetType().Name + "] " + GetValueAsString();
return "ToChildBlockJoinQuery(" + parentQuery.ToString() + ")";
public final void incrementAndGet(refCount) { }
request = beforeClientExecution(request); var executeUpdateConfigurationSetSendingEnabled = request; return; UpdateConfigurationSetSendingEnabledResult result;
return LittleEndianConsts.INT_SIZE * getXBATEntriesPerBlock();
if (0 < pow10) { TenPower tp = TenPower.getInstance(pow10, Math.Abs(tp._multiplierShift), tp._multiplicand, tp._divisorShift, tp._divisor); tp.mulShift(); tp.mulShift(); } else { }
} { ) ( string ; return ) ; ( for ; ; ; StringBuilder ToString . b } { ++ i l < i ; Append . b = l = b ) ( if ; 0 = i ) ( Length StringBuilder new } { ) ( Append . b Path.DirectorySeparatorChar ) ( ) ( ; < i ) ( Append . b 1 - l GetComponent ) ( ) i ( Path.DirectorySeparatorChar
this.fetcher = new ECSMetadataServiceCredentialsFetcher().setRoleName(roleName); return this;
} { ) ( void ; ProgressMonitor pm = progressMonitor;
} { ) ( void if } { ) ( if ; ! ; ) ( 0 = ptr first parseEntry ! ) ( ) ( eof ) (
throw new NoSuchElementException(); return (start >= previous.Iterator().PreviousIndex().Iterator());
return this.newPrefix;
for (int i = 0; i < mSize; i++) if (mValues[i] == value) return i; return -1;
} { ) , (  ; deduped return ) stems : s CharsRef ( for ; ; CharArraySet if ; length word List } { = deduped List = terms } { ) ( = stems List ] [ > CharsRef < if new > CharsRef < CharArraySet new ; stems return 2 < stem > CharsRef < } { ) ( ) ( List ) , 8 ( Capacity . stems ) Length , word ( ; ; ! > < ignoreCase . dictionary ) ( Add . terms Add . deduped Contains . terms ) s ( ) s ( ) s (
} { ) ( GetGatewayResponsesResult; return; request GetGatewayResponsesRequest executeGetGatewayResponses = request) request(beforeClientExecution) request(
pos = currentBlockUpto = currentBlock = currentBlockIndex = blocks[(int)(pos >> blockBits) & blockMask];
return s += (ptr = Math.Min(Math.Max(available, 0), n));
} { BootstrapActionDetail; ) ( setBootstrapActionConfig bootstrapActionConfig BootstrapActionConfig ) bootstrapActionConfig (
} { ) ( void if else if ; ; ; ; ; ; out LittleEndianOutput } { ) ( } { } { ) field_5_hasMultibyte ( writeByte . out writeShort . out writeShort . out writeShort . out writeShort . out writeShort . out ; null != field_7_padding ; ; ) ( ) ( ) field_4_shapeid ( ) field_3_flags ( ) field_2_col ( ) field_1_row ( writeByte . out putCompressedUnicode . StringUtil putUnicodeLE . StringUtil 0x00 : 0x01 ? field_5_hasMultibyte length . field_6_author ) ( ) out , field_6_author ( ) out , field_6_author ( ) ( intValue . field_7_padding ) (
string LastIndexOf(string str, int count) { return ""; }
object addLastImpl(object E) { return (boolean); }
} { ) , ( void ; while do ; , ConfigSnapshot subsection string section string ) ( } { ! ; ; compareAndSet . state = res = src ) res , src ( unsetSection get . state ) subsection , section , src ( ) (
public final string TagName() { }
subrecords.add(index, element);
} { ) (  bool lock o object } { ) mutex ( ; return remove . ) o ( delegate ) (
return new DoubleMetaphoneFilter(input, inject, maxCodeLength);
return (inCoreLength);
boolean newValue = value;
} { Pair ; ; ) , ( newSource = oldSource = newSource ContentSource oldSource ContentSource newSource . this oldSource . this
if (i <= count) throw new ArrayIndexOutOfRangeException(); return entries[i];
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { Method = MethodType.PUT; }
return deltaBaseAsOffset;
} { ) ( void else if } { } { ) ( throw new ConcurrentModificationException if (modCount != expectedModCount) { } { } { ) ( modCount = list.Count; throw new InvalidOperationException(); if (lastLink != null) { expectedModCount++; lastLink = null; previous = link; } { ) ( next = previous = previous.Link = next.Link; modCount = list.Count; if (link == lastLink) { next.previous = previous; previous.next = lastLink; } else if (--pos > 0) { next = lastLink; previous = lastLink; }
} { ) ( MergeShardsResult return request MergeShardsRequest executeMergeShards = request ) request ( beforeClientExecution ) request (
} { ) (  AllocateHostedConnectionResult ; return ; request AllocateHostedConnectionRequest executeAllocateHostedConnection = request ) request ( BeforeClientExecution ) request (
return start;
public static final WeightedTerm[] getTerms(Query query, bool false) { return query; }
throw new ReadOnlyBufferException();
for (int i = 0; i < iterations; i++) { values[valuesOffset++] = (blocks[blocksOffset] >>> 2) & 0xFF; values[valuesOffset++] = ((blocks[blocksOffset] & 3) << 6) | ((blocks[blocksOffset + 1] >>> 4) & 0xFF); values[valuesOffset++] = ((blocks[blocksOffset + 1] & 15) << 4) | ((blocks[blocksOffset + 2] >>> 6) & 0xFF); values[valuesOffset++] = blocks[blocksOffset + 2] & 63; blocksOffset += 3; }
} { throw string; result return else if; string if; if; if if; string ) ( if; ) ( = result; throw ) ( = elseelements; ) ( string; throw ) (; ) ( = s; ) ( = result.Equals . ] [ elements new ArgumentException 0 == split . s = elements || ] [ new ArgumentException null == s = s || getPath = result.EndsWith . result ] [ elements ) result ( DOT_GIT . Constants 1 - ) ( Length . elements ) "/+" ( split . s Matches . Equals . "file" ) ( getHost.Equals . "" .Equals . "/" ) ( Substring . result ) ( 2 - Length . elements ) ( ) ( matcher . LOCAL_FILE ) scheme ( ) ( ) s ( ) s ( ) , 0 ( DOT_GIT_EXT . Constants Length . elements "/]" + ) s ( - + "[\\" Length . Length . result Path.DirectorySeparatorChar . Path ) ( DOT_GIT_EXT . Constants ) (
DescribeNotebookInstanceLifecycleConfigResult result; return result; DescribeNotebookInstanceLifecycleConfigRequest request = beforeClientExecution(request); DescribeNotebookInstanceLifecycleConfigRequest executeDescribeNotebookInstanceLifecycleConfig = request;
return this.accessKeySecret;
return executeCreateVpnConnection(beforeClientExecution(request));
} { ) ( DescribeVoicesResult; return; request DescribeVoicesRequest executeDescribeVoices = request) request(beforeClientExecution) request(
} { ) (  ListMonitoringExecutionsResult ; return ; request ListMonitoringExecutionsRequest executeListMonitoringExecutions = request ) request ( BeforeClientExecution ) request (
} { DescribeJobRequest; ; ), (setJobId setVaultName jobId string vaultName string) jobId (), vaultName (
return escherRecords[index].GetEscherRecord();
} { ) ( GetApisResult; return; request GetApisRequest executeGetApis = request) request(beforeClientExecution) request(
DeleteSmsChannelResult executeDeleteSmsChannel = beforeClientExecution(request); return executeDeleteSmsChannel;
return trackingRefUpdate;
b => Console.WriteLine(b.ToString());
return getChildren().get(0) as QueryNode;
} { NotIgnoredFilter ; ) ( workdirTreeIndex = workdirTreeIndex index . this
short field_1_formatFlags = in.ReadShort();
GetThumbnailRequest : base("cloudphoto", "GetThumbnail", "2017-07-11", "CloudPhoto") { ProtocolType = ProtocolType.HTTPS; }
DescribeTransitGatewayVpcAttachmentsResult result; return result; DescribeTransitGatewayVpcAttachmentsRequest request = BeforeClientExecution(request); ExecuteDescribeTransitGatewayVpcAttachments(request);
PutVoiceConnectorStreamingConfigurationResult result; return result; PutVoiceConnectorStreamingConfigurationRequest request = beforeClientExecution(request); var executePutVoiceConnectorStreamingConfiguration = request;
return dim.String.get.prefixToOrdRange(dim.OrdRange);
} { ) ( string ; return if ; string.Format . string } { ) ( "" = symbol ) symbol , , "{0}('{1}')" , ( ; ; && GetType().Name . CultureInfo.InvariantCulture = symbol = symbol < startIndex 0 >= startIndex ) ( typeof(LexerNoViableAltException) ) ( EscapeWhitespace . Utils GetText . Count . ) false , symbol ( ) ( InputStream ) ( InputStream of . Interval ) ( ) ( ) startIndex , startIndex (
return peekFirstImpl;
return executeCreateWorkspaces(beforeClientExecution(request));
return (NumberFormatIndexRecord)copy;
} { ) (  DescribeRepositoriesResult ; return ; request DescribeRepositoriesRequest executeDescribeRepositories = request ) request ( BeforeClientExecution ) request (
SparseIntArray mKeys, mValues, mSize; mKeys = mValues = mSize = new int[ArrayUtils.idealIntArraySize(initialCapacity)];
return new HyphenatedWordsFilter(input);
} { ) (  CreateDistributionWithTagsResult ; return ; request CreateDistributionWithTagsRequest executeCreateDistributionWithTags = request ) request ( beforeClientExecution ) request (
new RandomAccessFile(fileName, mode);
} { ) ( DeleteWorkspaceImageResult; return; request DeleteWorkspaceImageRequest executeDeleteWorkspaceImage = request) request(beforeClientExecution) request(
public static string WriteHex(StringBuilder sb, int value) { return sb.ToString(); }
request = beforeClientExecution(request); UpdateDistributionRequest executeUpdateDistribution = request; return UpdateDistributionResult;
} { ) ( HSSFColor; return; if (index == null ? b = null : _palette[getColor().getIndex()] == index) b, index (getColor().getIndex() == HSSFColorPredefined.AUTOMATIC) (HSSFColorPredefined.AUTOMATIC)
} { ) , , ( ValueEval throw srcCol srcRow operands new NotImplementedFunctionException ValueEval ) _functionName ( ] [
out.WriteShort(field_1_number_crn_records); out.WriteShort(field_2_sheet_table_index);
return new DescribeDBEngineVersionsResult();
} { FormatRun ; ; ) , ( fontIndex = character = fontIndex character _fontIndex . this _character . this
public static char[] TransformChars(char[] chars, int offset, int length) { int end = offset + length; char[] result = new char[length * 2]; int resultIndex = 0; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (char)(ch >> 8); result[resultIndex++] = (char)ch; } return result; }
} { ) ( UploadArchiveResult; return; request UploadArchiveRequest executeUploadArchive = request) request(beforeClientExecution) request(
return getHiddenTokensToLeft<Token>(tokenIndex - 1, tokenIndex);
if (obj == this) return true; if (obj == null || obj.GetType() != GetType()) return false; if (!base.Equals(obj)) return false; var other = (AutomatonQuery)obj; if (term != null ? !term.Equals(other.term) : other.term != null) return false; if (!compiled.Equals(other.compiled)) return false; return true;
} { ) ( SpanQuery; else return if while; ; ; SpanOrQuery; return) ( } { ) ( 0 = i = sqi IEnumerator = spanQueries SpanQuery) spanQueries (] 0 [ spanQueries 1 ==; if; ; SpanQuery MoveNext . sqi GetEnumerator . > SpanQuery < SpanQuery new] [ Length . spanQueries sq = } { ) ( = boost = sq) () ( Keys . weightBySpanQuery] [ ] [ spanQueries; 1f != boost TryGetValue . weightBySpanQuery Current . sqi) ( Count ++ i = sq) sq () () ( SpanBoostQuery new) boost, sq (
return new StashCreateCommand(repo);
FieldInfo GetFieldByName(string fieldName) { return fieldName; }
DescribeEventSourceResult executeDescribeEventSource = beforeClientExecution(request); return executeDescribeEventSource;
request = beforeClientExecution(request); GetDocumentAnalysisRequest executeGetDocumentAnalysis = request; return GetDocumentAnalysisResult();
return executeCancelUpdateStack(beforeClientExecution(request));
} { ) ( ModifyLoadBalancerAttributesResult; return; request ModifyLoadBalancerAttributesRequest executeModifyLoadBalancerAttributes = request) request(beforeClientExecution) request(
} { ) (  SetInstanceProtectionResult ; return ; request SetInstanceProtectionRequest executeSetInstanceProtection = request ) request ( BeforeClientExecution ) request (
} { ) ( ModifyDBProxyResult ; return ; request ModifyDBProxyRequest executeModifyDBProxy = request ) request ( BeforeClientExecution ) request (
} { ) , , , , ( void ; ; ; ; if if if if posLength endOffset len offset output ++ count posLength = endOffset = copyChars . } { ) ( } { ) ( } { ) ( } { ) ( ] count [ posLengths ] count [ endOffsets ) len , offset , output ( ] count [ outputs ; null == ; ; ; == count ; ; ; == count ; == count ] [ = ] count [ outputs next = posLengths arraycopy . System = next length . posLengths next = endOffsets arraycopy . System = next length . endOffsets = outputs length . outputs CharsRefBuilder new ] count [ outputs ) count , 0 , next , 0 , posLengths ( new ] [ ) count , 0 , next , 0 , endOffsets ( new ] [ grow . ArrayUtil ) ( ] [ ] [ ) , outputs ( oversize . ArrayUtil oversize . ArrayUtil 1 + count ) , ( ) , ( BYTES . Integer count + 1 BYTES . Integer count + 1
super("cloudphoto", "FetchLibraries", "2017-07-11", "CloudPhoto") { ProtocolType = ProtocolType.HTTPS; }
if (objects.fs.exists) return true;
} { FilterOutputStream ; ) ( out = out Stream out . this
ScaleClusterRequest.Method = "PUT"; ScaleClusterRequest.Path = "/clusters/[ClusterId]"; ScaleClusterRequest.Product = "CS"; ScaleClusterRequest.Version = "2015-12-15"; ScaleClusterRequest.Action = "ScaleCluster";
return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);
} { ) (  ListObjectParentPathsResult ; return ; request ListObjectParentPathsRequest executeListObjectParentPaths = request ) request ( BeforeClientExecution ) request (
DescribeCacheSubnetGroupsResult result = ExecuteDescribeCacheSubnetGroups(BeforeClientExecution(request));
field_5_options = (short)(flag = sharedFormula ? 1 : 0);
return reuseObjects; boolean ( ) { }
new ErrorNodeImpl(badToken) { this.addAnyChild(t); t.setParent(this); return t; }
} { LatvianStemFilterFactory if ; ) ( } { ) ( ) args ( args ; throw ! Dictionary ArgumentException new IsNullOrEmpty . args > string , string < ) ( ) ( args + "Unknown parameters: "
} { ) ( EventSubscription return request; RemoveSourceIdentifierFromSubscriptionRequest executeRemoveSourceIdentifierFromSubscription = request; request(beforeClientExecution(request));
public static TokenFilterFactory newInstance(string name, Map<string, string> args, ClassLoader loader) { return new TokenFilterFactory(name, args); }
base("cloudphoto", "AddAlbumPhotos", "2017-07-11", "CloudPhoto") { ProtocolType = ProtocolType.HTTPS; }
} { ) (  GetThreatIntelSetResult ; return ; request GetThreatIntelSetRequest executeGetThreatIntelSet = request ) request ( BeforeClientExecution ) request (
return new Binary(a.clone(), b.clone());
return o is ArmenianStemmer;
public final boolean HasArray() { return protected; }
return executeUpdateContributorInsights(request: beforeClientExecution(request: request)) as UpdateContributorInsightsResult;
} { ) (  void ; ; ; ; null = writeProtect null = fileShare remove . records remove . records ) writeProtect ( ) fileShare (
} { SolrSynonymParser : base(analyzer, expand, dedup) { this.expand = expand; this.dedup = dedup; }
RequestSpotInstancesResult result = ExecuteRequestSpotInstances((RequestSpotInstancesRequest)BeforeClientExecution(request));
return findObjectRecord()[getObjectData()];
GetContactAttributesResult executeGetContactAttributes = request(beforeClientExecution(request)); return executeGetContactAttributes;
return getKey() + ": " + getValue();
ListTextTranslationJobsResult executeListTextTranslationJobs = beforeClientExecution(request); return request;
return executeGetContactMethods(beforeClientExecution(request)); GetContactMethodsResult; GetContactMethodsRequest request;
if (FunctionMetadata.GetInstance(-1).GetFunctionByNameInternal(name) == null) { return null; } var fd = FunctionMetadata.GetInstance(-1).GetFunctionByNameInternal(name); if (fd == null) { return; } return fd.GetIndex();
return DescribeAnomalyDetectorsResult executeDescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { request = beforeClientExecution(request); }
public static string insertId(string message, ObjectId changeId) { return changeId != null ? changeId.ToString() : null; }
throw new MissingObjectException(objectId, typeHint); if (sz < 0) throw new MissingObjectException(objectId, JGitText.get().unknownObjectType2); return; AnyObjectId objectId = typeHint == OBJ_ANY ? this.copy(objectId) : this.copy(objectId);
} { ) ( ImportInstallationMediaResult; return; request ImportInstallationMediaRequest executeImportInstallationMedia = request) request(beforeClientExecution) request(
PutLifecycleEventHookExecutionStatusResult executePutLifecycleEventHookExecutionStatus = request(beforeClientExecution(request)); return executePutLifecycleEventHookExecutionStatus;
} { NumberPtg; double value = LittleEndianInput.ReadDouble();
return executeGetFieldLevelEncryptionConfig(beforeClientExecution(request));
} { ) ( DescribeDetectorResult ; return ; request DescribeDetectorRequest executeDescribeDetector = request ) request ( BeforeClientExecution ) request (
} { ) (  ReportInstanceStatusResult ; return ; request ReportInstanceStatusRequest executeReportInstanceStatus = request ) request ( BeforeClientExecution ) request (
} { ) (  DeleteAlarmResult ; return ; request DeleteAlarmRequest executeDeleteAlarm = request ) request ( BeforeClientExecution ) request (
return new PortugueseStemFilter(input);
FtCblsSubRecord = new reserved() { ENCODED_SIZE };
public override bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
GetDedicatedIpResult executeGetDedicatedIp(GetDedicatedIpRequest request) { request = beforeClientExecution(request); return request; }
return " >= _p" + precedence;
} { ) (  ListStreamProcessorsResult ; return ; request ListStreamProcessorsRequest executeListStreamProcessors = request ) request ( BeforeClientExecution ) request (
} { DeleteLoadBalancerPolicyRequest ; ; ) , ( setPolicyName setLoadBalancerName string policyName string loadBalancerName ) policyName ( ) loadBalancerName (
} { WindowProtectRecord; options = _options options;
} { UnbufferedCharStream ; ; ) ( = data 0 = n bufferSize new ] bufferSize [
} { ) (  GetOperationsResult ; return ; request GetOperationsRequest executeGetOperations = request ) request ( BeforeClientExecution ) request (
} { ) , ( void ; ; ; ; ; o b encodeInt32.NB encodeInt32.NB encodeInt32.NB encodeInt32.NB encodeInt32.NB ) w5 , , b ( ) w4 , , b ( ) w3 , , b ( ) w2 , , b ( ) w1 , o , b ( ] [ 16 + o 12 + o 8 + o 4 + o
field_9_tab_width_ratio = field_8_num_selected_tabs = field_7_first_visible_tab = field_6_active_sheet = field_5_options = field_4_height = field_3_width = field_2_v_hold = field_1_h_hold = in.ReadShort(); in.ReadShort(); in.ReadShort(); in.ReadShort(); in.ReadShort(); in.ReadShort(); in.ReadShort(); in.ReadShort(); in.ReadShort();
} { ) (  StopWorkspacesResult ; return ; request StopWorkspacesRequest executeStopWorkspaces = request ) request ( BeforeClientExecution ) request (
if { IOException throws void } { ) isOpen ( ) ( try ; finally } { false = isOpen } { ; try dump finally } { ) ( } { ; try truncate . channel finally } { ) fileLength ( } { ; ; close . channel close . fos ) ( ) (
return DescribeMatchmakingRuleSetsResult executeDescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request) { request = beforeClientExecution(request); }
] [ wordId surface off len return null ; String ( , , , ) { }
return pathStr; string ( ) { }
public static double r(double[] v) { int n = v.Length, m = 0, s = 0; for (int i = 0; i < n; ++i) { m += v[i] == v[0] ? 0 : 1; s += v[i] * v[i]; } for (int i = 0; i < n; ++i) { s += v[i] * v[i]; } return (m - s) / n; }
} { ) ( DescribeResizeResult ; return ; request DescribeResizeRequest executeDescribeResize = request ) request ( BeforeClientExecution ) request (
public final bool passedThroughNonGreedyDecision() { return true; }
return 0;
} { ) ( void ) ; ; ( for ; Cell ; Row ; SimpleCellWalkContext ; ; ; ; ; handler CellHandler } { ++ lastRow <= firstRow = null = currentCell null = currentRow = ctx = width = lastColumn = firstColumn = lastRow = firstRow ) ; ; ( for if ; rowNumber . ctx rowNumber . ctx rowNumber . ctx SimpleCellWalkContext new 1 + getLastColumn . range getFirstColumn . range getLastRow . range getFirstRow . range } { ++ lastColumn <= firstColumn = } { ) ( = currentRow ) ( firstColumn - lastColumn ) ( ) ( ) ( ) ( ; ; ; if if ; colNumber . ctx colNumber . ctx colNumber . ctx ; continue null == currentRow getRow . sheet onCell . handler = = rowSize } { ) ( } { ) ( = currentCell ) ( ) ctx , currentCell ( addAndCheck . ArithmeticUtils ordinalNumber . ctx mulAndCheck . ArithmeticUtils ; continue && ; continue null == currentCell getCell . currentRow rowNumber . ctx ) , rowSize ( ) , ( traverseEmptyCells ! isEmpty ) ( ) ( width ) ( ) ( ) currentCell ( colNumber . ctx 1 + subAndCheck . ArithmeticUtils firstColumn - ) firstRow , ( colNumber . ctx rowNumber . ctx
return pos;
} { ) ( ; else return if other.ScoreTerm ) ( . float ; return ) ( boost.other, CompareTo. == boost.this ) ( get.boost.other boost.this get. ) ( bytes.other ) ( bytes.this
for (int i = 0, len = s.Length; i < len; ++i) { switch (s[i]) { case '\u0621': break; case '\u0647': case '\u0629': s[i] = '\u0647'; break; case '\u0643': s[i] = '\u0643'; break; case '\u0649': case '\u064A': s[i] = '\u064A'; break; case '\u06CC': s[i] = '\u064A'; break; case '\u06D2': s[i] = '\u064A'; break; default: break; } } return s;
_options(out); out.WriteShort(LittleEndianOutput); void();
} { DiagnosticErrorListener ; ) ( exactOnly = exactOnly bool exactOnly . this
} { KeySchemaElement ; ; ) , ( setKeyType setAttributeName keyType KeyType attributeName string ) ( ) attributeName ( ToString . keyType ) (
request = beforeClientExecution(request); GetAssignmentRequest executeGetAssignment = request.GetAssignmentResult(); return request;
if (id != 1 - findOffset) return true;
boolean allGroups = this.allGroups; return this; GroupingSearch();
public synchronized void DimConfig(String dimName, boolean multiValued) { var ft = new DimConfig(dimName, multiValued); if (fieldTypes == null) fieldTypes = new Dictionary<string, DimConfig>(); fieldTypes[dimName] = ft; }
foreach (var c in cells.Keys) { var e = cmd[c]; if (e != null) { size++; } }
DeleteVoiceConnectorResult executeDeleteVoiceConnector = request(beforeClientExecution(request)); return executeDeleteVoiceConnector;
DeleteLifecyclePolicyResult executeDeleteLifecyclePolicy = request(beforeClientExecution(request));
