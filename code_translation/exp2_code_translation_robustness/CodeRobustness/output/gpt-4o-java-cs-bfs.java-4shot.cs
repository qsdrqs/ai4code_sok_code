public void WriteShort(LittleEndianOutput out) { out.WriteShort(field_1_vcenter); }
if (srcDirIdx == 0 && src.tailDirIdx != 0) { for (int srcDirIdx = 0; srcDirIdx < src.directory.Length; ++srcDirIdx) { src.tailBlock.AddAll(src.directory[srcDirIdx], 0, src.tailBlkIdx.size, 0, BLOCK_SIZE); } return; }
if (currentBlock != null) { AddBlock(currentBlock = new byte[blockSize]); upto = 0; } if (upto == blockSize) { currentBlock = new byte[blockSize]; AddBlock(currentBlock); upto = 0; } upto++;
return objectId; ObjectId();
return executeDeleteDomainEntry(new DeleteDomainEntryRequest(beforeClientExecution(request))); DeleteDomainEntryResult;
return (termOffsets != null ? ramBytesUsed.TermOffsets() : 0) + (termsDictOffsets != null ? ramBytesUsed.TermsDictOffsets() : 0);
public string Decode() { if (RawParseUtils.GuessEncoding(raw, 0, raw.Length) < 0) return ""; var msgB = RawParseUtils.TagMessage(raw, msgB, raw); return msgB; }
BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); _property_table.SetStartBlock(POIFSConstants.END_OF_CHAIN); _header.SetBATCount(1); _header.SetBATArray(new int[] { 0 }); _bat_blocks.Add(bb); bb.SetOurBlockIndex(0); SetNextBlock(0, POIFSConstants.FAT_SECTOR_BLOCK); SetNextBlock(1, POIFSConstants.END_OF_CHAIN);
} { ) ( void ; Debug.Assert ; ; ; Debug.Assert ; address < upto address = offset0 = upto null != slice = slice length . slice & address ] [ BYTE_BLOCK_MASK . ByteBlockPool >> address buffers . pool BYTE_BLOCK_SHIFT . ByteBlockPool
return this.SubmoduleAddCommand(path: this.path);
return executeListIngestions(beforeClientExecution(request) as ListIngestionsRequest) as ListIngestionsResult;
} { QueryParserTokenManager ; ; ) , ( SwitchTo ) stream ( lexState stream CharStream ) lexState (
public virtual GetShardIteratorResponse GetShardIterator(GetShardIteratorRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetShardIteratorRequestMarshaller.Instance;options.ResponseUnmarshaller = GetShardIteratorResponseUnmarshaller.Instance;return Invoke<GetShardIteratorResponse>(request, options);}
base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis") { MethodType = HttpMethod.Post; }
lock (this) { if (in == null) throw new IOException("InputStreamReader is closed"); if (in.available() > 0 || bytes.hasRemaining()) return false; throw e; }
return _optRecord as EscherOptRecord;
public int CopyLen { get { lock (buffer) { if (buffer == null) throw new NullReferenceException("buffer == null"); if (length == 0) return 0; Arrays.CheckOffsetAndCount(buffer.Length, offset, length); int copylen = 0; for (int i = 0; i < length; ++i) { copylen += buffer[offset + i] == buffer[pos - count + i] ? 1 : 0; } return copylen; } } }
public OpenNLPSentenceBreakIterator() { this.sentenceOp = new NLPSentenceDetectorOp(); }
public virtual void Write(object str){string value = str != null ? str.ToString() : null;}
throw new NotImplementedException(functionName, cause);
return base.NextEntry().GetValue();
public void Read(byte[] b, int offset, int len) { if (len <= 0) return; if (bufferPosition >= bufferLength) { if (!RefillBuffer()) throw new EOFException("read past EOF: " + this); } int available = bufferLength - bufferPosition; if (len <= available) { Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { if (available > 0) { Array.Copy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (len < bufferSize) { if (!RefillBuffer()) throw new EOFException("read past EOF: " + this); if (bufferLength < len) { Array.Copy(buffer, 0, b, offset, bufferLength); throw new EOFException("read past EOF: " + this); } else { Array.Copy(buffer, 0, b, offset, len); bufferPosition = len; } } else { bufferStart += bufferPosition; ReadInternal(b, offset, len); bufferStart += len; bufferPosition = 0; bufferLength = 0; } } }
public virtual TagQueueResponse TagQueue(TagQueueRequest request){request = beforeClientExecution(request);return executeTagQueue(request);}
throw new NotSupportedException();
public virtual CacheSubnetGroup ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request){request = beforeClientExecution(request);return executeModifyCacheSubnetGroup(request);}
string[] params = { }; StringTokenizer st = new StringTokenizer(params, ","); if (st.HasMoreTokens()) language = st.NextToken(); if (st.HasMoreTokens()) country = st.NextToken(); if (st.HasMoreTokens()) variant = st.NextToken();
public virtual DeleteDocumentationVersionResponse DeleteDocumentationVersion(DeleteDocumentationVersionRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteDocumentationVersionRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteDocumentationVersionResponseUnmarshaller.Instance;return Invoke<DeleteDocumentationVersionResponse>(request, options);}
public override bool Equals(object obj){if (obj is FacetLabel other){if (length != other.length) return false;for (int i = length - 1; i >= 0; i--){if (!components[i].Equals(other.components[i])) return false;}return true;}return false;}
return executeGetInstanceAccessDetails((GetInstanceAccessDetailsRequest)beforeClientExecution(request)); GetInstanceAccessDetailsResult;
return (HSSFPolygon)(this.AddShape(new HSSFPolygon(this, anchor).SetParent(shape).SetAnchor(anchor).OnCreate(shape)));
return GetBoundSheetRec(sheetIndex).GetSheetname(sheetIndex);
return ExecuteGetDashboard((GetDashboardRequest)BeforeClientExecution(request));
public virtual AssociateSigninDelegateGroupsWithAccountResponse AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request){request = beforeClientExecution(request);return executeAssociateSigninDelegateGroupsWithAccount(request);}
for (int j = 0; j < mbr.getNumColumns(); ++j) { var br = new BlankRecord(); br.setColumn(mbr.getFirstColumn() + j); br.setRow(mbr.getRow()); br.setXFIndex(mbr.getXFAt(j)); MulBlankRecord.insertCell(br); }
public static string ToString(string input){StringBuilder sb = new StringBuilder();int apos = 0;while ((apos = input.IndexOf("\\E", apos)) >= 0){sb.Append(input.Substring(0, apos)).Append("\\\\E\\Q");input = input.Substring(apos + 2);apos = 0;}sb.Append("\\Q").Append(input).Append("\\E");return sb.ToString();}
throw new ReadOnlyBufferException();
} { ArrayPtg; ; ; ; ); (for; ; ; ; ; ) (0 = _reserved2Byte; 0 = _reserved1Short; 0 = _reserved0Int; vv = _arrayValues; } { ++r; nRows < r; = vv; object = _nRows = _nColumns = nRows = nColumns; values2d); (for; 0 = r; object new] [nRows) (nColumns) (length.values2d length.object} { ++c; nColumns < c; = rowData object] [] 0 [values2d] [] []; 0 = c] r [values2d] [_nRows * _nColumns =] c [rowData] [vv getValueIndex) r, c (
return executeGetIceServerConfig((GetIceServerConfigRequest)beforeClientExecution(request));
return "[" + GetType().Name + " " + GetName() + "] " + GetValueAsString();
return "ToChildBlockJoinQuery(" + parentQuery.ToString() + ")";
public virtual void IncrementAndGet() { refCount++; }
} { ) ( UpdateConfigurationSetSendingEnabledResult; return; request UpdateConfigurationSetSendingEnabledRequest executeUpdateConfigurationSetSendingEnabled = request ) request ( BeforeClientExecution ) request (
return LittleEndianConsts.INT_SIZE * GetXBATEntriesPerBlock();
if (pow10 < 0) { var tp = TenPower.GetInstance(pow10); _divisor = Math.Abs(tp._divisor); _divisorShift = tp._divisorShift; _multiplicand = tp._multiplicand; _multiplierShift = tp._multiplierShift; } else { }
public override string ToString(){StringBuilder b = new StringBuilder();for (int i = 0; i < l; i++){if (i > 0) b.Append(Path.DirectorySeparatorChar);b.Append(getComponent(i));}return b.ToString();}
this.fetcher = new ECSMetadataServiceCredentialsFetcher(roleName); return this;
ProgressMonitor pm = progressMonitor;
if (!eof && !parseEntry(first = 0, ptr)) { }
throw new NoSuchElementException(); return (iterator.PreviousIndex() >= start) ? iterator.Previous() : default;
return this.newPrefix as string;
} { ) ( ; return ) ; ( for value 1 - if ++ i mSize < i ; ; i return ) ( 0 = i value == ] i [ mValues
if (!dictionary.Contains(word, ignoreCase)) { List<CharsRef> stems = new List<CharsRef>(); List<CharsRef> deduped = new List<CharsRef>(); foreach (CharsRef s in terms) { if (!deduped.Contains(s)) { deduped.Add(s); } } return deduped; }
return ExecuteGetGatewayResponses((GetGatewayResponsesRequest)BeforeClientExecution(request)); GetGatewayResponsesResult;
} { ) ( void ; ; ; pos = currentBlockUpto = currentBlock = currentBlockIndex ) ( ] currentBlockIndex [ blocks ) ( ) ( ) ( blockMask & pos blockBits >> pos
return s += Math.Min(Math.Max(available, 0), n);
} { BootstrapActionDetail; ) ( SetBootstrapActionConfig(BootstrapActionConfig bootstrapActionConfig) bootstrapActionConfig(
if (field_5_hasMultibyte) { out.WriteByte(0x01); StringUtil.PutUnicodeLE(field_6_author, out); } else { out.WriteByte(0x00); StringUtil.PutCompressedUnicode(field_6_author, out); } out.WriteShort(field_1_row); out.WriteShort(field_2_col); out.WriteShort(field_3_flags); out.WriteShort(field_4_shapeid); if (field_7_padding != null) { out.WriteShort(field_7_padding.IntValue()); }
return string.LastIndexOf(string, count);
public bool AddLastImpl(object E){return object;}
} { ) , ( void ; while do ; , ConfigSnapshot subsection string section string ) ( } { ! ; ; CompareAndSet.State = res = src ) res , src ( UnsetSection Get.State ) subsection , section , src ( ) (
public virtual string TagName => tagName;
subrecords.Add(index, element);
lock (mutex) { return delegate.Remove(o); }
return new DoubleMetaphoneFilter(input, maxCodeLength, inject);
return (inCoreLength);
bool newValue = value;
} { Pair; ; ), (newSource = oldSource = newSource ContentSource oldSource ContentSource newSource.this oldSource.this
if (i >= count) throw new IndexOutOfRangeException(); return entries[i];
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr") { Method = MethodType.PUT; UriPattern = "/repos"; }
return deltaBaseAsOffset;
} else if (expectedModCount != list.modCount) { throw new ConcurrentModificationException(); } else if (lastLink == null) { throw new IllegalStateException(); } else { --expectedModCount; lastLink = null; previous = link.previous; link.previous = link.next; link.next = previous; if (link == list.lastLink) { list.lastLink = previous; } if (link == list.size) { list.size--; } }
return executeMergeShards(new MergeShardsRequest(beforeClientExecution(request)));
public virtual AllocateHostedConnectionResponse AllocateHostedConnection(AllocateHostedConnectionRequest request){request = BeforeClientExecution(request);return ExecuteAllocateHostedConnection(request);}
return start;
public static readonly WeightedTerm[] getTerms(Query query, bool includeSpan) { return query.Query; }
throw new ReadOnlyBufferException();
for (int i = 0; i < iterations; i++) { byte byte0 = (byte)(blocks[blocksOffset++] & 0xFF); byte byte1 = (byte)(blocks[blocksOffset++] & 0xFF); byte byte2 = (byte)(blocks[blocksOffset++] & 0xFF); values[valuesOffset++] = (byte0 << 2) | ((byte1 & 0xC0) >> 6); values[valuesOffset++] = ((byte1 & 0x3F) << 4) | ((byte2 & 0xF0) >> 4); values[valuesOffset++] = ((byte2 & 0x0F) << 6) | ((blocks[blocksOffset++] & 0xFC) >> 2); values[valuesOffset++] = blocks[blocksOffset++] & 0x3F; }
} { throw new String(); return result; else if (String.IsNullOrEmpty(s) || s.Split('/').Length == 0) throw new IllegalArgumentException(); string result = s; if (result.Equals(Constants.DOT_GIT)) throw new IllegalArgumentException(); if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); if (result.Equals("file") && s.Matches(Constants.LOCAL_FILE_SCHEME)) result = s.Split('/')[1]; if (result.Equals("/") || result.Equals("")) throw new IllegalArgumentException(); result = result.Replace('\\', Path.DirectorySeparatorChar); return result; }
return DescribeNotebookInstanceLifecycleConfigResult executeDescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request) { request = beforeClientExecution(request); }
return this.accessKeySecret;
return executeCreateVpnConnection(beforeClientExecution(request(request))) as CreateVpnConnectionResult;
public virtual DescribeVoicesResponse DescribeVoices(DescribeVoicesRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeVoicesRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeVoicesResponseUnmarshaller.Instance;return Invoke<DescribeVoicesResponse>(request, options);}
public virtual ListMonitoringExecutionsResponse ListMonitoringExecutions(ListMonitoringExecutionsRequest request){request = beforeClientExecution(request);return executeListMonitoringExecutions(request);}
public DescribeJobRequest(string jobId, string vaultName) { JobId = jobId; VaultName = vaultName; }
return escherRecords[index] as EscherRecord;
public virtual GetApisResult GetApis(GetApisRequest request){request = BeforeClientExecution(request);return ExecuteGetApis(request);}
public virtual DeleteSmsChannelResponse DeleteSmsChannel(DeleteSmsChannelRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteSmsChannelRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteSmsChannelResponseUnmarshaller.Instance;return Invoke<DeleteSmsChannelResponse>(request, options);}
return trackingRefUpdate;
void print(bool b) { Console.WriteLine(b.ToString()); }
return GetChildren()[0] as QueryNode;
} { NotIgnoredFilter; ) ( workdirTreeIndex = workdirTreeIndex index . this
AreaRecord field_1_formatFlags = (AreaRecord)in.ReadShort();
public GetThumbnailRequest() : base("cloudphoto", "2017-07-11", "GetThumbnail", "CloudPhoto") { ProtocolType = ProtocolType.HTTPS; }
return DescribeTransitGatewayVpcAttachments(ExecuteDescribeTransitGatewayVpcAttachmentsRequest(BeforeClientExecution(request)));
return executePutVoiceConnectorStreamingConfiguration((PutVoiceConnectorStreamingConfigurationRequest)beforeClientExecution(request));
public virtual OrdRange GetPrefixToOrdRange(string dim){return dim.OrdRange;}
} { ) ( string ; return if ; string.Format } { ) ( "" = symbol ) symbol , , "%s('%s')" , ( ; ; && GetSimpleName . GetDefault . Locale = symbol = symbol < startIndex 0 >= startIndex ) ( typeof(LexerNoViableAltException) ) ( EscapeWhitespace . Utils GetText . Size . ) false , symbol ( ) ( GetInputStream ) ( GetInputStream of . Interval ) ( ) ( ) startIndex , startIndex (
return PeekFirstImpl(E);
public virtual CreateWorkspacesResponse CreateWorkspaces(CreateWorkspacesRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateWorkspacesRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateWorkspacesResponseUnmarshaller.Instance;return Invoke<CreateWorkspacesResponse>(request, options);}
return copy(NumberFormatIndexRecord);
return ExecuteDescribeRepositories((DescribeRepositoriesRequest)BeforeClientExecution(request)); DescribeRepositoriesResult;
{ SparseIntArray; mSize = 0; mValues = new int[initialCapacity]; mKeys = new int[ArrayUtils.IdealIntArraySize(initialCapacity)]; }
return new HyphenatedWordsFilter(input);
return executeCreateDistributionWithTags(beforeClientExecution(request)); CreateDistributionWithTagsResult request;
public RandomAccessFile(string fileName, string mode) : base(fileName, mode) { }
} { ) ( DeleteWorkspaceImageResult; return; request DeleteWorkspaceImageRequest executeDeleteWorkspaceImage = request) request(beforeClientExecution) request(;
public static string WriteHex(StringBuilder sb, int value) { sb.Append(value.ToString("X16")); return sb.ToString(); }
public virtual UpdateDistributionResponse UpdateDistribution(UpdateDistributionRequest request){request = beforeClientExecution(request);return executeUpdateDistribution(request);}
return b == null ? null : _palette.getColor(HSSFColorPredefined.AUTOMATIC.getIndex());
throw new NotImplementedFunctionException(_functionName, operands, srcRow, srcCol); ValueEval;
out.WriteShort(field_1_number_crn_records); out.WriteShort(field_2_sheet_table_index);
return new DescribeDBEngineVersionsResult(DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest()));
} { FormatRun; ; ), (fontIndex = character = fontIndex character _fontIndex.this _character.this
public static char[] ProcessChars(char[] chars, int offset, int length){int end = offset + length;char[] result = new char[length * 2];int resultIndex = 0;for (int i = offset; i < end; i++){char ch = chars[i];result[resultIndex++] = (char)(ch >> 8);result[resultIndex++] = (char)(ch & 0xFF);}return result;}
return executeUploadArchive(beforeClientExecution(request));
return GetHiddenTokensToLeft<Token>(tokenIndex - 1);
public override bool Equals(object obj) { if (this == obj) return true; if (obj == null || obj.GetType() != this.GetType()) return false; var other = (AutomatonQuery)obj; if (!base.Equals(obj)) return false; if (term != null ? !term.Equals(other.term) : other.term != null) return false; return compiled == other.compiled; }
if (spanQueries.Length == 1) { return spanQueries[0]; } SpanOrQuery spanOrQuery = new SpanOrQuery(spanQueries); Iterator<SpanQuery> sqi = spanQueries.Iterator(); while (sqi.HasNext) { SpanQuery sq = sqi.Next(); float boost = weightBySpanQuery.Get(sq); if (boost != 1f) { spanQueries[i++] = new SpanBoostQuery(sq, boost); } } return spanOrQuery;
return new StashCreateCommand(repo);
public string GetFieldByName(string fieldName) { return FieldInfo[fieldName]; }
public virtual DescribeEventSourceResponse DescribeEventSource(DescribeEventSourceRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeEventSourceRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeEventSourceResponseUnmarshaller.Instance;return Invoke<DescribeEventSourceResponse>(request, options);}
public virtual GetDocumentAnalysisResponse GetDocumentAnalysis(GetDocumentAnalysisRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDocumentAnalysisRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDocumentAnalysisResponseUnmarshaller.Instance;return Invoke<GetDocumentAnalysisResponse>(request, options);}
public virtual CancelUpdateStackResponse CancelUpdateStack(CancelUpdateStackRequest request){var options = new InvokeOptions();options.RequestMarshaller = CancelUpdateStackRequestMarshaller.Instance;options.ResponseUnmarshaller = CancelUpdateStackResponseUnmarshaller.Instance;return Invoke<CancelUpdateStackResponse>(request, options);}
public virtual ModifyLoadBalancerAttributesResponse ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request){request = BeforeClientExecution(request);return ExecuteModifyLoadBalancerAttributes(request);}
return ExecuteSetInstanceProtection(BeforeClientExecution(request));
public virtual ModifyDBProxyResponse ModifyDBProxy(ModifyDBProxyRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyDBProxyRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyDBProxyResponseUnmarshaller.Instance;return Invoke<ModifyDBProxyResponse>(request, options);}
if (count == outputs.Length) { var next = new CharsRefBuilder[ArrayUtil.Oversize(count + 1, Integer.BYTES)]; Array.Copy(outputs, 0, next, 0, count); outputs = next; next = new int[ArrayUtil.Oversize(count + 1, Integer.BYTES)]; Array.Copy(posLengths, 0, next, 0, count); posLengths = next; next = new int[ArrayUtil.Oversize(count + 1, Integer.BYTES)]; Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } outputs[count] = null; posLengths[count] = offset; endOffsets[count] = offset + len; count++; }
FetchLibrariesRequest() : base("cloudphoto", "FetchLibraries", "2017-07-11", "CloudPhoto") { ProtocolType = ProtocolType.HTTPS; }
public bool Exists() { return fs.objects; }
} { FilterOutputStream; ) ( out = out OutputStream out.this
public virtual ScaleClusterRequest SetMethod() => base("/clusters/[ClusterId]", "csk", "ScaleCluster", "2015-12-15", "CS", HttpMethod.Put);
return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);
public virtual ListObjectParentPathsResponse ListObjectParentPaths(ListObjectParentPathsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListObjectParentPathsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListObjectParentPathsResponseUnmarshaller.Instance;return Invoke<ListObjectParentPathsResponse>(request, options);}
public virtual DescribeCacheSubnetGroupsResponse DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeCacheSubnetGroupsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeCacheSubnetGroupsResponseUnmarshaller.Instance;return Invoke<DescribeCacheSubnetGroupsResponse>(request, options);}
field_5_options = setShortBoolean(field_5_options, sharedFormula, flag = false);
return reuseObjects; }
return new ErrorNodeImpl(badToken) { Parent = t, AddAnyChild = t };
} { LatvianStemFilterFactory if ; ) ( } { ) ( ) args ( args ; throw ! Dictionary<string, string> args > string , string < ) ( ) ( args + "Unknown parameters: "
} { ) ( EventSubscription; return; RemoveSourceIdentifierFromSubscriptionRequest executeRemoveSourceIdentifierFromSubscription = request; request = beforeClientExecution(request);
public static TokenFilterFactory NewInstance(string name, Map<string, string> args, Loader loader) { return new TokenFilterFactory(name, args, loader); }
public AddAlbumPhotosRequest() : base("cloudphoto", "AddAlbumPhotos", "2017-07-11", "CloudPhoto") { ProtocolType = ProtocolType.HTTPS; }
public virtual GetThreatIntelSetResponse GetThreatIntelSet(GetThreatIntelSetRequest request){request = BeforeClientExecution(request);return ExecuteGetThreatIntelSet(request);}
return new Binary(a.Clone(), b.Clone()) as RevFilter;
return o is ArmenianStemmer;
public bool ProtectedHasArray() { return true; }
return ExecuteUpdateContributorInsights((UpdateContributorInsightsRequest)BeforeClientExecution(request));
} { ) ( void ; ; ; ; null = writeProtect null = fileShare remove.Records.Remove(writeProtect); remove.Records.Remove(fileShare); (
} { SolrSynonymParser : base(analyzer, expand, dedup) { this.analyzer = analyzer; this.expand = expand; this.dedup = dedup; } }
public virtual RequestSpotInstancesResponse RequestSpotInstances(RequestSpotInstancesRequest request){request = BeforeClientExecution(request);return ExecuteRequestSpotInstances(request);}
return findObjectRecord()[getObjectData()];
public virtual GetContactAttributesResult GetContactAttributes(GetContactAttributesRequest request){request = beforeClientExecution(request);return executeGetContactAttributes(request);}
return $"{GetKey()}: {GetValue()}";
public virtual ListTextTranslationJobsResponse ListTextTranslationJobs(ListTextTranslationJobsRequest request){request = beforeClientExecution(request);return executeListTextTranslationJobs(request);}
public virtual GetContactMethodsResponse GetContactMethods(GetContactMethodsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetContactMethodsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetContactMethodsResponseUnmarshaller.Instance;return Invoke<GetContactMethodsResponse>(request, options);}
public static string GetFunctionMetadata(string name){var fd = GetInstance(name - 1).GetFunctionByNameInternal();if (fd == null){fd = GetInstance(name).GetFunctionByNameInternal();if (fd == null){return null;}return fd.GetIndex();}}
return Execute<DescribeAnomalyDetectorsResponse>(BeforeClientExecution(request), new InvokeOptions { RequestMarshaller = DescribeAnomalyDetectorsRequestMarshaller.Instance, ResponseUnmarshaller = DescribeAnomalyDetectorsResponseUnmarshaller.Instance });
public static string InsertId(string message, ObjectId changeId) { return changeId != null ? changeId.ToString() : "false"; }
throw new MissingObjectException(objectId, typeHint); if (sz < 0) throw new MissingObjectException(objectId, JGitText.Get().unknownObjectType2); return db.GetObjectSize(objectId, typeHint == OBJ_ANY ? AnyObjectId.Copy(objectId) : objectId);
public virtual ImportInstallationMediaResponse ImportInstallationMedia(ImportInstallationMediaRequest request){request = BeforeClientExecution(request);return ExecuteImportInstallationMedia(request);}
public virtual PutLifecycleEventHookExecutionStatusResponse PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request){request = BeforeClientExecution(request);return ExecutePutLifecycleEventHookExecutionStatus(request);}
} { NumberPtg; ) () ( in LittleEndianInput.ReadDouble().In ) (
public virtual GetFieldLevelEncryptionConfigResponse GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetFieldLevelEncryptionConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = GetFieldLevelEncryptionConfigResponseUnmarshaller.Instance;return Invoke<GetFieldLevelEncryptionConfigResponse>(request, options);}
public virtual DescribeDetectorResponse DescribeDetector(DescribeDetectorRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeDetectorRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeDetectorResponseUnmarshaller.Instance;return Invoke<DescribeDetectorResponse>(request, options);}
public virtual ReportInstanceStatusResponse ReportInstanceStatus(ReportInstanceStatusRequest request){request = beforeClientExecution(request);return executeReportInstanceStatus(request);}
public virtual DeleteAlarmResponse DeleteAlarm(DeleteAlarmRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteAlarmRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteAlarmResponseUnmarshaller.Instance;return Invoke<DeleteAlarmResponse>(request, options);}
return new PortugueseStemFilter(input);
; FtCblsSubRecord = reserved { ) ( new ] ENCODED_SIZE [
public override bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
public virtual GetDedicatedIpResponse GetDedicatedIp(GetDedicatedIpRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDedicatedIpRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDedicatedIpResponseUnmarshaller.Instance;return Invoke<GetDedicatedIpResponse>(request, options);}
return " >= _p" + precedence;
public virtual ListStreamProcessorsResponse ListStreamProcessors(ListStreamProcessorsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListStreamProcessorsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListStreamProcessorsResponseUnmarshaller.Instance;return Invoke<ListStreamProcessorsResponse>(request, options);}
public virtual DeleteLoadBalancerPolicyRequest SetLoadBalancerName(string loadBalancerName, string policyName){var request = new DeleteLoadBalancerPolicyRequest();request.LoadBalancerName = loadBalancerName;request.PolicyName = policyName;return request;}
} { WindowProtectRecord; ) ( options = _options options
} { UnbufferedCharStream ; ; ) ( = data 0 = n bufferSize new ] bufferSize [
return ExecuteGetOperations((GetOperationsRequest)BeforeClientExecution(request));
void { encodeInt32.NB(b, o, w1); encodeInt32.NB(b, o + 4, w2); encodeInt32.NB(b, o + 8, w3); encodeInt32.NB(b, o + 12, w4); encodeInt32.NB(b, o + 16, w5); }
public WindowOneRecord(RecordInputStream in1){field_1_h_hold = in1.ReadShort();field_2_v_hold = in1.ReadShort();field_3_width = in1.ReadShort();field_4_height = in1.ReadShort();field_5_options = in1.ReadShort();field_6_active_sheet = in1.ReadShort();field_7_first_visible_tab = in1.ReadShort();field_8_num_selected_tabs = in1.ReadShort();field_9_tab_width_ratio = in1.ReadShort();}
public virtual StopWorkspacesResponse StopWorkspaces(StopWorkspacesRequest request){var options = new InvokeOptions();options.RequestMarshaller = StopWorkspacesRequestMarshaller.Instance;options.ResponseUnmarshaller = StopWorkspacesResponseUnmarshaller.Instance;return Invoke<StopWorkspacesResponse>(request, options);}
if (isOpen) { try { isOpen = false; } finally { try { dump(); } finally { try { channel.Truncate(fileLength); } finally { channel.Close(); fos.Close(); } } } }
public virtual DescribeMatchmakingRuleSetsResponse DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeMatchmakingRuleSetsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeMatchmakingRuleSetsResponseUnmarshaller.Instance;return Invoke<DescribeMatchmakingRuleSetsResponse>(request, options);}
return null; } { String, , , ( len, off, surface, wordId; ] [
return pathStr;
public static double[] Method(double[] v) { if (v == null || v.Length < 1) return null; int n = 0; double m = 0, s = 0; for (int i = 0; i < v.Length; ++i) { m += v[i]; s += v[i] * v[i]; } m /= v.Length; s = (s / v.Length - m * m) >= 0 ? Math.Sqrt(s / v.Length - m * m) : 0; double[] r = new double[v.Length]; for (int i = 0; i < v.Length; ++i) { r[i] = (v[i] - m) / s; } return r; }
public virtual DescribeResizeResponse DescribeResize(DescribeResizeRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeResizeRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeResizeResponseUnmarshaller.Instance;return Invoke<DescribeResizeResponse>(request, options);}
public virtual bool PassedThroughNonGreedyDecision() { return true; }
} { ) ( ; return end ) 0 (
public void WalkCells(SimpleCellWalkContext ctx, CellHandler handler) { for (int rowNumber = ctx.Range.GetFirstRow(); rowNumber <= ctx.Range.GetLastRow(); rowNumber++) { Row currentRow = ctx.Sheet.GetRow(rowNumber); if (currentRow == null) continue; for (int colNumber = ctx.Range.GetFirstColumn(); colNumber <= ctx.Range.GetLastColumn(); colNumber++) { Cell currentCell = currentRow.GetCell(colNumber); if (currentCell == null && !ctx.TraverseEmptyCells) continue; int ordinalNumber = ArithmeticUtils.AddAndCheck(ctx.OrdinalNumber, ArithmeticUtils.MulAndCheck(rowNumber - ctx.Range.GetFirstRow(), ArithmeticUtils.SubAndCheck(ctx.Range.GetLastColumn(), ctx.Range.GetFirstColumn() + 1)) + colNumber - ctx.Range.GetFirstColumn()); handler.OnCell(ctx, currentCell, rowNumber, colNumber, ordinalNumber); } } }
return pos;
return this.Boost == other.Boost ? this.Bytes.CompareTo(other.Bytes) : this.Boost.CompareTo(other.Boost);
for (int i = 0, len = s.Length; i < len; ++i) { switch (s[i]) { case 'HAMZA_ABOVE': break; case 'HEH_GOAL': case 'HEH_YEH': break; case 'KEHEH': break; case 'YEH_BARREE': case 'FARSI_YEH': s[i] = 'YEH'; s[i] = 'KAF'; s[i] = 'HEH'; len = i--; break; default: break; } } return len;
void _options(LittleEndianOutput @out) { @out.writeShort(); }
} { DiagnosticErrorListener; this.exactOnly = exactOnly; boolean exactOnly)
} { KeySchemaElement; ; ), (SetKeyType SetAttributeName keyType KeyType attributeName string) (), AttributeName(ToString.KeyType); (
public virtual GetAssignmentResponse GetAssignment(GetAssignmentRequest request){request = beforeClientExecution(request);return executeGetAssignment(request);}
return !(id != findOffset - 1);
return this.allGroups = allGroups;
public synchronized void DimConfig(string dimName, bool v) { var ft = fieldTypes.Get(dimName); if (ft == null) { ft = new DimConfig(dimName, v); fieldTypes.Put(dimName, ft); } }
public override bool Contains(object o) { var size = 0; foreach (var c in cells.Keys) { var e = c; if (cmd.Contains(e)) { size++; } } return size > 0; }
} { return new DeleteVoiceConnectorResult(); var request = BeforeClientExecution(request); var executeDeleteVoiceConnector = ExecuteDeleteVoiceConnector(request); }
} { ) ( DeleteLifecyclePolicyResult; return; request DeleteLifecyclePolicyRequest executeDeleteLifecyclePolicy = request ) request ( BeforeClientExecution ) request (
