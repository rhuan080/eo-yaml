/**
 * Copyright (c) 2016-2020, Mihai Emil Andronache
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 *  list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 * Neither the name of the copyright holder nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 */
package com.amihaiemil.eoyaml;

import java.util.*;

/**
 * YAML mapping implementation (rt means runtime).
 * @author Mihai Andronache (amihaiemil@gmail.com)
 * @version $Id$
 * @since 1.0.0
 * @see http://yaml.org/spec/1.2/spec.html#mapping//
 */
final class RtYamlMapping extends ComparableYamlMapping {

    /**
     * Key:value tree map (ordered keys).
     */
    private final Map<YamlNode, YamlNode> mappings =
        new TreeMap<YamlNode, YamlNode>();

    /**
     * Ctor.
     * @param entries Entries contained in this mapping.
     */
    RtYamlMapping(final Map<YamlNode, YamlNode> entries) {
        this.mappings.putAll(entries);
    }

    @Override
    public YamlMapping yamlMapping(final String key) {
        return this.yamlMapping(new BuiltPlainScalar(key));
    }

    @Override
    public YamlMapping yamlMapping(final YamlNode key) {
        final YamlNode value = this.mappings.get(key);
        final YamlMapping found;
        if (value != null && value instanceof YamlMapping) {
            found = (YamlMapping) value;
        } else {
            found = null;
        }
        return found;
    }

    @Override
    public YamlSequence yamlSequence(final String key) {
        return this.yamlSequence(new BuiltPlainScalar(key));
    }

    @Override
    public YamlSequence yamlSequence(final YamlNode key) {
        final YamlNode value = this.mappings.get(key);
        final YamlSequence found;
        if (value != null && value instanceof YamlSequence) {
            found =  (YamlSequence) value;
        } else {
            found = null;
        }
        return found;
    }

    @Override
    public String string(final String key) {
        return this.string(new BuiltPlainScalar(key));
    }

    @Override
    public String string(final YamlNode key) {
        final YamlNode value = this.mappings.get(key);
        final String found;
        if (value != null && value instanceof Scalar) {
            found = ((Scalar) value).value();
        } else {
            found = null;
        }
        return found;
    }

    @Override
    public Collection<YamlNode> values() {
        return this.mappings.values();
    }

    @Override
    public String toString() {
        return this.indent(0);
    }

    @Override
    public Set<YamlNode> keys() {
        final Set<YamlNode> keys = new TreeSet<>();
        keys.addAll(this.mappings.keySet());
        return keys;
    }

    @Override
    public YamlNode value(final YamlNode key) {
        return this.mappings.get(key);
    }
}
