static int check_stack_read(struct bpf_verifier_env *env,
			    struct bpf_func_state *reg_state /* func where register points to */,
			    int off, int size, int value_regno)
{
	struct bpf_verifier_state *vstate = env->cur_state;
	struct bpf_func_state *state = vstate->frame[vstate->curframe];
	int i, slot = -off - 1, spi = slot / BPF_REG_SIZE;
	u8 *stype;

	if (reg_state->allocated_stack <= slot) {
		verbose(env, "invalid read from stack off %d+0 size %d\n",
			off, size);
		return -EACCES;
	}
	stype = reg_state->stack[spi].slot_type;

	if (stype[0] == STACK_SPILL) {
		if (size != BPF_REG_SIZE) {
			verbose(env, "invalid size of register spill\n");
			return -EACCES;
		}
		for (i = 1; i < BPF_REG_SIZE; i++) {
			if (stype[(slot - i) % BPF_REG_SIZE] != STACK_SPILL) {
				verbose(env, "corrupted spill memory\n");
				return -EACCES;
			}
		}

		if (value_regno >= 0) {
			/* restore register state from stack */
			state->regs[value_regno] = reg_state->stack[spi].spilled_ptr;
			/* mark reg as written since spilled pointer state likely
			 * has its liveness marks cleared by is_state_visited()
			 * which resets stack/reg liveness for state transitions
			 */
			state->regs[value_regno].live |= REG_LIVE_WRITTEN;
		}
		mark_stack_slot_read(env, vstate, vstate->parent, spi,
				     reg_state->frameno);
		return 0;
	} else {
		int zeros = 0;

		for (i = 0; i < size; i++) {
			if (stype[(slot - i) % BPF_REG_SIZE] == STACK_MISC)
				continue;
			if (stype[(slot - i) % BPF_REG_SIZE] == STACK_ZERO) {
				zeros++;
				continue;
			}
			verbose(env, "invalid read from stack off %d+%d size %d\n",
				off, i, size);
			return -EACCES;
		}
		mark_stack_slot_read(env, vstate, vstate->parent, spi,
				     reg_state->frameno);
		if (value_regno >= 0) {
			if (zeros == size) {
				/* any size read into register is zero extended,
				 * so the whole register == const_zero
				 */
				__mark_reg_const_zero(&state->regs[value_regno]);
			} else {
				/* have read misc data from the stack */
				mark_reg_unknown(env, state->regs, value_regno);
			}
			state->regs[value_regno].live |= REG_LIVE_WRITTEN;
		}
		return 0;
	}
}