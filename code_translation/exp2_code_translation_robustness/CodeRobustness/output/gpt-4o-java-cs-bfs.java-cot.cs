LittleEndianOutput.WriteShort(field_1_vcenter);
} { ) ( void if ) ; ; ( for ; if src ; ) ( ; ++ srcDirIdx < srcDirIdx 0 = srcDirIdx ; return ) ( BlockList.AddRange 0 != AddRange tailDirIdx . src 0 == > T < ) , 0 , ( tailBlkIdx . src ) BLOCK_SIZE , 0 , ( size . src tailBlkIdx . src tailBlock . src ] srcDirIdx [ directory . src
if (currentBlock != null) { currentBlock = new(); } if (blockSize == upto) { upto = 0; upto++; currentBlock = currentBlock[blockSize]; }
return objectId; ObjectId ( ) { }
return ExecuteDeleteDomainEntry(BeforeClientExecution(request)) as DeleteDomainEntryResult;
return (termOffsets != null ? ramBytesUsed.termOffsets() : 0) + (termsDictOffsets != null ? ramBytesUsed.termsDictOffsets() : 0);
public static string Decode(byte[] raw) { int msgB = RawParseUtils.GuessEncoding(raw, 0, raw.Length); return msgB < 0 ? "" : RawParseUtils.TagMessage(raw, msgB); }
BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false, POIFSConstants.END_OF_CHAIN, POIFSConstants.FAT_SECTOR_BLOCK, 1, 0, 1, 0, true); bb.SetBATArray(_header.SetBATCount(_header.SetOurBlockIndex(_bat_blocks.Add(_property_table.SetNextBlock(_property_table.SetStartBlock(POIFSFileSystem.BATBlock))))));
} { ) ( void; Debug.Assert(true); Debug.Assert(true); address < upto; address = offset0 = upto; slice != null; slice.Length; slice & address; BYTE_BLOCK_MASK[ByteBlockPool.BYTE_BLOCK_SHIFT >> address]; buffers.pool.ByteBlockPool;
string path = this.path; return this; SubmoduleAddCommand();
return ListIngestionsResult; ListIngestionsRequest request = BeforeClientExecution(request); var executeListIngestions = request;
} { QueryParserTokenManager; ; ), (SwitchTo) stream(lexState stream CharStream) lexState(
return ExecuteGetShardIterator(BeforeClientExecution(request)) as GetShardIteratorResult;
base("vipaegis", "ModifyStrategy", "2016-11-11", "aegis") { Method = HttpMethod.Post; }
lock (in) { try { if (in.available() > 0 || !in.bytes.hasRemaining()) throw new IOException("InputStreamReader is closed"); return false; } catch (IOException e) { throw e; } }
return _optRecord; EscherOptRecord();
public int SynchronizedCopy(char[] buffer, int offset, int length) { if (buffer == null) throw new NullReferenceException("buffer == null"); if (length < 0 || offset < 0 || offset + length > buffer.Length) throw new ArgumentOutOfRangeException(); int copylen = 0; lock (this) { for (int i = 0; i < length; i++) { buffer[offset + i] = this[pos + i]; copylen++; } pos += copylen; } return copylen; }
OpenNLPSentenceBreakIterator sentenceOp = this.sentenceOp as NLPSentenceDetectorOp;
if (str != null) { string write = str.ToString(); }
throw new NotImplementedException(functionName, cause);
return base.nextEntry().getValue();
public void Read(byte[] b, int offset, int len) { if (len <= 0) return; if (bufferPosition >= bufferLength) { if (!Refill()) throw new EndOfStreamException("read past EOF: " + this); } int available = bufferLength - bufferPosition; if (len <= available) { Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { Array.Copy(buffer, bufferPosition, b, offset, available); bufferPosition += available; offset += available; len -= available; if (len > 0) { if (!useBuffer) { int after = ReadInternal(b, offset, len); if (after < len) throw new EndOfStreamException("read past EOF: " + this); } else { while (len > 0) { if (!Refill()) throw new EndOfStreamException("read past EOF: " + this); available = bufferLength - bufferPosition; int toCopy = Math.Min(len, available); Array.Copy(buffer, bufferPosition, b, offset, toCopy); offset += toCopy; len -= toCopy; bufferPosition += toCopy; } } } } }
return TagQueueResult executeTagQueue = beforeClientExecution(request)(request TagQueueRequest request);
throw new NotSupportedException();
ModifyCacheSubnetGroupRequest request = beforeClientExecution(request); return executeModifyCacheSubnetGroup(request); CacheSubnetGroup;
string[] paramsArray = params.Split(','); string language = paramsArray.Length > 0 ? paramsArray[0] : ""; string country = paramsArray.Length > 1 ? paramsArray[1] : ""; string variant = paramsArray.Length > 2 ? paramsArray[2] : "";
return ExecuteDeleteDocumentationVersion(BeforeClientExecution(request)) as DeleteDocumentationVersionResult;
if (obj is FacetLabel other) { if (length != other.length) return false; for (int i = length - 1; i >= 0; --i) { if (!components[i].Equals(other.components[i])) return false; } return true; } return false;
return executeGetInstanceAccessDetails((GetInstanceAccessDetailsRequest)beforeClientExecution(request));
HSSFPolygon shape = new HSSFPolygon(this, anchor); this.shapes.Add(shape); shape.SetAnchor(anchor); shape.SetParent(this); return shape;
return getBoundSheetRec(sheetIndex).getSheetname();
return ExecuteGetDashboard(BeforeClientExecution(request) as GetDashboardRequest) as GetDashboardResult;
return AssociateSigninDelegateGroupsWithAccountResult executeAssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request) => beforeClientExecution(request);
for (int j = 0; j < mbr.getNumColumns(); j++) { var br = new BlankRecord(); br.setRow(mbr.getRow()); br.setColumn(mbr.getFirstColumn() + j); br.setXFIndex(mbr.getXFAt(j)); MulBlankRecord.insertCell(br); }
public static string ToString(string input) { StringBuilder sb = new StringBuilder(); int apos = 0; while ((apos = input.IndexOf("\\E", apos)) >= 0) { sb.Append(input.Substring(0, apos)).Append("\\\\E\\Q"); input = input.Substring(apos + 2); } sb.Append(input).Append("\\E"); return sb.ToString(); }
throw new ReadOnlyBufferException();
} { ArrayPtg; ; ; ; ); (for; ; ; ; ;) (0 = _reserved2Byte; 0 = _reserved1Short; 0 = _reserved0Int; vv = _arrayValues; } { ++r; nRows < r; = vv; object = _nRows = _nColumns = nRows = nColumns; values2d); (for; 0 = r; object new[,] nRows) (nColumns) (values2d.Length; values2d.Length; object } { ++c; nColumns < c; = rowData object[,] values2d[0,]; 0 = c; values2d[r,] = _nRows * _nColumns = c; rowData[vv.GetValueIndex(r, c));
return executeGetIceServerConfig(request.BeforeClientExecution(request)) as GetIceServerConfigResult;
return GetType().GetProperty("Name").GetValue(this) + " [" + GetType().GetProperty("ValueAsString").GetValue(this) + "]";
return "ToChildBlockJoinQuery(" + parentQuery.ToString() + ")";
public static void incrementAndGet(ref int refCount) { }
return ExecuteUpdateConfigurationSetSendingEnabled(BeforeClientExecution(request)) as UpdateConfigurationSetSendingEnabledResult;
return LittleEndianConsts.INT_SIZE * getXBATEntriesPerBlock();
} { ) ( void else if ; TenPower pow10 } { } { ) ( = tp ; ; 0 < pow10 getInstance . TenPower mulShift mulShift ) ( ) , ( ) , ( Math.Abs _multiplierShift . tp _multiplicand . tp _divisorShift . tp _divisor . tp ) pow10 (
new StringBuilder().Append(Path.DirectorySeparatorChar).Append(components[i]).Append(Path.DirectorySeparatorChar).ToString();
this.fetcher = new ECSMetadataServiceCredentialsFetcher(roleName); this.fetcher.SetRoleName(fetcher); return this; InstanceProfileCredentialsProvider;
ProgressMonitor pm = progressMonitor;
if (!eof() && !parseEntry(first = 0, ptr)) { }
if (start >= previous.Iterator().previousIndex()) return; else throw new NoSuchElementException();
return this.newPrefix; String
for (int i = 0; i < mSize; i++) if (value == mValues[i]) return i; return -1;
return deduped = new List<CharsRef>(stems.Where(s => !dictionary.Contains(s, ignoreCase)).Select(s => new CharsRef(word, 0, word.Length)).Distinct().ToList());
return executeGetGatewayResponses(beforeClientExecution(request));
blocks[currentBlockIndex] = (currentBlockIndex = currentBlock = currentBlockUpto = pos >> blockBits & blockMask);
return s += (ptr = Math.Min(Math.Max(available, 0), n));
} { BootstrapActionDetail; ) ( bootstrapActionConfig.SetBootstrapActionConfig(new BootstrapActionConfig()) bootstrapActionConfig (
} { ) ( void if else if ; ; ; ; ; ; out LittleEndianOutput } { ) ( } { } { ) field_5_hasMultibyte ( writeByte . out writeShort . out writeShort . out writeShort . out writeShort . out writeShort . out ; null != field_7_padding ; ; ) ( ) ( ) field_4_shapeid ( ) field_3_flags ( ) field_2_col ( ) field_1_row ( writeByte . out putCompressedUnicode . StringUtil putUnicodeLE . StringUtil 0x00 : 0x01 ? field_5_hasMultibyte length . field_6_author ) ( ) out , field_6_author ( ) out , field_6_author ( ) ( intValue . field_7_padding ) (
return string.LastIndexOf(string, count);
bool AddLastImpl(object obj) { return true; }
} { ) , ( void ; while do ; , ConfigSnapshot subsection string section string ) ( } { ! ; ; compareAndSet . state = res = src ) res , src ( unsetSection get . state ) subsection , section , src ( ) (
public readonly string TagName;
subrecords.Add(index, element);
return delegate.Remove(o); lock (mutex) { }
return new DoubleMetaphoneFilter(input, inject, maxCodeLength);
return inCoreLength;
bool newValue = value;
} { Pair ; ; ) , ( newSource = oldSource = newSource ContentSource oldSource ContentSource newSource . this oldSource . this
if (i < 0 || i >= count) throw new ArrayIndexOutOfRangeException(); return entries[i];
base("cr", "CreateRepo", "2016-06-07", "cr") { MethodType = HttpMethod.Put; Path = "/repos"; }
return deltaBaseAsOffset; // boolean
} { ) ( void else if } { } { ) ( ; throw else if == expectedModCount new ConcurrentModificationException } { } { ) ( modCount . list ) ( ; throw ; ; ; ; ; if ; ; ; ; null != lastLink new IllegalStateException ++ -- ++ expectedModCount null = lastLink previous = link } { ) ( next = previous = = previous Link = next Link ) ( modCount . list size . list ; link == lastLink next . previous previous . next previous . lastLink > ET < next . lastLink > ET < -- pos
return ExecuteMergeShards(BeforeClientExecution(request) as MergeShardsRequest) as MergeShardsResult;
return ExecuteAllocateHostedConnection(BeforeClientExecution(request) as AllocateHostedConnectionRequest) as AllocateHostedConnectionResult;
return start;
public static WeightedTerm[] getTerms(Query query, bool isQuery = false) { return query; }
throw new ReadOnlyBufferException();
} { ) , , , , ( void ) ; ( for (int i = 0; i < iterations; i++) { values[valuesOffset++] = (byte0 & 3) | ((byte1 & 15) << 2) | ((byte2 & 63) << 6); values[valuesOffset++] = (blocks[blocksOffset++] << 4) | (blocks[blocksOffset++] >> 4); values[valuesOffset++] = (blocks[blocksOffset++] << 2) | (blocks[blocksOffset++] >> 6); values[valuesOffset++] = blocks[blocksOffset++]; }
} { throw string; result return else if; string if; if; if if; string ) ( if; ) ( = result; throw ) ( = elseelements; ) ( string; throw ) (; ) ( = s; ) ( = result.Equals . ] [ elements new IllegalArgumentException 0 == s.Split || ] [ new IllegalArgumentException s == null || result = getPath.EndsWith . result ] [ elements ) result ( Constants.DOT_GIT . 1 - ) ( elements.Length ) "/+" ( s.Split . s.Matches . Equals . "file" ) ( getHost.Equals . "" .Equals . "/" ) ( result.Substring ) ( elements.Length - 2 ) ( ) ( matcher.LOCAL_FILE ) scheme ( ) ( ) s ( ) s ( ) , 0 ( Constants.DOT_GIT_EXT . elements.Length "/]" + ) s ( - + "[\\" result.Length . result.Length . Path.DirectorySeparatorChar ) ( Constants.DOT_GIT_EXT ) (
return DescribeNotebookInstanceLifecycleConfigResult executeDescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) => beforeClientExecution(request);
return this.accessKeySecret; string
return ExecuteCreateVpnConnection(BeforeClientExecution(request)) as CreateVpnConnectionResult;
return DescribeVoicesResult; DescribeVoicesRequest request = beforeClientExecution(request); var executeDescribeVoices = request;
return ListMonitoringExecutionsResult executeListMonitoringExecutions = beforeClientExecution(request)(request);
public class DescribeJobRequest { public void SetJobId(string jobId) { } public void SetVaultName(string vaultName) { } public string JobId { get; set; } public string VaultName { get; set; } }
return escherRecords[index].GetEscherRecord();
return executeGetApis(beforeClientExecution(request));
return ExecuteDeleteSmsChannel(BeforeClientExecution(request)) as DeleteSmsChannelResult;
return trackingRefUpdate;
Console.WriteLine(bool.Parse(b.ToString()));
return getChildren().get(0) as QueryNode;
} { NotIgnoredFilter; workdirTreeIndex = this.index.workdirTreeIndex; )
AreaRecord field_1_formatFlags = in.ReadShort();
base("CloudPhoto", "GetThumbnail", "2017-07-11", "cloudphoto") { ProtocolType = ProtocolType.HTTPS; }
return DescribeTransitGatewayVpcAttachmentsResult; DescribeTransitGatewayVpcAttachmentsRequest request = beforeClientExecution(request); var executeDescribeTransitGatewayVpcAttachments = request;
return ExecutePutVoiceConnectorStreamingConfiguration(BeforeClientExecution(request)) as PutVoiceConnectorStreamingConfigurationResult;
return OrdRange; } { ( dim String get.prefixToOrdRange(dim);
if (startIndex >= 0 && startIndex < symbol.Length) return string.Format("{0}('{1}')", symbol.GetType().Name, Utils.EscapeWhitespace(symbol.GetText(), false));
return peekFirstImpl();
return ExecuteCreateWorkspaces(BeforeClientExecution(request) as CreateWorkspacesRequest) as CreateWorkspacesResult;
return (NumberFormatIndexRecord)copy;
return DescribeRepositoriesResult request = ExecuteDescribeRepositories(BeforeClientExecution(request));
{ SparseIntArray; ; ; ; mSize = mValues = mKeys = initialCapacity = new int[ArrayUtils.idealIntArraySize(initialCapacity)]; }
return new HyphenatedWordsFilter(input);
return ExecuteCreateDistributionWithTags(BeforeClientExecution(request)) as CreateDistributionWithTagsResult;
File file = new File(fileName); RandomAccessFile raf = new RandomAccessFile(file, mode);
return executeDeleteWorkspaceImage(request: beforeClientExecution(request)) as DeleteWorkspaceImageResult;
public static string WriteHex(StringBuilder sb, int value) { return sb.AppendFormat("{0:X}", value).ToString(); }
return ExecuteUpdateDistribution(BeforeClientExecution(request)) as UpdateDistributionResult;
} { ) ( HSSFColor; return; if (index == null ? b = null : index) { } (CustomColor new) (getColor._palette][; return index == b, index (null == b) index (getColor.GetIndex()) (HSSFColorPredefined.AUTOMATIC) (HSSFColorPredefined.AUTOMATIC
throw new NotImplementedFunctionException(_functionName, operands, srcRow, srcCol);
out.writeShort((short)field_1_number_crn_records); out.writeShort((short)field_2_sheet_table_index);
return describeDBEngineVersions(new DescribeDBEngineVersionsRequest());
} { FormatRun; ; ), (fontIndex = character = fontIndex character _fontIndex.this _character.this
public static char[] CharsToResult(char[] chars, int offset, int length) { int end = offset + length; char[] result = new char[2 * length]; for (int i = offset, resultIndex = 0; i < end; i++, resultIndex++) { char ch = chars[i]; result[resultIndex++] = (char)(ch >> 8); result[resultIndex] = (char)ch; } return result; }
return UploadArchiveResult executeUploadArchive = beforeClientExecution(request(request UploadArchiveRequest));
return getHiddenTokensToLeft(tokenIndex - 1, typeof(Token));
if (obj == null || obj.GetType() != GetType() || !base.Equals(obj) || !(obj is AutomatonQuery other) || term != other.term || compiled != other.compiled) return false; return true;
return new SpanOrQuery(spanQueries.Select(sq => weightBySpanQuery.ContainsKey(sq) && weightBySpanQuery[sq] != 1f ? new SpanBoostQuery(sq, weightBySpanQuery[sq]) : sq).ToArray());
return new StashCreateCommand(repo);
return FieldInfo.GetByName(fieldName) as string;
DescribeEventSourceResult result = ExecuteDescribeEventSource(BeforeClientExecution(request));
executeGetDocumentAnalysis = BeforeClientExecution(request); return GetDocumentAnalysisResult(request);
return executeCancelUpdateStack(beforeClientExecution(request) as CancelUpdateStackRequest) as CancelUpdateStackResult;
return ModifyLoadBalancerAttributes(executeModifyLoadBalancerAttributes(beforeClientExecution(request)));
return SetInstanceProtectionResult executeSetInstanceProtection = beforeClientExecution(request);
return ModifyDBProxyResult executeModifyDBProxy(ModifyDBProxyRequest request) => beforeClientExecution(request);
} { ) , , , , ( void ; ; ; ; if if if if posLength endOffset len offset output ++ count posLength = endOffset = copyChars . } { ) ( } { ) ( } { ) ( } { ) ( ] count [ posLengths ] count [ endOffsets ) len , offset , output ( ] count [ outputs ; null == ; ; ; == count ; ; ; == count ; == count ] [ = ] count [ outputs next = posLengths arraycopy . System = next length . posLengths next = endOffsets arraycopy . System = next length . endOffsets = outputs length . outputs CharsRefBuilder new ] count [ outputs ) count , 0 , next , 0 , posLengths ( new ] [ ) count , 0 , next , 0 , endOffsets ( new ] [ grow . ArrayUtil ) ( ] [ ] [ ) , outputs ( oversize . ArrayUtil oversize . ArrayUtil 1 + count ) , ( ) , ( BYTES . Integer count + 1 BYTES . Integer count + 1
base("cloudphoto", "FetchLibraries", "2017-07-11", "CloudPhoto") { ProtocolType = ProtocolType.HTTPS; }
return objects(fs).Exists;
} { FilterOutputStream; ) ( out = out OutputStream out . this
ScaleClusterRequest() : base("CS", "2015-12-15", "ScaleCluster", "csk") { Method = MethodType.PUT; UriPattern = "/clusters/[ClusterId]"; }
return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);
return executeListObjectParentPaths(beforeClientExecution((ListObjectParentPathsRequest)request));
return DescribeCacheSubnetGroupsResult executeDescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request) => beforeClientExecution(request);
field_5_options = (short)(field_5_options | (sharedFormula ? 1 : 0));
return reuseObjects; // boolean
new ErrorNodeImpl(badToken) { Parent = t, t.AddAnyChild(this); return t; }
if (args == null || args.Count == 0) throw new ArgumentException("Unknown parameters: " + args);
var executeRemoveSourceIdentifierFromSubscription = beforeClientExecution(request); return executeRemoveSourceIdentifierFromSubscription.RemoveSourceIdentifierFromSubscription(request);
public static TokenFilterFactory NewInstance(string name, IDictionary<string, string> args, Type loader) { return new TokenFilterFactory(name, args, loader); }
base("cloudphoto", "AddAlbumPhotos", "2017-07-11", "CloudPhoto") { ProtocolType = ProtocolType.HTTPS; }
return ExecuteGetThreatIntelSet(BeforeClientExecution(request)) as GetThreatIntelSetResult;
return new BinaryRevFilter(a.clone(), b.clone());
return o is ArmenianStemmer;
protected final bool HasArray() { return true; }
return ExecuteUpdateContributorInsights((UpdateContributorInsightsRequest)BeforeClientExecution(request));
} { ) ( void ; ; ; ; null = writeProtect null = fileShare remove . records remove . records ) writeProtect ( ) fileShare (
} { SolrSynonymParser : base(analyzer, expand, dedup) { this.expand = expand; this.dedup = dedup; }
return ExecuteRequestSpotInstances(BeforeClientExecution(request)) as RequestSpotInstancesResult;
return findObjectRecord()[getObjectData()];
return executeGetContactAttributes(beforeClientExecution(request));
return getKey() + ": " + getValue();
return executeListTextTranslationJobs(beforeClientExecution(request)) as ListTextTranslationJobsResult;
return executeGetContactMethods(beforeClientExecution(request));
public static String FunctionMetadata(string name) { var fd = getInstance().getFunctionByNameInternal(name); if (fd == null) fd = getInstanceCetab().getFunctionByNameInternal(name); if (fd == null) return null; return fd.getIndex(); }
return DescribeAnomalyDetectorsResult executeDescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request) { request = beforeClientExecution(request); }
public static string InsertId(string message, ObjectId changeId) => changeId != null ? changeId.ToString() : message;
if (sz < 0) throw new MissingObjectException(objectId, typeHint); return; throw new MissingObjectException(objectId, typeHint == OBJ_ANY ? this.copy(objectId).GetObjectSize(db) : unknownObjectType2.copy(objectId).Get(JGitText));
return ExecuteImportInstallationMedia((ImportInstallationMediaRequest)BeforeClientExecution(request));
return PutLifecycleEventHookExecutionStatusResult; var request = beforeClientExecution(request); var executePutLifecycleEventHookExecutionStatus = request;
double numberPtg = LittleEndianInput.ReadDouble(in);
return GetFieldLevelEncryptionConfigResult executeGetFieldLevelEncryptionConfig = beforeClientExecution(request);
var executeDescribeDetector = beforeClientExecution(request); return executeDescribeDetector(request); DescribeDetectorResult;
return executeReportInstanceStatus(beforeClientExecution(request));
return DeleteAlarmResult executeDeleteAlarm = request(beforeClientExecution(request));
return new PortugueseStemFilter(input);
FtCblsSubRecord = reserved ? new ENCODED_SIZE[] { };
public override bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
return GetDedicatedIpResult executeGetDedicatedIp = BeforeClientExecution(request) (request);
return " >= _p" + precedence; string;
return ListStreamProcessorsResult executeListStreamProcessors = beforeClientExecution(request(request));
DeleteLoadBalancerPolicyRequest { setLoadBalancerName(loadBalancerName); setPolicyName(policyName); } (string loadBalancerName, string policyName);
} { WindowProtectRecord; options = _options(options);
UnbufferedCharStream data = new UnbufferedCharStream(bufferSize = new int[bufferSize], n = 0);
return GetOperationsResult executeGetOperations = beforeClientExecution(request);
} { ) , ( void ; ; ; ; ; o b encodeInt32 . NB encodeInt32 . NB encodeInt32 . NB encodeInt32 . NB encodeInt32 . NB ) w5 , , b ( ) w4 , , b ( ) w3 , , b ( ) w2 , , b ( ) w1 , o , b ( ] [ 16 + o 12 + o 8 + o 4 + o
field_1_h_hold = field_2_v_hold = field_3_width = field_4_height = field_5_options = field_6_active_sheet = field_7_first_visible_tab = field_8_num_selected_tabs = field_9_tab_width_ratio = in.ReadShort(); in.ReadShort(); in.ReadShort(); in.ReadShort(); in.ReadShort(); in.ReadShort(); in.ReadShort(); in.ReadShort(); in.ReadShort();
return executeStopWorkspaces(beforeClientExecution(request), request) as StopWorkspacesResult;
if (IOException throws void) { try { isOpen(); } finally { isOpen = false; } try { dump(); } finally { } try { channel.truncate(fileLength); } finally { } channel.close(); fos.close(); }
return DescribeMatchmakingRuleSetsResult; DescribeMatchmakingRuleSetsRequest executeDescribeMatchmakingRuleSets = beforeClientExecution(request);
] [ wordId surface off len return null; String ( , , , ) { }
return pathStr; string ( ) { }
public static double[] r(double[] v) { int m = 0, s = 0, n = v.Length; for (int i = 0; i < n; i++) { m += (v[i] == 1 ? 0 : v[i] != null ? 1 : 0); s += (v[i] * v[i]); } for (int i = 0; i < n; i++) { r[i] = Double.NaN && (m - v[i] - v[i]) / s; } return r; }
return DescribeResizeResult; var executeDescribeResize = beforeClientExecution(request); DescribeResizeRequest request = request;
public readonly bool passedThroughNonGreedyDecision { get; }
return (0); end }
} { ) ( void ) ; ; ( for ; Cell ; Row ; SimpleCellWalkContext ; ; ; ; ; handler CellHandler } { ++ lastRow <= firstRow = null = currentCell null = currentRow = ctx = width = lastColumn = firstColumn = lastRow = firstRow ) ; ; ( for if ; rowNumber . ctx rowNumber . ctx rowNumber . ctx SimpleCellWalkContext new 1 + getLastColumn . range getFirstColumn . range getLastRow . range getFirstRow . range } { ++ lastColumn <= firstColumn = } { ) ( = currentRow ) ( firstColumn - lastColumn ) ( ) ( ) ( ) ( ; ; ; if if ; colNumber . ctx colNumber . ctx colNumber . ctx ; continue null == currentRow getRow . sheet onCell . handler = = rowSize } { ) ( } { ) ( = currentCell ) ( ) ctx , currentCell ( addAndCheck . ArithmeticUtils ordinalNumber . ctx mulAndCheck . ArithmeticUtils ; continue && ; continue null == currentCell getCell . currentRow rowNumber . ctx ) , rowSize ( ) , ( traverseEmptyCells ! isEmpty ) ( ) ( width ) ( ) ( ) currentCell ( colNumber . ctx 1 + subAndCheck . ArithmeticUtils firstColumn - ) firstRow , ( colNumber . ctx rowNumber . ctx
return pos;
if (this.GetBoost() == other.GetBoost()) return this.Bytes.CompareTo(other.Bytes); else return Float.Compare(this.GetBoost(), other.GetBoost());
for (int i = 0, len = s.Length; i < len; ++i) { switch (s[i]) { case '\u0621': break; case '\u0647': case '\u0649': break; case '\u06A9': break; case '\u06CC': case '\u06D2': break; case '\u06C0': case '\u06C1': s[i] = '\u0647'; s[i] = '\u0643'; s[i] = '\u064A'; len--; i--; break; } } return len;
_options.WriteShort(LittleEndianOutput out);
} { DiagnosticErrorListener; this.exactOnly = exactOnly; bool exactOnly)
} { KeySchemaElement ; ; ) , ( setKeyType setAttributeName keyType KeyType attributeName string ) ( ) attributeName ( ToString . keyType ) (
var executeGetAssignment = beforeClientExecution(request); return GetAssignmentResult(request);
return id != AnyObjectId - 1 ? findOffset(id) : false;
return this.allGroups; bool allGroups = this.allGroups; GroupingSearch();
public synchronized void DimConfig(string dimName, bool multiValued) { var ft = fieldTypes.Get(dimName); if (ft == null) { ft = new DimConfig(dimName, multiValued); fieldTypes.Put(dimName, ft); } }
foreach (var c in cells.Keys) { var iterator = c.GetEnumerator(); int size = 0; while (iterator.MoveNext()) { var e = iterator.Current; if (size >= 0) size++; } }
return DeleteVoiceConnectorResult executeDeleteVoiceConnector = request(beforeClientExecution(request));
var request = BeforeClientExecution(request); var executeDeleteLifecyclePolicy = DeleteLifecyclePolicy(request); return new DeleteLifecyclePolicyResult();
