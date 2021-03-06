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

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Unit tests for {@link EvenlyIndentedLine}.
 * @author Mihai Andronache (amihaiemil@gmail.com)
 * @version $Id$
 * @since 1.0.0
 *
 */
public final class EvenlyIndentedLineTest {

    /**
     * EvenlyIndentedLine knows its number.
     */
    @Test
    public void knowsNumber() {
        YamlLine line = new EvenlyIndentedLine(
            new RtYamlLine("this line", 12)
        );
        MatcherAssert.assertThat(line.number(), Matchers.is(12));
    }

    /**
     * EvenlyIndentedLine can trim itself.
     */
    @Test
    public void trimmsItself() {
        YamlLine line = new EvenlyIndentedLine(
            new RtYamlLine("   this: line   ", 10)
        );
        MatcherAssert.assertThat(
            line.trimmed(), Matchers.equalTo("this: line")
        );
    }

    /**
     * EvenlyIndentedLine returns its, indentation if it's right.
     */
    @Test
    public void isWellIndented() {
        YamlLine byZero = new EvenlyIndentedLine(
            new RtYamlLine("this: line", 5)
        );
        MatcherAssert.assertThat(
            byZero.indentation(), Matchers.is(0)
        );
        YamlLine byFour = new EvenlyIndentedLine(
                new RtYamlLine("    this: line", 10)
        );
        MatcherAssert.assertThat(
            byFour.indentation(), Matchers.is(4)
        );
    }

    /**
     * EvenlyIndentedLine throws an exception when the line
     * has an odd indentation.
     */
    @Test (expected = IllegalStateException.class)
    public void isBadlyIndented() {
        YamlLine line = new EvenlyIndentedLine(
            new RtYamlLine("   odd: indent", 8)
        );
        line.indentation();
    }

}
