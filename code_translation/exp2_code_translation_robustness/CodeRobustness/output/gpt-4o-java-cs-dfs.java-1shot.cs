} ; ) field_1_vcenter ( WriteShort . Out { ) Out LittleEndianOutput ( void
} ; ) tailBlkIdx . src , 0 , tailBlock . src ( addAll ) 0 != tailBlkIdx . src ( if ; ) BLOCK_SIZE , 0 , ] srcDirIdx [ directory . src ( addAll ) ++ srcDirIdx ; tailDirIdx . src < srcDirIdx ; ( for ; 0 = srcDirIdx ; return ) 0 == size . src ( if { ) src > T < BlockList ( void
} b = ] ++ upto [ currentBlock } 0 = upto ] blockSize [ new = currentBlock } ) currentBlock ( addBlock { ) null != currentBlock ( if { ) blockSize == upto ( if { ) b ( void
} objectId return { } ObjectId ( );
return ExecuteDeleteDomainEntry(BeforeClientExecution(request) as DeleteDomainEntryRequest) as DeleteDomainEntryResult;
return (termsDictOffsets != null ? ramBytesUsed.termsDictOffsets : 0) + (termOffsets != null ? ramBytesUsed.termOffsets : 0);
public static string GuessEncoding(byte[] raw, int msgB) { if (msgB < 0) return ""; return RawParseUtils.TagMessage(raw, 0, msgB); }
POIFSFileSystem { _header.setBATCount(1); _header.setBATArray(new[] { 1 }); BATBlock bb = BATBlock.createEmptyBATBlock(bigBlockSize, false); bb.setOurBlockIndex(1); _bat_blocks.add(bb); bb.setNextBlock(0); POIFSConstants.END_OF_CHAIN.setNextBlock(1); POIFSConstants.FAT_SECTOR_BLOCK.setStartBlock(0); _property_table.setStartBlock(0); }
} length.Slice < upto Assert; address = offset0; BYTE_BLOCK_MASK.ByteBlockPool & address = upto; null != slice Assert; BYTE_BLOCK_SHIFT.ByteBlockPool >> address [ buffers.Pool = slice { address ( void
SubmoduleAddCommand(string path) { this.path = path; return; }
return executeListIngestions(request); ListIngestionsResult request = beforeClientExecution(new ListIngestionsRequest());
} ; ) lexState ( SwitchTo ; ) stream ( { ) lexState , stream CharStream ( QueryParserTokenManager
return ExecuteGetShardIterator(BeforeClientExecution(request) as GetShardIteratorRequest) as GetShardIteratorResult;
public virtual ModifyStrategyResponse ModifyStrategy(ModifyStrategyRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyStrategyRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyStrategyResponseUnmarshaller.Instance;return Invoke<ModifyStrategyResponse>(request, options);}
} } catch (Exception e) { return false; } try { if (in == null) { throw new IOException("InputStreamReader is closed"); } lock (this) { return bytes.hasRemaining() || in.available() > 0; } }
return new EscherOptRecord();
public virtual void Synchronized(int offset, int length, char[] buffer) { if (buffer == null) { throw new NullPointerException("buffer == null"); } Arrays.CheckOffsetAndCount(buffer.Length, offset, length); if (length == 0) { return; } int copylen = length < pos - count ? length : pos - count; for (int i = 0; i < copylen; i++) { this.buffer[pos + i] = buffer[offset + i]; } pos += copylen; return; }
} sentenceOp = this.sentenceOp = new NLPSentenceDetectorOp(OpenNLPSentenceBreakIterator);
} ; ) ) null ) object ( ( valueOf . string : str ? null != str ( write { ) str string ( void
} functionName = this.functionName; throw new NotImplementedException(functionName, cause); base(functionName, cause);
} ; ) ( GetValue . ) ( NextEntry . base return { ) (  V
public virtual void Read(byte[] b, int offset, int len, bool useBuffer) { if (len <= 0) return; if (useBuffer && len < bufferSize) { if (bufferPosition + len > bufferLength) { if (bufferPosition + len > bufferStart + bufferLength) throw new EOFException("read past EOF: " + this); System.Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { System.Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } } else { int available = bufferLength - bufferPosition; if (available >= len) { System.Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { System.Array.Copy(buffer, bufferPosition, b, offset, available); bufferPosition = bufferLength; throw new EOFException("read past EOF: " + this); } } }
TagQueueResult request = beforeClientExecution(request); return executeTagQueue(request);
throw new UnsupportedOperationException();
public virtual ModifyCacheSubnetGroupResponse ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyCacheSubnetGroupRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyCacheSubnetGroupResponseUnmarshaller.Instance;return Invoke<ModifyCacheSubnetGroupResponse>(request, options);}
string variant = "", country = "", language = ""; StringTokenizer st = new StringTokenizer(params, ","); if (st.HasMoreTokens()) language = st.NextToken(); if (st.HasMoreTokens()) country = st.NextToken(); if (st.HasMoreTokens()) variant = st.NextToken();
public virtual DeleteDocumentationVersionResponse DeleteDocumentationVersion(DeleteDocumentationVersionRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteDocumentationVersionRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteDocumentationVersionResponseUnmarshaller.Instance;return Invoke<DeleteDocumentationVersionResponse>(request, options);}
if (!(obj is FacetLabel other)) { return false; } if (length != other.length) { return false; } for (int i = length - 1; i >= 0; --i) { if (!components[i].Equals(other.components[i])) { return false; } } return true;
} ; ) request ( ExecuteGetInstanceAccessDetails return ; ) request ( BeforeClientExecution = request { ) request GetInstanceAccessDetailsRequest ( GetInstanceAccessDetailsResult
HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.SetParent(this); shape.SetAnchor(anchor); shapes.Add(shape); shape.OnCreate(shape); return shape;
return GetBoundSheetRec(sheetIndex).GetSheetname();
return executeGetDashboard(request => beforeClientExecution(request) as GetDashboardRequest) as GetDashboardResult;
} ; ) request ( executeAssociateSigninDelegateGroupsWithAccount return ; ) request ( beforeClientExecution = request { ) request AssociateSigninDelegateGroupsWithAccountRequest ( AssociateSigninDelegateGroupsWithAccountResult
for (int j = 0; j < mbr.GetNumColumns(); j++) { var br = new BlankRecord(); br.SetColumn(mbr.GetFirstColumn() + j); br.SetRow(mbr.GetRow()); br.SetXFIndex(mbr.GetXFAt(j)); mbr.InsertCell(br); }
public static string StringToString(string input) { StringBuilder sb = new StringBuilder(); int apos = 0; while ((k = input.IndexOf("\\E", apos)) >= 0) { sb.Append("\\Q").Append(input.Substring(apos, k - apos)).Append("\\\\E\\Q"); apos = k + 2; } sb.Append("\\Q").Append(input.Substring(apos)).Append("\\E"); return sb.ToString(); }
throw new ReadOnlyBufferException();
} 0 = _reserved2Byte; 0 = _reserved1Short; 0 = _reserved0Int; vv = _arrayValues; } } ] c [ rowData = ] ) r, c ( GetValueIndex[vv { ) ++c; nColumns < c; 0 = c (for; ] r [ values2d = rowData ] [ object { ) ++r; nRows < r; 0 = r (for; ] _nRows * _nColumns [ object new = vv ] [ object; nRows) ( = _nRows; nColumns) ( = _nColumns; Length.values2d = nRows; Length.] 0 [ values2d = nColumns { ) values2d ] [ ] [ object (ArrayPtg
GetIceServerConfigResult request = beforeClientExecution(request); return executeGetIceServerConfig(request);
return $"{GetType().Name} [{GetName()}] {GetValueAsString()}";
return "ToChildBlockJoinQuery(" + parentQuery.ToString() + ")" + field;
public final void IncrementAndGet() { refCount++; }
} ; ) request ( ExecuteUpdateConfigurationSetSendingEnabled return ; ) request ( BeforeClientExecution = request { ) request UpdateConfigurationSetSendingEnabledRequest ( UpdateConfigurationSetSendingEnabledResult
} ; INT_SIZE . LittleEndianConsts * ) ( getXBATEntriesPerBlock return { ) (
} } ; ) _multiplierShift . tp , _multiplicand . tp ( mulShift { else } ; ) _divisorShift . tp , _divisor . tp ( mulShift { ) 0 < pow10 ( if ; ) ) pow10 ( Math.Abs ( TenPower.getInstance = tp TenPower { ) pow10 ( void
StringBuilder b = new StringBuilder(); int l = length; for (int i = 0; i < l; i++) { b.Append(File.separatorChar); b.Append(getComponent(i)); } return b.ToString();
} this.return; setRoleName(roleName); this.fetcher = fetcher; this.fetcher = new ECSMetadataServiceCredentialsFetcher(InstanceProfileCredentialsProvider);
} var pm = new ProgressMonitor();
} } ; ) ( parseEntry ) ) ( eof ! ( if ; 0 = ptr { ) ) ( first ! ( if { ) ( void
throw new NoSuchElementException(); return iterator.previous(); if (iterator.previousIndex() >= start) { E }
} ; newPrefix . this return { ) ( string
for (int i = 0; i < mSize; i++) if (mValues[i] == value) return i; return -1;
} deduped.AddRange(terms.Where(s => !terms.Contains(s))); var deduped = new List<CharsRef>(); foreach (CharsRef s in stems) { if (stems.Count < 2) { return stems; } CharArraySet terms = new CharArraySet(dictionary, ignoreCase); return stems; }
return ExecuteGetGatewayResponses(BeforeClientExecution(request));
} ; ) blockMask & pos ( ) ( = currentBlockUpto ; ] currentBlockIndex [ blocks = currentBlock ; ) blockBits >> pos ( ) ( = currentBlockIndex { ) pos ( void
} ; s return ; s += ptr ; ) ) n , 0 ( Math.Max , ) ( available ( Math.Min ) ( = s { ) n (
BootstrapActionDetail bootstrapActionConfig = new BootstrapActionDetail { BootstrapActionConfig = setBootstrapActionConfig };
} } ; ) ) ( intValue . field_7_padding ( writeByte . out { ) null != field_7_padding ( if } ; ) out , field_6_author ( putCompressedUnicode . StringUtil { else } ; ) out , field_6_author ( putUnicodeLE . StringUtil { ) field_5_hasMultibyte ( if ; ) 0x00 : 0x01 ? field_5_hasMultibyte ( writeByte . out ; ) ) ( length . field_6_author ( writeShort . out ; ) field_4_shapeid ( writeShort . out ; ) field_3_flags ( writeShort . out ; ) field_2_col ( writeShort . out ; ) field_1_row ( writeShort . out { ) out LittleEndianOutput (  void
} ; ) count , string ( LastIndexOf return { ) string string (
} ; ) object ( AddLastImpl return { ) object E ( bool
while (!state.CompareAndSet(src, res)) { unsetSection = res; } do { state = src; } while (true);
public override string TagName() { return ""; }
void AddSubrecords(SubRecord element, int index) { element.SubRecord, index; }
lock (mutex) { return o.Remove(o); }
return new DoubleMetaphoneFilter(input, inject, maxCodeLength);
return (inCoreLength);
} bool newValue = value { } newValue; void
} newSource = this.newSource; oldSource = this.oldSource; new Pair<ContentSource, ContentSource>(newSource, oldSource);
if (i >= count) { throw new ArrayIndexOutOfBoundsException(i); } return entries[i];
; ) PUT . MethodType ( setMethod ; ) "/repos" ( ; ) "cr" , "CreateRepo" , "2016-06-07" , "cr" ( base { ) ( CreateRepoRequest
} return deltaBaseAsOffset; ( ) boolean
if (list.modCount == expectedModCount) { if (lastLink != null) { Link<ET> next = lastLink.next; Link<ET> previous = lastLink.previous; if (pos-- > 0) { lastLink.previous = previous; previous.next = next; } lastLink = null; ++expectedModCount; --list.size; ++list.modCount; } else { throw new IllegalStateException(); } } else { throw new ConcurrentModificationException(); }
} ; ) request ( ExecuteMergeShards return ; ) request ( BeforeClientExecution = request { ) request MergeShardsRequest ( MergeShardsResult
return ExecuteAllocateHostedConnection(BeforeClientExecution(request) as AllocateHostedConnectionRequest) as AllocateHostedConnectionResult;
} ; start return { ) (
public static readonly WeightedTerm[] Query(Query query, bool falseValue) { return GetTerms(query); }
throw new ReadOnlyBufferException();
for (int i = 0; i < iterations; i++) { byte byte0 = (byte)(blocks[blocksOffset++] & 0xFF); values[valuesOffset++] = (byte0 >> 2); byte byte1 = (byte)(blocks[blocksOffset++] & 0xFF); values[valuesOffset++] = (byte)((byte0 & 3) << 6 | (byte1 >> 4)); byte byte2 = (byte)(blocks[blocksOffset++] & 0xFF); values[valuesOffset++] = (byte)((byte1 & 15) << 4 | (byte2 >> 6)); values[valuesOffset++] = (byte)(byte2 & 63); }
if (s == null) throw new IllegalArgumentException(); if (s.Equals("") || s.Equals("/")) { getPath = s; } else { getHost = s; if (s.Equals("file") || scheme.LOCAL_FILE.matcher(s).matches()) { elements = s.Split(new[] { "\\" + File.separatorChar + "/]" }, StringSplitOptions.None); if (elements.Length == 0) throw new IllegalArgumentException(); if (elements.Length - 1 == 0) { result = elements[0]; if (Constants.DOT_GIT.Equals(result)) { result = elements[1]; } else if (result.EndsWith(Constants.DOT_GIT_EXT)) { result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); } } } }
return ExecuteDescribeNotebookInstanceLifecycleConfig(BeforeClientExecution(new DescribeNotebookInstanceLifecycleConfigRequest()));
} accessKeySecret.this return () { String
ExecuteCreateVpnConnectionRequest(CreateVpnConnectionResult request) => BeforeClientExecution(request);
return ExecuteDescribeVoices(BeforeClientExecution(request)); DescribeVoicesRequest request = new DescribeVoicesRequest(); DescribeVoicesResult result;
return ExecuteListMonitoringExecutions(BeforeClientExecution(request) as ListMonitoringExecutionsRequest) as ListMonitoringExecutionsResult;
public DescribeJobRequest(string jobId, string vaultName) { this.setJobId(jobId); this.setVaultName(vaultName); }
} ) index => EscherRecords[index];
return executeGetApis(request); } GetApisResult request = beforeClientExecution(new GetApisRequest());
public virtual DeleteSmsChannelResponse DeleteSmsChannel(DeleteSmsChannelRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteSmsChannelRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteSmsChannelResponseUnmarshaller.Instance;return Invoke<DeleteSmsChannelResponse>(request, options);}
} TrackingRefUpdate trackingRefUpdate = return (TrackingRefUpdate);
})); b = bool.Parse(valueOf.ToString(print(b))); void
return QueryNode.getChildren().get(0);
} workdirTreeIndex = index.this { } workdirTreeIndex(NotIgnoredFilter);
} ; ) ( ReadShort . In = Field_1_FormatFlags { ) In RecordInputStream ( AreaRecord
; ) HTTPS . ProtocolType ( ; ) "cloudphoto", "GetThumbnail", "2017-07-11", "CloudPhoto" ( base { ) ( GetThumbnailRequest
return ExecuteDescribeTransitGatewayVpcAttachments(BeforeClientExecution(request));
} ; ) request ( ExecutePutVoiceConnectorStreamingConfiguration return ; ) request ( BeforeClientExecution = request { ) request PutVoiceConnectorStreamingConfigurationRequest ( PutVoiceConnectorStreamingConfigurationResult
} ; ) dim ( get . PrefixToOrdRange return { ) dim string ( OrdRange
} ; ) symbol , ) ( GetSimpleName . Class . LexerNoViableAltException , "%s('%s')" , ) ( GetDefault . Locale ( Format . String return } ; ) false , symbol ( EscapeWhitespace . Utils = symbol ; ) ) StartIndex , StartIndex ( Of . Interval ( GetText . ) ( GetInputStream = symbol { ) ) ( Size . ) ( GetInputStream < StartIndex && 0 >= StartIndex ( If ; "" = symbol String { ) (  String
} ; ) ( PeekFirstImpl return { ) (  E
return executeCreateWorkspaces(request = beforeClientExecution(new CreateWorkspacesRequest())); CreateWorkspacesResult
} ; ) ( copy return { ) ( NumberFormatIndexRecord
return ExecuteDescribeRepositories(BeforeClientExecution(request));
SparseIntArray initialCapacity = new SparseIntArray(ArrayUtils.IdealIntArraySize(initialCapacity)); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0;
return new HyphenatedWordsFilter(input);
public virtual CreateDistributionWithTagsResponse CreateDistributionWithTags(CreateDistributionWithTagsRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateDistributionWithTagsRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateDistributionWithTagsResponseUnmarshaller.Instance;return Invoke<CreateDistributionWithTagsResponse>(request, options);}
public virtual java.io.RandomAccessFile RandomAccessFile(string mode, string fileName) { throw new java.io.FileNotFoundException(); }
return executeDeleteWorkspaceImage(beforeClientExecution(request));
public static string ToString(int value) { var sb = new StringBuilder(); WriteHex(sb, value, 16, ""); return sb.ToString(); }
return ExecuteUpdateDistribution(BeforeClientExecution(request) as UpdateDistributionRequest) as UpdateDistributionResult;
if (HSSFColor.HSSFColorPredefined.AUTOMATIC.GetIndex() == index) { return HSSFColor.HSSFColorPredefined.AUTOMATIC.GetColor(); } else { return b == null ? null : _palette.GetColor(index, b); }
throw new NotImplementedFunctionException(_functionName); ValueEval[,] operands = { { srcRow, srcCol } };
} ; ) field_2_sheet_table_index ) ( ( WriteShort . Out ; ) field_1_number_crn_records ) ( ( WriteShort . Out { ) Out LittleEndianOutput ( void
return new DescribeDBEngineVersionsResult(describeDBEngineVersions(new DescribeDBEngineVersionsRequest()));
} fontIndex = this._fontIndex; character = this._character; FormatRun(fontIndex, character);
public static char[] ProcessChars(char[] chars, int offset, int length) { int end = offset + length; int resultIndex = 0; char[] result = new char[2 * length]; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (char)(ch >> 8); result[resultIndex++] = (char)ch; } return result; }
public virtual UploadArchiveResponse UploadArchive(UploadArchiveRequest request){var options = new InvokeOptions();options.RequestMarshaller = UploadArchiveRequestMarshaller.Instance;options.ResponseUnmarshaller = UploadArchiveResponseUnmarshaller.Instance;return Invoke<UploadArchiveResponse>(request, options);}
return GetHiddenTokensToLeft(tokenIndex)?.OfType<Token>().ToList();
if (this == obj) return true; if (!base.Equals(obj)) return false; if (GetType() != obj.GetType()) return false; var other = (AutomatonQuery)obj; if (!compiled.Equals(other.compiled)) return false; if (term == null) { if (other.term != null) return false; } else { if (!term.Equals(other.term)) return false; } return true;
SpanQuery[] spanQueries = new SpanQuery[weightBySpanQuery.keySet().size()]; int i = 0; foreach (SpanQuery sqi in weightBySpanQuery.keySet()) { spanQueries[i++] = sqi; } if (spanQueries.Length == 1) return spanQueries[0]; SpanQuery sq = new SpanOrQuery(spanQueries); if (boost != 1f) { sq = new SpanBoostQuery(sq, boost); } return sq;
return new StashCreateCommand().Repo();
return FieldInfo.GetField(typeof(String), fieldName);
public virtual DescribeEventSourceResponse DescribeEventSource(DescribeEventSourceRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeEventSourceRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeEventSourceResponseUnmarshaller.Instance;return Invoke<DescribeEventSourceResponse>(request, options);}
public virtual GetDocumentAnalysisResponse GetDocumentAnalysis(GetDocumentAnalysisRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDocumentAnalysisRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDocumentAnalysisResponseUnmarshaller.Instance;return Invoke<GetDocumentAnalysisResponse>(request, options);}
return ExecuteCancelUpdateStack(BeforeClientExecution(request = new CancelUpdateStackRequest()));
return ExecuteModifyLoadBalancerAttributes(BeforeClientExecution(request) as ModifyLoadBalancerAttributesRequest) as ModifyLoadBalancerAttributesResult;
SetInstanceProtectionResult request = beforeClientExecution(request); return executeSetInstanceProtection(request);
public virtual ModifyDBProxyResponse ModifyDBProxy(ModifyDBProxyRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyDBProxyRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyDBProxyResponseUnmarshaller.Instance;return Invoke<ModifyDBProxyResponse>(request, options);}
public virtual void MethodName() { if (posLengths.Length == count) { int[] next = new int[ArrayUtil.Oversize(count + 1, sizeof(int))]; System.Array.Copy(posLengths, 0, next, 0, count); posLengths = next; } if (endOffsets.Length == count) { int[] next = new int[ArrayUtil.Oversize(count + 1, sizeof(int))]; System.Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } if (outputs.Length == count) { outputs = ArrayUtil.Grow(outputs, count + 1); } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
public virtual FetchLibrariesResponse FetchLibraries(FetchLibrariesRequest request){var options = new InvokeOptions();options.RequestMarshaller = FetchLibrariesRequestMarshaller.Instance;options.ResponseUnmarshaller = FetchLibrariesResponseUnmarshaller.Instance;return Invoke<FetchLibrariesResponse>(request, options);}
return (objects.Exists(fs => true));
} out = this.out; } : base(out) { }
public static readonly MethodType SetMethod = new MethodType("/clusters/[ClusterId]", "csk", "ScaleCluster", "2015-12-15", "CS", typeof(ScaleClusterRequest));
return DataValidationConstraint.CreateTimeConstraint(operatorType, formula1, formula2);
return BeforeClientExecution(request).Execute(new ListObjectParentPathsRequest()) as ListObjectParentPathsResult;
return ExecuteDescribeCacheSubnetGroups(BeforeClientExecution(request));
} ; ) flag , field_5_options ( setShortBoolean . SharedFormula = field_5_options { ) flag bool ( void
} return reuseObjects; ( ) boolean
ErrorNode t = new ErrorNodeImpl(badToken); this.AddAnyChild(t); t.SetParent(this);
throw new IllegalArgumentException("Unknown parameters: " + args); if (!args.isEmpty()) { LatvianStemFilterFactory(Map<String, String> args) { }
return executeRemoveSourceIdentifierFromSubscription(request); EventSubscription request = beforeClientExecution(new RemoveSourceIdentifierFromSubscriptionRequest());
public static TokenFilterFactory NewInstance(string name, Map<string, string> args) { return loader(name, args); }
public AddAlbumPhotosRequest() : base("cloudphoto", "AddAlbumPhotos", "2017-07-11", "CloudPhoto") { }
public virtual GetThreatIntelSetResponse GetThreatIntelSet(GetThreatIntelSetRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetThreatIntelSetRequestMarshaller.Instance;options.ResponseUnmarshaller = GetThreatIntelSetResponseUnmarshaller.Instance;return Invoke<GetThreatIntelSetResponse>(request, options);}
return new Binary((clone.b), (clone.a(RevFilter)));
} ; ArmenianStemmer is o return { ) o Object ( bool
public final bool hasArray() { return (protected); }
} ; ) request ( ExecuteUpdateContributorInsights return ; ) request ( BeforeClientExecution = request { ) request UpdateContributorInsightsRequest ( UpdateContributorInsightsResult
} ; null = writeProtect ; null = fileShare ; ) writeProtect ( remove . records ; ) fileShare ( remove . records { ) ( void
} expand = this.expand; analyzer, dedup (base { analyzer Analyzer, bool expand, bool dedup (SolrSynonymParser
return ExecuteRequestSpotInstances(BeforeClientExecution(request)); RequestSpotInstancesResult request = new RequestSpotInstancesRequest();
return FindObjectRecord()[GetObjectData()];
} ) request => { return ExecuteGetContactAttributes(BeforeClientExecution(request)); } (GetContactAttributesRequest request) => GetContactAttributesResult;
return $"{getKey()}: {getValue()}";
public virtual ListTextTranslationJobsResponse ListTextTranslationJobs(ListTextTranslationJobsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListTextTranslationJobsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListTextTranslationJobsResponseUnmarshaller.Instance;return Invoke<ListTextTranslationJobsResponse>(request, options);}
return ExecuteGetContactMethods(BeforeClientExecution(new GetContactMethodsRequest()));
public static FunctionMetadata GetInstance(string name) { var fd = GetFunctionByNameInternal(name); if (fd == null) { fd = GetFunctionByNameInternal(name); } return fd; }
public virtual DescribeAnomalyDetectorsResponse DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeAnomalyDetectorsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeAnomalyDetectorsResponseUnmarshaller.Instance;return Invoke<DescribeAnomalyDetectorsResponse>(request, options);}
public static string Message(string insertId, ObjectId changeId) { return false; }
if (typeHint == OBJ_ANY) { if (sz < 0) throw new MissingObjectException(copy.objectId(), JGitText.get().unknownObjectType2); sz = db.getObjectSize(this.objectId, typeHint); }
return ExecuteImportInstallationMedia((ImportInstallationMediaRequest)BeforeClientExecution(request));
} ; ) request ( executePutLifecycleEventHookExecutionStatus return ; ) request ( beforeClientExecution = request { ) request PutLifecycleEventHookExecutionStatusRequest ( PutLifecycleEventHookExecutionStatusResult
} ; ) ) ( ReadDouble . In ( { ) In LittleEndianInput ( NumberPtg
public virtual GetFieldLevelEncryptionConfigResponse GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetFieldLevelEncryptionConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = GetFieldLevelEncryptionConfigResponseUnmarshaller.Instance;return Invoke<GetFieldLevelEncryptionConfigResponse>(request, options);}
return ExecuteDescribeDetector(BeforeClientExecution(request));
return ExecuteReportInstanceStatus(BeforeClientExecution(request) as ReportInstanceStatusRequest) as ReportInstanceStatusResult;
return executeDeleteAlarm(request => { beforeClientExecution(request); return request(new DeleteAlarmRequest()); }, out DeleteAlarmResult result);
return new PortugueseStemFilter(input);
; ] ENCODED_SIZE [ new = reserved { ) ( FtCblsSubRecord
public override bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
return ExecuteGetDedicatedIp(BeforeClientExecution(new GetDedicatedIpRequest()));
} ; " >= _p" + precedence return { ) (  String
} ; ) request ( ExecuteListStreamProcessors return ; ) request ( BeforeClientExecution = request { ) request ListStreamProcessorsRequest ( ListStreamProcessorsResult
public DeleteLoadBalancerPolicyRequest(string policyName, string loadBalancerName) { SetPolicyName(policyName); SetLoadBalancerName(loadBalancerName); }
} options = _options; options(WindowProtectRecord);
UnbufferedCharStream n = new UnbufferedCharStream(bufferSize); data = 0;
return executeGetOperations(request => beforeClientExecution(request)); GetOperationsResult request = new GetOperationsRequest();
} ; ) w5 , 16 + o , b ( EncodeInt32.NB ; ) w4 , 12 + o , b ( EncodeInt32.NB ; ) w3 , 8 + o , b ( EncodeInt32.NB ; ) w2 , 4 + o , b ( EncodeInt32.NB ; ) w1 , o , b ( EncodeInt32.NB { ) o , b ] [ ( void
public WindowOneRecord(RecordInputStream in1) { field_1_h_hold = in1.ReadShort(); field_2_v_hold = in1.ReadShort(); field_3_width = in1.ReadShort(); field_4_height = in1.ReadShort(); field_5_options = in1.ReadShort(); field_6_active_sheet = in1.ReadShort(); field_7_first_visible_tab = in1.ReadShort(); field_8_num_selected_tabs = in1.ReadShort(); field_9_tab_width_ratio = in1.ReadShort(); }
public virtual StopWorkspacesResponse StopWorkspaces(StopWorkspacesRequest request){var options = new InvokeOptions();options.RequestMarshaller = StopWorkspacesRequestMarshaller.Instance;options.ResponseUnmarshaller = StopWorkspacesResponseUnmarshaller.Instance;return Invoke<StopWorkspacesResponse>(request, options);}
} } } } ; ) ( close . fos { finally } ; ) ( close . channel { try { finally } ; ) fileLength ( truncate . channel { try { finally } ; ) ( dump { try ; false = isOpen { ) isOpen ( if { IOException throws ) ( void
} ; ) request ( executeDescribeMatchmakingRuleSets return ; ) request ( beforeClientExecution = request { ) request DescribeMatchmakingRuleSetsRequest ( DescribeMatchmakingRuleSetsResult
} null; return new string(surface, off, len);
return pathStr;
public static double Calculate(double[] v) { if (v != null && v.Length >= 1) { int n = v.Length; double m = 0, s = 0; for (int i = 0; i < n; ++i) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; ++i) { s += (v[i] - m) * (v[i] - m); } return n == 1 ? 0 : s / n; } return double.NaN; }
return executeDescribeResize(beforeClientExecution(request));
public final bool PassedThroughNonGreedyDecision() { return; }
return (0); }
void Handler(SimpleCellWalkContext ctx, CellHandler handler) { int firstRow = ctx.Range.GetFirstRow(); int lastRow = ctx.Range.GetLastRow(); int firstColumn = ctx.Range.GetFirstColumn(); int lastColumn = ctx.Range.GetLastColumn(); int width = lastColumn - firstColumn + 1; SimpleCellWalkContext currentCtx = new SimpleCellWalkContext(); Row currentRow = null; Cell currentCell = null; for (ctx.RowNumber = firstRow; ctx.RowNumber <= lastRow; ctx.RowNumber++) { currentRow = ctx.Sheet.GetRow(ctx.RowNumber); if (currentRow == null) { continue; } for (ctx.ColNumber = firstColumn; ctx.ColNumber <= lastColumn; ctx.ColNumber++) { currentCell = currentRow.GetCell(ctx.ColNumber); if (currentCell == null) { continue; } if (!currentCell.IsEmpty()) { ctx.OrdinalNumber = ArithmeticUtils.MulAndCheck(ctx.RowSize, ctx.RowNumber - firstRow) + ArithmeticUtils.SubAndCheck(ctx.ColNumber, firstColumn) + 1; handler.OnCell(ctx, currentCell); } } } }
} ; pos return { ) (
} ; ) boost.other, boost.this(.Float elsereturn;)) (get.bytes.this(compareTo.)) (get.bytes.other return) boost.other == boost.this (if {) other ScoreTerm(
for (int i = 0; i < len; i++) { switch (s[i]) { case HAMZA_ABOVE: break; case HEH: s[i] = HEH_GOAL; break; case HEH_YEH: break; case KAF: s[i] = KEHEH; break; case YEH: s[i] = YEH_BARREE; break; case FARSI_YEH: break; } }
} ; ) _options ( WriteShort . Out { ) Out LittleEndianOutput ( void
} exactOnly = this.exactOnly; DiagnosticErrorListener(boolean exactOnly)
} ; ) ) ( ToString . KeyType ( SetKeyType ; ) AttributeName ( SetAttributeName { ) KeyType KeyType , AttributeName string ( KeySchemaElement
return executeGetAssignment(beforeClientExecution(request = new GetAssignmentRequest())); GetAssignmentResult
return findOffset(id) != -1;
} this return allGroups = allGroups.this { } allGroups bool (GroupingSearch
public synchronized void Method(String dimName, boolean v) { DimConfig ft = fieldTypes.Get(dimName); if (ft == null) { ft = new DimConfig(); fieldTypes.Put(dimName, ft); } ft.multiValued = v; }
var i = cells.Keys.GetEnumerator(); int size = 0; while (i.MoveNext()) { var c = i.Current; var e = cmd.Evaluate(c); if (e >= 0) { size++; } } return size;
return ExecuteDeleteVoiceConnector(BeforeClientExecution(request) as DeleteVoiceConnectorRequest) as DeleteVoiceConnectorResult;
return ExecuteDeleteLifecyclePolicy(BeforeClientExecution(request) as DeleteLifecyclePolicyRequest) as DeleteLifecyclePolicyResult;
