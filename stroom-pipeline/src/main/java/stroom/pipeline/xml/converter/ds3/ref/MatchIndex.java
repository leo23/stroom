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

package stroom.pipeline.xml.converter.ds3.ref;

public class MatchIndex {
    private final int index;
    private final boolean offset;

    public MatchIndex(final int index, final boolean offset) {
        this.index = index;
        this.offset = offset;
    }

    public int getIndex() {
        return index;
    }

    public boolean isOffset() {
        return offset;
    }
}
