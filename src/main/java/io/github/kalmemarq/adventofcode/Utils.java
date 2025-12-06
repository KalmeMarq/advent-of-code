package io.github.kalmemarq.adventofcode;

import module java.base;

public class Utils {
    private static Path jarResourcesPath;

    public static Path getJarResourcesPath() {
        if (jarResourcesPath == null) {
            try {
                URI uri = Utils.class.getResource("/.root").toURI();
                try {
                    jarResourcesPath = Path.of(uri).getParent();
                } catch (FileSystemNotFoundException _) {
                    FileSystems.newFileSystem(uri, Collections.emptyMap());
                    jarResourcesPath = Path.of(uri).getParent();
                }

            } catch (URISyntaxException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        return jarResourcesPath;
    }
}
