
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

public class Tag {

    @SerializedName("vocabulary_id")
    @Expose
    private Object vocabularyId;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Tag() {
    }

    /**
     * 
     * @param id
     * @param vocabularyId
     * @param name
     * @param state
     * @param displayName
     */
    public Tag(Object vocabularyId, String state, String displayName, String id, String name) {
        super();
        this.vocabularyId = vocabularyId;
        this.state = state;
        this.displayName = displayName;
        this.id = id;
        this.name = name;
    }

    public Object getVocabularyId() {
        return vocabularyId;
    }

    public void setVocabularyId(Object vocabularyId) {
        this.vocabularyId = vocabularyId;
    }

    public Tag withVocabularyId(Object vocabularyId) {
        this.vocabularyId = vocabularyId;
        return this;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Tag withState(String state) {
        this.state = state;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Tag withDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Tag withId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tag withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("vocabularyId", vocabularyId).append("state", state).append("displayName", displayName).append("id", id).append("name", name).toString();
    }

}
