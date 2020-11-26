package com.example.mobiledevproject.Models.RankedMaps.Sorted;

import java.util.Date;
import java.util.List;

public class SortedRankMaps {

    private List<SortedRankMap> sortedRankMaps;

    private Date date;


    public List<SortedRankMap> getSortedRankMaps() {
        return sortedRankMaps;
    }

    public void setSortedRankMaps(List<SortedRankMap> sortedRankMaps) {
        this.sortedRankMaps = sortedRankMaps;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
