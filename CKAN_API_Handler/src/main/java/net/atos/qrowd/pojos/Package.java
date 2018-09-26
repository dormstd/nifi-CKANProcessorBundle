
package net.atos.qrowd.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Package {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("sort")
    @Expose
    private String sort;
    @SerializedName("facets")
    @Expose
    private Facets facets;
    @SerializedName("results")
    @Expose
    private List<Package_> results = null;
    @SerializedName("search_facets")
    @Expose
    private SearchFacets searchFacets;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Package() {
    }

    /**
     * 
     * @param searchFacets
     * @param sort
     * @param results
     * @param count
     * @param facets
     */
    public Package(Integer count, String sort, Facets facets, List<Package_> results, SearchFacets searchFacets) {
        super();
        this.count = count;
        this.sort = sort;
        this.facets = facets;
        this.results = results;
        this.searchFacets = searchFacets;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Package withCount(Integer count) {
        this.count = count;
        return this;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Package withSort(String sort) {
        this.sort = sort;
        return this;
    }

    public Facets getFacets() {
        return facets;
    }

    public void setFacets(Facets facets) {
        this.facets = facets;
    }

    public Package withFacets(Facets facets) {
        this.facets = facets;
        return this;
    }

    public List<Package_> getPackages() {
        return results;
    }

    public void setPackages(List<Package_> results) {
        this.results = results;
    }

    public Package withPackages(List<Package_> results) {
        this.results = results;
        return this;
    }

    public SearchFacets getSearchFacets() {
        return searchFacets;
    }

    public void setSearchFacets(SearchFacets searchFacets) {
        this.searchFacets = searchFacets;
    }

    public Package withSearchFacets(SearchFacets searchFacets) {
        this.searchFacets = searchFacets;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("count", count).append("sort", sort).append("facets", facets).append("results", results).append("searchFacets", searchFacets).toString();
    }

}
