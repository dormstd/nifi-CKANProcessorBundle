
package net.atos.qrowd.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

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


public class Package_ {

    @SerializedName("license_title")
    @Expose
    private String licenseTitle;
    @SerializedName("maintainer")
    @Expose
    private String maintainer;
    @SerializedName("relationships_as_object")
    @Expose
    private List<Object> relationshipsAsObject = null;
    @SerializedName("private")
    @Expose
    private Boolean _private;
    @SerializedName("maintainer_email")
    @Expose
    private String maintainerEmail;
    @SerializedName("num_tags")
    @Expose
    private Integer numTags;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("metadata_created")
    @Expose
    private String metadataCreated;
    @SerializedName("metadata_modified")
    @Expose
    private String metadataModified;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("author_email")
    @Expose
    private String authorEmail;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("creator_user_id")
    @Expose
    private String creatorUserId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("resources")
    @Expose
    private List<Resource> resources = null;
    @SerializedName("num_resources")
    @Expose
    private Integer numResources;
    @SerializedName("tags")
    @Expose
    private List<Tag> tags = null;
    @SerializedName("groups")
    @Expose
    private List<Object> groups = null;
    @SerializedName("license_id")
    @Expose
    private String licenseId;
    @SerializedName("relationships_as_subject")
    @Expose
    private List<Object> relationshipsAsSubject = null;
    @SerializedName("organization")
    @Expose
    private Organization organization;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("isopen")
    @Expose
    private Boolean isopen;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("owner_org")
    @Expose
    private String ownerOrg;
    @SerializedName("extras")
    @Expose
    private List<Object> extras = null;
    @SerializedName("license_url")
    @Expose
    private String licenseUrl;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("revision_id")
    @Expose
    private String revisionId;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Package_() {
    }

    /**
     * 
     * @param resources
     * @param licenseId
     * @param metadataCreated
     * @param numResources
     * @param state
     * @param ownerOrg
     * @param type
     * @param revisionId
     * @param numTags
     * @param version
     * @param id
     * @param author
     * @param maintainerEmail
     * @param title
     * @param creatorUserId
     * @param organization
     * @param _private
     * @param name
     * @param groups
     * @param metadataModified
     * @param tags
     * @param isopen
     * @param authorEmail
     * @param relationshipsAsSubject
     * @param url
     * @param maintainer
     * @param extras
     * @param licenseTitle
     * @param notes
     * @param licenseUrl
     * @param relationshipsAsObject
     */
    public Package_(String licenseTitle, String maintainer, List<Object> relationshipsAsObject, Boolean _private, String maintainerEmail, Integer numTags, String id, String metadataCreated, String metadataModified, String author, String authorEmail, String state, String version, String creatorUserId, String type, List<Resource> resources, Integer numResources, List<Tag> tags, List<Object> groups, String licenseId, List<Object> relationshipsAsSubject, Organization organization, String name, Boolean isopen, String url, String notes, String ownerOrg, List<Object> extras, String licenseUrl, String title, String revisionId) {
        super();
        this.licenseTitle = licenseTitle;
        this.maintainer = maintainer;
        this.relationshipsAsObject = relationshipsAsObject;
        this._private = _private;
        this.maintainerEmail = maintainerEmail;
        this.numTags = numTags;
        this.id = id;
        this.metadataCreated = metadataCreated;
        this.metadataModified = metadataModified;
        this.author = author;
        this.authorEmail = authorEmail;
        this.state = state;
        this.version = version;
        this.creatorUserId = creatorUserId;
        this.type = type;
        this.resources = resources;
        this.numResources = numResources;
        this.tags = tags;
        this.groups = groups;
        this.licenseId = licenseId;
        this.relationshipsAsSubject = relationshipsAsSubject;
        this.organization = organization;
        this.name = name;
        this.isopen = isopen;
        this.url = url;
        this.notes = notes;
        this.ownerOrg = ownerOrg;
        this.extras = extras;
        this.licenseUrl = licenseUrl;
        this.title = title;
        this.revisionId = revisionId;
    }

    public String getLicenseTitle() {
        return licenseTitle;
    }

    public void setLicenseTitle(String licenseTitle) {
        this.licenseTitle = licenseTitle;
    }

    public Package_ withLicenseTitle(String licenseTitle) {
        this.licenseTitle = licenseTitle;
        return this;
    }

    public String getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(String maintainer) {
        this.maintainer = maintainer;
    }

    public Package_ withMaintainer(String maintainer) {
        this.maintainer = maintainer;
        return this;
    }

    public List<Object> getRelationshipsAsObject() {
        return relationshipsAsObject;
    }

    public void setRelationshipsAsObject(List<Object> relationshipsAsObject) {
        this.relationshipsAsObject = relationshipsAsObject;
    }

    public Package_ withRelationshipsAsObject(List<Object> relationshipsAsObject) {
        this.relationshipsAsObject = relationshipsAsObject;
        return this;
    }

    public Boolean getPrivate() {
        return _private;
    }

    public void setPrivate(Boolean _private) {
        this._private = _private;
    }

    public Package_ withPrivate(Boolean _private) {
        this._private = _private;
        return this;
    }

    public String getMaintainerEmail() {
        return maintainerEmail;
    }

    public void setMaintainerEmail(String maintainerEmail) {
        this.maintainerEmail = maintainerEmail;
    }

    public Package_ withMaintainerEmail(String maintainerEmail) {
        this.maintainerEmail = maintainerEmail;
        return this;
    }

    public Integer getNumTags() {
        return numTags;
    }

    public void setNumTags(Integer numTags) {
        this.numTags = numTags;
    }

    public Package_ withNumTags(Integer numTags) {
        this.numTags = numTags;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Package_ withId(String id) {
        this.id = id;
        return this;
    }

    public String getMetadataCreated() {
        return metadataCreated;
    }

    public void setMetadataCreated(String metadataCreated) {
        this.metadataCreated = metadataCreated;
    }

    public Package_ withMetadataCreated(String metadataCreated) {
        this.metadataCreated = metadataCreated;
        return this;
    }

    public String getMetadataModified() {
        return metadataModified;
    }

    public void setMetadataModified(String metadataModified) {
        this.metadataModified = metadataModified;
    }

    public Package_ withMetadataModified(String metadataModified) {
        this.metadataModified = metadataModified;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Package_ withAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public Package_ withAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
        return this;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Package_ withState(String state) {
        this.state = state;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Package_ withVersion(String version) {
        this.version = version;
        return this;
    }

    public String getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(String creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public Package_ withCreatorUserId(String creatorUserId) {
        this.creatorUserId = creatorUserId;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Package_ withType(String type) {
        this.type = type;
        return this;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public Package_ withResources(List<Resource> resources) {
        this.resources = resources;
        return this;
    }

    public Integer getNumResources() {
        return numResources;
    }

    public void setNumResources(Integer numResources) {
        this.numResources = numResources;
    }

    public Package_ withNumResources(Integer numResources) {
        this.numResources = numResources;
        return this;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Package_ withTags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public List<Object> getGroups() {
        return groups;
    }

    public void setGroups(List<Object> groups) {
        this.groups = groups;
    }

    public Package_ withGroups(List<Object> groups) {
        this.groups = groups;
        return this;
    }

    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }

    public Package_ withLicenseId(String licenseId) {
        this.licenseId = licenseId;
        return this;
    }

    public List<Object> getRelationshipsAsSubject() {
        return relationshipsAsSubject;
    }

    public void setRelationshipsAsSubject(List<Object> relationshipsAsSubject) {
        this.relationshipsAsSubject = relationshipsAsSubject;
    }

    public Package_ withRelationshipsAsSubject(List<Object> relationshipsAsSubject) {
        this.relationshipsAsSubject = relationshipsAsSubject;
        return this;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Package_ withOrganization(Organization organization) {
        this.organization = organization;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Package_ withName(String name) {
        this.name = name;
        return this;
    }

    public Boolean getIsopen() {
        return isopen;
    }

    public void setIsopen(Boolean isopen) {
        this.isopen = isopen;
    }

    public Package_ withIsopen(Boolean isopen) {
        this.isopen = isopen;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Package_ withUrl(String url) {
        this.url = url;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Package_ withNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public String getOwnerOrg() {
        return ownerOrg;
    }

    public void setOwnerOrg(String ownerOrg) {
        this.ownerOrg = ownerOrg;
    }

    public Package_ withOwnerOrg(String ownerOrg) {
        this.ownerOrg = ownerOrg;
        return this;
    }

    public List<Object> getExtras() {
        return extras;
    }

    public void setExtras(List<Object> extras) {
        this.extras = extras;
    }

    public Package_ withExtras(List<Object> extras) {
        this.extras = extras;
        return this;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public Package_ withLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Package_ withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getRevisionId() {
        return revisionId;
    }

    public void setRevisionId(String revisionId) {
        this.revisionId = revisionId;
    }

    public Package_ withRevisionId(String revisionId) {
        this.revisionId = revisionId;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("licenseTitle", licenseTitle).append("maintainer", maintainer).append("relationshipsAsObject", relationshipsAsObject).append("_private", _private).append("maintainerEmail", maintainerEmail).append("numTags", numTags).append("id", id).append("metadataCreated", metadataCreated).append("metadataModified", metadataModified).append("author", author).append("authorEmail", authorEmail).append("state", state).append("version", version).append("creatorUserId", creatorUserId).append("type", type).append("resources", resources).append("numResources", numResources).append("tags", tags).append("groups", groups).append("licenseId", licenseId).append("relationshipsAsSubject", relationshipsAsSubject).append("organization", organization).append("name", name).append("isopen", isopen).append("url", url).append("notes", notes).append("ownerOrg", ownerOrg).append("extras", extras).append("licenseUrl", licenseUrl).append("title", title).append("revisionId", revisionId).toString();
    }

}
