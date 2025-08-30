ofputil_decode_ofpst10_flow_request(struct ofputil_flow_stats_request *fsr,
                                    const struct ofp10_flow_stats_request *buffer1,
                                    bool aggregate)
{
    fsr->aggregate = aggregate;
    ofputil_match_from_ofp10_match(&buffer1->match, &fsr->match);
    fsr->out_port = u16_to_ofp(ntohs(buffer1->out_port));
    fsr->out_group = OFPG_ANY;
    fsr->table_id = buffer1->table_id;
    fsr->cookie = fsr->cookie_mask = htonll(0);

    return 0;
}
