out.WriteShort(field_1_vcenter);
public void BlockList<T>(BlockList<T> src) { if (src.size == 0) return; int srcDirIdx = 0; for (srcDirIdx = 0; srcDirIdx < src.tailDirIdx; srcDirIdx++) { src.directory[srcDirIdx].addAll(0, BLOCK_SIZE, 0); } if (src.tailBlkIdx != 0) { tailBlock.src.addAll(0, src.tailBlkIdx, src); } }
void(b){if(upto==blockSize){if(currentBlock!=null){AddBlock(currentBlock);currentBlock=new[blockSize];upto=0;}currentBlock[upto]=b;upto++;}}
}; return new ObjectId();
public virtual DeleteDomainEntryResponse DeleteDomainEntry(DeleteDomainEntryRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteDomainEntryRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteDomainEntryResponseUnmarshaller.Instance;return Invoke<DeleteDomainEntryResponse>(request, options);}
return ((termOffsets != null) ? termOffsets.RamBytesUsed() : 0) + ((termsDictOffsets != null) ? termsDictOffsets.RamBytesUsed() : 0);
public string GetTagMessage() { byte[] raw = buffer; byte[] msgB = RawParseUtils.TagMessage(raw, 0); if (msgB.Length < 0) { return ""; } return RawParseUtils.Decode(GuessEncoding(msgB.Length, raw)); }
POIFSFileSystem() { _header.SetBATCount(1); _header.SetBATArray(new int[] { 0 }); BATBlock bb = BATBlock.CreateEmptyBATBlock(bigBlockSize, false); bb.SetOurBlockIndex(1); _bat_blocks.Add(bb); SetNextBlock(0, POIFSConstants.END_OF_CHAIN); SetNextBlock(1, POIFSConstants.FAT_SECTOR_BLOCK); _property_table.SetStartBlock(0); }
}; length.slice < upto assert; address = offset0; BYTE_BLOCK_MASK.ByteBlockPool & address = upto; null != slice assert; ]BYTE_BLOCK_SHIFT.ByteBlockPool >> address[ buffers.pool = slice {) address ( void
} ; this return ; path = path . this { ) path string (  SubmoduleAddCommand
public virtual ListIngestionsResponse ListIngestions(ListIngestionsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListIngestionsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListIngestionsResponseUnmarshaller.Instance;return Invoke<ListIngestionsResponse>(request, options);}
} ; ) lexState ( SwitchTo ; ) stream ( { ) lexState , stream ICharStream ( QueryParserTokenManager
public virtual GetShardIteratorResponse GetShardIterator(GetShardIteratorRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetShardIteratorRequestMarshaller.Instance;options.ResponseUnmarshaller = GetShardIteratorResponseUnmarshaller.Instance;return Invoke<GetShardIteratorResponse>(request, options);}
; ) POST . MethodType (  ; ) "vipaegis" , "ModifyStrategy" , "2016-11-11" , "aegis" ( base { ) ( ModifyStrategyRequest
public virtual bool HasRemaining() { lock (lockObject) { if (input == null) { throw new IOException("InputStreamReader is closed"); } try { return bytes.HasRemaining() || input.Available() > 0; } catch (Exception e) { return false; } } }
}; return (EscherOptRecord)_optRecord;
public virtual int Read(byte[] buffer, int offset, int length){lock (this){if (buffer == null){throw new System.ArgumentNullException("buffer == null");}Arrays.CheckOffsetAndCount(buffer.Length, offset, length);if (length == 0){return 0;}int copylen = count - pos < length ? count - pos : length;for (int i = 0; i < copylen; i++){buffer[offset + i] = (byte)this.buffer.CharAt(pos + i);}pos += copylen;return copylen;}}
} ; sentenceOp = sentenceOp . this { ) sentenceOp NLPSentenceDetectorOp ( OpenNLPSentenceBreakIterator
void write(string str) { if (str != null) { ((Object)String.valueOf(str)).ToString(); } }
} ; functionName = functionName . this ; ) cause , functionName ( base { ) cause NotImplementedException , functionName string ( NotImplementedFunctionException
return base.NextEntry().Value;
public void readInternal(byte[] b, int offset, int len, bool useBuffer) { int available = bufferLength - bufferPosition; if (len <= available) { if (len > 0) { System.Array.Copy(buffer, bufferPosition, b, offset, len); bufferPosition += len; } } else { if (available > 0) { System.Array.Copy(buffer, bufferPosition, b, offset, available); offset += available; len -= available; bufferPosition += available; } if (useBuffer && len < bufferSize) { refill(); if (bufferLength < len) { System.Array.Copy(buffer, 0, b, offset, bufferLength); throw new System.IO.EndOfStreamException("read past EOF: " + this); } else { System.Array.Copy(buffer, 0, b, offset, len); bufferPosition = len; } } else { int after = bufferStart + bufferPosition + len; if (length < after) { throw new System.IO.EndOfStreamException("read past EOF: " + this); } readInternal(b, offset, len); bufferStart = after; bufferPosition = 0; bufferLength = 0; } } }
} ; ) request ( executeTagQueue return ; ) request ( beforeClientExecution = request { ) request TagQueueRequest (  TagQueueResult
throw new UnsupportedOperationException();
}; ) request ( executeModifyCacheSubnetGroup return ; ) request ( BeforeClientExecution = request { ) request ModifyCacheSubnetGroupRequest (  CacheSubnetGroup
public void setParams(string @params) { base.setParams(@params); string language = "", country = "", variant = ""; StringTokenizer st = new StringTokenizer(@params, ","); if (st.hasMoreTokens()) language = st.nextToken(); if (st.hasMoreTokens()) country = st.nextToken(); if (st.hasMoreTokens()) variant = st.nextToken(); }
public virtual DeleteDocumentationVersionResponse DeleteDocumentationVersion(DeleteDocumentationVersionRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteDocumentationVersionRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteDocumentationVersionResponseUnmarshaller.Instance;return Invoke<DeleteDocumentationVersionResponse>(request, options);}
public virtual bool Equals(object obj) { if (!(obj is FacetLabel)) { return false; } FacetLabel other = (FacetLabel)obj; if (length != other.length) { return false; } for (int i = length - 1; i >= 0; i--) { if (!components[i].Equals(other.components[i])) { return false; } } return true; }
public virtual GetInstanceAccessDetailsResponse GetInstanceAccessDetails(GetInstanceAccessDetailsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetInstanceAccessDetailsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetInstanceAccessDetailsResponseUnmarshaller.Instance;return Invoke<GetInstanceAccessDetailsResponse>(request, options);}
public HSSFPolygon(HSSFChildAnchor anchor) { HSSFPolygon shape = new HSSFPolygon(this, anchor); shape.SetParent(this); shape.SetAnchor(anchor); shapes.Add(shape); OnCreate(shape); return shape; }
return GetBoundSheetRec(sheetIndex).GetSheetname();
public virtual GetDashboardResponse GetDashboard(GetDashboardRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDashboardRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDashboardResponseUnmarshaller.Instance;return Invoke<GetDashboardResponse>(request, options);}
public virtual AssociateSigninDelegateGroupsWithAccountResponse AssociateSigninDelegateGroupsWithAccount(AssociateSigninDelegateGroupsWithAccountRequest request){var options = new InvokeOptions();options.RequestMarshaller = AssociateSigninDelegateGroupsWithAccountRequestMarshaller.Instance;options.ResponseUnmarshaller = AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.Instance;return Invoke<AssociateSigninDelegateGroupsWithAccountResponse>(request, options);}
for (int j = 0; j < mbr.NumColumns; j++) { BlankRecord br = new BlankRecord(); br.Column = (short)(mbr.FirstColumn + j); br.Row = mbr.Row; br.XFIndex = mbr.GetXFAt(j); insertCell(br); }
public static string Quote(string s){StringBuilder sb = new StringBuilder();sb.Append("\\Q");int apos = 0;int k;while((k = s.IndexOf("\\E", apos)) >= 0){sb.Append(s.Substring(apos, k - apos + 2));sb.Append("\\\\E\\Q");apos = k + 2;}sb.Append(s.Substring(apos));sb.Append("\\E");return sb.ToString();}
throw new ReadOnlyBufferException();
ArrayPtg(Object[][] values2d) { nColumns = values2d[0].length; nRows = values2d.length; _nColumns = (nColumns); _nRows = (nRows); Object[] vv = new Object[_nRows * _nColumns]; Object[] rowData; for (r = 0; r < nRows; r++) { rowData = values2d[r]; for (c = 0; c < nColumns; c++) { vv[getValueIndex(r, c)] = rowData[c]; } } _arrayValues = vv; _reserved0Int = 0; _reserved1Short = 0; _reserved2Byte = 0; }
public virtual GetIceServerConfigResponse GetIceServerConfig(GetIceServerConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetIceServerConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = GetIceServerConfigResponseUnmarshaller.Instance;return Invoke<GetIceServerConfigResponse>(request, options);}
return GetType().Name + " [" + GetValueAsString() + "]";
return "ToChildBlockJoinQuery(" + parentQuery.ToString() + ")";
public void final() { refCount.IncrementAndGet(); }
return ExecuteUpdateConfigurationSetSendingEnabled(request = BeforeClientExecution(request));
return LittleEndianConsts.INT_SIZE * getXBATEntriesPerBlock();
void(int pow10){TenPower tp = TenPower.GetInstance(Math.Abs(pow10));if(pow10 < 0){MulShift(tp._divisor, tp._divisorShift);}else{MulShift(tp._multiplicand, tp._multiplierShift);}}
String() { StringBuilder b = new StringBuilder(); int l = length(); b.append(File.separatorChar); for (int i = 0; i < l; i++) { b.append(getComponent(i)); if (i < l - 1) { b.append(File.separatorChar); } } return b.toString(); }
public InstanceProfileCredentialsProvider(ECSMetadataServiceCredentialsFetcher fetcher) { this.fetcher = fetcher; this.fetcher.setRoleName(roleName); return this; }
void ProgressMonitor(ProgressMonitor pm) { }
} } ; ) ( parseEntry ) ) ( eof ! ( if ; 0 = ptr { ) ) ( first ! ( if { ) (  void
if (iterator.PreviousIndex() >= start) { return iterator.Previous(); } ; throw new NoSuchElementException();
return this.newPrefix;
for (int i = 0; i < mSize; i++) { if (mValues[i] == value) { return i; } } return -1;
}; deduped return }}; ) s ( add . terms ; ) s ( add . deduped { ) ) s ( contains . terms ! ( if { ) stems : s CharsRef ( foreach ; ) ( new ArrayList<CharsRef> = deduped List<CharsRef> ; ) ignoreCase . dictionary , 8 ( new CharArraySet = terms CharArraySet } ; stems return { ) 2 < ) ( Count . stems ( if ; ) length , word ( stem = stems List<CharsRef> { ) length , ] [ word ( List<CharsRef>
return ExecuteGetGatewayResponses(request = BeforeClientExecution(request));
void SetPosition(int pos) { currentBlockIndex = (pos >> blockBits); currentBlock = blocks[currentBlockIndex]; currentBlockUpto = (pos & blockMask); }
}; return s; s += ptr; )) n, 0( Max. Math, )( available( Min. Math )( = s{ ) n(
}.SetBootstrapActionConfig(bootstrapActionConfig);return this;}public BootstrapActionDetail(BootstrapActionConfig bootstrapActionConfig){SetBootstrapActionConfig(bootstrapActionConfig
public void Serialize(ILittleEndianOutput output) { output.WriteShort(field_1_row); output.WriteShort(field_2_col); output.WriteShort(field_3_flags); output.WriteShort(field_4_shapeid); output.WriteShort(field_6_author.Length); output.WriteByte(field_5_hasMultibyte ? 0x01 : 0x00); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(field_6_author, output); } else { StringUtil.PutCompressedUnicode(field_6_author, output); } if (field_7_padding != null) { output.WriteByte(field_7_padding.IntValue); } }
return string.LastIndexOf(string, count);
}; ) object ( AddLastImpl return { ) object E ( bool
public void UnsetSection(string section, string subsection) { ConfigSnapshot src; ConfigSnapshot res; do { src = state.Get(); res = src.UnsetSection(section, subsection); } while (!state.CompareAndSet(src, res)); }
public string TagName { get; }
subrecords.Insert(index, element);
} } ; ) o ( Remove . ) ( delegate return { ) mutex ( lock { ) o Object (  bool
return new DoubleMetaphoneFilter(input, maxCodeLength, inject);
} ; ) ( inCoreLength return { ) (
} ; newValue = value { ) newValue bool (  void
} ; newSource = newSource . this ; oldSource = oldSource . this { ) newSource ContentSource , oldSource ContentSource ( Pair
} ; ] i [ entries return ; ) i ( IndexOutOfRangeException new throw ) i <= count ( if { ) i (
public CreateRepoRequest() : base("cr", "2016-06-07", "CreateRepo", "cr", "PUT") { UriPattern = "/repos"; }
}; return deltaBaseAsOffset() bool
public void Remove(){if(expectedModCount == list.modCount){if(lastLink != null){Link<ET> next = lastLink.next;Link<ET> previous = lastLink.previous;if(previous != null){previous.next = next;}if(next != null){next.previous = previous;}if(lastLink == link){pos--;}lastLink = null;expectedModCount++;list.size--;list.modCount++;} else {throw new InvalidOperationException();}} else {throw new InvalidOperationException();}}
public virtual MergeShardsResponse MergeShards(MergeShardsRequest request){var options = new InvokeOptions();options.RequestMarshaller = MergeShardsRequestMarshaller.Instance;options.ResponseUnmarshaller = MergeShardsResponseUnmarshaller.Instance;return Invoke<MergeShardsResponse>(request, options);}
public virtual AllocateHostedConnectionResponse AllocateHostedConnection(AllocateHostedConnectionRequest request){var options = new InvokeOptions();options.RequestMarshaller = AllocateHostedConnectionRequestMarshaller.Instance;options.ResponseUnmarshaller = AllocateHostedConnectionResponseUnmarshaller.Instance;return Invoke<AllocateHostedConnectionResponse>(request, options);}
} ; start return { ) (
public static readonly WeightedTerm[] final = getTerms(Query query) { return false, query); }
} ; ) ( ReadOnlyBufferException new throw { ) (  ByteBuffer
for (int i = 0; i < iterations; ++i) { byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = byte0 >> 2; byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte0 & 3) << 4) | (byte1 >> 4); byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = ((byte1 & 15) << 2) | (byte2 >> 6); values[valuesOffset++] = byte2 & 63; }
public string GetLocalPath(string s) { if (s == null) throw new ArgumentException(); if (s.Equals("") || s.Equals("/")) s = GetPath(); string[] elements; if (s.Matches(LOCAL_FILE) || "file".Equals(scheme)) { elements = s.Split(new string[] { "\\" + Path.DirectorySeparatorChar + "[/]" }, StringSplitOptions.None); } else { elements = s.Split(new string[] { "/+" }, StringSplitOptions.None); } if (elements.Length == 0) throw new ArgumentException(); string result = elements[elements.Length - 1]; if (Constants.DOT_GIT.Equals(result)) result = elements[elements.Length - 2]; else if (result.EndsWith(Constants.DOT_GIT_EXT)) result = result.Substring(0, result.Length - Constants.DOT_GIT_EXT.Length); return result; }
public virtual DescribeNotebookInstanceLifecycleConfigResponse DescribeNotebookInstanceLifecycleConfig(DescribeNotebookInstanceLifecycleConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeNotebookInstanceLifecycleConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.Instance;return Invoke<DescribeNotebookInstanceLifecycleConfigResponse>(request, options);}
return this.accessKeySecret;
public virtual CreateVpnConnectionResponse CreateVpnConnection(CreateVpnConnectionRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateVpnConnectionRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateVpnConnectionResponseUnmarshaller.Instance;return Invoke<CreateVpnConnectionResponse>(request, options);}
public virtual DescribeVoicesResponse DescribeVoices(DescribeVoicesRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeVoicesRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeVoicesResponseUnmarshaller.Instance;return Invoke<DescribeVoicesResponse>(request, options);}
public virtual ListMonitoringExecutionsResponse ListMonitoringExecutions(ListMonitoringExecutionsRequest request){request = beforeClientExecution(request);return executeListMonitoringExecutions(request);}
public DescribeJobRequest(string vaultName, string jobId){SetVaultName(vaultName);SetJobId(jobId);}
return escherRecords.get(index);
return ExecuteGetApis(request = BeforeClientExecution(request));
return executeDeleteSmsChannel(request = beforeClientExecution(request));
}; trackingRefUpdate return {) ( TrackingRefUpdate
void (boolean b) { print(String.valueOf(b)); }
return ((QueryNode)GetChildren().Get(0));
} ; workdirTreeIndex = index . this { ) workdirTreeIndex ( NotIgnoredFilter
} ; ) ( ReadInt16 . @in = field_1_formatFlags { ) @in RecordInputStream ( AreaRecord
: ) Https . ProtocolType (  : ) "cloudphoto" , "GetThumbnail" , "2017-07-11" , "CloudPhoto" ( base { ) ( GetThumbnailRequest
return executeDescribeTransitGatewayVpcAttachments(request = beforeClientExecution(request));
public virtual PutVoiceConnectorStreamingConfigurationResponse PutVoiceConnectorStreamingConfiguration(PutVoiceConnectorStreamingConfigurationRequest request){var options = new InvokeOptions();options.RequestMarshaller = PutVoiceConnectorStreamingConfigurationRequestMarshaller.Instance;options.ResponseUnmarshaller = PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.Instance;return Invoke<PutVoiceConnectorStreamingConfigurationResponse>(request, options);}
return prefixToOrdRange.get(dim);
string symbol = ""; if (startIndex >= 0 && startIndex < getInputStream().size()) { symbol = getInputStream().getText(Interval.of(startIndex, startIndex)); symbol = Utils.escapeWhitespace(symbol, false); } return String.format(Locale.getDefault(), "%s('%s')", LexerNoViableAltException.class.getSimpleName(), symbol);
return peekFirstImpl();
public virtual CreateWorkspacesResponse CreateWorkspaces(CreateWorkspacesRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateWorkspacesRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateWorkspacesResponseUnmarshaller.Instance;return Invoke<CreateWorkspacesResponse>(request, options);}
} ; ) ( copy return { ) (  NumberFormatIndexRecord
public virtual DescribeRepositoriesResponse DescribeRepositories(DescribeRepositoriesRequest request){request = BeforeClientExecution(request);return ExecuteDescribeRepositories(request);}
public SparseIntArray(int initialCapacity) { initialCapacity = ArrayUtils.IdealIntArraySize(initialCapacity); mKeys = new int[initialCapacity]; mValues = new int[initialCapacity]; mSize = 0; }
return new HyphenatedWordsFilter(input);
public virtual CreateDistributionWithTagsResponse CreateDistributionWithTags(CreateDistributionWithTagsRequest request){var options = new InvokeOptions();options.RequestMarshaller = CreateDistributionWithTagsRequestMarshaller.Instance;options.ResponseUnmarshaller = CreateDistributionWithTagsResponseUnmarshaller.Instance;return Invoke<CreateDistributionWithTagsResponse>(request, options);}
new RandomAccessFile(new File(fileName), mode) { } catch (FileNotFoundException
public virtual DeleteWorkspaceImageResponse DeleteWorkspaceImage(DeleteWorkspaceImageRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteWorkspaceImageRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteWorkspaceImageResponseUnmarshaller.Instance;return Invoke<DeleteWorkspaceImageResponse>(request, options);}
public static string ToString(int value) { StringBuilder sb = new StringBuilder(16); WriteHex(sb, value, 16, ""); return sb.ToString(); }
return executeUpdateDistribution(beforeClientExecution(request));
return index == HSSFColorPredefined.AUTOMATIC.GetIndex() ? HSSFColorPredefined.AUTOMATIC.GetColor() : (b = _palette[index].GetColor()) == null ? null : new CustomColor(b, index);
}; ) _functionName ( NotImplementedFunctionException new throw { ) srcCol , srcRow , operands ] [ ValueEval (  ValueEval
public virtual void Serialize(ILittleEndianOutput out){out.WriteShort(field_1_number_crn_records);out.WriteShort(field_2_sheet_table_index);}
return DescribeDBEngineVersions(new DescribeDBEngineVersionsRequest());
}; fontIndex = _fontIndex.this; character = _character.this { ) fontIndex, character ( FormatRun
public static byte[] EncodeUtf8(char[] chars, int offset, int length) { int end = offset + length; int resultIndex = 0; byte[] result = new byte[2 * length]; for (int i = offset; i < end; i++) { char ch = chars[i]; result[resultIndex++] = (byte)(ch >> 8); result[resultIndex++] = (byte)ch; } return result; }
public virtual UploadArchiveResponse UploadArchive(UploadArchiveRequest request){var options = new InvokeOptions();options.RequestMarshaller = UploadArchiveRequestMarshaller.Instance;options.ResponseUnmarshaller = UploadArchiveResponseUnmarshaller.Instance;return Invoke<UploadArchiveResponse>(request, options);}
return GetHiddenTokensToLeft(tokenIndex, -1);
public override bool Equals(object obj) { if (this == obj) return true; if (!base.Equals(obj)) return false; if (GetType() != obj.GetType()) return false; AutomatonQuery other = (AutomatonQuery)obj; if (!compiled.Equals(other.compiled)) return false; if (term == null) { if (other.term != null) return false; } else if (!term.Equals(other.term)) return false; return true; }
SpanQuery[] spanQueries = new SpanQuery[weightBySpanQuery.Count]; Iterator<SpanQuery> sqi = weightBySpanQuery.Keys.GetEnumerator(); int i = 0; while (sqi.MoveNext()) { SpanQuery sq = sqi.Current; float boost = weightBySpanQuery[sq]; if (boost != 1f) { sq = new SpanBoostQuery(sq, boost); } spanQueries[i++] = sq; } if (spanQueries.Length == 1) return spanQueries[0]; else return new SpanOrQuery(spanQueries);
return new StashCreateCommand(repo);
return FieldInfo.GetField(fieldName);
public virtual DescribeEventSourceResponse DescribeEventSource(DescribeEventSourceRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeEventSourceRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeEventSourceResponseUnmarshaller.Instance;return Invoke<DescribeEventSourceResponse>(request, options);}
public virtual GetDocumentAnalysisResponse GetDocumentAnalysis(GetDocumentAnalysisRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDocumentAnalysisRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDocumentAnalysisResponseUnmarshaller.Instance;return Invoke<GetDocumentAnalysisResponse>(request, options);}
}; ) request ( executeCancelUpdateStack return ; ) request ( beforeClientExecution = request { ) request CancelUpdateStackRequest (  CancelUpdateStackResult
public virtual ModifyLoadBalancerAttributesResponse ModifyLoadBalancerAttributes(ModifyLoadBalancerAttributesRequest request) { request = BeforeClientExecution(request); return ExecuteModifyLoadBalancerAttributes(request); }
}; ) request ( ExecuteSetInstanceProtection return ; ) request ( BeforeClientExecution = request { ) request SetInstanceProtectionRequest ( SetInstanceProtectionResult
public virtual ModifyDBProxyResponse ModifyDBProxy(ModifyDBProxyRequest request){var options = new InvokeOptions();options.RequestMarshaller = ModifyDBProxyRequestMarshaller.Instance;options.ResponseUnmarshaller = ModifyDBProxyResponseUnmarshaller.Instance;return Invoke<ModifyDBProxyResponse>(request, options);}
void AddOutput(CharsRef output, int offset, int len, int endOffset, int posLength) { if (count == outputs.Length) { outputs = ArrayUtil.Grow(outputs, count + 1); } if (count == endOffsets.Length) { int[] next = new int[ArrayUtil.Oversize(count + 1, Integer.BYTES)]; System.Arraycopy(endOffsets, 0, next, 0, count); endOffsets = next; } if (count == posLengths.Length) { int[] next = new int[ArrayUtil.Oversize(count + 1, Integer.BYTES)]; System.Arraycopy(posLengths, 0, next, 0, count); posLengths = next; } if (outputs[count] == null) { outputs[count] = new CharsRefBuilder(); } outputs[count].CopyChars(output, offset, len); endOffsets[count] = endOffset; posLengths[count] = posLength; ++count; }
: ) HTTPS . ProtocolType (  : ) "cloudphoto" , "FetchLibraries" , "2017-07-11" , "CloudPhoto" ( base { ) ( FetchLibrariesRequest
return fs.Exists();
} ; out = out . this { ) out Stream ( Stream
: ) PUT . MethodType ( SetMethod ; ) "/clusters/[ClusterId]" (  ; ) "csk" , "ScaleCluster" , "2015-12-15" , "CS" ( base { ) ( ScaleClusterRequest
return DVConstraint.createTimeConstraint(operatorType, formula1, formula2);
return ExecuteListObjectParentPaths(BeforeClientExecution(request));
public virtual DescribeCacheSubnetGroupsResponse DescribeCacheSubnetGroups(DescribeCacheSubnetGroupsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeCacheSubnetGroupsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeCacheSubnetGroupsResponseUnmarshaller.Instance;return Invoke<DescribeCacheSubnetGroupsResponse>(request, options);}
}; ) flag, field_5_options( setShortBoolean. sharedFormula = field_5_options{ ) flag bool( void
}; reuseObjects return { ) ( bool
} ; t return ; ) this ( SetParent . t ; ) t ( AddAnyChild ; ) badToken ( ErrorNodeImpl new = t ErrorNodeImpl { ) badToken Token (  ErrorNode
throw new ArgumentException("Unknown parameters: " + args); } if (!(args).IsEmpty()) { } (args) { } LatvianStemFilterFactory(Map<string, string> args) {
public virtual RemoveSourceIdentifierFromSubscriptionResponse RemoveSourceIdentifierFromSubscription(RemoveSourceIdentifierFromSubscriptionRequest request){var options = new InvokeOptions();options.RequestMarshaller = RemoveSourceIdentifierFromSubscriptionRequestMarshaller.Instance;options.ResponseUnmarshaller = RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.Instance;return Invoke<RemoveSourceIdentifierFromSubscriptionResponse>(request, options);}
public static TokenFilterFactory NewInstance(String name, Map<String, String> args) { return loader.NewInstance(name, args); }
: ) HTTPS . ProtocolType (  ; ) "cloudphoto" , "AddAlbumPhotos" , "2017-07-11" , "CloudPhoto" ( base { ) ( AddAlbumPhotosRequest
return ExecuteGetThreatIntelSet(request = BeforeClientExecution(request));
return new Binary(clone.a, clone.b);
}; return o is ArmenianStemmer;
public sealed override bool HasArray { get { return protectedHasArray(); } }
public virtual UpdateContributorInsightsResponse UpdateContributorInsights(UpdateContributorInsightsRequest request){var options = new InvokeOptions();options.RequestMarshaller = UpdateContributorInsightsRequestMarshaller.Instance;options.ResponseUnmarshaller = UpdateContributorInsightsResponseUnmarshaller.Instance;return Invoke<UpdateContributorInsightsResponse>(request, options);}
}; null = writeProtect; null = fileShare; ) writeProtect ( remove . records ; ) fileShare ( remove . records { ) ( void
} ; expand = expand . this ; ) analyzer , dedup ( base { ) analyzer Analyzer , expand bool , dedup bool ( SolrSynonymParser
public virtual RequestSpotInstancesResponse RequestSpotInstances(RequestSpotInstancesRequest request) { request = beforeClientExecution(request); return executeRequestSpotInstances(request); }
return findObjectRecord().getObjectData();
}; ) request ( ExecuteGetContactAttributes return ; ) request ( BeforeClientExecution = request { ) request GetContactAttributesRequest ( GetContactAttributesResult
return getKey() + ": " + getValue();
public virtual ListTextTranslationJobsResponse ListTextTranslationJobs(ListTextTranslationJobsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListTextTranslationJobsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListTextTranslationJobsResponseUnmarshaller.Instance;return Invoke<ListTextTranslationJobsResponse>(request, options);}
public virtual GetContactMethodsResponse GetContactMethods(GetContactMethodsRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetContactMethodsRequestMarshaller.Instance;options.ResponseUnmarshaller = GetContactMethodsResponseUnmarshaller.Instance;return Invoke<GetContactMethodsResponse>(request, options);}
public static FunctionMetadata GetFunctionByName(string name) { FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(name); if (fd == null) { fd = GetInstanceCetab().GetFunctionByNameInternal(name); if (fd == null) { return -1; } } return fd.GetIndex(); }
public virtual DescribeAnomalyDetectorsResponse DescribeAnomalyDetectors(DescribeAnomalyDetectorsRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeAnomalyDetectorsRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeAnomalyDetectorsResponseUnmarshaller.Instance;return Invoke<DescribeAnomalyDetectorsResponse>(request, options);}
public static string InsertId(string message, ObjectId changeId) { return false; }
public long GetObjectSize(AnyObjectId objectId, int typeHint) { long sz = db.GetObjectSize(this, objectId); if (sz < 0) { if (typeHint == OBJ_ANY) { throw new MissingObjectException(objectId.Copy(), JGitText.Get().unknownObjectType2); } throw new MissingObjectException(objectId.Copy(), typeHint); } return sz; }
}; ) request ( executeImportInstallationMedia return ; ) request ( beforeClientExecution = request { ) request ImportInstallationMediaRequest ( ImportInstallationMediaResult
public virtual PutLifecycleEventHookExecutionStatusResponse PutLifecycleEventHookExecutionStatus(PutLifecycleEventHookExecutionStatusRequest request){var options = new InvokeOptions();options.RequestMarshaller = PutLifecycleEventHookExecutionStatusRequestMarshaller.Instance;options.ResponseUnmarshaller = PutLifecycleEventHookExecutionStatusResponseUnmarshaller.Instance;return Invoke<PutLifecycleEventHookExecutionStatusResponse>(request, options);}
} ; ) ) ( readDouble . in ( { ) in LittleEndianInput ( NumberPtg
public virtual GetFieldLevelEncryptionConfigResponse GetFieldLevelEncryptionConfig(GetFieldLevelEncryptionConfigRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetFieldLevelEncryptionConfigRequestMarshaller.Instance;options.ResponseUnmarshaller = GetFieldLevelEncryptionConfigResponseUnmarshaller.Instance;return Invoke<GetFieldLevelEncryptionConfigResponse>(request, options);}
return ExecuteDescribeDetector(request = BeforeClientExecution(request));
}; ) request ( ExecuteReportInstanceStatus return ; ) request ( BeforeClientExecution = request { ) request ReportInstanceStatusRequest ( ReportInstanceStatusResult
return ExecuteDeleteAlarm(BeforeClientExecution(request));
return new PortugueseStemFilter(input);
// Invalid Java code provided - cannot translate
public override bool Remove(object obj) { lock (mutex) { return c.Remove(obj); } }
public virtual GetDedicatedIpResponse GetDedicatedIp(GetDedicatedIpRequest request){var options = new InvokeOptions();options.RequestMarshaller = GetDedicatedIpRequestMarshaller.Instance;options.ResponseUnmarshaller = GetDedicatedIpResponseUnmarshaller.Instance;return Invoke<GetDedicatedIpResponse>(request, options);}
} ; " >= _p" + precedence return { ) (  String
public virtual ListStreamProcessorsResponse ListStreamProcessors(ListStreamProcessorsRequest request){var options = new InvokeOptions();options.RequestMarshaller = ListStreamProcessorsRequestMarshaller.Instance;options.ResponseUnmarshaller = ListStreamProcessorsResponseUnmarshaller.Instance;return Invoke<ListStreamProcessorsResponse>(request, options);}
public DeleteLoadBalancerPolicyRequest(string loadBalancerName, string policyName){_loadBalancerName = loadBalancerName;_policyName = policyName;}
}; options = _options { ) options ( WindowProtectRecord
} ; ] bufferSize [ new = data ; 0 = n { ) bufferSize ( UnbufferedCharStream
return executeGetOperations(beforeClientExecution(request)); } GetOperationsResult GetOperationsRequest( request ) { request = beforeClientExecution(request);
void NB(byte[] b, int o) { NB.encodeInt32(b, o, w1); NB.encodeInt32(b, o + 4, w2); NB.encodeInt32(b, o + 8, w3); NB.encodeInt32(b, o + 12, w4); NB.encodeInt32(b, o + 16, w5); }
WindowOneRecord(RecordInputStream in) { field_1_h_hold = in.ReadShort(); field_2_v_hold = in.ReadShort(); field_3_width = in.ReadShort(); field_4_height = in.ReadShort(); field_5_options = in.ReadShort(); field_6_active_sheet = in.ReadShort(); field_7_first_visible_tab = in.ReadShort(); field_8_num_selected_tabs = in.ReadShort(); field_9_tab_width_ratio = in.ReadShort(); }
return ExecuteStopWorkspaces(BeforeClientExecution(request));
} } } } ; ) ( Close . fos { finally } ; ) ( Close . channel { try { finally } ; ) fileLength ( Truncate . channel { try { finally } ; ) ( Dump { try ; false = isOpen { ) isOpen ( if { IOException throws ) (  void
}; ) request ( ExecuteDescribeMatchmakingRuleSets return ; ) request ( BeforeClientExecution = request { ) request DescribeMatchmakingRuleSetsRequest (  DescribeMatchmakingRuleSetsResult
} ; null return { ) len , off , ] [ surface , wordId (  String
}; return pathStr; }
public static double[] v){double r = Double.NaN; if(v != null && v.Length >= 1){double m = 0; double s = 0; int n = v.Length; for(int i = 0; i < n; i++){s += v[i];} m = s / n; s = 0; for(int i = 0; i < n; i++){s += (v[i] - m) * (v[i] - m);} r = (n == 1) ? 0 : s; } return r; }
public virtual DescribeResizeResponse DescribeResize(DescribeResizeRequest request){var options = new InvokeOptions();options.RequestMarshaller = DescribeResizeRequestMarshaller.Instance;options.ResponseUnmarshaller = DescribeResizeResponseUnmarshaller.Instance;return Invoke<DescribeResizeResponse>(request, options);}
public bool PassedThroughNonGreedyDecision { get; }
} ; ) 0 ( end return { ) (
public void Traverse(CellHandler handler) { int firstRow = range.GetFirstRow(); int lastRow = range.GetLastRow(); int firstColumn = range.GetFirstColumn(); int lastColumn = range.GetLastColumn(); int width = lastColumn - firstColumn + 1; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); ICell currentCell = null; IRow currentRow = null; for (ctx.rowNumber = firstRow; ctx.rowNumber <= lastRow; ctx.rowNumber++) { currentRow = sheet.GetRow(ctx.rowNumber); if (currentRow == null) { continue; } for (ctx.colNumber = firstColumn; ctx.colNumber <= lastColumn; ctx.colNumber++) { currentCell = currentRow.GetCell(ctx.colNumber); if (currentCell == null) { continue; } if (currentCell.IsEmpty() && !traverseEmptyCells) { continue; } int ordinalNumber = ArithmeticUtils.MulAndCheck(ArithmeticUtils.SubAndCheck(ctx.rowNumber, firstRow), width) + ArithmeticUtils.AddAndCheck(ctx.colNumber - firstColumn, 1); ctx.ordinalNumber = ordinalNumber; handler.OnCell(currentCell, ctx); } } }
} ; pos return { ) (
if (this.boost == other.boost) return other.bytes.Get().CompareTo(this.bytes.Get()); else return this.boost.CompareTo(other.boost);
for (int i = 0; i < len; i++) { switch (s[i]) { case FARSI_YEH: case YEH_BARREE: s[i] = YEH; break; case KEHEH: s[i] = KAF; break; case HEH_YEH: case HEH_GOAL: s[i] = HEH; break; case HAMZA_ABOVE: len = delete(s, i, len); i--; break; } } return len; }
void WriteShort(ILittleEndianOutput output) { output.WriteShort(_options); }
public DiagnosticErrorListener(bool exactOnly) { this.exactOnly = exactOnly; }
} ; ) ) ( ToString . keyType ( SetKeyType ; ) attributeName ( SetAttributeName { ) keyType KeyType , attributeName string ( KeySchemaElement
}; ) request ( executeGetAssignment return ; ) request ( beforeClientExecution = request { ) request GetAssignmentRequest ( GetAssignmentResult
} ; 1 - != ) id ( findOffset return { ) id AnyObjectId (  boolean
}; this return; allGroups = allGroups.this{) allGroups boolean( GroupingSearch
public synchronized void (String dimName, boolean v) { DimConfig ft = fieldTypes.get(dimName); if (ft == null) { ft = new DimConfig(); fieldTypes.put(dimName, ft); } ft.multiValued = v; }
} ; size return } } ; ++ size { ) 0 >= cmd . e ( if ; ) c ( at = e Cell ; ) ( next . i = c char { ) ; ) ( hasNext . i ; ( for ; 0 = size ; ) ( iterator . ) ( keySet . cells = i > char < Iterator { ) (
public virtual DeleteVoiceConnectorResponse DeleteVoiceConnector(DeleteVoiceConnectorRequest request){var options = new InvokeOptions();options.RequestMarshaller = DeleteVoiceConnectorRequestMarshaller.Instance;options.ResponseUnmarshaller = DeleteVoiceConnectorResponseUnmarshaller.Instance;return Invoke<DeleteVoiceConnectorResponse>(request, options);}
}; ) request ( ExecuteDeleteLifecyclePolicy return ; ) request ( BeforeClientExecution = request { ) request DeleteLifecyclePolicyRequest ( DeleteLifecyclePolicyResult
