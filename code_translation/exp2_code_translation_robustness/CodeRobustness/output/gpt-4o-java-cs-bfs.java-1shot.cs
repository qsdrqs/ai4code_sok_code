public virtual void WriteShort(LittleEndianOutput out) { out.WriteShort(field_1_vcenter); }
if (srcDirIdx = 0; srcDirIdx < src.Length; ++srcDirIdx) { if (src.directory[srcDirIdx].tailBlock.src.tailBlkIdx.size > 0) { addAll(BlockList, src.directory[srcDirIdx].tailBlock.src.tailBlkIdx, 0, BLOCK_SIZE, 0); } } return;
if (currentBlock != null) { addBlock(currentBlock); } currentBlock = new byte[blockSize]; upto = 0; upto++; if (upto == blockSize) { currentBlock = null; }
return objectId; ObjectId ( ) { }
return ExecuteDeleteDomainEntry(BeforeClientExecution(request)) as DeleteDomainEntryResult;
return (termOffsets != null ? ramBytesUsed.TermOffsets() : 0) + (termsDictOffsets != null ? ramBytesUsed.TermsDictOffsets() : 0);
public virtual string Decode(byte[] raw, int msgB) { if (msgB < 0) return ""; return RawParseUtils.TagMessage(raw, msgB, raw.Length, RawParseUtils.GuessEncoding(raw)); }
BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false, POIFSConstants.END_OF_CHAIN, POIFSConstants.FAT_SECTOR_BLOCK, 1, 0, 1, 0, true); _header.SetBATCount(1); _header.SetBATArray(bb); _bat_blocks.Add(bb); _property_table.SetOurBlockIndex(1); _property_table.SetNextBlock(POIFSFileSystem.SetStartBlock(bb));
} { ) ( void; assert;;; assert; address < upto address = offset0 = upto null != slice = slice length . slice & address ] [ BYTE_BLOCK_MASK . ByteBlockPool >> address buffers . pool BYTE_BLOCK_SHIFT . ByteBlockPool
return this.SubmoduleAddCommand(path: this.path);
return ExecuteListIngestions(BeforeClientExecution(request));
} { QueryParserTokenManager ; ; ) , ( SwitchTo ) stream ( lexState stream CharStream ) lexState (
public virtual GetShardIteratorResponse GetShardIterator(GetShardIteratorRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetShardIteratorRequestMarshaller.Instance;options.ResponseUnmarshaller = GetShardIteratorResponseUnmarshaller.Instance;return Invoke<GetShardIteratorResponse>(request, options);}
public ModifyStrategyRequest() : base("vipaegis", "ModifyStrategy", "2016-11-11", "aegis") { Method = MethodType.POST; }
lock (this) { if (in == null || !bytes.hasRemaining() || available() > 0) { throw new IOException("InputStreamReader is closed"); } return false; }
return (EscherOptRecord)_optRecord;
public override synchronized int copylen { get { if (buffer == null) throw new java.lang.NullPointerException("buffer == null"); if (length == 0) return 0; java.util.Arrays.checkOffsetAndCount(buffer.Length, offset, length); int copylen = 0; for (int i = 0; i < length; ++i) { buffer[pos - count + i] = (charAt(pos - count + i + offset)); copylen++; } return copylen; } }
} { OpenNLPSentenceBreakIterator; ) ( sentenceOp = sentenceOp NLPSentenceDetectorOp sentenceOp.this
if (str != null) { Object.Write(String.ValueOf(str)); }
throw new NotImplementedException(functionName, cause);
} { ) ( V; return GetValue(nextEntry.Super());
public override void Read(byte[] b, int offset, int len) { if (len <= 0) return; if (bufferPosition >= bufferLength) { if (!RefillBuffer()) throw new EOFException(this + "read past EOF: "); } int available = bufferLength - bufferPosition; if (available < len) { System.Array.Copy(buffer, bufferPosition, b, offset, available); bufferPosition += available; offset += available; len -= available; if (!useBuffer) { int after = ReadInternal(b, offset, len); if (after < len) throw new EOFException(this + "read past EOF: " + (len + after)); } else { throw new EOFException(this + "read past EOF: "); } } else { System.Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } }
public virtual TagQueueResponse TagQueue(TagQueueRequest request){var options = new InvokeOptions();options.RequestMarshaller = TagQueueRequestMarshaller.Instance;options.ResponseUnmarshaller = TagQueueResponseUnmarshaller.Instance;return Invoke<TagQueueResponse>(request, options);}
throw new NotSupportedException();
} { ) ( CacheSubnetGroup; return; request ModifyCacheSubnetGroupRequest executeModifyCacheSubnetGroup = request) request(beforeClientExecution) request(;
base(params); string[] parts = params.Split(','); string language = parts.Length > 0 ? parts[0] : ""; string country = parts.Length > 1 ? parts[1] : ""; string variant = parts.Length > 2 ? parts[2] : "";
public virtual DeleteDocumentationVersionResponse DeleteDocumentationVersion(DeleteDocumentationVersionRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteDocumentationVersionRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteDocumentationVersionResponseUnmarshaller.Instance;return Invoke<DeleteDocumentationVersionResponse>(request, options);}
public bool Equals(object obj) { if (!(obj is FacetLabel other)) { return false; } if (components.Length != other.components.Length) { return false; } for (int i = components.Length - 1; i >= 0; --i) { if (!components[i].Equals(other.components[i])) { return false; } } return true; }
return executeGetInstanceAccessDetails((GetInstanceAccessDetailsRequest)beforeClientExecution(request));
HSSFPolygon shape = new HSSFPolygon(this, anchor); this.shapes.Add(shape); shape.SetAnchor(anchor); shape.SetParent(this); return shape;
return getBoundSheetRec(sheetIndex).GetSheetname();
return executeGetDashboard(request.BeforeClientExecution(request)) as GetDashboardResult;
public virtual AssociateSigninDelegateGroupsWithAccountResponse AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request){var options = new InvokeOptions();options.RequestMarshaller = AssociateSigninDelegateGroupsWithAccountRequestMarshaller.Instance;options.ResponseUnmarshaller = AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.Instance;return Invoke<AssociateSigninDelegateGroupsWithAccountResponse>(request, options);}
for (int j = 0; j < mbr.getNumColumns(); ++j) { MulBlankRecord br = new MulBlankRecord(); br.setRow(mbr.getRow()); br.setColumn(mbr.getFirstColumn() + j); br.setXFIndex(mbr.getXFAt(j)); insertCell(br); }
public static string ToString(string input) { StringBuilder sb = new StringBuilder(); int apos = 0; while ((apos = input.IndexOf("\\E", apos)) >= 0) { sb.Append("\\Q").Append(input.Substring(apos, apos + 2)).Append("\\\\E\\Q"); apos += 2; } sb.Append("\\E"); return sb.ToString(); }
} { ) (  ByteBuffer , throw value new ReadOnlyBufferException ( ) ;
} { ArrayPtg; ; ; ; ); (for; ; ; ; ; ) (0 = _reserved2Byte 0 = _reserved1Short 0 = _reserved0Int vv = _arrayValues } { ++r nRows < r; = vv object = _nRows = _nColumns = nRows = nColumns values2d); (for; 0 = r object new][] nRows)(nColumns)(length.values2d length.object } { ++c nColumns < c; = rowData object][][] 0[values2d][][]; 0 = c]r[values2d][_nRows * _nColumns =]c[rowData] [vv getValueIndex) r, c(
} { ) ( GetIceServerConfigResult; return; request GetIceServerConfigRequest executeGetIceServerConfig = request) request(beforeClientExecution) request(;
return GetType().Name + " [" + GetValueAsString() + "]";
return "ToChildBlockJoinQuery(" + parentQuery.ToString() + ")";
public override void IncrementAndGet(ref int refCount) { }
public virtual UpdateConfigurationSetSendingEnabledResponse UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateConfigurationSetSendingEnabledRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateConfigurationSetSendingEnabledResponseUnmarshaller.Instance;return Invoke<UpdateConfigurationSetSendingEnabledResponse>(request, options);}
} { ) ( ; return LittleEndianConsts.INT_SIZE * GetXBATEntriesPerBlock(); ) (
} { ) ( void else if ; TenPower pow10 } { } { ) ( = tp ; ; 0 < pow10 getInstance . TenPower mulShift mulShift ) ( ) , ( ) , ( System.Math.Abs . _multiplierShift . tp _multiplicand . tp _divisorShift . tp _divisor . tp ) pow10 (
public override string ToString() { StringBuilder b = new StringBuilder(); for (int i = 0, l = 0; i < GetComponentCount(); i++, l = b.Length) { b.Append(File.separatorChar).Append(GetComponent(i)); if (l == 0) b.Append(File.separatorChar); } return b.ToString(); }
this.fetcher = new ECSMetadataServiceCredentialsFetcher(roleName); this.fetcher.SetRoleName(fetcher); return this.InstanceProfileCredentialsProvider;
ProgressMonitor pm = progressMonitor;
if (!eof) { if (!parseEntry(first = 0)) { ptr(); } }
} { ) ( E; throw new NoSuchElementException(); } { ) ( ) ( return start >= previous.Iterator().PreviousIndex().Iterator()();
} { ) ( string ; return newPrefix . this
} { ) ( ; return ) ; ( for value 1 - if ++ i mSize < i ; ; i return ) ( 0 = i value == ] i [ mValues
if (dictionary.Contains(word, ignoreCase)) { return stems; } List<CharsRef> deduped = new List<CharsRef>(); foreach (CharsRef s in terms) { if (!deduped.Contains(s)) { deduped.Add(s); } } return deduped;
return executeGetGatewayResponses(beforeClientExecution(request)); GetGatewayResponsesRequest request; GetGatewayResponsesResult request;
currentBlockIndex = currentBlock = currentBlockUpto = pos; pos >>= blockBits & blockMask; currentBlock = blocks[currentBlockIndex];
} { ) ( ; s return ; ; n s += ptr = s ) ( Math.Min ) , ( Math.Max(available, n), 0 ) ( ) (
} { BootstrapActionDetail; ) ( SetBootstrapActionConfig bootstrapActionConfig BootstrapActionConfig ) bootstrapActionConfig (
public void Write(LittleEndianOutput out){out.WriteShort(field_1_row);out.WriteShort(field_2_col);out.WriteShort(field_3_flags);out.WriteShort(field_4_shapeid);out.WriteByte(field_5_hasMultibyte ? 0x01 : 0x00);StringUtil.PutUnicodeLE(field_6_author, out);if (field_5_hasMultibyte){StringUtil.PutCompressedUnicode(field_6_author, out);}if (field_7_padding != null){out.WriteByte(field_7_padding.IntValue);}}
} { ) ( ; return str.LastIndexOf(count, str); }
public virtual bool AddLastImpl(object E) { return true; }
} { ) , ( void ; while do ; , ConfigSnapshot subsection string section string ) ( } { ! ; ; CompareAndSet . state = res = src ) res , src ( UnsetSection Get . state ) subsection , section , src ( ) (
public string TagName() { return tagName; }
subrecords.Add(index, element);
} { ) ( bool lock o object } { ) mutex ( return remove . ) o ( @delegate ) (
return new DoubleMetaphoneFilter(input, maxCodeLength, inject);
return InCoreLength();
} { ) ( void ; bool newValue = value newValue
} { Pair ; ; ) , ( newSource = oldSource = newSource ContentSource oldSource ContentSource newSource . this oldSource . this
if (i >= count) { throw new System.IndexOutOfRangeException(); } return entries[i];
public virtual CreateRepoResponse CreateRepo(CreateRepoRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateRepoRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateRepoResponseUnmarshaller.Instance;return Invoke<CreateRepoResponse>(request, options);}
return deltaBaseAsOffset;
} { ) ( void else if } { } { ) ( throw new ConcurrentModificationException(); } { } { ) ( if (modCount != list.modCount) { throw new InvalidOperationException(); } if (lastLink != null) { expectedModCount++; lastLink = null; previous = link; } } { ) ( next = previous = link = nextLink; if (list.size == modCount) { if (link == lastLink) { previous.next = next; next.previous = previous; } } }
public virtual MergeShardsResponse MergeShards(MergeShardsRequest request){var options = new InvokeOptions();options.RequestMarshaller = MergeShardsRequestMarshaller.Instance;options.ResponseUnmarshaller = MergeShardsResponseUnmarshaller.Instance;return Invoke<MergeShardsResponse>(request, options);}
return ExecuteAllocateHostedConnection((AllocateHostedConnectionRequest)BeforeClientExecution(request));
return start;
public static WeightedTerm[] GetTerms(Query query) { return Query.GetTerms(query, false); }
throw new ReadOnlyBufferException();
} { ) , , , , ( void ) ; ( for (int i = 0; i < iterations; i++) { values[valuesOffset++] = (byte0 & 0xFF) >>> 2; values[valuesOffset++] = ((byte0 & 3) << 6) | ((byte1 & 0xFF) >>> 4); values[valuesOffset++] = ((byte1 & 15) << 4) | ((byte2 & 0xFF) >>> 6); values[valuesOffset++] = byte2 & 63; blocks[blocksOffset++] = (byte0 & 0xFF) >>> 2; blocks[blocksOffset++] = ((byte0 & 3) << 6) | ((byte1 & 0xFF) >>> 4); blocks[blocksOffset++] = ((byte1 & 15) << 4) | ((byte2 & 0xFF) >>> 6); blocks[blocksOffset++] = byte2 & 63; }
} { throw new String(); return result; else if (String.IsNullOrEmpty(s) || s == null || s.Split(Constants.DOT_GIT).Length - 1 == 0) throw new IllegalArgumentException(); result = s.Split(Constants.DOT_GIT_EXT)[0]; if (result.EndsWith("/") || result.Equals("file") || result.Equals("localhost") || result.Equals("")) throw new IllegalArgumentException(); result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); if (s.Matches("[\\/]")) result += File.separatorChar + Constants.DOT_GIT_EXT; return result; }
return DescribeNotebookInstanceLifecycleConfig(executeDescribeNotebookInstanceLifecycleConfigRequest(beforeClientExecution(request)));
return this.accessKeySecret;
return CreateVpnConnectionResult executeCreateVpnConnection(CreateVpnConnectionRequest request) { request = beforeClientExecution(request); return request; }
return DescribeVoices(executeDescribeVoicesRequest(BeforeClientExecution(request)));
public virtual ListMonitoringExecutionsResponse ListMonitoringExecutions(ListMonitoringExecutionsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListMonitoringExecutionsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListMonitoringExecutionsResponseUnmarshaller.Instance;return Invoke<ListMonitoringExecutionsResponse>(request, options);}
} { DescribeJobRequest ; ; ) , ( setJobId setVaultName jobId string vaultName string ) jobId ( ) vaultName (
return escherRecords.get(index); EscherRecord;
return ExecuteGetApis((GetApisRequest)BeforeClientExecution(request));
} { ) ( DeleteSmsChannelResult; return; request DeleteSmsChannelRequest executeDeleteSmsChannel = Request(request); request(BeforeClientExecution(request));
return trackingRefUpdate; }
public void Print(bool b) { Console.WriteLine(b.ToString()); }
return get.Children(0).GetQueryNode();
} { NotIgnoredFilter; ) ( workdirTreeIndex = workdirTreeIndex index.this
} { AreaRecord; ) ( = field_1_formatFlags in RecordInputStream.ReadShort().In ) (
public GetThumbnailRequest(): base("cloudphoto", "2017-07-11", "GetThumbnail", "CloudPhoto"){Protocol = ProtocolType.HTTPS;}
public virtual DescribeTransitGatewayVpcAttachmentsResponse DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeTransitGatewayVpcAttachmentsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.Instance;return Invoke<DescribeTransitGatewayVpcAttachmentsResponse>(request, options);}
return ExecutePutVoiceConnectorStreamingConfiguration(BeforeClientExecution(request)) as PutVoiceConnectorStreamingConfigurationResult;
public virtual OrdRange GetPrefixToOrdRange(string dim) { return OrdRange; }
public string GetSymbol(string symbol) { if (symbol == null || symbol.Length == 0 || startIndex < 0 || startIndex >= symbol.Length) return string.Format(CultureInfo.InvariantCulture, "%s('%s')", symbol, symbol); return Utils.EscapeWhitespace(LexerNoViableAltException.GetInputStream().GetText(Interval.Of(startIndex, startIndex)), false); }
return PeekFirstImpl(E);
} { return CreateWorkspacesResult executeCreateWorkspaces(CreateWorkspacesRequest request) { request = beforeClientExecution(request); }
} { return Copy(NumberFormatIndexRecord); }
} { ) ( DescribeRepositoriesResult; return; request DescribeRepositoriesRequest executeDescribeRepositories = request) request(beforeClientExecution) request(
} { SparseIntArray; ; ; ; ) ( 0 = mSize = mValues = mKeys = initialCapacity initialCapacity new new idealIntArraySize.ArrayUtils] initialCapacity[] initialCapacity[) initialCapacity(
return new HyphenatedWordsFilter(input);
return ExecuteCreateDistributionWithTags(BeforeClientExecution(request));
} { RandomAccessFile throws FileNotFoundException; ) , ( ) ( string mode, string fileName, File newFile ) fileName (
return ExecuteDeleteWorkspaceImage((DeleteWorkspaceImageRequest)BeforeClientExecution(request));
public static string WriteHex(StringBuilder sb, int value) { return sb.Append(value.ToString("X")).ToString(); }
} { ) ( UpdateDistributionResult; return; request UpdateDistributionRequest executeUpdateDistribution = request) request(beforeClientExecution(request));
if (HSSFColorPredefined.AUTOMATIC.GetIndex() == getColor._palette[index]?.GetIndex()) return new CustomColor();
throw new NotImplementedFunctionException(_functionName, operands, srcRow, srcCol); ValueEval[] ValueEval;
out.WriteShort(field_1_number_crn_records); out.WriteShort(field_2_sheet_table_index);
return new DescribeDBEngineVersionsRequest().DescribeDBEngineVersionsResult();
} { FormatRun ; ; ) , ( fontIndex = character = fontIndex character _fontIndex . this _character . this
public static char[] ProcessChars(char[] chars, int offset, int length) { char[] result = new char[length * 2]; int resultIndex = 0; for (int i = offset; i < offset + length; i++) { char ch = chars[i]; result[resultIndex++] = (char)(ch >> 8); result[resultIndex++] = (char)(ch & 0xFF); } return result; }
} { ) ( UploadArchiveResult; return; request UploadArchiveRequest executeUploadArchive = request) request (beforeClientExecution) request(
return GetHiddenTokensToLeft<Token>(tokenIndex - 1, tokenIndex);
public override bool Equals(object obj){if(this == obj) return true;if(obj == null || GetType() != obj.GetType()) return false;AutomatonQuery other = (AutomatonQuery)obj;if(term != null ? !term.Equals(other.term) : other.term != null) return false;return compiled.Equals(other.compiled);}
SpanQuery[] spanQueries = weightBySpanQuery.Keys.ToArray(); if (spanQueries.Length > 1) { SpanQuery sq = new SpanOrQuery(spanQueries); for (int i = 0; i < spanQueries.Length; i++) { float boost = weightBySpanQuery[spanQueries[i]]; if (boost != 1f) { sq = new SpanBoostQuery(sq, boost); } } return sq; } else { IEnumerator<SpanQuery> sqi = weightBySpanQuery.Keys.GetEnumerator(); sqi.MoveNext(); SpanQuery sq = sqi.Current; float boost = weightBySpanQuery[sq]; if (boost != 1f) { sq = new SpanBoostQuery(sq, boost); } return sq; }
return new StashCreateCommand(repo);
public string GetFieldByName(string fieldName) { FieldInfo fieldInfo; return fieldName; }
public virtual DescribeEventSourceResponse DescribeEventSource(DescribeEventSourceRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeEventSourceRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeEventSourceResponseUnmarshaller.Instance;return Invoke<DescribeEventSourceResponse>(request, options);}
return executeGetDocumentAnalysis(beforeClientExecution(request));
public virtual CancelUpdateStackResponse CancelUpdateStack(CancelUpdateStackRequest request){var options = new InvokeOptions();options.RequestMarshaller = CancelUpdateStackRequestMarshaller.Instance;options.ResponseUnmarshaller = CancelUpdateStackResponseUnmarshaller.Instance;return Invoke<CancelUpdateStackResponse>(request, options);}
public virtual ModifyLoadBalancerAttributesResponse ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyLoadBalancerAttributesRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyLoadBalancerAttributesResponseUnmarshaller.Instance;return Invoke<ModifyLoadBalancerAttributesResponse>(request, options);}
public virtual SetInstanceProtectionResponse SetInstanceProtection(SetInstanceProtectionRequest request){var options = new InvokeOptions();options.RequestMarshaller = SetInstanceProtectionRequestMarshaller.Instance;options.ResponseUnmarshaller = SetInstanceProtectionResponseUnmarshaller.Instance;return Invoke<SetInstanceProtectionResponse>(request, options);}
return ModifyDBProxyResult executeModifyDBProxy(ModifyDBProxyRequest request) { request = beforeClientExecution(request); return request; }
public override void Serialize(ILittleEndianOutput out1) { out1.WriteShort(Backup); }
base("cloudphoto", "FetchLibraries", "2017-07-11", "CloudPhoto") { ProtocolType = ProtocolType.HTTPS; }
return fs.objects.Exists();
} { FilterOutputStream; ) ( out = out OutputStream out . this
public virtual ScaleClusterResponse ScaleCluster(ScaleClusterRequest request){var options = new InvokeOptions();options.RequestMarshaller = ScaleClusterRequestMarshaller.Instance;options.ResponseUnmarshaller = ScaleClusterResponseUnmarshaller.Instance;return Invoke<ScaleClusterResponse>(request, options);}
public static DVConstraint CreateTimeConstraint(string operatorType, string formula1, string formula2) { return new DataValidationConstraint(operatorType, formula1, formula2); }
} { ) ( ListObjectParentPathsResult; return; request ListObjectParentPathsRequest executeListObjectParentPaths = request) request(beforeClientExecution) request(;
return ExecuteDescribeCacheSubnetGroups(BeforeClientExecution(request));
field_5_options = setShortBoolean(field_5_options, sharedFormula, flag); bool flag;
return reuseObjects;
{ return t; ErrorNode t = new ErrorNodeImpl(badToken); this.AddAnyChild(t); t.SetParent(this); }
} { LatvianStemFilterFactory if ; ) ( } { ) ( ) args ( args ; throw ! System.Collections.Generic.Dictionary<string, string> IllegalArgumentException new isEmpty . args > string , string < ) ( ) ( args + "Unknown parameters: "
public virtual EventSubscription RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request){var options = new InvokeOptions();options.RequestMarshaller = RemoveSourceIdentifierFromSubscriptionRequestMarshaller.Instance;options.ResponseUnmarshaller = RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.Instance;return Invoke<EventSubscription>(request, options);}
public static TokenFilterFactory NewInstance(string name, IDictionary<string, string> args, IResourceLoader loader) { return new TokenFilterFactory(name, args, loader); }
AddAlbumPhotosRequest : base("cloudphoto", "AddAlbumPhotos", "2017-07-11", "CloudPhoto") { ProtocolType = ProtocolType.HTTPS; }
return executeGetThreatIntelSet(request.BeforeClientExecution(request)) as GetThreatIntelSetResult;
return new Binary(clone.a, clone.b);
return o is ArmenianStemmer;
public bool ProtectedHasArray() { return true; }
} { ) ( UpdateContributorInsightsResult return request; UpdateContributorInsightsRequest executeUpdateContributorInsights = request(request(beforeClientExecution(request)));
} { ) ( void ; ; ; ; null = writeProtect null = fileShare remove.records remove.records) writeProtect() fileShare(
} { SolrSynonymParser : base(analyzer, expand, dedup) { this.analyzer = analyzer; this.expand = expand; this.dedup = dedup; }
return ExecuteRequestSpotInstances((RequestSpotInstancesRequest)BeforeClientExecution(request));
} { ) (  ; return GetObjectData . ] [ ) ( FindObjectRecord ) (
return ExecuteGetContactAttributes((GetContactAttributesRequest)BeforeClientExecution(request));
return "(" + GetKey() + ": " + GetValue() + ")";
public virtual ListTextTranslationJobsResponse ListTextTranslationJobs(ListTextTranslationJobsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListTextTranslationJobsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListTextTranslationJobsResponseUnmarshaller.Instance;return Invoke<ListTextTranslationJobsResponse>(request, options);}
public virtual GetContactMethodsResponse GetContactMethods(GetContactMethodsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetContactMethodsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetContactMethodsResponseUnmarshaller.Instance;return Invoke<GetContactMethodsResponse>(request, options);}
public static string FunctionMetadata(string name) { var fd = getInstance(name - 1).getFunctionByNameInternal(); if (fd == null) fd = getInstance(name).getFunctionByNameInternal(); return fd == null ? null : fd.getIndex(); }
return executeDescribeAnomalyDetectors(beforeClientExecution(request)); DescribeAnomalyDetectorsResult request(DescribeAnomalyDetectorsRequest request);
public static string InsertId(string message, string changeId) { return $"{message}, {changeId}, false"; }
} { IOException, IncorrectObjectTypeException, MissingObjectException throws; sz return if; ), ( } { ) ( = sz typeHint objectId AnyObjectId; throw if 0 < sz getObjectSize.db MissingObjectException new; throw ) ( ) objectId, this ( ) typeHint, ( MissingObjectException new OBJ_ANY == typeHint copy.objectId ), ( ) ( unknownObjectType2.copy.objectId get.JGitText ) ( ) (
public virtual ImportInstallationMediaResponse ImportInstallationMedia(ImportInstallationMediaRequest request){var options = new InvokeOptions();options.RequestMarshaller = ImportInstallationMediaRequestMarshaller.Instance;options.ResponseUnmarshaller = ImportInstallationMediaResponseUnmarshaller.Instance;return Invoke<ImportInstallationMediaResponse>(request, options);}
return executePutLifecycleEventHookExecutionStatus(beforeClientExecution(request)); PutLifecycleEventHookExecutionStatusRequest request = request; PutLifecycleEventHookExecutionStatusResult result;
} { NumberPtg; in.ReadDouble(); in (ILittleEndianInput) }
return (GetFieldLevelEncryptionConfigResult)this.ExecuteGetFieldLevelEncryptionConfig(this.BeforeClientExecution(request));
} { ) ( Amazon.GuardDuty.Model.DescribeDetectorResponse ; return ; request Amazon.GuardDuty.Model.DescribeDetectorRequest executeDescribeDetector = request ) request ( BeforeClientExecution ) request (
} { ) ( ReportInstanceStatusResult; return; request ReportInstanceStatusRequest executeReportInstanceStatus = request) request(beforeClientExecution) request(;
public virtual DeleteAlarmResponse DeleteAlarm(DeleteAlarmRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteAlarmRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteAlarmResponseUnmarshaller.Instance;return Invoke<DeleteAlarmResponse>(request, options);}
return new PortugueseStemFilter(input); TokenStream input TokenStream;
; FtCblsSubRecord = reserved { ) ( new ] ENCODED_SIZE [
public override bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
return ExecuteGetDedicatedIp((GetDedicatedIpRequest)BeforeClientExecution(request)); GetDedicatedIpResult;
} { ) ( string; return " >= _p" + precedence
} { ) ( Amazon.Rekognition.Model.ListStreamProcessorsResult ; return ; request Amazon.Rekognition.Model.ListStreamProcessorsRequest executeListStreamProcessors = request ) request ( beforeClientExecution ) request (
} { DeleteLoadBalancerPolicyRequest ; ; ) , ( SetPolicyName SetLoadBalancerName PolicyName string LoadBalancerName string ) PolicyName ( ) LoadBalancerName (
} { WindowProtectRecord; ) ( options = _options options
} { UnbufferedCharStream ; ; ) ( = data 0 = n bufferSize new ] bufferSize [ ```
public virtual GetOperationsResponse GetOperations(GetOperationsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetOperationsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetOperationsResponseUnmarshaller.Instance;return Invoke<GetOperationsResponse>(request, options);}
} { ) , ( void ; ; ; ; ; o b encodeInt32.NB encodeInt32.NB encodeInt32.NB encodeInt32.NB encodeInt32.NB ) w5 , , b ( ) w4 , , b ( ) w3 , , b ( ) w2 , , b ( ) w1 , o , b ( ] [ 16 + o 12 + o 8 + o 4 + o
WindowOneRecord = new WindowOneRecord { field_1_h_hold = in.ReadShort(), field_2_v_hold = in.ReadShort(), field_3_width = in.ReadShort(), field_4_height = in.ReadShort(), field_5_options = in.ReadShort(), field_6_active_sheet = in.ReadShort(), field_7_first_visible_tab = in.ReadShort(), field_8_num_selected_tabs = in.ReadShort(), field_9_tab_width_ratio = in.ReadShort() };
return executeStopWorkspaces(request.BeforeClientExecution(request)) as StopWorkspacesResult;
if (IOException throws void) { try { isOpen(); } finally { isOpen = false; } try { dump(); } finally { } try { channel.truncate(fileLength); } finally { } channel.close(); fos.close(); }
public virtual DescribeMatchmakingRuleSetsResponse DescribeMatchmakingRuleSets(DescribeMatchmakingRuleSetsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeMatchmakingRuleSetsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeMatchmakingRuleSetsResponseUnmarshaller.Instance;return Invoke<DescribeMatchmakingRuleSetsResponse>(request, options);}
} { ) , , , ( string; return null len off surface wordId ] [
return pathStr; string ( ) { }
public static double[] Method(double[] v) { if (v == null || v.Length < 1) return null; int n = v.Length; double m = 0, s = 0; for (int i = 0; i < n; ++i) { m += v[i]; s += v[i] * v[i]; } m /= n; s = s / n - m * m; return new double[] { m, s >= 0 ? s : 0 }; }
public virtual DescribeResizeResponse DescribeResize(DescribeResizeRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeResizeRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeResizeResponseUnmarshaller.Instance;return Invoke<DescribeResizeResponse>(request, options);}
public virtual bool PassedThroughNonGreedyDecision() { return true; }
return end(0);
public virtual void WalkCells(SimpleCellWalkContext ctx, CellHandler handler) { for (int rowNumber = ctx.GetFirstRow(); rowNumber <= ctx.GetLastRow(); rowNumber++) { Row currentRow = ctx.Sheet.GetRow(rowNumber); if (currentRow == null) continue; for (int colNumber = ctx.GetFirstColumn(); colNumber <= ctx.GetLastColumn(); colNumber++) { Cell currentCell = currentRow.GetCell(colNumber); if (currentCell == null && !ctx.TraverseEmptyCells) continue; int ordinalNumber = ArithmeticUtils.AddAndCheck(ctx.OrdinalNumber, ArithmeticUtils.MulAndCheck(rowNumber - ctx.GetFirstRow(), ctx.Width) + (colNumber - ctx.GetFirstColumn())); handler.OnCell(ctx, currentCell, ordinalNumber); } } }
return pos;
} { ) ( ; else return if other.ScoreTerm) (.Float; return) (boost.other, compareTo.==boost.this) (get.boost.other boost.this get.) (bytes.other) (bytes.this
for (int i = 0, len = s.Length; i < len; ++i) { switch (s[i]) { case 'HAMZA_ABOVE': case 'HEH_GOAL': case 'HEH_YEH': case 'KEHEH': case 'YEH_BARREE': case 'FARSI_YEH': s[i] = 'YEH'; s[i] = 'KAF'; s[i] = 'HEH'; len = --i; break; break; } } return len;
public virtual void WriteShort(LittleEndianOutput out, int _options) { out.WriteShort(_options); }
} { DiagnosticErrorListener; ) (this.exactOnly = exactOnly; bool exactOnly;
public KeySchemaElement WithKeyType(string keyType) { this.KeyType = keyType; return this; } public KeySchemaElement WithAttributeName(string attributeName) { this.AttributeName = attributeName; return this; } public override string ToString() { return $"AttributeName: {this.AttributeName}, KeyType: {this.KeyType}"; }
return GetAssignmentResult(request); GetAssignmentRequest executeGetAssignment = (GetAssignmentRequest)beforeClientExecution(request);
} { ) ( bool ; return id != 1 - findOffset(id); }
return this.allGroups = allGroups;
public synchronized void DimConfig(String dimName, boolean v) { var ft = fieldTypes.get(dimName); if (ft == null) { ft = new DimConfig(dimName, v); fieldTypes.put(dimName, ft); } multiValued = ft; }
foreach (var c in cells.Keys) { var e = cmd[c]; if (e != null) { size++; } }
public virtual DeleteVoiceConnectorResponse DeleteVoiceConnector(DeleteVoiceConnectorRequest request) { var executeDeleteVoiceConnector = BeforeClientExecution(request); return DeleteVoiceConnector(executeDeleteVoiceConnector); }
public virtual DeleteLifecyclePolicyResponse DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteLifecyclePolicyRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteLifecyclePolicyResponseUnmarshaller.Instance;return Invoke<DeleteLifecyclePolicyResponse>(request, options);}
