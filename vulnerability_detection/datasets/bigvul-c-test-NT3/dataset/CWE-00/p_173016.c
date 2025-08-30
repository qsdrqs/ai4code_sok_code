vulnerable_func(struct display *dp)
{
 if (dp->write_pp != NULL)
         png_destroy_write_struct(&dp->write_pp, NULL);
}
