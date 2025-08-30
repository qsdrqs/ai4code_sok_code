static int pxa3xx_gcu_probe(struct platform_device *pdev)
{
	int i, ret, irq;
	struct resource *r;
	struct pxa3xx_gcu_priv *priv;
	struct device *dev = &pdev->dev;

	priv = devm_kzalloc(dev, sizeof(struct pxa3xx_gcu_priv), GFP_KERNEL);
	if (!priv)
		return -ENOMEM;

	init_waitqueue_head(&priv->wait_idle);
	init_waitqueue_head(&priv->wait_free);
	spin_lock_init(&priv->spinlock);

	/* we allocate the misc device structure as part of our own allocation,
	 * so we can get a pointer to our priv structure later on with
	 * container_of(). This isn't really necessary as we have a fixed minor
	 * number anyway, but this is to avoid statics. */

	priv->misc_dev.minor	= PXA3XX_GCU_MINOR,
	priv->misc_dev.name	= DRV_NAME,
	priv->misc_dev.fops	= &pxa3xx_gcu_miscdev_fops;

	/* handle IO resources */
	r = platform_get_resource(pdev, IORESOURCE_MEM, 0);
	priv->mmio_base = devm_ioremap_resource(dev, r);
	if (IS_ERR(priv->mmio_base))
		return PTR_ERR(priv->mmio_base);

	/* enable the clock */
	priv->clk = devm_clk_get(dev, NULL);
	if (IS_ERR(priv->clk))
		return dev_err_probe(dev, PTR_ERR(priv->clk), "failed to get clock\n");

	/* request the IRQ */
	irq = platform_get_irq(pdev, 0);
	if (irq < 0)
		return irq;

	ret = devm_request_irq(dev, irq, pxa3xx_gcu_handle_irq,
			       0, DRV_NAME, priv);
	if (ret < 0) {
		dev_err(dev, "request_irq failed\n");
		return ret;
	}

	/* allocate dma memory */
	priv->shared = dma_alloc_coherent(dev, SHARED_SIZE,
					  &priv->shared_phys, GFP_KERNEL);
	if (!priv->shared) {
		dev_err(dev, "failed to allocate DMA memory\n");
		return -ENOMEM;
	}

	/* register misc device */
	ret = misc_register(&priv->misc_dev);
	if (ret < 0) {
		dev_err(dev, "misc_register() for minor %d failed\n",
			PXA3XX_GCU_MINOR);
		goto err_free_dma;
	}

	ret = clk_prepare_enable(priv->clk);
	if (ret < 0) {
		dev_err(dev, "failed to enable clock\n");
		goto err_misc_deregister;
	}

	for (i = 0; i < 8; i++) {
		ret = pxa3xx_gcu_add_buffer(dev, priv);
		if (ret) {
			pxa3xx_gcu_free_buffers(dev, priv);
			dev_err(dev, "failed to allocate DMA memory\n");
			goto err_disable_clk;
		}
	}

	platform_set_drvdata(pdev, priv);
	priv->resource_mem = r;
	priv->dev = dev;
	pxa3xx_gcu_reset(priv);
	pxa3xx_gcu_init_debug_timer(priv);

	dev_info(dev, "registered @0x%p, DMA 0x%p (%d bytes), IRQ %d\n",
			(void *) r->start, (void *) priv->shared_phys,
			SHARED_SIZE, irq);
	return 0;

err_disable_clk:
	clk_disable_unprepare(priv->clk);

err_misc_deregister:
	misc_deregister(&priv->misc_dev);

err_free_dma:
	dma_free_coherent(dev, SHARED_SIZE,
			  priv->shared, priv->shared_phys);

	return ret;
}