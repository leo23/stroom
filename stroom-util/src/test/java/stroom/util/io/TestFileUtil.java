/*
 * Copyright 2016 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package stroom.util.io;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import stroom.util.concurrent.SimpleExecutor;

import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

class TestFileUtil {
    @Test
    void testMkdirs() throws InterruptedException {
        final String tempDir = FileUtil.getTempDir().toAbsolutePath().toString();
        final String rootDir = tempDir + "/TestFileUtil_" + System.currentTimeMillis();

        final String[] dirArray = new String[10];
        for (int i = 0; i < dirArray.length; i++) {
            dirArray[i] = buildDir(rootDir);
        }
        final AtomicBoolean exception = new AtomicBoolean(false);

        final SimpleExecutor simpleExecutor = new SimpleExecutor(4);
        for (int i = 0; i < 200; i++) {
            final int count = i;
            simpleExecutor.execute(() -> {
                try {
                    final String dir = dirArray[count % dirArray.length];
                    System.out.println(dir);
                    FileUtil.mkdirs(Paths.get(dir));
                } catch (final RuntimeException e) {
                    e.printStackTrace();
                    exception.set(true);
                }
            });
        }
        simpleExecutor.waitForComplete();
        simpleExecutor.stop(false);

        assertThat(exception.get()).isFalse();

        FileUtil.deleteDir(Paths.get(rootDir));
    }

    private String buildDir(final String rootDir) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(rootDir);
        for (int i = 0; i < 10; i++) {
            stringBuilder.append("/");
            stringBuilder.append(RandomUtils.nextInt(0, 10));
        }
        final String dirToCreate = stringBuilder.toString();
        return dirToCreate;
    }

    @Test
    void testMkdirsUnableToCreate() {
        try {
            FileUtil.mkdirs(Paths.get("/dev/null"));
            fail("Not expecting that this directory can be created");
        } catch (final RuntimeException e) {
            // Ignore.
        }
    }
}
