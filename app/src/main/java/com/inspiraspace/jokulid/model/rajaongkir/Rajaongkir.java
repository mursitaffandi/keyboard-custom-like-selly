
package com.inspiraspace.jokulid.model.rajaongkir;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Rajaongkir {

    @SerializedName("destination_details")
    private DestinationDetails mDestinationDetails;
    @SerializedName("origin_details")
    private OriginDetails mOriginDetails;
    @SerializedName("query")
    private Query mQuery;
    @SerializedName("results")
    private List<Result> mResults;
    @SerializedName("status")
    private Status mStatus;

    public DestinationDetails getDestinationDetails() {
        return mDestinationDetails;
    }

    public void setDestinationDetails(DestinationDetails destinationDetails) {
        mDestinationDetails = destinationDetails;
    }

    public OriginDetails getOriginDetails() {
        return mOriginDetails;
    }

    public void setOriginDetails(OriginDetails originDetails) {
        mOriginDetails = originDetails;
    }

    public Query getQuery() {
        return mQuery;
    }

    public void setQuery(Query query) {
        mQuery = query;
    }

    public List<Result> getResults() {
        return mResults;
    }

    public void setResults(List<Result> results) {
        mResults = results;
    }

    public Status getStatus() {
        return mStatus;
    }

    public void setStatus(Status status) {
        mStatus = status;
    }

}
