} ); field_1_vcenter { out.WriteShort(); } ILittleEndianOutput out1 void
if (size.src == 0) return; for (srcDirIdx = 0; srcDirIdx < tailDirIdx.src; ++srcDirIdx) directory.src[addAll](srcDirIdx); if (tailBlkIdx.src != 0) tailBlock.src[addAll](0, BLOCK_SIZE);
if (currentBlock != null) { if (upto == blockSize) { AddBlock(currentBlock); currentBlock = new byte[blockSize]; upto = 0; } b = currentBlock[upto++]; }
return ObjectId;
return ExecuteDeleteDomainEntry(BeforeClientExecution(request) as DeleteDomainEntryRequest) as DeleteDomainEntryResult;
return (ramBytesUsed.termsDictOffsets != null ? ramBytesUsed.termsDictOffsets : 0) + (ramBytesUsed.termOffsets != null ? ramBytesUsed.termOffsets : 0);
public final string DecodeMessage(byte[] raw, int length) { var msgB = RawParseUtils.TagMessage(raw, 0); if (msgB < 0) return ""; return RawParseUtils.GuessEncoding(raw, msgB, length); }
POIFSFileSystem fs = new POIFSFileSystem(); fs.Header.SetBATCount(1); fs.Header.SetBATArray(new int[] { 1 }); BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); fs.BATBlocks.Add(bb); bb.SetNextBlock(POIFSConstants.END_OF_CHAIN, 0); bb.SetNextBlock(POIFSConstants.FAT_SECTOR_BLOCK, 1); fs.PropertyTable.SetStartBlock(0);
} length.Slice < upto.Assert(); address = offset0; BYTE_BLOCK_MASK.ByteBlockPool & address = upto; Debug.Assert(slice != null); BYTE_BLOCK_SHIFT.ByteBlockPool >> address; buffers.Pool = slice; address();
return this.Path(path); SubmoduleAddCommand path = this;
return ExecuteListIngestions(BeforeClientExecution(request) as ListIngestionsRequest) as ListIngestionsResult;
} ; ) lexState ( SwitchTo ; ) stream ( { ) lexState , stream CharStream ( QueryParserTokenManager
public virtual GetShardIteratorResponse GetShardIterator(GetShardIteratorRequest request){request = BeforeClientExecution(request);return ExecuteGetShardIterator(request);}
base("aegis", "ModifyStrategy", "2016-11-11", "vipaegis", MethodType.POST);
lock (this) { if (in == null) { throw new IOException("InputStreamReader is closed"); } try { return bytes.hasRemaining() || in > 0; } catch (Exception e) { return false; } }
return new EscherOptRecord();
public virtual void SynchronizedMethod(char[] buffer, int offset, int length) { if (buffer == null) { throw new NullPointerException("buffer == null"); } Arrays.CheckOffsetAndCount(buffer.Length, offset, length); if (length == 0) { return; } int copylen = 0; for (int i = 0; i < length; i++) { this.buffer[copylen + offset + i] = buffer[copylen + i]; copylen += pos; } }
} sentenceOp = this.sentenceOp = new NLPSentenceDetectorOp(OpenNLPSentenceBreakIterator);
} ; ) ) null ) object ( ( valueOf . string : str ? null != str ( write { ) str string ( void
} ; functionName = this.functionName ; ) cause , functionName ( base { ) cause NotImplementedException , functionName string ( NotImplementedFunctionException
} ; ) ( GetValue . ) ( NextEntry . base return { ) (  V
public final void Read(byte[] b, int offset, int len, bool useBuffer) throws IOException { if (len <= 0) return; if (useBuffer && len < bufferSize) { if (bufferPosition >= bufferLength) { refill(); if (bufferLength <= 0) throw new EOFException("read past EOF: " + this); } int available = bufferLength - bufferPosition; if (len <= available) { System.arraycopy(buffer, bufferPosition, b, offset, len); bufferPosition += len; return; } else { System.arraycopy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition = bufferLength; } } while (len > 0) { long after = bufferStart + bufferPosition + len; if (after > length) { throw new EOFException("read past EOF: " + this); } if (len < bufferLength) { refill(); if (bufferLength <= 0) throw new EOFException("read past EOF: " + this); int available = bufferLength - bufferPosition; int toCopy = Math.min(len, available); System.arraycopy(buffer, bufferPosition, b, offset, toCopy); bufferPosition += toCopy; offset += toCopy; len -= toCopy; } else { readInternal(b, offset, len); bufferStart += len; return; } } }
return ExecuteTagQueue(new TagQueueRequest { BeforeClientExecution = request });
throw new NotSupportedException();
public virtual ModifyCacheSubnetGroupResponse ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyCacheSubnetGroupRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyCacheSubnetGroupResponseUnmarshaller.Instance;return Invoke<ModifyCacheSubnetGroupResponse>(request, options);}
string language = "", country = "", variant = ""; StringTokenizer st = new StringTokenizer(params, ","); if (st.HasMoreTokens()) language = st.NextToken(); if (st.HasMoreTokens()) country = st.NextToken(); if (st.HasMoreTokens()) variant = st.NextToken();
public virtual DeleteDocumentationVersionResponse DeleteDocumentationVersion(DeleteDocumentationVersionRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteDocumentationVersionRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteDocumentationVersionResponseUnmarshaller.Instance;return Invoke<DeleteDocumentationVersionResponse>(request, options);}
if (!(obj is FacetLabel other)) { return false; } if (length != other.length) { return false; } for (int i = length - 1; i >= 0; --i) { if (!components[i].Equals(other.components[i])) { return false; } } return true;
return ExecuteGetInstanceAccessDetails(BeforeClientExecution(request));
var shape = new HSSFPolygon(this, anchor) { Parent = this, Anchor = anchor }; shapes.Add(shape); return shape;
return Workbook.GetSheetName(sheetIndex);
return ExecuteGetDashboard(BeforeClientExecution(request));
public virtual AssociateSigninDelegateGroupsWithAccountResponse AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request){request = beforeClientExecution(request);return executeAssociateSigninDelegateGroupsWithAccount(request);}
void MulBlankRecord() { for (int j = 0; j < getNumColumns.mbr; j++) { var br = new BlankRecord(); br.setColumn(getFirstColumn.mbr + j); getRow.mbr.setRow(br); getXFAt.mbr.setXFIndex(br); insertCell(br); } }
public static string StringToString(string str){StringBuilder sb = new StringBuilder();sb.Append("\\Q");int apos = 0;int k;while ((k = str.IndexOf("\\E", apos)) >= 0){sb.Append(str.Substring(apos, k - apos));sb.Append("\\\\E\\Q");apos = k + 2;}sb.Append(str.Substring(apos));sb.Append("\\E");return sb.ToString();}
throw new ReadOnlyBufferException();
} 0 = _reserved2Byte; 0 = _reserved1Short; 0 = _reserved0Int; vv = _arrayValues; } } ] c [ rowData = ] ) r, c ( getValueIndex [ vv { ++c; nColumns < c; 0 = c; for ] r [ values2d = rowData ] [ object { ++r; nRows < r; 0 = r; for ] _nRows * _nColumns [ object new = vv ] [ object; nRows = _nRows; nColumns = _nColumns; values2d.Length = nRows; values2d[0].Length = nColumns; { values2d ] [ ] [ object ( ArrayPtg
public virtual GetIceServerConfigResponse GetIceServerConfig(GetIceServerConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetIceServerConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = GetIceServerConfigResponseUnmarshaller.Instance;return Invoke<GetIceServerConfigResponse>(request, options);}
return $"{getValueAsString} [{getName.GetType()}]";
return "ToChildBlockJoinQuery(" + parentQuery.ToString() + ")";
} ; ) ( IncrementAndGet . RefCount { ) ( void final public
public virtual UpdateConfigurationSetSendingEnabledResponse UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){request = BeforeClientExecution(request);return ExecuteUpdateConfigurationSetSendingEnabled(request);}
} return (INT_SIZE.LittleEndianConsts * getXBATEntriesPerBlock());
} } ; ) _multiplierShift . tp , _multiplicand . tp ( mulShift { else } ; ) _divisorShift . tp , _divisor . tp ( mulShift { ) 0 < pow10 ( if ; ) ) pow10 ( Math.Abs ( TenPower.GetInstance = tp TenPower { ) pow10 ( void
StringBuilder b = new StringBuilder(); int l = length; for (int i = 0; i < l; i++) { b.Append(File.separatorChar); b.Append(getComponent(i)); } return b.ToString();
this.fetcher = new ECSMetadataServiceCredentialsFetcher(InstanceProfileCredentialsProvider.Instance); this.fetcher.SetRoleName(roleName); return this;
} ProgressMonitor pm = progressMonitor; void
} } ; ) ( ParseEntry ) ) ( EOF ! ( if ; 0 = ptr { ) ) ( First ! ( if { ) ( void
throw new NoSuchElementException(); return previous.Iterator; if (start >= previousIndex.Iterator) { E }
} ; newPrefix . this return { ) ( string
for (int i = 0; i < mSize; i++) if (mValues[i] == value) return i; return -1;
var terms = new CharArraySet(dictionary, ignoreCase); var deduped = new List<CharsRef>(); foreach (CharsRef s in stems) { if (!terms.contains(s)) { deduped.add(s); terms.add(s); } } return deduped; if (stems.size() < 2) { return stems; } var stems = new List<CharsRef>(word, length);
} ; ) request ( executeGetGatewayResponses return ; ) request ( beforeClientExecution = request { ) request GetGatewayResponsesRequest ( GetGatewayResponsesResult
} ; ) blockMask & pos ( ) ( = currentBlockUpto ; ] currentBlockIndex [ blocks = currentBlock ; ) blockBits >> pos ( ) ( = currentBlockIndex { ) pos ( void
} s; return; s += ptr; })) n, 0 (Math.Max, (Math.Min(available(), n)) = s { n (
})); bootstrapActionConfig.SetBootstrapActionConfig(new BootstrapActionConfig(bootstrapActionDetail));
void Write(LittleEndianOutput out) { out.WriteShort(field_1_row); out.WriteShort(field_2_col); out.WriteShort(field_3_flags); out.WriteShort(field_4_shapeid); out.WriteShort(field_6_author.Length); out.WriteByte(field_5_hasMultibyte ? (byte)0x01 : (byte)0x00); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, out); } else { StringUtil.PutCompressedUnicode(field_6_author, out); } if (field_7_padding != null) { out.WriteByte(field_7_padding.Value); } }
return string.LastIndexOf(count);
} ; ) object ( AddLastImpl return { ) object E (  bool
do { } while (!compareAndSet.State(res, src)); ConfigSnapshot unsetSection = new ConfigSnapshot(section, subsection, src);
public final String TagName() { return ""; }
} ; ) element , index ( Add . Subrecords { ) element SubRecord , index ( void
lock (mutex) { return (o) => { remove(o); return true; }; }
return new DoubleMetaphoneFilter(input, maxCodeLength, inject);
return (inCoreLength);
} newValue = value; newValue boolean void;
newSource = this.newSource; oldSource = this.oldSource; Pair<ContentSource, ContentSource> newSourceOldSource;
if (i <= count) throw new ArrayIndexOutOfBoundsException(); return entries[i];
public virtual CreateRepoResponse CreateRepo(CreateRepoRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateRepoRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateRepoResponseUnmarshaller.Instance;return Invoke<CreateRepoResponse>(request, options);}
} return deltaBaseAsOffset; boolean ( )
if (modCount.list == expectedModCount) { if (lastLink != null) { Link<ET> next = lastLink.next; Link<ET> previous = lastLink.previous; previous.next = next; next.previous = previous; lastLink = null; ++expectedModCount; --size.list; ++modCount.list; } else { throw new InvalidOperationException(); } } else { throw new InvalidOperationException(); }
return executeMergeShards(request); MergeShardsResult request = beforeClientExecution(request); MergeShardsRequest request;
public virtual AllocateHostedConnectionResponse AllocateHostedConnection(AllocateHostedConnectionRequest request){var options = new InvokeOptions();options.RequestMarshaller = AllocateHostedConnectionRequestMarshaller.Instance;options.ResponseUnmarshaller = AllocateHostedConnectionResponseUnmarshaller.Instance;return Invoke<AllocateHostedConnectionResponse>(request, options);}
} ; start return { ) (
public static readonly WeightedTerm[] Query(Query query) => query.GetTerms(false);
throw new ReadOnlyBufferException();
for (int i = 0; i < iterations; i++) { values[valuesOffset++] = (blocks[blocksOffset++] & 0xFF) << 2 | (byte0 >>> 6); values[valuesOffset++] = (byte0 & 3) << 4 | (byte1 >>> 4); values[valuesOffset++] = (byte1 & 15) << 2 | (byte2 >>> 2); values[valuesOffset++] = byte2 & 63; }
if (s == null) throw new ArgumentException(); if (s.Equals("") || s.Equals("/")) { getPath = s; } else { getHost = s; } if (scheme.Equals("file") || LOCAL_FILE.matcher(s).matches()) { elements = s.Split(new[] { Path.DirectorySeparatorChar + "/[" + Path.AltDirectorySeparatorChar + "]" }, StringSplitOptions.None); } else { elements = s.Split(new[] { "/+" }, StringSplitOptions.None); } if (elements.Length == 0) throw new ArgumentException(); if (Constants.DOT_GIT.Equals(elements[elements.Length - 1])) { result = elements[elements.Length - 2]; } else if (Constants.DOT_GIT_EXT.EndsWith(result)) { result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); } return result;
public virtual DescribeNotebookInstanceLifecycleConfigResponse DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request){request = beforeClientExecution(request);return executeDescribeNotebookInstanceLifecycleConfig(request);}
return this.accessKeySecret;
return ExecuteCreateVpnConnection(BeforeClientExecution(request) as CreateVpnConnectionRequest);
} ; ) request ( ExecuteDescribeVoices return ; ) request ( BeforeClientExecution = request { ) request DescribeVoicesRequest ( DescribeVoicesResult
return ExecuteListMonitoringExecutions(BeforeClientExecution(new ListMonitoringExecutionsRequest())) as ListMonitoringExecutionsResult;
public DescribeJobRequest(string jobId, string vaultName){SetJobId(jobId);SetVaultName(vaultName);}
public virtual EscherRecord GetEscherRecords(int index){return escherRecords[index];}
return ExecuteGetApis(BeforeClientExecution(request));
public virtual DeleteSmsChannelResponse DeleteSmsChannel(DeleteSmsChannelRequest request){request = BeforeClientExecution(request);return ExecuteDeleteSmsChannel(request);}
return (TrackingRefUpdate)trackingRefUpdate;
} ; ) ) b ( valueOf . String ( Print { ) b bool ( void
return QueryNode.Get().GetChildren();
} workdirTreeIndex = index.this { } workdirTreeIndex(NotIgnoredFilter);
} ; ) ( ReadShort . In = Field_1_FormatFlags { ) In RecordInputStream ( AreaRecord
HTTPS.ProtocolType("cloudphoto", "GetThumbnail", "2017-07-11", "CloudPhoto", base.GetThumbnailRequest());
return ExecuteDescribeTransitGatewayVpcAttachments(BeforeClientExecution(new DescribeTransitGatewayVpcAttachmentsRequest()));
return ExecutePutVoiceConnectorStreamingConfiguration(BeforeClientExecution(request) as PutVoiceConnectorStreamingConfigurationRequest) as PutVoiceConnectorStreamingConfigurationResult;
} ); dim => get.PrefixToOrdRange(return dim as string, OrdRange);
} ; ) symbol , ) ( GetType().Name , "LexerNoViableAltException('%s')" , ) ( CultureInfo.InvariantCulture ( string.Format return } ; ) false , symbol ( Utils.EscapeWhitespace = symbol ; ) ) startIndex , startIndex ( Interval.Of ( GetText() ) ( GetInputStream = symbol { ) ) ( size ) ( GetInputStream < startIndex && 0 >= startIndex ( if ; "" = symbol string { ) ( string
} ; ) ( PeekFirstImpl return { ) (  E
} ); request.ExecuteCreateWorkspaces(); return; request.BeforeClientExecution = request; CreateWorkspacesRequest request = new CreateWorkspacesRequest(); CreateWorkspacesResult result;
} ; ) ( Copy return { ) (  NumberFormatIndexRecord
public virtual DescribeRepositoriesResponse DescribeRepositories(DescribeRepositoriesRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeRepositoriesRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeRepositoriesResponseUnmarshaller.Instance;return Invoke<DescribeRepositoriesResponse>(request, options);}
SparseIntArray initialCapacity = new SparseIntArray(ArrayUtils.IdealIntArraySize(initialCapacity)); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0;
return new HyphenatedWordsFilter(input);
public virtual CreateDistributionWithTagsResponse CreateDistributionWithTags(CreateDistributionWithTagsRequest request){request = BeforeClientExecution(request);return ExecuteCreateDistributionWithTags(request);}
public RandomAccessFile(string fileName, string mode) : base(fileName, mode) { }
public virtual DeleteWorkspaceImageResponse DeleteWorkspaceImage(DeleteWorkspaceImageRequest request){request = BeforeClientExecution(request);return ExecuteDeleteWorkspaceImage(request);}
public static string WriteHex(int value) { var sb = new StringBuilder(); sb.AppendFormat("{0:X}", value); return sb.ToString(); }
} ); request.ExecuteUpdateDistribution(); return request.BeforeClientExecution(request = new UpdateDistributionRequest()); UpdateDistributionResult
return index == HSSFColor.HSSFColorPredefined.AUTOMATIC.GetIndex() ? HSSFColor.HSSFColorPredefined.AUTOMATIC.GetColor() : (b == null ? null : _palette.GetColor(index, b));
throw new NotImplementedFunctionException(_functionName); ValueEval[] operands, ValueEval srcRow, ValueEval srcCol;
} ; ) field_2_sheet_table_index ) ( ( WriteShort . Out ; ) field_1_number_crn_records ) ( ( WriteShort . Out { ) Out LittleEndianOutput ( void
public virtual DescribeDBEngineVersionsResponse DescribeDBEngineVersions(DescribeDBEngineVersionsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeDBEngineVersionsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeDBEngineVersionsResponseUnmarshaller.Instance;return Invoke<DescribeDBEngineVersionsResponse>(request, options);}
} fontIndex = _fontIndex.this; character = _character.this { } fontIndex, character (FormatRun);
public static char[] Process(char[] chars, int offset, int length){char[] result = new char[2 * length];int resultIndex = 0;for (int i = offset; i < offset + length; i++){char ch = chars[i];result[resultIndex++] = (char)(ch >> 8);result[resultIndex++] = (char)ch;}return result;}
public virtual UploadArchiveResponse UploadArchive(UploadArchiveRequest request){request = beforeClientExecution(request);return executeUploadArchive(request);}
return GetHiddenTokensToLeft(tokenIndex)?.Cast<Token>().ToList();
public override bool Equals(object obj) { if (this == obj) return true; if (!base.Equals(obj)) return false; if (GetType() != obj.GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (!compiled.Equals(other.compiled)) return false; if (term == null) { if (other.term != null) return false; } else if (!term.Equals(other.term)) return false; return true; }
SpanQuery[] spanQueries = new SpanQuery[weightBySpanQuery.Count]; int i = 0; foreach (SpanQuery sqi in weightBySpanQuery.Keys) { spanQueries[i++] = sqi; } if (spanQueries.Length == 1) return spanQueries[0]; SpanQuery sq = new SpanOrQuery(spanQueries); if (boost != 1f) sq = new SpanBoostQuery(sq, boost); return sq;
return new StashCreateCommand();
} ) FieldInfo fieldName { return Get.ByName(fieldName); }
public virtual DescribeEventSourceResult ExecuteDescribeEventSource(DescribeEventSourceRequest request){request = BeforeClientExecution(request);return Invoke<DescribeEventSourceResult>(request);}
public virtual GetDocumentAnalysisResult GetDocumentAnalysis(GetDocumentAnalysisRequest request){request = beforeClientExecution(request);return executeGetDocumentAnalysis(request);}
} ); request.ExecuteCancelUpdateStack(); return; }); request.BeforeClientExecution = request => { request = new CancelUpdateStackRequest(); CancelUpdateStackResult
public virtual ModifyLoadBalancerAttributesResponse ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request){request = BeforeClientExecution(request);return ExecuteModifyLoadBalancerAttributes(request);}
return ExecuteSetInstanceProtection(BeforeClientExecution(request) as SetInstanceProtectionRequest) as SetInstanceProtectionResult;
public virtual ModifyDBProxyResponse ModifyDBProxy(ModifyDBProxyRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyDBProxyRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyDBProxyResponseUnmarshaller.Instance;return Invoke<ModifyDBProxyResponse>(request, options);}
void AddOutput(int len, int offset, object output, int posLength, int endOffset) { if (outputs.Length == count) { outputs = ArrayUtil.Grow(outputs, count + 1); } if (endOffsets.Length == count) { int[] next = new int[ArrayUtil.Oversize(count + 1, sizeof(int))]; System.Array.Copy(endOffsets, 0, next, 0, count); endOffsets = next; } if (posLengths.Length == count) { int[] next = new int[ArrayUtil.Oversize(count + 1, sizeof(int))]; System.Array.Copy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } ((CharsRefBuilder)outputs[count]).CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; count++; }
; ) HTTPS.ProtocolType ( ; ) "cloudphoto", "FetchLibraries", "2017-07-11", "CloudPhoto" ( base { ) ( FetchLibrariesRequest
throw new System.NotImplementedException();
} ; out = out . this { ) out OutputStream ( FilterOutputStream
; ) PUT . MethodType ( setMethod ; ) "/clusters/[ClusterId]" ( ; ) "csk" , "ScaleCluster" , "2015-12-15" , "CS" ( base { ) ( ScaleClusterRequest
return new DataValidationConstraint(operatorType, formula1, formula2);
public virtual ListObjectParentPathsResponse ListObjectParentPaths(ListObjectParentPathsRequest request){request = BeforeClientExecution(request);return ExecuteListObjectParentPaths(request);}
return ExecuteDescribeCacheSubnetGroups((DescribeCacheSubnetGroupsRequest)BeforeClientExecution(request));
} ; ) flag , field_5_options ( SetShortBoolean.SharedFormula = field_5_options { ) flag bool ( void
} ; reuseObjects return { ) (  bool
var t = new ErrorNodeImpl(badToken); this.setParent(t); t.addAnyChild(badToken);
} } ; ) args + "Unknown parameters: " ( new System.ArgumentException throw { ) ) ( args.IsEmpty ! ( if ; ) args ( { ) args > Dictionary<string, string> ( LatvianStemFilterFactory
ExecuteRemoveSourceIdentifierFromSubscriptionRequest(BeforeClientExecution(new RemoveSourceIdentifierFromSubscriptionRequest()));
public static TokenFilterFactory NewInstance(string name, Map<string, string> args, ClassLoader loader) { return new TokenFilterFactory(name, args); }
; ) HTTPS.ProtocolType ( ; ) "cloudphoto", "AddAlbumPhotos", "2017-07-11", "CloudPhoto" ( base { ) ( AddAlbumPhotosRequest
return ExecuteGetThreatIntelSet(BeforeClientExecution(new GetThreatIntelSetRequest()));
return new Binary((clone.b), (clone.a(RevFilter)));
} ArmenianStemmer is o return (o is object);
public virtual bool HasArray() { return true; }
public virtual UpdateContributorInsightsResult UpdateContributorInsights(UpdateContributorInsightsRequest request){request = BeforeClientExecution(request);return ExecuteUpdateContributorInsights(request);}
} null = writeProtect; null = fileShare; writeProtect(remove.records); fileShare(remove.records) { } void
} expand = this.expand; analyzer, dedup(super(analyzer, expand, dedup), SolrSynonymParser);
public virtual RequestSpotInstancesResponse RequestSpotInstances(RequestSpotInstancesRequest request){request = beforeClientExecution(request);return executeRequestSpotInstances(request);}
} ; ) ( GetObjectData . ) ( FindObjectRecord return { ) (  ] [
return ExecuteGetContactAttributes(BeforeClientExecution(request) as GetContactAttributesRequest) as GetContactAttributesResult;
return $"{getKey}: {getValue}";
public virtual ListTextTranslationJobsResponse ListTextTranslationJobs(ListTextTranslationJobsRequest request){request = beforeClientExecution(request);return executeListTextTranslationJobs(request);}
public virtual GetContactMethodsResponse GetContactMethods(GetContactMethodsRequest request){request = BeforeClientExecution(request);return ExecuteGetContactMethods(request);}
public static FunctionMetadata GetInstance(string name) { FunctionMetadata fd = GetFunctionByNameInternal(name); if (fd == null) { fd = GetFunctionByNameInternal(name); if (fd == null) return null; } return fd; }
public virtual DescribeAnomalyDetectorsResponse DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request){request = BeforeClientExecution(request);return ExecuteDescribeAnomalyDetectors(request);}
public static string Message(ObjectId changeId, string insertId) { return message; }
throw new MissingObjectException(copy.ObjectId, JGitText.Get().UnknownObjectType2); if (typeHint == OBJ_ANY) { if (sz < 0) { sz = db.GetObjectSize(objectId, typeHint); } return sz; }
public virtual ImportInstallationMediaResponse ImportInstallationMedia(ImportInstallationMediaRequest request){var options = new InvokeOptions();options.RequestMarshaller = ImportInstallationMediaRequestMarshaller.Instance;options.ResponseUnmarshaller = ImportInstallationMediaResponseUnmarshaller.Instance;return Invoke<ImportInstallationMediaResponse>(request, options);}
public virtual PutLifecycleEventHookExecutionStatusResponse PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request){var options = new InvokeOptions();options.RequestMarshaller = PutLifecycleEventHookExecutionStatusRequestMarshaller.Instance;options.ResponseUnmarshaller = PutLifecycleEventHookExecutionStatusResponseUnmarshaller.Instance;return Invoke<PutLifecycleEventHookExecutionStatusResponse>(request, options);}
} ; ) ) ( ReadDouble . In ( { ) In LittleEndianInput ( NumberPtg
public virtual GetFieldLevelEncryptionConfigResponse GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request){request = beforeClientExecution(request);return executeGetFieldLevelEncryptionConfig(request);}
return ExecuteDescribeDetector((DescribeDetectorRequest)BeforeClientExecution(request));
public virtual ReportInstanceStatusResult ReportInstanceStatus(ReportInstanceStatusRequest request){request = beforeClientExecution(request);return executeReportInstanceStatus(request);}
return ExecuteDeleteAlarm(BeforeClientExecution(request) as DeleteAlarmRequest) as DeleteAlarmResult;
return new PortugueseStemFilter(input);
; ] ENCODED_SIZE [ new = reserved { ) ( FtCblsSubRecord
public override bool RemoveObject(object obj){lock(mutex){return c.Remove(obj);}}
public virtual GetDedicatedIpResponse GetDedicatedIp(GetDedicatedIpRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDedicatedIpRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDedicatedIpResponseUnmarshaller.Instance;return Invoke<GetDedicatedIpResponse>(request, options);}
} ; " >= _p" + precedence return { ) (  string
return ExecuteListStreamProcessors(BeforeClientExecution(request));
public DeleteLoadBalancerPolicyRequest(string policyName, string loadBalancerName){PolicyName = policyName;LoadBalancerName = loadBalancerName;}
} _options = options; WindowProtectRecord(options);
UnbufferedCharStream n = new UnbufferedCharStream(bufferSize) { data = bufferSize; };
public virtual GetOperationsResponse GetOperations(GetOperationsRequest request){request = BeforeClientExecution(request);return ExecuteGetOperations(request);}
} ; ) w5 , 16 + o , b ( EncodeInt32.NB ; ) w4 , 12 + o , b ( EncodeInt32.NB ; ) w3 , 8 + o , b ( EncodeInt32.NB ; ) w2 , 4 + o , b ( EncodeInt32.NB ; ) w1 , o , b ( EncodeInt32.NB { ) o , b ] [ ( void
public WindowOneRecord(RecordInputStream in1){field_1_h_hold = in1.ReadShort();field_2_v_hold = in1.ReadShort();field_3_width = in1.ReadShort();field_4_height = in1.ReadShort();field_5_options = in1.ReadShort();field_6_active_sheet = in1.ReadShort();field_7_first_visible_tab = in1.ReadShort();field_8_num_selected_tabs = in1.ReadShort();field_9_tab_width_ratio = in1.ReadShort();}
public virtual StopWorkspacesResponse StopWorkspaces(StopWorkspacesRequest request){request = beforeClientExecution(request);return executeStopWorkspaces(request);}
} } } } ; ) ( close . fos { finally } ; ) ( close . channel { try { finally } ; ) fileLength ( truncate . channel { try { finally } ; ) ( dump { try ; false = isOpen { ) isOpen ( if { IOException throws ) ( void
} ; ) request ( executeDescribeMatchmakingRuleSets return ; ) request ( beforeClientExecution = request { ) request DescribeMatchmakingRuleSetsRequest ( DescribeMatchmakingRuleSetsResult
} return null; String(wordId, surface[off, len]);
} return pathStr; }
public static double R(double[] v) { if (v != null && v.Length >= 1) { int n = v.Length; double s = 0, m = 0; for (int i = 0; i < n; ++i) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; ++i) { s += (v[i] - m) * (v[i] - m); } return n == 1 ? 0 : s / (n - 1); } return double.NaN; }
public virtual DescribeResizeResult DescribeResize(DescribeResizeRequest request){request = beforeClientExecution(request);return executeDescribeResize(request);}
public final bool PassedThroughNonGreedyDecision() { return; }
} ; ) 0 ( end return { ) (
void Handler(SimpleCellWalkContext ctx, CellHandler handler) { int firstRow = range.GetFirstRow(); int lastRow = range.GetLastRow(); int firstColumn = range.GetFirstColumn(); int lastColumn = range.GetLastColumn(); int width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); Row currentRow = null; Cell currentCell = null; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ctx.rowNumber++) { currentRow = sheet.GetRow(ctx.rowNumber); if (currentRow == null) { continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ctx.colNumber++) { currentCell = currentRow.GetCell(ctx.colNumber); if (currentCell == null) { continue; } if (!currentCell.IsEmpty()) { handler.OnCell(ctx, currentCell); } } } }
} ; pos return { ) (
if (boost.other == boost.this) return other.ScoreTerm(get.bytes.this.compareTo(get.bytes.other)); else return boost.this > boost.other ? this : other;
for (int i = 0; i < len; i++) { switch (s[i]) { case HAMZA_ABOVE: break; case HEH: s[i] = HEH_GOAL; break; case HEH_YEH: break; case KAF: s[i] = KEHEH; break; case YEH: s[i] = YEH_BARREE; break; case FARSI_YEH: break; } }
void WriteShort(LittleEndianOutput out) { out.WriteShort(_options); }
} exactOnly = this.exactOnly; exactOnly boolean (DiagnosticErrorListener)
public KeySchemaElement(string attributeName, KeyType keyType) { AttributeName = attributeName; KeyType = keyType; }
return beforeClientExecution(request => executeGetAssignment(request)) as GetAssignmentResult;
return findOffset(id) != -1;
return this; allGroups = this.allGroups; return allGroups.GroupingSearch(allGroups);
public override void SynchronizedVoid(string dimName, bool v) { DimConfig ft = fieldTypes.Get(dimName); if (ft == null) { ft = new DimConfig(); fieldTypes.Put(dimName, ft); } ft.MultiValued = v; }
Iterator<Character> i = cells.keySet().iterator();size = 0;for (; i.hasNext();){Character c = i.next();Cell e = cmd.e(c);if (e >= 0){size++;}}
public virtual DeleteVoiceConnectorResponse DeleteVoiceConnector(DeleteVoiceConnectorRequest request){request = beforeClientExecution(request);return executeDeleteVoiceConnector(request);}
public virtual DeleteLifecyclePolicyResponse DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request){request = BeforeClientExecution(request);return ExecuteDeleteLifecyclePolicy(request);}
