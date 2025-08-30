CodingReturnValue VP8ComponentDecoder::decode_chunk(UncompressedComponents * const colldata)
{
    mux_splicer.init(spin_workers_);
    /* cmpc is a global variable with the component count */


    /* construct 4x4 VP8 blocks to hold 8x8 JPEG blocks */
    if ( thread_state_[0] == nullptr || thread_state_[0]->context_[0].isNil() ) {
        /* first call */
        BlockBasedImagePerChannel<false> framebuffer;
        framebuffer.memset(0);
        for (size_t i = 0; i < framebuffer.size() && int( i ) < colldata->get_num_components(); ++i) {
            framebuffer[i] = &colldata->full_component_write((BlockType)i);
        }
        Sirikata::Array1d<BlockBasedImagePerChannel<false>, MAX_NUM_THREADS> all_framebuffers;
        for (size_t i = 0; i < all_framebuffers.size(); ++i) {
            all_framebuffers[i] = framebuffer;
        }
        size_t num_threads_needed = initialize_decoder_state(colldata,
                                                             all_framebuffers).size();


        for (size_t i = 0;i < num_threads_needed; ++i) {
            map_logical_thread_to_physical_thread(i, i);
        }
        for (size_t i = 0;i < num_threads_needed; ++i) {
            initialize_thread_id(i, i, framebuffer);
            if (!do_threading_) {
                break;
            }
        }
        if (num_threads_needed > NUM_THREADS || num_threads_needed == 0) {
            return CODING_ERROR;
        }
    }
    if (do_threading_) {
        for (unsigned int thread_id = 0; thread_id < NUM_THREADS; ++thread_id) {
            unsigned int cur_spin_worker = thread_id;
            spin_workers_[cur_spin_worker].work
                = std::bind(worker_thread,
                            thread_state_[thread_id],
                            thread_id,
                            colldata,
                            mux_splicer.thread_target,
                            getWorker(cur_spin_worker),
                            &send_to_actual_thread_state);
            spin_workers_[cur_spin_worker].activate_work();
        }
        flush();
        for (unsigned int thread_id = 0; thread_id < NUM_THREADS; ++thread_id) {
            unsigned int cur_spin_worker = thread_id;
            TimingHarness::timing[thread_id][TimingHarness::TS_THREAD_WAIT_STARTED] = TimingHarness::get_time_us();
            spin_workers_[cur_spin_worker].main_wait_for_done();
            TimingHarness::timing[thread_id][TimingHarness::TS_THREAD_WAIT_FINISHED] = TimingHarness::get_time_us();
        }
        // join on all threads
    } else {
        if (virtual_thread_id_ != -1) {
            TimingHarness::timing[0][TimingHarness::TS_ARITH_STARTED] = TimingHarness::get_time_us();
            CodingReturnValue ret = thread_state_[0]->vp8_decode_thread(0, colldata);
            if (ret == CODING_PARTIAL) {
                return ret;
            }
            TimingHarness::timing[0][TimingHarness::TS_ARITH_FINISHED] = TimingHarness::get_time_us();
        }
        // wait for "threads"
        virtual_thread_id_ += 1; // first time's a charm
        for (unsigned int thread_id = virtual_thread_id_; thread_id < NUM_THREADS; ++thread_id, ++virtual_thread_id_) {
            BlockBasedImagePerChannel<false> framebuffer;
            framebuffer.memset(0);
            for (size_t i = 0; i < framebuffer.size() && int( i ) < colldata->get_num_components(); ++i) {
                framebuffer[i] = &colldata->full_component_write((BlockType)i);
            }

            initialize_thread_id(thread_id, 0, framebuffer);
            thread_state_[0]->bool_decoder_.init(new VirtualThreadPacketReader(thread_id, &mux_reader_, &mux_splicer));
            TimingHarness::timing[thread_id][TimingHarness::TS_ARITH_STARTED] = TimingHarness::get_time_us();
            CodingReturnValue ret;
            if ((ret = thread_state_[0]->vp8_decode_thread(0, colldata)) == CODING_PARTIAL) {
                return ret;
            }
            TimingHarness::timing[thread_id][TimingHarness::TS_ARITH_FINISHED] = TimingHarness::get_time_us();
        }
    }
    TimingHarness::timing[0][TimingHarness::TS_JPEG_RECODE_STARTED] = TimingHarness::get_time_us();
    for (int component = 0; component < colldata->get_num_components(); ++component) {
        colldata->worker_mark_cmp_finished((BlockType)component);
    }
    colldata->worker_update_coefficient_position_progress( 64 );
    colldata->worker_update_bit_progress( 16 );
    write_byte_bill(Billing::DELIMITERS, true, mux_reader_.getOverhead());
    return CODING_DONE;
}