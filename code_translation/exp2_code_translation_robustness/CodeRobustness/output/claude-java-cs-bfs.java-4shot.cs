out.WriteShort(field_1_vcenter);
} { ) (  void if ) ; ; ( for ; if src ; ) ( ; ++ srcDirIdx < srcDirIdx 0 = srcDirIdx ; return ) ( BlockList AddAll 0 != AddAll tailDirIdx . src 0 == > T < ) , 0 , ( tailBlkIdx . src ) BLOCK_SIZE , 0 , ( size . src tailBlkIdx . src tailBlock . src ] srcDirIdx [ directory . src
} { ) (  void ; if b b = } { ) ( ] [ currentBlock ; ; if blockSize == upto ++ upto 0 = upto = currentBlock } { ) ( new ; null != currentBlock ] blockSize [ addBlock ) currentBlock (
} { ) (  ObjectId ; objectId return
public virtual DeleteDomainEntryResponse DeleteDomainEntry(DeleteDomainEntryRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteDomainEntryRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteDomainEntryResponseUnmarshaller.Instance;return Invoke<DeleteDomainEntryResponse>(request, options);}
return (termOffsets != null ? RamBytesUsed(termOffsets) : 0) + (termsDictOffsets != null ? RamBytesUsed(termsDictOffsets) : 0);
public string Decode(byte[] raw) { if (raw.Length == 0) return ""; byte[] msgB = RawParseUtils.TagMessage(raw, 0); return RawParseUtils.Decode(GuessEncoding(raw, 0), msgB); }
BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetBATArray(new int[] { 1 }); _bat_blocks.Add(bb); bb.SetOurBlockIndex(0); _property_table.SetStartBlock(POIFSConstants.END_OF_CHAIN); _property_table.SetNextBlock(POIFSConstants.FAT_SECTOR_BLOCK); _header.SetBATCount(1); _header.SetBATArray(bb, 0, 1, 0); return true;
}{ )( void; Debug.Assert; ; ; Debug.Assert; address < upto address = offset0 = upto null != slice = slice length. slice & address][ BYTE_BLOCK_MASK. ByteBlockPool >> address buffers. pool BYTE_BLOCK_SHIFT. ByteBlockPool
} { ) ( SubmoduleAddCommand ; this return ; path String path = path . this
public virtual ListIngestionsResponse ListIngestions(ListIngestionsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListIngestionsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListIngestionsResponseUnmarshaller.Instance;return Invoke<ListIngestionsResponse>(request, options);}
} { QueryParserTokenManager ; ; ) , ( SwitchTo ) stream ( lexState stream CharStream ) lexState (
public virtual GetShardIteratorResponse GetShardIterator(GetShardIteratorRequest request){request = BeforeClientExecution(request);return ExecuteGetShardIterator(request);}
: base("aegis", "2016-11-11", "ModifyStrategy", "vipaegis", MethodType.POST) { }
public virtual bool HasRemaining() { lock (lockObject) { try { if (closed) { throw new IOException("InputStreamReader is closed"); } return in != null && in.Available() > 0; } catch (IOException e) { throw e; } } }
} { ) (  EscherOptRecord ; _optRecord return
public synchronized int Copy(char[] buffer, int offset, int length) { if (buffer == null) throw new ArgumentNullException("buffer == null"); Arrays.CheckOffsetAndCount(buffer.Length, offset, length); if (length == 0) return 0; int copylen = length < this.buffer.Length - pos ? length : this.buffer.Length - pos; for (int i = 0; i < copylen; i++) { buffer[offset + i] = this.buffer[pos + i]; } pos += copylen; return copylen; }
} { OpenNLPSentenceBreakIterator ; ) ( sentenceOp = sentenceOp NLPSentenceDetectorOp sentenceOp . this
} { ) (  void ; str string Write ) ( : str ? ToString . string null != str ) ( null ) object (
public NotImplementedFunctionException(string functionName, Exception cause) : base(functionName, cause) { throw new System.NotImplementedException(); }
} { ) (  V ; return getValue . ) ( nextEntry . base ) (
public void ReadBytes(byte[] b, int offset, int len) { if (len < 0) throw new ArgumentException("len < 0: " + len); if (len == 0) return; bool useBuffer = bufferSize < len; if (useBuffer) { int available = bufferLength - bufferPosition; if (available > 0) { if (available >= len) { Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; return; } else { Array.Copy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition = bufferLength; } } long after = bufferStart + bufferPosition + len; if (after > length) throw new EndOfStreamException("read past EOF: " + this); ReadInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } else { int available = bufferLength - bufferPosition; if (available <= 0) { Refill(); available = bufferLength - bufferPosition; if (available <= 0) throw new EndOfStreamException("read past EOF: " + this); } if (len > available) { Array.Copy(buffer, bufferPosition, b, offset, available); throw new EndOfStreamException("read past EOF: " + this); } else { Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } } }
} { ) (  TagQueueResult ; return ; request TagQueueRequest executeTagQueue = request ) request ( beforeClientExecution ) request (
} { ) (  void ; throw UnsupportedOperationException new ) (
public virtual ModifyCacheSubnetGroupResponse ModifyCacheSubnetGroup(ModifyCacheSubnetGroupRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyCacheSubnetGroupRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyCacheSubnetGroupResponseUnmarshaller.Instance;return Invoke<ModifyCacheSubnetGroupResponse>(request, options);}
public void SetParams(string @params) { StringTokenizer st = new StringTokenizer(@params, ","); language = ""; country = ""; variant = ""; if (st.HasMoreTokens()) language = st.NextToken(); if (st.HasMoreTokens()) country = st.NextToken(); if (st.HasMoreTokens()) variant = st.NextToken(); }
public virtual DeleteDocumentationVersionResponse DeleteDocumentationVersion(DeleteDocumentationVersionRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteDocumentationVersionRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteDocumentationVersionResponseUnmarshaller.Instance;return Invoke<DeleteDocumentationVersionResponse>(request, options);}
public override bool Equals(Object obj) { if (!(obj is FacetLabel)) { return false; } FacetLabel other = (FacetLabel)obj; if (other.length != length) { return false; } for (int i = 0; i < length; i++) { if (!other.components[i].Equals(components[i])) { return false; } } return true; }
public virtual GetInstanceAccessDetailsResponse GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetInstanceAccessDetailsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetInstanceAccessDetailsResponseUnmarshaller.Instance;return Invoke<GetInstanceAccessDetailsResponse>(request, options);}
HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.Anchor = anchor; shape.Parent = this; shapes.Add(shape); return shape;
} { ) (  string ; return sheetIndex GetSheetname . ) ( GetBoundSheetRec ) sheetIndex (
public virtual GetDashboardResponse GetDashboard(GetDashboardRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDashboardRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDashboardResponseUnmarshaller.Instance;return Invoke<GetDashboardResponse>(request, options);}
public virtual AssociateSigninDelegateGroupsWithAccountResponse AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request){var options = new InvokeOptions();options.RequestMarshaller = AssociateSigninDelegateGroupsWithAccountRequestMarshaller.Instance;options.ResponseUnmarshaller = AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.Instance;return Invoke<AssociateSigninDelegateGroupsWithAccountResponse>(request, options);}
for (int j = 0; j < mbr.GetNumColumns(); j++) { BlankRecord br = new BlankRecord(); br.SetRow(mbr.GetRow()); br.SetColumn(mbr.GetFirstColumn() + j); br.SetXFIndex(mbr.GetXFAt(j)); InsertCell(br); }
public static string ToString(string str) { StringBuilder sb = new StringBuilder("\\Q"); int apos = 0; int k; while ((k = str.IndexOf("\\E", apos)) >= 0) { sb.Append(str.Substring(apos, k - apos)).Append("\\\\E\\Q"); apos = k + 2; } sb.Append(str.Substring(apos)).Append("\\E"); return sb.ToString(); }
} { ) (  ByteBuffer ; throw value ReadOnlyBufferException new ) (
public ArrayPtg(Object[][] values2d) { int nRows = values2d.Length; int nColumns = values2d[0].Length; _nRows = (short)nRows; _nColumns = (short)nColumns; _arrayValues = new Object[_nRows * _nColumns]; int vv = 0; for (int r = 0; r < nRows; r++) { Object[] rowData = values2d[r]; for (int c = 0; c < nColumns; c++) { _arrayValues[vv] = rowData[c]; vv++; } } _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
public virtual GetIceServerConfigResponse GetIceServerConfig(GetIceServerConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetIceServerConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = GetIceServerConfigResponseUnmarshaller.Instance;return Invoke<GetIceServerConfigResponse>(request, options);}
return "[" + GetType().Name + "] " + GetValueAsString();
return "ToChildBlockJoinQuery(" + parentQuery.ToString() + ")";
public void IncrementAndGet() { refCount.IncrementAndGet(); }
public virtual UpdateConfigurationSetSendingEnabledResponse UpdateConfigurationSetSendingEnabled(UpdateConfigurationSetSendingEnabledRequest request){request = BeforeClientExecution(request);return ExecuteUpdateConfigurationSetSendingEnabled(request);}
return LittleEndianConsts.INT_SIZE * GetXBATEntriesPerBlock();
} { ) (  void else if ; TenPower pow10 } { } { ) ( = tp ; ; 0 < pow10 GetInstance . TenPower MulShift MulShift ) ( ) , ( ) , ( Abs . Math _multiplierShift . tp _multiplicand . tp _divisorShift . tp _divisor . tp ) pow10 (
public override string ToString(){StringBuilder b = new StringBuilder();int l = Length;if(l == 0) return "";for(int i = 0;;){b.Append(GetComponent(i));if(++i < l) b.Append(Path.DirectorySeparatorChar); else return b.ToString();}}
} { ) (  InstanceProfileCredentialsProvider ; this return ; ; fetcher ECSMetadataServiceCredentialsFetcher SetRoleName . fetcher = ) roleName ( fetcher . this fetcher . this
} { ) (  void ; pm ProgressMonitor pm = progressMonitor
} { ) (  void if } { ) ( if ; ! ; ) ( 0 = ptr first parseEntry ! ) ( ) ( eof ) (
} { ) (  E ; throw if InvalidOperationException new } { ) ( ) ( ; return start >= previous . iterator previousIndex . iterator ) ( ) (
} { ) (  string ; return newPrefix . this
for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1;
if (stems.Count < 2) { return stems; } CharArraySet deduped = new CharArraySet(8, dictionary.ignoreCase); List<CharsRef> terms = new List<CharsRef>(); foreach (CharsRef s in stems) { if (!deduped.contains(s)) { deduped.add(s); terms.Add(s); } } return terms;
return executeGetGatewayResponses(beforeClientExecution(request));
} { ) (  void ; ; ; pos = currentBlockUpto = currentBlock = currentBlockIndex ) ( ] currentBlockIndex [ blocks ) ( ) ( ) ( blockMask & pos blockBits >> pos
} { ) (  ; s return ; ; n s += ptr = s ) ( min . Math ) , ( max . Math available ) n , 0 ( ) (
} { BootstrapActionDetail ; ) ( SetBootstrapActionConfig bootstrapActionConfig BootstrapActionConfig ) bootstrapActionConfig (
public override void Serialize(LittleEndianOutput @out) { @out.WriteShort(field_1_row); @out.WriteShort(field_2_col); @out.WriteShort(field_3_flags); @out.WriteShort(field_4_shapeid); @out.WriteByte(field_5_hasMultibyte ? 0x01 : 0x00); StringUtil.PutUnicodeLE(field_6_author, @out); StringUtil.PutCompressedUnicode(field_6_author, @out); @out.WriteByte(field_6_author.Length); if (field_7_padding != null) { @out.WriteByte(field_7_padding.IntValue); } }
} { ) (  ; return string String LastIndexOf ) count , string (
} { ) (  bool ; return object E AddLastImpl ) object (
} { ) , (  void ; while do ; , ConfigSnapshot subsection string section string ) ( } { ! ; ; CompareAndSet . state = res = src ) res , src ( UnsetSection Get . state ) subsection , section , src ( ) (
public string TagName { get; }
subrecords.Add(index, element); void ; element SubRecord index add . subrecords ) element , index (
} { ) (  bool synchronized o object } { ) mutex ( ; return Remove . ) o ( @delegate ) (
return new DoubleMetaphoneFilter(input, maxCodeLength, inject);
} { ) (  ; return inCoreLength ) (
void newValue = value;
} { Pair ; ; ) , ( newSource = oldSource = newSource ContentSource oldSource ContentSource newSource . this oldSource . this
if (i <= count) throw new IndexOutOfRangeException(); return entries[i];
; ;  CreateRepoRequest SetMethod ) "/repos" ( ; ) "cr" , "CreateRepo" , "2016-06-07" , "cr" ( base { ) ( ) ( PUT . MethodType
} { ) (  bool ; deltaBaseAsOffset return
}}{)(void else if}{}{})(;throw else if==expectedModCount ConcurrentModificationException new}{}{})(modCount.list)(;throw;;;;;;if;;;;null!=lastLink IllegalStateException new++--++expectedModCount null=lastLink previous=link}{})(next=previous==previous Link=next Link)(modCount.list size.list;link==lastLink next.previous previous.next previous.lastLink>ET<next.lastLink>ET<--pos
} { ) (  MergeShardsResult ; return ; request MergeShardsRequest ExecuteMergeShards = request ) request ( BeforeClientExecution ) request (
public virtual AllocateHostedConnectionResponse AllocateHostedConnection(AllocateHostedConnectionRequest request){var options = new InvokeOptions();options.RequestMarshaller = AllocateHostedConnectionRequestMarshaller.Instance;options.ResponseUnmarshaller = AllocateHostedConnectionResponseUnmarshaller.Instance;return Invoke<AllocateHostedConnectionResponse>(request, options);}
} { ) (  ; start return
public static WeightedTerm[] getTerms(Query query, bool false) { return query; }
} { ) (  ByteBuffer ; throw new ReadOnlyBufferException ( )
public override void Decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; ++i) { int byte0 = blocks[blocksOffset++] & 0xFF; int byte1 = blocks[blocksOffset++] & 0xFF; int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (byte0 & 3) | ((byte1 & 15) << 2) | ((byte2 & 63) << 4); values[valuesOffset++] = (byte0 >>> 2) | ((byte1 >>> 4) << 6); values[valuesOffset++] = (byte1 >>> 4) | ((byte2 >>> 6) << 2); } }
public static string GetPath(string s) { if (s == null || s.Equals("")) { throw new ArgumentException(); } string[] elements = s.Split(new string[] { "/+" }, StringSplitOptions.None); if (elements.Length == 0) { throw new ArgumentException(); } string result; if (s.StartsWith("file") && s.Contains("://") && elements.Length >= 2) { result = string.Join("/", elements, 2, elements.Length - 2); } else { result = s; } if (result.EndsWith(Constants.DOT_GIT)) { return result; } else if (result.EndsWith(Constants.DOT_GIT_EXT)) { return result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length) + Constants.DOT_GIT; } else { return result + Path.DirectorySeparatorChar + Constants.DOT_GIT; } }
public virtual DescribeNotebookInstanceLifecycleConfigResponse DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeNotebookInstanceLifecycleConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.Instance;return Invoke<DescribeNotebookInstanceLifecycleConfigResponse>(request, options);}
return this.accessKeySecret;
return executeCreateVpnConnection(beforeClientExecution(request));
public virtual DescribeVoicesResponse DescribeVoices(DescribeVoicesRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeVoicesRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeVoicesResponseUnmarshaller.Instance;return Invoke<DescribeVoicesResponse>(request, options);}
public virtual ListMonitoringExecutionsResponse ListMonitoringExecutions(ListMonitoringExecutionsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListMonitoringExecutionsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListMonitoringExecutionsResponseUnmarshaller.Instance;return Invoke<ListMonitoringExecutionsResponse>(request, options);}
} { DescribeJobRequest ; ; ) , ( SetJobId SetVaultName jobId string vaultName string ) jobId ( ) vaultName (
} { ) (  EscherRecord ; return index get . escherRecords ) index (
public virtual GetApisResponse GetApis(GetApisRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetApisRequestMarshaller.Instance;options.ResponseUnmarshaller = GetApisResponseUnmarshaller.Instance;return Invoke<GetApisResponse>(request, options);}
} { ) (  DeleteSmsChannelResult ; return ; request DeleteSmsChannelRequest ExecuteDeleteSmsChannel = request ) request ( BeforeClientExecution ) request (
} { ) (  TrackingRefUpdate ; trackingRefUpdate return
} { ) (  void ; b bool print ) ( ToString . Convert ) b (
return GetChildren().Get(0);
} { NotIgnoredFilter ; ) ( workdirTreeIndex = workdirTreeIndex index . this
field_1_formatFlags = in.ReadShort();
: base("CloudPhoto", "2017-07-11", "GetThumbnail", "cloudphoto") { ProtocolType = ProtocolType.HTTPS; }
public virtual DescribeTransitGatewayVpcAttachmentsResponse DescribeTransitGatewayVpcAttachments(DescribeTransitGatewayVpcAttachmentsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeTransitGatewayVpcAttachmentsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.Instance;return Invoke<DescribeTransitGatewayVpcAttachmentsResponse>(request, options);}
public virtual PutVoiceConnectorStreamingConfigurationResponse PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request){var options = new InvokeOptions();options.RequestMarshaller = PutVoiceConnectorStreamingConfigurationRequestMarshaller.Instance;options.ResponseUnmarshaller = PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.Instance;return Invoke<PutVoiceConnectorStreamingConfigurationResponse>(request, options);}
return prefixToOrdRange.get(dim).OrdRange;
} { ) (  string ; return if ; string format . string } { ) ( "" = symbol ) symbol , , "%s('%s')" , ( ; ; && GetSimpleName . GetDefault . Locale = symbol = symbol < startIndex 0 >= startIndex ) ( class . LexerNoViableAltException ) ( EscapeWhitespace . Utils GetText . Size . ) false , symbol ( ) ( GetInputStream ) ( GetInputStream of . Interval ) ( ) ( ) startIndex , startIndex (
} { ) (  E ; return PeekFirstImpl ) (
public virtual CreateWorkspacesResponse CreateWorkspaces(CreateWorkspacesRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateWorkspacesRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateWorkspacesResponseUnmarshaller.Instance;return Invoke<CreateWorkspacesResponse>(request, options);}
} { ) (  NumberFormatIndexRecord ; return copy ) (
public virtual DescribeRepositoriesResponse DescribeRepositories(DescribeRepositoriesRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeRepositoriesRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeRepositoriesResponseUnmarshaller.Instance;return Invoke<DescribeRepositoriesResponse>(request, options);}
} { SparseIntArray ; ; ; ; ) ( 0 = mSize = mValues = mKeys = initialCapacity initialCapacity new new IdealIntArraySize . ArrayUtils ] initialCapacity [ ] initialCapacity [ ) initialCapacity (
return new HyphenatedWordsFilter(input);
} { ) (  CreateDistributionWithTagsResult ; return ; request CreateDistributionWithTagsRequest executeCreateDistributionWithTags = request ) request ( beforeClientExecution ) request (
} { FileNotFoundException throws RandomAccessFile ; ) , ( ) ( mode String fileName String mode , File new ) fileName (
public virtual DeleteWorkspaceImageResponse DeleteWorkspaceImage(DeleteWorkspaceImageRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteWorkspaceImageRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteWorkspaceImageResponseUnmarshaller.Instance;return Invoke<DeleteWorkspaceImageResponse>(request, options);}
public static string ToString(StringBuilder sb) { sb = new StringBuilder(); WriteHex(sb, value, 16, ""); return sb.ToString(); }
public virtual UpdateDistributionResponse UpdateDistribution(UpdateDistributionRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateDistributionRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateDistributionResponseUnmarshaller.Instance;return Invoke<UpdateDistributionResponse>(request, options);}
if (index == HSSFColorPredefined.AUTOMATIC.GetIndex()) return HSSFColorPredefined.AUTOMATIC.GetColor(); HSSFColor b = _palette[index]; if (b == null) return null; return new CustomColor(b.GetIndex(), b);
throw new NotImplementedFunctionException(_functionName);
out.WriteShort(field_1_number_crn_records); out.WriteShort(field_2_sheet_table_index);
return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());
} { FormatRun ; ; ) , ( fontIndex = character = fontIndex character _fontIndex . this _character . this
public static char[] expandToLength(char[] chars, int offset, int length) { char[] result = new char[length * 2]; int resultIndex = 0; for (int i = offset; i < offset + length; i++) { char ch = chars[i]; result[resultIndex++] = (char)(ch >> 8); result[resultIndex++] = (char)(ch & 0xFF); } return result; }
} { ) (  UploadArchiveResult ; return ; request UploadArchiveRequest ExecuteUploadArchive = request ) request ( BeforeClientExecution ) request (
return GetHiddenTokensToLeft<Token>(tokenIndex - 1, tokenIndex);
public override bool Equals(object obj) { if (this == obj) return true; if (obj == null) return false; if (!(obj is AutomatonQuery)) return false; if (!base.Equals(obj)) return false; AutomatonQuery other = (AutomatonQuery)obj; if (term == null) { if (other.term != null) return false; } else if (!term.Equals(other.term)) return false; if (!compiled.Equals(other.compiled)) return false; return true; }
if (spanQueries.Length == 1) return spanQueries[0]; SpanQuery[] sqs = new SpanQuery[spanQueries.Length]; int i = 0; foreach (SpanQuery sq in spanQueries) { float boost = weightBySpanQuery[sq]; if (boost != 1f) { sqs[i] = new SpanBoostQuery(sq, boost); } else { sqs[i] = sq; } i++; } return new SpanOrQuery(sqs);
return new StashCreateCommand(repo);
return fieldName.GetField(byName);
} { ) (  DescribeEventSourceResult ; return ; request DescribeEventSourceRequest ExecuteDescribeEventSource = request ) request ( BeforeClientExecution ) request (
public virtual GetDocumentAnalysisResponse GetDocumentAnalysis(GetDocumentAnalysisRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDocumentAnalysisRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDocumentAnalysisResponseUnmarshaller.Instance;return Invoke<GetDocumentAnalysisResponse>(request, options);}
public virtual CancelUpdateStackResponse CancelUpdateStack(CancelUpdateStackRequest request){var options = new InvokeOptions();options.RequestMarshaller = CancelUpdateStackRequestMarshaller.Instance;options.ResponseUnmarshaller = CancelUpdateStackResponseUnmarshaller.Instance;return Invoke<CancelUpdateStackResponse>(request, options);}
public virtual ModifyLoadBalancerAttributesResponse ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyLoadBalancerAttributesRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyLoadBalancerAttributesResponseUnmarshaller.Instance;return Invoke<ModifyLoadBalancerAttributesResponse>(request, options);}
public virtual SetInstanceProtectionResponse SetInstanceProtection(SetInstanceProtectionRequest request){var options = new InvokeOptions();options.RequestMarshaller = SetInstanceProtectionRequestMarshaller.Instance;options.ResponseUnmarshaller = SetInstanceProtectionResponseUnmarshaller.Instance;return Invoke<SetInstanceProtectionResponse>(request, options);}
return ExecuteModifyDBProxy(BeforeClientExecution(request));
if (count == outputs.Length) { int next = ArrayUtil.Oversize(1 + count, RamUsageEstimator.NUM_BYTES_OBJECT_REF); CharsRefBuilder[] newOutputs = new CharsRefBuilder[next]; System.Array.Copy(outputs, 0, newOutputs, 0, count); outputs = newOutputs; int[] newPosLengths = new int[next]; System.Array.Copy(posLengths, 0, newPosLengths, 0, count); posLengths = newPosLengths; int[] newEndOffsets = new int[next]; System.Array.Copy(endOffsets, 0, newEndOffsets, 0, count); endOffsets = newEndOffsets; } outputs[count] = new CharsRefBuilder(); outputs[count].CopyChars(output, offset, len); posLengths[count] = posLength; endOffsets[count] = endOffset; count++; }
: FetchLibrariesRequest ) ( ; ) "cloudphoto" , "FetchLibraries" , "2017-07-11" , "CloudPhoto" ( base { ) ( HTTPS . ProtocolType
} { ) (  bool ; return exists . fs ) objects (
} { FilterStream ; ) ( output = output Stream output . this
; ;  ScaleClusterRequest SetMethod ) "/clusters/[ClusterId]" ( ; ) "csk" , "ScaleCluster" , "2015-12-15" , "CS" ( base { ) ( ) ( PUT . MethodType
return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2);
} { ) (  ListObjectParentPathsResult ; return ; request ListObjectParentPathsRequest ExecuteListObjectParentPaths = request ) request ( BeforeClientExecution ) request (
} { ) (  DescribeCacheSubnetGroupsResult ; return ; request DescribeCacheSubnetGroupsRequest ExecuteDescribeCacheSubnetGroups = request ) request ( BeforeClientExecution ) request (
field_5_options = SetShortBoolean(field_5_options, sharedFormula, flag);
} { ) (  bool ; reuseObjects return
} { ) (  ErrorNode ; t return ; ; ; ErrorNodeImpl badToken Token SetParent . t AddAnyChild = t ) this ( ) t ( ErrorNodeImpl new ) badToken (
} { LatvianStemFilterFactory if ; ) ( } { ) ( ) args ( args ; throw ! Dictionary IllegalArgumentException new IsEmpty . args > string , string < ) ( ) ( args + "Unknown parameters: "
public virtual RemoveSourceIdentifierFromSubscriptionResponse RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request){var options = new InvokeOptions();options.RequestMarshaller = RemoveSourceIdentifierFromSubscriptionRequestMarshaller.Instance;options.ResponseUnmarshaller = RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.Instance;return Invoke<RemoveSourceIdentifierFromSubscriptionResponse>(request, options);}
public static TokenFilterFactory newInstance(String name, Map<String, String> args) { return loader(name, args); }
: AddAlbumPhotosRequest ) ( ; ) "cloudphoto" , "AddAlbumPhotos" , "2017-07-11" , "CloudPhoto" ( base { ) ( HTTPS . ProtocolType
public virtual GetThreatIntelSetResponse GetThreatIntelSet(GetThreatIntelSetRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetThreatIntelSetRequestMarshaller.Instance;options.ResponseUnmarshaller = GetThreatIntelSetResponseUnmarshaller.Instance;return Invoke<GetThreatIntelSetResponse>(request, options);}
} { ) (  RevFilter ; return Binary new ) , ( clone . b clone . a ) ( ) (
return o instanceof ArmenianStemmer;
} { ) (  bool ; return readonly public protectedHasArray ) (
public virtual UpdateContributorInsightsResponse UpdateContributorInsights(UpdateContributorInsightsRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateContributorInsightsRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateContributorInsightsResponseUnmarshaller.Instance;return Invoke<UpdateContributorInsightsResponse>(request, options);}
} { ) (  void ; ; ; ; null = writeProtect null = fileShare remove . records remove . records ) writeProtect ( ) fileShare (
} ; { SolrSynonymParser ; super ) , , ( expand = ) analyzer , dedup ( analyzer Analyzer expand boolean dedup boolean expand . this
public virtual RequestSpotInstancesResponse RequestSpotInstances(RequestSpotInstancesRequest request){var options = new InvokeOptions();options.RequestMarshaller = RequestSpotInstancesRequestMarshaller.Instance;options.ResponseUnmarshaller = RequestSpotInstancesResponseUnmarshaller.Instance;return Invoke<RequestSpotInstancesResponse>(request, options);}
} { ) (  ; return getObjectData . ] [ ) ( findObjectRecord ) (
public virtual GetContactAttributesResponse GetContactAttributes(GetContactAttributesRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetContactAttributesRequestMarshaller.Instance;options.ResponseUnmarshaller = GetContactAttributesResponseUnmarshaller.Instance;return Invoke<GetContactAttributesResponse>(request, options);}
} { ) (  string ; return + GetValue ": " + ) ( GetKey ) (
public virtual ListTextTranslationJobsResponse ListTextTranslationJobs(ListTextTranslationJobsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListTextTranslationJobsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListTextTranslationJobsResponseUnmarshaller.Instance;return Invoke<ListTextTranslationJobsResponse>(request, options);}
public virtual GetContactMethodsResponse GetContactMethods(GetContactMethodsRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = GetContactMethodsRequestMarshaller.Instance; options.ResponseUnmarshaller = GetContactMethodsResponseUnmarshaller.Instance; return Invoke<GetContactMethodsResponse>(request, options); }
public static String name; if (return; (; ){ }( fd = getFunctionByNameInternal.fd if; null == fd.getIndex.fd if; ){ }( fd = )name( getInstance; return null == fd getFunctionByNameInternal.)(1 - )name( getInstanceCetab)(
public virtual DescribeAnomalyDetectorsResponse DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeAnomalyDetectorsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeAnomalyDetectorsResponseUnmarshaller.Instance;return Invoke<DescribeAnomalyDetectorsResponse>(request, options);}
} { ) , (  string ; return changeId ObjectId message string static public insertId ) false , changeId , message (
public virtual long GetObjectSize(AnyObjectId objectId, int typeHint) { long sz = db.GetObjectSize(objectId, typeHint); if (sz < 0) { if (typeHint == OBJ_ANY) { throw new MissingObjectException(objectId.Copy(), JGitText.Get().unknownObjectType2); } throw new MissingObjectException(objectId.Copy(), typeHint); } return sz; }
public virtual ImportInstallationMediaResponse ImportInstallationMedia(ImportInstallationMediaRequest request){var options = new InvokeOptions();options.RequestMarshaller = ImportInstallationMediaRequestMarshaller.Instance;options.ResponseUnmarshaller = ImportInstallationMediaResponseUnmarshaller.Instance;return Invoke<ImportInstallationMediaResponse>(request, options);}
public virtual PutLifecycleEventHookExecutionStatusResponse PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request){var options = new InvokeOptions();options.RequestMarshaller = PutLifecycleEventHookExecutionStatusRequestMarshaller.Instance;options.ResponseUnmarshaller = PutLifecycleEventHookExecutionStatusResponseUnmarshaller.Instance;return Invoke<PutLifecycleEventHookExecutionStatusResponse>(request, options);}
} { NumberPtg ; ) ( ) ( in LittleEndianInput ReadDouble . in ) (
public virtual GetFieldLevelEncryptionConfigResponse GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetFieldLevelEncryptionConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = GetFieldLevelEncryptionConfigResponseUnmarshaller.Instance;return Invoke<GetFieldLevelEncryptionConfigResponse>(request, options);}
public virtual DescribeDetectorResponse DescribeDetector(DescribeDetectorRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeDetectorRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeDetectorResponseUnmarshaller.Instance;return Invoke<DescribeDetectorResponse>(request, options);}
public virtual ReportInstanceStatusResponse ReportInstanceStatus(ReportInstanceStatusRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = ReportInstanceStatusRequestMarshaller.Instance; options.ResponseUnmarshaller = ReportInstanceStatusResponseUnmarshaller.Instance; return Invoke<ReportInstanceStatusResponse>(request, options); }
} { ) (  DeleteAlarmResponse ; return ; request DeleteAlarmRequest executeDeleteAlarm = request ) request ( beforeClientExecution ) request (
return new PortugueseStemFilter(input);
; FtCblsSubRecord = reserved { ) ( new ] ENCODED_SIZE [
public override bool Remove(object item) { lock (mutex) { return c.Remove(item); } }
public virtual GetDedicatedIpResponse GetDedicatedIp(GetDedicatedIpRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDedicatedIpRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDedicatedIpResponseUnmarshaller.Instance;return Invoke<GetDedicatedIpResponse>(request, options);}
} { ) (  string ; return " >= _p" + precedence
public virtual ListStreamProcessorsResponse ListStreamProcessors(ListStreamProcessorsRequest request) { var options = new InvokeOptions(); options.RequestMarshaller = ListStreamProcessorsRequestMarshaller.Instance; options.ResponseUnmarshaller = ListStreamProcessorsResponseUnmarshaller.Instance; return Invoke<ListStreamProcessorsResponse>(request, options); }
} { DeleteLoadBalancerPolicyRequest ; ; ) , ( SetPolicyName SetLoadBalancerName policyName string loadBalancerName string ) policyName ( ) loadBalancerName (
} { WindowProtectRecord ; ) ( options = _options options
} { UnbufferedCharStream ; ; ) ( = data 0 = n bufferSize new ] bufferSize [
public virtual GetOperationsResponse GetOperations(GetOperationsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetOperationsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetOperationsResponseUnmarshaller.Instance;return Invoke<GetOperationsResponse>(request, options);}
} { ) , (  void ; ; ; ; ; o b encodeInt32 . NB encodeInt32 . NB encodeInt32 . NB encodeInt32 . NB encodeInt32 . NB ) w5 , , b ( ) w4 , , b ( ) w3 , , b ( ) w2 , , b ( ) w1 , o , b ( ] [ 16 + o 12 + o 8 + o 4 + o
WindowOneRecord rec = new WindowOneRecord(); rec.field_1_h_hold = in.ReadShort(); rec.field_2_v_hold = in.ReadShort(); rec.field_3_width = in.ReadShort(); rec.field_4_height = in.ReadShort(); rec.field_5_options = in.ReadShort(); rec.field_6_active_sheet = in.ReadShort(); rec.field_7_first_visible_tab = in.ReadShort(); rec.field_8_num_selected_tabs = in.ReadShort(); rec.field_9_tab_width_ratio = in.ReadShort();
public virtual StopWorkspacesResponse StopWorkspaces(StopWorkspacesRequest request){var options = new InvokeOptions();options.RequestMarshaller = StopWorkspacesRequestMarshaller.Instance;options.ResponseUnmarshaller = StopWorkspacesResponseUnmarshaller.Instance;return Invoke<StopWorkspacesResponse>(request, options);}
try { dump(); } finally { isOpen = false; } try { channel.truncate(fileLength); } finally { channel.close(); fos.close(); }
} { ) (  DescribeMatchmakingRuleSetsResult ; return ; request DescribeMatchmakingRuleSetsRequest ExecuteDescribeMatchmakingRuleSets = request ) request ( BeforeClientExecution ) request (
} { ) , , , (  string ; null return len off surface wordId ] [
} { ) (  string ; pathStr return
public static double variance(double[] v) { if (v == null || v.length < 1) return 0; int n = v.length; double s = 0; double m = 0; for (int i = 0; i < n; i++) { s += v[i]; m += v[i] * v[i]; } s = s / n; m = m / n; double r = m - s * s; for (int i = 0; i < n; i++) { for (int j = 0; j < n; j++) { if (v[i] != v[i] && Double.IsNaN(v[i])) { r = Double.NaN; return r; } } } return r; }
public virtual DescribeResizeResponse DescribeResize(DescribeResizeRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeResizeRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeResizeResponseUnmarshaller.Instance;return Invoke<DescribeResizeResponse>(request, options);}
public bool PassedThroughNonGreedyDecision { get; }
} { ) (  ; return end ) 0 (
for (int rowNumber = firstRow; rowNumber <= lastRow; rowNumber++) { ctx.RowNumber = rowNumber; currentRow = sheet.GetRow(rowNumber); if (currentRow == null) { if (!traverseEmptyCells) continue; rowSize = lastColumn - firstColumn + 1; } else { rowSize = currentRow.LastCellNum; } for (int colNumber = firstColumn; colNumber <= lastColumn; colNumber++) { ctx.ColNumber = colNumber; if (currentRow == null) { if (!traverseEmptyCells) continue; currentCell = null; } else { currentCell = currentRow.GetCell(colNumber); if (currentCell == null && !traverseEmptyCells) continue; } ctx.OrdinalNumber = ArithmeticUtils.MulAndCheck(ArithmeticUtils.SubAndCheck(ctx.RowNumber, firstRow), width) + ArithmeticUtils.SubAndCheck(ctx.ColNumber, firstColumn) + 1; handler.OnCell(currentCell, ctx); } }
} { ) (  ; pos return
} { ) (  ;  elsereturn if other ScoreTerm ) ( . Float ; return ) ( boost . other , compareTo . == boost . this ) ( get . boost . other boost . this get . ) ( bytes . other ) ( bytes . this
for (int i = 0; i < len; i++) { switch (s[i]) { case YEH: case KAF: case HEH: s[i] = delete; break; case FARSI_YEH: case YEH_BARREE: len = --i; break; case KEHEH: break; case HEH_YEH: case HEH_GOAL: break; case HAMZA_ABOVE: break; } } return len;
out.WriteShort(_options);
} { DiagnosticErrorListener ; ) ( exactOnly = exactOnly bool exactOnly . this
}{KeySchemaElement;;),(setKeyType setAttributeName keyType KeyType attributeName string)()(attributeName)(ToString.keyType)(
} { ) (  GetAssignmentResult ; return ; request GetAssignmentRequest ExecuteGetAssignment = request ) request ( BeforeClientExecution ) request (
return id != null && FindOffset(id) != -1;
public GroupingSearch(bool allGroups) { this.allGroups = allGroups; return this; }
public synchronized void SetDimConfig(string dimName, bool multiValued) { DimConfig ft = fieldTypes.Get(dimName); if (ft == null) { ft = new DimConfig(dimName, multiValued); fieldTypes.Put(dimName, ft); } ft.multiValued = multiValued; }
} { ) (  ; size return ) ; ; ( for ; ; } { HasNext . i 0 = size = i IEnumerator if ; Cell ; char ) ( GetEnumerator . > char < } { ) ( = e = c ) ( Keys . cells ; 0 >= at MoveNext . i ) ( ++ size cmd . e ) c ( ) (
} { ) (  DeleteVoiceConnectorResult ; return ; request DeleteVoiceConnectorRequest ExecuteDeleteVoiceConnector = request ) request ( BeforeClientExecution ) request (
public virtual DeleteLifecyclePolicyResponse DeleteLifecyclePolicy(DeleteLifecyclePolicyRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteLifecyclePolicyRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteLifecyclePolicyResponseUnmarshaller.Instance;return Invoke<DeleteLifecyclePolicyResponse>(request, options);}
