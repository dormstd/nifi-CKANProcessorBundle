
package net.atos.qrowd.processors.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Resource {

    @SerializedName("url_type")
    @Expose
    private String urlType;
    @SerializedName("cache_last_updated")
    @Expose
    private Object cacheLastUpdated;
    @SerializedName("package_id")
    @Expose
    private String packageId;
    @SerializedName("webstore_last_updated")
    @Expose
    private Object webstoreLastUpdated;
    @SerializedName("file")
    @Expose
    private String file;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("size")
    @Expose
    private Object size;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("last_modified")
    @Expose
    private String lastModified;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("mimetype")
    @Expose
    private Object mimetype;
    @SerializedName("cache_url")
    @Expose
    private Object cacheUrl;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("webstore_url")
    @Expose
    private Object webstoreUrl;
    @SerializedName("mimetype_inner")
    @Expose
    private Object mimetypeInner;
    @SerializedName("position")
    @Expose
    private Integer position;
    @SerializedName("revision_id")
    @Expose
    private String revisionId;
    @SerializedName("resource_type")
    @Expose
    private Object resourceType;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Resource() {
    }

    /**
     * 
     * @param position
     * @param lastModified
     * @param hash
     * @param state
     * @param cacheUrl
     * @param packageId
     * @param format
     * @param revisionId
     * @param url
     * @param urlType
     * @param resourceType
     * @param size
     * @param mimetypeInner
     * @param id
     * @param cacheLastUpdated
     * @param mimetype
     * @param created
     * @param file
     * @param description
     * @param name
     * @param webstoreUrl
     * @param key
     * @param webstoreLastUpdated
     */
    public Resource(String urlType, Object cacheLastUpdated, String packageId, Object webstoreLastUpdated, String file, String id, Object size, String state, String hash, String description, String format, String lastModified, String key, Object mimetype, Object cacheUrl, String name, String created, String url, Object webstoreUrl, Object mimetypeInner, Integer position, String revisionId, Object resourceType) {
        super();
        this.urlType = urlType;
        this.cacheLastUpdated = cacheLastUpdated;
        this.packageId = packageId;
        this.webstoreLastUpdated = webstoreLastUpdated;
        this.file = file;
        this.id = id;
        this.size = size;
        this.state = state;
        this.hash = hash;
        this.description = description;
        this.format = format;
        this.lastModified = lastModified;
        this.key = key;
        this.mimetype = mimetype;
        this.cacheUrl = cacheUrl;
        this.name = name;
        this.created = created;
        this.url = url;
        this.webstoreUrl = webstoreUrl;
        this.mimetypeInner = mimetypeInner;
        this.position = position;
        this.revisionId = revisionId;
        this.resourceType = resourceType;
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public Resource withUrlType(String urlType) {
        this.urlType = urlType;
        return this;
    }

    public Object getCacheLastUpdated() {
        return cacheLastUpdated;
    }

    public void setCacheLastUpdated(Object cacheLastUpdated) {
        this.cacheLastUpdated = cacheLastUpdated;
    }

    public Resource withCacheLastUpdated(Object cacheLastUpdated) {
        this.cacheLastUpdated = cacheLastUpdated;
        return this;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public Resource withPackageId(String packageId) {
        this.packageId = packageId;
        return this;
    }

    public Object getWebstoreLastUpdated() {
        return webstoreLastUpdated;
    }

    public void setWebstoreLastUpdated(Object webstoreLastUpdated) {
        this.webstoreLastUpdated = webstoreLastUpdated;
    }

    public Resource withWebstoreLastUpdated(Object webstoreLastUpdated) {
        this.webstoreLastUpdated = webstoreLastUpdated;
        return this;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Resource withFile(String file) {
        this.file = file;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Resource withId(String id) {
        this.id = id;
        return this;
    }

    public Object getSize() {
        return size;
    }

    public void setSize(Object size) {
        this.size = size;
    }

    public Resource withSize(Object size) {
        this.size = size;
        return this;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Resource withState(String state) {
        this.state = state;
        return this;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Resource withHash(String hash) {
        this.hash = hash;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Resource withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Resource withFormat(String format) {
        this.format = format;
        return this;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public Resource withLastModified(String lastModified) {
        this.lastModified = lastModified;
        return this;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Resource withKey(String key) {
        this.key = key;
        return this;
    }

    public Object getMimetype() {
        return mimetype;
    }

    public void setMimetype(Object mimetype) {
        this.mimetype = mimetype;
    }

    public Resource withMimetype(Object mimetype) {
        this.mimetype = mimetype;
        return this;
    }

    public Object getCacheUrl() {
        return cacheUrl;
    }

    public void setCacheUrl(Object cacheUrl) {
        this.cacheUrl = cacheUrl;
    }

    public Resource withCacheUrl(Object cacheUrl) {
        this.cacheUrl = cacheUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Resource withName(String name) {
        this.name = name;
        return this;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Resource withCreated(String created) {
        this.created = created;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Resource withUrl(String url) {
        this.url = url;
        return this;
    }

    public Object getWebstoreUrl() {
        return webstoreUrl;
    }

    public void setWebstoreUrl(Object webstoreUrl) {
        this.webstoreUrl = webstoreUrl;
    }

    public Resource withWebstoreUrl(Object webstoreUrl) {
        this.webstoreUrl = webstoreUrl;
        return this;
    }

    public Object getMimetypeInner() {
        return mimetypeInner;
    }

    public void setMimetypeInner(Object mimetypeInner) {
        this.mimetypeInner = mimetypeInner;
    }

    public Resource withMimetypeInner(Object mimetypeInner) {
        this.mimetypeInner = mimetypeInner;
        return this;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Resource withPosition(Integer position) {
        this.position = position;
        return this;
    }

    public String getRevisionId() {
        return revisionId;
    }

    public void setRevisionId(String revisionId) {
        this.revisionId = revisionId;
    }

    public Resource withRevisionId(String revisionId) {
        this.revisionId = revisionId;
        return this;
    }

    public Object getResourceType() {
        return resourceType;
    }

    public void setResourceType(Object resourceType) {
        this.resourceType = resourceType;
    }

    public Resource withResourceType(Object resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("urlType", urlType).append("cacheLastUpdated", cacheLastUpdated).append("packageId", packageId).append("webstoreLastUpdated", webstoreLastUpdated).append("file", file).append("id", id).append("size", size).append("state", state).append("hash", hash).append("description", description).append("format", format).append("lastModified", lastModified).append("key", key).append("mimetype", mimetype).append("cacheUrl", cacheUrl).append("name", name).append("created", created).append("url", url).append("webstoreUrl", webstoreUrl).append("mimetypeInner", mimetypeInner).append("position", position).append("revisionId", revisionId).append("resourceType", resourceType).toString();
    }

}
