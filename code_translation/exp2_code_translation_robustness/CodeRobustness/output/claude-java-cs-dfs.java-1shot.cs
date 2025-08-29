out.WriteShort(field_1_vcenter);
void BlockList<T> src) { if (src.size == 0) return; int srcDirIdx = 0; for (; srcDirIdx < src.tailDirIdx; srcDirIdx++) { directory[srcDirIdx].src.AddRange(src.directory[srcDirIdx].src, 0, BLOCK_SIZE); } if (src.tailBlkIdx != 0) { tailBlock.src.AddRange(src.tailBlkIdx.src, 0); } }
void(b)if(upto==blockSize){if(currentBlock!=null){addBlock(currentBlock);}currentBlock=new[blockSize];upto=0;}currentBlock[upto]=b;upto++;}
return ObjectId;
public virtual DeleteDomainEntryResponse DeleteDomainEntry(DeleteDomainEntryRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteDomainEntryRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteDomainEntryResponseUnmarshaller.Instance;return Invoke<DeleteDomainEntryResponse>(request, options);}
return ((termOffsets != null) ? termOffsets.RamBytesUsed() : 0) + ((termsDictOffsets != null) ? termsDictOffsets.RamBytesUsed() : 0);
public string final() { byte[] raw = buffer; int msgB = RawParseUtils.tagMessage(raw, 0); if (msgB < 0) { return ""; } return RawParseUtils.decode(guessEncoding(raw, msgB, raw.length)); }
POIFSFileSystem() { _header.SetBATCount(1); _header.SetBATArray(new int[] { 0 }); BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.SetStartBlock(0); }
void(address) { slice = pool.buffers[address >> ByteBlockPool.BYTE_BLOCK_SHIFT]; assert slice != null; address = address & ByteBlockPool.BYTE_BLOCK_MASK; assert slice.length > upto; offset0 = address; }
} ; this return ; path = path . this { ) path string (  SubmoduleAddCommand
} ; ) request ( executeListIngestions return ; ) request ( beforeClientExecution = request { ) request ListIngestionsRequest (  ListIngestionsResult
} ; ) lexState ( SwitchTo ; ) stream ( { ) lexState , stream CharStream ( QueryParserTokenManager
} ; ) request ( ExecuteGetShardIterator return ; ) request ( BeforeClientExecution = request { ) request GetShardIteratorRequest (  GetShardIteratorResult
; ) POST . MethodType (  ; ) "vipaegis" , "ModifyStrategy" , "2016-11-11" , "aegis" ( base { ) ( ModifyStrategyRequest
public bool available() throws IOException { synchronized (lock) { if (in == null) { throw new IOException("InputStreamReader is closed"); } try { return bytes.hasRemaining() || in.available() > 0; } catch (Exception e) { return false; } } }
} ; _optRecord return { ) (  EscherOptRecord
public synchronized int read(byte[] buffer, int offset, int length) { if (buffer == null) { throw new ArgumentNullException("buffer == null"); } Arrays.checkOffsetAndCount(buffer.length, offset, length); if (length == 0) { return 0; } int copylen = count - pos < length ? count - pos : length; for (int i = 0; i < copylen; i++) { buffer[offset + i] = this.buffer.charAt(pos + i); } pos += copylen; return copylen; }
} ; sentenceOp = sentenceOp . this { ) sentenceOp NLPSentenceDetectorOp ( OpenNLPSentenceBreakIterator
} ; ) ) null ) Object ( ( valueOf . String : str ? null != str ( write { ) str String (  void
} ; functionName = functionName . this ; ) cause , functionName ( base { ) cause NotImplementedException , functionName string ( NotImplementedFunctionException
return base.NextEntry().GetValue();
public void ReadInternal(byte[] b, int offset, int len, bool useBuffer) { if (useBuffer && len < bufferSize) { refill(); if (bufferLength < len) { Array.Copy(buffer, 0, b, offset, bufferLength); throw new EndOfStreamException("read past EOF: " + this); } else { Array.Copy(buffer, 0, b, offset, len); bufferPosition = len; } } else { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) { Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } } else { if (available > 0) { Array.Copy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } int after = bufferStart + bufferPosition + len; if (after > length) { throw new EndOfStreamException("read past EOF: " + this); } ReadInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
public virtual TagQueueResponse TagQueue(TagQueueRequest request){var options = new InvokeOptions();options.RequestMarshaller = TagQueueRequestMarshaller.Instance;options.ResponseUnmarshaller = TagQueueResponseUnmarshaller.Instance;return Invoke<TagQueueResponse>(request, options);}
} ; ) ( UnsupportedOperationException new throw { ) (  void
} ; ) request ( ExecuteModifyCacheSubnetGroup return ; ) request ( BeforeClientExecution = request { ) request ModifyCacheSubnetGroupRequest (  CacheSubnetGroup
void SetParams(string parameters) { base.SetParams(parameters); string language = "", country = "", variant = ""; StringTokenizer st = new StringTokenizer(parameters, ","); if (st.HasMoreTokens()) language = st.NextToken(); if (st.HasMoreTokens()) country = st.NextToken(); if (st.HasMoreTokens()) variant = st.NextToken(); }
}; ) request ( executeDeleteDocumentationVersion return ; ) request ( beforeClientExecution = request { ) request DeleteDocumentationVersionRequest ( DeleteDocumentationVersionResult
public override bool Equals(object obj) { if (!(obj is FacetLabel)) { return false; } FacetLabel other = (FacetLabel)obj; if (length != other.length) { return false; } for (int i = length - 1; i >= 0; i--) { if (!components[i].Equals(other.components[i])) { return false; } } return true; }
} ; ) request ( ExecuteGetInstanceAccessDetails return ; ) request ( BeforeClientExecution = request { ) request GetInstanceAccessDetailsRequest (  GetInstanceAccessDetailsResult
HSSFPolygon shape = new HSSFPolygon(this, (HSSFChildAnchor)anchor); shape.setParent(this); shape.setAnchor(anchor); shapes.add(shape); onCreate(shape); return shape;
return getBoundSheetRec(sheetIndex).getSheetname();
public virtual GetDashboardResponse GetDashboard(GetDashboardRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDashboardRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDashboardResponseUnmarshaller.Instance;return Invoke<GetDashboardResponse>(request, options);}
public virtual AssociateSigninDelegateGroupsWithAccountResponse AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request){var options = new InvokeOptions();options.RequestMarshaller = AssociateSigninDelegateGroupsWithAccountRequestMarshaller.Instance;options.ResponseUnmarshaller = AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.Instance;return Invoke<AssociateSigninDelegateGroupsWithAccountResponse>(request, options);}
void MulBlankRecord mbr) { for (int j = 0; j < mbr.getNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.setColumn((short)(mbr.getFirstColumn() + j)); br.setRow(mbr.getRow()); br.setXFIndex(mbr.getXFAt(j)); insertCell(br); } }
public static string quote(string @string) { java.lang.StringBuilder sb = new java.lang.StringBuilder(); sb.append("\\Q"); int apos = 0; int k; while ((k = @string.indexOf("\\E", apos)) >= 0) { sb.append(@string.substring(apos, k + 2)).append("\\\\E\\Q"); apos = k + 2; } return sb.append(@string.substring(apos)).append("\\E").toString(); }
throw new ReadOnlyBufferException();
ArrayPtg(object[][] values2d) { nColumns = values2d[0].Length; nRows = values2d.Length; _nColumns = nColumns; _nRows = nRows; object[] vv = new object[_nRows * _nColumns]; object[] rowData; for (int r = 0; r < nRows; r++) { rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { vv[GetValueIndex(r, c)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
}; ) request ( executeGetIceServerConfig return ; ) request ( beforeClientExecution = request { ) request GetIceServerConfigRequest ( GetIceServerConfigResult
return GetType().Name + " [" + GetValueAsString() + "]";
return "ToChildBlockJoinQuery(" + parentQuery.ToString() + ")";
public final void ( ) { refCount . incrementAndGet ( ) ; }
public virtual UpdateConfigurationSetSendingEnabledResponse UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateConfigurationSetSendingEnabledRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateConfigurationSetSendingEnabledResponseUnmarshaller.Instance;return Invoke<UpdateConfigurationSetSendingEnabledResponse>(request, options);}
} ; INT_SIZE . LittleEndianConsts * ) ( getXBATEntriesPerBlock return { ) (
void(int pow10) { TenPower tp = TenPower.GetInstance(Math.Abs(pow10)); if (pow10 < 0) { MulShift(_divisor.tp, _divisorShift.tp); } else { MulShift(_multiplicand.tp, _multiplierShift.tp); } }
String { StringBuilder b = new StringBuilder(); int l = length(); b.append(File.separatorChar); for(int i = 0; i < l; i++) { b.append(getComponent(i)); if(i < l - 1) { b.append(File.separatorChar); } } return b.toString(); }
InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; this.fetcher.SetRoleName(roleName); return this; }
} ; pm = progressMonitor { ) pm ProgressMonitor (  void
} } ; ) ( parseEntry ) ) ( eof ! ( if ; 0 = ptr { ) ) ( first ! ( if { ) (  void
} ; ) ( java.util.NoSuchElementException new throw } ; ) ( previous . iterator return { ) start >= ) ( previousIndex . iterator ( if { ) (  E
return this.newPrefix;
for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1;
}; deduped return } } ; ) s ( Add . terms ; ) s ( Add . deduped { ) ) s ( Contains . terms ! ( if { ) stems : s CharsRef ( foreach ; ) ( > < ArrayList new = deduped > CharsRef < List ; ) ignoreCase . dictionary , 8 ( CharArraySet new = terms CharArraySet } ; stems return { ) 2 < ) ( Count . stems ( if ; ) length , word ( Stem = stems > CharsRef < List { ) length , ] [ word (  > CharsRef < List
return ExecuteGetGatewayResponses(request = BeforeClientExecution(request));
void pos(int pos) { currentBlockIndex = (pos >> blockBits); currentBlock = blocks[currentBlockIndex]; currentBlockUpto = (pos & blockMask); }
} ; s return ; s += ptr ; ) ) n , 0 ( max . Math , ) ( available ( min . Math ) ( = s { ) n (
}.SetBootstrapActionConfig(bootstrapActionConfig); BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig) { SetBootstrapActionConfig(bootstrapActionConfig);
void (LittleEndianOutput out) { out.WriteShort(field_1_row); out.WriteShort(field_2_col); out.WriteShort(field_3_flags); out.WriteShort(field_4_shapeid); out.WriteShort(field_6_author.Length); out.WriteByte(field_5_hasMultibyte ? 0x01 : 0x00); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, out); } else { StringUtil.PutCompressedUnicode(field_6_author, out); } if (field_7_padding != null) { out.WriteByte(field_7_padding.IntValue); } }
// Cannot translate invalid Java syntax
} ; ) object ( addLastImpl return { ) object E (  bool
do { src = state.Get(); res = unsetSection(src, section, subsection); } while (!state.CompareAndSet(src, res));
public final String tagName() { return ; }
subrecords.Insert(index, element);
public virtual bool remove(object o) { lock (mutex) { return @delegate.remove(o); } }
} ; ) inject , maxCodeLength , input ( DoubleMetaphoneFilter new return { ) input TokenStream (  DoubleMetaphoneFilter
} ; ) ( inCoreLength return { ) (
} ; newValue = value { ) newValue bool (  void
} ; newSource = newSource . this ; oldSource = oldSource . this { ) newSource ContentSource , oldSource ContentSource ( Pair
}; ] i [ entries return ; ) i ( IndexOutOfRangeException new throw ) i <= count ( if { ) i (
; ) PUT . MethodType ( setMethod ; ) "/repos" (  ; ) "cr" , "CreateRepo" , "2016-06-07" , "cr" ( base { ) ( CreateRepoRequest
} ; deltaBaseAsOffset return { ) (  bool
if (expectedModCount == list.modCount) { if (lastLink != null) { Link<ET> previous = lastLink.previous; Link<ET> next = lastLink.next; previous.next = next; next.previous = previous; if (lastLink == link) { pos--; } lastLink = null; expectedModCount++; list.size--; list.modCount++; } else { throw new InvalidOperationException(); } } else { throw new InvalidOperationException(); }
return executeMergeShards(request = beforeClientExecution(request));
public virtual AllocateHostedConnectionResponse AllocateHostedConnection(AllocateHostedConnectionRequest request){var options = new InvokeOptions();options.RequestMarshaller = AllocateHostedConnectionRequestMarshaller.Instance;options.ResponseUnmarshaller = AllocateHostedConnectionResponseUnmarshaller.Instance;return Invoke<AllocateHostedConnectionResponse>(request, options);}
} ; start return { ) (
public static readonly WeightedTerm[] final = getTerms(query, false);
throw new ReadOnlyBufferException();
for (int i = 0; i < iterations; i++) { byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = byte0 >> 2; byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 63; }
if (s == null) throw new IllegalArgumentException(); string s = uri.getPath(); if (s.equals("") || s.equals("/")) s = uri.getHost(); if (s == null) throw new IllegalArgumentException(); string[] elements; if (s.matcher(LOCAL_FILE).matches() || "file".equals(scheme)) elements = s.split("[\\\\" + File.separatorChar + "/]"); else elements = s.split("/+"); if (elements.length == 0) throw new IllegalArgumentException(); string result; if (Constants.DOT_GIT.equals(result)) result = elements[elements.length - 1]; else if (result.endsWith(Constants.DOT_GIT_EXT)) result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); return result;
public virtual DescribeNotebookInstanceLifecycleConfigResponse DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeNotebookInstanceLifecycleConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.Instance;return Invoke<DescribeNotebookInstanceLifecycleConfigResponse>(request, options);}
return this.accessKeySecret;
} ; ) request ( ExecuteCreateVpnConnection return ; ) request ( BeforeClientExecution = request { ) request CreateVpnConnectionRequest (  CreateVpnConnectionResult
} ; ) request ( ExecuteDescribeVoices return ; ) request ( BeforeClientExecution = request { ) request DescribeVoicesRequest (  DescribeVoicesResult
} ; ) request ( executeListMonitoringExecutions return ; ) request ( beforeClientExecution = request { ) request ListMonitoringExecutionsRequest (  ListMonitoringExecutionsResult
} ; ) jobId ( setJobId ; ) vaultName ( setVaultName { ) jobId string , vaultName string ( DescribeJobRequest
return escherRecords.get(index);
} ; ) request ( executeGetApis return ; ) request ( beforeClientExecution = request { ) request GetApisRequest (  GetApisResult
} ; ) request ( ExecuteDeleteSmsChannel return ; ) request ( BeforeClientExecution = request { ) request DeleteSmsChannelRequest (  DeleteSmsChannelResult
}; trackingRefUpdate return { ) ( TrackingRefUpdate
} ; ) ) b ( ToString . ( Console.Write { ) b bool (  void
} ; ) 0 ( get . ) ( GetChildren return { ) (  QueryNode
}; workdirTreeIndex = index.this{) workdirTreeIndex( NotIgnoredFilter
} ; ) ( ReadInt16 . input = field_1_formatFlags { ) input RecordInputStream ( AreaRecord
; ) HTTPS . ProtocolType (  ; ) "cloudphoto" , "GetThumbnail" , "2017-07-11" , "CloudPhoto" ( base { ) ( GetThumbnailRequest
DescribeTransitGatewayVpcAttachmentsResult DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request) { request = beforeClientExecution(request); return executeDescribeTransitGatewayVpcAttachments(request); }
public virtual PutVoiceConnectorStreamingConfigurationResponse PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request){var options = new InvokeOptions();options.RequestMarshaller = PutVoiceConnectorStreamingConfigurationRequestMarshaller.Instance;options.ResponseUnmarshaller = PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.Instance;return Invoke<PutVoiceConnectorStreamingConfigurationResponse>(request, options);}
return prefixToOrdRange.get(dim);
string symbol = ""; if (startIndex >= 0 && startIndex < getInputStream().size()) { symbol = getInputStream().getText(Interval.of(startIndex, startIndex)); symbol = Utils.escapeWhitespace(symbol, false); } return String.format(Locale.getDefault(), "%s('%s')", typeof(LexerNoViableAltException).Name, symbol);
} ; ) ( PeekFirstImpl return { ) (  E
}; ) request ( executeCreateWorkspaces return ; ) request ( beforeClientExecution = request { ) request CreateWorkspacesRequest ( CreateWorkspacesResult
} ; ) ( copy return { ) (  NumberFormatIndexRecord
} ; ) request ( executeDescribeRepositories return ; ) request ( beforeClientExecution = request { ) request DescribeRepositoriesRequest (  DescribeRepositoriesResult
} ; 0 = mSize ; ] initialCapacity [ new = mValues ; ] initialCapacity [ new = mKeys ; ) initialCapacity ( idealIntArraySize . ArrayUtils = initialCapacity { ) initialCapacity ( SparseIntArray
} ; ) input ( HyphenatedWordsFilter new return { ) input TokenStream (  HyphenatedWordsFilter
public virtual CreateDistributionWithTagsResponse CreateDistributionWithTags(CreateDistributionWithTagsRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateDistributionWithTagsRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateDistributionWithTagsResponseUnmarshaller.Instance;return Invoke<CreateDistributionWithTagsResponse>(request, options);}
} ; ) mode , ) fileName ( FileInfo new ( { FileNotFoundException throws ) mode string , fileName string ( FileStream
} ; ) request ( executeDeleteWorkspaceImage return ; ) request ( beforeClientExecution = request { ) request DeleteWorkspaceImageRequest (  DeleteWorkspaceImageResult
public static string ToString(int value) { StringBuilder sb = new StringBuilder(16); WriteHex(sb, value, 16, ""); return sb.ToString(); }
} ; ) request ( executeUpdateDistribution return ; ) request ( beforeClientExecution = request { ) request UpdateDistributionRequest (  UpdateDistributionResult
} ; ) b , index ( CustomColor new : null ? ) null == b ( return ; ) index ( getColor . _palette = b ] [ } ; ) ( getColor . AUTOMATIC . HSSFColorPredefined return { ) ) ( getIndex . AUTOMATIC . HSSFColorPredefined == index ( if { ) index (  HSSFColor
}; ) _functionName ( NotImplementedFunctionException new throw { ) srcCol , srcRow , operands ] [ ValueEval (  ValueEval
void out(LittleEndianOutput out) { out.WriteShort(field_1_number_crn_records); out.WriteShort(field_2_sheet_table_index); }
} ; ) ) ( DescribeDBEngineVersionsRequest new ( describeDBEngineVersions return { ) (  DescribeDBEngineVersionsResult
} ; fontIndex = _fontIndex . this ; character = _character . this { ) fontIndex , character ( FormatRun
public static byte[] EncodeToBytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int end = offset + length; int resultIndex = 0; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
}; ) request ( ExecuteUploadArchive return ; ) request ( BeforeClientExecution = request { ) request UploadArchiveRequest ( UploadArchiveResult
return GetHiddenTokensToLeft(tokenIndex, -1);
if (this == obj) { return true; } if (!base.Equals(obj)) { return false; } if (this.GetType() != obj.GetType()) { return false; } AutomatonQuery other = (AutomatonQuery)obj; if (!compiled.Equals(other.compiled)) { return false; } if (term == null) { if (other.term != null) { return false; } } else if (!term.Equals(other.term)) { return false; } return true;
SpanQuery[] spanQueries = new SpanQuery[weightBySpanQuery.Count]; Iterator<SpanQuery> sqi = weightBySpanQuery.Keys.GetEnumerator(); int i = 0; while (sqi.MoveNext()) { SpanQuery sq = sqi.Current; float boost = weightBySpanQuery[sq]; if (boost != 1f) { sq = new SpanBoostQuery(sq, boost); } spanQueries[i++] = sq; } if (spanQueries.Length == 1) return spanQueries[0]; else return new SpanOrQuery(spanQueries);
} ; ) repo ( StashCreateCommand new return { ) (  StashCreateCommand
return FieldInfo.GetField(fieldName);
} ; ) request ( executeDescribeEventSource return ; ) request ( beforeClientExecution = request { ) request DescribeEventSourceRequest (  DescribeEventSourceResult
} ; ) request ( executeGetDocumentAnalysis return ; ) request ( beforeClientExecution = request { ) request GetDocumentAnalysisRequest (  GetDocumentAnalysisResult
public virtual CancelUpdateStackResponse CancelUpdateStack(CancelUpdateStackRequest request){var options = new InvokeOptions();options.RequestMarshaller = CancelUpdateStackRequestMarshaller.Instance;options.ResponseUnmarshaller = CancelUpdateStackResponseUnmarshaller.Instance;return Invoke<CancelUpdateStackResponse>(request, options);}
}; ) request ( ExecuteModifyLoadBalancerAttributes return ; ) request ( BeforeClientExecution = request { ) request ModifyLoadBalancerAttributesRequest ( ModifyLoadBalancerAttributesResult
}; ) request ( ExecuteSetInstanceProtection return ; ) request ( BeforeClientExecution = request { ) request SetInstanceProtectionRequest ( SetInstanceProtectionResult
} ; ) request ( executeModifyDBProxy return ; ) request ( beforeClientExecution = request { ) request ModifyDBProxyRequest (  ModifyDBProxyResult
void (output, offset, len, endOffset, posLength) { if (count == outputs.Length) { outputs = ArrayUtil.Grow(outputs, count + 1); } if (count == endOffsets.Length) { int[] next = new int[ArrayUtil.Oversize(count + 1, Integer.BYTES)]; System.Arraycopy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.Length) { int[] next = new int[ArrayUtil.Oversize(count + 1, Integer.BYTES)]; System.Arraycopy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; ++count; }
; ) HTTPS . ProtocolType (  ; ) "cloudphoto" , "FetchLibraries" , "2017-07-11" , "CloudPhoto" ( base { ) ( FetchLibrariesRequest
} ; ) objects ( exists . fs return { ) (  bool
} ; out = out . this { ) out Stream ( FilterStream
; ) PUT . MethodType ( setMethod ; ) "/clusters/[ClusterId]" (  ; ) "csk" , "ScaleCluster" , "2015-12-15" , "CS" ( base { ) ( ScaleClusterRequest
return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);
} ; ) request ( executeListObjectParentPaths return ; ) request ( beforeClientExecution = request { ) request ListObjectParentPathsRequest (  ListObjectParentPathsResult
return ExecuteDescribeCacheSubnetGroups((request = BeforeClientExecution(request)));
} ; ) flag , field_5_options ( SetShortBoolean . sharedFormula = field_5_options { ) flag bool (  void
} ; reuseObjects return { ) (  bool
} ; t return ; ) this ( SetParent . t ; ) t ( AddAnyChild ; ) badToken ( ErrorNodeImpl new = t ErrorNodeImpl { ) badToken Token (  ErrorNode
} } ; ) args + "Unknown parameters: " ( ArgumentException new throw { ) ) ( IsEmpty . args ! ( if ; ) args ( { ) args > string , string < Dictionary ( LatvianStemFilterFactory
public virtual RemoveSourceIdentifierFromSubscriptionResponse RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request){var options = new InvokeOptions();options.RequestMarshaller = RemoveSourceIdentifierFromSubscriptionRequestMarshaller.Instance;options.ResponseUnmarshaller = RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.Instance;return Invoke<RemoveSourceIdentifierFromSubscriptionResponse>(request, options);}
public static TokenFilterFactory newInstance(string name, Map<string, string> args) { return loader(name, args); }
: ) HTTPS . ProtocolType (  : ) "cloudphoto" , "AddAlbumPhotos" , "2017-07-11" , "CloudPhoto" ( base { ) ( AddAlbumPhotosRequest
} ; ) request ( ExecuteGetThreatIntelSet return ; ) request ( BeforeClientExecution = request { ) request GetThreatIntelSetRequest (  GetThreatIntelSetResult
} ; ) ) ( clone . b , ) ( clone . a ( Binary new return { ) (  RevFilter
return o is ArmenianStemmer;
public bool HasArray { get; protected set; }
return ExecuteUpdateContributorInsights(request = BeforeClientExecution(request)); } public virtual UpdateContributorInsightsResponse UpdateContributorInsights(UpdateContributorInsightsRequest request) {
} ; null = writeProtect ; null = fileShare ; ) writeProtect ( remove . records ; ) fileShare ( remove . records { ) (  void
} ; expand = expand . this ; ) analyzer , dedup ( base { ) analyzer Analyzer , expand bool , dedup bool ( SolrSynonymParser
}; ) request ( executeRequestSpotInstances return ; ) request ( beforeClientExecution = request { ) request RequestSpotInstancesRequest ( RequestSpotInstancesResult
return findObjectRecord(getObjectData());
public virtual GetContactAttributesResponse GetContactAttributes(GetContactAttributesRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetContactAttributesRequestMarshaller.Instance;options.ResponseUnmarshaller = GetContactAttributesResponseUnmarshaller.Instance;return Invoke<GetContactAttributesResponse>(request, options);}
return getKey() + ": " + getValue();
public virtual ListTextTranslationJobsResponse ListTextTranslationJobs(ListTextTranslationJobsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListTextTranslationJobsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListTextTranslationJobsResponseUnmarshaller.Instance;return Invoke<ListTextTranslationJobsResponse>(request, options);}
} ; ) request ( executeGetContactMethods return ; ) request ( beforeClientExecution = request { ) request GetContactMethodsRequest (  GetContactMethodsResult
public static FunctionMetadata GetFunctionByName(string name) { FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name); if (fd == null) { fd = GetInstanceCetab().GetFunctionByNameInternal(name); } if (fd == null) { return -1; } return fd.GetIndex(); }
} ; ) request ( ExecuteDescribeAnomalyDetectors return ; ) request ( BeforeClientExecution = request { ) request DescribeAnomalyDetectorsRequest (  DescribeAnomalyDetectorsResult
public static string InsertId(string message, ObjectId changeId) { return string.Format("insertId({0},{1})", message, changeId); }
throw new MissingObjectException(objectId.Copy(), unknownObjectType2.Get(JGitText)); throw new MissingObjectException(objectId.Copy(), typeHint); if (typeHint == OBJ_ANY) { if (sz < 0) { sz = db.GetObjectSize(this, objectId); } if (sz < 0) throw new MissingObjectException(objectId.Copy(), JGitText.Get().unknownObjectType2); } return sz; }
} ; ) request ( executeImportInstallationMedia return ; ) request ( beforeClientExecution = request { ) request ImportInstallationMediaRequest (  ImportInstallationMediaResult
public virtual PutLifecycleEventHookExecutionStatusResponse PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request){var options = new InvokeOptions();options.RequestMarshaller = PutLifecycleEventHookExecutionStatusRequestMarshaller.Instance;options.ResponseUnmarshaller = PutLifecycleEventHookExecutionStatusResponseUnmarshaller.Instance;return Invoke<PutLifecycleEventHookExecutionStatusResponse>(request, options);}
} ; ) ) ( ReadDouble . in ( { ) in LittleEndianInput ( NumberPtg
} ; ) request ( executeGetFieldLevelEncryptionConfig return ; ) request ( beforeClientExecution = request { ) request GetFieldLevelEncryptionConfigRequest (  GetFieldLevelEncryptionConfigResult
public virtual DescribeDetectorResponse DescribeDetector(DescribeDetectorRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeDetectorRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeDetectorResponseUnmarshaller.Instance;return Invoke<DescribeDetectorResponse>(request, options);}
public virtual ReportInstanceStatusResponse ReportInstanceStatus(ReportInstanceStatusRequest request){var options = new InvokeOptions();options.RequestMarshaller = ReportInstanceStatusRequestMarshaller.Instance;options.ResponseUnmarshaller = ReportInstanceStatusResponseUnmarshaller.Instance;return Invoke<ReportInstanceStatusResponse>(request, options);}
return executeDeleteAlarm(request = beforeClientExecution(request)); } DeleteAlarmResult ( DeleteAlarmRequest request ) {
} ; ) input ( PortugueseStemFilter new return { ) input TokenStream (  TokenStream
; ] ENCODED_SIZE [ new = reserved { ) ( FtCblsSubRecord
public override bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
}; ) request ( ExecuteGetDedicatedIp return ; ) request ( BeforeClientExecution = request { ) request GetDedicatedIpRequest ( GetDedicatedIpResult
// Cannot translate: invalid Java syntax
} ; ) request ( executeListStreamProcessors return ; ) request ( beforeClientExecution = request { ) request ListStreamProcessorsRequest (  ListStreamProcessorsResult
DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName) { setLoadBalancerName(loadBalancerName); setPolicyName(policyName); }
}; options = _options { ) options ( WindowProtectRecord
} ; ] bufferSize [ new = data ; 0 = n { ) bufferSize ( UnbufferedCharStream
public virtual GetOperationsResponse GetOperations(GetOperationsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetOperationsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetOperationsResponseUnmarshaller.Instance;return Invoke<GetOperationsResponse>(request, options);}
void NB.encodeInt32(b, o, w1); NB.encodeInt32(b, o + 4, w2); NB.encodeInt32(b, o + 8, w3); NB.encodeInt32(b, o + 12, w4); NB.encodeInt32(b, o + 16, w5);
WindowOneRecord(RecordInputStream in) { field_1_h_hold = in.ReadShort(); field_2_v_hold = in.ReadShort(); field_3_width = in.ReadShort(); field_4_height = in.ReadShort(); field_5_options = in.ReadShort(); field_6_active_sheet = in.ReadShort(); field_7_first_visible_tab = in.ReadShort(); field_8_num_selected_tabs = in.ReadShort(); field_9_tab_width_ratio = in.ReadShort(); }
} ; ) request ( executeStopWorkspaces return ; ) request ( BeforeClientExecution = request { ) request StopWorkspacesRequest (  StopWorkspacesResult
} } } } ; ) ( Close . fos { finally } ; ) ( Close . channel { try { finally } ; ) fileLength ( Truncate . channel { try { finally } ; ) ( Dump { try ; false = isOpen { ) isOpen ( if { IOException throws ) (  void
} ; ) request ( ExecuteDescribeMatchmakingRuleSets return ; ) request ( BeforeClientExecution = request { ) request DescribeMatchmakingRuleSetsRequest (  DescribeMatchmakingRuleSetsResult
return null; } String(wordId, surface[off, len]) {
} ; pathStr return { ) (  string
public static double r(double[] v) { if (v != null && v.Length >= 1) { double m = 0; double s = 0; int n = v.Length; for (int i = 0; i < n; i++) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; return r; } r = double.NaN; return r; }
} ; ) request ( ExecuteDescribeResize return ; ) request ( BeforeClientExecution = request { ) request DescribeResizeRequest (  DescribeResizeResult
public bool passedThroughNonGreedyDecision { get; }
} ; ) 0 ( end return { ) (
public void VisitCells(CellHandler handler) { int firstRow = range.GetFirstRow(); int lastRow = range.GetLastRow(); int firstColumn = range.GetFirstColumn(); int lastColumn = range.GetLastColumn(); int width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); ICell currentCell = null; IRow currentRow = null; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ctx.rowNumber++) { currentRow = sheet.GetRow(ctx.rowNumber); if (currentRow == null) { continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ctx.colNumber++) { currentCell = currentRow.GetCell(ctx.colNumber); if (currentCell == null) { continue; } if (!traverseEmptyCells && currentCell.IsEmpty()) { continue; } int ordinalNumber = ArithmeticUtils.MulAndCheck(ArithmeticUtils.SubAndCheck(ctx.rowNumber, firstRow), width) + ArithmeticUtils.AddAndCheck(ctx.colNumber - firstColumn, 1); handler.OnCell(currentCell, ctx); } } }
} ; pos return { ) (
if (this.boost == other.boost) return other.bytes.Get().CompareTo(this.bytes.Get()); else return this.boost.CompareTo(other.boost);
for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = delete(s, i, len); i--; break; } } return len; }
public void WriteShort(ILittleEndianOutput out1) { out1.WriteShort(_options); }
} ; exactOnly = exactOnly . this { ) exactOnly bool ( DiagnosticErrorListener
new KeySchemaElement { AttributeName = attributeName, KeyType = keyType }.ToString();
public virtual GetAssignmentResponse GetAssignment(GetAssignmentRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetAssignmentRequestMarshaller.Instance;options.ResponseUnmarshaller = GetAssignmentResponseUnmarshaller.Instance;return Invoke<GetAssignmentResponse>(request, options);}
} ; 1 - != ) id ( findOffset return { ) id AnyObjectId (  bool
} ; this return ; allGroups = allGroups . this { ) allGroups bool (  GroupingSearch
public synchronized void (String dimName, boolean v) { DimConfig ft = fieldTypes.get(dimName); if (ft == null) { ft = new DimConfig(); fieldTypes.put(dimName, ft); } ft.multiValued = v; }
{ ) ( ; return size } } ; ++ size { ) 0 >= cmd . e ( if ; ) c ( at = e Cell ; ) ( next . i = c Character { ) ; ) ( hasNext . i ; ( for ; 0 = size ; ) ( iterator . ) ( keySet . cells = i > Character < Iterator { ) (
public virtual DeleteVoiceConnectorResponse DeleteVoiceConnector(DeleteVoiceConnectorRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteVoiceConnectorRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteVoiceConnectorResponseUnmarshaller.Instance;return Invoke<DeleteVoiceConnectorResponse>(request, options);}
} ; ) request ( executeDeleteLifecyclePolicy return ; ) request ( beforeClientExecution = request { ) request DeleteLifecyclePolicyRequest (  DeleteLifecyclePolicyResult
