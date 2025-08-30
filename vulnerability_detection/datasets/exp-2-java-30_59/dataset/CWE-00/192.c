public static void unzipFilesToPath(String jarPath, String destinationDir) throws IOException {
        File file = new File(jarPath);
        try (JarFile jar = new JarFile(file)) {

            // fist get all directories,
            // then make those directory on the destination Path
            /*for (Enumeration<JarEntry> enums = jar.entries(); enums.hasMoreElements(); ) {
                JarEntry entry = (JarEntry) enums.nextElement();

                String fileName = destinationDir + File.separator + entry.getName();
                File f = new File(fileName);

                if (fileName.endsWith("/")) {
                    f.mkdirs();
                }

            }*/

            //now create all files
            for (Enumeration<JarEntry> enums = jar.entries(); enums.hasMoreElements(); ) {
                JarEntry entry = enums.nextElement();

                String fileName = destinationDir + File.separator + entry.getName();
                File f = new File(fileName);

                if (!f.getCanonicalPath().startsWith(destinationDir)) {
                    System.out.println("Zip Slip exploit detected. Skipping entry " + entry.getName());
                    continue;
                }

                File parent = f.getParentFile();
                if (!parent.exists()) {
                    parent.mkdirs();
                }

                if (!fileName.endsWith("/")) {
                    try (InputStream is = jar.getInputStream(entry);
                         FileOutputStream fos = new FileOutputStream(f)) {
                        // write contents of 'is' to 'fos'
                        while (is.available() > 0) {
                            fos.write(is.read());
                        }
                    }
                }
            }
        }
    }