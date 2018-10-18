
package net.atos.qrowd.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Copyright 2018 Atos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class Result {

    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("results")
    @Expose
    private List<Result_> results = null;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Result withCount(int count) {
        this.count = count;
        return this;
    }

    public List<Result_> getResults() {
        return results;
    }

    public void setResults(List<Result_> results) {
        this.results = results;
    }

    public Result withResults(List<Result_> results) {
        this.results = results;
        return this;
    }

}
