/* «•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»

    Copyright © 2011, Ingo Wechsung
    All rights reserved.
    
    Redistribution and use in source and binary forms, with or
    without modification, are permitted provided that the following
    conditions are met:
    
        Redistributions of source code must retain the above copyright
        notice, this list of conditions and the following disclaimer.
    
        Redistributions in binary form must reproduce the above
        copyright notice, this list of conditions and the following
        disclaimer in the documentation and/or other materials provided
        with the distribution. Neither the name of the copyright holder
        nor the names of its contributors may be used to endorse or
        promote products derived from this software without specific
        prior written permission. 
        
    THIS SOFTWARE IS PROVIDED BY THE
    COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
    IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
    WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
    PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER
    OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
    SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
    LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
    USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
    AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
    LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
    IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
    THE POSSIBILITY OF SUCH DAMAGE.

    «•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•»«•» */
    
package frege.rt;

// $Author$
// $Date$
// $Rev$
// $Id$

/**
 * Designates an evaluated frege value.
 * This is implemented by all non-lazy frege values.
 */
public interface Value {
    /**
     * <p>The number of the frege constructor that was applied to make this value.</p>
     * <h3>How this interface is used in code generated by frege</h3>
     * <p>The frege compiler compiles algebraic data types to interfaces that
     * extend {@link Value} and every variant of the type to a class that
     * implements it's types interface. Here is the frege <tt>Maybe</tt> type for an example:
     * <pre>
     * data Maybe a = Nothing | Just a
     * </pre>
     * <p> The corresponding java code would be: </p>
     * <pre>
     * public class Prelude {
    ...
    public static interface TMaybe&lt;Ta&gt; extends Value, Lazy&lt;TMaybe&lt;Ta&gt;&gt; {
        public Nothing&lt;Ta&gt; nothing();      // get the Nothing variant
        public Just&lt;Ta&gt;    just();         // get the Just variant

        public static final class Nothing&lt;Ta&gt; extends Constant
                                        implements TMaybe&lt;Ta&gt;,  Lazy&lt;TMaybe&lt;Ta&gt;&gt; {
            public final int _c() { return 0; }                // this is a Nothing
            public final Nothing&lt;Ta&gt; nothing() { return this; } // return me
            public final Just&lt;Ta&gt;    just()    { return null; } // no, I am not Just
            ....
        }

        public static final class Just&lt;Ta&gt;  extends Tuple1
                                      implements TMaybe&lt;Ta&gt;,  Lazy&lt;TMaybe&lt;Ta&gt;&gt; {
            public final int _c() { return 1; }                // this is a Just
            public final Nothing&lt;Ta&gt; nothing() { return null; } // wrong variant
            public final Just&lt;Ta&gt;    just()    { return this; } // return me
            ....
        }
    }
    ...
     * }
     * </pre>
     * <p> To find out if a <tt>Maybe</tt> is actually <tt>Just</tt> or <tt>Nothing</tt>
     * one can now switch on the return value of <tt>_c()</tt> or call <tt>nothing()</tt>
     * and <tt>just()</tt> in turn to see which one returns a non null value.</p>
     * <p> The whole scheme is devised so as to avoid type casting and instanceof. </p>
     * <p> Note that from javas point of view, the variant types are totally unrelated, it
     * is just that they happen to implement the same interface.</p>
     *
     *
     * @return the zero based constructor number for values of algebraic
     *          data types, or 0 for native types and functions.
     *          <p> Because product types have only one constructor,
     *          this will return 0 for every value of a product type.</p>
     */
    public int _c();  // constructor
}