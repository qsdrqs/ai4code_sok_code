 static void non_vulnerable_func(bool cpu_own)
 {
	qrio_gpio_direction_output(GPIO_A, PROM_SEL_L, !cpu_own);
 }
