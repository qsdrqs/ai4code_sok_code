#define SNPRINTF(buf, size, fmt, ...)            sprintf((buf), (fmt), __VA_ARGS__)

static ssize_t driver_override_show(struct device *dev,
 				    struct device_attribute *attr, char *buf)
 {
 	struct platform_device *pdev = to_platform_device(dev);
 
	return SNPRINTF(buf, 1, "%s\n", pdev->driver_override);
 }
