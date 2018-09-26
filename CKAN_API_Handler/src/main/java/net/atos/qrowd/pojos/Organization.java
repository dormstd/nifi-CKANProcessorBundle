
package net.atos.qrowd.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Organization {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("is_organization")
    @Expose
    private Boolean isOrganization;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("revision_id")
    @Expose
    private String revisionId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("approval_status")
    @Expose
    private String approvalStatus;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Organization() {
    }

    /**
     * 
     * @param id
     * @param title
     * @param isOrganization
     * @param imageUrl
     * @param created
     * @param description
     * @param approvalStatus
     * @param name
     * @param state
     * @param type
     * @param revisionId
     */
    public Organization(String description, String created, String title, String name, Boolean isOrganization, String state, String imageUrl, String revisionId, String type, String id, String approvalStatus) {
        super();
        this.description = description;
        this.created = created;
        this.title = title;
        this.name = name;
        this.isOrganization = isOrganization;
        this.state = state;
        this.imageUrl = imageUrl;
        this.revisionId = revisionId;
        this.type = type;
        this.id = id;
        this.approvalStatus = approvalStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Organization withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Organization withCreated(String created) {
        this.created = created;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Organization withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Organization withName(String name) {
        this.name = name;
        return this;
    }

    public Boolean getIsOrganization() {
        return isOrganization;
    }

    public void setIsOrganization(Boolean isOrganization) {
        this.isOrganization = isOrganization;
    }

    public Organization withIsOrganization(Boolean isOrganization) {
        this.isOrganization = isOrganization;
        return this;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Organization withState(String state) {
        this.state = state;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Organization withImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getRevisionId() {
        return revisionId;
    }

    public void setRevisionId(String revisionId) {
        this.revisionId = revisionId;
    }

    public Organization withRevisionId(String revisionId) {
        this.revisionId = revisionId;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Organization withType(String type) {
        this.type = type;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Organization withId(String id) {
        this.id = id;
        return this;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Organization withApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("description", description).append("created", created).append("title", title).append("name", name).append("isOrganization", isOrganization).append("state", state).append("imageUrl", imageUrl).append("revisionId", revisionId).append("type", type).append("id", id).append("approvalStatus", approvalStatus).toString();
    }

}
