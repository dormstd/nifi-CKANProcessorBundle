
package net.atos.qrowd.processors.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResourceResponse {

    @SerializedName("help")
    @Expose
    private String help;
    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("result")
    @Expose
    private Result result;

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public ResourceResponse withHelp(String help) {
        this.help = help;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ResourceResponse withSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public ResourceResponse withResult(Result result) {
        this.result = result;
        return this;
    }

}
