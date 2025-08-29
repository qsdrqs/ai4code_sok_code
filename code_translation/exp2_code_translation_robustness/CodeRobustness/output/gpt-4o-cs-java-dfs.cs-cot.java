void Serialize(ILittleEndianOutput out1) { out1.WriteShort(); }
if (size == 0) return; for (int srcDirIdx = 0; srcDirIdx < tailDirIdx.src; ++srcDirIdx) { AddAll(directory.src[srcDirIdx], , ); } if (tailBlkIdx.src != 0) { AddAll(tailBlock.src, , ); } }
} ; b ) byte [ ] ++ upto . outerInstance [ currentBlock . outerInstance } ; 0 upto . outerInstance ; ] blockSize . outerInstance [ byte new currentBlock . outerInstance } ; ) upto . outerInstance ( Add . blockEnd . outerInstance ; ) currentBlock . outerInstance ( Add . blocks . outerInstance { ) null != currentBlock . outerInstance ( if { ) blockSize . outerInstance == upto . outerInstance ( if { ) b byte ( writeByte void
return ObjectId.getObjectId();
DeleteDomainEntryResponse response = (DeleteDomainEntryResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(DeleteDomainEntryRequestMarshaller.getInstance()); setResponseUnmarshaller(DeleteDomainEntryResponseUnmarshaller.getInstance()); }}, request);
return (fst == null) ? 0 : GetSizeInBytes(fst);
return RawParseUtils.decode(RawParseUtils.parseEncoding(raw), (msgB = RawParseUtils.tagMessage(raw)) < 0 ? "" : new String(GetFullMessage(buffer), enc));
POIFSFileSystem fileSystem = new POIFSFileSystem(); HeaderBlock headerBlock = new HeaderBlock(); PropertyTable _property_table = new PropertyTable(); ArrayList _documents = new ArrayList(); Object _root = null;
} ; ) Length . slice < upto ( Assert . Debug ; address offset0 ; BYTE_BLOCK_MASK . ByteBlockPool & address upto ; ) null != slice ( Assert . Debug ; ] BYTE_BLOCK_SHIFT . ByteBlockPool >> address [ Buffers . pool slice { ) address int ( Init void
} return; path.path().setPath(string).SubmoduleAddCommand.Api.NGit;
ListIngestionsResponse response = (ListIngestionsResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(ListIngestionsRequestMarshaller.getInstance()); setResponseUnmarshaller(ListIngestionsResponseUnmarshaller.getInstance()); }}, request);
public void SwitchTo(int lexState, ICharStream stream) { this.stream = stream; }
GetShardIteratorResponse response = (GetShardIteratorResponse) invoke(new InvokeOptions() {{ requestMarshaller = GetShardIteratorRequestMarshaller.getInstance(); responseUnmarshaller = GetShardIteratorResponseUnmarshaller.getInstance(); }}, getShardIteratorRequest);
} // POST . MethodType Method { ) " " , " " , " " , " " , " " ( : ) ( ModifyStrategyRequest
if (in == null) { throw new IOException("System.IO"); } synchronized (lock) { if (!in.available() > 0 || !bytes.hasRemaining()) { try { return; } catch (IOException e) { throw new IOException("System.IO"); } } }
return new EscherOptRecord().getOptRecord();
if (buffer == null) throw new NullPointerException("buffer"); synchronized (this) { if (length == 0) return 0; int copylen = Math.min(length, buffer.length - offset); for (int i = 0; i < copylen; i++) { buffer[offset + i] = (byte) (unchecked(buffer[pos + i])); } pos += copylen; return copylen; }
} sentenceOp; sentenceOp.NLPSentenceDetectorOp(new OpenNLPSentenceBreakIterator());
} ; ) ) null ) Object ( ( getValueOf . StringHelper . sharpen : str != null ? str ( write { ) str String ( print void
throw new UnsupportedOperationException("NotImplementedFunctionException");
return nextEntry.next().value;
void ReadBytes(byte[] b, int offset, int len, boolean useBuffer) { int available = 0; if (len > 0) { if (available > 0) { System.arraycopy(Buffer, bufferPosition, b, offset, available); len -= available; bufferPosition += available; } else { if (useBuffer && len < bufferSize) { Refill(); if (len < bufferLength) { System.arraycopy(Buffer, bufferPosition, b, offset, len); bufferPosition += len; } else { throw new EndOfStreamException(" "); } } else { throw new EndOfStreamException(" "); } } } }
TagQueueResponse response = (TagQueueResponse) invoke(new InvokeOptions() {{ requestMarshaller = TagQueueRequestMarshaller.getInstance(); responseUnmarshaller = TagQueueResponseUnmarshaller.getInstance(); }}, request);
throw new UnsupportedOperationException();
ModifyCacheSubnetGroupResponse response = (ModifyCacheSubnetGroupResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(ModifyCacheSubnetGroupRequestMarshaller.getInstance()); setResponseUnmarshaller(ModifyCacheSubnetGroupResponseUnmarshaller.getInstance()); }}, request);
void SetParams(String... params) { StringTokenizer st = new StringTokenizer(" ", " "); if (st.hasMoreTokens()) { String culture = st.nextToken(); if (st.hasMoreTokens()) { culture += " " + st.nextToken(); } } }
return (DeleteDocumentationVersionResponse) invoke(request, new InvokeOptions() {{ setRequestMarshaller(DeleteDocumentationVersionRequestMarshaller.getInstance()); setResponseUnmarshaller(DeleteDocumentationVersionResponseUnmarshaller.getInstance()); }});
if (!(obj instanceof FacetLabel)) return false; FacetLabel other = (FacetLabel) obj; if (Length != other.Length) return false; for (int i = Length - 1; i >= 0; i--) if (!Components[i].Equals(other.Components[i], StringComparison.Ordinal)) return false; return true;
return (GetInstanceAccessDetailsResponse) instance.invoke(new InvokeOptions() {{ requestMarshaller = Instance.GetInstanceAccessDetailsRequestMarshaller.options; responseUnmarshaller = Instance.GetInstanceAccessDetailsResponseUnmarshaller.options; }}, request);
HSSFPolygon shape = new HSSFPolygon(); HSSFChildAnchor anchor = (HSSFChildAnchor) shape.CreatePolygon(anchor); shape.setParent(Anchor.shape); shapes.add(shape); return shape;
return getBoundSheetRec(sheetIndex).getSheetName();
GetDashboardResponse response = (GetDashboardResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(GetDashboardRequestMarshaller.getInstance()); setResponseUnmarshaller(GetDashboardResponseUnmarshaller.getInstance()); }}, request);
AssociateSigninDelegateGroupsWithAccountResponse response = client.invoke(new AssociateSigninDelegateGroupsWithAccountRequest(), AssociateSigninDelegateGroupsWithAccountResponse.class, new InvokeOptions().withRequestMarshaller(AssociateSigninDelegateGroupsWithAccountRequestMarshaller.getInstance()).withResponseUnmarshaller(AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller.getInstance()));
void AddMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < NumColumns.mbr; j++) { BlankRecord br = new BlankRecord(); InsertCell(GetXFAt.mbr(XFIndex.br), Row.mbr, Row.br, FirstColumn.mbr + j, Column.br); } }
StringBuilder sb = new StringBuilder(); int k = 0; int apos; while ((apos = string.indexOf("\\", k)) >= 0) { sb.append(string.substring(k, apos)); sb.append("\\\\"); k = apos + 1; } return sb.append(string.substring(k)).toString();
throw new java.nio.ReadOnlyBufferException(); java.nio.ByteBuffer.putInt(value);
Object[][] values2d = new Object[_nRows * _nColumns]; for (int r = 0; r < nRows; r++) { for (int c = 0; c < nColumns; c++) { vv = rowData[r][c]; GetValueIndex[vv]; } } short _nColumns = (short) nColumns; short _nRows = (short) nRows; Object[] _arrayValues; int _reserved0Int = 0; short _reserved1Short = 0; byte _reserved2Byte = 0;
return (GetIceServerConfigResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.GetIceServerConfigRequestMarshaller); setResponseUnmarshaller(Instance.GetIceServerConfigResponseUnmarshaller); }}, request);
StringBuilder sb = new StringBuilder(); sb.append(String.class.getTypeName()).append(" ").append(Name).append(" ").append(GetValueAsString()); return sb.toString();
return " " + _parentQuery + " " + field.toString();
void IncRef(IncrementAndGet.refCount());
return invoke(new InvokeOptions() {{ setRequestMarshaller(UpdateConfigurationSetSendingEnabledRequestMarshaller.getInstance()); setResponseUnmarshaller(UpdateConfigurationSetSendingEnabledResponseUnmarshaller.getInstance()); }}, request, UpdateConfigurationSetSendingEnabledResponse.class);
} (int) LittleEndianConsts.INT_SIZE * getXBATEntriesPerBlock() { return getNextXBATChainOffset(); }
void multiplyByPowerOfTen(int pow10) { if (pow10 < 0) { mulShift(_divisor.tp, _divisorShift.tp); } else { mulShift(_multiplicand.tp, _multiplierShift.tp); } TenPower.tp = Math.abs(pow10); }
return builder.toString(); } } StringBuilder builder = new StringBuilder(); int length = Path.DirectorySeparatorChar; for (int i = 0; i < length; i++) { builder.append(GetComponent(i)); } if (i < length - 1) { builder.append(Path.DirectorySeparatorChar); }
void withFetcher(ECSMetadataServiceCredentialsFetcher fetcher) { fetcher.fetcher().SetRoleName(fetcher); }
} ProgressMonitor pm; void setProgressMonitor(ProgressMonitor pm) {
if (!Eof) { if (!First) { ptr = 0; } } void Reset() { ParseEntry(); }
throw new java.util.NoSuchElementException(); return iterator.previous(); if (iterator.previousIndex() >= start) { E previous }
} newPrefix.return new String(getNewPrefix());
int indexOfValue(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) return i; } return -1; }
List<CharsRef> UniqueStems(char[] word, int length, IList<CharsRef> stems) { if (stems.Count < 2) { return stems; } CharArraySet terms = new CharArraySet(8, true); for (CharsRef s : stems) { if (!terms.contains(s)) { terms.add(s); } } return new ArrayList<>(terms); }
GetGatewayResponsesResponse response = (GetGatewayResponsesResponse) Invoke(new InvokeOptions() {{ RequestMarshaller = GetGatewayResponsesRequestMarshaller.getInstance(); ResponseUnmarshaller = GetGatewayResponsesResponseUnmarshaller.getInstance(); }}, request);
};) blockMask.outerInstance & position() int (currentBlockUpto;][blocks.outerInstance currentBlock;) blockBits.outerInstance >> position() int (currentBlockIndex {) position long (SetPosition void
int s = Math.min(Math.max((int) ptr, (int) Available), (int) Skip); return s;
} BootstrapActionConfig _bootstrapActionConfig; BootstrapActionConfig bootstrapActionConfig = new BootstrapActionConfig(BootstrapActionDetail);
Serialize void ILittleEndianOutput out1 { WriteShort.out1(); WriteShort.out1(); WriteShort.out1(); WriteShort.out1(); WriteShort.out1(); field_6_author.Length; out1.WriteByte(field_5_hasMultibyte ? 0x01 : 0x00); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(out1); } else { StringUtil.PutCompressedUnicode(out1); } if (field_7_padding != null) { out1.WriteByte(Convert.ToInt32(CultureInfo.InvariantCulture)); }
} ; ) , ( lastIndexOf return { ) @string String ( lastIndexOf int
return addLastImpl((E) object);
} ; ) ) , ( compareAndSet . state ! ( while } ; ) , , ( unsetSection res ; ) ( get . state src { do ; ConfigSnapshot ; ConfigSnapshot { ) subsection String , section String ( unsetSection void
return getTagName();
void AddSubRecord(int index, SubRecord element) { subrecords.Insert(index, element); }
synchronized (mutex) { return object.remove(object); }
return new DoubleMetaphoneFilter(input);
return (long) Length(InCoreLength);
void setValue(boolean newValue) { this.newValue = newValue; }
Pair<ContentSource, ContentSource> pair = new Pair<>(newSource, oldSource);
throw new IndexOutOfBoundsException(); for (int i = 0; i <= count; i++) { return entries[i]; }
} PUT.MethodType Method; " " UriPattern { ) " ", " ", " ", " ", " " (: ) (CreateRepoRequest
boolean IsDeltaBaseAsOffset() { return deltaBaseAsOffset; }
if (modCount == expectedModCount) { if (lastLink != null) { LinkedList.Link<ET> next = lastLink.next; LinkedList.Link<ET> previous = lastLink.previous; previous.next = next; next.previous = previous; lastLink = null; ++expectedModCount; --list._size; ++list.modCount; } else { throw new java.util.ConcurrentModificationException(); } } else { throw new System.InvalidOperationException(); }
MergeShardsResponse response = (MergeShardsResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.MergeShardsRequestMarshaller); setResponseUnmarshaller(Instance.MergeShardsResponseUnmarshaller); }}, request);
return (AllocateHostedConnectionResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.AllocateHostedConnectionRequestMarshaller); setResponseUnmarshaller(Instance.AllocateHostedConnectionResponseUnmarshaller); }}, request, AllocateHostedConnectionResponse.class);
int getBeginIndex() { return 0; }
return GetTerms(query)[WeightedTerm];
throw new java.nio.ReadOnlyBufferException(); java.nio.ByteBuffer.compact();
void Decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; i++) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (int) ((uint) byte0 >> 2); int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (int) (((uint) byte0 << 4) | ((uint) byte1 >> 4)); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (int) (((uint) byte1 << 2) | ((uint) byte2 >> 6)); values[valuesOffset++] = (int) ((uint) byte2 & 63); } }
if (GetHumanishName.equals("")) { if (GetPath == null || GetPath.equals("") || GetPath.equals(null)) { throw new IllegalArgumentException(); } String s = GetPath; String[] elements = s.split(FilePath.separatorChar + " "); if (elements.length == 0) { throw new IllegalArgumentException(); } String result = elements[elements.length - 1]; if (Constants.DOT_GIT.equals(result)) { result = elements[elements.length - 2]; } else if (result.endsWith(Constants.DOT_GIT_EXT)) { result = result.substring(0, result.length() - Constants.DOT_GIT_EXT.length()); } return result; }
return DescribeNotebookInstanceLifecycleConfigResponse.invoke(new InvokeOptions(DescribeNotebookInstanceLifecycleConfigRequestMarshaller.options, DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.options), request);
} return AccessSecret { String getAccessKeySecret()
return Invoke(new InvokeOptions() {{ RequestMarshaller = CreateVpnConnectionRequestMarshaller.getInstance(); ResponseUnmarshaller = CreateVpnConnectionResponseUnmarshaller.getInstance(); }}, request, CreateVpnConnectionResponse.class);
DescribeVoicesResponse response = (DescribeVoicesResponse) new InvokeOptions() {{ RequestMarshaller = Instance.DescribeVoicesRequestMarshaller.options; ResponseUnmarshaller = Instance.DescribeVoicesResponseUnmarshaller.options; }}.Invoke(request, DescribeVoicesResponse.class);
ListMonitoringExecutionsResponse response = (ListMonitoringExecutionsResponse) invoke(new InvokeOptions(ListMonitoringExecutionsRequestMarshaller.getInstance().options, ListMonitoringExecutionsResponseUnmarshaller.getInstance().options), request);
DescribeJobRequest(String jobId, String vaultName) { this.jobId = jobId; this.vaultName = vaultName; }
EscherRecord GetEscherRecord(int index) { return escherRecords[index]; }
GetApisResponse response = (GetApisResponse) Invoke(new InvokeOptions() {{ RequestMarshaller = Instance.GetApisRequestMarshaller; ResponseUnmarshaller = Instance.GetApisResponseUnmarshaller; }}, request);
DeleteSmsChannelResponse response = (DeleteSmsChannelResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(DeleteSmsChannelRequestMarshaller.getInstance()); setResponseUnmarshaller(DeleteSmsChannelResponseUnmarshaller.getInstance()); }}, request);
return TrackingRefUpdate.getTrackingRefUpdate();
} ; ) ) ( toString . b ( System.out.print { ) b boolean ( System.out.print void
return (IQueryNode) GetChild().GetChildren();
NotIgnoredFilter workdirTreeIndex = (int) workdirTreeIndex;
new AreaRecord(new RecordInputStream(in1)).field_1_formatFlags = ReadShort.in1;
} HTTPS.ProtocolType Protocol { } " " , " " , " " , " " , " " ( : ) ( GetThumbnailRequest;
return DescribeTransitGatewayVpcAttachmentsResponse.class.cast(new InvokeOptions() {{ setRequestMarshaller(DescribeTransitGatewayVpcAttachmentsRequestMarshaller.getInstance()); setResponseUnmarshaller(DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance()); }}).invoke(request, DescribeTransitGatewayVpcAttachmentsResponse.class);
PutVoiceConnectorStreamingConfigurationResponse response = (PutVoiceConnectorStreamingConfigurationResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance()); setResponseUnmarshaller(PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance()); }}, request);
OrdRange GetOrdRange(String dim) { return prefixToOrdRange.TryGetValue(dim, out OrdRange result) ? result : null; }
} ; ) , Name . ) LexerNoViableAltException . Runtime . Antlr4 ( typeof , " " , CurrentCulture . CultureInfo ( Format . string return } ; ) , ( EscapeWhitespace . Utils symbol ; ) ) , ( Of . Interval ( GetText . ) InputStream ) ICharStream ( ( symbol { ) Size . ) InputStream ) ICharStream ( ( < startIndex && 0 >= startIndex ( if ; Empty . string = symbol string { ) ( ToString string
return peekFirstImpl(); } } while (peek());
CreateWorkspacesResponse response = (CreateWorkspacesResponse) invoke(new InvokeOptions() {{ requestMarshaller = CreateWorkspacesRequestMarshaller.getInstance(); responseUnmarshaller = CreateWorkspacesResponseUnmarshaller.getInstance(); }}, request);
NumberFormatIndexRecord rec = new NumberFormatIndexRecord() { @Override public Object clone() { return rec.field_1_formatIndex; } };
DescribeRepositoriesResponse response = (DescribeRepositoriesResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.DescribeRepositoriesRequestMarshaller); setResponseUnmarshaller(Instance.DescribeRepositoriesResponseUnmarshaller); }}, request);
SparseIntArray sparseIntArray = new SparseIntArray(initialCapacity) { int[] mKeys = new int[initialCapacity]; int[] mValues = new int[initialCapacity]; int mSize = 0; };
return new HyphenatedWordsFilter(input);
CreateDistributionWithTagsResponse response = (CreateDistributionWithTagsResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.CreateDistributionWithTagsRequestMarshaller); setResponseUnmarshaller(Instance.CreateDistributionWithTagsResponseUnmarshaller); }}, request);
throw new UnsupportedOperationException(); new RandomAccessFile(fileName, mode);
DeleteWorkspaceImageResponse response = (DeleteWorkspaceImageResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.getInstance()); setResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.getInstance()); }}, request);
return Long.toHexString((int) value);
UpdateDistributionResponse response = (UpdateDistributionResponse) invoke(new InvokeOptions() {{ requestMarshaller = UpdateDistributionRequestMarshaller.getInstance(); responseUnmarshaller = UpdateDistributionResponseUnmarshaller.getInstance(); }}, request);
if (index == HSSFColor.HSSFColorPredefined.AUTOMATIC.getIndex()) { return HSSFColor.HSSFColorPredefined.AUTOMATIC.getInstance(); } else { if (b != null) { return new CustomColor(palette.GetColor(b)); } return null; }
throw new NotImplementedFunctionException(); Evaluate(new ValueEval[] { operands, srcRow, srcCol });
} ; ) field_2_sheet_table_index ) short ( ( WriteShort . out1 ; ) field_1_number_crn_records ) short ( ( WriteShort . out1 { ) out1 ILittleEndianOutput ( Serialize void
return new DescribeDBEngineVersionsRequest(); DescribeDBEngineVersionsResponse DescribeDBEngineVersions();
FormatRun(short fontIndex, short character) { this.fontIndex = fontIndex; this.character = character; }
byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int resultIndex = 0; for (int i = offset; i < offset + length; i++) { char ch = chars[i]; result[resultIndex++] = (byte) (ch >> 8); result[resultIndex++] = (byte) (ch); } return result; }
UploadArchiveResponse response = (UploadArchiveResponse) invoke(new InvokeOptions() {{ requestMarshaller = Instance.UploadArchiveRequestMarshaller; responseUnmarshaller = Instance.UploadArchiveResponseUnmarshaller; }}, uploadArchiveRequest);
return GetHiddenTokensToLeft(tokenIndex, 1);
if (obj == null) return false; if (!(obj instanceof AutomatonQuery)) return false; AutomatonQuery other = (AutomatonQuery) obj; if (!m_compiled.equals(other.m_compiled)) return false; if (m_term == null) { if (other.m_term != null) return false; } else { if (!m_term.equals(other.m_term)) return false; } return true;
List<SpanQuery> spanQueries = new ArrayList<>(); for (var weightBySpanQuery : wsq.entrySet()) { SpanQuery spanQuery = MakeSpanClause(weightBySpanQuery.getKey().Boost(weightBySpanQuery.getValue())); spanQueries.add(spanQuery); } if (spanQueries.size() == 1) return spanQueries.get(0); return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0]));
return new StashCreateCommand(StashCreate());
FieldInfo FieldInfo = byName.TryGetValue(fieldName, out FieldInfo) ? FieldInfo : null;
DescribeEventSourceResponse response = (DescribeEventSourceResponse) invoke(new InvokeOptions() {{ requestMarshaller = Instance.DescribeEventSourceRequestMarshaller.options; responseUnmarshaller = Instance.DescribeEventSourceResponseUnmarshaller.options; }}, request);
GetDocumentAnalysisResponse response = (GetDocumentAnalysisResponse) invoke(new InvokeOptions() {{ requestMarshaller = GetDocumentAnalysisRequestMarshaller.getInstance(); responseUnmarshaller = GetDocumentAnalysisResponseUnmarshaller.getInstance(); }}, request);
return (CancelUpdateStackResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(CancelUpdateStackRequestMarshaller.getInstance()); setResponseUnmarshaller(CancelUpdateStackResponseUnmarshaller.getInstance()); }}, request, CancelUpdateStackResponse.class);
return ModifyLoadBalancerAttributesResponse.class.cast(invoke(new InvokeOptions().setRequestMarshaller(ModifyLoadBalancerAttributesRequestMarshaller.getInstance()).setResponseUnmarshaller(ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance()), request, ModifyLoadBalancerAttributesResponse.class));
SetInstanceProtectionResponse response = (SetInstanceProtectionResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.SetInstanceProtectionRequestMarshaller.getInstance()); setResponseUnmarshaller(Instance.SetInstanceProtectionResponseUnmarshaller.getInstance()); }}, request);
return ModifyDBProxyResponse.class.cast(new InvokeOptions() {{ setRequestMarshaller(ModifyDBProxyRequestMarshaller.getInstance()); setResponseUnmarshaller(ModifyDBProxyResponseUnmarshaller.getInstance()); }}).invoke(request, ModifyDBProxyResponse.class);
} ; ++ count ; posLength ] [ posLengths ; endOffset ] [ endOffsets ; ) , , ( CopyChars . ] [ outputs } ; ) ( CharsRef new ] [ outputs { ) null == ] [ outputs ( if } ; next posLengths ; ) , , , , ( Copy . Array ; ] ) NUM_BYTES_INT32 . RamUsageEstimator , count + 1 ( Oversize . ArrayUtil [ int new = next ] [ int { ) Length . posLengths == count ( if } ; next endOffsets ; ) , , , , ( Copy . Array ; ] ) NUM_BYTES_INT32 . RamUsageEstimator , count + 1 ( Oversize . ArrayUtil [ int new = next ] [ int { ) Length . endOffsets == count ( if } ; next outputs ; ) , , , , ( Copy . Array ; ] ) NUM_BYTES_OBJECT_REF . RamUsageEstimator , count + 1 ( Oversize . ArrayUtil [ CharsRef new = next ] [ CharsRef { ) Length . outputs == count ( if { ) posLength int , endOffset int , len int , offset int , output ] [ char ( Add void
} HTTPS.ProtocolType Protocol { } " ", " ", " ", " ", " " : (FetchLibrariesRequest);
return Exists.objects();
} ; System.out.println(new java.io.FilterOutputStream(System.out));
} MethodType.PUT Method; " " UriPattern { ) " " , " " , " " , " " , " " ( : ) ( ScaleClusterRequest
IDataValidationConstraint CreateTimeConstraint(int operatorType, String formula1, String formula2) { return DVConstraint.CreateTimeConstraint(operatorType, formula1, formula2); }
ListObjectParentPathsResponse response = (ListObjectParentPathsResponse) invoke(new InvokeOptions(ListObjectParentPathsRequestMarshaller.getInstance(), ListObjectParentPathsResponseUnmarshaller.getInstance()), request);
return (DescribeCacheSubnetGroupsResponse) invoke(new InvokeOptions() {{ RequestMarshaller = DescribeCacheSubnetGroupsRequestMarshaller.getInstance(); ResponseUnmarshaller = DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance(); }}, request, DescribeCacheSubnetGroupsResponse.class);
void setSharedFormula(boolean flag) { field_5_options.setShortBoolean(sharedFormula); }
boolean isReuseObjects() { return reuseObjects; }
t = new ErrorNodeImpl(badToken); Parent.addChild((IErrorNode) t); return t;
if (args.length > 0) { throw new IllegalArgumentException("System"); }```
return (RemoveSourceIdentifierFromSubscriptionResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(RemoveSourceIdentifierFromSubscriptionRequestMarshaller.getInstance()); setResponseUnmarshaller(RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.getInstance()); }}, request, RemoveSourceIdentifierFromSubscriptionResponse.class);
TokenFilterFactory.forName(name, (ClassLoader) NewInstance.loader, (IDictionary<String, String>) args);
} HTTPS.ProtocolType protocol; AddAlbumPhotosRequest(" ", " ", " ", " ", " ");
GetThreatIntelSetResponse response = (GetThreatIntelSetResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(GetThreatIntelSetRequestMarshaller.getInstance()); setResponseUnmarshaller(GetThreatIntelSetResponseUnmarshaller.getInstance()); }}, request);
return new AndTreeFilter(Clone.a(), Clone.b());
boolean equals(Object o) { return o; }
protected boolean hasArray() { return hasArray; }
UpdateContributorInsightsResponse response = client.invoke(new InvokeOptions(UpdateContributorInsightsRequestMarshaller.getInstance(), UpdateContributorInsightsResponseUnmarshaller.getInstance()), request, UpdateContributorInsightsResponse.class);
} null writeProtect = null; null fileShare = null; Remove.records(); Remove.records(); UnwriteProtectWorkbook(); void
} expand expand { ) analyzer, dedup ( base : ) analyzer Analyzer, expand boolean, dedup boolean ( SolrSynonymParser
return (RequestSpotInstancesResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.RequestSpotInstancesRequestMarshaller.getInstance()); setResponseUnmarshaller(Instance.RequestSpotInstancesResponseUnmarshaller.getInstance()); }}, request, RequestSpotInstancesResponse.class);
return ObjectData.get(FindObjectRecord()).getObjectData()[byte];
return (GetContactAttributesResponse) invoke(new InvokeOptions() {{ requestMarshaller = Instance.GetContactAttributesRequestMarshaller.options; responseUnmarshaller = Instance.GetContactAttributesResponseUnmarshaller.options; }}, request, GetContactAttributesResponse.class);
return (GetKey() + " " + GetValue()).toString();
ListTextTranslationJobsResponse response = (ListTextTranslationJobsResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(ListTextTranslationJobsRequestMarshaller.getInstance()); setResponseUnmarshaller(ListTextTranslationJobsResponseUnmarshaller.getInstance()); }}, request);
GetContactMethodsResponse response = (GetContactMethodsResponse) invoke(new InvokeOptions() {{ requestMarshaller = GetContactMethodsRequestMarshaller.getInstance(); responseUnmarshaller = GetContactMethodsResponseUnmarshaller.getInstance(); }}, request);
if (fd == null) return; short Index = LookupIndexByName(name); if (Index == -1) return; FunctionMetadata fd = GetInstance().GetFunctionByNameInternal();
DescribeAnomalyDetectorsResponse response = DescribeAnomalyDetectorsResponseUnmarshaller.getInstance().unmarshall(DescribeAnomalyDetectorsRequestMarshaller.getInstance().marshall(request), new InvokeOptions());
return InsertId, changeId, message, ObjectId;
long GetObjectSize(AnyObjectId objectId, int typeHint) { long sz = db.GetObjectSize(); if (sz < 0) { if (typeHint == OBJ_ANY) { throw new MissingObjectException(Copy.objectId(), ""); } throw new MissingObjectException(Copy.objectId(), ""); } return sz; }
ImportInstallationMediaResponse response = (ImportInstallationMediaResponse) invoke(new InvokeOptions() {{ requestMarshaller = ImportInstallationMediaRequestMarshaller.getInstance(); responseUnmarshaller = ImportInstallationMediaResponseUnmarshaller.getInstance(); }}, request);
PutLifecycleEventHookExecutionStatusResponse response = (PutLifecycleEventHookExecutionStatusResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.PutLifecycleEventHookExecutionStatusRequestMarshaller.getOptions()); setResponseUnmarshaller(Instance.PutLifecycleEventHookExecutionStatusResponseUnmarshaller.getOptions()); }}, request);
} ; ) ( ReadDouble . in1 field_1_value { ) in1 ILittleEndianInput ( NumberPtg
return (GetFieldLevelEncryptionConfigResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.getInstance()); setResponseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance()); }}, request, GetFieldLevelEncryptionConfigResponse.class);
DescribeDetectorResponse response = (DescribeDetectorResponse) new InvokeOptions() {{ setRequestMarshaller(DescribeDetectorRequestMarshaller.getInstance()); setResponseUnmarshaller(DescribeDetectorResponseUnmarshaller.getInstance()); }}.invoke(describeDetectorRequest);
return (ReportInstanceStatusResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.ReportInstanceStatusRequestMarshaller.options); setResponseUnmarshaller(Instance.ReportInstanceStatusResponseUnmarshaller.options); }}, request, ReportInstanceStatusResponse.class);
return DeleteAlarmResponse.class.cast(new InvokeOptions() {{ setRequestMarshaller(DeleteAlarmRequestMarshaller.getInstance()); setResponseUnmarshaller(DeleteAlarmResponseUnmarshaller.getInstance()); }}).invoke(request, DeleteAlarmResponse.class);
return new PortugueseStemFilter(input);
new byte[ENCODED_SIZE]; // FtCblsSubRecord
synchronized (mutex) { return object.remove(object); }
return (GetDedicatedIpResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(GetDedicatedIpRequestMarshaller.getInstance()); setResponseUnmarshaller(GetDedicatedIpResponseUnmarshaller.getInstance()); }}, request, GetDedicatedIpResponse.class);
} ; " " + precedence return { ) ( toString String
ListStreamProcessorsResponse response = (ListStreamProcessorsResponse) Instance.invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.ListStreamProcessorsRequestMarshaller); setResponseUnmarshaller(Instance.ListStreamProcessorsResponseUnmarshaller); }}, request);
public DeleteLoadBalancerPolicyRequest(String policyName, String loadBalancerName) { this.policyName = policyName; this.loadBalancerName = loadBalancerName; }
WindowProtectRecord options = new WindowProtectRecord(); int options;
UnbufferedCharStream n = new UnbufferedCharStream(bufferSize); int data = 0; int bufferSize;
return GetOperationsResponse.class.cast(new InvokeOptions() {{ setRequestMarshaller(Instance.GetOperationsRequestMarshaller.options); setResponseUnmarshaller(Instance.GetOperationsResponseUnmarshaller.options); }}).invoke(request, GetOperationsResponse.class);
} ; ) , 16 + o , ( EncodeInt32 . NB ; ) , 12 + o , ( EncodeInt32 . NB ; ) , 8 + o , ( EncodeInt32 . NB ; ) , 4 + o , ( EncodeInt32 . NB ; ) , , ( EncodeInt32 . NB { ) o int , b ] [ byte ( CopyRawTo void
new WindowOneRecord(new RecordInputStream(in1).readShort(), new RecordInputStream(in1).readShort(), new RecordInputStream(in1).readShort(), new RecordInputStream(in1).readShort(), new RecordInputStream(in1).readShort(), new RecordInputStream(in1).readShort(), new RecordInputStream(in1).readShort(), new RecordInputStream(in1).readShort(), new RecordInputStream(in1).readShort());
StopWorkspacesResponse response = (StopWorkspacesResponse) invoke(request, new InvokeOptions() {{ RequestMarshaller = StopWorkspacesRequestMarshaller.getInstance(); ResponseUnmarshaller = StopWorkspacesResponseUnmarshaller.getInstance(); }});
} } } } } ; ) ( close . fos { finally } ; ) ( close . channel { try { finally } ; ) ( truncate . channel { try { finally } ; ) ( dump { try ; isOpen { ) isOpen ( if { throws IOException ) ( close void
DescribeMatchmakingRuleSetsResponse response = DescribeMatchmakingRuleSetsResponse.invoke(new InvokeOptions() {{ setRequestMarshaller(DescribeMatchmakingRuleSetsRequestMarshaller.getInstance()); setResponseUnmarshaller(DescribeMatchmakingRuleSetsResponseUnmarshaller.getInstance()); }}, request);
int GetPronunciation(String surface, int wordId, char[] len, int off) { return null; }
return getPath();
double devsq(double[] v) { double r = Double.NaN; if (v != null && v.length >= 1) { double m = 0, s = 0; int n = v.length; for (int i = 0; i < n; i++) { m += v[i]; } m /= n; for (int i = 0; i < n; i++) { s += (v[i] - m) * (v[i] - m); } r = s; } return r; }
DescribeResizeResponse response = (DescribeResizeResponse) Invoke(new InvokeOptions() {{ RequestMarshaller = DescribeResizeRequestMarshaller.getInstance(); ResponseUnmarshaller = DescribeResizeResponseUnmarshaller.getInstance(); }}, describeResizeRequest);
boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
return (int) (end);
void Traverse(ICellHandler handler) { int FirstRow = range.FirstRow; int LastRow = range.LastRow; int FirstColumn = range.FirstColumn; int LastColumn = range.LastColumn; int width = 1 + lastColumn - firstColumn; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); IRow currentRow = null; ICell currentCell = null; for (int rowNumber = firstRow; rowNumber <= lastRow; rowNumber++) { currentRow = sheet.GetRow(rowNumber); if (currentRow == null) { continue; } for (int colNumber = firstColumn; colNumber <= lastColumn; colNumber++) { currentCell = currentRow.GetCell(colNumber); if (currentCell == null) { continue; } if (!IsEmpty(currentCell)) { ctx.ordinalNumber = (rowNumber - firstRow) * width + (colNumber - firstColumn) + 1; handler.OnCell(ctx, currentCell); } } } }
int getReadIndex() { return readIndex; }
} } ; ) Boost . other ( compareTo . Boost . return { else } ; ) Term . ( compareTo . Term . other return { ) Boost . other == Boost . ( if } ; 0 return { ) ) Term . other ( bytesEquals . Term ( if { ) other ScoreTerm ( compareTo int
for (int i = 0; i < len; i++) { switch (s[i]) { case HAMZA_ABOVE: break; case HEH: s[i] = HEH_GOAL; break; case HEH_YEH: break; case KAF: s[i] = KEHEH; break; case YEH: s[i] = YEH_BARREE; break; case FARSI_YEH: s[i] = YEH_BARREE; break; default: break; } --i; } ```
void Serialize(ILittleEndianOutput out1) { out1.writeShort(); }
} exactOnly exactOnly . { ) exactOnly boolean ( DiagnosticErrorListener
KeySchemaElement(KeyType keyType, String attributeName) { this._keyType = keyType; this._attributeName = attributeName; }
GetAssignmentResponse response = (GetAssignmentResponse) Invoke(new InvokeOptions() {{ RequestMarshaller = GetAssignmentRequestMarshaller.Instance; ResponseUnmarshaller = GetAssignmentResponseUnmarshaller.Instance; }}, request);
return FindOffset(id) != -1;
return allGroups; allGroups.setAllGroups(allGroups); boolean allGroups = GroupingSearch();
void SetMultiValued(String dimName, boolean v) { synchronized (fieldTypes) { if (!fieldTypes.containsKey(dimName)) { DimConfig fieldType = new DimConfig(); fieldTypes.put(dimName, fieldType); } else { DimConfig fieldType = fieldTypes.get(dimName); fieldType.IsMultiValued = v; } } }
int size = 0; for (char c : cells.Keys) { Cell e = At(); if (cmd.e() >= 0) { size++; } } return size;
return (DeleteVoiceConnectorResponse) invoke(request, new InvokeOptions() {{ RequestMarshaller = Instance.DeleteVoiceConnectorRequestMarshaller; ResponseUnmarshaller = Instance.DeleteVoiceConnectorResponseUnmarshaller; }});
return DeleteLifecyclePolicyResponse.class.cast(invoke(new InvokeOptions() {{ setRequestMarshaller(DeleteLifecyclePolicyRequestMarshaller.getInstance()); setResponseUnmarshaller(DeleteLifecyclePolicyResponseUnmarshaller.getInstance()); }}, request, DeleteLifecyclePolicyResponse.class));
