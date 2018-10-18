
package net.atos.qrowd.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

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

public class CkanFullList {

    @SerializedName("help")
    @Expose
    private String help;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("result")
    @Expose
    private Package result;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CkanFullList() {
    }

    /**
     * 
     * @param result
     * @param help
     * @param success
     */
    public CkanFullList(String help, Boolean success, Package result) {
        super();
        this.help = help;
        this.success = success;
        this.result = result;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public CkanFullList withHelp(String help) {
        this.help = help;
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public CkanFullList withSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public Package getPackage() {
        return result;
    }

    public void setPackage(Package result) {
        this.result = result;
    }

    public CkanFullList withPackage(Package result) {
        this.result = result;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("help", help).append("success", success).append("result", result).toString();
    }

}
