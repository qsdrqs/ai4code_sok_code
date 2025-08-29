void Serialize(ILittleEndianOutput out1) { out1.writeShort(); }
if (size == 0) return; for (int srcDirIdx = 0; srcDirIdx < tailDirIdx; ++srcDirIdx) { AddAll(directory[srcDirIdx], , ); } if (tailBlkIdx != 0) { AddAll(tailBlock, , ); } }
} ; b ) byte ( ] ++ upto . outerInstance [ currentBlock . outerInstance } ; 0 upto . outerInstance ; ] blockSize . outerInstance [ byte new currentBlock . outerInstance } ; ) upto . outerInstance ( Add . blockEnd . outerInstance ; ) currentBlock . outerInstance ( Add . blocks . outerInstance { ) null != currentBlock . outerInstance ( if { ) blockSize . outerInstance == upto . outerInstance ( if { ) b byte ( writeByte void
return ObjectId.GetObjectId();
DeleteDomainEntryResponse response = (DeleteDomainEntryResponse) Invoke(new InvokeOptions() {{ RequestMarshaller = DeleteDomainEntryRequestMarshaller.Instance; ResponseUnmarshaller = DeleteDomainEntryResponseUnmarshaller.Instance; }}, request);
return (fst == null) ? 0 : fst.getSizeInBytes();
return new String(GetFullMessage(raw), ParseEncoding.RawParseUtils.decode(msgB < 0 ? "" : TagMessage.RawParseUtils.msgB, raw));
POIFSFileSystem fileSystem = new POIFSFileSystem(); HeaderBlock headerBlock = new HeaderBlock(); PropertyTable _property_table = new PropertyTable(); ArrayList _documents = new ArrayList(); Object _root = null;
} ; ) Length . slice < upto ( Assert . Debug ; address offset0 ; BYTE_BLOCK_MASK . ByteBlockPool & address upto ; ) null != slice ( Assert . Debug ; ] BYTE_BLOCK_SHIFT . ByteBlockPool >> address [ Buffers . pool slice { ) address int ( Init void
} return; path.path().setPath(pathString(SubmoduleAddCommand.Api.NGit));
ListIngestionsResponse response = (ListIngestionsResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(ListIngestionsRequestMarshaller.getInstance()); setResponseUnmarshaller(ListIngestionsResponseUnmarshaller.getInstance()); }}, request);
QueryParserTokenManager(ICharStream stream, int lexState) { this.stream = stream; }
GetShardIteratorResponse response = (GetShardIteratorResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(GetShardIteratorRequestMarshaller.getInstance()); setResponseUnmarshaller(GetShardIteratorResponseUnmarshaller.getInstance()); }}, request);
} POST.MethodType method() { return new ModifyStrategyRequest(" ", " ", " ", " ", " "); }```
} } } return; catch (IOException e) { System.out.println("IO Exception"); throw new IOException(""); } try { if (in == null) { synchronized (lock) { boolean ready = (in.available() > 0 || bytes.hasRemaining()); return ready; } } }
return (EscherOptRecord) getOptRecord();
int read(byte[] buffer, int offset, int length) { if (buffer == null) { throw new NullPointerException("buffer"); } java.util.Arrays.checkOffsetAndCount(buffer.length, offset, length); if (length == 0) { return 0; } int copylen = Math.min(length, pos - count); for (int i = 0; i < copylen; i++) { buffer[offset + i] = (byte) (unchecked(buffer[pos + i])); } return copylen; }
} sentenceOp; sentenceOp.NLPSentenceDetectorOp(new OpenNLPSentenceBreakIterator());
} ; ) ) null ) Object ( ( getValueOf . StringHelper . sharpen : str != null ? str ( write { ) str String ( System.out.print void
} ; functionName functionName . { ) cause , functionName ( base : ) cause NotImplementedException , functionName String ( NotImplementedFunctionException
return nextEntry.next().value;
} } } ; 0 bufferLength ; 0 bufferPosition ; after bufferStart ; ) , , ( readInternal } ; ) + " " ( new EndOfStreamException throw { ) length > after ( if ; len + bufferPosition + bufferStart = after long { else } } ; len bufferPosition ; ) , , , , ( System.arraycopy { else } ; ) + " " ( new EndOfStreamException throw ; ) , , , , ( System.arraycopy { ) len < bufferLength ( if ; ) ( refill { ) bufferSize < len && useBuffer ( if } ; available bufferPosition ; available len ; available offset ; ) , , , , ( System.arraycopy { ) 0 > available ( if { else } ; len bufferPosition } ; ) , , , , ( System.arraycopy { ) 0 > len ( if { ) available <= len ( if ; bufferPosition - bufferLength = available int { ) boolean useBuffer , int len , int offset , byte[] b ( void readBytes
InvokeOptions options = new InvokeOptions(); options.setRequestMarshaller(Instance.TagQueueRequestMarshaller); options.setResponseUnmarshaller(Instance.TagQueueResponseUnmarshaller); return (TagQueueResponse) invoke(request, options);
throw new UnsupportedOperationException();
ModifyCacheSubnetGroupResponse response = (ModifyCacheSubnetGroupResponse) Invoke(new InvokeOptions() {{ setRequestMarshaller(ModifyCacheSubnetGroupRequestMarshaller.getInstance()); setResponseUnmarshaller(ModifyCacheSubnetGroupResponseUnmarshaller.getInstance()); }}, request);
} StringTokenizer st = new StringTokenizer(culture, " "); if (st.hasMoreTokens()) { String Current = st.nextToken(); if (st.hasMoreTokens()) { Current += " " + st.nextToken(); if (st.hasMoreTokens()) { Current += " " + st.nextToken(); } } } void SetParams(String... params) { }
DeleteDocumentationVersionResponse response = (DeleteDocumentationVersionResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(DeleteDocumentationVersionRequestMarshaller.getInstance()); setResponseUnmarshaller(DeleteDocumentationVersionResponseUnmarshaller.getInstance()); }}, request);
if (!(obj instanceof FacetLabel)) return false; FacetLabel other = (FacetLabel) obj; if (Length != other.Length) return false; for (int i = Length - 1; i >= 0; i--) if (!Components[i].equals(other.Components[i])) return false; return true;
return (GetInstanceAccessDetailsResponse) Instance.invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.GetInstanceAccessDetailsRequestMarshaller.getInstance()); setResponseUnmarshaller(Instance.GetInstanceAccessDetailsResponseUnmarshaller.getInstance()); }}, request);
HSSFPolygon shape = new HSSFPolygon(); HSSFChildAnchor anchor = (HSSFChildAnchor) shape.CreatePolygon(); shape.setParent(Anchor.shape); shapes.Add(shape); OnCreate(shape);
return GetBoundSheetRec(sheetIndex).getSheetName();
GetDashboardResponse response = (GetDashboardResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.GetDashboardRequestMarshaller); setResponseUnmarshaller(Instance.GetDashboardResponseUnmarshaller); }}, request);
AssociateSigninDelegateGroupsWithAccountResponse response = (AssociateSigninDelegateGroupsWithAccountResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.AssociateSigninDelegateGroupsWithAccountRequestMarshaller); setResponseUnmarshaller(Instance.AssociateSigninDelegateGroupsWithAccountResponseUnmarshaller); }}, request);
void AddMultipleBlanks(MulBlankRecord mbr) { for (int j = 0; j < NumColumns.mbr; j++) { BlankRecord br = new BlankRecord(); InsertCell(GetXFAt.mbr(XFIndex.br, Row.mbr, Row.br, FirstColumn.mbr + j, Column.br)); } }
StringBuilder sb = new StringBuilder(); sb.append("\\"); int apos = 0; int k; while ((k = string.indexOf("\\", apos)) >= 0) { sb.append(StringHelper.Substring(string, apos, k + 2)); sb.append("\\\\"); apos = k + 2; } return sb.append(StringHelper.Substring(string, apos)).toString();
throw new java.nio.ReadOnlyBufferException(); java.nio.ByteBuffer.putInt((int) value);
} 0 _reserved2Byte; 0 _reserved1Short; 0 _reserved0Int; vv _arrayValues; }}; ][ rowData]), (GetValueIndex[vv {) ++c; nColumns < c; c = 0; for (int c = 0; c < nColumns; c++) { values2d[r][c] = rowData[r][c]; } for (int r = 0; r < nRows; r++) { Object[][] vv = new Object[_nRows * _nColumns]; short _nRows = (short) nRows; short _nColumns = (short) nColumns; int nRows = values2d.length; int nColumns = values2d[0].length; (ArrayPtg[][] Object[][]); }
GetIceServerConfigResponse response = (GetIceServerConfigResponse) Invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.GetIceServerConfigRequestMarshaller); setResponseUnmarshaller(Instance.GetIceServerConfigResponseUnmarshaller); }}, request);
StringBuilder sb = new StringBuilder(); sb.append(getClass().getName()).append(" ").append(getValueAsString()).append(" "); return sb.toString();
} + " " + _parentQuery + " " + return (String) field.toString();
void IncRef() { refCount.IncrementAndGet(); }
return (UpdateConfigurationSetSendingEnabledResponse) invoke(request, new InvokeOptions() {{ setRequestMarshaller(UpdateConfigurationSetSendingEnabledRequestMarshaller.getInstance()); setResponseUnmarshaller(UpdateConfigurationSetSendingEnabledResponseUnmarshaller.getInstance()); }});
} LittleEndianConsts.INT_SIZE * (getXBATEntriesPerBlock()) { return (getNextXBATChainOffset()); int
} } ; ) _multiplierShift . tp , _multiplicand . tp ( mulShift { else } ; ) _divisorShift . tp , _divisor . tp ( mulShift { ) 0 < pow10 ( if ; ) ) ( Math . abs ( GetInstance . TenPower = tp TenPower { ) pow10 int ( multiplyByPowerOfTen void
return new StringBuilder().append(java.io.File.separator).append(new StringBuilder().append(java.io.File.separator).append(new StringBuilder().append(java.io.File.separator).toString()).toString()).toString();
void withFetcher(ECSMetadataServiceCredentialsFetcher fetcher) { fetcher.fetcher().SetRoleName(); }
} ProgressMonitor pm { void setProgressMonitor(ProgressMonitor pm) ) ;```
if (!Eof) { if (!First) { ptr = 0; } } void Reset() { ParseEntry(); } }
throw new java.util.NoSuchElementException(); return iterator.previous(); if (iterator.previousIndex() >= start) { E previous; }
} newPrefix.return (GetNewPrefix(String));
int indexOfValue(int value) { for (int i = 0; i < mSize; i++) { if (mValues[i] == value) return i; } return -1; }
List<CharsRef> UniqueStems(char[] word, int length, IList<CharsRef> stems) { if (stems.Count < 2) { return stems; } CharArraySet terms = new CharArraySet(8, true); for (CharsRef s : stems) { if (!terms.contains(s)) { terms.add(s); } } return new ArrayList<>(terms); }
GetGatewayResponsesResponse response = (GetGatewayResponsesResponse) Invoke(new InvokeOptions() {{ RequestMarshaller = GetGatewayResponsesRequestMarshaller.getInstance(); ResponseUnmarshaller = GetGatewayResponsesResponseUnmarshaller.getInstance(); }}, request);
} ; ) blockMask . outerInstance & position ( ) int ( currentBlockUpto ; ] [ blocks . outerInstance currentBlock ; ) blockBits . outerInstance >> position ( ) int ( currentBlockIndex { ) position long ( setPosition void
int s = (int) Math.min(Math.max((int) Available(Skip((long) n)), 0), ptr); return s;
} ; BootstrapActionConfig _bootstrapActionConfig { ) BootstrapActionConfig BootstrapActionConfig ( BootstrapActionDetail
Serialize void ( ILittleEndianOutput out1 ) { WriteShort.out1(); WriteShort.out1(); WriteShort.out1(); WriteShort.out1(); WriteShort.out1(); field_6_author.Length; out1.WriteByte(field_5_hasMultibyte ? 0x01 : 0x00); if (field_5_hasMultibyte) { StringUtil.PutUnicodeLE(out1); } else { StringUtil.PutCompressedUnicode(out1); } if (field_7_padding != null) { out1.WriteByte(Convert.ToInt32(CultureInfo.InvariantCulture)); } }
} ; ) , ( lastIndexOf return { ) @string String ( lastIndexOf int
} ; ) ( addLastImpl return { ) @object E ( add boolean
})); while (!CompareAndSet.state) { do { ConfigSnapshot res; ConfigSnapshot src; UnsetSection(section, subsection); } while (Get.state); UnsetSection(String section, String subsection); void
return getTagName();
void AddSubRecord(int index, SubRecord element) { subrecords.Insert(index, element); }
synchronized (mutex) { return object.remove(object); }
return new DoubleMetaphoneFilter(input, CreateTokenStream());
return (long) Length(InCoreLength);
void setValue(boolean newValue) { this.newValue = newValue; }
new Pair<ContentSource, ContentSource>(newSource, oldSource);
throw new IndexOutOfBoundsException(); for (int i = 0; i <= count; i++) { return entries[i]; }
} PUT.MethodType.Method; " " UriPattern { ) " ", " ", " ", " ", " " (: ) (CreateRepoRequest
boolean IsDeltaBaseAsOffset() { return deltaBaseAsOffset; }
if (modCount == expectedModCount) { if (lastLink != null) { LinkedList.Link<ET> next = lastLink.next; LinkedList.Link<ET> previous = lastLink.previous; if (previous != null) { previous.next = next; } if (next != null) { next.previous = previous; } lastLink = null; ++expectedModCount; --list._size; ++list.modCount; } else { throw new java.util.ConcurrentModificationException(); } } else { throw new System.InvalidOperationException(); }
MergeShardsResponse response = (MergeShardsResponse) Invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.MergeShardsRequestMarshaller); setResponseUnmarshaller(Instance.MergeShardsResponseUnmarshaller); }}, request);
return (AllocateHostedConnectionResponse) Invoke(new InvokeOptions() {{ RequestMarshaller = AllocateHostedConnectionRequestMarshaller.Instance; ResponseUnmarshaller = AllocateHostedConnectionResponseUnmarshaller.Instance; }}, request, AllocateHostedConnectionResponse.class);
int getBeginIndex() { return start; }
return GetTerms(Query query)[WeightedTerm];
throw new java.nio.ReadOnlyBufferException(); java.nio.ByteBuffer.compact();
void Decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) { for (int i = 0; i < iterations; i++) { int byte0 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (int) (((byte0 >> 2) & 3) | ((byte0 << 4) & 0x3F)); int byte1 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (int) (((byte1 >> 4) & 15) | ((byte1 << 2) & 0x3F)); int byte2 = blocks[blocksOffset++] & 0xFF; values[valuesOffset++] = (int) (((byte2 >> 6) & 63) | (byte2 & 0x3F)); } }
if (GetPath == null || GetPath.equals("") || GetPath.equals(Constants.DOT_GIT)) throw new IllegalArgumentException(); String[] elements = s.split(FilePath.separatorChar + " "); if (elements.length == 0) throw new IllegalArgumentException(); if (elements[elements.length - 1].equals(Constants.DOT_GIT_EXT)) { if (elements[elements.length - 2].endsWith(Constants.DOT_GIT_EXT)) return elements[elements.length - 2].substring(0, elements[elements.length - 2].length() - Constants.DOT_GIT_EXT.length()); else return elements[elements.length - 1].substring(0, elements[elements.length - 1].length() - Constants.DOT_GIT_EXT.length()); } else return elements[elements.length - 1];
return (DescribeNotebookInstanceLifecycleConfigResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(DescribeNotebookInstanceLifecycleConfigRequestMarshaller.getInstance()); setResponseUnmarshaller(DescribeNotebookInstanceLifecycleConfigResponseUnmarshaller.getInstance()); }}, describeNotebookInstanceLifecycleConfigRequest, DescribeNotebookInstanceLifecycleConfigResponse.class);
} return AccessSecret { String getAccessKeySecret()
CreateVpnConnectionResponse response = (CreateVpnConnectionResponse) invoke(request, new InvokeOptions() {{ setRequestMarshaller(CreateVpnConnectionRequestMarshaller.getInstance()); setResponseUnmarshaller(CreateVpnConnectionResponseUnmarshaller.getInstance()); }});
DescribeVoicesResponse response = (DescribeVoicesResponse) Invoke(new InvokeOptions() {{ RequestMarshaller = DescribeVoicesRequestMarshaller.Instance; ResponseUnmarshaller = DescribeVoicesResponseUnmarshaller.Instance; }}, request);
ListMonitoringExecutionsResponse response = (ListMonitoringExecutionsResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(ListMonitoringExecutionsRequestMarshaller.getInstance()); setResponseUnmarshaller(ListMonitoringExecutionsResponseUnmarshaller.getInstance()); }}, request);
DescribeJobRequest(jobId String, vaultName String) { this.jobId = jobId; this.vaultName = vaultName; }
return escherRecords[index]; // EscherRecord GetEscherRecord(int index)
GetApisResponse response = (GetApisResponse) Invoke(new InvokeOptions() {{ RequestMarshaller = Instance.GetApisRequestMarshaller.options; ResponseUnmarshaller = Instance.GetApisResponseUnmarshaller.options; }}, request);
DeleteSmsChannelResponse response = (DeleteSmsChannelResponse) Invoke(new InvokeOptions() {{ RequestMarshaller = DeleteSmsChannelRequestMarshaller.Instance; ResponseUnmarshaller = DeleteSmsChannelResponseUnmarshaller.Instance; }}, request);
} return TrackingRefUpdate.getTrackingRefUpdate(); ```
} ; ) ) ( toString . b ( System.out.print { ) b boolean ( System.out.print void
return (IQueryNode) GetChild().GetChildren();
} workdirTreeIndex.index(NotIgnoredFilter::intValue);
} ; ) ( readShort . in1 field_1_formatFlags { ) in1 RecordInputStream ( AreaRecord
} HTTPS.ProtocolType Protocol { } " ", " ", " ", " ", " " ( ) ( GetThumbnailRequest;
DescribeTransitGatewayVpcAttachmentsResponse response = (DescribeTransitGatewayVpcAttachmentsResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(DescribeTransitGatewayVpcAttachmentsRequestMarshaller.getInstance()); setResponseUnmarshaller(DescribeTransitGatewayVpcAttachmentsResponseUnmarshaller.getInstance()); }}, request);
PutVoiceConnectorStreamingConfigurationResponse response = (PutVoiceConnectorStreamingConfigurationResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(PutVoiceConnectorStreamingConfigurationRequestMarshaller.getInstance()); setResponseUnmarshaller(PutVoiceConnectorStreamingConfigurationResponseUnmarshaller.getInstance()); }}, request);
OrdRange GetOrdRange(String dim) { return prefixToOrdRange.TryGetValue(dim, out result) ? result : null; }
} ; ) , Name . ) LexerNoViableAltException . Runtime . Antlr4 ( typeof , " " , CurrentCulture . CultureInfo ( Format . string return } ; ) , ( EscapeWhitespace . Utils symbol ; ) ) , ( Of . Interval ( GetText . ) InputStream ) ICharStream ( ( symbol { ) Size . ) InputStream ) ICharStream ( ( < startIndex && 0 >= startIndex ( if ; Empty . string = symbol string { ) ( ToString string
return peekFirstImpl(); } } while (peek() != null);
return CreateWorkspacesResponse.class.cast(new InvokeOptions() {{ setRequestMarshaller(Instance.CreateWorkspacesRequestMarshaller); setResponseUnmarshaller(Instance.CreateWorkspacesResponseUnmarshaller); }}).invoke(request, CreateWorkspacesResponse.class);
NumberFormatIndexRecord rec = new NumberFormatIndexRecord(); rec.field_1_formatIndex = rec.field_1_formatIndex; Object Clone = (Object) rec;
DescribeRepositoriesResponse response = (DescribeRepositoriesResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.DescribeRepositoriesRequestMarshaller.options); setResponseUnmarshaller(Instance.DescribeRepositoriesResponseUnmarshaller.options); }}, request);
SparseIntArray sparseIntArray = new SparseIntArray(initialCapacity) { int[] mKeys = new int[initialCapacity]; int[] mValues = new int[initialCapacity]; int mSize = 0; };
return new HyphenatedWordsFilter(input);
CreateDistributionWithTagsResponse response = (CreateDistributionWithTagsResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.CreateDistributionWithTagsRequestMarshaller); setResponseUnmarshaller(Instance.CreateDistributionWithTagsResponseUnmarshaller); }}, request);
new RandomAccessFile(fileName, mode); throw new UnsupportedOperationException();
return DeleteWorkspaceImageResponse.class.cast(new InvokeOptions() {{ setRequestMarshaller(DeleteWorkspaceImageRequestMarshaller.getInstance()); setResponseUnmarshaller(DeleteWorkspaceImageResponseUnmarshaller.getInstance()); }}).invoke(request, DeleteWorkspaceImageResponse.class);
return Long.toHexString((int) value);
UpdateDistributionResponse response = (UpdateDistributionResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(UpdateDistributionRequestMarshaller.getInstance()); setResponseUnmarshaller(UpdateDistributionResponseUnmarshaller.getInstance()); }}, request);
if (index == HSSFColor.HSSFColorPredefined.AUTOMATIC.getIndex()) return HSSFColor.HSSFColorPredefined.AUTOMATIC.getInstance(); else { byte[] b = palette.GetColor(index); if (b != null) return new CustomColor(b); } return null;
throw new NotImplementedFunctionException(); ValueEval Evaluate(ValueEval[] operands, int srcRow, int srcCol);
} ; ) field_2_sheet_table_index ) short ( ( WriteShort . out1 ; ) field_1_number_crn_records ) short ( ( WriteShort . out1 { ) out1 ILittleEndianOutput ( Serialize void
return new DescribeDBEngineVersionsRequest(); DescribeDBEngineVersionsResponse DescribeDBEngineVersions();
FormatRun(short fontIndex, short character) { this.fontIndex = fontIndex; this.character = character; }
byte[] toBigEndianUtf16Bytes(char[] chars, int offset, int length) { byte[] result = new byte[length * 2]; int resultIndex = 0; for (int i = offset; i < offset + length; i++) { char ch = chars[i]; result[resultIndex++] = (byte) (ch >> 8); result[resultIndex++] = (byte) (ch); } return result; }
UploadArchiveResponse response = (UploadArchiveResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.UploadArchiveRequestMarshaller.options); setResponseUnmarshaller(Instance.UploadArchiveResponseUnmarshaller.options); }}, request);
return GetHiddenTokensToLeft(tokenIndex);
if (obj == null) return false; if (!(obj instanceof AutomatonQuery)) return false; AutomatonQuery other = (AutomatonQuery) obj; if (!m_compiled.equals(other.m_compiled)) return false; if (m_term == null) { if (other.m_term != null) return false; } else { if (!m_term.equals(other.m_term)) return false; } return true;
List<SpanQuery> spanQueries = new ArrayList<>(); for (var weightBySpanQuery : wsq.entrySet()) { SpanQuery spanQuery = MakeSpanClause(weightBySpanQuery.getKey().getBoost(), weightBySpanQuery.getValue()); spanQueries.add(spanQuery); } if (spanQueries.size() == 1) return spanQueries.get(0); return new SpanOrQuery(spanQueries.toArray(new SpanQuery[0]));
return new StashCreateCommand(StashCreate());
FieldInfo FieldInfo = FieldInfo.byName.TryGetValue(fieldName, out ret) ? ret : null;
DescribeEventSourceResponse response = (DescribeEventSourceResponse) Invoke(new InvokeOptions() {{ RequestMarshaller = Instance.DescribeEventSourceRequestMarshaller.options; ResponseUnmarshaller = Instance.DescribeEventSourceResponseUnmarshaller.options; }}, request);
GetDocumentAnalysisResponse response = (GetDocumentAnalysisResponse) instance.invoke(new InvokeOptions() {{ setRequestMarshaller(instance.getGetDocumentAnalysisRequestMarshaller()); setResponseUnmarshaller(instance.getGetDocumentAnalysisResponseUnmarshaller()); }}, request);
return (CancelUpdateStackResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(CancelUpdateStackRequestMarshaller.getInstance()); setResponseUnmarshaller(CancelUpdateStackResponseUnmarshaller.getInstance()); }}, cancelUpdateStackRequest, CancelUpdateStackResponse.class);
return ModifyLoadBalancerAttributesResponse.class.cast(Invoke(new InvokeOptions() {{ RequestMarshaller = ModifyLoadBalancerAttributesRequestMarshaller.getInstance(); ResponseUnmarshaller = ModifyLoadBalancerAttributesResponseUnmarshaller.getInstance(); }}, request));
SetInstanceProtectionResponse response = (SetInstanceProtectionResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.SetInstanceProtectionRequestMarshaller.getInstance()); setResponseUnmarshaller(Instance.SetInstanceProtectionResponseUnmarshaller.getInstance()); }}, request);
ModifyDBProxyResponse response = (ModifyDBProxyResponse) Invoke(new InvokeOptions() {{ RequestMarshaller = ModifyDBProxyRequestMarshaller.Instance; ResponseUnmarshaller = ModifyDBProxyResponseUnmarshaller.Instance; }}, request);
} ++count; posLength][posLengths; endOffset][endOffsets; ), , (CopyChars.][outputs};) (new CharsRef][outputs {) null ==][outputs (if}; next posLengths; ), , , , (Copy.Array;]) NUM_BYTES_INT32.RamUsageEstimator, count + 1 (Oversize.ArrayUtil [int newNext = next][int {) Length.posLengths == count (if}; next endOffsets; ), , , , (Copy.Array;]) NUM_BYTES_INT32.RamUsageEstimator, count + 1 (Oversize.ArrayUtil [int newNext = next][int {) Length.endOffsets == count (if}; next outputs; ), , , , (Copy.Array;]) NUM_BYTES_OBJECT_REF.RamUsageEstimator, count + 1 (Oversize.ArrayUtil [CharsRef newNext = next][CharsRef {) Length.outputs == count (if {) int posLength, int endOffset, int len, int offset, char[] output (void Add
} HTTPS.ProtocolType Protocol { } " ", " ", " ", " ", " " : (FetchLibrariesRequest);
return Exists(objects);
} ; @out @out . { ) . . java ( FilterOutputStream
} PUT . MethodType Method ; " " UriPattern { ) " " , " " , " " , " " , " " ( : ) ( ScaleClusterRequest
return new CreateTimeConstraint((int operatorType, String formula1, String formula2) -> IDataValidationConstraint.createTimeConstraint(operatorType, formula1, formula2));
ListObjectParentPathsResponse response = (ListObjectParentPathsResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.ListObjectParentPathsRequestMarshaller); setResponseUnmarshaller(Instance.ListObjectParentPathsResponseUnmarshaller); }}, request);
DescribeCacheSubnetGroupsResponse response = (DescribeCacheSubnetGroupsResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(DescribeCacheSubnetGroupsRequestMarshaller.getInstance()); setResponseUnmarshaller(DescribeCacheSubnetGroupsResponseUnmarshaller.getInstance()); }}, request);
} ; ) , ( SetShortBoolean . sharedFormula field_5_options { ) flag boolean ( setSharedFormula void
} return isReuseObjects; boolean isReuseObjects() {
t = new ErrorNodeImpl(badToken); Parent.addChild(t); return t;
if (args.length > 0) throw new IllegalArgumentException(); LatvianStemFilterFactory(Map<String, String> args) { super(args); }
RemoveSourceIdentifierFromSubscriptionResponse response = (RemoveSourceIdentifierFromSubscriptionResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(RemoveSourceIdentifierFromSubscriptionRequestMarshaller.getInstance()); setResponseUnmarshaller(RemoveSourceIdentifierFromSubscriptionResponseUnmarshaller.getInstance()); }}, request);
} ; ) , ( NewInstance . loader return { ) args > String , String < Map , name String ( forName TokenFilterFactory
} HTTPS.ProtocolType Protocol { } " ", " ", " ", " ", " " : (AddAlbumPhotosRequest);
GetThreatIntelSetResponse response = (GetThreatIntelSetResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(GetThreatIntelSetRequestMarshaller.getInstance()); setResponseUnmarshaller(GetThreatIntelSetResponseUnmarshaller.getInstance()); }}, request);
return new Binary.AndTreeFilter(Clone.a, Clone.b);
} ; return o.equals(o) ? true : false; ```
protected boolean hasArray() { return hasArray; }
UpdateContributorInsightsResponse response = (UpdateContributorInsightsResponse) invoke(request, new InvokeOptions(UpdateContributorInsightsRequestMarshaller.getInstance(), UpdateContributorInsightsResponseUnmarshaller.getInstance()));
} ; null writeProtect ; null fileShare ; ) ( Remove . records ; ) ( Remove . records { ) ( UnwriteProtectWorkbook void
} expand expand . { ) analyzer , dedup ( base : ) analyzer Analyzer , expand boolean , dedup boolean ( SolrSynonymParser
RequestSpotInstancesResponse response = (RequestSpotInstancesResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.RequestSpotInstancesRequestMarshaller.getInstance()); setResponseUnmarshaller(Instance.RequestSpotInstancesResponseUnmarshaller.getInstance()); }}, request);
return ObjectData.FindObjectRecord().GetObjectData()[byte];
return (GetContactAttributesResponse) Invoke(new InvokeOptions() {{ RequestMarshaller = Instance.GetContactAttributesRequestMarshaller; ResponseUnmarshaller = Instance.GetContactAttributesResponseUnmarshaller; }}, request, GetContactAttributesResponse.class);
return getKey() + " " + getValue().toString();
ListTextTranslationJobsResponse response = (ListTextTranslationJobsResponse) invoke(new InvokeOptions() {{ requestMarshaller = ListTextTranslationJobsRequestMarshaller.getInstance(); responseUnmarshaller = ListTextTranslationJobsResponseUnmarshaller.getInstance(); }}, request);
GetContactMethodsResponse response = (GetContactMethodsResponse) Invoke(new InvokeOptions() {{ RequestMarshaller = GetContactMethodsRequestMarshaller.Instance; ResponseUnmarshaller = GetContactMethodsResponseUnmarshaller.Instance; }}, request);
if (fd == null) return; FunctionMetadata fd = GetInstance().GetFunctionByNameInternal(); return (short) (1 - LookupIndexByName(name));
DescribeAnomalyDetectorsResponse response = (DescribeAnomalyDetectorsResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.DescribeAnomalyDetectorsRequestMarshaller.options); setResponseUnmarshaller(Instance.DescribeAnomalyDetectorsResponseUnmarshaller.options); }}, request);
return new ObjectId(changeId, message, InsertId);
} ; sz return } ; ) , ) ( Copy.objectId(new MissingObjectException()); } ; ) " " , ) ( Copy.objectId(new MissingObjectException()) { if (typeHint == OBJ_ANY) { if (sz > 0); } long sz = GetObjectSize.db; { long GetObjectSize(int typeHint, AnyObjectId objectId);
ImportInstallationMediaResponse response = (ImportInstallationMediaResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.ImportInstallationMediaRequestMarshaller); setResponseUnmarshaller(Instance.ImportInstallationMediaResponseUnmarshaller); }}, request);
PutLifecycleEventHookExecutionStatusResponse response = (PutLifecycleEventHookExecutionStatusResponse) instance.invoke(new InvokeOptions().withRequestMarshaller(instance.getPutLifecycleEventHookExecutionStatusRequestMarshaller()).withResponseUnmarshaller(instance.getPutLifecycleEventHookExecutionStatusResponseUnmarshaller()), request);
} ; ) ( ReadDouble . in1 field_1_value { ) in1 ILittleEndianInput ( NumberPtg
return (GetFieldLevelEncryptionConfigResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(GetFieldLevelEncryptionConfigRequestMarshaller.getInstance()); setResponseUnmarshaller(GetFieldLevelEncryptionConfigResponseUnmarshaller.getInstance()); }}, request, GetFieldLevelEncryptionConfigResponse.class);
DescribeDetectorResponse response = (DescribeDetectorResponse) Invoke(new InvokeOptions() {{ RequestMarshaller = Instance.DescribeDetectorRequestMarshaller.options; ResponseUnmarshaller = Instance.DescribeDetectorResponseUnmarshaller.options; }}, request);
return (ReportInstanceStatusResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.ReportInstanceStatusRequestMarshaller.getInstance()); setResponseUnmarshaller(Instance.ReportInstanceStatusResponseUnmarshaller.getInstance()); }}, request, ReportInstanceStatusResponse.class);
DeleteAlarmResponse response = (DeleteAlarmResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.DeleteAlarmRequestMarshaller.getInstance()); setResponseUnmarshaller(Instance.DeleteAlarmResponseUnmarshaller.getInstance()); }}, request);
return new PortugueseStemFilter(input);
} ; ] ENCODED_SIZE [ byte new reserved { ) ( FtCblsSubRecord
return (remove) ? (synchronized (mutex) { return object.remove(object); }) : false;
return (GetDedicatedIpResponse) instance.invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.GetDedicatedIpRequestMarshaller.options); setResponseUnmarshaller(Instance.GetDedicatedIpResponseUnmarshaller.options); }}, request);
} ; " " + precedence return { ) ( toString String
ListStreamProcessorsResponse response = (ListStreamProcessorsResponse) Instance.invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.ListStreamProcessorsRequestMarshaller); setResponseUnmarshaller(Instance.ListStreamProcessorsResponseUnmarshaller); }}, request);
DeleteLoadBalancerPolicyRequest(String policyName, String loadBalancerName) { String _policyName = policyName; String _loadBalancerName = loadBalancerName; }
} options _options; { ) options int ( WindowProtectRecord
UnbufferedCharStream n = new UnbufferedCharStream(bufferSize = 0); int data; int bufferSize;
return (GetOperationsResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.GetOperationsRequestMarshaller); setResponseUnmarshaller(Instance.GetOperationsResponseUnmarshaller); }}, request);
} ; ) , 16 + o , ( EncodeInt32 . NB ; ) , 12 + o , ( EncodeInt32 . NB ; ) , 8 + o , ( EncodeInt32 . NB ; ) , 4 + o , ( EncodeInt32 . NB ; ) , , ( EncodeInt32 . NB { ) o int , b ] [ byte ( CopyRawTo void
new WindowOneRecord(new RecordInputStream(in1).readShort(), new RecordInputStream(in1).readShort(), new RecordInputStream(in1).readShort(), new RecordInputStream(in1).readShort(), new RecordInputStream(in1).readShort(), new RecordInputStream(in1).readShort(), new RecordInputStream(in1).readShort(), new RecordInputStream(in1).readShort(), new RecordInputStream(in1).readShort());
StopWorkspacesResponse response = (StopWorkspacesResponse) invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.StopWorkspacesRequestMarshaller); setResponseUnmarshaller(Instance.StopWorkspacesResponseUnmarshaller); }}, request);
} } } } } ; ) ( close . fos { finally } ; ) ( close . channel { try { finally } ; ) ( truncate . channel { try { finally } ; ) ( dump { try ; isOpen { ) isOpen ( if { throws IOException ) ( close void
return (DescribeMatchmakingRuleSetsResponse) Invoke(new InvokeOptions() {{ setRequestMarshaller(DescribeMatchmakingRuleSetsRequestMarshaller.getInstance()); setResponseUnmarshaller(DescribeMatchmakingRuleSetsResponseUnmarshaller.getInstance()); }}, request, DescribeMatchmakingRuleSetsResponse.class);
int GetPronunciation(String surface, int wordId, char[] charArray, int off, int len) { return null; }
return getPath(); String pathStr;
double r = Double.NaN; if (v != null && v.length >= 1) { double m = 0, s = 0; int n = v.length; for (int i = 0; i < n; ++i) { s += v[i]; } m = s / n; s = 0; for (int i = 0; i < n; ++i) { s += (v[i] - m) * (v[i] - m); } r = (n == 1) ? 0 : s; }
DescribeResizeResponse response = (DescribeResizeResponse) new InvokeOptions() {{ setRequestMarshaller(Instance.DescribeResizeRequestMarshaller); setResponseUnmarshaller(Instance.DescribeResizeResponseUnmarshaller); }}.invoke(describeResizeRequest);
boolean hasPassedThroughNonGreedyDecision() { return passedThroughNonGreedyDecision; }
return (int) end;
void Traverse(ICellHandler handler) { int FirstRow = range.FirstRow; int LastRow = range.LastRow; int FirstColumn = range.FirstColumn; int LastColumn = range.LastColumn; int width = 1 + LastColumn - FirstColumn; SimpleCellWalkContext ctx = new SimpleCellWalkContext(); IRow currentRow = null; ICell currentCell = null; for (int rowNumber = FirstRow; rowNumber <= LastRow; rowNumber++) { currentRow = sheet.GetRow(rowNumber); if (currentRow == null) { continue; } for (int colNumber = FirstColumn; colNumber <= LastColumn; colNumber++) { currentCell = currentRow.GetCell(colNumber); if (currentCell == null) { continue; } if (!IsEmpty(currentCell) && traverseEmptyCells) { continue; } ctx.ordinalNumber = (rowNumber - FirstRow) * width + (colNumber - FirstColumn) + 1; handler.OnCell(ctx, currentCell); } } }
return getReadIndex();
} } ; ) Boost . other ( compareTo . Boost . return { else } ; ) Term . ( compareTo . Term . other return { ) Boost . other == Boost . ( if } ; 0 return { ) ) Term . other ( bytesEquals . Term ( if { ) other ScoreTerm ( compareTo int
for (int i = 0; i < len; i++) { switch (s[i]) { case HAMZA_ABOVE: break; case HEH: case HEH_GOAL: case HEH_YEH: break; case KAF: s[i] = KEHEH; break; case YEH: case YEH_BARREE: case FARSI_YEH: s[i] = YEH; break; default: break; } } return len;
} ; ) ( writeShort . out1 { ) out1 ILittleEndianOutput ( serialize void
} exactOnly.exactOnly().exactOnly(bool DiagnosticErrorListener);
} KeyType _keyType; String _attributeName { KeyType keyType; String attributeName (KeySchemaElement
GetAssignmentResponse response = (GetAssignmentResponse) Invoke(new InvokeOptions() {{ RequestMarshaller = Instance.GetAssignmentRequestMarshaller; ResponseUnmarshaller = Instance.GetAssignmentResponseUnmarshaller; }}, request);
boolean HasObject(AnyObjectId id) { return FindOffset(id) != -1; }
} return; allGroups allGroups. { ) allGroups boolean ( setAllGroups groupingSearch
} } } ; v IsMultiValued . fieldType { else } ; } v IsMultiValued { DimConfig new ] [ fieldTypes { ) ) fieldType DimConfig out , ( TryGetValue . fieldTypes ! ( if { ) ( synchronized { ) v boolean , dimName String ( SetMultiValued void
int size = 0; for (char c : cells.keySet()) { Cell e = At(cmd.e); if (size >= 0) { size++; } } return size;
return Invoke(new DeleteVoiceConnectorRequest(), new InvokeOptions() {{ RequestMarshaller = Instance.DeleteVoiceConnectorRequestMarshaller; ResponseUnmarshaller = Instance.DeleteVoiceConnectorResponseUnmarshaller; }}, DeleteVoiceConnectorResponse.class);
DeleteLifecyclePolicyResponse response = (DeleteLifecyclePolicyResponse) Instance.invoke(new InvokeOptions() {{ setRequestMarshaller(Instance.DeleteLifecyclePolicyRequestMarshaller); setResponseUnmarshaller(Instance.DeleteLifecyclePolicyResponseUnmarshaller); }}, request);
