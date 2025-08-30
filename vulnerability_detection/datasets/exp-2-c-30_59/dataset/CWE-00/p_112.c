static u32 arcmsr_disable_outbound_ints(struct AdapterControlBlock *acb)
{
	u32 orig_mask = 0;
	switch (acb->adapter_type) {	
	case ACB_ADAPTER_TYPE_A : {
		struct MessageUnit_A __iomem *reg = acb->pmuA;
		orig_mask = readl(&reg->outbound_intmask);
		writel(orig_mask|ARCMSR_MU_OUTBOUND_ALL_INTMASKENABLE, \
						&reg->outbound_intmask);
		}
		break;
	case ACB_ADAPTER_TYPE_B : {
		struct MessageUnit_B *reg = acb->pmuB;
		orig_mask = readl(reg->iop2drv_doorbell_mask);
		writel(0, reg->iop2drv_doorbell_mask);
		}
		break;
	case ACB_ADAPTER_TYPE_C:{
		struct MessageUnit_C __iomem *reg = acb->pmuC;
		/* disable all outbound interrupt */
		orig_mask = readl(&reg->host_int_mask); /* disable outbound message0 int */
		writel(orig_mask|ARCMSR_HBCMU_ALL_INTMASKENABLE, &reg->host_int_mask);
		}
		break;
	case ACB_ADAPTER_TYPE_D: {
		struct MessageUnit_D *reg = acb->pmuD;
		/* disable all outbound interrupt */
		writel(ARCMSR_ARC1214_ALL_INT_DISABLE, reg->pcief0_int_enable);
		}
		break;
	}
	return orig_mask;
}