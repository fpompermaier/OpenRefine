/*

Copyright 2011, Google Inc.
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are
met:

    * Redistributions of source code must retain the above copyright
notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above
copyright notice, this list of conditions and the following disclaimer
in the documentation and/or other materials provided with the
distribution.
    * Neither the name of Google Inc. nor the names of its
contributors may be used to endorse or promote products derived from
this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

*/

package com.google.refine.expr.functions.html;

import java.util.Properties;

import org.json.JSONException;
import org.json.JSONWriter;
import org.jsoup.nodes.Element;

import com.google.refine.expr.EvalError;
import com.google.refine.grel.ControlFunctionRegistry;
import com.google.refine.grel.Function;
import com.google.refine.operations.OperationRegistry;
import com.google.refine.preference.PreferenceStore;

public class OwnText implements Function {

    @Override
    public Object call(Properties bindings, Object[] args) {
        if (args.length >= 1) {
            Object o1 = args[0];
            if (o1 != null && o1 instanceof Element) {
                Element e1 = (Element)o1;
                return e1.ownText();

            }else{
                return new EvalError(ControlFunctionRegistry.getFunctionName(this) + " failed as the first parameter is not an HTML Element.  Please first use parseHtml(string) and select(query) prior to using this function");
            }
        }
        return null;
    }


    @Override
    public void write(OperationRegistry opRegistry, PreferenceStore prefStore,
        JSONWriter writer, Properties options) throws JSONException {

        writer.object();
        writer.key("description"); writer.value("Gets the text owned by this HTML element only; does not get the combined text of all children.");
        writer.key("params"); writer.value("Element e");
        writer.key("returns"); writer.value("String ownText");
        writer.endObject();
    }
}

